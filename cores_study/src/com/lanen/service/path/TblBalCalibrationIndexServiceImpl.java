package com.lanen.service.path;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblBalCalibrationIndex;
import com.lanen.util.DateUtil;
/**
 * 天平校准索引表 serviceImpl
 * @author Administrator
 */
@Service
public class TblBalCalibrationIndexServiceImpl extends BaseDaoImpl<TblBalCalibrationIndex> implements
		TblBalCalibrationIndexService {

	public List<?>  loadListByCondition(Date beginDate, Date endDate,
			String balCode) {
		List<?> list = null;
		String sql=" select " +
				   " id ,hostCode,balCode,calBeginTime,calEndTime,calResult" +
				   " ,(select realName from CoresUserPrivilege.dbo.tbluser where userName=calSign) as calSign" +
				   " from CoresStudy.dbo.tblBalCalibrationIndex "+
			       " where calEndTime between :beginDate and :endDate ";
		if(null!=balCode && !balCode.equals("")){
			sql= sql +" and balCode=:balCode";	
		}
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("beginDate", beginDate);
		query.setParameter("endDate", endDate);
		if(null!=balCode && balCode!=""){
			query.setParameter("balCode", balCode);
		}
		list=query.list();
		return list;
	}

	public boolean isExistByBalCode(String balCode) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
//		calendar.add(Calendar.HOUR, -12);
		Date ydate = calendar.getTime();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(ydate);
		Date date=DateUtil.stringToDate(today+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
	
		String hql = "from TblBalCalibrationIndex where balCode = :balCode and calResult = 1 and  calEndTime> :date";
		List<?> list = getSession().createQuery(hql)
									.setParameter("balCode", balCode)
									.setParameter("date", date)
									.list();
		if(null != list && list.size() > 0 ){
			return true;
		}
		return false;
	}

	public String getPassCalId(String balCode) {
		String sql = "select top 1 calIndex.id"+
					" from CoresStudy.dbo.tblBalCalibrationIndex  calIndex"+
					" where calIndex.balCode = ? and calIndex.calResult = 1"+
					" order by calEndTime desc";
		String calIndexId = (String) getSession().createSQLQuery(sql)
										.setParameter(0, balCode)
										.uniqueResult();
		return calIndexId;
	}
   
	
}
