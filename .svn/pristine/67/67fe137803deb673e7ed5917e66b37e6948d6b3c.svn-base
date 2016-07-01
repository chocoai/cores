package com.lanen.model.qa;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TblStudyPlanReadRecord entity. @author MyEclipse Persistence Tools
 */

public class TblStudyPlanReadRecord implements java.io.Serializable {

	// Fields

	private String id;
	private String readerCode;
	private String reader;
	private Date readFinishTime;
	//private String studyNo;
	private TblStudyFileIndex tblStudyFileIndex;

	// Constructors

	/** default constructor */
	public TblStudyPlanReadRecord() {
	}

	/** minimal constructor */
	public TblStudyPlanReadRecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public TblStudyPlanReadRecord(String id, String readerCode, String reader,
			Timestamp readFinishTime, TblStudyFileIndex tblStudyFileIndex) {
		this.id = id;
		this.readerCode = readerCode;
		this.reader = reader;
		this.readFinishTime = readFinishTime;
		this.tblStudyFileIndex = tblStudyFileIndex;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReaderCode() {
		return this.readerCode;
	}

	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}

	public String getReader() {
		return this.reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}

	public Date getReadFinishTime() {
		return this.readFinishTime;
	}

	public void setReadFinishTime(Date readFinishTime) {
		this.readFinishTime = readFinishTime;
	}

	public TblStudyFileIndex getTblStudyFileIndex() {
		return tblStudyFileIndex;
	}

	public void setTblStudyFileIndex(TblStudyFileIndex tblStudyFileIndex) {
		this.tblStudyFileIndex = tblStudyFileIndex;
	}

}