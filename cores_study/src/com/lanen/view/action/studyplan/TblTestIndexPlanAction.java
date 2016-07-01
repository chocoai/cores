package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.DictBloodCoag;
import com.lanen.model.studyplan.DictHemat;
import com.lanen.model.studyplan.DictUrine;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.service.studyplan.TblTestIndexPlanService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblTestIndexPlanAction extends BaseAction<TblTestIndexPlan> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 课题计划检验指标Service
	 */
	@Resource
	private TblTestIndexPlanService tblTestIndexPlanService;

	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 课题计划检验指标列表内容
	 */
	private List<TblTestIndexPlan> tblTestIndexPlanList;
	
	/**
	 * 试验计划课题编号
	 */
	private String studyNoPara;
	
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
	
	private String success;
	
	//课题成员标记
	private String member;
	/**
	 * 列表显示
	 * @return
	 */
	public String testIndexPlanList() {
		//获取生化指标可选项(准备生化指标可选项)
		dictBioChemListSel = dictBioChemService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictBioChemListSel", dictBioChemListSel);
		
		//获取尿常规可选项（贮备尿常规可选项）
		dictUrineListSel = dictUrineService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictUrineListSel", dictUrineListSel);
		
		//获取血常规可选项（准备血常规可选项）
		dictHematListSel = dictHematService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictHematListSel", dictHematListSel);
		
		//获取血凝可选项（准备血凝可选项）
		dictBloodCoagListSel = dictBloodCoagService.findAllOrderByOrderNo();
		ActionContext.getContext().put("dictBloodCoagListSel", dictBloodCoagListSel);
		
		//默认设置
		//获得试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获得已设置项目
		tblTestIndexPlanList = tblTestIndexPlanService.getByStudyNo(studyPlan);
		dictBioChemList = new ArrayList<String>(); 
		dictUrineList = new ArrayList<String>(); 
		dictHematList = new ArrayList<String>(); 
		dictBloodCoagList = new ArrayList<String>(); 
		//判断是否有已设置项目，如果有则显示已设置项目
		if(tblTestIndexPlanList != null && !tblTestIndexPlanList.isEmpty()){
			for(TblTestIndexPlan testIndexPlan :  tblTestIndexPlanList){
				if(testIndexPlan.getTestItem() == 1){
					dictBioChemList.add(testIndexPlan.getTestIndex());
				}else if(testIndexPlan.getTestItem() == 4){
					dictUrineList.add(testIndexPlan.getTestIndex());
				}else if (testIndexPlan.getTestItem() == 2) {
					dictHematList.add(testIndexPlan.getTestIndex());
				}else if (testIndexPlan.getTestItem() == 3) {
					dictBloodCoagList.add(testIndexPlan.getTestIndex());
				}
			}
		}
		ActionContext.getContext().put("studyState", studyPlan.getStudyState());
		ActionContext.getContext().put("left_member", member);
		return "testIndexPlanList";
	}
	
	
	/**
	 * 保存
	 * @return
	 */
	public void addSave() {
		List<TblTestIndexPlan> testIndexPlanList = new ArrayList<TblTestIndexPlan>();
		//获得试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		
		//设置生化指标
		if(null!=dictBioChemList &&dictBioChemList.size()>0){
			for(String bioChem : dictBioChemList){
				//初始化
				TblTestIndexPlan tempTest = new TblTestIndexPlan();
				//设置课题类别实体
				tempTest.setTblStudyPlan(studyPlan);
				tempTest.setTestItem(1);
				tempTest.setTestIndex(bioChem);
				DictBioChem temp = dictBioChemService.getById(bioChem);
				tempTest.setTestIndexAbbr(temp.getAbbr());
				tempTest.setPrecision(temp.getPrecision());
				testIndexPlanList.add(tempTest);
			}
		}
		//设置血常规
		if(null!=dictHematList &&dictHematList.size()>0){
			for(String hemat : dictHematList){
				//初始化
				TblTestIndexPlan tempTest = new TblTestIndexPlan();
				//设置课题类别实体
				tempTest.setTblStudyPlan(studyPlan);
				tempTest.setTestItem(2);
				tempTest.setTestIndex(hemat);
				DictHemat temp = dictHematService.getById(hemat);
				tempTest.setTestIndexAbbr(temp.getAbbr());
				tempTest.setPrecision(temp.getPrecision());
				testIndexPlanList.add(tempTest);
			}
		}
		//设置血凝
		if(null!=dictBloodCoagList &&dictBloodCoagList.size()>0){
			for(String bloodCoag : dictBloodCoagList){
				//初始化
				TblTestIndexPlan tempTest = new TblTestIndexPlan();
				//设置课题类别实体
				tempTest.setTblStudyPlan(studyPlan);
				tempTest.setTestItem(3);
				tempTest.setTestIndex(bloodCoag);
				DictBloodCoag temp = dictBloodCoagService.getById(bloodCoag);
				tempTest.setTestIndexAbbr(temp.getAbbr());
				testIndexPlanList.add(tempTest);
			}
		}
		//设置尿常规
		if(null!=dictUrineList &&dictUrineList.size()>0){
			for(String urine : dictUrineList){
				//初始化
				TblTestIndexPlan tempTest = new TblTestIndexPlan();
				//设置课题类别实体
				tempTest.setTblStudyPlan(studyPlan);
				tempTest.setTestItem(4);
				tempTest.setTestIndex(urine);
				DictUrine temp = dictUrineService.getById(urine);
				tempTest.setTestIndexAbbr(temp.getAbbr());
				testIndexPlanList.add(tempTest);
			}
		}
		Json json = new Json();
		if(null!=testIndexPlanList && testIndexPlanList.size()>-1){
			//删除原有数据
			tblTestIndexPlanService.deleteByStudyPlan(studyPlan);
			
			//保存新数据
			tblTestIndexPlanService.saveIndexPlans(testIndexPlanList);
			//ActionContext.getContext().put("success", "success");
			//return "addSave";
			json.setSuccess(true);
			json.setMsg("保存成功");
		}else{
			json.setMsg("请先选择指标");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}


	public List<TblTestIndexPlan> getTblTestIndexPlanList() {
		return tblTestIndexPlanList;
	}


	public void setTblTestIndexPlanList(List<TblTestIndexPlan> tblTestIndexPlanList) {
		this.tblTestIndexPlanList = tblTestIndexPlanList;
	}


	public String getStudyNoPara() {
		return studyNoPara;
	}


	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
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


	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public String getMember() {
		return member;
	}


	public void setMember(String member) {
		this.member = member;
	}
	
	
}
