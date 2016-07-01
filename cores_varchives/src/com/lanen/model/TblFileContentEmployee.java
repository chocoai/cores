package com.lanen.model;

/**
 * TblFileContentEmployee entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentEmployee implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String staffName;
	private String staffCode;
	private String staffDept;
	private Integer staffState;

	// Constructors

	/** default constructor */
	public TblFileContentEmployee() {
	}

	/** minimal constructor */
	public TblFileContentEmployee(String fileRecordId,
			TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentEmployee(String fileRecordId,
			TblFileRecord tblFileRecord, String archiveCode, String staffName,
			String staffCode, String staffDept, Integer staffState) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.staffName = staffName;
		this.staffCode = staffCode;
		this.staffDept = staffDept;
		this.staffState = staffState;
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

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffDept() {
		return this.staffDept;
	}

	public void setStaffDept(String staffDept) {
		this.staffDept = staffDept;
	}

	public Integer getStaffState() {
		return this.staffState;
	}

	public void setStaffState(Integer staffState) {
		this.staffState = staffState;
	}

}