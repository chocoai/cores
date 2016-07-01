package com.lanen.model.company;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同附件表
 * @author Administrator
 *
 */
public class CompanyInfo implements Serializable {

	private static final long serialVersionUID = -6785055558976273750L;
	
	private String id;
    
	private String companyName;		// 公司名称
	private String imgName;			//logo名称
	private byte[] companyLogo;		// 公司logo
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
	
	

}
