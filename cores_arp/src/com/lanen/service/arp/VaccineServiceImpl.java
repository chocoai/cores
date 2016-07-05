package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Vaccine;
import com.lanen.model.Vaccine_Json;
import com.lanen.util.Constant;

@Service
public class VaccineServiceImpl extends BaseLongDaoImpl<Vaccine> implements
		VaccineService {

	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String type, String monkeyid) {
		String sql = "select id,monkeyid,cdate,"
				+ "(select name from quarantine q where v.q_id=q.id)as ymlx,"
				+ "(select name from employee e where e.id=v.veterinarian)as veterinarian,"
				+ "(select name from employee e where e.id=v.protector)as protector,"
				+ "(select name from employee e where e.id=v.recorder)as recorder,remark  "
				+ "from vaccine v where v.deleted!=1 ";
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
		List<Vaccine_Json> listt = new ArrayList<Vaccine_Json>();

		if (list != null) {
			for (Object obj : list) {
				Vaccine_Json v = new Vaccine_Json();
				Object[] objs = (Object[]) obj;
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

	public List<Map<String, String>> getYMLX(String type) {
		String sql = "select id,name from quarantine where deleted!=1 and type=:type";
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(type) && type != null) {
			query.setParameter("type", type);
		}
		List<?> list = query.list();
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		map=new HashMap<String,String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		for (Object ob : list) {
			Object[] objs = (Object[]) ob;
			map=new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String)objs[1]);
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
	//SELECT *, COUNT(DISTINCT monkeyid) FROM vaccine GROUP BY monkeyid
	public Map<String,Object> getVaccine(String page, String rows,String monkeyid,Date cdate) {
		String sql = "select id,q_id," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=v.monkeyid))as room," +
				"(select lhao from individual i where i.monkeyid=v.monkeyid)as lhao,monkeyid," +
				"(select sex from individual i where i.monkeyid=v.monkeyid)as sex,remark,normal_id, " +
				"(select title from normal n where n.id=v.normal_id)as title " +
				"from vaccine v where v.deleted!=1 ";
		
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+"and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+"and cdate=:cdate";
		}
		sql=sql+" group by v.normal_id,v.monkeyid";
		Query query = getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
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
				v.setQ_id((String)objs[1]);
				//v.setRoomId((BigInteger) objs[2]);//int类型，java.math.BigInteger cannot be cast to java.lang.Integer
				v.setRoomname((String) objs[2]);
				v.setLhao((String) objs[3]);
				v.setMonkeyid((String) objs[4]);
				v.setSex((BigInteger)objs[5]);//java.math.BigInteger cannot be cast to java.lang.String
				v.setRemark((String) objs[6]);
				v.setNormal_id((BigInteger)objs[7]);
				v.setTitle((String)objs[8]);
				listt.add(v);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}
	public List<Vaccine_Json> getVaccine(String monkeyid,Date cdate,String checkId) {
		String sql = "select v.id,q_id," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=v.monkeyid))as room," +
				"(select lhao from individual i where i.monkeyid=v.monkeyid)as lhao,monkeyid," +
				"(select sex from individual i where i.monkeyid=v.monkeyid)as sex,remark,normal_id, " +
				"(select title from normal n where n.id=v.normal_id)as title " +
				"from vaccine v,normal n where v.deleted!=1 ";
		
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+"and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId and n.id=v.normal_id";
		}
		sql=sql+" group by v.normal_id,v.monkeyid";
		Query query = getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<?> lists = query.list();
		
		List<Vaccine_Json> listt = new ArrayList<Vaccine_Json>();
		if (lists != null) {
			for (Object ob : lists) {
				Object[] objs = (Object[]) ob;
				Vaccine_Json v = new Vaccine_Json();
				v.setId((Integer) objs[0]);
				v.setQ_id((String)objs[1]);
				//v.setRoomId((BigInteger) objs[2]);//int类型，java.math.BigInteger cannot be cast to java.lang.Integer
				v.setRoomname((String) objs[2]);
				v.setLhao((String) objs[3]);
				v.setMonkeyid((String) objs[4]);
				v.setSex((BigInteger)objs[5]);//java.math.BigInteger cannot be cast to java.lang.String
				v.setRemark((String) objs[6]);
				v.setNormal_id((BigInteger)objs[7]);
				v.setTitle((String)objs[8]);
				listt.add(v);
			}
		}
		
		return listt;
	}
	/**
	 * 根据猴子ID获得疫苗ID
	 * 
	 * @param id
	 * @return
	 */
	public List<?> getVaccineId(String monkeyid,String normalid){
		String sql="select q_id,ypin from vaccine  where monkeyid=:monkeyid and normal_id=:normalid";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(normalid)&&normalid!=null){
			query.setParameter("normalid", normalid);
		}
		List<?> list=query.list();
		return list;
	}

	public List<String> getDistinctMonkeyid() {
		String sql="SELECT *, COUNT(DISTINCT monkeyid) FROM vaccine where deleted!=1 GROUP BY monkeyid";
		List<String> listMonkeyid=getSession().createSQLQuery(sql).list();
		return listMonkeyid;
	}

	public List<?> getVaccineIdByName(String name) {
		String sql="from Quarantine ";
		if(!"".equals(name)&&name!=null){
			sql=sql+" where name=?";
		}
		Query query=getSession().createQuery(sql);
		if(!"".equals(name)&&name!=null){
			query.setParameter(0, name);
		}
		return query.list();
	}
	public List<Vaccine> getLast3VaccineRecord(String monkeyid){
		String sql="select (select name from quarantine q where q.id=v.q_id)q_idName,ypin from vaccine v ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where monkeyid=:monkeyid ";
		}
		sql=sql+"order by cdate desc limit 0,3";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> list=query.list();
		List<Vaccine> listVaccine=new ArrayList<Vaccine>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Vaccine v=new Vaccine();
			v.setQ_id((String)objs[0]);
			v.setYpin((String)objs[1]);
			listVaccine.add(v);
		}
		return listVaccine;
	}

	public List<Vaccine_Json> getVaccineById(String monkeyid,String checkId) {
		String sql="select (select name from quarantine q where q.id=v.q_id)q_idName,ypin," +
				"(select title from normal n where v.normal_id=n.id)as title,cdate,normal_id,monkeyid  from vaccine v where 1=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid ";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+"  and normal_id=:checkId";
		}
		sql=sql+" group by v.normal_id,v.monkeyid ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
				
		List<?> list=query.list();
		List<Vaccine_Json> listVaccine=new ArrayList<Vaccine_Json>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Vaccine_Json v=new Vaccine_Json();
			v.setQ_id((String)objs[0]);
			v.setYpin((String)objs[1]);
			v.setTitle((String)objs[2]);
			v.setCdate((Date)objs[3]);
			v.setNormal_id((BigInteger)objs[4]);
			v.setMonkeyid((String)objs[5]);
			listVaccine.add(v);
		}
		return listVaccine;
	}
}
