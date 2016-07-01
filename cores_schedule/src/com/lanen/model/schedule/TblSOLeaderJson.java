package com.lanen.model.schedule;

import java.io.Serializable;

public class TblSOLeaderJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9137182875857816610L;
	private String id;
	private String studyNo;//专题编号
	private String taskName;//
	private String soleader;//负责人
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String signId;//签字ID
	private String finsh;//判断是否有结束日期
	private int taskKind;
	private String _parentId;//父级Id
	private String iconCls;
	private String state;
	
	private String privilege;//操作权限 1  有 0 无
	
	
	
	
	public int getTaskKind() {
		return taskKind;
	}
	public void setTaskKind(int taskKind) {
		this.taskKind = taskKind;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
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
	public String getSoleader() {
		return soleader;
	}
	public void setSoleader(String soleader) {
		this.soleader = soleader;
	}

	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
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
	public String getFinsh() {
		return finsh;
	}
	public void setFinsh(String finsh) {
		this.finsh = finsh;
	}
	
	
	
	
}
