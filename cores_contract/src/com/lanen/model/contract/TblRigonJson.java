package com.lanen.model.contract;

import java.io.Serializable;
/**
 * 地区表json字符串
 * @author wan
 *
 */
public class TblRigonJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1253155363517890018L;
	
	private String id;			
	private String regionName;		// 名称
	private String pid;				// 层级
	
	private int level;      		// 1:国家   2：省 3：市
	
	private String _parentId;		//父级Id
	private String iconCls;	//图标
	private String state;   //'open','closed'
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

	

}
