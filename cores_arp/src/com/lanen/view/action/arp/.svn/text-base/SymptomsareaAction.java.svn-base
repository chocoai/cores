package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Symptomsarea;
import com.lanen.model.Treasury;
import com.lanen.service.arp.SymptomsareaService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SymptomsareaAction extends BaseAction<Symptomsarea> {

	/**
	 * 疾病系统
	 */
	private static final long serialVersionUID = 5490271779626749L;

	@Resource
	private SymptomsareaService symptomsareaService;
	private String page;
	private String rows;
	private String sys;

	public String list() {
		return "symptomsareaList";
	}

	public void loadList() {
		Map<String, Object> map = symptomsareaService.getSymptomsarea(page,
				rows, model.getName(), sys);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListSymptomsarea() {
		List<Map<String, String>> mapList = symptomsareaService
				.getAllSymptomsareaMap();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Symptomsarea sarea = new Symptomsarea();
		if (model.getName() != null && !"".equals(model.getName())) {
			
			sarea.setName(model.getName());
			sarea.setRemark(model.getRemark());
			sarea.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			symptomsareaService.save(sarea);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delSymptomsarea() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Symptomsarea t = symptomsareaService.getById(model.getId());
			t.setDeleted(1);
			symptomsareaService.update(t);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Symptomsarea t = symptomsareaService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(t, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if ( model.getId() != null
				&& !"".equals(model.getId())) {
			Symptomsarea t = symptomsareaService.getById(model.getId());
			t.setId(model.getId());
			t.setName(model.getName());
			t.setRemark(model.getRemark());
			symptomsareaService.update(t);
			map.put("success", true);
			map.put("msg", "编辑成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

}
