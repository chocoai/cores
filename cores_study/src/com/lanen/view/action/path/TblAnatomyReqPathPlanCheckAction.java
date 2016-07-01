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
import com.lanen.model.path.TblAnatomyReqPathPlanCheck;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.service.path.TblAnatomyReqPathPlanCheckService;
import com.lanen.service.studyplan.DictAnimalTypeService;
@Controller
@Scope("prototype")
public class TblAnatomyReqPathPlanCheckAction extends
		BaseAction<TblAnatomyReqPathPlanCheck> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3382062270749799730L;
	/**
	 * 解剖申请-脏器/组织学检查  Service
	 */
	@Resource
	private TblAnatomyReqPathPlanCheckService tblAnatomyReqPathPlanCheckService;
	/**
	 * 动物种类字典     service 
	 */
	@Resource
	private DictAnimalTypeService dictAnimalTypeService;
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	/**
	 * 动物种类（名）
	 */
	private String animalType;
	/**
	 * 编辑解剖申请-脏器/组织学检查前，加载数据
	 */
	public void toEdit(){
		List<TblAnatomyReqPathPlanCheck> list=tblAnatomyReqPathPlanCheckService.getListByStudyNoAndReqNo(studyNoPara,model.getReqNo());
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 map.put("total", list.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	public void loadVisceraList(){
		//TODO animalType为什么是null
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		DictAnimalType dictAnimalType=dictAnimalTypeService.getByName(animalType);
		if(dictAnimalType!=null){
			String AnimalTypeId=dictAnimalType.getId();
			mapList=tblAnatomyReqPathPlanCheckService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara,model.getReqNo());
		}else{
			mapList=tblAnatomyReqPathPlanCheckService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara,model.getReqNo());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", mapList);
		 map.put("total", mapList.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
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
	
	
	
}
