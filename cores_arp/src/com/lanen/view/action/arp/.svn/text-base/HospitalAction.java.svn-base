package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Hospital;
import com.lanen.service.arp.HospitalService;
import com.lanen.util.Constant;

@Controller
@Scope("prototype")
public class HospitalAction extends BaseAction<Hospital> {

	/**
	 * 住院治疗
	 */
	private static final long serialVersionUID = -8277096522843398846L;

	@Resource
	private HospitalService hospitalService;

	private String rows;
	private String page;

	private String startdate;
	private String enddate;
	private String treatroom;
	public String list() {
		return "hospitalList";
	}

	public void loadList() {
		Map<String, Object> map = hospitalService.getHospital(rows, page,model.getMonkeyid(),startdate,enddate,treatroom);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Hospital h = new Hospital();
		if (null != model.getMonkeyid() && !("").equals(model.getMonkeyid())) {
			h.setId(model.getId());
			h.setMonkeyid(model.getMonkeyid());
			h.setCheckdate(model.getCheckdate());
			h.setTreatveterinarian(model.getTreatveterinarian());
			h.setSremark(model.getSremark());
			h.setCf(model.getCf());

			hospitalService.save(h);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (null != model.getId()) {
			Hospital h = hospitalService.getById(model.getId());
			String json = JsonPluginsUtil.beanToJson(h, "yyyy-MM-dd");
			writeJson(json);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != model.getId()) {
			Hospital h = hospitalService.getById(model.getId());
			h.setCheckdate(model.getCheckdate());
			h.setTreatveterinarian(model.getTreatveterinarian());
			h.setSremark(model.getSremark());
			h.setCf(model.getCf());
			hospitalService.update(h);
			map.put("success", true);
			map.put("msg", "编辑成功");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void delHospital() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Hospital h = hospitalService.getById(model.getId());
			h.setDeleted(Constant.deleted_1);
			hospitalService.update(h);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String str = JsonPluginsUtil.beanToJson(map);
		writeJson(str);
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

	public String getTreatroom() {
		return treatroom;
	}

	public void setTreatroom(String treatroom) {
		this.treatroom = treatroom;
	}

}
