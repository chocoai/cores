package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Xysh;
import com.lanen.model.Xysh_Json;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;

@Service
public class XyshServiceImpl extends BaseLongDaoImpl<Xysh> implements
		XyshService {
	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String monkeyid, String type) {
		String sql = "select id,monkeyid,cdate,ptype,"
				+ "(select e.name from employee e where x.veterinarian=e.id)as veterinarian,"
				+ " ast,alt,alp,tp,alb,ggt,tbil,bun,crea,glu,tg,chol,ldh,ck,na,k,ci "
				+ " from xysh where x.deleted=0 ";
		if (!"".equals(monkeyid) && monkeyid != null && !"".equals(type)
				&& type != null) {
			sql = sql + " and ptype=:type and monkeyid=:monkeyid ";
		}
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(monkeyid) && monkeyid != null && !"".equals(type)
				&& type != null) {
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
		List<Xysh> listx = new ArrayList<Xysh>();
		if (list != null) {
			for (Object ob : list) {
				Object[] objs = (Object[]) ob;
				Xysh x = new Xysh();
				x.setId((Integer) objs[0]);
				x.setMonkeyid((String) objs[1]);
				x.setCdate((Date) objs[2]);
				x.setPtype((String) objs[3]);
				x.setVeterinarian((String) objs[4]);
				x.setAst((String) objs[5]);
				x.setAlt((String) objs[6]);
				x.setAlp((String) objs[7]);
				x.setTp((String) objs[8]);
				x.setAlb((String) objs[9]);
				x.setGgt((String) objs[10]);
				x.setTbil((String) objs[11]);
				x.setBun((String) objs[12]);
				x.setCrea((String) objs[13]);
				x.setGlu((String) objs[14]);
				x.setTg((String) objs[15]);
				x.setChol((String) objs[16]);
				x.setLdh((String) objs[17]);
				x.setCk((String) objs[18]);
				x.setNa((String) objs[19]);
				x.setK((String) objs[20]);
				x.setCi((String) objs[21]);
				listx.add(x);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listx);
		map.put("total", lists.size());
		return map;
	}

	// group by normalid
	public Map<String, Object> getXYSH(String rows, String page,String monkeyid,Date cdate) {
		String sql = "select x.id,x.bhao,"
				+ "(select areaname from area where id=(select room from individual i where i.monkeyid=x.monkeyid))as roomid,"
				+ "(select lhao from individual i where i.monkeyid=x.monkeyid)as lhao,"
				+ "monkeyid,"
				+ "(select sex from individual i where i.monkeyid=x.monkeyid)as sex,"
				+ "(select currentweight from individual i where i.monkeyid=x.monkeyid)as weight,"
				+ "ptype,ast,alt,alp,tp,alb,ggt,tbil,bun,crea,glu,tg,chol,ldh,ck,na,k,ci ," +
						"(select title from normal n where n.id=x.normal_id) as title "
				+ " from xysh x where x.deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		//sql=sql+" group by normal_id";
		sql=sql+" order by cdate desc";
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
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Object ob : list) {
			Object[] objs = (Object[]) ob;
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", objs[0]);
			m.put("roomid", objs[2]);
			m.put("lhao", objs[3]);
			m.put("monkeyid", objs[4]);
		
			m.put("sex", objs[5]);
			m.put("weight", objs[6]);
			m.put("ptype", objs[7]);

			//在检疫信息录入时对录入信息经行了控制，不输入时默认填入""，且当血样号为""时默认采样标志为否.
			if (!"".equals(objs[1])&&objs[1]!=null) {
				m.put("xueyh", objs[1]);// 血样号
				m.put("caiy", "√");// 采样标志
			}
			m.put("ast", objs[8]);
			m.put("alt", objs[9]);
			m.put("alp", objs[10]);
			m.put("tp", objs[11]);
			m.put("alb", objs[12]);
			m.put("ggt", objs[13]);
			m.put("tbil", objs[14]);
			m.put("bun", objs[15]);
			m.put("crea", objs[16]);
			m.put("glu", objs[17]);
			m.put("tg", objs[18]);

			m.put("chol", objs[19]);
			m.put("ldh", objs[20]);
			m.put("ck", objs[21]);
			m.put("na", objs[22]);
			m.put("k", objs[23]);
			m.put("ci", objs[24]);
			
			m.put("checkId", objs[25]);//检疫编号.
			listMap.add(m);

		}
		Map map = new HashMap<String, Object>();
		map.put("rows", listMap);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, Object>> getXYSHByDate(String date,String checkId) {
		String sql = "select x.id,x.bhao,"
				+ "(select areaname from area where id=(select room from individual i where i.monkeyid=x.monkeyid))as roomid,"
				+ "(select lhao from individual i where i.monkeyid=x.monkeyid)as lhao,"
				+ "monkeyid,"
				+ "(select sex from individual i where i.monkeyid=x.monkeyid)as sex,"
				+ "(select currentweight from individual i where i.monkeyid=x.monkeyid)as weight,"
				+ "ptype,ast,alt,alp,tp,alb,ggt,tbil,bun,crea,glu,tg,chol,ldh,ck,na,k,ci "
				+ " from xysh x,normal n where x.deleted!=1 and n.id=x.normal_id ";
		if(!"".equals(date)&&date!=null){
			sql=sql+" and cdate=:date";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by normal_id";
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(date) && date != null) {
			Date cdate=DateUtil.stringToDate(date, "yyyy-MM-dd");
			query.setParameter("date", cdate);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<Map<String, Object>> listXCG = query.list();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object ob : listXCG) {
			Map m = new HashMap<String, Object>();
			Object objs[] = (Object[]) ob;
			m.put("id", objs[0]);
			m.put("roomid", objs[2]);
			m.put("lhao", objs[3]);
			m.put("monkeyid", objs[4]);
			
				m.put("sex", objs[5]);
			
			m.put("weight", objs[6]);
			m.put("ptype", objs[7]==(Constant.normal)?"常规检疫":"其他检疫");

			if (!"".equals(objs[1])&&objs[1]!=null) {
				m.put("xueyh", objs[1]);// 血样号
				m.put("caiy", "√");// 采样标志
			}else{
				m.put("xueyh", "");// 血样号
				m.put("caiy", "/");// 采样标志
			}
			m.put("ast", objs[8]!=null?objs[8]:"");
			m.put("alt", objs[9]!=null?objs[9]:"");
			m.put("alp", objs[10]!=null?objs[10]:"");
			m.put("tp", objs[11]!=null?objs[11]:"");
			m.put("alb", objs[12]!=null?objs[12]:"");
			m.put("ggt", objs[13]!=null?objs[13]:"");
			m.put("tbil", objs[14]!=null?objs[14]:"");
			m.put("bun", objs[15]!=null?objs[15]:"");
			m.put("crea", objs[16]!=null?objs[16]:"");
			m.put("glu", objs[17]!=null?objs[17]:"");
			m.put("tg", objs[18]!=null?objs[18]:"");

			m.put("chol", objs[19]!=null?objs[19]:"");
			m.put("ldh", objs[20]!=null?objs[20]:"");
			m.put("ck", objs[21]!=null?objs[21]:"");
			m.put("na", objs[22]!=null?objs[22]:"");
			m.put("k", objs[23]!=null?objs[23]:"");
			m.put("ci", objs[24]!=null?objs[24]:"");

			list.add(m);
		}
		return list;
	}

	public List<Xysh_Json> getXyshById(String monkeyid,String checkId) {
		String sql="select (select title from normal n where n.id=x.normal_id) as title," +
				"cdate,monkeyid,bhao,ast,alt,tp,alb,ggt,tbil,bun,crea,glu,tg,chol,ldh,ck,na,k,ci from xysh x where 1=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid ";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+"  and normal_id=:checkId";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<?> list=query.list();
		List<Xysh_Json> ls=new ArrayList<Xysh_Json>();
		if(list.size()>0){
			for(Object ob:list){
				Object[] objs=(Object[])ob;
				Xysh_Json x=new Xysh_Json();
				x.setTitle((String) objs[0]);
				x.setCdate((Date) objs[1]);
				x.setMonkeyid((String) objs[2]);
				x.setBhao((String)objs[3]);
				x.setAst((String)objs[4]);
				x.setAlt((String) objs[5]);
				x.setTp((String)objs[6]);
				x.setAlb((String)objs[7]);
				x.setGgt((String) objs[8]);
				x.setTbil((String) objs[9]);
				x.setBun((String) objs[10]);
				x.setCrea((String)objs[11]);
				x.setGlu((String)objs[12]);
				x.setTg((String)objs[13]);
				x.setChol((String)objs[14]);
				x.setLdh((String)objs[15]);
				x.setCk((String)objs[16]);
				x.setNa((String)objs[17]);
				x.setK((String)objs[18]);
				x.setCi((String)objs[19]);
				ls.add(x);
			}
			return ls;
		}else{
			return null;
		}
	}

}
