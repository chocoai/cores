package com.lanen.model;

import java.util.Date;

/**
 * TblFileContentSop entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentSop implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String soptypeCode;
	private String soptypeName;
	private String sopcode;
	private String sopname;
	private String sopver;
	private Date sopeffectiveDate;
	private Date sopinvalidDate;
	
	private byte[] sopfile;//file
	private String sopfileName;
	private Integer sopflag;

	// Constructors

	/** default constructor */
	public TblFileContentSop() {
	}

	/** minimal constructor */
	public TblFileContentSop(String fileRecordId, TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentSop(String fileRecordId, TblFileRecord tblFileRecord,
			String archiveCode, String soptypeCode, String soptypeName,
			String sopcode, String sopname, String sopver,
			Date sopeffectiveDate, Date sopinvalidDate) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.soptypeCode = soptypeCode;
		this.soptypeName = soptypeName;
		this.sopcode = sopcode;
		this.sopname = sopname;
		this.sopver = sopver;
		this.sopeffectiveDate = sopeffectiveDate;
		this.sopinvalidDate = sopinvalidDate;
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

	public String getSoptypeCode() {
		return this.soptypeCode;
	}

	public void setSoptypeCode(String soptypeCode) {
		this.soptypeCode = soptypeCode;
	}

	public String getSoptypeName() {
		return this.soptypeName;
	}

	public void setSoptypeName(String soptypeName) {
		this.soptypeName = soptypeName;
	}

	public String getSopcode() {
		return this.sopcode;
	}

	public void setSopcode(String sopcode) {
		this.sopcode = sopcode;
	}

	public String getSopname() {
		return this.sopname;
	}

	public void setSopname(String sopname) {
		this.sopname = sopname;
	}

	public String getSopver() {
		return this.sopver;
	}

	public void setSopver(String sopver) {
		this.sopver = sopver;
	}

	public Date getSopeffectiveDate() {
		return this.sopeffectiveDate;
	}

	public void setSopeffectiveDate(Date sopeffectiveDate) {
		this.sopeffectiveDate = sopeffectiveDate;
	}

	public Date getSopinvalidDate() {
		return this.sopinvalidDate;
	}

	public void setSopinvalidDate(Date sopinvalidDate) {
		if(sopinvalidDate==null||"".equals(sopinvalidDate))
		{
			this.sopinvalidDate = null;
		}else {
			this.sopinvalidDate = sopinvalidDate;
		}
	}

	public Integer getSopflag() {
		return sopflag;
	}

	public void setSopflag(Integer sopflag) {
		this.sopflag = sopflag;
	}

	public byte[] getSopfile() {
		return sopfile;
	}

	public void setSopfile(byte[] sopfile) {
		this.sopfile = sopfile;
	}

	public String getSopfileName() {
		return sopfileName;
	}

	public void setSopfileName(String sopfileName) {
		this.sopfileName = sopfileName;
	}

}