package com.lanen.view.action;


import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.model.User;

import com.lanen.service.UserService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	
	@Resource
	private UserService userService;
	
	
	
	public String indexArchive() throws Exception{
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
		boolean isEdit = userService.checkPrivilege(user, "SOP管理_编辑");
		boolean isCheck = userService.checkPrivilege(user, "SOP管理_查看");
		if(isEdit)
		{
			ActionContext.getContext().put("privilegeType",1);
		}else if(isCheck)
		{
			ActionContext.getContext().put("privilegeType",0);
		}
			
		ActionContext.getContext().put("todayDate",DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		
		//currentNum=0;
		//ActionContext.getContext().put("currentNum", currentNum);
		return "indexArchive";
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
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		
		return "main";
	}

	public String left() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 1);
		ActionContext.getContext().put("afterOneMonth",DateUtil.dateToString(c.getTime(), "yyyy-MM-dd"));
		//ActionContext.getContext().put("userprivilege",privilege);
		return "left";
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
