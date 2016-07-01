package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

public class TblDissectPlanHis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7334152620750455598L;
	
	private String id;                     //主键，自增长
	private int dissectNum;              //解剖次数
	private String describe;             //关于实验阶段的描述
	private Date beginDate;              //开始日期
	private Date endDate;                //结束日期
	private String oldID;               //源ID
	private String tblApplyReviseID;    //申请表id
	private String operate;             // 操作
	private Date operateDate;           //修改时间
	private String  studyNo;            //课题编号
	
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDissectNum() {
		return dissectNum;
	}
	public void setDissectNum(int dissectNum) {
		this.dissectNum = dissectNum;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOldID() {
		return oldID;
	}
	public void setOldID(String oldID) {
		this.oldID = oldID;
	}
	public String getTblApplyReviseID() {
		return tblApplyReviseID;
	}
	public void setTblApplyReviseID(String tblApplyReviseID) {
		this.tblApplyReviseID = tblApplyReviseID;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDescribe() {
		return describe;
	}
	

}
