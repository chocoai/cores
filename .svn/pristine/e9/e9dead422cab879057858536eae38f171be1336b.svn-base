package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDao;
import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkReportReader;
import com.lanen.service.qa.QAChkReportReaderService;
@Service
public class QAChkReportReaderServiceImpl extends BaseDaoImpl<QAChkReportReader> implements QAChkReportReaderService{

	@SuppressWarnings("unchecked")
	public List<QAChkReportReader> getByReportCode(String chkReportCode)
	{
		String hql = "from QAChkReportReader where qachkReport.chkReportCode=:chkReportCode";
		List<QAChkReportReader> list = getSession().createQuery(hql)
													.setString("chkReportCode", chkReportCode)
													.list();
		return list;
										
	}
	@SuppressWarnings("unchecked")
	public List<QAChkReportReader> getByReportCodes(String[] chkReportCode)
	{
		String hql = "from QAChkReportReader where qachkReport.chkReportCode in (:chkReportCode)";
		List<QAChkReportReader> list = getSession().createQuery(hql)
													.setParameterList("chkReportCode", chkReportCode)
													.list();
		return list;
										
	}

}
