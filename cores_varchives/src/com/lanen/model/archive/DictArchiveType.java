package com.lanen.model.archive;

/**
 * DictArchiveType entity. @author MyEclipse Persistence Tools
 */

public class DictArchiveType implements java.io.Serializable {

	// Fields

	private String archiveTypeCode;
	private String archiveTypeName;
	private Integer archiveTypeFlag;//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本

	// Constructors

	/** default constructor */
	public DictArchiveType() {
	}

	/** minimal constructor */
	public DictArchiveType(String archiveTypeCode) {
		this.archiveTypeCode = archiveTypeCode;
	}

	/** full constructor */
	public DictArchiveType(String archiveTypeCode, String archiveTypeName,
			Integer archiveTypeFlag) {
		this.archiveTypeCode = archiveTypeCode;
		this.archiveTypeName = archiveTypeName;
		this.archiveTypeFlag = archiveTypeFlag;
	}

	// Property accessors

	public String getArchiveTypeCode() {
		return this.archiveTypeCode;
	}

	public void setArchiveTypeCode(String archiveTypeCode) {
		this.archiveTypeCode = archiveTypeCode;
	}

	public String getArchiveTypeName() {
		return this.archiveTypeName;
	}

	public void setArchiveTypeName(String archiveTypeName) {
		this.archiveTypeName = archiveTypeName;
	}

	public Integer getArchiveTypeFlag() {
		return this.archiveTypeFlag;
	}

	public void setArchiveTypeFlag(Integer archiveTypeFlag) {
		this.archiveTypeFlag = archiveTypeFlag;
	}

}