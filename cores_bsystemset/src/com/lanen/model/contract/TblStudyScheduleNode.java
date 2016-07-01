package com.lanen.model.contract;

import java.io.Serializable;

/**
 * 项目进度节点设置
 * @author Administrator
 *
 */
public class TblStudyScheduleNode implements Serializable {

	private static final long serialVersionUID = 6924314780947002252L;
	
	private String id;				// ID
	
	private String studyTypeCode;	// 项目编码
	
	private int nodeSn;				// 节点序号
	
	private String nodeName;		// 节点名称
	
	private String planDays;		// 计划时间点
	
	private int defaultNode;		//是否是默认节点     0：否       	1：是
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudyTypeCode() {
		return studyTypeCode;
	}
	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
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
	public String getPlanDays() {
		return planDays;
	}
	public void setPlanDays(String planDays) {
		this.planDays = planDays;
	}
	public int getDefaultNode() {
		return defaultNode;
	}
	public void setDefaultNode(int defaultNode) {
		this.defaultNode = defaultNode;
	}

}
