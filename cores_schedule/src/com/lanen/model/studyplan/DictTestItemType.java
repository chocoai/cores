package com.lanen.model.studyplan;


import java.io.Serializable;

/**
 * 字典-供试品类型
 * @author 黄国刚
 *
 */
public class DictTestItemType   implements Serializable   {
	
	private static final long serialVersionUID = -8237462605014350540L;
	private String tiCode ;         //供试品类型代码      （主键）
	private String tiType;          //供试品类型名称
	public String getTiCode() {
		return tiCode;
	}
	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}
	public String getTiType() {
		return tiType;
	}
	public void setTiType(String tiType) {
		this.tiType = tiType;
	}

}
