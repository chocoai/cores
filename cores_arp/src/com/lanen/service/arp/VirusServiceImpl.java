package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Virus;
import com.lanen.model.Virus_Json;
@Service
public class VirusServiceImpl extends BaseLongDaoImpl<Virus> implements VirusService{
	
	public Map<String,Object> loadListByMonkeyIdAndCdate(String page,String rows,String type){
		String sql="select id,monkeyid,cdate,ptype,xueq," +
		"(select e.name from employee e where v.veterinarian=e.id)as veterinarian," +
		"(select e.name from employee e where v.protector=e.id)as protector," +
		"(select e.name from employee e where v.recorder=e.id)as recorder,remark " +
		" from virus v group by monkeyid,cdate";
		
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null){
			query.setParameter("type", type);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Virus> listt=new ArrayList<Virus>();

		if(list!=null){
			for(Object obj:list){
				Virus s=new Virus();
				Object[] objs=(Object[])obj;
				//s.setId((Integer)objs[0]);
				s.setMonkeyid((String)objs[1]);
				//s.setCdate(DateUtil.stringToDate((String)objs[2], ""));
				s.setCdate((Date)objs[2]);
				s.setPtype((String)objs[3]);
				s.setXueq((String)objs[4]);
				s.setVeterinarian((String)objs[5]);
				s.setProtector((String)objs[6]);
				s.setRecorder((String)objs[7]);
				s.setRemark((String)objs[8]);
				
				listt.add(s);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
		
	}

	public Map<String, Object> loadListItem(String monkeyid,String cdate) {
		
		String sql="select " +
				"(select name from quarantine q where v.q_id=q.id) as q_id," +
				"resoult," +
				"(select name from quarantine q where q.id=v.qconfig_id) as qconfig_id," +
				"drugs_name,drugs_count from virus v where deleted!=1" ;
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null&&!"".equals(cdate)&&cdate!=null){
			query.setParameter("monkeyid", monkeyid);
			query.setParameter("cdate", cdate);
		}
		List<?> lists=query.list();
		
		List<Virus_Json> listt=new ArrayList<Virus_Json>();

		if(lists!=null){
			for(Object obj:lists){
				Virus_Json v=new Virus_Json();
				Object[] objs=(Object[])obj;
				
				v.setQ_id((String)objs[0]);
				v.setResoult((Byte)objs[1]);
				v.setQconfig_id((String)objs[2]);
				v.setDrugs_name((String)objs[3]);
				v.setDrugs_count((String)objs[4]);
				listt.add(v);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}
	//每次添加纪录时,若果virus表存在monkeyid,则更新，否则insert.
	//否则使用group by monkeyid 无效.
	public Map<String, Object> getVirus(String rows, String page,String monkeyid,Date cdate) {
		String sql="select id,"+
			"(select areaname from area where id=(select room from individual i where i.monkeyid=v.monkeyid))as roomid,"+
			"(select lhao from individual i where i.monkeyid=v.monkeyid)as lhao," +
			"monkeyid,"+
			"(select sex from individual i where i.monkeyid=v.monkeyid)as sex,"+
			"xueq,"+
			"remark ,(select title from normal n where n.id=v.normal_id)as title ," +
			"bv,stlv,srv,siv,filo " +
			"from virus v where v.deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and v.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and v.cdate=:cdate";
		}
		//sql=sql+" group by v.normal_id";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> listVir=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Object []objs=(Object[])ob;
			Map virusMap=new HashMap<String,Object>();
			virusMap.put("id", objs[0]);
			virusMap.put("roomid", objs[1]);
			virusMap.put("lhao", objs[2]);
			virusMap.put("monkeyid", objs[3]);
			
				virusMap.put("sex", objs[4]);
			
			
			virusMap.put("xueq", objs[5]);
			virusMap.put("remark", objs[6]);
			
			//下面增加检测项目,根据猴子编号查询该猴子的检测项目信息.
			String monkeyids=(String)objs[3];
			List<?> listItems=getVirusItems(monkeyids);
			for(int i=0;i<listItems.size();i++){
				Object[]obj=(Object[])listItems.get(i);
				if(obj[0]!=null&&!"".equals(obj[0])){
					//virusMap.put("bv", obj[0]);
				}
				if(obj[1]!=null&&!"".equals(obj[1])){
					//virusMap.put("stlv", obj[1]);
				}
				if(obj[2]!=null&&!"".equals(obj[2])){
					//virusMap.put("srv", obj[2]);
				}
				if(obj[3]!=null&&!"".equals(obj[3])){
					//virusMap.put("siv", obj[3]);
				}
				if(obj[4]!=null&&!"".equals(obj[4])){
					//virusMap.put("filo", obj[4]);
				}
				
			}
			virusMap.put("checkId", objs[7]);//检疫编号.
			virusMap.put("bv", objs[8]);
			virusMap.put("stlv", objs[9]);
			virusMap.put("srv", objs[10]);
			virusMap.put("siv", objs[11]);
			virusMap.put("filo", objs[12]);
			if (!"".equals(objs[5])&&objs[5]!=null) {
				virusMap.put("caiy", "√");//已采样
			}else{
				virusMap.put("caiy", "/");//未采样
			}
			listMap.add(virusMap);
		}
		Map map=new HashMap<String,Object>();
		map.put("rows", listMap);
		map.put("total", listVir.size());
		return map;
	}
	/**
	 * 由动物编号获取对应的病毒检测项目,每个q_id对应唯一项目,
	 * 且在常规检疫添加纪录时每个q_id需对应更新每个项目。如q_id=26对应bv,q_id=27对应stlv.
	 */
	public List<?> getVirusItems(String monkeyid){
		String sql="select bv,stlv,srv,siv,filo from virus where deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listItem=query.list();
		/*for(int i=0;i<listItem.size();i++){
			Object[]objs=(Object[])listItem.get(i);
			Map map=new HashMap();
			map.put("bv", objs[0]);
			map.put("stlv", objs[1]);
			map.put("srv", objs[2]);
			map.put("siv", objs[3]);
			map.put("filo", objs[4]);
		}*/
		return listItem;
	}
	
	public Map<String, Object> getVirus(String monkeyid,String cdate,String checkId) {
		String sql="select v.id,"+
			"(select areaname from area where id=(select room from individual i where i.monkeyid=v.monkeyid))as roomid,"+
			"(select lhao from individual i where i.monkeyid=v.monkeyid)as lhao," +
			"monkeyid,"+
			"(select sex from individual i where i.monkeyid=v.monkeyid)as sex,"+
			"xueq,"+
			"remark ,(select title from normal n where n.id=v.normal_id)as title ," +
			"bv,stlv,srv,siv,filo from virus v,normal n where v.deleted!=1 and n.id=v.normal_id ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and v.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and v.cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by v.normal_id";
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
		List<?> listVir=query.list();
		
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:listVir){
			Object []objs=(Object[])ob;
			Map virusMap=new HashMap<String,Object>();
			virusMap.put("id", objs[0]);
			virusMap.put("roomid", objs[1]);
			virusMap.put("lhao", objs[2]==null?"":objs[2]);
			virusMap.put("monkeyid", objs[3]);
				virusMap.put("sex", objs[4]);
			
			virusMap.put("xueq", objs[5]==null?"":objs[5]);
			virusMap.put("remark", objs[6]==null?"":objs[6]);
			
			
				//下面增加检测项目,根据猴子编号查询该猴子的检测项目信息.
				/*String monkeyids=(String)objs[3];
				List<?> listItems=getVirusItems(monkeyids);
				for(int i=0;i<listItems.size();i++){
					Object[]obj=(Object[])listItems.get(i);
					if(obj[0]!=null&&!"".equals(obj[0])){
						virusMap.put("bv", obj[0]);
					}
					if(obj[1]!=null&&!"".equals(obj[1])){
						virusMap.put("stlv", obj[1]);
					}
					if(obj[2]!=null&&!"".equals(obj[2])){
						virusMap.put("srv", obj[2]);
					}
					if(obj[3]!=null&&!"".equals(obj[3])){
						virusMap.put("siv", obj[3]);
					}
					if(obj[4]!=null&&!"".equals(obj[4])){
						virusMap.put("filo", obj[4]);
					}
					
				}*/
			if (!"".equals(objs[5])&&objs[5]!=null) {
				virusMap.put("caiy", "√");
			}else{
				virusMap.put("caiy", "/");
			}
			virusMap.put("bv", objs[8]==null?"":objs[8]);
			virusMap.put("stlv", objs[9]==null?"":objs[9]);
			virusMap.put("srv", objs[10]==null?"":objs[10]);
			virusMap.put("siv", objs[11]==null?"":objs[11]);
			virusMap.put("filo", objs[12]==null?"":objs[12]);
			virusMap.put("other", "");
			listMap.add(virusMap);
		}
		Map map=new HashMap<String,Object>();
		map.put("rows", listMap);
		map.put("total", listVir.size());
		return map;
	}
	
