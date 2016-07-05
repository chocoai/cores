package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Title;
import com.lanen.service.arp.TitleService;

@Controller
@Scope("prototype")
public class TitleAction extends BaseAction<Title> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2392831060452590262L;
	@Resource
	private TitleService titleService;

	/**
	 * 查找所有职称表的id和name
	 */
	public void getAllTitleIdName() {
		List<Map<String, Object>> list = titleService.getAllTitIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 得到所有的职称信息
	 */
	public void loadList() {
		List<Title> list = titleService.getAllTits();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Title t = new Title();
		if (model.getName() != null && !"".equals(model.getName())) {
			t.setName(model.getName());
			t.setDescription(model.getDescription());

			titleService.save(t);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", t.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEditTit() {
		if (model.getId() != null) {
			Title e = titleService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(e, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Title t = titleService.getById(model.getId());
			t.setName(model.getName());
			t.setDescription(model.getDescription());

			titleService.update(t);
			map.put("success", true);
			map.put("msg", "修改成功");
			map.put("id", t.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	public void delTitle() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Title d = titleService.getById(model.getId());
			d.setDeleted(-1);
			titleService.update(d);
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	/**
	 * 检查职称名是否已存在
	 */
	public void checkName() {
		if (null != model.getName() && !"".equals(model.getName())) {
			boolean isExist = titleService.isExistName(model.getName());
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
