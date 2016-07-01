package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

/**
 * 动物信息列表(json)
 * @author Administrator
 *
 */
public class TblAnimal_json   implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3302429880319857572L;
	private String id;   
	private String animalId;              //动物ID号
	private String animalCode;            //动物编号
	private int gender;                   //性别
	private String weight;                //体重
	private int dissectBatch;             //计划解剖次数
	private int aniSerialNum;             //动物序号
	private String studyNo;               //试验编号
	
	
	/**
	 * 动物死亡
	 * wan
	 */
	private int deadFlag;                 //死亡标记
	private String deadReason;            //死亡原因
	private Date deadDate;                //死亡日期
	private String showdeadDate;
	private String deadFlagUser;          //死亡标记人

	private String showdissectBatch;             //计划解剖次数
	
	
	
	public String getShowdeadDate() {
		return showdeadDate;
	}
	public void setShowdeadDate(String showdeadDate) {
		this.showdeadDate = showdeadDate;
	}
	public String getShowdissectBatch() {
		return showdissectBatch;
	}
	public void setShowdissectBatch(String showdissectBatch) {
		this.showdissectBatch = showdissectBatch;
	}
	public int getDeadFlag() {
		return deadFlag;
	}
	public void setDeadFlag(int deadFlag) {
		this.deadFlag = deadFlag;
	}
	public String getDeadReason() {
		return deadReason;
	}
	public void setDeadReason(String deadReason) {
		this.deadReason = deadReason;
	}
	public Date getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}
	public String getDeadFlagUser() {
		return deadFlagUser;
	}
	public void setDeadFlagUser(String deadFlagUser) {
		this.deadFlagUser = deadFlagUser;
	}
	public String getAnimalId() {
		return animalId;
	}
	public void setAnimalId(String animalId) {
		this.animalId = animalId;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public int getDissectBatch() {
		return dissectBatch;
	}
	public void setDissectBatch(int dissectBatch) {
		this.dissectBatch = dissectBatch;
	}
	public int getAniSerialNum() {
		return aniSerialNum;
	}
	public void setAniSerialNum(int aniSerialNum) {
		this.aniSerialNum = aniSerialNum;
	}
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
	
	
	
	
	
}
