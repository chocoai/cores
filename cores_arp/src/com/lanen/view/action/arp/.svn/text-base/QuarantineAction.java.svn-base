package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Quarantine;
import com.lanen.service.arp.QuarantineService;
import com.lanen.util.Constant;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QuarantineAction extends BaseAction<Quarantine> {

	/**
	 * 防疫配置
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private QuarantineService quarantineService;

	private String rows;
	private String page;
	private String name;
	private String virusname;
	private String bacterianame;
	private String vaccinename;
	private String infectiousname;
	public String parasite = "QuarantineTypeParasite";
	public String virus = "QuarantineTypeVirus";
	public String bacteria = "QuarantineTypeBacteria";
	public String vaccine = "QuarantineTypeVaccine";
	public String infectious = "QuarantineTypeInfectious";

	// public String parasite="QuarantineTypeParasite";
	// public String parasite="QuarantineTypeParasite";
	// public String parasite="QuarantineTypeParasite";
	// public String parasite="QuarantineTypeParasite";
	public String list() {
		return "quarantineList";
	}

	// 寄生虫
	public void loadList() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				model.getName(), parasite);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Quarantine e = new Quarantine();
		if (model.getName() != null && !"".equals(model.getName())) {
			e.setId(model.getId());
			e.setName(model.getName());
			e.setRemark(model.getRemark());
			e.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			e.setCreated_by(Integer.valueOf(user.getId()+""));
			// e.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));?
			e.setType(parasite);
			quarantineService.save(e);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delParasite() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			Long id = q.getId();
			q.setDeleted(1);
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(q, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != null
				&& !"".equals(model.getId())) {
			Quarantine q = quarantineService.getById(model.getId());
			q.setId(model.getId());
			q.setName(model.getName());
			q.setRemark(model.getRemark());
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", q.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	// 病毒
	public String listVirus() {
		return "virusList";
	}

	public void loadListVirus() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				virusname, "QuarantineTypeVirus");
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void addVirus() {
		Map<String, Object> map = new HashMap<String, Object>();
		Quarantine e = new Quarantine();
		if (model.getName() != null && !"".equals(model.getName())) {
			e.setId(model.getId());
			e.setName(model.getName());
			e.setRemark(model.getRemark());
			e.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			e.setCreated_by(Integer.valueOf(user.getId()+""));
			// e.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));?
			e.setType("QuarantineTypeVirus");
			quarantineService.save(e);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delVirus() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			Long id = q.getId();
			q.setDeleted(1);
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditVirus() {
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(q, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSaveVirus() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != null
				&& !"".equals(model.getId())) {
			Quarantine q = quarantineService.getById(model.getId());
			q.setId(model.getId());
			q.setName(model.getName());
			q.setRemark(model.getRemark());
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", q.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void loadListMethod() {
		List<Map<String, String>> mapList = quarantineService
				.getMethod(Constant.virus);
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}

	// 细菌
	public String listBacteria() {
		return "bacteriaList";
	}

	public void loadListBacteria() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				bacterianame, Constant.bacteria);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void addBacteria() {
		Map<String, Object> map = new HashMap<String, Object>();
		Quarantine e = new Quarantine();
		if (model.getName() != null && !"".equals(model.getName())) {
			e.setId(model.getId());
			e.setName(model.getName());
			e.setRemark(model.getRemark());
			e.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			e.setCreated_by(Integer.valueOf(user.getId()+""));
			// e.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));?
			e.setType(Constant.bacteria);
			quarantineService.save(e);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delBacteria() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			Long id = q.getId();
			q.setDeleted(1);
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditBacteria() {
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(q, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSaveBacteria() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != null
				&& !"".equals(model.getId())) {
			Quarantine q = quarantineService.getById(model.getId());
			q.setId(model.getId());
			q.setName(model.getName());
			q.setRemark(model.getRemark());
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", q.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	// 疫苗接种
	public String listVaccine() {
		return "vaccineList";
	}

	public void loadListVaccine() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				vaccinename, Constant.vaccine);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void addVaccine() {
		Map<String, Object> map = new HashMap<String, Object>();
		Quarantine e = new Quarantine();
		if (model.getName() != null && !"".equals(model.getName())) {
			e.setId(model.getId());
			e.setName(model.getName());
			e.setRemark(model.getRemark());
			e.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			e.setCreated_by(Integer.valueOf(user.getId()+""));
			// e.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));?
			e.setType(Constant.vaccine);
			quarantineService.save(e);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delVaccine() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			Long id = q.getId();
			q.setDeleted(1);
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditVaccine() {
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(q, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSaveVaccine() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != null
				&& !"".equals(model.getId())) {
			Quarantine q = quarantineService.getById(model.getId());
			q.setId(model.getId());
			q.setName(model.getName());
			q.setRemark(model.getRemark());
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", q.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	// 传染病
	public String listInfectious() {
		return "infectiousList";
	}

	public void loadListInfectious() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				infectiousname, Constant.infectious);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void addInfectious() {
		Map<String, Object> map = new HashMap<String, Object>();
		Quarantine e = new Quarantine();
		if (model.getName() != null && !"".equals(model.getName())) {
			e.setId(model.getId());
			e.setName(model.getName());
			e.setRemark(model.getRemark());
			e.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			e.setCreated_by(Integer.valueOf(user.getId()+""));
			// e.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new
			// Date(),"yyyy-MM-dd")));?
			e.setType(Constant.infectious);
			quarantineService.save(e);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delInfectious() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			Long id = q.getId();
			q.setDeleted(1);
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditInfectious() {
		if (model.getId() != null) {
			Quarantine q = quarantineService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(q, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSaveInfectious() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != null
				&& !"".equals(model.getId())) {
			Quarantine q = quarantineService.getById(model.getId());
			q.setId(model.getId());
			q.setName(model.getName());
			q.setRemark(model.getRemark());
			quarantineService.update(q);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", q.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVirusname() {
		return virusname;
	}

	public void setVirusname(String virusname) {
		this.virusname = virusname;
	}

	public String getBacterianame() {
		return bacterianame;
	}

	public void setBacterianame(String bacterianame) {
		this.bacterianame = bacterianame;
	}

	public String getVaccinename() {
		return vaccinename;
	}

	public void setVaccinename(String vaccinename) {
		this.vaccinename = vaccinename;
	}

	public String getInfectiousname() {
		return infectiousname;
	}

	public void setInfectiousname(String infectiousname) {
		this.infectiousname = infectiousname;
	}

}
