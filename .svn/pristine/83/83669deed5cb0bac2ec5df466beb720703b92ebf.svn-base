package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictScheduleChkItem;

public interface DictScheduleChkItemService extends BaseDao<DictScheduleChkItem>{

	DictScheduleChkItem getByChkItemIdAndChkTblId(String id,String chkItemId);
	/**
	 * 根据日程名获取日程检查项关系
	 * @param scheduleName
	 * @return
	 */
	public List<DictScheduleChkItem> getByScheduleName(String scheduleName);
	/**
	 * 根据日程Id获取日程检查项关系
	 * @param scheduleName
	 * @return
	 */
	public List<DictScheduleChkItem> getByScheduleNameId(String scheduleNameId);
	/**
	 * 
	 * @return
	 */
	public List<DictScheduleChkItem> getByChkItemId(String chkItemId);

}
