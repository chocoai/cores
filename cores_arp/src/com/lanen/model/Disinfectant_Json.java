package com.lanen.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Disinfectant_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8938743608636458664L;
	private Long id;     //消毒液表主键ID
    private String disinfectantCode;//消毒液编号
    private Date validdate;    //有效期
    private Long createdBy;    //消毒液配置者（ID）
    private Date createdDate;  //配置日期
    private String creator;    //配置者
    private int isUsed;        //是否已使用过。0：未使用   1.已使用
    private List<Disinfectantmaterial> dmList;//配方列表
    private String dmLiString;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDisinfectantCode() {
		return disinfectantCode;
	}
	public void setDisinfectantCode(String disinfectantCode) {
		this.disinfectantCode = disinfectantCode;
	}
	public Date getValiddate() {
		return validdate;
	}
	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator() {
		return creator;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setDmliList(List<Disinfectantmaterial> dmliList) {
		this.dmList = dmliList;
	}
	public List<Disinfectantmaterial> getDmliList() {
		return dmList;
	}
	public void setDmLiString(String dmLiString) {
		this.dmLiString = dmLiString;
	}
	public String getDmLiString() {
		return dmLiString;
	}
    
}
