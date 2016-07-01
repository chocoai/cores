package com.lanen.service.schdeule;

import java.util.List;


import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeField;

public interface TblTaskTypeService extends BaseDao<TblTaskType>{
	
	/**
	 * 查询所有列表
	 * @return
	 */
	List<TblTaskType> getAll(List<Integer> taskTypeID);
	
	/**
	 * 判断标本种类是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByTaskName(String taskName);
	
	/**
	 * 获得任务
	 * @return
	 */
	List<Integer> gettaskKind(List<Integer> taskTypeID);
	
	/**
	 * 根据任务类型查询名称
	 * @param taskTypeID
	 * @return
	 */
	List<TblTaskType> getTaskTypeName(int taskTypeID);

	void delectAllTask(List<String> list);
	
	 /**
	 * 获得任务
	 * @return
	 */
	List<String> getTaskTypeID();
	
	/**
	 * 根据任务名称查询
	 * @param name
	 * @return
	 */
	List<TblTaskType> getByTypeName(String name);
	
	void updateTaskType(List<TblTaskType> list);
	
	//保存名称的时候，保存tasktypeField
	void saveAllTaskType(TblTaskType taskType,List<TblTaskTypeField> fidlist);
	/**
	 * 跟新任务名称时，更新它的可见范围
	 * @param taskType
	 * @param fidlist
	 */
	void updateTaskTypeAndTblTaskTypeFields(TblTaskType taskType,List<TblTaskTypeField> oldfidlist,List<TblTaskTypeField> fidlist);
	/**获取字典任务列表（无效的除外）
	 * @return
	 */
	List<TblTaskType> getTblTaskTypeList();
	
	/**获取字典任务列表（无效的包括）
	 * @return
	 */
	List<TblTaskType> getTblTaskTypeListhaveV();

}
