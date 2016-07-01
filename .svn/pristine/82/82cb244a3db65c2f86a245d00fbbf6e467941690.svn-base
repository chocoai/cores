package com.lanen.view.action;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFile;
import com.lanen.model.qa.TblStudyFileDis;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.qa.TblStudyPlanReadRecord;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.qa.TblStudyFileService;
import com.lanen.service.qa.TblStudyPlanReadRecordService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblStudyFileIndexAction extends BaseAction<TblStudyFileIndex>{

	private static final long serialVersionUID = -2378797888943341845L;
	
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private TblStudyFileService tblStudyFileService;
	@Resource
	private TblESService  tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private UserService userService;
	@Resource
	private TblStudyPlanReadRecordService tblStudyPlanReadRecordService;
	
	private String password;
	private String studyNoParam;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	private InputStream fileInput;
	private String downFileName;
	private String fileId;
	
	private String afterTabOpenAction;
	
	public String list()
	{
		ActionContext.getContext().getSession().put("studyNoParam", studyNoParam);
		User user = (User)ActionContext.getContext().getSession().get("user");
		String qa = null;
		QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(index!=null&&index.getInspector()!=null)
		{
			qa = index.getInspector();
		}else {
			TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
			if(item!=null&&item.getQa()!=null)
				qa = item.getQa();			
		}
		if (user.getRealName().equals(qa)) {
			ActionContext.getContext().put("have", true);
		}else {
			ActionContext.getContext().put("have", false);
		}
		
		if(index!=null&&((index.getReportState()!=null&&index.getReportState()==1)||(index.getChkPlanFinishFlag()!=null&&index.getChkPlanFinishFlag()==1)))
		{
			ActionContext.getContext().put("studyFinishForStudyFile",  true);	
		}else {
			ActionContext.getContext().put("studyFinishForStudyFile",  false);	
		}
		if(afterTabOpenAction!=null&&!"".equals(afterTabOpenAction))
		{
			ActionContext.getContext().put("afterTabOpenAction",  afterTabOpenAction);	
		}
		return "list";
	}
	public void getTypeStudyNo()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//最多两个
		List<TblStudyFileIndex> indexs = tblStudyFileIndexService.getByStudyNo(model.getStudyNo());
		for (TblStudyFileIndex index:indexs) 
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("indexId", index.getStudyFileIndexId()+":"+index.getConfirmer());
			if(index.getFileType()==1)//1：方案；2：报告；
			{
				if(index.getChangeFlag()!=null&&index.getChangeFlag()==1)
				{
					//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
					map.put("text", "专题方案变更"+DateUtil.dateToString(index.getChangeTime(),"yyyy-MM-dd"));	
				}else {
					map.put("text", "专题方案");					
				}
				
			}else {
				//map.put("text","专题报告" );
				if(index.getChangeFlag()!=null&&index.getChangeFlag()==1)
				{
					//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
					map.put("text", "专题报告变更"+DateUtil.dateToString(index.getChangeTime(),"yyyy-MM-dd"));	
				}else {
					map.put("text", "专题报告");					
				}
			}
			
			mapList.add(map);
		}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
		
	}
	public void getStudyFileByStudyFileIndexId()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		TblStudyFileIndex index = tblStudyFileIndexService.getById(model.getStudyFileIndexId());
		if(index!=null)
		{
			//Set<TblStudyFile> files = index.getTblStudyFiles();
			List<TblStudyFile> files = tblStudyFileService.getByFileIndexId(index.getStudyFileIndexId());
			if(files!=null&&files.size()>0)
			{
				for(TblStudyFile file:files)
				{
					
						if(file.getDelFlag()!=null&&file.getDelFlag()==1)
						{
							//无效的记录不做操作
							
						}else {
							Map<String, Object> map = new HashMap<String, Object>();
							//fileType 1：方案；2：报告；
							map.put("studyFileIndexId", index.getStudyFileIndexId());
							map.put("fileType", index.getFileType());
							map.put("fileState", index.getFileState());//0：草稿；1：提交审批中；2：结束
							
							//	Map<String, Object> child = new HashMap<String, Object>();
							//child.put("attachmentFileString",file.getAttachmentFile());
							map.put("id", file.getId());
							map.put("fileVersion", file.getFileVersion());
							map.put("attachmentDesc", file.getAttachmentDesc());
							map.put("submitTime", DateUtil.dateToString(file.getSubmitTime(),"yyyy-MM-dd HH:mm"));
							map.put("attachmentName", file.getAttachmentName());
							map.put("delFlag", file.getDelFlag());//0有效，1删除
							map.put("operate", "");
							mapList.add(map);
						}
					
						
				}
			}
		}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
		
	}
	public void getStudyFileByStudyNo()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<TblStudyFileIndex> indexs = tblStudyFileIndexService.getByStudyNo(model.getStudyNo());
		if(indexs!=null&&indexs.size()>0)
		{
			for(TblStudyFileIndex index:indexs)
			{
				//Set<TblStudyFile> files = index.getTblStudyFiles();
				List<TblStudyFile> files = tblStudyFileService.getByFileIndexId(index.getStudyFileIndexId());
				if(files!=null&&files.size()>0)
				{
					for(TblStudyFile file:files)
					{
						if(file.getDelFlag()!=null&&file.getDelFlag()==1)
						{
							//无效的不做操作
						}else {
							Map<String, Object> map = new HashMap<String, Object>();
							//fileType 1：方案；2：报告；
							map.put("studyFileIndexId", index.getStudyFileIndexId());
							map.put("fileType", index.getFileType());
							map.put("fileState", index.getFileState());//0：草稿；1：提交审批中；2：结束
							
							map.put("id", file.getId());
							map.put("fileVersion", file.getFileVersion());
							map.put("attachmentDesc", file.getAttachmentDesc());
							map.put("submitTime", DateUtil.dateToString(file.getSubmitTime(),"yyyy-MM-dd HH:mm"));
							map.put("attachmentName", file.getAttachmentName());
							map.put("delFlag", file.getDelFlag());//0有效，1删除
							map.put("operate", "");
							mapList.add(map);
						}
						
					}
				}
		
			}
		}
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	
	public void confirmStudyFileIndex()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		TblStudyFileIndex fileIndex = tblStudyFileIndexService.getById(model.getStudyFileIndexId());
		fileIndex.setConfirmer(user.getRealName());
		fileIndex.setConfirmTime(new Date());
		tblStudyFileIndexService.update(fileIndex);
		map.put("indexId", fileIndex.getStudyFileIndexId()+":"+fileIndex.getConfirmer());
		
		String fileType="";
		if(fileIndex.getFileType()==1)
		{
			fileType="方案";
			writeES("QA确认接收"+fileType, 822, "TblStudyFileIndex", model.getStudyFileIndexId());
			
		}else if(fileIndex.getFileType()==2){
			fileType="报告";
			writeES("QA确认接收"+fileType, 842, "TblStudyFileIndex", model.getStudyFileIndexId());
			
		}
		writeLog("QA确认接收"+fileType,"QA确认接收"+fileType,"QA确认接收"+fileType+",专题编号："+fileIndex.getStudyNo());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void getChangeApply()
	{
		//最多两个
		List<TblStudyFileIndex> studyFileIndexs = tblStudyFileIndexService.getByStudyNo(studyNoParam);
		Map<String, Object> map = new HashMap<String, Object>();
		String str = "";
		Integer type=-1;
		for(TblStudyFileIndex studyFileIndex:studyFileIndexs)
		{
			if(studyFileIndex.getChangeFlag()!=null&&studyFileIndex.getChangeFlag()==1)//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
			{
				if(studyFileIndex.getFileType()==1)//1：方案；2：报告；
				{
					str +="由于"+studyFileIndex.getChangeRsn()+"申请方案变更<br/>";
					if(type!=-1)
					{
						type = 2;
					}else {
						type = 0;//0是方案
					}
				}else if(studyFileIndex.getFileType()==2){
					str +="由于"+studyFileIndex.getChangeRsn()+"申请报告变更<br/>";
					if(type!=-1)
					{
						type = 2;
					}else {
						type = 1;//1报告
					}
					
				}
			}
		}
		map.put("type", type);
		map.put("reason", str);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void afterSignApprovalApply()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User tempUser = (User)ActionContext.getContext().getSession().get("user");
		//model.getFileType();//1：方案；2：报告； 前台传过来的是0方案，1报告，2是两者
		String str = "";
		if(model.getFileType()==0)
			str="方案";
		if(model.getFileType()==1)
			str="报告";
		if(model.getFileType()==2)
			str="方案和报告";
			
		
		if(model.getFileType()==0||model.getFileType()==2)
		{
			TblStudyFileIndex studyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);
			if(model.getChangeFlag()==-2)
			{
				studyFileIndex.setChangeFlag(null);//驳回就相当于没有了申请这回事
			}else {
				//审批通过要QA重新确认
				studyFileIndex.setChangeFlag(2);//2或者-2
				studyFileIndex.setConfirmer(null);
				studyFileIndex.setConfirmTime(null);
			}
			tblStudyFileIndexService.update(studyFileIndex);
		}
		if(model.getFileType()==1||model.getFileType()==2)
		{
			TblStudyFileIndex studyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 2);
			studyFileIndex.setChangeFlag(model.getChangeFlag());//2或者-2
			if(model.getChangeFlag()==-2)
			{
				studyFileIndex.setChangeFlag(null);//驳回就相当于没有了申请这回事
			}
			tblStudyFileIndexService.update(studyFileIndex);
		}
		
		//writeES(String EsTypeDesc,int EsType,String tableName,String dataId);
		String apprResult = "";
		if(model.getChangeFlag()==2)
		{
			apprResult="通过";
		}
		if(model.getChangeFlag()==-2)
		{
			apprResult="驳回";
		}
		//通知SD
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		String msgTitle=("QAM("+tempUser.getRealName()+")，"+apprResult+"了修改"+str+"申请， 专题编号:　"+studyNoParam+"，请注意查看");//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QAM("+tempUser.getRealName()+")于"+currentDate+apprResult+"了修改"+str+"申请，"+
		"<br>专题编号:　 "+studyNoParam+"，请注意查看，特此提醒！";
		
		String receiverList = "";
		QAStudyChkIndex chkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		String sd = "";
		if(chkIndex!=null)
		{
			sd = chkIndex.getSd();
		}
		if(sd==null||"".equals(sd))
		{
			TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
			if(studyItem!=null)
			{
				sd = studyItem.getSd();
			}else{
				sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
			}
		}
	//	String sd=tblStudyPlanService.getSDByStudyNo(studyNoParam);
		String sdCode = userService.getByRealName(sd).getUserName();
		receiverList+=(sdCode)+",";
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public String downloadAttachment() throws Exception{  
		if(null != fileId && !"".equals(fileId)){
			TblStudyFile attchment = tblStudyFileService.getById(fileId);
			
			if(null != attchment){
				fileInput = new ByteArrayInputStream(attchment.getAttachmentFile());
				downFileName = attchment.getAttachmentName();
				downFileName= java.net.URLEncoder.encode(downFileName,request.getCharacterEncoding());
				downFileName = new String(downFileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
			
			}
		}

	    return "download";  
	} 
	
	public void saveStudyPlanRead()
	{
		Map<String, Object> map = new HashMap<String, Object>();
	//	studyNoParam
		TblStudyFileIndex tblStudyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		TblStudyPlanReadRecord readRecord = new TblStudyPlanReadRecord();
		String key = tblStudyPlanReadRecordService.getKey("TblStudyPlanReadRecord");
		readRecord.setId(key);
		readRecord.setReader(user.getRealName());
		readRecord.setReaderCode(user.getUserName());
		readRecord.setReadFinishTime(new Date());
		readRecord.setTblStudyFileIndex(tblStudyFileIndex);
		tblStudyPlanReadRecordService.save(readRecord);
		
		writeES("阅读方案完成", 834, "TblStudyPlanReadRecord", key);
		writeLog("阅读方案完成", "阅读方案完成", "阅读方案完成,专题编号："+studyNoParam);
		
		map.put("success", true);
		
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
			TblNotification tblNotification = new TblNotification();
			tblNotification.setId(tblNotificationService.getKey("TblNotification"));
			
			tblNotification.setMsgTitle(msgTitle);//消息头
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			
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
	
	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}

	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}
	
	public TblStudyFileService getTblStudyFileService() {
		return tblStudyFileService;
	}
	public void setTblStudyFileService(TblStudyFileService tblStudyFileService) {
		this.tblStudyFileService = tblStudyFileService;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getStudyNoParam() {
		return studyNoParam;
	}

	public void setStudyNoParam(String studyNoParam) {
		this.studyNoParam = studyNoParam;
	}
	public TblStudyItemService getTblStudyItemService() {
		return tblStudyItemService;
	}
	public void setTblStudyItemService(TblStudyItemService tblStudyItemService) {
		this.tblStudyItemService = tblStudyItemService;
	}
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}
	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public InputStream getFileInput() {
		return fileInput;
	}
	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAfterTabOpenAction() {
		return afterTabOpenAction;
	}
	public void setAfterTabOpenAction(String afterTabOpenAction) {
		this.afterTabOpenAction = afterTabOpenAction;
	}
	

}
