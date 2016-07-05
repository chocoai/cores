package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Breeding;
import com.lanen.model.Breeding_Json;

/**
 * 发情配种   serviceImpl
 * @author Administrator
 */
@Service
public class BreedingServiceImpl extends BaseLongDaoImpl<Breeding> implements
		BreedingService {

	public Map<String, Object> loadListByCondition(String page,String rows,String monkeyid,
			Date oestrusdate, Date breedingdate, Date matingdate,
			Integer oestrustype) {
		String sql = "SELECT id,monkeyid,oestrusdate,oestrustype,breedingdate,matingdate,malesmonkeyid " +
				     " ,veterinarian,(select name from employee where id=veterinarian ) as veterinarianName " +
				     " ,protector,(select name from employee where id=protector ) as protectorName " +
				     " ,recorder,(select name from employee where id=recorder ) as recorderName " +
				     " ,operater,(select name from employee where id=operater ) as operaterName " +
				     " ,deleted,remark,(select name from publiccode p where p.id=oestrustype) as oestrustypeName " +
				     " FROM breeding WHERE deleted != 1 ";
		if(null != monkeyid && !monkeyid.equals("")){
			sql = sql +" and monkeyid = :monkeyid ";
		}
		if(null != oestrusdate && !oestrusdate.equals("") ){
		    sql = sql +" and oestrusdate = :oestrusdate ";
		}
		if(null != breedingdate && !breedingdate.equals("") ){
		    sql = sql +" and breedingdate = :breedingdate ";
		}
		if(null != matingdate && !matingdate.equals("") ){
		    sql = sql +" and matingdate = :matingdate ";
		}
		if(null != oestrustype && oestrustype!=0){
		    sql = sql +" and oestrustype = :oestrustype ";
		}
		sql = sql +" order by oestrusdate desc";
		Query query = getSession().createSQLQuery(sql);
		if(null != monkeyid && !monkeyid.equals("")){
		    query.setParameter("monkeyid", monkeyid);
		}
		if(null != oestrusdate && !oestrusdate.equals("")){
		    query.setParameter("oestrusdate", oestrusdate);
		}
		if(null != breedingdate && !breedingdate.equals("")){
		    query.setParameter("breedingdate", breedingdate);
		}
		if(null != matingdate && !matingdate.equals("") ){
		    query.setParameter("matingdate", matingdate);
		}
		if(null != oestrustype && oestrustype!=0){
			query.setParameter("oestrustype", oestrustype);
		}
		List<?> listtotal = query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
		
		List<Breeding_Json> list1 = new ArrayList<Breeding_Json>();
		if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				
				Breeding_Json json = new Breeding_Json(); 
				Integer id=(Integer)objs[0];
				Long idL=Long.valueOf(id+"");
				json.setId(idL);
				json.setMonkeyid((String)objs[1]);
				json.setOestrusdate((Date)objs[2]);
				if(null != objs[3]){
					json.setOestrustype((Integer)objs[3]);
				}
				json.setBreedingdate((Date)objs[4]);
			   	json.setMatingdate((Date)objs[5]);
				json.setMalesmonkeyid((String)objs[6]);
				json.setVeterinarianName((String)objs[8]);
				json.setProtectorName((String)objs[10]);
				json.setRecorderName((String)objs[12]);
				json.setOperaterName((String)objs[14]);
				json.setDeleted((Byte)objs[15]);
				json.setRemark((String)objs[16]);
				json.setOestrustypeName((String)objs[17]);
				list1.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

	public List<Map<String, String>> getAllOestrusTypeMapNo(String mark) {
		String sql="SELECT ID,NAME FROM publiccode WHERE mark=:mark ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("mark", mark);
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

	public List<Breeding_Json> getAllOestrusById(String monkeyid) {
		String sql = "SELECT id,monkeyid,oestrusdate,oestrustype,breedingdate,matingdate,malesmonkeyid "
				+ " ,veterinarian,(select name from employee where id=veterinarian ) as veterinarianName "
				+ " ,protector,(select name from employee where id=protector ) as protectorName "
				+ " ,recorder,(select name from employee where id=recorder ) as recorderName "
				+ " ,operater,(select name from employee where id=operater ) as operaterName "
				+ " ,deleted,remark,(select name from publiccode p where p.id=oestrustype) as oestrustypeName "
				+ " FROM breeding WHERE deleted != 1 and monkeyid=:monkeyid";
		
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("monkeyid", monkeyid);
		
		List<?> list = query.list();
		List<Breeding_Json> list1 = new ArrayList<Breeding_Json>();
		if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				
				Breeding_Json json = new Breeding_Json(); 
				Integer id=(Integer)objs[0];
				Long idL=Long.valueOf(id+"");
				json.setId(idL);
				json.setMonkeyid((String)objs[1]);
				json.setOestrusdate((Date)objs[2]);
				if(null != objs[3]){
					json.setOestrustype((Integer)objs[3]);
				}
				json.setBreedingdate((Date)objs[4]);
			   	json.setMatingdate((Date)objs[5]);
				json.setMalesmonkeyid((String)objs[6]);
				json.setVeterinarianName((String)objs[8]);
				json.setProtectorName((String)objs[10]);
				json.setRecorderName((String)objs[12]);
				json.setOperaterName((String)objs[14]);
				json.setDeleted((Byte)objs[15]);
				json.setRemark((String)objs[16]);
				json.setOestrustypeName((String)objs[17]);
				list1.add(json);
			}
		}
		return list1;
	}

}
