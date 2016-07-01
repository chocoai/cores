package com.lanen.model.qa;

import java.util.Date;



/**
 * QachkSop entity. @author MyEclipse Persistence Tools
 */

public class QAChkSop  implements java.io.Serializable {


    // Fields    

     private String sopRecordId;
     private QAChkIndex qachkIndex;
     private String fileRecordId;
     private String sopCode;
     private String sopName;
     private String sopVersion;
     private Date sopPublishTime;
     private String sopPublishDepartment;
     private String remark;


    // Constructors

    /** default constructor */
    public QAChkSop() {
    }

	/** minimal constructor */
    public QAChkSop(String sopRecordId) {
        this.sopRecordId = sopRecordId;
    }
    
    /** full constructor */
    public QAChkSop(String sopRecordId, QAChkIndex qachkIndex, String fileRecordId, String sopCode, String sopName, String sopVersion, Date sopPublishTime, String sopPublishDepartment, String remark) {
        this.sopRecordId = sopRecordId;
        this.qachkIndex = qachkIndex;
        this.fileRecordId = fileRecordId;
        this.sopCode = sopCode;
        this.sopName = sopName;
        this.sopVersion = sopVersion;
        this.sopPublishTime = sopPublishTime;
        this.sopPublishDepartment = sopPublishDepartment;
        this.remark = remark;
    }

   
    // Property accessors

    public String getSopRecordId() {
        return this.sopRecordId;
    }
    
    public void setSopRecordId(String sopRecordId) {
        this.sopRecordId = sopRecordId;
    }

    public QAChkIndex getQachkIndex() {
        return this.qachkIndex;
    }
    
    public void setQachkIndex(QAChkIndex qachkIndex) {
        this.qachkIndex = qachkIndex;
    }

    public String getFileRecordId() {
        return this.fileRecordId;
    }
    
    public void setFileRecordId(String fileRecordId) {
        this.fileRecordId = fileRecordId;
    }

    public String getSopCode() {
        return this.sopCode;
    }
    
    public void setSopCode(String sopCode) {
        this.sopCode = sopCode;
    }

    public String getSopName() {
        return this.sopName;
    }
    
    public void setSopName(String sopName) {
        this.sopName = sopName;
    }

    public String getSopVersion() {
        return this.sopVersion;
    }
    
    public void setSopVersion(String sopVersion) {
        this.sopVersion = sopVersion;
    }

    public Date getSopPublishTime() {
        return this.sopPublishTime;
    }
    
    public void setSopPublishTime(Date sopPublishTime) {
        this.sopPublishTime = sopPublishTime;
    }

    public String getSopPublishDepartment() {
        return this.sopPublishDepartment;
    }
    
    public void setSopPublishDepartment(String sopPublishDepartment) {
        this.sopPublishDepartment = sopPublishDepartment;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}