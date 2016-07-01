package com.lanen.service.archive.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.DictDataTable;
import com.lanen.service.archive.DictDataTableService;
@Service
public class DictDataTableServiceImpl extends BaseDaoImpl<DictDataTable> implements
		DictDataTableService {

	@SuppressWarnings("unchecked")
	public DictDataTable getByTableNameAndField(String table, String field) {
		String hql = "from DictDataTable where tableName=:tableName and fieldName=:fieldName";
		List<DictDataTable> list = getSession().createQuery(hql)
												.setString("tableName", table)
												.setString("fieldName", field)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		
		return null;
	}

	

}
