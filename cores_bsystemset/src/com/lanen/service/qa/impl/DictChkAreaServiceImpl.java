package com.lanen.service.qa.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictChkArea;
import com.lanen.service.qa.DictChkAreaService;

@Service
public class DictChkAreaServiceImpl extends BaseDaoImpl<DictChkArea> implements
		DictChkAreaService {

	@SuppressWarnings("unchecked")
	public boolean isExistChildByParentId(String parentAreaID)
	{
		String hql = "from DictChkArea where parentAreaID=:parentAreaID";
		List<DictChkArea> list = getSession().createQuery(hql)
											.setString("parentAreaID", parentAreaID)
											.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<DictChkArea> getAll() {
		String hql = "from DictChkArea";
		List<DictChkArea> list = getSession().createQuery(hql)
											.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByName(String areaName){
		
		String hql = "from DictChkArea where areaName=:areaName";
		List<DictChkArea> list = getSession().createQuery(hql)
											.setString("areaName", areaName)
											.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByNameExceptOne(String areaName,String areaID){
		
		String hql = "from DictChkArea where areaName=:areaName and areaID!=:areaID";
		List<DictChkArea> list = getSession().createQuery(hql)
											.setString("areaName", areaName)
											.setString("areaID", areaID)
											.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}
		return false;
	}
}
