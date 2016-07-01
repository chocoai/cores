package com.lanen.service.schdeule;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblSchedulePlanHis;

public interface TblSchedulePlanHisService extends BaseDao<TblSchedulePlanHis>{
	
	List<TblSchedulePlanHis> getSchedulePlan(String scheduleID);

}
