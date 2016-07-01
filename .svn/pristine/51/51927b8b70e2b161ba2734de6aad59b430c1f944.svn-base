package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.schedule.TblTaskLeader;
import com.lanen.model.schedule.TblTaskLeaderJson;
import com.lanen.util.DateUtil;
@Service
public class TblTaskLeaderServiceImpl extends BaseDaoImpl<TblTaskLeader> implements TblTaskLeaderService{

	public void saveAllTaskLeader(List<TblTaskLeader> list) {
		 for(TblTaskLeader taskLeader:list){
				getSession().save(taskLeader);
			}
		
	}

	
	public void delAllTaskLeader(List<String> list) {
		for(String userid:list){
			delete(userid);
		}
	}

	public void updateAllTaskLeader(List<TblTaskLeader> list) {
		for(TblTaskLeader taskLeader:list){
			update(taskLeader);
		}
	}


	@SuppressWarnings("unchecked")
	public List<Date> selectALLEndDate(String taskTypeID, String taskLeader) {
		List<Date> list= getSession().createSQLQuery("select t.endDate  FROM TblTaskLeader t where t.taskLeader = ? and t.taskTypeID = ? ")
		.setParameter(0, taskLeader).setParameter(1, taskTypeID).list();
		if( null == list ||list.size()<1 ){
			return null;
		}else{
			return list;
		}
	}


