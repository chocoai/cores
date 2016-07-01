package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictQACheckContentTable;

public interface DictQACheckContentTableService extends BaseDao<DictQACheckContentTable> {
	/**
	 * 根据检查表id获取检查内容
	 * @param chkTblId
	 * @return
	 */
	List<DictQACheckContentTable> getByChkTblId(String chkTblId);
	/**
	 * 获取最大的序号
	 * @param chkTblId
	 * @return
	 */
	Integer getMaxSnByChkTblId(String chkTblId);
	/**
	 * 保存之前判断是否已经存在
	 * @param chkTblId
	 * @param chkContent
	 * @return
	 */
	boolean isExistChkTblAndContent(String chkTblId,String chkContent);
	/**
	 * 获取表里面有多少条内容
	 * @param chkTblId
	 * @return
	 */
	Integer getSizeByTable(String chkTblId);

}
