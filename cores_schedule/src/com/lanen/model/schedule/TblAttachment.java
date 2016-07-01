package com.lanen.model.schedule;

import java.util.Date;

/**
 * 附件
 * @author Administrator
 *
 */
public class TblAttachment implements java.io.Serializable {

	private static final long serialVersionUID = 7297310550101793303L;
	
	private String id;			//
	private String indexId;		//外键(tblAttachmentIndex 表主键)
	private String fileUrl;		//文件路径200 
	private String fileName;	//文件名(上传时)200
	private String realFileName;//文件名(保存时)200
	private int printNum;		//要求打印份数

	private int state;			//0:未打印  1:已打印
	private Date finishTime;	//打印时间
	private String printer;		//打印者(用户名)
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}
	public int getPrintNum() {
		return printNum;
	}
	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}

}
