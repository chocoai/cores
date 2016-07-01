package com.lanen.service.clinicaltest;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.clinicaltest.TblTrace;

public interface TblTraceService extends BaseDao<TblTrace>{
	
	
	/**
	 * 根据表名查询
	 * @param tableName
	 * @return
	 */
	List<TblTrace> getListByTableName(String tableName);
	
	/**
	 * 查询临检数据相关的  修改痕迹数据
	 * @param studyNo
	 * @param reqNo
	 * @param testItem
	 * @return
	 */
	List<TblTrace> getTblClinicalTestDataTraceListByStudyNoReqNoTestItem(String studyNo, int reqNo,
			int testItem);
	/**
	 * 保存修改痕迹列表
	 * @param tblTraceList
	 */
	void saveList(List<TblTrace> tblTraceList);
	
	/**根据表名和数据id查询修改痕迹列表
	 * @param string
	 * @param sessionId
	 * @return
	 */
	List<TblTrace> getListByTableNameAndDataId(String string, String sessionId);
}
