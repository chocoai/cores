package com.lanen.model.studyplan;

/**
 * 打印报表时的检查项目临时实体
 * @author shen.dong
 *
 */
public class TempClinicalTestIndexBean {

	/**
	 * 检查名称
	 */
	private String indexName;
	
	/**
	 * 检查项目
	 */
	private String indexItems;

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexItems() {
		return indexItems;
	}

	public void setIndexItems(String indexItems) {
		this.indexItems = indexItems;
	}
	
	
}
