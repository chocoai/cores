package com.lanen.service.schdeule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblSchedulePlanHis;
@Service
public class TblSchedulePlanHisServiceImpl extends BaseDaoImpl<TblSchedulePlanHis> implements TblSchedulePlanHisService{

	@SuppressWarnings("unchecked")
	public List<TblSchedulePlanHis> getSchedulePlan(String scheduleID) {
		List<TblSchedulePlanHis> tblSchedulePlanList = getSession().createQuery("FROM TblSchedulePlanHis t WHERE t.scheduleID = ? ").setParameter(0, scheduleID).list();
		if(null == tblSchedulePlanList || tblSchedulePlanList.size() < 1){
			return null;
		}else{
			 return tblSchedulePlanList;
		}
    
	}

}
