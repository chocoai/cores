package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Bacteria;
import com.lanen.model.Parasite;
import com.lanen.model.Parasite_Json;
import com.lanen.util.Constant;
@Service
public class ParasiteServiceImpl extends BaseLongDaoImpl<Parasite> implements ParasiteService{
	
	public Map<String,Object> loadListByMonkeyIdAndCdate(String page,String rows,String type){
		String sql="select monkeyid,getybdate," +
		"(select e.name from employee e where p.veterinarian=e.id)as veterinarian," +
		"(select e.name from employee e where p.protector=e.id)as protector," +
		"(select e.name from employee e where p.recorder=e.id)as recorder,cdate,remark " +
		" from parasite p group by monkeyid,cdate";
		
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null){
			query.setParameter("type", type);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Parasite> listt=new ArrayList<Parasite>();

		if(list!=null){
			for(Object obj:list){
				Parasite p=new Parasite();
				Object[] objs=(Object[])obj;
				p.setMonkeyid((String)objs[0]);
				p.setGetybdate((Date)objs[1]);
				p.setVeterinarian((String)objs[2]);
				p.setProtector((String)objs[3]);
				p.setRecorder((String)objs[4]);
				p.setCdate((Date)objs[5]);
				p.setRemark((String)objs[6]);
				
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
				"(select name from quarantine q where p.q_id=q.id) as q_name," +
				"resoult," +
				"(select name from quarantine q where q.id=p.qconfig_id) as qconfig_name," +
				"drugs_name,drugs_count from parasite p where deleted!=1" ;
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
		
		List<Parasite_Json> listt=new ArrayList<Parasite_Json>();

		if(lists!=null){
			for(Object obj:lists){
				Parasite_Json p=new Parasite_Json();
				Object[] objs=(Object[])obj;
				
				p.setQ_name((String)objs[0]);
				p.setResoult((Byte)objs[1]);
				p.setQconfig_name((String)objs[2]);
				
				listt.add(p);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, String>> getYP(String mark) {
		String sql="select id,name from publiccode where mark=:mark";
		List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("mark", mark);
		Map<String,String> map=null;
		List<?> list=query.list();
		for(Object o:list){
			Object[]objs=(Object[])o;
			map=new HashMap<String,String>();
			map.put("id", objs[0]+"");
			map.put("text", (String)objs[1]);
			listMap.add(map);
		}
		return listMap;
	}

	//检疫记录表根据monkeyid存在是update,不存在时才insert.
	public Map<String, Object> getInParasite(String rows, String page,String monkeyid,Date cdate) {
		//List<?> list=getInParasiteId(Constant.IN_PARASITE_NAME);
		//String inParasiteId=null;
		//for(int i=0;i<list.size();i++){
		//	inParasiteId=(String) list.get(i);
		//}
		String sql="select id,"+
					"(select areaname from area where id=(select room from individual i where i.monkeyid=p.monkeyid))as roomid,"+
					"(select lhao from individual i where i.monkeyid=p.monkeyid)as lhao,"+
					"monkeyid,"+
					"(select sex from individual i where i.monkeyid=p.monkeyid)as sex,"+
					"remark ," +
					"q_id,amb,gxc,bmc,twjsc,bhao,(select title from normal n where n.id=p.normal_id )as title " +
					"from parasite p where p.deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		//sql=sql+" group by normal_id";
		//sql=sql+" and amb is not null or gxc is not null or bmc is not null  ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> listParasite=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> lis = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:lis){
			Object []objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]);
			map.put("monkeyid", objs[3]);
			String monkeyids=(String)objs[3];

				map.put("sex", objs[4]);
			
			map.put("remark", objs[5]);
			
			//String q_id=objs[6]+"";//group by 查q_id 只能取一个值，而漏掉其他的
			//下面通过monkeyid查q_id
			List<Map<String,Integer>> listQId=getQIdByMonkeyid(monkeyid);

			for (Object qid:listQId) {
				Map ids=(Map) qid;
				String q_id=ids.get("q_id")+"";
				if (!"".equals(q_id)&&q_id!=null) {
					String name = getInParasiteName(q_id).get(0);
					//下面开始判断体内--体外寄生虫
					if ((Constant.IN_PARASITE_NAME_AMIE_IN_SOLUTION)
							.equals(name)) {
						//map.put("nam", "√");//溶组织内阿米
					}
					if ((Constant.IN_PARASITE_NAME_WORM).equals(name)) {
						//map.put("rc", "√");//蠕虫
					}
					if ((Constant.IN_PARASITE_NAME_FLAGELLATES).equals(name)) {
						//map.put("bmc", "√");//鞭毛虫
					}
					if ((Constant.OUT_PARASITE_NAME).equals(name)) {
						//map.put("tiw", "√");//体外寄生虫
					}
				}
			}
				map.put("nam", objs[7]);
			
				map.put("rc", objs[8]);
			
				map.put("bmc", objs[9]);
			
				map.put("twjsc", objs[10]);
			
			map.put("yangb", objs[11]);//样本号
			if (!"".equals(objs[11])&&objs[11]!=null) {
				map.put("caiy", "√");
			}else{
				map.put("caiy", "/");
			}
			map.put("checkId", objs[12]);//检疫编号.
			listMap.add(map);
		}
		Map m=new HashMap<String,Object>();
		m.put("rows", listMap);
		m.put("total", listParasite.size());
		return m;
	}

	public Map<String, Object> getInParasite(String monkeyid,String cdate,String checkId) {
		
		String sql="select p.id,"+
					"(select areaname from area where id=(select room from individual i where i.monkeyid=p.monkeyid))as roomid,"+
					"(select lhao from individual i where i.monkeyid=p.monkeyid)as lhao,"+
					"monkeyid,"+
					"(select sex from individual i where i.monkeyid=p.monkeyid)as sex,"+
					"remark ," +
					"q_id ,amb,gxc,bmc,twjsc,bhao " +
					"from parasite p,normal n where p.deleted!=1 and n.id=p.normal_id";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+"  group by normal_id";
		//sql=sql+" and twjsc is not null";
		//sql=sql+" and amb is not null or gxc is not null or bmc is not null  ";
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
		List<?> listParasite=query.list();
		
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:listParasite){
			Object []objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]==null?"":objs[2]);
			map.put("monkeyid", objs[3]);
			String monkeyids=(String)objs[3];
				map.put("sex", objs[4]);
			
			if (!"".equals(objs[5])&&objs[5]!=null) {
				map.put("remark", objs[5]);
			}else{
				map.put("remark", "");
			}
			//String q_id=objs[6]+"";//group by 查q_id 只能取一个值，而漏掉其他的
			//下面通过monkeyid查q_id
			List<Map<String,Integer>> listQId=getQIdByMonkeyid(monkeyid);

			/*for (Object qid:listQId) {
				Map ids=(Map) qid;
				String q_id=ids.get("q_id")+"";
				if (!"".equals(q_id)&&q_id!=null) {
					String name = getInParasiteName(q_id).get(0);
					//下面开始判断体内--体外寄生虫
					if ((Constant.IN_PARASITE_NAME_AMIE_IN_SOLUTION)
							.equals(name)) {
						map.put("nam", "√");//溶组织内阿米
					}
					if ((Constant.IN_PARASITE_NAME_WORM).equals(name)) {
						map.put("rc", "√");//蠕虫
					}
					if ((Constant.IN_PARASITE_NAME_FLAGELLATES).equals(name)) {
						map.put("bmc", "√");//鞭毛虫
					}
					if ((Constant.OUT_PARASITE_NAME).equals(name)) {
						map.put("tiw", "√");//体外寄生虫
					}
				}
			}*/
				map.put("nam", objs[7]==null?"":objs[7]);
			
				map.put("rc", objs[8]==null?"":objs[8]);
			
				map.put("bmc", objs[9]==null?"":objs[9]);
			
				map.put("twjsc", objs[10]==null?"":objs[10]);
			
			
			if (!"".equals(objs[11])&&objs[11]!=null) {
				map.put("caiy", "√");
				map.put("yangb", objs[11]);//样本号
			}else{
				map.put("caiy", "/");
				map.put("yangb", "");
			}
			map.put("other", "");
			listMap.add(map);
		}
		Map m=new HashMap<String,Object>();
		m.put("rows", listMap);
		m.put("total", listParasite.size());
		return m;
	}

	public Map<String, Object> getOutParasite(String rows, String page,String monkeyid,Date cdate) {
		String sql = "select id,"
				+ "(select areaname from area where id=(select room from individual i where i.monkeyid=p.monkeyid))as roomid,"
				+ "(select lhao from individual i where i.monkeyid=p.monkeyid)as lhao,"
				+ "monkeyid,"
				+ "(select sex from individual i where i.monkeyid=p.monkeyid)as sex,"
				+ "remark ," + "q_id,amb,gxc,bmc,twjsc,bhao ,(select title from normal n where n.id=p.normal_id) as title " 
				+ "from parasite p where p.deleted!=1";
		if (!"".equals(monkeyid) && monkeyid != null) {
			sql = sql + " and monkeyid=:monkeyid";
		}
		if (!"".equals(cdate) && cdate != null) {
			sql = sql + " and cdate=:cdate";
		}
		// sql=sql+" group by normal_id";
		sql = sql+ " and twjsc is not null  ";
		Query query = getSession().createSQLQuery(sql);
		if (!"".equals(monkeyid) && monkeyid != null) {
			query.setParameter("monkeyid", monkeyid);
		}
		if (!"".equals(cdate) && cdate != null) {
			query.setParameter("cdate", cdate);
		}
		List<?> listParasite = query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> lis = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Object ob : lis) {
			Object[] objs = (Object[]) ob;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]);
			map.put("monkeyid", objs[3]);
			String monkeyids = (String) objs[3];
			
				map.put("sex", objs[4]);

			map.put("remark", objs[5]);

			// String q_id=objs[6]+"";//group by 查q_id 只能取一个值，而漏掉其他的
			// 下面通过monkeyid查q_id
			List<Map<String, Integer>> listQId = getQIdByMonkeyid(monkeyid);

			for (Object qid : listQId) {
				Map ids = (Map) qid;
				String q_id = ids.get("q_id") + "";
				if (!"".equals(q_id) && q_id != null) {
					String name = getInParasiteName(q_id).get(0);
					// 下面开始判断体内--体外寄生虫
					if ((Constant.IN_PARASITE_NAME_AMIE_IN_SOLUTION)
							.equals(name)) {
						// map.put("nam", "√");//溶组织内阿米
					}
					if ((Constant.IN_PARASITE_NAME_WORM).equals(name)) {
						// map.put("rc", "√");//蠕虫
					}
					if ((Constant.IN_PARASITE_NAME_FLAGELLATES).equals(name)) {
						// map.put("bmc", "√");//鞭毛虫
					}
					if ((Constant.OUT_PARASITE_NAME).equals(name)) {
						// map.put("tiw", "√");//体外寄生虫
					}
				}
			}
			if (!"".equals(objs[7]) && objs[7] != null) {
				map.put("nam", objs[7]);
			}
			if (!"".equals(objs[8]) && objs[8] != null) {
				map.put("rc", objs[8]);
			}
			if (!"".equals(objs[9]) && objs[9] != null) {
				map.put("bmc", objs[9]);
			}
			if (!"".equals(objs[10]) && objs[10] != null) {
				map.put("twjsc", objs[10]);
			}
			map.put("other", "--");// 其他
			map.put("yangb", objs[11]);// 样本号
			if (!"".equals(objs[11]) && objs[11] != null) {
				map.put("caiy", "√");
			}
			map.put("checkId", objs[12]);//检疫编号.
			listMap.add(map);
		}
		Map m = new HashMap<String, Object>();
		m.put("rows", listMap);
		m.put("total", listParasite.size());
		return m;
	}
