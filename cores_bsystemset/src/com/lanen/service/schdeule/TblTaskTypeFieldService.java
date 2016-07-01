package com.lanen.service.schdeule;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblTaskTypeField;

public interface TblTaskTypeFieldService extends BaseDao<TblTaskTypeField>{
	/**
	 * 查询所有列表
	 * @return
	 */
	List<TblTaskTypeField> getByTaskTypeFieldId(String taskTypeField);
	/**
	 * 根据任务种类查找可见的
	 * @param taskKind
	 * @return
	 */
	List<TblTaskTypeField> getByTaskKind2(int taskKind);

}
