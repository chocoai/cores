package com.lanen.model.qa;



/**
 * QachkPlanChangeIndex entity. @author MyEclipse Persistence Tools
 */

public class QAChkPlanChangeIndex  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2332409333658845524L;
	private String chkPlanChangeIndexId;
     private QAStudyChkIndex qastudyChkIndex;
     private Integer sn;
     private java.util.Date changeDate;
     private String operator;
     private Integer changeState;//0：原始；1：提交；-1：否决；2：通过,-2:撤销
     private java.util.Date approvalTime;
     private String approver;
     private String reason;

    // Constructors

    /** default constructor */
    public QAChkPlanChangeIndex() {
    }

	/** minimal constructor */
    public QAChkPlanChangeIndex(String chkPlanChangeIndexId) {
        this.chkPlanChangeIndexId = chkPlanChangeIndexId;
    }
    
    /** full constructor */
    public QAChkPlanChangeIndex(String chkPlanChangeIndexId, QAStudyChkIndex qastudyChkIndex, Integer sn, java.util.Date changeDate, String operator, Integer changeState, java.util.Date approvalTime, String approver) {
        this.chkPlanChangeIndexId = chkPlanChangeIndexId;
        this.qastudyChkIndex = qastudyChkIndex;
        this.sn = sn;
        this.changeDate = changeDate;
        this.operator = operator;
        this.changeState = changeState;
        this.approvalTime = approvalTime;
        this.approver = approver;
    }

   
    // Property accessors

    public String getChkPlanChangeIndexId() {
        return this.chkPlanChangeIndexId;
    }
    
    public void setChkPlanChangeIndexId(String chkPlanChangeIndexId) {
        this.chkPlanChangeIndexId = chkPlanChangeIndexId;
    }

    public QAStudyChkIndex getQastudyChkIndex() {
        return this.qastudyChkIndex;
    }
    
    public void setQastudyChkIndex(QAStudyChkIndex qastudyChkIndex) {
        this.qastudyChkIndex = qastudyChkIndex;
    }

    public Integer getSn() {
        return this.sn;
    }
    
    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public java.util.Date getChangeDate() {
        return this.changeDate;
    }
    
    public void setChangeDate(java.util.Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getChangeState() {
        return this.changeState;
    }
    
    public void setChangeState(Integer changeState) {
        this.changeState = changeState;
    }

    public java.util.Date getApprovalTime() {
        return this.approvalTime;
    }
    
    public void setApprovalTime(java.util.Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprover() {
        return this.approver;
    }
    
    public void setApprover(String approver) {
        this.approver = approver;
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
   








}