package com.lanen.service.qa.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.User;
import com.lanen.model.qa.TblStudyPlanReadRecord;
import com.lanen.service.qa.TblStudyPlanReadRecordService;

@Service
public class TblStudyPlanReadRecordServiceImpl extends
		BaseDaoImpl<TblStudyPlanReadRecord> implements
		TblStudyPlanReadRecordService {

	@SuppressWarnings("unchecked")
	public boolean isExistByStudyAndUser(String studyNoParam, User user) {
		String hql = "from TblStudyPlanReadRecord where reader=:reader and tblStudyFileIndex.studyNo=:studyNo";
		List<TblStudyPlanReadRecord> list =getSession().createQuery(hql)
													.setString("reader", user.getRealName())
													.setString("studyNo", studyNoParam)
													.list();
		if(list!=null&&list.size()>0)
			return true;
		return false;
	}

	
}
