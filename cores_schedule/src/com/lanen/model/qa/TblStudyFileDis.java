package com.lanen.model.qa;



/**
 * TblStudyFileDis entity. @author MyEclipse Persistence Tools
 */

public class TblStudyFileDis  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7127579024287310607L;
	private String disId;
     private TblStudyFileIndex tblStudyFileIndex;
     private String studyNo;
     private java.util.Date disTime;
     private String reader;
     private Integer readFlag;
     private Integer revokeFlag;
     private java.util.Date revokeTime;
     private String remark;


    // Constructors

    /** default constructor */
    public TblStudyFileDis() {
    }

	/** minimal constructor */
    public TblStudyFileDis(String disId) {
        this.disId = disId;
    }
    
    /** full constructor */
    public TblStudyFileDis(String disId, TblStudyFileIndex tblStudyFileIndex, String studyNo, java.util.Date disTime, String reader, Integer readFlag, Integer revokeFlag, java.util.Date revokeTime, String remark) {
        this.disId = disId;
        this.tblStudyFileIndex = tblStudyFileIndex;
        this.studyNo = studyNo;
        this.disTime = disTime;
        this.reader = reader;
        this.readFlag = readFlag;
        this.revokeFlag = revokeFlag;
        this.revokeTime = revokeTime;
        this.remark = remark;
    }

   
    // Property accessors

    public String getDisId() {
        return this.disId;
    }
    
    public void setDisId(String disId) {
        this.disId = disId;
    }

    public TblStudyFileIndex getTblStudyFileIndex() {
        return this.tblStudyFileIndex;
    }
    
    public void setTblStudyFileIndex(TblStudyFileIndex tblStudyFileIndex) {
        this.tblStudyFileIndex = tblStudyFileIndex;
    }

    public String getStudyNo() {
        return this.studyNo;
    }
    
    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public java.util.Date getDisTime() {
        return this.disTime;
    }
    
    public void setDisTime(java.util.Date disTime) {
        this.disTime = disTime;
    }

    public String getReader() {
        return this.reader;
    }
    
    public void setReader(String reader) {
        this.reader = reader;
    }

    public Integer getReadFlag() {
        return this.readFlag;
    }
    
    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    public Integer getRevokeFlag() {
        return this.revokeFlag;
    }
    
    public void setRevokeFlag(Integer revokeFlag) {
        this.revokeFlag = revokeFlag;
    }

    public java.util.Date getRevokeTime() {
        return this.revokeTime;
    }
    
    public void setRevokeTime(java.util.Date revokeTime) {
        this.revokeTime = revokeTime;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}