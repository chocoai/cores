package com.lanen.model.studyplan;

import java.io.Serializable;

/**
 * 字典-血常规
 * @author Administrator
 *
 */
public class DictHemat   implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3991904982428084110L;
	private String name;         //名称
	private String abbr;         //错写
	private int precision;       //进度（小数位）
	private String unit;         //单位
	private int orderNo;		//排序
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	

}
