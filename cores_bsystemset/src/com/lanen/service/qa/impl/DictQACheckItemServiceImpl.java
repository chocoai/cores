package com.lanen.service.qa.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.service.qa.DictQACheckItemService;
@Service
public class DictQACheckItemServiceImpl extends BaseDaoImpl<DictQACheckItem> implements DictQACheckItemService {
	
	@SuppressWarnings("unchecked")
	public boolean isExistByTypeAndName(Integer chkItemType, String chkItemName) {
		List<DictQACheckItem> items = getSession().createQuery("from DictQACheckItem item where item.chkItemType=? and item.chkItemName=?")
												.setInteger(0, chkItemType)
												.setString(1,chkItemName)
												.list();
		if(items!=null&&items.size()>0)
			return true;
		return false;
	}
	@SuppressWarnings("unchecked")
	public DictQACheckItem getByItemName(Integer chkItemType, String chkItemName) {
		List<DictQACheckItem> items = getSession().createQuery("from DictQACheckItem item where item.chkItemType=? and item.chkItemName=?")
													.setInteger(0, chkItemType)
													.setString(1,chkItemName)
													.list();
		if(items!=null&&items.size()>0)
		return items.get(0);
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<DictQACheckItem> getAll() {
		List<DictQACheckItem> items = getSession().createQuery("from DictQACheckItem item order by chkItemType")
												  .list();
		
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public List<DictQACheckItem> getByType(Integer chkItemType) {
		List<DictQACheckItem> items = getSession().createQuery("from DictQACheckItem item where item.chkItemType=?")
												.setInteger(0, chkItemType)
												.list();
	
		return items;
		
	}
	
	public Integer getTblConSizeByItem(String chkItemId)
	{
		String hql = "select sum(num) from " +
					"( " +
					"	select chkTbls.chkTblID,count(content.chkContent) num " +
					"	 from( " +
					"		SELECT [chkItemChkTblRegID] ,[chkTblID],[chkItemID]  " +
					"			 FROM [CoresSystemSet].[dbo].[DictChkItemChkTblReg] " +
					"		  where chkItemID=:chkItemId " +
					"	  ) as chkTbls " +
					"	  left join [CoresSystemSet].[dbo].[DictQACheckContentTable] content " +
					"	  on content.chkTblID = chkTbls.chkTblID " +
					"	  group by chkTbls.chkTblID " +
					"  ) as tblConNum ";
		Integer num = (Integer)getSession().createSQLQuery(hql)
								.setParameter("chkItemId", chkItemId)	
								.uniqueResult();
		
		return num;
	}
	
	
	

}
