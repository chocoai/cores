package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Iplogin;

@Service
public class IploginServiceImpl extends BaseLongDaoImpl<Iplogin> implements
		IploginService {
	//小写.
	public Map<String, Object> getListByConditions(String page, String rows,
			String id, String start, String end) {
		String sql=" select id,ip," +
				"(select name from employee e where e.id=i.created_by)as username,createtime from iplogin i ";
		/*if(!"".equals(start)&&start!=null &&!"".equals(end)&&end!=null){
			sql=sql+"  createtime  between :start and :end";
		}*/
		if(!"".equals(id)&&id!=null){
			sql=sql+" where created_by=:id";
		}
		sql=sql+" order by createtime desc";
		Query query=getSession().createSQLQuery(sql);
		/*if(!"".equals(start)&&start!=null &&!"".equals(end)&&end!=null){
			query.setParameter("start", start);
			query.setParameter("end", end);
		}*/
		if(!"".equals(id)&&id!=null){
			query.setParameter("id", id);
		}
		List<?> listtotal = query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
		
		
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		if(null!=list){
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			
			Map<String,Object> m = new HashMap<String,Object>(); 
			m.put("ip", objs[1]);
			m.put("username", objs[2]);
			if(!"".equals(objs[3])&&objs[3]!=null){
				String tim=objs[3]+"";
				m.put("logintime", tim);
			}
			m.put("id", objs[0]);
			list1.add(m);
		}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

}
