package com.lanen.view.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.model.User;

import com.lanen.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	
	@Resource
	private UserService userService;
	
	
	
	public String index() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
		currentNum=0;
		
		ActionContext.getContext().put("currentNum", currentNum);
		return "index";
	}
	
	public String indexSystemSet(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		currentNum=0;

		ActionContext.getContext().put("currentNum", currentNum);
		
		return "indexSystemSet";
	}
	public String topSystemSet(){
		return "topSystemSet";
	}
	
	public String mainSytemSet(){
		return "mainSytemSet";
	}
	
	public String top() throws Exception {
		return "top";
	}
	
	public String main() throws Exception {
		return "main";
	}

	
		
	public String leftSystemSet() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		String privilege = "";
		if(userService.checkPrivilege(user, "系统设置-临检指标")){//权限模块  1
			privilege = privilege + "1";
		}
		if(userService.checkPrivilege(user, "系统设置-试验相关")){//权限模块  2
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2";
			
		} 
		if(userService.checkPrivilege(user, "系统设置-资源分配")){//权限模块  3
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "3";
		}
		ActionContext.getContext().put("userprivilege",privilege);
		return "leftSystemSet";
	}
	public String right() throws Exception {
		return "right";
	}
	

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
