package com.lanen.service.studyplan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.util.DateUtil;



@Service
public class TblAnimalServiceImpl extends BaseDaoImpl<TblAnimal> implements TblAnimalService {

	@SuppressWarnings("unchecked")
	public List<TblAnimal> getByStudyNo(TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan = ? ORDER BY ( case when t.animalCode ='' or t.animalCode is NULL then '9999' else t.animalCode  end ) , t.aniSerialNum ").setParameter(0, tblStudyPlan).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblAnimal> getNoDieByStudyNo(TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan = ? And t.deadFlag = 0 ORDER BY ( case when t.animalCode ='' or t.animalCode is NULL then '9999' else t.animalCode  end ) , t.aniSerialNum ").setParameter(0, tblStudyPlan).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblAnimal> getNoDieANDTodayDieByStudyNo(
			TblStudyPlan tblStudyPlan) {
//	     Calendar cal = Calendar.getInstance();
//	     cal.set(Calendar.HOUR_OF_DAY, 23);
//	     cal.set(Calendar.SECOND, 59);
//	     cal.set(Calendar.MINUTE, 59);
//	     cal.set(Calendar.MILLISECOND, 998);
//	     cal.add(Calendar.DATE, -1);
//		 Date date = cal.getTime();
	     Date date = DateUtil.getYesterday();
		return getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan = ?  " +
				"  And ( t.deadFlag = 0  OR ( t.deadFlag > 0 AND t.deadDate >  ? )) " +
				" ORDER BY ( case when t.animalCode ='' or t.animalCode is NULL then '9999' else t.animalCode  end ) , t.aniSerialNum ").setParameter(0, tblStudyPlan).setParameter(1,date).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblAnimal> getNoDieByAnimalSum(TblStudyPlan tblStudyPlan) {
		 return getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan = ? And t.deadFlag >= 1 ORDER BY ( case when t.animalCode ='' or t.animalCode is NULL then '9999' else t.animalCode  end ) , t.aniSerialNum ")
		 .setParameter(0, tblStudyPlan).list();
	}
	@SuppressWarnings("unchecked")
	public List<String> getByStudyNo() {
		List<String> list = getSession().createQuery("Select distinct t.deadReason FROM TblAnimal t where t.deadReason != null ").list();
		if(list != null){
			return list;
		}else{
			return null;
		}
		
	}
	public void saveAllAnimals(List<TblAnimal> animalList) {
		for(TblAnimal obj : animalList){
			obj.setId(getKey("TblAnimal"));
			save(obj);
		}
	}

	public void deleteAnimals(List<TblAnimal> animalList) {
		for(TblAnimal obj : animalList){
			delete(obj.getId());
		}
	}

	public void updateAnimals(List<TblAnimal> animalList) {
		for(TblAnimal obj : animalList){
			update(obj);
		}
	}

