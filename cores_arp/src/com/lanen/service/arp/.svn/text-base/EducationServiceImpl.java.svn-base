package com.lanen.service.arp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Education;
@Service
public class EducationServiceImpl extends BaseLongDaoImpl<Education> implements EducationService {
	//小写.
	public List<Map<String, Object>> getAllEduIdName() {
		String sql="SELECT id,NAME FROM education ";
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
	//小写.
	public String getNameById(Integer xueli) {
		String sql="SELECT NAME FROM education WHERE id=:id";
		List<?> listSql=getSession().createSQLQuery(sql).setParameter("id", xueli).list();
		String name=null;
		if(listSql!=null){
			for(Object obj:listSql){
				name=(String)obj;
			}
		}
		return name;
	}

	

}
