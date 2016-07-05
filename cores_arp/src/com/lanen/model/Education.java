package com.lanen.model;

import java.io.Serializable;

public class Education implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227923866679687612L;
    private Integer id;              //学历表ID
    private String name;          //学历名称
    private String description;   //描述
    private int deleted;          //删除标记
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
    
}
