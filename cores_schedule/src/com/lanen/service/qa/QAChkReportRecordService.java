package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkReportRecord;

public interface QAChkReportRecordService extends BaseDao<QAChkReportRecord> {
	/**
	 * 
	 * @param chkReportCode
	 * @return
	 */
	List<QAChkReportRecord> getByReportCode(String chkReportCode);
}
