package com.lanen.service.qa.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictQAStatementTemple;
import com.lanen.service.qa.DictQAStatementTempleService;
@Service
public class DictQAStatementTempleServiceImpl extends BaseDaoImpl<DictQAStatementTemple> implements
		DictQAStatementTempleService {
	
	@SuppressWarnings("unchecked")
	public List<DictQAStatementTemple> getByTiCode(String tiCode) {
		String hql = "from DictQAStatementTemple where tiCode=:tiCode";
		List<DictQAStatementTemple> list = getSession().createQuery(hql)
														.setString("tiCode", tiCode)
														.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByTiAndName(String tiCode, String templeName) {
		String hql = "from DictQAStatementTemple where tiCode=:tiCode and templeName=:templeName";
		List<DictQAStatementTemple> list = getSession().createQuery(hql)
														.setString("tiCode", tiCode)
														.setString("templeName", templeName)
														.list();
		if(list!=null&&list.size()>0)
			return true;
		else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByTiAndNameExceptOne(String tiCode,
			String templeName, String templeId) {
		String hql = "from DictQAStatementTemple where tiCode=:tiCode and templeName=:templeName and templeId!=:templeId";
		List<DictQAStatementTemple> list = getSession().createQuery(hql)
														.setString("tiCode", tiCode)
														.setString("templeName", templeName)
														.setString("templeId", templeId)
														.list();
		if(list!=null&&list.size()>0)
			return true;
		else {
			return false;
		}
	}

	
}
