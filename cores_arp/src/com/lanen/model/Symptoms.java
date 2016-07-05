package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Symptoms implements Serializable{

	/**
	 * 症状
	 */
	private static final long serialVersionUID = 5954473868940087584L;
	
    private Long id;                //id
    private String name;           //编号
    private Integer symptomssite;           //名字
    private String symptomsremark;                //?
    private Integer deleted;              //?
    private Integer modified_by;              //?
    private Timestamp createtime;           //删除标记
    private Integer created_by;       //修改者(id)
    private Timestamp lastmodifytime;       //创建时间
    private String istreatment;        //创建者(id)     
    private String reason;   //最后修改时间
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
	public Integer getSymptomssite() {
		return symptomssite;
	}
	public void setSymptomssite(Integer symptomssite) {
		this.symptomssite = symptomssite;
	}
	public String getSymptomsremark() {
		return symptomsremark;
	}
	public void setSymptomsremark(String symptomsremark) {
		this.symptomsremark = symptomsremark;
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
	public String getIstreatment() {
		return istreatment;
	}
	public void setIstreatment(String istreatment) {
		this.istreatment = istreatment;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
    
}
