package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictStudyTypeAction extends BaseAction<DictStudyType> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	
	/**列表*/
	public String list() throws Exception {
//		List<DictStudyType> objList;
//		objList = dictStudyTypeService.getAll();
//		ActionContext.getContext().put("objList", objList);
		return "list";
	}
	/**list加载数据(json)*/
	public void loadList(){
		List<DictStudyType>  objList=dictStudyTypeService.getAll();
		String[] _nory_changes ={"tiCode","studyTypeCode","studyName","studyPeriod","studyPeriodUnit"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	
	public void loadPartList(){
		List<DictStudyType>  objList=null;
		if(model.getTiCode()!=null && !"".equals(model.getTiCode())){
			objList = dictStudyTypeService.getByTiCode(model.getTiCode());
		}else{
			objList=dictStudyTypeService.getAll();
		}
		String[] _nory_changes ={"tiCode","studyTypeCode","studyName","studyPeriod","studyPeriodUnit"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	
	/** 增加页面*/
	public String addUI() throws Exception {
		return "addUI";
	}
	/**检查课题名称的唯一性*/
	public void checkStudyName(){
		if(null!=model.getStudyName() && !"".equals(model.getStudyName())){
			boolean isExist = dictStudyTypeService.isExistByStudyName(model.getStudyName());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**检查课题编码的唯一性*/
	public void checkStudyTypeCode(){
		if(null!=model.getStudyTypeCode() && !"".equals(model.getStudyTypeCode())){
			boolean isExist = dictStudyTypeService.isExistByCode(model.getStudyTypeCode());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**新增*/
	public String add() throws Exception {
		if(!checkValue()){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "addUI";
		}
		String studyTypeCode = model.getStudyTypeCode();
		boolean isExist = dictStudyTypeService.isExistByCode(studyTypeCode);
		if(isExist){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","课题类别编码已存在！");
			return "addUI";
		}
		dictStudyTypeService.save(model);
		return "toList";
	}
//	/**
//	 * 删除
//	 * @return String
//	 * @throws Exception
//	 */
//	public String delete() throws Exception {
//		dictStudyTypeService.delete(model.getStudyTypeCode());
//		return "toList";
//	}
	/**
	 * 编辑页面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		if(null!=model.getStudyTypeCode() && !"".equals(model.getStudyTypeCode())){
			DictStudyType obj  =dictStudyTypeService.getById(model.getStudyTypeCode());
			ActionContext.getContext().getValueStack().push(obj);
		}
		return "editUI";
	}
//	checkStudyNameCode
	/**检查课题名称的唯一性(自己除外)*/
	public void checkStudyNameCode(){
		if(null!=model.getStudyTypeCode() && !"".equals(model.getStudyTypeCode()) && null!=model.getStudyName() && !"".equals(model.getStudyName())){
			boolean isExist = dictStudyTypeService.isExistByNameCode(model.getStudyTypeCode(),model.getStudyName());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if(!checkValue()){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "editUI";
		}
		DictStudyType tempModel = dictStudyTypeService.getById(model.getStudyTypeCode());
		tempModel.setStudyName(model.getStudyName());
		tempModel.setStudyPeriod(model.getStudyPeriod());
		tempModel.setStudyPeriodUnit(model.getStudyPeriodUnit());
		tempModel.setTiCode(model.getTiCode());
		dictStudyTypeService.update(tempModel);
		return "toList";
	}
	
	
	public void select(){
		List<DictTestItemType> list = dictTestItemTypeService.getAll();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(DictTestItemType obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}

	
//	/**
//	 * 新增页面
//	 * @return
//	 * @throws Exception
//	 */
//	public String addUI() throws Exception {
//		//供试品类型列表
//		List<DictTestItemType> tiTypeList = dictTestItemTypeService.getAll();
//		ActionContext.getContext().put("tiTypeList", tiTypeList);
//		return "saveUI";
//	}
	

	
	/**验证输入的值*/
	public boolean checkValue(){
		if(null==model.getStudyTypeCode()||"".equals(model.getStudyTypeCode())){
			return false;
		}else if(null==model.getStudyName()||"".equals(model.getStudyName())){
			return false;
		}else if(0==model.getStudyPeriod()||"".equals(model.getStudyPeriod())){
			return false;
		}else if(null==model.getStudyPeriodUnit()||"".equals(model.getStudyPeriodUnit())){
			return false;
		}else if(null==model.getTiCode()||"".equals(model.getTiCode())){
			return false;
		}
		return true;
	}
	public DictStudyTypeService getDictStudyTypeService() {
		return dictStudyTypeService;
	}
	public void setDictStudyTypeService(DictStudyTypeService dictStudyTypeService) {
		this.dictStudyTypeService = dictStudyTypeService;
	}
}
