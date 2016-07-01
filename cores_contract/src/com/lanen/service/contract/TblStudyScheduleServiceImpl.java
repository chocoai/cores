package com.lanen.service.contract;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import javax.annotation.Resource;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblStudySchedule;

@Service
public class TblStudyScheduleServiceImpl extends BaseDaoImpl<TblStudySchedule> implements TblStudyScheduleService {

	public List<?> getTblStudyScheduleByContractCode(
			String contractCode) {
	    String sql = " "+
           "  SELECT tblt.tid,tblt.[tiNo] as ttiNo,tblt.[tiCode],tblt.[tiName],tblt.[tiType],tbls.sid,[studyTypeCode] , "+
	       "     tbls.[studyName] ,tbls.[studyNo] ,tbls.[sd] ,tbls.[studyState],tbls.[tiNo]  FROM (  "+
	       "      SELECT [id] as tid ,[tiNo],[tiCode],[tiName],[tiType] "+
	       "        FROM [CoresContract].[dbo].[tblTestItem] WHERE [contractCode] = :contractCode) as tblt "+
	       "        left join (  "+
	       "      SELECT [id] as sid  ,[studyTypeCode] ,[studyName] ,[studyNo] ,[sd] ,[studyState],[tiNo]  "+
	       "        FROM [CoresContract].[dbo].[tblStudyItem] WHERE [contractCode] = :contractCode ) as tbls  "+
	       "    ON tblt.tiNo = tbls.tiNo ";
	    Query query = getSession().createSQLQuery(sql);
	    query.setParameter("contractCode", contractCode);
	    List<?> list = query.list();
		return list;
	}

	@Resource
	private TblStudyItemService tblStudyItemService;
	@SuppressWarnings("unchecked")
	public List<TblStudySchedule> getListByStudyNo(final String studyNo) {
		if(null != studyNo){
			String hql = "FROM TblStudySchedule where studyNo = ? order by nodeSn";
			List<TblStudySchedule> list = getSession().createQuery(hql)
											.setParameter(0, studyNo)
											.list();
			if(null !=list && list.size()>1){
				return list;
			}else{
				String studyTypeCode = tblStudyItemService.getStudyTypeCodeByStudyNo(studyNo);
				if(null != studyTypeCode){
					String sql ="select node.id,node.nodeName,node.nodeSn "+
								" from CoresSystemSet.dbo.tblStudyScheduleNode as node "+
								" where node.studyTypeCode = ? "+
								" order by node.nodeSn";
					list = (List<TblStudySchedule>) getSession().createSQLQuery(sql)
										.setParameter(0, studyTypeCode)
										.setResultTransformer(new ResultTransformer(){

											private static final long serialVersionUID = 3591753421011617510L;

											public List transformList(List list) {
												return list;
											}

											public Object transformTuple(
													Object[] values, String[] columns) {
												TblStudySchedule tblStudySchedule = new TblStudySchedule();
												int i = 0;
											    for(Object value : values){
											    	switch (i) {
													case 0:tblStudySchedule.setId((String)value);
														break;
													case 1:tblStudySchedule.setNodeName((String)value);
														break;
													case 2:tblStudySchedule.setNodeSn((Integer)value);
														break;

													default:
														break;
													}
											    	i++;
											    }
											    tblStudySchedule.setStudyNo(studyNo);
												return tblStudySchedule;
											}})
										.list();
					if(null != list && list.size()>0){
						return list;
					}else{
						list = (List<TblStudySchedule>) getSession().createSQLQuery(sql)
						.setParameter(0, "@@@@@@")
						.setResultTransformer(new ResultTransformer(){


							private static final long serialVersionUID = 3334081781451147745L;

							public List transformList(List list) {
								return list;
							}

							public Object transformTuple(
									Object[] values, String[] columns) {
								TblStudySchedule tblStudySchedule = new TblStudySchedule();
								int i = 0;
							    for(Object value : values){
							    	switch (i) {
									case 0:tblStudySchedule.setId((String)value);
										break;
									case 1:tblStudySchedule.setNodeName((String)value);
										break;
									case 2:tblStudySchedule.setNodeSn((Integer)value);
										break;

									default:
										break;
									}
							    	i++;
							    }
							    tblStudySchedule.setStudyNo(studyNo);
								return tblStudySchedule;
							}})
						.list();
						
						return list;
					}
				}
			}
		}
		return null;
	}
	
