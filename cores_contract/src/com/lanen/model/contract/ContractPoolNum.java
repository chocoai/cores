package com.lanen.model.contract;

import java.io.Serializable;

/**
 * 流水号 
 * @author Administrator
 *
 */
//TODO
public class ContractPoolNum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3429394084433538451L;
	
	private Long  id;         //主键  ，1： SD任命的编号
	private String currentYear;  //当前 年份    2014
	private String prefix;       //前缀（2014- _ _）
	private int serialNumLen;             //几位流水号      3
	private String currentSerialNum;      //当前流水号      001，超过三位时，用四位
	private int kind ;//流水号类别 1合同
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getSerialNumLen() {
		return serialNumLen;
	}
	public void setSerialNumLen(int serialNumLen) {
		this.serialNumLen = serialNumLen;
	}
	public String getCurrentSerialNum() {
		return currentSerialNum;
	}
	public void setCurrentSerialNum(String currentSerialNum) {
		this.currentSerialNum = currentSerialNum;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	

}
