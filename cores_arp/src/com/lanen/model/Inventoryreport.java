package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Inventoryreport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5467134634501664609L;
    private Long id;      //盘点记录表ID
    private String roomsum;  //房间列表
    private Date inventorydate;//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomsum() {
		return roomsum;
	}
	public void setRoomsum(String roomsum) {
		this.roomsum = roomsum;
	}
	public Date getInventorydate() {
		return inventorydate;
	}
	public void setInventorydate(Date inventorydate) {
		this.inventorydate = inventorydate;
	}
    
}
