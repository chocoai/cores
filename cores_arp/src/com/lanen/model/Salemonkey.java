package com.lanen.model;

import java.io.Serializable;

public class Salemonkey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1633813057769921417L;
    private long id;   //已售出猴子表ID
    private Long sale_id;  //订单表ID
    private String monkeyid;  //猴子编号
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getSale_id() {
		return sale_id;
	}
	public void setSale_id(Long saleId) {
		sale_id = saleId;
	}
	public String getMonkeyid() {
		return monkeyid;
	}
	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}
    
}
