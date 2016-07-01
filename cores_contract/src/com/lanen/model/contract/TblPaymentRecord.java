package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同付款记录
 * @author Administrator
 *
 */
public class TblPaymentRecord implements Serializable {

	private static final long serialVersionUID = -6036294813200532263L;
	// ID
	private String id;
	
	private String contractCode;		// 合同编号
	private Date paymentDate;			// 付款日期
	private String amount;			    // 付款金额
	private int priceUnit;           // 付款金额单位
	private int receiptFlag;			// 是否开具发票
	private String operator;			// 操作者
	private Date operateTime;			// 操作时间
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

	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getReceiptFlag() {
		return receiptFlag;
	}
	public void setReceiptFlag(int receiptFlag) {
		this.receiptFlag = receiptFlag;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public void setPriceUnit(int priceUnit) {
		this.priceUnit = priceUnit;
	}
	public int getPriceUnit() {
		return priceUnit;
	}
	

}
