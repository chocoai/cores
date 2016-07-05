package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Childbirth;
import com.lanen.service.arp.ChildbirthService;

@Controller
@Scope("prototype")
public class ChildbirthAction extends BaseAction<Childbirth> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659704101675721984L;
	/**
	 * 产仔登记 service
	 */
	@Resource
	private ChildbirthService childbirthService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	/**
	 * 跳转到产仔登记主页面
	 * 
	 * @return
	 */
	public String list() {
		return "childbirthList";
	}

	/**
	 * 根据条件加载主页面妊娠检查数据
	 */
	public void loadList() {
		Map<String, Object> mapList = childbirthService.loadListByCondition(
				page, rows, model.getMonkeyid(), model.getLabordate(),
				model.getChildercount());
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 添加妊娠检查记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Childbirth childbirth = new Childbirth();
		if (null != model.getMonkeyid() && !model.getMonkeyid().equals("")) {
			childbirth.setMonkeyid(model.getMonkeyid());
			childbirth.setMonkeyids(model.getMonkeyids());
			childbirth.setLabordate(model.getLabordate());
			childbirth.setLaborcondition(model.getLaborcondition());
			childbirth.setChildercount(model.getChildercount());
			childbirth.setKeeper(model.getKeeper());
			childbirth.setVeterinarian(model.getVeterinarian());
			childbirth.setProtector(model.getProtector());
			childbirth.setRecorder(model.getRecorder());
			childbirth.setOperater(model.getOperater());
			childbirth.setCreatetime(new Date());
			childbirth.setDeleted(0);
			childbirthService.save(childbirth);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", childbirth.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑妊娠检查前加载数据
	 */
	public void toEdit() {
		if (null != model.getId() && model.getId() != 0) {
			Childbirth childbirth = childbirthService.getById(model.getId());
			String jsonString = JsonPluginsUtil.beanToJson(childbirth,
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
			Childbirth childbirth = childbirthService.getById(model.getId());
			childbirth.setMonkeyid(model.getMonkeyid());
			childbirth.setMonkeyids(model.getMonkeyids());
			childbirth.setLabordate(model.getLabordate());
			childbirth.setLaborcondition(model.getLaborcondition());
			childbirth.setChildercount(model.getChildercount());
			childbirth.setKeeper(model.getKeeper());
			childbirth.setVeterinarian(model.getVeterinarian());
			childbirth.setProtector(model.getProtector());
			childbirth.setRecorder(model.getRecorder());
			childbirth.setOperater(model.getOperater());
			childbirth.setCreatetime(new Date());
			childbirth.setDeleted(0);
			childbirthService.update(childbirth);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", childbirth.getId());
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
			childbirthService.delete(model.getId());
			// Childbirth childbirth=childbirthService.getById(model.getId());
			// childbirth.setDeleted(1);
			// childbirthService.update(childbirth);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}
}
