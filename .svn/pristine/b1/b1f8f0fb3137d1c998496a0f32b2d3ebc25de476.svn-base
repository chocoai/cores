package com.lanen.service.qa;


import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictQACheckItem;

public interface DictQACheckItemService extends BaseDao<DictQACheckItem> {
	
	boolean isExistByTypeAndName( Integer chkItemType,String chkItemName);
	
	/**
	 * 根据类型和名字查找检查项
	 * @param chkItemName
	 * @return
	 */
	DictQACheckItem getByItemName(Integer chkItemType,String chkItemName);
	/**
	 * 
	 * @param chkItemId
	 * @return
	 */
	Integer getTblConSizeByItem(String chkItemId);
}
