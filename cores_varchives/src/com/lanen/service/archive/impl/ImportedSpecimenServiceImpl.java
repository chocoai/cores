package com.lanen.service.archive.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.ImportedSpecimen;
import com.lanen.service.archive.ImportedSpecimenService;

@Service
public class ImportedSpecimenServiceImpl extends BaseDaoImpl<ImportedSpecimen>
		implements ImportedSpecimenService {

	@SuppressWarnings("unchecked")
	public List<ImportedSpecimen> getListByCondition(String studydNo, String searchCond)
	{
		if(searchCond==null)
			searchCond="";
		
		String hql = "from ImportedSpecimen where studyNo=:studydNo and (" +
				" specimenCode like :searchCond or visceraName like :searchCond)";
		
		List<ImportedSpecimen> list = getSession().createQuery(hql)
												.setString("studydNo", studydNo)
												.setString("searchCond", "%"+searchCond+"%")
												.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImportedSpecimen> getByStudyNoAndSpecimenCode(String studyNo,
			String specimenCode) {
		String hql = "from ImportedSpecimen where studyNo=:studyNo and specimenCode=:specimenCode ";

		List<ImportedSpecimen> list = getSession().createQuery(hql)
												.setString("studyNo", studyNo)
												.setString("specimenCode", specimenCode)
												.list();
		
		return list;
	}
	

}
