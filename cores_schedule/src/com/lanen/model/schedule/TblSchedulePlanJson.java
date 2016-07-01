package com.lanen.model.schedule;

import java.io.Serializable;
import java.util.Date;

public class TblSchedulePlanJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 399943130811931618L;
	
	
	private Date dateCol;//日期
	private String  scheduleID;//日程ID
	private String taskCode;//任务识别号
	private String taskName;//任务名称
	private int taskKind;//任务类型（字典）
	private String taskTypeId;//任务类型Id（字典）
	private String tOleader;//任务负责人
	private String resId;//资源ID
	private String taskLeader;//资源和任务负责人
	private String soleader; //课题负责人
	public Date getDateCol() {
		return dateCol;
	}
	public void setDateCol(Date dateCol) {
		this.dateCol = dateCol;
	}
	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getTaskKind() {
		return taskKind;
	}
	public void setTaskKind(int taskKind) {
		this.taskKind = taskKind;
	}
	public String getTaskTypeId() {
		return taskTypeId;
	}
	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	public String gettOleader() {
		return tOleader;
	}
	public void settOleader(String tOleader) {
		this.tOleader = tOleader;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getTaskLeader() {
		return taskLeader;
	}
	public void setTaskLeader(String taskLeader) {
		this.taskLeader = taskLeader;
	}
	public String getSoleader() {
		return soleader;
	}
	public void setSoleader(String soleader) {
		this.soleader = soleader;
	}
	
	
	
	

}
