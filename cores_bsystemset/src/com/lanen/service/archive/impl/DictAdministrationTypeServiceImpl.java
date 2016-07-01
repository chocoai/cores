package com.lanen.service.archive.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.archive.DictAdministrationType;
import com.lanen.service.archive.DictAdministrationTypeService;

@Service
public class DictAdministrationTypeServiceImpl extends BaseDaoImpl<DictAdministrationType> implements
		DictAdministrationTypeService {
	
	@SuppressWarnings("unchecked")
	public List<DictAdministrationType> getAll() {
		String hql = " from DictAdministrationType order by docTypeFlag ";
		List<DictAdministrationType> list = getSession().createQuery(hql)
												.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean hasTypeCode(String docTypeFlag) {
		// TODO Auto-generated method stub
		String hql = " from DictAdministrationType where docTypeFlag=:docTypeFlag";
		List<DictAdministrationType> list = getSession().createQuery(hql)
												.setString("docTypeFlag", docTypeFlag)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean hasTypeName(String docTypeName) {
		// TODO Auto-generated method stub
		String hql = " from DictAdministrationType where docTypeName=:docTypeName";
		List<DictAdministrationType> list = getSession().createQuery(hql)
												.setString("docTypeName", docTypeName)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public DictAdministrationType getByArchiveTypeCode(String docTypeFlag) {
		// TODO Auto-generated method stub
		String hql = " from DictAdministrationType where docTypeFlag=:docTypeFlag";
		List<DictAdministrationType> list = getSession().createQuery(hql)
												.setString("docTypeFlag", docTypeFlag)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public void deleteByTypeCode(String docTypeFlag) {
		// TODO Auto-generated method stub
		String hql = " delete DictAdministrationType where docTypeFlag=:docTypeFlag";
		getSession().createQuery(hql).setString("docTypeFlag", docTypeFlag)
									.executeUpdate();
		
	}
	
	
	public Integer getMaxSn()
	{
		// TODO Auto-generated method stub
		String sql = " select max(sn) sn from [CoresSystemSet].[dbo].[DictAdministrationType] ";
		Object sn = getSession().createSQLQuery(sql).uniqueResult();
		if(sn!=null)
			return (Integer)sn;
		
		return 0;
	}
	

}
