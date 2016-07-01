package com.lanen.model.qa;

import java.io.File;
import java.util.Date;



/**
 * QafileAttachment entity. @author MyEclipse Persistence Tools
 */

public class QAFileAttachment  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 3142194672957197555L;
	private String attachmentId;
     private QAFileReg qafileReg;
     private String attachmentName;
     private String fileName;
     private String remark;
     private byte[] file;
     private Integer readMinute;
 	private Date appendDate;			// 添加日期
	private String operator;			// 操作者


    // Constructors

    /** default constructor */
    public QAFileAttachment() {
    }

	/** minimal constructor */
    public QAFileAttachment(String attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    /** full constructor */
    public QAFileAttachment(String attachmentId, QAFileReg qafileReg, String attachmentName, String fileName, String remark, byte[] file) {
        this.attachmentId = attachmentId;
        this.qafileReg = qafileReg;
        this.attachmentName = attachmentName;
        this.fileName = fileName;
        this.remark = remark;
        this.file = file;
    }

   
    // Property accessors

    public String getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public QAFileReg getQafileReg() {
        return this.qafileReg;
    }
    
    public void setQafileReg(QAFileReg qafileReg) {
        this.qafileReg = qafileReg;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }
    
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getFile() {
        return this.file;
    }
    
    public void setFile(byte[] file) {
        this.file = file;
    }
   
    public Date getAppendDate() {
		return appendDate;
	}

	public void setAppendDate(Date appendDate) {
		this.appendDate = appendDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getReadMinute() {
		return readMinute;
	}

	public void setReadMinute(Integer readMinute) {
		this.readMinute = readMinute;
	}



}