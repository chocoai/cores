package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Treasury;
import com.lanen.util.DateUtil;
@Service
public class TreasuryServiceImpl extends BaseLongDaoImpl<Treasury> implements
		TreasuryService {

	public Map<String, Object> getTreasury(String page, String rows,
			String name, String sys) {
		String sql="select id,name,reason,treasurydate,symptomsremark,prevention," +
				" (select name from symptomsarea s where s.id=t.symptomssite) as symptomssite from treasury t where deleted=0";
		if(name!=null && !("").equals(name)){
			sql=sql+" and name=:name";
		}
		if(sys!=null && !("").equals(sys)){
			sql=sql+" and symptomssite=:sys";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=name && !("").equals(name)){
			query.setParameter("name", name);
		}
		if(sys != null && !("").equals(sys) ){
			query.setParameter("symptomssite", sys);
		}
		List<?> lists=query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Map<String,Object>> listt=new ArrayList<Map<String,Object>>();

		if(list!=null){
			for(Object obj:list){
				Map<String,Object> t=new HashMap<String,Object>();
				Object[] objs=(Object[])obj;
				t.put("id", objs[0]);
				t.put("name", objs[1]);
				t.put("reason", objs[2]);
				t.put("treasurydate", DateUtil.dateToString((Date)objs[3], "yyyy-MM-dd"));
				t.put("symptomsremark", objs[4]);
				t.put("prevention", objs[5]);
				t.put("symptomssite", objs[6]);
				listt.add(t);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public List<?> getTreasuryCount(String start,String end ,int symptomssite){
		String sql="select count(id) from treasury where deleted!=1 and treasurydate between :start and :end and symptomssite=:symptomssite";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("symptomssite", symptomssite);
		return (List<?>)query.list();
	}
}
