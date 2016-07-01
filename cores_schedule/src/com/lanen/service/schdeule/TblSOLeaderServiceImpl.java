package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblSOLeader;
import com.lanen.util.DateUtil;
@Service
public class TblSOLeaderServiceImpl extends BaseDaoImpl<TblSOLeader>  implements TblSOLeaderService {

	@SuppressWarnings("unchecked")
	public List<TblSOLeader> getAll() {
		List<TblSOLeader> list=getSession().createQuery("FROM TblSOLeader ").list();
		return list;
	}

	public void saveAllSOLeader(List<TblSOLeader> list) {
		for(TblSOLeader soLeader:list){
			save(soLeader);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TblSOLeader> getByStudyNo(String StudyNo) {
		List<TblSOLeader> list=getSession().createQuery("FROM TblSOLeader t where t.studyNo = ?  ").setParameter(0, StudyNo).list();
		return list;
	}

	public void updateAllSOLeader(List<TblSOLeader> list1,List<TblSOLeader> list2) {
		String oldString ="";//原来负责人ID
		String newString ="";//新负责人
		for(TblSOLeader obj2 :list2){
			oldString = obj2.getSoleader()+oldString;
		}
		for(TblSOLeader obj1 : list1){
			newString = obj1.getSoleader() +newString;
		}
		for(TblSOLeader obj2 :list2){
			if(!newString.contains(obj2.getSoleader())){
				delete(obj2.getId());
			}
		}
		for(TblSOLeader obj1 :list1){
			if(!oldString.contains(obj1.getSoleader())){
				obj1.setId(getKey("TblSOLeader"));
				save(obj1);
			}
		}
	}

	public void updateAll(List<TblSOLeader> list) {
		for(TblSOLeader obj:list){
			update(obj);
		}
		
	}

	public List<TblSOLeader> getBySOlList(Date startDate,Date endDate) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = " select ss.studyNo ,tso.signId,tbluser.realName,tso.taskKind from (select distinct " +
				" study.studyNo from CoresSchedule.dbo.tblSchedulePlan as schedule left join " +
				" CoresStudy.dbo.tblStudyPlan as study on schedule.taskCode = study.studyNo and " +
				" schedule.codeType =2 where " +
				//" study.studyStartDate between ? and ? " +
				"  schedule.endTime >= ? and schedule.startTime <=  ?  "+
				" and schedule.signId is not null and schedule.signId !='') as ss left join CoresSchedule.dbo.tblSOLeader " +
				" as tso on ss.studyNo = tso.studyNo and ( ? between (case when tso.startDate is null then " +
				" '2000-01-01' else tso.startDate end ) and  (case when tso.endDate is null then '2030-12-31' else tso.endDate end ) " +
				"or tso.signId is null or tso.signId ='' ) left join CoresUserPrivilege.dbo.tbluser as tbluser on tso.soleader = tbluser.userName  ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, startDate).setParameter(1, endDate).setParameter(2, currentDate).list();
		List<TblSOLeader> sollist = new ArrayList<TblSOLeader>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				TblSOLeader leader = new TblSOLeader();
				leader.setId((String)objs[0]);
				leader.setSignId((String)objs[1]);
				leader.setSoleader((String)objs[2]);
				leader.setStudyNo((String)objs[0]);
				if(null != objs[3] && (!objs[3].equals(""))){
					leader.setTaskKind((Integer)objs[3]);
				}
			
				sollist.add(leader);
			}
			
		}
		return sollist;
	}

	public List<?> studyresanimalhouseresManagersoleader(
			String studyNo) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = " select distinct tt.resManager,tbluser.realName from (select tsr.studyNo,tsr.resId,trm.resManager from" +
				"  CoresSchedule.dbo.tblStudyRes  as tsr left join CoresSchedule.dbo.tblResManager as trm 	on tsr.resId = trm.resId" +
				"  where tsr.studyNo = :studyNo and tsr.state>1 and trm.signid is not null and trm.signid !='' " +
				" and :currentDate between(case when trm.startDate is null then '2001-01-01'else trm.startDate end) and " +
				" (case when trm.endDate is null then '2030-12-31'else trm.endDate end) ) as tt	left join CoresSchedule.dbo.tblSOLeader as tso " +
				" on tso.studyNo = tt.studyNo and tso.soleader = tt.resManager and	(tso.signid is  null or tso.signid ='' or (	:currentDate " +
				" between(case when tso.startDate is null then '2001-01-01'else tso.startDate end)	and	(case when tso.endDate is null then " +
				" '2030-12-31'else tso.endDate end) )) 	left join CoresUserPrivilege.dbo.tbluser on tt.resManager = tbluser.userName where " +
				" tso.soleader is  null or  tso.soleader ='' and ( " +
				" ((tso.signid is not null and tso.signid !='') and ( :currentDate <(case when tso.startDate is null then '2001-01-01'else" +
				"  tso.startDate end)	or :currentDate>(case when tso.endDate is null then '2030-12-31'else tso.endDate end) )) or " +
				" ( (tso.signid is  null or tso.signid ='') ) )";
		List<?> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("currentDate", currentDate).list();
		return list;
	}

	public List<?> studyresresManagersoleader(String studyNo) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = " select distinct tt.resManager,tbluser.realName " +
				" from (select tsr.studyNo,house.parentId,trm.resManager " +
				" from CoresSchedule.dbo.tblStudyRes  as tsr left join CoresSystemSet.dbo.tblAnimalHouse as house on tsr.resId = house.id " +
				" left join CoresSchedule.dbo.tblResManager as trm 	on house.parentId = trm.resId " +
				" where tsr.studyNo = :studyNo and tsr.state>1 and trm.signid is not null and trm.signid !='' " +
				" and :currentDate between(case when trm.startDate is null then '2001-01-01'else trm.startDate end) and	" +
				" (case when trm.endDate is null then '2030-12-31'else trm.endDate end) ) as tt " +
				" left join CoresSchedule.dbo.tblSOLeader as tso on tso.studyNo = tt.studyNo and tso.soleader = tt.resManager and " +
				" (tso.signid is  null or tso.signid ='' or ( :currentDate between(case when tso.startDate is null then " +
				" '2001-01-01'else tso.startDate end) and (case when tso.endDate is null then '2030-12-31'else tso.endDate end) )) " +
				" left join CoresUserPrivilege.dbo.tbluser on tt.resManager = tbluser.userName where tso.soleader is  null or  tso.soleader ='' and ( ( " +
				" (tso.signid is not null and tso.signid !='') and ( " +
				" :currentDate <(case when tso.startDate is null then '2001-01-01'else tso.startDate end) or " +
				" :currentDate>(case when tso.endDate is null then '2030-12-31'else tso.endDate end) ) )  " +
				" or ( (tso.signid is  null or tso.signid ='') ) )";
	List<?> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("currentDate", currentDate).list();
		return list;
	}

	public List<?> findUserNameRealNameByResIdPrivilegeName(
			String studyNo, String privilegeName,int taskKind) {
		if(null ==studyNo || studyNo.equals("") ||
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
		" left join CoresSchedule.dbo.tblSOLeader as tblrm on tbluser.userName = tblrm.soleader and tblrm.studyNo =:studyNo and tblrm.taskKind = :taskKind"+
		" where tblp.privilegeName =:privilegeName and tbluser.flag ='可用'  and "+
		" ( tblrm.studyNo is null or tblrm.studyNo ='' or "+
		" 	("+
		" 		(tblrm.signid is not null and tblrm.signid !='') and ("+
		" 				:currentDate <(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
		" 				or"+
		" 				:currentDate>(case when tblrm.endDate is null then '2030-12-31'else tblrm.endDate end) )"+
		" 	) "+
		" 	or (tblrm.studyNo is not null and tblrm.studyNo ='' and (tblrm.signid is  null or tblrm.signid ='') )"+
			
		"  )";
		
		List<?> list = getSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setParameter("privilegeName", privilegeName)
						.setParameter("currentDate", currentDate)
						.setParameter("taskKind", taskKind)
						.list();
		return list;
	}

	public boolean isExist(String studyNo, String taskLeader, Date startDate,
			Date endDate,int taskKind) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql ="select count(id)"+
					" from dbo.tblSOLeader"+
					" where studyNo =:studyNo and soleader =:soleader and taskKind = :taskKind and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setParameter("soleader", taskLeader)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.setParameter("taskKind", taskKind)
						.uniqueResult();
		
		return !(count==0);
	}
	
	

}
