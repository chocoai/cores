package com.lanen.model;

import java.util.Date;

import com.lanen.util.DateUtil;

/**
 * TblFileRecordSmplReserve entity. @author MyEclipse Persistence Tools
 */

public class TblFileRecordSmplReserve implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileIndex tblFileIndex;
	private Integer fileRecordSn;
	private String smplType;
	private String smplCode;
	private String smplName;
	private String sponsorName;
	private String container;
	private String reserveNum;
	private String reserveNumUnit;
	private String reportUnitName;
	private String smplProvUnitName;
	private String batchCode;
	private Date validDate;
	private Date reserveDate;
	private String reserveMan;
	private String reserveRecMan;
	private Date reserveRecDate;
	private String fileOperator;
	private Date fileDate;
	private Date keepDate;
	private Date destoryDate;
	private String destoryRegSign;
	private Date smplDestoryDate;
	private String smplDestoryRegSign;
	private Date operateTime;
	private String operator;
	private String remark;
	private String keyWord;
	private Integer delFlag;
	private Date delTime;
	
	private String storageCondition;
	
	private String reserveBalance;
	private String gross;
	private String grossUnit;
	private String grossBalance;

	// Constructors

	/** default constructor */
	public TblFileRecordSmplReserve() {
	}

	/** minimal constructor */
	public TblFileRecordSmplReserve(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	/** full constructor */
	public TblFileRecordSmplReserve(String fileRecordId,
			TblFileIndex tblFileIndex, Integer fileRecordSn, String smplType,
			String smplCode, String smplName, String sponsorName,
			String container, String reserveNum, String reserveNumUnit,
			String reportUnitName, String smplProvUnitName, String batchCode,
			Date validDate, Date reserveDate, String reserveMan,
			String reserveRecMan, Date reserveRecDate, String fileOperator,
			Date fileDate, Date keepDate, Date destoryDate,
			String destoryRegSign, Date operateTime, String operator,
			String remark, String keyWord, Integer delFlag, Date delTime) {
		this.fileRecordId = fileRecordId;
		this.tblFileIndex = tblFileIndex;
		this.fileRecordSn = fileRecordSn;
		this.smplType = smplType;
		this.smplCode = smplCode;
		this.smplName = smplName;
		this.sponsorName = sponsorName;
		this.container = container;
		this.reserveNum = reserveNum;
		this.reserveNumUnit = reserveNumUnit;
		this.reportUnitName = reportUnitName;
		this.smplProvUnitName = smplProvUnitName;
		this.batchCode = batchCode;
		this.validDate = validDate;
		this.reserveDate = reserveDate;
		this.reserveMan = reserveMan;
		this.reserveRecMan = reserveRecMan;
		this.reserveRecDate = reserveRecDate;
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

	public String getSmplType() {
		return this.smplType;
	}

	public void setSmplType(String smplType) {
		this.smplType = smplType;
	}

	public String getSmplCode() {
		return this.smplCode;
	}

	public void setSmplCode(String smplCode) {
		this.smplCode = smplCode;
	}

	public String getSmplName() {
		return this.smplName;
	}

	public void setSmplName(String smplName) {
		this.smplName = smplName;
	}

	public String getSponsorName() {
		return this.sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getContainer() {
		return this.container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getReserveNum() {
		return this.reserveNum;
	}

	public void setReserveNum(String reserveNum) {
		this.reserveNum = reserveNum;
	}

	public String getReserveNumUnit() {
		return this.reserveNumUnit;
	}

	public void setReserveNumUnit(String reserveNumUnit) {
		this.reserveNumUnit = reserveNumUnit;
	}

	public String getReportUnitName() {
		return this.reportUnitName;
	}

	public void setReportUnitName(String reportUnitName) {
		this.reportUnitName = reportUnitName;
	}

	public String getSmplProvUnitName() {
		return this.smplProvUnitName;
	}

	public void setSmplProvUnitName(String smplProvUnitName) {
		this.smplProvUnitName = smplProvUnitName;
	}

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getReserveDate() {
		return this.reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public String getReserveMan() {
		return this.reserveMan;
	}

	public void setReserveMan(String reserveMan) {
		this.reserveMan = reserveMan;
	}

	public String getReserveRecMan() {
		return this.reserveRecMan;
	}

	public void setReserveRecMan(String reserveRecMan) {
		this.reserveRecMan = reserveRecMan;
	}

	public Date getReserveRecDate() {
		return this.reserveRecDate;
	}

	public void setReserveRecDate(Date reserveRecDate) {
		this.reserveRecDate = reserveRecDate;
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

	public Date getSmplDestoryDate() {
		return smplDestoryDate;
	}

	public void setSmplDestoryDate(Date smplDestoryDate) {
		this.smplDestoryDate = smplDestoryDate;
	}

	public String getSmplDestoryRegSign() {
		return smplDestoryRegSign;
	}

	public void setSmplDestoryRegSign(String smplDestoryRegSign) {
		this.smplDestoryRegSign = smplDestoryRegSign;
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	public String getReserveBalance() {
		return reserveBalance;
	}

	public void setReserveBalance(String reserveBalance) {
		this.reserveBalance = reserveBalance;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getGrossUnit() {
		return grossUnit;
	}

	public void setGrossUnit(String grossUnit) {
		this.grossUnit = grossUnit;
	}

	public String getGrossBalance() {
		return grossBalance;
	}

	public void setGrossBalance(String grossBalance) {
		this.grossBalance = grossBalance;
	}

}