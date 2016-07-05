package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Symptomsarea implements Serializable{

	/**
	 * 疾病系统
	 */
	private static final long serialVersionUID = 5954473868940087584L;
	
    private Long id;                //id
    private String name;           //编号
    private String remark;           //名字
    private Integer deleted;                //?
    private Integer modified_by;              //?
    private Timestamp createtime;           //删除标记
    private Integer created_by;       //修改者(id)
    private Timestamp lastmodifytime;       //创建时间
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
    
}
