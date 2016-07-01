package com.lanen.model.schedule;

import java.io.Serializable;

public class TblStudyRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7678886336432802938L;
	
	private String id;		//主键ID
	private String studyNo;	//课题编号
	private String resId;	//资源ID
	private String signId;	//签字ID
	private int state;		//状态         0: 未分配  1:已分配  2:已确认3:已审核 先审核再分配 2014-11-28改的
	private String auditId; //审核签字Id
	
	
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