	public List<?> getLastOneVirusInfo(String monkeyid){
		String sql="select bv,stlv,siv,srv,filo from  virus ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where monkeyid=:monkeyid";
		}
		sql=sql+" order by cdate desc limit 0,1";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listVirusInfo=	query.list();
		return listVirusInfo;
	}

	public List<Virus_Json> getVirusById(String monkeyid,String checkId) {
		String sql="select (select title from normal n where n.id=v.normal_id) as title," +
				"cdate,monkeyid,xueq,bv,stlv,srv,siv,filo from virus v where deleted!=1  ";
		if(monkeyid!=null&&!"".equals(monkeyid)){
			sql=sql+" and monkeyid=:id ";
		}
		if(checkId!=null&&!"".equals(checkId)){
			sql=sql+" and v.normal_id=:checkId ";
		}
		Query query=getSession().createSQLQuery(sql);
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("id", monkeyid);
		}
		if(checkId!=null&&!"".equals(checkId)){
			query.setParameter("checkId", checkId);
		}
		List<?> l=query.list();
		List<Virus_Json> ls=new ArrayList<Virus_Json>();
		for(Object ob:l){
			Object[]objs=(Object[])ob;
			Virus_Json v=new Virus_Json();
			v.setTitle((String)objs[0]);
			v.setCdate((Date)objs[1]);
			v.setMonkeyid((String)objs[2]);
			v.setXueq((String)objs[3]);
			v.setBv((String)objs[4]);
			v.setStlv((String)objs[5]);
			v.setSrv((String)objs[6]);
			v.setSiv((String)objs[7]);
			v.setFilo((String)objs[8]);
			ls.add(v);
		}
		return ls;
	}
}
