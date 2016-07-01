package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

public class TblAppointQA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7900077455294019140L;
	
    private String id; //主键
	
	private String contractCode;//合同编号
	
	private String studyName;//项目名称
	
	private String studyNo;//专题编号
	
	private String tINo;//供试品编码
	
	private String tIName;//供试品名称
	
	private String qaCode;// QA编号
	
	private String qa;// QA姓名
	
	private Date appointDate;//SD任命时间
	
	private String FMCode;//FM编号
	
	private Date cancelDate;//撤销时间
	
	
	private String appointSignID;//任命签字
	
	private int state;//状态
	
	private String poolNum;//流水号
	
	private int printNumber;//打印次数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public String gettINo() {
		return tINo;
	}

	public void settINo(String tINo) {
		this.tINo = tINo;
	}

	public String gettIName() {
		return tIName;
	}

	public void settIName(String tIName) {
		this.tIName = tIName;
	}

	public String getQaCode() {
		return qaCode;
	}

	public void setQaCode(String qaCode) {
		this.qaCode = qaCode;
	}

	public String getQa() {
		return qa;
	}

	public void setQa(String qa) {
		this.qa = qa;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public String getFMCode() {
		return FMCode;
	}

	public void setFMCode(String fMCode) {
		FMCode = fMCode;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getAppointSignID() {
		return appointSignID;
	}

	public void setAppointSignID(String appointSignID) {
		this.appointSignID = appointSignID;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPoolNum() {
		return poolNum;
	}

	public void setPoolNum(String poolNum) {
		this.poolNum = poolNum;
	}

	public int getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(int printNumber) {
		this.printNumber = printNumber;
	}
	
    
	
}
