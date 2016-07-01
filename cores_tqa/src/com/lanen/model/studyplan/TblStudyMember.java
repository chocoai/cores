package com.lanen.model.studyplan;

import java.io.Serializable;

public class TblStudyMember implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4507390058517982578L;

	private String id;
	
	private String StudyNo;
	
	private String Member;//用户名 tbluser 主键
	
	private String name;//显示人名
	
	
    private String departmentname;//前台显示部门名称
	
	
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudyNo() {
		return StudyNo;
	}

	public void setStudyNo(String studyNo) {
		StudyNo = studyNo;
	}

	public String getMember() {
		return Member;
	}

	public void setMember(String member) {
		Member = member;
	}
	
	
	
}
