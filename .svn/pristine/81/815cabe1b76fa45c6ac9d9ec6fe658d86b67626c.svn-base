package com.lanen.view.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 1L;
	protected HttpServletResponse response;
	public String index() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
		return "index";
	}
	/**
	 * 加载系统名称列表
	 */
	public void loadSystemName(){
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		//1：临床检验管理系统
		map = new HashMap<String,Object>();
		map.put("id", 1);
		map.put("text", "临床检验管理系统");
		mapList.add(map);
		//2：毒性病理管理系统
		map = new HashMap<String,Object>();
		map.put("id", 2);
		map.put("text", "毒性病理管理系统");
		mapList.add(map);
		//3：综合管理系统
		map = new HashMap<String,Object>();
		map.put("id", 3);
		map.put("text", "综合管理系统");
		mapList.add(map);
		//4：一般毒理系统
		map = new HashMap<String,Object>();
		map.put("id", 4);
		map.put("text", "一般毒理系统");
		mapList.add(map);
		//5：QA管理系统
		map = new HashMap<String,Object>();
		map.put("id", 5);
		map.put("text", "QA管理系统");
		mapList.add(map);
		//6：委托管理系统
		map = new HashMap<String,Object>();
		map.put("id", 6);
		map.put("text", "委托管理系统");
		mapList.add(map);
		
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		
		HttpServletResponse response2 = ServletActionContext.getResponse();
		response2.setCharacterEncoding("utf-8");
		response2.setContentType("text/html;charset=utf-8");
		try {
			response2.getWriter().write(jsonStr);
			response2.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().write(jsonStr);
//			response.getWriter().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	public String main() throws Exception {
		return "main";
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
