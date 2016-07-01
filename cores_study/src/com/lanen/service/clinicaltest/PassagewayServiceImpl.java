package com.lanen.service.clinicaltest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.DictInstrument;
import com.lanen.model.clinicaltest.TblPassageway;

@Service
public class PassagewayServiceImpl extends BaseDaoImpl<TblPassageway> implements PassagewayService{
	@Resource
	private GetIdService getIdService;
	@SuppressWarnings("unchecked")
	public List<TblPassageway> getByTestItem(int testItem, String abbr , String instrumentId) {
		if(null==instrumentId){
			return null;
		}
		DictInstrument dictInstrument = new DictInstrument();
		dictInstrument.setInstrumentId(instrumentId);
		List<TblPassageway> list=getSession().createQuery("FROM TblPassageway p WHERE p.testItem =? AND p.testIndex =? AND p.diactInstrument =? ")//
		.setParameter(0, testItem)//
		.setParameter(1, abbr)//
		.setParameter(2, dictInstrument)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExist(int testItem, String instrumentId, String passagewayName) {
		DictInstrument dictInstrument = new DictInstrument();
		dictInstrument.setInstrumentId(instrumentId);
			List<TblPassageway> list=getSession().createQuery("FROM TblPassageway p WHERE p.testItem =?  AND p.diactInstrument =? AND p.passageway = ? ")//
			.setParameter(0, testItem)//
			.setParameter(1, dictInstrument)
			.setParameter(2, passagewayName)
			.list();
			if(null!=list&&list.size()>0){
				return true;
			}
		return false;
	}

	public void saveAll(int testItem, String instrumentId, String abbr,
			List<String> strList) {
		//删除    该检查项目    该设备     该指标   下的通道号
		DictInstrument dictInstrument = new DictInstrument();
		dictInstrument.setInstrumentId(instrumentId);
		getSession().createQuery("delete FROM TblPassageway p WHERE p.testItem =? AND p.testIndex =? AND p.diactInstrument =? ")//
		.setParameter(0, testItem)
		.setParameter(1, abbr)
		.setParameter(2, dictInstrument)
		.executeUpdate();
		
		//保存
		for(String str:strList){
			TblPassageway passageway =new TblPassageway();
			passageway.setDiactInstrument(dictInstrument);
			passageway.setTestIndex(abbr);
			passageway.setPassageway(str);
			passageway.setTestItem(testItem);
			this.save(passageway);
		}
		
	}

	public void deleteByDictInstrument(DictInstrument dictInstrument) {
		if(null!=dictInstrument){
			getSession().createQuery("delete FROM TblPassageway p WHERE p.diactInstrument =? ")//
			.setParameter(0, dictInstrument)
			.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public TblPassageway getByTestItemPassagewayInstrumentId(int testItem,
			String passageway, String instrumentId) {
		if(null==instrumentId){
			return null;
		}
		DictInstrument dictInstrument = new DictInstrument();
		dictInstrument.setInstrumentId(instrumentId);
		List<TblPassageway> list=getSession().createQuery("FROM TblPassageway p WHERE p.testItem =? AND p.passageway =? AND p.diactInstrument =? ")//
		.setParameter(0, testItem)//
		.setParameter(1, passageway)//
		.setParameter(2, dictInstrument)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(TblPassageway entity) {
		String id=getIdService.getKey("Passageway");
		entity.setId(id);
		super.save(entity);
	}

	public void save(int testItem, String instrumentId, String abbr,
			String passagewayName) {
		TblPassageway passageway =new TblPassageway();
		DictInstrument dictInstrument = new DictInstrument();
		dictInstrument.setInstrumentId(instrumentId);
		passageway.setDiactInstrument(dictInstrument);
		passageway.setTestIndex(abbr);
		passageway.setPassageway(passagewayName);
		passageway.setTestItem(testItem);
		this.save(passageway);
		
	}

	public void deleteByPassageway(String selected) {
		getSession().createQuery("delete From TblPassageway p WHERE p.passageway =? ")//
		.setParameter(0, selected)
		.executeUpdate();
		
	}

	public boolean isExist(String abbr) {
		List<?> list = getSession().createQuery("From TblPassageway p WHERE p.testIndex =? ")
		.setParameter(0, abbr)
		.list();
		if(null !=list && list.size()>0){
			return true;
		}
		return false;
	}
}
