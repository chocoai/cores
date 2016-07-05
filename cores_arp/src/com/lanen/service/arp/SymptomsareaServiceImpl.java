package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Symptomsarea;
@Service
public class SymptomsareaServiceImpl extends BaseLongDaoImpl<Symptomsarea> implements
		SymptomsareaService {

	public Map<String, Object> getSymptomsarea(String page, String rows,
			String name, String sys) {
		String sql="select id,name,remark from symptomsarea where deleted=0";
		if(name!=null && !("").equals(name)){
			sql=sql+" and name=:name";
		}
		if(sys!=null && !("").equals(sys)){
			sql=sql+" and symptomssite=:sys";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=name && !("").equals(name)){
			query.setParameter("name", name);
		}
		if(sys != null && !("").equals(sys) ){
			query.setParameter("symptomssite", sys);
		}
		List<?> lists=query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Symptomsarea> listt=new ArrayList<Symptomsarea>();

		if(list!=null){
			for(Object obj:list){
				Symptomsarea s=new Symptomsarea();
				Object[] objs=(Object[])obj;
				s.setId((Long.valueOf(objs[0]+"")));
				s.setName((String)objs[1]);
				s.setRemark((String)objs[2]);
				listt.add(s);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public List<Map<String, String>> getAllSymptomsareaMap() {
		String sql="SELECT ID,NAME FROM symptomsarea WHERE deleted!=-1 ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}
}
