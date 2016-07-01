package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.service.qa.DictChkItemChkTblRegService;
@Service
public class DictChkItemChkTblRegServiceImpl extends BaseDaoImpl<DictChkItemChkTblReg> implements DictChkItemChkTblRegService{

	@SuppressWarnings("unchecked")
	public List<DictChkItemChkTblReg> getByChkItemId(String chkItemId) {
		String hql="from DictChkItemChkTblReg reg where reg.dictQacheckItem.chkItemId=:chkItemId ";
		List<DictChkItemChkTblReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictChkItemChkTblReg> getByChkTblId(String chkTblId)
	{
		String hql="from DictChkItemChkTblReg reg where reg.dictQacheckTable.chkTblId=:chkTblId ";
		List<DictChkItemChkTblReg> list = getSession().createQuery(hql)
											.setString("chkTblId", chkTblId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public DictChkItemChkTblReg getByChkItemIdAndChkTblId(String chkItemId,
			String chkTblId) {
		String hql="from DictChkItemChkTblReg reg where reg.dictQacheckItem.chkItemId=:chkItemId and reg.dictQacheckTable.chkTblId=:chkTblId";
		List<DictChkItemChkTblReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.setString("chkTblId", chkTblId)
											.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		else
			return null;
	}

	
}
