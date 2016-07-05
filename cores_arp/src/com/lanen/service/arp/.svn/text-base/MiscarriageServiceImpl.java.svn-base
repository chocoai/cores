package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Miscarriage;
import com.lanen.model.Miscarriage_Json;
@Service
public class MiscarriageServiceImpl extends BaseLongDaoImpl<Miscarriage> implements MiscarriageService{

	public Map<String, Object> getAllMiscarriageAnimal(String page,String rows,String monkeyid,String start,String end) {
		String sql="SELECT id,monkeyid,miscarriagedate," +
				" (select e.name from employee e where e.id=m.veterinarian) as veterinarian," +
				" (select e.name from employee e where e.id=m.protector) as protector," +
				" (select e.name from employee e where e.id=m.recorder) as recorder," +
				" (select e.name from employee e where e.id=m.operater) as operater," +
				" remarks FROM miscarriage m where deleted != 1 ";
		
		if(!("").equals(monkeyid)&&monkeyid!=null){
			sql=sql+"and m.monkeyid=:monkeyid";
		}
		if(!("").equals(start)&&start!=null&&!("").equals(end)&&end!=null){
			sql=sql+" and miscarriagedate between :start and :end";
		}
		sql=sql+" order by id desc";
		Query query=getSession().createSQLQuery(sql);
		if(null!=monkeyid && !("").equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(start != null && !("").equals(start) && end != null && !("").equals(end) ){
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		List<?> listSql=query.list();
		
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Miscarriage_Json> m=new ArrayList<Miscarriage_Json>();
		if(listSql!=null){
			for(Object obj:list){
				Miscarriage_Json mis=new Miscarriage_Json();
				Object[] objs=(Object[])obj;
				mis.setId((Integer)objs[0]);
				mis.setMonkeyid((String)objs[1]);
				mis.setMiscarriagedate((java.util.Date)objs[2]);
				mis.setVeterinarian((String)objs[3]);
				mis.setProtector((String)objs[4]);
				mis.setRemarks((String)objs[7]);
				mis.setRecorder((String)objs[5]);
				mis.setOperater((String)objs[6]);
				m.add(mis);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", m);
		map.put("total", listSql.size());
		return map;
	}

	public List<Miscarriage_Json> getAllMiscarriageById(String monkeyid) {
		String sql = "SELECT id,monkeyid,miscarriagedate,"
				+ " (select e.name from employee e where e.id=m.veterinarian) as veterinarian,"
				+ " (select e.name from employee e where e.id=m.protector) as protector,"
				+ " (select e.name from employee e where e.id=m.recorder) as recorder,"
				+ " (select e.name from employee e where e.id=m.operater) as operater,"
				+ " remarks FROM miscarriage m where deleted != 1 and m.monkeyid=:monkeyid ";
		
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("monkeyid", monkeyid);
		
		List<?> listSql = query.list();

		List<Miscarriage_Json> m = new ArrayList<Miscarriage_Json>();
		if (listSql != null) {
			for (Object obj : listSql) {
				Miscarriage_Json mis = new Miscarriage_Json();
				Object[] objs = (Object[]) obj;
				mis.setId((Integer) objs[0]);
				mis.setMonkeyid((String) objs[1]);
				mis.setMiscarriagedate((java.util.Date) objs[2]);
				mis.setVeterinarian((String) objs[3]);
				mis.setProtector((String) objs[4]);
				mis.setRemarks((String) objs[7]);
				mis.setRecorder((String) objs[5]);
				mis.setOperater((String) objs[6]);
				m.add(mis);
			}
		}
		return m;
	}

}
