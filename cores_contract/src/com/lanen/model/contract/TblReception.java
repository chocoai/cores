package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户接待记录
 * @author Administrator
 *
 */
public class TblReception implements Serializable {

	private static final long serialVersionUID = -8354550306754366792L;
	
	private String id;
	private String customerId;			// 客户编号(客户表主键)
	private String linkman;				// 客户联系人
	private String linkInformation;		// 客户联系方式
	private Date receptionTime;			// 接待日期时间
	private String contractCode;		// 相关合同编号
	private String question;			// 问题
	private String result;				// 处理结果
	private String operator;			// 接待人
	private Date recordTime;			// 记录时间
	private String state;				// 问题状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkInformation() {
		return linkInformation;
	}
	public void setLinkInformation(String linkInformation) {
		this.linkInformation = linkInformation;
	}
	public Date getReceptionTime() {
		return receptionTime;
	}
	public void setReceptionTime(Date receptionTime) {
		this.receptionTime = receptionTime;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
