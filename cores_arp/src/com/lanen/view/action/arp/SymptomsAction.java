package com.lanen.view.action.arp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Symptoms;
import com.lanen.service.arp.SymptomsService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SymptomsAction extends BaseAction<Symptoms> {

	/**
	 * 症状
	 */
	private static final long serialVersionUID = 5490271779626749L;

	@Resource
	private SymptomsService symptomsService;
	private String page;
	private String rows;
	private String sys;

	public String list() {
		return "symptomsList";
	}

	public void loadList() {
		Map<String, Object> map = symptomsService.getSymptoms(page, rows,
				model.getName(), sys);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Symptoms sarea = new Symptoms();
		if (model.getName() != null && !"".equals(model.getName())) {
			
			sarea.setName(model.getName());
			sarea.setReason(model.getReason());
			sarea.setSymptomssite(model.getSymptomssite());
			sarea.setSymptomsremark(model.getSymptomsremark());
			sarea.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession().get("user");
			sarea.setIstreatment("是");
			sarea.setCreated_by(Integer.valueOf(user.getId()+""));
			sarea.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
			symptomsService.save(sarea);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delSymptoms() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Symptoms t = symptomsService.getById(model.getId());
			t.setDeleted(1);
			symptomsService.update(t);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Symptoms t = symptomsService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(t, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if ( model.getId() != null
				&& !"".equals(model.getId())) {
			Symptoms t = symptomsService.getById(model.getId());
			t.setId(model.getId());
			t.setName(model.getName());
			t.setReason(model.getReason());
			t.setSymptomssite(model.getSymptomssite());
			t.setSymptomsremark(model.getSymptomsremark());
			
			symptomsService.update(t);
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
