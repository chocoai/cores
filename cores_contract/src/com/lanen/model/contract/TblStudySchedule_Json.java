package com.lanen.model.contract;

import java.io.Serializable;

public class TblStudySchedule_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5224481964245321329L;
	
    private String id;			// ID
	
	private String studyNo;	// 课题编号
	
	private int nodeSn;			// 进度节点序号
	
	private String nodeName;	// 节点名称
	
	private String planDate;		// 计划时间
	
	private String actualDate;	// 实际时间
	
	private int confirmFlag;	// 确认标志
	
	private String confirmTime;	// 确认时间
	
	private String tiNo;					// 供试品编码
	
	private String tiType;// 供试品类型
	
	private String progress;//进度
	
	private String studyName;				// 项目名称
	
	private String sd; //SD
	
	private String _parentId;		//父级Id
	private String iconCls;	//
	private String state;   //'open','closed'
	
	
	

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

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getTiType() {
		return tiType;
	}

	public void setTiType(String tiType) {
		this.tiType = tiType;
	}

	public String getTiNo() {
		return tiNo;
	}

	public void setTiNo(String tiNo) {
		this.tiNo = tiNo;
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

	public int getNodeSn() {
		return nodeSn;
	}

	public void setNodeSn(int nodeSn) {
		this.nodeSn = nodeSn;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getActualDate() {
		return actualDate;
	}

	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}

	public int getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(int confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}
	
	


}
