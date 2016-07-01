package com.lanen.model.path;

import java.io.Serializable;

/**
 * 病理计划-脏器/组织学检查版本历史表
 * @author 曾锋
 *
 */
public class TblPathPlanCheckVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5661063554719027130L;
    private String id;         //记录ID
    private String studyNo;    //课题编号
    private int visceraType; //脏器类型
    private String visceraCode;  //脏器编号
    private String visceraName;  //脏器名称
    private int gender;      //所属动物性别
    private int atanomyCheckFlag;  //是否需要剖检
    private int visceraFixedFlag;   //是否需要固定
    private int histopathCheckFlag; //是否需要镜检
    private int sn;          //序号
    private Integer version;            //版本号
    
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
	public int getVisceraType() {
		return visceraType;
	}
	public void setVisceraType(int visceraType) {
		this.visceraType = visceraType;
	}
	public String getVisceraCode() {
		return visceraCode;
	}
	public void setVisceraCode(String visceraCode) {
		this.visceraCode = visceraCode;
	}
	public String getVisceraName() {
		return visceraName;
	}
	public void setVisceraName(String visceraName) {
		this.visceraName = visceraName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAtanomyCheckFlag() {
		return atanomyCheckFlag;
	}
	public void setAtanomyCheckFlag(int atanomyCheckFlag) {
		this.atanomyCheckFlag = atanomyCheckFlag;
	}
	public int getHistopathCheckFlag() {
		return histopathCheckFlag;
	}
	public void setHistopathCheckFlag(int histopathCheckFlag) {
		this.histopathCheckFlag = histopathCheckFlag;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public void setVisceraFixedFlag(int visceraFixedFlag) {
		this.visceraFixedFlag = visceraFixedFlag;
	}
	public int getVisceraFixedFlag() {
		return visceraFixedFlag;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}