package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictDoseUnit;
@Service
public class DictDoseUnitServiceImpl extends BaseDaoImpl<DictDoseUnit> implements DictDoseUnitService{

	@SuppressWarnings("unchecked")
	public List<DictDoseUnit> getAll() {
		List<DictDoseUnit> list=getSession().createQuery("FROM DictDoseUnit  d ORDER BY d.orderNo ASC").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public DictDoseUnit getByOrderNo(int orderNo) {
		List<DictDoseUnit> list=getSession().createQuery("FROM DictDoseUnit  d WHERE d.orderNo =?").setParameter(0, orderNo).list();
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else {
			return null;
		}
	}

	public int getNextOrderNo() {
		int returnValue=0;
		Object ttt =  getSession().createQuery("SELECT MAX(orderNo) FROM DictDoseUnit").uniqueResult();
		if(null!=ttt ){
			returnValue=(Integer)ttt;
		}
		return returnValue+1;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByAbbr(String abbr) {
		List<DictDoseUnit> list=getSession().createQuery("FROM DictDoseUnit  d WHERE  d.abbr = ? ")//
		.setParameter(0, abbr)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public boolean isExistByName(String name) {
		Object obj =getSession().get(DictDoseUnit.class, name);
		return obj!=null ? true :false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByNameAbbr(String name, String abbr) {
		List<DictDoseUnit> list=getSession().createQuery("FROM DictDoseUnit  d WHERE  d.abbr = ? and d.name != ?")//
		.setParameter(0, abbr)
		.setParameter(1, name)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public void moveOeder(int orderNoPara, int orderNoNext) {
		if(orderNoPara!=0 && orderNoNext!=0){
			DictDoseUnit firster = getByOrderNo(orderNoPara);
			DictDoseUnit seconder = getByOrderNo(orderNoNext);
			int temp;
			temp = firster.getOrderNo();
			firster.setOrderNo(seconder.getOrderNo());
			seconder.setOrderNo(temp);
			save(firster);
			save(seconder);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<DictDoseUnit> getAllorderNo() {
		List<DictDoseUnit> list=getSession().createQuery("FROM DictDoseUnit  d ORDER BY d.orderNo ASC").list();
		return list;
	}

}
