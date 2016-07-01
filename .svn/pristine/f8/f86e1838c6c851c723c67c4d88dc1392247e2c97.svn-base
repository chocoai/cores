package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictChkArea;

public interface DictChkAreaService extends BaseDao<DictChkArea> {

	boolean isExistChildByParentId(String parentAreaID);
	
	/**
	 * 
	 * @return
	 */
	List<DictChkArea> getAll();
	/**
	 * 判断区域名是否存在
	 * @param areaName
	 * @return
	 */
	boolean isExistByName(String areaName);
	/**
	 * 判断区域名是否存在
	 * @param areaName
	 * @return
	 */
	boolean isExistByNameExceptOne(String areaName,String areaID);
}
