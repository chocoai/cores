package com.lanen.service.qa.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.model.qa.QAChkPlanChangeIndex;
import com.lanen.service.qa.QAChkPlanChangeIndexService;

import com.lanen.base.BaseDaoImpl;

@Service
public class QAChkPlanChangeIndexServiceImpl extends BaseDaoImpl<QAChkPlanChangeIndex> implements QAChkPlanChangeIndexService{
	
	public int getMaxSnByStudyNo(String studyNoParam) {
		String sqlString="SELECT max([sn]) maxSn FROM [CoresQA].[dbo].[QAChkPlanChangeIndex] where studyNo=:studyNo";
		Query query = getSession().createSQLQuery(sqlString)
					.setParameter("studyNo", studyNoParam);
		Object maxSn = query.uniqueResult();
		if(maxSn!=null)
			return (Integer)maxSn;
		return 0;
	}
	@SuppressWarnings("unchecked")
	public boolean getByStudyNoAndStatue(String studyNoParam) {
		//0：原始；1：提交；-1：否决；2：通过
		String hql="FROM QAChkPlanChangeIndex where qastudyChkIndex.studyNo=:studyNo and (changeState=0 or changeState=1)";
		Query query = getSession().createQuery(hql)
					.setString("studyNo", studyNoParam);
		
		List<QAChkPlanChangeIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return true;
		return false;
	}
	@SuppressWarnings("unchecked")
	public QAChkPlanChangeIndex getByStudyNoAndNoCommit(String studyNoParam)
	{
		//0：原始；1：提交；-1：否决；2：通过
		String hql="FROM QAChkPlanChangeIndex where qastudyChkIndex.studyNo=:studyNo and changeState=0";
		Query query = getSession().createQuery(hql)
					.setString("studyNo", studyNoParam);
		
		List<QAChkPlanChangeIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public QAChkPlanChangeIndex getByStudyNoSnStatus(String studyNoParam,Integer sn) {
		//0：原始；1：提交；-1：否决；2：通过  
		String hql="FROM QAChkPlanChangeIndex where qastudyChkIndex.studyNo=:studyNo and sn=:sn and (changeState=0 or changeState=1)";
		Query query = getSession().createQuery(hql)
					.setString("studyNo", studyNoParam)
					.setInteger("sn", sn);
		
		List<QAChkPlanChangeIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	public QAChkPlanChangeIndex getByStudyNoAndNoApproval(String studyNo) {
		//0：原始；1：提交；-1：否决；2：通过 -2撤销
		String hql="FROM QAChkPlanChangeIndex where qastudyChkIndex.studyNo=:studyNo and (changeState=0 or changeState=1)";
		Query query = getSession().createQuery(hql)
					.setString("studyNo", studyNo);
		
		List<QAChkPlanChangeIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	
	
	
}
