package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Routine;
import com.lanen.service.arp.RoutineService;

@Controller
@Scope("prototype")
public class RoutineAction extends BaseAction<Routine> {

	/**
	 * 常规检疫
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private RoutineService routineService;
	private String page;
	private String rows;

	private String monkeyid;

	public String list() {
		return "normalList";
	}

	public void loadList() {
		Map<String, Object> map = routineService.loadListByCondition(page,
				rows, model.getMonkeyid());
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-mm-dd");
		writeJson(json);
	}

	public void normalLIst() {
		Map<String, Object> map = routineService.loadNormalList();
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void toEditNormal() {
		if (model.getId() != null) {
			// Leavebreast d=routineService.getById(model.getId());
			// String jsonStr = JsonPluginsUtil.beanToJson(d,"yyyy-MM-dd");
			// writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			// Leavebreast l=weaningService.getById(model.getId());
			// if(d.getMonkeyid()!=model.getMonkeyid()){
			// Individual
			// individual=individualService.getByMonkeyid(d.getMonkeyid());
			// individual.setDeleted(0);
			// individualService.update(individual);
			// Individual
			// individual2=individualService.getByMonkeyid(model.getMonkeyid());
			// individual2.setDeleted(1);
			// individualService.update(individual2);
			// }
			// l.setMonkeyid(model.getMonkeyid());
			// l.setLeavebreastdate(model.getLeavebreastdate());
			// l.setMotherid(model.getMotherid());
			// l.setKeeper(model.getKeeper());
			// l.setOperater(model.getOperater());
			// l.setRemark(model.getRemark());
			/*
			 * d.setDeathdate(model.getDeathdate());
			 * d.setDissectdate(model.getDissectdate());
			 * d.setVeterinarian(model.getVeterinarian());
			 * d.setDissectveterinarian(model.getDissectveterinarian());
			 * d.setPathology(model.getPathology()); d.setBoss(model.getBoss());
			 * d.setKeeper(model.getKeeper()); d.setRemarks(model.getRemarks());
			 * d.setBl_remarks(model.getBl_remarks());
			 * d.setSc_remarks(model.getSc_remarks());
			 * d.setOthers(model.getOthers());
			 * d.setRecorder(model.getRecorder());
			 * d.setOperater(model.getOperater());
			 */
			// weaningService.update(l);
			map.put("success", true);
			map.put("msg", "编辑成功");
			// map.put("id", l.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void virus() {
		Map<String, Object> map = routineService.virus(model.getMonkeyid());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
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
}
