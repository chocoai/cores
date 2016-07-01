package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictScheduleChkItem;
import com.lanen.service.qa.DictScheduleChkItemService;

@Service
public class DictScheduleChkItemServiceImpl extends BaseDaoImpl<DictScheduleChkItem> implements	DictScheduleChkItemService {

	@SuppressWarnings("unchecked")
	public DictScheduleChkItem getByChkItemIdAndChkTblId(String id,	String chkItemId) {
		
		String hql="from DictScheduleChkItem reg where reg.dictQACheckItem.chkItemId=:chkItemId and reg.taskNameId=:id";
		List<DictScheduleChkItem> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.setString("id", id)
											.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	public List<DictScheduleChkItem> getByScheduleName(String taskName) {
		String hql="from DictScheduleChkItem reg where reg.taskName=:taskName";
		List<DictScheduleChkItem> list = getSession().createQuery(hql)
											.setString("taskName", taskName)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictScheduleChkItem> getByScheduleNameId(String taskNameId) {
		String hql="from DictScheduleChkItem reg where reg.taskNameId=:taskNameId";
		List<DictScheduleChkItem> list = getSession().createQuery(hql)
											.setString("taskNameId", taskNameId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictScheduleChkItem> getByChkItemId(String chkItemId)
	{
		String hql="from DictScheduleChkItem reg where dictQACheckItem.chkItemId=:chkItemId";
		List<DictScheduleChkItem> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.list();
		return list;
	}
}
