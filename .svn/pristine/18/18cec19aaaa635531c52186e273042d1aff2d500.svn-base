package com.lanen.view.action.contract;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblContractAttachment;
import com.lanen.service.contract.TblContractAttachmentService;
import com.lanen.service.contract.TblContractService;
import com.lanen.util.DateUtil;
import com.lanen.util.FileOperateUtil;
import com.lanen.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;


/**
 * 合同附件Action 
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class TblContractAttachmentAction extends BaseAction<TblContractAttachment>{

	private static final long serialVersionUID = 7034428232272125979L;

	@Resource
	private TblContractAttachmentService tblContractAttachmentService;
	@Resource
	private TblContractService tblContractService;
	
	private File attachment;//File对象，目的是获取页面上传的文件
	private String attachmentFileName;    
	private String attachmentContentType;
	
	private InputStream fileInput;  
    private String fileName;  
  
  
	
	/**文件上传*/
	public void importFile(){
		String success = "true";
		
		byte[] attachmentFile = FileOperateUtil.getInstance().getBytes(attachment);
		if(dataCheck(model)){
			//检查是否有写的权限或自己的，且当前合同是否可编辑
			TblContract tblContract = tblContractService.getByCode(model.getContractCode());
			if(null != tblContract){
				if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
					boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
					String reader = getCurrentRealName();
					if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
						||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
						
						TblContractAttachment contractAttachment = new TblContractAttachment();
						contractAttachment.setId(tblContractAttachmentService.getKey());
						contractAttachment.setContractCode(model.getContractCode());
						String suffixName ="";
						if(attachmentFileName.contains(".")){
							suffixName = attachmentFileName.substring(attachmentFileName.lastIndexOf(".")
									, attachmentFileName.length());
						}
						String attachmentName = model.getAttachmentName();
						if(!"".equals(suffixName) && !attachmentName.endsWith(suffixName)){
							attachmentName = attachmentName+suffixName;
						}
						contractAttachment.setAttachmentName(attachmentName);
						contractAttachment.setAttachmentFile(attachmentFile);
						contractAttachment.setRemark(model.getRemark());
						contractAttachment.setAppendDate(DateUtil.getTodayDate());
						contractAttachment.setOperator(getCurrentRealName());
						try{
						   tblContractAttachmentService.save(contractAttachment);
						   success = "true";
						}catch(Exception e){
							 success = "false";
							 System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
							
						}
						//json.setSuccess(true);
						//json.setMsg("附件上传成功");
					
					}else{
						//json.setMsg("无权限此操作");
						success = "false";
					}
				}else{
//					json.setMsg("当前合同不可编辑");
					success = "false";
				}
			}
			
		}else{
//			json.setMsg("附件上传 失败");
			success = "false";
		}
		
//		String jsonStr = JsonPluginsUtil.beanToJson(json);
//		writeJson(jsonStr);
		writeJson(success);
	}
	
	public String download2() throws Exception{  
		if(null != model.getId() && !"".equals(model.getId())){
			TblContractAttachment attchment = tblContractAttachmentService.getById(model.getId());
			if(null != attchment){
				fileInput = new ByteArrayInputStream(attchment.getAttachmentFile());
				fileName = attchment.getAttachmentName();
//				System.out.println(request.getCharacterEncoding());
				fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
				fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
			}
		}
//		System.out.println(fileInput);
//		System.out.println();
//	    fileInput=ServletActionContext.getServletContext().getResourceAsStream("/download/"+"aaaa.jpg");  
//	    fileName="aaaa.jpg";
//	    ActionContext.getContext().put("fileName", fileName);
//	    ActionContext.getContext().put("fileInput", fileInput);
//	    new ByteArrayInputStream("Struts2 文件下载测试".getBytes());
	    return "download2";  
	} 
	
	/**删除附件*/
	public void delete() throws Exception{
		Json json = new Json();
		if(null != model.getId() && !"".equals(model.getId())){
			
			TblContractAttachment tblContractAttachment = tblContractAttachmentService.getById(model.getId());
			
			//检查是否有写的权限或自己的，且当前合同是否可编辑
			TblContract tblContract = tblContractService.getByCode(tblContractAttachment.getContractCode());
			if(null != tblContract){
				if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
					boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
					String reader = getCurrentRealName();
					if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
						||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
						if(tblContractAttachment.getState() == 0){
							tblContractAttachmentService.delete(model.getId());
							json.setSuccess(true);
							json.setMsg("附件删除成功");
						}
					}else{
						json.setMsg("无权限此操作");
					}
				}else{
					json.setMsg("当前合同不可编辑");
				}
			}
		}

		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
//	@Override
//	public void addActionError(String anErrorMessage) {
//		System.out.println("---------------------");
//		if(anErrorMessage.startsWith("the request was rejected because its size")){
//			Json json = new Json();
//			json.setMsg("文件大于10M");
//			String jsonStr = JsonPluginsUtil.beanToJson(json);
//			writeJson(jsonStr);
//			//super.addActionError("文件太大");
//		}else{
//			super.addActionError(anErrorMessage);
//		}
//	}
//	
//	/**文件下载*/
//	public void download(){
//		if(null != model.getId() && !"".equals(model.getId())){
//			
//			ServletContext servletContext = ServletActionContext.getServletContext();  
//			
//			//获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
//			String path = servletContext.getRealPath("/");  
//			
//			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
//			response.setContentType("multipart/form-data");  
//			//2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
//			response.setHeader("Content-Disposition", "attachment;fileName="+"aaaa.txt");  
//			ServletOutputStream out;  
//			//通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
//			File file = new File(path + "download/" + "aaaa.txt");  
//			
//			try {  
//			    FileInputStream inputStream = new FileInputStream(file);  
//			
//			    //3.通过response获取ServletOutputStream对象(out)  
//			    out = response.getOutputStream();  
//			
//			    int b = 0;  
//			    byte[] buffer = new byte[512];  
//			    while (b != -1){  
//			        b = inputStream.read(buffer);  
//			        //4.写到输出流(out)中  
//			        out.write(buffer,0,b); 
//			    }  
//			    inputStream.close();  
//			    out.close();  
//			    out.flush();  
//			
//			} catch (IOException e) {  
//			    e.printStackTrace();  
//			}  
//
//		}
//	}

	/**
	 * 数据检查（非空，长度），false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean dataCheck(TblContractAttachment obj){
		boolean flag = true;
		if(!StringUtil.checkStingAndLength(obj.getContractCode(), 50)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getAttachmentName(), 200)){
			flag = false;
		}else if(null != obj.getRemark() && obj.getRemark().getBytes().length>200){
			flag = false;
		}
		return flag;
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

	
}
