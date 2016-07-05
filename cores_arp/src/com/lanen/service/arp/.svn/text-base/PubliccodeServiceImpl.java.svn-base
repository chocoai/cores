package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Publiccode;
@Service
public class PubliccodeServiceImpl extends BaseLongDaoImpl<Publiccode> implements PubliccodeService {

	//驱虫记录报表,分页
	public Map<String, Object> getQC(String rows, String page,String monkeyid,Date cdate) {
		String sql="select id,"+
				"(select areaname from area where id=(select room from individual i where i.monkeyid=q.monkeyid))as roomid,"+
				"(select lhao from individual i where i.monkeyid=q.monkeyid)as lhao,"+
				"monkeyid,"+
				"(select sex from individual i where i.monkeyid=q.monkeyid)as sex,"+
				"(select currentweight from individual i where i.monkeyid=q.monkeyid)as weight,"+
				"qcyp,"+
				"remark ,(select title from normal n where n.id=q.normal_id) as title,qcyl,qcrq " +
				"from qc q where q.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and q.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and q.cdate=:cdate";
		}
		//sql=sql+" group by normal_id";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Object[] objs=(Object[])ob;
			Map map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			map.put("roomid", objs[1]);
			map.put("lhao", objs[2]);
			map.put("monkeyid", objs[3]);
			map.put("sex", objs[4]);
			map.put("weight", objs[5]);//体重转为Double
			map.put("qcyp", objs[6]);//驱虫药品
			map.put("remark", objs[7]);
			map.put("checkId", objs[8]);//检疫编号.
			map.put("qcyl", objs[9]);
			map.put("qcrq", objs[10]);
			//下面格式化输出TB检测项目结果   已驱虫"√"  未驱虫"/"
			//检测项目备注 需要统一标识。此处没有做处理
			if(!"".equals(objs[6])&&objs[6]!=null){
				map.put("qc", "√");
			}else{
				map.put("qc", "/");
			}
			String qcyp=(String)objs[6];			
			listMap.add(map);
		}
		Map returnMap=new HashMap<String,Object>();
		returnMap.put("rows", listMap);
		returnMap.put("total",listtotal.size());
		Map<String,Object> m1=new HashMap<String,Object>();
		m1.put("shuoming", "备注：");
		returnMap.put("footer", m1);
		return returnMap;
	}
	
	//驱虫记录报表,不分页
	public Map<String, Object> getQC(String monkeyid,String cdate,String checkId) {
		String sql="select q.id,"+
				"(select areaname from area where id=(select room from individual i where i.monkeyid=q.monkeyid))as roomid,"+
				"(select lhao from individual i where i.monkeyid=q.monkeyid)as lhao,"+
				"monkeyid,"+
				"(select sex from individual i where i.monkeyid=q.monkeyid)as sex,"+
				"(select currentweight from individual i where i.monkeyid=q.monkeyid)as weight,"+
				"qcyp,"+
				"remark,qcyl,qcrq from qc q,normal n where q.deleted!=1 and n.id=q.normal_id ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and q.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and q.cdate=:cdate";
		}
		if(!"".equals(checkId)&&checkId!=null){
			sql=sql+" and n.title=:checkId ";
		}
		//sql=sql+" group by normal_id";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(cdate)&&cdate!=null){
			query.setParameter("cdate", cdate);
		}
		if(!"".equals(checkId)&&checkId!=null){
			query.setParameter("checkId", checkId);
		}
		List<?> listtotal=query.list();
		
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		for(Object ob:listtotal){
			Object[] objs=(Object[])ob;
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", objs[0]);
			if (!"".equals(objs[1])&&objs[1]!=null) {
				map.put("roomid", objs[1]);
			}else{
				map.put("roomid", "");
			}
			map.put("lhao", objs[2]==null?"":objs[2]);
			map.put("monkeyid", objs[3]);
			map.put("sex", objs[4]==null?"":objs[4]);
			map.put("weight", objs[5]==null?"":objs[5]);//体重转为Double
			map.put("qcyp", objs[6]==null?"":objs[6]);//驱虫药品
			map.put("remark", objs[7]==null?"":objs[7]);
			map.put("qcyl", objs[8]==null?"":objs[8]);
			map.put("qcrq", objs[9]==null?"":objs[9]);
			String qcyp = (String) objs[6];
			if (!"".equals(qcyp)&&qcyp!=null) {
				//下面格式化输出TB检测项目结果   已驱虫"√"  未驱虫"/"
				//检测项目备注 需要统一标识。此处没有做处理
				map.put("qc", "√");
			}else{
				map.put("qc", "/");
			}
			listMap.add(map);
		}
		Map<String,Object> returnMap=new HashMap<String,Object>();
		returnMap.put("rows", listMap);
		returnMap.put("total",listtotal.size());
		return returnMap;
	}
}
