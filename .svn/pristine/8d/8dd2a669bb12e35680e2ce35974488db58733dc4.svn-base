package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblCounterWeight;
@Service
public class TblCounterWeightServiceImpl extends BaseDaoImpl<TblCounterWeight>  implements  TblCounterWeightService {

	@SuppressWarnings("unchecked")
	public boolean isHaveCpCodeByCpCode(String cpCode) {
		List<TblCounterWeight> list=getSession().createQuery("FROM TblCounterWeight  d WHERE d.cpCode = ?  ")
        .setParameter(0, cpCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;

	}

	public void delectAll(String id) {
		TblCounterWeight  counterWeight = getById(id);
		String cpCode  = counterWeight.getCpCode();
		getSession().createSQLQuery(" delete from  CoresStudy.dbo.tblCounterpoise   where cpCode = ? ")
        .setParameter(0,cpCode)
		.executeUpdate();
		delete(id);
		
	}
	

	

}
