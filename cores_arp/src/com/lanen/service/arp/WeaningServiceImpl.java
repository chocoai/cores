package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Individual;
import com.lanen.model.Leavebreast;
import com.lanen.model.Leavebreast_Json;
@Service
public class WeaningServiceImpl extends BaseLongDaoImpl<Leavebreast> implements
		WeaningService {
	//小写.
	public Map<String, Object> getChildMonkey(String page,String rows,String monkeyid,String startdate,String enddate) {
		String sql="select id,monkeyid,leavebreastdate,motherid," +
				"(SELECT e.name FROM  employee AS e WHERE e.id=l.keeper) as keeper," +
				"(SELECT e.name FROM  employee AS e WHERE e.id=l.operater) as operater, " +
				"remark," +
				"(select name from employee e where e.id=l.recorder)as recorder," +
				"leavebreastweight " +
				"FROM leavebreast as l WHERE  deleted !=1";
		if(null!=monkeyid && !("").equals(monkeyid)){
			sql = sql +" and monkeyid = :monkeyid ";
		}
		if((null != startdate && !("").equals(startdate))&&(null != enddate && !("").equals(enddate))){
			sql = sql +"  and leavebreastdate between  :startdate and  :enddate ";
		}else{
			sql=sql+" order by lastmodifytime DESC";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=monkeyid && !("").equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(startdate != null && !("").equals(startdate) && enddate != null && !("").equals(enddate) ){
			query.setParameter("startdate", startdate);
			query.setParameter("enddate", enddate);
		}
		List<?> listSql=query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Leavebreast_Json> lists=new ArrayList<Leavebreast_Json>();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if(list!=null){
			for(Object obj:list){
				Leavebreast_Json json=new Leavebreast_Json();
				Object[] objs=(Object[])obj;
				json.setId((Long.valueOf(objs[0]+"")));
				json.setMonkeyid((String) objs[1]);
				
				//json.setWeaningdate((Timestamp.valueOf(format.format(objs[1]))));
				json.setLeavebreastdate(((String) objs[2]));
				json.setMotherid((String) objs[3]);
				json.setKeeperp((String)objs[4]);
				json.setOperaterr(((String)objs[5]));
				json.setRemark((String)objs[6]);
				json.setRecorderer((String) objs[7]);
				json.setLeavebreastweight((Float)objs[8]);
				lists.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", lists);
		map.put("total", listSql.size());
		return map;
	}
	
	public Individual getIndividualByMonkeyid(String monkeyid){
		String sql=" From Individual";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where monkeyid=?";
		}
		Query query=getSession().createQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter(0, monkeyid);
		}
		Individual i=(Individual)query.list().get(0);
		return i;
	}

}
