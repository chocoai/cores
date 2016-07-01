package com.lanen.view.action.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.DictViscera;
import com.lanen.model.path.TblPathPlanCheck;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.path.DictVisceraService;
import com.lanen.service.path.TblPathPlanCheckService;
import com.lanen.service.studyplan.DictAnimalTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
@Controller
@Scope("prototype")
public class TblPathPlanCheckAction extends BaseAction<TblPathPlanCheck> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5396659437102040468L;
	/**
	 * 病理计划-脏器/组织学检查     service 
	 */
	@Resource
	private TblPathPlanCheckService tblPathPlanCheckService;
	/**
	 * 脏器字典     service 
	 */
	@Resource
	private DictVisceraService dictVisceraService;
	/**
	 * 动物种类字典     service 
	 */
	@Resource
	private DictAnimalTypeService dictAnimalTypeService;
	/**
	 * 专题计划service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	/**
	 * 病理计划-脏器/组织学检查-脏器列表
	 */
	private String visceraNames;
	/**
	 * 病理计划-脏器/组织学检查-剖检标志数组
	 */
	private String atanomyCheckFlags;
	/**
	 * 病理计划-脏器/组织学检查-固定标志数组
	 */
	private String visceraFixedFlags;
	/**
	 * 病理计划-脏器/组织学检查-镜检标志数组
	 */
	private String histopathCheckFlags;
	//异常脏器剖检标志
	private Integer abnVisceraAnatomyCheckFlag;
	//异常脏器固定标志
	private Integer abnVisceraFixedFlag;
	//异常脏器镜检标志
	private Integer abnVisceraHistopathCheckFlag;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	/**
	 * 动物种类（名）
	 */
	private String animalType;
	/**加载指定课题下病理计划-脏器/组织学检查计划*/
	public void loadList(){
		List<TblPathPlanCheck> list=tblPathPlanCheckService.getListByStudyNo(studyNoPara);
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 map.put("total", list.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	/**加载脏器列表(所有一级脏器)*/
	public void loadVisceraList(){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		DictAnimalType dictAnimalType=dictAnimalTypeService.getByName(animalType);
//		List<DictViscera> list=new ArrayList<DictViscera>();
		if(dictAnimalType!=null){
//			list=dictVisceraService.get1LListByAnimalType(dictAnimalType.getId());
			String AnimalTypeId=dictAnimalType.getId();
			mapList=tblPathPlanCheckService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara);
		}else{
			mapList=tblPathPlanCheckService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara);
//			list=dictVisceraService.get1LListByAnimalType("0");
		}
//			List<DictViscera> list1=new ArrayList<DictViscera>();
//			List<DictViscera> list2=new ArrayList<DictViscera>();
//			for(DictViscera dv:list){
//				if(dv.getpVisceraCode()==null){
//					list1.add(dv);
//				}else{
//					list2.add(dv);
//				}
//			}
//			for(DictViscera dv1:list1){
//				Map<String,Object> map=new HashMap<String, Object>();
//				List<Map<String,Object>> dataMapList1=new ArrayList<Map<String,Object>>();
//				map.put("id", dv1.getVisceraCode());
//				map.put("text", dv1.getVisceraName());
//				map.put("iconCls", "icon-space");
//				for(DictViscera dv2:list2){
//					if(dv1.getVisceraCode().equals(dv2.getpVisceraCode())){
//						Map<String,Object> map1=new HashMap<String, Object>();
//						map1.put("id", dv2.getVisceraCode());
//						map1.put("text", dv2.getVisceraName());
//						map1.put("iconCls", "icon-space");
//						dataMapList1.add(map1);
//					}
//					
//				}
//				map.put("children", dataMapList1);
//				dataMapList.add(map);
//			}
//		
//		String jsonStr = JsonPluginsUtil.beanListToJson(dataMapList);
//		writeJson(jsonStr);
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", mapList);
		 map.put("total", mapList.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	/**
	 * 添加脏器/组织学检查计划
	 */
	public void addPathPlanCheck(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<TblPathPlanCheck> list=null;
		if(null!=model.getStudyNo()&&!"".equals(model.getStudyNo())&&null!=visceraNames&&!"".equals(visceraNames)){
			list=new ArrayList<TblPathPlanCheck>();
			TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(model.getStudyNo());
			tblStudyPlan.setAbnVisceraAnatomyCheck(abnVisceraAnatomyCheckFlag);
			tblStudyPlan.setAbnVisceraFixedFlag(abnVisceraFixedFlag);
			tblStudyPlan.setAbnVisceraHistopathCheckFlag(abnVisceraHistopathCheckFlag);
			String[] visceraNamesCheck=visceraNames.split(",");
    		String[] atanomyCheckFlags1=atanomyCheckFlags.split(",");
    		String[] visceraFixedFlags1=visceraFixedFlags.split(",");
    		String[] histopathCheckFlags1=histopathCheckFlags.split(",");
    		//获得解剖申请-脏器/组织学检查序号(Sn)
			int sn=tblPathPlanCheckService.getSn(model.getStudyNo());
    		for(int i=0;i<visceraNamesCheck.length;i++){
    			TblPathPlanCheck tblPathPlanCheck=new TblPathPlanCheck();
    			String id=tblPathPlanCheckService.getKey();
    			tblPathPlanCheck.setId(id);
    			tblPathPlanCheck.setStudyNo(model.getStudyNo());
    			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesCheck[i]);
    			if(dictViscera!=null){
    				tblPathPlanCheck.setVisceraCode(dictViscera.getVisceraCode());
    				tblPathPlanCheck.setVisceraName(visceraNamesCheck[i]);
    				tblPathPlanCheck.setVisceraType(dictViscera.getVisceraType());
    				tblPathPlanCheck.setGender(dictViscera.getGender());
    			}
    			tblPathPlanCheck.setAtanomyCheckFlag(Integer.parseInt(atanomyCheckFlags1[i]));
    			tblPathPlanCheck.setVisceraFixedFlag(Integer.parseInt(visceraFixedFlags1[i]));
    			tblPathPlanCheck.setHistopathCheckFlag(Integer.parseInt(histopathCheckFlags1[i]));
    			tblPathPlanCheck.setSn(sn);
    			sn++;
    			list.add(tblPathPlanCheck);
    		}
    		if(list.size()>0){
    			tblPathPlanCheckService.addSavePathPlanCheck(tblStudyPlan,list);
    			map.put("success",true);
    		}
		}
//		TblPathPlanCheck tblPathPlanCheck=null;
//		tblPathPlanCheck=tblPathPlanCheckService.getByVisceraCode(model.getVisceraCode(),model.getStudyNo());
//		//如果当前脏器已设置病理检查计划，进行更新，否则新增
//		if(tblPathPlanCheck!=null){
//			if(model.getHistopathCheckFlag()==1){
//				//如果镜检被选中，固定也必须选择
//				tblPathPlanCheck.setAtanomyCheckFlag(1);
//				tblPathPlanCheck.setHistopathCheckFlag(1);
//			}else{
//				tblPathPlanCheck.setAtanomyCheckFlag(model.getAtanomyCheckFlag());
//				tblPathPlanCheck.setHistopathCheckFlag(model.getHistopathCheckFlag());
//			}
//			tblPathPlanCheckService.update(tblPathPlanCheck);
//		}else{
//			tblPathPlanCheck=new TblPathPlanCheck();
//			String visceraCode=model.getVisceraCode();
//			//获得所选脏器的信息
//			DictViscera dictViscera=dictVisceraService.getById(visceraCode);
//			if(dictViscera!=null){
//				tblPathPlanCheck.setVisceraCode(visceraCode);
//				tblPathPlanCheck.setVisceraName(dictViscera.getVisceraName());
//				tblPathPlanCheck.setVisceraType(dictViscera.getVisceraType());
//				tblPathPlanCheck.setGender(dictViscera.getGender());
//				String id=tblPathPlanCheckService.getKey();
//				tblPathPlanCheck.setId(id);
//				tblPathPlanCheck.setStudyNo(model.getStudyNo());
//				if(model.getHistopathCheckFlag()==1){
//					tblPathPlanCheck.setAtanomyCheckFlag(1);
//					tblPathPlanCheck.setHistopathCheckFlag(1);
//				}else{
//					tblPathPlanCheck.setAtanomyCheckFlag(model.getAtanomyCheckFlag());
//					tblPathPlanCheck.setHistopathCheckFlag(model.getHistopathCheckFlag());
//				}
//				int sn=tblPathPlanCheckService.getSn(model.getStudyNo());
//				tblPathPlanCheck.setSn(sn);
//				tblPathPlanCheckService.save(tblPathPlanCheck);
//			}
//		}
		
//		map.put("success",true);
//		map.put("msg","添加成功");
//		map.put("id", tblPathPlanCheck.getId());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 删除病理检查计划
	 */
	public void deletePathPlanCheck(){
		Map<String,Object> map = new HashMap<String, Object>();
		if(model.getId()!=null){
			tblPathPlanCheckService.delete(model.getId());
			map.put("success",true);
			map.put("msg","删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 根据课题编号得到异常脏器剖检、固定、镜检的当前设置状态
	 */
	public void getAbnVisceraStateByStudyNo(){
		Map<String,Object> map = new HashMap<String, Object>();
		if(null!=model.getStudyNo()&&!"".equals(model.getStudyNo())){
			TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(model.getStudyNo());
			map.put("abnVisceraAnatomyCheck", tblStudyPlan.getAbnVisceraAnatomyCheck());
			map.put("abnVisceraFixedFlag", tblStudyPlan.getAbnVisceraFixedFlag());
			map.put("abnVisceraHistopathCheckFlag", tblStudyPlan.getAbnVisceraHistopathCheckFlag());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	
	
	
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public void setVisceraNames(String visceraNames) {
		this.visceraNames = visceraNames;
	}
	public String getVisceraNames() {
		return visceraNames;
	}
	public void setAtanomyCheckFlags(String atanomyCheckFlags) {
		this.atanomyCheckFlags = atanomyCheckFlags;
	}
	public String getAtanomyCheckFlags() {
		return atanomyCheckFlags;
	}
	public void setVisceraFixedFlags(String visceraFixedFlags) {
		this.visceraFixedFlags = visceraFixedFlags;
	}
	public String getVisceraFixedFlags() {
		return visceraFixedFlags;
	}
	public void setHistopathCheckFlags(String histopathCheckFlags) {
		this.histopathCheckFlags = histopathCheckFlags;
	}
	public String getHistopathCheckFlags() {
		return histopathCheckFlags;
	}
	public void setAbnVisceraAnatomyCheckFlag(Integer abnVisceraAnatomyCheckFlag) {
		this.abnVisceraAnatomyCheckFlag = abnVisceraAnatomyCheckFlag;
	}
	public Integer getAbnVisceraAnatomyCheckFlag() {
		return abnVisceraAnatomyCheckFlag;
	}
	public void setAbnVisceraFixedFlag(Integer abnVisceraFixedFlag) {
		this.abnVisceraFixedFlag = abnVisceraFixedFlag;
	}
	public Integer getAbnVisceraFixedFlag() {
		return abnVisceraFixedFlag;
	}
	public void setAbnVisceraHistopathCheckFlag(
			Integer abnVisceraHistopathCheckFlag) {
		this.abnVisceraHistopathCheckFlag = abnVisceraHistopathCheckFlag;
	}
	public Integer getAbnVisceraHistopathCheckFlag() {
		return abnVisceraHistopathCheckFlag;
	}
	
}
