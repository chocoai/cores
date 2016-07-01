package com.lanen.view.action;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.studyplan.TblWeighInd;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.studyplan.TblWeightIndService;
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
	// 
	private String ticket;
	// 课题编号
	private String studyNoPara;
	/** 动物体重Service*/
	@Resource
	private TblWeightIndService tblWeightIndService;
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	
	private String username;
	
	/** 用户Service*/
	@Resource
	private UserService UserService;
	
	/**session验证是否过期*/
	public void isvail() throws Exception {
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("");
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/** 登录*/
	public String login() throws Exception {
		System.out.println("专题管理登录验证开始"+ticket);
		if(null !=ticket && ticket.length()>32){
			String password = ticket.substring(0, 32);
//			String dateStr = DateUtil.getNow("yyyy-MM-dd")+SystemTool.getWindowsMACAddress();
			String userName = CryptUtils.decryptString(ticket.substring(32));
			User user = userService.getByUserName(userName);
			
			if(null != user){
				System.out.println(userName+userName);
				if(password.equals(user.getPassword())){
					boolean ishas = userService.checkPrivilege(user, "SD登录");
					if(ishas){
						ActionContext.getContext().getSession().put("user", user);
						
						//权限路径列表
						List<String> privilegeUrlList = userService.findPrivilegeUrlListByUserId(user.getId());
						ActionContext.getContext().getSession().put("myPrivilegeUrlList", privilegeUrlList);
						
						System.out.println("专题管理登录验证成功"+ticket);
						//写日志
						writeLog("登录系统","",user);
						return "toIndex";
					}
				}
			}
		}
		System.out.println("专题管理登录验证失败"+ticket);
		return "toLogout";
	}
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
				User user =(User) ActionContext.getContext().getSession().get("user");
				int flag =0;
				if(null==user){
					flag=1;//过期密码修改
					user =(User) ActionContext.getContext().getSession().get("user2");
				}
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
	
	/** 密码验证（json）*/
	public void passwordCheck(){
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		if(null==tempUser){
			tempUser = (User) ActionContext.getContext().getSession().get("user2");
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
	
	/** 复核密码验证（json）*/
	public void fupasswordCheck(){
		List<TblWeighInd> list  =  tblWeightIndService.gettblWeighInd(studyNoPara);
		int index = list.size();
		TblWeighInd tblWeighInd = list.get(index-1);
		//获取tblWeighInd主键作为签名链接的数据ID
		TblESLink tblESLink1= tblESLinkService.getByEntityNameAndDataIdType("TbLWeightData"+tblWeighInd.getWeighSn(), studyNoPara,8);
		String name = tblESLink1.getTblES().getSigner();//签字人
	
		String realName = model.getUserName();
		 User tempUser = userService.getByUserName(realName);
		 Json json= new Json();
		 if(null != tempUser){
		 boolean flag = true;
		 if(tempUser.getFlag().equals("停用")){
				 flag = false;
		 }
	    User qianziUser = userService.getByRealName(name);
		if(qianziUser.getRealName().equals(tempUser.getRealName())){
			json.setMsg("请重新选择复核人员");
		}else{if( flag &&null!=tempUser && null!=model.getPassword() &&!"".equals(model.getPassword())){
			if(tempUser.getPassword().equals(DigestUtils.md5Hex(model.getPassword()))){
				json.setSuccess(true);
				json.setMsg("密码验证通过");
				json.setObj(tempUser.getRealName());
			}else{
				json.setMsg("密码错误");
			}
		}else if( !flag){
			json.setMsg("此用户名已停用");
		}else{
			json.setMsg("用户名错误");
		}
			
		}
		}else{
			json.setMsg("用户名错误");
		}
		String jsonStr=JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
//	public void QueryTheApplicant(){
//		List<TblWeighInd> list  =  tblWeightIndService.gettblWeighInd(studyNoPara);
//		int index = list.size();
//		TblWeighInd tblWeighInd = list.get(index-1);
//		//获取tblWeighInd主键作为签名链接的数据ID
//		TblESLink tblESLink1= tblESLinkService.getByEntityNameAndDataIdType("TbLWeightData"+tblWeighInd.getWeighSn(), studyNoPara,8);
//		String name = tblESLink1.getTblES().getSigner();//签字人
//		User tempUser = (User) ActionContext.getContext().getSession().get("user");
//		if(null==tempUser){
//			tempUser = (User) ActionContext.getContext().getSession().get("user2");
//		}
//		String tempUsername = tempUser.getRealName();
//		
//		User qianziUser = userService.getByRealName(name);
//		String qianziUserName = qianziUser.getUserName();
//		Map map = new HashMap();
//		map.put("qianziname",qianziUserName);
//		map.put("tempUsername",tempUsername);
//		map.put("TheFHUserName",name);
//		String json = JsonPluginsUtil.beanToJson(map);
//		writeJson(json);
//	}
	
	
	/**权限验证（包括密码）(json)*/
//	public void checkPrivilege(){
//		Map<String,Object> map = userService.checkPrivilege(model.getUserName(), model.getPassword(),"SD登录","系统设置登录");
//		//判断用户是否为空
//		if("".equals(map.get("nullUserError"))){
//			//用户非空
//			User user =(User) map.get("user");
//			if("".equals(map.get("forbidden"))){
//				//账户未停用
//				if("".equals(map.get("entitle"))){//用户有权限
//					//用户密码未过期    或 已过期
//					if("".equals(map.get("overdue"))){
//						//未过期
//						//保存登录用户
//						ActionContext.getContext().getSession().put("user", user);
//					}else{
//						//密码已过期
//						ActionContext.getContext().getSession().put("user2", user);
//						//写日志
//						writeLog("登录","失败:密码已过期",user);
//					}
//				}else{
//					//写日志
//					writeLog("登录","失败:未授权",user);
//				}
//			}else{
//				//写日志
//				writeLog("登录","失败:账号已停用",user);
//			}
//		}else{
//			//写日志
//			writeLog("登录","失败:用户名或密码错误",null,model.getUserName());
//		}
//		map.remove("user");
////		map = new HashMap<String,Object>();
////		map.put("success", true);
//		String json = JsonPluginsUtil.beanToJson(map);
//		writeJson(json);
//	}
	
	
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
//	/**
//	 * 写日志
//	 * @return
//	 */
//	
//	private void writeLog(String operatType,String operatContent,User user,String userName){
//		//记录设备登记日志
//		TblLog tblLog = new TblLog();
//		tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
//		tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
//		tblLog.setOperatType(operatType);
//		tblLog.setOperatOject(SystemMessage.getSystemFullName());
//		if(null!=user){
//			tblLog.setOperator(user.getRealName());
//		}else{
//			tblLog.setOperator(userName);
//		}
//		tblLog.setOperatContent(operatContent);
//		tblLog.setOperatHost(SystemTool.getIPAddress(request));
//		tblLogService.save(tblLog);
//	}
	

	
	
	
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
	public String getStudyNoPara() {
		return studyNoPara;
	}
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}
	public TblWeightIndService getTblWeightIndService() {
		return tblWeightIndService;
	}
	public void setTblWeightIndService(TblWeightIndService tblWeightIndService) {
		this.tblWeightIndService = tblWeightIndService;
	}
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserService getUserService() {
		return UserService;
	}
	public void setUserService(UserService userService) {
		UserService = userService;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
	
}
