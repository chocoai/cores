package com.lanen.model.path;

import java.io.Serializable;

public class TblAnatomyReqAnimalList_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092760543498098903L;
	private String id;        //编号
	private String studyNo;    //课题编号
	private int anatomyReqNo;  //申请编号
	private String animalCode; //动物编号
	private int gender;        //动物性别
	private int groupID;       //动物组别
	private int isAnatomyReq;     //检查该动物是否已被申请解剖
	private String addOrEdit;    //添加或编辑（查看）按钮
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
	public void setIsAnatomyReq(int isAnatomyReq) {
		this.isAnatomyReq = isAnatomyReq;
	}
	public int getIsAnatomyReq() {
		return isAnatomyReq;
	}
	public void setAddOrEdit(String addOrEdit) {
		this.addOrEdit = addOrEdit;
	}
	public String getAddOrEdit() {
		return addOrEdit;
	}
}
