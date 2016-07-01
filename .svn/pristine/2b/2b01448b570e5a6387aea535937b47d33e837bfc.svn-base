package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictReportNumber;

@Service
public class DictReportNumberServiceImpl extends BaseDaoImpl<DictReportNumber> implements DictReportNumberService{

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
	
	@SuppressWarnings("unchecked")
	public List<DictReportNumber> getAllReportCodeList()
	{
		 String hql = "FROM DictReportNumber   order by CAST(id AS int)";
		 List<DictReportNumber> list = getSession().createQuery(hql)
												.list();
			
		 return list;
	}

}
