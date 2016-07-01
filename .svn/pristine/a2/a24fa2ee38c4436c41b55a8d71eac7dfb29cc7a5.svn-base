package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictScheduleChkItem;

public interface DictScheduleChkItemService extends BaseDao<DictScheduleChkItem>{

	DictScheduleChkItem getByChkItemIdAndChkTblId(String id,String chkItemId);
	/**
	 * 根据日程名称获取日程相关的检查项
	 * @param taskName
	 * @return
	 */
	List<DictScheduleChkItem> getByScheduleName(String taskName);

}
