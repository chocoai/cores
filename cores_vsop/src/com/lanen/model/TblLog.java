package com.lanen.model;

import java.util.Date;

/**
 * TblLog entity. @author MyEclipse Persistence Tools
 */

public class TblLog implements java.io.Serializable {

	// Fields

	private String id;
	private Integer archiveTypeFlag;
	private String archiveCode;
	private Integer fileRecordSn;
	private String archiveTitle;
	private Integer operateTypeFlag;
	private String operateType;
	private String tableName;
	private String fieldName;
	private String fieldDesc;
	private String oldValue;
	private String newValue;
	private String operator;
	private Date operateTime;
	private String operateRsn;
	private String oldFileRecordId;

	// Constructors

	/** default constructor */
	public TblLog() {
	}

	/** minimal constructor */
	public TblLog(String id) {
		this.id = id;
	}

	/** full constructor */
	public TblLog(String id, Integer archiveTypeFlag, String archiveCode,
			Integer fileRecordSn, String archiveTitle, Integer operateTypeFlag,
			String operateType, String tableName, String fieldName,
			String fieldDesc, String oldValue, String newValue,
			String operator, Date operateTime, String operateRsn,
			String oldFileRecordId) {
		this.id = id;
		this.archiveTypeFlag = archiveTypeFlag;
		this.archiveCode = archiveCode;
		this.fileRecordSn = fileRecordSn;
		this.archiveTitle = archiveTitle;
		this.operateTypeFlag = operateTypeFlag;
		this.operateType = operateType;
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.operator = operator;
		this.operateTime = operateTime;
		this.operateRsn = operateRsn;
		this.oldFileRecordId = oldFileRecordId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getArchiveTypeFlag() {
		return this.archiveTypeFlag;
	}

	public void setArchiveTypeFlag(Integer archiveTypeFlag) {
		this.archiveTypeFlag = archiveTypeFlag;
	}

	public String getArchiveCode() {
		return this.archiveCode;
	}

	public void setArchiveCode(String archiveCode) {
		this.archiveCode = archiveCode;
	}

	public Integer getFileRecordSn() {
		return this.fileRecordSn;
	}

	public void setFileRecordSn(Integer fileRecordSn) {
		this.fileRecordSn = fileRecordSn;
	}

	public String getArchiveTitle() {
		return this.archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	public Integer getOperateTypeFlag() {
		return this.operateTypeFlag;
	}

	public void setOperateTypeFlag(Integer operateTypeFlag) {
		this.operateTypeFlag = operateTypeFlag;
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return this.fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return this.newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateRsn() {
		return this.operateRsn;
	}

	public void setOperateRsn(String operateRsn) {
		this.operateRsn = operateRsn;
	}

	public String getOldFileRecordId() {
		return this.oldFileRecordId;
	}

	public void setOldFileRecordId(String oldFileRecordId) {
		this.oldFileRecordId = oldFileRecordId;
	}

}