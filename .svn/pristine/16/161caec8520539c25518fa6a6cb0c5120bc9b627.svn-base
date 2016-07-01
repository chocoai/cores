package com.lanen.service.qa;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkIndex;

public interface QAChkIndexService extends BaseDao<QAChkIndex> {
	/**
	 * 获取检查计划下的某个检查项的索引
	 * @param chkPlanId
	 * @param chkItemId
	 * @return
	 */
	QAChkIndex isExistByChkPlanIdAndChkItemId(String chkPlanId,String chkItemName);
	/**
	 * 
	 * @param startChkDate
	 * @param endChkDate
	 * @return
	 */
	List<QAChkIndex> getByTime(Date startChkDate,Date endChkDate);
	/**
	 * 
	 * @return
	 */
	List<QAChkIndex> getByStudyNo(String studyNoParam);
	
	/**
	 * 
	 */
	List<String> getByReport(String chkReportCode);
	/**
	 * 
	 */
	List<QAChkIndex> getByReportCode(String chkReportCode);
	/**
	 * 获取报告的专题编号
	 * @param chkReportCode
	 * @return
	 */
	String getStudyNoByReportCode(String chkReportCode);
	/**
	 * 获取报告的类型
	 * @param chkReportCode
	 * @return
	 */
	Integer getChkTypeByReportCode(String chkReportCode);
}
