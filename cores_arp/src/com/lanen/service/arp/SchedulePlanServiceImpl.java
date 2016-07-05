package com.lanen.service.arp;

import java.awt.Font;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Bacteria;
import com.lanen.model.Bacteria_Json;
import com.lanen.model.Task;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
@Service
public class SchedulePlanServiceImpl extends BaseLongDaoImpl<Task> implements SchedulePlanService{
	
	public Map<String,Object> loadListByMonkeyIdAndCdate(String page,String rows,String type){
		String sql="select monkeyid," +
		"(select e.name from employee e where b.veterinarian=e.id)as veterinarian," +
		"(select e.name from employee e where b.protector=e.id)as protector," +
		"(select e.name from employee e where b.recorder=e.id)as recorder,cdate,remark " +
		" from bacteria b group by monkeyid,cdate";
		
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null){
			query.setParameter("type", type);
		}
		List<?> lists=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Bacteria> listt=new ArrayList<Bacteria>();

		if(list!=null){
			for(Object obj:list){
				Bacteria p=new Bacteria();
				Object[] objs=(Object[])obj;
				p.setMonkeyid((String)objs[0]);
				p.setVeterinarian((String)objs[1]);
				p.setProtector((String)objs[2]);
				p.setRecorder((String)objs[3]);
				p.setCdate((Date)objs[4]);
				p.setRemark((String)objs[5]);
				
				listt.add(p);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
		
	}

	public Map<String, Object> loadListItem(String monkeyid,String cdate) {
		
		String sql="select " +
				"(select name from quarantine q where b.q_id=q.id) as q_name," +
				"resoult," +
				"(select name from quarantine q where q.id=b.qconfig_id) as qconfig_name," +
				"drugs_name,drugs_count from bacteria b where deleted!=1" ;
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and cdate=:cdate";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null&&!"".equals(cdate)&&cdate!=null){
			query.setParameter("monkeyid", monkeyid);
			query.setParameter("cdate", cdate);
		}
		List<?> lists=query.list();
		
		List<Bacteria_Json> listt=new ArrayList<Bacteria_Json>();

		if(lists!=null){
			for(Object obj:lists){
				Bacteria_Json b=new Bacteria_Json();
				Object[] objs=(Object[])obj;
				
				b.setQ_name((String)objs[0]);
				b.setResoult((Byte)objs[1]);
				b.setQconfig_name((String)objs[2]);
				b.setDrugs_name((String)objs[3]);
				b.setDrugs_count((String)objs[4]);
				listt.add(b);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", listt);
		map.put("total", lists.size());
		return map;
	}

	public Map<String, Object> getBacteria(String rows, String page,String monkeyid,Date cdate) {
		String sql="select id,"+
		"(select room from individual i where i.monkeyid=b.monkeyid)as roomid,"+
		"(select lhao from individual i where i.monkeyid=b.monkeyid)as lhao," +
		"monkeyid,"+
		"(select sex from individual i where i.monkeyid=b.monkeyid)as sex,"+
		"(select currentweight from individual i where i.monkeyid=b.monkeyid)as weight,"+
		"remark from bacteria b where b.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and b.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and b.cdate=:cdate";
		}
		sql=sql+" group by b.normal_id";
	Query query=getSession().createSQLQuery(sql);
	if(!"".equals(monkeyid)&&monkeyid!=null){
		query.setParameter("monkeyid", monkeyid);
	}
	if(!"".equals(cdate)&&cdate!=null){
		query.setParameter("cdate", cdate);
	}
	List<?> listVir=query.list();
	int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
	int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
	List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
	List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
	for(Object ob:list){
		Object []objs=(Object[])ob;
		Map bacteriaMap=new HashMap<String,Object>();
		bacteriaMap.put("id", objs[0]);
		bacteriaMap.put("roomid", objs[1]);
		bacteriaMap.put("lhao", objs[2]);
		bacteriaMap.put("monkeyid", objs[3]);
		String sex=sexToZh((BigInteger)objs[4]);
		bacteriaMap.put("sex", sex);
		bacteriaMap.put("weight", objs[5]);
		bacteriaMap.put("remark", objs[6]);
		
		//下面增加检测项目,在常规建议中需增加检测项目结果记录，否则无值.
		String monkeyids=(String)objs[3];
		List<?> listItems=getBacteriaItems(monkeyids);
		for(int i=0;i<listItems.size();i++){
			Object[]obj=(Object[])listItems.get(i);
			if(obj[0]!=null&&!"".equals(obj[0])){
				bacteriaMap.put("shig", obj[0]);
			}
			if(obj[1]!=null&&!"".equals(obj[1])){
				bacteriaMap.put("salm", obj[1]);
			}
			if(obj[2]!=null&&!"".equals(obj[2])){
				bacteriaMap.put("yers", obj[2]);
			}
		}
		bacteriaMap.put("gangs", "");//肛拭号,后续优化
		bacteriaMap.put("caiy", "");
		listMap.add(bacteriaMap);
	}
	Map map=new HashMap<String,Object>();
	map.put("rows", listMap);
	map.put("total", listVir.size());
	return map;
	}
	/**
	 * 由动物编号获取对应的病毒检测项目,每个q_id对应唯一项目,
	 * 且在常规检疫添加纪录时每个q_id需对应更新每个项目。如q_id=26对应bv,q_id=27对应stlv.
	 * 沙门氏菌:shig
	 * 致贺氏菌：salm
	 * 耶尔森氏菌:yers
	 */
	public List<?> getBacteriaItems(String monkeyid){
		String sql="select shig,salm,yers from bacteria where deleted!=1";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listItem=query.list();
		/*for(int i=0;i<listItem.size();i++){
			Object[]objs=(Object[])listItem.get(i);
			Map map=new HashMap();
			map.put("bv", objs[0]);
			map.put("stlv", objs[1]);
			map.put("srv", objs[2]);
			map.put("siv", objs[3]);
			map.put("filo", objs[4]);
		}*/
		return listItem;
	}
	/**
	 * 增加性别判断
	 */
	public String sexToZh(BigInteger sex){
		String sexZh = null;
		int strSex=sex.intValue();
		if(strSex==0){
			sexZh=Constant.MALE_MONKEY;
		}
		if(strSex==1){
			sexZh=Constant.FEMALE_MONKEY;
		}
		return sexZh;
	}
	public Map<String, Object> getBacteria(String monkeyid,String cdate) {
		String sql="select id,"+
		"(select room from individual i where i.monkeyid=b.monkeyid)as roomid,"+
		"(select lhao from individual i where i.monkeyid=b.monkeyid)as lhao," +
		"monkeyid,"+
		"(select sex from individual i where i.monkeyid=b.monkeyid)as sex,"+
		"(select currentweight from individual i where i.monkeyid=b.monkeyid)as weight,"+
		"remark from bacteria b where b.deleted!=1 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and b.monkeyid=:monkeyid";
		}
		if(!"".equals(cdate)&&cdate!=null){
			sql=sql+" and b.cdate=:cdate";
		}
		sql=sql+" group by b.normal_id";
	Query query=getSession().createSQLQuery(sql);
	if(!"".equals(monkeyid)&&monkeyid!=null){
		query.setParameter("monkeyid", monkeyid);
	}
	if(!"".equals(cdate)&&cdate!=null){
		query.setParameter("cdate", cdate);
	}
	List<?> listbacteria=query.list();
	
	List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
	for(Object ob:listbacteria){
		Object []objs=(Object[])ob;
		Map bacteriaMap=new HashMap<String,Object>();
		bacteriaMap.put("id", objs[0]);
		bacteriaMap.put("roomid", objs[1]);
		bacteriaMap.put("lhao", objs[2]);
		bacteriaMap.put("monkeyid", objs[3]);
		String sex=sexToZh((BigInteger)objs[4]);
		bacteriaMap.put("sex", sex);
		bacteriaMap.put("weight", objs[5]);
		bacteriaMap.put("remark", objs[6]);
		
		//下面增加检测项目,在常规建议中需增加检测项目结果记录，否则无值.
		String monkeyids=(String)objs[3];
		List<?> listItems=getBacteriaItems(monkeyids);
		for(int i=0;i<listItems.size();i++){
			Object[]obj=(Object[])listItems.get(i);
			if(obj[0]!=null&&!"".equals(obj[0])){
				bacteriaMap.put("shig", obj[0]);
			}
			if(obj[1]!=null&&!"".equals(obj[1])){
				bacteriaMap.put("salm", obj[1]);
			}
			if(obj[2]!=null&&!"".equals(obj[2])){
				bacteriaMap.put("yers", obj[2]);
			}
		}
		bacteriaMap.put("gangs", "");//肛拭号,后续优化
		bacteriaMap.put("caiy", "");
		listMap.add(bacteriaMap);
	}
	Map map=new HashMap<String,Object>();
	map.put("rows", listMap);
	map.put("total", listbacteria.size());
	return map;
	}

