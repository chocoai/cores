package com.lanen.model.path;

import java.io.Serializable;

/**
 * 申请解剖动物列表_历史
 * @author 曾锋
 */
public class TblAnatomyReqAnimalListHis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5914981674533869226L;
	private String id;        //编号
	private String studyNo;    //课题编号
	private int anatomyReqNo;  //申请编号
	private String animalCode; //动物编号
	private int gender;        //动物性别
	private int groupID;       //动物组别
	
	private int cancelFlag ;	//排除标记（解剖前已死亡）  0:否    1：是
	private String reason ;   	//排除原因
	private String signId;		//签字Id
	
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
	public int getAnatomyReqNo() {
		return anatomyReqNo;
	}
	public void setAnatomyReqNo(int anatomyReqNo) {
		this.anatomyReqNo = anatomyReqNo;
	}
	public String getAnimalCode() {
		return animalCode;
	}
	public void setAnimalCode(String animalCode) {
		this.animalCode = animalCode;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	
	
}
