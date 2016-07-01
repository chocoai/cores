package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblSOLeaderJson;
import com.lanen.model.schedule.TblTOLeader;
import com.lanen.util.DateUtil;
@Service
public class TblTOLeaderServiceImpl extends BaseDaoImpl<TblTOLeader> implements TblTOLeaderService{

	@SuppressWarnings("unchecked")
	public List<TblTOLeader> getByLeaderList(String scheduleId) {
		List<TblTOLeader> list=getSession().createQuery("FROM TblTOLeader  d where d.scheduleId = ? ").setParameter(0, scheduleId).list();
		return list;
	}

	public void saveAllLeaderList(List<TblTOLeader> list) {
		for(TblTOLeader obj:list){
			save(obj);
		}
		
	}

	public void updateAllLeaderList(List<TblTOLeader> list1,
			List<TblTOLeader> list2) {
		//list1 新增的 list2  已有的
		String oldString ="";//原来负责人ID
		String newString ="";//新负责人
		for(TblTOLeader obj1 : list1){
			newString = obj1.gettOLeader() +newString;
		}
		for(TblTOLeader obj2 :list2){
			oldString = obj2.gettOLeader() +oldString;
		}
		for(TblTOLeader obj1 :list1){
			if(!oldString.contains(obj1.gettOLeader())){
				obj1.setId(getKey("TblTOLeader"));
				save(obj1);
			}
		}
		
		for(TblTOLeader obj2 :list2){
			if(!newString.contains(obj2.gettOLeader())){
				delete(obj2.getId());
			}
		}
	}

	public void updateAll(List<TblTOLeader> list) {
		for(TblTOLeader tol:list){
			update(tol);
		}
		
	}

