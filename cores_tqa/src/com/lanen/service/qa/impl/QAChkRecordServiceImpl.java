package com.lanen.service.qa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.util.DateUtil;

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
								.setString("startChkDate", DateUtil.dateToString(startChkDate,"yyyy-MM-dd"))
								.setString("endChkDate", DateUtil.dateToString(endChkDate,"yyyy-MM-dd"));
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			query.setString("qaIndexId", qaIndexId);
		}
		 List<QAChkRecord> list= query.list();
		return list;	
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(
			String studyNoParam, Date startChkDate, Date endChkDate,
			String[] qaIndexIds,User user) {
		String sql = "select [chkRecordID],qaChkIndex.chkItemName,qaChkIndex.chkType,qaChkIndex.operator,qaChkIndex.chkPlanID,record.[chkIndexID],[chkTblRegID],[chkTblContentID] ,[chkContent],[chkResult],[chkResultDesc] " +
				",[remark],[chkTime],[inspector],[advice],[chkResultFlag],[sn]" +
				" from" +
				"	  (	SELECT [chkRecordID]  ,[chkIndexID],[chkTblRegID] ,[chkTblContentID]" +
				" ,[chkContent],[chkResult] ,[chkResultDesc],[remark] ,[chkTime],[inspector],[advice] ,[chkResultFlag],[sn]" +
				" FROM [CoresQA].[dbo].[QAChkRecord]" +
				"  where  chkTime between :startChkDate and :endChkDate and [inspector]=:realName" +
				" ) as record" +
				"  left join [CoresQA].[dbo].[QAChkIndex] as qaChkIndex on record.[chkIndexID] = qaChkIndex.[chkIndexID]" +
				"  where qaChkIndex.studyNo=:studyNo and qaChkIndex.chkState=2 and qaChkIndex.chkReportCode is null ";
		if(qaIndexIds!=null&&qaIndexIds.length>0)
		{
			sql+=" and qaChkIndex.chkIndexID in (:qaIndexIds)";
		}
		sql+=" order by chkindexID,chkTblRegID,sn";
		Query query = getSession().createSQLQuery(sql)
									.setParameter("startChkDate", startChkDate)
									.setParameter("endChkDate", endChkDate)
									.setParameter("studyNo",studyNoParam)
									.setParameter("realName", user.getRealName());
									
		if(qaIndexIds!=null&&qaIndexIds.length>0)
		{
			query.setParameterList("qaIndexIds", qaIndexIds);
		}
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getByTblRegId(String tblRegId) {
		String hql="from QAChkRecord where qachkTblReg.chkTblRegId=:chkTblRegId " ;
	
		Query query = getSession().createQuery(hql)
								.setString("chkTblRegId", tblRegId);
		
		 List<QAChkRecord> list= query.list();
		return list;	
	}
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getByStudyNoTimeItemStatusAndUser(
			String studyNoParam, Date startChkDate, Date endChkDate,
			String qaIndexId, Integer chkIndexStatus, String realName) {
		
		String hql="from QAChkRecord where qachkIndex.studyNo=:studyNo and inspector=:realName";
		if(startChkDate!=null&&endChkDate!=null)
		{
			hql+=" and chkTime between :start and :end ";
		}
		//0是全部，1是未签字，2是签字
		if(chkIndexStatus!=null&&chkIndexStatus!=0)
		{
			if(chkIndexStatus==1)
			{
				hql+=" and qachkIndex.chkState!=2";
			}
			if(chkIndexStatus==2)
			{
				hql+=" and qachkIndex.chkState=2";
			}
		}
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			hql+=" and qachkIndex.chkIndexId=:chkIndexId";
		}
		hql+=" order by chkTime desc,qachkIndex.chkItemName asc";
		Query query = getSession().createQuery(hql)
								.setString("studyNo", studyNoParam)
								.setString("realName", realName);
		
		if(startChkDate!=null&&endChkDate!=null)
		{
			query.setString("start", DateUtil.dateToString(startChkDate,"yyyy-MM-dd"));
			query.setString("end", DateUtil.dateToString(endChkDate,"yyyy-MM-dd"));
		}
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			query.setString("chkIndexId",qaIndexId);
		}
		List<QAChkRecord> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkRecord> getByChkIndexId(String chkIndexId)
	{
		String hql="from QAChkRecord where qachkIndex.chkIndexId=:chkIndexId " ;
		
		Query query = getSession().createQuery(hql)
								.setString("chkIndexId", chkIndexId);
		
		 List<QAChkRecord> list= query.list();
		return list;	
	}
	@SuppressWarnings("unchecked")
	public Map<String,Object> getSizeAndValidByChkIndexId(String chkIndexId){
		Map<String,Object> map = new HashMap<String, Object>();
		String hql="SELECT count([chkRecordID]) num   FROM [CoresQA].[dbo].[QAChkRecord]" +
				"  where chkIndexID=:chkIndexId " +
				"  group by [chkIndexID] " ;
		
		Query query = getSession().createSQLQuery(hql)
								.setParameter("chkIndexId", chkIndexId);
		
		 Integer num = (Integer)query.uniqueResult();
		 map.put("num", num);
		 
		 String isValSql = "select count(validList.valid) validNum from" +
		 		" (SELECT  (case when [chkResultFlag]!=0 then '1' else '0' end) as valid" +
		 		"  FROM [CoresQA].[dbo].[QAChkRecord]" +
		 		"  where  chkIndexID=:chkIndexId) as validList" +
		 		"  where validList.valid !=0";
		 Query query2 = getSession().createSQLQuery(isValSql)
									.setParameter("chkIndexId", chkIndexId);
		 Integer validNum = (Integer)query2.uniqueResult();
		 if(validNum!=null&&validNum>0)
		 {
			 map.put("isValid", true);
		 }else {
			 map.put("isValid", false);
		}
		return map;
		
	}
}
