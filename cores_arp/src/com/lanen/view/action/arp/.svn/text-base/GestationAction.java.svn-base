package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Gestation;
import com.lanen.service.arp.GestationService;

@Controller
@Scope("prototype")
public class GestationAction extends BaseAction<Gestation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5719787317601049388L;

	/**
	 * 妊娠检查 service
	 */
	@Resource
	private GestationService gestationService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	/**
	 * 跳转到妊娠检查主页面
	 * 
	 * @return
	 */
	public String list() {
		return "gestationList";
	}

	/**
	 * 根据条件加载主页面妊娠检查数据
	 */
	public void loadList() {
		Map<String, Object> mapList = gestationService.loadListByCondition(
				page, rows, model.getMonkeyid(), model.getCheckdate(),
				model.getIshave());
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 添加妊娠检查记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Gestation gestation = new Gestation();
		if (null != model.getMonkeyid() && !model.getMonkeyid().equals("")) {
			gestation.setMonkeyid(model.getMonkeyid());
			gestation.setCheckdate(model.getCheckdate());
			gestation.setIshave(model.getIshave());
			gestation.setRemarks(model.getRemarks());
			gestation.setVeterinarian(model.getVeterinarian());
			gestation.setProtector(model.getProtector());
			gestation.setRecorder(model.getRecorder());
			gestation.setOperater(model.getOperater());
			gestation.setCreatetime(new Date());
			gestation.setDeleted(0);
			gestationService.save(gestation);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", gestation.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑妊娠检查前加载数据
	 */
	public void toEdit() {
		if (null != model.getId() && model.getId() != 0) {
			Gestation gestation = gestationService.getById(model.getId());
			String jsonString = JsonPluginsUtil.beanToJson(gestation,
					"yyyy-MM-dd");
			writeJson(jsonString);
		}
	}

	/**
	 * 编辑妊娠检查后更新数据
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != model.getId() && model.getId() != 0) {
			Gestation gestation = gestationService.getById(model.getId());
			gestation.setMonkeyid(model.getMonkeyid());
			gestation.setCheckdate(model.getCheckdate());
			gestation.setIshave(model.getIshave());
			gestation.setRemarks(model.getRemarks());
			gestation.setVeterinarian(model.getVeterinarian());
			gestation.setProtector(model.getProtector());
			gestation.setRecorder(model.getRecorder());
			gestation.setOperater(model.getOperater());
			gestation.setLastmodifytime(new Date());
			gestationService.update(gestation);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", gestation.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 妊娠检查记录删除
	 */
	public void delete() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != model.getId() && model.getId() != 0) {
			gestationService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
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
}
