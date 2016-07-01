package com.lanen.model.qa;

import java.util.Date;



/**
 * QachkPlan entity. @author MyEclipse Persistence Tools
 */

public class QAChkPlan  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 8771897014921459746L;
	 private String chkPlanId;
     private QAStudyChkIndex qastudyChkIndex;
     private String scheduleChkItemId;
     private Integer chkPlanType;//1:研究；2：过程；3：设施
     private String chkItemId;
     private String chkItemName;
     private String taskNameId;
     private String taskName;
     private Date scheduleTime;
     private String scheduleName;
     private java.util.Date createTime;
     private java.util.Date planChkTime;
     private String planChkArea;
     private Integer chkFinishedFlag;
     private String planChkOperator;
     private String chkOperator;
     private java.util.Date chkTime;
     private Integer chkPlanVersion;
     private Integer SOPFlag;
     private Integer tempChkOperatorFlag;
     private String tempChkOperator;
     private Date tempChkOperatorApplyTime;
     private Date tempChkOperatorApprovalTime;
     
     
     private QAChkIndex chkIndex;
     
     private String scheduleId;
     private Integer number;//第几次执行

    // Constructors

    /** default constructor */
    public QAChkPlan() {
    }

	/** minimal constructor */
    public QAChkPlan(String chkPlanId) {
        this.chkPlanId = chkPlanId;
    }
    
    /** full constructor */
    public QAChkPlan(String chkPlanId, QAStudyChkIndex qastudyChkIndex, String scheduleChkItemId, Integer chkPlanType, String chkItemId, String chkItemName, String taskNameId, String taskName, Date scheduleTime, String scheduleName, java.util.Date createTime, java.util.Date planChkTime, String planChkArea, Integer chkFinishedFlag, String planChkOperator, String chkOperator, java.util.Date chkTime, Integer chkPlanVersion) {
        this.chkPlanId = chkPlanId;
        this.qastudyChkIndex = qastudyChkIndex;
        this.scheduleChkItemId = scheduleChkItemId;
        this.chkPlanType = chkPlanType;
        this.chkItemId = chkItemId;
        this.chkItemName = chkItemName;
        this.taskNameId = taskNameId;
        this.taskName = taskName;
        this.scheduleTime = scheduleTime;
        this.scheduleName = scheduleName;
        this.createTime = createTime;
        this.planChkTime = planChkTime;
        this.planChkArea = planChkArea;
        this.chkFinishedFlag = chkFinishedFlag;
        this.planChkOperator = planChkOperator;
        this.chkOperator = chkOperator;
        this.chkTime = chkTime;
        this.chkPlanVersion = chkPlanVersion;
    }

   
    // Property accessors

    public String getChkPlanId() {
        return this.chkPlanId;
    }
    
    public void setChkPlanId(String chkPlanId) {
        this.chkPlanId = chkPlanId;
    }

    public QAStudyChkIndex getQastudyChkIndex() {
        return this.qastudyChkIndex;
    }
    
    public void setQastudyChkIndex(QAStudyChkIndex qastudyChkIndex) {
        this.qastudyChkIndex = qastudyChkIndex;
    }

    

    public Integer getChkPlanType() {
        return this.chkPlanType;
    }
    
    public void setChkPlanType(Integer chkPlanType) {
        this.chkPlanType = chkPlanType;
    }

    public String getChkItemId() {
        return this.chkItemId;
    }
    
    public void setChkItemId(String chkItemId) {
        this.chkItemId = chkItemId;
    }

    public String getChkItemName() {
        return this.chkItemName;
    }
    
    public void setChkItemName(String chkItemName) {
        this.chkItemName = chkItemName;
    }

    public String getTaskNameId() {
        return this.taskNameId;
    }
    
    public void setTaskNameId(String taskNameId) {
        this.taskNameId = taskNameId;
    }

    public String getTaskName() {
        return this.taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getScheduleName() {
        return this.scheduleName;
    }
    
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getPlanChkTime() {
        return this.planChkTime;
    }
    
    public void setPlanChkTime(java.util.Date planChkTime) {
        this.planChkTime = planChkTime;
    }

    public String getPlanChkArea() {
        return this.planChkArea;
    }
    
    public void setPlanChkArea(String planChkArea) {
        this.planChkArea = planChkArea;
    }

    public Integer getChkFinishedFlag() {
        return this.chkFinishedFlag;
    }
    
    public void setChkFinishedFlag(Integer chkFinishedFlag) {
        this.chkFinishedFlag = chkFinishedFlag;
    }

    public String getPlanChkOperator() {
        return this.planChkOperator;
    }
    
    public void setPlanChkOperator(String planChkOperator) {
        this.planChkOperator = planChkOperator;
    }

    public String getChkOperator() {
        return this.chkOperator;
    }
    
    public void setChkOperator(String chkOperator) {
        this.chkOperator = chkOperator;
    }

    public java.util.Date getChkTime() {
        return this.chkTime;
    }
    
    public void setChkTime(java.util.Date chkTime) {
        this.chkTime = chkTime;
    }

    public Integer getChkPlanVersion() {
        return this.chkPlanVersion;
    }
    
    public void setChkPlanVersion(Integer chkPlanVersion) {
        this.chkPlanVersion = chkPlanVersion;
    }

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public QAChkIndex getChkIndex() {
		return chkIndex;
	}

	public void setChkIndex(QAChkIndex chkIndex) {
		this.chkIndex = chkIndex;
	}

	public String getScheduleChkItemId() {
		return scheduleChkItemId;
	}

	public void setScheduleChkItemId(String scheduleChkItemId) {
		this.scheduleChkItemId = scheduleChkItemId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSOPFlag() {
		return SOPFlag;
	}

	public void setSOPFlag(Integer sOPFlag) {
		SOPFlag = sOPFlag;
	}

	public Integer getTempChkOperatorFlag() {
		return tempChkOperatorFlag;
	}

	public void setTempChkOperatorFlag(Integer tempChkOperatorFlag) {
		this.tempChkOperatorFlag = tempChkOperatorFlag;
	}

	public String getTempChkOperator() {
		return tempChkOperator;
	}

	public void setTempChkOperator(String tempChkOperator) {
		this.tempChkOperator = tempChkOperator;
	}

	public Date getTempChkOperatorApplyTime() {
		return tempChkOperatorApplyTime;
	}

	public void setTempChkOperatorApplyTime(Date tempChkOperatorApplyTime) {
		this.tempChkOperatorApplyTime = tempChkOperatorApplyTime;
	}

	public Date getTempChkOperatorApprovalTime() {
		return tempChkOperatorApprovalTime;
	}

	public void setTempChkOperatorApprovalTime(Date tempChkOperatorApprovalTime) {
		this.tempChkOperatorApprovalTime = tempChkOperatorApprovalTime;
	}
   








}