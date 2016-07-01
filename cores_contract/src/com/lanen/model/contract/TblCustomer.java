package com.lanen.model.contract;

import java.io.Serializable;

/**
 * 客户信息表
 * @author Administrator
 *
 */
public class TblCustomer implements Serializable {

	private static final long serialVersionUID = -151675390185590015L;

	private String id;
	private String customerName;		// 客户名称
	private int customerType;			// 客户类型  1 厂家 2委托方
	private String regionId;			// 地区ID   (地区表主键)
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
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
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

	
}
