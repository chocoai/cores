package com.lanen.view.action.schdeule;

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
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
// 2014-11-14 添加任务总负责人
@Controller
@Scope("prototype")
public class TblTaskTypeLeaderAction extends BaseAction<TblTaskTypeLeader>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	
	
	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	private String userNamesStr;
	
	private String esType;
	
	/**保存资源负责人（add or  edit）*/
	public void save() throws Exception {
		
		Json json = new Json();
		//2030-12-31
		
		Date startDate = model.getStartDate();
		//用户名列表
		List<String> userNameList = null;
		if(null != userNamesStr && userNamesStr.length() >= 1 ){
			userNameList = new ArrayList<String>();
			String[] userNames = userNamesStr.split(",");
			for(int j = 0;j < userNames.length;j++){
				userNameList.add(userNames[j]);
			}
		}
		
		if(model.getTaskTypeID() == null ){
			//资源id 为空
			json.setMsg("与服务器交互错误");
		}else if(startDate == null){
			json.setMsg("请选择开始日期");
		}else if(userNameList ==null || userNameList.size()<1){
			json.setMsg("请选择常规任务操作者");
		}else{
			List<TblTaskTypeLeader> tblTaskTypeList = new ArrayList<TblTaskTypeLeader>();
			TblTaskTypeLeader tblTaskLeader =null;
			for(String userName:userNameList){
				//是否，资源负责人已存在且日期区间重叠
			
				boolean isExist = tblTaskTypeLeaderService.isExist(model.getTaskTypeID(),userName,startDate,model.getEndDate());
				if(isExist){
					String reamName = userService.getRealNameByUserName(userName);
					json.setMsg(reamName+",该负责人(选择日期区间内)已存在");
					break;
				}else{
					tblTaskLeader  = new TblTaskTypeLeader();
					tblTaskLeader.setId(tblTaskTypeLeaderService.getKey());
					tblTaskLeader.setTaskLeader(userName);
					tblTaskLeader.setTaskTypeID(model.getTaskTypeID());
					tblTaskLeader.setStartDate(model.getStartDate());
					tblTaskTypeList.add(tblTaskLeader);
				}
				if(!isExist){
					tblTaskTypeLeaderService.saveAllTaskLeader(tblTaskTypeList);
					json.setMsg("添加成功");
					json.setSuccess(true);
				}
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	public void loadtaskLeader(){
		List<TblTaskTypeLeader>  list = tblTaskTypeLeaderService.getByTaskTypeIDList(model.getTaskTypeID());
		List<TblTaskTypeLeader>  list1 = new ArrayList<TblTaskTypeLeader>();
		for(TblTaskTypeLeader obj:list){
			TblTaskTypeLeader json = new TblTaskTypeLeader();
			 json.setId(obj.getId());
			 String user= userService.getRealNameByUserName(obj.getTaskLeader());
			 json.setTaskLeader(user);
			 json.setEndDate(obj.getEndDate());
			 json.setStartDate(obj.getStartDate());
			 String sign = obj.getSignId();
			 if(null != sign){				
			     TblES tblES =  tblESService.getById(sign);
			     json.setSignId(tblES.getSigner());
			 } 
			 json.setTaskLeader(user );
			 list1.add(json);
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows",list1);
		 String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		 writeJson(json);
		
	}
	
	
	public void deleteTaskTypeLeader(){
		System.out.println(model.getId());
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId())){
			TblTaskTypeLeader tblTaskLeader = tblTaskTypeLeaderService.getById(model.getId());
			if(null != tblTaskLeader){
				if(tblTaskLeader.getSignId() == null || tblTaskLeader.getSignId().equals("")){
					tblTaskTypeLeaderService.delete(model.getId());
					json.setSuccess(true);
				}else{
					//不存在
					json.setMsg("已签字，不可以删除");
				}
			}else{
				//不存在
				json.setMsg("数据交互错误");
			}
		}else{
			json.setMsg("数据交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}
	
	
	public void sign(){
	    User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		esType = "501";
		es.setEsType(Integer.parseInt(esType));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("任务总负责人添加签字");
		String esId = tblESService.getKey("TblES");
		es.setDateTime(new Date());
		es.setEsId(esId);
	
      
		TblTaskTypeLeader  leader= tblTaskTypeLeaderService.getById(model.getId());
		leader.setSignId(esId);
	
		
        esLink.setTableName("TblTaskTypeLeader");
    	esLink.setDataId(model.getId());
    	esLink.setTblES(es);
    	esLink.setEsType(Integer.parseInt(esType));
    	esLink.setEsTypeDesc("任务总负责人添加签字");
    	esLink.setRecordTime(new Date());
    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    	Json json = new Json();
    	try{
	    	tblESService.save(es);
	    	tblESLinkService.save(esLink);
	    	// 日志录入
	    	writeLog("签字", "任务总负责人添加签字，签字");
	    	tblTaskTypeLeaderService.update(leader);
	    	json.setMsg("签字成功");
			json.setSuccess(true);
    	}catch(Exception e){
    	     json.setSuccess(false);
    	     json.setMsg("与数据库交互异常");
    	     System.out.println("执行失败，出错种类"+e.getMessage()+".");
    	}finally{ 
    	     System.out.println("执行结束");
    	} 
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
   }

	
	//设置无效，读取ID和结束时间
	public void setupInvalid(){
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		esType = "502";
		es.setEsType(Integer.parseInt(esType));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("任务总负责人设置无效签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
	
		
		TblTaskTypeLeader  leader= tblTaskTypeLeaderService.getById(model.getId());
		leader.setEndDate(model.getEndDate());
		leader.setEndDateSignId(esId);
		
 
        esLink.setTableName("TblTaskLeader");
    	esLink.setDataId(model.getId());
    	esLink.setTblES(es);
    	esLink.setEsType(Integer.parseInt(esType));
    	esLink.setEsTypeDesc("任务总负责人设置无效签字");
    	esLink.setRecordTime(new Date());
    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    	    tblESService.save(es);
    		tblESLinkService.save(esLink);
    		// 日志录入
    		writeLog("签字", "任务总负责人设置无效签字，签字");
    		tblTaskTypeLeaderService.update(leader);

		json.setMsg("签字成功");
		json.setSuccess(true);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	/**
	 * 写日志
	 * 
	 * @return
	 */
	private void writeLog(String operatType, String operatContent) {
		// 记录设备登记日志
		TblLog tblLog = new TblLog();
		tblLog.setSystemName(SystemMessage.getSystemName());// 系统名称
		tblLog.setSystemVersion(SystemMessage.getSystemVersion());// 系统版本
		tblLog.setOperatType(operatType);
		tblLog.setOperatOject("任务总负责人");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}	
	
	public String getUserNamesStr() {
		return userNamesStr;
	}

	public void setUserNamesStr(String userNamesStr) {
		this.userNamesStr = userNamesStr;
	}

	public String getEsType() {
		return esType;
	}

	public void setEsType(String esType) {
		this.esType = esType;
	}
	
	
	

}
