package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目进度表
 * @author Administrator
 *
 */
public class TblStudySchedule implements Serializable {

	private static final long serialVersionUID = 7155112758733485955L;
	
	private String id;			// ID
	
	private String studyNo;	// 课题编号
	
	private int nodeSn;			// 进度节点序号
	
	private String nodeName;	// 节点名称
	
	private Date planDate;		// 计划时间
	
	private Date actualDate;	// 实际时间
	
	private int confirmFlag;	// 确认标志
	
	private Date confirmTime;	// 确认时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Date getActualDate() {
		return actualDate;
	}
	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
	public int getConfirmFlag() {
		return confirmFlag;
	}
	public void setConfirmFlag(int confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	
}
