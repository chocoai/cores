package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Symptoms;
@Service
public class SymptomsServiceImpl extends BaseLongDaoImpl<Symptoms> implements
		SymptomsService {

	public Map<String, Object> getSymptoms(String page, String rows,
			String name, String sys) {
		String sql="select id,name,(select name from symptomsarea sa where sa.id=s.symptomssite)as symptomssite,istreatment,symptomsremark,reason from symptoms s where deleted=0";
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
				Map<String,Object> s=new HashMap<String,Object>();
				Object[] objs=(Object[])obj;
				
				s.put("id", objs[0]);
				s.put("name", objs[1]);
				s.put("symptomssite", objs[2]);
				s.put("istreatment", objs[3]);
				s.put("symptomsremark", objs[4]);
				s.put("reason", objs[5]);
				listt.add(s);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

}
