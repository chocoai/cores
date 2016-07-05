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
import com.lanen.model.Position;
import com.lanen.model.Position_Json;
import com.lanen.service.arp.DepartmentService;
import com.lanen.service.arp.PositionService;

@Controller
@Scope("prototype")
public class PositionAction extends BaseAction<Position> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900614046012286644L;
	@Resource
	private PositionService positionService;
	@Resource
	private DepartmentService departmentService;

	/**
	 * 根据部门查找所有职位的id和name
	 */
	public void getAllPositionIdName() {
		List<Map<String, Object>> list = positionService.getAllPosIdName(model
				.getDepartment_id());
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 得到所有的部门信息
	 */
	public void loadList() {
		List<Position> list = positionService.getALLPosOrderByDid();
		List<Position_Json> list2 = new ArrayList<Position_Json>();
		for (Position p : list) {
			Position_Json json = new Position_Json();
			json.setId(p.getId());
			json.setName(p.getName());
			json.setDescription(p.getDescription());
			Integer department_id = p.getDepartment_id();
			if (department_id != null) {
				String name = departmentService.getNameById(department_id);
				json.setDepartment(name);
			}
			list2.add(json);
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list2);
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Position p = new Position();
		if (model.getName() != null && !"".equals(model.getName())) {
			p.setName(model.getName());
			p.setDescription(model.getDescription());
			p.setDepartment_id(model.getDepartment_id());
			positionService.save(p);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", p.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditPos() {
		if (model.getId() != null) {
			Position p = positionService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(p, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Position p = positionService.getById(model.getId());
			p.setName(model.getName());
			p.setDescription(model.getDescription());
			p.setDepartment_id(model.getDepartment_id());
			positionService.update(p);
			map.put("success", true);
			map.put("msg", "修改成功");
			map.put("id", p.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	public void delPosition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			positionService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	/**
	 * 检查职位名是否已存在
	 */
	public void checkName() {
		if (null != model.getName() && !"".equals(model.getName())) {
			boolean isExist = positionService.isExistName(model.getName());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}
}
