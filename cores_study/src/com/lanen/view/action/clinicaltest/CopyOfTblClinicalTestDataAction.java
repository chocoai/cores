package com.lanen.view.action.clinicaltest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Columns;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblClinicalTestData;
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.DictBloodCoag;
import com.lanen.model.studyplan.DictHemat;
import com.lanen.model.studyplan.DictUrine;
import com.lanen.service.clinicaltest.TblClinicalTestDataService;
import com.lanen.service.studyplan.DictBioChemService;
import com.lanen.service.studyplan.DictBloodCoagService;
import com.lanen.service.studyplan.DictHematService;
import com.lanen.service.studyplan.DictUrineService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class CopyOfTblClinicalTestDataAction extends BaseAction<TblClinicalTestData>{
	
	private static final long serialVersionUID = 6576496431290186706L;
	@Resource
	private TblClinicalTestDataService tblClinicalTestDataService;
	@Resource
	private DictBioChemService dictBioChemService;
	@Resource
	private DictHematService dictHematService;
	@Resource
	private DictBloodCoagService dictBloodCoagService;
	@Resource
	private DictUrineService dictUrineService;
	//临检检测数据     List
	private List<TblClinicalTestData>  tblClinicalTestDataList = new ArrayList<TblClinicalTestData>();
	/*转到list页面*/
	public String list(){
		String studyNoPara =request.getParameter("studyNoPara");
		String reqNoPara =request.getParameter("reqNoPara");
		ActionContext.getContext().put("studyNoPara", studyNoPara);
		ActionContext.getContext().put("reqNoPara", reqNoPara);
		return "list";
	}

	/*加载数据和表头*/
	public void loadRowsAndColumns(){
		String studyNoPara =request.getParameter("studyNoPara");
		System.out.println(studyNoPara);
		studyNoPara="LS2013-0001";
		int reqNoPara =Integer.parseInt(request.getParameter("reqNoPara"));
		reqNoPara=1;
		int testItemPara = Integer.parseInt(request.getParameter("testItemPara"));
		List<TblClinicalTestData> tblClinicalTestDataList=tblClinicalTestDataService.findByStudyNoReqNoTestItem(studyNoPara, reqNoPara, testItemPara);
		String jsonStr= tblClinicalTestDataList2RowsAndColumnsJson(tblClinicalTestDataList,testItemPara);
		writeJson(jsonStr);
	}
	
	/** 临检检测数据转成  crosstab 表格形式*/
	public  String tblClinicalTestDataList2RowsAndColumnsJson(List<TblClinicalTestData> list,int testItem){
		if(null!=list && list.size()>0){
			//动物Id号set
			Set<String> animalIdSet = new HashSet<String>();
			//列set
			Set<Columns> columnsSet = new HashSet<Columns>();
			//检验指标缩写set
			Set<String> testIndexAbbrSet = new HashSet<String>();
			Columns columns=null;

			for(TblClinicalTestData testData:list){
				testIndexAbbrSet.add(testData.getTestIndexAbbr());
				animalIdSet.add(testData.getAnimalId());
			}
			//检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			//动物Id号list(行)
			List<String> animalIdList=new ArrayList<String>(animalIdSet);
			//动物id号列表排序
			Collections.sort(animalIdList, new Comparator<String>(){
				
				public int compare(String o1, String o2) {
					return Integer.parseInt(o1)-Integer.parseInt(o2);
				}
				
			});
			
			
			//列list（列）
			List<Columns> columnsList= testIndexAbbrList2Columns(testIndexAbbrList,testItem);
			
			//数值列表
			List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
			Map<String,String> rowMap = null;
			for(String animalId:animalIdList){
				rowMap = new HashMap<String,String>();
				rowMap.put("animalId", animalId);
				for(TblClinicalTestData testData:list){
					if(testData.getAnimalId().equals(animalId)){
						rowMap.put(testData.getTestIndexAbbr().replaceAll("#", "_"), testData.getTestData());
					}
				}
				rowsList.add(rowMap);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("columns", columnsList);
			map.put("rows", rowsList);
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			return jsonStr;
		}
		return "";
	}
	/**检验指标缩写转成表头所要格式*/
    private List<Columns> testIndexAbbrList2Columns(List<String> testIndexAbbrList, int testItem) {
//    	private DictBioChemService dictBioChemService;
//    	private DictHematService dictHematService;
//    	private DictBloodCoagService dictBloodCoagService;
//    	private DictUrineService dictUrineService;
    	List<Columns> list = new ArrayList<Columns>();
    	Columns columns = null;
		columns =new Columns();
		columns.setField("animalId");
		columns.setTitle("动物Id号");
		columns.setWidth(80);
		list.add(columns);
		switch (testItem) {
		case 1:
			List<DictBioChem> dictBioChemList=dictBioChemService.findAllOrderByOrderNo();
			if(null!=dictBioChemList && dictBioChemList.size()>0){
				for(DictBioChem obj:dictBioChemList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
						columns.setField(obj.getAbbr().replaceAll("#", "_"));
						columns.setTitle(obj.getName());
						columns.setWidth(80);
						list.add(columns);
					}
				}
			}
			break;
		case 2:
			List<DictHemat> dictHematList=dictHematService.findAllOrderByOrderNo();
			if(null!=dictHematList && dictHematList.size()>0){
				for(DictHemat obj:dictHematList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
						columns.setField(obj.getAbbr().replaceAll("#", "_"));
						columns.setTitle(obj.getName());
						columns.setWidth(80);
						list.add(columns);
					}
				}
			}
			break;
		case 3:
			List<DictBloodCoag> dictBloodCoagList=dictBloodCoagService.findAllOrderByOrderNo();
			if(null!=dictBloodCoagList && dictBloodCoagList.size()>0){
				for(DictBloodCoag obj:dictBloodCoagList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
						columns.setField(obj.getAbbr().replaceAll("#", "_"));
						columns.setTitle(obj.getName());
						columns.setWidth(80);
						list.add(columns);
					}
				}
			}
			break;
		case 4:
			List<DictUrine> dicUrineList=dictUrineService.findAllOrderByOrderNo();
			if(null!=dicUrineList && dicUrineList.size()>0){
				for(DictUrine obj:dicUrineList){
					if(testIndexAbbrList.contains(obj.getAbbr())){
						columns =new Columns();
						columns.setField(obj.getAbbr().replaceAll("#", "_"));
						columns.setTitle(obj.getName());
						columns.setWidth(80);
						list.add(columns);
					}
				}
			}
			break;
		}
		return list;
	}

	//---------------------------------------------------------------------	
	public String print(){
		tblClinicalTestDataList=tblClinicalTestDataService.findAll();
		
		return "print";
	}

	//-----------------------------------------------------------------------------
	public List<TblClinicalTestData> getTblClinicalTestDataList() {
		return tblClinicalTestDataList;
	}

	public void setTblClinicalTestDataList(
			List<TblClinicalTestData> tblClinicalTestDataList) {
		this.tblClinicalTestDataList = tblClinicalTestDataList;
	}
	
	
}
