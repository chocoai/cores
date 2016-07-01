package com.lanen.service.qa.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.util.DateUtil;

@Service
public class QAChkReportServiceImpl extends BaseDaoImpl<QAChkReport> implements QAChkReportService{

	@SuppressWarnings("unchecked")
	public String getKeyByStudyNo(String dateStr) {
		String sql = "SELECT  [chkReportCode] FROM [CoresQA].[dbo].[QAChkReport] where [chkReportCode] like :id  order by [chkReportCode] desc";
		List<String> ids = getSession().createSQLQuery(sql)
											.setParameter("id", dateStr+"%")
											.list();
		if(ids!=null&&ids.size()>0)
		{
			return ids.get(0);
		}
		return dateStr+"00";
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(
			String chkReportCode, Date startChkDate, Date endChkDate,
			String[] qaIndexIds) {
		String sql = "select reportRecord.[chkRecordID],chkType,chkItemName,reportRecord.confirmer,reportRecord.chkTime,reportRecord.chkContent,reportRecord.chkResult,reportRecord.chkResultDesc,reportRecord.advice" +
				" from " +
				"(	SELECT [chkRecordID]" +
				" ,[chkContent],[chkResult],[chkResultDesc] ,[remark] ,[chkTime],[inspector],[advice] ,[chkResultFlag],[confirmer]" +
				" FROM [CoresQA].[dbo].[QAChkReportRecord]" +
				"  where  chkTime between :startChkDate and :endChkDate and chkReportCode=:chkReportCode" +
				" ) as reportRecord" +
				" join" +
				" [CoresQA].[dbo].[QAChkRecord] as record on record.[chkRecordID]=reportRecord.[chkRecordID]" +
				" join [CoresQA].[dbo].[QAChkIndex] as qaChkIndex on record.[chkIndexID] = qaChkIndex.[chkIndexID]";
		if(qaIndexIds!=null&&qaIndexIds.length>0)
		{
			sql+=" and qaChkIndex.chkIndexID in (:qaIndexIds)";
		}
		sql+=" order by qaChkIndex.chkindexID,chkTblRegID,sn";
		Query query = getSession().createSQLQuery(sql)
									.setParameter("startChkDate", startChkDate)
									.setParameter("endChkDate", endChkDate)
									.setParameter("chkReportCode",chkReportCode);
									
		if(qaIndexIds!=null&&qaIndexIds.length>0)
		{
			query.setParameterList("qaIndexIds", qaIndexIds);
		}
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNeedDoByCondition(String studyNoParam,User user,Integer status,Integer catalog,String searchString)
	{
		
		if(searchString==null)
			searchString="";
		
		String sql = "SELECT distinct(report.[chkReportCode]),chkIndex.studyNo,report.inspector,[rptState],[needReply],[replyState] "+
					 "     ,[replyFMApprovalTime],[replyFMName],[replyFMReviewResult],[replyFMReveiwRemark],[replyFMReveiwTime],[replyInspectorReceiveTime],[replyInspectorReciveName] "+
					 "     ,[needReChk],[reChkState],[needDelay],[delayState],[delayFMApprovalTime],[delayFMName],[delayQAUReceiveTime],[delayQAUReceiveName],item.studyName "+
					" from(SELECT [chkReportCode],[SD] ,[inspector] ,[inspectorSignTime],[QAM],[QAMSignTime],[rptState],[needReply],[replyState] "+
					 "     ,[replyFMApprovalTime],[replyFMName],[replyFMReviewResult],[replyFMReveiwRemark],[replyFMReveiwTime],[replyInspectorReceiveTime],[replyInspectorReciveName] "+
					 "     ,[needReChk],[reChkState],[needDelay],[delayState],[delayFMApprovalTime],[delayFMName],[delayQAUReceiveTime],[delayQAUReceiveName] "+
					 "     ,[finalFinishTime],[finalFinishSign],[createTime] "+
					"		 FROM [CoresQA].[dbo].[QAChkReport] where rptState!=9) report "+
					 " join (select studyNo,chkReportCode from [CoresQA].[dbo].[QAChkIndex] where chkReportCode is not null)chkIndex "+
					 " on report.chkReportCode = chkIndex.chkReportCode " +
					 " left join [CoresQA].[dbo].[QAStudyChkIndex] studyIndex on studyIndex.studyNo=chkIndex.studyNo " +
					 "	left join [CoresContract].[dbo].[tblStudyItem] item	on item.studyNo=studyIndex.studyNo"+
					 " where (studyIndex.reportState is null or studyIndex.reportState=0 ) and (chkPlanFinishFlag is null  or chkPlanFinishFlag =0) ";
		if(user!=null&&!"".equals(user))
		{
			sql+=" and report.inspector=:inspector ";
		}
		if(studyNoParam!=null)
		{
			sql +=" and chkIndex.studyNo=:studyNo ";
		}
		
		if(catalog!=null&&catalog!=0&&catalog!=1)
		{
			/*<option value="0">全部</option>
			<option value="1">基于研究的检查</option>
			<option value="2">基于过程的检查</option>
			<option value="3">基于设施的检查</option>*/
			//hql +=" and chkPlanType=:catalog ";
			sql+=" and 1=0 ";//如果不是全部或者基于研究的检查就直接false
		}
		if(status!=null&&status!=0&&status!=1)//0代表全部 1未实施，2已实施
		{
			sql+=" and 1=0 ";
		}
		
		sql+=" and ( chkIndex.studyNo like :searchString or report.[chkReportCode] like :searchString ) " ;
		sql+= "  order by chkIndex.studyNo desc  ";
		
		Query query = getSession().createSQLQuery(sql);
		if(studyNoParam!=null)//null是这页面的请求，""是子页面的请求
		{
			query.setString("studyNo", studyNoParam);
		}
		if(user!=null&&!"".equals(user))
		{
			query.setString("inspector", user.getRealName());
		}
		
		query.setString("searchString", "%"+searchString+"%");
		
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer())
											.list();	
		
		

		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getStudyInfoByReportCodeList(String[] reportIds)
	{
		String sql = "select report.[chkReportCode],chkIndex.studyNo,item.studyName  from " +
				"  (" +
				"	SELECT [chkReportCode],[SD]" +
				"	 FROM [CoresQA].[dbo].[QAChkReport] where chkReportCode in (:reportIds) " +
				"  ) as report" +
				"  left join " +
				"  ( SELECT [studyNo],[chkReportCode]  FROM [CoresQA].[dbo].[QAChkIndex]" +
				"    where chkReportCode is not null  group by [chkReportCode],[studyNo]" +
				"  ) as chkIndex on chkIndex.chkReportCode=report.chkReportCode " +
				"  left join [CoresContract].[dbo].[tblStudyItem] item on item.studyNo=chkIndex.studyNo";
		
		Query query = getSession().createSQLQuery(sql)
									.setParameterList("reportIds", reportIds);
		
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer())
												.list();	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyNo(String studyNoParam) {
		
		String sql = "SELECT  report.[chkReportCode],[SD],[inspector] ,[inspectorSignTime] ,[QAM],[QAMSignTime] ,[rptState],[needReply] " +
				" ,[replyState],[replyFMApprovalTime] ,[replyFMName] ,[replyFMReviewResult] ,[replyFMReveiwRemark],[replyFMReveiwTime],[replyInspectorReceiveTime]" +
				" ,[replyInspectorReciveName],[needReChk],[reChkState] ,[needDelay],[delayState],[delayFMApprovalTime]" +
				" ,[delayFMName],[delayQAUReceiveTime] ,[delayQAUReceiveName],[finalFinishTime],[finalFinishSign],[createTime]" +
				"  FROM [CoresQA].[dbo].[QAChkReport] report" +
				"  left join" +
				"  (SELECT [studyNo] ,[chkReportCode] FROM [CoresQA].[dbo].[QAChkIndex] where chkReportCode is not null  " +
				"	group by [studyNo]  ,[chkReportCode]) as chkIndex" +
				" on chkIndex.chkReportCode = report.chkReportCode " +
				" where studyNo=:studyNo " +
				" order by createTime desc";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql )
								.setParameter("studyNo", studyNoParam)
								.setResultTransformer(new MapResultTransformer())
								.list();
		
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByConditions(Date reportStartDate,
			Date reportEndDate, Integer reportStatus, Integer reportCatalog,
			String reportSearcher) {
		
		String hql = "SELECT  report.[chkReportCode],[SD],[inspector] ,[inspectorSignTime] ,[QAM],[QAMSignTime] " +
					",[rptState],[needReply] ,[replyState],[replyFMApprovalTime] ,[replyFMName] ,[replyFMReviewResult] " +
					",[replyFMReveiwRemark],[replyFMReveiwTime],[replyInspectorReceiveTime],[replyInspectorReciveName]" +
					",[needReChk],[reChkState] ,[needDelay],[delayState],[delayFMApprovalTime],[delayFMName]" +
					",[delayQAUReceiveTime] ,[delayQAUReceiveName],[finalFinishTime],[finalFinishSign],[createTime]" +
					"  FROM [CoresQA].[dbo].[QAChkReport] report" +
					"  left join" +
					"  (SELECT  [chkReportCode],chkType FROM [CoresQA].[dbo].[QAChkIndex] where chkReportCode is not null " +
					"	group by  [chkReportCode],chkType) as chkIndex" +
					"  on chkIndex.chkReportCode = report.chkReportCode" +
					"  where 1=1 ";
		
		if(reportStartDate!=null&&!"".equals(reportStartDate)&&reportEndDate!=null)
		{
			//hql+=" and qachkReport.createTime between :reportStartDate and :reportEndDate";
			hql+=" and createTime between :reportStartDate and :reportEndDate ";
		}
		if(reportStatus!=null&&reportStatus!=0)//0是全部
		{
			if(reportStatus==31)//待接收回复或整改
			{
				//hql+=" and qachkReport.rptState=3 and " +
				//		" ((qachkReport.replyFmapprovalTime is not null and qachkReport.replyInspectorReceiveTime is null) or " +
				//		"  (qachkReport.delayFmapprovalTime is not null and qachkReport.delayQaureceiveTime is null))";	
				hql += " and rptState=3 " +
						"	and ((replyState=2 and replyInspectorReceiveTime is null) or" +
						"	(needDelay=2 and delayQAUReceiveTime is null)	)";
			}else{
				hql+=" and rptState=:reportStatus";				
			}
		}
		if(reportCatalog!=null&&reportCatalog!=0)//0是全部
		{
			hql+=" and chkType=:reportCatalog ";
		}
		if(reportSearcher!=null&&!"".equals(reportSearcher))
		{
			hql+=" and report.chkReportCode like :reportSearcher ";
		}
		hql+=" order by createTime desc ";
		Query query = getSession().createSQLQuery(hql);
		if(reportStartDate!=null&&!"".equals(reportStartDate)&&reportEndDate!=null)
		{
			query.setParameter("reportStartDate", DateUtil.dateToString(reportStartDate,"yyyy-MM-dd"))
				.setParameter("reportEndDate", DateUtil.dateToString(reportEndDate,"yyyy-MM-dd"));
		}
		if(reportStatus!=null&&reportStatus!=0&&reportStatus!=31)//0是全部
		{
			query.setParameter("reportStatus",reportStatus);
		}
		if(reportCatalog!=null&&reportCatalog!=0)//0是全部
		{
			query.setParameter("reportCatalog",reportCatalog);
		}					
		if(reportSearcher!=null&&!"".equals(reportSearcher))
		{
			query.setParameter("reportSearcher", "%"+reportSearcher+"%");
		}
		
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer()).list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return new ArrayList<Map<String, Object>>();
	}
	
}
