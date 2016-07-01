package com.lanen.model.archive;

/**
 * DictSoptype entity. @author MyEclipse Persistence Tools
 */

public class DictArchivePosition implements java.io.Serializable {

	// Fields

	private String id;
	private String positionName;
	private String pid;
	private Integer sn;

	// Constructors

	/** default constructor */
	public DictArchivePosition() {
	}

	/** minimal constructor */
	public DictArchivePosition(String id) {
		this.id = id;
	}

	/** full constructor */
	public DictArchivePosition(String id, String positionName,
			String pid, Integer sn) {
		this.id = id;
		this.positionName = positionName;
		this.pid = pid;
		this.sn = sn;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

}