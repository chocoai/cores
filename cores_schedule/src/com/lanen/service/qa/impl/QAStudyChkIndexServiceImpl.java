package com.lanen.service.qa.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.service.qa.QAStudyChkIndexService;
@Service
public class QAStudyChkIndexServiceImpl extends BaseDaoImpl<QAStudyChkIndex> implements	QAStudyChkIndexService {
	
	@SuppressWarnings("unchecked")
	public QAStudyChkIndex getByStudyNo(String studyNo)
	{
		Query query = getSession().createQuery("from QAStudyChkIndex where studyNo=:studyNo")
									.setString("studyNo", studyNo);
		List<QAStudyChkIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
		
	}
}
