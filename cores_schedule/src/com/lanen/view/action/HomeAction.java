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
//	private int isSchedule = 0;//1：显示日程管理系统
//	private int isContract = 0;//1：显示委托管理系统
	
	private int isNotification = 0;//1:显示通知管理
	private int islAppointSD = 0;//显示SD任命
	private int isSelectSchedule;//显示日程管理查看
	private int attachment;//显示文件打印
	private int QAManagement;//显示QA管理
	
	
	
	
	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	private int tblTestItem = 0;//1.表示显示供试品 
	@Resource
	private UserService userService;
	
	
	
	public String index() throws Exception {
//		ActionContext.getContext().put("ticket", ticket);
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
//		isSchedule = 1;
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
//		if(userService.checkPrivilege(user, "系统设置登录")){
//			isSystemSet =1;
//		}else{
//			isSystemSet =0;
//		}
		currentNum=0;
		isNotification =1;
		
//		if(userService.checkPrivilege(user, "通知管理-查看")){
//			isNotification =1;
//		}else{
//			isNotification =0;
//		}
		if(userService.checkPrivilege(user, "SD任命-查看")){
			islAppointSD =1;
		}else{
			islAppointSD =0;
		}
		if(userService.checkPrivilege(user, "日程管理-查看")){
			isSelectSchedule=1;
		}else{
			isSelectSchedule =0;
		}
		tblTestItem = 0;
		if(userService.checkPrivilege(user, "供试品信息-查看")){
			tblTestItem = 1;
		}else{
			tblTestItem = 0;
		}
		attachment = 0;
		if(userService.checkPrivilege(user, "文件打印-查看")){
			attachment = 1;
		}else{
			attachment = 0;
		}
		QAManagement = 0;
		//userService.checkPrivilege(user, "QA")||userService.checkPrivilege(user, "QA负责人")
		if(userService.checkPrivilege(user, "SD")||userService.checkPrivilege(user, "FM")){//SD,FM都可以查看
			QAManagement = 1;
		}else{
			QAManagement = 0;
		}
		
		
		
		
		String privilege = "";
		//日常查看权限
		//		日程管理-登录
		//		日程管理-查看-委托管理
		//		日程管理-查看-动物试验
		//		日程管理-查看-临床检验
		//		日程管理-查看-毒性病理
		//		日程管理-查看-QA管理
		//		日程管理-查看-供试品管理
		if(userService.checkPrivilege(user, "日程管理-查看-委托管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-1";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-动物试验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-2";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-临床检验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-3";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-毒性病理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-QA管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-5";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-供试品管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-6";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-分析")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-7";
		} 
		if(userService.checkPrivilege(user, "日程管理-查看-生态毒理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "1-8";
		} 

//		日程管理-分配权限
//		日程管理-分配-资源负责人
//		日程管理-分配-专题任务分配
//		日程管理-分配-专题资源分配
//		日程管理-常规任务分配-委托管理
//		日程管理-常规任务分配-动物试验
//		日程管理-常规任务分配-临床检验
//		日程管理-常规任务分配-毒性病理
//		日程管理-常规任务分配-QA管理
//		日程管理-常规任务分配-供试品管理
		
		if(userService.checkPrivilege(user, "日程管理-分配-资源负责人")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-1";
		} 
		if(userService.checkPrivilege(user, "日程管理-分配-专题任务分配")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2";
		} 
		if(userService.checkPrivilege(user, "日程管理-分配-专题资源分配")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-3";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-1";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-2";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-3";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-5";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-6";
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-7";
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2-8";
		}
		ActionContext.getContext().put("indexuserprivilege",privilege);
		return "index";
	}
	public String schedule() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		String privilege = "";
		if(null != user){
			privilege = privilege + "1";
			if(userService.checkPrivilege(user, "日程管理-分配")){//权限模块  2
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2";
			} 
			if(userService.checkPrivilege(user, "日程管理-分配-资源负责人")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-1";
			} 
			if(userService.checkPrivilege(user, "日程管理-分配-专题任务分配")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-2";
			} 
			if(userService.checkPrivilege(user, "日程管理-分配-专题资源分配")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-3";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			} 
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			}
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			}
			
			if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
				if(privilege != ""){
					privilege = privilege +","; 
				}
				privilege = privilege + "2-4";
			}
		}
		ActionContext.getContext().put("userprivilege",privilege);
		return "schedule";
	}
	public String main() throws Exception {
		return "main";
	}
	public String leftSchedule() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		String privilege = "";
		privilege = privilege + "1";
		if(userService.checkPrivilege(user, "日程管理-分配")){//权限模块  2
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2";
		} 
		if(userService.checkPrivilege(user, "日程管理-分配-资源负责人")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-1";
		} 
		if(userService.checkPrivilege(user, "日程管理-分配-专题任务分配")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-2";
		} 
		if(userService.checkPrivilege(user, "日程管理-分配-专题资源分配")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-3";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		} 
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		}
		
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2-4";
		}
		ActionContext.getContext().put("userprivilege",privilege);
		return "leftSchedule";
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

	public int getIsNotification() {
		return isNotification;
	}
	public void setIsNotification(int isNotification) {
		this.isNotification = isNotification;
	}
	public int getIslAppointSD() {
		return islAppointSD;
	}
	public void setIslAppointSD(int islAppointSD) {
		this.islAppointSD = islAppointSD;
	}
	public int getIsSelectSchedule() {
		return isSelectSchedule;
	}
	public void setIsSelectSchedule(int isSelectSchedule) {
		this.isSelectSchedule = isSelectSchedule;
	}
	public int getTblTestItem() {
		return tblTestItem;
	}
	public void setTblTestItem(int tblTestItem) {
		this.tblTestItem = tblTestItem;
	}
	public int getAttachment() {
		return attachment;
	}
	public void setAttachment(int attachment) {
		this.attachment = attachment;
	}
	public int getQAManagement() {
		return QAManagement;
	}
	public void setQAManagement(int qAManagement) {
		QAManagement = qAManagement;
	}
	

}
