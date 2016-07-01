package com.lanen.service.qa.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkSop;
import com.lanen.service.qa.QAChkSopService;
@Service
public class QAChkSopServiceImpl extends BaseDaoImpl<QAChkSop> implements QAChkSopService {

	@SuppressWarnings("unchecked")
	public List<QAChkSop> getByChkIndexId(String qaIndexId) {
		String hql ="from QAChkSop where qachkIndex.chkIndexId=:chkIndexId ";
		List<QAChkSop> list = getSession().createQuery(hql)
										.setString("chkIndexId",qaIndexId )
										.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkSop> getByChkIndexIds(List<String> qaIndexIds) {
		if(qaIndexIds!=null&&!"".equals(qaIndexIds))
		{
			String hql ="from QAChkSop where qachkIndex.chkIndexId in (:chkIndexIds) ";
			List<QAChkSop> list = getSession().createQuery(hql)
			.setParameterList("chkIndexIds",qaIndexIds )
			.list();
			return list;			
		}else {
			return null;
		}
		
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByIndexIdAndFileId(String qaIndexId, String fileRegId) {
		String sql="SELECT [sopRecordID] FROM [CoresQA].[dbo].[QAChkSOP]" +
				"  where chkIndexID=:qaIndexId and fileRecordID=:fileRegId";
		List<String> list = getSession().createSQLQuery(sql)
									.setParameter("qaIndexId", qaIndexId)
									.setParameter("fileRegId", fileRegId)
									.list();
		if(list!=null&&list.size()>0)
			return true;
		
		return false;
	}

	

}
