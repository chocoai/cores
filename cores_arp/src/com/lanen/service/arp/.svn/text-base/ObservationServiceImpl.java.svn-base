package com.lanen.service.arp;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Observation;
@Service
public class ObservationServiceImpl extends BaseLongDaoImpl<Observation> implements ObservationService{

	public List<?> getAllObservationById(String monkeyid) {
		String sql="select id," +
				"(select sex from individual i where i.monkeyid=o.monkeyid) as sex," +
				"(select areaname from area where id=(select blongarea from individual i where i.monkeyid=o.monkeyid)) as quyu ," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=o.monkeyid)) as room ," +
				"monkeyid," +
				"content," +
				"observationtime," +
				"(select name from employee e where e.id=o.observer) as observer,observationdate  " +
				"from observation o where deleted != 1 " ;
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and o.monkeyid=:monkeyid ";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> list=query.list();
		return list;
	}
	public List<?> getAllObservationById(String monkeyid,String observationdate) {
		String sql="select id," +
				"(select sex from individual i where i.monkeyid=o.monkeyid) as sex," +
				"(select areaname from area where id=(select blongarea from individual i where i.monkeyid=o.monkeyid)) as quyu ," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=o.monkeyid)) as room ," +
				"monkeyid," +
				"content," +
				"observationtime," +
				"(select name from employee e where e.id=o.observer) as observer,observationdate  " +
				"from observation o where deleted != 1 " +
				" and o.monkeyid=:monkeyid and observationdate=:observationdate";
		
		Query query=getSession().createSQLQuery(sql);
		
		List<?> list=query.setParameter("monkeyid", monkeyid).setParameter("observationdate", observationdate).list();
		return list;
	}
}
