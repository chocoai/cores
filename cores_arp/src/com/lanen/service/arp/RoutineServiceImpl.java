package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Quarantine_Json;
import com.lanen.model.Routine;
import com.lanen.model.Routine_Json;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class RoutineServiceImpl extends BaseLongDaoImpl<Routine> implements
		RoutineService {

	public Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid) {

		/*
		 * String sql =
		 * "SELECT distinct i.monkeyid,surface_normal,parasite_normal,virus_normal,bacteria_normal,"
		 * +
		 * "vaccine_normal,infectious_normal,tb_normal,x_normal,xcg_normal,xysh_normal from individual i"
		 * + " left join  surface s on i.monkeyid=s.monkeyid" +
		 * " left join  parasite p on i.monkeyid=p.monkeyid " +
		 * " left join  virus v on i.monkeyid=v.monkeyid" +
		 * " left join  bacteria b on i.monkeyid=b.monkeyid" +
		 * " left join  vaccine v2 on i.monkeyid=v2.monkeyid" +
		 * " left join  infectious i2 on i.monkeyid=i2.monkeyid" +
		 * " left join  tb t on i.monkeyid=t.monkeyid" +
		 * " left join  x x on i.monkeyid=x.monkeyid" +
		 * " left join  xcg x2 on i.monkeyid=x2.monkeyid" +
		 * " left join  xysh x3 on i.monkeyid=x3.monkeyid ";
		 */
		/*String sql = "SELECT distinct i.id,s.monkeyid,p.monkeyid,v.monkeyid,b.monkeyid,"
				+ "v2.monkeyid,i2.monkeyid,t.monkeyid,x.monkeyid,x2.monkeyid,x3.monkeyid from individual i"
				+ " left join  surface s on i.monkeyid=s.monkeyid"
				+ " left join  parasite p on i.monkeyid=p.monkeyid "
				+ " left join  virus v on i.monkeyid=v.monkeyid"
				+ " left join  bacteria b on i.monkeyid=b.monkeyid"
				+ " left join  vaccine v2 on i.monkeyid=v2.monkeyid"
				+ " left join  infectious i2 on i.monkeyid=i2.monkeyid"
				+ " left join  tb t on i.monkeyid=t.monkeyid"
				+ " left join  x x on i.monkeyid=x.monkeyid"
				+ " left join  xcg x2 on i.monkeyid=x2.monkeyid"
				+ " left join  xysh x3 on i.monkeyid=x3.monkeyid ";*/
		//新增检测时间，常规检疫列表中存在同一monkeyid不同checkdate记录
		String sql="select id, normallist,surface,parasite,virus,bacteria,vaccine,infectious,tb,x,xcg,xysh," +
				" checkdate " +
				"from normal n where n.deleted!=1 and ISNULL(monkeylist)";
		if (monkeyid != null && !("").equals(monkeyid)) {
			sql = sql + " and n.normallist=:monkeyid";
		}
		Query query = getSession().createSQLQuery(sql);
		if (monkeyid != null && !("").equals(monkeyid)) {
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listtotal = query.list();
		
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();

		List<Routine_Json> list1 = new ArrayList<Routine_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;

				Routine_Json json = new Routine_Json();
				json.setId((Integer)objs[0]);
				json.setMonkeyid((String) objs[1]);
				json.setSurface((String) objs[2]);
				json.setParasite((String) objs[3]);
				json.setVirus((String) objs[4]);
				json.setBacteria((String) objs[5]);
				json.setVaccine((String) objs[6]);
				json.setInfectious((String) objs[7]);
				json.setTb((String) objs[8]);
				json.setX((String) objs[9]);
				json.setXcg((String) objs[10]);
				json.setXysh((String) objs[11]);
				json.setCheckdate((Date)objs[12]);
				list1.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

	public Map<String, Object> loadNormalList() {
		String sql = "select id,name,type from quarantine";
		Query query = getSession().createQuery(sql);
		List<Quarantine_Json> list1 = new ArrayList<Quarantine_Json>();
		List<?> listtotal = query.list();
		if (null != listtotal) {
			for (Object obj : listtotal) {
				Object[] objs = (Object[]) obj;

				Quarantine_Json json = new Quarantine_Json();
				json.setIconCls("icon-space");
				Integer id = (Integer) objs[0];
				Long idL = Long.valueOf(id + "");
				json.setId(idL);
				json.setName((String) objs[1]);
				json.setType((String) objs[2]);

				list1.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

	//
	public Map<String, Object> virus(String monkeyid) {
		String sql = "select id from virus ";
		if (!"".equals(monkeyid) && monkeyid != null) {
			sql = sql + " where monkeyid=:monkeyid";
		}
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(monkeyid) && monkeyid != null) {
			query.setParameter("monkeyid", monkeyid);
		}
		// int sum=(Integer)query.iterate().next();
		List<?> list = query.list();
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.size() > 0) {
			map.put("checked", true);
		}
		return map;

	}
}
