package com.lanen.view.action;

//import javax.annotation.Resource;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.model.Employee;
import com.lanen.service.arp.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<Employee>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String ticket;
	
	private int isSystemSet =0;//1：显示系统管理
	private int isReportSet =0;//1：显示报表
	private int isZonglSet=0;
	private int isSiySet=0;//饲养
	private int isFanzSet=0;
	private int isFangySet=0;
	private int isZhilSet=0;
	@Resource
	private EmployeeService employeeService;
	
	public String indexSystemSet(){
		Employee user = (Employee) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userid", user.getUserid());
		}
		return "indexSystemSet";
	}
	public String topSystemSet(){
		return "topSystemSet";
	}
	
	public String mainSytemSet(){
		return "mainSytemSet";
	}
	/**左侧功能菜单*/
	public String leftSystemSet() throws Exception {
		if(null != model.getName()){
			Employee user = (Employee) ActionContext.getContext().getSession().get("user");
			if(null != user && model.getName().equals(user.getUserid())){
				isReportSet =(Integer) ActionContext.getContext().getSession().get("reportset");
				isSystemSet =(Integer) ActionContext.getContext().getSession().get("systemset");
				isZonglSet =(Integer) ActionContext.getContext().getSession().get("zonglSet");
				isSiySet =(Integer) ActionContext.getContext().getSession().get("siySet");
				isFanzSet =(Integer) ActionContext.getContext().getSession().get("fanzSet");
				isFangySet =(Integer) ActionContext.getContext().getSession().get("fangySet");
				isZhilSet =(Integer) ActionContext.getContext().getSession().get("zhilSet");
			}else{
				//后续优化.
				Employee tempUser = employeeService.getByUserName(model.getName());
				if(employeeService.isHasPrivilege(model.getName(), "系统设置")){
					isSystemSet =1;
				}else{
					isSystemSet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "报表")){
					isReportSet =1;
				}else{
					isReportSet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "总览")){
					isZonglSet =1;
				}else{
					isZonglSet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "饲养管理")){
					isSiySet =1;
				}else{
					isSiySet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "繁殖管理")){
					isFanzSet =1;
				}else{
					isFanzSet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "防疫管理")){
					isFangySet =1;
				}else{
					isFangySet =0;
				}
				if(employeeService.isHasPrivilege(model.getName(), "治疗管理")){
					isZhilSet =1;
				}else{
					isZhilSet =0;
				}

//				myticket = tempUser.getPassword()+CryptUtils.encryptString(tempUser.getName());
			}
		}
		Employee Useraddroles1 = employeeService.getByUserName(model.getName());
		ActionContext.getContext().getSession().put("user", Useraddroles1);
		return "leftSystemSet";
	}
	public String right() throws Exception {
		return "right";
	}
	
	//-----------------------------------

	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getIsSystemSet() {
		return isSystemSet;
	}
	public void setIsSystemSet(int isSystemSet) {
		this.isSystemSet = isSystemSet;
	}
	public int getIsReportSet() {
		return isReportSet;
	}
	public void setIsReportSet(int isReportSet) {
		this.isReportSet = isReportSet;
	}
	public int getIsZonglSet() {
		return isZonglSet;
	}
	public void setIsZonglSet(int isZonglSet) {
		this.isZonglSet = isZonglSet;
	}
	public int getIsSiySet() {
		return isSiySet;
	}
	public void setIsSiySet(int isSiySet) {
		this.isSiySet = isSiySet;
	}
	public int getIsFanzSet() {
		return isFanzSet;
	}
	public void setIsFanzSet(int isFanzSet) {
		this.isFanzSet = isFanzSet;
	}
	public int getIsFangySet() {
		return isFangySet;
	}
	public void setIsFangySet(int isFangySet) {
		this.isFangySet = isFangySet;
	}
	public int getIsZhilSet() {
		return isZhilSet;
	}
	public void setIsZhilSet(int isZhilSet) {
		this.isZhilSet = isZhilSet;
	}
	
}
