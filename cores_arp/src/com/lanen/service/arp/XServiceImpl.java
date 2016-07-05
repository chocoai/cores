package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Publiccode;
import com.lanen.model.X;
import com.lanen.util.Constant;
@Service
public class XServiceImpl extends BaseLongDaoImpl<X> implements XService {
	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String monkeyid, String type) {
		String sql="select id,monkeyid,cdate,ptype,checkarea," +
				"(select e.name from employee e where x.veterinarian=e.id)as veterinarian," +
				"(select e.name from employee e where x.protector=e.id)as protector," +
				"(select e.name from employee e where x.recorder=e.id)as recorder,remark " +
				"from x where x.deleted=0 ";
		if(!"".equals(monkeyid)&&monkeyid!=null&&!"".equals(type)&&type!=null){
			sql=sql+" and ptype=:type and monkeyid=:monkeyid ";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null&&!"".equals(type)&&type!=null){
			query.setParameter("type", type);
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<X> listx=new ArrayList<X>();
		if(list!=null){
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				X x=new X();
				x.setId((Integer)objs[0]);
				x.setMonkeyid((String)objs[1]);
				x.setCdate((Date)objs[2]);
				x.setPtype((String)objs[3]);
				x.setCheckarea((String)objs[4]);
				x.setVeterinarian((String)objs[5]);
				x.setProtector((String)objs[6]);
				x.setRecorder((String)objs[7]);
				x.setRemark((String)objs[8]);
				
				listx.add(x);
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", listx);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, String>> getCheckArea() {
		List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
		String sql="select id,name from publiccode where mark=:mark";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("mark", Constant.checkarea);
		List<?> list=query.list();
		Map<String,String> map=null;
		//map=new HashMap<String,String>();
		//map.put("id", "-1");
		//map.put("text", "&nbsp;");
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			map=new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String)objs[1]);
			listMap.add(map);
		}
		return listMap;
	}

	public List<Map<String, Object>> getCheckArea(String mark) {
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		String sql="select id,name from publiccode where mark=:mark";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("mark", mark);
		List<?> list=query.list();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("checkAreaId", objs[1]+"("+objs[0]+")");
			map.put("checkAreaName",objs[1]);
			listMap.add(map);
		}
		return listMap;
	}

	public Publiccode getIdByNamess(String name) {
		String sql="from Publiccode where name=?";
		List<?> l=getSession().createQuery(sql).setParameter(0, name).list();
		if(l.size()>0){
			return (Publiccode)l.get(0);
		}else{
			return null;
		}
	}

}
