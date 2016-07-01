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
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
import com.lanen.model.path.TblPathPlanVisceraWeigh_Json;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.service.path.DictVisceraService;
import com.lanen.service.path.TblPathPlanAttachedVisceraService;
import com.lanen.service.path.TblPathPlanVisceraWeighService;
import com.lanen.service.studyplan.DictAnimalTypeService;
@Controller
@Scope("prototype")
public class TblPathPlanVisceraWeighAction extends BaseAction<TblPathPlanVisceraWeigh> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2923492981609408527L;
	/**
	 * 病理计划-脏器称重     service
	 */
	@Resource
	private TblPathPlanVisceraWeighService tblPathPlanVisceraWeighService;
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
	 * 病理计划-脏器称重-附加脏器     service 
	 */
	@Resource
	private TblPathPlanAttachedVisceraService tblPathPlanAttachedVisceraService;
	
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	/**
	 * 动物种类（名）
	 */
	private String animalType;
	/**
	 * 病理计划-脏器称重-脏器列表
	 */
	private String visceraNames1;
	/**
	 * 病理计划-脏器称重-成对脏器分开称重标志数组
	 */
	private String partVisceraSeparateWeighs;
	/**
	 * 病理计划-脏器称重-固定称重标志数组
	 */
	private String fixedWeighFlags;
	/**
	 *病理计划-脏器称重-附加脏器 
	 */
	private String attachedVisceras;
	
	/**
	 * 加载病理计划-脏器称重数据，如果有附加脏器，加载附加脏器名
	 */
	public void loadList(){
		List<TblPathPlanVisceraWeigh> list=tblPathPlanVisceraWeighService.getListByStudyNo(studyNoPara);
		List<TblPathPlanVisceraWeigh_Json> list2=new ArrayList<TblPathPlanVisceraWeigh_Json>();
		if(list!=null&&list.size()>0){
			for(TblPathPlanVisceraWeigh ppvw:list){
				TblPathPlanVisceraWeigh_Json json=new TblPathPlanVisceraWeigh_Json();
				//根据脏器称重ID，查询附加脏器
				List<TblPathPlanAttachedViscera> list3=tblPathPlanAttachedVisceraService.getListByPid(ppvw.getId());//附加脏器列表
				String attachedViscera="";
				if(list3!=null&&list3.size()>0){
					for(TblPathPlanAttachedViscera ppav:list3){
						if(ppav!=list3.get(list3.size()-1)){
							//附加脏器有多个名字用逗号隔开
							attachedViscera=attachedViscera+ppav.getVisceraName()+"、";
						}else{
							attachedViscera=attachedViscera+ppav.getVisceraName();
						}
						
					}
				}
				json.setId(ppvw.getId());
				json.setStudyNo(ppvw.getStudyNo());
				json.setVisceraType(ppvw.getVisceraType());
				json.setVisceraName(ppvw.getVisceraName());
				json.setVisceraCode(ppvw.getVisceraCode());
				json.setAttachedVisceraFlag(ppvw.getAttachedVisceraFlag());
				json.setPartVisceraSeparateWeigh(ppvw.getPartVisceraSeparateWeigh());
				json.setFixedWeighFlag(ppvw.getFixedWeighFlag());
				json.setSn(ppvw.getSn());
				json.setAttachedViscera(attachedViscera);
				list2.add(json);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list2);
		 map.put("total", list2.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	public void loadVisceraList(){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		DictAnimalType dictAnimalType=dictAnimalTypeService.getByName(animalType);
//		List<DictViscera> list=new ArrayList<DictViscera>();
		if(dictAnimalType!=null){
//			list=dictVisceraService.get1LListByAnimalType(dictAnimalType.getId());
			String AnimalTypeId=dictAnimalType.getId();
			mapList=tblPathPlanVisceraWeighService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara);
		}else{
			mapList=tblPathPlanVisceraWeighService.getVisceraListByAnimalTypeAndStudyNo(animalType,studyNoPara);
//			list=dictVisceraService.get1LListByAnimalType("0");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", mapList);
		 map.put("total", mapList.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	/**
	 * 添加脏器称重计划
	 */
	public void addPathPlanVisceraWeigh(){
		Map<String,Object> map = new HashMap<String, Object>();
		List<TblPathPlanVisceraWeigh> list=null;
		List<TblPathPlanAttachedViscera> attachedList=new ArrayList<TblPathPlanAttachedViscera>();
		if(null!=model.getStudyNo()&&!"".equals(model.getStudyNo())&&null!=visceraNames1&&!"".equals(visceraNames1)){
			list=new ArrayList<TblPathPlanVisceraWeigh>();
			String[] visceraNamesWeigh =visceraNames1.split(",");
    		String[] partVisceraSeparateWeighs1=partVisceraSeparateWeighs.split(",");
    		String[] fixedWeighFlags1=fixedWeighFlags.split(",");
    		String[] attachedVisceras1=attachedVisceras.split(",");
    		//获得解剖申请-脏器称重序号(Sn)
    		int sn=tblPathPlanVisceraWeighService.getSn(model.getStudyNo());
    		for(int i=0;i<visceraNamesWeigh.length;i++){
    			TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh=new TblPathPlanVisceraWeigh();
    			String id=tblPathPlanVisceraWeighService.getKey();
    			tblPathPlanVisceraWeigh.setId(id);
    			tblPathPlanVisceraWeigh.setStudyNo(model.getStudyNo());
    			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesWeigh[i]);
    			tblPathPlanVisceraWeigh.setVisceraCode(dictViscera.getVisceraCode());
    			tblPathPlanVisceraWeigh.setVisceraType(dictViscera.getVisceraType());
    			tblPathPlanVisceraWeigh.setVisceraName(dictViscera.getVisceraName());
    			if(attachedVisceras1[i].equals("0")){
    				tblPathPlanVisceraWeigh.setAttachedVisceraFlag(0);
    			}else{
    				tblPathPlanVisceraWeigh.setAttachedVisceraFlag(1);
    				String[] attachedVisceras2=attachedVisceras1[i].split("、");
    				for(int j=0;j<attachedVisceras2.length;j++){
    					TblPathPlanAttachedViscera tblPathPlanAttachedViscera=new TblPathPlanAttachedViscera();
    					String attachedId=tblPathPlanAttachedVisceraService.getKey();
    					tblPathPlanAttachedViscera.setId(attachedId);
    					tblPathPlanAttachedViscera.setVisceraWeighPlanID(id);
    					DictViscera dictViscera1=dictVisceraService.getByVisceraName(attachedVisceras2[j]);
    					tblPathPlanAttachedViscera.setVisceraCode(dictViscera1.getVisceraCode());
    					tblPathPlanAttachedViscera.setVisceraType(dictViscera1.getVisceraType());
    					tblPathPlanAttachedViscera.setVisceraName(dictViscera1.getVisceraName());
    					attachedList.add(tblPathPlanAttachedViscera);
    				}
    			}
    			tblPathPlanVisceraWeigh.setPartVisceraSeparateWeigh(Integer.parseInt(partVisceraSeparateWeighs1[i]));
    			tblPathPlanVisceraWeigh.setFixedWeighFlag(Integer.parseInt(fixedWeighFlags1[i]));
    			tblPathPlanVisceraWeigh.setSn(sn);
    			sn++;
    			list.add(tblPathPlanVisceraWeigh);
    		}
    		if(list.size()>0){
    			tblPathPlanVisceraWeighService.addSavePathPlanVisceraWeigh(list,attachedList,model.getStudyNo());
    			map.put("success",true);
    		}
		}
//		TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh=null;
//		tblPathPlanVisceraWeigh=tblPathPlanVisceraWeighService.getByVisceraCode(model.getVisceraCode(),model.getStudyNo());
		//如果当前脏器已设置称重计划，进行更新，否则新增
//		if(tblPathPlanVisceraWeigh!=null){
//			tblPathPlanVisceraWeigh.setPartVisceraSeparateWeigh(model.getPartVisceraSeparateWeigh());
//			tblPathPlanVisceraWeigh.setFixedWeighFlag(model.getFixedWeighFlag());
//			tblPathPlanVisceraWeighService.update(tblPathPlanVisceraWeigh);
//		}else{
//			tblPathPlanVisceraWeigh=new TblPathPlanVisceraWeigh();
//			String visceraCode=model.getVisceraCode();
//			//获得所选脏器的信息
//			DictViscera dictViscera=dictVisceraService.getById(visceraCode);
//			if(dictViscera!=null){
//				tblPathPlanVisceraWeigh.setVisceraCode(visceraCode);
//				tblPathPlanVisceraWeigh.setVisceraName(dictViscera.getVisceraName());
//				tblPathPlanVisceraWeigh.setVisceraType(dictViscera.getVisceraType());
////			    tblPathPlanVisceraWeigh.setGender(dictViscera.getGender());
//				String id=tblPathPlanVisceraWeighService.getKey();
//				tblPathPlanVisceraWeigh.setId(id);
//				tblPathPlanVisceraWeigh.setStudyNo(model.getStudyNo());
//				tblPathPlanVisceraWeigh.setPartVisceraSeparateWeigh(model.getPartVisceraSeparateWeigh());
//				tblPathPlanVisceraWeigh.setFixedWeighFlag(model.getFixedWeighFlag());
//				int sn=tblPathPlanVisceraWeighService.getSn(model.getStudyNo());
//				tblPathPlanVisceraWeigh.setSn(sn);
//				tblPathPlanVisceraWeighService.save(tblPathPlanVisceraWeigh);
//			}
//		}
//		map.put("success",true);
//		map.put("msg","添加成功");
//		map.put("id", tblPathPlanVisceraWeigh.getId());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 删除脏器称重计划，如果有对应的附加脏器也一并删除
	 */
	public void deletePathPlanVisceraWeigh(){
		Map<String,Object> map = new HashMap<String, Object>();
//		if(model.getId()!=null){
//			TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh=tblPathPlanVisceraWeighService.getById(model.getId());
//			if(tblPathPlanVisceraWeigh.getAttachedVisceraFlag()==1){
//				List<TblPathPlanAttachedViscera> list=tblPathPlanAttachedVisceraService.getListByPid(model.getId());
//				if(list!=null&&list.size()>0){
//					for(TblPathPlanAttachedViscera ppav:list){
//						tblPathPlanAttachedVisceraService.delete(ppav.getId());
//					}
//				}
//			}
//			tblPathPlanVisceraWeighService.delete(model.getId());
//			map.put("success",true);
//			map.put("msg","删除成功");
//		}
		System.out.println(model.getVisceraName());
		if(null!=model.getVisceraName() && !"".equals(model.getVisceraName())){
			DictViscera dictViscera=dictVisceraService.getByVisceraName(model.getVisceraName());
			if(null!=dictViscera){
				map.put("isPart", dictViscera.getIsPart());
			}
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
	public void setAttachedVisceras(String attachedVisceras) {
		this.attachedVisceras = attachedVisceras;
	}
	public String getAttachedVisceras() {
		return attachedVisceras;
	}
	public void setFixedWeighFlags(String fixedWeighFlags) {
		this.fixedWeighFlags = fixedWeighFlags;
	}
	public String getFixedWeighFlags() {
		return fixedWeighFlags;
	}
	public void setPartVisceraSeparateWeighs(String partVisceraSeparateWeighs) {
		this.partVisceraSeparateWeighs = partVisceraSeparateWeighs;
	}
	public String getPartVisceraSeparateWeighs() {
		return partVisceraSeparateWeighs;
	}
	public void setVisceraNames1(String visceraNames1) {
		this.visceraNames1 = visceraNames1;
	}
	public String getVisceraNames1() {
		return visceraNames1;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	
}
