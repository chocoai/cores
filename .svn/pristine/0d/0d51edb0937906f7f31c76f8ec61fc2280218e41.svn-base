package com.lanen.view.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.lanen.model.qa.QAFileAttachment;
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QALearnTask;
import com.lanen.model.qa.QALearnTaskFileReadRecord;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.QAFileAttachmentService;
import com.lanen.service.qa.QAFileRegReaderService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QALearnTaskFileReadRecordService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.DateUtil;
import com.lanen.util.FileOperateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAFileAttachmentAction extends BaseAction<QAFileAttachment>{

	private static final long serialVersionUID = 7034428232272125979L;

	@Resource
	private QAFileAttachmentService qAFileAttachmentService;
	@Resource
	private QAFileRegService qaFileRegService;
	@Resource
	private QAFileRegReaderService qAFileRegReaderService;
	@Resource
	private QALearnTaskFileReadRecordService qALearnTaskFileReadRecordService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private QALearnTaskService qALearnTaskService;
	
	private File attachment;//File对象，目的是获取页面上传的文件
	private String attachmentFileName;    
	private String attachmentContentType;
	
	private InputStream fileInput;  
    private String fileName;  
    private String downFileName;
  
    private String learnTaskId;
    private String fileRegId;
    
    private Integer readMinuteUnit;//1分钟，2小时
	
	/**文件上传*/
	public void importFile(){
		Map<String, Object> map = new HashMap<String, Object>();
		byte[] attachmentFile = FileOperateUtil.getInstance().getBytes(attachment);
		if(dataCheck(model)){
		
			QAFileAttachment qaAttachment = new QAFileAttachment();
			String key = qAFileAttachmentService.getKey("QAFileAttachment");
			qaAttachment.setAttachmentId(key);
			qaAttachment.setAttachmentName(attachmentFileName);
			qaAttachment.setFile(attachmentFile);
			qaAttachment.setRemark(model.getRemark());
			qaAttachment.setAppendDate(DateUtil.getTodayDate());
			qaAttachment.setOperator(getCurrentRealName());
			qaAttachment.setReadMinute(30);
			
			QAFileReg qafileReg = qaFileRegService.getById(fileRegId);
			qaAttachment.setQafileReg(qafileReg);
			qaAttachment.setFileName(qafileReg.getFileName());
			try{
				  qAFileAttachmentService.save(qaAttachment);
				  map.put("success",true);
			
				  map.put("attachmentId", qaAttachment.getAttachmentId());
				  map.put("attachmentName", qaAttachment.getAttachmentName());
				  writeJson("true");
			}catch(Exception e){
				 map.put("success",false);
				 System.out.println("执行失败，出错种类"+e.getMessage()+".");
				 writeJson("false");
			}	
		}else{
				//json.setMsg("无权限此操作");
			 map.put("success",false);
			 writeJson("false");
		}

		//String jsonStr = JsonPluginsUtil.beanToJson(map);
		//writeJson(jsonStr);
	}
	public void readTimeUnitList()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("text", "分钟");
		mapList.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("text", "小时");
		mapList.add(map);
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	public void updateMinute()
	{
		QAFileAttachment attachment = qAFileAttachmentService.getById(model.getAttachmentId());
		if(readMinuteUnit!=null&&readMinuteUnit==2){
			attachment.setReadMinute(model.getReadMinute()*60);
		}else{
			attachment.setReadMinute(model.getReadMinute());
		}
			
		qAFileAttachmentService.update(attachment);
		
	}
	public void isExistAtt(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != model.getAttachmentId() && !"".equals(model.getAttachmentId())){
			QAFileAttachment attchment = qAFileAttachmentService.getById(model.getAttachmentId());
			if(null != attchment){
				map.put("success", true);
			}
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public String downloadAttachment() throws Exception{  
		if(null != model.getAttachmentId() && !"".equals(model.getAttachmentId())){
			QAFileAttachment attchment = qAFileAttachmentService.getById(model.getAttachmentId());
			/*ByteArrayInputStream testInput = new ByteArrayInputStream(attchment.getFile());
			byte[] bs=new byte[1024];
			int i=0;
			while((i=testInput.read(bs))!=-1)
			{
				System.out.println(new String(bs,0,i));
			}*/
			
			if(null != attchment){
				fileInput = new ByteArrayInputStream(attchment.getFile());
				downFileName = attchment.getAttachmentName();
				downFileName= java.net.URLEncoder.encode(downFileName,request.getCharacterEncoding());
				downFileName = new String(downFileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
				
				User user = (User)ActionContext.getContext().getSession().get("user");
				QAFileRegReader qafileRegReader = qAFileRegReaderService.getbyTaskAndUser(learnTaskId, user);
				if(qafileRegReader!=null)
				{
					boolean isExist = qALearnTaskFileReadRecordService.isExistByLearnRecFileAndAttach(qafileRegReader.getLearnRecordId(),fileRegId,model.getAttachmentId());
					if(!isExist)
					{
						QALearnTaskFileReadRecord learnAttachRecord = new QALearnTaskFileReadRecord();
						learnAttachRecord.setAttachmentId(model.getAttachmentId());
						learnAttachRecord.setFileReadRecordId(qALearnTaskFileReadRecordService.getKey("QALearnTaskFileReadRecord"));
						learnAttachRecord.setFileRegId(fileRegId);
						learnAttachRecord.setQafileRegReader(qafileRegReader);
						learnAttachRecord.setReadBeginTime(new Date());
						learnAttachRecord.setReadFinishFlag(0);
						
						learnAttachRecord.setAttachmentName(attchment.getAttachmentName());
						learnAttachRecord.setFileName(attchment.getFileName());
						
						qALearnTaskFileReadRecordService.save(learnAttachRecord);
					}
				}
				
			}
		}

	    return "download2";  
	} 
	
	/**删除附件*/
	public void delete() throws Exception{
		Json json = new Json();
		if(null != model.getAttachmentId() && !"".equals(model.getAttachmentId())){
			//QALearnTaskFileReadRecord查看有没有学习记录，如果有学习记录就不可以删除
			
			qAFileAttachmentService.delete(model.getAttachmentId());
			json.setSuccess(true);
			json.setMsg("附件删除成功");
						
		}

		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	public void getListByFileRegId()
	{
		List<QAFileAttachment> attachments = qAFileAttachmentService.getByFileRegId(fileRegId);
		List<Map<String, Object> > mapList = new ArrayList<Map<String,Object>>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		for(QAFileAttachment att:attachments)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("attachmentId", att.getAttachmentId());
			map.put("attachmentName", att.getAttachmentName());
			map.put("fileName", att.getFileName());
			map.put("remark", att.getRemark());
			map.put("appendDate", att.getAppendDate());
			map.put("readMinute", att.getReadMinute());
			map.put("readMinuteUnit", 1);
			
			
			QAFileRegReader qafileRegReader = qAFileRegReaderService.getbyTaskAndUser(learnTaskId, user);
			if(qafileRegReader!=null)
			{
				QALearnTaskFileReadRecord learnAtta = qALearnTaskFileReadRecordService.getByLearnRecFileAndAttach(qafileRegReader.getLearnRecordId(),fileRegId,att.getAttachmentId());
				if(learnAtta!=null)
				{
					//附件阅读记录
					map.put("fileReadRecordId", learnAtta.getFileReadRecordId());
					map.put("readFinishFlag",learnAtta.getReadFinishFlag());//确认完成 0否 1是
					map.put("operator", 1);//学习中
				}else{
					map.put("operator", 0);//未开始学习
				}
			}
			
			mapList.add(map);
		}
		
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	}
	public void getLearnAttListByFileRegId()
	{
		//QALearnTask task = qALearnTaskService.getById(learnTaskId);
		User user = (User)ActionContext.getContext().getSession().get("user");
		List<Map<String, Object> > mapList = new ArrayList<Map<String,Object>>();
		
		QAFileRegReader qafileRegReader = qAFileRegReaderService.getbyTaskAndUser(learnTaskId, user);
		List<QALearnTaskFileReadRecord> list = qALearnTaskFileReadRecordService.getByLearnRecFile(qafileRegReader.getLearnRecordId(), fileRegId);
		if(qafileRegReader.getLearnState()!=null&&qafileRegReader.getLearnState()==2)
		{
			for(QALearnTaskFileReadRecord learnRecord:list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("attachmentId", learnRecord.getAttachmentId());
				map.put("attachmentName", learnRecord.getAttachmentName());
				map.put("fileName", learnRecord.getFileName());
				//map.put("remark", learnRecord.getRemark());
				//map.put("appendDate", learnRecord.getAppendDate());
				//map.put("readMinute", learnRecord.getReadMinute());
				//map.put("readMinuteUnit", 1);
				map.put("fileReadRecordId", learnRecord.getFileReadRecordId());
				map.put("readFinishFlag",learnRecord.getReadFinishFlag());//确认完成 0否 1是
				map.put("operator", 2);//学习完成
				
				mapList.add(map);
			}
			
			
		}else{
			List<QAFileAttachment> attachments = qAFileAttachmentService.getByFileRegId(fileRegId);
			
			for(QAFileAttachment att:attachments)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("attachmentId", att.getAttachmentId());
				map.put("attachmentName", att.getAttachmentName());
				map.put("fileName", att.getFileName());
				map.put("remark", att.getRemark());
				map.put("appendDate", att.getAppendDate());
				map.put("readMinute", att.getReadMinute());
				map.put("readMinuteUnit", 1);
				
				QALearnTaskFileReadRecord learnAtta = getLearnRecordFromList(list,att.getAttachmentId());
				if(learnAtta!=null)
				{
					//附件阅读记录
					map.put("fileReadRecordId", learnAtta.getFileReadRecordId());
					map.put("readFinishFlag",learnAtta.getReadFinishFlag());//确认完成 0否 1是
					map.put("operator", 1);//学习中
					if(learnAtta.getReadFinishFlag()==1)
					{
						map.put("operator", 2);//学习中
					}
				}else{
					map.put("operator", 0);//未开始学习
				}
				
				mapList.add(map);
			}
		}
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	}
	public QALearnTaskFileReadRecord getLearnRecordFromList(List<QALearnTaskFileReadRecord> list,String attachmentId)
	{
		for(QALearnTaskFileReadRecord rec:list)
		{
			if(rec.getAttachmentId().equals(attachmentId))
			{
				return rec;
			}
		}
		return null;
	}
	
	
	public void canFinishLearnAtt()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAFileAttachment att = qAFileAttachmentService.getById(model.getAttachmentId());
		
		QAFileRegReader qafileRegReader = qAFileRegReaderService.getbyTaskAndUser(learnTaskId, user);
		if(qafileRegReader!=null)
		{
			QALearnTaskFileReadRecord learnAtta = qALearnTaskFileReadRecordService.getByLearnRecFileAndAttach(qafileRegReader.getLearnRecordId(),fileRegId,model.getAttachmentId());
			if(learnAtta!=null)
			{
				Calendar calendar1 = new GregorianCalendar();
				calendar1.setTime(learnAtta.getReadBeginTime());
				if(att.getReadMinute()!=null)
					calendar1.add(Calendar.MINUTE, att.getReadMinute());
				else {
					att.setReadMinute(30);
					calendar1.add(Calendar.MINUTE, 30);
				}
				Date endTime = new Date();
				
				if(endTime.after(calendar1.getTime()))
				{
					
					map.put("success", true);
					
				}else {
					map.put("msg", "该附件的学习时间为："+att.getReadMinute()+"分钟！");
					map.put("success", false);
				}
			}else {
				map.put("msg", "请先学习！");
				map.put("success", false);
			}
				
			
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void finishLearnAttachment()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAFileAttachment att = qAFileAttachmentService.getById(model.getAttachmentId());
		
		QAFileRegReader qafileRegReader = qAFileRegReaderService.getbyTaskAndUser(learnTaskId, user);
	
		QALearnTaskFileReadRecord learnAtta = qALearnTaskFileReadRecordService.getByLearnRecFileAndAttach(qafileRegReader.getLearnRecordId(),fileRegId,model.getAttachmentId());
			
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(learnAtta.getReadBeginTime());
		if(att.getReadMinute()!=null)
			calendar1.add(Calendar.MINUTE, att.getReadMinute());
		else {
				att.setReadMinute(30);
				calendar1.add(Calendar.MINUTE, 30);
		}
		Date endTime = new Date();
				
		if(endTime.after(calendar1.getTime()))
		{
			learnAtta.setReadEndTime(endTime);
			learnAtta.setReadFinishFlag(1);
			map.put("attFinish", true);
			qALearnTaskFileReadRecordService.update(learnAtta);
					
			//查看任务中的文件的附件是否都学习完毕。如果都学习完毕则查看任务下的所有文件是否都学习完毕
			Integer allAttaNum= qALearnTaskFileReadRecordService.getAllTaskAttachNum(learnTaskId);
			Integer allAttaFinNum = qALearnTaskFileReadRecordService.getAllTaskAttachFinishNum(qafileRegReader.getLearnRecordId());
			if(allAttaNum==allAttaFinNum)
			{
				qafileRegReader.setFinishTime(endTime);
				//0：未生效；1：要求学，还未学；2：学完
				qafileRegReader.setLearnState(2);
				qAFileRegReaderService.update(qafileRegReader);
				map.put("taskReadFinish",true );
				
				//看整个任务的所有人是否都学习完毕
				List<QAFileRegReader> readers = qAFileRegReaderService.getByTask(learnTaskId);
				boolean flag = true;
				for(QAFileRegReader reader:readers)
				{
					if(reader.getLearnState()==null||reader.getLearnState()!=2)
						flag=false;
				}
				if(flag)
				{
					QALearnTask task = qALearnTaskService.getById(learnTaskId);
					//0：未提交；1：学习中（已提交）；2：完成
					task.setLearnState(2);
					qALearnTaskService.update(task);
				}
					
					
				
			}else{
				map.put("taskReadFinish",false );
			}
			
			writeES("确认学习附件完成", 835, "QALearnTaskFileReadRecord", learnAtta.getFileReadRecordId());
			writeLog("确认学习附件完成","确认学习附件完成","确认学习附件完成：附件名称："+att.getAttachmentName());
			map.put("success", true);
			
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	

	/**
	 * 数据检查（非空，长度），false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean dataCheck(QAFileAttachment obj){
		boolean flag = true;
	/*	if(!StringUtil.checkStingAndLength(obj.getContractCode(), 50)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getAttachmentName(), 200)){
			flag = false;
		}else if(null != obj.getRemark() && obj.getRemark().getBytes().length>200){
			flag = false;
		}*/
		return flag;
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
	
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public QAFileAttachmentService getqAFileAttachmentService() {
		return qAFileAttachmentService;
	}

	public void setqAFileAttachmentService(
			QAFileAttachmentService qAFileAttachmentService) {
		this.qAFileAttachmentService = qAFileAttachmentService;
	}

	public QAFileRegService getQaFileRegService() {
		return qaFileRegService;
	}

	public void setQaFileRegService(QAFileRegService qaFileRegService) {
		this.qaFileRegService = qaFileRegService;
	}

	public String getFileRegId() {
		return fileRegId;
	}

	public void setFileRegId(String fileRegId) {
		this.fileRegId = fileRegId;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	public Integer getReadMinuteUnit() {
		return readMinuteUnit;
	}
	public void setReadMinuteUnit(Integer readMinuteUnit) {
		this.readMinuteUnit = readMinuteUnit;
	}
	public String getLearnTaskId() {
		return learnTaskId;
	}
	public void setLearnTaskId(String learnTaskId) {
		this.learnTaskId = learnTaskId;
	}

	
}
