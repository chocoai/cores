package com.lanen.model;

import java.io.Serializable;

public class Animaltype  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1694195658629158124L;
	
	private Long id;//主键
	
	private String name;//名称
	
	private String desciption;//简介
	
	private Integer del;//删除标记 0 存在 -1删除
	

	

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public void setDel(Integer del) {
		this.del = del;
	}
	
	

}
