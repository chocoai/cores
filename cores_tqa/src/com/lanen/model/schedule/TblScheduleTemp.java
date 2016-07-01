package com.lanen.model.schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 日程模板
 * @author Administrator
 *
 */
public class TblScheduleTemp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6734017131863054121L;
	private String  tempID;//模板ID
	private int taskType;//任务类型
	private String taskCode;//任务识别号
	private int codeType;//识别号类型
	private int startDay;//任务开始天数
	private Date startTime;//执行时间开始
	private Date endTime;//执行结束时间
	private int period;//周期
	private int periodUnit;//周期单位
	private int taskEndNum;//结束次数
	private int taskEndDays;//结束天数
	private int taskEndState;//结束状态
	private int taskEndType;//任务结束类型
	private int taskItemType;//任务项目类型
	private String taskName;//任务名称
	private String creater;//创建人
	private Date createDate;//创建日期
	private String remark;//备注
	
	
	//日程预期读者
	private Set<TblScheduleTempReader> tblScheduleTempReader = new HashSet<TblScheduleTempReader>();
	
	
	public Set<TblScheduleTempReader> getTblScheduleTempReader() {
		return tblScheduleTempReader;
	}
	public void setTblScheduleTempReader(
			Set<TblScheduleTempReader> tblScheduleTempReader) {
		this.tblScheduleTempReader = tblScheduleTempReader;
	}
	public String getTempID() {
		return tempID;
	}
	public void setTempID(String tempID) {
		this.tempID = tempID;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public int getCodeType() {
		return codeType;
	}
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	public int getStartDay() {
		return startDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getPeriodUnit() {
		return periodUnit;
	}
	public void setPeriodUnit(int periodUnit) {
		this.periodUnit = periodUnit;
	}
	public int getTaskEndNum() {
		return taskEndNum;
	}
	public void setTaskEndNum(int taskEndNum) {
		this.taskEndNum = taskEndNum;
	}
	public int getTaskEndDays() {
		return taskEndDays;
	}
	public void setTaskEndDays(int taskEndDays) {
		this.taskEndDays = taskEndDays;
	}
	public int getTaskEndState() {
		return taskEndState;
	}
	public void setTaskEndState(int taskEndState) {
		this.taskEndState = taskEndState;
	}
	public int getTaskEndType() {
		return taskEndType;
	}
	public void setTaskEndType(int taskEndType) {
		this.taskEndType = taskEndType;
	}
	public int getTaskItemType() {
		return taskItemType;
	}
	public void setTaskItemType(int taskItemType) {
		this.taskItemType = taskItemType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
