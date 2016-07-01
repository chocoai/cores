package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblSchedulePlan;

public interface TblSchedulePlanService extends BaseDao<TblSchedulePlan>{
	
	
	List<TblSchedulePlan> getSchedulePlanList(int taskType,String StudyNo,int CodeType);
		
	/**
	 * 查询日程安排
	 * @param taskType 传入的任务类型
	 * @param StudyNo 传入的任务识别号
	 * @param CodeType 识别号类型
	 * @return
	 */
	List<TblSchedulePlan> getSchedulePlan(int taskType ,String StudyNo,int CodeType);
	
	/**
	 * 查询相同任务名称的日程安排
	 * @param startime 开始时间
	 * @param endtime 结束时间
	 * @param taskName 任务名称
	 * @param CodeType 识别号
	 * @return
	 */
	List<TblSchedulePlan> getSameTaskNameSchedulePlan(Date startime,Date endtime,String taskName,int CodeType,int taskType);
	/**
	 * 根据
	 * @param taslCode 任务识别号
	 * @param oldtime 修改日程的时间
	 * @param taskName 任务名称
	 * @return 所要修改的日程
	 */
	List<TblSchedulePlan> selecTblSchedulePlans(String taslCode,Date oldtime,String taskName);
		
	/**
	 * 更新签字
	 * @param singIdlist日程id集合
	 * @param singid签字人
	 */
	void updateAllTblSchedulePlans(List<String> singIdlist,String singid);
	
	/**
	 * 删除日程
	 * @param singIdlist日程id集合
	 */
	void delectTblSchedulePlans(List<String> singIdlist);
	
	/**
	 * 根据课题编号
	 */
	void submitSchedulePlanByTaskCode(String studyNo,String signid);
	/**
	 * 根据专题更新validFlag
	 * @param studyNo
	 */
	void updateSchedulePlanValidFlagByTaskCode(String studyNo) ;
}
