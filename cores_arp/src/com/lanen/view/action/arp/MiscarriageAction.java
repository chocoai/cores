package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Miscarriage;
import com.lanen.service.arp.MiscarriageService;
import com.lanen.util.Constant;

@Controller
@Scope("prototype")
public class MiscarriageAction extends BaseAction<Miscarriage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7894429251375667031L;

	private String rows;
	private String page;

	private String startdate;
	private String enddate;
	@Resource
	private MiscarriageService miscarriageService;

	public String list() {
		return "miscarriageList";
	}

	public void loadList() {
		Map<String, Object> map = miscarriageService.getAllMiscarriageAnimal(
				page, rows, model.getMonkeyid(), startdate, enddate);
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Miscarriage m = new Miscarriage();
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			m.setMonkeyid(model.getMonkeyid());
			m.setMiscarriagedate(new Date());
			m.setVeterinarian(model.getVeterinarian());
			m.setProtector(model.getProtector());
			m.setRecorder(model.getRecorder());
			m.setOperater(model.getOperater());
			m.setRemarks(model.getRemarks());
			// l.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));
			m.setDeleted(Constant.deleted_0);
			miscarriageService.save(m);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", m.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Miscarriage m = miscarriageService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(m, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			Miscarriage m = miscarriageService.getById(model.getId());

			m.setMiscarriagedate(new Date());
			m.setVeterinarian(model.getVeterinarian());
			m.setProtector(model.getProtector());
			m.setRecorder(model.getRecorder());
			m.setOperater(model.getOperater());
			m.setRemarks(model.getRemarks());
			m.setMonkeyid(model.getMonkeyid());
			miscarriageService.update(m);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", m.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delMiscarriage() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Miscarriage m = miscarriageService.getById(model.getId());
			m.setDeleted(Constant.deleted_1);
			miscarriageService.update(m);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
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
