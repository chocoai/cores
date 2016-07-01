package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;

public class TblClinicalTestReq_json  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1305180726426935819L;
	private String id;                     //主键，自增长
	private String studyNo;              //课题编号
	private int reqNo;                   //申请编号    1,2,3,4,5....
	private String testPhase;            //试验阶段
	private Date beginDate;              //计划检查开始日期
	private Date endDate;                //计划检查结束日期
	private String testOther;            //其他检查项目
	private String remark;               //备注
	private Date createDate;             //创建日期
	private int  es  ;                   //  0,为签字   1，签字
	private int temp;                    //  0,临时   ，1  ，非临时
	
	private int parentReqNo;             //  0 无父申请， 

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

	public int getReqNo() {
		return reqNo;
	}

	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}

	public String getTestPhase() {
		return testPhase;
	}

	public void setTestPhase(String testPhase) {
		this.testPhase = testPhase;
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

	public String getTestOther() {
		return testOther;
	}

	public void setTestOther(String testOther) {
		this.testOther = testOther;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getEs() {
		return es;
	}

	public void setEs(int es) {
		this.es = es;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getParentReqNo() {
		return parentReqNo;
	}

	public void setParentReqNo(int parentReqNo) {
		this.parentReqNo = parentReqNo;
	}
	
	

}
