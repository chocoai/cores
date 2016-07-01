package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictStudyGroup;
import com.lanen.service.qa.DictStudyGroupService;
@Service
public class dictStudyGroupServiceImpl extends BaseDaoImpl<DictStudyGroup> implements DictStudyGroupService {
	@SuppressWarnings("unchecked")
	public boolean isExistByStudyGroupName(String studyGroupName) {
		List<DictStudyGroup> groups = getSession().createQuery("from DictStudyGroup d where d.studyGroupName=? ")
										.setString(0, studyGroupName)
										.list(); 
		if(groups!=null&&groups.size()>0)
			return true;
		return false;
	}

	

}
