package com.lanen.view.action;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.util.CryptUtils;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ap_userName;
	private String ap_password;
	private String ap_newPassword;
	private String ap_reNewpassword;
	
	
	private String myticket;
	private String from ;
	private int isSystemSet =0;//1：显示系统管理
	private int isStudyPlan =0;//1：显示课题管理系统
	private int isSchedule = 0;//1：显示日程管理系统
	private int isContract = 0;//1：显示委托管理系统
	private int isQA = 0;	   //1:显示QA
	private int isSOP = 0;	   //1:显示SOP
	private int isArchive = 0; //1：显示档案管理
	private int audit = 0;	   //1:显示系统审计
	
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	
//	private String username;
	
	/** 用户Service*/
	@Resource
	private UserService UserService;
	
	/** 登录页面*/
	public String loginUI() throws Exception {
		return "loginUI";
	}
//	/** 登录*/
//	public String login() throws Exception {
//		User user =(User) ActionContext.getContext().getSession().get("user");
//		if(user== null){
//			//登录不成功
//			addFieldError("error","用户名或密码错误！");
//			//写日志
//			writeLog("登录","失败:用户名或密码错误",null);
//			return "loginUI";
//		}else{
//			if(user.getUserName().equals("admin")||user.getUserName().equals("administrator")){
//				//ActionContext.getContext().getSession().put("user", user);
//				//写日志
//				writeLog("登录","成功："+user.getUserName(),user);
//				return "toIndexSystem";
//			}else{
//				if(user.getFlag().equals("停用")){
//					addFieldError("error","账号已停用，请联系管理员！");
//					//写日志
//					writeLog("登录","失败:账号已停用",user);
//					return "loginUI";
//				}else{
//					//ActionContext.getContext().getSession().put("user", user);
//					//写日志
//					writeLog("登录","成功："+user.getUserName(),user);
//					//"SD登录","系统设置登录")
//					if(userService.checkPrivilege(user, "SD登录")){
//						return "toIndex";
//					}else if(userService.checkPrivilege(user, "系统设置登录")){
//						return "toIndexSystem";
//					}else{
//						return "loginUI";
//					}
//					
//				}
//			}
//		}
		
