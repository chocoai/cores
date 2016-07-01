package com.lanen.service.clinicaltest;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;

@Service
public class PassagewayServiceImpl extends BaseDaoImpl<Object> implements PassagewayService{

	public boolean isExist(String abbr,int testItem) {
		List<?> list = getSession().createSQLQuery("select * from CoresStudy.dbo.tblPassageway as p WHERE p.testIndex =? and p.testItem = ? ")
		.setParameter(0, abbr)
		.setParameter(1, testItem)
		.list();
		if(null !=list && list.size()>0){
			return true;
		}
		return false;
	}
}
