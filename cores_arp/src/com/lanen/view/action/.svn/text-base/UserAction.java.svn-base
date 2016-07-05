package com.lanen.view.action;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Iplogin;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IploginService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;


/**
 * 用户
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<Employee> {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户Service*/
	@Resource
	private EmployeeService userService;
	@Resource
	private IploginService iploginService;
	
	private String ap_userName;
	private String ap_password;
	private String ap_newPassword;
	private String ap_reNewpassword;
	

	/** 登录页面*/
	public String loginUI() throws Exception {
		/*List<String> privilegeNameList = new ArrayList<String>();
		privilegeNameList.add("报表");
		privilegeNameList.add("系统设置");
		Map<String,Object> map = userService.newCheckPrivilege(model.getName(), model.getPassword(),privilegeNameList);
		//判断用户是否为空
		if("".equals(map.get("nullUserError"))){
			//用户非空
			Employee user =(Employee) map.get("user");
			if("".equals(map.get("forbidden"))){
				//账户未停用
				if("".equals(map.get("entitle"))){//用户有权限
					//用户密码未过期    或 已过期
					if("".equals(map.get("overdue"))){
						//未过期
						//保存登录用户
						ActionContext.getContext().getSession().put("user", user);
					}else{
						//密码已过期
						ActionContext.getContext().getSession().put("user2", user);
						//写日志
						//writeLog("登录","失败:密码已过期",user);
					}
				}else{
					//写日志
					//writeLog("登录","失败:未授权",user);
				}
			}else{
				//写日志
				//writeLog("登录","失败:账号已停用",user);
			}
		}else{
			//写日志
			//writeLog("登录","失败:用户名或密码错误",null,model.getUserName());
		}
		map.remove("user");
//		map = new HashMap<String,Object>();
//		map.put("success", true);
		String json = JsonPluginsUtil.beanToJson(map);
		
		
		//显示报表
		if(null != map.get("reportset") && map.get("reportset").equals("true")){
			ActionContext.getContext().getSession().put("reportset", 1);
		}else{
			ActionContext.getContext().getSession().put("reportset", 0);
		}
		//显示系统管理
		if(null != map.get("systemset") && map.get("systemset").equals("true")){
			ActionContext.getContext().getSession().put("systemset", 1);
		}else{
			ActionContext.getContext().getSession().put("systemset", 0);
		}
		//ticket
		if(null != map.get("ticket") ){
			ActionContext.getContext().getSession().put("ticket", map.get("ticket"));
		}else{
			ActionContext.getContext().getSession().remove("ticket");
		}
		
		
//		String json =JSONArray.fromObject(map).toString();
		//writeJson(json);
*/		return "loginUI";
	}
	
	
	/** 登录*/
	public void login() throws Exception {
		Employee user = userService.getByUserName(model.getUserid());
		Map<String,Object> map = new HashMap<String, Object>();
		//判断用户是否为空
		if(null != user){
			//用户非空
			String password = model.getPassword();
			String userName = user.getUserid();
			if(password.equals(user.getPassword())){
				
				//boolean ishas = userService.checkPrivilege(user, "系统登录");
				//if(ishas){
					ActionContext.getContext().getSession().put("user", user);
					
					//权限路径列表
					List<String> privilegeUrlList = userService.findPrivilegeUrlListByUserId(user.getEmployeeid());
					ActionContext.getContext().getSession().put("myPrivilegeUrlList", privilegeUrlList);
					
					//System.out.println("系统设置登录验证通过"+userName);
					//写日志
					//writeLog("登录系统","",user);
					//return "toIndexSystem";
				
					ActionContext.getContext().getSession().put("user", user);
					//权限路径列表
					System.out.println("系统设置登录验证通过"+userName);
					map.put("forbidden", "");
					map.put("nullUserError", "");
				//}else{
					//map.put("noPrivilege", "该用户没有登录权限!");
				//}
			}else{
				map.put("forbidden", "用户名或密码错误!");
				map.put("nullUserError", "");
			}
		}else{
			map.put("nullUserError", "用户名错误!");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	
	/** 注销*/
	public String logout() throws Exception {
		Employee user = (Employee) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().remove("user");
		//写日志
		writeLog("退出系统",user);
		return "logout";
	}
	
	/** 密码验证（json）*/
	public void passwordCheck(){
		Employee tempUser = (Employee) ActionContext.getContext().getSession().get("user");
		if(null==tempUser){
			tempUser = (Employee) ActionContext.getContext().getSession().get("user2");
		}
		Json json= new Json();
		if(null!=tempUser && null!=model.getPassword() &&!"".equals(model.getPassword())){
			if(tempUser.getPassword().equals(model.getPassword())){
				json.setSuccess(true);
				json.setMsg("密码验证通过");
			}else{
				json.setMsg("密码错误");
			}
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**修改密码*/
	public void alterPassword(){
		Json json = new Json();
		//参数不等于null ''
		if(null!=ap_userName && !"".equals(ap_userName) && null!=ap_password && !"".equals(ap_password)
				&&null!=ap_newPassword && !"".equals(ap_newPassword)&&null!=ap_reNewpassword && !"".equals(ap_reNewpassword)){
			//新旧密码不同
			if(ap_password != ap_newPassword){
				Employee user =(Employee) ActionContext.getContext().getSession().get("user");
				int flag =0;
				if(null==user){
					flag=1;//过期密码修改
					user =(Employee) ActionContext.getContext().getSession().get("user2");
				}
				//登陆用户不为空
				if(null != user){
					//用户名密码正确
					if(user.getUserid().equals(ap_userName) &&   user.getPassword().equals(ap_password)){
						user.setPassword(ap_newPassword);
						user.setDatemodified(new Date());
//						userService.updateWithTime(user);
						userService.update(user);
						ActionContext.getContext().getSession().put("user",user);
						if(flag==1){
							ActionContext.getContext().getSession().remove("user2");
						}
						json.setSuccess(true);
						json.setMsg("密码修改成功");
					}
				}
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}


	public void newCheckPrivilege()  {
		List<String> privilegeNameList = new ArrayList<String>();
		privilegeNameList.add("总览");
		privilegeNameList.add("报表");
		privilegeNameList.add("饲养管理");
		privilegeNameList.add("繁殖管理");
		privilegeNameList.add("防疫管理");
		privilegeNameList.add("治疗管理");
		privilegeNameList.add("系统设置");
		Map<String,Object> map = userService.newCheckPrivilege(model.getUserid(), model.getPassword(),privilegeNameList);
		//判断用户是否为空
		if("".equals(map.get("nullUserError"))){
			//用户非空
			Employee user =(Employee) map.get("user");
			if("".equals(map.get("forbidden"))){
				//账户未停用
				if("".equals(map.get("entitle"))){//用户有权限
					//用户密码未过期    或 已过期
					//if("".equals(map.get("overdue"))){
						//未过期
						//保存登录用户
						ActionContext.getContext().getSession().put("user", user);
						writeLog("登录系统",user);
					//}else{
						//密码已过期
						//ActionContext.getContext().getSession().put("user2", user);
						//写日志
						//writeLog("登录","失败:密码已过期",user);
					//}
				}else{
					//写日志
					writeLog("登录-失败:未授权",user);
				}
			}else{
				//写日志
				writeLog("登录-失败:账号已停用",user);
			}
		}else{
			//写日志
			//writeLog("登录-失败:用户名或密码错误",model.getName());
		}
		map.remove("user");
//		map = new HashMap<String,Object>();
//		map.put("success", true);
		//Employee user=(Employee)map.get("user");
		//user.setRoles1(null);
	
		//map.put("user", user);
		String json = JsonPluginsUtil.beanToJson(map);
		//显示总揽
		if(null != map.get("zonglSet") && map.get("zonglSet").equals("true")){
			ActionContext.getContext().getSession().put("zonglSet", 1);
		}else{
			ActionContext.getContext().getSession().put("zonglSet", 0);
		}
		
		//显示报表
		if(null != map.get("reportset") && map.get("reportset").equals("true")){
			ActionContext.getContext().getSession().put("reportset", 1);
		}else{
			ActionContext.getContext().getSession().put("reportset", 0);
		}
		//饲养
		if(null != map.get("siySet") && map.get("siySet").equals("true")){
			ActionContext.getContext().getSession().put("siySet", 1);
		}else{
			ActionContext.getContext().getSession().put("siySet", 0);
		}
		//繁殖
		if(null != map.get("fanzSet") && map.get("fanzSet").equals("true")){
			ActionContext.getContext().getSession().put("fanzSet", 1);
		}else{
			ActionContext.getContext().getSession().put("fanzSet", 0);
		}
		//防疫
		if(null != map.get("fangySet") && map.get("fangySet").equals("true")){
			ActionContext.getContext().getSession().put("fangySet", 1);
		}else{
			ActionContext.getContext().getSession().put("fangySet", 0);
		}
		//治疗
		if(null != map.get("zhilSet") && map.get("zhilSet").equals("true")){
			ActionContext.getContext().getSession().put("zhilSet", 1);
		}else{
			ActionContext.getContext().getSession().put("zhilSet", 0);
		}
		//显示系统管理
		if(null != map.get("systemset") && map.get("systemset").equals("true")){
			ActionContext.getContext().getSession().put("systemset", 1);
		}else{
			ActionContext.getContext().getSession().put("systemset", 0);
		}
		//ticket
		/*if(null != map.get("ticket") ){
			ActionContext.getContext().getSession().put("ticket", map.get("ticket"));
		}else{
			ActionContext.getContext().getSession().remove("ticket");
		}*/
		
		
//		
		//ActionContext.getContext().getSession().put("user", user);//roels1==null
		//return "loginUI";
		//String json =JSONArray.fromObject(map).toString();
		//写日志
		//writeLog("登录系统",user);
		writeJson(json);
	}

	/**
	 * 写日志
	 * @return
	 */
	
	private void writeLog(String datastring,Employee user){
		//记录设备登记日志
		  Iplogin tblLog = new Iplogin();
		  if(null!=user){
			  tblLog.setCreated_by(user.getId());
		  }
		  String ip=null;
		  if (request.getHeader("x-forwarded-for") == null) { 
		         ip= request.getRemoteAddr(); 
		        }else{
		        ip= request.getHeader("x-forwarded-for");
		        }
		  tblLog.setIp(ip);
		  tblLog.setCreatetime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")));
		  tblLog.setDeleted(0);
		  iploginService.save(tblLog);
	}
	public String getAp_userName() {
		return ap_userName;
	}


	public void setAp_userName(String apUserName) {
		ap_userName = apUserName;
	}


	public String getAp_password() {
		return ap_password;
	}


	public void setAp_password(String apPassword) {
		ap_password = apPassword;
	}


	public String getAp_newPassword() {
		return ap_newPassword;
	}


	public void setAp_newPassword(String apNewPassword) {
		ap_newPassword = apNewPassword;
	}


	public String getAp_reNewpassword() {
		return ap_reNewpassword;
	}


	public void setAp_reNewpassword(String apReNewpassword) {
		ap_reNewpassword = apReNewpassword;
	}
	
	
	
}
