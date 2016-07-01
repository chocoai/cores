package com.lanen.service.qa;


import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictQACheckTable;


public interface DictQACheckTableService  extends BaseDao<DictQACheckTable> {
	/**
	 * 根据编号和检查表名称模糊查询
	 * @param chkTblCode
	 * @param chkTblName
	 * @return
	 */
	List<DictQACheckTable> getByChkTblCodeAndName(String chkTblCode,String chkTblName);
	/**
	 * 插入之前判断是否存在
	 * @param chkTblCode
	 * @param chkTblName
	 * @return
	 */
	boolean isExistByCodeAndName(String chkTblCode,String chkTblName);
}