	// 返回百分比
	public String getPercentageByStudyNo(String studyNo) {
		//获得 200 800 的进度计划
		String sql = 
        "        SELECT  [id],[studyNo],[nodeSn],[nodeName],[planDate] "+
        "             ,[actualDate],[confirmFlag],[confirmTime] "+
        "         FROM [CoresContract].[dbo].[tblStudySchedule]  as tbls "+
        "        WHERE tbls .studyNo = :studyNo and ((( "+
        "              tbls.nodeSn = '200' or  tbls.nodeSn = '800') and tbls.planDate is not NULL ) or ( tbls.actualDate =( "+
        "         SELECT  max([actualDate]) FROM [CoresContract].[dbo].[tblStudySchedule]   WHERE studyNo = :studyNo "+
        "        ) and tbls.planDate is not NULL  ))  ";
		
	    Query query = getSession().createSQLQuery(sql);
	    query.setParameter("studyNo", studyNo);
	    List<?> list = query.list();
	    
	    if(null != list){
	    	//falg200 当进度计划节点200有计划时间为true 否则为false
	    	boolean falg200 = false;
	    	//falg800 当进度计划节点200有计划时间为true 否则为false
	    	boolean falg800 = false;
	    	for (Object obj : list) {
	    		Object[] objs= (Object[]) obj;
	    		Integer nodeSn = (Integer) objs[2];
	    		if(nodeSn == 200){
	    			falg200 = true;
	    		}else if(nodeSn == 800){
	    			falg800 = true;
	    		}
	    	}
	    	//
	    	if( falg200 && falg800){
	    		String sql1 = "";
	    		if(list.size() >= 3){
	    			sql1 = "SELECT  mm.m,nn.n FROM (  "+
		    		" SELECT DATEDIFF ( DD ,(SELECT [planDate] "+
		    		" FROM [CoresContract].[dbo].[tblStudySchedule] "+
		    		" WHERE [studyNo] = :studyNo   and nodeSn = '200') "+
		    		" ,(SELECT [planDate] "+
		    		"  FROM [CoresContract].[dbo].[tblStudySchedule] "+
		    		"  WHERE [studyNo] = :studyNo   and nodeSn = '800')"+
		    		" )as m )"+
		    		
		    		"  as mm "+
		    		" left join ( "+
		    		"  SELECT DATEDIFF ( DD ,(SELECT [planDate] "+
		    		" FROM [CoresContract].[dbo].[tblStudySchedule] "+
		    		" WHERE [studyNo] = :studyNo   and nodeSn = '200') "+
		    		" ,(tblss.m))"+
		    		"  as n "+
		    		" FROM  "+
		    		"  ( select distinct planDate as m"+
		    		" 	FROM [CoresContract].[dbo].[tblStudySchedule] "+
		    		" 	where actualDate = ("+
		    		" 		SELECT max([actualDate])   "+
		    		" 	FROM [CoresContract].[dbo].[tblStudySchedule] "+
		    		" 	WHERE [studyNo] = :studyNo  and [actualDate] is not null "+
		    		" 	)"+
		    		" 	) as tblss ) as nn ON 1=1 ";
	    		}else{
	    			sql1 =" SELECT  mm.m,nn.n FROM (  "+
		             " SELECT DATEDIFF ( DD ,(SELECT [planDate]  "+
		    	     "  FROM [CoresContract].[dbo].[tblStudySchedule]  "+
		    	     "  WHERE [studyNo] = :studyNo    and nodeSn = '200')  "+
		    	     "  ,(SELECT [planDate]  "+
		    		 "  FROM [CoresContract].[dbo].[tblStudySchedule]  "+
		    		 "  WHERE [studyNo] = :studyNo   and nodeSn = '800') "+
		    		 "  )as m ) "+
		    		 "  as mm  "+
		    		 " left join (  "+
		    		 "  SELECT DATEDIFF ( DD,(tblss.m) ,(SELECT [actualDate] "+
		    		 " FROM [CoresContract].[dbo].[tblStudySchedule]  "+
		    		 " WHERE [studyNo] = :studyNo    and nodeSn = '200')  "+
		    		 "  ) "+
		    		 " as n  "+
		    		 " FROM   "+
		    		 "  ( select distinct planDate as m "+
		    		 "	FROM [CoresContract].[dbo].[tblStudySchedule]  "+
		    		 "	where actualDate = ( "+
		    		 "		SELECT max([actualDate])    "+
		    		 "	FROM [CoresContract].[dbo].[tblStudySchedule]  "+
		    		 "	WHERE [studyNo] = :studyNo  and [actualDate] is not null  "+
		    		 "	) "+
		    		 "	) as tblss ) as nn ON 1=1";
	    			
	    		}
	    		
	    		
	    		Query query1 = getSession().createSQLQuery(sql1);
	    		query1.setParameter("studyNo", studyNo);
	    		List<?> list1 = query1.list();
	    		for (Object obj : list1) {
	    			Object[] objs= (Object[]) obj;
	    			if(null != objs[0]&& null !=  objs[1]){
	    				int m = (Integer)objs[0];//总共天数
	    				int n = (Integer)objs[1];//已完成计划天数
	    				if(m!= 0 && n>0){
	    					float size = (float)n/m;
	    					DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
	    					String filesize = df.format(size);//返回的是String类型的
	    					if(filesize.equals("0.00") ){
	    						return "0.01";
	    					}
	    					return filesize;
	    				}else{
	    					return "0.01";
	    				}
	    			}
	    		}
	    	}else{
	    		//另一种情况，200或者800 无计划时间 查询 200 到800 之间的节点数为分母 
	    		
	    		String sql1 = " SELECT mm.m,nn.n FROM ( "+
	    		" SELECT  count(id) as m "+
	    		" FROM [CoresContract].[dbo].[tblStudySchedule] "+
	    		" WHERE [studyNo] = :studyNo  and ( nodeSn  between '200' and '800' ) ) as mm  left join ( "+
	    		" SELECT  count(id) as n "+
	    		" FROM [CoresContract].[dbo].[tblStudySchedule] "+
	    		" WHERE [studyNo] = :studyNo  and ( nodeSn  between '200' and  ( "+
	    		" ( SELECT max(nodeSn) as s FROM [CoresContract].[dbo].[tblStudySchedule] "+
	    		" WHERE [studyNo] = :studyNo and [actualDate] is not null )) ))as nn ON 1=1 ";
	    		Query query1 = getSession().createSQLQuery(sql1);
	    		query1.setParameter("studyNo", studyNo);
	    		List<?> list1 = query1.list();
	    		for (Object obj : list1) {
	    			Object[] objs= (Object[]) obj;
	    			if(null != objs[0]&& null !=  objs[1]){
	    				int m = (Integer)objs[0];//总共天数
	    				int n = (Integer)objs[1];//已完成计划天数
	    				if(m != 0 ){
	    					float size = (float)n/m;
	    					DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
	    					String filesize = df.format(size);//返回的是String类型的
	    					if(filesize.equals("0.00")){
	    						sql ="SELECT count(*) "+
	    							" FROM [CoresContract].[dbo].[tblStudySchedule] "+
	    							" WHERE [studyNo] = :studyNo and [actualDate] is not null ";
	    						Integer count = (Integer) getSession().createSQLQuery(sql)
	    												.setParameter("studyNo", studyNo)
	    												.uniqueResult();
	    						if(count > 0){
	    							return "0.01";
	    						}
	    					}
	    					return filesize;
	    				}else{
	    					String sqlc = " select count (*) from  [CoresContract].[dbo].[tblStudySchedule] as ss where ss.nodeSn = 100 and "+
	    					" ss.actualDate is not null and  ss.studyNo = :studyNo ";
	    					Integer count = (Integer) getSession().createSQLQuery(sqlc)
							.setParameter("studyNo", studyNo)
							.uniqueResult();
	    					if(count > 0){
	    						return "0.01";
	    					}
	    					return "0.00";
	    				}
	    				
	    			}
	    			
	    		}
	    	}
	    }

		return "0.00";
	}
	
	
	public TblStudySchedule getByStudyNoMaxStudySchedule(String studyNo) {
		String sql = " SELECT [nodeSn],[nodeName],[planDate],[actualDate] " +
       "  FROM [CoresContract].[dbo].[tblStudySchedule] WHERE studyNo = :studyNo and  nodeSn =  ( " +
       "  	SELECT max([nodeSn]) " +
       "     FROM [CoresContract].[dbo].[tblStudySchedule] WHERE studyNo = :studyNo and  [actualDate] is not null " +
       "   ) ";
		 Query query1 = getSession().createSQLQuery(sql);
	 	    query1.setParameter("studyNo", studyNo);
	 	    List<?> list1 = query1.list();
	 	   TblStudySchedule  studySchedule = new  TblStudySchedule();
	 	   for (Object obj : list1) {
				Object[] objs= (Object[]) obj;
				studySchedule.setNodeName((String)objs[1]);
				studySchedule.setActualDate((Date)objs[3]);
				studySchedule.setPlanDate((Date)objs[2]);
			} 
		return studySchedule;
	}
	public boolean isHasStudyNo(String studyNo) {
		String sql = "select count(*)"+
					" from CoresContract.dbo.tblStudySchedule as s"+
					" where s.studyNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.uniqueResult();
		if(count>1){
			return true;
		}
		return false;
	}
	public TblStudySchedule getByStudyNoNodeName(String studyNo, String nodeName) {
		String hql = "from TblStudySchedule where studyNo = ? and nodeName = ? ";
		TblStudySchedule tblStudySchedule = (TblStudySchedule) getSession().createQuery(hql)
														.setParameter(0, studyNo)
														.setParameter(1, nodeName)
														.uniqueResult();
		return tblStudySchedule;
	}
	public Map<String, String> getMapByStudyNo(String studyNo) {

		Map<String,String> map = null;
		String studyTypeCode = tblStudyItemService.getStudyTypeCodeByStudyNo(studyNo);
		if(null != studyTypeCode){
			String sql ="select node.nodeName,node.planDays "+
						" from CoresSystemSet.dbo.tblStudyScheduleNode as node "+
						" where node.studyTypeCode = ? and node.nodeName !='试验分组' "+
						" order by node.nodeSn";
			List<?>	list =  getSession().createSQLQuery(sql)
								.setParameter(0, studyTypeCode)
								.list();
			if(null != list && list.size()>0){
				map = new HashMap<String,String>();
				for(Object obj:list){
					Object[] objs = (Object[]) obj;
					map.put((String)objs[0],(String) objs[1]);
				}
			}
		}
		return map;
	}
	public List<Map<Integer, Object>> getMapBeforeNodeSn(String studyNo, int NodeSn) {
		String sql="select [nodeSn],[actualDate]  FROM [CoresContract].[dbo].[tblStudySchedule] "+ 
                   "where studyNo=:studyNo and nodeSn<:NodeSn";
		List<?> listSql=getSession().createSQLQuery(sql).setParameter("studyNo", studyNo)
		                                                .setParameter("NodeSn", NodeSn)
		                                                .list();
		List<Map<Integer, Object>> list=new ArrayList<Map<Integer,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Object[] objs=(Object[])obj;
				Map<Integer, Object> map=new HashMap<Integer, Object>();
				if((Integer)objs[0]!=null){
				   map.put((Integer)objs[0],(Date)objs[1] );
				}
				list.add(map);
			}
		}
		return list;
	}
	
}
