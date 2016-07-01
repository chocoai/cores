package com.lanen.model;

import java.util.Date;


/**
 * TblFileContentContract entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentContract implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String contractCode;
	private String contractName;
	private Integer contractTypeFlag;
	private String sponsorName;
	private Integer num;
	private Date beginDate;
	private Date endDate;
	private Date terminalDate;

	// Constructors

	/** default constructor */
	public TblFileContentContract() {
	}

	/** minimal constructor */
	public TblFileContentContract(String fileRecordId,
			TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentContract(String fileRecordId,
			TblFileRecord tblFileRecord, String archiveCode,
			String contractCode, String contractName, Integer contractTypeFlag,
			String sponsorName, Integer num, Date beginDate,
			Date endDate, Date terminalDate) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.contractCode = contractCode;
		this.contractName = contractName;
		this.contractTypeFlag = contractTypeFlag;
		this.sponsorName = sponsorName;
		this.num = num;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.terminalDate = terminalDate;
	}

	// Property accessors

	public String getFileRecordId() {
		return this.fileRecordId;
	}

	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	public TblFileRecord getTblFileRecord() {
		return this.tblFileRecord;
	}

	public void setTblFileRecord(TblFileRecord tblFileRecord) {
		this.tblFileRecord = tblFileRecord;
	}

	public String getArchiveCode() {
		return this.archiveCode;
	}

	public void setArchiveCode(String archiveCode) {
		this.archiveCode = archiveCode;
	}

	public String getContractCode() {
		return this.contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Integer getContractTypeFlag() {
		return this.contractTypeFlag;
	}

	public void setContractTypeFlag(Integer contractTypeFlag) {
		this.contractTypeFlag = contractTypeFlag;
	}

	public String getSponsorName() {
		return this.sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTerminalDate() {
		return this.terminalDate;
	}

	public void setTerminalDate(Date terminalDate) {
		this.terminalDate = terminalDate;
	}

}