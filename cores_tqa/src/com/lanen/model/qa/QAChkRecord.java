package com.lanen.model.qa;



/**
 * QachkRecord entity. @author MyEclipse Persistence Tools
 */

public class QAChkRecord  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6369429777287243502L;
	private String chkRecordId;
     private QAChkTblReg qachkTblReg;//检查登记的表，不是字典表
     private QAChkIndex qachkIndex;
     private String chkTblContentId;//字典表里的
     private Integer sn;
     private String chkContent;
     private String chkResult;
     
     private String chkResultDesc;
     
     private String remark;//没有维护
     private java.util.Date chkTime;
     private String inspector;
     private String advice;
     private Integer chkResultFlag;//1：符合；-1：不符合；0：不适用


    // Constructors

    /** default constructor */
    public QAChkRecord() {
    }

	/** minimal constructor */
    public QAChkRecord(String chkRecordId) {
        this.chkRecordId = chkRecordId;
    }
    
    /** full constructor */
    public QAChkRecord(String chkRecordId, QAChkTblReg qachkTblReg, QAChkIndex qachkIndex, String chkTblContentId, String chkContent, String chkResult, String remark, java.util.Date chkTime, String inspector, String advice, Integer chkResultFlag) {
        this.chkRecordId = chkRecordId;
        this.qachkTblReg = qachkTblReg;
        this.qachkIndex = qachkIndex;
        this.chkTblContentId = chkTblContentId;
        this.chkContent = chkContent;
        this.chkResult = chkResult;
        this.remark = remark;
        this.chkTime = chkTime;
        this.inspector = inspector;
        this.advice = advice;
        this.chkResultFlag = chkResultFlag;
    }

   
    // Property accessors

    public String getChkRecordId() {
        return this.chkRecordId;
    }
    
    public void setChkRecordId(String chkRecordId) {
        this.chkRecordId = chkRecordId;
    }

    public QAChkTblReg getQachkTblReg() {
        return this.qachkTblReg;
    }
    
    public void setQachkTblReg(QAChkTblReg qachkTblReg) {
        this.qachkTblReg = qachkTblReg;
    }

    public QAChkIndex getQachkIndex() {
        return this.qachkIndex;
    }
    
    public void setQachkIndex(QAChkIndex qachkIndex) {
        this.qachkIndex = qachkIndex;
    }

    public String getChkTblContentId() {
        return this.chkTblContentId;
    }
    
    public void setChkTblContentId(String chkTblContentId) {
        this.chkTblContentId = chkTblContentId;
    }

    public String getChkContent() {
        return this.chkContent;
    }
    
    public void setChkContent(String chkContent) {
        this.chkContent = chkContent;
    }

    public String getChkResult() {
        return this.chkResult;
    }
    
    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }

    public String getChkResultDesc() {
		return chkResultDesc;
	}

	public void setChkResultDesc(String chkResultDesc) {
		this.chkResultDesc = chkResultDesc;
	}

	public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public java.util.Date getChkTime() {
        return this.chkTime;
    }
    
    public void setChkTime(java.util.Date chkTime) {
        this.chkTime = chkTime;
    }

    public String getInspector() {
        return this.inspector;
    }
    
    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getAdvice() {
        return this.advice;
    }
    
    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Integer getChkResultFlag() {
        return this.chkResultFlag;
    }
    
    public void setChkResultFlag(Integer chkResultFlag) {
        this.chkResultFlag = chkResultFlag;
    }

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}
   








}