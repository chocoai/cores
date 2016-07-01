package com.lanen.model.schedule;

import java.io.Serializable;
import java.util.Date;

public class TblTaskLeaderJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2866951565265240952L;
	
	private String id;
	private String taskTypeID;//系统任务ID
	private String taskLeader;//负责人
	private Date startDate;
	private Date endDate;
	private String signId;
	private String iconCls;
	private String state;
	private String _parentId;
	private String canSee;//可见范围
	private String ttid;
	
	private String startTime;
	private String endTime;
	
	private String taskName;//任务名称
	
	private String taskLeaderId;
	
	private String tasks;//已参加的任务
	
	private String time;//组合成批量查询时所所需要的时间
	
	private String studyNo;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getTasks() {
		return tasks;
	}
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	public String getTaskLeaderId() {
		return taskLeaderId;
	}
	public void setTaskLeaderId(String taskLeaderId) {
		this.taskLeaderId = taskLeaderId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTtid() {
		return ttid;
	}
	public void setTtid(String ttid) {
		this.ttid = ttid;
	}
	public String getCanSee() {
		return canSee;
	}
	public void setCanSee(String canSee) {
		this.canSee = canSee;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private String taskKind; //任务类别
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskTypeID() {
		return taskTypeID;
	}
	public void setTaskTypeID(String taskTypeID) {
		this.taskTypeID = taskTypeID;
	}
	public String getTaskLeader() {
		return taskLeader;
	}
	public void setTaskLeader(String taskLeader) {
		this.taskLeader = taskLeader;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getTaskKind() {
		return taskKind;
	}
	public void setTaskKind(String taskKind) {
		this.taskKind = taskKind;
	}
	
	
	

}
