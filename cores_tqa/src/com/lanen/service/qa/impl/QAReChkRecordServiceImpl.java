package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAReChkRecord;
import com.lanen.service.qa.QAReChkRecordService;
@Service
public class QAReChkRecordServiceImpl extends BaseDaoImpl<QAReChkRecord> implements QAReChkRecordService{

	@SuppressWarnings("unchecked")
	public List<QAReChkRecord> getNoSignByReportRecordIds(String[] ids) {
		// TODO Auto-generated method stub
		String hql = " from QAReChkRecord where qachkReportRecord.chkReportRecordId in (:ids)" +
				"	and reChkSignID is null ";
		
		List<QAReChkRecord> list = getSession().createQuery(hql)
												.setParameterList("ids", ids)
												.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAReChkRecord> getByReportRecord(String reportRecordId) {
		String hql = " from QAReChkRecord where qachkReportRecord.chkReportRecordId=:id" +
					"	and reChkSignID is null ";

		List<QAReChkRecord> list = getSession().createQuery(hql)
												.setString("id", reportRecordId)
												.list();
		return list;
		
	}

}
