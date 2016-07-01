package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QastudyChkIndex entity. @author MyEclipse Persistence Tools
 */

public class QAStudyChkIndex  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8403824436270234672L;
	private String studyNo;
     private Integer inspectorAppointState;
     private String sd;
     private String inspector;
     private java.util.Date inspectorAppointTime;
     private Integer studyPlanState;
     private java.util.Date studyPlanTime;
     private Integer studyPlanChangeState;
     private Integer scheduleState;
     private java.util.Date scheduleSubmitTime;
     private Integer reportState;
     private java.util.Date reportFinishTime;
     private Integer chkPlanState;
     private java.util.Date approvalTime;
     private String chkPlanAuthor;
     private java.util.Date chkPlanApprovalTime;
     private String chkPlanApprover;
     private Integer chkPlanFinishFlag;
     private java.util.Date chkPlanFinishTime;
     private String chkPlanFinishConfirmer;
     private Integer chkPlanCurVersion;
     private Integer scheduleChangedFlag;
   


    // Constructors

    /** default constructor */
    public QAStudyChkIndex() {
    }

	/** minimal constructor */
    public QAStudyChkIndex(String studyNo) {
        this.studyNo = studyNo;
    }
    
    /** full constructor */
    public QAStudyChkIndex(String studyNo, Integer inspectorAppointState, String sd, String inspector, java.util.Date inspectorAppointTime, Integer studyPlanState, java.util.Date studyPlanTime, Integer studyPlanChangeState, Integer scheduleState, java.util.Date scheduleSubmitTime, Integer reportState, java.util.Date reportFinishTime, Integer chkPlanState, java.util.Date approvalTime, String chkPlanAuthor, java.util.Date chkPlanApprovalTime, String chkPlanApprover, Integer chkPlanFinishFlag, java.util.Date chkPlanFinishTime, String chkPlanFinishConfirmer, Integer chkPlanCurVersion, Integer scheduleChangedFlag) {
        this.studyNo = studyNo;
        this.inspectorAppointState = inspectorAppointState;
        this.sd = sd;
        this.inspector = inspector;
        this.inspectorAppointTime = inspectorAppointTime;
        this.studyPlanState = studyPlanState;
        this.studyPlanTime = studyPlanTime;
        this.studyPlanChangeState = studyPlanChangeState;
        this.scheduleState = scheduleState;
        this.scheduleSubmitTime = scheduleSubmitTime;
        this.reportState = reportState;
        this.reportFinishTime = reportFinishTime;
        this.chkPlanState = chkPlanState;
        this.approvalTime = approvalTime;
        this.chkPlanAuthor = chkPlanAuthor;
        this.chkPlanApprovalTime = chkPlanApprovalTime;
        this.chkPlanApprover = chkPlanApprover;
        this.chkPlanFinishFlag = chkPlanFinishFlag;
        this.chkPlanFinishTime = chkPlanFinishTime;
        this.chkPlanFinishConfirmer = chkPlanFinishConfirmer;
        this.chkPlanCurVersion = chkPlanCurVersion;
        this.scheduleChangedFlag = scheduleChangedFlag;

    }

   
    // Property accessors

    public String getStudyNo() {
        return this.studyNo;
    }
    
    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public Integer getInspectorAppointState() {
        return this.inspectorAppointState;
    }
    
    public void setInspectorAppointState(Integer inspectorAppointState) {
        this.inspectorAppointState = inspectorAppointState;
    }

    public String getSd() {
        return this.sd;
    }
    
    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getInspector() {
        return this.inspector;
    }
    
    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public java.util.Date getInspectorAppointTime() {
        return this.inspectorAppointTime;
    }
    
    public void setInspectorAppointTime(java.util.Date inspectorAppointTime) {
        this.inspectorAppointTime = inspectorAppointTime;
    }

    public Integer getStudyPlanState() {
        return this.studyPlanState;
    }
    
    public void setStudyPlanState(Integer studyPlanState) {
        this.studyPlanState = studyPlanState;
    }

    public java.util.Date getStudyPlanTime() {
        return this.studyPlanTime;
    }
    
    public void setStudyPlanTime(java.util.Date studyPlanTime) {
        this.studyPlanTime = studyPlanTime;
    }

    public Integer getStudyPlanChangeState() {
        return this.studyPlanChangeState;
    }
    
    public void setStudyPlanChangeState(Integer studyPlanChangeState) {
        this.studyPlanChangeState = studyPlanChangeState;
    }

    public Integer getScheduleState() {
        return this.scheduleState;
    }
    
    public void setScheduleState(Integer scheduleState) {
        this.scheduleState = scheduleState;
    }

    public java.util.Date getScheduleSubmitTime() {
        return this.scheduleSubmitTime;
    }
    
    public void setScheduleSubmitTime(java.util.Date scheduleSubmitTime) {
        this.scheduleSubmitTime = scheduleSubmitTime;
    }

    public Integer getReportState() {
        return this.reportState;
    }
    
    public void setReportState(Integer reportState) {
        this.reportState = reportState;
    }

    public java.util.Date getReportFinishTime() {
        return this.reportFinishTime;
    }
    
    public void setReportFinishTime(java.util.Date reportFinishTime) {
        this.reportFinishTime = reportFinishTime;
    }

    public Integer getChkPlanState() {
        return this.chkPlanState;
    }
    
    public void setChkPlanState(Integer chkPlanState) {
        this.chkPlanState = chkPlanState;
    }

    public java.util.Date getApprovalTime() {
        return this.approvalTime;
    }
    
    public void setApprovalTime(java.util.Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getChkPlanAuthor() {
        return this.chkPlanAuthor;
    }
    
    public void setChkPlanAuthor(String chkPlanAuthor) {
        this.chkPlanAuthor = chkPlanAuthor;
    }

    public java.util.Date getChkPlanApprovalTime() {
        return this.chkPlanApprovalTime;
    }
    
    public void setChkPlanApprovalTime(java.util.Date chkPlanApprovalTime) {
        this.chkPlanApprovalTime = chkPlanApprovalTime;
    }

    public String getChkPlanApprover() {
        return this.chkPlanApprover;
    }
    
    public void setChkPlanApprover(String chkPlanApprover) {
        this.chkPlanApprover = chkPlanApprover;
    }

    public Integer getChkPlanFinishFlag() {
        return this.chkPlanFinishFlag;
    }
    
    public void setChkPlanFinishFlag(Integer chkPlanFinishFlag) {
        this.chkPlanFinishFlag = chkPlanFinishFlag;
    }

    public java.util.Date getChkPlanFinishTime() {
        return this.chkPlanFinishTime;
    }
    
    public void setChkPlanFinishTime(java.util.Date chkPlanFinishTime) {
        this.chkPlanFinishTime = chkPlanFinishTime;
    }

    public String getChkPlanFinishConfirmer() {
        return this.chkPlanFinishConfirmer;
    }
    
    public void setChkPlanFinishConfirmer(String chkPlanFinishConfirmer) {
        this.chkPlanFinishConfirmer = chkPlanFinishConfirmer;
    }

    public Integer getChkPlanCurVersion() {
        return this.chkPlanCurVersion;
    }
    
    public void setChkPlanCurVersion(Integer chkPlanCurVersion) {
        this.chkPlanCurVersion = chkPlanCurVersion;
    }

    public Integer getScheduleChangedFlag() {
        return this.scheduleChangedFlag;
    }
    
    public void setScheduleChangedFlag(Integer scheduleChangedFlag) {
        this.scheduleChangedFlag = scheduleChangedFlag;
    }

   
  

}