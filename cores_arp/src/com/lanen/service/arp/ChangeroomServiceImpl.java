package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Changeroom;
import com.lanen.model.Death;
@Service
public class ChangeroomServiceImpl extends BaseLongDaoImpl<Changeroom> implements
		ChangeroomService {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getListByConditions(String page, String rows,
			String monkeyid, Date changeroomdate) {
		String hql="from Changeroom where deleted!=1";
		if(monkeyid!=null&&!"".equals(monkeyid)){
			hql=hql+" and monkeyid=:monkeyid";
		}
		if(changeroomdate!=null){
			hql=hql+" and changeroomdate=:changeroomdate";
		}
		hql= hql+" order by changeroomdate desc";
		Query query=getSession().createQuery(hql);
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(changeroomdate!=null){
			query.setParameter("changeroomdate", changeroomdate);
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
	//小写.
	public List<?> getListByConditions(String monkeyid,String changeroomdate,Long xkeeper,Long ykeeper) {
		String hql="select yarea,yroom,ylh,monkeyid," +
				"(select sex from individual i where i.monkeyid=c.monkeyid )as sex," +
				"(select currentweight from individual i where i.monkeyid=c.monkeyid)as weight," +
				"changeinarea,changeinroom,lhao,remark," +
				"(select name from employee e where e.id=c.ykeeper) as ykeeper," +
				"(select name from employee e where e.id=c.operater) as xkeeper," +
				"(select name from animaltype where id=(select animaltype from individual i where i.monkeyid=c.monkeyid))as animaltype from changeroom c where c.deleted!=1";
		
		if(changeroomdate!=null&&!"".equals(changeroomdate)){
			hql=hql+" and changeroomdate=:changeroomdate";
		}
		if(monkeyid!=null&&!"".equals(monkeyid)){
			hql=hql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(xkeeper)&&xkeeper!=null&&xkeeper!=-1){
			hql=hql+" and c.operater=:xkeeper";
		}
		if(!"".equals(ykeeper)&&ykeeper!=null&&ykeeper!=-1){
			hql=hql+" and c.ykeeper=:ykeeper";
		}
		hql= hql+" order by changeroomdate desc";
		Query query=getSession().createSQLQuery(hql);
		
		if(changeroomdate!=null&&!"".equals(changeroomdate)){
			query.setParameter("changeroomdate", changeroomdate);
		}
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(xkeeper)&&xkeeper!=null&&xkeeper!=-1){
			query.setParameter("xkeeper", xkeeper);
		}
		if(!"".equals(ykeeper)&&ykeeper!=null&&ykeeper!=-1){
			query.setParameter("ykeeper", ykeeper);
		}
		List<?> listtotal=query.list();
		return listtotal;
	}
	//报表+性别体重。表名小写.
	@SuppressWarnings("unchecked")
	public Map<String, Object> getChangeroom(String page, String rows,
			String monkeyid, Date changeroomdate,Long xkeeper,Long ykeeper) {
		String hql="select id,monkeyid,yarea,yroom,ylh," +
				"(select sex from individual i where i.monkeyid=c.monkeyid) as sex," +
				"(select currentweight from individual i where i.monkeyid=c.monkeyid)as weight," +
				"changeinarea,changeinroom,lhao,remark,(select name from employee e where e.id=c.operater)as operater,(select name from employee e where e.id=c.ykeeper)as ykeeper, " +
				"changeroomdate from changeroom c where c.deleted!=1";
		if(monkeyid!=null&&!"".equals(monkeyid)){
			hql=hql+" and c.monkeyid=:monkeyid";
		}
		if(changeroomdate!=null){
			hql=hql+" and c.changeroomdate=:changeroomdate";
		}
		if(!"".equals(xkeeper)&&xkeeper!=null&&xkeeper!=-1){
			hql=hql+" and c.operater=:xkeeper";
		}
		if(!"".equals(ykeeper)&&ykeeper!=null&&ykeeper!=-1){
			hql=hql+" and c.ykeeper=:ykeeper";
		}
		hql= hql+" order by c.changeroomdate desc";
		Query query=getSession().createSQLQuery(hql);
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(changeroomdate!=null){
			query.setParameter("changeroomdate", changeroomdate);
		}
		if(!"".equals(xkeeper)&&xkeeper!=null&&xkeeper!=-1){
			query.setParameter("xkeeper", xkeeper);
		}
		if(!"".equals(ykeeper)&&ykeeper!=null&&ykeeper!=-1){
			query.setParameter("ykeeper", ykeeper);
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		return map;
	}
	public List<Long> getXKeeper() {
		String sql="select distinct operater from changeroom where deleted!=1";
		Query query=getSession().createSQLQuery(sql);
		List<?> list=query.list();
		List<Long> xkeeper=new ArrayList<Long>();
		if (list!=null) {
			for (Object ob : list) {
				String keep = (String) ob;
				Long keepLon = Long.parseLong(keep);
				xkeeper.add(keepLon);
			}
		}
		return xkeeper;
	}
	public List<Long> getYKeeper(){
		String sql="select distinct ykeeper from changeroom where deleted!=1";
		List<?> list=getSession().createSQLQuery(sql).list();
		List<Long> ykeeper=new ArrayList<Long>();
		if(list!=null){
			for(Object ob:list){
				Integer keep=(Integer)ob;
				if (keep!=null&&!"".equals(keep)) {
					Long keepLon = Long.parseLong(keep + "");
					ykeeper.add(keepLon);
				}
			}
		}
		return ykeeper;
	}
	public List<Changeroom> getChangeroomById(String monkeyid) {
		String hql=" from Changeroom where monkeyid=?";
		List<Changeroom> l=getSession().createQuery(hql).setParameter(0, monkeyid).list();
		return l;
	}

}
