package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblVisceraMissing;

@Service
public class TblVisceraMissingServiceImpl extends BaseDaoImpl<TblVisceraMissing> implements TblVisceraMissingService {

	@SuppressWarnings("unchecked")
	public TblVisceraMissing getVisceraByStudyNoAndAnimalCodeAndVisceraCodeOrSubVisceraCode(
			String studyNo, String animalCode, String visceraCode,
			String subVisceraCode) {
		String hql = "from TblVisceraMissing where  studyNo = :studyNo and animalCode = :animalCode and  visceraCode= :visceraCode and " +
				" ( :subVisceraCode is null or subVisceraCode = :subVisceraCode )";
		List<TblVisceraMissing> list = getSession().createQuery(hql)
		.setParameter("studyNo", studyNo)
		.setParameter("animalCode", animalCode)
		.setParameter("visceraCode", visceraCode)
		.setParameter("subVisceraCode", subVisceraCode)
		.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public int getVisceraMissingCountBySessionIdList(List<String> sessionIdList) {
		String sql= "  select  distinct studyNo,animalCode from CoresStudy.dbo.tblVisceraMissing where sessionId in (:sessionId)";
		List<TblVisceraMissing> list = getSession().createSQLQuery(sql).setParameterList("sessionId", sessionIdList).list();
		if(null != list && list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	
	}

}
