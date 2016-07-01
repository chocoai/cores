package com.lanen.model.qa;

import java.util.Date;


/**
 * QalearnTaskFile entity. @author MyEclipse Persistence Tools
 */

public class QALearnTaskFile  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 37068857911673379L;
	 private String learnTaskFileId;
     private QALearnTask qalearnTask;
     private String fileRegId;
     
     //private QAFileType qafileType;
     private Integer fileType;//1：法规；2：指导原则；3：SOP
     private String fileTypeId;
     private String fileTypeName;
     private String fileCode;
     private String fileName;
     private String fileVersion;
     private Date filePublishTime;
     private String filePublishDepartment;
     private String remark;
     private Integer isVersionUpdate;
    // Constructors

    /** default constructor */
    public QALearnTaskFile() {
    }

	/** minimal constructor */
    public QALearnTaskFile(String learnTaskFileId) {
        this.learnTaskFileId = learnTaskFileId;
    }
    
    /** full constructor */
    public QALearnTaskFile(String learnTaskFileId, QALearnTask qalearnTask, String fileRegId) {
        this.learnTaskFileId = learnTaskFileId;
        this.qalearnTask = qalearnTask;
        this.fileRegId = fileRegId;
    }

   
    // Property accessors

    public String getLearnTaskFileId() {
        return this.learnTaskFileId;
    }
    
    public void setLearnTaskFileId(String learnTaskFileId) {
        this.learnTaskFileId = learnTaskFileId;
    }

    public QALearnTask getQalearnTask() {
        return this.qalearnTask;
    }
    
    public void setQalearnTask(QALearnTask qalearnTask) {
        this.qalearnTask = qalearnTask;
    }

    public String getFileRegId() {
        return this.fileRegId;
    }
    
    public void setFileRegId(String fileRegId) {
        this.fileRegId = fileRegId;
    }

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(String fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public Date getFilePublishTime() {
		return filePublishTime;
	}

	public void setFilePublishTime(Date filePublishTime) {
		this.filePublishTime = filePublishTime;
	}

	public String getFilePublishDepartment() {
		return filePublishDepartment;
	}

	public void setFilePublishDepartment(String filePublishDepartment) {
		this.filePublishDepartment = filePublishDepartment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsVersionUpdate() {
		return isVersionUpdate;
	}

	public void setIsVersionUpdate(Integer isVersionUpdate) {
		this.isVersionUpdate = isVersionUpdate;
	}
   








}