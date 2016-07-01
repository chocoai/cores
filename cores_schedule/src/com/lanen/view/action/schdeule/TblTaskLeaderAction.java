package com.lanen.view.action.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblTOLeader;
import com.lanen.model.schedule.TblTaskLeader;
import com.lanen.model.schedule.TblTaskLeaderJson;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblTOLeaderService;
import com.lanen.service.schdeule.TblTaskLeaderService;
import com.lanen.service.schdeule.TblTaskTypeService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblTaskLeaderAction extends BaseAction<TblTaskLeader>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TblTaskTypeService tblTaskTypeService;
	@Resource
	private TblTaskLeaderService tblTaskLeaderService;
	/** 用户Service*/
	@Resource
	private UserService userService;
	
	@Resource
	private TblESService tblESService;
	@Resource
	private TblTOLeaderService tblTOLeaderService;
	
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	
	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	
	private String taskKind;
	
	private String resMans;
	
	private String[] getUserIds;
	
	private String esType;
	
	private String tlid;
	
	private String userNamesStr;
	
	private String taskNames;
	
	private String ids;
	
	
	/** 列表*/
	public String list() throws Exception {
		ActionContext.getContext().put("tlid", model.getId());
		return "list";
	}

	public void loadList(){
		User user1 =(User) ActionContext.getContext().getSession().get("user");
		 List<TblTaskLeaderJson> alllist = new ArrayList<TblTaskLeaderJson>();
			List<TblTaskLeaderJson> treelist = new ArrayList<TblTaskLeaderJson>();
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("1");
			 TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(1+"");		
			 json.setTaskKind("委托管理");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			 alllist.addAll(list);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("2");
			 TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(2+"");		
			 json.setTaskKind("动物试验");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			alllist.addAll(list);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("3");
			 TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(3+"");		
			 json.setTaskKind("临床检验");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			alllist.addAll(list);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("4");
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(4+"");		
			 json.setTaskKind("毒性病理");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			alllist.addAll(list);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("5");
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(5+"");		
			 json.setTaskKind("QA管理");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			alllist.addAll(list);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("6");
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(6+"");		
			 json.setTaskKind("供试品管理");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json); 
			alllist.addAll(list);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("7");
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(7+"");		
			 json.setTaskKind("分析");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json);
			alllist.addAll(list);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")){
			List<TblTaskLeaderJson> list = tblTaskLeaderService.getByPrivilege("8");
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(8+"");		
			 json.setTaskKind("生态毒理");
			 json.setIconCls("icon-space");
			 json.setState("closed");
			 treelist.add(json);
			alllist.addAll(list);
		}
		String taskNames = "";
		for(TblTaskLeaderJson  obj:alllist){
			 String taskName = obj.getTaskKind();
			 if(!taskNames.contains('/'+taskName+"/")){
				 TblTaskLeaderJson json = new TblTaskLeaderJson();
				 json.setId(obj.getTtid());	
				 json.set_parentId(obj.get_parentId());
				 json.setTaskKind(obj.getTaskKind());
				 json.setIconCls("icon-space");
				 String userName = "";
				 String canSee = "";
				 String canSee1 ="";
				 for(TblTaskLeaderJson  obj1:alllist){
					 String taskind =obj1.getCanSee(); 
					 if((obj1.getTaskKind().equals(obj.getTaskKind()) &&(!canSee1.contains(taskind))) ){
						 if(canSee != ""){
							 canSee = canSee+",";
						 }
						 if(taskind.equals("1")){
							 canSee = canSee + "委托管理";
						 }else if(taskind.equals("2")){
							 canSee = canSee + "动物试验";
						 }else if(taskind.equals("3")){
							 canSee = canSee + "临床检验 ";
						 }else if(taskind.equals("4")){
							 canSee = canSee + "毒性病理";
						 }else if(taskind.equals("5")){
							 canSee = canSee + "QA管理";
						 }else if(taskind.equals("6")){
							 canSee = canSee + "供试品管理";
						 }else if(taskind.equals("7")){
							 canSee = canSee + "分析";
						 }else if(taskind.equals("8")){
							 canSee = canSee + "生态毒理";
						 }
						 canSee1 =canSee1+taskind ;
					 }
					 
			     }
				 for(TblTaskLeaderJson  obj1:alllist){
					 if( obj1.getId() != null ){
						 //(obj1.getTaskKind().equals(obj.getTaskKind()) )&& (!(userName.contains(obj1.getTaskLeader()))) &&  ( (obj1.getTaskLeader() != null )||(!obj1.getTaskLeader().equals("")) )
					 if((obj1.getTaskKind().equals(obj.getTaskKind()) )&& (!(userName.contains(obj1.getTaskLeader()))) &&  ( (obj1.getTaskLeader() != null )||(!obj1.getTaskLeader().equals("")) )){
					    String name = obj1.getTaskLeader();
					    if(userName != ""){
						 userName = userName+",";
					    }
					    if(obj1.getSignId() == null || obj1.equals("")){
					    	  userName = userName + "<a style='color:red;'>"+name+"<a>";
					    }else{
					    	  userName = userName + name;
					    }
					  }
					 }
					
				 }
				 json.setCanSee(canSee);
				 json.setTaskLeader(userName);
				 treelist.add(json);
			 }
			 taskNames = taskNames+'/'+taskName+"/,";
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows",treelist);
		 map.put("tlid",tlid);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
}
		

	
	/** 增加页面*/
	public String addUI() throws Exception {
		    String id = model.getId();
		    TblTaskType taskType = tblTaskTypeService.getById(id);
		    String taskName = taskType.getTaskName();
		    int kind = taskType.getTaskKind();
		    String str = "";
		    if(kind == 1){
		    	str = "委托管理: "+taskName;
			 }else if(kind == 2){
				 str = "动物试验: "+taskName;
			 }else if(kind == 3){
				 str = "临床检验: "+taskName;
			 }else if(kind == 4){
				 str = "毒性病理: "+taskName;
			 }else if(kind == 5){
				 str = "QA管理: "+taskName;
			 }else if(kind == 6){
				 str = "供试品管理: "+taskName;
			 }else if(kind == 7){
				 str = "分析: "+taskName;
			 }else if(kind == 8){
				 str = "生态毒理: "+taskName;
			 }
		    ActionContext.getContext().put("taskFullName", str);
		    ActionContext.getContext().put("taskid", id);
			return "addUI";
		
	}

	
	
	public void combobox(){
		 List<TblTaskType>   list= tblTaskTypeService.getTaskTypeName(Integer.parseInt(taskKind));
		 if(null != list && list.size() > 0){
			 List<Map<String, String>> serialNumList = new ArrayList<Map<String,String>>();
			 Map<String, String> map = null;
			 for(int i = 0 ;i< list.size();i++){
				 map = new HashMap<String,String>();
				 map.put("id",list.get(i).getId());
				 map.put("text",list.get(i).getTaskName());
				 serialNumList.add(map);
			 }
				Map<String,Object> jsonMap = new HashMap<String,Object> ();
				jsonMap.put("serialNumList", serialNumList);
				String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
				writeJson(jsonStr);
		}
	}
	
	public void sameUserAndTime(){
		String[] array = null;
		if(resMans.contains(",")){
			array = resMans.split(",");
		}
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		boolean falg = true;
		if(null == array){
			List<Date> list = tblTaskLeaderService.selectALLEndDate(model.getTaskTypeID(), resMans);
			   if(list.size()>0){
				   for(Date time:list){
					   if( (null == time) || (time.after(model.getStartDate()))){
						   falg = false;
						   String username = userService.getRealNameByUserName(resMans);
						   jsonMap.put("msg", username);
						   break;
						}
				   }
			   }
			  
	    }else{
		   for(int i=0;i<array.length;i++){
			   List<Date> list = tblTaskLeaderService.selectALLEndDate(model.getTaskTypeID(), array[i]);
			   if(list.size()>0){
			   for(Date time:list){
				   if( (null ==time)|| (time.after(model.getStartDate())) ){
					   falg = false;
					   String username = userService.getRealNameByUserName(array[i]);
					   jsonMap.put("msg", username);
					   break;
					 }
				   }
			   }
			   if(!falg){
				   break;
			   }
		   }
		}
		if(falg){
			jsonMap.put("success", true);
	    }else{
		  jsonMap.put("success", false);
	    }
    	String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
	    writeJson(jsonStr);
		
	}
	
	
	/**保存资源负责人（add or  edit）*/
	public void save() throws Exception {
		Json json = new Json();
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
		List<String> ids = new ArrayList<String>();
		if(model.getTaskTypeID() == null ){
			//资源id 为空
			json.setMsg("与服务器交互错误");
		}else if(startDate == null){
			json.setMsg("请选择开始日期");
		}else if(userNameList ==null || userNameList.size()<1){
			json.setMsg("请选择常规任务操作者");
		}else{
			List<TblTaskLeader> tblTaskTypeList = new ArrayList<TblTaskLeader>();
			TblTaskLeader tblTaskLeader =null;
			
			for(String userName:userNameList){
				//是否，资源负责人已存在且日期区间重叠
			
				boolean isExist = tblTaskLeaderService.isExist(model.getTaskTypeID(),userName,startDate,model.getEndDate());
				if(isExist){
					String reamName = userService.getRealNameByUserName(userName);
					json.setMsg(reamName+",该负责人(选择日期区间内)已存在");
					break;
				}else{
					tblTaskLeader  = new TblTaskLeader();
					String id = tblTaskLeaderService.getKey();
					tblTaskLeader.setId(id);
					tblTaskLeader.setTaskLeader(userName);
					tblTaskLeader.setTaskTypeID(model.getTaskTypeID());
					tblTaskLeader.setStartDate(model.getStartDate());
					tblTaskTypeList.add(tblTaskLeader);
					ids.add(id);
				}
				if(!isExist){
					tblTaskLeaderService.saveAllTaskLeader(tblTaskTypeList);
					json.setMsg("添加成功");
					json.setSuccess(true);
				}
			}
		}
		json.setObj(ids);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**签字*/
	public void sign(){
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			// 验证通过则进行一下操作
			esType = "412";
			es.setEsType(Integer.parseInt(esType));
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("常规责任负责人添加签字");
			String esId = tblESService.getKey("TblES");
			es.setEsId(esId);
	        TblTaskLeader  leader= tblTaskLeaderService.getById(model.getId());
			leader.setSignId(esId);
	        esLink.setTableName("TblTaskLeader");
	    	esLink.setDataId(model.getId());
	    	esLink.setTblES(es);
	    	esLink.setEsType(Integer.parseInt(esType));
	    	esLink.setEsTypeDesc("常规责任负责人添加签字");
	    	esLink.setRecordTime(new Date());
	    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    	Json json = new Json();
	    	
	    	try{
	    	  tblESService.save(es);
	    	  tblESLinkService.save(esLink);
	    	  tblTaskLeaderService.update(leader);
	    	  // 日志录入
	    	  writeLog("签字", "常规责任负责人添加签字，签字");
	  		  json.setSuccess(true);
	  	      json.setMsg("签字成功");
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
	
	public void signAll(){
		List<String> singIdList = new ArrayList<String>();
		if(ids.contains(",")){
			String[] strarray = ids.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				singIdList.add(strarray[j]);
			}
		}else{
			singIdList.add(ids);
		}
		
		   User tempUser = (User) ActionContext.getContext().getSession().get("user");
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			// 验证通过则进行一下操作
			esType = "412";
			List<TblTaskLeader> list = new ArrayList<TblTaskLeader>();
	    	Json json = new Json();
	    	 try{
				for (int i = 0; i < singIdList.size(); i++) {
					String id = singIdList.get(i);
					es.setEsType(Integer.parseInt(esType));
					es.setSigner(tempUser.getRealName());
					es.setEsTypeDesc("常规责任负责人添加签字");
					String esId = tblESService.getKey("TblES");
					es.setEsId(esId);
					tblESService.save(es);
			      
			        TblTaskLeader  leader= tblTaskLeaderService.getById(id);
					leader.setSignId(esId);
					//tblTaskLeaderService.update(leader);
					list.add(leader);
			        esLink.setTableName("TblTaskLeader");
			    	esLink.setDataId(id);
			    	esLink.setTblES(es);
			    	esLink.setEsType(Integer.parseInt(esType));
			    	esLink.setEsTypeDesc("常规责任负责人添加签字");
			    	esLink.setRecordTime(new Date());
			    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			    	tblESLinkService.save(esLink);
			    	// 日志录入
			    	writeLog("签字", "常规责任负责人添加签字，签字");
				}
				tblTaskLeaderService.updateAllTaskLeader(list);
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
		esType = "413";
		es.setEsType(Integer.parseInt(esType));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("常规责任负责人设置无效签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		TblTaskLeader  leader= tblTaskLeaderService.getById(model.getId());
		leader.setEndDate(model.getEndDate());
		leader.setEndDateSignId(esId);
 
        esLink.setTableName("TblTaskLeader");
    	esLink.setDataId(model.getId());
    	esLink.setTblES(es);
    	esLink.setEsType(Integer.parseInt(esType));
    	esLink.setEsTypeDesc("常规责任负责人设置无效签字");
    	esLink.setRecordTime(new Date());
    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    	
    	try{
    	  tblESService.save(es);
    	  tblESLinkService.save(esLink);
    	  tblTaskLeaderService.update(leader);
    	  // 日志录入
    	  writeLog("签字", "常规责任负责人设置无效签字，签字");
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
	
	public void delete(){
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId())){
			TblTaskLeader tblTaskLeader = tblTaskLeaderService.getById(model.getId());
			if(null != tblTaskLeader){
				if(tblTaskLeader.getSignId() == null || tblTaskLeader.getSignId().equals("")){
					tblTaskLeaderService.delete(model.getId());
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
	
	public void loadtaskLeader(){
		List<TblTaskLeaderJson> listall = new ArrayList<TblTaskLeaderJson>();
		List<TblTaskLeader> list = tblTaskLeaderService.getbyTaskid(model.getId());
		for(TblTaskLeader obj:list){
			 TblTaskLeaderJson json = new TblTaskLeaderJson();
			 json.setId(obj.getId());
			 String endTimeAndName = "";
			 json.setStartTime(DateUtil.dateToString(obj.getStartDate(), "yyyy-MM-dd"));
			 if(null != obj.getEndDate()){
				 String endTime=DateUtil.dateToString(obj.getEndDate(), "yyyy-MM-dd"); 
				 String esignid = obj.getEndDateSignId();
				 if(null != esignid){
				     TblES tblES =  tblESService.getById(esignid);
					 endTimeAndName = endTime +"("+tblES.getSigner()+")";
				 }else{
					 endTimeAndName = endTime;
				 }
				
			 }
			 json.setEndTime(endTimeAndName);
			 String user= userService.getRealNameByUserName(obj.getTaskLeader());
			 String sign = obj.getSignId();
			 if(null != sign){				
			     TblES tblES =  tblESService.getById(sign);
			     json.setSignId(tblES.getSigner());
			 } 
			  
			  
			 json.setTaskLeader(user );
			 listall.add(json);
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows",listall);
		 map.put("tlid",tlid);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	public void loadComboboxData(){
		String taskTypeID = model.getTaskTypeID();
		int taskKind = tblTaskTypeService.getById(taskTypeID).getTaskKind();
		User user1 =(User) ActionContext.getContext().getSession().get("user");
		List<Object> list = new ArrayList<Object>();
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理")&&taskKind==1){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-委托管理");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")&&taskKind==2){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-动物试验");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")&&taskKind==3){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-临床检验");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")&&taskKind==4){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-毒性病理");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")&&taskKind==5){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-QA管理");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")&&taskKind==6){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-供试品管理");
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")&&taskKind==7){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-分析");
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")&&taskKind==8){
			List<?> list1 = tblTaskLeaderService.findUserNameRealNameByResIdPrivilegeName(taskTypeID,"日程管理-列表-生态毒理");
			list.addAll(list1);
		}

		
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id);
       	 	map.put("text",text);
       	 	mapList.add(map);
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
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
		tblLog.setOperatOject("常规责任负责人");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}	
	
	//批量选择
	public void selectBatachSetloadList(){
		String[] strarray = taskNames.split(",");
		Set<String> list = new HashSet<String>();
		for (int j = 0; j < strarray.length; j++) {
			list.add(strarray[j].trim());
		}
		List<TblTaskLeaderJson>  list1 = tblTaskLeaderService.getByTaskNameTaskLeader(list);
		List<TblTaskLeaderJson>  joinlist = new ArrayList<TblTaskLeaderJson>();
		String taskNames = "";
		int i = 1;
		for(TblTaskLeaderJson json:list1){
			if((!(taskNames).contains("#"+json.getTaskName()+"#") )|| taskNames.equals("")){
				TblTaskLeaderJson json1 = new TblTaskLeaderJson();
				json1.setId(i+"");
				json1.setTaskName(json.getTaskName());
				json1.setIconCls("icon-space");
				//json1.setTaskKind(json.getTaskKind());
				joinlist.add(json1);
				taskNames =taskNames+"#"+json.getTaskName()+"#";
				for(TblTaskLeaderJson json2:list1){
				  if(json2.getTaskName().equals(json.getTaskName())){
					  TblTaskLeaderJson json3 = new TblTaskLeaderJson();
					  json3.setId(json2.getId());
					  json3.setTaskName(userService.getRealNameByUserName(json2.getTaskLeader()));
					  String tasks = "";
					  List<TblTOLeader> tList = tblTOLeaderService.getByDateAndLeader(model.getStartDate(), model.getEndDate(), json2.getTaskLeader());
					  for(TblTOLeader leader:tList){
						  if(!tasks.equals("")){
							  tasks = tasks+",";
						  }
						  TblSchedulePlan tblSchedulePlan = tblSchedulePlanService.getById(leader.getScheduleId());
						  tasks = tasks + tblSchedulePlan.getTaskCode()+" ( "+tblSchedulePlan.getTaskName()+" ) ";
					  }
					  
					  json3.setTasks(tasks);
					  json3.setTaskLeaderId(json2.getTaskLeader());
					  json3.set_parentId(i+"");
					  json3.setIconCls("icon-space");
					  joinlist.add(json3);
				  }	
				}
			}
			i++;
		}
		String noTaskLeader  = tblTaskLeaderService.getNoTaskLeaderByTaskName(list);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows",joinlist);
		 map.put("noTaskLeader", noTaskLeader);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}

	
	public String getTaskNames() {
		return taskNames;
	}

	public void setTaskNames(String taskNames) {
		this.taskNames = taskNames;
	}

	public String getTaskKind() {
		return taskKind;
	}

	public void setTaskKind(String taskKind) {
		this.taskKind = taskKind;
	}

	public String getResMans() {
		return resMans;
	}

	public void setResMans(String resMans) {
		this.resMans = resMans;
	}

	public String[] getGetUserIds() {
		return getUserIds;
	}

	public void setGetUserIds(String[] getUserIds) {
		this.getUserIds = getUserIds;
	}

	public String getTlid() {
		return tlid;
	}

	public void setTlid(String tlid) {
		this.tlid = tlid;
	}

	public String getUserNamesStr() {
		return userNamesStr;
	}

	public void setUserNamesStr(String userNamesStr) {
		this.userNamesStr = userNamesStr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	
	
	
}
