package com.lanen.service.archive.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.archive.DictSoptype;
import com.lanen.service.archive.DictSOPTypeService;
@Service
public class DictSOPTypeServiceImpl extends BaseDaoImpl<DictSoptype> implements
		DictSOPTypeService {
	
	public Integer getMaxSnByPid(String pid) {
		String sql = "select max(sn) from [CoresSystemSet].[dbo].[DictSOPType] ";
		if(pid==null||"".equals(pid))
		{
			sql += " where pid is null or pid='' ";
		}else {
			sql += " where pid=:pid";
		}
		Query query =  getSession().createSQLQuery(sql);
		if(pid!=null&&!"".equals(pid))
		{
			query.setParameter("pid", pid);
		}
		Object sn = query.uniqueResult();
		if(sn!=null)
		{
			return (Integer)sn;
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	public boolean isExist(String sopName, String sopTypeCode) {
		String hql = " from DictSoptype where sopname=:sopName or soptypeCode=:sopTypeCode";
		List<DictSoptype> list = getSession().createQuery(hql)
												.setString("sopName", sopName)
												.setString("sopTypeCode", sopTypeCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistExceptOne(String id, String sopName,
			String sopTypeCode) {
		String hql = " from DictSoptype where (sopname=:sopName or soptypeCode=:sopTypeCode) and id!=:id ";
		List<DictSoptype> list = getSession().createQuery(hql)
												.setString("sopName", sopName)
												.setString("sopTypeCode", sopTypeCode)
												.setString("id",id)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistChild(String id) {
		String hql = " from DictSoptype where pid=:id";
		List<DictSoptype> list = getSession().createQuery(hql)
												.setString("id", id)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public DictSoptype getByCode(String soptypeCode){
		String hql = " from DictSoptype where soptypeCode=:soptypeCode";
		List<DictSoptype> list = getSession().createQuery(hql)
												.setString("soptypeCode", soptypeCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<String> getExistSOPTypeCodeByList(List<String> codes)
	{
		String hql = " SELECT [SOPTypeCode]  FROM [CoresSystemSet].[dbo].[DictSOPType]" +
				" where SOPTypeCode in (:codes)";
		List<String> list = getSession().createSQLQuery(hql)
										.setParameterList("codes", codes)
										.list();
		return list;
	}

}