//	}
	/**修改密码*/
	public void alterPassword(){
//		private String ap_userName;
//		private String ap_password;
//		private String ap_newPassword;
//		private String ap_reNewpassword;
		Json json = new Json();
		//参数不等于null ''
		if(null!=ap_userName && !"".equals(ap_userName) && null!=ap_password && !"".equals(ap_password)
				&&null!=ap_newPassword && !"".equals(ap_newPassword)&&null!=ap_reNewpassword && !"".equals(ap_reNewpassword)){
			//新旧密码不同
			if(ap_password != ap_newPassword){
				User user =null;//当前仅有过期密码修改(User) ActionContext.getContext().getSession().get("user");
				int flag =0;
//				if(null==user){
					flag=1;//过期密码修改
					user =(User) ActionContext.getContext().getSession().get("user2");
//				}
				//登陆用户不为空
				if(null != user){
					//用户名密码正确
					if(user.getUserName().equals(ap_userName) &&   user.getPassword().equals(DigestUtils.md5Hex(ap_password))){
						user.setPassword(DigestUtils.md5Hex(ap_newPassword));
						user.setUpdatePasswordTime(new Date());
//						userService.updateWithTime(user);
						userService.updatePwd(user, ap_newPassword);
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
	/** 注销*/
	public String logout() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().getSession().remove("user");
		//写日志
		writeLog("退出系统","",user);
		return "logout";
	}
	/** 导航菜单*/
	public String menu() throws Exception {
		if(null != model.getUserName()){
			User user = (User) ActionContext.getContext().getSession().get("user");
			if(null != user && model.getUserName().equals(user.getUserName())){
				isSchedule =(Integer) ActionContext.getContext().getSession().get("schedule");
				isStudyPlan =(Integer) ActionContext.getContext().getSession().get("study");
				isContract =(Integer) ActionContext.getContext().getSession().get("contract");
				isSystemSet =(Integer) ActionContext.getContext().getSession().get("systemset");
				isQA = (Integer) ActionContext.getContext().getSession().get("qa");
				isSOP = (Integer) ActionContext.getContext().getSession().get("sop");
				isArchive =(Integer) ActionContext.getContext().getSession().get("varchive");
				audit = (Integer) ActionContext.getContext().getSession().get("audit");
				myticket = (String) ActionContext.getContext().getSession().get("ticket");
//				ActionContext.getContext().put("myticket", myticket);
			}else{
				User tempUser = userService.getByUserName(model.getUserName());
				if(userService.isHasPrivilege(model.getUserName(), "系统设置登录")){
					isSystemSet =1;
				}else{
					isSystemSet =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "SD登录")){
					isStudyPlan =1;
				}else{
					isStudyPlan =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "委托管理_登录")){
					isContract =1;
				}else{
					isContract =0;
				}
				
				if(userService.isHasPrivilege(model.getUserName(), "综合管理-登录")){
					isSchedule =1;
				}else{
					isSchedule =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "系统审计_登录")){
					audit =1;
				}else{
					audit =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "QA")||userService.isHasPrivilege(model.getUserName(), "QA负责人")){
					isQA =1;
				}else{
					isQA =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "SOP管理_查看")||userService.isHasPrivilege(model.getUserName(), "SOP管理_编辑")){
					isSOP =1;
				}else{
					isSOP =0;
				}
				if(userService.isHasPrivilege(model.getUserName(), "档案管理_查看")||userService.isHasPrivilege(model.getUserName(), "档案管理_编辑")){
					isArchive =1;
				}else{
					isArchive =0;
				}
				
				myticket = tempUser.getPassword()+CryptUtils.encryptString(tempUser.getUserName());
			}
		}
		
		return "menu";
	}
	
	/** 密码验证（json）*/
	public void passwordCheck(){
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user2");
		if(null==tempUser){
			tempUser = (User) ActionContext.getContext().getSession().get("user");
		}
		Json json= new Json();
		if(null!=tempUser && null!=model.getPassword() &&!"".equals(model.getPassword())){
			if(tempUser.getPassword().equals(DigestUtils.md5Hex(model.getPassword()))){
				json.setSuccess(true);
				json.setMsg("密码验证通过");
			}else{
				json.setMsg("密码错误");
			}
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**密码修改后获得ticket*/
	public void getTicket(){
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String ticket="";
		if(null != tempUser){
			ticket = tempUser.getPassword()+CryptUtils.encryptString(tempUser.getUserName());
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("ticket", ticket);
		//把ticket 保存到session里,menu要调用
		ActionContext.getContext().getSession().put("ticket", map.get("ticket"));
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**
	 * 
	 * 权限验证（包括密码）(json)
	 * 
	 * 
	 * 同时把可以登录的系统的信息保存到session  ,menu时用,
	 * 包括   ticket ,在密码修改是重新保存ticket
	 * 
	 * 
	 * */
	public void newCheckPrivilege(){
		List<String> privilegeNameList = new ArrayList<String>();
		privilegeNameList.add("SD登录");
		privilegeNameList.add("系统设置登录");
		privilegeNameList.add("综合管理-登录");
		privilegeNameList.add("委托管理_登录");
		privilegeNameList.add("QA");
		privilegeNameList.add("QA负责人");
		privilegeNameList.add("系统审计_登录");
		privilegeNameList.add("SOP管理_查看");
		privilegeNameList.add("SOP管理_编辑");
		privilegeNameList.add("档案管理_查看");
		privilegeNameList.add("档案管理_编辑");
		
		Map<String,Object> map = userService.newCheckPrivilege(model.getUserName(), model.getPassword(),privilegeNameList);
		//判断用户是否为空
		if("".equals(map.get("nullUserError"))){
			//用户非空
			User user =(User) map.get("user");
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
						writeLog("登录","失败:密码已过期",user);
					}
				}else{
					//写日志
					writeLog("登录","失败:未授权",user);
				}
			}else{
				//写日志
				writeLog("登录","失败:账号已停用",user);
			}
		}else{
			//写日志
			writeLog("登录","失败:用户名或密码错误",null,model.getUserName());
		}
		map.remove("user");
//		map = new HashMap<String,Object>();
//		map.put("success", true);
		String json = JsonPluginsUtil.beanToJson(map);
		
		//显示日程管理系统
		if(null != map.get("schedule") && map.get("schedule").equals("true")){
			ActionContext.getContext().getSession().put("schedule", 1);
		}else{
			ActionContext.getContext().getSession().put("schedule", 0);
		}
		//显示课题管理系统
		if(null != map.get("study") && map.get("study").equals("true")){
			ActionContext.getContext().getSession().put("study", 1);
		}else{
			ActionContext.getContext().getSession().put("study", 0);
		}
		//显示委托管理系统
		if(null != map.get("contract") && map.get("contract").equals("true")){
			ActionContext.getContext().getSession().put("contract", 1);
		}else{
			ActionContext.getContext().getSession().put("contract", 0);
		}
		//显示系统管理
		if(null != map.get("systemset") && map.get("systemset").equals("true")){
			ActionContext.getContext().getSession().put("systemset", 1);
		}else{
			ActionContext.getContext().getSession().put("systemset", 0);
		}
		//显示QA
		if(null != map.get("qa") && map.get("qa").equals("true")){
			ActionContext.getContext().getSession().put("qa", 1);
		}else{
			ActionContext.getContext().getSession().put("qa", 0);
		}
		//显示SOP
		if(null != map.get("sop") && map.get("sop").equals("true")){
			ActionContext.getContext().getSession().put("sop", 1);
		}else{
			ActionContext.getContext().getSession().put("sop", 0);
		}
		//显示档案管理
		if(null != map.get("varchive") && map.get("varchive").equals("true")){
			ActionContext.getContext().getSession().put("varchive", 1);
		}else{
			ActionContext.getContext().getSession().put("varchive", 0);
		}
		//显示系统审计
		if(null != map.get("audit") && map.get("audit").equals("true")){
			ActionContext.getContext().getSession().put("audit", 1);
		}else{
			ActionContext.getContext().getSession().put("audit", 0);
		}
		//ticket
		if(null != map.get("ticket") ){
			ActionContext.getContext().getSession().put("ticket", map.get("ticket"));
		}else{
			ActionContext.getContext().getSession().remove("ticket");
		}
		
		
//		String json =JSONArray.fromObject(map).toString();
		writeJson(json);
	}
	
	/**
	 * 写日志
	 * @return
	 */
	
	private void writeLog(String operatType,String operatContent,User user){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(SystemMessage.getSystemFullName());
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	/**
	 * 写日志
	 * @return
	 */
	
	private void writeLog(String operatType,String operatContent,User user,String userName){
		//记录设备登记日志
		TblLog tblLog = new TblLog();
		tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		tblLog.setOperatType(operatType);
		tblLog.setOperatOject(SystemMessage.getSystemFullName());
		if(null!=user){
			tblLog.setOperator(user.getRealName());
		}else{
			tblLog.setOperator(userName);
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
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
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public UserService getUserService() {
		return UserService;
	}
	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public int getIsSystemSet() {
		return isSystemSet;
	}

	public int getIsStudyPlan() {
		return isStudyPlan;
	}

	public int getIsSchedule() {
		return isSchedule;
	}

	public int getIsContract() {
		return isContract;
	}


	public void setIsSystemSet(int isSystemSet) {
		this.isSystemSet = isSystemSet;
	}

	public void setIsStudyPlan(int isStudyPlan) {
		this.isStudyPlan = isStudyPlan;
	}

	public int getIsQA() {
		return isQA;
	}

	public void setIsQA(int isQA) {
		this.isQA = isQA;
	}

	public void setIsSchedule(int isSchedule) {
		this.isSchedule = isSchedule;
	}

	public void setIsContract(int isContract) {
		this.isContract = isContract;
	}

	public String getMyticket() {
		return myticket;
	}

	public void setMyticket(String myticket) {
		this.myticket = myticket;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(int isArchive) {
		this.isArchive = isArchive;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

	public int getIsSOP() {
		return isSOP;
	}

	public void setIsSOP(int isSOP) {
		this.isSOP = isSOP;
	}
	
	
	
}
