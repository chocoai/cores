package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.util.DateUtil;
@Service
public class TblTaskTypeLeaderServiceImpl extends BaseDaoImpl<TblTaskTypeLeader> implements TblTaskTypeLeaderService{

	public boolean isExist(String taskTypeID, String taskLeader,
			Date startDate, Date endDate) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql =" select count(id) "+
					" from dbo.tblTaskTypeLeader "+
					" where taskTypeID =:taskTypeID and taskLeader =:taskLeader and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("taskTypeID", taskTypeID)
						.setParameter("taskLeader", taskLeader)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.uniqueResult();
		
		return !(count==0);
	}

	public void saveAllTaskLeader(List<TblTaskTypeLeader> list) {
		for(TblTaskTypeLeader taskLeader:list){
			getSession().save(taskLeader);
		}
		
	}
	// TODO 查询根据taskTypeID 2014-11-14
	@SuppressWarnings("unchecked")
	public List<TblTaskTypeLeader> getByTaskTypeIDList(String taskTypeID) {
		String hql = " From TblTaskTypeLeader as t where t.taskTypeID = :taskTypeID ";
		List<TblTaskTypeLeader> list = getSession().createQuery(hql).setParameter("taskTypeID", taskTypeID).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblTaskTypeLeader> getAllTblTaskTypeLeaderListByTaskTypeID(
			List<String> list) {
		String hql = "  FROM TblTaskTypeLeader as tblt where  "+
		"  getdate() between  tblt.startDate and  (case when tblt.endDate is null then '2030-12-31' else tblt.endDate end ) "+
		"  and tblt.taskTypeID in ( :taskTypeID )";
		List<TblTaskTypeLeader> list1 = getSession().createQuery(hql).setParameterList("taskTypeID", list).list();
		return list1;
	}

}
