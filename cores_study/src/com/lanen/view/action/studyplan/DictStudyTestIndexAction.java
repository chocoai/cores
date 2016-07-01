package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.DictBloodCoag;
import com.lanen.model.studyplan.DictHemat;
import com.lanen.model.studyplan.DictStudyTestIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictUrine;
import com.lanen.service.studyplan.DictStudyTestIndexService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictStudyTestIndexAction extends BaseAction<DictStudyTestIndex> {

	@Resource
	private DictStudyTestIndexService dictStudyTestIndexService;
	
	private static final long serialVersionUID = 1L;
	
	private String studyTypeCode;
	//生化指标
	private List<String> dictBioChemList;
	//尿常规
	private List<String> dictUrineList;
	//血常规
	private List<String> dictHematList;
	//血凝
	private List<String> dictBloodCoagList;
	
	//生化指标待选
	private List<DictBioChem> dictBioChemListSel;
	//尿常规待选
	private List<DictUrine> dictUrineListSel;
	//血常规待选
	private List<DictHemat> dictHematListSel;
	//血凝待选
	private List<DictBloodCoag> dictBloodCoagListSel;
	//保存成功
	
	//课题缺省检验指标
	private List<DictStudyTestIndex> dictStudyTestIndexList = new ArrayList<DictStudyTestIndex>();
	
	/**
	 * 编辑
	 * @return String
	 */
	public String editUI() {
		//获取生化指标可选项
		dictBioChemListSel = dictBioChemService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictBioChemListSel", dictBioChemListSel);
		//获取尿常规可选项
		dictUrineListSel = dictUrineService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictUrineListSel", dictUrineListSel);
		//获取血常规可选项
		dictHematListSel = dictHematService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictHematListSel", dictHematListSel);
		//获取血凝可选项
		dictBloodCoagListSel = dictBloodCoagService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictBloodCoagListSel", dictBloodCoagListSel);
		
		//获取课题类别实体
		DictStudyType studyType = dictStudyTypeService.getById(studyTypeCode);
		//获取课题缺省检验指标
		dictStudyTestIndexList = dictStudyTestIndexService.getByType(studyType);
		
		dictBioChemList = new ArrayList<String>(); 
		dictUrineList = new ArrayList<String>(); 
		dictHematList = new ArrayList<String>(); 
		dictBloodCoagList = new ArrayList<String>(); 
		for(DictStudyTestIndex dictStudyTestIndexTemp :  dictStudyTestIndexList){
			if(dictStudyTestIndexTemp.getTestItem() == 1){
				dictBioChemList.add(dictStudyTestIndexTemp.getIndexName());
			}else if(dictStudyTestIndexTemp.getTestItem() == 4){
				dictUrineList.add(dictStudyTestIndexTemp.getIndexName());
			}else if (dictStudyTestIndexTemp.getTestItem() == 2) {
				dictHematList.add(dictStudyTestIndexTemp.getIndexName());
			}else if (dictStudyTestIndexTemp.getTestItem() == 3) {
				dictBloodCoagList.add(dictStudyTestIndexTemp.getIndexName());
			}
		}
		return "editUI";
	}
	
	/**
	 * 保存
	 * @return String
	 */
	public String save() {
		//初始化列表信息
		dictStudyTestIndexList = new ArrayList<DictStudyTestIndex>();
		
		//取得课题类别实体
		DictStudyType tempType = dictStudyTypeService.getById(studyTypeCode);
		
		//课题缺省检验指标实体
		DictStudyTestIndex tempTest;
		
		if(null!=dictBioChemList&&dictBioChemList.size()>0){
			//设置生化指标
			for(String bioChem : dictBioChemList){
				//初始化
				tempTest = new DictStudyTestIndex();
				//设置课题类别实体
				tempTest.setDictStudyType(tempType);
				tempTest.setTestItem(1);
				tempTest.setIndexName(bioChem);
				DictBioChem temp = dictBioChemService.getById(bioChem);
				tempTest.setIndexAbbr(temp.getAbbr());
				dictStudyTestIndexList.add(tempTest);
			}
		}
		
		//设置尿常规
		if(null!=dictUrineList&&dictUrineList.size()>0){
			for(String urine : dictUrineList){
				//初始化
				tempTest = new DictStudyTestIndex();
				//设置课题类别实体
				tempTest.setDictStudyType(tempType);
				tempTest.setTestItem(4);
				tempTest.setIndexName(urine);
				DictUrine temp = dictUrineService.getById(urine);
				tempTest.setIndexAbbr(temp.getAbbr());
				dictStudyTestIndexList.add(tempTest);
			}
		}
		
		//设置血常规
		if(null!=dictHematList&&dictHematList.size()>0){
			for(String hemat : dictHematList){
				//初始化
				tempTest = new DictStudyTestIndex();
				//设置课题类别实体
				tempTest.setDictStudyType(tempType);
				tempTest.setTestItem(2);
				tempTest.setIndexName(hemat);
				DictHemat temp = dictHematService.getById(hemat);
				tempTest.setIndexAbbr(temp.getAbbr());
				dictStudyTestIndexList.add(tempTest);
			}
		}
		
		//设置血凝
		if(null!=dictBloodCoagList&&dictBloodCoagList.size()>0){
			for(String bloodCoag : dictBloodCoagList){
				//初始化
				tempTest = new DictStudyTestIndex();
				//设置课题类别实体
				tempTest.setDictStudyType(tempType);
				tempTest.setTestItem(3);
				tempTest.setIndexName(bloodCoag);
				DictBloodCoag temp = dictBloodCoagService.getById(bloodCoag);
				tempTest.setIndexAbbr(temp.getAbbr());
				dictStudyTestIndexList.add(tempTest);
			}
		}
		
		//删除原有数据
		dictStudyTestIndexService.deleteByTypeCode(tempType);
		//插入新数据
		dictStudyTestIndexService.saveAll(dictStudyTestIndexList);
		return "save";
	}

	
	
	
	
	public String getStudyTypeCode() {
		return studyTypeCode;
	}

	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}

	public List<String> getDictBioChemList() {
		return dictBioChemList;
	}

	public void setDictBioChemList(List<String> dictBioChemList) {
		this.dictBioChemList = dictBioChemList;
	}

	public List<String> getDictUrineList() {
		return dictUrineList;
	}

	public void setDictUrineList(List<String> dictUrineList) {
		this.dictUrineList = dictUrineList;
	}

	public List<String> getDictHematList() {
		return dictHematList;
	}

	public void setDictHematList(List<String> dictHematList) {
		this.dictHematList = dictHematList;
	}

	public List<String> getDictBloodCoagList() {
		return dictBloodCoagList;
	}

	public void setDictBloodCoagList(List<String> dictBloodCoagList) {
		this.dictBloodCoagList = dictBloodCoagList;
	}

}
