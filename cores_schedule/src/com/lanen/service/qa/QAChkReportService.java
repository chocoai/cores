package com.lanen.service.qa;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkReport;

public interface QAChkReportService extends BaseDao<QAChkReport>{
	/**
	 * 
	 * @return
	 */
	List<Integer> 	getYears();
	/**
	 * 获取某一个年的某个状态的报告
	 * @param time
	 * @param status
	 * @param searchCondition
	 * @return
	 */
	List<QAChkReport> getByTimeStatusAndCondition(Integer time, Integer status,String searchCondition,String realName);
	/**
	 * 获取某个时间段内有签字信息的报告
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> getByDateAndSignStatus(Date startDate,Date endDate,String searchCondition);
}
