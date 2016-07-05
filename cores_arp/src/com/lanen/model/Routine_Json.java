package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Routine_Json implements Serializable{

	/**
	 * 常规检疫
	 */
	private static final long serialVersionUID = -3174163748392653579L;
	
	private Integer id;
	private String monkeyid;
	private String surface;//体表
	private String parasite;//寄生虫
	private String virus;//病毒
	private String bacteria;//细菌
	private String vaccine;//疫苗
	private String infectious;//传染病
	private String tb;
	private String x;
	private String xcg;
	private String xysh;//血液生化
	
	private Date checkdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonkeyid() {
		return monkeyid;
	}
	public void setMonkeyid(String monkeyid) {
		this.monkeyid = monkeyid;
	}
	
	public String getParasite() {
		return parasite;
	}
	public void setParasite(String parasite) {
		this.parasite = parasite;
	}
	public String getVirus() {
		return virus;
	}
	public void setVirus(String virus) {
		this.virus = virus;
	}
	
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getBacteria() {
		return bacteria;
	}
	public void setBacteria(String bacteria) {
		this.bacteria = bacteria;
	}
	public String getVaccine() {
		return vaccine;
	}
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	public String getInfectious() {
		return infectious;
	}
	public void setInfectious(String infectious) {
		this.infectious = infectious;
	}
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = tb;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getXcg() {
		return xcg;
	}
	public void setXcg(String xcg) {
		this.xcg = xcg;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public Date getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	
	
	
	
	
}
