package com.lanen.model.schedule;

import java.io.Serializable;

public class TblTOLeaderJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 311981819336132179L;
	
	private String id;//
	private String scheduleId;		//任务（日程）ID
	private String tOLeader;		//负责人
	private String startDate;			//开始日期
	private String endDate;			//结束日期
	private String signId;			//签字Id
	private String taskName;        //任务名称
	private String finsh;
	
	public String getFinsh() {
		return finsh;
	}
	public void setFinsh(String finsh) {
		this.finsh = finsh;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String gettOLeader() {
		return tOLeader;
	}
	public void settOLeader(String tOLeader) {
		this.tOLeader = tOLeader;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
  
}
