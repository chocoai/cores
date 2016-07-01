package com.lanen.service.contract;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblRegion;

public interface TblRegionService extends BaseDao<TblRegion>{

	/**
	 * @param conditionItem
	 * @return
	 */
	List<Map<String, String>> getMapListByName(String conditionItem);
	/**
	 * 获取已选的城市
	 * @return
	 */
	List<TblRegion> getAllRegions();
	/**
	 * 根据level查询所有地区
	 * @param level
	 * @return
	 */
	List<TblRegion> getByRegLevel(int level,String pid);
	
	/**
	 * 查询已存在客户的地方
	 * @param level
	 * @param pid
	 * @return
	 */
	List<TblRegion> getByHaveCutRegLevel(int level,String pid);
	
	/**根据regionId 获得地区全名
	 * @param regionId
	 * @return
	 */
	String getFullNameByregionId(String regionId);
	

}
