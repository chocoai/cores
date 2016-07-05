package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Gestation;
import com.lanen.model.Gestation_Json;
/**
 * 妊娠检查  serviceImpl
 * @author Administrator
 */
@Service
public class GestationServiceImpl extends BaseLongDaoImpl<Gestation> implements
		GestationService {

	public Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, Date checkdate, Integer ishave) {
		String sql = "SELECT id,monkeyid,checkdate,ishave,remarks " +
				     " ,veterinarian,(select name from employee where id=veterinarian ) as veterinarianName " +
				     " ,protector,(select name from employee where id=protector ) as protectorName " +
				     " ,recorder,(select name from employee where id=recorder ) as recorderName " +
				     " ,operater,(select name from employee where id=operater ) as operaterName " +
				     " ,deleted" +
				     " FROM gestation WHERE deleted != 1 ";
			if(null != monkeyid && !monkeyid.equals("")){
			     sql = sql +" and monkeyid = :monkeyid ";
			}
			if(null != checkdate && !checkdate.equals("") ){
			     sql = sql +" and checkdate = :checkdate ";
			}
			if(null != ishave && !"".equals(ishave)){
			     sql = sql +" and ishave = :ishave ";
			}
			sql = sql +" order by checkdate desc";
			Query query = getSession().createSQLQuery(sql);
			if(null != monkeyid && !monkeyid.equals("")){
			     query.setParameter("monkeyid", monkeyid);
			}
			if(null != checkdate && !checkdate.equals("")){
			     query.setParameter("checkdate", checkdate);
			}
			if(null != ishave && !"".equals(ishave)){
			     query.setParameter("ishave", ishave);
			}
			List<?> listtotal = query.list();
			//当为缺省值的时候进行赋值        
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
			List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
			
			
			List<Gestation_Json> list1 = new ArrayList<Gestation_Json>();
			if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				
				Gestation_Json json = new Gestation_Json(); 
				Integer id=(Integer)objs[0];
				Long idL=Long.valueOf(id+"");
				json.setId(idL);
				json.setMonkeyid((String)objs[1]);
				json.setCheckdate((Date)objs[2]);
				if(null != objs[3]){
					json.setIshave(Integer.valueOf((Byte)objs[3]));
				}
				json.setRemarks((String)objs[4]);
				json.setVeterinarianName((String)objs[6]);
				json.setProtectorName((String)objs[8]);
				json.setRecorderName((String)objs[10]);
				json.setOperaterName((String)objs[12]);
				json.setDeleted(Integer.valueOf((Byte)objs[13]));
				list1.add(json);
			}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list1);
			map.put("total", listtotal.size());
			return map;
	}

	public List<Gestation> getGestationById(String monkeyid) {
		String hql=" from Gestation where monkeyid=?";
		List<Gestation> l=getSession().createQuery(hql).setParameter(0, monkeyid).list();
		return l;
	}

	
}
