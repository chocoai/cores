package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblResManager;

public interface TblResManagerService  extends BaseDao<TblResManager>{
	
	
	/**
	 * 查询所有列表(根据资源Id ，未在有效期内除外)
	 * @return
	 */
	List<TblResManager> getByHouseId(String houseid);
	
	/**
	 * 保存多条动物信息
	 * @param animalList
	 */
	void saveAllresManager(List<TblResManager> resManagerList);
	
	/**
	 * 设置有的资源负责人，删除原来不用的资源负责人
	 * @param resManagerList
	 * @param resList
	 */
	void updateResManager(List<TblResManager> resManagerList,List<TblResManager> resList); 
	
	void updateAllresManager(List<TblResManager> list);

	/**根据资源Id 查询列表（id,负责人姓名，开始日期，结束日期，签字人，负责人，结束日期确认人）
	 * @param currentResId
	 * @return
	 */
	List<?> getDataListByHouseId(String currentResId);

	/**判断该资源该人是否已经安排
	 * @param resId
	 * @param resManager
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	boolean isExist(String resId, String resManager, Date startDate,
			Date endDate);

	/**判断该资源该人是否已经安排(当前Id除外)
	 * @param resId
	 * @param resManager
	 * @param startDate
	 * @param endDate
	 * @param id
	 * @return
	 */
	boolean isExist(String resId, String resManager, Date startDate,
			Date endDate, String id);

	/**根据当前资源id 和 权限名称，查询用户名、姓名（未被使用过的）
	 * @param currentResId
	 * @param string
	 * @return
	 */
	List<?> findUserNameRealNameByResIdPrivilegeName(String currentResId,
			String string);
	
	/**
	 * 根据资源负责人查询
	 * @param resManager
	 * @return
	 */
    List<TblResManager> getByResManager(String resManager);
    
    /**
     * 判断资源和负责人是否匹配
     * @param resManager
     * @param userName
     * @return
     */
    boolean getByResManagerAndResID(String resManager,String resId);
}
