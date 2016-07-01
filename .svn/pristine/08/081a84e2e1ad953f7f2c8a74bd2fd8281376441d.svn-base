package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictStudyTestIndex;
import com.lanen.model.studyplan.DictStudyType;

@Service
public class DictStudyTestIndexServiceImpl extends BaseDaoImpl<DictStudyTestIndex> implements DictStudyTestIndexService {

	@SuppressWarnings("unchecked")
	public List<DictStudyTestIndex> getAll() {
		List<DictStudyTestIndex> list=getSession().createQuery("FROM DictStudyTestIndex  d").list();
		return list;
	}

	public void deleteByTypeCode(DictStudyType obj) {

		getSession().createQuery("delete DictStudyTestIndex d where d.dictStudyType = ?").setParameter(0, obj).executeUpdate();
	}

	public void saveAll(List<DictStudyTestIndex> objList) {
		for(DictStudyTestIndex studyTestIndex : objList){
			save(studyTestIndex);
		}
	}

	@SuppressWarnings("unchecked")
	public List<DictStudyTestIndex> getByType(DictStudyType obj) {
		return getSession().createQuery("From DictStudyTestIndex d where d.dictStudyType = ?").setParameter(0, obj).list();
	}

	
}
