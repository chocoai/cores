package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.TblStudyFile;
import com.lanen.service.qa.TblStudyFileService;

@Service
public class TblStudyFileServiceImpl extends BaseDaoImpl<TblStudyFile> implements TblStudyFileService{
	@SuppressWarnings("unchecked")
	public List<TblStudyFile> getByFileIndexId(String studyFileIndexId)
	{
		String hql = "from TblStudyFile where tblStudyFileIndex.studyFileIndexId=:studyFileIndexId";
		List<TblStudyFile> list = getSession().createQuery(hql)
												.setString("studyFileIndexId", studyFileIndexId)
												.list();
		return list;
		
	}
	
}
