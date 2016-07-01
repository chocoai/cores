package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblTaskLeader;
import com.lanen.model.schedule.TblTaskLeaderJson;

public interface TblTaskLeaderService  extends BaseDao<TblTaskLeader> {
	
	void saveAllTaskLeader(List<TblTaskLeader> list);
	
	
	
	/**
	 * 删除常规责任负责人
	 * @param list
	 */
	void delAllTaskLeader(List<String> list);
	
	void updateAllTaskLeader(List<TblTaskLeader> list);
	
	List<Date> selectALLEndDate(String taskTypeID,String taskLeader);
	
	//根据权限跨库生成树 
	List<TblTaskLeaderJson> getByPrivilege(String privilege);
	
	//根据任务id查询负责人
	List<TblTaskLeader> getbyTaskid(String taskid);
	
	/**根据当前资源id 和 权限名称，查询用户名、姓名（未被使用过的）
	 * @param currentResId
	 * @param string
	 * @return
	 */
	List<?> findUserNameRealNameByResIdPrivilegeName(String currentResId,
			String string);
	
	/**判断该资源该人是否已经安排
	 * @param resId
	 * @param resManager
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	boolean isExist(String taskTypeID, String taskLeader, Date startDate,
			Date endDate);
	/**
	 * 更具任务名称查询
	 * @param taskNameList
	 * @return
	 */
	List<TblTaskLeaderJson> getByTaskNameTaskLeader(Set<String> taskNameList);
	
	/**
	 * 获取没有任务负责人的字符传
	 * @param taskNameList
	 * @return
	 */
	String getNoTaskLeaderByTaskName(Set<String> taskNameList);
	
	/**
	 * 获得一段时间内的任务和负责人
	 * @param startDate
	 * @param endDate
	 * @param taskKind
	 * @param codeType
	 * @param taskType
	 * @return
	 */
	List<TblTaskLeaderJson> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(Date startDate,Date endDate,int taskKind,int codeType,int taskType);
	

	
}
