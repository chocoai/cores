package com.lanen.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限表
 * @author Administrator
 *
 */
public class Privilege implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4803157427519819753L;
	private Long id;
	private String sid;
	private String name;//privilegeName;//权限名称
	private String url;//privilegePath;//控制路径（web）
	private String description;
	
	
	private Set<Role> roles=new HashSet<Role>();
		

	public Privilege(){}
	public Privilege(Long id,String name,String url,String sid,String description ){
		this.id=id;
		this.name=name;
		this.url=url;
		this.sid=sid;
		this.description=description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
