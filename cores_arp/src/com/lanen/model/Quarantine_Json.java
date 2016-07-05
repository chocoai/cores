package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Quarantine_Json implements Serializable{

	
	/**
	 * 防疫配置
	 */
	private static final long serialVersionUID = 116762136454819752L;
	
	private String pid;
	private String _parentId;
	private String iconCls;
	private String state;
	
	private Long id;
	private String name;
	private String type;
	private String remark;
	private Integer deleted;
	private Integer modified_by;
	private Timestamp createtime;
	private Integer created_by;
	private Timestamp lastmodifytime;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getModified_by() {
		return modified_by;
	}
	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Timestamp getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Timestamp lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String id) {
		_parentId = id;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
