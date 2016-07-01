package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblResManager;
import com.lanen.util.DateUtil;
@Service
public class TblResManagerServiceImpl extends BaseDaoImpl<TblResManager> implements TblResManagerService{

	@SuppressWarnings("unchecked")
	public List<TblResManager> getByHouseId(String houseid) {
		String dateStr = DateUtil.getNow("yyyy-MM-dd");
		Date date = DateUtil.stringToDate(dateStr, "yyyy-MM-dd");
		
		List<TblResManager> list=	getSession()
									.createQuery("FROM TblResManager  d where resId = :resId and " +
										" startDate <= :currentDate and ( endDate is null or endDate >= :currentDate  )" )
									.setParameter("resId", houseid)
									.setParameter("currentDate", date)
									.list();
		return list;
	}

	public void saveAllresManager(List<TblResManager> resManagerList) {
		for(TblResManager obj : resManagerList){
			getSession().save(obj);
		}
	}

	public void updateResManager(List<TblResManager> resManagerList,
			List<TblResManager> resList) {
		String oldString ="";//原来负责人ID
		String newString ="";//新负责人
		for(TblResManager obj1 :resList){
			oldString = obj1.getResManager()+oldString;
		}
		for(TblResManager obj : resManagerList){
			newString = obj.getResManager() +newString;
		}
		for(TblResManager obj1 :resList){
			if(!newString.contains(obj1.getResManager())){
				delete(obj1.getId());
			}
			
		}
		for(TblResManager obj : resManagerList){
			if(!oldString.contains(obj.getResManager())){
				obj.setId(getKey("TblResManager"));
				save(obj);
			}
			
		}
	}

	public void updateAllresManager(List<TblResManager> list) {
		for(TblResManager obj : list){
			update(obj);
		}
		
	}

	public List<?> getDataListByHouseId(String currentResId) {
		String sql ="SELECT trm.id, tbluser.realName, trm.startDate, trm.endDate,tbles.signer,trm.resManager,tbles2.signer "+
					" FROM  dbo.tblResManager as trm left join CoresUserPrivilege.dbo.tbluser as tbluser "+
					" on trm.resManager = tbluser.userName left join CoresUserPrivilege.dbo.tblES  as tbles"+
					" on tbles.esId = trm.signid left join CoresUserPrivilege.dbo.tblES  as tbles2 "+
					" on tbles2.esId = trm.endDateSignId "+
					" WHERE   (resId = ?)";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, currentResId).list();
		return list;
	}

	public boolean isExist(String resId, String resManager, Date startDate,
			Date endDate) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql ="select count(id)"+
					" from dbo.tblResManager"+
					" where resId =:resId and resManager =:resManager and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("resId", resId)
						.setParameter("resManager", resManager)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.uniqueResult();
		
		return !(count==0);
	}

	public boolean isExist(String resId, String resManager, Date startDate,
			Date endDate, String id) {
		if(null == endDate){
			endDate = DateUtil.stringToDate("2030-12-31", "yyyy-MM-dd");
		}
		String sql ="select count(id)"+
					" from dbo.tblResManager"+
					" where resId =:resId and resManager =:resManager and (startDate <= :endDate and "+
					" (case when endDate is null then '2030-12-31' "+
					" else endDate end)  >= :startDate ) and id != :id";
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("resId", resId)
						.setParameter("resManager", resManager)
						.setParameter("startDate", startDate)
						.setParameter("endDate", endDate)
						.setParameter("id", id)
						.uniqueResult();
		
		return !(count==0);
	}

	public List<?> findUserNameRealNameByResIdPrivilegeName(
			String currentResId, String privilegeName) {
		if(null ==currentResId || currentResId.equals("") ||
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
		" left join CoresSchedule.dbo.tblResManager as tblrm on tbluser.userName = tblrm.resManager and tblrm.resId =:currentResId "+
		" and (tblrm.signid is  null or tblrm.signid ='' or ("+
		" :currentDate between(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
		" and"+
		" (case when tblrm.endDate is null then '2030-12-31'else tblrm.endDate end) ))"+
		" where tblp.privilegeName =:privilegeName and tbluser.flag ='可用'  and "+
		" ( tblrm.resId is null or tblrm.resId ='' or "+
		" 	("+
		" 		(tblrm.signid is not null and tblrm.signid !='') and ("+
		" 				:currentDate <(case when tblrm.startDate is null then '2001-01-01'else tblrm.startDate end)"+
		" 				or"+
		" 				:currentDate>(case when tblrm.endDate is null then '2030-12-31'else tblrm.endDate end) )"+
		" 	) "+
		" 	or (tblrm.resId is not null and tblrm.resId ='' and (tblrm.signid is  null or tblrm.signid ='') )"+
			
		"  )";
		
		List<?> list = getSession().createSQLQuery(sql)
						.setParameter("currentResId", currentResId)
						.setParameter("privilegeName", privilegeName)
						.setParameter("currentDate", currentDate)
						.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblResManager> getByResManager(String resManager) {
		List<TblResManager> list=	getSession()
		.createQuery("FROM TblResManager  d where resManager = :resManager " )
		.setParameter("resManager", resManager)
		.list();
        return list;
	}

	@SuppressWarnings("unchecked")
	public boolean getByResManagerAndResID(String resManager, String resId) {
		List<TblResManager> list=	getSession()
		.createQuery("FROM TblResManager  d where resManager = :resManager and resId = :resId  " )
		.setParameter("resManager", resManager).setParameter("resId", resId)
		.list();
		if(list != null && list.size() > 0){
			return true;
		}else{
		    return false;
		}
		
	}

	

}
