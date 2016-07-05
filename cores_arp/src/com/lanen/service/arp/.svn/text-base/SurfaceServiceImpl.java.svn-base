package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Surface;
@Service
public class SurfaceServiceImpl extends BaseLongDaoImpl<Surface> implements SurfaceService{

	public Map getSurfaceListByMonkeyId(String id) {
		String sql="SELECT id,createtime,ptype,veterinarian,protector,recorder,remark FROM surface where deleted != 1 ";
		if(!("").equals(id)&&id!=null){
			sql=sql+" and monkeyid=:id";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!("").equals(id)&&id!=null){
			query.setParameter("id", id);
		}
		List<?> listSql=query.list();
	
		Map map=new HashMap();
		if(listSql!=null){
			for(Object obj:listSql){
				StringBuffer tmp = new StringBuffer();
				Object[] objs=(Object[])obj;
				tmp.append("<tr><td>检疫时间：</td><td>"+objs[1]+"</td><td>检疫类型：</td><td>"+"常规检疫"+"</td></tr>"+
						"<tr><td>检测兽医：</td><td>"+objs[3]+"</td><td>保定人员：</td><td>"+objs[4]+"</td></tr>"+
						"<tr><td>记录人员：</td><td>"+objs[5]+"</td></tr>"+
						"<tr><td>体表状况：</td><td>"+objs[6]+"</td></tr>");
				map.put("table", tmp);
			}
		}
		return map;
	}

	public List<Map<String, Object>> getAllAnimalTypeIdName() {
		String sql="SELECT id,name FROM animaltype where del != 1 ";
		List<?> listSql=getSession().createSQLQuery(sql).list();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, Object> map=new HashMap<String, Object>();
				Object[] objs=(Object[])obj;
				map.put("id", (Integer)objs[0]);
				map.put("text",(String)objs[1] );
				list.add(map);
			}
		}
		return list;
	}
	
	public Map<String,Object> loadListByMonkeyId(String rows,String page,String type,String id){
		String sql="select id,cdate,ptype," +
				" (select e.name from employee e where s.veterinarian=e.id) as veterinarian," +
				" (select e.name from employee e where s.protector=e.id) as protector," +
				" (select e.name from employee e where s.recorder=e.id) as recorder," +
				"  remark from surface s where ptype=:type and monkeyid=:monkeyid";
		
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null&&!"".equals(id)&&id!=null){
			query.setParameter("type", type);
			query.setParameter("monkeyid", id);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Surface> listt=new ArrayList<Surface>();

		if(list!=null){
			for(Object obj:list){
				Surface s=new Surface();
				Object[] objs=(Object[])obj;
				s.setId((Integer)objs[0]);
				s.setCdate((Date)objs[1]);
				s.setPtype((String)objs[2]);
				s.setVeterinarian((String)objs[3]);
				s.setProtector((String)objs[4]);
				s.setRecorder((String)objs[5]);
				s.setRemark((String)objs[6]);
				
				listt.add(s);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;	
	}

	public List<?> getSurfaceBymonkeyidAndCheckdate(String monkeyid,
			Date checkdate) {
		String hql=" FROM Surface where monkeyid=? and cdate=?";
		List<Surface> list = getSession().createQuery(hql).setParameter(0, monkeyid).setParameter(1, checkdate).list();
		
		return list;
	}

	public List<?> getSurfaceByNormalId(String monkeyid, Integer normalid) {
		String hql="FROM Surface where monkeyid=? and normal_id=?";
		List<Surface> list=getSession().createQuery(hql).setParameter(0, monkeyid).setParameter(1, normalid).list();
		return list;
	}

}
