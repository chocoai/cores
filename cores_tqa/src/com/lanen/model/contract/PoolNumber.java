package com.lanen.model.contract;

import java.io.Serializable;

/**
 * 流水号池（暂时仅包括   SD任命的编号）
 * @author 黄国刚
 *
 */
public class PoolNumber implements Serializable{
	private static final long serialVersionUID = 6717555011079615065L;
	
	
	private Long  id;         //主键  ，1： SD任命的编号
	private String currentYear;  //当前 年份    2014
	private String prefix;       //前缀（2014- _ _）
	private int serialNumLen;             //几位流水号      3
	private String currentSerialNum;      //当前流水号      001，超过三位时，用四位
	public Long getId() {
		return id;
	}
	public void setId(Long item) {
		this.id = item;
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
	
	
}
