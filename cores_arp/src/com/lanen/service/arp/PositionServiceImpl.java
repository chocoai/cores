package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Position;
@Service
public class PositionServiceImpl extends BaseLongDaoImpl<Position> implements PositionService {
	//小写.
	public String getNameById(Integer id) {
		String sql="SELECT NAME FROM position WHERE id=:id";
		List<?> listSql=getSession().createSQLQuery(sql).setParameter("id", id).list();
		String name=null;
		if(listSql!=null){
			for(Object obj:listSql){
				name=(String)obj;
			}
		}
		return name;
	}
	//小写.
	public List<Map<String, Object>> getAllPosIdName(Integer depId) {
		String sql="SELECT id,NAME FROM position ";
		List<?> listSql=null;
		if(depId!=null&&!"".equals(depId)){
			sql=sql+"WHERE department_id=:depId";
			listSql=getSession().createSQLQuery(sql).setParameter("depId", depId).list();
		}else{
			listSql=getSession().createSQLQuery(sql).list();
		}
		
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

	@SuppressWarnings("unchecked")
	public List<Position> getALLPosOrderByDid() {
		String hql="from Position order by department_id";
		List<Position> list=getSession().createQuery(hql).list();
		return list;
	}

	public boolean isExistName(String name) {
		if(null != name){
			List<?> list = getSession().createQuery(" From Position where name = :name ")
						.setParameter("name", name)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	

}
