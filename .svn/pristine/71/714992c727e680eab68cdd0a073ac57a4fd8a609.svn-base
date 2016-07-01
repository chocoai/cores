package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QALearnTask;
import com.lanen.model.qa.QALearnTaskFile;
import com.lanen.model.qa.QALearnTaskFileReadRecord;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.qa.QAFileRegReaderService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QALearnTaskFileReadRecordService;
import com.lanen.service.qa.QALearnTaskFileService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QALearnTaskAction extends BaseAction<QALearnTask>{

	private static final long serialVersionUID = -2402721910937259220L;
	@Resource
	private QALearnTaskService qALearnTaskService;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private UserService userService;
	@Resource
	private QAFileRegReaderService qAFileRegReaderService;
	@Resource
	private QALearnTaskFileService qALearnTaskFileService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private QALearnTaskFileReadRecordService qALearnTaskFileReadRecordService;
	
	private String fileRegIds;
	private String planFinishTime;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public void save()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		QALearnTask task = new QALearnTask();
		String key = qALearnTaskService.getKey("QALearnTask");
		task.setLearnTaskId(key);
		task.setCreateTime(DateUtil.getTodayDate());
		task.setLearnState(0);//未提交
		task.setPurpose(model.getPurpose());
		task.setStudent(model.getStudent());
		qALearnTaskService.save(task);
		//文件任务关系表
		String[] fileRegs = fileRegIds.split(",");
		List<QAFileReg> fileRegList = qAFileRegService.getByIds(fileRegs);
		for(QAFileReg fileReg:fileRegList)
		{
			QALearnTaskFile taskFile = new QALearnTaskFile();
			String taskFileKey = qALearnTaskFileService.getKey("QALearnTaskFile");
			taskFile.setLearnTaskFileId(taskFileKey);
			taskFile.setFileRegId(fileReg.getFileRegId());
			taskFile.setQalearnTask(task);
			
			taskFile.setFileCode(fileReg.getFileCode());
			taskFile.setFileName(fileReg.getFileName());
			taskFile.setFilePublishDepartment(fileReg.getFilePublishDepartment());
			taskFile.setFilePublishTime(fileReg.getFilePublishTime());
			taskFile.setFileType(fileReg.getFileType());
			taskFile.setFileTypeId(fileReg.getQafileType().getFileTypeId());
			taskFile.setFileTypeName(fileReg.getFileTypeName());
			taskFile.setFileVersion(fileReg.getFileVersion());
			taskFile.setIsVersionUpdate(fileReg.getIsVersionUpdate());
			taskFile.setRemark(fileReg.getRemark());
			
			
			qALearnTaskFileService.save(taskFile);
		}

		List<User> users = userService.findByPrivilegeName2(task.getStudent());
		for(User user:users)
		{
			QAFileRegReader reader = new QAFileRegReader();
			reader.setCreateTime(DateUtil.getTodayDate());
			String recordKey = qAFileRegReaderService.getKey("QAFileRegReader");
			reader.setLearnRecordId(recordKey);
			reader.setLearnState(0);
			reader.setPlanFinishTime(DateUtil.stringToDate(planFinishTime,"yyyy-MM-dd"));
			reader.setQalearnTask(task);
			reader.setReaderCode(user.getUserName());
			reader.setReaderName(user.getRealName());
			qAFileRegReaderService.save(reader);
			
			
		}
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void saveOneTask()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		QALearnTask task = new QALearnTask();
		String key = qALearnTaskService.getKey("QALearnTask");
		task.setLearnTaskId(key);
		task.setCreateTime(DateUtil.getTodayDate());
		task.setLearnState(0);//未提交
		task.setPurpose(model.getPurpose());
		task.setStudent(model.getStudent());
		qALearnTaskService.save(task);
		
		map.put("learnTaskId", key);
		map.put("createTime", DateUtil.dateToString(task.getCreateTime(),"yyyy-MM-dd"));
		map.put("purpose", task.getPurpose());
		map.put("student", task.getStudent());
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void delOneTask()
	{
		List<QALearnTaskFile> files = qALearnTaskFileService.getFileListByTaskId(model.getLearnTaskId());
		for(QALearnTaskFile learnFile:files)
		{
			qALearnTaskFileService.delete(learnFile.getLearnTaskFileId());
		}
		List<QAFileRegReader> readers = qAFileRegReaderService.getByTask(model.getLearnTaskId());
		for(QAFileRegReader reader:readers)
		{
			qAFileRegReaderService.delete(reader.getLearnRecordId());
		}
		List<QALearnTaskFileReadRecord> readRecords = qALearnTaskFileReadRecordService.getByTask(model.getLearnTaskId());
		for(QALearnTaskFileReadRecord readRecord:readRecords)
		{
			qALearnTaskFileReadRecordService.delete(readRecord.getFileReadRecordId());
		}
		qALearnTaskService.delete(model.getLearnTaskId());
		writeES("删除学习任务", 827,"QALearnTask" , model.getLearnTaskId());
		writeLog("删除学习任务","QAM删除一个学习任务","QAM删除一个学习任务");
	}
	public void canCommit()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//num,fileName
		List<Map<String, Object>> fileNameAttNum = qALearnTaskService.isExistAttByTask(model.getLearnTaskId());
		boolean isExistReader = qALearnTaskService.isExistReaderByTask(model.getLearnTaskId());
		boolean isExistAttInTask = true;
		String noAttFileNames="";
		if(fileNameAttNum!=null){
			for(Map<String, Object> tempMap:fileNameAttNum)
			{
				if((Integer)tempMap.get("num")==0)
				{
					isExistAttInTask = false;
					if(!"".equals(noAttFileNames))
						noAttFileNames+=",";
					noAttFileNames+=tempMap.get("fileName");
				}
			}
		}else{
			isExistAttInTask = false;
		}
		
		if(!isExistAttInTask)
		{
			map.put("success", false);
			map.put("noAttFileNames", noAttFileNames);
			map.put("msg", "该任务中下列文件不包含需学习的附件：");
		}//else if(readers==null||readers.size()==0){
		else if(!isExistReader){
			map.put("success", false);
			map.put("msg", "该任务中不存在学习者！");
		}else{
			map.put("success", true);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void commit()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QALearnTask task = qALearnTaskService.getById(model.getLearnTaskId());
			
		task.setLearnState(1);
		qALearnTaskService.update(task);
			
			String fileNameString="文件名称：";
			List<QALearnTaskFile> files = qALearnTaskFileService.getFileListByTaskId(task.getLearnTaskId());
			for(QALearnTaskFile file:files)
			{
				String fileName=qAFileRegService.getById(file.getFileRegId()).getFileName();
				fileNameString+=fileName+"  ";
			}
			String receiverList = "";
			List<QAFileRegReader> readers =  qAFileRegReaderService.getByTask(task.getLearnTaskId());
			for(QAFileRegReader reader:readers)
			{
				reader.setLearnState(1);//学习中
				qAFileRegReaderService.update(reader);
				receiverList+=(reader.getReaderCode())+",";
			
			}
			
			String msgContent=("你有文件("+fileNameString+")需要学习,特此提醒");
			String msgTitle=("学习文件提醒");
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
			//签名写入
			writeES("提交学习任务签字确认",823,"QALearnTask",task.getLearnTaskId());
			
			//日志录入
			writeLog("提交学习任务","学习任务提交","提交学习任务！");
			
			
			map.put("success", true);
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void cancelTask()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QALearnTask task = qALearnTaskService.getById(model.getLearnTaskId());
		if(task.getLearnState()!=null&&task.getLearnState()==2)
		{
			map.put("success", false);
			map.put("msg", "该任务已经结束！");
		}else{
			
			if(task.getLearnState()!=null&&task.getLearnState()==1){
				writeES("撤销学习任务", 845, "QALearnTask", model.getLearnTaskId());
			}
			
			task.setLearnState(-1);
			List<QAFileRegReader> readers = qAFileRegReaderService.getByTask(model.getLearnTaskId());
			for(QAFileRegReader reader:readers)
			{
				reader.setLearnState(-1);
				qAFileRegReaderService.update(reader);
			}
			qALearnTaskService.update(task);
			
				
			
			map.put("success", true);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void del()
	{
		qALearnTaskService.delete(model.getLearnTaskId());
	}
	
	public void getTaskList()
	{
		List<QALearnTask> taskList = qALearnTaskService.getAll(model.getLearnState());
		
		//0：未提交；1：学习中（已提交）；2：完成
	     
	    String[] _nory_format = {"learnTaskId", "purpose","student","createTime","learnState"};
		String json = JsonPluginsUtil.beanListToJson(taskList, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);
		
	}
	public void getTaskListByUser()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		List<QAFileRegReader> readers = qAFileRegReaderService.getByUser(user,model.getLearnState());
		List<QALearnTask> taskList = new ArrayList<QALearnTask>();
		for(QAFileRegReader reader:readers)
		{
			if(!taskList.contains(reader.getQalearnTask()))
			{
				QALearnTask task = reader.getQalearnTask();
				//task 0：未提交；1：学习中（已提交）；2：完成
				//reader 0：未生效；1：要求学，还未学；2：学完
				task.setLearnState(reader.getLearnState());
				taskList.add(task);
			}
		}
		
		//0：未提交；1：学习中（已提交）；2：完成
	     
	     String[] _nory_format = {"learnTaskId", "purpose","student","createTime","learnState"};
		String json = JsonPluginsUtil.beanListToJson(taskList, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);
	}
	
	
	public void confirmFinish()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAFileRegReader reader = qAFileRegReaderService.getbyTaskAndUser(model.getLearnTaskId(),user);
		reader.setFinishTime(new Date());
		if(reader.getLearnState()!=2)
		{
			reader.setLearnState(2);
			qAFileRegReaderService.update(reader);
			
			QALearnTask task = qALearnTaskService.getById(model.getLearnTaskId());
			boolean isFinish = true;
			//Set<QAFileRegReader> readers = (Set<QAFileRegReader>)task.getQafileRegReaders();
			List<QAFileRegReader> readers = qAFileRegReaderService.getByTask(task.getLearnTaskId());
			for(QAFileRegReader reader2:readers)
			{
				if(reader2.getFinishTime()==null||"".equals(reader2.getFinishTime()))
				{
					isFinish = false;
				}
			}
			if(isFinish)
			{
				task.setLearnState(2);
				qALearnTaskService.update(task);
				map.put("finish", true);
			}
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "该任务你已经确认完成过");
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
		
		
	}
	
	public void sendNotification()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> receList = new ArrayList<String>();
			if(receiverList!=null)
			{
				String[] resList = receiverList.split(",");
				if(resList!=null&&resList.length>0)
				{
					for(String str:resList)
					{
						receList.add(str);
					}
					
				}
			}
			//通知QAM去审批
			TblNotification tblNotification = new TblNotification();
			tblNotification.setId(tblNotificationService.getKey("TblNotification"));
			
			tblNotification.setAttachmentFlag(0);//比别人多了这个设置
			
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgTitle(msgTitle);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());
			tblNotification.setSendTime(new Date());
		
			tblNotificationService.save(tblNotification,receList);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","发送邮件过程中出现异常"+e.getMessage());
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	private void writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(EsTypeDesc);
		es.setEsType(EsType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(EsType);
		esLink.setEsTypeDesc(EsTypeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public QALearnTaskService getqALearnTaskService() {
		return qALearnTaskService;
	}
	public void setqALearnTaskService(QALearnTaskService qALearnTaskService) {
		this.qALearnTaskService = qALearnTaskService;
	}
	public String getFileRegIds() {
		return fileRegIds;
	}
	public void setFileRegIds(String fileRegIds) {
		this.fileRegIds = fileRegIds;
	}



	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}



	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}



	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public QAFileRegReaderService getqAFileRegReaderService() {
		return qAFileRegReaderService;
	}



	public void setqAFileRegReaderService(
			QAFileRegReaderService qAFileRegReaderService) {
		this.qAFileRegReaderService = qAFileRegReaderService;
	}



	public String getPlanFinishTime() {
		return planFinishTime;
	}



	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}



	public QALearnTaskFileService getqALearnTaskFileService() {
		return qALearnTaskFileService;
	}



	public void setqALearnTaskFileService(
			QALearnTaskFileService qALearnTaskFileService) {
		this.qALearnTaskFileService = qALearnTaskFileService;
	}



	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}



	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}
	public TblESService getTblESService() {
		return tblESService;
	}
	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
	
	
}
