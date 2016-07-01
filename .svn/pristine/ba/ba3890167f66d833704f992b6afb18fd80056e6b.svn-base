package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblTaskState;

public interface TblTaskStateService extends BaseDao<TblTaskState>{
	
	
	/** 某人某天的任务以及是否完成
	 * @param oneDay		时间分钟等为0
	 * @param onePeople		--src0129
	 * @return
	 */
	List<Map<String,Object>> getOneDayOnePeopleAllTask(Date oneDay ,String onePeople);

	/**保存多个记录
	 * @param scheduleIds
	 * @param selectedDate
	 * @param currentUserCode
	 */
	void saveMuch(String[] scheduleIds, String selectedDate,
			String currentUserCode);
}
