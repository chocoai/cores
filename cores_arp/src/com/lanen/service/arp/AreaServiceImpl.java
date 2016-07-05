package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Area;
import com.lanen.model.AreaJson;
import com.lanen.model.Employee;

@Service
public class AreaServiceImpl  extends BaseLongDaoImpl<Area> implements AreaService{
     @Resource
     private EmployeeService employeeService;
     //小写.
	public List<?> getAllAreaList() {
		String sql="select id,areaname,blongarea" +
				   ",(select name from animaltype where id=a.animaltype) as animaltype" +
				   ",roompinxi" +
				   ",(select name from employee where id=a.keeper ) as keeper" +
				   ",(select name from employee where id=a.boss ) as boss" +
				   ",remarks,deleted" +
				   ",(select name from employee where id=a.reader ) as reader" +
				   ",(select name from employee where id=a.veterinarian ) as veterinarian" +
				   " FROM area as a where deleted != 1";
//		String hql="FROM Area where deleted != 1 ORDER BY deleted";
		List<?> sqlList=getSession().createSQLQuery(sql).list();
//		List<Area> list=getSession().createQuery(hql).list();
		return sqlList;
	}

	//linux服务器上Mysql区分大小写.
	public List<Map<String, Object>> getAllPareaIdName() {
		String sql="SELECT id,areaname FROM area where deleted != 1 and blongarea is  null ";
		List<?> listSql=getSession().createSQLQuery(sql).list();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, Object> map=new HashMap<String, Object>();
				Object[] objs=(Object[])obj;
				map.put("id", (Integer)objs[0]);
				map.put("text",(String)objs[1] );
				list.add(map);
			}
		}
		return list;
	}

	public boolean isExistName(String areaname) {
		if(null != areaname){
			List<?> list = getSession().createQuery(" From Area where areaname = :areaname and deleted!=1 ")
						.setParameter("areaname", areaname)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	//小写.
	public List<Map<String, Object>> getAllRoomIdName(Long blongArea) {
		String sql="SELECT id,areaname FROM area where deleted != 1 and blongarea=:blongArea";
		List<?> listSql=getSession().createSQLQuery(sql).setParameter("blongArea", blongArea).list();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, Object> map=new HashMap<String, Object>();
				Object[] objs=(Object[])obj;
				map.put("id", (Integer)objs[0]);
				map.put("text",(String)objs[1] );
				list.add(map);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Area getAreaByName(String areaname) {
		Area area=new Area();
		String hql="from Area where deleted!=1 and areaname=:areaname";
		List<Area> list=getSession().createQuery(hql).setParameter("areaname", areaname).list();
		if(list!=null&&list.size()>0){
			for(Area a:list){
				area=a;
			}
		}
     	return area;
	}
	//mysql表名小写.
	public List<Long> getAllKeeper() {
		//String sql="SELECT DISTINCT keeper FROM area WHERE keeper IS NOT null and deleted!=1";
		//动物所属饲养员，并非房舍所属饲养员.
		String sql="select distinct keeper from individual where keeper is not null and deleted!=1";
		List<?> list=getSession().createSQLQuery(sql).list();
		List<Long> keeper=new ArrayList<Long>();
		if(list!=null){
			for(Object obj:list){
				Integer keep=(Integer)obj;
				Long keepLong=Long.parseLong(keep+"");
				keeper.add(keepLong);
			}
		}
		return keeper;
	}

	@SuppressWarnings("unchecked")
	public List<Area> getIdsByKeeper(Long keeper) {
		//String hql="from  Area WHERE keeper=:keeper and deleted!=1";
		String sql="select id,areaname from area where deleted!=1";
		if(!"".equals(keeper)&&keeper!=null){
			sql=sql+" and id in(select room from individual where keeper=:keeper and deleted!=1)";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		List<?> list=query.list();
		List<Area> l=new ArrayList<Area>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Area area=new Area();
			area.setId(Long.parseLong(objs[0]+""));
			l.add(area);
		}
		return l;
	}
	//小写.
	public AreaJson getAreanameAndMonkeyCount(Long id) {
		String sql="SELECT a.areaname,"+ 
                   " (SELECT COUNT(id) FROM individual i1  WHERE deleted != 1 AND agetype = 3 AND sex = 0 AND a.id=i1.room) AS c1 ,"+
		           " (SELECT COUNT(id) FROM individual i2  WHERE deleted != 1 AND agetype = 3 AND sex = 1 AND a.id=i2.room) AS c2 ,"+
		           " (SELECT COUNT(id) FROM individual i3  WHERE deleted != 1 AND agetype = 2 AND a.id=i3.room) AS c3  ,"+
		           " (SELECT COUNT(id) FROM individual i4  WHERE deleted != 1 AND agetype = 1 AND a.id=i4.room) AS c4 "+
		           " FROM area a WHERE a.id=:id ";
		List<?> sqlList=getSession().createSQLQuery(sql).setParameter("id", id).list();
		AreaJson json=new AreaJson();
		if(sqlList!=null){
			for(Object obj:sqlList){
				Object[] objs=(Object[])obj;
				json.setAreaname((String)objs[0]);
				json.setMaleMonkeyCount(((BigInteger)objs[1]).intValue());
				json.setFemaleMonkeyCount(((BigInteger)objs[2]).intValue());
				json.setYuchengMonkeyCount(((BigInteger)objs[3]).intValue());
				json.setCubMonkeyCount(((BigInteger)objs[4]).intValue());
				json.setTotalCount(((BigInteger)objs[1]).intValue()+((BigInteger)objs[2]).intValue()+((BigInteger)objs[3]).intValue()+((BigInteger)objs[4]).intValue());
			}
		}
		return json;
	}

	public ArrayList<Object> getExcelFiledDataList(Long keeper) {
		List<Area> list=getIdsByKeeper(keeper);
		ArrayList<Object> filedData = new ArrayList<Object>();
		if(list!=null){
			int i=1;
			for(Area a:list){
				AreaJson json=getAreanameAndMonkeyCount(a.getId());
				ArrayList<Object> dataList = new ArrayList<Object>();
				dataList.add(i);
				i++;
				dataList.add(json.getAreaname());
				dataList.add(json.getMaleMonkeyCount());
				dataList.add("");
				dataList.add(json.getFemaleMonkeyCount());
				dataList.add("");
				dataList.add(json.getYuchengMonkeyCount());
				dataList.add("");
				dataList.add(json.getCubMonkeyCount());
				dataList.add("");
				dataList.add(json.getTotalCount());
				dataList.add("");
				dataList.add(getEmpName(keeper));
				filedData.add(dataList);
			}
		}
		return filedData;
	}

	public ArrayList<Object> getExcelFiledNameList() {
		String [] titles = {"序号","房间名","应盘公猴数量","实盘公猴数量","应盘母猴数量","实盘母猴数量","应盘育成猴数量","实盘育成猴数量","应盘仔猴数量","实盘仔猴数量","应盘合计","实盘合计","饲养员"};
		ArrayList<Object> filedName = new ArrayList<Object>();
		for(int i=0;i<titles.length;i++){
			String title = titles[i];
			filedName.add(title);
		}
		return filedName;
	}
	/**根据员工ID获得员工姓名
	 * @param id
	 * @return
	 */
	public  String getEmpName(Long id){
		String name=null;
		if(id!=null){
			Employee e=employeeService.getById(id);
			if(e!=null){
				name=e.getName();
			}
		}
		return name;
	}

	@SuppressWarnings("unchecked")
	public List<Area> getListByBlongId(Long blongarea) {
		String  hql="from Area where deleted!=1 and blongarea=:blongarea";
		List<Area> list=getSession().createQuery(hql).setParameter("blongarea", blongarea).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Area> getAllArea() {
		String hql="FROM Area where deleted != 1 ORDER BY deleted";
		List<Area> list=getSession().createQuery(hql).list();
		return list;
	}

	public List<Map<String, String>> getTreatRoom(String name) {
		String sql="SELECT ID,areaNAME FROM area WHERE deleted!=-1 and blongarea=(select id from area where deleted=0 and areaname=:areaname)";
		Query query = getSession().createSQLQuery(sql);
		if(!"".equals(name)&&name!=null){
			query.setParameter("areaname", name);
		}
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		map = new HashMap<String, String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		mapList.add(map);
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}

	public Map<String,Object> getRoomByKeeper(String rows,String page,Long keeper,Long area) {
		String hql="select id,areaname from  area where deleted!=1 and blongarea is not null";
		
		if(!"".equals(keeper) && keeper !=null){
			hql=hql+" and id in(select distinct room from individual i where i.keeper=:keeper)";
		}
		
		if(!"".equals(area) && area !=null){
			hql=hql+" and blongarea=:area";
		}
		Query query=getSession().createSQLQuery(hql);
		if(!"".equals(keeper) && keeper !=null){
			query.setParameter("keeper", keeper);
		}
		if(!"".equals(area) && area !=null){
			query.setParameter("area", area);
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Object[] objs=(Object[])ob;
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("id", objs[0]);
			map1.put("areaname", objs[1]);
			lm.add(map1);
		}
		map.put("rows", lm);
		map.put("total", listtotal.size());
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<AreaJson> getRoomByKeeperAndArea(Long keeper,Long area ) {
		String hql="select id,areaname,blongarea,(select areaname from area a1 where a1.id=a.blongarea)as blongarea2 from  area a where  deleted!=1 and blongarea is not null";
		if(!"".equals(keeper) && keeper !=null){
			hql=hql+" and id in(select distinct room from individual i where i.keeper=:keeper)";
		}
		if(!"".equals(area) && area !=null){
			hql=hql+" and blongarea=:area";
		}
		Query query=getSession().createSQLQuery(hql);
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		if(!"".equals(area) && area !=null){
			query.setParameter("area", area);
		}
		List<?> list=query.list();
		List<AreaJson> l=new ArrayList<AreaJson>();
		for(Object ob:list){
			Object[] objs=(Object[])ob;
			AreaJson a=new AreaJson();
			a.setId(Long.parseLong(objs[0]+""));
			a.setAreaname((String)objs[1]);
			a.setBlongarea(Long.parseLong(objs[2]+""));
			a.setBlongareaName((String)objs[3]);
			l.add(a);
		}
		return l;
	}
	//在场动物
	public List<?> getAnimal(Long room,Long keeper){
		List<Map<String,List<?>>> lists=new ArrayList<Map<String,List<?>>>();
		String sql="select monkeyid from individual where deleted!=1 and status=1 ";
		if(!"".equals(room)&&room!=null){
			sql=sql+" and room=:room";
		}
		if(!"".equals(keeper)&&keeper!=null){
			sql=sql+" and keeper=:keeper";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", room);
		}
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		List<?> list= query.list();
		return list;
	}
	public List<?> getAnimal(Long room){
		String sql="select monkeyid from individual where deleted!=1 and status=1 ";
		if(!"".equals(room)&&room!=null){
			sql=sql+" and room=:room";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", room);
		}
		List<?> list= query.list();
		return list;
	}
	public List<Map<String, String>> getRoom() {
		String sql="SELECT id,areaname FROM AREA where deleted != 1 and blongarea is not null ";
		List<?> listSql=getSession().createSQLQuery(sql).list();
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, String> map=new HashMap<String, String>();
				Object[] objs=(Object[])obj;
				map.put("id", objs[0]+"");
				map.put("text",(String)objs[1] );
				list.add(map);
			}
		}
		return list;
	}
	
	public Map<String,Object> getAllRoomName(String rows,String page){
		String sql="From Area where deleted!=1 and blongarea is not null order by id desc";
		Query query=getSession().createQuery(sql);
		List<Area> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// 第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页多少行
		List<Area> lists = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		
		List<AreaJson> list=new ArrayList<AreaJson>();
		for(Area area:lists){
			//Map map=new HashMap<String,Object>();
			Long areaid=area.getId();
			//由id得到房间种类猴子数量.
			AreaJson ajson=getAreanameAndMonkeyCount(areaid);
			//由id得到房间名称.
			String roomName=ajson.getAreaname();
			if (roomName.contains("-")) {
				//幢
				String dong = roomName.split("-")[0];
				String room = roomName.split("-")[1];
				ajson.setDong(dong);
				ajson.setRoom(room);
			}else{
				ajson.setRoom(roomName);
			}
			/*String roomName=ajson.getAreaname();
			map.put("roomName", roomName);
			map.put("maleCount", ajson.getMaleMonkeyCount());
			map.put("femaleCount", ajson.getFemaleMonkeyCount());
			map.put("yuchengCount", ajson.getYuchengMonkeyCount());
			map.put("cubCount", ajson.getCubMonkeyCount());*/
			list.add(ajson);
		}
		Map map=new HashMap<String,Object>();
		map.put("rows", list);
		map.put("total", listtotal.size());
		//return (List<Area>)getSession().createSQLQuery(sql).list();
		return map;
	}
	public List<AreaJson> getAllRoomName(){
		String sql="From Area where deleted!=1 and blongarea is not null";
		Query query=getSession().createQuery(sql);
		List<Area> listtotal=query.list();
		
		List<AreaJson> list=new ArrayList<AreaJson>();
		for(Area area:listtotal){
			Long areaid=area.getId();
			//由id得到房间种类猴子数量.
			AreaJson ajson=getAreanameAndMonkeyCount(areaid);
			String roomName=ajson.getAreaname();
			if (roomName.contains("-")) {
				//幢
				String dong = roomName.split("-")[0];
				String room = roomName.split("-")[1];
				ajson.setDong(dong);
				ajson.setRoom(room);
				
			}else{
				ajson.setDong("");
				ajson.setRoom(roomName);
			}
			list.add(ajson);
		}
		return list;
	}
	public Map<String,Object> getAnimalByArea(String rows,String page,String areaid){
		//排除出场猴子status=2出场(待销售)
		//根据房间名查询from individual i ,area a where i.deleted!=1 and status=1  and i.blongarea=1 and a.areaname='A-102' and a.id=i.room group by room
		if(areaid!=null&&!"".equals(areaid)){
			String sql="select ROOM , " +
					"count(case when Sex=0 and agetype=3 then 1 end) as malemonkey,"+
					"count(case when Sex=1 and agetype=3 then 1 end) as femalemonkey,"+ 
					"count(case when  agetype=2 then 1 end) as yuchengmonkey,"+
					"count(case when agetype=1 then 1 end) as cubmonkey"+
					" from individual i where i.deleted!=1 and status=1 ";
			if(!"".equals(areaid) && areaid !=null){
				sql=sql+" and i.blongarea=:areaid ";
			}
			
			sql=sql+" group by room";
			Query query=getSession().createSQLQuery(sql);
			
			if(!"".equals(areaid) && areaid !=null){
				query.setParameter("areaid", areaid);
			}
			
			List<?> listtotal=query.list();
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);// 第几页
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
					: rows);// 每页多少行
			List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
			
			Map<String,Object> map=new HashMap<String,Object>();
			List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
			for(Object ob:list){
				Object[]objs=(Object[])ob;
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("roomid", objs[0]);
				m.put("malemonkey", objs[1]);
				m.put("femalemonkey", objs[2]);
				m.put("yuchengmonkey", objs[3]);
				m.put("cubmonkey", objs[4]);
				lm.add(m);
			}
			map.put("rows", lm);
			map.put("total", listtotal.size());
			return map;
		}
		return null;
		
	}
	/**
	 * 饲养数量报表
	 */
	public List<Map<String,Object>> getAnimalByArea(String areaid){
		//排除出场猴子status=2出场
		if(areaid!=null&&!"".equals(areaid)){
			String sql="select ROOM , " +
					"count(case when Sex=0 and agetype=3 then 1 end) as malemonkey,"+
					"count(case when Sex=1 and agetype=3 then 1 end) as femalemonkey,"+ 
					"count(case when  agetype=2 then 1 end) as yuchengmonkey,"+
					"count(case when agetype=1 then 1 end) as cubmonkey"+
					" from individual where deleted!=1 and status=1 ";
			if(!"".equals(areaid) && areaid !=null){
				sql=sql+" and blongarea=:areaid ";
			}
			sql=sql+" group by room";
			Query query=getSession().createSQLQuery(sql);
			
			if(!"".equals(areaid) && areaid !=null){
				query.setParameter("areaid", areaid);
			}
			List<?> listtotal=query.list();
			Map<String,Object> map=new HashMap<String,Object>();
			List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
			for(Object ob:listtotal){
				Object[]objs=(Object[])ob;
				Map<String,Object> m=new HashMap<String,Object>();
				m.put("roomid", objs[0]);
				m.put("malemonkey", objs[1]);
				m.put("femalemonkey", objs[2]);
				m.put("yuchengmonkey", objs[3]);
				m.put("cubmonkey", objs[4]);
				lm.add(m);
			}
			return lm;
		}
		return null;
		
	}
	//再根据room查询room信息
	public List<?> getAnimalRoomByArea(String roomid){
		String sql="select areaname,(select name from animaltype at where at.id=a.animaltype)as animaltype, " +
				"(select name from employee e where e.id=a.keeper)as keeper," +
				"(select name from employee e where e.id=a.veterinarian)as veterinarian," +
				"(select name from employee e where e.id=a.boss)as boss " +
				" from area a ";
		if(!"".equals(roomid)&&roomid!=null){
			sql=sql+" where a.id=:roomid ";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(roomid)&&roomid!=null){
			query.setParameter("roomid", roomid);
		}
		return (List<?>)query.list();
		
	}

	public List<Area> getAreas() {
		String hql="From Area where deleted!=1 and blongarea is not null";
		return getSession().createQuery(hql).list();
	}
	public List<?> getAnimalByKeeper(Long keeper){
		String sql="select monkeyid from individual where deleted=0 ";
		
		if(!"".equals(keeper)&&keeper!=null){
			sql=sql+" and keeper=:keeper";
		}
		Query query=getSession().createSQLQuery(sql);
		
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		List<?> list= query.list();
		return list;
	}
	
	public List<?> getInAnimal(Long room){
		String sql="select monkeyid from individual where deleted=0 and status=1 ";
		if(!"".equals(room)&&room!=null){
			sql=sql+" and room=:room";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", room);
		}
		List<?> list= query.list();
		return list;
	}
	public List<?> getOutAnimal(Long room){
		String sql="select monkeyid from individual where deleted=0 and status=2 ";
		if(!"".equals(room)&&room!=null){
			sql=sql+" and room=:room";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", room);
		}
		List<?> list= query.list();
		return list;
	}
	public List<?> getInAnimalByKeeper(Long keeper){
		String sql="select monkeyid from individual where deleted=0 and status=1 ";
		
		if(!"".equals(keeper)&&keeper!=null){
			sql=sql+" and keeper=:keeper";
		}
		Query query=getSession().createSQLQuery(sql);
		
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		List<?> list= query.list();
		return list;
	}
	public List<?> getOutAnimalByKeeper(Long keeper){
		String sql="select monkeyid from individual where deleted=0 and status=2 ";
		
		if(!"".equals(keeper)&&keeper!=null){
			sql=sql+" and keeper=:keeper";
		}
		Query query=getSession().createSQLQuery(sql);
		
		if(!"".equals(keeper)&&keeper!=null){
			query.setParameter("keeper", keeper);
		}
		List<?> list= query.list();
		return list;
	}
	public List<?> getAnimalByArea(Long room){
		String sql="select monkeyid from individual where deleted=0 ";
		if(!"".equals(room)&&room!=null){
			sql=sql+" and room=:room";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(room)&&room!=null){
			query.setParameter("room", room);
		}
		List<?> list= query.list();
		return list;
	}
}
