package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770931076602009727L;
    private Long id;             //部门id
    private String name;        //部门名
    private int issales;        //是销售（待定）
    private int deleted;        //删除标记
    private String createdby;   //创建者
    private String modifiedby;  //修改者
    private Date dateentered;   //输入时间
    private Date datemodified;   //修改时间
    private String description; //描述
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIssales() {
		return issales;
	}
	public void setIssales(int issales) {
		this.issales = issales;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public Date getDateentered() {
		return dateentered;
	}
	public void setDateentered(Date dateentered) {
		this.dateentered = dateentered;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDatemodified() {
		return datemodified;
	}
	public void setDatemodified(Date datemodified) {
		this.datemodified = datemodified;
	}
	
    
}
