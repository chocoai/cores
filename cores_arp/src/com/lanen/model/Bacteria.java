package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Bacteria implements Serializable {

	/**
	 * 细菌
	 */
	private static final long serialVersionUID = -3959749931101658871L;
	
	private Integer id;
	private String monkeyid;
	private String protector;
	private String recorder;
	private Timestamp createtime;
	private Integer modified_by;
	private Integer created_by;
	private Timestamp lastmodifytime;
	private Byte deleted;
	private String remark;
	private String veterinarian;
	private Integer normal_id;//bigint
	private Integer q_id;
	private Byte resoult;//tinyint
	private Integer qconfig_id;
	private Integer drugs_id;
	private String drugs_count;
	private String drugs_name;
	private Date cdate;
	private String ptype;
	private String dateid;
	
	private String ypid;
	private String shig;
	private String salm;
	private String yers;
	private String pccode;
	private String process;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonkeyid() {
		return monkeyid;
	}
	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}
	public String getProtector() {
		return protector;
	}
	public void setProtector(String protector) {
		this.protector = protector;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
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
	public Byte getDeleted() {
		return deleted;
	}
	public void setDeleted(Byte deleted) {
		this.deleted = deleted;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVeterinarian() {
		return veterinarian;
	}
	public void setVeterinarian(String veterinarian) {
		this.veterinarian = veterinarian;
	}
	public Integer getNormal_id() {
		return normal_id;
	}
	public void setNormal_id(Integer normal_id) {
		this.normal_id = normal_id;
	}
	public Integer getQ_id() {
		return q_id;
	}
	public void setQ_id(Integer q_id) {
		this.q_id = q_id;
	}
	public Byte getResoult() {
		return resoult;
	}
	public void setResoult(Byte resoult) {
		this.resoult = resoult;
	}
	public Integer getQconfig_id() {
		return qconfig_id;
	}
	public void setQconfig_id(Integer qconfig_id) {
		this.qconfig_id = qconfig_id;
	}
	public Integer getDrugs_id() {
		return drugs_id;
	}
	public void setDrugs_id(Integer drugs_id) {
		this.drugs_id = drugs_id;
	}
	public String getDrugs_count() {
		return drugs_count;
	}
	public void setDrugs_count(String drugs_count) {
		this.drugs_count = drugs_count;
	}
	public String getDrugs_name() {
		return drugs_name;
	}
	public void setDrugs_name(String drugs_name) {
		this.drugs_name = drugs_name;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getDateid() {
		return dateid;
	}
	public void setDateid(String dateid) {
		this.dateid = dateid;
	}
	public String getYpid() {
		return ypid;
	}
	public void setYpid(String ypid) {
		this.ypid = ypid;
	}
	public String getShig() {
		return shig;
	}
	public void setShig(String shig) {
		this.shig = shig;
	}
	public String getSalm() {
		return salm;
	}
	public void setSalm(String salm) {
		this.salm = salm;
	}
	public String getYers() {
		return yers;
	}
	public void setYers(String yers) {
		this.yers = yers;
	}
	public String getPccode() {
		return pccode;
	}
	public void setPccode(String pccode) {
		this.pccode = pccode;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}	

}
