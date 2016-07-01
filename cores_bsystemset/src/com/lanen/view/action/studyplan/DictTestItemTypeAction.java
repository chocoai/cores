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
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class DictTestItemTypeAction extends BaseAction<DictTestItemType> {
	private static final long serialVersionUID = -1255720734389073012L;

	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	/**转到 list页面*/
	public String list(){
		return "list";
	}
	
	/**加载list数据（json）*/
	public void loadList(){
		List<DictTestItemType> list=dictTestItemTypeService.getAll();
		int total = 0;
		if(null!=list){
			total=list.size();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);
		String jsonStr=JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/*转到add页面*/
	public String addUI(){
		return "addUI";
	}
	
	/**保存数据（json）*/
	public void save(){
		Json json = new Json();
		if(null!=model.getTiCode() && !"".equals(model.getTiCode())&&null!=model.getTiType() && !"".equals(model.getTiType())){
			dictTestItemTypeService.svaeOrUpdate(model);
			json.setSuccess(true);
			json.setMsg("供试品类型保存成功");
		}else{
			json.setMsg("供试品类型保存失败");
		}
		String jsonStr= JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**删除数据*/
	public void delete(){
		Json json = new Json();
		if(null!=model.getTiCode() && !"".equals(model.getTiCode())){
			// TODO
			if(!dictStudyTypeService.isExistByTiCode(model.getTiCode())){
				dictTestItemTypeService.delete(model.getTiCode());
				json.setSuccess(true);
				json.setMsg("供试品类型删除成功");
			}else{
				json.setMsg("供试品代码被引用，无法删除");
			}
		}else{
			json.setMsg("与服务器交互错误，无法删除");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**检查tiCode是否存在（json）*/
	public void checkTiCode(){
		if(null!=model.getTiCode() && !"".equals(model.getTiCode())){
			if(!dictTestItemTypeService.isExistByTiCode(model.getTiCode())){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**检查tiType是否存在（json）*/
	public void checkTiType(){
		if(null!=model.getTiType() && !"".equals(model.getTiType())){
			if(null!=model.getTiCode() &&!"".equals(model.getTiCode())){
				if(!dictTestItemTypeService.isExistByTiType(model.getTiType(),model.getTiCode())){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}else{
				if(!dictTestItemTypeService.isExistByTiType(model.getTiType())){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}
		}else{
			writeJson("false");
		}
	}
	
	/*转到add页面*/
	public String editUI(){
		//准备回显数据
		ActionContext.getContext().getValueStack().push(model);
		return "editUI";
	}
	
	/**加载供试品类别  下拉框*/
	public void loadComboboxList(){
		List<DictTestItemType> doseUnitList = dictTestItemTypeService.getAll();
		List<Map<String,String>> doseUnitMapList = new ArrayList<Map<String,String>>();
		Map<String,String> map =null;
		map = new HashMap<String,String>();
		if(null!=doseUnitList && doseUnitList.size()>0){
			for(DictTestItemType obj:doseUnitList){
				map = new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				doseUnitMapList.add(map);
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(doseUnitMapList);
		writeJson(jsonStr);
	}
}
