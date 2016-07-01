package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblSOLeader;

public interface TblSOLeaderService extends BaseDao<TblSOLeader>{
	
	/**
	 * 查询所有列表
	 * @return
	 */
	List<TblSOLeader> getAll();
	
	/**
	 * 保存所有的课题负责人
	 * @param list
	 */
	void saveAllSOLeader(List<TblSOLeader> list);
	
	List<TblSOLeader> getByStudyNo(String StudyNo);
	
	/**
	 * 编辑课题负责人
	 */
	void updateAllSOLeader(List<TblSOLeader> list1,List<TblSOLeader> list2);
	
	/**
	 * 更新所有的课题负责人
	 * @param list
	 */
	void updateAll(List<TblSOLeader> list);
	
	
	List<TblSOLeader> getBySOlList(Date startDate,Date endDate);
	/**
	 * 所在房间负责人
	 * @return
	 */
	List<?> studyresresManagersoleader(String studyNo);
	
	/**
	 * 所在楼层负责人
	 * @return
	 */
	List<?> studyresanimalhouseresManagersoleader(String studyNo);
	
	/**根据当前课题编号 和 权限名称，查询用户名、姓名（未被使用过的）
	 * @param currentResId
	 * @param string
	 * @return
	 */
	List<?> findUserNameRealNameByResIdPrivilegeName(String studyNo,
			String string,int taskKind);
	
	/**判断该资源该人是否已经安排
	 * @param resId
	 * @param resManager
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	boolean isExist(String studyNo, String taskLeader, Date startDate,
			Date endDate,int taskKind);
}
