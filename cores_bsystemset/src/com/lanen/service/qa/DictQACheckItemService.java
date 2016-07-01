package com.lanen.service.qa;


import java.util.List;

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
	 * 获取所有并按照类别排序
	 * @return
	 */
	List<DictQACheckItem> getAll();
	/**
	 * 
	 * @param chkItemType
	 * @return
	 */
	List<DictQACheckItem> getByType(Integer chkItemType);
	
	/**
	 * 
	 * @param chkItemId
	 * @return
	 */
	Integer getTblConSizeByItem(String chkItemId);
}
