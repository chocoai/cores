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
//	
//	private int isSystemSet =0;//1：显示系统管理
//	private int isStudyPlan =0;//1：显示课题管理系统
//	private int isSchedule = 0;//1：显示综合管理系统
//	private int isContract = 0;//1:显示委托管理
	
	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	@Resource
	private UserService userService;
	
	
	
	public String index() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
		
		currentNum=0;
		return "index";
	}
	public String main() throws Exception {
		return "main";
	}
	
	/**
	 * 加载左边导航页面，同时查询该用户权限，放入session中
	 * @return
	 * @throws Exception
	 */
	public String left() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		
		//是否有编辑权限
		if(userService.checkPrivilege(user, "委托管理_编辑")){
			ActionContext.getContext().getSession().put("write", true);
			ActionContext.getContext().getSession().put("read", true);
		}else{
			ActionContext.getContext().getSession().put("write", false);
			//是否有查看权限
			if(userService.checkPrivilege(user, "委托管理_查看")){
				ActionContext.getContext().getSession().put("read", true);
			}else{
				ActionContext.getContext().getSession().put("read",false);
			}
		}
		//是否有登记权限
		if(userService.checkPrivilege(user, "委托管理_登记")){
			ActionContext.getContext().getSession().put("add",true);
		}else{
			ActionContext.getContext().getSession().put("add",false);
		}
		

		return "left";
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
