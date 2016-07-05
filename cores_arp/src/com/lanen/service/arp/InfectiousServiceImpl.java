package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Infectious;
import com.lanen.model.Infectious_Json;
import com.lanen.model.Quarantine;
import com.lanen.model.Vaccine_Json;
import com.lanen.util.Constant;

@Service
public class InfectiousServiceImpl extends BaseLongDaoImpl<Infectious> implements
		InfectiousService {

	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String type, String monkeyid) {
		String sql = "select id,monkeyid,cdate,"
				+ "(select name from quarantine q where i.q_id=q.id)as crbmc,"
				+ "(select name from employee e where e.id=i.veterinarian)as veterinarian,"
				+ "(select name from employee e where e.id=i.protector)as protector,"
				+ "(select name from employee e where e.id=i.recorder)as recorder,remark,drugs_name,drugs_count"
				+ " from infectious i where i.deleted!=1 ";
		if (!"".equals(type) && type != null) {
			sql = sql + " and ptype=:type";
		}
		if (!"".equals(monkeyid) && monkeyid != null) {
			sql = sql + " and monkeyid=:monkeyid";
		}
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(type) && type != null && !"".equals(monkeyid)
				&& monkeyid != null) {
			query.setParameter("type", type);
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> lists = query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Infectious_Json> listt = new ArrayList<Infectious_Json>();

		if (list != null) {
			for (Object obj : list) {
				Infectious_Json i = new Infectious_Json();
				Object[] objs = (Object[]) obj;
				i.setId((Integer) objs[0]);
				i.setMonkeyid((String) objs[1]);
				i.setCdate((Date) objs[2]);
				i.setCrbmc((String)objs[3]);
				i.setVeterinarian((String) objs[4]);
				i.setProtector((String) objs[5]);
				i.setRecorder((String) objs[6]);
				i.setRemark((String) objs[7]);
				i.setDrugs_name((String)objs[8]);
				i.setDrugs_count((String)objs[9]);
				listt.add(i);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, Object>> getYMLX(String type) {
		String sql = "select id,name from quarantine where deleted!=1 and type=:type";
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(type) && type != null) {
			query.setParameter("type", type);
		}
		List<?> list = query.list();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object ob : list) {
			Object[] objs = (Object[]) ob;
			map.put("id", objs[0]);
			map.put("text", objs[1]);
			listMap.add(map);
		}

		return listMap;
	}

	public Map<String, Object> loadListVaccine(String page, String rows) {
		String sql = "select id,monkeyid,cdate,"
				+ "(select name from quarantine q where q.id=v.q_id)as ymlx,"
				+ "(select name from employee e where e.id=v.veterinarian)as veterinarian,"
				+ "(select name from employee e where e.id=v.protector)as protector,"
				+ "(select name from employee e where e.id=v.recorder)as recorder,"
				+ "remark from vaccine where deleted!=1 and ptype=:type";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("type", Constant.normal);
		List<?> lists = query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Vaccine_Json> listt = new ArrayList<Vaccine_Json>();
		if (lists != null) {
			for (Object ob : list) {
				Object[] objs = (Object[]) ob;
				Vaccine_Json v = new Vaccine_Json();
				v.setId((Integer) objs[0]);
				v.setMonkeyid((String) objs[1]);
				v.setCdate((Date) objs[2]);
				v.setYmlx((String) objs[3]);
				v.setVeterinarian((String) objs[4]);
				v.setProtector((String) objs[5]);
				v.setRecorder((String) objs[6]);
				v.setRemark((String) objs[7]);
				listt.add(v);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, String>> getAllInfectious(String type) {
		String sql="SELECT ID,NAME FROM quarantine WHERE deleted!=-1 and type=:type";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("type", type);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}

	public List<Map<String, Object>> getInfectiousMap(String type) {
		String sql="select id,name from quarantine where deleted!=1 and type=:type";
		List<?> l=getSession().createSQLQuery(sql).setParameter("type", type).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:l){
			Object[]objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("drugId", objs[0]);
			map.put("drugName", objs[1]);
			listMap.add(map);
			
		}
		return listMap;
	}

	public Quarantine getIdByNamw(String name) {
		String sql="from Quarantine where name=? and type=?";
		List<?> l=getSession().createQuery(sql).setParameter(0, name).setParameter(1, Constant.infectious).list();
		if(l.size()>0){
			return (Quarantine)l.get(0);
		}else{
			return null;
		}
		
	}
}
