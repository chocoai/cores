package com.lanen.model.studyplan;

import java.io.Serializable;

/**
 * 剂量设置
 * @author Administrator
 *
 */
public class TblDoseSetting   implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4868081828336991915L;
	private String id;                     //    主键，自增长
	private TblStudyPlan tblStudyPlan ;  //试验计划，     类
	private int dosageNum;               //剂量组编号
	private String dosageDesc;           //剂量组说明
	private String dosage;               //剂量
	private int maleNum;                 //雄性数量
	private int femaleNum;               //雌性数量
	
	private String femaleDosage;		//雌性给药剂量
	private String maleVolume;			//雄性给药容积
	private String femaleVolume;		//雌性给药容积
	private String maleThickness;		//雄性给药浓度
	private String femaleThickness;		//雌性给药浓度
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TblStudyPlan getTblStudyPlan() {
		return tblStudyPlan;
	}
	public void setTblStudyPlan(TblStudyPlan tblStudyPlan) {
		this.tblStudyPlan = tblStudyPlan;
	}
	public int getDosageNum() {
		return dosageNum;
	}
	public void setDosageNum(int dosageNum) {
		this.dosageNum = dosageNum;
	}
	public String getDosageDesc() {
		return dosageDesc;
	}
	public void setDosageDesc(String dosageDesc) {
		this.dosageDesc = dosageDesc;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public int getMaleNum() {
		return maleNum;
	}
	public void setMaleNum(int maleNum) {
		this.maleNum = maleNum;
	}
	public int getFemaleNum() {
		return femaleNum;
	}
	public void setFemaleNum(int femaleNum) {
		this.femaleNum = femaleNum;
	}
	public String getFemaleDosage() {
		return femaleDosage;
	}
	public void setFemaleDosage(String femaleDosage) {
		this.femaleDosage = femaleDosage;
	}
	public String getMaleVolume() {
		return maleVolume;
	}
	public void setMaleVolume(String maleVolume) {
		this.maleVolume = maleVolume;
	}
	public String getFemaleVolume() {
		return femaleVolume;
	}
	public void setFemaleVolume(String femaleVolume) {
		this.femaleVolume = femaleVolume;
	}
	public String getMaleThickness() {
		return maleThickness;
	}
	public void setMaleThickness(String maleThickness) {
		this.maleThickness = maleThickness;
	}
	public String getFemaleThickness() {
		return femaleThickness;
	}
	public void setFemaleThickness(String femaleThickness) {
		this.femaleThickness = femaleThickness;
	}
	
}
