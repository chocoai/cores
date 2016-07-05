package com.lanen.service.arp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Yzc;

@Service
public class YzcServiceImpl extends BaseLongDaoImpl<Yzc> implements
		YzcService {

	public Map<String, Object> getListByConditions(String page, String rows,
			String name) {
		String sql="From Yzc where deleted!=1";
		
		if(!"".equals(name)&&name!=null){
			sql=sql+" and yzcmane=:name";
		}
		Query query=getSession().createQuery(sql);
		
		if(!"".equals(name)&&name!=null){
			query.setParameter("name", name);
		}
		List<Yzc> listtotal = query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<Yzc> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		return map;
	}

}
