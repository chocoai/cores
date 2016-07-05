package com.lanen.model;

import java.io.Serializable;

public class Position_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1407566009814031777L;
	 private Long id;              //职位id
	    private String name;         //职位名
	    private int department_id;    //部门id
	    private String description;  //描述
	    private String department;    //职位名
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
		
		public int getDepartment_id() {
			return department_id;
		}
		public void setDepartment_id(int departmentId) {
			department_id = departmentId;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setDepartment(String departmen) {
			this.department = departmen;
		}
		public String getDepartment() {
			return department;
		}
	    
}
