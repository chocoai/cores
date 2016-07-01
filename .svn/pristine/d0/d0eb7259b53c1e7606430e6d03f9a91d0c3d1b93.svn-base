package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.contract.TblAppointSD;
import com.lanen.model.contract.TblAppointSD_JSON;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.service.UserService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Service
public class TblAppointSDServiceImpl extends BaseDaoImpl<TblAppointSD> implements TblAppointSDService {
	
	@Resource
	private TblStudyScheduleService tblStudyScheduleService;
	
	@Resource
	private TblTestItemService tblTestItemService;
	
	@Resource
	private UserService userService;
	
	

	public List<Integer> getStartStudyItem() {
		String sql = "SELECT distinct [studyState] FROM [CoresContract].[dbo].[tblStudyItem] ";
		Query query = getSession().createSQLQuery(sql);
		List<?> listSql = query.list();
		List<Integer> list = new ArrayList<Integer>();
		if(null != listSql){
			for(Object obj:listSql){
				list.add((Integer)obj);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void saveAll(List<TblAppointSD> list) {
		for(TblAppointSD obj : list){
			List<TblAppointSD> list1 = getSession().createQuery("From TblAppointSD where studyNo = ? ")
			.setParameter(0, obj.getStudyNo())
			.list();
			if(null != list1 && list1.size()>0){
				delete(list1.get(0).getId());
			}
			getSession().save(obj);
		}
	}

	public void updateAll(List<TblAppointSD> list) {
		for(TblAppointSD obj : list){
			getSession().update(obj);
		}
		
	}

	public void updateAll(List<TblAppointSD> list, List<TblStudyItem> list2) {
		for(TblAppointSD obj : list){
			getSession().update(obj);
		}
		for(TblStudyItem obj : list2){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set sd = :sd  ,sdCode = :sdCode,sdState = 1 ,sdAppointDate = :sdAppointDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("sd", obj.getSd());
			query.setParameter("sdCode", obj.getSdCode());
			query.setParameter("id", obj.getId());
			query.setParameter("sdAppointDate", new Date());
			query.executeUpdate();
			
			String sql_studyplan = "update CoresStudy.dbo.tblStudyPlan set  studydirector = :sd "+
						" from CoresStudy.dbo.tblStudyPlan"+
						" where studyNo = :studyNo ";
			Query query_studyplan = getSession().createSQLQuery(sql_studyplan);
			query_studyplan.setParameter("studyNo",obj.getStudyNo());
			query_studyplan.setParameter("sd", obj.getSd());
			query_studyplan.executeUpdate();
		}
		
	}


	public void updateAgainAll(List<TblAppointSD> list, List<TblAppointSD> list2,List<TblStudyItem> list3) {
		for(TblAppointSD obj : list){
			getSession().update(obj);
		}
		for(TblAppointSD obj : list2){
			getSession().save(obj);
		}
		for(TblStudyItem obj : list3){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set sd = :sd  ,sdCode = :sdCode,sdState = 1,sdAppointDate = :sdAppointDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("sd", obj.getSd());
			query.setParameter("sdCode", obj.getSdCode());
			query.setParameter("id", obj.getId());
			query.setParameter("sdAppointDate", new Date());
			query.executeUpdate();
			
			String sql_studyplan = "update CoresStudy.dbo.tblStudyPlan set  studydirector = :sd "+
									" from CoresStudy.dbo.tblStudyPlan"+
									" where studyNo = :studyNo ";
			Query query_studyplan = getSession().createSQLQuery(sql_studyplan);
			query_studyplan.setParameter("studyNo",obj.getStudyNo());
			query_studyplan.setParameter("sd", obj.getSd());
			query_studyplan.executeUpdate();
			
		}
		
	}



	@SuppressWarnings("unchecked")
	public TblAppointSD getByStudyNo(String studyNo) {
		List<TblAppointSD> list1 = getSession().createQuery("From TblAppointSD where studyNo = ?  and state = 0")
		.setParameter(0, studyNo)
		.list();
		if(null != list1 && list1.size()>0){
			return list1.get(0);
		}else{
			return null;
		}
		
	}


	@SuppressWarnings("unchecked")
	public List<TblAppointSD_JSON> getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrder(
			Date startime, Date endtime, String name, String sort, String order,String tiCode,String chooseOwn) {
		//判断是否是部门负责人
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean falg  = userService.checkPrivilege(user, "部门负责人");
		List<String> userList  = new ArrayList<String>();
		if(chooseOwn.equals("0")){
			if(falg){
				String departmentId  = user.getDepartment().getId();
				List<User> list = userService.getUserListByDepartmentId(departmentId);
				for(User obj:list){
					userList.add(obj.getUserName());
				}
			}else{
				userList.add(name);
			}
		}else{
			userList.add(name);
		}
		
		String sql ="SELECT tblcsao.*,esl.recordTime  "+
        "  FROM (   "+
        "  	    SELECT tblcsa.*,tblsd.sd as tsd ,tblsd.appointDate as tappointDate,tblsd.cancelDate as tcancelDate,tblsd.state as sds    "+
        "            FROM (    "+
        "                  SELECT tblcs.[id],tblcs.[cid],tblcs.[contractCode],tblcs.[tiNo],tblcs.[studyName],tblcs.[studyNo],tbla.[sd],tblcs.[sdCode],tblcs.sdAppointDate,  "+
        "  	            tblcs.qa,tblcs.qaCode,tblcs.qaAppointDate,tblcs.pathSD,tblcs.pathSDCode,tblcs.pathSDDate,tblcs.[sdManager],tblcs.[state] as csstate,tbla.id as tblaid   "+
        "  	            ,tbla.state ,tbla.appointSignID,tbla.appointDate,tblcs.signingDate,tblcs.[sdState],tblcs.[qaState],tblcs.[paState],tblq.qa as qaun,tblq.id as qid ,tblpa.pathSD as pa,tblpa.id as pid  "+
        "  				FROM (  "+
        "  				    SELECT tbls.[id],tbls.[contractCode],tbls.[tiNo],tbls.[studyName],tbls.[studyNo],tbls.[sdCode],  "+ 
        "                             tbls.[sd],tbls.[sdAppointDate],tbls.[sdManager],tbls.[state],tblc.[signingDate],tblc.id as cid ,  "+
        "  					       tbls.[qa],tbls.[qaCode],tbls.[qaAppointDate],tbls.[pathSD],tbls.[pathSDCode],tbls.[pathSDDate],tbls.[sdState],tbls.[qaState],tbls.[paState]  "+
        "  	                       FROM [CoresContract].[dbo].[tblStudyItem] as tbls      "+
	    "                             left join [CoresContract].[dbo].[tblContract] as tblc ON tbls.contractCode = tblc.contractCode    "+    
        "                             WHERE   (convert(varchar(10),tblc.signingDate,120) between  :startime and :endtime  ) as tblcs     "+
        "                             left join [CoresSchedule].[dbo].[tblAppointSD] as tbla       "+
        "                         ON  tblcs.contractCode = tbla.contractCode and  tblcs.tiNo = tbla.tiNo and tblcs.studyNo = tbla.studyNo      "+
        " left join [CoresSchedule].[dbo].[tblAppointQA] as tblq "+
        "  ON  tblq.contractCode = tblcs.contractCode and  tblcs.tiNo = tblq.tiNo and tblcs.studyNo = tblq.studyNo " +
        "  left join [CoresSchedule].[dbo].[tblAppointPathSD] as tblpa " +
        " ON tblpa.contractCode = tblcs.contractCode and  tblcs.tiNo = tblpa.tiNo and tblcs.studyNo = tblpa.studyNo " +
        "       WHERE    (tbla.state = 0  or tbla.state = 1 or  tbla.state is null or  tbla.state = '' )" +
        "  and (tblq.state = 0  or tblq.state = 1 or  tblq.state is null or  tblq.state = '' )  " +
        "  and (	  tblpa.state = 0  or 	tblpa.state = 1 or  tblpa.state is null or  tblpa.state = '' )  and ( tbla.sdCode in ( :name ) or tblq.qaCode in ( :name ) or tblpa.pathSDCode in ( :name ) ) ";
      //-1 	全部 0 未任命1未确认 2已确认
    	//if(start == 0){  
    	//	sql = sql + " and ( ((tbla.sd is null or tbla.sd ='') and ( tblcs.sdManager = '' or tblcs.sdManager is null))) "; 
    	//}else if(start == 1){
    	//	sql = sql +"   and(( tbla.sd !='' and tbla.sd is not null ) or (tblcs.sdManager != '' and tblcs.sdManager is not null))  and (tbla.appointSignID is null or tbla.appointSignID = '') ";
    	//}else if(start == 2){
    		sql = sql + " and tbla.sd !='' and tbla.sd is not null  and (tbla.appointSignID is not null and tbla.appointSignID != '') ";
    	//}
    	//-1 	全部 0 未任命1未确认 2已确认
    	//if(qastate == 0){  
    	//	sql = sql + " and (tblq.qa is null or tblq.qa = '' ) "; 
    	//}else if(qastate == 1){
    	//	sql = sql +"   and(( tblq.qa  !='' and tblq.qa  is not null )  and (tblq.appointSignID is null or tblq.appointSignID = '')) ";
    	//}else if(qastate == 2){
    	//	sql = sql + " and tblq.qa  !='' and tblq.qa  is not null  and (tblq.appointSignID is not null and tblq.appointSignID != '') ";
    	//}
    	//-1 	全部 0 未任命1未确认 2已确认
    	//if(pastate == 0){  
    	//	sql = sql + " and (tblpa.pathSD is null or tblpa.pathSD  = '' ) "; 
    	//}else if(pastate == 1){
    	//	sql = sql +"   and(( tblpa.pathSD  !='' and tblpa.pathSD  is not null )  and (tblpa.appointSignID is null or tblpa.appointSignID = '')) ";
        //	}else if(pastate == 2){
        //		sql = sql + " and tblpa.pathSD  !='' and tblpa.pathSD  is not null  and (tblpa.appointSignID is not null and tblpa.appointSignID != '') ";
        //	}
//		if(tiNo.equals("-1")){
//    		sql = sql +"";
//    	}else {
//    		sql = sql +" and tblcs.[tiNo] = :tiNo";
//    	}
		
    	sql = sql + " ) as tblcsa left join (SELECT * FROM [CoresSchedule].[dbo].[tblAppointSD] WHERE state = -1 ) as tblsd   "+
      "       ON tblcsa.studyNo = tblsd.studyNo  )  as tblcsao left join(SELECT  dataId,min(recordTime) as recordTime FROM" +
      " [CoresUserPrivilege].[dbo].[tblESLink]   "+
	  "		 WHERE esType = '601' GROUP BY dataId  "+
	  "		) as esl    "+
      "      ON tblcsao.[cid] = esl.dataId  where (esl.recordTime != '' and esl.recordTime is not null  )  "+
      "    order by   ";
		  if(sort.equals("recordTime")){
			 sql = sql +"esl."+sort +" " +order ;
	      }else{
	    	  sql = sql +"tblcsao."+sort +" " +order ;
	      }
		 Query query = getSession().createSQLQuery(sql);
			query.setParameter("startime", startime);
			query.setParameter("endtime", endtime);
			query.setParameterList("name", userList);
//			if(tiNo.equals("-1")){
//	    	}else {
//	    		query.setParameter("tiNo", tiNo);
//	    	}
			//List<?> list = query.list();
			List<Map<String,Object>> list = query
			.setResultTransformer(new MapResultTransformer())
			.list();
			TblAppointSD_JSON json = null;
			//存放最终结果
			List<TblAppointSD_JSON> jsonlist =new ArrayList<TblAppointSD_JSON>();
			String StudyNoStr = "";
			for(Map<String,Object> map :list){
				if(StudyNoStr.equals("") || (!StudyNoStr.contains((String)map.get("studyNo")))){
				json = new TblAppointSD_JSON();
				String code = tblTestItemService.getTiCodeByTiNo((String)map.get("tiNo"));
		    	if(tiCode.equals("-1")){
		    		
		    	}else {
		    		if(!tiCode.equals(code)){
		    			continue;
		    		}
		    	}
		    	json.setTiCode(code);
				json.setId((String)map.get("studyNo"));
				json.setQid((String)map.get("qid"));//QAid
				json.setPid((String)map.get("pid"));//PathSd id
				json.setIsSDM((String)map.get("tblaid"));
				json.setSid((String)map.get("id"));
				json.setContractCode((String)map.get("contractCode"));
				json.settINo((String)map.get("tiNo"));
				json.setStudyName((String)map.get("studyName"));
				StudyNoStr = "@"+(String)map.get("studyNo")+"@";
				json.setStudyNo((String)map.get("studyNo"));
			    json.setSdCode((String)map.get("sdCode"));
			    if(null == map.get("sd") || map.get("sd").equals("") ){
			    	json.setSd((String)map.get("sdManager"));
			    }else{
				    json.setSd((String)map.get("sd"));
			    }
			    json.setStart((Integer)map.get("csstate"));
			    json.setAppointDate((Date)map.get("appointDate"));
			    json.setAppointSignID((String)map.get("appointSignID"));
			    if(null == map.get("qa") || map.get("qa").equals("") ){
			    	json.setQa((String)map.get("qaun"));
			    }else{
			    	 json.setQa((String)map.get("qa"));
			    }
			   
			    json.setQaCode((String)map.get("QaCode"));
			    if(null == map.get("pathSD") || map.get("pathSD").equals("") ){
			    	  json.setPathSD((String)map.get("pa"));
			    }else{
			    	json.setPathSD((String)map.get("pathSD"));
			    }
			    json.setPathSDDate((Date)map.get("pathSDDate"));
			    json.setQaAppointDate((Date)map.get("qaAppointDate"));
			    json.setSdAppointDate((Date)map.get("sdAppointDate"));
			    json.setSdState((Integer)map.get("sdState"));
			    json.setQaState((Integer)map.get("qaState"));
			    json.setPathState((Integer)map.get("paState"));
			    
//				//进度
//				String  progress= tblStudyScheduleService.getPercentageByStudyNo(json.getStudyNo());
//				double aa=Double.parseDouble(progress);
//			    String    p  =Double.toString(aa * 100);
//				TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(json.getStudyNo());
//				json.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
//				String remark = "";
//				for(Map<String,Object>  map2 :list){
//					if( map2.get("sds") != null  && (map.get("studyNo")).equals(map2.get("studyNo"))){
//						if(!remark.equals("")){
//							remark = remark +" ; ";
//						}
//						remark =remark + (String)map2.get("tsd") +" : "+DateUtil.dateToString((Date)map2.get("tappointDate"), "yyyy-MM-dd") 
//						+ " ～ " + DateUtil.dateToString((Date)map2.get("tcancelDate"), "yyyy-MM-dd");
//						
//					}
//				}
//				json.setRemark(remark);
				json.setSigningDate((Date)map.get("signingDate"));
				json.setRecordTime((Date)map.get("recordTime"));
			    if( (user.getUserName()).equals( (json.getSdCode()))){
			    	json.setOwn("1");
			    }else{
			    	json.setOwn("0");
			    }
				jsonlist.add(json);
				}
			}
			return jsonlist;
	}


	@SuppressWarnings("unchecked")
	public void saveAllAndUpdate(List<TblAppointSD> list,
			List<TblStudyItem> list2) {
		for(TblAppointSD obj : list){
			List<TblAppointSD> list1 = getSession().createQuery("From TblAppointSD where studyNo = ? ")
			.setParameter(0, obj.getStudyNo())
			.list();
			if(null != list1 && list1.size()>0){
				delete(list1.get(0).getId());
			}
			getSession().save(obj);
		}
		for(TblStudyItem obj : list2){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set sd = :sd  ,sdCode = :sdCode,sdState = 1 ,sdAppointDate = :sdAppointDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("sd", obj.getSd());
			query.setParameter("sdCode", obj.getSdCode());
			query.setParameter("id", obj.getId());
			query.setParameter("sdAppointDate", new Date());
			query.executeUpdate();
			
			String sql_studyplan = "update CoresStudy.dbo.tblStudyPlan set  studydirector = :sd "+
									" from CoresStudy.dbo.tblStudyPlan"+
									" where studyNo = :studyNo ";
			Query query_studyplan = getSession().createSQLQuery(sql_studyplan);
			query_studyplan.setParameter("studyNo",obj.getStudyNo());
			query_studyplan.setParameter("sd", obj.getSd());
			query_studyplan.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public Date getappointDateByStudyNo(String studyNo) {
		List<TblAppointSD> list1 = getSession().createQuery("From TblAppointSD where studyNo = ? and state = 1 ")
												.setParameter(0, studyNo)
												.list();
		if(null != list1 && list1.size()>0){
			return list1.get(0).getAppointDate();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object>  getBytartimeAndEndtimeAndStartAndSortAndOrderAndPage(
			Date startime, Date endtime, int start, String sort, String order,
			int qastate, int pastate, String tiCode,String page,String rows,String searchString) {
		String sql = "  select tblcsaon.id,tblcsaon.cid,tblcsaon.contractCode,tblcsaon.tiNo,tblcsaon.studyName,tblcsaon.studyNo,tblcsaon.sd,tblcsaon.sdCode,tblcsaon.sdAppointDate,tblcsaon.finishDate,tblcsaon.qa, "+
                     "    tblcsaon.qaCode,tblcsaon.qaAppointDate,tblcsaon.pathSD,tblcsaon.pathSDCode,tblcsaon.pathSDDate,tblcsaon.sdManager,tblcsaon.csstate,tblcsaon.tblaid,tblcsaon.state, "+
                     "    tblcsaon.appointSignID,tblcsaon.appointDate,tblcsaon.signingDate,tblcsaon.sdState,tblcsaon.qaState,tblcsaon.paState,tblcsaon.qaun,tblcsaon.qid,tblcsaon.pa,tblcsaon.pid, "+
                     "    tblcsaon.tsd,tblcsaon.tappointDate,tblcsaon.tcancelDate,tblcsaon.sds,tblcsaon.recordTime, "+
                     "    tblt.tiCode ,tblcsaon.remark,tblcsaon.partner  "+
                     "           from (  "+
                     "    	         select tblcsao.id,tblcsao.cid,tblcsao.contractCode,tblcsao.tiNo,tblcsao.studyName,tblcsao.studyNo,tblcsao.sd,tblcsao.sdCode,tblcsao.sdAppointDate,tblcsao.finishDate,tblcsao.qa, "+
                     "                tblcsao.qaCode,tblcsao.qaAppointDate,tblcsao.pathSD,tblcsao.pathSDCode,tblcsao.pathSDDate,tblcsao.sdManager,tblcsao.csstate,tblcsao.tblaid,tblcsao.state, "+
                     "                tblcsao.appointSignID,tblcsao.appointDate,tblcsao.signingDate,tblcsao.sdState,tblcsao.qaState,tblcsao.paState,tblcsao.qaun,tblcsao.qid,tblcsao.pa,tblcsao.pid, "+
                     "                tblcsao.tsd,tblcsao.tappointDate,tblcsao.tcancelDate,tblcsao.sds,esl.recordTime  , tblcsao.remark   , tblcsao.partner   "+
                     "                      from  (    "+
                     "            	            select  tblcsa.id, tblcsa.cid, tblcsa.contractCode, tblcsa.tiNo, tblcsa.studyName, tblcsa.studyNo, tblcsa.sd, tblcsa.sdCode, tblcsa.sdAppointDate,tblcsa.finishDate, "+
                     "    						tblcsa.qa,tblcsa.qaCode,tblcsa.qaAppointDate,tblcsa.pathSD,tblcsa.pathSDCode,tblcsa.pathSDDate,tblcsa.sdManager,tblcsa.csstate,tblcsa.tblaid,tblcsa.state, "+
                     "                            tblcsa.appointSignID,tblcsa.appointDate,tblcsa.signingDate,tblcsa.sdState,tblcsa.qaState,tblcsa.paState,tblcsa.qaun,tblcsa.qid,tblcsa.pa,tblcsa.pid,tblsd.sd as tsd , "+
                     "    			            tblsd.appointDate as tappointDate,tblsd.cancelDate as tcancelDate,tblsd.state as sds  ,  tblcsa.remark , tblcsa.partner    "+
                     "                                  from  (     "+
                     "                                        select tblcs.id,tblcs.cid,tblcs.contractCode,tblcs.tiNo,tblcs.studyName,tblcs.studyNo,tbla.sd,tblcs.sdCode,tblcs.sdAppointDate,tblcs.finishDate,   "+
                     "            	                        tblcs.qa,tblcs.qaCode,tblcs.qaAppointDate,tblcs.pathSD,tblcs.pathSDCode,tblcs.pathSDDate,tblcs.sdManager,tblcs.state as csstate, "+
                     "    									tbla.id as tblaid,tbla.state ,tbla.appointSignID,tbla.appointDate,tblcs.signingDate,tblcs.sdState,tblcs.qaState,tblcs.paState, "+
                     "    									tblq.qa as qaun,tblq.id as qid ,tblpa.pathSD as pa,tblpa.id as pid , tbla.remark , tbla.partner  "+
                     "          				                  from (   "+
        			 "            				                       select tbls.id,tbls.contractCode,tbls.tiNo,tbls.studyName,tbls.studyNo,tbls.sdCode,   "+
                     "                                                   tbls.sd,tbls.sdAppointDate,tbls.finishDate,tbls.sdManager,tbls.state,tblc.signingDate,tblc.id as cid ,   "+
                     "            					                   tbls.qa,tbls.qaCode,tbls.qaAppointDate,tbls.pathSD,tbls.pathSDCode,tbls.pathSDDate,tbls.sdState,tbls.qaState,tbls.paState   "+
                     "           	                                         from CoresContract.dbo.tblStudyItem as tbls       "+
                     "    	                                                 left join CoresContract.dbo.tblContract as tblc on tbls.contractCode = tblc.contractCode      "+
                     "                                                        where tblc.signingDate between  :startime and :endtime   ) as tblcs      "+
                     "                                       left join CoresSchedule.dbo.tblAppointSD as tbla        "+
                     "                                        on  tblcs.contractCode = tbla.contractCode and  tblcs.tiNo = tbla.tiNo and tblcs.studyNo = tbla.studyNo       "+
                     "                             left join CoresSchedule.dbo.tblAppointQA as tblq  "+
                     "                             on  tblq.contractCode = tblcs.contractCode and  tblcs.tiNo = tblq.tiNo and tblcs.studyNo = tblq.studyNo  "+
                     "                        left join CoresSchedule.dbo.tblAppointPathSD as tblpa  "+
                     "                       on tblpa.contractCode = tblcs.contractCode and  tblcs.tiNo = tblpa.tiNo and tblcs.studyNo = tblpa.studyNo  "+
                     "              where  (tbla.state = 0  or tbla.state = 1 or  tbla.state is null or  tbla.state = '' ) "+
                     "              and ( tblq.state = 0  or tblq.state = 1 or  tblq.state is null or  tblq.state = '' )   "+
                     "              and ( tblpa.state = 0  or tblpa.state = 1 or  tblpa.state is null or  tblpa.state = '' ) ";
	 //-1 	全部 0 未任命1未确认 2已确认
   	if(start == 0){  
   		sql = sql + " and ( ((tbla.sd is null or tbla.sd ='') and ( tblcs.sdManager = '' or tblcs.sdManager is null))) "; 
   	}else if(start == 1){
   		sql = sql +"   and(( tbla.sd !='' and tbla.sd is not null ) or (tblcs.sdManager != '' and tblcs.sdManager is not null))  and (tbla.appointSignID is null or tbla.appointSignID = '') ";
   	}else if(start == 2){
   		sql = sql + " and tbla.sd !='' and tbla.sd is not null  and (tbla.appointSignID is not null and tbla.appointSignID != '') ";
   	}
   	//-1 	全部 0 未任命1未确认 2已确认
   	if(qastate == 0){  
   		sql = sql + " and (tblq.qa is null or tblq.qa = '' ) "; 
   	}else if(qastate == 1){
   		sql = sql +"   and(( tblq.qa  !='' and tblq.qa  is not null )  and (tblq.appointSignID is null or tblq.appointSignID = '')) ";
   	}else if(qastate == 2){
   		sql = sql + " and tblq.qa  !='' and tblq.qa  is not null  and (tblq.appointSignID is not null and tblq.appointSignID != '') ";
   	}
   	//-1 	全部 0 未任命1未确认 2已确认
   	if(pastate == 0){  
   		sql = sql + " and (tblpa.pathSD is null or tblpa.pathSD  = '' ) "; 
   	}else if(pastate == 1){
   		sql = sql +"   and(( tblpa.pathSD  !='' and tblpa.pathSD  is not null )  and (tblpa.appointSignID is null or tblpa.appointSignID = '')) ";
   	}else if(pastate == 2){
   		sql = sql + " and tblpa.pathSD  !='' and tblpa.pathSD  is not null  and (tblpa.appointSignID is not null and tblpa.appointSignID != '') ";
   	}
   	sql = sql +" ) as tblcsa left join (select studyNo,state,cancelDate,appointDate,sd  from CoresSchedule.dbo.tblAppointSD where state = -1 ) as tblsd     "+  
    "       on tblcsa.studyNo = tblsd.studyNo  )  as tblcsao left join(select  dataId,min(recordTime) as recordTime   "+  
    "      from CoresUserPrivilege.dbo.tblESLink  where esType = '601' group by dataId    "+  
    "    	) as esl      "+  
    "     on tblcsao.cid = esl.dataId  where (esl.recordTime != '' and esl.recordTime is not null  )    "+  
    "    ) as tblcsaon left join  CoresContract.dbo.tblTestItem as tblt   "+  
    "  on tblt.tiNo = tblcsaon.tiNo ";
   	if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
	   	//专题编号，供试品编号，项目名称，SD，QA
	   sql = sql + " where  ( studyNo like :searchString or tblcsaon.tiNo like :searchString or studyName like :searchString or sd like :searchString or qa like :searchString )";
	}
     if(sort == null || order == null ){
    	 sql = sql + "       order by ( case when (tblcsaon.id ='' or tblcsaon.id is NULL ) and tblcsaon.[sdManager]  = '' then  1   "+  
         "       when tblcsaon.appointSignID is null and tblcsaon.[sdManager]  != '' and tblcsaon.[sd] is null then 2      "+
         "       when tblcsaon.appointSignID is null and tblcsaon.[sd] is not null  then 3 when tblcsaon.appointSignID is not null then 4   "+
         "      end  ) , tblcsaon.signingDate,tblcsaon.id desc ";
     }else{
    	 sql = sql + "   order by  tblcsaon."+sort +",tblcsaon.id " +order ;
     }
   
     //hibernate分页和order by混用,排序字段必须唯一，这个hibernate的bug。
     
      int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
      int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
      Query query = getSession().createSQLQuery(sql);
	  query.setParameter("startime", startime);
	  query.setParameter("endtime", endtime);
	  if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
		  query.setParameter("searchString", "%"+searchString+"%");
	  }
	  List<Map<String,Object>> clist = query.setResultTransformer(new MapResultTransformer()).list();
	  int count = clist.size();
	  List<Map<String,Object>> list = query.setResultTransformer(new MapResultTransformer())
		                              .setFirstResult((currentpage - 1) * pagesize)
		                              .setMaxResults(pagesize)
		                              .list();
	  
	  TblAppointSD_JSON json = null;
	  //存放最终结果
	  List<TblAppointSD_JSON> jsonlist =new ArrayList<TblAppointSD_JSON>();
	  String StudyNoStr = "";
	  if(tiCode.equals("-1")){
	  }else {
	  		int i = 1;
	  	  for(Map<String,Object> map :clist){
	  		String code = (String)map.get("tiCode");
	  		if(!tiCode.equals(code)){
	  			continue;
	  		}else{
	  			i++;
	  		}
	  	  }
	  	count = i;
	  	}
	
	  
	  if(sort == null || order == null ){
		  
	  }
	  for(Map<String,Object> map :list){
			if(StudyNoStr.equals("") || (!StudyNoStr.contains((String)map.get("studyNo")))){
			json = new TblAppointSD_JSON();
			if(null != map.get("finishDate") && !map.get("finishDate").equals("")){
				 json.setFinishDate(DateUtil.dateToString((Date)map.get("finishDate"), "yyyy-MM-dd"));
			}else{
				 json.setFinishDate("");
			}
			 
			String code = (String)map.get("tiCode");
	    	if(tiCode.equals("-1")){
	    	}else {
	    		if(!tiCode.equals(code)){
	    			continue;
	    		}
	    	}
	    	json.setTiCode(code);
			json.setId((String)map.get("studyNo"));
			json.setQid((String)map.get("qid"));//QAid
			json.setPid((String)map.get("pid"));//PathSd id
			json.setIsSDM((String)map.get("tblaid"));
			json.setSid((String)map.get("id"));
			json.setContractCode((String)map.get("contractCode"));
			json.settINo((String)map.get("tiNo"));
			json.setStudyName((String)map.get("studyName"));
			StudyNoStr = "@"+(String)map.get("studyNo")+"@";
			json.setStudyNo((String)map.get("studyNo"));
		    json.setSdCode((String)map.get("sdCode"));
		    if(null == map.get("sd") || map.get("sd").equals("") ){
		    	json.setSd((String)map.get("sdManager"));
		    }else{
			    json.setSd((String)map.get("sd"));
		    }
		    json.setStart((Integer)map.get("csstate"));
		    json.setAppointDate((Date)map.get("appointDate"));
		    json.setAppointSignID((String)map.get("appointSignID"));
		    if(null == map.get("qa") || map.get("qa").equals("") ){
		    	json.setQa((String)map.get("qaun"));
		    }else{
		    	 json.setQa((String)map.get("qa"));
		    }
		   
		    json.setQaCode((String)map.get("QaCode"));
		    if(null == map.get("pathSD") || map.get("pathSD").equals("") ){
		    	  json.setPathSD((String)map.get("pa"));
		    }else{
		    	json.setPathSD((String)map.get("pathSD"));
		    }
		    json.setPathSDDate((Date)map.get("pathSDDate"));
		    json.setQaAppointDate((Date)map.get("qaAppointDate"));
		    json.setSdAppointDate((Date)map.get("sdAppointDate"));
		    json.setSdState((Integer)map.get("sdState"));
		    json.setQaState((Integer)map.get("qaState"));
		    json.setPathState((Integer)map.get("paState"));
			//进度
			String  progress= tblStudyScheduleService.getPercentageByStudyNo(json.getStudyNo());
			double aa=Double.parseDouble(progress);
			if(aa > 0){
			    String    p  =Double.toString(aa * 100);
				TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(json.getStudyNo());
				json.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
			}
			
			String remark = "";
			for(Map<String,Object>  map2 :list){
				if( map2.get("sds") != null  && (map.get("studyNo")).equals(map2.get("studyNo"))){
					if(!remark.equals("")){
						remark = remark +" ; ";
					}
					remark =remark + (String)map2.get("tsd") +" : "+DateUtil.dateToString((Date)map2.get("tappointDate"), "yyyy-MM-dd") 
					+ " ～ " + DateUtil.dateToString((Date)map2.get("tcancelDate"), "yyyy-MM-dd");
					
				}
			}
			json.setRemark(remark);
			json.setSigningDate((Date)map.get("signingDate"));
			json.setRecordTime((Date)map.get("recordTime"));
		    if(null != (String)map.get("remark") && (!map.get("remark").equals(""))){
		    	json.setRemark2((String)map.get("remark"));
		    }else{
		    	json.setRemark2("");
		    }
		    if(null != (String)map.get("partner") && (!map.get("partner").equals(""))){
		    	json.setPartner((String)map.get("partner"));
		    }else{
		    	json.setPartner("");
		    }
			jsonlist.add(json);
			}
		}
	   Map<String,Object> map = new HashMap<String,Object>();
	    map.put("rows", jsonlist);
		map.put("total", count);
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrderAndPage(
			Date startime, Date endtime, String name, String sort,
			String order, String tiCode, String chooseOwn, String page,
			String rows,String searchString) {
		//判断是否是部门负责人
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<String> userList  = new ArrayList<String>();
		boolean falg  = userService.checkPrivilege(user, "部门负责人");
		if(chooseOwn.equals("0")){
			if(falg){
				String departmentId  = user.getDepartment().getId();
				List<User> list = userService.getUserListByDepartmentId(departmentId);
				for(User obj:list){
					userList.add(obj.getUserName());
				}
			}else{
				userList.add(name);
			}
		}else{
			userList.add(name);
		}
			
//		String sql ="SELECT tblcsao.*,esl.recordTime  "+
//        "  FROM (   "+
//        "  	    SELECT tblcsa.*,tblsd.sd as tsd ,tblsd.appointDate as tappointDate,tblsd.cancelDate as tcancelDate,tblsd.state as sds    "+
//        "            FROM (    "+
//        "                  SELECT tblcs.[id],tblcs.[cid],tblcs.[contractCode],tblcs.[tiNo],tblcs.[studyName],tblcs.[studyNo],tbla.[sd],tblcs.[sdCode],tblcs.sdAppointDate,  "+
//        "  	            tblcs.qa,tblcs.qaCode,tblcs.qaAppointDate,tblcs.pathSD,tblcs.pathSDCode,tblcs.pathSDDate,tblcs.[sdManager],tblcs.[state] as csstate,tbla.id as tblaid   "+
//        "  	            ,tbla.state ,tbla.appointSignID,tbla.appointDate,tblcs.signingDate,tblcs.[sdState],tblcs.[qaState],tblcs.[paState],tblq.qa as qaun,tblq.id as qid ,tblpa.pathSD as pa,tblpa.id as pid  "+
//        "  				FROM (  "+
//        "  				    SELECT tbls.[id],tbls.[contractCode],tbls.[tiNo],tbls.[studyName],tbls.[studyNo],tbls.[sdCode],  "+ 
//        "                             tbls.[sd],tbls.[sdAppointDate],tbls.[sdManager],tbls.[state],tblc.[signingDate],tblc.id as cid ,  "+
//        "  					       tbls.[qa],tbls.[qaCode],tbls.[qaAppointDate],tbls.[pathSD],tbls.[pathSDCode],tbls.[pathSDDate],tbls.[sdState],tbls.[qaState],tbls.[paState]  "+
//        "  	                       FROM [CoresContract].[dbo].[tblStudyItem] as tbls      "+
//	    "                             left join [CoresContract].[dbo].[tblContract] as tblc ON tbls.contractCode = tblc.contractCode    "+    
//        "                             WHERE tblc.signingDate between  :startime and :endtime  ) as tblcs     "+
//        "                             left join [CoresSchedule].[dbo].[tblAppointSD] as tbla       "+
//        "                         ON  tblcs.contractCode = tbla.contractCode and  tblcs.tiNo = tbla.tiNo and tblcs.studyNo = tbla.studyNo      "+
//        " left join [CoresSchedule].[dbo].[tblAppointQA] as tblq "+
//        "  ON  tblq.contractCode = tblcs.contractCode and  tblcs.tiNo = tblq.tiNo and tblcs.studyNo = tblq.studyNo " +
//        "  left join [CoresSchedule].[dbo].[tblAppointPathSD] as tblpa " +
//        " ON tblpa.contractCode = tblcs.contractCode and  tblcs.tiNo = tblpa.tiNo and tblcs.studyNo = tblpa.studyNo " +
//        "       WHERE    (tbla.state = 0  or tbla.state = 1 or  tbla.state is null or  tbla.state = '' )" +
//        "  and (tblq.state = 0  or tblq.state = 1 or  tblq.state is null or  tblq.state = '' )  " +
//        "  and (	  tblpa.state = 0  or 	tblpa.state = 1 or  tblpa.state is null or  tblpa.state = '' ) and ( tbla.sdCode in (:name) or tblq.qaCode in (:name) or tblpa.pathSDCode in( :name) ) ";
//     
//    		sql = sql + " and tbla.sd !='' and tbla.sd is not null  and (tbla.appointSignID is not null and tbla.appointSignID != '') ";
//    	sql = sql + " ) as tblcsa left join (SELECT * FROM [CoresSchedule].[dbo].[tblAppointSD] WHERE state = -1 ) as tblsd   "+
//      "       ON tblcsa.studyNo = tblsd.studyNo  )  as tblcsao left join(SELECT  dataId,min(recordTime) as recordTime FROM" +
//      " [CoresUserPrivilege].[dbo].[tblESLink]   "+
//	  "		 WHERE esType = '601' GROUP BY dataId  "+
//	  "		) as esl    "+
//      "      ON tblcsao.[cid] = esl.dataId  where (esl.recordTime != '' and esl.recordTime is not null  )  ";
      
     String sql = "select tblcsao.id,tblcsao.cid,tblcsao.contractCode,tblcsao.tiNo,tblcsao.studyName,tblcsao.studyNo,tblcsao.sd,tblcsao.sdCode,tblcsao.sdAppointDate,tblcsao.finishDate " +
     "      ,tblcsao.qa,tblcsao.qaCode,tblcsao.qaAppointDate,tblcsao.pathSD,tblcsao.pathSDCode,tblcsao.pathSDDate,tblcsao.sdManager,tblcsao.csstate,tblcsao.tblaid    " +
     "      ,tblcsao.state ,tblcsao.appointSignID,tblcsao.appointDate,tblcsao.signingDate,tblcsao.sdState,tblcsao.qaState,tblcsao.paState,tblcsao.qaun,tblcsao.qid " +
     "      ,tblcsao.pa,tblcsao.pid,tblcsao.sd as tsd ,tblcsao.appointDate as tappointDate,tblcsao.tcancelDate,tblcsao.sds,esl.recordTime   " +
     "        from (    " +
     "          	 select tblcsa.id,tblcsa.cid,tblcsa.contractCode,tblcsa.tiNo,tblcsa.studyName,tblcsa.studyNo,tblcsa.sd,tblcsa.sdCode,tblcsa.sdAppointDate,tblcsa.finishDate,   " +
     "          	 tblcsa.qa,tblcsa.qaCode,tblcsa.qaAppointDate,tblcsa.pathSD,tblcsa.pathSDCode,tblcsa.pathSDDate,tblcsa.sdManager,tblcsa.csstate,tblcsa.tblaid    " +
     "           	 ,tblcsa.state ,tblcsa.appointSignID,tblcsa.appointDate,tblcsa.signingDate,tblcsa.sdState,tblcsa.qaState,tblcsa.paState,tblcsa.qaun,tblcsa.qid, " +
     "      		 tblcsa.pa,tblcsa.pid,tblsd.sd as tsd ,tblsd.appointDate as tappointDate,tblsd.cancelDate as tcancelDate,tblsd.state as sds     " +
     "                    from (     " +
     "                          select tblcs.id,tblcs.cid,tblcs.contractCode,tblcs.tiNo,tblcs.studyName,tblcs.studyNo,tbla.sd,tblcs.sdCode,tblcs.sdAppointDate,tblcs.finishDate,   " +
     "          	             tblcs.qa,tblcs.qaCode,tblcs.qaAppointDate,tblcs.pathSD,tblcs.pathSDCode,tblcs.pathSDDate,tblcs.sdManager,tblcs.state as csstate,tbla.id as tblaid    " +
     "           	             ,tbla.state ,tbla.appointSignID,tbla.appointDate,tblcs.signingDate,tblcs.sdState,tblcs.qaState,tblcs.paState,tblq.qa as qaun,tblq.id as qid , " +
     "      				     tblpa.pathSD as pa,tblpa.id as pid   " +
     "           			    	from (   " +
     "           				          select tbls.id,tbls.contractCode,tbls.tiNo,tbls.studyName,tbls.studyNo,tbls.sdCode,   " +
     "                                   tbls.sd,tbls.sdAppointDate,tbls.finishDate,tbls.sdManager,tbls.state,tblc.signingDate,tblc.id as cid ,  " +
     "           					      tbls.qa,tbls.qaCode,tbls.qaAppointDate,tbls.pathSD,tbls.pathSDCode,tbls.pathSDDate, " +
     "      							  tbls.sdState,tbls.qaState,tbls.paState   " +
     "           	                          from CoresContract.dbo.tblStudyItem as tbls       " +
     "                                       left join CoresContract.dbo.tblContract as tblc  " +
     "      								  on tbls.contractCode = tblc.contractCode     " +
     "                                        where  tblc.signingDate between  :startime and :endtime  ) as tblcs      " +
     "                              left join CoresSchedule.dbo.tblAppointSD as tbla        " +
     "                              on  tblcs.contractCode = tbla.contractCode and  tblcs.tiNo = tbla.tiNo and tblcs.studyNo = tbla.studyNo     " +  
     "                    left join CoresSchedule.dbo.tblAppointQA as tblq  " +
     "                     on  tblq.contractCode = tblcs.contractCode and  tblcs.tiNo = tblq.tiNo and tblcs.studyNo = tblq.studyNo  " +
     "           left join CoresSchedule.dbo.tblAppointPathSD as tblpa  " +
     "           on tblpa.contractCode = tblcs.contractCode and  tblcs.tiNo = tblpa.tiNo and tblcs.studyNo = tblpa.studyNo  " +
     "          where (tbla.state = 0  or tbla.state = 1 or  tbla.state is null or  tbla.state = '' ) " +
     "          and (tblq.state = 0  or tblq.state = 1 or  tblq.state is null or  tblq.state = '' )   " +
     "          and (	  tblpa.state = 0  or 	tblpa.state = 1 or  tblpa.state is null or  tblpa.state = '' )  " +
     "      	 and ( tbla.sdCode in (:name) or tblq.qaCode in (:name) or tblpa.pathSDCode in( :name) )  " +
     "         and tbla.sd !='' and tbla.sd is not null  and (tbla.appointSignID is not null and tbla.appointSignID != '')  " +
     "      	 )as tblcsa  " +
     "      left join (SELECT * FROM CoresSchedule.dbo.tblAppointSD WHERE state = -1 ) as tblsd    " +
     "      on tblcsa.studyNo = tblsd.studyNo  )  as tblcsao left join(SELECT  dataId,min(recordTime) as recordTime  " +
     "      from CoresUserPrivilege.dbo.tblESLink    " +
     "      where esType = '601' GROUP BY dataId   " +
     "      ) as esl     " +
     "      on tblcsao.cid = esl.dataId  where (esl.recordTime != '' and esl.recordTime is not null  )  ";
 	if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
	   	//专题编号，供试品编号，项目名称，SD，QA
	   sql = sql + " and  ( studyNo like :searchString or tblcsao.tiNo like :searchString or studyName like :searchString or sd like :searchString or qa like :searchString )";
	}
     if(sort == null || order == null ){
    	 sql = sql +  "    order by ( case when (tblcsao.id ='' or tblcsao.id is NULL ) and tblcsao.[sdManager]  = '' then  1    "+
    	       "   when tblcsao.appointSignID is null and tblcsao.[sdManager]  != '' and tblcsao.[sd] is null then 2     "+
    	       "  when tblcsao.appointSignID is null and tblcsao.[sd] is not null  then 3 when tblcsao.appointSignID is not null then 4  " +
    	       "    end  ) , tblcsao.signingDate desc ";
    	  }else{
    	    	 if(sort.equals("recordTime")){
    				 sql = sql +"  order by esl."+sort +" " +order ;
    		      }else{
    		    	  sql = sql +"  order by tblcsao."+sort +" " +order ;
    		      }
     }
     System.out.println("sql==="+sql);
     Query query = getSession().createSQLQuery(sql);
	 query.setParameter("startime", startime);
	 query.setParameter("endtime", endtime);
	 query.setParameterList("name", userList);
	 
	 if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
		  query.setParameter("searchString", "%"+searchString+"%");
	 }
	 
	 List<?> listc = query.list();
	 int count = listc.size();
	 int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
	 int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
	 List<Map<String,Object>> list = query
	 .setResultTransformer(new MapResultTransformer())
	 .setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize)
	 .list();
     TblAppointSD_JSON json = null;
	 //存放最终结果
	 List<TblAppointSD_JSON> jsonlist =new ArrayList<TblAppointSD_JSON>();
	 String StudyNoStr = "";
	 for(Map<String,Object> map :list){
		if(StudyNoStr.equals("") || (!StudyNoStr.contains((String)map.get("studyNo")))){
			json = new TblAppointSD_JSON();
			String code = tblTestItemService.getTiCodeByTiNo((String)map.get("tiNo"));
			if(tiCode.equals("-1")){
			   }else {
			    	if(!tiCode.equals(code)){
			    		continue;
			    	}
			   }
			if(null != map.get("finishDate") && !map.get("finishDate").equals("")){
				 json.setFinishDate(DateUtil.dateToString((Date)map.get("finishDate"), "yyyy-MM-dd"));
			}else{
				 json.setFinishDate("");
			}
			json.setTiCode(code);
		    json.setId((String)map.get("studyNo"));
			json.setQid((String)map.get("qid"));//QAid
			json.setPid((String)map.get("pid"));//PathSd id
			json.setIsSDM((String)map.get("tblaid"));
			json.setSid((String)map.get("id"));
			json.setContractCode((String)map.get("contractCode"));
			json.settINo((String)map.get("tiNo"));
			json.setStudyName((String)map.get("studyName"));
			StudyNoStr = "@"+(String)map.get("studyNo")+"@";
			json.setStudyNo((String)map.get("studyNo"));
		    json.setSdCode((String)map.get("sdCode"));
		    if(null == map.get("sd") || map.get("sd").equals("") ){
				json.setSd((String)map.get("sdManager"));
			}else{
			    json.setSd((String)map.get("sd"));
		    }
		    json.setStart((Integer)map.get("csstate"));
		    json.setAppointDate((Date)map.get("appointDate"));
		    json.setAppointSignID((String)map.get("appointSignID"));
		    if(null == map.get("qa") || map.get("qa").equals("") ){
		        json.setQa((String)map.get("qaun"));
		    }else{
				json.setQa((String)map.get("qa"));
			}
		    json.setQaCode((String)map.get("QaCode"));
			if(null == map.get("pathSD") || map.get("pathSD").equals("") ){
				json.setPathSD((String)map.get("pa"));
		    }else{
				json.setPathSD((String)map.get("pathSD"));
			}
			json.setPathSDDate((Date)map.get("pathSDDate"));
		    json.setQaAppointDate((Date)map.get("qaAppointDate"));
		    json.setSdAppointDate((Date)map.get("sdAppointDate"));
		    json.setSdState((Integer)map.get("sdState"));
		    json.setQaState((Integer)map.get("qaState"));
			json.setPathState((Integer)map.get("paState"));
		    //进度
			String  progress= tblStudyScheduleService.getPercentageByStudyNo(json.getStudyNo());
			double aa=Double.parseDouble(progress);
		    String    p  =Double.toString(aa * 100);
			TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(json.getStudyNo());
			json.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
			String remark = "";
			for(Map<String,Object>  map2 :list){
				if( map2.get("sds") != null  && (map.get("studyNo")).equals(map2.get("studyNo"))){
					if(!remark.equals("")){
						remark = remark +" ; ";
					 }
					remark =remark + (String)map2.get("tsd") +" : "+DateUtil.dateToString((Date)map2.get("tappointDate"), "yyyy-MM-dd") 
					+ " ～ " + DateUtil.dateToString((Date)map2.get("tcancelDate"), "yyyy-MM-dd");
					}
				}
			json.setRemark(remark);
			json.setSigningDate((Date)map.get("signingDate"));
			json.setRecordTime((Date)map.get("recordTime"));
			if( (user.getUserName()).equals( (json.getSdCode()))){
				json.setOwn("1");
			}else{
				json.setOwn("0");
			}
			jsonlist.add(json);
		}
	}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", jsonlist);
		map.put("total", count);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> 	getByNoFinishFileDis(Date startime,Date endtime,String name,String sort,String order,String tiCode,String chooseOwn,String page,String rows,String searchString)
	{
		
			User user = (User) ActionContext.getContext().getSession().get("user");
				
		     String sql = "select tblcsao.id,tblcsao.cid,tblcsao.contractCode,tblcsao.tiNo,tblcsao.studyName,tblcsao.studyNo,tblcsao.sd,tblcsao.sdCode,tblcsao.sdAppointDate,tblcsao.finishDate " +
		     "      ,tblcsao.qa,tblcsao.qaCode,tblcsao.qaAppointDate,tblcsao.pathSD,tblcsao.pathSDCode,tblcsao.pathSDDate,tblcsao.sdManager,tblcsao.csstate,tblcsao.tblaid    " +
		     "      ,tblcsao.state ,tblcsao.appointSignID,tblcsao.appointDate,tblcsao.signingDate,tblcsao.sdState,tblcsao.qaState,tblcsao.paState,tblcsao.qaun,tblcsao.qid " +
		     "      ,tblcsao.pa,tblcsao.pid,tblcsao.sd as tsd ,tblcsao.appointDate as tappointDate,tblcsao.tcancelDate,tblcsao.sds,esl.recordTime   " +
		     "        from (    " +
		     "          	 select tblcsa.id,tblcsa.cid,tblcsa.contractCode,tblcsa.tiNo,tblcsa.studyName,tblcsa.studyNo,tblcsa.sd,tblcsa.sdCode,tblcsa.sdAppointDate,tblcsa.finishDate,   " +
		     "          	 tblcsa.qa,tblcsa.qaCode,tblcsa.qaAppointDate,tblcsa.pathSD,tblcsa.pathSDCode,tblcsa.pathSDDate,tblcsa.sdManager,tblcsa.csstate,tblcsa.tblaid    " +
		     "           	 ,tblcsa.state ,tblcsa.appointSignID,tblcsa.appointDate,tblcsa.signingDate,tblcsa.sdState,tblcsa.qaState,tblcsa.paState,tblcsa.qaun,tblcsa.qid, " +
		     "      		 tblcsa.pa,tblcsa.pid,tblsd.sd as tsd ,tblsd.appointDate as tappointDate,tblsd.cancelDate as tcancelDate,tblsd.state as sds     " +
		     "                    from (     " +
		     "                          select tblcs.id,tblcs.cid,tblcs.contractCode,tblcs.tiNo,tblcs.studyName,tblcs.studyNo,tbla.sd,tblcs.sdCode,tblcs.sdAppointDate,tblcs.finishDate,   " +
		     "          	             tblcs.qa,tblcs.qaCode,tblcs.qaAppointDate,tblcs.pathSD,tblcs.pathSDCode,tblcs.pathSDDate,tblcs.sdManager,tblcs.state as csstate,tbla.id as tblaid    " +
		     "           	             ,tbla.state ,tbla.appointSignID,tbla.appointDate,tblcs.signingDate,tblcs.sdState,tblcs.qaState,tblcs.paState,tblq.qa as qaun,tblq.id as qid , " +
		     "      				     tblpa.pathSD as pa,tblpa.id as pid   " +
		     "           			    	from (   " +
		     "           				          select tbls.id,tbls.contractCode,tbls.tiNo,tbls.studyName,tbls.studyNo,tbls.sdCode,   " +
		     "                                   tbls.sd,tbls.sdAppointDate,tbls.finishDate,tbls.sdManager,tbls.state,tblc.signingDate,tblc.id as cid ,  " +
		     "           					      tbls.qa,tbls.qaCode,tbls.qaAppointDate,tbls.pathSD,tbls.pathSDCode,tbls.pathSDDate, " +
		     "      							  tbls.sdState,tbls.qaState,tbls.paState   " +
		     "           	                          from CoresContract.dbo.tblStudyItem as tbls       " +
		     "                                       left join CoresContract.dbo.tblContract as tblc  " +
		     "      								  on tbls.contractCode = tblc.contractCode     " +
		     "                                        where  tblc.signingDate between  :startime and :endtime  ) as tblcs      " +
		     "                              left join CoresSchedule.dbo.tblAppointSD as tbla        " +
		     "                              on  tblcs.contractCode = tbla.contractCode and  tblcs.tiNo = tbla.tiNo and tblcs.studyNo = tbla.studyNo     " +  
		     "                    left join CoresSchedule.dbo.tblAppointQA as tblq  " +
		     "                     on  tblq.contractCode = tblcs.contractCode and  tblcs.tiNo = tblq.tiNo and tblcs.studyNo = tblq.studyNo  " +
		     "           left join CoresSchedule.dbo.tblAppointPathSD as tblpa  " +
		     "           on tblpa.contractCode = tblcs.contractCode and  tblcs.tiNo = tblpa.tiNo and tblcs.studyNo = tblpa.studyNo  " +
		     "          where (tbla.state = 0  or tbla.state = 1 or  tbla.state is null or  tbla.state = '' ) " +
		     "          and (tblq.state = 0  or tblq.state = 1 or  tblq.state is null or  tblq.state = '' )   " +
		     "          and (	  tblpa.state = 0  or 	tblpa.state = 1 or  tblpa.state is null or  tblpa.state = '' )  " +
		  //   "      	 and ( tbla.sdCode in (:name) or tblq.qaCode in (:name) or tblpa.pathSDCode in( :name) )  " +
		     "         and tbla.sd !='' and tbla.sd is not null  and (tbla.appointSignID is not null and tbla.appointSignID != '')  " +
		     "      	 )as tblcsa  " +
		     "      left join (SELECT * FROM CoresSchedule.dbo.tblAppointSD WHERE state = -1 ) as tblsd    " +
		     "      on tblcsa.studyNo = tblsd.studyNo  )  as tblcsao left join(SELECT  dataId,min(recordTime) as recordTime  " +
		     "      from CoresUserPrivilege.dbo.tblESLink    " +
		     "      where esType = '601' GROUP BY dataId   " +
		     "      ) as esl     " +
		     "      on tblcsao.cid = esl.dataId  where (esl.recordTime != '' and esl.recordTime is not null  )  ";
		 	if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
			   	//专题编号，供试品编号，项目名称，SD，QA
			   sql = sql + " and  ( studyNo like :searchString or tblcsao.tiNo like :searchString or studyName like :searchString or sd like :searchString or qa like :searchString )";
			}
		 	//条件，有文件分发的专题
		 	sql += " and studyNo in (SELECT [studyNo] FROM [CoresQA].[dbo].[TblStudyFileDis]" +
					"  where reader= :realName  )";//and readFlag=0
		 	
		     if(sort == null || order == null ){
		    	 sql = sql +  "    order by ( case when (tblcsao.id ='' or tblcsao.id is NULL ) and tblcsao.[sdManager]  = '' then  1    "+
		    	       "   when tblcsao.appointSignID is null and tblcsao.[sdManager]  != '' and tblcsao.[sd] is null then 2     "+
		    	       "  when tblcsao.appointSignID is null and tblcsao.[sd] is not null  then 3 when tblcsao.appointSignID is not null then 4  " +
		    	       "    end  ) , tblcsao.signingDate desc ";
		    	  }else{
		    	    	 if(sort.equals("recordTime")){
		    				 sql = sql +"  order by esl."+sort +" " +order ;
		    		      }else{
		    		    	  sql = sql +"  order by tblcsao."+sort +" " +order ;
		    		      }
		     }
		     System.out.println("sql==="+sql);
		     Query query = getSession().createSQLQuery(sql);
			 query.setParameter("startime", startime);
			 query.setParameter("endtime", endtime);
			 query.setParameter("realName", user.getRealName());
			// query.setParameterList("name", userList);
			 
			 if(null != searchString && !searchString.equals("")&&!"undefined".equals(searchString)){
				  query.setParameter("searchString", "%"+searchString+"%");
			 }
			 
			 List<?> listc = query.list();
			 int count = listc.size();
			 int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
			 int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
			 List<Map<String,Object>> list = query
			 								.setResultTransformer(new MapResultTransformer())
			 								.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize)
			 								.list();
		     TblAppointSD_JSON json = null;
			 //存放最终结果
			 List<TblAppointSD_JSON> jsonlist =new ArrayList<TblAppointSD_JSON>();
			 String StudyNoStr = "";
			 for(Map<String,Object> map :list){
				if(StudyNoStr.equals("") || (!StudyNoStr.contains((String)map.get("studyNo")))){
					json = new TblAppointSD_JSON();
					String code = tblTestItemService.getTiCodeByTiNo((String)map.get("tiNo"));
					if(tiCode.equals("-1")){
					   }else {
					    	if(!tiCode.equals(code)){
					    		continue;
					    	}
					   }
					if(null != map.get("finishDate") && !map.get("finishDate").equals("")){
						 json.setFinishDate(DateUtil.dateToString((Date)map.get("finishDate"), "yyyy-MM-dd"));
					}else{
						 json.setFinishDate("");
					}
					json.setTiCode(code);
				    json.setId((String)map.get("studyNo"));
					json.setQid((String)map.get("qid"));//QAid
					json.setPid((String)map.get("pid"));//PathSd id
					json.setIsSDM((String)map.get("tblaid"));
					json.setSid((String)map.get("id"));
					json.setContractCode((String)map.get("contractCode"));
					json.settINo((String)map.get("tiNo"));
					json.setStudyName((String)map.get("studyName"));
					StudyNoStr = "@"+(String)map.get("studyNo")+"@";
					json.setStudyNo((String)map.get("studyNo"));
				    json.setSdCode((String)map.get("sdCode"));
				    if(null == map.get("sd") || map.get("sd").equals("") ){
						json.setSd((String)map.get("sdManager"));
					}else{
					    json.setSd((String)map.get("sd"));
				    }
				    json.setStart((Integer)map.get("csstate"));
				    json.setAppointDate((Date)map.get("appointDate"));
				    json.setAppointSignID((String)map.get("appointSignID"));
				    if(null == map.get("qa") || map.get("qa").equals("") ){
				        json.setQa((String)map.get("qaun"));
				    }else{
						json.setQa((String)map.get("qa"));
					}
				    json.setQaCode((String)map.get("QaCode"));
					if(null == map.get("pathSD") || map.get("pathSD").equals("") ){
						json.setPathSD((String)map.get("pa"));
				    }else{
						json.setPathSD((String)map.get("pathSD"));
					}
					json.setPathSDDate((Date)map.get("pathSDDate"));
				    json.setQaAppointDate((Date)map.get("qaAppointDate"));
				    json.setSdAppointDate((Date)map.get("sdAppointDate"));
				    json.setSdState((Integer)map.get("sdState"));
				    json.setQaState((Integer)map.get("qaState"));
					json.setPathState((Integer)map.get("paState"));
				    //进度
					String  progress= tblStudyScheduleService.getPercentageByStudyNo(json.getStudyNo());
					double aa=Double.parseDouble(progress);
				    String    p  =Double.toString(aa * 100);
					TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(json.getStudyNo());
					json.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
					String remark = "";
					for(Map<String,Object>  map2 :list){
						if( map2.get("sds") != null  && (map.get("studyNo")).equals(map2.get("studyNo"))){
							if(!remark.equals("")){
								remark = remark +" ; ";
							 }
							remark =remark + (String)map2.get("tsd") +" : "+DateUtil.dateToString((Date)map2.get("tappointDate"), "yyyy-MM-dd") 
							+ " ～ " + DateUtil.dateToString((Date)map2.get("tcancelDate"), "yyyy-MM-dd");
							}
						}
					json.setRemark(remark);
					json.setSigningDate((Date)map.get("signingDate"));
					json.setRecordTime((Date)map.get("recordTime"));
					if( (user.getUserName()).equals( (json.getSdCode()))){
						json.setOwn("1");
					}else{
						json.setOwn("0");
					}
					json.setIsFileDis(1);
					jsonlist.add(json);
				}
			}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("rows", jsonlist);
				map.put("total", count);
				return map;
		
	}
		
	

}
