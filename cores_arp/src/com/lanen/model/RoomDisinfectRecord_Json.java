package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class RoomDisinfectRecord_Json implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 934342394922536401L;
	private Long id;               //房间消毒记录表主键
    private Long area_id;          //区域ID（用于获取房间和所属区域信息）
    private Long disinfectant_id;  //消毒液ID
    private Date disinfectDate;    //消毒
    private Long operator_id;      //操作者ID
    private String disinfectType;  //消毒方式
    private Long recorder_id;      //记录者ID
    private String roomname;       //房间号
    private String areaname;       //所属区域名
    private String disinfectantCode; //消毒液编号
    private String operator;         //操作者
    private String recorder;         //记录者
    private Long   blongarea;        //所属区域ID
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArea_id() {
		return area_id;
	}
	public void setArea_id(Long areaId) {
		area_id = areaId;
	}
	public Long getDisinfectant_id() {
		return disinfectant_id;
	}
	public void setDisinfectant_id(Long disinfectantId) {
		disinfectant_id = disinfectantId;
	}
	public Date getDisinfectDate() {
		return disinfectDate;
	}
	public void setDisinfectDate(Date disinfectDate) {
		this.disinfectDate = disinfectDate;
	}
	public Long getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(Long operatorId) {
		operator_id = operatorId;
	}
	public String getDisinfectType() {
		return disinfectType;
	}
	public void setDisinfectType(String disinfectType) {
		this.disinfectType = disinfectType;
	}
	public Long getRecorder_id() {
		return recorder_id;
	}
	public void setRecorder_id(Long recorderId) {
		recorder_id = recorderId;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getDisinfectantCode() {
		return disinfectantCode;
	}
	public void setDisinfectantCode(String disinfectantCode) {
		this.disinfectantCode = disinfectantCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public void setBlongarea(Long blongarea) {
		this.blongarea = blongarea;
	}
	public Long getBlongarea() {
		return blongarea;
	}
	
}
