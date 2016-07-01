package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblBalConnect;
import com.lanen.model.path.TblCounterpoise;

@Service
public class TblCounterpoiseServiceImpl extends BaseDaoImpl<TblCounterpoise>  implements  TblCounterpoiseService {
	

	@SuppressWarnings("unchecked")
	public boolean isEnabledByCpCode(String cpCode) {
		List<TblCounterpoise> list=getSession().createQuery("FROM TblCounterpoise  d WHERE d.cpCode = ?  ")
        .setParameter(0, cpCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	public List<TblCounterpoise> getByPrecision(String precision) {
		String hql="from TblCounterpoise where balPrecision=:precision";
		List<TblCounterpoise> list=getSession().createQuery(hql)
		                                       .setParameter("precision", precision)
		                                       .list();
		return list;
	}
	


	@SuppressWarnings("unchecked")
	public boolean isEnabledByCpWeightAndCalPrecision(String cpWeight,
			String balPrecision) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblCounterpoise  d WHERE d.cpWeight = ?  and d.balPrecision = ? ")
		.setParameter(0, cpWeight).setParameter(1, balPrecision)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isEnabledByCpWeightAndCalPrecision(String cPCode,String cpWeight,
			String balPrecision) {
		List<TblBalConnect> list=getSession().createQuery("FROM TblCounterpoise  d WHERE d.cpCode = ?  and d.balPrecision = ? ")
        .setParameter(0, cPCode).setParameter(1, balPrecision)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	public List<TblCounterpoise> getListByCpCode(String cpCode) {
		List<TblCounterpoise> list=getSession().createQuery("FROM TblCounterpoise  d WHERE d.cpCode = ?  ")
        .setParameter(0, cpCode)
		.list();
		return list;
	}


}
