package com.lanen.model.schedule;

import java.io.Serializable;

/**
 * 动物房资源设置
 * @author Administrator
 *
 */
public class TblAnimalHouse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1499477180140639696L;
	private String id;			//资源ID
	private int resKind;		//类型  1：建筑   2：楼层（区域） 3：房间号
	private String resName;		//资源名称
	private String validFlag;	//有效标志
	private String parentId;	//父级Id
	private int orderNo;		//顺序
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getResKind() {
		return resKind;
	}
	public void setResKind(int resKind) {
		this.resKind = resKind;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
