package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblTaskTypeLeader;

public interface TblTaskTypeLeaderService extends BaseDao<TblTaskTypeLeader>{
	
	List<TblTaskTypeLeader> getByTaskTypeIDList(String taskTypeID);
	/**
	 * 添加
	 * @param list
	 */
	void saveAllTaskLeader(List<TblTaskTypeLeader> list);
	
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
	 * 根据类别获取任务类型负责人
	 * @param list
	 * @return
	 */
	List<TblTaskTypeLeader> getAllTblTaskTypeLeaderListByTaskTypeID(List<String> list);
	

}
