package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblSchedulePlan_Json;

/**
 * @author 黄国刚
 *
 */
public interface TblSchedulePlanService extends BaseDao<TblSchedulePlan>{
	
	
	List<TblSchedulePlan> getAllschedulePlanList();
	/**
	 * 查询时间段内的日常
	 * @return
	 */
	List<?> getAllByTimeschedulePlanList(Date startTime, Date endTime,String taskKind);
	
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
	 * 根据任务名称list，查询任务名称是否被引用
	 */
	boolean getBySchedulePlan(List<String> list);
	
	/** 调用存数过程  getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType，  返回   datagrid 数据及各列
	 * @param startDate
	 * @param endDate
	 * @param taskKind
	 * @param codeType
	 * @param taskType
	 * 
	 * @return  datagrid 数据及各列
	 */
	Map<String,Object> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(Date startDate,Date endDate,int taskKind,int codeType,int taskType,boolean isAllDate);
	
	
	
	Map<String, Object> getOneDateScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(Date startDate,Date endDate,int taskKind,int codeType,int taskType,boolean isAllDate
			,List<String> studyNoList ,List<String> resIdList ,List<String> taskNameList,List<String> leaderNameList);
	/**调用存数过程  getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType，  返回   datagrid 数据及各列
	 * @param startDate         开始日期
	 * @param endDate			结束日期
	 * @param taskKind			任务类型（字典）
	 * @param codeType			识别号（编码）类型
	 * @param taskType			任务类型
	 * @param isAllDate			是否显示全部日期
	 * @param studyNoList		专题编号列表
	 * @param resIdList			资源Id列表
	 * @param taskNameList		任务名称列表
	 * @param leaderNameList	操作人列表
	 * @return
	 */
	Map<String,Object> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(Date startDate,Date endDate,int taskKind,int codeType,int taskType,boolean isAllDate
			,List<String> studyNoList ,List<String> resIdList ,List<String> taskNameList,List<String> leaderNameList);
	
	
	//查询自定义的任务名称
	List<String> getTaskNameNoInTaskType(String taskKind);
	
	/**查询日程列表（studyNo，resName，state，signer（审核者））
	 * @param startime    专题开始riqi
	 * @param endtime
	 * @param isSelectAllStudy 0 仅查询未分配或未确认，1 查询全部  ，2查询未新建的，还未加到试验安置中的
	 * @return
	 */
	List<?> getStudyNoRes(Date startime, Date endtime,int isSelectAllStudy);
	
	
	List<String> getTblSchedulePlantaskCode(Date startDate , Date endDate);
	
	/**查询日程安排 ,已提交日程
	 * @param i
	 * @param studyNoPara
	 * @param j
	 * @return
	 */
	List<TblSchedulePlan> getHasSubmitSchedulePlan(int i, String studyNoPara,int j);
	/**查询日程安排 ,已提交日程,包括是第几次执行
	 * @param i
	 * @param studyNoPara
	 * @param j
	 * @return
	 */
	List<TblSchedulePlan_Json> getHasSubmitSchedulePlanJson(int taskType,String StudyNo,int CodeType);
	/**
	 * 根据课题编号
	 */
	void submitSchedulePlanByTaskCode(String studyNo,String signid);
}
