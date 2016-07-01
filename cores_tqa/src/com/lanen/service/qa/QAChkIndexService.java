package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkIndex;

public interface QAChkIndexService extends BaseDao<QAChkIndex> {
	/**
	 * 获取检查计划下的某个检查项的索引
	 * @param chkPlanId
	 * @param chkItemId
	 * @return
	 */
	QAChkIndex isExistByChkPlanIdAndChkItemName(String chkPlanId,String chkItemName);
	/**
	 * 
	 * @param startChkDate
	 * @param endChkDate
	 * @return
	 */
	List<QAChkIndex> getByStudyNoAndTime(String studyNo,Date startChkDate,Date endChkDate,User user);
	/**
	 * 
	 * @return
	 */
	List<QAChkIndex> getByStudyNo(String studyNoParam);
	/**
	 * 
	 * @param reportStartDate
	 * @param reportEndDate
	 * @param reportStatus
	 * @param reportCatalog
	 * @param reportSearcher
	 * @return
	 */
	List<QAChkIndex> getByConditions(Date reportStartDate,Date reportEndDate,Integer reportStatus,Integer reportCatalog,String reportSearcher);
	/**
	 * 
	 */
	List<String> getByReport(String chkReportCode,Date startChkDate,Date endChkDate,User user);
	/**
	 * 
	 */
	List<String> getByReports(String[] reportIds,User user);
	/**
	 * 
	 * @param studyNo
	 * @param chkType
	 * @return
	 */
	QAChkIndex isExistByStudyNoChkTypeAndChkItemName(String studyNo,Integer chkType,String chkItemName);
	/**
	 * 
	 * @param studyNoParam
	 * @param realName
	 * @return
	 */
	List<QAChkIndex> getByStudyNoAndUser(String studyNoParam,Date startChkDate,Date endChkDate,String realName);

	/**
	 * 
	 */
	List<QAChkIndex> getByReportCode(String chkReportCode);
	/**
	 * 
	 */
	List<QAChkIndex> getByReportCodes(String[] chkReportCode);
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
	/**
	 * 
	 * @param chkIndexIdList
	 * @return
	 */
	List<QAChkIndex> getNoSignByIds(String[] chkIndexIdList);
	/**
	 * 
	 * @param chkItemId
	 * @return
	 */
	List<Map<String, Object>> getNoFinTableByItem(String chkItemId);
}
