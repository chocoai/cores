package com.lanen.model.schedule;

import java.io.Serializable;

public class TblTaskType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845482589800454084L;

	private String id;
	
	private String taskName;//任务名称        
	
	private int taskKind; //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理
	
	private String validFlag;	//有效标志     默认为null    1为无效
	
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
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
