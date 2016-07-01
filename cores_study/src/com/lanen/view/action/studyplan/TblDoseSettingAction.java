package com.lanen.view.action.studyplan;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.studyplan.DictDoseUnit;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.DictDoseUnitService;
//import com.lanen.service.studyplan.TblDissectPlanService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblDoseSettingAction extends BaseAction<TblDoseSetting> {

	private static final long serialVersionUID = 1L;
	
	 /**
	  * 剂量设置Service
	  */
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	/**
	 * 动物编号表Service
	 */
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	
	/**剂量单位service*/
	@Resource
	private DictDoseUnitService dictDoseUnitService;
	
	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 签字
	 */
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	//解剖计划Service
//	@Resource
//	private TblDissectPlanService tblDissectPlanService;
	
	/**
	 * 剂量设置列表显示list
	 */
	private List<TblDoseSetting> tblDoseSettingList;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	//动物编号规则
	private int animalCodeMode;
	
	private String volumeUnit;			//给药容积单位			
	private String thicknessUnit;		//给药浓度单位
	private int isNoGender;			//不分性别  0:未设置    1：分性别  2：不分性别
	private int isIndentical;		//雌雄动物剂量是否相同   0：未设置   1：相同  2：不相同
	private int doseSettingFlag;	//剂量组设计确认标记    0:未确认   1：已确认
	
	
	
	private String doseId;
	
//	/**
//	 * 剂量设置
//	 */
//	private TblDoseSetting tblDoseSetting;
	
	/**
	 * 剂量组编号
	 */
	private int dosageNumPara;
	
	/**
	 * 计量设置组数
	 */
	private int allDoseNum;
	//更新的行数
	private  int updatedLength;
	
	//课题成员标记
	private String member;
	
	
	private String studyNo ;
	private String codeId;
	private int groupId;
	private int gender;
	private String animalCode;
	
	private Integer version;
	private Date newEffectiveDate;
	
