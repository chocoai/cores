package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Childbirth;
import com.lanen.model.Childbirth_Json;
import com.lanen.util.DateUtil;
/**
 * 产仔登记   serviceImpl
 * @author Administrator
 */
@Service
public class ChildbirthServiceImpl extends BaseLongDaoImpl<Childbirth> implements
		ChildbirthService {

	public Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, String labordate, Integer childercount) {
		String sql = "SELECT id,monkeyid,monkeyids,labordate,childercount,laborcondition " +
				     " ,veterinarian,(select name from employee where id=veterinarian ) as veterinarianName " +
				     " ,keeper,(select name from employee where id=keeper ) as keeperName " +
				     " ,protector,(select name from employee where id=protector ) as protectorName " +
				     " ,recorder,(select name from employee where id=recorder ) as recorderName " +
				     " ,operater,(select name from employee where id=operater ) as operaterName " +
				     " ,deleted " +
				     " FROM childbirth WHERE deleted != 1 ";
			if(null != monkeyid && !monkeyid.equals("")){
			    sql = sql +" and monkeyid = :monkeyid ";
			}
			if(null != labordate && !labordate.equals("") ){
			    sql = sql +" and labordate = :labordate ";
			}
			if(null != childercount && childercount!=0){
			    sql = sql +" and childercount = :childercount ";
			}
			sql=sql+" order by labordate desc";
			Query query = getSession().createSQLQuery(sql);
			if(null != monkeyid && !monkeyid.equals("")){
			    query.setParameter("monkeyid", monkeyid);
			}
			if(null != labordate && !labordate.equals("") ){
			    query.setParameter("labordate", labordate);
			}
			if(null != childercount && childercount!=0){
			    query.setParameter("childercount", childercount);
			}
			List<?> listtotal = query.list();
			//当为缺省值的时候进行赋值        
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
			List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
			
			
			List<Childbirth_Json> list1 = new ArrayList<Childbirth_Json>();
			if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				
				Childbirth_Json json = new Childbirth_Json(); 
				Integer id=(Integer)objs[0];
				Long idL=Long.valueOf(id+"");
				json.setId(idL);
				json.setMonkeyid((String)objs[1]);
				json.setMonkeyids((String)objs[2]);
				//json.setLabordate((Date)objs[3]);
				//json.setLabordate(DateUtil.stringToDate((String)objs[3], "yyyy-MM-dd"));
				json.setLabordate(DateUtil.stringToDate(objs[3].toString(), "yyyy-MM-dd"));
				if(null != objs[4]){
					json.setChildercount(Integer.valueOf((Byte)objs[4]));
				}
				json.setLaborcondition((String)objs[5]);
				json.setVeterinarianName((String)objs[7]);
				json.setKeeperName((String)objs[9]);
				json.setProtectorName((String)objs[11]);
				json.setRecorderName((String)objs[13]);
				json.setOperaterName((String)objs[15]);
				json.setDeleted(Integer.valueOf((Byte)objs[16]));
				list1.add(json);
			}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list1);
			map.put("total", listtotal.size());
			return map;
	}

	public List<Childbirth> getChaildbirthById(String monkeyid) {
		String hql=" from Childbirth where monkeyid=?";
		List<Childbirth> l=getSession().createQuery(hql).setParameter(0, monkeyid).list();
		return l;
	}

}
