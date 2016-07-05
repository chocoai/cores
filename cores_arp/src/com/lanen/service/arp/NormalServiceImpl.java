package com.lanen.service.arp;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Employee;
import com.lanen.model.Leavebreast_Json;
import com.lanen.model.Normal;
import com.lanen.model.Normal_Json;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Service
public class NormalServiceImpl extends BaseLongDaoImpl<Normal> implements
		NormalService {

	public Map<String, Object> getChildMonkey(String page,String rows,String monkeyid,String startdate,String enddate) {
		String sql="select id,monkeyid,leavebreastdate,motherid," +
				"(SELECT e.name FROM  employee AS e WHERE e.id=l.keeper) as keeper," +
				"(SELECT e.name FROM  employee AS e WHERE e.id=l.operater) as operater, " +
				"remark " +
				"FROM Leavebreast as l WHERE  deleted !=1";
		if(null!=monkeyid && !("").equals(monkeyid)){
			sql = sql +" and monkeyid = :monkeyid ";
		}
		if((null != startdate && !("").equals(startdate))&&(null != enddate && !("").equals(enddate))){
			sql = sql +"  and labordate between  :startdate and  :enddate ";
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
				lists.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", lists);
		map.put("total", listSql.size());
		return map;
	}

	public void updateNormalById(String id,String type) {
		Normal normal=getById(Long.valueOf(id));
		if(type==Constant.SURFACE_NAME){
			normal.setSurface(Constant.NORMAL_FLAG);
		}
		if(type==Constant.PARASITE_NAME){
			normal.setParasite(Constant.NORMAL_FLAG);
		}
		if(type==Constant.VIRUS_NAME){
			normal.setVirus(Constant.NORMAL_FLAG);
		}
		if(type==Constant.BACTERIA_NAME){
			normal.setBacteria(Constant.NORMAL_FLAG);
		}
		if(type==Constant.VACCINE_NAME){
			normal.setVaccine(Constant.NORMAL_FLAG);
		}
		if(type==Constant.INFECTIOUS_NAME){
			normal.setInfectious(Constant.NORMAL_FLAG);
		}
		if(type==Constant.TB_NAME){
			normal.setTb(Constant.NORMAL_FLAG);
		}
		if(type==Constant.X_NAME){
			normal.setX(Constant.NORMAL_FLAG);
		}
		if(type==Constant.XCG_NAME){
			normal.setXcg(Constant.NORMAL_FLAG);
		}
		if(type==Constant.XYSH_NAME){
			normal.setXysh(Constant.NORMAL_FLAG);
		}
		
		normal.setLastmodifytime(Timestamp.valueOf(DateUtil.dateToString(new Date(), "yyyy-MM-dd hh:mm:ss")));
		Employee user=(Employee)ActionContext.getContext().getSession().get("user");
		normal.setModified_by(Integer.valueOf(user.getId()+""));
		update(normal);
		
	}

	public List<Map<String, Object>> listItem(String id) {
		String sql="select surface,parasite,virus,bacteria,vaccine,infectious,tb,x,xcg,xysh, " +
				"normallist,checkdate " +
				" from normal ";
		
		if(!"".equals(id)&&id!=null){
			sql=sql+" where id=:id";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(id)&&id!=null){
			query.setParameter("id", id);
		}
		List<?> listmap=query.list();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(Object ob: listmap){
			Object[] objs=(Object[])ob;
			Map map=new HashMap<String,Object>();
			map.put("surface", objs[0]);
			map.put("parasite", objs[1]);
			map.put("virus", objs[2]);
			map.put("bacteria", objs[3]);
			map.put("vaccine", objs[4]);
			map.put("infectious", objs[5]);
			map.put("tb", objs[6]);
			map.put("x", objs[7]);
			map.put("xcg", objs[8]);
			map.put("xysh", objs[9]);
			map.put("monkeyid", objs[10]);//常规检疫 normallist---monkeyid
			map.put("checkdate", objs[11]);
			list.add(map);
		}
		return list;
	}

	public boolean isExistNormalid(String title){
		if(title!=null){
			//Long export=Long.valueOf(exportid);
			List<?> list=getSession().createQuery("From Normal where deleted!=1 and title=?").setParameter(0, title).list();
			if(list!=null && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public String getCheckDateByTitle(String title) {
		String hql="from Normal where title=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, title);
		if (query.list().size()>0) {
			Normal n = (Normal) query.list().get(0);
			if (!"".equals(n.getCheckdate())&&n.getCheckdate()!=null) {
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String str = sdf.format(n.getCheckdate());
				return str;
			}else{
				return "";
			}
		}else{
			return "";
		}
	}

	public Normal_Json getCheckItemInfo(String title) {
		String hql="select id,title,checkdate,(select name from employee e where e.id=n.boss)boss from normal n where title=?";
		List<?> ln=getSession().createSQLQuery(hql).setParameter(0, title).list();
		if(ln.size()>0){
			Normal_Json normal=new Normal_Json();
			for(Object ob:ln){
				Object[]objs=(Object[])ob;
				
				normal.setId(Long.parseLong(objs[0]+""));
				normal.setTitle((String)objs[1]);
				normal.setCheckdate((Date)objs[2]);
				normal.setBossName((String) objs[3]);
			}
			return normal;
		}else{
			return null;
		}
	}
}
