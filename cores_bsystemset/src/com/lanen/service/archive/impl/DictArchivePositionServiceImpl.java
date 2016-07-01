package com.lanen.service.archive.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.archive.DictArchivePosition;
import com.lanen.service.archive.DictArchivePositionService;
@Service
public class DictArchivePositionServiceImpl extends BaseDaoImpl<DictArchivePosition> implements
DictArchivePositionService {
	
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
	public boolean isExist(String positionName) {
		String hql = " from DictArchivePosition where positionName=:positionName ";
		List<DictArchivePosition> list = getSession().createQuery(hql)
												.setString("positionName", positionName)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistExceptOne(String id, String positionName) {
		String hql = " from DictArchivePosition where positionName=:positionName and id!=:id ";
		List<DictArchivePosition> list = getSession().createQuery(hql)
												.setString("positionName", positionName)
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
		String hql = " from DictArchivePosition where pid=:id";
		List<DictArchivePosition> list = getSession().createQuery(hql)
												.setString("id", id)
												.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public DictArchivePosition getByCode(String soptypeCode){
		String hql = " from DictArchivePosition where soptypeCode=:soptypeCode";
		List<DictArchivePosition> list = getSession().createQuery(hql)
												.setString("soptypeCode", soptypeCode)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	

}
