package com.lanen.model;

import java.io.Serializable;

public class Security_unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164737017239011163L;
    private int id;              //id
    private String sid;          //模块英文名
    private String name;         //模块中文名
    private String description;  //描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
    
}
