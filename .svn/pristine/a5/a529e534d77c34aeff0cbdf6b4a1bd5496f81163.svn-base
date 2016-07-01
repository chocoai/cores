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
import com.lanen.model.path.TblAnatomyReqAttachedViscera;
import com.lanen.model.path.TblAnatomyReqVisceraWeigh;
import com.lanen.model.path.TblAnatomyReqVisceraWeigh_Json;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.service.path.TblAnatomyReqAttachedVisceraService;
import com.lanen.service.path.TblAnatomyReqVisceraWeighService;
import com.lanen.service.studyplan.DictAnimalTypeService;
@Controller
@Scope("prototype")
public class TblAnatomyReqVisceraWeighAction extends
		BaseAction<TblAnatomyReqVisceraWeigh> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4694464686916834306L;
	/**
	 * 解剖申请-脏器称重  Service
	 */
	@Resource
	private TblAnatomyReqVisceraWeighService tblAnatomyReqVisceraWeighService;
	/**
	 * 解剖申请-脏器称重-附加脏器  Service
	 */
	@Resource
	private TblAnatomyReqAttachedVisceraService tblAnatomyReqAttachedVisceraService;
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
	 * 编辑解剖神奇-脏器称重前，加载数据
	 */
	public void toEdit(){
		List<TblAnatomyReqVisceraWeigh> list=tblAnatomyReqVisceraWeighService.getListByStudyAndReqNo(studyNoPara,model.getReqNo());
		List<TblAnatomyReqVisceraWeigh_Json> list2=new ArrayList<TblAnatomyReqVisceraWeigh_Json>();
		if(list!=null&&list.size()>0){
			for(TblAnatomyReqVisceraWeigh arvw:list){
				TblAnatomyReqVisceraWeigh_Json json=new TblAnatomyReqVisceraWeigh_Json();
				//根据解剖申请-脏器称重ID，查询对应的附加脏器
				List<TblAnatomyReqAttachedViscera> list3=tblAnatomyReqAttachedVisceraService.getListByPid(arvw.getId());
				String attachedViscera="";
				if(list3!=null&&list3.size()>0){
					for(TblAnatomyReqAttachedViscera aav:list3){
						if(aav!=list3.get(list3.size()-1)){
							//附加脏器有多个名字用顿号隔开
							attachedViscera=attachedViscera+aav.getVisceraName()+"、";
						}else{
							attachedViscera=attachedViscera+aav.getVisceraName();
						}
					}
				}
				json.setId(arvw.getId());
				json.setStudyNo(studyNoPara);
				json.setReqNo(arvw.getReqNo());
				json.setVisceraCode(arvw.getVisceraCode());
				json.setVisceraName(arvw.getVisceraName());
				json.setPartVisceraSeparateWeigh(arvw.getPartVisceraSeparateWeigh());
				json.setFixedWeighFlag(arvw.getFixedWeighFlag());
				System.out.println(attachedViscera);
				json.setAttachedViscera(attachedViscera);
				list2.add(json);
			}
		} 
		Map<String, Object> map=new HashMap<String, Object>();
    	map.put("rows", list2);
    	map.put("total", list2.size());
    	String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	public void loadVisceraList(){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		DictAnimalType dictAnimalType=dictAnimalTypeService.getByName(animalType);
		if(dictAnimalType!=null){
//			String AnimalTypeId=dictAnimalType.getId();
			mapList=tblAnatomyReqVisceraWeighService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara,model.getReqNo());
		}else{
			mapList=tblAnatomyReqVisceraWeighService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara,model.getReqNo());
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
