package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Hospitaldl;
import com.lanen.service.arp.HospitaldlService;
import com.lanen.util.Constant;

@Controller
@Scope("prototype")
public class HospitaldlAction extends BaseAction<Hospitaldl> {

	/**
	 * 常规治疗
	 */
	private static final long serialVersionUID = 5490271779626749L;

	@Resource
	private HospitaldlService hospitaldlService;
	private String page;
	private String rows;
	private String treatveterinarians;
	private String startdate;
	private String enddate;

	public String list() {
		return "hospitaldlList";
	}

	public void loadList() {
		Map<String, Object> map = hospitaldlService.getHospitaldl(page, rows,
				model.getMonkeyid(), model.getZzmc(), treatveterinarians,
				startdate, enddate);
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Hospitaldl hl = new Hospitaldl();
		if (model.getMonkeyid() != null && !("").equals(model.getMonkeyid())) {
			hl.setId(model.getId());
			hl.setMonkeyid(model.getMonkeyid());
			hl.setZlrq(model.getZlrq());
			hl.setTreatveterinarian(model.getTreatveterinarian());
			hl.setZzmc(model.getZzmc());
			hl.setCf(model.getCf());
			hl.setRemark(model.getRemark());
			hl.setDeleted(Constant.deleted_0);
			hospitaldlService.save(hl);

			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Hospitaldl h = hospitaldlService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(h, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			Hospitaldl h = hospitaldlService.getById(model.getId());

			h.setMonkeyid(model.getMonkeyid());
			h.setZlrq(model.getZlrq());
			h.setTreatveterinarian(model.getTreatveterinarian());
			h.setZzmc(model.getZzmc());
			h.setCf(model.getCf());
			h.setRemark(model.getRemark());
			hospitaldlService.update(h);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", h.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delHospitaldl() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Hospitaldl h = hospitaldlService.getById(model.getId());
			h.setDeleted(Constant.deleted_1);
			hospitaldlService.update(h);
			map.put("success", true);
			map.put("msg", "删除成功");
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

	public String getTreatveterinarians() {
		return treatveterinarians;
	}

	public void setTreatveterinarians(String treatveterinarians) {
		this.treatveterinarians = treatveterinarians;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
