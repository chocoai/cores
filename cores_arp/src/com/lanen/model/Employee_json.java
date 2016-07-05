package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Employee_json implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1204133978476357125L;
	private Long id;  //主键id
	 private String name; //真实姓名
	 private String userid; //用户名
	 private String employeeid;//
	 private String password;//密码
	 private String roles;//角色 角色暂未空
	 private String description; //描述
	 private Date dateentered;//输入日期
	 private Date datemodified;//修改日期
	 private String modifiedby;//修改者 （id）
	 private String createdby;//创建者（id）
	 private Integer positionid;//职位ID
	 private Integer departmentid;//部门
	 private String  phone;//电话
	 private String mobile;//手机
	 private String email;//电子邮件
	 private int deleted;//删除标记
	 private String image;//图片
	 private Integer sex;//性别
	 private Integer married;//已婚
	 private Integer xueli;//学历
	 private String jguan;//籍贯
	 private Integer zhic;//职称
	 private String sfz;//身份证
	 private String address;//地址
	 private Date intime;//进入时间
	 private Date bod;//生日（待定）
	 private String national;//民族
	 private String school;//学校
	 private String zye;//专业
	 private String position;
	 private String department;
	 private String xueliName;
	 private String zhicName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateentered() {
		return dateentered;
	}
	public void setDateentered(Date dateentered) {
		this.dateentered = dateentered;
	}
	public Date getDatemodified() {
		return datemodified;
	}
	public void setDatemodified(Date datemodified) {
		this.datemodified = datemodified;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Integer getPositionid() {
		return positionid;
	}
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getMarried() {
		return married;
	}
	public void setMarried(Integer married) {
		this.married = married;
	}
	public Integer getXueli() {
		return xueli;
	}
	public void setXueli(Integer xueli) {
		this.xueli = xueli;
	}
	public String getJguan() {
		return jguan;
	}
	public void setJguan(String jguan) {
		this.jguan = jguan;
	}
	public Integer getZhic() {
		return zhic;
	}
	public void setZhic(Integer zhic) {
		this.zhic = zhic;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public Date getBod() {
		return bod;
	}
	public void setBod(Date bod) {
		this.bod = bod;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getZye() {
		return zye;
	}
	public void setZye(String zye) {
		this.zye = zye;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setXueliName(String xueliName) {
		this.xueliName = xueliName;
	}
	public String getXueliName() {
		return xueliName;
	}
	public void setZhicName(String zhicName) {
		this.zhicName = zhicName;
	}
	public String getZhicName() {
		return zhicName;
	}
	
}
