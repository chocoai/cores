package com.lanen.service.contract;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblRegion;


@Service
public  class TblRegionServiceImpl extends BaseDaoImpl<TblRegion> implements TblRegionService{

	public List<Map<String, String>> getMapListByName(String conditionItem) {
		String sql = "SELECT  tr.id, tr.regionName"+
					" FROM     CoresContract.dbo.tblRigon as tr"+
					" where tr.regionName like "+"'%"+conditionItem+"%'"+
					" order by tr.id";
		List<?> list = getSession().createSQLQuery(sql)
									//.setParameter("regionName", "%"+conditionItem+"%")
									.list();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		if(null != list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				map = new HashMap<String,String>();
				map.put("id", (String)objs[0]);
				map.put("text", (String)objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public List<TblRegion> getAllRegions() {
		String sql = "SELECT distinct tlr2.[id] ,tlr2.[level] ,tlr2.[pid] ,tlr2.[regionName] " +
					"  , (case tlr2.regionName  when '中国' then 'a' else tlr2.regionName end ) as orderby  "+
					" FROM  [CoresContract].[dbo].[tblRegion] as tlr2 "+
					" right join ( SELECT distinct tlr1.[id] ,tlr1.[level] ,tlr1.[pid] ,tlr1.[regionName]  "+
					" FROM  [CoresContract].[dbo].[tblRegion] as tlr1 "+
					" right join ( "+
					" SELECT distinct tlr.[id],[regionName],[pid],[level] "+
					" FROM [CoresContract].[dbo].[tblRegion] as tlr "+
					" right join [CoresContract].[dbo].[tblCustomer] as tlc "+
					" on tlr.id = tlc.regionId where tlc.deleteFlag=0 "+
					" ) as tlrc "+
					" on tlr1.id = tlrc.id or tlrc.pid =tlr1.id )as tlrc1"+
					" on tlr2.id = tlrc1.id or tlrc1.pid =tlr2.id   " +
					" order by orderby   ";
//		
//		String sql = "SELECT tlr2.[id] ,tlr2.[level] ,tlr2.[pid] ,tlr2.[regionName]  "+
//		" FROM  [CoresContract].[dbo].[tblRegion] as tlr2 "+
//		" right join ( SELECT distinct tlr1.[id] ,tlr1.[level] ,tlr1.[pid] ,tlr1.[regionName]  "+
//		" FROM  [CoresContract].[dbo].[tblRegion] as tlr1 "+
//		" right join ( "+
//		" SELECT distinct tlr.[id],[regionName],[pid],[level] "+
//		" FROM [CoresContract].[dbo].[tblRegion] as tlr "+
//		" right join [CoresContract].[dbo].[tblCustomer] as tlc "+
//		" on tlr.id = tlc.regionId where tlc.deleteFlag=0 "+
//		" ) as tlrc "+
//		" on tlr1.id = tlrc.id or tlrc.pid =tlr1.id )as tlrc1"+
//		" on tlr2.id = tlrc1.id or tlrc1.pid =tlr2.id   ORDER BY ( case when tlr2.[pid]  is  null or tlr2.[pid]  = ''  then tlr2.[id] else tlr2.[regionName] end )  ";
		
		List<?> list = getSession().createSQLQuery(sql).list();
		List<TblRegion> rList = new ArrayList<TblRegion>();
		if(null != list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				TblRegion region = new TblRegion();
				region.setId((String)objs[0]);
				region.setLevel((Integer)objs[1]);
				if(null != objs[2]){
					region.setPid((String)objs[2]);
				}
				region.setRegionName((String)objs[3]);
				rList.add(region);
			}
		}
		return rList;
	}

	@SuppressWarnings("unchecked")
	public List<TblRegion> getByRegLevel(int level ,String pid ) {
		List<TblRegion> list = null;
		if( pid.equals("-1")){
			list = getSession().createQuery("FROM TblRegion t WHERE t.level = :level ").setParameter("level", level).list();
		}else{
			list = getSession().createQuery("FROM TblRegion t WHERE t.level = :level and t.pid = :pid ")
			.setParameter("level", level).setParameter("pid", pid).list();
		}

		return list;
	}

	public List<TblRegion> getByHaveCutRegLevel(int level, String pid) {
		//有父级
		String sql1 = " select distinct tblr2.[id],tblr2.[regionName] from  "+
       " (SELECT  distinct  tblr.[id],tblr1.[id] as pid "+
       "  ,tblr.[regionName] FROM [CoresContract].[dbo].[tblRegion] as tblr left join [CoresContract].[dbo].[tblRegion] as tblr1 "+
	   "  on  tblr.[id] = tblr1.[id] or tblr.[id] = tblr1.[pid] where tblr.[pid]= :pid and tblr.[level] = :level) as tblr2 "+
	   "  right join [CoresContract].[dbo].tblCustomer as tblc "+
	   "  on tblr2.[id] = tblc.regionId or tblr2.pid = tblc.regionId "+
	   "  where tblr2.id is not null  ";
		//国家无父级
		String sql2 = " select distinct tblr2.[id],tblr2.[regionName] from  "+
	       " (SELECT  distinct  tblr.[id],tblr1.[id] as pid "+
	       "  ,tblr.[regionName] FROM [CoresContract].[dbo].[tblRegion] as tblr left join [CoresContract].[dbo].[tblRegion] as tblr1 "+
		   "  on  tblr.[id] = tblr1.[id] or tblr.[id] = tblr1.[pid] where tblr.[level] = :level) as tblr2 "+
		   "  right join [CoresContract].[dbo].tblCustomer as tblc "+
		   "  on tblr2.[id] = tblc.regionId or tblr2.pid = tblc.regionId "+
		   "  where tblr2.id is not null  ";
		List<?> list;
		if( pid.equals("-1")){
		   list = getSession().createSQLQuery(sql2).setParameter("level", level).list();
		 }else{
		   list = getSession().createSQLQuery(sql1).setParameter("level", level).setParameter("pid", pid).list(); 
		 }
		List<TblRegion> rList = new ArrayList<TblRegion>();
		if(null != list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				TblRegion region = new TblRegion();
				region.setId((String)objs[0]);
				region.setRegionName((String)objs[1]);
				rList.add(region);
			}
		}
		return rList;
	}

	public String getFullNameByregionId(String regionId) {
		String fullRegionName = "";
		if(null != regionId){
			TblRegion tblRegion = getById(regionId);
			if(null != tblRegion){
				fullRegionName = tblRegion.getRegionName();
				//市
				if(tblRegion.getLevel()>2){
					tblRegion = getById(tblRegion.getPid());
					if(null != tblRegion){//省
						fullRegionName = tblRegion.getRegionName()+"#"+fullRegionName;
					}
				}
			}
		}
		return fullRegionName;
	}



}
