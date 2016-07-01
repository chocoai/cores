package com.lanen.model.studyplan;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 体重表
 * @author Administrator
 *
 */
public class TblWeighInd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1200519545917626499L;
	
	private String id;
	private String StudyNo;              //课题编号
	private int WeighSn;                //序号    1,2,3,4,5....
	private String WeightUnit;  //重量单位
	private Date WeighDate;   //称重日期
	private String SignStatus;  //签字状态
	private int VerStatus; //复核状态
	private int InputMode; //数据录入方式
	private String BalCode;//天平编号
	private String HostName;//计算机编号
	
	private String WeighDate1;   //称重日期
	
	private String Audit;//审核人
	
	private String Review;//复核人
	
	
	public String getAudit() {
		return Audit;
	}

	public void setAudit(String audit) {
		Audit = audit;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	}

	public String getWeighDate1() {
		return WeighDate1;
	}

	public void setWeighDate1(String weighDate1) {
		WeighDate1 = weighDate1;
	}

	//配置申请领用日期
	private Set<TbLWeighData> tbLWeighDatas = new HashSet<TbLWeighData>();
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudyNo() {
		return StudyNo;
	}

	public void setStudyNo(String studyNo) {
		StudyNo = studyNo;
	}

	public int getWeighSn() {
		return WeighSn;
	}

	public void setWeighSn(int weighSn) {
		WeighSn = weighSn;
	}

	public String getWeightUnit() {
		return WeightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		WeightUnit = weightUnit;
	}

	public Date getWeighDate() {
		return WeighDate;
	}

	public void setWeighDate(Date weighDate) {
		WeighDate = weighDate;
	}

	public String getSignStatus() {
		return SignStatus;
	}

	public void setSignStatus(String signStatus) {
		SignStatus = signStatus;
	}

	public int getVerStatus() {
		return VerStatus;
	}

	public void setVerStatus(int verStatus) {
		VerStatus = verStatus;
	}

	public int getInputMode() {
		return InputMode;
	}

	public void setInputMode(int inputMode) {
		InputMode = inputMode;
	}

	public String getBalCode() {
		return BalCode;
	}

	public void setBalCode(String balCode) {
		BalCode = balCode;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public Set<TbLWeighData> getTbLWeighDatas() {
		return tbLWeighDatas;
	}

	public void setTbLWeighDatas(Set<TbLWeighData> tbLWeighDatas) {
		this.tbLWeighDatas = tbLWeighDatas;
	}

    

	
}
