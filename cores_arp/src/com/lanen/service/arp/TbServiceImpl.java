package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Tb;
@Service
public class TbServiceImpl extends BaseLongDaoImpl<Tb> implements TbService {
	public Map<String, Object> loadListByMonkeyId(String rows, String page,
			String monkeyid, String type) {
		String sql="select id,monkeyid,cdate,ptype,q_id,drugs_count," +
				"tb24,(select e.name from employee e where t.tb24v=e.id)as tb24v," +
				"tb48,(select e.name from employee e where t.tb48v=e.id)as tb48v," +
				"tb72,(select e.name from employee e where t.tb72v=e.id)as tb72v," +
				"(select e.name from employee e where t.veterinarian=e.id)as veterinarian," +
				"(select e.name from employee e where t.protector=e.id)as protector," +
				"(select e.name from employee e where t.recorder=e.id)as recorder,remark " +
				"from tb t where t.deleted!=1 ";
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
		List<Tb> listtb=new ArrayList<Tb>();
		if(list!=null){
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				Tb t=new Tb();
				t.setId((Integer)objs[0]);
				t.setMonkeyid((String)objs[1]);
				t.setCdate((Date)objs[2]);
				t.setPtype((String)objs[3]);
				t.setQ_id((String)objs[4]);//tb药剂
				t.setDrugs_count((String)objs[5]);
				t.setTb24((String)objs[6]);
				t.setTb24v((String) objs[7]);
				t.setTb48((String)objs[8]);
				t.setTb48v((String) objs[9]);
				t.setTb72((String) objs[10]);
				t.setTb72v((String)objs[11]);
				t.setVeterinarian((String)objs[12]);
				t.setProtector((String)objs[13]);
				t.setRecorder((String)objs[14]);
				t.setRemark((String)objs[15]);
				
				listtb.add(t);
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", listtb);
		map.put("total", lists.size());
		return map;
	}
	//每个检测项目同时加进每条记录中，不用group by monkeyid.
	public Map<String, Object> getTB(String rows, String page,String monkeyid,Date cdate) {
		String sql="select id,"+
			"(select areaname from area where id=(select room from individual i where i.monkeyid=t.monkeyid))as roomid,"+
			"(select lhao from individual i where i.monkeyid=t.monkeyid)as lhao,"+
			"monkeyid,"+
			"(select sex from individual i where i.monkeyid=t.monkeyid)as sex,"+
			"tb24,tb48,tb72,"+
			"remark ,(select title from normal n where n.id=t.normal_id) as title from tb t where t.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		//sql=sql+" group by normal_id";//可以不要
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Object[] objs=(Object[])ob;
			Map map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]);
			map.put("monkeyid", objs[3]);
			map.put("sex", objs[4]);
			map.put("tb24", objs[5]);
			map.put("tb48", objs[6]);
			map.put("tb72", objs[7]);
			map.put("remark", objs[8]);
			
			map.put("checkId", objs[9]);
			if ((!"".equals(objs[5])&&objs[5]!=null)||(!"".equals(objs[6])&&objs[6]!=null)||(!"".equals(objs[6])&&objs[6]!=null)) {
				//下面格式化输出TB检测项目结果   已注射"√" 阴性"-,+"  可疑"++" 阳性"+++,++++" 未检"/"
				//检测项目备注 需要统一标识。此处没有做处理
				map.put("rightEyes", "√");
			}
			String tb24=(String)objs[5];
			String tb48=(String)objs[6];
			String tb72=(String)objs[7];
			
			listMap.add(map);
		}
		Map returnMap=new HashMap<String,Object>();
		returnMap.put("rows", listMap);
		returnMap.put("total",listtotal.size());
		return returnMap;
	}
	
	public Map<String, Object> getTB(String monkeyid,String cdate,String checkId) {
		String sql="select t.id,"+
			"(select areaname from area where id=(select room from individual i where i.monkeyid=t.monkeyid))as roomid,"+
			"(select lhao from individual i where i.monkeyid=t.monkeyid)as lhao,"+
			"monkeyid,"+
			"(select sex from individual i where i.monkeyid=t.monkeyid)as sex,"+
			"tb24,tb48,tb72,"+
			"remark from tb t,normal n where t.deleted!=1 and n.id=t.normal_id ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by normal_id";//可以不要
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<?> listtotal=query.list();
		
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:listtotal){
			Object[] objs=(Object[])ob;
			Map map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]!=null?objs[2]:"");
			map.put("monkeyid", objs[3]);
			map.put("sex", objs[4]);
			map.put("tb24", objs[5]!=null?objs[5]:"");
			map.put("tb48", objs[6]!=null?objs[6]:"");
			map.put("tb72", objs[7]!=null?objs[7]:"");
			map.put("remark", objs[8]==null?"":objs[8]);
			
			if ((!"".equals(objs[5])&&objs[5]!=null)||(!"".equals(objs[6])&&objs[6]!=null)||(!"".equals(objs[6])&&objs[6]!=null)) {
				//下面格式化输出TB检测项目结果   已注射"√" 阴性"-,+"  可疑"++" 阳性"+++,++++" 未检"/"
				//检测项目备注 需要统一标识。此处没有做处理
				map.put("rightEyes", "√");
			}else{
				map.put("rightEyes", "/");
			}
			
			listMap.add(map);
		}
		Map returnMap=new HashMap<String,Object>();
		returnMap.put("rows", listMap);
		returnMap.put("total",listtotal.size());
		return returnMap;
	}

}