	public Map<String, Object> getSchedulebyTaskKindCodeTypeTaskType(String rows, String page, Integer status,String title,Date begindate,Date enddate,Integer typeid,Long ower) {
		String sql="select id,(select name from publiccode p where p.id=t.typeid) as typename,begindate,enddate,status,isremind,title,contant,(select name from employee e where e.id=t.ower) as ower from task t where deleted!=1 ";
		if(!"".equals(title)&&title!=null){
			sql=sql+" and title=:title";
		}
		if(!"".equals(begindate)&&begindate!=null){
			sql=sql+" and begindate>=:begindate";
		}
		if(!"".equals(enddate)&&enddate!=null){
			sql=sql+" and enddate<=:enddate";
		}
		if(!"".equals(typeid)&&typeid!=null){
			sql=sql+" and typeid=:typeid";
		}
		if(!"".equals(ower)&&ower!=null){
			sql=sql+" and ower=:ower";
		}
		if(!"".equals(status)&&status!=null){
			sql=sql+" and status=:status";
		}
		sql=sql+" order by id desc";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(title)&&title!=null){
			query.setParameter("title", title);
		}
		if(!"".equals(begindate)&&begindate!=null){
			query.setParameter("begindate", begindate);
		}
		if(!"".equals(enddate)&&enddate!=null){
			query.setParameter("enddate", enddate);
		}
		if(!"".equals(typeid)&&typeid!=null){
			query.setParameter("typeid", typeid);
		}
		if(!"".equals(ower)&&ower!=null){
			query.setParameter("ower", ower);
		}
		if(!"".equals(status)&&status!=null){
			query.setParameter("status", status);
		}
		List<?> lists=query.list();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "12": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		for(Object obj:list){
			Object[] objs=(Object[])obj;
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("id", objs[0]);
			m.put("typename", objs[1]);
			//原因：从数据库里读出来的是java.sql.Date赋值给了java.util.Date,转化成JSONArray时出错； 
			//办法：可以在从数据库读出Date 时直接写成：new java.util.Date(rs.getDate("date").getTime),这样就不会出错了； 
			m.put("begindate", DateUtil.dateToString((Date)objs[2], "yyyy-MM-dd"));
			m.put("enddate", DateUtil.dateToString((Date)objs[3], "yyyy-MM-dd"));
			m.put("status", objs[4]);
			m.put("isremind",objs[5]);
			m.put("title", objs[6]);
			m.put("contant", objs[7]);
			m.put("ower", objs[8]);
			listMap.add(m);
		}
		Map <String,Object> map=new HashMap<String,Object>();
		map.put("rows", listMap);
		map.put("total", lists.size());
		
