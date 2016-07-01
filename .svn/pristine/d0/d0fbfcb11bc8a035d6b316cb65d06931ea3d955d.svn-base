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
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblTaskTypeFieldService;
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

	
	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblTaskTypeFieldService tblTaskTypeFieldService;

	private String oldName;
	
	private String taskIds;
	
	private String ttField;//用于存入TblTaskTypeField的字段  存的是可见的taskKind
	
	/** 列表*/
	public String list() throws Exception {
		ActionContext.getContext().put("taskIds", taskIds);
		return "list";
	}
	
	/**list加载数据(json)*/
	public void loadList(){
		List<TblTaskType>  objList=tblTaskTypeService.getAll();
		List<TblTaskTypeJson>  list = new ArrayList<TblTaskTypeJson>();
		List<Integer> taskList = tblTaskTypeService.gettaskKind();
		for(Integer obj:taskList){
			TblTaskTypeJson  taskType = new TblTaskTypeJson();
			taskType.setId(obj+"");
			taskType.setTaskKind(obj);
			taskType.setTaskName(obj+"");
			taskType.setState("closed");
			taskType.setIconCls("icon-space");
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
		 Map<String,Object> map = new HashMap<String,Object>();
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
		
		ActionContext.getContext().put("taskIds", model.getId());
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(406);
		es.setSigner(realName);
		es.setEsTypeDesc("通用任务设置签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
	
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(model.getId());
	    esLink.setTblES(es);
	    esLink.setEsType(406);
        esLink.setEsTypeDesc("通用任务设置签字确认");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    
		tblESService.save(es);
	    tblESLinkService.save(esLink);
	    //保存任务名称的同时（TblTaskType），保存链接表（TblTaskTypeField）
		tblTaskTypeService.saveAllTaskType(model, fidlist);
	  
		// 日志录入
	    writeLog("签字", "通用任务设置签字确认，签字");
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
		TblTaskType  taskType =tblTaskTypeService.getById(model.getId());
		taskType.setTaskName(model.getTaskName());
		
		//根据任务的Id查询TblTaskTypeField的相应内容
		List<TblTaskTypeField> oldfidlist = tblTaskTypeFieldService.getByTaskTypeFieldId(model.getId());
		//tblTaskTypeService.update( taskType);
		
		//新的可见范围集合
		String[] ttFields=ttField.split(","); 
		List<TblTaskTypeField> fidlist = new ArrayList<TblTaskTypeField>();
	      for (int i = 0; i < ttFields.length; i++){
	    	    TblTaskTypeField field = new TblTaskTypeField();
	  		    field.setTaskKind2(Integer.valueOf(ttFields[i].trim()).intValue());
	  		    field.setTttId(model.getId());
	  		    fidlist.add(field);
	      } 
		
		//更新TblTaskType 删除旧的可见范围  保存新的可见范围
		
		tblTaskTypeService.updateTaskTypeAndTblTaskTypeFields(taskType, oldfidlist, fidlist);
		
		
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
		tblESService.save(es);
		
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(model.getId());
	    esLink.setTblES(es);
	    esLink.setEsType(408);
        esLink.setEsTypeDesc("通用任务设置修改签字");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    tblESLinkService.save(esLink);
	    // 日志录入
	    writeLog("签字", "通用任务设置修改签字，签字");
	    ActionContext.getContext().put("taskIds", model.getId());
		
		
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
		tblTaskTypeService.updateTaskType(list);
		
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
		tblESService.save(es);
		
	    esLink.setTableName("TblTaskType");
	    esLink.setDataId(model.getId());
	    esLink.setTblES(es);
	    esLink.setEsType(409);
        esLink.setEsTypeDesc("通用任务设置删除签字");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    tblESLinkService.save(esLink);
	    // 日志录入
	    writeLog("签字", "通用任务设置删除签字，签字");
		
		
		
		
		json.setSuccess(true);
		json.setMsg("删除成功");
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
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
	
}
