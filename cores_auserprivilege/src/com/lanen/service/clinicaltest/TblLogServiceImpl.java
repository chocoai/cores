package com.lanen.service.clinicaltest;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.util.DateUtil;

@Service
public class TblLogServiceImpl extends BaseLongDaoImpl<TblLog> implements TblLogService{

	@Override
	public void save(TblLog entity) {
		entity.setOperatTime(new Date());
		super.save(entity);
	}

	@SuppressWarnings("unchecked")
	public List<String> getOperatHostListBySystemName(String systemName) {
		List<String> list = getSession().createQuery("select distinct t.operatHost From TblLog t where t.systemName = ?  ")//
		.setParameter(0, systemName)//
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblLog> getListByDateHostSystemName(String beginDateStr,
			String endDateStr, String operatHost, String systemName) {
		//beginDateStr=beginDateStr+" 00:00:00";
		//endDateStr=endDateStr+" 23:59:59";
		operatHost="%"+operatHost+"%";
		List<TblLog> list = getSession().createQuery("From TblLog t where t.systemName = :systemName and ((convert(date,t.operatTime,120)) between :startDate and :endDate  or (convert(date,t.operatTime,120)) between :endDate and :startDate) and t.operatHost like :operatHost  order by t.operatTime desc  ")//
		.setParameter("systemName", systemName)//
		.setParameter("startDate", DateUtil.stringToDate(beginDateStr, "yyyy-MM-dd"))//
		.setParameter("endDate", DateUtil.stringToDate(endDateStr, "yyyy-MM-dd"))//
//		.setParameter(1, DateUtil.stringToDate(beginDateStr, "yyyy-MM-dd HH:mm:ss"))//
//		.setParameter(2, DateUtil.stringToDate(endDateStr, "yyyy-MM-dd HH:mm:ss"))//
		.setParameter("operatHost", operatHost)//
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getOperatTypeListBySystemName(String systemName) {
		List<String> list = getSession().createQuery("select distinct t.operatType From TblLog t where t.systemName = ?  ")//
		.setParameter(0, systemName)//
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblLog> getListByDateHostTypeSystemName(String beginDate,
			String endDate, String operatHost, String operatType,
			String systemName) {
		operatHost="%"+operatHost+"%";
		List<TblLog> list = getSession().createQuery("From TblLog t where t.systemName = :systemName and ((convert(date,t.operatTime,120)) between :startDate and :endDate  or (convert(date,t.operatTime,120)) between :endDate and :startDate) and t.operatHost like :operatHost and (t.operatType = :operatType or :operatType = '' ) order by t.operatTime desc  ")//
										.setParameter("systemName", systemName)//
										.setParameter("startDate", beginDate)//
										.setParameter("endDate",endDate)//
										.setParameter("operatHost", operatHost)//
										.setParameter("operatType", operatType)//
										.list();
//		beginDate=beginDate+" 00:00:00";
//		endDate=endDate+" 23:59:59";
////		operatHost="%"+operatHost+"%";
//		String hql = "";
//		if(null == operatType || "".equals(operatType)){
//			hql = "From TblLog t where t.systemName = :systemName and t.operatTime between :startDate and :endDate  or t.operatTime between :endDate and :startDate)  order by t.operatTime desc  ";
//		}else{
//			hql = "From TblLog t where t.systemName = :systemName and t.operatTime between :startDate and :endDate  or t.operatTime between :endDate and :startDate) and t.operatHost like :operatHost and (t.operatType = :operatType or :operatType = '' ) order by t.operatTime desc  ";
//		}
//		List<TblLog> list = getSession().createQuery(hql)//
//		.setParameter("systemName", systemName)//
//		.setParameter("startDate", beginDate)//
//		.setParameter("endDate",endDate)//
////		.setParameter("operatHost", operatHost)//
////		.setParameter("operatType", operatType)//
//		.list();
		return list;
	}
	
	

}
