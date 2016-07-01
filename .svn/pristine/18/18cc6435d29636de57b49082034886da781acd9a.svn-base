package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.studyplan.DictDoseUnit;
import com.lanen.service.studyplan.DictDoseUnitService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class DictDoseUnitAction  extends BaseAction<DictDoseUnit>{
	private static final long serialVersionUID = 1L;
	private String oldAbbr;
	@Resource
	private DictDoseUnitService dictDoseUnitService;
	/** 顺序设置参数*/
	private int orderNoPara;
	
	/** 顺序设置参数*/
	private int orderNoNext;
	
	/**加载剂量单位   下拉框*/
	public void loadComboboxList(){
		List<DictDoseUnit> doseUnitList = dictDoseUnitService.getAll();
		List<Map<String,String>> doseUnitMapList = new ArrayList<Map<String,String>>();
		Map<String,String> map =null;
		map = new HashMap<String,String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		doseUnitMapList.add(map);
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
	
	
	/** 列表*/
	public String list() throws Exception {
		return "list";
	}
	/**list加载数据(json)*/
	public void loadList(){
		List<DictDoseUnit>  objList=dictDoseUnitService.getAll();
		String[] _nory_changes ={"orderNo","name","abbr"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	/** 增加页面*/
	public String addUI() throws Exception {
		return "addUI";
	}
	/**异步检查名称是否存在*/
	public void checkName(){
		if(null!=model.getName() && !"".equals(model.getName())){
			boolean isExist = dictDoseUnitService.isExistByName(model.getName());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**异步检查缩写是否存在*/
	public void checkAbbr(){
		if(null!=model.getAbbr() && !"".equals(model.getAbbr())){
			boolean isExist = dictDoseUnitService.isExistByAbbr(model.getAbbr());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/** 增加 （保存） */
	public String add() throws Exception {
		if(!checkValue()){
			//ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "addUI";
		}
		
		model.setOrderNo(dictDoseUnitService.getNextOrderNo());
		dictDoseUnitService.save(model);
		return "toList";
	}
	/** 删除(异步 json)*/
	public void delete() {
		Json json = new Json();
		if(null!=model.getName() && !"".equals(model.getName())){
			dictDoseUnitService.delete(model.getName());
//			dictDoseUnitService.
			json.setSuccess(true);
			json.setMsg("删除成功");
		}else{
			json.setMsg("删除失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/** 修改页面*/
	public String editUI() throws Exception {
		if(null!=model.getName() && !"".equals(model.getName())){
			DictDoseUnit obj  =dictDoseUnitService.getById(model.getName());
			ActionContext.getContext().getValueStack().push(obj);
			return "editUI";
		}else{
			return "toList";
		}
	}
	/**检查缩写是否存在（自己除外）*/
	public void checkOtherAbbr(){
		if(null!=model.getAbbr() && !"".equals(model.getAbbr())&&null!=oldAbbr && !"".equals(oldAbbr)){
			if(oldAbbr.trim().equals(model.getAbbr().trim())){
				writeJson("true");
			}else{
				boolean isExist = dictDoseUnitService.isExistByAbbr(model.getAbbr());
				if(!isExist){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}
		}else{
			writeJson("false");
		}
	}
	/** 修改*/
	public String edit() throws Exception {
		if(!checkValue_edit()){
//			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "editUI";
		}
		DictDoseUnit DictDoseUnit =dictDoseUnitService.getById(model.getName());
		DictDoseUnit.setAbbr(model.getAbbr());
		dictDoseUnitService.update(DictDoseUnit);
		return "toList";
	}
	
	/**验证  输入的值（添加）*/
	public boolean checkValue(){
		if(null==model.getName()||"".equals(model.getName())){
			return false;
		}else if(null==model.getAbbr()||"".equals(model.getAbbr())){
			return false;
		}
		boolean isExistName = dictDoseUnitService.isExistByName(model.getName());
		if(isExistName){
			return false;
		}
		boolean isExistAbbr = dictDoseUnitService.isExistByAbbr(model.getAbbr());
		if(isExistAbbr){
			return false;
		}
		return true;
	}
	/**验证  输入的值（添加）*/
	public boolean checkValue_edit(){
		if(null==model.getName()||"".equals(model.getName())){
			return false;
		}else if(null==model.getAbbr()||"".equals(model.getAbbr())){
			return false;
		}
		boolean isExistName = dictDoseUnitService.isExistByName(model.getName());
		if(!isExistName){
			return false;
		}
		boolean isExistAbbr = dictDoseUnitService.isExistByNameAbbr(model.getName(),model.getAbbr());
		if(isExistAbbr){
			return false;
		}
		return true;
	}
	
	/** 顺序设置*/
	public void moveOrder() {
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderNoPara!=0&&orderNoNext!=0){
			dictDoseUnitService.moveOeder(orderNoPara, orderNoNext);
			map.put("success", true);
			map.put("msg","移动设置成功");
			DictDoseUnit currentRow = dictDoseUnitService.getByOrderNo(orderNoPara);
			DictDoseUnit nextRow = dictDoseUnitService.getByOrderNo(orderNoNext);
			map.put("nextRow", nextRow);
			map.put("currentRow", currentRow);
		}else{
			map.put("success", false);
			map.put("msg","移动设置失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
		
	}
	
	public int getOrderNoPara() {
		return orderNoPara;
	}
	public void setOrderNoPara(int orderNoPara) {
		this.orderNoPara = orderNoPara;
	}
	public int getOrderNoNext() {
		return orderNoNext;
	}
	public void setOrderNoNext(int orderNoNext) {
		this.orderNoNext = orderNoNext;
	}


	public String getOldAbbr() {
		return oldAbbr;
	}


	public void setOldAbbr(String oldAbbr) {
		this.oldAbbr = oldAbbr;
	}
	
}
