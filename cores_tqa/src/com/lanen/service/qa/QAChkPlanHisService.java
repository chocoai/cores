package com.lanen.service.qa;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkPlanHis;

public interface QAChkPlanHisService extends BaseDao<QAChkPlanHis>{

	/**
	 * 获取一个专题的检查计划的最大版本
	 * @param studyNo
	 * @return
	 */
	Integer getMaxVersionByStudyNo(String studyNo);
	/**
	 * 
	 * @param studyNo
	 * @return
	 */
	List<Integer> getVersionsByStudyNo(String studyNo);
	
	List<QAChkPlanHis> getByStudyNoAndVersion(String studyNo,Integer versionInt);
	
	/**
	 * 				   
	 * @param studyNoParam
	 * @param start
	 * @return
	 */
	
	List<QAChkPlanHis> getByStudyNoAndCondition(String studyNoParam,Date start, Date end,Integer status,Integer catalog,String searchString);
}
