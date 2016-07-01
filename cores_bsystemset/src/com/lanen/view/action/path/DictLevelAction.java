package com.lanen.view.action.path;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.DictLevel;
import com.lanen.service.path.DictLevelService;

/**病变程度Action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DictLevelAction extends BaseAction<DictLevel>{

	private static final long serialVersionUID = -543733410810086701L;
	
	@Resource
	private DictLevelService dictLevelService;
	/**
	 * 转到list页面
	 * @return
	 */
	public String list(){
		return "list";
	}
	
	/**
	 * 加载数据(datagrid)
	 */
	public void loadList(){
		List<DictLevel> dataList = dictLevelService.findAll();
		
		String jsonStr = "";
		if(null != dataList){
			jsonStr = JsonPluginsUtil.beanListToJson(dataList);
		}
		writeJson(jsonStr);
	}
	
	/**
	 * 检查是否存在
	 */
	public void checkLevel(){
		if(null != model.getLevel()){
			if(model.getLevel().getBytes().length < 20){
				boolean isExist = dictLevelService.isExistByLevel(model.getLevel());
				if(isExist){
					writeJson("false");
				}else{
					writeJson("true");
				}
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	
	/**
	 * 添加
	 */
	public void addOne(){
		Json json = new Json();
		if(null != model.getLevel()){
			DictLevel dictLevel = new DictLevel();
			String id = dictLevelService.getKey();
			dictLevel.setId(id);
			dictLevel.setLevel(model.getLevel());
			dictLevelService.save(dictLevel);
			json.setSuccess(true);
			json.setMsg(id);
		}
		String jsonstr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonstr);
	}
	/**
	 * 编辑
	 */
	public void editOne(){
		Json json = new Json();
		if(null != model.getLevel() && null != model.getId()){
			DictLevel dictLevel = new DictLevel();
			dictLevel.setId(model.getId());
			dictLevel.setLevel(model.getLevel());
			dictLevelService.update(dictLevel);
			json.setSuccess(true);
			json.setMsg(model.getId());
		}
		String jsonstr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonstr);
	}
	/**
	 * 删除
	 */
	public void delOne(){
		Json json = new Json();
		if( null != model.getId()){
//			DictLevel dictLevel = dictLevelService.getById(model.getId());
			dictLevelService.delete(model.getId());
			json.setSuccess(true);
		}
		String jsonstr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonstr);
	}

}
