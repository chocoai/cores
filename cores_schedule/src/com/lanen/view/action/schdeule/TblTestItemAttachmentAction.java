package com.lanen.view.action.schdeule;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
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
import com.lanen.model.contract.TblTestItemAttachment;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblTestItemAttachmentService;
import com.lanen.util.DateUtil;
import com.lanen.util.FileOperateUtil;
import com.lanen.util.StringUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblTestItemAttachmentAction extends BaseAction<TblTestItemAttachment>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948755742424270318L;
	
	@Resource
	private TblTestItemAttachmentService tblTestItemAttachmentService;
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	private File attachment;//File对象，目的是获取页面上传的文件
	
	private String attachmentFileName;    
	private String attachmentContentType;
	//uploadFile对应页面<input type="file" name="uploadFile">  
	private List<File> uploadFile;   
	//文件名对应uploadFile+“FileName”，要不获取不到文件名  
	private List<String> uploadFileFileName;    

	private List<String> uploadFileContentType;//文件类型
	
	private List<String> remarks;//备注
	
	private List<String> attachmentNames;//文件名
	
	private InputStream fileInput;  
    private String fileName;    
	
    public void importFile(){
    	int i = 0;
    	List<TblTestItemAttachment> list = new ArrayList<TblTestItemAttachment>();
    	String fileNames = "";
    	for(String fileName:uploadFileFileName){
            TblTestItemAttachment attachment = new TblTestItemAttachment();
		    attachment.setId(tblTestItemAttachmentService.getKey());
		    attachment.setTestItemCode(model.getTestItemCode());
			String suffixName ="";
			if(fileName.contains(".")){
				suffixName = fileName.substring(fileName.lastIndexOf(".")
						, fileName.length());
			}
            String attachmentName = attachmentNames.get(i);
			if(!"".equals(suffixName) && !attachmentName.endsWith(suffixName)){
				   attachmentName = attachmentName+suffixName;
			}
            attachment.setAttachmentName(attachmentName);
			attachment.setAttachmentFile( FileOperateUtil.getInstance().getBytes(uploadFile.get(i)));
			attachment.setRemark(remarks.get(i));
			attachment.setAppendDate(new Date());
			attachment.setOperator(getCurrentRealName());
			list.add(attachment);
			i++;
			if(i==0){
				fileNames = attachmentName;
			}else{
				fileNames = fileNames + attachmentName;
			}
    	}
    	//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setEsType(447);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("供试品文件上传签字确认");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
	
		esLink.setTableName("TblTestItemAttachment");
		esLink.setDataId(model.getId());
		esLink.setTblES(es);
		esLink.setEsType(447);
		esLink.setEsTypeDesc("供试品文件上传签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		String success =  "";
		 try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			writeLog("签字","供试品："+model.getTestItemCode()+"  ，上传    "+fileNames+"  文件，签字");
	    	tblTestItemAttachmentService.saveList(list);
	    	success =  "true";
		 }catch(Exception e){
			 success =  "false";
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
    	writeJson(success);
    }
    
	public void importFile2(){
        String success = "true";
		
		byte[] attachmentFile = FileOperateUtil.getInstance().getBytes(attachment);
		if(dataCheck(model)){
			    TblTestItemAttachment attachment = new TblTestItemAttachment();
			    attachment.setId(tblTestItemAttachmentService.getKey());
			    attachment.setTestItemCode(model.getTestItemCode());
				String suffixName ="";
				if(attachmentFileName.contains(".")){
					suffixName = attachmentFileName.substring(attachmentFileName.lastIndexOf(".")
							, attachmentFileName.length());
				}
				String attachmentName = model.getAttachmentName();
				if(!"".equals(suffixName) && !attachmentName.endsWith(suffixName)){
					   attachmentName = attachmentName+suffixName;
				}
				attachment.setAttachmentName(attachmentName);
				attachment.setAttachmentFile(attachmentFile);
				attachment.setRemark(model.getRemark());
				attachment.setAppendDate(DateUtil.getTodayDate());
				attachment.setOperator(getCurrentRealName());
				tblTestItemAttachmentService.save(attachment);
				success = "true";
	    }else{
			success = "false";
		}
		
		writeJson(success);
	}
	
	public void loadAttachment(){
		List<TblTestItemAttachment> list = tblTestItemAttachmentService.getByTestItemCode(model.getTestItemCode());
		String jsonStr = JsonPluginsUtil.beanListToJson(list, "yyyy-MM-dd HH:mm");
		writeJson(jsonStr);
		
	}
	
	public String download2() throws Exception{  
		if(null != model.getId() && !"".equals(model.getId())){
			TblTestItemAttachment attchment = tblTestItemAttachmentService.getById(model.getId());
			if(null != attchment){
				fileInput = new ByteArrayInputStream(attchment.getAttachmentFile());
				fileName = attchment.getAttachmentName();
				fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
				fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
			}
		}
	    return "download2";  
	} 
	
	
	public void delete(){
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setEsType(446);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("供试品文件删除签字确认");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
	
		esLink.setTableName("TblTestItemAttachment");
		esLink.setDataId(model.getId());
		esLink.setTblES(es);
		esLink.setEsType(446);
		esLink.setEsTypeDesc("供试品文件删除签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		
		TblTestItemAttachment tblTestItemAttachment = tblTestItemAttachmentService.getById(model.getId());
		
		Map<String,Object> map =  new HashMap<String, Object>();
		 try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			map.put("success", true);
			tblTestItemAttachmentService.delete(model.getId());
			writeLog("签字","供试品："+tblTestItemAttachment.getTestItemCode()+"  ，删除    "+tblTestItemAttachment.getAttachmentName()+"  文件，签字");
		 }catch(Exception e){
			 map.put("success", false);
			 map.put("msg", "与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public void selectAttachment(){
		Map<String,Object> map =  new HashMap<String, Object>();
		List<TblTestItemAttachment> list = tblTestItemAttachmentService.getByTestItemCode(model.getTestItemCode());
		if(null != list && list.size() > 0 ){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("供试品");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	/**
	 * 数据检查（非空，长度），false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean dataCheck(TblTestItemAttachment obj){
		boolean flag = true;
		if(!StringUtil.checkStingAndLength(obj.getTestItemCode(), 50)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getAttachmentName(), 200)){
			flag = false;
		}else if(null != obj.getRemark() && obj.getRemark().getBytes().length>200){
			flag = false;
		}
		return flag;
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

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
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

	public List<File> getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(List<File> uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<String> getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(List<String> uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public List<String> getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(List<String> uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public List<String> getAttachmentNames() {
		return attachmentNames;
	}

	public void setAttachmentNames(List<String> attachmentNames) {
		this.attachmentNames = attachmentNames;
	}

}
