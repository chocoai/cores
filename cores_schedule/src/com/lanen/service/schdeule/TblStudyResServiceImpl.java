package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblStudyRes;
import com.lanen.util.DateUtil;

@Service
public class TblStudyResServiceImpl extends BaseDaoImpl<TblStudyRes> implements TblStudyResService{


	@SuppressWarnings("unchecked")
	public List<TblStudyRes> getAll(Date startDate , Date endDate) {
		List list=getSession().createSQLQuery("SELECT a.* " +
				" FROM [CoresSchedule].[dbo].[tblStudyRes] as a  left join [CoresStudy].[dbo].[tblStudyPlan]  as b ON  " +
				"a.studyNo = b.studyNo  WHERE  b.studyBeginDate between ? and ? ")
		.setParameter(0, startDate).setParameter(1, endDate).list();
		List<TblStudyRes> studyReslist = new ArrayList<TblStudyRes>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblStudyRes tblStudyRes = new TblStudyRes();
				Object[] objs = (Object[]) list.get(i);
				tblStudyRes.setId((String) objs[0]);
				tblStudyRes.setStudyNo((String) objs[1]);
				tblStudyRes.setResId((String) objs[2]);
				tblStudyRes.setSignId((String)objs[3]);
				tblStudyRes.setState((Integer) objs[4]);
				tblStudyRes.setAuditId((String)objs[5]);
				studyReslist.add(tblStudyRes);
			}
		}
		return studyReslist;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblStudyRes> getAllByState(int state) {
		List<TblStudyRes> list=getSession().createQuery("FROM TblStudyRes t where t.state = ? ").setParameter(0, state).list();
		return list;
	}

	public void saveAllStudyRes(List<TblStudyRes> studyResList) {
		for(TblStudyRes studyRes:studyResList){
			//studyRes.setId(getKey("TblStudyRes"));
			getSession().save(studyRes);
		}
	}

	public void updateAll(List<TblStudyRes> list) {
        for(TblStudyRes studyRes:list){
        	getSession().update(studyRes);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyRes> getByStudyNo(String StudyNo) {
		List<TblStudyRes> list=getSession().createQuery("FROM TblStudyRes t where t.studyNo = ? ").setParameter(0, StudyNo).list();
		return list;
	}

	public void detAllStudyRes(List<String> list) {
		for(String id: list){
			delete(id);
		}
    }

	public List<?> getResNameSignerByStudyNo(String studyNo) {
		String sql ="select sr.id,sr.studyNo, house.resName,(case when es.signer is null then '' else es.signer end)as signer"+
					" from CoresSchedule.dbo.tblStudyRes as sr "+
					" left join "+
					" CoresSystemSet.dbo.tblAnimalHouse as house on sr.resId = house.id left join "+
					" CoresUserPrivilege.dbo.tblES as es on sr.signId = es.esId"+
					"  where sr.studyNo = :studyNo ";
		List<?> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).list();
		return list;
	}  


	
	public List<?> findUserNameRealNameByResIdPrivilegeName(
			String studyNo, String privilegeName) {
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
		" left join CoresSchedule.dbo.tblStudyRes as tblrm on tbluser.userName = tblrm.soleader and tblrm.studyNo =:studyNo"+
		" where tblp.privilegeName =:privilegeName and tbluser.flag ='可用'  and "+
		" ( tblrm.studyNo is null or tblrm.studyNo='' or "+
		" 	("+
		" 		(tblrm.signid is not null and tblrm.signid !='') and ("+
		" 				:currentDate <(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
		" 				or"+
		" 				:currentDate>(case when tblrm.endDate is null then '2001-01-01'else tblrm.endDate end) )"+
		" 	) "+
		" 	or (tblrm.studyNo is not null and tblrm.studyNo ='' and (tblrm.signid is  null or tblrm.signid ='') )"+
			
		"  )";
		
		List<?> list = getSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setParameter("privilegeName", privilegeName)
						.setParameter("currentDate", currentDate)
						.list();
		return list;
	}

	public List<?> findUserNameRealNameByResId(String studyNo) {
		String sql = " select res.studyNo,res.resId,tbluser.realName from " +
				" CoresUserPrivilege.dbo.tbluser  as tbluser right join  " +
				" ( select  studyRes.[id],studyRes.[studyNo],studyRes.[resId],studyRes.[signId],resMan.[resManager],resMan.[signid] " +
				" as resSignid FROM [CoresSchedule].[dbo].[tblStudyRes] as studyRes left join [CoresSchedule].[dbo].[tblResManager] " +
				"as resMan  on  studyRes .[resId] = resMan .[resId] where [studyNo] =? ) as res on  tbluser.userName = res.resManager";
		List<?> list = getSession().createSQLQuery(sql)
		.setParameter("studyNo", studyNo)
		.list();
		return list;
	}

	public boolean isExist(String studyNo, String currentResId) {
		String hql ="From TblStudyRes where studyNo = ? and resId = ? ";
		List<?> list = getSession().createQuery(hql)
					.setParameter(0, studyNo)
					.setParameter(1, currentResId)
					.list();
		if(null != list && list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public String getAduitByStudyNo(String studyNo) {
		String hql ="From TblStudyRes where studyNo = ? and state = 3 and  auditId is not null and auditId !='' ";
		List<TblStudyRes> list = getSession().createQuery(hql)
					.setParameter(0, studyNo)
					.list();
		if(null != list && list.size()>0){
			return list.get(0).getAuditId();
		}
		return "";
	}

	public boolean isExistThisOne(String studyNo, String userName) {
		if(null ==studyNo || studyNo.equals("") ||
				null ==userName || userName.equals("") ){
			return false;
		}
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql = "	 select * from (  "+
		"			 select house.id from ( "+
		"				 (select tsr.resId  from CoresSchedule.dbo.tblStudyInfo as tsr where tsr.studyNo = :studyNo ) as sinfo left join "+
		"				      CoresSystemSet.dbo.tblAnimalHouse as house on sinfo.resID = house.id or house.parentId = '' or house.parentId is null "+
		"					  )) as ahouse left join CoresSchedule.dbo.tblResManager as trm  "+
	    "				 on ahouse.id = trm.resId and trm.signid is not null and trm.signid !='' and   "+
	    "			:currentDate between(case when trm.startDate is null then '2001-01-01' else trm.startDate end)  "+
	    "		 and (case when trm.endDate is null then '2030-12-31'else trm.endDate end )    "+
		"	 where trm.resManager = :userName ";
//			"select * "+ 
//					" from	(select tsr.resId ,house.parentId as parent1 ,house2.parentId as parent2,trm.resManager,trm2.resManager as resManager2 "+ 
//					" from CoresSchedule.dbo.tblStudyRes as tsr left join CoresSystemSet.dbo.tblAnimalHouse as house  "+ 
//					" on house.id = tsr.resId  and house.resKind >1 left join CoresSystemSet.dbo.tblAnimalHouse as house2  "+ 
//					" on house2.id = house.parentId and house2.resKind>1 left join CoresSchedule.dbo.tblResManager as trm "+ 
//					" on house.parentId = trm.resId and trm.signid is not null and trm.signid !='' and  "+ 
//					" :currentDate between(case when trm.startDate is null then '2001-01-01'else trm.startDate end) "+ 
//					" and "+ 
//					" (case when trm.endDate is null then '2030-12-31'else trm.endDate end)  "+ 
//					" left join  CoresSchedule.dbo.tblResManager as trm2 "+ 
//					" on house2.parentId = trm2.resId and trm2.signid is not null and trm2.signid !='' and  "+ 
//					" 	:currentDate between(case when trm2.startDate is null then '2001-01-01'else trm2.startDate end) "+ 
//					" 	and "+ 
//					" 	(case when trm2.endDate is null then '2030-12-31'else trm2.endDate end)  "+ 
//                    " where tsr.studyNo = :studyNo and tsr.state = 2) as temp "+ 
//                    " where temp.resManager = :userName or temp.resManager2 = :userName "; 
		List<?> list = getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setParameter("userName", userName)
									.setParameter("currentDate", currentDate)
									.list();
		if(null != list && list.size()>0)
			return true;
		return false;
	}

	public boolean isExistThisStudyNoAndUser(String studyNo, String userName) {
		String datestr = DateUtil.getNow("yyyy-MM-dd");
		Date currentDate = DateUtil.stringToDate(datestr, "yyyy-MM-dd");
		String sql =" select tbsl.resID from tblStudyInfo as tbsl left join tblResManager as tblr "+
		" on tbsl.resID = tblr.resId "+
		" where  tbsl.studyNo = :studyNo and tblr.resManager = :userName and "+
		" ( :currentDate between (case when tblr.startDate is null then '2000-01-01' else tblr.startDate end ) and "+ 	
		" 	(case when tblr.endDate is null then '2030-12-31' else tblr.endDate end ))";
		List<?> list = getSession().createSQLQuery(sql)
		.setParameter("studyNo", studyNo)
		.setParameter("userName", userName)
		.setParameter("currentDate", currentDate)
		.list();
		if(null != list && list.size()>0)
		return true;
		return false;
	}
	
	
	

}
