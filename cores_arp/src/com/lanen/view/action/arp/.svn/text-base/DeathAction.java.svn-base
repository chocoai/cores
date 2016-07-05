package com.lanen.view.action.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Death;
import com.lanen.model.Death_Json;
import com.lanen.model.Employee;
import com.lanen.model.Individual;
import com.lanen.service.arp.DeathService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;

@Controller
@Scope("prototype")
public class DeathAction extends BaseAction<Death> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854106155603814530L;
	@Resource
	private DeathService deathService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private IndividualService individualService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	public String list() {
		return "list";
	}

	@SuppressWarnings("unchecked")
	public void loadList() {
		Map<String, Object> map = deathService
				.getListByConditions(page, rows, model.getMonkeyid(),
						model.getDissectdate(), model.getRemarks());
		List<Death> list = (List<Death>) map.get("rows");
		List<Death_Json> list2 = new ArrayList<Death_Json>();
		for (Death d : list) {
			Death_Json json = new Death_Json();
			json.setId(d.getId());
			json.setMonkeyid(d.getMonkeyid());
			json.setDeathdate(d.getDeathdate());
			json.setDissectdate(d.getDissectdate());
			json.setVeterinarian(d.getVeterinarian());
			String veterinarianName = getEmployeeName(d.getVeterinarian());
			json.setVeterinarianName(veterinarianName);
			json.setDissectveterinarian(d.getDissectveterinarian());
			String dissectveterinarianName = getEmployeeName(d
					.getDissectveterinarian());
			json.setDissectveterinarianName(dissectveterinarianName);
			json.setPathology(d.getPathology());
			String pathologyName = getEmployeeName(d.getPathology());
			json.setPathologyName(pathologyName);
			json.setBoss(d.getBoss());
			String bossName = getEmployeeName(d.getBoss());
			json.setBossName(bossName);
			String kepperName = getEmployeeName(d.getKeeper());
			json.setKeeperName(kepperName);
			json.setRemarks(d.getRemarks());
			json.setBl_remarks(d.getBl_remarks());
			json.setSc_remarks(d.getSc_remarks());
			json.setOthers(d.getOthers());
			String recorderName = getEmployeeName(d.getRecorder());
			json.setRecorderName(recorderName);
			String operaterName = getEmployeeName(d.getOperater());
			json.setOperaterName(operaterName);
			list2.add(json);
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		Integer total = (Integer) map.get("total");
		map2.put("rows", list2);
		map2.put("total", total);
		String jsonStr = JsonPluginsUtil.beanToJson(map2, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 添加死亡记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Death d = new Death();
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			d.setMonkeyid(model.getMonkeyid());
			d.setDeathdate(model.getDeathdate());
			d.setDissectdate(model.getDissectdate());
			d.setVeterinarian(model.getVeterinarian());
			d.setDissectveterinarian(model.getDissectveterinarian());
			d.setPathology(model.getPathology());
			d.setBoss(model.getBoss());
			d.setKeeper(model.getKeeper());
			d.setRemarks(model.getRemarks());
			d.setBl_remarks(model.getBl_remarks());
			d.setSc_remarks(model.getSc_remarks());
			d.setOthers(model.getOthers());
			d.setRecorder(model.getRecorder());
			d.setOperater(model.getOperater());
			deathService.save(d);
			Individual individual = individualService.getByMonkeyid(d
					.getMonkeyid());
			individual.setDeleted(1);
			individualService.update(individual);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", d.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑前数据加载
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Death d = deathService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			Death d = deathService.getById(model.getId());
			if (model.getMonkeyid().equals(d.getMonkeyid())) {
				Individual individual = individualService.getByMonkeyid(d.getMonkeyid());
				if (!"".equals(individual)&&individual!=null) {
					individual.setDeleted(0);
					individualService.update(individual);
					Individual individual2 = individualService
							.getByMonkeyid(model.getMonkeyid());
					individual2.setDeleted(1);
					individualService.update(individual2);
				}
			}
			d.setMonkeyid(model.getMonkeyid());
			d.setDeathdate(model.getDeathdate());
			d.setDissectdate(model.getDissectdate());
			d.setVeterinarian(model.getVeterinarian());
			d.setDissectveterinarian(model.getDissectveterinarian());
			d.setPathology(model.getPathology());
			d.setBoss(model.getBoss());
			d.setKeeper(model.getKeeper());
			d.setRemarks(model.getRemarks());
			d.setBl_remarks(model.getBl_remarks());
			d.setSc_remarks(model.getSc_remarks());
			d.setOthers(model.getOthers());
			d.setRecorder(model.getRecorder());
			d.setOperater(model.getOperater());
			deathService.update(d);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", d.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 删除死亡记录
	 */
	public void delDeath() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Death d = deathService.getById(model.getId());
			String monkeyid = d.getMonkeyid();
			Individual individual = individualService.getByMonkeyid(monkeyid);
			individual.setDeleted(0);
			individualService.update(individual);
			deathService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 根据员工id查询员工名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmployeeName(Long id) {
		String name = null;
		if (id != null) {
			Employee e = employeeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}
}
