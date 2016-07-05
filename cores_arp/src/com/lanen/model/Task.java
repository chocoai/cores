package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Task  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1694195658629158124L;
	
	private Long id;//主键
	
private Integer typeid;
 private String porptyid;
 private Long ower;
private Date begindate;
private Date enddate;
private Integer status;
private Integer isremind;
private String monkeys;
private String protectors;
private String recorders;
private String veterinarians;
private String dateid;
private String title;
private String contant;
private String lookers;
private Timestamp createtime;
private Integer modified_by;
private Integer created_by;
private Timestamp lastmodifytime;
private Integer deleted;
private String remark;
private String keeper;
private String tbjy;

private String bdjy;
private String tb;
private String jsc;
private String xjjy;
private String ym;
private String crb;
private String x;
private String saleid;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Integer getTypeid() {
	return typeid;
}
public void setTypeid(Integer typeid) {
	this.typeid = typeid;
}
public String getPorptyid() {
	return porptyid;
}
public void setPorptyid(String porptyid) {
	this.porptyid = porptyid;
}
public Long getOwer() {
	return ower;
}
public void setOwer(Long ower) {
	this.ower = ower;
}
public Date getBegindate() {
	return begindate;
}
public void setBegindate(Date begindate) {
	this.begindate = begindate;
}
public Date getEnddate() {
	return enddate;
}
public void setEnddate(Date enddate) {
	this.enddate = enddate;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getIsremind() {
	return isremind;
}
public void setIsremind(Integer isremind) {
	this.isremind = isremind;
}
public String getMonkeys() {
	return monkeys;
}
public void setMonkeys(String monkeys) {
	this.monkeys = monkeys;
}
public String getProtectors() {
	return protectors;
}
public void setProtectors(String protectors) {
	this.protectors = protectors;
}
public String getRecorders() {
	return recorders;
}
public void setRecorders(String recorders) {
	this.recorders = recorders;
}
public String getVeterinarians() {
	return veterinarians;
}
public void setVeterinarians(String veterinarians) {
	this.veterinarians = veterinarians;
}
public String getDateid() {
	return dateid;
}
public void setDateid(String dateid) {
	this.dateid = dateid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContant() {
	return contant;
}
public void setContant(String contant) {
	this.contant = contant;
}
public String getLookers() {
	return lookers;
}
public void setLookers(String lookers) {
	this.lookers = lookers;
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
public void setModified_by(Integer modifiedBy) {
	modified_by = modifiedBy;
}
public Integer getCreated_by() {
	return created_by;
}
public void setCreated_by(Integer createdBy) {
	created_by = createdBy;
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
public String getKeeper() {
	return keeper;
}
public void setKeeper(String keeper) {
	this.keeper = keeper;
}
public String getTbjy() {
	return tbjy;
}
public void setTbjy(String tbjy) {
	this.tbjy = tbjy;
}
public String getBdjy() {
	return bdjy;
}
public void setBdjy(String bdjy) {
	this.bdjy = bdjy;
}
public String getTb() {
	return tb;
}
public void setTb(String tb) {
	this.tb = tb;
}
public String getJsc() {
	return jsc;
}
public void setJsc(String jsc) {
	this.jsc = jsc;
}
public String getXjjy() {
	return xjjy;
}
public void setXjjy(String xjjy) {
	this.xjjy = xjjy;
}
public String getYm() {
	return ym;
}
public void setYm(String ym) {
	this.ym = ym;
}
public String getCrb() {
	return crb;
}
public void setCrb(String crb) {
	this.crb = crb;
}
public String getX() {
	return x;
}
public void setX(String x) {
	this.x = x;
}
public String getSaleid() {
	return saleid;
}
public void setSaleid(String saleid) {
	this.saleid = saleid;
}
	
	

}
