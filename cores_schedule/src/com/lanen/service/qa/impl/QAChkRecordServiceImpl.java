package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.service.qa.QAChkRecordService;

@Service
public class QAChkRecordServiceImpl extends BaseDaoImpl<QAChkRecord> implements	QAChkRecordService {

	@SuppressWarnings("unchecked")
	public boolean isExistRecord(String qaIndexId,String chkTblContentId)
	{
		boolean flag = false;
		String hql="from QAChkRecord where qachkIndex.chkIndexId=:qaIndexId  and chkTblContentId=:chkTblContentId";
		
		List<QAChkRecord> list = getSession().createQuery(hql)
											.setString("qaIndexId", qaIndexId)
											.setString("chkTblContentId", chkTblContentId)
											.list();
		
		if(list!=null&&list.size()>0)
		{
			flag = true;
		}
		return flag;
	
	}
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getListByIndexIdAndTblId(String qaIndexId) {
		String hql="from QAChkRecord where qachkIndex.chkIndexId=:qaIndexId";
		
		List<QAChkRecord> list = getSession().createQuery(hql)
											.setString("qaIndexId", qaIndexId)
											.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getByStudyNo(String studyNoParam) {
		String hql="from QAChkRecord where qachkIndex.studyNo=:studyNo";
		List<QAChkRecord> list = getSession().createQuery(hql)
											.setString("studyNo", studyNoParam)
											.list();
		return list;	
	}
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getByStudyAndChkTime(String studyNoParam,
			Date startChkDate, Date endChkDate,String qaIndexId) {
		String hql="from QAChkRecord where qachkIndex.studyNo=:studyNo and chkTime between :startChkDate and :endChkDate" ;
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			hql+=" and qachkIndex.chkIndexId=:qaIndexId";
		}
		Query query = getSession().createQuery(hql)
								.setString("studyNo", studyNoParam)
								.setDate("startChkDate", startChkDate)
								.setDate("endChkDate", endChkDate);
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			query.setString("qaIndexId", qaIndexId);
		}
		 List<QAChkRecord> list= query.list();
		return list;	
	}
	public List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(
			String studyNoParam, Date startChkDate, Date endChkDate,
			String qaIndexId) {
		String sql = "select [chkRecordID],qaChkIndex.chkItemName,qaChkIndex.operator,qaChkIndex.chkPlanID,record.[chkIndexID],[chkTblRegID],[chkTblContentID] ,[chkContent],[chkResult]" +
				",[remark],[chkTime],[inspector],[advice],[chkResultFlag],[sn]" +
				" from" +
				"	  (	SELECT [chkRecordID]  ,[chkIndexID],[chkTblRegID] ,[chkTblContentID]" +
				" ,[chkContent],[chkResult] ,[remark] ,[chkTime],[inspector],[advice] ,[chkResultFlag],[sn]" +
				" FROM [CoresQA].[dbo].[QAChkRecord]" +
				"  where  chkTime between :startChkDate and :endChkDate" +
				" ) as record" +
				"  left join [CoresQA].[dbo].[QAChkIndex] as qaChkIndex on record.[chkIndexID] = qaChkIndex.[chkIndexID]" +
				"  where [chkRecordID] not in" +
				"  (" +
				"		select [chkRecordID] from [CoresQA].[dbo].[QAChkReportRecord] as reportRecord " +
				"		where chkTime between :startChkDate and :endChkDate" +
				"  )";
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			sql+=" and qaChkIndex.chkIndexID=:qaIndexId";
		}
		sql+=" order by sn";
		Query query = getSession().createSQLQuery(sql)
									.setParameter("startChkDate", startChkDate)
									.setParameter("endChkDate", endChkDate)
									.setResultTransformer(new MapResultTransformer());
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			query.setParameter("qaIndexId", qaIndexId);
		}
		List<Map<String, Object>> list = query.list();
		return list;
	}
}
