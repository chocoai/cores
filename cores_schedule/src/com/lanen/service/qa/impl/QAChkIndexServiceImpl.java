package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.service.qa.QAChkIndexService;

@Service
public class QAChkIndexServiceImpl extends BaseDaoImpl<QAChkIndex> implements
		QAChkIndexService {
	@SuppressWarnings("unchecked")
	public QAChkIndex isExistByChkPlanIdAndChkItemId(String chkPlanId,
			String chkItemName) {
		
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where chkPlanId=:chkPlanId and chkItemName=:chkItemName")
											.setString("chkPlanId", chkPlanId)
											.setString("chkItemName",chkItemName)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}

		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByTime(Date startChkDate, Date endChkDate) {
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where createTime between :startChkDate and :endChkDate")
											.setDate("startChkDate", startChkDate)
											.setDate("endChkDate",endChkDate)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	
	public List<QAChkIndex> getByStudyNo(String studyNoParam) {
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where studyNo=:studyNo")
				.setString("studyNo", studyNoParam)
				.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<String> getByReport(String chkReportCode) {
		List<String> list = getSession().createSQLQuery("SELECT [chkIndexID]  FROM [CoresQA].[dbo].[QAChkIndex] where chkReportCode=:chkReportCode")
											.setParameter("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByReportCode(String chkReportCode) {
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getStudyNoByReportCode(String chkReportCode)
	{
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getStudyNo();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Integer getChkTypeByReportCode(String chkReportCode)
	{
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getChkType();
		}
		return 1;
	}

}
