package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.service.qa.TblStudyFileIndexService;

@Service
public class TblStudyFileIndexServiceImpl extends BaseDaoImpl<TblStudyFileIndex> implements TblStudyFileIndexService{

	@SuppressWarnings("unchecked")
	public List<TblStudyFileIndex> getByStudyNo(String studyNo) {
		
		String hql = "from TblStudyFileIndex where studyNo=:studyNo";
		List<TblStudyFileIndex> list = getSession().createQuery(hql)
													.setString("studyNo", studyNo)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public TblStudyFileIndex getByStudyNoAndFileType(String studyNo,
			Integer fileType) {
		String hql = "from TblStudyFileIndex where studyNo=:studyNo and fileType=:fileType";
		List<TblStudyFileIndex> list = getSession().createQuery(hql)
													.setString("studyNo", studyNo)
													.setInteger("fileType", fileType)
													.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	

}
