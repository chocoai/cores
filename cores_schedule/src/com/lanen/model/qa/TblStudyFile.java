package com.lanen.model.qa;



/**
 * TblStudyFile entity. @author MyEclipse Persistence Tools
 */

public class TblStudyFile  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1301993454864115396L;
	private String id;
     private TblStudyFileIndex tblStudyFileIndex;
     private String attachmentName;
     private String attachmentDesc;
     private byte[] attachmentFile;
     private java.util.Date submitTime;
     private Integer delFlag;
     private java.util.Date delTime;
     private Integer fileVersion;


    // Constructors

    /** default constructor */
    public TblStudyFile() {
    }

	/** minimal constructor */
    public TblStudyFile(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public TblStudyFile(String id, TblStudyFileIndex tblStudyFileIndex, String attachmentName, String attachmentDesc, byte[] attachmentFile, java.util.Date submitTime, Integer delFlag, java.util.Date delTime, Integer fileVersion) {
        this.id = id;
        this.tblStudyFileIndex = tblStudyFileIndex;
        this.attachmentName = attachmentName;
        this.attachmentDesc = attachmentDesc;
        this.attachmentFile = attachmentFile;
        this.submitTime = submitTime;
        this.delFlag = delFlag;
        this.delTime = delTime;
        this.fileVersion = fileVersion;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public TblStudyFileIndex getTblStudyFileIndex() {
        return this.tblStudyFileIndex;
    }
    
    public void setTblStudyFileIndex(TblStudyFileIndex tblStudyFileIndex) {
        this.tblStudyFileIndex = tblStudyFileIndex;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }
    
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentDesc() {
        return this.attachmentDesc;
    }
    
    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    public byte[] getAttachmentFile() {
        return this.attachmentFile;
    }
    
    public void setAttachmentFile(byte[] attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public java.util.Date getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(java.util.Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }
    
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public java.util.Date getDelTime() {
        return this.delTime;
    }
    
    public void setDelTime(java.util.Date delTime) {
        this.delTime = delTime;
    }

    public Integer getFileVersion() {
        return this.fileVersion;
    }
    
    public void setFileVersion(Integer fileVersion) {
        this.fileVersion = fileVersion;
    }
   








}