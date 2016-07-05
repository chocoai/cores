package com.lanen.model;

import java.io.Serializable;
import java.util.Date;

public class Salemonkey_Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5426774464724443900L;
	    private long id;   //已出场猴子表ID
		//private BigInteger id;
	    private Long sale_id;  //订单表ID
	    private String monkeyid;  //猴子编号
	    private Date outdate;    //出场日期
	    private Long monkeytype;       //动物类型
	    private String typeName;       //类型名
	    private String title;          //订单名（编号）
	    private Long approveserial;  //销售许可证表ID
	    private String aphao;        //销售许可批号
	    private Long trance;         //运输许可证表ID
	    private String tphao;        //运输许可批号
	    private Long saletype;         //销售类型 15：内销，16：外销，17：实验
	    private String saletypeName;   //销售类型 
	    private Long boss;             //主管（负责人）
	    private String bossName;       //主管名
	    private String salecount;      //订单动物数量
	    private String remark;         //备注
	    private String tiaojian;       //动物选择条件
	    private int addmark;           //添加标志，是否可以添加出场记录 0:可以添加，1.不可以
		//public long getId() {
		//	return id;
		//}
		//public void setId(long id) {
		//	this.id = id;
		//}
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
		public Date getOutdate() {
			return outdate;
		}
		public void setOutdate(Date outdate) {
			this.outdate = outdate;
		}
		public Long getMonkeytype() {
			return monkeytype;
		}
		public void setMonkeytype(Long monkeytype) {
			this.monkeytype = monkeytype;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Long getApproveserial() {
			return approveserial;
		}
		public void setApproveserial(Long approveserial) {
			this.approveserial = approveserial;
		}
		public String getAphao() {
			return aphao;
		}
		public void setAphao(String aphao) {
			this.aphao = aphao;
		}
		public Long getTrance() {
			return trance;
		}
		public void setTrance(Long trance) {
			this.trance = trance;
		}
		public String getTphao() {
			return tphao;
		}
		public void setTphao(String tphao) {
			this.tphao = tphao;
		}
		public Long getSaletype() {
			return saletype;
		}
		public void setSaletype(Long saletype) {
			this.saletype = saletype;
		}
		public Long getBoss() {
			return boss;
		}
		public void setBoss(Long boss) {
			this.boss = boss;
		}
		public String getSalecount() {
			return salecount;
		}
		public void setSalecount(String salecount) {
			this.salecount = salecount;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public void setTiaojian(String tiaojian) {
			this.tiaojian = tiaojian;
		}
		public String getTiaojian() {
			return tiaojian;
		}
		public void setBossName(String bossName) {
			this.bossName = bossName;
		}
		public String getBossName() {
			return bossName;
		}
		public void setSaletypeName(String saletypeName) {
			this.saletypeName = saletypeName;
		}
		public String getSaletypeName() {
			return saletypeName;
		}
		public void setAddmark(int addmark) {
			this.addmark = addmark;
		}
		public int getAddmark() {
			return addmark;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
}
