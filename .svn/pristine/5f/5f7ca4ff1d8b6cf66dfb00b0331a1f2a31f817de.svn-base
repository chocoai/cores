package com.lanen.service.clinicaltest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.DictInstrument;

@Service
public class DictInstrumentServiceImpl extends BaseDaoImpl<DictInstrument> implements DictInstrumentService {

	@SuppressWarnings("unchecked")
	public List<DictInstrument> getAll() {
		List<DictInstrument> list=getSession().createQuery("FROM DictInstrument  d ORDER BY d.instrumentId ASC").list();
		return list;
	}

	public boolean isExistByInstrumentId(String instrumentId) {
		Object obj =getSession().get(DictInstrument.class, instrumentId);
		return obj!=null ? true :false;
	}

	@SuppressWarnings("unchecked")
	public List<DictInstrument> findByTestItem(int testItem) {
		List<DictInstrument> list=getSession().createQuery("FROM DictInstrument  d WHERE d.instrumentType = ? ORDER BY d.instrumentId ASC")//
		.setParameter(0, testItem)
		.list();
		return list;
	}

}
