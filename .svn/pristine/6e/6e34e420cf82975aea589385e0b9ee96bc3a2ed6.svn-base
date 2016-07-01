package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.service.qa.QAChkReportRecordService;

@Service
public class QAChkReportRecordServiceImpl extends BaseDaoImpl<QAChkReportRecord> implements QAChkReportRecordService {

	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getByReportCode(String chkReportCode) {
		String hql = "from QAChkReportRecord where qachkReport.chkReportCode=:chkReportCode";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setString("chkReportCode", chkReportCode)
													.list();
		return list;
	}

}
