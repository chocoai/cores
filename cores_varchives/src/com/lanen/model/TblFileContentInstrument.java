package com.lanen.model;

import java.util.Date;

/**
 * TblFileContentInstrument entity. @author MyEclipse Persistence Tools
 */

public class TblFileContentInstrument implements java.io.Serializable {

	// Fields

	private String fileRecordId;
	private TblFileRecord tblFileRecord;
	private String archiveCode;
	private String instrumentId;
	private String instrumentName;
	private String instrumentModel;
	private String instrumentManufacturer;
	private Date instrumentPurchaseDate;

	// Constructors

	/** default constructor */
	public TblFileContentInstrument() {
	}

	/** minimal constructor */
	public TblFileContentInstrument(String fileRecordId,
			TblFileRecord tblFileRecord) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
	}

	/** full constructor */
	public TblFileContentInstrument(String fileRecordId,
			TblFileRecord tblFileRecord, String archiveCode,
			String instrumentId, String instrumentName, String instrumentModel,
			String instrumentManufacturer, Date instrumentPurchaseDate) {
		this.fileRecordId = fileRecordId;
		this.tblFileRecord = tblFileRecord;
		this.archiveCode = archiveCode;
		this.instrumentId = instrumentId;
		this.instrumentName = instrumentName;
		this.instrumentModel = instrumentModel;
		this.instrumentManufacturer = instrumentManufacturer;
		this.instrumentPurchaseDate = instrumentPurchaseDate;
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

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getInstrumentName() {
		return this.instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getInstrumentModel() {
		return this.instrumentModel;
	}

	public void setInstrumentModel(String instrumentModel) {
		this.instrumentModel = instrumentModel;
	}

	public String getInstrumentManufacturer() {
		return this.instrumentManufacturer;
	}

	public void setInstrumentManufacturer(String instrumentManufacturer) {
		this.instrumentManufacturer = instrumentManufacturer;
	}

	public Date getInstrumentPurchaseDate() {
		return this.instrumentPurchaseDate;
	}

	public void setInstrumentPurchaseDate(Date instrumentPurchaseDate) {
		this.instrumentPurchaseDate = instrumentPurchaseDate;
	}

}