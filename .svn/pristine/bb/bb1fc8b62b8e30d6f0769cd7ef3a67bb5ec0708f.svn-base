package com.lanen.service.clinicaltest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.TblDataSource;
@Service
public class TblDataSourceServiceImpl extends BaseDaoImpl<TblDataSource> implements TblDataSourceService{

	@Override
	public void save(TblDataSource entity) {
		entity.setDsId(getKey("TblDataSource"));
		super.save(entity);
	}

	@SuppressWarnings("unchecked")
	public TblDataSource saveOrSelect(TblDataSource tblDataSource) {
		List<TblDataSource>  list= getSession().createQuery("FROM TblDataSource t WHERE t.dsHost =? and t.dsInstrumentId  =? and t.dsInstrumentName  =? and t.dsSoftware  =? and t.dsVersion  =? ")//
		.setParameter(0, tblDataSource.getDsHost())
		.setParameter(1, tblDataSource.getDsInstrumentId())
		.setParameter(2, tblDataSource.getDsInstrumentName())
		.setParameter(3, tblDataSource.getDsSoftware())
		.setParameter(4, tblDataSource.getDsVersion())
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}else{
			save(tblDataSource);
			return tblDataSource;
		}
	}

	
}
