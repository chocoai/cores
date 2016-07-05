package com.lanen.service.arp;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Death;
@Service
public class DeathServiceImpl extends BaseLongDaoImpl<Death> implements
		DeathService {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getListByConditions(String page, String rows,
			String monkeyid, Date dissectdate, String remarks) {
		String hql="from Death where id!=0";
		if(monkeyid!=null&&!"".equals(monkeyid)){
			hql=hql+" and monkeyid=:monkeyid";
		}
		if(dissectdate!=null){
			hql=hql+" and dissectdate=:dissectdate";
		}
		if(remarks!=null&&!"".equals(remarks)){
			hql=hql+" and remarks like :remarks or bl_remarks like :remarks or sc_remarks like :remarks or others like :remarks";
		}
		hql= hql+" order by deathdate desc";
		Query query=getSession().createQuery(hql);
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(dissectdate!=null){
			query.setParameter("dissectdate", dissectdate);
		}
		if(remarks!=null&&!"".equals(remarks)){
			query.setParameter("remarks", "%"+remarks+"%");
		}
		List<Death> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<Death> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		return map;
	}

	public Death getDeathById(String monkeyid) {
		String hql=" from Death where monkeyid=?";
		List<?> l=getSession().createQuery(hql).setParameter(0, monkeyid).list();
		if(l.size()>0){
			return (Death)l.get(0);
		}
		return null;
	}

	

}
