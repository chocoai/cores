package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Hospitaldl_Json implements Serializable{

	/**
	 * 疾病系统
	 */
	private static final long serialVersionUID = 5954473868940087584L;
	
    private Long id;                //id
    private String monkeyid;           //编号
    private Date zlrq;           //名字
    private String zzmc;                //?
    private String cf;              //?
    private String treatveterinarian;           //主治兽医
    private Timestamp createtime;       //修改者(id)
    private Integer modified_by;       //创建时间
    private Integer created_by;
    private Timestamp lastmodifytime;
    private Integer deleted;
    private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonkeyid() {
		return monkeyid;
	}
	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}
	public Date getZlrq() {
		return zlrq;
	}
	public void setZlrq(Date zlrq) {
		this.zlrq = zlrq;
	}
	public String getZzmc() {
		return zzmc;
	}
	public void setZzmc(String zzmc) {
		this.zzmc = zzmc;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getTreatveterinarian() {
		return treatveterinarian;
	}
	public void setTreatveterinarian(String treatveterinarian) {
		this.treatveterinarian = treatveterinarian;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Integer getModified_by() {
		return modified_by;
	}
	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
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
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}
