package com.lanen.model.schedule;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源负责人
 * @author Administrator
 *
 */
public class TblResManager implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5238126121282723965L;
	private String id;         		//
	private String resId;			//自选Id
	private String resManager;		//负责人
	private Date startDate;			//开始日期	
	private Date endDate;			//结束日期
	private String signid;			//签字Id号
	private String endDateSignId;	//结束日期签字Id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResManager() {
		return resManager;
	}
	public void setResManager(String resManager) {
		this.resManager = resManager;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSignid() {
		return signid;
	}
	public void setSignid(String signid) {
		this.signid = signid;
	}
	public String getEndDateSignId() {
		return endDateSignId;
	}
	public void setEndDateSignId(String endDateSignId) {
		this.endDateSignId = endDateSignId;
	}
	
	
	

}
