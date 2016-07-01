package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkTblReg;

public interface QAChkTblRegService extends BaseDao<QAChkTblReg>{
	/**
	 * 
	 * @param qaIndexId
	 * @param chkTblCode
	 * @param chkTblName
	 * @return
	 */
	QAChkTblReg getByChkIndexAndTblCodeAndName(String qaIndexId,String chkTblCode,String chkTblName);
	/**
	 * 
	 * @param chkIndexId
	 * @return
	 */
	Integer getMaxSnByQachkIndex(String chkIndexId);
	
	/**
	 * 
	 */
	List<QAChkTblReg> getByChkIndexId(String chkIndexId);
	
	/**
	 * 
	 * @param chkIndexId
	 * @return
	 */
	Integer getSizeByChkIndexId(String chkIndexId);
}
