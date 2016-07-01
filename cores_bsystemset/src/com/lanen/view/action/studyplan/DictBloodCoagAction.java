package com.lanen.view.action.studyplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.studyplan.DictBloodCoag;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class DictBloodCoagAction  extends BaseAction<DictBloodCoag>{
	private static final long serialVersionUID = 1L;
	private String oldAbbr;
	/**
	 * 顺序设置参数
	 */
	private int orderNoPara;
	
	/**
	 * 顺序设置参数
	 */
	private int orderNoNext;
	
	/** 列表*/
	public String list() throws Exception {
		return "list";
	}
	/**list加载数据(json)*/
	public void loadList(){
		List<DictBloodCoag>  objList=dictBloodCoagService.getAll();
		String[] _nory_changes ={"orderNo","name","abbr","precision","unit"};
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
			boolean isExist = dictBloodCoagService.isExistByName(model.getName());
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
			boolean isExist = dictBloodCoagService.isExistByAbbr(model.getAbbr());
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
		
		model.setOrderNo(dictBloodCoagService.getNextOrderNo());
		dictBloodCoagService.save(model);
		return "toList";
	}
	/**查询指标是否已经设置了通道号(异步 json)*/
	public void checkIsExist()throws Exception{
		Json json = new Json();
		String abbr = model.getAbbr();
		if(null != abbr && !"".equals(abbr) ){
			boolean isExist = passagewayService.isExist(abbr,3);
			if(isExist){
				json.setSuccess(false);
				json.setMsg(abbr+"已设通道号,无法删除或编辑");
			}else{
				json.setSuccess(true);
				json.setMsg("");
			}
		}else{
			json.setMsg("与服务器交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/** 删除(异步 json)*/
	public void delete() {
		Json json = new Json();
		if(null!=model.getName() && !"".equals(model.getName())){
			
			DictBloodCoag  dictBloodCoag  = dictBloodCoagService.getById(model.getName());
			String abbr = dictBloodCoag.getAbbr();
			boolean isExist = passagewayService.isExist(abbr,3);
			if(isExist){
				json.setSuccess(false);
				json.setMsg("‘"+abbr+"’已设置通道号，无法删除");
			}else{
				dictBloodCoagService.delete(model.getName());
				json.setSuccess(true);
				json.setMsg("删除成功");
			}
		}else{
			json.setMsg("删除失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/** 修改页面*/
	public String editUI() throws Exception {
		if(null!=model.getName() && !"".equals(model.getName())){
			DictBloodCoag obj  =dictBloodCoagService.getById(model.getName());
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
				boolean isExist = dictBloodCoagService.isExistByAbbr(model.getAbbr());
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
		DictBloodCoag DictBloodCoag =dictBloodCoagService.getById(model.getName());
		DictBloodCoag.setAbbr(model.getAbbr());
		DictBloodCoag.setPrecision(model.getPrecision());
		DictBloodCoag.setUnit(model.getUnit());
		dictBloodCoagService.update(DictBloodCoag);
		return "toList";
	}
	
	/**验证  输入的值（添加）*/
	public boolean checkValue(){
		if(null==model.getName()||"".equals(model.getName())){
			return false;
		}else if(null==model.getAbbr()||"".equals(model.getAbbr())){
			return false;
		}
		boolean isExistName = dictBloodCoagService.isExistByName(model.getName());
		if(isExistName){
			return false;
		}
		boolean isExistAbbr = dictBloodCoagService.isExistByAbbr(model.getAbbr());
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
		boolean isExistName = dictBloodCoagService.isExistByName(model.getName());
		if(!isExistName){
			return false;
		}
		boolean isExistAbbr = dictBloodCoagService.isExistByNameAbbr(model.getName(),model.getAbbr());
		if(isExistAbbr){
			return false;
		}
		return true;
	}
	
	/** 顺序设置*/
	public void moveOrder() {
		Map<String,Object> map = new HashMap<String,Object>();
		if(orderNoPara!=0&&orderNoNext!=0){
			dictBloodCoagService.moveOeder(orderNoPara, orderNoNext);
			map.put("success", true);
			map.put("msg","移动设置成功");
			DictBloodCoag currentRow = dictBloodCoagService.getByOrderNo(orderNoPara);
			DictBloodCoag nextRow = dictBloodCoagService.getByOrderNo(orderNoNext);
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
