package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Department;
import com.lanen.model.Employee;
import com.lanen.service.arp.DepartmentService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157760434073098087L;
	@Resource
	private DepartmentService departmentService;

	/**
	 * 查找所有的部门id和name
	 */
	public void getAllDepartmentNameId() {
		List<Map<String, Object>> list = departmentService.getAllDepIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 得到所有的部门信息
	 */
	public void loadList() {
		List<Department> list = departmentService.getAllDeps();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Department d = new Department();
		if (model.getName() != null && !"".equals(model.getName())) {
			d.setName(model.getName());
			d.setDescription(model.getDescription());
			Date date = new Date();
			d.setDateentered(date);
			d.setDatemodified(date);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			d.setCreatedby(user.getId() + "");
			d.setModifiedby(user.getId() + "");
			departmentService.save(d);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", d.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditDep() {
		if (model.getId() != null) {
			Department e = departmentService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(e, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Department d = departmentService.getById(model.getId());
			d.setName(model.getName());
			d.setDescription(model.getDescription());
			Date date = new Date();
			d.setDatemodified(date);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			d.setModifiedby(user.getId() + "");
			departmentService.update(d);
			map.put("success", true);
			map.put("msg", "修改成功");
			map.put("id", d.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	public void delDepartment() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Department d = departmentService.getById(model.getId());
			d.setDeleted(-1);
			departmentService.update(d);
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	/**
	 * 检查部门名是否已存在
	 */
	public void checkName() {
		if (null != model.getName() && !"".equals(model.getName())) {
			boolean isExist = departmentService.isExistName(model.getName());
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
