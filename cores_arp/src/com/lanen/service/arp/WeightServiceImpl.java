package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Employee;
import com.lanen.model.Weight;
import com.lanen.model.Weight_Json;
import com.lanen.util.Constant;

@Service
public class WeightServiceImpl extends BaseLongDaoImpl<Weight> implements
		WeightService {

	@Resource
	private EmployeeService employeeService;
	//小写.
	public Map<String, Object> getAllWeight(String page, String rows,
			int weighttype, String startDate, String endDate) {
		String sql = "SELECT id,monkeyid,weightdate,weight, weighttype,boss,"
				+ "protector,recorder,operater,remark,(select name from publiccode where id=weighttype) as weighttypeName"
				+ " FROM weight AS w WHERE w.deleted = 0  ";
		if (weighttype != -1) {
			sql = sql + "  AND  w.weighttype = :weighttype ";
		}
		if (startDate != null && !startDate.equals("") && endDate != null
				&& !endDate.equals("")) {
			sql = sql + "  and w.weightdate between  :startDate and  :endDate ";
		}
		sql = sql + " ORDER BY w.weightdate DESC  ";

		Query query = getSession().createSQLQuery(sql);
		if (weighttype != -1) {
			query.setParameter("weighttype", weighttype);
		}
		if (startDate != null && !startDate.equals("") && endDate != null
				&& !endDate.equals("")) {
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		List<?> listtotal = query.list();
		// 当为缺省值的时候进行赋值
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		List<Weight_Json> list1 = new ArrayList<Weight_Json>();
		if (null != list) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				Weight_Json json = new Weight_Json();
				json.setId((Integer) objs[0]);
				json.setMonkeyid((String) objs[1]);
				json.setWeightdate((String) objs[2]);
				json.setWeight((Float) objs[3]);
				json.setWeighttype((Integer) objs[4]);
				if (null != objs[5] && (!objs[5].equals(""))) {
					Employee employee = employeeService.getById(Long
							.parseLong((String) objs[5]));
					if (null != employee) {
						json.setBoss(employee.getName());
					}
				}

				if (null != objs[6] && (!objs[6].equals(""))) {
					Employee employee1 = employeeService.getById(Long
							.parseLong((String) objs[6]));
					if (null != employee1) {
						json.setProtector(employee1.getName());
					}
				}
				if (null != objs[7] && (!objs[7].equals(""))) {
					Employee employee2 = employeeService.getById(Long
							.parseLong((String) objs[7]));
					if (null != employee2) {
						json.setRecorder(employee2.getName());
					}
				}
				if (null != objs[8] && (!objs[8].equals(""))) {
					Employee employee3 = employeeService.getById(Long
							.parseLong((String) objs[8]));
					if (null != employee3) {
						json.setOperater(employee3.getName());
					}
				}
				json.setRemark((String) objs[9]);
				json.setWeighttypeName((String) objs[10]);
				list1.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list1);
		map.put("total", listtotal.size());
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Weight> getOneWeight(String monkeyid) {
		List<Weight> list = getSession().createQuery(" FROM Weight AS w WHERE w.monkeyid = ?  ORDER BY w.weightdate DESC ")//
				.setParameter(0, monkeyid).list();
		return list;
	}

	public void saveWeight(Weight weight) {
		getSession().save(weight);
		String sql = "UPDATE   Individual     SET  currentweight = :currentweight  WHERE   monkeyid= :monkeyid ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("currentweight", weight.getWeight());
		query.setParameter("monkeyid", weight.getMonkeyid());
		query.executeUpdate();
	}

	public void updateWeight(Weight weight) {
		getSession().update(weight);
		String sql = "UPDATE   Individual     SET  currentweight = :currentweight  WHERE   monkeyid= :monkeyid ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("currentweight", weight.getWeight());
		query.setParameter("monkeyid", weight.getMonkeyid());
		query.executeUpdate();

	}

	public Integer getWeightType(String monkeyid) {
		String sql = "select weighttype from weight where deleted!=1 and monkeyid=:monkeyid";
		Query q = getSession().createSQLQuery(sql);
		if (monkeyid != null && !"".equals(monkeyid)) {
			q.setParameter("monkeyid", monkeyid);
		}
		return (Integer) q.list().get(0);
	}

	public Float getWeight(String monkeyid, Integer type) {
		String sql = "select weight from weight where deleted!=1 and monkeyid=:monkeyid and weighttype=:weighttype";
		Query q = getSession().createSQLQuery(sql);
		if (monkeyid != null && !"".equals(monkeyid) && type != null
				&& !"".equals(type)) {
			q.setParameter("monkeyid", monkeyid);
			q.setParameter("weighttype", type);
		}
		return (Float) q.list().get(0);
	}

	public String getSex(String monkeyid) {
		String sql = "select sex from individual where monkeyid=:monkeyid and deleted!=1";
		Query q = getSession().createSQLQuery(sql);
		if (monkeyid != null && !"".equals(monkeyid)) {
			q.setParameter("monkeyid", monkeyid);
		}
		if (q.list().size() != 0) {
			Byte s = (Byte) q.list().get(0);
			String sex;
			if (s == 1) {
				sex = "女";
			} else {
				sex = "男";
			}
			return sex;
		}
		return null;
	}

	public List<String> getMonkeyid() {
		String sql = "select monkeyid from weight where deleted!=1";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	public List<?> getMore(String monkeyid) {
		String sql = "select room ,lhao from individual where deleted!=1 and monkeyid=:monkeyid";
		Query q = getSession().createSQLQuery(sql);
		if (monkeyid != null && !"".equals(monkeyid)) {
			q.setParameter("monkeyid", monkeyid);
		}
		List<?> list = q.list();
		return list;
	}
	//表名小写.
	public Map<String,Object> getWeight(String page, String rows,String monkeyid,String weightdate){
		String sql="select id," +
				"(select room from individual i where i.monkeyid=w.monkeyid)as roomid," +
				"(select lhao from individual i where i.monkeyid=w.monkeyid)as lhao," +
				"w.monkeyid," +
				"(select sex from individual i where i.monkeyid=w.monkeyid)as sex, " +
				"remark ," +
				"(select name from employee e where e.id=w.protector)as protector," +
				"(select name from employee e where e.id=w.recorder)as recorder," +
				"(select name from employee e where e.id=w.operater)as operater,weightdate,weight " +
				" from weight w where w.deleted!=1 and weighttype=14";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and w.monkeyid=:monkeyid ";
		}
		if(!"".equals(weightdate)&&weightdate!=null){
			sql=sql+" and w.weightdate=:weightdate";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(weightdate)&&weightdate!=null){
			query.setParameter("weightdate", weightdate);
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		
		List<Weight_Json> lj=new ArrayList<Weight_Json>();
		for (Object ob : list) {
			Object[] obj = (Object[]) ob;
			Weight_Json json = new Weight_Json();

			// 设置体重
			String monkeyids = (String) obj[3];
			List<?> listWeight = getTypeAndWeight(monkeyids);
			for (Object obs : listWeight) {
				Object[] objs = (Object[]) obs;
				Float weight = (Float) objs[0];
				Integer weighttype = (Integer) objs[1];

				String weighttypeName = getWeightTypeName(weighttype);
				if (Constant.ORIGINAL_WEIGHT.equals(weighttypeName)) {
					json.setOriginalWeight(weight.toString());
				}
			}
			json.setId((Integer) obj[0]);
			if (!"".equals(obj[1]) && obj[1] != null) {
				String roomname = getAreaName(Long.parseLong(obj[1] + ""));
				json.setRoomid((Long.valueOf(obj[1] + "")));
				json.setRoomname(roomname);
			}
			json.setLhao((String) obj[2]);
			json.setMonkeyid((String) obj[3]);

			json.setSex(obj[4] + "");
			json.setRemark((String) obj[5]);
			json.setProtector((String) obj[6]);
			json.setRecorder((String) obj[7]);
			json.setOperater((String) obj[8]);
			json.setWeightdate((String) obj[9]);
			if (!"".equals(obj[10])&&obj[10]!=null) {
				json.setCurrentWeight(obj[10] + "");
				json.setTijian("√");
			}
			lj.add(json);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", lj);
		map.put("total", listtotal.size());
		return map;
	}
	/**
	 * 根据体重id获得体重类型
	 * 
	 * @param id
	 * @return
	 */
	public String getWeightTypeName(Integer weighttype){
		String sql="select name from publiccode where id=:weighttype ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(weighttype)&&weighttype!=null){
			query.setParameter("weighttype", weighttype);
		}
		String listWeightType=(String)query.list().get(0);
		return listWeightType;
	}
	/**
	 * 根据猴子ID获得体重信息
	 * 
	 * @param id
	 * @return
	 */
	public List<?> getTypeAndWeight(String monkeyid){
		String sql="select weight,weighttype from weight where deleted!=1 and monkeyid=:monkeyid ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listWeight=query.list();
		return listWeight;
	}
	//报表。表名小写.
	public Map<String,Object> getWeight(String monkeyid,String weightdate){
		String sql="select id," +
				"(select room from individual i where i.monkeyid=w.monkeyid)as roomid," +
				"(select lhao from individual i where i.monkeyid=w.monkeyid)as lhao," +
				"w.monkeyid," +
				"(select sex from individual i where i.monkeyid=w.monkeyid)as sex, " +
				"remark ," +
				"(select name from employee e where e.id=w.protector)as protector," +
				"(select name from employee e where e.id=w.recorder)as recorder," +
				"(select name from employee e where e.id=w.operater)as operater,weight  " +
				"from weight w where w.deleted!=1 and weighttype=14 ";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and w.monkeyid=:monkeyid ";
		}
		if(!"".equals(weightdate)&&weightdate!=null){
			sql=sql+" and w.weightdate=:weightdate";
		}
		//sql=sql+" GROUP BY w.monkeyid ";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		if(!"".equals(weightdate)&&weightdate!=null){
			query.setParameter("weightdate", weightdate);
		}
		List<?> listtotal=query.list();
		List<Weight_Json> lj=new ArrayList<Weight_Json>();
		for(Object ob:listtotal){
			Weight_Json json=new Weight_Json();
			Object[] obj=(Object[])ob;
			//设置yuan体重
			String monkeyids=(String)obj[3];
			List<?> listWeight=getTypeAndWeight(monkeyids);
			
			for(Object obs:listWeight){
				Object[] objs=(Object[])obs;
				Float weight=(Float)objs[0];
				Integer weighttype=(Integer)objs[1];

				String weighttypeName=getWeightTypeName(weighttype);
				if(Constant.ORIGINAL_WEIGHT.equals(weighttypeName)){
					json.setOriginalWeight(weight.toString());
				}
				
			}
			json.setId((Integer)obj[0]);
			if (!"".equals(obj[1])&&obj[1]!=null) {
				String roomname=getAreaName(Long.parseLong(obj[1] + ""));
				json.setRoomid((Long.valueOf(obj[1] + "")));
				json.setRoomname(roomname);
			}
			json.setLhao((String)obj[2]==null?"":(String)obj[2]);
			json.setMonkeyid((String)obj[3]);
			if (!"".equals(obj[4])&&obj[4]!=null) {
				//设置性别
				if ((Constant.MONKEY_SEX_MALE).equals(obj[4]+"")) {
					//json.setSex(BigInteger.valueOf(Long.parseLong(Constant.MALE_MONKEY)));
					json.setSex(Constant.MALE_MONKEY);
				}
				if ((Constant.MONKEY_SEX_FEMALE).equals(obj[4]+"")) {
					//json.setSex(BigInteger.valueOf(Long.parseLong(Constant.FEMALE_MONKEY)));
					json.setSex(Constant.FEMALE_MONKEY);
				}
			}else{
				json.setSex("");
			}
			json.setRemark((String)obj[5]==null?"":(String)obj[5]);
			json.setProtector((String)obj[6]==null?"":(String)obj[6]);
			json.setRecorder((String)obj[7]==null?"":(String)obj[7]);
			json.setOperater((String)obj[8]==null?"":(String)obj[8]);
			if (obj[9]!=null&&!"".equals(obj[9])) {
				json.setCurrentWeight(obj[9] + "");
				json.setTijian("√");
			}else{
				json.setCurrentWeight("");
				json.setTijian("/");
			}
			lj.add(json);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", lj);
		map.put("total", listtotal.size());
		return map;
	}
	
	public String getAreaName(Long id){
		String sql="select areaname from area where id=? and deleted!=1";
		Query query=getSession().createSQLQuery(sql);
		List<?> list=query.setParameter(0, id).list();
		if (list.size()>0) {
			return (String) list.get(0);
		}else{
			return null;
		}
	}

	public List<Map<String, String>> getWeightTypeMap(String mark) {
		String sql="select id,name from publiccode where mark=:mark";
		Query query=getSession().createSQLQuery(sql);
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
	
	public List<Weight> getLast4WeightRecord(String monkeyid){
		String sql=" select id,monkeyid,weightdate,weight,weighttype from weight where deleted=0";
		if(!"".equals(monkeyid)&&monkeyid!=null){
			sql=sql+" and monkeyid=:monkeyid";
		}
		sql=sql+" order by createtime desc limit 0,4";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(monkeyid)&&monkeyid!=null){
			query.setParameter("monkeyid", monkeyid);
		}
		List<?> listWeight=query.list();
		List<Weight> list=new ArrayList<Weight>();
		for(Object ob:listWeight){
			Object[] objs=(Object[])ob;
			Weight we=new Weight();
			we.setId((Integer)objs[0]);
			we.setMonkeyid((String)objs[1]);
			we.setWeightdate((String)objs[2]);
			we.setWeight((Float)objs[3]);
			list.add(we);
		}
		return list;
	}

	public List<?> getAllWeightById(String monkeyid) {
		String sql="select id,monkeyid,weight,weightdate,(select name from employee e where e.id=w.boss) as boss, " +
				"(select sex from individual i where i.monkeyid=w.monkeyid) as sex," +
				"(select areaname from area a where a.id=(select blongarea from individual i where i.monkeyid=w.monkeyid)) as blongarea," +
				"(select areaname from area a where a.id=(select room from individual i where i.monkeyid=w.monkeyid)) as room " +
				"from weight w where deleted!=1 and w.monkeyid=:monkeyid and weighttype=14 ";
		
		Query query=getSession().createSQLQuery(sql);
		
		List<?> list=query.setParameter("monkeyid", monkeyid).list();
		return list;
	}

	public List<?> getAllWeightById(String monkeyid, String weightdate) {
		String sql = "select id,monkeyid,weight,weightdate,(select name from employee e where e.id=w.boss) as boss, "
				+ "(select sex from individual i where i.monkeyid=w.monkeyid) as sex,"
				+ "(select areaname from area a where a.id=(select blongarea from individual i where i.monkeyid=w.monkeyid)) as blongarea,"
				+ "(select areaname from area a where a.id=(select room from individual i where i.monkeyid=w.monkeyid)) as room "
				+ "from weight w where deleted!=1 and w.monkeyid=:monkeyid and weighttype=14 and weightdate=:weightdate";

		Query query = getSession().createSQLQuery(sql);

		List<?> list = query.setParameter("monkeyid", monkeyid).setParameter("weightdate", weightdate).list();
		return list;
	}
}
