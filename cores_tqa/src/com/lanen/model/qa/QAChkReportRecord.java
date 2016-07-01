package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QachkReportRecord entity. @author MyEclipse Persistence Tools
 */

public class QAChkReportRecord  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2799287804630920390L;
	private String chkReportRecordId;
     private QAChkReport qachkReport;
     private String studyNo;
     private String chkRecordId;
     private String chkItemName;
     private String chkContent;
     private String chkResult;
     
     private String chkResultDesc;
     
     private String remark;
     private java.util.Date chkTime;
     private String inspector;
     private String advice;
     private java.util.Date confirmTime;
     private String confirmer;
     private Integer chkResultFlag;
     private String replyContent;
     private String replyRemark;
     private Integer reChkFlag;
     private Integer reChkResult;
     private String reChkInspector;
     private java.util.Date reChkTime;
     private Integer needDelay;
     private String delayDesc;
     private String delayRsn;
     private java.util.Date delayPlanFinishDate;
     private String delaySd;
     private java.util.Date delayFinishTime;
     private java.util.Date delayQaconfirmTime;
     private String delayQainspector;
    

    // Constructors

    /** default constructor */
    public QAChkReportRecord() {
    }

	/** minimal constructor */
    public QAChkReportRecord(String chkReportRecordId) {
        this.chkReportRecordId = chkReportRecordId;
    }
    
    /** full constructor */
    public QAChkReportRecord(String chkReportRecordId, QAChkReport qachkReport, String studyNo, String chkRecordId, String chkItemName, String chkContent, String chkResult, String remark, java.util.Date chkTime, String inspector, String advice, java.util.Date confirmTime, String confirmer, Integer chkResultFlag, String replyContent, String replyRemark, Integer reChkFlag, Integer reChkResult, String reChkInspector, java.util.Date reChkTime, Integer needDelay, String delayDesc, String delayRsn, java.util.Date delayPlanFinishDate, String delaySd, java.util.Date delayFinishTime, java.util.Date delayQaconfirmTime, String delayQainspector) {
        this.chkReportRecordId = chkReportRecordId;
        this.qachkReport = qachkReport;
        this.studyNo = studyNo;
        this.chkRecordId = chkRecordId;
        this.chkItemName = chkItemName;
        this.chkContent = chkContent;
        this.chkResult = chkResult;
        this.remark = remark;
        this.chkTime = chkTime;
        this.inspector = inspector;
        this.advice = advice;
        this.confirmTime = confirmTime;
        this.confirmer = confirmer;
        this.chkResultFlag = chkResultFlag;
        this.replyContent = replyContent;
        this.replyRemark = replyRemark;
        this.reChkFlag = reChkFlag;
        this.reChkResult = reChkResult;
        this.reChkInspector = reChkInspector;
        this.reChkTime = reChkTime;
        this.needDelay = needDelay;
        this.delayDesc = delayDesc;
        this.delayRsn = delayRsn;
        this.delayPlanFinishDate = delayPlanFinishDate;
        this.delaySd = delaySd;
        this.delayFinishTime = delayFinishTime;
        this.delayQaconfirmTime = delayQaconfirmTime;
        this.delayQainspector = delayQainspector;
    }

   
    // Property accessors

    public String getChkReportRecordId() {
        return this.chkReportRecordId;
    }
    
    public void setChkReportRecordId(String chkReportRecordId) {
        this.chkReportRecordId = chkReportRecordId;
    }

    public QAChkReport getQachkReport() {
        return this.qachkReport;
    }
    
    public void setQachkReport(QAChkReport qachkReport) {
        this.qachkReport = qachkReport;
    }

    public String getStudyNo() {
        return this.studyNo;
    }
    
    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public String getChkRecordId() {
        return this.chkRecordId;
    }
    
    public void setChkRecordId(String chkRecordId) {
        this.chkRecordId = chkRecordId;
    }

    public String getChkItemName() {
        return this.chkItemName;
    }
    
    public void setChkItemName(String chkItemName) {
        this.chkItemName = chkItemName;
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

    public java.util.Date getConfirmTime() {
        return this.confirmTime;
    }
    
    public void setConfirmTime(java.util.Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmer() {
        return this.confirmer;
    }
    
    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }

    public Integer getChkResultFlag() {
        return this.chkResultFlag;
    }
    
    public void setChkResultFlag(Integer chkResultFlag) {
        this.chkResultFlag = chkResultFlag;
    }

    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyRemark() {
        return this.replyRemark;
    }
    
    public void setReplyRemark(String replyRemark) {
        this.replyRemark = replyRemark;
    }

    public Integer getReChkFlag() {
        return this.reChkFlag;
    }
    
    public void setReChkFlag(Integer reChkFlag) {
        this.reChkFlag = reChkFlag;
    }

    public Integer getReChkResult() {
        return this.reChkResult;
    }
    
    public void setReChkResult(Integer reChkResult) {
        this.reChkResult = reChkResult;
    }

    public String getReChkInspector() {
        return this.reChkInspector;
    }
    
    public void setReChkInspector(String reChkInspector) {
        this.reChkInspector = reChkInspector;
    }

    public java.util.Date getReChkTime() {
        return this.reChkTime;
    }
    
    public void setReChkTime(java.util.Date reChkTime) {
        this.reChkTime = reChkTime;
    }

    public Integer getNeedDelay() {
        return this.needDelay;
    }
    
    public void setNeedDelay(Integer needDelay) {
        this.needDelay = needDelay;
    }

    public String getDelayDesc() {
        return this.delayDesc;
    }
    
    public void setDelayDesc(String delayDesc) {
        this.delayDesc = delayDesc;
    }

    public String getDelayRsn() {
        return this.delayRsn;
    }
    
    public void setDelayRsn(String delayRsn) {
        this.delayRsn = delayRsn;
    }

    public java.util.Date getDelayPlanFinishDate() {
        return this.delayPlanFinishDate;
    }
    
    public void setDelayPlanFinishDate(java.util.Date delayPlanFinishDate) {
        this.delayPlanFinishDate = delayPlanFinishDate;
    }

    public String getDelaySd() {
        return this.delaySd;
    }
    
    public void setDelaySd(String delaySd) {
        this.delaySd = delaySd;
    }

    public java.util.Date getDelayFinishTime() {
        return this.delayFinishTime;
    }
    
    public void setDelayFinishTime(java.util.Date delayFinishTime) {
        this.delayFinishTime = delayFinishTime;
    }

    public java.util.Date getDelayQaconfirmTime() {
        return this.delayQaconfirmTime;
    }
    
    public void setDelayQaconfirmTime(java.util.Date delayQaconfirmTime) {
        this.delayQaconfirmTime = delayQaconfirmTime;
    }

    public String getDelayQainspector() {
        return this.delayQainspector;
    }
    
    public void setDelayQainspector(String delayQainspector) {
        this.delayQainspector = delayQainspector;
    }

	public String getChkResultDesc() {
		return chkResultDesc;
	}

	public void setChkResultDesc(String chkResultDesc) {
		this.chkResultDesc = chkResultDesc;
	}

   
	
}