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
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeField;
import com.lanen.model.schedule.TblTaskTypeJson;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblTaskTypeFieldService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.schdeule.TblTaskTypeService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblTaskTypeAction extends BaseAction<TblTaskType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private TblTaskTypeService tblTaskTypeService;
	/** 用户Service*/
	@Resource
	private UserService userService;
	
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	
	@Resource
	private TblESService tblESService;
	
	
	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	

	
	
	@Resource
	private TblTaskTypeFieldService tblTaskTypeFieldService;

	private String oldName;
	
	private String taskIds;
	
	private String ttField;//用于存入TblTaskTypeField的字段  存的是可见的taskKind
	
	private String taskTypeid;//
	
	/** 列表*/
	public String list() throws Exception {
		ActionContext.getContext().put("taskIds", taskIds);
		return "list";
	}
	public void loadListForQA()
	{
		
		//TODO根据权限显示
		List<Integer> taskKind = new ArrayList<Integer>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		 //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
			taskKind.add(1);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
			taskKind.add(2);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
			taskKind.add(3);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
			taskKind.add(4);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
			taskKind.add(5);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
			taskKind.add(6);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
			taskKind.add(7);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
			taskKind.add(8);
		//}
		
		List<TblTaskType>  objList=tblTaskTypeService.getAll(taskKind);
		String json = JsonPluginsUtil.beanListToJson(objList);
		writeJson(json);
		
	}
	/**list加载数据(json)*/
	public void loadList(){
		
		List<TblTaskTypeJson>  list = new ArrayList<TblTaskTypeJson>();
		//TODO根据权限显示
		List<Integer> taskKind = new ArrayList<Integer>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		 //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
			taskKind.add(1);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
			taskKind.add(2);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
			taskKind.add(3);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
			taskKind.add(4);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
			taskKind.add(5);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
			taskKind.add(6);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
			taskKind.add(7);
		}
		if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
			taskKind.add(8);
		}
		
		List<Integer> taskList = tblTaskTypeService.gettaskKind(taskKind);
		List<TblTaskType>  objList=tblTaskTypeService.getAll(taskKind);
		for(Integer obj:taskList){
			TblTaskTypeJson  taskType = new TblTaskTypeJson();
			taskType.setId(obj+"");
			taskType.setTaskKind(obj);
			taskType.setTaskName(obj+"");
			taskType.setState("closed");
			taskType.setIconCls("icon-space");
			String canSee = "";
			// 前台显示负责人
			List<TblTaskTypeLeader>  leaderlist = tblTaskTypeLeaderService.getByTaskTypeIDList(obj+"");
			for(TblTaskTypeLeader leader:leaderlist){
				if(leader.getEndDate() == null || leader.getEndDate().equals("")|| leader.getEndDate().after(new Date())){
					if(canSee != ""){
						 canSee = canSee+",";
					 }
					String name = userService.getRealNameByUserName(leader.getTaskLeader());
					if( leader.getSignId()== null){
						canSee = canSee +"<a style='color:red;'>"+name+"</a>" ;
					}else{
						canSee = canSee +name ;
					}
					
				}
			}
			taskType.setCanSee(canSee);
			list.add(taskType);
		}
		for(TblTaskType obj:objList){
			TblTaskTypeJson  taskType = new TblTaskTypeJson();
			taskType.setId(obj.getId());
			taskType.setIconCls("icon-space");
			taskType.set_parentId(obj.getTaskKind()+"");
			taskType.setTaskName(obj.getTaskName());
			taskType.setValidFlag(obj.getValidFlag());
			List<TblTaskTypeField> seelist = tblTaskTypeFieldService.getByTaskTypeFieldId(obj.getId());
			String canSee = "";
			for(TblTaskTypeField field:seelist){
				 int kind = field.getTaskKind2();
				 if(canSee != ""){
					 canSee = canSee+",";
				 }
				 if(kind == 1){
					 canSee = canSee + "委托管理";
				 }else if(kind == 2){
					 canSee = canSee + "动物试验";
				 }else if(kind == 3){
					 canSee = canSee + "临床检验 ";
				 }else if(kind == 4){
					 canSee = canSee + "毒性病理";
				 }else if(kind == 5){
					 canSee = canSee + "QA管理";
				 }else if(kind == 6){
					 canSee = canSee + "供试品管理";
				 }else if(kind == 7){
					 canSee = canSee + "分析";
				 }else if(kind == 8){
					 canSee = canSee + "生态毒理";
				 }
			}
			taskType.setCanSee(canSee);
			list.add(taskType);
		}
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("rows",list);
		 map.put("taskIds",taskIds);
		 String json = JsonPluginsUtil.beanToJson(map);
	     writeJson(json);
	}
	
	/** 增加页面*/
	public String addUI() throws Exception {
		if(0 != model.getTaskKind()){
			ActionContext.getContext().put("theTaskKind", model.getTaskKind());
		}
		ActionContext.getContext().put("taskIds", model.getId());
		return "addUI";
	}
	
	/**异步检查标本种类是否存在*/
	public void checktaskName(){
		if(null!=model.getTaskName() && !"".equals(model.getTaskName())){
			boolean isExist = tblTaskTypeService.isExistByTaskName(model.getTaskName());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	
	}
	
	/** 增加 （保存） */
	public String add() throws Exception {
		String[] ttFields=ttField.split(","); 
		String tblTaskTypeid=tblTaskTypeService.getKey();
		List<TblTaskTypeField> fidlist = new ArrayList<TblTaskTypeField>();
	      for (int i = 0; i < ttFields.length; i++){
	    	    TblTaskTypeField field = new TblTaskTypeField();
	  		    field.setTaskKind2(Integer.valueOf(ttFields[i].trim()).intValue());
	  		    field.setTttId(tblTaskTypeid);
	  		  fidlist.add(field);
	      } 
	     
		model.setId(tblTaskTypeid);
		//tblTaskTypeService.save(model);
		//保存任务名称的同时（TblTaskType），保存链接表（TblTaskTypeField）
	
		ActionContext.getContext().put("taskIds", model.getId());
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(407);
		es.setSigner(realName);
		es.setEsTypeDesc("通用任务设置签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
	
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(model.getId());
	    esLink.setTblES(es);
	    esLink.setEsType(407);
        esLink.setEsTypeDesc("通用任务设置签字确认");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    try{
		    tblESService.save(es);
		    tblESLinkService.save(esLink);
			tblTaskTypeService.saveAllTaskType(model, fidlist);
			 // 日志录入
		    writeLog("签字", "通用任务设置签字确认，签字");
	    }catch(Exception e){
	        System.out.println("执行失败，出错种类"+e.getMessage()+".");
	   }finally{ 
	        System.out.println("执行结束");
	   } 
	  
		return "toList";
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
		tblLog.setOperatOject("通用任务设置");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}	
	
	
	
	
	
	/** 修改页面*/
	public String editUI() throws Exception {
		if(null!=model.getId() && !"".equals(model.getId())){
			TblTaskType obj  =tblTaskTypeService.getById(model.getId());
			ActionContext.getContext().getValueStack().push(obj);
			//根据任务的Id查询TblTaskTypeField的相应内容
			List<TblTaskTypeField> list = tblTaskTypeFieldService.getByTaskTypeFieldId(model.getId());
			if(null != list ){
				String taskKind = "";
				for(TblTaskTypeField field:list){
					 int kind = field.getTaskKind2();
					 if(taskKind != ""){
						 taskKind = taskKind +","+ kind;
					 }else{
						 taskKind =  kind+"";
					 }
					
				}
				ActionContext.getContext().put("ttaskKind", taskKind);
				ActionContext.getContext().put("taskTypeid", model.getId());
			}
			return "editUI";
		}else{
			return "toList";
		}
	}
	
	
	
	/**检查标本种类是否存在（自己除外）*/
	public void checkOthertaskName(){
		oldName = tblTaskTypeService.getById(model.getId()).getTaskName();
		if(null!=model.getTaskName() && !"".equals(model.getTaskName())&&null!=oldName && !"".equals(oldName)){
			if(oldName.trim().equals(model.getTaskName().trim())){
				writeJson("true");
			}else{
				boolean isExist = tblTaskTypeService.isExistByTaskName(model.getTaskName());
				if(!isExist){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}
		}else{
			writeJson("false");
		}
	}
	
	
	/** 修改*/
	public String edit() throws Exception {
		TblTaskType  taskType =tblTaskTypeService.getById(taskTypeid);
		taskType.setTaskName(model.getTaskName());
		
		//根据任务的Id查询TblTaskTypeField的相应内容
		List<TblTaskTypeField> oldfidlist = tblTaskTypeFieldService.getByTaskTypeFieldId(taskTypeid);
		//tblTaskTypeService.update( taskType);
		
		//新的可见范围集合
		String[] ttFields=ttField.split(","); 
		List<TblTaskTypeField> fidlist = new ArrayList<TblTaskTypeField>();
	      for (int i = 0; i < ttFields.length; i++){
	    	    TblTaskTypeField field = new TblTaskTypeField();
	  		    field.setTaskKind2(Integer.valueOf(ttFields[i].trim()).intValue());
	  		    field.setTttId(taskTypeid);
	  		    fidlist.add(field);
	      } 
		

		
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(408);
		es.setSigner(realName);
		es.setEsTypeDesc("通用任务设置修改签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(taskTypeid);
	    esLink.setTblES(es);
	    esLink.setEsType(408);
        esLink.setEsTypeDesc("通用任务设置修改签字");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    try{
	        tblESService.save(es);
		    tblESLinkService.save(esLink);
		    // 日志录入
		    writeLog("签字", "通用任务设置修改签字，签字");
			//更新TblTaskType 删除旧的可见范围  保存新的可见范围
			tblTaskTypeService.updateTaskTypeAndTblTaskTypeFields(taskType, oldfidlist, fidlist);
	    }catch(Exception e){
	        System.out.println("执行失败，出错种类"+e.getMessage()+".");
	   }finally{ 
	        System.out.println("执行结束");
	   } 
	    ActionContext.getContext().put("taskIds", taskTypeid);
		return "toList";
	}
	
	
	/** 删除*/
	public void delete() {
		Json json = new Json();
		List<String> taskIdslist = new ArrayList<String>();
		if(taskIds.contains(",")){
			String[] strarray = taskIds.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				taskIdslist.add(strarray[j]);
			}
		}else{
			taskIdslist.add(taskIds);
		}
		List<TblTaskType> list = new ArrayList<TblTaskType>();
		for(String taskId:taskIdslist){
			TblTaskType taskType=tblTaskTypeService.getById(taskId);
			taskType.setValidFlag("1");
			list.add(taskType);
		}
	
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(409);
		es.setSigner(realName);
		es.setEsTypeDesc("通用任务设置删除签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(model.getId());
	    esLink.setTblES(es);
	    esLink.setEsType(409);
        esLink.setEsTypeDesc("通用任务设置删除签字");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    try{
		    tblESService.save(es);
		    tblESLinkService.save(esLink);
		    // 日志录入
		    writeLog("签字", "通用任务设置删除签字，签字");
			tblTaskTypeService.updateTaskType(list);
			json.setSuccess(true);
			json.setMsg("删除成功");
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
	
	/** 增加页面*/
	public String toAddUI() throws Exception {
		    int kind = model.getTaskKind();
		    String str = "";
		    if(kind == 1){
		    	str = "委托管理" ;
			 }else if(kind == 2){
				 str = "动物试验";
			 }else if(kind == 3){
				 str = "临床检验 ";
			 }else if(kind == 4){
				 str = "毒性病理";
			 }else if(kind == 5){
				 str = "QA管理";
			 }else if(kind == 6){
				 str = "供试品管理";
			 }else if(kind == 7){
				 str = "分析";
			 }else if(kind == 8){
				 str = "生态毒理";
			 }
		    ActionContext.getContext().put("taskFullName", str);
		    ActionContext.getContext().put("taskid", kind);
			return "addUILeader";
		
	}
	
	/**加载任务总负责人下拉选*/
	public void loadTaskTypeLeader(){
		int kind = model.getTaskKind();
		List<Object> list = new ArrayList<Object>();
		
		if(kind == 1){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-委托管理");
			 list.addAll(list1);
		 }else if(kind == 2){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-动物试验");
			 list.addAll(list1);
		 }else if(kind == 3){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-临床检验");
			 list.addAll(list1);
		 }else if(kind == 4){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-毒性病理");
			 list.addAll(list1);
		 }else if(kind == 5){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-QA管理");
			 list.addAll(list1);
		 }else if(kind == 6){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-供试品管理");
			 list.addAll(list1);
		 }else if(kind == 7){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-分析");
			 list.addAll(list1);
		 }else if(kind == 8){
			 List<?> list1 = userService.findUserNameRealNameByPrivilegeName("日程管理-常规任务分配-生态毒理");
			 list.addAll(list1);
		 }
		
	//	List<TblTaskTypeLeader> tblTaskTypeList = tblTaskTypeLeaderService.getByTaskTypeIDList(kind+"");
		List<String> tlist = new ArrayList<String>();
		tlist.add(kind+"");
		List<TblTaskTypeLeader> tblTaskTypeList = tblTaskTypeLeaderService.getAllTblTaskTypeLeaderListByTaskTypeID(tlist);
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
	    String str = "";
		for(TblTaskTypeLeader obj:tblTaskTypeList){
			str = str+"#"+obj.getTaskLeader()+"#";
		}
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
    		String text = (String) objs[1];
    		String id = (String) objs[0];
        	if(tblTaskTypeList != null && tblTaskTypeList.size() > 0 ){
	        		map = new HashMap<String,String>();
               	 	map.put("id", id);
               	 	map.put("text",text);
	        		if(!str.contains(id) && !mapList.contains(map)){
	               	 	mapList.add(map);
	        		}
        	}else{
        		map = new HashMap<String,String>();
        		System.out.println(id);
           		System.out.println(text);
           	 	map.put("id", id);
           	 	map.put("text",text);
           	 	mapList.add(map);
        	}
        	
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	
	
	public TblTaskTypeService getTblTaskTypeService() {
		return tblTaskTypeService;
	}

	public void setTblTaskTypeService(TblTaskTypeService tblTaskTypeService) {
		this.tblTaskTypeService = tblTaskTypeService;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}
	
	

	public String getTtField() {
		return ttField;
	}
	public void setTtField(String ttField) {
		this.ttField = ttField;
	}

	public String getTaskTypeid() {
		return taskTypeid;
	}

	public void setTaskTypeid(String taskTypeid) {
		this.taskTypeid = taskTypeid;
	}
	
}