	@SuppressWarnings("unchecked")
	public TblAnimal getByStudyPlanAndAnimalId(TblStudyPlan tblStudyPlan, String animalId) {
		List<TblAnimal>retList = getSession().createQuery("FROM TblAnimal WHERE tblStudyPlan=? AND animalId=?")
								.setParameter(0, tblStudyPlan).setParameter(1, animalId).list();
		if(retList!=null&&retList.size()>0){
			return retList.get(0);
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimal> getByStudyNoWithPageRows(TblStudyPlan tblStudyPlan,
			int page, int rows) {
		if(page==0 ||rows==0){
			page=1;
			rows =2000;
		}
		List<TblAnimal> list = getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan=? Order By  case  when t.animalCode is NULL or t.animalCode ='' then '9999' else t.animalCode end , t.aniSerialNum")
		.setParameter(0, tblStudyPlan)//
		.setFirstResult((page-1)*rows)//
		.setMaxResults(page*rows)
		.list();
		return list;
	}

	public Long getTotalByStudyPlan(TblStudyPlan tblStudyPlan) {
		return (Long) getSession().createQuery("select count(*) FROM TblAnimal t WHERE t.tblStudyPlan=? ")//
		.setParameter(0, tblStudyPlan)//
		.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<String> getAnimalCodeByStudyNo(TblStudyPlan tblStudyPlan) {
		List<String> list = getSession().createQuery("select t.animalCode FROM TblAnimal t WHERE t.tblStudyPlan=? ").setParameter(0, tblStudyPlan).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimal> getByStudyNoDiePageRows(TblStudyPlan tblStudyPlan,
			int page, int rows) {
		if(page==0 ||rows==0){
			page=1;
			rows =2000;
		}
		List<TblAnimal> list = getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan=? And t.deadFlag = 0    Order By  case  when t.animalCode is NULL or t.animalCode ='' then '9999' else t.animalCode end , t.aniSerialNum")
		.setParameter(0, tblStudyPlan)//
		.setFirstResult((page-1)*rows)//
		.setMaxResults(page*rows)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getAnimalsListForStudyNo(TblStudyPlan tblStudyPlan) {
		List<String> animalist = getSession().createQuery("select t.animalCode FROM TblAnimal t WHERE t.tblStudyPlan=? ").setParameter(0, tblStudyPlan).list();
		String StudyNO = tblStudyPlan.getStudyNo();
		String sign = null;
		List<Integer> countid = getSession().createQuery("select count(id) FROM TblAnimal t WHERE t.tblStudyPlan=? AND t.deadFlag = 0 ").setParameter(0, tblStudyPlan).list();
		try {
			if(animalist.size() <= 0  ){
				Query query = getSession().createSQLQuery("{Call updateAnimalStudyNo(?)}");
				query.setParameter(0, StudyNO);
				query.executeUpdate();
				List<String> animalist1 = getSession().createQuery("select t.animalCode FROM TblAnimal t WHERE t.tblStudyPlan=? ").setParameter(0, tblStudyPlan).list();
				if( animalist1.size() > 0 ){
				sign =  "add";
				}
			}else{
				try {
					Query query = getSession().createSQLQuery("{Call updateDifferentAnimalStudyNo(?)}");
					query.setParameter(0, StudyNO);
					query.executeUpdate();
					List<Integer> countid2 = getSession().createQuery("select count(id) FROM TblAnimal t WHERE t.tblStudyPlan=? AND t.deadFlag = 0 ").setParameter(0, tblStudyPlan).list();
					if(countid.get(0) != countid2.get(0)){
				    	sign = "update";
				    }
				} catch (Exception e) {
					sign = null;
				}
				
			}
		} catch (Exception e) {
			sign = null;
		}
		
		return sign;
	}

	@SuppressWarnings("unchecked")
	public TblAnimal getByStudyPlanAnimalCode(TblStudyPlan tblStudyPlan,
			String animalIdStr) {
		String hql ="From TblAnimal where tblStudyPlan = :tblStudyPlan and animalCode = :animalIdStr ";
		List<TblAnimal> list = getSession().createQuery(hql)
					.setParameter("tblStudyPlan", tblStudyPlan)
					.setParameter("animalIdStr", animalIdStr)
					.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public TblAnimal getByStudyPlanAnimalId(TblStudyPlan tblStudyPlan,
			String animalIdStr) {
		String hql ="From TblAnimal where tblStudyPlan = :tblStudyPlan and animalId = :animalIdStr ";
		List<TblAnimal> list = getSession().createQuery(hql)
					.setParameter("tblStudyPlan", tblStudyPlan)
					.setParameter("animalIdStr", animalIdStr)
					.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public TblAnimal getByStudyPlanAnimalCode2(TblStudyPlan tblStudyPlan,
			String animalCode) {
		String sql="select ara.id " +
				   " from CoresStudy.dbo.tblAnatomyReqAnimalList as ara " +
				   " join CoresStudy.dbo.tblAnatomyReq as ar "+
			       " on ara.studyNo=ar.studyNo and ara.anatomyReqNo=ar.reqNo " +
			       " where ar.submitFlag>0 and ar.studyNo=:studyNo and ara.animalCode=:animalCode";
		//用于判断是否已被提交申请解剖(0:未被申请，1.已被申请)
		int isHaveAnatomyReq=0;
		    if(tblStudyPlan!=null){
		    	List<?> sqlList=getSession().createSQLQuery(sql).setParameter("studyNo", tblStudyPlan.getStudyNo())
							                .setParameter("animalCode", animalCode)
							                .list();
				if(sqlList!=null&&sqlList.size()>0){
				    isHaveAnatomyReq=1;
                }
		    }
		TblAnimal tblAnimal=null;
		if(isHaveAnatomyReq==0){
			String hql="From TblAnimal where tblStudyPlan = :tblStudyPlan and animalCode = :animalCode";
			List<TblAnimal> list = getSession().createQuery(hql)
												.setParameter("tblStudyPlan", tblStudyPlan)
												.setParameter("animalCode", animalCode)
												.list();
			if(null != list && list.size()>0){
				tblAnimal=list.get(0);
			}
		}
		return tblAnimal;
	}

	@SuppressWarnings("unchecked")
	public TblAnimal getByStudyPlanAnimalId2(TblStudyPlan tblStudyPlan,
			String animalId) {
		String sql="select  reqAnimal.araId from "+
				   "(select ara.id as araId ,ara.studyNo as araStudyNo ,ara.anatomyReqNo as araReqNo "+
				   "     from CoresStudy.dbo.tblAnimal as a "+ 
				   "     join CoresStudy.dbo.tblAnatomyReqAnimalList as ara  "+
				   "     on a.animalCode=ara.animalCode and a.studyNo=ara.studyNo "+
				   "     where a.animalId=:animalId and a.studyNo=:studyNo" +
				   " ) as reqAnimal "+
				   " join  CoresStudy.dbo.tblAnatomyReq as ar  "+
				   " on reqAnimal.araStudyNo=ar.studyNo and reqAnimal.araReqNo=ar.reqNo  "+
				   " where ar.submitFlag>0 and ar.studyNo=:studyNo";
		//用于判断是否已被提交申请解剖(0:未被申请，1.已被申请)
		int isHaveAnatomyReq=0;
		if(tblStudyPlan!=null){
			List<?> sqlList=getSession().createSQLQuery(sql).setParameter("studyNo", tblStudyPlan.getStudyNo())
							                				.setParameter("animalId", animalId)
							                				.list();
			if(sqlList!=null&&sqlList.size()>0){
				isHaveAnatomyReq=1;
	        }
		 }
		TblAnimal tblAnimal=null;
		if(isHaveAnatomyReq==0){
			String hql="From TblAnimal where tblStudyPlan = :tblStudyPlan and animalId = :animalId";
			List<TblAnimal> list = getSession().createQuery(hql)
											.setParameter("tblStudyPlan", tblStudyPlan)
											.setParameter("animalId", animalId)
											.list();
			if(null != list && list.size()>0){
				tblAnimal=list.get(0);
			}
		}
		return tblAnimal;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimal> getAllByStudyNo(TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblAnimal t WHERE t.tblStudyPlan = ?  ORDER BY ( case when t.animalCode ='' or t.animalCode is NULL then '9999' else t.animalCode  end ) , t.aniSerialNum ").setParameter(0, tblStudyPlan).list();
	}

	public void moveOrder(TblAnimal currentAnimal, TblAnimal nextAnimal) {
		//没有录入id的行上移
		
		int currentInputOrder = currentAnimal.getAniSerialNum();
		int nextInputOrder = nextAnimal.getAniSerialNum();
		System.out.println("currentInputOrder is "+currentInputOrder+"nextInputOrder is "+nextInputOrder);
		
		currentAnimal.setAniSerialNum(nextInputOrder);
		nextAnimal.setAniSerialNum(currentInputOrder);
		
		update(currentAnimal);
		update(nextAnimal);
	}
	

}
