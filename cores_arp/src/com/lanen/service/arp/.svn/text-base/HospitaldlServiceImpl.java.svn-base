package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Hospitaldl;
import com.lanen.model.Hospitaldl_Json;
//常规治疗
@Service
public class HospitaldlServiceImpl extends BaseLongDaoImpl<Hospitaldl> implements
		HospitaldlService {

	public Map<String, Object> getHospitaldl(String page, String rows,
			String monkeyid, String zzmc,String treatveterinarian,String start,String end) {
		String sql="select DISTINCT id,monkeyid,zlrq,remark," +
				"(select e.name from employee as e where e.id=h.treatveterinarian) as treatveterinarian," +
				" zzmc,cf from hospitaldl as h where deleted=0";
		if(monkeyid!=null && !("").equals(monkeyid)){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(zzmc!=null && !("").equals(zzmc)){
			sql=sql+" and zzmc=:zzmc";
		}
		if(treatveterinarian!=null && !("").equals(treatveterinarian)){
			sql=sql+" and treatveterinarian=:treatveterinarian";
		}
		if((null != start && !("").equals(start))&&(null != end && !("").equals(end))){
			sql = sql +"  and zlrq between  :start and  :end ";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=monkeyid && !("").equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(zzmc != null && !("").equals(zzmc) ){
			query.setParameter("zzmc", zzmc);
		}
		if(null!=treatveterinarian && !("").equals(treatveterinarian)){
			query.setParameter("treatveterinarian", treatveterinarian);
		}
		if(start != null && !start.equals("") && end != null && !end.equals("") ){
			query.setParameter("start", start);
			query.setParameter("end", end);
		}
		List<?> lists=query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Hospitaldl_Json> listt=new ArrayList<Hospitaldl_Json>();

		if(list!=null){
			for(Object obj:list){
				Hospitaldl_Json h=new Hospitaldl_Json();
				Object[] objs=(Object[])obj;
				h.setId((Long.valueOf(objs[0]+"")));
				h.setMonkeyid((String)objs[1]);
				h.setZlrq((Date)objs[2]);
				h.setRemark((String)objs[3]);
				h.setTreatveterinarian((String)objs[4]);
				h.setZzmc((String) objs[5]);
				h.setCf((String) objs[6]);
				listt.add(h);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

}
