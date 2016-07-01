package com.lanen.view.action.studyplan;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.DictSpecimen;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictSpecimenAction extends BaseAction<DictSpecimen>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oldKind;
	
	/** 列表*/
	public String list() throws Exception {
		return "list";
	}
	
	/**list加载数据(json)*/
	public void loadList(){
		List<DictSpecimen>  objList=dictSpecimenService.getAll();
		String[] _nory_changes ={"id","specKind","specType"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	/** 删除*/
	public void delete() {
		Json json = new Json();
		if(null!=model.getId() && !"".equals(model.getId())){
			dictSpecimenService.delete(model.getId());
			json.setSuccess(true);
			json.setMsg("删除成功");
		}else{
			json.setMsg("删除失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/** 增加页面*/
	public String addUI() throws Exception {
		return "addUI";
	}
	
	
	/**异步检查标本种类是否存在*/
	public void checkspecKind(){
		if(null!=model.getSpecKind() && !"".equals(model.getSpecKind())){
			boolean isExist = dictSpecimenService.isExistBySpecKind(model.getSpecKind());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	
	}
	
	/**检查标本种类是否存在（自己除外）*/
	public void checkOtherspecKind(){
		if(null!=model.getSpecKind() && !"".equals(model.getSpecKind())&&null!=oldKind && !"".equals(oldKind)){
			if(oldKind.trim().equals(model.getSpecKind().trim())){
				writeJson("true");
			}else{
				boolean isExist = dictSpecimenService.isExistBySpecKind(model.getSpecKind());
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
	
	/** 增加 （保存） */
	public String add() throws Exception {
		if(!checkValue()){
			//ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "addUI";
		}
		model.setId(dictSpecimenService.getKey());
		dictSpecimenService.save(model);
		return "toList";
	}
	
	/**验证  输入的值（添加）*/
	public boolean checkValue(){
		if(null==model.getSpecKind()||"".equals(model.getSpecKind())){
			return false;
		}else if(0==model.getSpecType()){
			return false;
		}
		boolean isExistKind = dictSpecimenService.isExistBySpecKind(model.getSpecKind());
		if(isExistKind ){
			return false;
		}
		return true;
	}
	
	/** 修改页面*/
	public String editUI() throws Exception {
		if(null!=model.getId() && !"".equals(model.getId())){
			DictSpecimen obj  =dictSpecimenService.getById(model.getId());
			ActionContext.getContext().getValueStack().push(obj);
			return "editUI";
		}else{
			return "toList";
		}
	}

	/** 修改*/
	public String edit() throws Exception {
		if(!checkValue_edit()){
//			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "editUI";
		}
		DictSpecimen  specimen =dictSpecimenService.getById(model.getId());
		specimen.setSpecKind(model.getSpecKind());
		dictSpecimenService.update( specimen);
		return "toList";
	}
	
	/**验证  输入的值（添加）*/
	public boolean checkValue_edit(){
		if(null==model.getSpecKind()||"".equals(model.getSpecKind())){
			return false;
		}
		boolean isExistKind = dictSpecimenService.isExistBySpecKind(model.getSpecKind());
		if(isExistKind ){
			return false;
		}
		return true;
	}
	
	
	public String getOldKind() {
		return oldKind;
	}

	public void setOldKind(String oldKind) {
		this.oldKind = oldKind;
	}
	
	

}
