package com.lanen.service.clinicaltest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.TblESLink;

@Service
public class TblESLinkServiceImpl extends BaseDaoImpl<TblESLink> implements TblESLinkService{

	@SuppressWarnings("unchecked")
	public TblESLink getByEntityNameAndDataId(String entityName, String dataId) {
		List<TblESLink> list= getSession().createQuery("FROM TblESLink t WHERE t.tableName = ? AND t.dataId = ? ")//
		.setParameter(0, entityName)
		.setParameter(1, dataId)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public TblESLink getByEntityNameAndDataIdType(String entityName, String dataId,int esType) {
		List<TblESLink> list= getSession().createQuery("FROM TblESLink t WHERE t.tableName = ? AND t.dataId = ? AND t.esType =? ")//
		.setParameter(0, entityName)
		.setParameter(1, dataId)
		.setParameter(2, esType)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public int isESLink(String tableName, String studyNoPara, int esType) {
		List<TblESLink> list= getSession().createQuery("FROM TblESLink t WHERE t.tableName = ? AND t.dataId = ? AND t.esType =? ")//
		.setParameter(0, tableName)
		.setParameter(1, studyNoPara)
		.setParameter(2, esType)
		.list();
		if(null!=list&&list.size()>0){
			return 1;
		}
		return 0;
	}

}
