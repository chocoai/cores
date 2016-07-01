package com.lanen.model;

/**
 * DictDataTable entity. @author MyEclipse Persistence Tools
 */

public class DictDataTable implements java.io.Serializable {

	// Fields

	private String id;
	private String dbname;
	private String tableName;
	private String fieldName;
	private String fieldDesc;

	// Constructors

	/** default constructor */
	public DictDataTable() {
	}

	/** minimal constructor */
	public DictDataTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public DictDataTable(String id, String dbname, String tableName,
			String fieldName, String fieldDesc) {
		this.id = id;
		this.dbname = dbname;
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDbname() {
		return this.dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
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

}