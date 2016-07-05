package com.lanen.view.action.arp;

import java.util.HashMap;
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
public class ParasiteMAction extends BaseAction<Quarantine> {

	/**
	 * 防疫配置,寄生虫检疫方法
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private QuarantineService quarantineService;

	private String rows;
	private String page;
	private String name;

	public String list() {
		return "parasiteMList";
	}

	// 寄生虫
	public void loadList() {
		Map<String, Object> map = quarantineService.getQuarantine(page, rows,
				model.getName(), Constant.parasitemethod);
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
			e.setType(Constant.parasitemethod);
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

}
