package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkPlan;

public interface QAChkPlanService extends BaseDao<QAChkPlan>{
	/**
	 * 获取专题的所有计划
	 * @param studyNo
	 * @return
	 */
	List<QAChkPlan> getByStudyNo(String studyNo);
	/**
	 * 根据选择条件获取检查计划
	 * @param studyNoParam
	 * @param start
	 * @param end
	 * @param status
	 * @param catalog
	 * @return
	 */
	List<Map<String,Object>> getByStudyNoAndCondition(String studyNoParam,Date start,Date end,Integer status,Integer catalog,String searchString);
	/**
	 * 获取专题的所有计划日期
	 * @param studyNo
	 * @return
	 */
	List<Date> getPlanDateByStudyNoAndUser(String studyNo,String realName);
	/**
	 * 根据日期获取当天所有的检查项
	 * @param planDate
	 * @return
	 */
	//List<QAChkPlan> getItemsByPlanDate(Date planDate);
	/**
	 * 根据专题和日期获取当天所有的检查项
	 * @param studyNo
	 * @param planDate
	 * @return
	 */
	List<QAChkPlan> getItemsByStudyNoAndPlanDateAndUser(String studyNo,Date planDate,String realName);
	/**
	 * 获取一个专题的检查计划的最大版本
	 * @param studyNo
	 * @return
	 */
	Integer getMaxVersionByStudyNo(String studyNo);
	
	QAChkPlan getByChkIndex(String chkIndexId);
	/**
	 * 
	 * @param studyNoParam
	 * @return
	 */
	Integer getVersionByStudyNo(String studyNoParam);
}
