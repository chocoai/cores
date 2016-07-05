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
import com.lanen.model.Leavebreast;
import com.lanen.model.LeavebreastErport;
import com.lanen.model.Leavebreast_Json;
@Service
public class LeavebreastServiceImpl extends BaseLongDaoImpl<Leavebreast> implements
		LeavebreastService {

	public Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, String motherid, String leavebreastdate) {
		String sql = "SELECT id,monkeyid,motherid,leavebreastdate,leavebreastweight " +
				     " ,keeper,(select name from employee where id=keeper ) as keeperName " +
				     " ,recorder,(select name from employee where id=recorder ) as recorderName " +
				     " ,operater,(select name from employee where id=operater ) as operaterName " +
				     " ,deleted,remark" +
				     " FROM gestation WHERE deleted != 1 ";
			if(null != monkeyid && !monkeyid.equals("")){
			    sql = sql +" and monkeyid = :monkeyid ";
			}
			if(null != motherid && !motherid.equals("") ){
			    sql = sql +" and motherid = :motherid ";
			}
			if(null != leavebreastdate && !leavebreastdate.equals("") ){
			    sql = sql +" and leavebreastdate = :leavebreastdate ";
			}
			sql = sql +" order by checkdate desc";
			Query query = getSession().createSQLQuery(sql);
			if(null != monkeyid && !monkeyid.equals("")){
			    query.setParameter("monkeyid", monkeyid);
			}
			if(null != motherid && !motherid.equals("")){
			    query.setParameter("motherid", motherid);
			}
			if(null != leavebreastdate && !leavebreastdate.equals("")){
			    query.setParameter("leavebreastdate", leavebreastdate);
			}
			List<?> listtotal = query.list();
			//当为缺省值的时候进行赋值        
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
			List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
			
			
			List<Leavebreast_Json> list1 = new ArrayList<Leavebreast_Json>();
			if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				
				Leavebreast_Json json = new Leavebreast_Json(); 
				Integer id=(Integer)objs[0];
				Long idL=Long.valueOf(id+"");
				json.setId(idL);
				json.setMonkeyid((String)objs[1]);
				json.setMotherid((String)objs[2]);
				json.setLeavebreastdate((Date)objs[3]+"");
				json.setLeavebreastweight((Float)objs[4]);
				Integer keeperId=(Integer)objs[5];
				Long keeperIdL=Long.valueOf(id+"");
				list1.add(json);
			}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list1);
			map.put("total", listtotal.size());
			return map;
	}
	//离乳记录报表
	public List<LeavebreastErport> getLeavebreastErport(String monkeyid,String leavebreastDate){
		String sql="select (select areaname from area where id=(select room from individual i where l.motherid=i.monkeyid))as mroom," +
				"(select lhao from individual i where l.motherid=i.monkeyid)as mlhao,monkeyid," +
				"(select sex from individual i where l.monkeyid=i.monkeyid)as sex,leavebreastweight,motherid," +
				"(select areaname from area where id=(select room from individual i where l.monkeyid=i.monkeyid))as zroom,remark " +
				"from leavebreast l where l.deleted!=1";
		if(!"".equals(leavebreastDate)&&leavebreastDate!=null){
			sql=sql+" and leavebreastdate=:date";
		}
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		Query q=getSession().createSQLQuery(sql);
		
		if(!"".equals(leavebreastDate)&&leavebreastDate!=null){
			q.setParameter("date", leavebreastDate);
		}
		if(!"".equals(monkeyid)&&monkeyid!=null){
			q.setParameter("monkeyid", monkeyid);
		}
		List<?> list=q.list();
		List<LeavebreastErport> listLevaebreastErport=new ArrayList<LeavebreastErport>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			LeavebreastErport l=new LeavebreastErport();
			l.setMroom((String)objs[0]);
			l.setMlhao((String) objs[1]);
			l.setMonkeyid((String)objs[2]);
			if(objs[3]==null||"".equals(objs[3])){
				l.setSex("--");
			}else{
				l.setSex(((BigInteger)objs[3]).intValue()+"");
			}			
			l.setWeight((objs[4])==null?"":(objs[4]+""));
			l.setMotherid((String)objs[5]);
			l.setZroom((String)(objs[6]));
			l.setRemark((String)objs[7]);
			
			listLevaebreastErport.add(l);
		}
		return listLevaebreastErport;
	}
	//报表之前分页
	public Map<String,Object> getLeavebreast(String page, String rows,String monkeyid,String leavebreastdate){
		String sql="select (select room from individual i where l.motherid=i.monkeyid)as mroom," +
				"(select lhao from individual i where l.motherid=i.monkeyid)as mlhao,monkeyid," +
				"(select sex from individual i where l.monkeyid=i.monkeyid)as sex,leavebreastweight,motherid," +
				"(select room from individual i where l.monkeyid=i.monkeyid)as zroom,remark,leavebreastdate " +
				"from leavebreast l where l.deleted!=1";
		if(!"".equals(leavebreastdate)&&leavebreastdate!=null){
			sql=sql+" and leavebreastdate=:leavebreastdate";
		}
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		Query q=getSession().createSQLQuery(sql);
		
		if(!"".equals(leavebreastdate)&&leavebreastdate!=null){
			q.setParameter("leavebreastdate", leavebreastdate);
		}
		if(!"".equals(monkeyid)&&monkeyid!=null){
			q.setParameter("monkeyid", monkeyid);
		}
		List<?> lists=q.list();
		
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = q.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		
		List<LeavebreastErport> listLevaebreastErport=new ArrayList<LeavebreastErport>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			LeavebreastErport l=new LeavebreastErport();
			l.setMroom(String.valueOf(objs[0]));
			l.setMlhao((String) objs[1]);
			l.setMonkeyid((String)objs[2]);
			if(objs[3]==null||"".equals(objs[3])){
				l.setSex("--");
			}else{
				l.setSex(((BigInteger)objs[3]).intValue()+"");
			}			
			l.setWeight((String.valueOf( objs[4])));
			l.setMotherid((String)objs[5]);
			l.setZroom(String.valueOf(objs[6]));
			l.setRemark((String)objs[7]);
			l.setLeavebreastdate((String)objs[8]);
			listLevaebreastErport.add(l);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", listLevaebreastErport);
		map.put("total", lists.size());
		return map;
	}
	public List<Leavebreast> getLeavebreastById(String monkeyid) {
		String sql = "select (select areaname from area where id=(select room from individual i where l.motherid=i.monkeyid))as mroom,"
				+ "(select lhao from individual i where l.motherid=i.monkeyid)as mlhao,monkeyid,"
				+ "(select sex from individual i where l.monkeyid=i.monkeyid)as sex,leavebreastweight,motherid,"
				+ "(select areaname from area where id=(select room from individual i where l.monkeyid=i.monkeyid))as zroom,remark,leavebreastdate,leavebreastweight "
				+ "from leavebreast l where l.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		Query q = getSession().createSQLQuery(sql);

		if (!"".equals(monkeyid)&&monkeyid!=null) {
			q.setParameter("monkeyid", monkeyid);
		}
		List<?> list = q.list();
		List<Leavebreast> listLevaebreast = new ArrayList<Leavebreast>();
		for (Object ob : list) {
			Object[] objs = (Object[]) ob;
			Leavebreast l = new Leavebreast();
			l.setMonkeyid((String) objs[2]);
			l.setMotherid((String) objs[5]);
			l.setRemark((String) objs[7]);
			l.setLeavebreastdate((String) objs[8]);
			l.setLeavebreastweight((Float)objs[9]);
			listLevaebreast.add(l);
		}
		return listLevaebreast;
	}
}
