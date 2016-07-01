package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkReportRecord;

public interface QAChkReportRecordService extends BaseDao<QAChkReportRecord> {
	/**
	 * 
	 * @param chkReportCode
	 * @return
	 */
	List<QAChkReportRecord> getByReportCode(String chkReportCode);
	/**
	 * 
	 * @param reportIds
	 * @return
	 */
	List<QAChkReportRecord> getByReportCodes(String[] reportIds);
	/**
	 * 
	 * @param chkReportCode
	 * @return
	 */
	List<QAChkReportRecord> getReChkByReportCode(String chkReportCode);
	/**
	 * 
	 * @param chkReportCode
	 * @return
	 */
	List<QAChkReportRecord> getReChkByReportCodes(String[] chkReportCode);
	
	/**
	 * 
	 * @param chkRecordId
	 * @return
	 */
	QAChkReportRecord getByChkRecordId(String chkRecordId);
	
	/**
	 * 获取延迟整改符合条件的计划在检查信息
	 * @param studyNoParam
	 * @param start
	 * @param end
	 * @param status
	 * @param catalog
	 * @param searchString
	 * @return
	 */
	List<QAChkReportRecord> getByCondition(String studyNoParam,Date start, Date end, Integer status, Integer catalog,String searchString);
}
