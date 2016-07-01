package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

public class TblAnimalDetailDissectPlanHis implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6782463191763625486L;
	private String id;     //数据Id
	private String animalCode;//动物编号
	private int gender;    //性别   
	private int dissectNum;    //解剖次数
	private int groupId;       //组号
	private String oldID;               //源ID
	private String tblApplyReviseID;    //申请表id
	private String operate;             // 操作
	private Date operateDate;           //修改时间
	private String  studyNo;            //课题编号
	
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getDissectNum() {
		return dissectNum;
	}
	public void setDissectNum(int dissectNum) {
		this.dissectNum = dissectNum;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getOldID() {
		return oldID;
	}
	public void setOldID(String oldID) {
		this.oldID = oldID;
	}
	public String getTblApplyReviseID() {
		return tblApplyReviseID;
	}
	public void setTblApplyReviseID(String tblApplyReviseID) {
		this.tblApplyReviseID = tblApplyReviseID;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	

}
