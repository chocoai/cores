package com.lanen.model;

import java.io.Serializable;

public class Security_er implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4170335610828493641L;
	private int id;                 //员工角色联合表id
    private String employee_id;     //员工id
    private int role_id;            //角色id
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employeeId) {
		employee_id = employeeId;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int roleId) {
		role_id = roleId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
    
}