public Map<String, Object> getOutParasite(String monkeyid,String cdate) {
		
		String sql="select id,"+
					"(select areaname from area where id=(select room from individual i where i.monkeyid=p.monkeyid))as roomid,"+
					"(select lhao from individual i where i.monkeyid=p.monkeyid)as lhao,"+
					"monkeyid,"+
					"(select sex from individual i where i.monkeyid=p.monkeyid)as sex,"+
					"remark ," +
					"q_id ,amb,gxc,bmc,twjsc,bhao " +
					"from parasite p where p.deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		//sql=sql+"  group by normal_id";
		//sql=sql+" and twjsc is not null";
		sql=sql+" and twjsc is not null  ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> listParasite=query.list();
		
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:listParasite){
			Object []objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]);
			map.put("monkeyid", objs[3]);
			String monkeyids=(String)objs[3];
			
				map.put("sex", objs[4]);
			
			if (!"".equals(objs[5])&&objs[5]!=null) {
				map.put("remark", objs[5]);
			}else{
				map.put("remark", "");
			}
			//String q_id=objs[6]+"";//group by 查q_id 只能取一个值，而漏掉其他的
			//下面通过monkeyid查q_id
			List<Map<String,Integer>> listQId=getQIdByMonkeyid(monkeyid);

			/*for (Object qid:listQId) {
				Map ids=(Map) qid;
				String q_id=ids.get("q_id")+"";
				if (!"".equals(q_id)&&q_id!=null) {
					String name = getInParasiteName(q_id).get(0);
					//下面开始判断体内--体外寄生虫
					if ((Constant.IN_PARASITE_NAME_AMIE_IN_SOLUTION)
							.equals(name)) {
						map.put("nam", "√");//溶组织内阿米
					}
					if ((Constant.IN_PARASITE_NAME_WORM).equals(name)) {
						map.put("rc", "√");//蠕虫
					}
					if ((Constant.IN_PARASITE_NAME_FLAGELLATES).equals(name)) {
						map.put("bmc", "√");//鞭毛虫
					}
					if ((Constant.OUT_PARASITE_NAME).equals(name)) {
						map.put("tiw", "√");//体外寄生虫
					}
				}
			}*/
			if(!"".equals(objs[7])&&objs[7]!=null){
				map.put("nam", objs[7]);
			}
			if(!"".equals(objs[8])&&objs[8]!=null){
				map.put("rc", objs[8]);
			}
			if(!"".equals(objs[9])&&objs[9]!=null){
				map.put("bmc", objs[9]);
			}
			if(!"".equals(objs[10])&&objs[10]!=null){
				map.put("twjsc", objs[10]);
			}else{
				map.put("twjsc", "");
			}
			map.put("other", "--");//其他
			
			if (!"".equals(objs[11])&&objs[11]!=null) {
				map.put("yangb", objs[11]);//样本号
				map.put("caiy", "√");
			}else{
				map.put("yangb", "");//样本号
				map.put("caiy", "");
			}
			listMap.add(map);
		}
		Map m=new HashMap<String,Object>();
		m.put("rows", listMap);
		m.put("total", listParasite.size());
		return m;
	}
	//根据monkeyid查qid
	public List<Map<String,Integer>> getQIdByMonkeyid(String monkeyid){
		String sql="select q_id from parasite where monkeyid=:monkeyid and deleted!=1";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("monkeyid", monkeyid);
		List<?> listId=query.list();
		List<Map<String,Integer>> listMap=new ArrayList<Map<String,Integer>>();
		for(Object ob:listId){
			Integer objs=(Integer)ob;
			Map map=new HashMap<String,Integer>();
			map.put("q_id", objs);
			listMap.add(map);
		}
		return listMap;
	}
	//根据寄生虫名称查Id
	public List<?> getInParasiteId(String name){
		String sql="select id from quarantine where name=:name and deleted!=1";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("name", name);
		List<?> listId=query.list();
		return listId;
	}
	//根据寄生虫id查名称
	public List<String> getInParasiteName(String id){
		String sql="select name from quarantine where id=:id and deleted!=1";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("id", id);
		List<String> listId=query.list();
		return listId;
	}

	public Parasite getParasite(String monkeyid, String normalid) {
		String sql="from Parasite where deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid ";
		}
		if(!"".equals(normalid)&&normalid!=null){
			sql=sql+" and normal_id=:normalid" ;
		}
		Query query=getSession().createQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(normalid)&&normalid!=null){
			query.setParameter("normalid", normalid);
		}
		return (Parasite)query.list().get(0);
	}
	public List<Parasite> getLast6ParasiteRecord(String monkeyid){
		String sql="select cdate,twjsc,amb,gxc,bmc from parasite";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where monkeyid=:monkeyid";
		}
		sql=sql+" order by cdate desc limit 0,6 ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listP=query.list();
		List<Parasite> list=new ArrayList<Parasite>();
		for(Object ob:listP){
			Object[]objs=(Object[])ob;
			Parasite p=new Parasite();
			p.setCdate((Date)objs[0]);
			p.setTwjsc((String)objs[1]);
			p.setAmb((String)objs[2]);
			p.setGxc((String)objs[3]);
			p.setBmc((String)objs[4]);
			list.add(p);
		}
		return list;
	}
}