	public List<TblSOLeaderJson> getTOLeader(Date startDate,Date endDate) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = "select ss.studyNo,ss.scheduleID,ss.taskName,tto.signId,tbluser.realName,ss.taskKind,ss.startTime from " +
				" (select distinct study.studyNo ,schedule.scheduleID,schedule.taskName ,schedule.taskKind,schedule.startTime from " +
				" CoresSchedule.dbo.tblSchedulePlan as schedule left join CoresStudy.dbo.tblStudyPlan " +
				" as study  on schedule.taskCode = study.studyNo and schedule.codeType =2 where " +
				"  schedule.endTime >= ? and schedule.startTime <=  ?  "+
				"and schedule.signId is not null and schedule.signId !='') as ss " +
				" left join CoresSchedule.dbo.tblTOLeader as tto on ss.scheduleID = tto.scheduleId 	and " +
				" ( ? between (case when tto.startDate is null then '2000-01-01' else tto.startDate end ) and 	" +
				" (case when tto.endDate is null then '2030-12-31' else tto.endDate end ) or tto.signId is null or tto.signId =''	" +
				" ) left join CoresUserPrivilege.dbo.tbluser on tbluser.userName = tto.tOLeader  ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, startDate).setParameter(1, endDate).setParameter(2, currentDate).list();
		List<TblSOLeaderJson> tollist = new ArrayList<TblSOLeaderJson>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				//System.out.println(objs[0]);//课题编号
				//System.out.println(objs[1]);//签字ID
				//System.out.println(objs[2]);//人员名称
				TblSOLeaderJson leader = new TblSOLeaderJson();
				leader.setId((String)objs[1]);
				leader.set_parentId((String)objs[0]);
				leader.setStudyNo((String)objs[0]);
				leader.setTaskName((String)objs[2]);
				leader.setSignId((String)objs[3]);
				leader.setSoleader((String)objs[4]);
				leader.setTaskKind((Integer)objs[5]);
				String date = DateUtil.dateToString((Date)objs[6], "yyyy-MM-dd");
				leader.setStartDate(date);
				tollist.add(leader);
			}
		}
		return tollist;
	}

	public boolean isExist(String scheduleId, String taskLeader,
			Date startDate, Date endDate) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql ="select count(id)"+
					" from dbo.tblTOLeader"+
					" where scheduleId =:scheduleId and tOLeader =:tOLeader and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("scheduleId", scheduleId)
						.setParameter("tOLeader", taskLeader)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.uniqueResult();
		
		return !(count==0);
	}

	public List<?> findUserNameRealNameByResIdPrivilegeName(String scheduleId,
			String privilegeName) {
			if(null ==scheduleId || scheduleId.equals("") ||
					null ==privilegeName || privilegeName.equals("") ){
				return null;
			}
			String datestr = DateUtil.getNow("yyyy-MM-dd");
			Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
			String sql = "select distinct tbluser.userName,tbluser.realName"+
						" from CoresUserPrivilege.dbo.tbluser as tbluser left join  CoresUserPrivilege.dbo.tbl_user_role as ur on tbluser.id = ur.userId"+ 
			" left join  CoresUserPrivilege.dbo.tblrole as tblrole on ur.roleId = tblrole.id "+
			" left join  CoresUserPrivilege.dbo.tbl_role_privilege as rp on tblrole.id = rp.roleId "+
			" left join  CoresUserPrivilege.dbo.tblprivilege as tblp on rp.privilegeId = tblp.id"+
			" left join CoresSchedule.dbo.tblTOLeader as tblrm on tbluser.userName = tblrm.tOLeader and tblrm.scheduleId =:scheduleId"+
			" where tblp.privilegeName =:privilegeName and tbluser.flag ='可用'  and "+
			" ( tblrm.scheduleId is null or tblrm.scheduleId='' or "+
			" 	("+
			" 		(tblrm.signid is not null and tblrm.signid !='') and ("+
			" 				:currentDate <(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
			" 				or"+
			" 				:currentDate>(case when tblrm.endDate is null then '2030-12-31'else tblrm.endDate end) )"+
			" 	) "+
			" 	or (tblrm.scheduleId is not null and tblrm.scheduleId ='' and (tblrm.signid is  null or tblrm.signid ='') )"+
				
			"  )";
			
			List<?> list = getSession().createSQLQuery(sql)
							.setParameter("scheduleId", scheduleId)
							.setParameter("privilegeName", privilegeName)
							.setParameter("currentDate", currentDate)
							.list();
			return list;
		}
	 
	public List<?> studyresanimalhouseresManagersoleader(String scheduleId,
			String studyNo) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = " select distinct tt.resManager,tbluser.realName"+
		"  from (select tsr.studyNo,house.parentId,trm.resManager"+
		"  		from CoresSchedule.dbo.tblStudyRes  as tsr left join CoresSystemSet.dbo.tblAnimalHouse as house on tsr.resId = house.id"+
		"  		 left join CoresSchedule.dbo.tblResManager as trm "+
		"  			on house.parentId = trm.resId "+
		"  		where tsr.studyNo = :studyNo and tsr.state>1 and trm.signid is not null and trm.signid !='' "+
		"  			and :currentDate between(case when trm.startDate is null then '2001-01-01'else trm.startDate end)"+
		"  								and"+
		"  								(case when trm.endDate is null then '2030-12-31'else trm.endDate end) ) as tt"+
		"  	left join CoresSchedule.dbo.tblSOLeader as tso on tso.studyNo = tt.studyNo and tso.soleader = tt.resManager and"+
		"  	(tso.signid is  null or tso.signid ='' or ("+
		"  						:currentDate between(case when tso.startDate is null then '2001-01-01'else tso.startDate end)"+
		"  						and"+
		"  						(case when tso.endDate is null then '2030-12-31'else tso.endDate end) )) "+
		"  	left join CoresUserPrivilege.dbo.tbluser on tt.resManager = tbluser.userName "+
		"  	left join CoresSchedule.dbo.tblTOLeader as tto on tt.resManager = tto.tOLeader and "+
		"  	(tto.signid is  null or tso.signid ='' or ("+
		"  						:currentDate between(case when tto.startDate is null then '2001-01-01'else tto.startDate end)"+
		"  						and"+
		"  						(case when tto.endDate is null then '2030-12-31'else tto.endDate end) )) and tto.scheduleId = :scheduleId"+
		"  where   (tto.tOLeader is null or tto.tOLeader = '') and ("+
		"  			("+
		"  				(tso.signid is not null and tso.signid !='') and ("+
		"  						:currentDate <(case when tso.startDate is null then '2001-01-01'else tso.startDate end)"+
		"  							or"+
		"  							:currentDate>(case when tso.endDate is null then '2030-12-31'else tso.endDate end) )"+
		"  				) "+
		"  				or ( (tso.signid is  null or tso.signid ='') )"+
		"  				) ";
		List<?> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("currentDate", currentDate).setParameter("scheduleId", scheduleId).list();
		System.out.println(list);
		return list;
	}

	public List<?> studyresresManagersoleader(String scheduleId,String studyNo) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = " select distinct tt.resManager,tbluser.realName "+
		"  		from (select tsr.studyNo,tsr.resId,trm.resManager"+
		"  				from CoresSchedule.dbo.tblStudyRes  as tsr left join CoresSchedule.dbo.tblResManager as trm "+
		"  					on tsr.resId = trm.resId"+
		"  				where tsr.studyNo = :studyNo and tsr.state>1 and trm.signid is not null and trm.signid !='' "+
		"  					and :currentDate between(case when trm.startDate is null then '2001-01-01'else trm.startDate end)"+
		"  										and"+
		"  										(case when trm.endDate is null then '2030-12-31'else trm.endDate end) ) as tt"+
		"  			left join CoresSchedule.dbo.tblSOLeader as tso on tso.studyNo = tt.studyNo and tso.soleader = tt.resManager and"+
		"  			(tso.signid is  null or tso.signid ='' or ("+
		"  								:currentDate between(case when tso.startDate is null then '2001-01-01'else tso.startDate end)"+
		"  								and"+
		"  								(case when tso.endDate is null then '2030-12-31'else tso.endDate end) )) "+
		"  			left join CoresUserPrivilege.dbo.tbluser on tt.resManager = tbluser.userName"+
		"  			left join CoresSchedule.dbo.tblTOLeader as tto on tt.resManager = tto.tOLeader and "+
		"  			(tto.signid is  null or tso.signid ='' or ("+
		"  								:currentDate between(case when tto.startDate is null then '2001-01-01'else tto.startDate end)"+
		"  								and"+
		"  								(case when tto.endDate is null then '2030-12-31'else tto.endDate end) )) and tto.scheduleId = :scheduleId"+
		"  		where (tto.tOLeader is null or tto.tOLeader = '') and ("+
		"  		 	("+
		"  						(tso.signid is not null and tso.signid !='') and ("+
		"  								:currentDate <(case when tso.startDate is null then '2001-01-01'else tso.startDate end)"+
		"  								or"+
		"  								:currentDate>(case when tso.endDate is null then '2030-12-31'else tso.endDate end) )"+
		"  					) "+
		"  					or ( (tso.signid is  null or tso.signid ='') ))";
	List<?> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("currentDate", currentDate)
	.setParameter("scheduleId", scheduleId).list();
		return list;
	}

	public List<TblSOLeaderJson> getBatchTOLeader(Date startDate, Date endDate,String sort,String order) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = "select ss.studyNo,ss.scheduleID,ss.taskName,tto.signId,tbluser.realName,ss.taskKind,ss.startTime from " +
				" (select distinct study.studyNo ,schedule.scheduleID,schedule.taskName ,schedule.taskKind,schedule.startTime from " +
				" CoresSchedule.dbo.tblSchedulePlan as schedule left join CoresStudy.dbo.tblStudyPlan " +
				" as study  on schedule.taskCode = study.studyNo and schedule.codeType =2 where " +
				//" study.studyStartDate between  ? and ?" +
				"  schedule.endTime >= ? and schedule.startTime <=  ?  "+
				" and schedule.signId is not null and schedule.signId !='') as ss " +
				" left join CoresSchedule.dbo.tblTOLeader as tto on ss.scheduleID = tto.scheduleId 	and " +
				" ( ? between (case when tto.startDate is null then '2000-01-01' else tto.startDate end ) and 	" +
				" (case when tto.endDate is null then '2030-12-31' else tto.endDate end ) or tto.signId is null or tto.signId =''	" +
				" ) left join CoresUserPrivilege.dbo.tbluser on tbluser.userName = tto.tOLeader    ";
		if(sort != null && !sort.equals("") && order != "" && !order.equals("")){
			sql = sql +"order by ss." +sort+" "+ order ;
		}else{
			sql = sql +"order by ss.studyNo "  ;
		}
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, startDate).setParameter(1, endDate).setParameter(2, currentDate).list();
		List<TblSOLeaderJson> tollist = new ArrayList<TblSOLeaderJson>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				TblSOLeaderJson leader = new TblSOLeaderJson();
				leader.setId((String)objs[1]);
				leader.setStudyNo((String)objs[0]);
				leader.setTaskName((String)objs[2]);
				leader.setSignId((String)objs[3]);
				leader.setSoleader((String)objs[4]);
				leader.setTaskKind((Integer)objs[5]);
				String date = DateUtil.dateToString((Date)objs[6], "yyyy-MM-dd");
				leader.setStartDate(date);
				tollist.add(leader);
			}
		}
		return tollist;
	}

	public List<?> getScheduleIdAndleaderName(List<String> scheduleIds,
			List<String> taskids) {
        String sql = " select a.scheduleID,b.taskLeader ,a.taskCode,a.taskName"+
        " from "+
        " (select schedule.scheduleID,schedule.taskKind,schedule.taskName ,schedule.taskCode"+
        " from CoresSchedule.dbo.tblSchedulePlan as schedule "+
        " where schedule.scheduleID in (:scheduleID)) as a "+
        " left join  "+
        " (select taskleader.id,taskleader.taskTypeID,taskleader.taskLeader,tasktype.taskKind,tasktype.taskName "+
        " from CoresSchedule.dbo.tblTaskLeader as taskleader left join CoresSystemSet.dbo.tblTaskType as tasktype "+
        " 	on tasktype.id = taskleader.taskTypeID "+
        " where taskleader.id in ( :taskleader)) as b on a.taskKind = b.taskKind and a.taskName = b.taskName  where b.taskLeader is not null ";
        List<?> list = getSession().createSQLQuery(sql).setParameterList("scheduleID", scheduleIds)
        .setParameterList("taskleader", taskids).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblTOLeader> getByScheduleIdListLeaderList(
			List<String> scheduleIds) {
		List<TblTOLeader> list=getSession().createQuery("FROM TblTOLeader  d where d.scheduleId in (:scheduleIds) and (  signId is null or signId = '' )")
		.setParameterList("scheduleIds", scheduleIds).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblTOLeader> getByDateAndLeader(Date startDate, Date endDate,
			String tOLeader) {
		List<TblTOLeader> list=getSession().createQuery("  FROM TblTOLeader as tl where tl.tOLeader = ? and "+
          "  ( tl.startDate <= ? and  (case when tl.endDate is null then convert(datetime,'2030-12-31',120)  else tl.endDate end)  >= ?  )    and tl.signId is not null  ")
          .setParameter(0, tOLeader).setParameter(1, endDate).setParameter(2, startDate).list();
		return list;
	}
	
}

	



