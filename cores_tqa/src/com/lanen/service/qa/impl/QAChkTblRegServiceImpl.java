package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkTblReg;
import com.lanen.service.qa.QAChkTblRegService;

@Service
public class QAChkTblRegServiceImpl extends BaseDaoImpl<QAChkTblReg> implements QAChkTblRegService{
	@SuppressWarnings("unchecked")
	public QAChkTblReg getByChkIndexAndTblCodeAndName(String qaIndexId,String chkTblCode,String chkTblName)
	{
		String hql = "from QAChkTblReg where qachkIndex.chkIndexId=:chkIndexId and chkTblCode=:chkTblCode and chkTblName=:chkTblName";
		List<QAChkTblReg> list = getSession().createQuery(hql)
												.setString("chkIndexId", qaIndexId)
												.setString("chkTblCode", chkTblCode)
												.setString("chkTblName", chkTblName)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Integer getMaxSnByQachkIndex(String chkIndexId)
	{
		String hql = "from QAChkTblReg where qachkIndex.chkIndexId=:chkIndexId order by sn desc";
		List<QAChkTblReg> list = getSession().createQuery(hql)
												.setString("chkIndexId", chkIndexId)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getSn();
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkTblReg> getByChkIndexId(String chkIndexId)
	{
		String hql = "from QAChkTblReg where qachkIndex.chkIndexId=:chkIndexId ";
		List<QAChkTblReg> list = getSession().createQuery(hql)
											.setString("chkIndexId", chkIndexId)
											.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public Integer getSizeByChkIndexId(String chkIndexId){
		String hql = "SELECT count([chkTblRegID]) num  FROM [CoresQA].[dbo].[QAChkTblReg]" +
				"  where chkIndexID=:chkIndexID " +
				"  group by [chkIndexID]";
		Integer num = (Integer)getSession().createSQLQuery(hql)
								.setParameter("chkIndexID", chkIndexId)	
								.uniqueResult();
		
		return num;
	}
}
