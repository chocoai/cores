package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Death;
import com.lanen.model.RoomDisinfectRecord;
@Service
public class RoomDisinfectRecordServiceImpl extends
		BaseLongDaoImpl<RoomDisinfectRecord> implements
		RoomDisinfectRecordService {

	public List<Map<String, Object>> loadAllDisinfectType() {
		String sql="SELECT DISTINCT disinfectType FROM roomdisinfectrecord";
		List<?> listSql=getSession().createSQLQuery(sql).list();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("id", (String)obj);
				map.put("text",(String)obj);
				list.add(map);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int isUsed(Long id) {
		List<RoomDisinfectRecord> list=null;
		String hql="from RoomDisinfectRecord where disinfectant_id=:disinfectant_id";
		list=getSession().createQuery(hql).setParameter("disinfectant_id", id).list();
		if(list==null||list.size()==0){
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getlistByRoomIdDisDate(String page, String rows,Long areaId,
			Date disDate) {
		String hql="from RoomDisinfectRecord";
		if(areaId==0L){
			if(disDate!=null&&!"".equals(disDate)){
				hql=hql+"  where disinfectDate=:disinfectDate";
			}
		}else{
			hql=hql+" where area_id=:areaId";
			if(disDate!=null&&!"".equals(disDate)){
				hql=hql+" and   disinfectDate=:disinfectDate";
			}
		}
		Query query=getSession().createQuery(hql);
		if(areaId==0L){
			if(disDate!=null&&!"".equals(disDate)){
				query.setParameter("disinfectDate", disDate);
			}
		}else{
			query.setParameter("areaId", areaId);
			if(disDate!=null&&!"".equals(disDate)){
				query.setParameter("disinfectDate", disDate);
			}
		}
		List<RoomDisinfectRecord> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<RoomDisinfectRecord> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		return map;
	}

	

}
