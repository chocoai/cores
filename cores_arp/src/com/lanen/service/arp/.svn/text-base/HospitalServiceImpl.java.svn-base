package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Hospital;
import com.lanen.model.Hospital_Json;
@Service
public class HospitalServiceImpl extends BaseLongDaoImpl<Hospital> implements
		HospitalService {

	public Map<String, Object> getHospital(String rows, String page,String monkeyid,String start,String end,String roomid) {
		String sql="select id,monkeyid,indate," +
				" (select e.name from employee e where e.id=h.treatveterinarian)as treatveterinarian," +
				"(select areaname from area a where a.id=h.roomid)as roomname,lhao," +
				" (select e.name from employee e where e.id=h.outkeeper)as outkeeper," +
				" (select e.name from employee e where e.id=h.inveterinarian)as inveterinarian," +
				" (select e.name from employee e where e.id=h.inkeeper)as inkeeper" +
				" from hospital h where h.deleted=0";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and h.monkeyid=:monkeyid";
		}if(roomid!=null&&!"".equals(roomid)){
			sql=sql+" and roomid=:roomid";
		}if(!"".equals(start)&&start!=null&&!"".equals(end)&&end!=null){
			sql=sql+" and indate between :start and :end";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}if(roomid!=null&&!"".equals(roomid)){
			query.setParameter("roomid", roomid);
		}if(!"".equals(start)&&start!=null&&!"".equals(end)&&end!=null){
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		List<?> lists=query.list();
		
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Hospital_Json> json=new ArrayList<Hospital_Json>();
		for(Object o:list){
			Hospital_Json h=new Hospital_Json();
			Object[] ob=(Object[])o;
			h.setId((Long.valueOf(ob[0]+"")));
			h.setMonkeyid((String)ob[1]);
			h.setIndate((String)ob[2]);
			h.setTreatveterinarian((String)ob[3]);
			h.setRoomid((String)ob[4]);
			h.setLhao((String)ob[5]);
			h.setOutkeeper((String)ob[6]);
			h.setInveterinarian((String)ob[7]);
			h.setInkeeper((String)ob[8]);
			
			json.add(h);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", json);
		map.put("total", lists.size());
		return map;
	}

}
