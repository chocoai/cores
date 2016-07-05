package com.lanen.view.action.arp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Education;
import com.lanen.service.arp.EducationService;

@Controller
@Scope("prototype")
public class EducationAction extends BaseAction<Education> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3532093241052446400L;
	@Resource
	private EducationService educationService;

	/**
	 * 查找所有学历表的id和name
	 */
	public void getAllEducationIdName() {
		List<Map<String, Object>> list = educationService.getAllEduIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}
}
