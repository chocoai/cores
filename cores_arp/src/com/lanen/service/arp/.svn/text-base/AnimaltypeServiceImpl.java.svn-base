package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Animaltype;
import com.lanen.model.Death;
@Service
public class AnimaltypeServiceImpl extends BaseLongDaoImpl<Animaltype> implements AnimaltypeService{

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

	public Map<String,Object> getAllAnimalType(String rows,String page){
		String hql=" From Animaltype where del!=1";
		Query query=getSession().createQuery(hql);
		List<Animaltype> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<Animaltype> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		return map;
	}

	public List<String> getAnimalTypes() {
		String sql="select name from animaltype";
		Query query=getSession().createSQLQuery(sql);
		List<String> name=query.list();
		return name;
	}
	
}
