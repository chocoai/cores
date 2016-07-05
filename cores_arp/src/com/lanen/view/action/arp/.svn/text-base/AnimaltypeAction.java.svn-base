package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Animaltype;
import com.lanen.service.arp.AnimaltypeService;

@Controller
@Scope("prototype")
public class AnimaltypeAction extends BaseAction<Animaltype> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 919479901935536249L;
	

	private String rows;
	private String page;
	
	@Resource
	private AnimaltypeService animaltypeService;

	public String list() {
		return "list";
	}

	// 主页面数据加载
	public void loadList() {
		Map<String,Object> listMap = animaltypeService.getAllAnimalType(rows, page);
		
		String json = JsonPluginsUtil.beanToJson(listMap);
		writeJson(json);
	}

	public void add(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(model.getName()!=null&&!"".equals(model.getName())){
			Animaltype at=new Animaltype();
			at.setName(model.getName());
			at.setDel(0);
			at.setDesciption(model.getDesciption());
			animaltypeService.save(at);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", at.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 编辑前数据加载
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Animaltype a = animaltypeService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(a);
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Animaltype a = animaltypeService.getById(model.getId());
			a.setName(model.getName());
			a.setDesciption(model.getDesciption());
			animaltypeService.update(a);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", a.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 区域（房间）删除
	 */
	public void delAnimaltype() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Animaltype a = animaltypeService.getById(model.getId());
			a.setDel(1);
			animaltypeService.update(a);
			
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}
	

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
