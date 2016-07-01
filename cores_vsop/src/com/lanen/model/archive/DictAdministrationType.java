package com.lanen.model.archive;

/**
 * DictArchiveType entity. @author MyEclipse Persistence Tools
 */

public class DictAdministrationType implements java.io.Serializable {

	// Fields

	private String docTypeFlag;
	private String docTypeName;
	private Integer sn;

	// Constructors

	/** default constructor */
	public DictAdministrationType() {
	}

	/** minimal constructor */
	public DictAdministrationType(String docTypeFlag) {
		this.docTypeFlag = docTypeFlag;
	}

	/** full constructor */
	public DictAdministrationType(String docTypeFlag, String docTypeName,
			Integer sn) {
		this.docTypeFlag = docTypeFlag;
		this.docTypeName = docTypeName;
		this.sn = sn;
	}

	public String getDocTypeFlag() {
		return docTypeFlag;
	}

	public void setDocTypeFlag(String docTypeFlag) {
		this.docTypeFlag = docTypeFlag;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	// Property accessors

	


}