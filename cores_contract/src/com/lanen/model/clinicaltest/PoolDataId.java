package com.lanen.model.clinicaltest;

import java.io.Serializable;

/**
 * 主键池
 * @author Administrator
 *
 */
public class PoolDataId  implements Serializable {
	private static final long serialVersionUID = 6651783711022083938L;
	private String tableName;     //实体名、表明
	private String currentValue;       //当前值
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	
	

}
