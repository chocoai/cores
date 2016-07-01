package com.lanen.service.clinicaltest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.DictSpecimen;
@Service
public class DictSpecimenServiceImpl extends BaseDaoImpl<DictSpecimen> implements DictSpecimenService{

	@SuppressWarnings("unchecked")
	public List<DictSpecimen> getAll() {
		List<DictSpecimen> list=getSession().createQuery("FROM DictSpecimen  d ORDER BY d.specType").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistBySpecKind(String SpecKind) {
		List<DictSpecimen> list=getSession().createQuery("FROM DictSpecimen  d WHERE  d.specKind = ? ")//
		.setParameter(0, SpecKind)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<String> getBloodSpecimenKindList() {
		String sql ="select specKind from dictSpecimen where specType = 1 or specType = 3 " +
				" order by specType ";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getUrineSpecimenKindList() {
		String sql ="select specKind from dictSpecimen where specType = 2 or specType = 3 " +
			" order by specType ";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	
	


}
