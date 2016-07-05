package com.lanen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表
 * @author Administrator
 *
 */
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3081136035731775158L;
	private Long  id;
	private String name;  //角色名
	private String description;
	
	private Set<Privilege> privileges =new HashSet<Privilege>();  //权限列表
	private Set<Employee> users=new HashSet<Employee>();//用户列表
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	public Set<Employee> getUsers() {
		return users;
	}
	public void setUsers(Set<Employee> users) {
		this.users = users;
	}
		
	//private Set<Privilege> privileges =new HashSet<Privilege>();  //权限列表
	//private Set<User> users=new HashSet<User>();//用户列表		

}
