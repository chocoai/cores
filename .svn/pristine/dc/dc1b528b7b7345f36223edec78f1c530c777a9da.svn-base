package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;

import com.lanen.model.studyplan.TblStudyPlan;


@Service
public class TblStudyPlanServiceImpl extends BaseDaoImpl<TblStudyPlan> implements TblStudyPlanService {


	@SuppressWarnings("unchecked")
	public TblStudyPlan getByStudyNo(String studyNo) {
		TblStudyPlan tblStudyPlan=null;
		String hql="from TblStudyPlan where studyNo=:studyNo";
		List<TblStudyPlan> list=getSession().createQuery(hql).setParameter("studyNo", studyNo).list();
		if(list!=null&&list.size()>0){
			tblStudyPlan=list.get(0);
		}
		return tblStudyPlan;
	}
	public String getSDByStudyNo(String studyNo) {
		String sql = "select sd"+
		" from CoresStudy.dbo.view_studyNoSD"+
		" where studyNo = :studyNo";
		String sd = (String) getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.uniqueResult();
		return sd;
	}


}
