package com.lanen.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Hospital_Json implements Serializable {

	/**
	 * 住院治疗
	 */
	private static final long serialVersionUID = -6813969423367446869L;

	private Long id;
	private String monkeyid;
	private String treatveterinarian;//主治兽医
	private String inveterinarian;//接受兽医
	private Date treatdate;
	private String treatmode;
	private String treatresoult;
	private Integer treatprotector;
	private Timestamp createtime;
	private Integer modified_by;
	private Integer created_by;
	private Timestamp lastmodifytime;
	private Integer deleted;
	private String inkeeper;//病房饲养员
	private Integer outveterinarian;
	private String outkeeper;//送治饲养员
	private String indate;
	private String outdate;
	private String roomid; //房号
	private String checkdate;
	private String fbdate;
	private String s_name;
	private Integer sareaid;
	private String sremark;
	private String reason;
	private String resoult;
	private Integer checkveterinarian;
	private Integer checkprotector;
	private String status;
	private Integer treatmenttype;
	private String inremark;
	private String outremark;
	private String addsy;
	private String cf;
	private String lhao;
	private String outroom;
	private String hokeeper;
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
	
	public Date getTreatdate() {
		return treatdate;
	}
	public void setTreatdate(Date treatdate) {
		this.treatdate = treatdate;
	}
	public String getTreatmode() {
		return treatmode;
	}
	public void setTreatmode(String treatmode) {
		this.treatmode = treatmode;
	}
	public String getTreatresoult() {
		return treatresoult;
	}
	public void setTreatresoult(String treatresoult) {
		this.treatresoult = treatresoult;
	}
	public Integer getTreatprotector() {
		return treatprotector;
	}
	public void setTreatprotector(Integer treatprotector) {
		this.treatprotector = treatprotector;
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

	public Integer getOutveterinarian() {
		return outveterinarian;
	}
	public void setOutveterinarian(Integer outveterinarian) {
		this.outveterinarian = outveterinarian;
	}
	
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getFbdate() {
		return fbdate;
	}
	public void setFbdate(String fbdate) {
		this.fbdate = fbdate;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public Integer getSareaid() {
		return sareaid;
	}
	public void setSareaid(Integer sareaid) {
		this.sareaid = sareaid;
	}
	public String getSremark() {
		return sremark;
	}
	public void setSremark(String sremark) {
		this.sremark = sremark;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getResoult() {
		return resoult;
	}
	public void setResoult(String resoult) {
		this.resoult = resoult;
	}
	public Integer getCheckveterinarian() {
		return checkveterinarian;
	}
	public void setCheckveterinarian(Integer checkveterinarian) {
		this.checkveterinarian = checkveterinarian;
	}
	public Integer getCheckprotector() {
		return checkprotector;
	}
	public void setCheckprotector(Integer checkprotector) {
		this.checkprotector = checkprotector;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTreatmenttype() {
		return treatmenttype;
	}
	public void setTreatmenttype(Integer treatmenttype) {
		this.treatmenttype = treatmenttype;
	}
	public String getInremark() {
		return inremark;
	}
	public void setInremark(String inremark) {
		this.inremark = inremark;
	}
	public String getOutremark() {
		return outremark;
	}
	public void setOutremark(String outremark) {
		this.outremark = outremark;
	}
	public String getAddsy() {
		return addsy;
	}
	public void setAddsy(String addsy) {
		this.addsy = addsy;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getLhao() {
		return lhao;
	}
	public void setLhao(String lhao) {
		this.lhao = lhao;
	}
	public String getOutroom() {
		return outroom;
	}
	public void setOutroom(String outroom) {
		this.outroom = outroom;
	}
	public String getHokeeper() {
		return hokeeper;
	}
	public void setHokeeper(String hokeeper) {
		this.hokeeper = hokeeper;
	}
	public String getTreatveterinarian() {
		return treatveterinarian;
	}
	public void setTreatveterinarian(String treatveterinarian) {
		this.treatveterinarian = treatveterinarian;
	}
	public String getInveterinarian() {
		return inveterinarian;
	}
	public void setInveterinarian(String inveterinarian) {
		this.inveterinarian = inveterinarian;
	}
	public String getInkeeper() {
		return inkeeper;
	}
	public void setInkeeper(String inkeeper) {
		this.inkeeper = inkeeper;
	}
	public String getOutkeeper() {
		return outkeeper;
	}
	public void setOutkeeper(String outkeeper) {
		this.outkeeper = outkeeper;
	}
	
}
