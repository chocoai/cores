package com.lanen.view.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.model.qa.QAFileAttachment;
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAFileType;
import com.lanen.model.qa.QALearnTaskFile;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkItemQAFileRegService;
import com.lanen.service.qa.QAFileAttachmentService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QAFileTypeService;
import com.lanen.service.qa.QALearnTaskFileService;
import com.lanen.service.qa.QALearnTaskService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class QAFileRegAction extends BaseAction<QAFileReg> {

	private static final long serialVersionUID = 2269399178386702788L;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private QAFileTypeService qAFileTypeService;
	@Resource
	private DictChkItemQAFileRegService dictChkItemQAFileRegService;
	@Resource
	private QAFileAttachmentService qAFileAttachmentService;
	@Resource
	private QALearnTaskFileService qALearnTaskFileService;
	@Resource
	private UserService userService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private QALearnTaskService qALearnTaskService;
	
	
	private String fileTypeId;
	private String fileSearchCondition;
	private String fileStateCondition;
	
	public String list()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean  falg = userService.checkPrivilege(user, "FM");
		boolean  falg1 = userService.checkPrivilege(user, "SD");
		boolean  falg2 = userService.checkPrivilege(user, "QA负责人");
		boolean  falg3 = userService.checkPrivilege(user, "QA");
		boolean  falg4 = userService.checkPrivilege(user, "病理负责人");
		boolean  falg5 = userService.checkPrivilege(user, "病理");
		if(falg){
			ActionContext.getContext().getSession().put("role", "FM");
		}else if(falg1){
			ActionContext.getContext().getSession().put("role", "SD");
		}else if(falg2){
			ActionContext.getContext().getSession().put("role", "QALead");
		}else if(falg3){
			ActionContext.getContext().getSession().put("role", "QA");
		}else if(falg4){
			ActionContext.getContext().getSession().put("role", "PathSDLead");
		}else if(falg5){
			ActionContext.getContext().getSession().put("role", "PathSD");
		}
        boolean falg6  = userService.checkPrivilege(user, "部门负责人");
		ActionContext.getContext().getSession().put("department", falg6);
		
		//从待办事宜那儿过来的
		String oneChkPlanId = (String)ActionContext.getContext().getSession().get("oneChkPlanId");
		ActionContext.getContext().getSession().put("oneChkPlanId","");
		
		if(oneChkPlanId!=null&&!"".equals(oneChkPlanId))
		{
			if(oneChkPlanId.startsWith("second3"))//学习任务
			{
				String learnTaskId = oneChkPlanId.substring(7);
				
				ActionContext.getContext().put("selectLearnTaskId", learnTaskId);
			}
		}
		
		
		return "list";
	}
	public void save()
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = qAFileRegService.isExist(model,fileTypeId);
		
		if(!isExist)
		{
			QAFileType type = qAFileTypeService.getById(fileTypeId);
			QAFileReg reg = new QAFileReg();
			String key = qAFileRegService.getKey("QAFileReg");
			reg.setFileRegId(key);
			reg.setFileCode(model.getFileCode());
			reg.setFileName(model.getFileName());
			reg.setFilePublishDepartment(model.getFilePublishDepartment());
			reg.setFilePublishTime(model.getFilePublishTime());
			reg.setFileType(type.getFileType());
			reg.setFileTypeName(type.getFileTypeName());
			reg.setFileVersion(model.getFileVersion());
			
			reg.setIsVersionUpdate(model.getIsVersionUpdate());
			reg.setQafileType(type);
			reg.setRemark(model.getRemark());
			
			qAFileRegService.save(reg);
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg","相同的数据已经存在");
		}
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);	
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAFileReg reg = qAFileRegService.getById(model.getFileRegId());
		boolean isExist = qAFileRegService.isExist(model,reg.getQafileType().getFileTypeId());
		if(!isExist)
		{
			QAFileType type = qAFileTypeService.getById(reg.getQafileType().getFileTypeId());
			
			if(reg.getFileCode().equals(model.getFileCode())&&reg.getFileName().equals(model.getFileName())
					&&reg.getFilePublishDepartment().equals(model.getFilePublishDepartment())
					&&reg.getFilePublishTime().equals(model.getFilePublishTime())
					&&reg.getQafileType().getFileTypeId().equals(fileTypeId)
					&&reg.getFileVersion().equals(model.getFileVersion())&&reg.getIsVersionUpdate().equals(model.getIsVersionUpdate())
					&&reg.getRemark().equals(model.getRemark()))
			{
				
				map.put("success", false);
				map.put("msg","没有做任何改动");
			}else {
				reg.setFileCode(model.getFileCode());
				reg.setFileName(model.getFileName());
				reg.setFilePublishDepartment(model.getFilePublishDepartment());
				reg.setFilePublishTime(model.getFilePublishTime());
				reg.setFileType(type.getFileType());
				reg.setFileTypeName(type.getFileTypeName());
				reg.setFileVersion(model.getFileVersion());
				
				reg.setIsVersionUpdate(model.getIsVersionUpdate());
				reg.setQafileType(type);
				reg.setRemark(model.getRemark());
				
				qAFileRegService.update(reg);
				map.put("success", true);
			}
		}else {
			map.put("success", false);
			map.put("msg","相同的数据已经存在");
		}
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);	
			
			
	}
	public void isExistTaskFile()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		//没有子文件类型，看该文件类型下的文件是否有学习任务。如果存在则不可删除
		boolean flag = qALearnTaskService.isExistTaskByFile(model.getFileRegId());
		map.put("isExistTask", flag);
			
			
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		//如果已经学习过就不可以删除了
		//删除文件和检查项的关系
		List<DictChkItemQAFileReg> regs = dictChkItemQAFileRegService.getByFileRegId(model.getFileRegId());
		if(regs!=null&&regs.size()>0)
		{
			for(DictChkItemQAFileReg reg:regs)
			{
				dictChkItemQAFileRegService.delete(reg.getChkItemQAFileRegId());
			}
		}
		List<QAFileAttachment> attachments = qAFileAttachmentService.getByFileRegId(model.getFileRegId());
		if(attachments!=null)
		{
			for(QAFileAttachment attachment:attachments)
			{
				qAFileAttachmentService.delete(attachment.getAttachmentId());
			}
		}
		/*删文件不可以同时删除任务文件
		List<QALearnTaskFile> qALearnTaskFiles = qALearnTaskFileService.getByFileRegId(model.getFileRegId());
		for(QALearnTaskFile qALearnTaskFile:qALearnTaskFiles)
		{
			qALearnTaskFileService.delete(qALearnTaskFile.getLearnTaskFileId());
		}*/
		QAFileReg qAFileReg = qAFileRegService.getById(model.getFileRegId());
		qAFileRegService.delete(model.getFileRegId());
		//签名写入
		writeES("QAM删除一个文件",826,"QAFileReg",qAFileReg.getFileCode()+":"+qAFileReg.getFileName());
		
		//日志录入
		writeLog("删除文件","QAM删除一个文件","QAM删除一个文件,文件编号和名称为："+qAFileReg.getFileCode()+":"+qAFileReg.getFileName());
	
	}
	public void loadListByType()
	{
		List<QAFileReg> list = qAFileRegService.getByType(fileTypeId);
		
	     String[] _nory_format={"fileRegId", "fileType", "fileTypeName","fileCode","fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
	     
	     String json=JsonPluginsUtil.beanListToJson(list,"yyyy-MM-dd",_nory_format,true);
	     writeJson(json);
	
	}
	public void loadListByCondition()
	{
		try {
			fileSearchCondition = new String(fileSearchCondition.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User)ActionContext.getContext().get("user");
		boolean isQAM = userService.checkPrivilege(user, "QA负责人");
		List<QAFileReg> list = new ArrayList<QAFileReg>();
		if(!isQAM)
		{
			list = qAFileRegService.getByConditionAndUser(model.getFileType(),fileStateCondition,fileSearchCondition,user);
		}else{
			list = qAFileRegService.getByConditionAndUser(model.getFileType(),fileStateCondition,fileSearchCondition,null);
		}
	
	     String[] _nory_format={"fileRegId", "fileType", "fileTypeName","fileCode","fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
	     
	     String json=JsonPluginsUtil.beanListToJson(list,"yyyy-MM-dd",_nory_format,true);
	     
	     writeJson(json);
	
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
	
	public String getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(String fileTypeId) {
		this.fileTypeId = fileTypeId;
	}
	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}

	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}


	public QAFileTypeService getqAFileTypeService() {
		return qAFileTypeService;
	}


	public void setqAFileTypeService(QAFileTypeService qAFileTypeService) {
		this.qAFileTypeService = qAFileTypeService;
	}
	public DictChkItemQAFileRegService getDictChkItemQAFileRegService() {
		return dictChkItemQAFileRegService;
	}
	public void setDictChkItemQAFileRegService(
			DictChkItemQAFileRegService dictChkItemQAFileRegService) {
		this.dictChkItemQAFileRegService = dictChkItemQAFileRegService;
	}
	public QAFileAttachmentService getqAFileAttachmentService() {
		return qAFileAttachmentService;
	}
	public void setqAFileAttachmentService(
			QAFileAttachmentService qAFileAttachmentService) {
		this.qAFileAttachmentService = qAFileAttachmentService;
	}
	public QALearnTaskFileService getqALearnTaskFileService() {
		return qALearnTaskFileService;
	}
	public void setqALearnTaskFileService(
			QALearnTaskFileService qALearnTaskFileService) {
		this.qALearnTaskFileService = qALearnTaskFileService;
	}
	public String getFileSearchCondition() {
		return fileSearchCondition;
	}
	public void setFileSearchCondition(String fileSearchCondition) {
		this.fileSearchCondition = fileSearchCondition;
	}
	public String getFileStateCondition() {
		return fileStateCondition;
	}
	public void setFileStateCondition(String fileStateCondition) {
		this.fileStateCondition = fileStateCondition;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
