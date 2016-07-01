package com.lanen.service.contract;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblCustomer_Json;
import com.lanen.model.studyplan.DictTestItemType;

/**
 * 客户信息表service实现
 * @author 黄国刚
 *
 */
@Service
public class TblCustomerServiceImpl extends BaseDaoImpl<TblCustomer> implements TblCustomerService{
      @Resource
      TblRegionService tblRegionService;
      
	public List<DictTestItemType> getAllDictTestItemTypes() {
		String sql = "SELECT  tiCode, tiType FROM   [CoresSystemSet].[dbo].[dictTestItemType] ";
		List<?> list = getSession().createSQLQuery(sql).list();
		List<DictTestItemType> tList = new ArrayList<DictTestItemType>();
		if(null != list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				DictTestItemType itemType = new DictTestItemType();
				itemType.setTiCode((String)objs[0]);
				itemType.setTiType((String)objs[1]);
				tList.add(itemType);
			}
		}
		return tList;
	}

	@SuppressWarnings("unchecked")
	public List<TblCustomer_Json> getByRegionIdCustomerList(String regionId,boolean readAll,String reader) {
		//String sql= "  SELECT distinct tblc.*,(case when " ;
		//if(readAll){
		//	sql = sql + " ((tblct.operator is not null or tblct.operator != '') and tblct.contractState != 0)   " ;
		//}else {
		//	sql = sql + " ( tblct.operator = :operator)   " ;
		//}
				
		
		//sql = sql + "  then 0 else 1 end ) as viewMark FROM ( "+
		//  " SELECT distinct t.sponsorId,c.* , "+
		//  " 		(case when t.sponsorId = c.id then 0 else 1 end ) as contractMark "+
		//  "  FROM (SELECT [id] ,[customerName] ,[customerType] ,[regionId] ,[address],[linkman]  "+
		//  "      ,[tel] ,[mobile],[email],[http],[fax] ,[postalCode],[deleteFlag],[tiCode]  "+
		//  "  FROM [CoresContract].[dbo].[tblCustomer] where regionId in (select r.id1 from (SELECT  r1.[id] id1 ,r2.[id] id2 ,r3.[id] id3  "+
		//  "   FROM [CoresContract].[dbo].[tblRegion] r1 left join [CoresContract].[dbo].[tblRegion] r2 on  "+
		//  "   r1.id=r2.pid left join [CoresContract].[dbo].[tblRegion] r3 on r2.id=r3.pid where r1.id=:regionId) r ) and deleteFlag=0 or  "+
		//  "   regionId in (select r.id2 from (SELECT  r1.[id] id1,r2.[id] id2 ,r3.[id] id3  "+
		//  "  FROM [CoresContract].[dbo].[tblRegion] r1 left join [CoresContract].[dbo].[tblRegion] r2 on  "+
		//  "   r1.id=r2.pid left join [CoresContract].[dbo].[tblRegion] r3 on r2.id=r3.pid where r1.id=:regionId) r) and deleteFlag=0 or  "+
		//  "  regionId in (select r.id3 from (SELECT  r1.[id] id1,r2.[id] id2 ,r3.[id] id3  "+
		//  "   FROM [CoresContract].[dbo].[tblRegion] r1 left join [CoresContract].[dbo].[tblRegion] r2 on  "+
		//  "   r1.id=r2.pid left join [CoresContract].[dbo].[tblRegion] r3 on r2.id=r3.pid where r1.id=:regionId) r) and deleteFlag=0 ) c  "+
		//  "   left join [CoresContract].[dbo].[tblContract] as t on  t.sponsorId = c.id) as tblc left join CoresContract.dbo.tblContract tblct "+
		//  "   ON tblc.id = tblct.sponsorId ";
	    String sql  = "   select  distinct tblcm.topRegionName, tblcm.tpRegionName, tblcm.regionName , tblcm.cid , tblcm.customerName, "
	        + "  tblcm.address, tblcm.linkman, tblcm.tel, tblcm.mobile, tblcm.email, tblcm.fax, tblcm.postalCode, tblcm.tiCode," 
	        + " (case when (tblco.id is not null or tblco.id != '') then 0 else 1 end) as contractMark, ";
	    if(readAll){
				sql = sql + " (case when ((tblco.operator is not null or tblco.operator != '') and tblco.contractState != 0)  then 0 else 1 end ) as viewMark " ;
		}else {
				sql = sql + " (case when tblco.operator = :operator  then 0 else 1 end ) as viewMark  " ;
		}
        sql = sql + " from ( "
       // + "  select  tblr.tpid ,tblr.tpRegionName,tblr.regionName ,tblc.id as cid ,tblc.customerName, "
       // + "  tblc.address,tblc.linkman,tblc.tel,tblc.mobile,tblc.email,tblc.fax,tblc.postalCode,tblc.tiCode "
       // + "  	from ( "
       // + "           select distinct tlr2.id as tpid,tlr2.regionName as tpRegionName,tlr3.id,tlr3.regionName "
       // + "    from CoresContract.dbo.tblRegion as tlr1 "
       // + "    left join  CoresContract.dbo.tblRegion as tlr2  "
//        + "      on (tlr1.level = 1 and tlr1.id = tlr2.pid  ) "
//        + "      or (tlr1.level = 2 and tlr1.id = tlr2.pid  )  "
//        + "      or (tlr1.level = 3 and tlr1.id = tlr2.id   )  "
//        + "      or (tlr1.id = tlr2.id) "
//        + "      join CoresContract.dbo.tblRegion as tlr3 "
//        + "      on ( tlr1.level = 1 and tlr2.level = 2 and  (tlr2.id = tlr3.pid or tlr2.id = tlr3.id ) )  "
//        + "      or ( tlr1.level = 2 and tlr2.level = 1 and  tlr2.id = tlr3.id  ) "
//        + "      or ( tlr1.level = 2 and tlr2.level = 3 and  tlr2.id = tlr3.id  ) "
//        + "      or ( tlr1.level = 3 and  tlr2.id = tlr3.id  ) "
//        + "      or ( tlr1.level = tlr2.level  and  tlr2.id = tlr3.id )  "
//        + "      where tlr1.id = :regionId ) as tblr  "
//        + "    left join CoresContract.dbo.tblCustomer as tblc "
//        + "   on tblc.regionId =  tblr.id where ( tblc.id is not null or tblc.id != '' ) " 
        +"  select a.id1,a.id2,a.id3,a.a as topRegionName,a.b as tpRegionName ,a.c as regionName,b.id cid ,b.customerName,b.address,b.linkman,b.tel, " 
        +" b.mobile,b.email,b.fax,b.postalCode,b.tiCode from " 
        +"   (select c.level,b.* from [CoresContract].[dbo].[tblCustomer] as b  "
        +" left join tblRegion as c  "
        +" on b.regionId=c.id) as b  "
        +" left join   "
        +" 	 (  "
        //市
		+" 		 select  3 level,a1.id id1,a2.id id2 ,a3.id id3,a1.regionName a,a2.regionName b ,a3.regionName c from   "
        +" 		 (select * from CoresContract.dbo.tblRegion where level=1) as a1  "
        +" 		 left join  "
        +" 		 (select * from CoresContract.dbo.tblRegion where level=2) as a2  "
        +" 		   on a1.id=a2.pid  "
        +" 		 left join  "
        +" 		 (select * from CoresContract.dbo.tblRegion where level=3) as a3  "
        +" 	   on a2.id=a3.pid  "
		//国家
		+" 		 union   "
        +" 		 (select 1 level, id id1,null id2,null id3 ,regionName a,null b,null c from CoresContract.dbo.tblRegion where level=1)  "
		//省
        +" 		 union   "
        +" 		 select 2 level, b1.id id1,b2.id id2,null id3,b1.regionName a,b2.regionName b,null c from   "
        +" 		 (select * from CoresContract.dbo.tblRegion where level=1) as b1  "
        +" 		 left join   "
        +" 		 (select * from CoresContract.dbo.tblRegion where level=2) as b2  "
        +" 		 on b1.id=b2.pid  "
        +" 	) as a  "
        +" on b.level=a.level and ((b.level=1 and b.regionId =a.id1) or (b.level=2 and b.regionId =a.id2) or (b.level=3 and b.regionId =a.id3))  "
        +" where id1=:regionId  or id2=:regionId  or id3=:regionId  "
        + "  )as tblcm "
        + "	 left join  CoresContract.dbo.tblContract  as tblco "
        + "	on tblcm.cid = tblco.sponsorId order by tblcm.tpRegionName,tblcm.regionName  ,tblcm.customerName ";
		List<TblCustomer_Json> list = new ArrayList<TblCustomer_Json>();
		Query query = getSession().createSQLQuery(sql).setParameter("regionId", regionId);
		if(readAll){
		}else {
			query.setParameter("operator", reader);
		}
		query.setResultTransformer(new MapResultTransformer());
		List<Map<String,Object>> listSql = query.list();
		for(Map<String,Object> map :listSql){
			TblCustomer_Json t=new TblCustomer_Json();
			t.setId((String)map.get("cid"));
			//String tpRegionName = (String)map.get("tpRegionName");
			if(null != map.get("tpRegionName") ){
				t.setRegion((String)map.get("tpRegionName"));
			}else{
				t.setRegion((String)map.get("topRegionName"));
			}
			
			//String regionName = (String)map.get("regionName");
			if(null != map.get("regionName")){
				t.setRegion1((String)map.get("regionName"));
			}
			
			t.setCustomerName((String)map.get("customerName"));
			t.setLinkman((String)map.get("linkman"));
			t.setAddress((String)map.get("address"));
			t.setTel((String)map.get("tel"));
			t.setMobile((String)map.get("mobile"));
			t.setEmail((String)map.get("email"));
			t.setFax((String)map.get("fax"));
			t.setPostalCode((String)map.get("postalCode"));
			t.setTiCode((String)map.get("tiCode"));
			if(null != map.get("viewMark")){
				t.setViewMark((Integer)map.get("viewMark"));
			}
			if(null != map.get("contractMark")){
			   t.setContractMark((Integer)map.get("contractMark"));
			}
    		list.add(t);
    	}
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByCustomerName(String name) {
		List<TblCustomer> list=getSession().createQuery("FROM TblCustomer t WHERE t.customerName = :customerName  and t.deleteFlag != 1")//
		.setParameter("customerName", name)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public boolean isExistOtherCustomerName(String name,String id) {
		if(null != name && null!= name){
			List<?> list = getSession().createQuery("FROM TblCustomer t WHERE t.customerName = :customerName  and t.id != :id")
						.setParameter("customerName",  name)
						.setParameter("id", id)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public List<TblCustomer_Json> getByCustomerNameCustomerList(String name) {
//		private String id;
//		private String customerName;		// 客户名称
//		private int customerType;			// 客户类型  1 厂家 2委托方
//		private String regionId;			// 地区ID   (地区表主键)
//		private String address;				// 地址
//		private String linkman;				// 联系人
//		private String tel;					// 电话
//		private String mobile;				// 手机
//		private String email;				// 电邮
//		private String http;				// 网址
//		private String fax;					// 传真
//		private String postalCode;			// 邮政编码
//		private int deleteFlag;				// 删除标记        1:已删除     0：正常（默认）
//		private String tiCode;				// 主要产品类型
		String sql = " SELECT  tr.id, tr.customerName,  tr.customerType, tr.regionId, tr.address, tr.linkman, " +
				" tr.tel ,tr.mobile, tr.email, tr.http, tr.fax, tr.postalCode, tr.deleteFlag,tr.tiCode "+
		" FROM     tblCustomer as tr "+
		" where tr.customerName like "+"'%"+name+"%'  and tr.deleteFlag != 1 ";
		List<?> list = getSession().createSQLQuery(sql)
								//.setParameter("regionName", "%"+conditionItem+"%")
								.list();
		List<TblCustomer_Json> clist = new ArrayList<TblCustomer_Json>();
		if(null != list){
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			String address="";
			String regionId1=(String)objs[3];
			address=tblRegionService.getFullNameByregionId(regionId1)+","+(String)objs[4];
			TblCustomer_Json t=new TblCustomer_Json();
			
			
			t.setId((String)objs[0]);
			t.setCustomerName((String)objs[1]);
			t.setCustomerType((Integer)objs[2]);
			t.setAddress(address);
			t.setLinkman((String)objs[5]);
			t.setTel((String)objs[6]);
			t.setMobile((String)objs[7]);
			t.setEmail((String)objs[8]);
			t.setHttp((String)objs[9]);
			t.setFax((String)objs[10]);
			t.setPostalCode((String)objs[11]);
			t.setDeleteFlag((Integer)objs[12]);
			t.setTiCode((String)objs[13]);
			clist.add(t);
			
			
		}
		}
		return clist;
	}

	
   //综合查询中根据条件查询委托单位具体信息
	public List<TblCustomer_Json> getCustomerByCondition(String type,Date start,Date end,String name,boolean readAll,String reader) {

		String sql = "select customer.id,customer.customerName, customer.regionId,customer.address,customer.linkman,"+
					" customer.tel,customer.mobile,customer.email,customer.http,customer.fax,customer.postalCode,"+
					" contract2.conts,contract2.cts,contract2.cis"+
					" from   [CoresContract].[dbo].[tblCustomer] as customer join "+
					" ( "+
					" select count(cid) as conts,sum(contract1.Tis) as cts,sum(contract1.Sis) as cis,  contract1.sponsorId"+
					" from"+
					" ("+
					"  SELECT  testItem.countTi as Tis,studyItem.countSi as Sis,"+
					"  contract.[id] as cid,contract.[contractCode],contract.[sponsorId],contract.[signingDate],contract.contractState"+
					" FROM [CoresContract].[dbo].[tblContract] as contract  left join "+
					" (select count(contractCode) as countTi,contractCode  from   [CoresContract].[dbo].[tblTestItem] group by contractCode) "+
					" as testItem on contract.[contractCode]=testItem.contractCode left join "+
					" (select count(contractCode) as countSi,contractCode  from   [CoresContract].[dbo].[tblStudyItem] group by contractCode) "+
					" as studyItem on contract.[contractCode]=studyItem.contractCode where  contract.contractState !=0 and"+
					" contract.[signingDate] between :minDate and :maxDate"+
					" and contract.[contractCode] in(select contractCode from [CoresContract].[dbo].[tblTestItem] where tiCode=:type or :type is null or :type ='') and "+
					" (contract.operator=:operator or :operator is null or :operator = '' ) and"+
					" (contract.[sponsorName] like :name or contract.sponsorLinkman like :name or contract.sponsorTel like :name or contract.sponsorMobile like :name )"+
					" ) as contract1 group by sponsorId"+
					" ) as contract2"+
					" on customer.id=contract2.sponsorId ";
		
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("minDate", start);
		query.setParameter("maxDate", end);
		query.setParameter("type", type);
		query.setParameter("name", "%"+name+"%");
		if(readAll){
			query.setParameter("operator", "");
		}else{
			query.setParameter("operator", reader);
		}
		List<?> listSql = query.list();
		
		List<TblCustomer_Json> list = new ArrayList<TblCustomer_Json>();
		if(null != listSql){
			for(Object obj:listSql){
				Object[] objs = (Object[]) obj;
				String address="";
				String regionId1=(String)objs[2];
				address=tblRegionService.getFullNameByregionId(regionId1)+","+(String)objs[3];
				TblCustomer_Json t=new TblCustomer_Json();
				t.setId((String)objs[0]);
				t.setCustomerName((String)objs[1]);
				t.setAddress(address);
				t.setLinkman((String)objs[4]);
				t.setTel((String)objs[5]);
				t.setMobile((String)objs[6]);
				t.setEmail((String)objs[7]);
				t.setHttp((String)objs[8]);
				t.setFax((String)objs[9]);
				t.setPostalCode((String)objs[10]);
				t.setConts((Integer)objs[11]);
				t.setCts((Integer)objs[12]);
				t.setCis((Integer)objs[13]);
				list.add(t);
			}
		}
		return list;
	}
	public String getPid(String regionId){
		String pid=null;
		String sql="SELECT [pid]  FROM [CoresContract].[dbo].[tblRegion] where id=:id";
		List<?> listSql = getSession().createSQLQuery(sql).setParameter("id", regionId).list();
		if(null != listSql){
			for(Object obj:listSql){
				pid=(String)obj;
			}
		}
		return pid;
		
	}

	@SuppressWarnings("unchecked")
	public List<TblCustomer_Json> getCustomerByNameOrIinkmanOrTelOrmobile(
			String content,boolean readAll,String reader) {
		 String sql  = "   select  distinct tblcm.topRegionName, tblcm.tpRegionName, tblcm.regionName , tblcm.cid , tblcm.customerName, "
		        + "  tblcm.address, tblcm.linkman, tblcm.tel, tblcm.mobile, tblcm.email, tblcm.fax, tblcm.postalCode, tblcm.tiCode," 
		        + " (case when (tblco.id is not null or tblco.id != '') then 0 else 1 end) as contractMark, ";
		    if(readAll){
					sql = sql + " (case when ((tblco.operator is not null or tblco.operator != '') and tblco.contractState != 0)  then 0 else 1 end ) as viewMark " ;
			}else {
					sql = sql + " (case when tblco.operator = :operator  then 0 else 1 end ) as viewMark  " ;
			}
	        sql = sql + " from ( "
	        +"  select a.id1,a.id2,a.id3,a.a as topRegionName,a.b as tpRegionName ,a.c as regionName,b.id cid ,b.customerName,b.address,b.linkman,b.tel, " 
	        +" b.mobile,b.email,b.fax,b.postalCode,b.tiCode from " 
	        +"   (select c.level,b.* from [CoresContract].[dbo].[tblCustomer] as b  "
	        +" left join tblRegion as c  "
	        +" on b.regionId=c.id) as b  "
	        +" left join   "
	        +" 	 (  "
	        //市
			+" 		 select  3 level,a1.id id1,a2.id id2 ,a3.id id3,a1.regionName a,a2.regionName b ,a3.regionName c from   "
	        +" 		 (select * from CoresContract.dbo.tblRegion where level=1) as a1  "
	        +" 		 left join  "
	        +" 		 (select * from CoresContract.dbo.tblRegion where level=2) as a2  "
	        +" 		   on a1.id=a2.pid  "
	        +" 		 left join  "
	        +" 		 (select * from CoresContract.dbo.tblRegion where level=3) as a3  "
	        +" 	   on a2.id=a3.pid  "
			//国家
			+" 		 union   "
	        +" 		 (select 1 level, id id1,null id2,null id3 ,regionName a,null b,null c from CoresContract.dbo.tblRegion where level=1)  "
			//省
	        +" 		 union   "
	        +" 		 select 2 level, b1.id id1,b2.id id2,null id3,b1.regionName a,b2.regionName b,null c from   "
	        +" 		 (select * from CoresContract.dbo.tblRegion where level=1) as b1  "
	        +" 		 left join   "
	        +" 		 (select * from CoresContract.dbo.tblRegion where level=2) as b2  "
	        +" 		 on b1.id=b2.pid  "
	        +" 	) as a  "
	        +" on b.level=a.level and ((b.level=1 and b.regionId =a.id1) or (b.level=2 and b.regionId =a.id2) or (b.level=3 and b.regionId =a.id3))  "
	        + " )as tblcm "
	        + "	 left join  CoresContract.dbo.tblContract  as tblco "
	        + "	on tblcm.cid = tblco.sponsorId where tblcm.customerName like :content  or tblcm.linkman like :content or tblcm.tel like :content  " 
	        + " or tblcm.mobile like :content " 
	        + " order by tblcm.tpRegionName,tblcm.regionName  ,tblcm.customerName ";
	        
			List<TblCustomer_Json> list = new ArrayList<TblCustomer_Json>();
			Query query = getSession().createSQLQuery(sql);
			if(readAll){
			}else {
				query.setParameter("operator", reader);
			}
			query.setParameter("content", "%" + content+"%");
			query.setResultTransformer(new MapResultTransformer());
			List<Map<String,Object>> listSql = query.list();
			for(Map<String,Object> map :listSql){
				TblCustomer_Json t=new TblCustomer_Json();
				t.setId((String)map.get("cid"));
				if(null != map.get("tpRegionName") ){
					t.setRegion((String)map.get("tpRegionName"));
				}else{
					t.setRegion((String)map.get("topRegionName"));
				}
				if(null != map.get("regionName")){
					t.setRegion1((String)map.get("regionName"));
				}
				
				t.setCustomerName((String)map.get("customerName"));
				t.setLinkman((String)map.get("linkman"));
				t.setAddress((String)map.get("address"));
				t.setTel((String)map.get("tel"));
				t.setMobile((String)map.get("mobile"));
				t.setEmail((String)map.get("email"));
				t.setFax((String)map.get("fax"));
				t.setPostalCode((String)map.get("postalCode"));
				t.setTiCode((String)map.get("tiCode"));
				if(null != map.get("viewMark")){
					t.setViewMark((Integer)map.get("viewMark"));
				}
				if(null != map.get("contractMark")){
				   t.setContractMark((Integer)map.get("contractMark"));
				}
	    		list.add(t);
	    	}
			return list;
	}
}
