package com.lanen.model.contract;

import java.io.Serializable;

public class TblTestItem_Json implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411941654552298743L;
	
    private String id;

	
	private String contractCode; 		// 合同编号
	private String tiNo;				// 供试品编号 手动录入
	private String tiCode;				// 供试品编码 从字典读取
	private String tiName;				// 供试品名称
	private String tiType;				// 供试品类别
	private String content;				// 含量
	private String sealNo;				// 封样号
	private String fileNo;				// 备案号
	private String physical;			// 外观
	private String meltPoint;			// 熔点
	private String boilPoint;			// 沸点
	private String photolysis;			// 光解性
	private String volatility;			// 挥发性
	private String density;				// 相对密度
	private String waterSolubility;		// 水中溶解度
	private String waterStability;		// 水中稳定性
	private String solventSolubility;	// 有机溶剂溶解度
	private String solventStability;	// 有机溶剂稳定性
	private String ph;					// PH值
	private String securityMeasures;	// 特殊安全防护措施
	private String analysis;			// 稳定性和均一性分析
	private String postTreatment;		// 样品检测后处理
	private String composition;			// 成分
	private String cas;					// CAS
	private String storageCondition;	// 存储条件
	private String validityPeriod;		// 有效期限
	
	
	private int sponsorIsVender;		// 委托方即厂家
	private String venderId;			// 厂家ID
	private String venderName;			// 厂家名称
	private String venderAddress;		// 厂家地址
	private String venderLinkman;		// 厂家联系人
	private String venderTel;			// 厂家电话
	private String venderMobile;		// 厂家手机
	private String venderEmail;			// 厂家Email
	private String venderFax;			// 厂家Fax
	private int testItemState;//供试品状态  0：未生效，1：执行中，2：完成，-1：终止
	private String studyItemCount;           //委托试验个数
	private String confirmSign;//确认签字
	
	private String reserveNum;			//留样量20
	private String reserveUnit;			//留样单位40

	public String getConfirmSign() {
		return confirmSign;
	}

	public void setConfirmSign(String confirmSign) {
		this.confirmSign = confirmSign;
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

	public String getTiNo() {
		return tiNo;
	}

	public void setTiNo(String tiNo) {
		this.tiNo = tiNo;
	}

	public String getTiCode() {
		return tiCode;
	}

	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}

	public String getTiName() {
		return tiName;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public String getTiType() {
		return tiType;
	}

	public void setTiType(String tiType) {
		this.tiType = tiType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSealNo() {
		return sealNo;
	}

	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

	public String getMeltPoint() {
		return meltPoint;
	}

	public void setMeltPoint(String meltPoint) {
		this.meltPoint = meltPoint;
	}

	public String getBoilPoint() {
		return boilPoint;
	}

	public void setBoilPoint(String boilPoint) {
		this.boilPoint = boilPoint;
	}

	public String getPhotolysis() {
		return photolysis;
	}

	public void setPhotolysis(String photolysis) {
		this.photolysis = photolysis;
	}

	public String getVolatility() {
		return volatility;
	}

	public void setVolatility(String volatility) {
		this.volatility = volatility;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getWaterSolubility() {
		return waterSolubility;
	}

	public void setWaterSolubility(String waterSolubility) {
		this.waterSolubility = waterSolubility;
	}

	public String getWaterStability() {
		return waterStability;
	}

	public void setWaterStability(String waterStability) {
		this.waterStability = waterStability;
	}

	public String getSolventSolubility() {
		return solventSolubility;
	}

	public void setSolventSolubility(String solventSolubility) {
		this.solventSolubility = solventSolubility;
	}

	public String getSolventStability() {
		return solventStability;
	}

	public void setSolventStability(String solventStability) {
		this.solventStability = solventStability;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getSecurityMeasures() {
		return securityMeasures;
	}

	public void setSecurityMeasures(String securityMeasures) {
		this.securityMeasures = securityMeasures;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getPostTreatment() {
		return postTreatment;
	}

	public void setPostTreatment(String postTreatment) {
		this.postTreatment = postTreatment;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
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

	public String getStudyItemCount() {
		return studyItemCount;
	}

	public void setStudyItemCount(String studyItemCount) {
		this.studyItemCount = studyItemCount;
	}

	public int getTestItemState() {
		return testItemState;
	}

	public void setTestItemState(int testItemState) {
		this.testItemState = testItemState;
	}

	public String getReserveNum() {
		return reserveNum;
	}

	public void setReserveNum(String reserveNum) {
		this.reserveNum = reserveNum;
	}

	public String getReserveUnit() {
		return reserveUnit;
	}

	public void setReserveUnit(String reserveUnit) {
		this.reserveUnit = reserveUnit;
	}
	

}
