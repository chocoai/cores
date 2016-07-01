package com.lanen.view.action.qa;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.TblStudyFile;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.qa.TblStudyFileService;
import com.lanen.util.FileOperateUtil;

@Controller
@Scope("prototype")
public class TblStudyFileAction extends BaseAction<TblStudyFile>{

	private static final long serialVersionUID = -9130038973233694336L;
	@Resource
	private TblStudyFileService tblStudyFileService;
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	
	private File attachmentfile;//页面传过来的file文件。
	private String studyNo;
	private Integer fileType;
	
	private InputStream fileInput;
	private String downFileName;
	
	/**文件上传*/
	public void importFile(){
		
		byte[] attachmentFileByte = FileOperateUtil.getInstance().getBytes(attachmentfile);
		
			TblStudyFile studyFile = new TblStudyFile();
			String key = tblStudyFileService.getKey("TblStudyFile");
			studyFile.setId(key);
			studyFile.setAttachmentDesc(model.getAttachmentDesc());
			studyFile.setAttachmentFile(attachmentFileByte);
			
			studyFile.setAttachmentName(model.getAttachmentName());
			
			studyFile.setSubmitTime(new Date());
			//studyNo和fileType（1方案，2报告）决定唯一的一个专题文件索引
			TblStudyFileIndex index = tblStudyFileIndexService.getByStudyNoAndFileType(studyNo,fileType);
			if(index!=null)
			{
				studyFile.setTblStudyFileIndex(index);				
			}else {
				index = new TblStudyFileIndex();
				index.setChangeFlag(0);//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
				index.setCurVersion(1);
				index.setFileState(0);//0：草稿；1：提交审批中；2：结束
				index.setFileType(fileType);//1方案 2报告
				String indexKey = tblStudyFileIndexService.getKey("TblStudyFileIndex");
				index.setStudyFileIndexId(indexKey);
				index.setStudyNo(studyNo);
				tblStudyFileIndexService.save(index);
							
				studyFile.setTblStudyFileIndex(index);
			}
			studyFile.setFileVersion(index.getCurVersion());
			
			try{
				  tblStudyFileService.save(studyFile);
				 /* map.put("success",true);
			
				  map.put("id", studyFile.getId());
				  map.put("attachmentName", studyFile.getAttachmentName());
				  */
				  writeJson("true");
			}catch(Exception e){
				// map.put("success",false);
				 System.out.println("执行失败，出错种类"+e.getMessage()+".");
				 writeJson("false");
			}	
		

		//String jsonStr = JsonPluginsUtil.beanToJson(map);
		//writeJson(jsonStr);
	}
	
	public String downloadAttachment() throws Exception{  
		if(null != model.getId() && !"".equals(model.getId())){
			TblStudyFile attchment = tblStudyFileService.getById(model.getId());
			/*ByteArrayInputStream testInput = new ByteArrayInputStream(attchment.getFile());
			byte[] bs=new byte[1024];
			int i=0;
			while((i=testInput.read(bs))!=-1)
			{
				System.out.println(new String(bs,0,i));
			}*/
			
			if(null != attchment){
				fileInput = new ByteArrayInputStream(attchment.getAttachmentFile());
				downFileName = attchment.getAttachmentName();
				downFileName= java.net.URLEncoder.encode(downFileName,request.getCharacterEncoding());
				downFileName = new String(downFileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
			
			}
		}

	    return "download";  
	} 
	
	/**删除附件*/
	public void del() throws Exception{
		Json json = new Json();
		//TblStudyFile file = tblStudyFileService.getById(model.getId());
		//file.setDelFlag(1);//1删除，0有效
		//file.setDelTime(new Date());
		
		tblStudyFileService.delete(model.getId());
		
		json.setSuccess(true);
	
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}

	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
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

	public File getAttachmentfile() {
		return attachmentfile;
	}

	public void setAttachmentfile(File attachmentfile) {
		this.attachmentfile = attachmentfile;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	

}
