package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Xcg;
import com.lanen.model.Xcg_Json;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
@Service
public class XcgServiceImpl extends BaseLongDaoImpl<Xcg> implements XcgService {
	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String monkeyid, String type) {
		String sql="select id,monkeyid,cdate,ptype," +
				"(select e.name from employee e where x.veterinarian=e.id)as veterinarian," +
				"(select e.name from employee e where x.recorder=e.id)as recorder," +
				" wbc,rbc,hgb,hct,plt,mcv,mch,mchc,lym,mid,gra " +
				" from xcg x where x.deleted=0 ";
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
		List<Xcg> listx=new ArrayList<Xcg>();
		if(list!=null){
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				Xcg x=new Xcg();
				x.setId((Integer)objs[0]);
				x.setMonkeyid((String)objs[1]);
				x.setCdate((Date)objs[2]);
				x.setPtype((String)objs[3]);
				x.setVeterinarian((String)objs[4]);
				x.setRecorder((String)objs[5]);
				x.setWbc((String)objs[6]);
				x.setRbc((String)objs[7]);
				x.setHgb((String)objs[8]);
				x.setHct((String)objs[9]);
				x.setPlt((String)objs[10]);
				x.setMcv((String)objs[11]);
				x.setMch((String)objs[12]);
				x.setMchc((String)objs[13]);
				x.setLym((String)objs[14]);
				x.setMid((String)objs[15]);
				x.setGra((String)objs[16]);
				listx.add(x);
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", listx);
		map.put("total", lists.size());
		return map;
	}

	public Map<String, Object> getXCG(String rows, String page,String monkeyid,Date cdate) {
		String sql="select x.id,x.bhao," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=x.monkeyid))as roomid," +
				"(select lhao from individual i where i.monkeyid=x.monkeyid)as lhao," +
				"monkeyid," +
				"(select sex from individual i where i.monkeyid=x.monkeyid)as sex," +
				"(select currentweight from individual i where i.monkeyid=x.monkeyid)as weight," +
				"ptype,wbc,rbc,hgb,hct,plt,mcv,mch,mchc,lym,mid,gra ," +
				"(select title from normal n where n.id=x.normal_id)as title " +
				" from xcg x where x.deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		//sql=sql+" group by  normal_id";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("id", objs[0]);
			m.put("roomid", objs[2]);
			m.put("lhao", objs[3]);
			m.put("monkeyid", objs[4]);
			m.put("sex", objs[5]);
			m.put("weight", objs[6]);
			m.put("ptype", objs[7]==(Constant.normal)?"常规检疫":"出口检疫");
			
			if (!"".equals(objs[1])&&objs[1]!=null) {
				m.put("xueyh", objs[1]);//血样号
				m.put("caiy", "√");//采样标志
			}
			m.put("wbc", objs[8]!=null?objs[8]:"");
			m.put("rbc", objs[9]!=null?objs[9]:"");
			m.put("hgb", objs[10]!=null?objs[10]:"");
			m.put("hct", objs[11]!=null?objs[11]:"");
			m.put("plt", objs[12]!=null?objs[12]:"");
			m.put("mcv", objs[13]!=null?objs[13]:"");
			m.put("mch", objs[14]!=null?objs[14]:"");
			m.put("mchc", objs[15]!=null?objs[15]:"");
			m.put("lym", objs[16]!=null?objs[16]:"");
			m.put("mid", objs[17]!=null?objs[17]:"");
			m.put("gra", objs[18]!=null?objs[18]:"");
			
			m.put("checkId", objs[19]);//检疫编号.
			listMap.add(m);
			
		}
		Map map=new HashMap<String,Object>();
		map.put("rows", listMap);
		map.put("total", lists.size());
		return map;
	}

	//按检疫编号打印.
	public List<Map<String, Object>> getXCGByDate(String date,String checkId) {
		String sql="select x.id,x.bhao," +
				"(select areaname from area where id=(select room from individual i where i.monkeyid=x.monkeyid))as roomid," +
				"(select lhao from individual i where i.monkeyid=x.monkeyid)as lhao," +
				"monkeyid," +
				"(select sex from individual i where i.monkeyid=x.monkeyid)as sex," +
				"(select currentweight from individual i where i.monkeyid=x.monkeyid)as weight," +
				"ptype,wbc,rbc,hgb,hct,plt,mcv,mch,mchc,lym,mid,gra " +
				" from xcg x,normal n where x.deleted!=1 and n.id=x.normal_id";
		if(!"".equals(date)&&date!=null){
			sql=sql+" and cdate=:date";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by  normal_id";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(date)&&date!=null){
			Date cdate=DateUtil.stringToDate(date, "yyyy-MM-dd");
			query.setParameter("date", cdate);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<Map<String,Object>> listXCG=query.list();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Object ob:listXCG){
			Map m=new HashMap<String,Object>();
			Object objs[]=(Object[])ob;
			m.put("id", objs[0]);
			m.put("roomid", objs[2]);
			m.put("lhao", objs[3]);
			m.put("monkeyid", objs[4]);
			
				m.put("sex", objs[5]);
			
			m.put("weight", objs[6]);
			m.put("ptype", objs[7]==(Constant.normal)?"常规检疫":"其他检疫");
			
			if (!"".equals(objs[1])&&objs[1]!=null) {
				m.put("xueyh", objs[1]);//血样号
				m.put("caiy", "√");//采样标志
			}else{
				m.put("xueyh", "");//血样号
				m.put("caiy", "/");//采样标志
			}
			m.put("wbc", objs[8]!=null?objs[8]:"");
			m.put("rbc", objs[9] !=null?objs[9]:"");
			m.put("hgb", objs[10] !=null?objs[10]:"");
			m.put("hct", objs[11] !=null?objs[11]:"");
			m.put("plt", objs[12] !=null?objs[12]:"");
			m.put("mcv", objs[13] !=null?objs[13]:"");
			m.put("mch", objs[14] !=null?objs[14]:"");
			m.put("mchc", objs[15] !=null?objs[15]:"");
			m.put("lym", objs[16] !=null?objs[16]:"");
			m.put("mid", objs[17] !=null?objs[17]:"");
			m.put("gra", objs[18] !=null?objs[18]:"");
			
			list.add(m);
		}
		return list;
	}

	public List<Xcg_Json> getXcgById(String monkeyid,String checkId) {
		String sql="select (select title from normal n where n.id=x.normal_id) as title," +
				"cdate,monkeyid,bhao,wbc,rbc,hgb,hct,plt,mcv,mch,mchc,lym,mid,gra from xcg x where 1=1";
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
		List<Xcg_Json> ls=new ArrayList<Xcg_Json>();
		if(list.size()>0){
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				Xcg_Json x=new Xcg_Json();
				x.setTitle((String)objs[0]);
				x.setCdate((Date)objs[1]);
				x.setMonkeyid((String) objs[2]);
				x.setBhao((String)objs[3]);
				x.setWbc((String)objs[4]);
				x.setRbc((String)objs[5]);
				x.setHgb((String)objs[6]);
				x.setHct((String)objs[7]);
				x.setPlt((String)objs[8]);
				x.setMcv((String)objs[9]);
				x.setMch((String)objs[10]);
				x.setMchc((String)objs[11]);
				x.setLym((String)objs[12]);
				x.setMid((String)objs[13]);
				x.setGra((String)objs[14]);
				ls.add(x);
			}
			return ls;
		}else{
			return null;
		}	
	}

}
