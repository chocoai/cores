package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

/**
 * 试验计划历史表
 * @author Administrator
 *
 */
public class TblStudyPlanHis   implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092534695780167083L;
	private String id;//主键
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String  studyNo;            //课题编号
	private String studyTypeCode;       //课题类别编码
	private String studyName;           //课题名称
	private String studydirector;       //课题负责人
	private int isGLP;                  //是否GLP     0：否       1：是
	private String animalType;          //动物种类
	private String animalStrain;        //动物品系
	private Date studyStartDate;        //试验启动日期
	private Date animalImportDate;      //动物引入日期
	private Date preStudyDate;          //预试验日期
	private Date studyBeginDate;        //正式试验时期
	private String smplCode;            //供试品编号
	private String dosageUnit;          //剂量单位
	private String qa;                  //QA负责人
	private String pathDirector;        //病理负责人
	private String clinicalTestDirector;//临检负责人
	private String studyState;          //试验状态   0 未提交 1 提交 11 申请再编辑 12再编辑
	private int temp;                   //0  ，非临时     1，临时
	private String  client;             //委托单位
	private int isValidation;           //0,否  1，是
	private int animalCodeMode ;        //1:A   2:B
	private String tblApplyReviseID;    //申请表id
	private String operate;             // 操作
	private Date operateDate;           //修改时间

	
	
	public String getTblApplyReviseID() {
		return tblApplyReviseID;
	}
	public void setTblApplyReviseID(String tblApplyReviseID) {
		this.tblApplyReviseID = tblApplyReviseID;
	}
	public String getStudyNo() {
		return studyNo;
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
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	
	public String getStudydirector() {
		return studydirector;
	}
	public void setStudydirector(String studydirector) {
		this.studydirector = studydirector;
	}
	public int getIsGLP() {
		return isGLP;
	}
	public void setIsGLP(int isGLP) {
		this.isGLP = isGLP;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getAnimalStrain() {
		return animalStrain;
	}
	public void setAnimalStrain(String animalStrain) {
		this.animalStrain = animalStrain;
	}
	public Date getStudyStartDate() {
		return studyStartDate;
	}
	public void setStudyStartDate(Date studyStartDate) {
		this.studyStartDate = studyStartDate;
	}
	public Date getAnimalImportDate() {
		return animalImportDate;
	}
	public void setAnimalImportDate(Date animalImportDate) {
		this.animalImportDate = animalImportDate;
	}
	public Date getPreStudyDate() {
		return preStudyDate;
	}
	public void setPreStudyDate(Date preStudyDate) {
		this.preStudyDate = preStudyDate;
	}
	public Date getStudyBeginDate() {
		return studyBeginDate;
	}
	public void setStudyBeginDate(Date studyBeginDate) {
		this.studyBeginDate = studyBeginDate;
	}
	public String getDosageUnit() {
		return dosageUnit;
	}
	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}
	public String getQa() {
		return qa;
	}
	public void setQa(String qa) {
		this.qa = qa;
	}
	public String getPathDirector() {
		return pathDirector;
	}
	public void setPathDirector(String pathDirector) {
		this.pathDirector = pathDirector;
	}
	public String getClinicalTestDirector() {
		return clinicalTestDirector;
	}
	public void setClinicalTestDirector(String clinicalTestDirector) {
		this.clinicalTestDirector = clinicalTestDirector;
	}
	public String getStudyState() {
		return studyState;
	}
	public void setStudyState(String studyState) {
		this.studyState = studyState;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public int getIsValidation() {
		return isValidation;
	}
	public void setIsValidation(int isValidation) {
		this.isValidation = isValidation;
	}
	public String getStudyTypeCode() {
		return studyTypeCode;
	}
	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	public int getAnimalCodeMode() {
		return animalCodeMode;
	}
	public void setAnimalCodeMode(int animalCodeMode) {
		this.animalCodeMode = animalCodeMode;
	}
	public String getSmplCode() {
		return smplCode;
	}
	public void setSmplCode(String smplCode) {
		this.smplCode = smplCode;
	}
	
	
	
}