//	/**
//	 * 列表显示
//	 * @return
//	 */
//	public String doseSettingView() {
//		//根据课题编号获取试验计划
//		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
//		//获取满足条件的计量设置
//		tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
//		//剂量单位
//		ActionContext.getContext().put("dosageUnit", tblStudyPlan.getDosageUnit());
//		//试验计划状态
//		ActionContext.getContext().put("studyState", tblStudyPlan.getStudyState());
//		if(tblDoseSettingList!=null && tblDoseSettingList.size()>0){
//			ActionContext.getContext().put("tblDoseSettingListLength", tblDoseSettingList.size());
//		}else {
//			ActionContext.getContext().put("tblDoseSettingListLength", 0);
//		}
//		
//		ActionContext.getContext().put("left_member", member);
//		return "doseSettingView";
//	}
	/**
	 * 展示列表
	 */
	public String list(){
		ActionContext.getContext().put("left_member", member);
		return "list";
	}
	
	/**
	 * 加载单位列表
	 */
	public void doseUnit(){
		
		//doseUnitMapList剂量单位
		List<Map<String,String>> doseUnitMapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		map = new HashMap<String,String>();
		map.put("id","-1");
		map.put("text","&nbsp;");
		doseUnitMapList.add(map);
		List<DictDoseUnit> doseUnitList = dictDoseUnitService.getAllorderNo();
		if(null!=doseUnitList && doseUnitList.size()>0){
			for(DictDoseUnit obj:doseUnitList){
				map = new HashMap<String,String>();
				map.put("id", obj.getAbbr());
				map.put("text", obj.getAbbr());
				doseUnitMapList.add(map);
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanListToJson(doseUnitMapList);
		writeJson(jsonStr);
	}
	/**
	 * 保存基本信息
	 */
	public void saveBasicInfo(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("success", "false");
		
		if(null != studyNoPara && !"".equals(studyNoPara) && 
			animalCodeMode != 0 &&
			(isNoGender == 2 || (isNoGender == 1 && isIndentical > 0))){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			if(tblStudyPlan.getAnimalCodeMode() != 0){
				//清空剂量组
				tblDoseSettingService.deleteByStudyNo(studyNoPara);
			}
			tblStudyPlan.setAnimalCodeMode(animalCodeMode);
			tblStudyPlan.setIsNoGender(isNoGender);
			//不分雌雄
			if(isNoGender == 2){
				tblStudyPlan.setIsIndentical(1);
			}else{
				tblStudyPlan.setIsIndentical(isIndentical);
			}
			tblStudyPlan.setVolumeUnit(volumeUnit);
			tblStudyPlan.setThicknessUnit(thicknessUnit);
			tblStudyPlanService.update(tblStudyPlan);
			
			map.put("success", "true");
			map.put("msg", "剂量组基本信息保存成功！");
		}else{
			map.put("msg", "参数交互错误！");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 检查基本信息是否真被修改，若修改则返回 true,
	 */
	public void hasEditCheck(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("success", "false");
		
		if(null != studyNoPara && !"".equals(studyNoPara) && 
				animalCodeMode != 0 &&
				(isNoGender == 2 || (isNoGender == 1 && isIndentical > 0))){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			if(animalCodeMode != tblStudyPlan.getAnimalCodeMode() ){
				map.put("success", "true");
			}else if(isNoGender != tblStudyPlan.getIsNoGender() ){
				map.put("success", "true");
			}else if(isIndentical != tblStudyPlan.getIsIndentical() ){
				map.put("success", "true");
			}else if((null != volumeUnit &&!volumeUnit.equals(tblStudyPlan.getVolumeUnit()))
					||(null == volumeUnit && (null != tblStudyPlan.getVolumeUnit() && !"".equals(tblStudyPlan.getVolumeUnit().trim())))){
				map.put("success", "true");
			}else if((null != thicknessUnit &&!thicknessUnit.equals(tblStudyPlan.getThicknessUnit()))
					||(null == thicknessUnit && (null != tblStudyPlan.getThicknessUnit() && !"".equals(tblStudyPlan.getThicknessUnit().trim())))){
				map.put("success", "true");
			}
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 编辑时加载，基本信息
	 */
	public void loadBasicInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", "false");
		
		if(null != studyNoPara && !"".equals(studyNoPara)  ){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			
			if(tblStudyPlan.getAnimalCodeMode() != 0){
				map.put("success", "true");
				map.put("animalCodeMode", tblStudyPlan.getAnimalCodeMode());
				map.put("isNoGender", tblStudyPlan.getIsNoGender());
				map.put("isIndentical", tblStudyPlan.getIsIndentical());
				map.put("volumeUnit", tblStudyPlan.getVolumeUnit());
				map.put("thicknessUnit", tblStudyPlan.getThicknessUnit());
			}else{
				//在一般毒理系统中是否存在
				boolean exist = tblStudyPlanService.isExistByStudyNo_YYDB(studyNoPara);
				if(exist){
					map.put("animalCodeMode", 2+"");
				}
			}
		}else{
			map.put("msg", "参数交互错误！");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**
	 * 剂量组添加时，加载的基本信息：剂量组设计确认标记，    动物类别 ， 给药容积单位，给要浓度单位，是否不分雌雄，雌性动物剂量是否相同，剂量单位
	 */
	public void loadDoseGroupinfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", "false");
		
		if(null != studyNoPara && !"".equals(studyNoPara)  ){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
				map.put("success", "true");
				map.put("doseSettingFlag", tblStudyPlan.getDoseSettingFlag());
				map.put("animalType", tblStudyPlan.getAnimalType());
				map.put("isNoGender", tblStudyPlan.getIsNoGender());
				map.put("isIndentical", tblStudyPlan.getIsIndentical());
				map.put("volumeUnit", tblStudyPlan.getVolumeUnit());
				map.put("thicknessUnit", tblStudyPlan.getThicknessUnit());
				map.put("dosageUnit", tblStudyPlan.getDosageUnit());
				map.put("state", tblStudyPlan.getStudyState());
				map.put("animalCodeMode", tblStudyPlan.getAnimalCodeMode());
		}else{
			map.put("msg", "参数交互错误！");
		}
		
		if(version!=null&&9999!=version){
			//map.put("success", "false");
			map.put("history", "true");
		}
		
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 剂量组编辑时，加载的基本信息：剂量组设计确认标记，    动物类别 ， 给药容积单位，给要浓度单位，是否不分雌雄，雌性动物剂量是否相同，剂量单位
	 */
	public void loadEditDoseGroupinfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", "false");
		
		if(null != studyNoPara && !"".equals(studyNoPara) && null != model.getId() && !"".equals(model.getId())  ){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			TblDoseSetting tblDoseSetting = tblDoseSettingService.getById(model.getId());
				map.put("success", "true");
				map.put("doseSettingFlag", tblStudyPlan.getDoseSettingFlag());
				map.put("animalType", tblStudyPlan.getAnimalType());
				map.put("isNoGender", tblStudyPlan.getIsNoGender());
				map.put("isIndentical", tblStudyPlan.getIsIndentical());
				map.put("volumeUnit", tblStudyPlan.getVolumeUnit());
				map.put("thicknessUnit", tblStudyPlan.getThicknessUnit());
				map.put("dosageDesc", tblDoseSetting.getDosageDesc());
				map.put("dosage", tblDoseSetting.getDosage());
				map.put("femaleDosage", tblDoseSetting.getFemaleDosage());
				map.put("maleNum", tblDoseSetting.getMaleNum());
				map.put("femaleNum", tblDoseSetting.getFemaleNum());
				map.put("femaleVolume", tblDoseSetting.getFemaleVolume());
				map.put("maleVolume",tblDoseSetting.getMaleVolume());
				map.put("maleThickness", tblDoseSetting.getMaleThickness());
				map.put("femaleThickness", tblDoseSetting.getFemaleThickness());
				map.put("state", tblStudyPlan.getStudyState());
				map.put("dosageUnit", tblStudyPlan.getDosageUnit());
				
		}else{
			map.put("msg", "参数交互错误！");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**
	 * 保存剂量组信息
	 */
	public void saveDoseGroupInfo(){
		Map<String,String> json = new HashMap<String,String>();
		json.put("success", "false");
		if(null != studyNoPara && !"".equals(studyNoPara) && null != model.getDosage() && 
			!"".equals(model.getDosage()) && null != model.getDosageDesc() && !"".equals(model.getDosageDesc())){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			
			if(tblStudyPlan.getDoseSettingFlag() == 0){
				TblDoseSetting tblDoseSetting = new TblDoseSetting();
				int nextNum = tblDoseSettingService.getNextNumByStudyNo(studyNoPara);
				String id = tblDoseSettingService.getKey();
				tblDoseSetting.setId(id);
				tblDoseSetting.setDosageNum(nextNum);
				tblDoseSetting.setTblStudyPlan(tblStudyPlan);
				tblDoseSetting.setDosageDesc(model.getDosageDesc());
				tblDoseSetting.setDosage(model.getDosage());
				tblDoseSetting.setFemaleDosage(model.getFemaleDosage());
				tblDoseSetting.setMaleNum(model.getMaleNum());
				tblDoseSetting.setFemaleNum(model.getFemaleNum());
				tblDoseSetting.setMaleVolume(model.getMaleVolume());
				tblDoseSetting.setFemaleVolume(model.getFemaleVolume());
				tblDoseSetting.setMaleThickness(model.getMaleThickness());
				tblDoseSetting.setFemaleThickness(model.getFemaleThickness());
				tblDoseSettingService.save(tblDoseSetting);
				
				json.put("success", "true");
				json.put("msg", id);
			}else{
				json.put("msg","剂量组设计已确认，不能再添加剂量组！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 保存动物编号
	 */
	public void saveAnimalCode(){
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success", "false");
		if(null != studyNo && !"".equals(studyNo) && groupId > 0  && null != animalCode 
				&& !"".equals(animalCode) && gender > 0){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNo);
			
			if(tblStudyPlan.getDoseSettingFlag() == 0){
				boolean isExist = tblAnimalDetailDissectPlanService.isExistByStudyNoAnimalcode(studyNo,animalCode);
				if(!isExist){
					TblAnimalDetailDissectPlan obj = new TblAnimalDetailDissectPlan();
					String id = tblAnimalDetailDissectPlanService.getKey();
					obj.setId(id);
					obj.setTblStudyPlan(tblStudyPlan);
					obj.setGroupId(groupId);
					obj.setGender(gender);
					obj.setAnimalCode(animalCode);
					tblAnimalDetailDissectPlanService.save(obj);
					
					json.put("success", "true");
					json.put("msg", id);
					
					//查询一下个待添加的剂量组和性别   dosageNum,gender
					Map<String,Object> next = tblAnimalDetailDissectPlanService.getNextByStudyNoGroupIdGender(studyNo,groupId,gender);
					if(null != next){
						json.put("next", next);
					}else{
						json.put("next", "");
					}
					
				}else{
					json.put("msg","动物编号已存在！");
				}
			}else{
				json.put("msg","剂量组设计已确认，不能再添加动物编号！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 编辑动物编号
	 */
	public void editAnimalCode(){
		Map<String,String> json = new HashMap<String,String>();
		json.put("success", "false");
		if(null != studyNo && !"".equals(studyNo) && groupId > 0  && null != animalCode 
				&& !"".equals(animalCode) && gender > 0 && null != codeId 
				&& !"".equals(codeId)){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNo);
			
			if(tblStudyPlan.getDoseSettingFlag() == 0){
				boolean isExist = tblAnimalDetailDissectPlanService.isExistByIdStudyNoAnimalcode(studyNo,animalCode,codeId);
				if(!isExist){
					TblAnimalDetailDissectPlan obj = tblAnimalDetailDissectPlanService.getById(codeId);
					obj.setGroupId(groupId);
					obj.setGender(gender);
					obj.setAnimalCode(animalCode);
					tblAnimalDetailDissectPlanService.update(obj);
					
					json.put("success", "true");
					json.put("msg", codeId);
				}else{
					json.put("msg","动物编号已存在！");
				}
			}else{
				json.put("msg","剂量组设计已确认，不能再编辑动物编号！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 删除动物编号
	 */
	public void deleteAnimalCode(){
		Map<String,String> json = new HashMap<String,String>();
		json.put("success", "false");
		if(null != studyNo && !"".equals(studyNo)  && null != codeId 
				&& !"".equals(codeId) ){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNo);
			
			if(tblStudyPlan.getDoseSettingFlag() == 0){
				tblAnimalDetailDissectPlanService.delete(codeId);
				json.put("success", "true");
			}else{
				json.put("msg","剂量组设计已确认，不能再删除动物编号！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 更新剂量组信息
	 */
	public void updateDoseGroupInfo(){
		Map<String,String> json = new HashMap<String,String>();
		json.put("success", "false");
		if(null != studyNoPara && !"".equals(studyNoPara) && null != model.getDosage() && 
				!"".equals(model.getDosage()) && null != model.getDosageDesc() && !"".equals(model.getDosageDesc())
				&& null != doseId && !"".equals(doseId)){
			//根据课题编号获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			
			if(tblStudyPlan.getDoseSettingFlag() == 0 ){
				TblDoseSetting tblDoseSetting = tblDoseSettingService.getById(doseId);
				tblDoseSetting.setDosageDesc(model.getDosageDesc());
				tblDoseSetting.setDosage(model.getDosage());
				tblDoseSetting.setFemaleDosage(model.getFemaleDosage());
				tblDoseSetting.setMaleNum(model.getMaleNum());
				tblDoseSetting.setFemaleNum(model.getFemaleNum());
				tblDoseSetting.setMaleVolume(model.getMaleVolume());
				tblDoseSetting.setFemaleVolume(model.getFemaleVolume());
				tblDoseSetting.setMaleThickness(model.getMaleThickness());
				tblDoseSetting.setFemaleThickness(model.getFemaleThickness());
				tblDoseSettingService.update(tblDoseSetting);
				
				json.put("success", "true");
			}else if(tblStudyPlan.getDoseSettingFlag() == 1 && tblStudyPlan.getStudyState().equals("0")){
				TblDoseSetting tblDoseSetting = tblDoseSettingService.getById(doseId);
				tblDoseSetting.setDosageDesc(model.getDosageDesc());
				tblDoseSetting.setDosage(model.getDosage());
				tblDoseSetting.setFemaleDosage(model.getFemaleDosage());
				tblDoseSetting.setMaleVolume(model.getMaleVolume());
				tblDoseSetting.setFemaleVolume(model.getFemaleVolume());
				tblDoseSetting.setMaleThickness(model.getMaleThickness());
				tblDoseSetting.setFemaleThickness(model.getFemaleThickness());
				tblDoseSettingService.update(tblDoseSetting);
				
				json.put("success", "true");
			}else if(tblStudyPlan.getDoseSettingFlag() == 1 && tblStudyPlan.getStudyState().equals("3")){
				TblDoseSetting tblDoseSetting = tblDoseSettingService.getById(doseId);
				tblDoseSetting.setDosageDesc(model.getDosageDesc());
				tblDoseSetting.setDosage(model.getDosage());
				tblDoseSetting.setFemaleDosage(model.getFemaleDosage());
				tblDoseSetting.setMaleVolume(model.getMaleVolume());
				tblDoseSetting.setFemaleVolume(model.getFemaleVolume());
				tblDoseSetting.setMaleThickness(model.getMaleThickness());
				tblDoseSetting.setFemaleThickness(model.getFemaleThickness());
				tblDoseSettingService.updateAndHis(tblDoseSetting);
				
				json.put("success", "true");
			}else{
				json.put("msg","剂量组设计已确认，不能再编辑剂量组！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	public void loadVerListByStudyNo()
	{
		List<Map<String,Object>> verList = tblDoseSettingService.getVerListByStudyNo(studyNoPara);
		//[version] id ,'第'+cast(version as varchar)+'历史版本' text,[doseEffectiveDate]	
		for(Map<String,Object> map:verList){
			if( map.get("doseEffectiveDate")!=null)
			{
				map.put("doseEffectiveDate", DateUtil.dateToString((Date)map.get("doseEffectiveDate"),"yyyy-MM-dd"));
			}
		}
		if(verList==null||verList.size()==0)
		{
			verList = new ArrayList<Map<String,Object>>();
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", "9999");
			map.put("text", "现版本");
			
			verList.add(0,map);
		}
		String jsonStr=JsonPluginsUtil.beanListToJson(verList);
		writeJson(jsonStr);
	}
	
	/*表格加载数据（json）*/
	public void loadList(){
//		//根据课题编号获取试验计划
//		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
//		//获取满足条件的计量设置
//		tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
//		String[] _nory_changes={"id","dosageNum","dosageDesc","dosage","maleNum","femaleNum"};
//		String json=JsonPluginsUtil.beanListToJson(tblDoseSettingList, _nory_changes, true);
//		writeJson(json);
		
		List<Map<String,Object>> mapList = tblDoseSettingService.getMapListByStudyNo(studyNoPara);
		
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	public void loadListByStudyNoAndVer(){

		
		List<Map<String,Object>> mapList = tblDoseSettingService.getMapListByStudyNoAndVersion(studyNoPara,version);
		
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	
	/**
	 * 加载动物编号表格数据
	 */
	public void loadAnimalCodeList(){
		List<Map<String,Object>> mapList = tblAnimalDetailDissectPlanService.getMapListByStudyNo(studyNoPara);
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	/**
	 * 修改剂量设置生效日期
	 * @throws ParseException
	 */
	public void changeDoseEffectiveDateSign() throws ParseException{
		Json json = new Json();
		/**
		 * version的生效日期是存在version-1中的变更申请中的
		 */
		Integer nextVersion = 0;
		if(version==null||"9999".equals(version))//最新版本
		{
			nextVersion = tblApplyReviseService.getMaxVersionByStudyNoAndType(studyNoPara,0);
		}else
		{
			nextVersion = tblApplyReviseService.getPresVersionByStudyNoTypeAndVersion(studyNoPara,0,version);
		}
		TblApplyRevise applyRevise = tblApplyReviseService.getByStudyNoAndVersion(studyNoPara,0,nextVersion);
		if(applyRevise==null){
			 json.setSuccess(false);
		     json.setMsg("数据库中不存在该数据！");
		}else{
			applyRevise.setDoseEffectiveDate(newEffectiveDate);
			
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			es.setSigner(tempUser.getRealName());
			es.setEsType(847);
			es.setEsTypeDesc("剂量设置,修改生效日期签字确认");
		
			es.setDateTime(new Date());
			es.setEsId(tblESService.getKey("TblES"));
			esLink.setTableName("TblApplyRevise");
			esLink.setDataId(applyRevise.getId());
			esLink.setTblES(es);
			esLink.setEsType(847);
			esLink.setEsTypeDesc("剂量设置,修改生效日期签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			
		
			try{
			   tblESService.save(es);
			   tblESLinkService.save(esLink);
			   tblApplyReviseService.update(applyRevise);
			   
			   //日志录入,修改的为version的生效日期，但是存入的是version-1的申请中
			   writeLog("签字","课题："+studyNoPara+" 版本："+version+" 修改剂量设置生效时间，签字");
			   json.setSuccess(true);
			   json.setMsg("修改成功");
			}catch(Exception e){
			     json.setSuccess(false);
			     json.setMsg("与数据库交互异常");
			     System.out.println("执行失败，出错种类"+e.getMessage()+".");
			}finally{ 
			     System.out.println("执行结束");
			} 
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 删除
	 */
	public void del(){
		Json json = new Json();
		if(null != model.getId() && !"".equals(model.getId())){
			TblDoseSetting tblDoseSetting = tblDoseSettingService.getById(model.getId());
			TblStudyPlan tblStudyPlan = tblDoseSetting.getTblStudyPlan();
			if(tblStudyPlan.getDoseSettingFlag() == 0){
				tblDoseSettingService.delete(model.getId(),tblDoseSetting.getDosageNum(),tblStudyPlan.getStudyNo());
				json.setSuccess(true);
				json.setMsg("删除成功！");
			}else{
				json.setMsg("剂量组设计已确认，不能删除！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 签字前的数据检查
	 */
	public void signCheck(){
		Json json = new Json();
		int  flag = 3;  //1:其他问题2:信息录入不全   3:没有问题  4:动物编号数量与剂量组动物数量不一致
		if(null != studyNoPara && !"".equals(studyNoPara)){
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			List<TblDoseSetting> tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
			if(null != tblDoseSettingList && tblDoseSettingList.size()>0){
				if(tblStudyPlan.getDoseSettingFlag() == 0){
					String animalType = tblStudyPlan.getAnimalType();
					//1:有无动物
					if(null !=animalType && !"".equals(animalType.trim()) ){
						//1.1:有动物
						int animalCodeMode = tblStudyPlan.getAnimalCodeMode();
						//2,有无动物编码规则
						if(animalCodeMode >0){
							//2.1有动物编码规则
							if(animalCodeMode == 9){
								json.setMsg("9");
								//检查动物数量与剂量组动物动物数量一致（分雌雄）
								//Map   中  groupId,gender,number
								List<Map<String,Object>> mapList = tblAnimalDetailDissectPlanService.getNumberMapListByStudyNo(studyNoPara);
								if(null != mapList && mapList.size() > 0 ){
									Integer count1 = 0;
									Integer count2 = 0;
									for(TblDoseSetting obj:tblDoseSettingList){
										//对应动物数量一致
										boolean exist_1 = false;
										boolean exist_2 = false;
										count1 = count1+obj.getMaleNum()+obj.getFemaleNum();
										count2 = 0;
										for(Map<String,Object> map:mapList){
											Integer groupId = (Integer) map.get("groupId");
											Integer gender = (Integer) map.get("gender");
											Integer number = (Integer) map.get("number");
											count2 = count2 + number;
											if(obj.getMaleNum() == 0 ){
												exist_1 = true;
											}
											if(obj.getFemaleNum() == 0 ){
												exist_2 = true;
											}
											if(obj.getDosageNum() == groupId){
												if(gender == 1 && number == obj.getMaleNum()){
													exist_1 = true;
												}
												if(gender == 2 && number == obj.getFemaleNum()){
													exist_2 = true;
												}
											}
										}
										if(!exist_1 || !exist_2 ){
											flag = 4;
											break;
										}
									}
									if(count1 != count2){
										flag = 4;
									}
								}else{
									flag = 4;
								}
							}
							//每组动物和 > 0
							for(TblDoseSetting obj:tblDoseSettingList){
								if(obj.getMaleNum() <1 && obj.getFemaleNum()<1){
									flag = 2;
									break;
								}
							}
							if(flag == 3){
								int isNoGender = tblStudyPlan.getIsNoGender();
								//3，是否分雌性
								if(isNoGender ==1){
									//3.1分雌性
									int isIndentical = tblStudyPlan.getIsIndentical();
									//4，雌雄剂量是否相同
									if(isIndentical ==1){
										//4.1相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}
											}
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}
											}
										}
									}else if(isIndentical ==2){
										//4.2不相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleDosage() || "".equals(tblDoseSetting.getFemaleDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleVolume() || "".equals(tblDoseSetting.getFemaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleThickness() || "".equals(tblDoseSetting.getFemaleThickness())){
													flag = 2;
													break;
												}
											}
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleDosage() || "".equals(tblDoseSetting.getFemaleDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}
											}
										}
									}else{
										flag = 1;
										json.setMsg("请先设置剂量组基本信息！");
									}
								}else if(isNoGender ==2){
									//3.2不分雌性
									int isIndentical = tblStudyPlan.getIsIndentical();
									//4，雌雄剂量是否相同
									if(isIndentical ==1){
										//4.1相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}
											}
											
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
												}
											}
										}
									}else if(isIndentical ==2){
										flag = 1;
										json.setMsg("请重新设置剂量组基本信息！");
									}else{
										flag = 1;
										json.setMsg("请先设置剂量组基本信息！");
									}
								}else{
									flag = 1;
									json.setMsg("请先设置剂量组基本信息！");
								}
							}
						}else{
							//2.2无动物编码规则
							flag =1;
							json.setMsg("请先设置剂量组基本信息！");
						}
					}else{
						//1.2无动物
						for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
							if(tblDoseSetting.getDosageNum() == 0){
								flag = 2;
								break;
							}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
								flag = 2;
								break;
							}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
								flag = 2;
								break;
							}
						}
					}
				}else{
					flag =1;
					json.setMsg("剂量组设计已确认！");
				}
			}else{
				flag =1;
				json.setMsg("剂量组还未设置");
			}
		}else{
			flag =1;
			json.setMsg("与服务器交互错误！");
		}
		if(flag == 2){
			json.setMsg("剂量组数据录入不全，请检查！");
		}else if(flag == 3){
			json.setSuccess(true);
			
		}else if(flag == 4){
			json.setMsg("动物编号设置内容与剂量组信息不一致！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 签字确认
	 */
	public void signConfirm(){
		Json json = new Json();
		int  flag = 3;  //1:其他问题2:信息录入不全   3:没有问题
		TblStudyPlan tblStudyPlan = null;
		List<TblDoseSetting> tblDoseSettingList = null;
		if(null != studyNoPara && !"".equals(studyNoPara)){
			tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
			if(null != tblDoseSettingList && tblDoseSettingList.size()>0){
				if(tblStudyPlan.getDoseSettingFlag() == 0){
					String animalType = tblStudyPlan.getAnimalType();
					//1:有无动物
					if(null !=animalType && !"".equals(animalType.trim()) ){
						//1.1:有动物
						int animalCodeMode = tblStudyPlan.getAnimalCodeMode();
						//2,有无动物编码规则
						if(animalCodeMode >0){
							//2.1有动物编码规则
							if(animalCodeMode == 9){
								//检查动物数量与剂量组动物动物数量一致（分雌雄）
								//Map   中  groupId,gender,number
								List<Map<String,Object>> mapList = tblAnimalDetailDissectPlanService.getNumberMapListByStudyNo(studyNoPara);
								if(null != mapList && mapList.size() > 0 ){
									Integer count1 = 0;
									Integer count2 = 0;
									for(TblDoseSetting obj:tblDoseSettingList){
										//对应动物数量一致
										boolean exist_1 = false;
										boolean exist_2 = false;
										count1 = count1+obj.getMaleNum()+obj.getFemaleNum();
										count2 = 0;
										for(Map<String,Object> map:mapList){
											Integer groupId = (Integer) map.get("groupId");
											Integer gender = (Integer) map.get("gender");
											Integer number = (Integer) map.get("number");
											count2 = count2 + number;
											if(obj.getMaleNum() == 0 ){
												exist_1 = true;
											}
											if(obj.getFemaleNum() == 0 ){
												exist_2 = true;
											}
											if(obj.getDosageNum() == groupId){
												if(gender == 1 && number == obj.getMaleNum()){
													exist_1 = true;
												}
												if(gender == 2 && number == obj.getFemaleNum()){
													exist_2 = true;
												}
											}
										}
										if(!exist_1 || !exist_2 ){
											flag = 4;
											break;
										}
									}
									if(count1 != count2){
										flag = 4;
									}
								}else{
									flag = 4;
								}
							}
							if(flag != 4){
								int isNoGender = tblStudyPlan.getIsNoGender();
								//3，是否分雌性
								if(isNoGender ==1){
									//3.1分雌性
									int isIndentical = tblStudyPlan.getIsIndentical();
									//4，雌雄剂量是否相同
									if(isIndentical ==1){
										//4.1相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum()+tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}
											}
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
//											}else if(tblDoseSetting.getMaleNum() <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}
											}
										}
									}else if(isIndentical ==2){
										//4.2不相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleDosage() || "".equals(tblDoseSetting.getFemaleDosage())){
													flag = 2;
													break;
//											}else if((tblDoseSetting.getMaleNum()) <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleVolume() || "".equals(tblDoseSetting.getFemaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleThickness() || "".equals(tblDoseSetting.getFemaleThickness())){
													flag = 2;
													break;
												}
											}
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getFemaleDosage() || "".equals(tblDoseSetting.getFemaleDosage())){
													flag = 2;
													break;
//											}else if((tblDoseSetting.getMaleNum()+tblDoseSetting.getFemaleNum()) <1 ){
//												flag = 2;
//												break;
//											}else if(tblDoseSetting.getFemaleNum() <1 ){
//												flag = 2;
//												break;
												}
											}
										}
									}else{
										flag = 1;
										json.setMsg("请先设置剂量组基本信息！");
									}
								}else if(isNoGender ==2){
									//3.2不分雌性
									int isIndentical = tblStudyPlan.getIsIndentical();
									//4，雌雄剂量是否相同
									if(isIndentical ==1){
										//4.1相同
										//5.是否有容积单位
										String volumeUnit = tblStudyPlan.getVolumeUnit();
										if( null != volumeUnit && !"".equals(volumeUnit.trim())){
											//有
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
											}else if(tblDoseSetting.getMaleNum() <1 ){
												flag = 2;
												break;
												}else if(null == tblDoseSetting.getMaleVolume() || "".equals(tblDoseSetting.getMaleVolume())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getMaleThickness() || "".equals(tblDoseSetting.getMaleThickness())){
													flag = 2;
													break;
												}
											}
											
										}else{
											//无
											for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
												if(tblDoseSetting.getDosageNum() == 0){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
													flag = 2;
													break;
												}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
													flag = 2;
													break;
											}else if(tblDoseSetting.getMaleNum() <1 ){
												flag = 2;
												break;
												}
											}
										}
									}else if(isIndentical ==2){
										flag = 1;
										json.setMsg("请重新设置剂量组基本信息！");
									}else{
										flag = 1;
										json.setMsg("请先设置剂量组基本信息！");
									}
								}else{
									flag = 1;
									json.setMsg("请先设置剂量组基本信息！");
								}
								
							}
							
						}else{
							//2.2无动物编码规则
							flag =1;
							json.setMsg("请先设置剂量组基本信息！");
						}
					}else{
						//1.2无动物
						for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
							if(tblDoseSetting.getDosageNum() == 0){
								flag = 2;
								break;
							}else if(null == tblDoseSetting.getDosageDesc() || "".equals(tblDoseSetting.getDosageDesc())){
								flag = 2;
								break;
							}else if(null == tblDoseSetting.getDosage() || "".equals(tblDoseSetting.getDosage())){
								flag = 2;
								break;
							}
						}
					}
				}else{
					flag =1;
					json.setMsg("剂量组设计已确认！");
				}
			}else{
				flag =1;
				json.setMsg("剂量组还未设置");
			}
		}else{
			flag =1;
			json.setMsg("与服务器交互错误！");
		}
		if(flag == 2){
			json.setMsg("剂量组数据录入不全，请检查！");
		}else if(flag == 3){
			tblStudyPlan.setDoseSettingFlag(1);
			if(null != tblStudyPlan.getAnimalType() && !"".equals(tblStudyPlan.getAnimalType().trim()) && tblStudyPlan.getAnimalCodeMode() != 9){
				tblDoseSettingService.createAnimalCodeAll(tblDoseSettingList,tblStudyPlan);
			}
			tblStudyPlanService.update(tblStudyPlan);
			json.setSuccess(true);
			json.setMsg("剂量组设计确认成功！");
		}else if(flag == 4){
			json.setMsg("动物编号设置内容与剂量组信息不一致！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 加载 剂量组说明 列表
	 * 
	 * */
	public void dosageDesc(){
		List<String> list = tblDoseSettingService.getdosageDescList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj);
				map.put("text", obj);
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	/**
	 * 加载 剂量组列表（当前课题的）
	 * 
	 * */
	public void dosageNum(){
		List<TblDoseSetting> list = tblDoseSettingService.getListByStudyNo(studyNoPara);
		if(null!=list && list.size()>0){
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map =null;
			for(TblDoseSetting obj:list){
				map =  new HashMap<String,Object>();
				map.put("id", obj.getDosageNum());
				map.put("text", obj.getDosageDesc());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	
	/**
	 * 上移
	 */
	public void upMove(){
		Json json = new Json();
		if(null != studyNoPara && model.getDosageNum() != 0){
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			if(null != tblStudyPlan){
				if(tblStudyPlan.getDoseSettingFlag() == 0){
					tblDoseSettingService.upMove(studyNoPara,model.getDosageNum());
					json.setSuccess(true);
					json.setMsg("上移成功！");
				}else{
					json.setMsg("与服务器交互错误！");
				}
			}else{
				json.setMsg("与服务器交互错误！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 下移
	 */
	public void downMove(){
		Json json = new Json();
		if(null != studyNoPara && model.getDosageNum() != 0){
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			if(null != tblStudyPlan){
				if(tblStudyPlan.getDoseSettingFlag() == 0){
					tblDoseSettingService.downMove(studyNoPara,model.getDosageNum());
					json.setSuccess(true);
					json.setMsg("下移成功！");
				}else{
					json.setMsg("与服务器交互错误！");
				}
			}else{
				json.setMsg("与服务器交互错误！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	
	/**设置剂量组*/
	public void newDosageNum(){
		
		if(studyNoPara !=null && !"".equals(studyNoPara) && animalCodeMode!=0 && dosageNumPara>0){
			tblStudyPlanService.update(studyNoPara,animalCodeMode);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map=null;
			for(int i=0;i<dosageNumPara;i++){
				map = new HashMap<String,String>();
				map.put("dosageNum", i+1+"");
				switch (i) {
				case 0:map.put("dosageDesc", "对照组");
				break;
				case 1:map.put("dosageDesc","低剂量组");
				break;
				case 2:map.put("dosageDesc", "中剂量组");
				break;
				case 3:map.put("dosageDesc", "高剂量组");
				break;
				default:map.put("dosageDesc", "其他剂量组");
				break;
				}
				map.put("dosage", "");
				map.put("maleNum", "");
				map.put("femaleNum","");
				list.add(map);
			}
			String jsonStr = JsonPluginsUtil.listToJson(list);
			writeJson(jsonStr);
		}
		
	}
	
	/** 保存 刚建的分组到Session里(json)	 */
	@SuppressWarnings("unchecked")
	public void addNewList(){
		//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		TblDoseSetting tblDoseSetting=model;
		if(null!=tblStudyPlan && null!=tblDoseSetting){
			tblDoseSetting.setTblStudyPlan(tblStudyPlan);
			
			tblDoseSettingList =(List<TblDoseSetting>) ActionContext.getContext().getSession().get("tblDoseSettingList");
			if(null == tblDoseSettingList || tblDoseSettingList.size()<1){
				tblDoseSettingList= new ArrayList<TblDoseSetting>();
			}else if(!tblDoseSettingList.get(0).getTblStudyPlan().getStudyNo().equals(studyNoPara)){
				Json json = new Json();
				json.setSuccess(false);
				json.setMsg("保存失败");
				String jsonStr= JsonPluginsUtil.beanToJson(json);
				writeJson(jsonStr);
			}
			tblDoseSettingList.add(tblDoseSetting);
			ActionContext.getContext().getSession().put("tblDoseSettingList", tblDoseSettingList);
			//tblDoseSettingService.save(tblDoseSetting);
			Json json = new Json();
			json.setSuccess(true);
			json.setMsg("保存成功");
			String jsonStr= JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}
	}
	
	/** 保存Session里的 tblDoseSettingList*/
	@SuppressWarnings("unchecked")
	public void saveOrupdateAll(){
		if(updatedLength<1){
			//移除session里的tblDoseSettingList
			ActionContext.getContext().getSession().remove("tblDoseSettingList");
			Json json = new Json();
			json.setSuccess(true);
			json.setMsg("移除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}else{
			//获取试验计划
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			String state = tblStudyPlan.getStudyState();
			tblDoseSettingList = (List<TblDoseSetting>) ActionContext.getContext().getSession().get("tblDoseSettingList");
			if(state.equals("0")){
				//未提交
				if (tblDoseSettingList!=null && tblDoseSettingList.size()>0 ) {
					//判断是否是该试验计划下的
					if(tblDoseSettingList.get(0).getTblStudyPlan().getStudyNo().equals(tblStudyPlan.getStudyNo())){
						//移除session里的tblDoseSettingList
						ActionContext.getContext().getSession().remove("tblDoseSettingList");
						if(null!=tblDoseSettingList.get(0).getId() && !"".equals(tblDoseSettingList.get(0).getId())){
							//更新
							tblDoseSettingService.updateAll(tblDoseSettingList, tblStudyPlan);
							Json json = new Json();
							json.setSuccess(true);
							json.setMsg("update");
							String jsonStr = JsonPluginsUtil.beanToJson(json);
							writeJson(jsonStr);
						}else if( tblDoseSettingList.size()==updatedLength){//TODO
							//保存
							tblDoseSettingService.saveAll(tblDoseSettingList);
							List<TblDoseSetting> tblDoseSettingList2 = tblDoseSettingService.getByStudyNo(tblStudyPlan);
							String[] _nory_changes={"id","dosageNum","dosageDesc","dosage","maleNum","femaleNum"};
							String json=JsonPluginsUtil.beanListToJson(tblDoseSettingList2, _nory_changes, true);
							writeJson(json);
						}else{
							//失败
							
						}
					}
				}
			}else{
				//在编辑
				if (tblDoseSettingList!=null && tblDoseSettingList.size()>0 ) {
					//判断是否是该试验计划下的
					if(tblDoseSettingList.get(0).getTblStudyPlan().getStudyNo().equals(tblStudyPlan.getStudyNo())){
						//移除session里的tblDoseSettingList
						ActionContext.getContext().getSession().remove("tblDoseSettingList");
						if(null!=tblDoseSettingList.get(0).getId() && !"".equals(tblDoseSettingList.get(0).getId())){
							//更新
							tblDoseSettingService.updateAllINApplyRevise(tblDoseSettingList, tblStudyPlan);
							Json json = new Json();
							json.setSuccess(true);
							json.setMsg("update");
							String jsonStr = JsonPluginsUtil.beanToJson(json);
							writeJson(jsonStr);
						}else{
							//失败
							
						}
					}
				}
				
			}
			
		}
	}
//	/**更新数据（json）*/
//	public void update(){
//		TblDoseSetting tblDoseSetting=tblDoseSettingService.getById(model.getId());
//		if(null!=tblDoseSetting){
//			tblDoseSetting.setDosage(model.getDosage());
//			tblDoseSetting.setDosageDesc(model.getDosageDesc());
//			tblDoseSetting.setDosageNum(model.getMaleNum());
//			tblDoseSetting.setFemaleNum(model.getFemaleNum());
//			tblDoseSetting.setMaleNum(model.getMaleNum());
//			tblDoseSettingService.update(tblDoseSetting);
//			Json json = new Json();
//			json.setSuccess(true);
//			json.setMsg("更新成功");
//			String jsonStr= JsonPluginsUtil.beanToJson(json);
//			writeJson(jsonStr);
//		}
//	}
	
	
	
	/**
	 * ajax调用唯一性检查
	 */
	public void uniqueCheck() throws Exception {
		//获得试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		boolean flag = tblDoseSettingService.uniqueCheck(tblStudyPlan, dosageNumPara, doseId);
		if(flag){
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("no");
		} else {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("is");
		}
	}
	
	/**
	 * 合法性检查
	 * @param doseSetting
	 * @return
	 */
	public boolean nullCheck(TblDoseSetting doseSetting) {
		boolean flag = true;
		if(doseSetting.getTblStudyPlan() == null){
			flag = false;
		}else if (doseSetting.getDosageNum() == 0) {
			flag = false;
		}else if (stringCherck(doseSetting.getDosageDesc())) {
			flag = false;
		}else if (stringCherck(doseSetting.getDosage())) {
			flag = false;
		}else if (doseSetting.getMaleNum() == 0) {
			flag = false;
		}else if (doseSetting.getFemaleNum() == 0) {
			flag = false;
		}else if (!tblDoseSettingService.uniqueCheck(doseSetting.getTblStudyPlan(), doseSetting.getDosageNum(), doseSetting.getId())) {
			flag = false;
		}
		return flag;
	}
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("剂量设置");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public List<TblDoseSetting> getTblDoseSettingList() {
		return tblDoseSettingList;
	}

	public void setTblDoseSettingList(List<TblDoseSetting> tblDoseSettingList) {
		this.tblDoseSettingList = tblDoseSettingList;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}


	public String getDoseId() {
		return doseId;
	}

	public void setDoseId(String doseId) {
		this.doseId = doseId;
	}

	public int getDosageNumPara() {
		return dosageNumPara;
	}

	public void setDosageNumPara(int dosageNumPara) {
		this.dosageNumPara = dosageNumPara;
	}

	public int getAllDoseNum() {
		return allDoseNum;
	}

	public void setAllDoseNum(int allDoseNum) {
		this.allDoseNum = allDoseNum;
	}

	public int getUpdatedLength() {
		return updatedLength;
	}

	public void setUpdatedLength(int updatedLength) {
		this.updatedLength = updatedLength;
	}

	public int getAnimalCodeMode() {
		return animalCodeMode;
	}

	public void setAnimalCodeMode(int animalCodeMode) {
		this.animalCodeMode = animalCodeMode;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getThicknessUnit() {
		return thicknessUnit;
	}

	public void setThicknessUnit(String thicknessUnit) {
		this.thicknessUnit = thicknessUnit;
	}

	public int getIsNoGender() {
		return isNoGender;
	}

	public void setIsNoGender(int isNoGender) {
		this.isNoGender = isNoGender;
	}

	public int getIsIndentical() {
		return isIndentical;
	}

	public void setIsIndentical(int isIndentical) {
		this.isIndentical = isIndentical;
	}

	public int getDoseSettingFlag() {
		return doseSettingFlag;
	}

	public void setDoseSettingFlag(int doseSettingFlag) {
		this.doseSettingFlag = doseSettingFlag;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(String animalCode) {
		this.animalCode = animalCode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getNewEffectiveDate() {
		return newEffectiveDate;
	}

	public void setNewEffectiveDate(Date newEffectiveDate) {
		this.newEffectiveDate = newEffectiveDate;
	}
	
	
}
