package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Approval;
@Service
public class ApprovalServiceImpl extends BaseLongDaoImpl<Approval> implements
		ApprovalService {

	public Map<String,Object> getAllApprovalInfo(String page,String rows) {
		Map<String,Object> map=new HashMap<String,Object>();
		String hql="from Approval where deleted=0";
		Query query=getSession().createQuery(hql);
		List<?> total=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<Approval> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		map.put("rows", list);
		map.put("total", total.size());
		return map;
	}

	public List<Map<String, Object>> getApprovalMap() {
		String sql="select id,phao from approval where deleted=0 ";
		Query query=getSession().createSQLQuery(sql);
		List<?> list=query.list();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Map<String,Object> map=new HashMap<String,Object>();
			Object[]objs=(Object[])ob;
			map.put("id", objs[0]);
			map.put("text", objs[1]);
			listmap.add(map);
		}
		return listmap;
	}

	

}