	@SuppressWarnings("unchecked")
	public List<TblTaskLeaderJson> getByPrivilege(String privilege) {
		//getByPrivilegeTaskLeader存储过程
		Query query = getSession().createSQLQuery("{Call getByPrivilegeTaskLeader(?)}");
		query.setParameter(0, privilege );
		List<Object> list =query.list(); 
		List<TblTaskLeaderJson> taskLeaderlist = new ArrayList<TblTaskLeaderJson>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
//				System.out.println((String)objs[0]);//taskleader id
//				System.out.println((String)objs[1]);//taskType id
//				System.out.println((String)objs[2]);//用户名
//				System.out.println((Integer)objs[3]);//任务类别
//				System.out.println((String)objs[4]);//任务名称
				//System.out.println((String)objs[5]);//有效
//				System.out.println((Integer)objs[6]);//可见范围
//				System.out.println((String)objs[7]);//人名
//				System.out.println((Date)objs[8]);//开始时间
//				System.out.println((Date)objs[9]);//结束时间
//				System.out.println((String)objs[10]);//签字
				TblTaskLeaderJson json =new TblTaskLeaderJson();
				json.setId((String)objs[0]);
				json.setTtid((String)objs[1]);
				json.set_parentId((Integer)objs[3]+"");
				json.setTaskKind((String)objs[4]);
				json.setCanSee((Integer)objs[6]+"");
				json.setTaskLeader((String)objs[7]);
				json.setStartDate((Date)objs[8]);
				json.setEndDate((Date)objs[9]);
				json.setSignId((String)objs[10]);
				taskLeaderlist.add(json);
			}
			
		}
	    return taskLeaderlist;
	}


	@SuppressWarnings("unchecked")
	public List<TblTaskLeader> getbyTaskid(String taskid) {
//		List<TblTaskLeader> list = getSession().createQuery("FROM TblTaskLeader t WHERE t.taskTypeID = ? ").
//     setParameter(0, taskid).list();
		List<TblTaskLeader> list=	getSession()
									.createQuery("FROM TblTaskLeader d where taskTypeID = :taskTypeID " )
									.setParameter("taskTypeID", taskid)
									.list();
		return list;
	}


	public List<?> findUserNameRealNameByResIdPrivilegeName(
			String taskTypeID, String privilegeName) {
		if(null ==taskTypeID || taskTypeID.equals("") ||
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
		" left join CoresSchedule.dbo.tblTaskLeader as tblrm on tbluser.userName = tblrm.taskLeader and tblrm.taskTypeID =:taskTypeID " +
		"  and (tblrm.signid is  null or tblrm.signid ='' or ( :currentDate between(case when tblrm.startDate is null then '2001-01-01' " +
		" else tblrm.startDate end) and (case when tblrm.endDate is null then '2030-12-31'else tblrm.endDate end) ))" +
		" where tblp.privilegeName =:privilegeName and tbluser.flag ='可用'  and "+
		" ( tblrm.taskTypeID is null or tblrm.taskTypeID ='' or "+
		" 	("+
		" 		(tblrm.signid is not null and tblrm.signid !='') and ("+
		" 				:currentDate <(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
		" 				or"+
		" 				:currentDate>(case when tblrm.endDate is null then '2001-01-01'else tblrm.endDate end) )"+
		" 	) "+
		" 	or (tblrm.taskTypeID is not null and tblrm.taskTypeID ='' and (tblrm.signid is  null or tblrm.signid ='') )"+
			
		"  )";
		
		List<?> list = getSession().createSQLQuery(sql)
						.setParameter("taskTypeID", taskTypeID)
						.setParameter("privilegeName", privilegeName)
						.setParameter("currentDate", currentDate)
						.list();
		return list;
	}


	public boolean isExist(String taskTypeID, String taskLeader, Date startDate,
			Date endDate) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql ="select count(id)"+
					" from dbo.tblTaskLeader"+
					" where taskTypeID =:taskTypeID and taskLeader =:taskLeader and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("taskTypeID", taskTypeID)
						.setParameter("taskLeader", taskLeader)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.uniqueResult();
		
		return !(count==0);
	}


	@SuppressWarnings("unchecked")
	public List<TblTaskLeaderJson> getByTaskNameTaskLeader(Set<String> taskNameList) {
		String sql = " select tp.id as tid,tp.taskName,tp.taskKind,tl.* from CoresSystemSet.dbo.tblTaskType as tp left join CoresSchedule.dbo.tblTaskLeader as tl "+
       " on tp.id = tl.taskTypeID  where tp.taskName in (:taskNameList) and tl.id is not null and "+
       " ( :currentDate between (case when tl.startDate is null then '2000-01-01' else tl.startDate end ) and  "+
       "	(case when tl.endDate is null then '2030-12-31' else tl.endDate end ))";
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
									.setParameterList("taskNameList", taskNameList)
									.setParameter("currentDate", currentDate)
									.setResultTransformer(new MapResultTransformer())
									.list();
		//存放最终结果
		List<TblTaskLeaderJson> jsonlist =new ArrayList<TblTaskLeaderJson>();
		for(Map<String,Object> map :list){
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			json.setId((String)map.get("id"));
			//json.setTaskKind((String)map.get("taskKind"));
			json.setTaskName((String)map.get("taskName"));
			json.setTaskLeader((String)map.get("taskLeader"));
			jsonlist.add(json);
		}
		return jsonlist;
	}


	@SuppressWarnings("unchecked")
	public String getNoTaskLeaderByTaskName(Set<String> taskNameList) {
		String sql = " select tp.id as tid,tp.taskName,tp.taskKind,tl.* "+
        "  from  CoresSystemSet.dbo.tblTaskType as tp  left join CoresSchedule.dbo.tblTaskLeader as tl   "+
        "      on tp.id = tl.taskTypeID  where tp.taskName in (:taskNameList)   "+
        "    and tl.id is null  and  "+
        " ( :currentDate between (case when tl.startDate is null then '2000-01-01' else tl.startDate end ) and  "+
        "	(case when tl.endDate is null then '2030-12-31' else tl.endDate end ))";
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
		.setParameterList("taskNameList", taskNameList)
		.setParameter("currentDate", currentDate)
		.setResultTransformer(new MapResultTransformer())
		.list();
		String taskNames = "";
		for(Map<String,Object> map :list){
			if(taskNames != ""){
				taskNames =taskNames +",";
			}
			taskNames =taskNames +(String)map.get("taskName");
		}
		return taskNames;
	}


	@SuppressWarnings("unchecked")
	public List<TblTaskLeaderJson> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(
			Date startDate, Date endDate, int taskKind, int codeType,
			int taskType) {
		Query query = getSession().createSQLQuery("{Call getScheduleLeaderNamebyDateTaskKindCodeTypeTaskTypeNew(?,?,?,?,?)}");
		query.setParameter(0, startDate );
		query.setParameter(1, endDate);
		query.setParameter(2, taskKind);
		query.setParameter(3, codeType);
		query.setParameter(4, taskType);
		
		List<Map<String,Object>> datalist =query.setResultTransformer(new MapResultTransformer()).list();
		//存放最终结果
		List<TblTaskLeaderJson> jsonlist =new ArrayList<TblTaskLeaderJson>();
		for(Map<String,Object> map :datalist){
			TblTaskLeaderJson json = new TblTaskLeaderJson();
			json.setTime(DateUtil.dateToString((Date)map.get("date"), "yyyy-mm-dd"));
			json.setTaskName((String)map.get("taskName"));
			json.setTaskLeader((String)map.get("leaderName"));
			jsonlist.add(json);
		}
		return jsonlist;
	}

}
