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
import com.lanen.model.Bacteria;
import com.lanen.model.Bacteria_Json;
import com.lanen.util.Constant;
@Service
public class BacteriaServiceImpl extends BaseLongDaoImpl<Bacteria> implements BacteriaService{
	
	public Map<String,Object> loadListByMonkeyIdAndCdate(String page,String rows,String type){
		String sql="select monkeyid," +
		"(select e.name from employee e where b.veterinarian=e.id)as veterinarian," +
		"(select e.name from employee e where b.protector=e.id)as protector," +
		"(select e.name from employee e where b.recorder=e.id)as recorder,cdate,remark " +
		" from bacteria b group by monkeyid,cdate";
		
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null){
			query.setParameter("type", type);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Bacteria> listt=new ArrayList<Bacteria>();

		if(list!=null){
			for(Object obj:list){
				Bacteria p=new Bacteria();
				Object[] objs=(Object[])obj;
				p.setMonkeyid((String)objs[0]);
				p.setVeterinarian((String)objs[1]);
				p.setProtector((String)objs[2]);
				p.setRecorder((String)objs[3]);
				p.setCdate((Date)objs[4]);
				p.setRemark((String)objs[5]);
				
				listt.add(p);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
		
	}

	public Map<String, Object> loadListItem(String monkeyid,String cdate) {
		
		String sql="select " +
				"(select name from quarantine q where b.q_id=q.id) as q_name," +
				"resoult," +
				"(select name from quarantine q where q.id=b.qconfig_id) as qconfig_name," +
				"drugs_name,drugs_count from bacteria b where deleted!=1" ;
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
		
		List<Bacteria_Json> listt=new ArrayList<Bacteria_Json>();

		if(lists!=null){
			for(Object obj:lists){
				Bacteria_Json b=new Bacteria_Json();
				Object[] objs=(Object[])obj;
				
				b.setQ_name((String)objs[0]);
				b.setResoult((Byte)objs[1]);
				b.setQconfig_name((String)objs[2]);
				b.setDrugs_name((String)objs[3]);
				b.setDrugs_count((String)objs[4]);
				listt.add(b);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public Map<String, Object> getBacteria(String rows, String page,String monkeyid,Date cdate) {
		String sql="select id,"+
		"(select areaname from area where id=(select room from individual i where i.monkeyid=b.monkeyid))as roomid,"+
		"(select lhao from individual i where i.monkeyid=b.monkeyid)as lhao," +
		"monkeyid,"+
		"(select sex from individual i where i.monkeyid=b.monkeyid)as sex,"+
		"(select currentweight from individual i where i.monkeyid=b.monkeyid)as weight,"+
		"remark ,(select title from normal n where n.id=b.normal_id) as title,shig,salm,yers ,ypid " +
		"from bacteria b where b.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and b.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and b.cdate=:cdate";
		}
		//sql=sql+" group by b.normal_id";
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
		Map bacteriaMap=new HashMap<String,Object>();
		bacteriaMap.put("id", objs[0]);
		bacteriaMap.put("roomid", objs[1]);
		bacteriaMap.put("lhao", objs[2]);
		bacteriaMap.put("monkeyid", objs[3]);
		bacteriaMap.put("sex", objs[4]);
		bacteriaMap.put("weight", objs[5]);
		bacteriaMap.put("remark", objs[6]);
		
		//下面增加检测项目,在常规建议中需增加检测项目结果记录，否则无值.
		String monkeyids=(String)objs[3];
		List<?> listItems=getBacteriaItems(monkeyids);
		for(int i=0;i<listItems.size();i++){
			Object[]obj=(Object[])listItems.get(i);
			if(obj[0]!=null&&!"".equals(obj[0])){
				//bacteriaMap.put("shig", obj[0]);
			}
			if(obj[1]!=null&&!"".equals(obj[1])){
				//bacteriaMap.put("salm", obj[1]);
			}
			if(obj[2]!=null&&!"".equals(obj[2])){
				//bacteriaMap.put("yers", obj[2]);
			}
		}
		bacteriaMap.put("checkId", objs[7]);//检疫编号.
		bacteriaMap.put("shig", objs[8]);
		bacteriaMap.put("salm", objs[9]);
		bacteriaMap.put("yers", objs[10]);
		
		if (!"".equals(objs[11])&&objs[11]!=null) {
			bacteriaMap.put("gangs", objs[11]);//肛拭号,后续优化
			bacteriaMap.put("caiy", "√");//已采样.
		}else{
			bacteriaMap.put("caiy", "/");//未采样.
		}
		listMap.add(bacteriaMap);
	}
	Map map=new HashMap<String,Object>();
	map.put("rows", listMap);
	map.put("total", listVir.size());
	return map;
	}
	/**
	 * 由动物编号获取对应的病毒检测项目,每个q_id对应唯一项目,
	 * 且在常规检疫添加纪录时每个q_id需对应更新每个项目。如q_id=26对应bv,q_id=27对应stlv.
	 * 沙门氏菌:shig
	 * 致贺氏菌：salm
	 * 耶尔森氏菌:yers
	 */
	public List<?> getBacteriaItems(String monkeyid){
		String sql="select shig,salm,yers from bacteria where deleted!=1";
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
	
	public Map<String, Object> getBacteria(String monkeyid,String cdate,String checkId) {
		String sql="select b.id,"+
		"(select areaname from area where id=(select room from individual i where i.monkeyid=b.monkeyid))as roomid,"+
		"(select lhao from individual i where i.monkeyid=b.monkeyid)as lhao," +
		"monkeyid,"+
		"(select sex from individual i where i.monkeyid=b.monkeyid)as sex,"+
		"(select currentweight from individual i where i.monkeyid=b.monkeyid)as weight,"+
		"remark ,(select title from normal n where n.id=b.normal_id) as title,shig,salm,yers ,ypid " +
		" from bacteria b,normal n where b.deleted!=1 and n.id=b.normal_id ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and b.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and b.cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by b.normal_id";
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
	List<?> listbacteria=query.list();
	
	List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
	for(Object ob:listbacteria){
		Object []objs=(Object[])ob;
		Map bacteriaMap=new HashMap<String,Object>();
		bacteriaMap.put("id", objs[0]);
		bacteriaMap.put("roomid", objs[1]);
		bacteriaMap.put("lhao", objs[2]==null?"":objs[2]);
		bacteriaMap.put("monkeyid", objs[3]);
		bacteriaMap.put("sex", objs[4]);
		bacteriaMap.put("weight", objs[5]==null?"":objs[5]);
		bacteriaMap.put("remark", objs[6]==null?"":objs[6]);
		
		//下面增加检测项目,在常规建议中需增加检测项目结果记录，否则无值.
		/*String monkeyids=(String)objs[3];
		List<?> listItems=getBacteriaItems(monkeyids);
		for(int i=0;i<listItems.size();i++){
			Object[]obj=(Object[])listItems.get(i);
			if(obj[0]!=null&&!"".equals(obj[0])){
				bacteriaMap.put("shig", obj[0]);
			}
			if(obj[1]!=null&&!"".equals(obj[1])){
				bacteriaMap.put("salm", obj[1]);
			}
			if(obj[2]!=null&&!"".equals(obj[2])){
				bacteriaMap.put("yers", obj[2]);
			}
		}*/
		if (!"".equals(objs[11])&&objs[11]!=null) {
			bacteriaMap.put("gangs", objs[11]);//肛拭号,后续优化
			bacteriaMap.put("caiy", "√");
			bacteriaMap.put("shig", objs[8]==null?"":objs[8]);
			bacteriaMap.put("salm", objs[9]==null?"":objs[9]);
			bacteriaMap.put("yers", objs[10]==null?"":objs[10]);
		}else{
			bacteriaMap.put("gangs", "");//肛拭号,后续优化
			bacteriaMap.put("caiy", "/");
			bacteriaMap.put("shig", objs[8]!=null?objs[8]:"");
			bacteriaMap.put("salm", objs[9]!=null?objs[9]:"");
			bacteriaMap.put("yers", objs[10]!=null?objs[10]:"");
		}
		listMap.add(bacteriaMap);
	}
	Map map=new HashMap<String,Object>();
	map.put("rows", listMap);
	map.put("total", listbacteria.size());
	return map;
	}
	public List<Bacteria> getLast7BacteriaRecord(String monkeyid){
		String sql="select cdate,shig,salm,yers from bacteria";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where monkeyid=:monkeyid";
		}
		sql=sql+" order by cdate desc limit 0,7 ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> list=query.list();
		List<Bacteria> listBacteria=new ArrayList<Bacteria>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Bacteria b=new Bacteria();
			b.setCdate((Date)objs[0]);
			b.setShig((String)objs[1]);
			b.setSalm((String)objs[2]);
			b.setYers((String)objs[3]);
			listBacteria.add(b);
		}
		return listBacteria;
	}
}
