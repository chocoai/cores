package com.lanen.model.contract;

public class TblCustomer_contract {
	private String contractCode;        //合同编号
	private String contractName;        //合同名
	private String id;                  //客户id
	private String customerName;		// 客户名称
	private int    customerType;           //客户类型
	private String address;				// 地址
	private String linkman;				// 联系人
	private String tel;					// 电话
	private String mobile;				// 手机
	private String email;				// 电邮
	private String http;				// 网址
	private String fax;					// 传真
	private String postalCode;			// 邮政编码
	private int deleteFlag;				// 删除标记        1:已删除     0：正常（默认）
	private String tiCode;				// 主要产品类型
	private int contractMark;           // 是否有合同标记 0：表示有 ，1：表示没有
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHttp() {
		return http;
	}
	public void setHttp(String http) {
		this.http = http;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getTiCode() {
		return tiCode;
	}
	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}
	public int getContractMark() {
		return contractMark;
	}
	public void setContractMark(int contractMark) {
		this.contractMark = contractMark;
	}
	
}
