package com.lanen.model.studyplan;

import java.io.Serializable;

/**
 * 字典—剂量单位
 * @author Administrator
 *
 */

public class DictDoseUnit   implements Serializable  {
	
	private static final long serialVersionUID = 3559197852105197462L;
	private String name;        //名称
	private String abbr;        //缩写
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
