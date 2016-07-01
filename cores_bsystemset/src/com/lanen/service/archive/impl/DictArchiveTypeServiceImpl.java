package com.lanen.service.archive.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.service.archive.DictArchiveTypeService;

@Service
public class DictArchiveTypeServiceImpl extends BaseDaoImpl<DictArchiveType> implements
		DictArchiveTypeService {
	
	@SuppressWarnings("unchecked")
	public List<DictArchiveType> getAll() {
		String hql = " from DictArchiveType order by archiveTypeFlag,archiveTypeCode";
		List<DictArchiveType> list = getSession().createQuery(hql)
												.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean hasTypeCode(String archiveTypeCode) {
		// TODO Auto-generated method stub
		String hql = " from DictArchiveType where archiveTypeCode=:archiveTypeCode";
		List<DictArchiveType> list = getSession().createQuery(hql)
												.setString("archiveTypeCode", archiveTypeCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean hasTypeName(String archiveTypeName) {
		// TODO Auto-generated method stub
		String hql = " from DictArchiveType where archiveTypeName=:archiveTypeName";
		List<DictArchiveType> list = getSession().createQuery(hql)
												.setString("archiveTypeName", archiveTypeName)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public DictArchiveType getByArchiveTypeCode(String archiveTypeCode) {
		// TODO Auto-generated method stub
		String hql = " from DictArchiveType where archiveTypeCode=:archiveTypeCode";
		List<DictArchiveType> list = getSession().createQuery(hql)
												.setString("archiveTypeCode", archiveTypeCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public void deleteByTypeCode(String archiveTypeCode) {
		// TODO Auto-generated method stub
		String hql = " delete DictArchiveType where archiveTypeCode=:archiveTypeCode";
		getSession().createQuery(hql).setString("archiveTypeCode", archiveTypeCode)
									.executeUpdate();
		
	}
	@SuppressWarnings("unchecked")
	public List<DictArchiveType> getByArchiveTypeFlag(Integer archiveTypeFlag) {
		// TODO Auto-generated method stub
		String hql = " from DictArchiveType where archiveTypeFlag=:archiveTypeFlag " +
				"	order by archiveTypeCode desc";
		List<DictArchiveType> list = getSession().createQuery(hql)
												.setInteger("archiveTypeFlag", archiveTypeFlag)
												.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<String> getExistTypeCodeByList(List<String> codes,Integer archiveType)
	{
		String sql = " SELECT [archiveTypeCode]" +
				"  FROM [CoresSystemSet].[dbo].[DictArchiveType]" +
				"  where archiveTypeFlag=:archiveTypeFlag and archiveTypeCode in (:codeList)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("archiveTypeFlag", archiveType)
										.setParameterList("codeList", codes)
										.list();
		return list;
	}
	

}
