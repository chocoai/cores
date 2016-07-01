package com.lanen.model;

/**
 * TblFileContentGlpSynthesis entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentGlpSynthesis implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String department;
	private String docName;
	
	private Integer baseGlpFlag;//1综合，2基建

	// Constructors

	/** default constructor */
	public TblFileContentGlpSynthesis() {
	}

	/** minimal constructor */
	public TblFileContentGlpSynthesis(String fileRecordId,
			TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentGlpSynthesis(String fileRecordId,
			TblFileRecord tblFileRecord, String archiveCode, String department,
			String docName) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.department = department;
		this.docName = docName;
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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getBaseGlpFlag() {
		return baseGlpFlag;
	}

	public void setBaseGlpFlag(Integer baseGlpFlag) {
		this.baseGlpFlag = baseGlpFlag;
	}

}