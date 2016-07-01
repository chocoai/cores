package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.qa.QAChkReport;
import com.lanen.service.qa.QAChkReportService;

@Service
public class QAChkReportServiceImpl extends BaseDaoImpl<QAChkReport> implements QAChkReportService{

	@SuppressWarnings("unchecked")
	public List<Integer> getYears() {
		String sql = "SELECT distinct YEAR([createTime]) year FROM [CoresQA].[dbo].[QAChkReport]";
		List<Integer> years =  getSession().createSQLQuery(sql).list();
		
		return years;
	}
	
	@SuppressWarnings("unchecked")
	public List<QAChkReport> getByTimeStatusAndCondition(Integer time,
			Integer status, String searchCondition,String realName) {
		//condition没实现
		if(searchCondition==null)
		{
			searchCondition="";
		}
		String sql = "from QAChkReport where YEAR(createTime)=:time  and chkReportCode like :reportCode " +
				" and rptState>=3 ";//只有QAM审批通过以后的报告FM和SD才可以查看
		if(realName!=null)
		{
			sql+=" and (sd = :realName or inspector=:realName or QAM=:realName) ";
		}
		if(status!=10)//全部
		{
   			 if(status==2||status==-2||status==4||status==5||status==9)
   			 {
   				 sql+=" and rptState=:status ";
   			 }else {
   				sql+=" and rptState=3 ";
   				if(status==31)
   				{
   					sql+=" and (replyState is null or replyState=0 ) ";
   				}else if(status==32){
   					sql+=" and replyState=1 ";
   				}else if(status==33){
   					sql+=" and (replyState=-1 or replyState=2 ) ";
   				}
   				if(status==34){
   					sql+=" and needDelay=1 ";
   				}else if(status==35){
   					sql+=" and (needDelay=-1 or needDelay=2) ";
   				}
   					
			 }
		}else {
			sql+=" and rptState!=9 ";
		}
		sql+=" order by createTime desc ";
		 Query query =  getSession().createQuery(sql)
									.setInteger("time", time)
									.setString("reportCode","%"+searchCondition+"%");
		 if(realName!=null)
		 {
			 query.setString("realName", realName);
		 }
		 if(status==2||status==-2||status==4||status==5||status==9)//3和全部不算
		 {
			 query.setInteger("status", status);
		 }
		List<QAChkReport> qAChkReports = query.list();
		
		return qAChkReports;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByDateAndSignStatus(Date startDate, Date endDate,String searchCondition) {
		//报告有报告（rptState），回复（replyState）和整改（delayState）三类签字
		//qa管理的的有计划（QAStudyChkIndex chkPlanState），记录（QAChkIndex 2）两类签字
		
		//condition没有作用
		/*String sql = "select linkId,eslink.[recordTime],[chkReportCode],[studyNo],eslink.[esTypeDesc],es.signer" +
				" from" +
				"  (select linkId,[esType],esId,[esTypeDesc],[recordTime],dataId,tblESId from [CoresUserPrivilege].[dbo].[tblESLink]" +
				"   where  (tableName='QAChkReport' or tableName='QAStudyChkIndex' or tableName='QAChkIndex' or tableName='TblStudyFileIndex') " +
				"			and recordTime between :startDate and :endDate" +
				"	)as eslink" +
				"  left join [CoresUserPrivilege].[dbo].[tblES] as es on eslink.esId=es.esId" +
				"  left join (" +
				"	select [chkReportCode] ,[SD] ,[inspector],[inspectorSignTime] ,[QAM]" +
				"	  from [CoresQA].[dbo].[QAChkReport] " +
				"	  where  rptState!=0  or replyState!=0  or delayState!=0 " +
				"  ) as report on dataId=report.chkReportCode" +
				"  left join (" +
				"	SELECT [studyNo],[inspectorAppointState] ,[SD],[inspector],[reportState]" +
				"	 FROM [CoresQA].[dbo].[QAStudyChkIndex] where chkPlanState!=0" +
				"  ) as studyIndex on studyIndex.studyNo=dataId ";
		String sql = "select linkId,eslink.[recordTime],[chkReportCode],eslink.[esTypeDesc],es.signer," +
				" (case when studyIndex.[studyNo] is null then studyFileIndex.studyNo else studyIndex.[studyNo] end) as studyNo " +
				" from" +
				" (select linkId,[esType],esId,[esTypeDesc],[recordTime],dataId,tblESId from [CoresUserPrivilege].[dbo].[tblESLink]" +
				"  where  (tableName='QAChkReport' or tableName='QAStudyChkIndex' or tableName='QAChkIndex' or tableName='TblStudyFileIndex')" +
				"  and recordTime between :startDate and :endDate" +
				"	)as eslink" +
				"  left join [CoresUserPrivilege].[dbo].[tblES] as es on eslink.esId=es.esId" +
				"  left join (" +
				"  select [chkReportCode] ,[SD] ,[inspector],[inspectorSignTime] ,[QAM]" +
				"  from [CoresQA].[dbo].[QAChkReport]" +
				//"  where  rptState!=0  or replyState!=0  or delayState!=0 " +
				"  ) as report on dataId=report.chkReportCode" +
				" left join (" +
				" SELECT [studyNo],[inspectorAppointState] ,[SD],[inspector],[reportState]" +
				"  FROM [CoresQA].[dbo].[QAStudyChkIndex] " +
				"  ) as studyIndex on studyIndex.studyNo=dataId " +
				" left join (" +
				" SELECT [studyFileIndexID],[studyNo]" +
				" FROM [CoresQA].[dbo].[TblStudyFileIndex] " +
				" ) as studyFileIndex on studyFileIndex.[studyFileIndexID]=dataId ";
		 */
		if(searchCondition==null)
			searchCondition = "";
		
		String sql = " select linkId,eslink.[recordTime],eslink.esType," +
				" (case when report.[chkReportCode] is null then QAChkIndex.[chkReportCode] else report.[chkReportCode] end)as chkReportCode" +
				"	,eslink.[esTypeDesc],es.signer," +
				" (case when studyIndex.[studyNo] is not null then studyIndex.[studyNo] " +
				"   else " +
				"		 case when studyFileIndex.studyNo is not null then  studyFileIndex.studyNo else qaChkIndex.studyNo" +
				"	     end" +
				"	end" +
				" ) as studyNo" +
				" from" +
				" (select linkId,[esType],esId,[esTypeDesc],[recordTime],dataId,tblESId from [CoresUserPrivilege].[dbo].[tblESLink]" +
				"  where  (tableName='QAChkReport' or tableName='QAStudyChkIndex' or tableName='QAChkIndex' or tableName='TblStudyFileIndex')" +
				"  and recordTime between :startDate and :endDate" +
				"  )as eslink" +
				"  left join [CoresUserPrivilege].[dbo].[tblES] as es on eslink.esId=es.esId" +
				"  left join (" +
				"  select [chkReportCode] ,[SD] ,[inspector],[inspectorSignTime] ,[QAM]" +
				"  from [CoresQA].[dbo].[QAChkReport]" +
				"  ) as report on dataId=report.chkReportCode" +
				" left join (" +
				" SELECT [studyNo],[inspectorAppointState] ,[SD],[inspector],[reportState]" +
				"  FROM [CoresQA].[dbo].[QAStudyChkIndex] " +
				"  ) as studyIndex on studyIndex.studyNo=dataId " +
				" left join (" +
				" SELECT [studyFileIndexID],[studyNo]" +
				" FROM [CoresQA].[dbo].[TblStudyFileIndex] " +
				" ) as studyFileIndex on studyFileIndex.[studyFileIndexID]=dataId " +
				" left join (" +
				" SELECT [chkReportCode],[studyNo],[chkIndexID]" +
				" FROM [CoresQA].[dbo].[QAChkIndex] " +
				" ) as qaChkIndex on qaChkIndex.[chkIndexID]=dataId " +
				" where  (case when report.[chkReportCode] is null then QAChkIndex.[chkReportCode] else report.[chkReportCode] end) like :searchCondition " +
				" or (case when studyIndex.[studyNo] is not null then studyIndex.[studyNo] " +
				"   else " +
				"		 case when studyFileIndex.studyNo is not null then  studyFileIndex.studyNo else qaChkIndex.studyNo " +
				"	     end " +
				"	end " +
				")  like :searchCondition " +
				" order by eslink.[recordTime] desc";
		List<Map<String, Object>> qAChkReports =  getSession().createSQLQuery(sql)
															.setParameter("startDate", startDate)
															.setParameter("endDate", endDate)
															.setString("searchCondition", "%"+searchCondition+"%")
															.setResultTransformer(new MapResultTransformer())
															.list();
		
		return qAChkReports;
	}
	
	
}
