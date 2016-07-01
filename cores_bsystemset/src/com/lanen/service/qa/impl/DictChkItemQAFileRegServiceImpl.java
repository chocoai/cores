package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.service.qa.DictChkItemQAFileRegService;

@Service
public class DictChkItemQAFileRegServiceImpl extends BaseDaoImpl<DictChkItemQAFileReg> implements DictChkItemQAFileRegService {
	@SuppressWarnings("unchecked")
	public List<DictChkItemQAFileReg> getByChkItemId(String chkItemId) {
		String hql="from DictChkItemQAFileReg reg where reg.dictQacheckItem.chkItemId=:chkItemId ";
		List<DictChkItemQAFileReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictChkItemQAFileReg> getByFileRegId(String fileRegId)
	{
		String hql="from DictChkItemQAFileReg reg where reg.fileRegId=:fileRegId ";
		List<DictChkItemQAFileReg> list = getSession().createQuery(hql)
											.setString("fileRegId", fileRegId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public DictChkItemQAFileReg getByChkItemIdAndChkTblId(String chkItemId,String fileRegId) {
		String hql="from DictChkItemQAFileReg reg where reg.dictQacheckItem.chkItemId=:chkItemId and reg.fileRegId=:fileRegId";
		List<DictChkItemQAFileReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.setString("fileRegId", fileRegId)
											.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		else
			return null;
	}
}
