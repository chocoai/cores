package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictReportNumber;

public interface DictReportNumberService extends BaseDao<DictReportNumber>{

	String getNumberByReportName(String reportName);
	
	List<DictReportNumber> getAllReportCodeList();
}
