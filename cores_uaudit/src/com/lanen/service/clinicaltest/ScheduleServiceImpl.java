package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAppointSDData(String studyNo) {
		String sql = "select distinct  a.id,a.studyNo,a.studyName,a.tiName,a.sd,convert(varchar(10),a.appointDate,120) as appointDate,"+
					" 	convert(varchar(10),a.cancelDate,120) as cancelDate"+
					" from CoresSchedule.dbo.tblAppointSD as a left join CoresSchedule.dbo.tblAppointSD as a2"+
					" 	on a.studyNo = a2.studyNo and a2.state = -1"+
					" where a.studyNo = :studyNo " +
					" and a2.id is not null order by a.id";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession().createSQLQuery(sql)
																			.setParameter("studyNo", studyNo)
																			.setResultTransformer(new MapResultTransformer())
																			.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAppointQAData(String studyNo) {
		String sql = "select  distinct a.id,a.studyNo,a.studyName,a.tiName,a.qa,convert(varchar(10),a.appointDate,120) as appointDate,"+
					" 	convert(varchar(10),a.cancelDate,120) as cancelDate"+
					" from CoresSchedule.dbo.tblAppointQA as a left join CoresSchedule.dbo.tblAppointQA as a2"+
					" 	on a.studyNo = a2.studyNo and a2.state = -1"+
					" where a.studyNo = :studyNo " +
					" and a2.id is not null order by a.id";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession().createSQLQuery(sql)
					.setParameter("studyNo", studyNo)
					.setResultTransformer(new MapResultTransformer())
					.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAppointPathSDData(String studyNo) {
		String sql = "select distinct  a.id,a.studyNo,a.studyName,a.tiName,a.pathSD,convert(varchar(10),a.appointDate,120) as appointDate,"+
						" 	convert(varchar(10),a.cancelDate,120) as cancelDate"+
						" from CoresSchedule.dbo.tblAppointPathSD as a left join CoresSchedule.dbo.tblAppointPathSD as a2"+
						" 	on a.studyNo = a2.studyNo and a2.state = -1"+
						" where a.studyNo = :studyNo " +
						" and a2.id is not null order by a.id";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setResultTransformer(new MapResultTransformer())
						.list();
		return mapList;
	}
	
	
}
