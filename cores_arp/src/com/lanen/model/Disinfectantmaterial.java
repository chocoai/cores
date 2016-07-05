package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Disinfectantmaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6728742336342911610L;
    private Long id;       //消毒液配方表ID
    private String materialname; //配方原料名
    private String content;      //含量
    private String productionbatch; //生产批号
    private Date validdate;         //有效日期
    private String supplier;        //供应商
    private Long disinfectant_id;   //消毒液Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProductionbatch() {
		return productionbatch;
	}
	public void setProductionbatch(String productionbatch) {
		this.productionbatch = productionbatch;
	}
	public Date getValiddate() {
		return validdate;
	}
	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public void setDisinfectant_id(Long disinfectant_id) {
		this.disinfectant_id = disinfectant_id;
	}
	public Long getDisinfectant_id() {
		return disinfectant_id;
	}
    
    
}
