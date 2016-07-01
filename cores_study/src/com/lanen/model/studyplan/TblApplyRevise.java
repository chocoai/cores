package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

public class TblApplyRevise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134129417716583876L;
	
	private String id;//主键
	
	private String applyUser;//申请人
	
	private Date applyDate;//申请时间
	
	private String approvalUser;//批准人
	
	private Date approvalDate;//批准时间
	
	private Date doseEffectiveDate; //剂量生效日期
	
	private String reason;//原因
	
	private int applyFlag;//是否允许

	private Date submitDate;//提交时间

	private String studyNo;//课题编号
	
	private Integer type;//课题类型，0试验计划，1日程,2两者
	
	private String refusalReason;//拒绝原因
	
	private Integer version;            //版本号
	
	
	
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRefusalReason() {
		return refusalReason;
	}

	public void setRefusalReason(String refusalReason) {
		this.refusalReason = refusalReason;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(int applyFlag) {
		this.applyFlag = applyFlag;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDoseEffectiveDate() {
		return doseEffectiveDate;
	}

	public void setDoseEffectiveDate(Date doseEffectiveDate) {
		this.doseEffectiveDate = doseEffectiveDate;
	}
	
	
	

}
