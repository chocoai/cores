package com.lanen.model;

import java.util.Date;

import com.lanen.util.DateUtil;

/**
 * TblFileRecordSpecimen entity. @author MyEclipse Persistence Tools
 */

public class TblFileRecordSpecimen implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileIndex tblFileIndex;
	private Integer fileRecordSn;
	private Integer specimenTypeFlag;//1：湿标本；2：蜡块；3：切片
	private String studyNo;
	private Integer studyNoType;//1：专题；2：合同
	private String studyName;
	private String sd;
	private Integer fileNum;
	private String fileNumUnit;
	private String fileOperator;
	private Date fileDate;
	private Date keepDate;
	private Date destoryDate;
	private String destoryRegSign;
	private Date specimenDestoryDate;
	private String specimenDestoryRegSign;
	private Date operateTime;
	private String operator;
	private String remark;
	private String keyWord;
	private Integer delFlag;
	private Date delTime;

	// Constructors

	/** default constructor */
	public TblFileRecordSpecimen() {
	}

	/** minimal constructor */
	public TblFileRecordSpecimen(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	/** full constructor */
	public TblFileRecordSpecimen(String fileRecordId,
			TblFileIndex tblFileIndex, Integer fileRecordSn,
			Integer specimenTypeFlag, String studyNo, Integer studyNoType,
			String studyName, String sd, Integer fileNum, String fileNumUnit,
			String fileOperator, Date fileDate, Date keepDate,
			Date destoryDate, String destoryRegSign, Date operateTime,
			String operator, String remark, String keyWord, Integer delFlag,
			Date delTime) {
		this.fileRecordId = fileRecordId;
		this.tblFileIndex = tblFileIndex;
		this.fileRecordSn = fileRecordSn;
		this.specimenTypeFlag = specimenTypeFlag;
		this.studyNo = studyNo;
		this.studyNoType = studyNoType;
		this.studyName = studyName;
		this.sd = sd;
		this.fileNum = fileNum;
		this.fileNumUnit = fileNumUnit;
		this.fileOperator = fileOperator;
		this.fileDate = fileDate;
		this.keepDate = keepDate;
		this.destoryDate = destoryDate;
		this.destoryRegSign = destoryRegSign;
		this.operateTime = operateTime;
		this.operator = operator;
		this.remark = remark;
		this.keyWord = keyWord;
		this.delFlag = delFlag;
		this.delTime = delTime;
	}

	// Property accessors

	public String getFileRecordId() {
		return this.fileRecordId;
	}

	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	public TblFileIndex getTblFileIndex() {
		return this.tblFileIndex;
	}

	public void setTblFileIndex(TblFileIndex tblFileIndex) {
		this.tblFileIndex = tblFileIndex;
	}

	public Integer getFileRecordSn() {
		return this.fileRecordSn;
	}

	public void setFileRecordSn(Integer fileRecordSn) {
		this.fileRecordSn = fileRecordSn;
	}

	public Integer getSpecimenTypeFlag() {
		return this.specimenTypeFlag;
	}

	public void setSpecimenTypeFlag(Integer specimenTypeFlag) {
		this.specimenTypeFlag = specimenTypeFlag;
	}

	public String getStudyNo() {
		return this.studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public Integer getStudyNoType() {
		return this.studyNoType;
	}

	public void setStudyNoType(Integer studyNoType) {
		this.studyNoType = studyNoType;
	}

	public String getStudyName() {
		return this.studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getSd() {
		return this.sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public Integer getFileNum() {
		return this.fileNum;
	}

	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileNumUnit() {
		return this.fileNumUnit;
	}

	public void setFileNumUnit(String fileNumUnit) {
		this.fileNumUnit = fileNumUnit;
	}

	public String getFileOperator() {
		return this.fileOperator;
	}

	public void setFileOperator(String fileOperator) {
		this.fileOperator = fileOperator;
	}

	public Date getFileDate() {
		return this.fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public Date getKeepDate() {
		return this.keepDate;
	}

	public void setKeepDate(Date keepDate) {
		this.keepDate = keepDate;
	}

	public Date getDestoryDate() {
		if(this.destoryDate!=null&&this.destoryDate.compareTo(DateUtil.stringToDate("1900-01-01", "yyyy-MM-dd"))!=0)
			return this.destoryDate;
		else 
			return null;
	}

	public void setDestoryDate(Date destoryDate) {
		this.destoryDate = destoryDate;
	}

	public String getDestoryRegSign() {
		return this.destoryRegSign;
	}

	public void setDestoryRegSign(String destoryRegSign) {
		this.destoryRegSign = destoryRegSign;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Date getSpecimenDestoryDate() {
		return specimenDestoryDate;
	}

	public void setSpecimenDestoryDate(Date specimenDestoryDate) {
		this.specimenDestoryDate = specimenDestoryDate;
	}

	public String getSpecimenDestoryRegSign() {
		return specimenDestoryRegSign;
	}

	public void setSpecimenDestoryRegSign(String specimenDestoryRegSign) {
		this.specimenDestoryRegSign = specimenDestoryRegSign;
	}

}