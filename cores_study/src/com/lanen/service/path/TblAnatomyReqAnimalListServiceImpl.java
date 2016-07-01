package com.lanen.service.path;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.TblAnatomyReqAnimalList;
import com.lanen.model.path.TblAnatomyReqAnimalListHis;
/**
 * 申请解剖动物列表   serviceImpl
 * @author 
 */ 
@Service
public class TblAnatomyReqAnimalListServiceImpl extends BaseDaoImpl<TblAnatomyReqAnimalList>
		implements TblAnatomyReqAnimalListService {

	public int getAnimalNumberByStudyNo(String studyNoPara, int anatomyReqNo) {
		String sql="select count(id) from tblAnatomyReqAnimalList " +
				   "where studyNo=:studyNoPara and anatomyReqNo=:anatomyReqNo";
		Integer animalNumber = (Integer) getSession().createSQLQuery(sql)
							                       .setParameter("studyNoPara", studyNoPara)
							                       .setParameter("anatomyReqNo", anatomyReqNo)
							                       .uniqueResult();
		return animalNumber;
	}

	public int isHaveAnatomyReq(String studyNo, String animalCode) {
		String sql="select ara.id " +
				   " from [CoresStudy].[dbo].[tblAnatomyReqAnimalList] as ara " +
				   " join [CoresStudy].[dbo].[tblAnatomyReq] as ar "+
                   " on ara.studyNo=ar.studyNo and ara.anatomyReqNo=ar.reqNo " +
                   " where ar.submitFlag = 0 and ar.studyNo=:studyNo and ara.animalCode=:animalCode" +
                   "  and ara.cancelFlag = 0 ";
		List<?> sqlList=getSession().createSQLQuery(sql)
		                            .setParameter("studyNo", studyNo)
		                            .setParameter("animalCode", animalCode)
		                            .list();
		int isHaveAnatomyReq=0;
		if(sqlList!=null && sqlList.size()>0){
			isHaveAnatomyReq=1;
		}
		return isHaveAnatomyReq;
	}

	public List<?> getAllAnimalByStudyNo(String studyNoPara) {
		String sql="select ta.animalCode,ta.gender " +
//				   " from [CoresStudy].[dbo].[tblAnimal] as ta " +  //原从 动物表里取
				   " from CoresStudy.dbo.tblAnimalDetailDissectPlan as ta " + //现从 解剖计划动物表里取
				   " where ta.studyNo=:studyNoPara and ta.animalCode not in "+
                   " ( select tara.animalCode from " +
                   "     [CoresStudy].[dbo].[tblAnatomyReqAnimalList] as tara " +
                   "     join [CoresStudy].[dbo].[tblAnatomyReq] as tar "+ 
                   "     on tara.studyNo=tar.studyNo and tara.anatomyReqNo=tar.reqNo " +
                   "     where tara.studyNo=:studyNoPara and tar.submitFlag = 1 " +
                   " 	and tara.cancelFlag = 0 " +
                   " ) order by ta.animalCode";
		List<?> sqlList=getSession().createSQLQuery(sql).setParameter("studyNoPara", studyNoPara).list();
		return sqlList;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqAnimalList> getListByStudyNoAndReqNo(
			String studyNoPara, int anatomyReqNo) {
		String hql="from TblAnatomyReqAnimalList where studyNo=:studyNoPara and  anatomyReqNo=:anatomyReqNo";
		List<TblAnatomyReqAnimalList> list=getSession().createQuery(hql)
		                                               .setParameter("studyNoPara", studyNoPara)
		                                               .setParameter("anatomyReqNo", anatomyReqNo)
		                                               .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public TblAnatomyReqAnimalList getByStudyNoReqNoAnimalCode(String studyNo, Integer reqNo,
			String animalCode) {
		String hql="from TblAnatomyReqAnimalList where studyNo=:studyNoPara and  " +
				" anatomyReqNo=:anatomyReqNo and animalCode = :animalCode";
		List<TblAnatomyReqAnimalList> list=getSession().createQuery(hql)
		                                               .setParameter("studyNoPara", studyNo)
		                                               .setParameter("anatomyReqNo", reqNo)
		                                               .setParameter("animalCode", animalCode)
		                                               .list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public int getAnimalNumberByTaskIdList(List<String> taskIdLists) {
		String sql=" select tbla.animalCode,tbla.studyNo,tbla.anatomyReqNo  "+
		" from CoresStudy.dbo.tblAnatomyReqAnimalList as tbla  "+
		"       left join CoresStudy.dbo.tblAnatomyTask as tblt "+
		" on tbla.studyNo = tblt.studyNo and tbla.anatomyReqNo = tblt.anatomyReqNo "+
		" where tblt.taskId in ( :taskIdLists )";
        List<?> sqlList=getSession().createSQLQuery(sql).setParameterList("taskIdLists", taskIdLists).list();
        return sqlList.size();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeByTaskIdList(
			List<String> taskIdLists) {
		String sql=" select tbla.animalCode  "+
		" from CoresStudy.dbo.tblAnatomyReqAnimalList as tbla  "+
		"       left join CoresStudy.dbo.tblAnatomyTask as tblt "+
		" on tbla.studyNo = tblt.studyNo and tbla.anatomyReqNo = tblt.anatomyReqNo "+
		" where tblt.taskId in ( :taskIdLists )";
        List<Map<String, Object>> sqlList=getSession().createSQLQuery(sql).setParameterList("taskIdLists", taskIdLists).setResultTransformer(new MapResultTransformer()).list();
        return sqlList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeByStudyNo(
			String studyNo, List<String> sessionIdList) {
//		String sql=" select tbla.animalCode  "+
//		" from CoresStudy.dbo.tblAnatomyReqAnimalList as tbla  "+
//		"       left join CoresStudy.dbo.tblAnatomyTask as tblt "+
//		" on tbla.studyNo = tblt.studyNo and tbla.anatomyReqNo = tblt.anatomyReqNo "+
//		" where tblt.studyNo in ( :studyNo )";
		String sql="select animalCode  "+
                   " from CoresStudy.dbo.tblAnatomyAnimal " +
                   " where studyNo=:studyNo and  anatomySessionId in (:sessionIdList)" +
                   " order by animalCode ";
        List<Map<String, Object>> sqlList=getSession().createSQLQuery(sql).setParameter("studyNo", studyNo)
                                                      .setParameterList("sessionIdList", sessionIdList)
                                                      .setResultTransformer(new MapResultTransformer()).list();
        return sqlList;
	}

	public Integer getAnimalNumberByStudyNoAndReqNo(String studyNo,
			int anatomyReqNo) {
		String sql="select count(aral.animalCode) FROM CoresStudy.dbo.tblAnatomyReqAnimalList as aral "+
					"  where aral.studyNo=:studyNo and aral.anatomyReqNo=:anatomyReqNo  "+
					"  and aral.animalCode in "+
					"  (select animalCode FROM CoresStudy.dbo.tblAnatomyReqAnimalList as aral  "+
					"  join  CoresStudy.dbo.tblAnatomyReq as ar "+
					"  on aral.studyNo=ar.studyNo and aral.anatomyReqNo=ar.reqNo "+
					"  where aral.studyNo=:studyNo  and  "+
					"  ar.submitFlag = 1 " +
					"   and aral.cancelFlag = 0 )";
		Integer number=0;
		number=(Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo)
		                   .setParameter("anatomyReqNo", anatomyReqNo)
		                   .uniqueResult();
		return number;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqAnimalListHis> getHisListByStudyNoAndReqNo(
			String studyNo, Integer anatomyReqNo) {
		String hql="from TblAnatomyReqAnimalListHis where studyNo=:studyNoPara and  anatomyReqNo=:anatomyReqNo";
		List<TblAnatomyReqAnimalListHis> list=getSession().createQuery(hql)
		                                               .setParameter("studyNoPara", studyNo)
		                                               .setParameter("anatomyReqNo", anatomyReqNo)
		                                               .list();
		return list;
	}


}
