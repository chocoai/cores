package com.lanen.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TblFileIndex entity. @author MyEclipse Persistence Tools
 */

public class TblFileIndex implements java.io.Serializable {

	// Fields

	private String archiveCode;
	private String archiveTypeCode;
	private Integer archiveTypeFlag;//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本
	private String storePosition;
	private String archiveTitle;
	private Integer validationFlag;
	private Date operateTime;
	private String operator;
	
	// Constructors

	/** default constructor */
	public TblFileIndex() {
	}

	/** minimal constructor */
	public TblFileIndex(String archiveCode) {
		this.archiveCode = archiveCode;
	}

	/** full constructor */
	public TblFileIndex(String archiveCode, String archiveTypeCode,
			Integer archiveTypeFlag, String storePosition, String archiveTitle,
			Integer validationFlag, Date operateTime, String operator) {
		this.archiveCode = archiveCode;
		this.archiveTypeCode = archiveTypeCode;
		this.archiveTypeFlag = archiveTypeFlag;
		this.storePosition = storePosition;
		this.archiveTitle = archiveTitle;
		this.validationFlag = validationFlag;
		this.operateTime = operateTime;
		this.operator = operator;
	}

	// Property accessors

	public String getArchiveCode() {
		return this.archiveCode;
	}

	public void setArchiveCode(String archiveCode) {
		this.archiveCode = archiveCode;
	}

	public String getArchiveTypeCode() {
		return this.archiveTypeCode;
	}

	public void setArchiveTypeCode(String archiveTypeCode) {
		this.archiveTypeCode = archiveTypeCode;
	}

	public Integer getArchiveTypeFlag() {
		return this.archiveTypeFlag;
	}

	public void setArchiveTypeFlag(Integer archiveTypeFlag) {
		this.archiveTypeFlag = archiveTypeFlag;
	}

	public String getStorePosition() {
		return this.storePosition;
	}

	public void setStorePosition(String storePosition) {
		this.storePosition = storePosition;
	}

	public String getArchiveTitle() {
		return this.archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	public Integer getValidationFlag() {
		return this.validationFlag;
	}

	public void setValidationFlag(Integer validationFlag) {
		this.validationFlag = validationFlag;
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


}