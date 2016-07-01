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
//	private String ticket;
	
//	private int isSystemSet =0;//1：显示系统管理
//	private int isStudyPlan =0;//1：显示课题管理系统
//	private int isSchedule = 0;//1：显示日程管理系统
//	private int isContract = 0;//1：显示委托管理系统
	
	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	@Resource
	private UserService userService;
	
	public String indexSystemSet(){
//		ActionContext.getContext().put("ticket", ticket);
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
//		isSystemSet=1;
//		if(userService.checkPrivilege(user, "SD登录")){
//			isStudyPlan =1;
//		}else{
//			isStudyPlan =0;
//		}
//		if(userService.checkPrivilege(user, "委托管理_登录")){
//			isContract =1;
//		}else{
//			isContract =0;
//		}
//		
//		if(userService.checkPrivilege(user, "综合管理-登录")){
//			isSchedule =1;
//		}else{
//			isSchedule =0;
//		}
		currentNum=0;
		//ActionContext.getContext().put("isStudyPlan", isStudyPlan);
		//ActionContext.getContext().put("isSystemSet", isSystemSet);
		//ActionContext.getContext().put("isSchedule", isSchedule);
		ActionContext.getContext().put("currentNum", currentNum);
		
		return "indexSystemSet";
	}
	public String topSystemSet(){
		return "topSystemSet";
	}
	
	public String mainSytemSet(){
		return "mainSytemSet";
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
		if(userService.checkPrivilege(user, "系统设置-毒性病理")){//权限模块  4
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "4";
		}
		if(userService.checkPrivilege(user, "系统设置-报表编号")){//权限模块  5
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "5";
		}
		if(userService.checkPrivilege(user, "系统设置-QA检查设置")){//权限模块  6
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "6";
		}
		if(userService.checkPrivilege(user, "系统设置-档案管理设置")){//权限模块  7
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "7";
		}
		if(userService.checkPrivilege(user, "系统设置-公司信息设置")){//权限模块  8
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "8";
		}
			
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){//
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "animalHome";
		}
		ActionContext.getContext().put("userprivilege",privilege);
		return "leftSystemSet";
	}
	public String right() throws Exception {
		return "right";
	}
	
	//-----------------------------------

	

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
