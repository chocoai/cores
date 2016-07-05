package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Normal;
import com.lanen.model.Normal_Json;

@Service
public class ExportServiceImpl extends BaseLongDaoImpl<Normal> implements
		ExportService {

	public Map<String, Object> getExportQuarantine(String page, String rows,
			String orderid,String startdate,String enddate) {
		String sql = "select id,checkdate,surface,parasite,virus,bacteria,vaccine,infectious,tb,x,title,xcg,xysh,qc from normal where deleted=0 and isnull(normallist)";
		if (orderid != null && !("").equals(orderid)) {
			sql = sql + " and title=:orderid";
		}
		if (startdate != null && !("").equals(startdate)&& enddate != null && !("").equals(enddate)) {
			sql = sql + " and checkdate between  :startdate and  :enddate";
		}
		Query query = getSession().createSQLQuery(sql);
		if (null != orderid && !("").equals(orderid)) {
			query.setParameter("orderid", orderid);
		}
		if (startdate != null && !("").equals(startdate)&& enddate != null && !("").equals(enddate) ){
			query.setParameter("startdate", startdate);
			query.setParameter("enddate", enddate);
		}
		List<?> lists = query.list();
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Normal> listt = new ArrayList<Normal>();

		if (list != null) {
			for (Object obj : list) {
				Normal n = new Normal();
				Object[] objs = (Object[]) obj;

				n.setId(Long.valueOf(objs[0] + ""));
				n.setCheckdate((Date) objs[1]);
				n.setSurface((String)objs[2]);
				n.setParasite((String)objs[3]);
				n.setVirus((String)objs[4]);
				n.setBacteria((String)objs[5]);
				n.setVaccine((String)objs[6]);
				n.setInfectious((String)objs[7]);
				n.setX((String)objs[9]);
				n.setTb((String)objs[8]);

				//检疫单号
				n.setTitle((String)objs[10]);
				//n.setStatus((String)objs[11]);
				n.setXcg((String) objs[11]);
				n.setXysh((String) objs[12]);
				n.setQc((String) objs[13]);
				listt.add(n);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public Map<String, Object> getMonkeyList(String page,String rows,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		//出口检疫normallist=null
		String sql = "select id,monkeylist,checkdate,surface,parasite,virus,bacteria,vaccine,infectious,tb,x ,title from normal where deleted=0 and isnull(normallist)";
		if (!("").equals(id) && id != null) {
			sql = sql + " and id=:id";
		}
		Query query = getSession().createSQLQuery(sql);
		if (!("").equals(id) && id != null) {
			query.setParameter("id", id);
		}
		List<?> listIsNotTotal = query.list();
		
		List<Normal_Json> listt = new ArrayList<Normal_Json>();
		for (Object obj : listIsNotTotal) {
			Object[] objs = (Object[]) obj;
			String monkeylist = ((String) objs[1]);
			String[] s = monkeylist.split(",");
			//开始分页。
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);// 第几页
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
					: rows);//每页行数.
			for (int i = 0; i < s.length; i++) {
				Normal_Json j = new Normal_Json();
				j.setId(Long.valueOf(objs[0] + ""));//normal表id
				j.setCheckdate((Date) objs[2]);
				j.setMonkeyid(s[i]);
				j.setSurface((String) objs[3]);
				j.setParasite((String) objs[4]);
				j.setVirus((String)objs[5]);
				j.setVaccine((String)objs[7]);
				j.setBacteria((String)objs[6]);
				j.setInfectious((String)objs[8]);
				j.setTb((String)objs[9]);
				j.setX((String)objs[10]);
				j.setTitle((String)objs[11]);
				listt.add(j);
			}
			//rows是将所有monkeylist装进去故无分页效果.
			map.put("rows", listt);
			map.put("total", s.length);

		}
		return map;

	}
	//获取normal 自增ID的下一个值
	public Integer getNextNormalId(){
		String sql="select max(id) from normal";
		List<?> list=getSession().createSQLQuery(sql).list();
		Integer id=(Integer)list.get(0);
		return id+1;
	}

	public ArrayList<Object> getExcelFiledDataList(long id) {
		String sql="select monkeylist from normal ";
		if(!"".equals(id)&&id!=0){
			sql=sql+" where id=:id";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(id)&&id!=0){
			query.setParameter("id", id);
		}
		String monkeylist=(String)query.list().get(0);
		String []monkeylists=monkeylist.split(",");
		
		ArrayList<Object> filedData = new ArrayList<Object>();
		
		for(int i=0;i<monkeylists.length;i++){
			ArrayList<Object> dataList=new ArrayList<Object>();
			//dataList.add("");//首列隐藏l
			dataList.add(i+1);//序号
			//dataList.add(monkeylists[i]);
			String monkeyid=monkeylists[i];
			//"房号", "笼号", "猴号", "性别", "体重"
			String sql1="select  (select areaname from area a where a.id=i.room) as room, " +
					"lhao,sex,currentweight from individual i ";
			if(!"".equals(monkeyid)&&monkeyid!=null){
				sql1=sql1+" where monkeyid=:monkeyid";
			}
			Query query1=getSession().createSQLQuery(sql1);
			if(!"".equals(monkeyid)&&monkeyid!=null){
				query1.setParameter("monkeyid", monkeyid);
			}
			//每行只允许1条数据.
			List<?> monkeyInfo=query1.list();
			if (monkeyInfo.size()==1) {
				for (Object ob : monkeyInfo) {
					Object objs[] = (Object[]) ob;
					dataList.add(objs[0]);//房号
					dataList.add(objs[1]);//笼号
					dataList.add(monkeylists[i]);//猴号
					if ("0".equals(objs[2])) {
						dataList.add("公");//性别
					} else {
						dataList.add("母");
					}

					dataList.add(objs[3]);//体重.
				}
				filedData.add(dataList);
			}
		}
		
		return filedData;
	}

	public ArrayList<Object> getExcelFiledNameList() {
			// 获取表头,存放到ArrayList filedName对象中(序号 动物编号 类别 出生日期 所属区域 所属房舍 饲养员 父亲编号
			// 母亲编号 当前体重 检疫日期 离乳日期 离乳体重 体检 )
			//String[] titlesVaccine = { "疫苗检疫","序号", "房号", "笼号", "猴号", "性别", "体重","麻疹", "甲肝","乙肝", "其他", "备注"};
			//String[] titlesVaccine = {"序号", "房号", "笼号", "猴号", "性别", "体重","疫苗", "其他", "备注"};
			String[] titlesVaccine = {"序号", "房号", "笼号", "猴号", "性别", "体重","麻疹", "甲肝","乙肝", "其他", "备注"};
			String[] titlesQc = {"序号", "房号", "笼号", "猴号", "性别", "体重","驱虫药品","驱虫用量", "驱虫日期","备注"};
			String[] titlesBacteria = {"序号", "房号", "笼号", "猴号", "性别", "体重","肛拭号", "采样","沙门氏菌", "志贺氏菌", "耶尔森氏菌","备注"};
			String[] titlesVirus = {"序号", "房号", "笼号", "猴号", "性别", "体重","血清号", "采样","B-V", "STLV", "SRV","SIV","FILO","其他","备注"};
			String[] titlesXcg = {"序号", "房号", "笼号", "猴号", "性别", "体重","血样号", "采样","WBC", "RBC","HGB","HCT","PLT","MCV","MCH","MCHC","LYM","MID","GRA", "备注"};
			String[] titlesXysh = {"序号", "房号", "笼号", "猴号", "性别", "体重","血样号", "采样","AST", "ALT","ALP","TP","ALB","GGT","TBIL","BUN","CREA","GLU","TG","CHOL", "备注"};
			String[] titlesInParasite = {"序号", "房号", "笼号", "猴号", "性别", "体重","样本号", "采样","溶组织内阿米", "蠕虫","鞭毛虫","其他", "备注"};
			String[] titlesOutParasite = {"序号", "房号", "笼号", "猴号", "性别", "体重","样本号", "采样","体外寄生虫(节肢动物)", "其他", "备注"};
			String[] titlesTb = {"序号", "房号", "笼号", "猴号", "性别", "体重","右眼","TB24", "TB48","TB72", "备注"};
			String[] titlesInfectious = {"序号", "房号", "笼号", "猴号", "性别", "体重","样本号","药名", "药量", "备注"};
			String[] titlesX = {"序号", "房号", "笼号", "猴号", "性别", "体重","检疫部位", "备注"};
			String[] titlesSurface = {"序号", "房号", "笼号", "猴号", "性别", "体重", "备注"};
			ArrayList<Object> filedNames = new ArrayList<Object>();
			
			ArrayList<Object> filedNameVaccine = new ArrayList<Object>();
			
			for (int i = 0; i < titlesVaccine.length; i++) {
				String title = titlesVaccine[i];
				filedNameVaccine.add(title);
			}
			filedNames.add(filedNameVaccine);
			
			ArrayList<Object> filedNameQc = new ArrayList<Object>();
			
			for (int i = 0; i < titlesQc.length; i++) {
				String title = titlesQc[i];
				filedNameQc.add(title);
			}
			filedNames.add(filedNameQc);
			
			ArrayList<Object> filedNameBacteria = new ArrayList<Object>();
			
			for (int i = 0; i < titlesBacteria.length; i++) {
				String title = titlesBacteria[i];
				filedNameBacteria.add(title);
			}
			filedNames.add(filedNameBacteria);
			
			ArrayList<Object> filedNameVirus = new ArrayList<Object>();
			
			for (int i = 0; i < titlesVirus.length; i++) {
				String title = titlesVirus[i];
				filedNameVirus.add(title);
			}
			filedNames.add(filedNameVirus);
			
			ArrayList<Object> filedNameXcg = new ArrayList<Object>();
			
			for (int i = 0; i < titlesXcg.length; i++) {
				String title = titlesXcg[i];
				filedNameXcg.add(title);
			}
			filedNames.add(filedNameXcg);
			
			ArrayList<Object> filedNameXysh = new ArrayList<Object>();
			
			for (int i = 0; i < titlesXysh.length; i++) {
				String title = titlesXysh[i];
				filedNameXysh.add(title);
			}
			filedNames.add(filedNameXysh);
			
			ArrayList<Object> filedNameInParasite = new ArrayList<Object>();
			
			for (int i = 0; i < titlesInParasite.length; i++) {
				String title = titlesInParasite[i];
				filedNameInParasite.add(title);
			}
			filedNames.add(filedNameInParasite);
			
			ArrayList<Object> filedNameOutParasite = new ArrayList<Object>();
			
			for (int i = 0; i < titlesOutParasite.length; i++) {
				String title = titlesOutParasite[i];
				filedNameOutParasite.add(title);
			}
			filedNames.add(filedNameOutParasite);
			
			ArrayList<Object> filedNameTb = new ArrayList<Object>();
			
			for (int i = 0; i < titlesTb.length; i++) {
				String title = titlesTb[i];
				filedNameTb.add(title);
			}
			filedNames.add(filedNameTb);
			
			ArrayList<Object> filedNameInfectious = new ArrayList<Object>();
			
			for (int i = 0; i < titlesInfectious.length; i++) {
				String title = titlesInfectious[i];
				filedNameInfectious.add(title);
			}
			filedNames.add(filedNameInfectious);
			
			ArrayList<Object> filedNameX = new ArrayList<Object>();
			
			for (int i = 0; i < titlesX.length; i++) {
				String title = titlesX[i];
				filedNameX.add(title);
			}
			filedNames.add(filedNameX);
			
			ArrayList<Object> filedNameSurface = new ArrayList<Object>();
			
			for (int i = 0; i < titlesSurface.length; i++) {
				String title = titlesSurface[i];
				filedNameSurface.add(title);
			}
			filedNames.add(filedNameSurface);
			return filedNames;
	}

	public List<?> getSelectHouse(String monkeyid) {
		String sql="select (select areaname from area a where a.id=i.room)as areaname from individual i ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" where i.monkeyid=:monkeyid";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		return query.list();
	}

	public ArrayList<Object> getExcelFiledNameList(String kind) {
		String[] titlesVaccine = { "动物编号","检疫时间", "麻疹","甲肝", "乙肝",  "备注" };
		String[] titlesQc = { "动物编号", "检疫时间", "驱虫药品", "药品用量", "驱虫日期",  "备注" };
		String[] titlesBacteria = { "动物编号", "检疫时间","肛拭号","沙门氏菌", "志贺氏菌", "耶尔森氏菌","备注" };
		String[] titlesVirus = { "动物编号", "检疫时间", "血清号","B-V", "STLV", "SRV", "SIV", "FILO", "备注" };
		String[] titlesXcg = { "动物编号", "检疫时间", "血样号", "WBC", "RBC", "HGB", "HCT", "PLT", "MCV", "MCH", "MCHC", "LYM","MID", "GRA", "备注" };
		String[] titlesXysh = { "动物编号", "检疫时间", "血样号", "AST", "ALT", "ALP", "TP", "ALB", "GGT", "TBIL", "BUN","CREA", "GLU", "TG", "CHOL", "LDH","CK", "NA", "K", "CI","备注" };
		String[] titlesInParasite = { "动物编号", "检疫时间","样本号", "溶组织内阿米", "蠕虫", "鞭毛虫", "体外寄生虫(节肢动物)", "备注" };
		// String[] titlesOutParasite = {"序号", "房号", "笼号", "猴号", "性别",
		// "体重","样本号", "采样","体外寄生虫(节肢动物)", "其他", "备注"};
		String[] titlesTb = { "动物编号", "检疫时间", "右眼", "TB24","TB48", "TB72", "备注" };
		String[] titlesInfectious = { "动物编号", "检疫时间","样本号", "用药名称", "用药量", "备注" };
		String[] titlesX = { "动物编号", "检疫时间","检疫部位", "备注" };
		String[] titlesSurface = { "动物编号", "检疫时间", "备注" };
		ArrayList<Object> filedNames = new ArrayList<Object>();

		if ( "vaccine".equals(kind)) {
			ArrayList<Object> filedNameVaccine = new ArrayList<Object>();
			for (int i = 0; i < titlesVaccine.length; i++) {
				String title = titlesVaccine[i];
				filedNameVaccine.add(title);
			}
			filedNames.add(filedNameVaccine);
		}
		if ("qc".equals(kind)) {
			ArrayList<Object> filedNameQc = new ArrayList<Object>();
			for (int i = 0; i < titlesQc.length; i++) {
				String title = titlesQc[i];
				filedNameQc.add(title);
			}
			filedNames.add(filedNameQc);
		}
		if ("bacteria".equals(kind)) {
			ArrayList<Object> filedNameBacteria = new ArrayList<Object>();
			for (int i = 0; i < titlesBacteria.length; i++) {
				String title = titlesBacteria[i];
				filedNameBacteria.add(title);
			}
			filedNames.add(filedNameBacteria);
		}
		if ("virus".equals(kind)) {
			ArrayList<Object> filedNameVirus = new ArrayList<Object>();
			for (int i = 0; i < titlesVirus.length; i++) {
				String title = titlesVirus[i];
				filedNameVirus.add(title);
			}
			filedNames.add(filedNameVirus);
		}
		if ("xcg".equals(kind)) {
			ArrayList<Object> filedNameXcg = new ArrayList<Object>();
			for (int i = 0; i < titlesXcg.length; i++) {
				String title = titlesXcg[i];
				filedNameXcg.add(title);
			}
			filedNames.add(filedNameXcg);
		}
		if ("xysh".equals(kind)) {
			ArrayList<Object> filedNameXysh = new ArrayList<Object>();
			for (int i = 0; i < titlesXysh.length; i++) {
				String title = titlesXysh[i];
				filedNameXysh.add(title);
			}
			filedNames.add(filedNameXysh);
		}
		if ("parasite".equals(kind)) {
			ArrayList<Object> filedNameInParasite = new ArrayList<Object>();
			for (int i = 0; i < titlesInParasite.length; i++) {
				String title = titlesInParasite[i];
				filedNameInParasite.add(title);
			}
			filedNames.add(filedNameInParasite);
		}
		if ("tb".equals(kind)) {
			ArrayList<Object> filedNameTb = new ArrayList<Object>();
			for (int i = 0; i < titlesTb.length; i++) {
				String title = titlesTb[i];
				filedNameTb.add(title);
			}
			filedNames.add(filedNameTb);
		}
		if ("infectious".equals(kind)) {
			ArrayList<Object> filedNameInfectious = new ArrayList<Object>();
			for (int i = 0; i < titlesInfectious.length; i++) {
				String title = titlesInfectious[i];
				filedNameInfectious.add(title);
			}
			filedNames.add(filedNameInfectious);
		}
		if ("x".equals(kind)) {
			ArrayList<Object> filedNameX = new ArrayList<Object>();
			for (int i = 0; i < titlesX.length; i++) {
				String title = titlesX[i];
				filedNameX.add(title);
			}
			filedNames.add(filedNameX);
		}
		if ("surface".equals(kind)) {
			ArrayList<Object> filedNameSurface = new ArrayList<Object>();
			for (int i = 0; i < titlesSurface.length; i++) {
				String title = titlesSurface[i];
				filedNameSurface.add(title);
			}
			filedNames.add(filedNameSurface);
		}
		return filedNames;
	}
	/**
	 * 疫苗数据.
	 */
	public ArrayList<Object> getVaccineExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		//String sql="select monkeyid,cdate,remark,GROUP_CONCAT(q_id)as qids from vaccine where normal_id=:id group by monkeyid";
		String sql1="select distinct monkeyid,cdate from vaccine where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			String sql2="select q_id,ypin,monkeyid from vaccine ";
			if(!"".equals(monkeyid)&&monkeyid!=null){
				sql2=sql2+" where monkeyid=:monkeyid";
			}
			if(!"".equals(id)){
				sql2=sql2+" and normal_id=:id";
			}
			Query query2=getSession().createSQLQuery(sql2);
			if(!"".equals(id)&&id!=0){
				query2.setParameter("id", id);
			}
			if(!"".equals(monkeyid)&&monkeyid!=null){
				query2.setParameter("monkeyid", monkeyid);
			}
			List<?> vaccinelist=query2.list();
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			for(Object ob1:vaccinelist){
				Object []objs1=(Object[])ob1;
				
				String qid=(String)objs1[0];
				if("81".equals(qid)){
					dataList.add(objs1[1]);
				}else if("82".equals(qid)){
					dataList.add(objs1[1]);
				}else if("83".equals(qid)){
					dataList.add(objs1[1]);
				}else {
					dataList.add("");
				}
			}
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * 病毒数据.
	 */
	public ArrayList<Object> getVirusExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,xueq,bv,stlv,srv,siv,filo,remark from virus where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			dataList.add(objs[6]);
			dataList.add(objs[7]);
			dataList.add(objs[8]);
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * 细菌数据.
	 */
	public ArrayList<Object> getBacteriaExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,ypid,salm,shig,yers,remark from bacteria where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			dataList.add(objs[6]);
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * 寄生虫数据.
	 */
	public ArrayList<Object> getParasiteExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,bhao,amb,gxc,bmc,twjsc,remark from parasite where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			dataList.add(objs[6]);
			dataList.add(objs[7]);
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * TB数据.
	 */
	public ArrayList<Object> getTBExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,tb24,tb48,tb72,remark from tb where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add("");//右眼.
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * XCG数据.
	 */
	public ArrayList<Object> getXCGExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,bhao,wbc,rbc,hgb,hct,plt,mcv,mch,mchc,lym,mid,gra from xcg where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			dataList.add(objs[6]);
			dataList.add(objs[7]);
			dataList.add(objs[8]);
			dataList.add(objs[9]);
			dataList.add(objs[10]);
			dataList.add(objs[11]);
			dataList.add(objs[12]);
			dataList.add(objs[13]);
			filedData.add(dataList);
		}
		return filedData;
	}
	
	/**
	 * XYSH数据.
	 */
	public ArrayList<Object> getXYSHExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,bhao,ast,alt,alp,tp,alb,ggt,tbil,bun,crea,glu,tg,chol,ldh,ck,na,k,ci from xysh where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			dataList.add(objs[6]);
			dataList.add(objs[7]);
			dataList.add(objs[8]);
			dataList.add(objs[9]);
			dataList.add(objs[10]);
			dataList.add(objs[11]);
			dataList.add(objs[12]);
			dataList.add(objs[13]);
			dataList.add(objs[14]);
			dataList.add(objs[15]);
			dataList.add(objs[16]);
			dataList.add(objs[17]);
			dataList.add(objs[18]);
			dataList.add(objs[19]);
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * QC数据.
	 */
	public ArrayList<Object> getQCExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,qcyp,qcyl,qcrq,remark from qc where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * Infectious数据.
	 */
	public ArrayList<Object> getInfectiousExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,ypin,drugs_name,drugs_count,remark from infectious where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			dataList.add(objs[4]);
			dataList.add(objs[5]);
			filedData.add(dataList);
		}
		return filedData;
	}
	
	/**
	 * x数据.
	 */
	public ArrayList<Object> getXExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,(select name from publiccode p where p.id=x.checkarea) as checkareaname,remark from x where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			dataList.add(objs[3]);
			
			filedData.add(dataList);
		}
		return filedData;
	}
	/**
	 * surface数据.
	 */
	public ArrayList<Object> getSurfaceExcelFiledDataList(long id) {
		ArrayList<Object> filedData = new ArrayList<Object>();
		String sql1="select monkeyid,cdate,remark from surface where deleted!=1 ";
		if(!"".equals(id)&&id!=0){
			sql1=sql1+" and normal_id=:id";
		}
		Query query1=getSession().createSQLQuery(sql1);
		if(!"".equals(id)&&id!=0){
			query1.setParameter("id", id);
		}
		List<?> monkeylist=query1.list();
		for(Object ob:monkeylist){
			ArrayList<Object> dataList=new ArrayList<Object>();
			Object[]objs=(Object[])ob;
			String monkeyid=(String)objs[0];
			
			dataList.add(monkeyid);
			dataList.add(objs[1]);
			dataList.add(objs[2]);
			
			filedData.add(dataList);
		}
		return filedData;
	}
}
