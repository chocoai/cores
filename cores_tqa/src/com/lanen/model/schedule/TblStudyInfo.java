package com.lanen.model.schedule;

import java.io.Serializable;

public class TblStudyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -596573715582248036L;
	
	private String id;
	
	private String studyNo;//专题编号
	
	private String resID;//资源id
	
	private String  scheduleReviewSignID;//签字id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public String getResID() {
		return resID;
	}

	public void setResID(String resID) {
		this.resID = resID;
	}

	public String getScheduleReviewSignID() {
		return scheduleReviewSignID;
	}

	public void setScheduleReviewSignID(String scheduleReviewSignID) {
		this.scheduleReviewSignID = scheduleReviewSignID;
	}
	
	

}
