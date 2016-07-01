package com.lanen.model.schedule;

import java.io.Serializable;

public class TblTaskTypeJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845482589800454084L;

	private String id;
	
	private String taskName;//任务名称
	
	private int taskKind; //任务类别

	private String iconCls;
	private String state;
	private String _parentId;
	private String validFlag;	//有效标志
	
	private String canSee;//可见范围
	
	
	
	public String getCanSee() {
		return canSee;
	}

	public void setCanSee(String canSee) {
		this.canSee = canSee;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
	
}
