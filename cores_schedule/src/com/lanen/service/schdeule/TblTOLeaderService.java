package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblSOLeaderJson;
import com.lanen.model.schedule.TblTOLeader;

public interface TblTOLeaderService  extends BaseDao<TblTOLeader>{
	
   /**
    * 根据日程ID查询	
    * @param scheduleId
    * @return
    */
	List<TblTOLeader> getByLeaderList(String scheduleId );
	
	/**
    * 根据日程ID查询	
    * @param scheduleId
    * @return
    */
	List<TblTOLeader> getByScheduleIdListLeaderList(List<String> scheduleId );
	
	/**
	 * 保存多条剂量
	 * @param list
	 */
	void saveAllLeaderList(List<TblTOLeader> list);
	
	/**
	 * 更新编辑责任人
	 * @param list
	 */
	void updateAllLeaderList(List<TblTOLeader> list1,List<TblTOLeader> list2);
	
	
	/**
	 * 更新
	 * @param list
	 */
	void updateAll(List<TblTOLeader> list);
	
	
	/**
	 * 根据开始结束时间获得任务操作者列表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TblSOLeaderJson> getTOLeader(Date startDate,Date endDate);
	
	/**
	 * 根据开始结束时间获得批量操作任务操作者列表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TblSOLeaderJson> getBatchTOLeader(Date startDate,Date endDate,String sort,String order );
	
	/**判断该资源该人是否已经安排
	 * @param resId
	 * @param resManager
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	boolean isExist(String scheduleId, String taskLeader, Date startDate,
			Date endDate);
	
	/**根据当前课题编号 和 权限名称，查询用户名、姓名（未被使用过的）
	 * @param currentResId
	 * @param string
	 * @return
	 */
	List<?> findUserNameRealNameByResIdPrivilegeName(String scheduleId,
			String string);
	//房间负责人
	List<?> studyresresManagersoleader(String scheduleId,
			String studyNo);
	//楼层负责人
	List<?> studyresanimalhouseresManagersoleader(String scheduleId,
			String studyNo);
	
	//
	List<?> getScheduleIdAndleaderName(List<String> scheduleIds,List<String> taskids);
	
	/**
	 * 根据时间和人查询日程
	 * @return
	 */
	List<TblTOLeader> getByDateAndLeader(Date startDate,Date endDate,String tOLeader);

}
