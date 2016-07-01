package com.lanen.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同信息表
 * @author Administrator
 *
 */
public class TblContractHis implements Serializable {

	private static final long serialVersionUID = -8180276189042266353L;

	private String id;
	
	private String contractCode;		// 合同编号
	private String contractName;		// 合同名称
	private String sponsorId;			// 委托方ID
	private String sponsorName;			// 委托方名称
	private String sponsorAddress;		// 委托方地址
	private String sponsorLinkman;		// 委托方联系人
	private String sponsorTel;			// 委托方电话
	private String sponsorMobile;		// 委托方手机
	private String sponsorEmail;		// 委托方Email
	private String sponsorFax;			// 委托方Fax
	private int sponsorIsVender;		// 委托方即厂家 报告出具方 1是0 不是
	private String venderId;			// 厂家ID报告出具方
	private String venderName;			// 厂家名称报告出具方
	private String venderAddress;		// 厂家地址报告出具方
	private String venderLinkman;		// 厂家联系人报告出具方
	private String venderTel;			// 厂家电话报告出具方
	private String venderMobile;		// 厂家手机报告出具方
	private String venderEmail;			// 厂家Email报告出具方
	private String venderFax;			// 厂家Fax报告出具方
	private String contractPrice;		// 合同金额
	private int priceUnit;			    //合同金额单位       1：元  2：美元   3：欧元  4：万元
	private Date signingDate;			// 签订日期
	private Date effectiveDate;			// 生效日期
	private Date finishDate;			// 结束日期
	private int contractState;			// 合同状态      0：未生效，1：执行中，2：再编辑       3:完成，-1：终止
	private String operator;			// 操作者
	private String remark;				// 备注
	
	private Date submitDate; //合同提交日期 2014-11-29号添
	
	private String oldId;				//原id
	private String operateType;			//操作方式
	private Date operateTime;			//操着时间
	private String operateRsn;			//操作原因
	private String operateSign;			//操作签字
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateRsn() {
		return operateRsn;
	}
	public void setOperateRsn(String operateRsn) {
		this.operateRsn = operateRsn;
	}
	public String getOperateSign() {
		return operateSign;
	}
	public void setOperateSign(String operateSign) {
		this.operateSign = operateSign;
	}
	
	
	
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
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
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getSponsorAddress() {
		return sponsorAddress;
	}
	public void setSponsorAddress(String sponsorAddress) {
		this.sponsorAddress = sponsorAddress;
	}
	public String getSponsorLinkman() {
		return sponsorLinkman;
	}
	public void setSponsorLinkman(String sponsorLinkman) {
		this.sponsorLinkman = sponsorLinkman;
	}
	public String getSponsorTel() {
		return sponsorTel;
	}
	public void setSponsorTel(String sponsorTel) {
		this.sponsorTel = sponsorTel;
	}
	public String getSponsorMobile() {
		return sponsorMobile;
	}
	public void setSponsorMobile(String sponsorMobile) {
		this.sponsorMobile = sponsorMobile;
	}
	public String getSponsorEmail() {
		return sponsorEmail;
	}
	public void setSponsorEmail(String sponsorEmail) {
		this.sponsorEmail = sponsorEmail;
	}
	public String getSponsorFax() {
		return sponsorFax;
	}
	public void setSponsorFax(String sponsorFax) {
		this.sponsorFax = sponsorFax;
	}
	public int getSponsorIsVender() {
		return sponsorIsVender;
	}
	public void setSponsorIsVender(int sponsorIsVender) {
		this.sponsorIsVender = sponsorIsVender;
	}

	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}

	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getVenderAddress() {
		return venderAddress;
	}
	public void setVenderAddress(String venderAddress) {
		this.venderAddress = venderAddress;
	}
	public String getVenderLinkman() {
		return venderLinkman;
	}
	public void setVenderLinkman(String venderLinkman) {
		this.venderLinkman = venderLinkman;
	}
	public String getVenderTel() {
		return venderTel;
	}
	public void setVenderTel(String venderTel) {
		this.venderTel = venderTel;
	}
	public String getVenderMobile() {
		return venderMobile;
	}
	public void setVenderMobile(String venderMobile) {
		this.venderMobile = venderMobile;
	}
	public String getVenderEmail() {
		return venderEmail;
	}
	public void setVenderEmail(String venderEmail) {
		this.venderEmail = venderEmail;
	}
	public String getVenderFax() {
		return venderFax;
	}
	public void setVenderFax(String venderFax) {
		this.venderFax = venderFax;
	}
	public String getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getContractState() {
		return contractState;
	}
	public void setContractState(int contractState) {
		this.contractState = contractState;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(int priceUnit) {
		this.priceUnit = priceUnit;
	}
	
}