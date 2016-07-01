package com.lanen.model;

import java.util.Date;

/**
 * TblFileContentStudy entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentStudy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3540214163799008026L;
	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String studyNo;
	private Integer studyNoType;
	private String studyName;
	private String contractCode;
	private String studySponerName;
	private String sdname;
	private Date operateTime;
	private String operator;

	// Constructors

	/** default constructor */
	public TblFileContentStudy() {
	}

	/** minimal constructor */
	public TblFileContentStudy(String fileRecordId, TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentStudy(String fileRecordId,
			TblFileRecord tblFileRecord, String archiveCode, String studyNo,
			Integer studyNoType, String studyName, String sdname,
			Date operateTime, String operator) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.studyNo = studyNo;
		this.studyNoType = studyNoType;
		this.studyName = studyName;
		this.sdname = sdname;
		this.operateTime = operateTime;
		this.operator = operator;
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

	public String getStudySponerName() {
		return studySponerName;
	}

	public void setStudySponerName(String studySponerName) {
		this.studySponerName = studySponerName;
	}

	public String getSdname() {
		return this.sdname;
	}

	public void setSdname(String sdname) {
		this.sdname = sdname;
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

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

}