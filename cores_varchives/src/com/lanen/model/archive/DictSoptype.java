package com.lanen.model.archive;

/**
 * DictSoptype entity. @author MyEclipse Persistence Tools
 */

public class DictSoptype implements java.io.Serializable {

	// Fields

	private String id;
	private String soptypeCode;
	private String sopname;
	private String pid;
	private Integer sn;

	// Constructors

	/** default constructor */
	public DictSoptype() {
	}

	/** minimal constructor */
	public DictSoptype(String id) {
		this.id = id;
	}

	/** full constructor */
	public DictSoptype(String id, String soptypeCode, String sopname,
			String pid, Integer sn) {
		this.id = id;
		this.soptypeCode = soptypeCode;
		this.sopname = sopname;
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

	public String getSoptypeCode() {
		return this.soptypeCode;
	}

	public void setSoptypeCode(String soptypeCode) {
		this.soptypeCode = soptypeCode;
	}

	public String getSopname() {
		return this.sopname;
	}

	public void setSopname(String sopname) {
		this.sopname = sopname;
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