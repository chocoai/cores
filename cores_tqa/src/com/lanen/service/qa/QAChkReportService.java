package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkReport;

public interface QAChkReportService extends BaseDao<QAChkReport>{
	
	/**
	 * 获取专题的当前最高版本key
	 * @param dateStr
	 * @return
	 */
	String getKeyByStudyNo(String dateStr);
	
	/**
	 * 查找特定日期中的特定检查索引下的还没有写如报告的检查记录
	 * @param studyNoParam
	 * @param startChkDate
	 * @param qaIndexIds
	 * @return
	 */
	List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(String studyNoParam,Date startChkDate,Date endChkDate,String[] qaIndexId);
	/**
	 * 
	 * @param studyNoParam
	 * @param start
	 * @param end
	 * @param status
	 * @param catalog
	 * @param searchString
	 * @return
	 */
	List<Map<String, Object>> getNeedDoByCondition(String studyNoParam,User user,Integer status,Integer catalog,String searchString);
	/**
	 * 根据报告编号获取报告的专题编号和专题名称
	 * @param reportIds
	 * @return
	 */
	List<Map<String,Object>> getStudyInfoByReportCodeList(String[] reportIds);
	
	/**
	 * 
	 * @return
	 */
	List<Map<String, Object>> getByStudyNo(String studyNoParam);
	/**
	 * 
	 * @param reportStartDate
	 * @param reportEndDate
	 * @param reportStatus
	 * @param reportCatalog
	 * @param reportSearcher
	 * @return
	 */
	List<Map<String, Object>> getByConditions(Date reportStartDate,Date reportEndDate,Integer reportStatus,Integer reportCatalog,String reportSearcher);

}
