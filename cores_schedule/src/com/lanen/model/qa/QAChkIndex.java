package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QachkIndex entity. @author MyEclipse Persistence Tools
 */

public class QAChkIndex  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6927191263323467808L;
	private String chkIndexId;
     private QAChkReport qachkReport;
    private String chkPlanId;
    // private QAChkPlan chkPlan;
     private Integer chkType;
     private String studyNo;
     private String chkItemName;
     private String operator;
     private java.util.Date createTime;
     private Integer chkState;//0:：草稿；1：检查中（启动）2：完成
     private java.util.Date chkFinishTime;
     

    // Constructors

    /** default constructor */
    public QAChkIndex() {
    }

	/** minimal constructor */
    public QAChkIndex(String chkIndexId) {
        this.chkIndexId = chkIndexId;
    }
    
    /** full constructor */
    public QAChkIndex(String chkIndexId, QAChkReport qachkReport, Integer chkType, String studyNo, String chkItemName, String operator, java.util.Date createTime, Integer chkState, java.util.Date chkFinishTime) {
        this.chkIndexId = chkIndexId;
        this.qachkReport = qachkReport;
       // this.chkPlan = chkPlan;
        this.chkType = chkType;
        this.studyNo = studyNo;
        this.chkItemName = chkItemName;
        this.operator = operator;
        this.createTime = createTime;
        this.chkState = chkState;
        this.chkFinishTime = chkFinishTime;
        
    }

   
    // Property accessors

    public String getChkIndexId() {
        return this.chkIndexId;
    }
    
    public void setChkIndexId(String chkIndexId) {
        this.chkIndexId = chkIndexId;
    }

    public QAChkReport getQachkReport() {
        return this.qachkReport;
    }
    
    public void setQachkReport(QAChkReport qachkReport) {
        this.qachkReport = qachkReport;
    }

   

    public Integer getChkType() {
        return this.chkType;
    }
    
    public void setChkType(Integer chkType) {
        this.chkType = chkType;
    }

    public String getStudyNo() {
        return this.studyNo;
    }
    
    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }

    public String getChkItemName() {
        return this.chkItemName;
    }
    
    public void setChkItemName(String chkItemName) {
        this.chkItemName = chkItemName;
    }

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChkState() {
        return this.chkState;
    }
    
    public void setChkState(Integer chkState) {
        this.chkState = chkState;
    }

    public java.util.Date getChkFinishTime() {
        return this.chkFinishTime;
    }
    
    public void setChkFinishTime(java.util.Date chkFinishTime) {
        this.chkFinishTime = chkFinishTime;
    }

	public String getChkPlanId() {
		return chkPlanId;
	}

	public void setChkPlanId(String chkPlanId) {
		this.chkPlanId = chkPlanId;
	}

	

}