package com.lanen.service.clinicaltest;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.DictInstrument;
import com.lanen.model.clinicaltest.TblInstrumentVerification;

@Service
public class TblInstrumentVerificationServiceImpl extends BaseDaoImpl<TblInstrumentVerification> implements
		TblInstrumentVerificationService {
	@Resource
	private GetIdService getIdService;
	public TblInstrumentVerification saveHaveReturn(
			TblInstrumentVerification verification) {
		
		this.save(verification);
		return verification;
	}

	public void deleteByDictInstrument(DictInstrument dictInstrument) {
		if(null!=dictInstrument){
			getSession().createQuery("delete FROM TblInstrumentVerification t WHERE t.dictInstrument = ? ")//
			.setParameter(0, dictInstrument)
			.executeUpdate();
		}
		
	}

	@Override
	public void save(TblInstrumentVerification entity) {
		String id=getIdService.getKey("TblInstrumentVerification");
		entity.setId(id);
		super.save(entity);
	}

	
}
