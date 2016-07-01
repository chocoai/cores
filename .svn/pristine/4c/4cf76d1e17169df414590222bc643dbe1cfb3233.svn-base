package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictReportNumber;
import com.lanen.model.studyplan.DictReportNumberHis;

@Service
public class DictReportNumberHisServiceImpl extends BaseDaoImpl<DictReportNumberHis> implements DictReportNumberHisService{

	@SuppressWarnings("unchecked")
	public String getNumberByReportName(String reportName) {
		List<DictReportNumber> list = getSession().createQuery("From DictReportNumber where reportName = ? ")
		.setParameter(0, reportName)
		.list();
		if(null != list && !list.isEmpty()){
			return list.get(0).getNumber();
		}
		return null;
	}

}