		return map;
	}
	public List<Map<String, String>> getSchedulePlanMapNo(String mark) {
		String sql="select id,name from  publiccode  ";
		if(!"".equals(mark)&&mark!=null){
			sql=sql+" where mark=:mark";
		}
		Query query = getSession().createSQLQuery(sql);
		if(!"".equals(mark)&&mark!=null){
			query.setParameter("mark", mark);
		}
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
	//在日期范围内，提醒.新建和登记完成的不提醒.
	public Map<String,Object> getSchedulePlanTips(String d1,String d2){
		String sql="from Task where status=2";
		if(!"".equals(d1)&&d1!=null&&!"".equals(d2)&&d2!=null){
			sql=sql+" and begindate between:d1 and :d2";
		}
		Query query=getSession().createQuery(sql);
		if(!"".equals(d1)&&d1!=null&&!"".equals(d2)&&d2!=null){
			query.setParameter("d1", DateUtil.stringToDate(d1, "yyyy-MM-dd"));
			query.setParameter("d2", DateUtil.stringToDate(d2, "yyyy-MM-dd"));
		}
		List<Task> l=(List<Task>)query.list();
		
		Map <String,Object> map=new HashMap<String,Object>();
		map.put("rows", l);
		map.put("total", l.size());
		return map;
	}
	
	//计划汇总.
	public JFreeChart createBarChart() {
		CategoryDataset dataset = getDataSet2();
		JFreeChart chart = ChartFactory.createBarChart3D(
				new Date().getYear()+1900+"年计划汇总图", // 图表标题
				"月份", // 目录轴的显示标签
				"计划个数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, 	// 是否显示图例(对于简单的柱状图必须是false)
				false, 	// 是否生成工具
				false 	// 是否生成URL链接
				);
		//PiePlot plot = (PiePlot) chart.getPlot();  
        //resetPiePlot(plot);
		CategoryPlot plot=chart.getCategoryPlot();
		//CategoryPlot plot=chart.getCategoryPlot();
		//chart.setBackgroundPaint(new Color(246, 244, 47));  //整个区域背景色设置
		//plot.setBackgroundPaint(new Color(255, 255, 255));	//图片背景色.
	    //设置标题字体样式
	    chart.getTitle().setFont(new Font("黑体", Font.CENTER_BASELINE,22));
	    //取得横轴和设置横轴样式
	    CategoryAxis categoryAxis = plot.getDomainAxis();
	    categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 12));
	    //设置横轴字体位置.
	    categoryAxis.setLabelLocation(AxisLabelLocation.HIGH_END);
	    //横轴分类标签
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	    categoryAxis.setTickLabelFont(new Font("宋体", Font.CENTER_BASELINE, 16));
	    //设置说明字体
	    chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));
	    //取得纵轴和设置纵轴样式
	    NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	    numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 12));
	     
	    //显示每个柱的数值，并修改该数值的字体属性   
	    BarRenderer3D renderer = new BarRenderer3D();    
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
	    renderer.setBaseItemLabelsVisible(true);  
	    //默认的数字显示在柱子中，通过如下两句可调整数字的显示   
	    //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 ，将数字显示在柱状图上面
	    renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(  
	            ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));  
	    renderer.setItemLabelAnchorOffset(10D);  
	     //设置每个地区所包含的平行柱的之间距离   
	     renderer.setItemMargin(0.1);   
	    plot.setRenderer(renderer);  
		return chart;
	}

	private CategoryDataset getDataSet2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		Map<String,Object> map=null;
		Date nows=new Date();
		int year=nows.getYear()+1900;
		for(int i=1;i<=12;i++){
			
			List<?> list=null;
			for (int j = 244; j <= 245; j++) {
				map=new HashMap<String,Object>();
				
				if (i == 2) {
					list = getTreasuryCount(year + "-02-01",year + "-02-29",j);
				}
				else if(10<=i&&i<=12){
					list = getTreasuryCount(year +"-"+ i + "-01", year + "-"+ i + "-31",j);
				}else{
					list = getTreasuryCount(year + "-0" + i + "-01", year + "-0" + i + "-31",j);
				}
				BigInteger count = (BigInteger) list.get(0);
				if (count.intValue() > 0) {
					map.put("treasurySum", count);
				} else {
					map.put("treasurySum", 0);
				}
				map.put("treasuryDate", i);
				if (j==245) {
					dataset.addValue(count, "检疫",i);
				}else{//244,计划类别代码
					dataset.addValue(count, "生产",i);
				}
				
			}
		}
		
		return dataset;
	}
	
	public  List<?> getTreasuryCount(String start,String end ,int typeid){
		String sql="select count(id) from task where deleted!=1 and begindate between :start and :end and typeid=:typeid";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("typeid", typeid);
		return (List<?>)query.list();
	}

	public String getTypeName(Integer id) {
		String sql="select name from publiccode";
		if(!"".equals(id)&&id!=null){
			sql=sql+" where id=:id";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(id)&&id!=null){
			query.setParameter("id", id);
		}
		String name=(String) query.list().get(0);
		return name;
	}
}
