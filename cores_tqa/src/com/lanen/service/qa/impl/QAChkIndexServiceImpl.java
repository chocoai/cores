package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.util.DateUtil;

@Service
public class QAChkIndexServiceImpl extends BaseDaoImpl<QAChkIndex> implements
		QAChkIndexService {
	@SuppressWarnings("unchecked")
	public QAChkIndex isExistByChkPlanIdAndChkItemName(String chkPlanId,
			String chkItemName) {
		
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where chkPlanId=:chkPlanId and chkItemName=:chkItemName")
											.setString("chkPlanId", chkPlanId)
											.setString("chkItemName",chkItemName)
											.list();
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}

		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByStudyNoAndTime(String studyNo,Date startChkDate, Date endChkDate,User user) {
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where studyNo=:studyNo and chkFinishTime between :startChkDate and :endChkDate" +
															" and chkFinishTime is not null and qachkReport is null " +
															" and operator=:realName" +
															" order by createTime desc")
											.setString("studyNo", studyNo)
											.setString("startChkDate", DateUtil.dateToString(startChkDate,"yyyy-MM-dd"))
											.setString("endChkDate",DateUtil.dateToString(endChkDate,"yyyy-MM-dd"))
											.setString("realName", user.getRealName())
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	
	public List<QAChkIndex> getByStudyNo(String studyNoParam) {
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where studyNo=:studyNo order by qachkReport.createTime desc")
				.setString("studyNo", studyNoParam)
				.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<String> getByReport(String chkReportCode,Date start,Date end,User user) {
		String sql="SELECT [chkIndexID]  FROM [CoresQA].[dbo].[QAChkIndex] where chkReportCode=:chkReportCode ";
		if(start!=null&&!"".equals(start))
		{
			sql+=" and chkFinishTime>=:start";
		}
		if(end!=null&&!"".equals(end))
		{
			sql+=" and chkFinishTime<=:end";
		}
		
		Query query =  getSession().createSQLQuery(sql)
								.setParameter("chkReportCode", chkReportCode);
		
		if(start!=null&&!"".equals(start))
		{
			query.setParameter("start", DateUtil.dateToString(start,"yyyy-MM-dd"));
		
		}
		if(end!=null&&!"".equals(end))
		{
			query.setParameter("end",DateUtil.dateToString(end,"yyyy-MM-dd"));
		}
			
		List<String> list =query.list();
		
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<String> getByReports(String[] reportIds,User user) {
		List<String> list = getSession().createSQLQuery("SELECT [chkIndexID]  FROM [CoresQA].[dbo].[QAChkIndex]" +
													"	where chkReportCode in (:reportIds) ")
											.setParameterList("reportIds", reportIds)
											//.setParameter("realName", user.getRealName())
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public QAChkIndex isExistByStudyNoChkTypeAndChkItemName(String studyNo, Integer chkType,String chkItemName) {
		// 针对方案和报告的
		List<QAChkIndex> list = getSession().createQuery("from QAChkIndex where studyNo=:studyNo and chkType=:chkType and chkItemName=:chkItemName")
											.setString("studyNo", studyNo)
											.setInteger("chkType", chkType)
											.setString("chkItemName", chkItemName)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}

		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByStudyNoAndUser(String studyNo,Date startChkDate,Date endChkDate,String realName) {
		String sql ="from QAChkIndex where studyNo=:studyNo and operator=:operator " +
				" and (createTime between :startChkDate and :endChkDate or chkFinishTime between :startChkDate and :endChkDate ) " ;
		
		sql+="order by createTime desc";
		
		Query query = getSession().createQuery(sql)
								.setString("studyNo", studyNo)
								.setString("operator", realName)
								.setString("startChkDate", DateUtil.dateToString(startChkDate,"yyyy-MM-dd"))
								.setString("endChkDate",DateUtil.dateToString(endChkDate,"yyyy-MM-dd"));
		List<QAChkIndex> list = query.list();
		
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByConditions(Date reportStartDate,
			Date reportEndDate, Integer reportStatus, Integer reportCatalog,
			String reportSearcher) {
		
		String hql = "from QAChkIndex where 1=1 ";
		if(reportStartDate!=null&&!"".equals(reportStartDate)&&reportEndDate!=null)
		{
			hql+=" and qachkReport.createTime between :reportStartDate and :reportEndDate";
		}
		if(reportStatus!=null&&reportStatus!=0)//0是全部
		{
			if(reportStatus==31)//待接收回复或整改
			{
				hql+=" and qachkReport.rptState=3 and " +
						" ((qachkReport.replyFmapprovalTime is not null and qachkReport.replyInspectorReceiveTime is null) or " +
						"  (qachkReport.delayFmapprovalTime is not null and qachkReport.delayQaureceiveTime is null))";	
			}else{
				hql+=" and qachkReport.rptState=:reportStatus";				
			}
		}
		if(reportCatalog!=null&&reportCatalog!=0)//0是全部
		{
			hql+=" and chkType=:reportCatalog ";
		}
		if(reportSearcher!=null&&!"".equals(reportSearcher))
		{
			hql+=" and qachkReport.chkReportCode like :reportSearcher ";
		}
		hql+=" order by qachkReport.createTime desc ";
		Query query = getSession().createQuery(hql);
		if(reportStartDate!=null&&!"".equals(reportStartDate)&&reportEndDate!=null)
		{
			query.setString("reportStartDate", DateUtil.dateToString(reportStartDate,"yyyy-MM-dd"))
				.setString("reportEndDate", DateUtil.dateToString(reportEndDate,"yyyy-MM-dd"));
		}
		if(reportStatus!=null&&reportStatus!=0&&reportStatus!=31)//0是全部
		{
			query.setInteger("reportStatus",reportStatus);
		}
		if(reportCatalog!=null&&reportCatalog!=0)//0是全部
		{
			query.setInteger("reportCatalog",reportCatalog);
		}					
		if(reportSearcher!=null&&!"".equals(reportSearcher))
		{
			query.setString("reportSearcher", "%"+reportSearcher+"%");
		}
		List<QAChkIndex> list = query.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByReportCode(String chkReportCode) {
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getByReportCodes(String[] chkReportCode) {
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode in (:chkReportCode)")
											.setParameterList("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public String getStudyNoByReportCode(String chkReportCode)
	{
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getStudyNo();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Integer getChkTypeByReportCode(String chkReportCode)
	{
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where qachkReport.chkReportCode=:chkReportCode")
											.setString("chkReportCode", chkReportCode)
											.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getChkType();
		}
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public List<QAChkIndex> getNoSignByIds(String[] chkIndexIdList)
	{
		List<QAChkIndex> list = getSession().createQuery("FROM QAChkIndex where chkIndexId in (:chkIndexIdList) " +
														"	and (chkState is null or chkState!=2) ")
								.setParameterList("chkIndexIdList", chkIndexIdList)
								.list();
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoFinTableByItem(String chkItemId){
		String sql = "select dictItem.chkItemName, chkTbl.chkTblCode code,chkTbl.chkTblName name,dictTable.chkTblCode,dictTable.chkTblName" +
				"	 from(" +
				"		SELECT [chkItemChkTblRegID] ,[chkTblID],[chkItemID]  " +
				"			 FROM [CoresSystemSet].[dbo].[DictChkItemChkTblReg]" +
				"		  where chkItemID=:chkItemId" +
				"	  ) as chkTbls" +
				"	  left join [CoresSystemSet].[dbo].[DictQACheckItem] dictItem" +
				"	  on dictItem.chkItemID = chkTbls.chkItemID" +
				"	   left join [CoresQA].[dbo].[QAChkIndex] chkIndex" +
				"	   on chkIndex.chkItemName = dictItem.chkItemName" +
				"	    left join [CoresSystemSet].[dbo].[DictQACheckTable] dictTable" +
				"	  on dictTable.chkTblID = chkTbls.chkTblID" +
				"	  left join [CoresQA].[dbo].[QAChkTblReg] chkTbl" +
				"	  on chkTbl.chkTblCode = dictTable.chkTblCode  and chkTbl.chkTblName = dictTable.chkTblName" +
				"	  and chkTbl.chkIndexID = chkIndex.chkIndexID" +
				"	  where chkIndex.chkIndexID is  null ";
	
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
													.setParameter("chkItemId", chkItemId)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
		
	}
	
	
}
