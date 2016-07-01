package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.morph.reflect.reflectors.MapReflector;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkPlanHis;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.util.DateUtil;

@Service
public class QAChkPlanServiceImpl extends BaseDaoImpl<QAChkPlan> implements QAChkPlanService {
	@Resource
	private QAChkIndexService qAChkIndexService;
	
	
	@SuppressWarnings("unchecked")
	public List<QAChkPlan> getByStudyNo(String studyNo) {
		List<QAChkPlan> list = getSession().createQuery("from QAChkPlan where qastudyChkIndex.studyNo=:studyNo")
							.setString("studyNo",studyNo)
							.list();

		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Date> getPlanDateByStudyNoAndUser(String studyNo,String realName) {
		String sql = "SELECT distinct [planChkTime] FROM [CoresQA].[dbo].[QAChkPlan] where studyNo=:studyNo and ([chkFinishedFlag] is null or ([chkFinishedFlag]!=1 and [chkFinishedFlag]!=-1))";
		if(realName!=null&&!"".equals(realName))
		{
			sql+=" and ([chkOperator]=:realName or [planChkOperator]=:realName)";	
		}
		sql+=" order by planChkTime asc";
		Query query = getSession().createSQLQuery(sql)
								.setParameter("studyNo",studyNo);
		if(realName!=null&&!"".equals(realName))
		{
			query.setParameter("realName", realName);
		}
		List<Date> list = query.list();

		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkPlan> getItemsByStudyNoAndPlanDateAndUser(String studyNo,Date planDate,String realName)
	{
		String hql = "FROM QAChkPlan where qastudyChkIndex.studyNo=:studyNo and planChkTime=:planDate and (chkFinishedFlag is null or (chkFinishedFlag!=1 and chkFinishedFlag!=-1))";
		if(realName!=null&&!"".equals(realName))
		{
			hql+=" and (chkOperator=:realName or (chkOperator is null and planChkOperator=:realName))";	
		}else {
			//qa的情况下要是别人没有开始检查的检查计划
			hql+=" and (chkOperator is null or chkOperator=qastudyChkIndex.inspector)";//还没开始检查，或者是自己检查的	
		}
		Query query = getSession().createQuery(hql)
										.setString("studyNo",studyNo)
										.setDate("planDate",planDate);
		if(realName!=null&&!"".equals(realName))
		{
			query.setString("realName", realName);
		}
										
		List<QAChkPlan> list = query.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getByStudyNoAndCondition(String studyNoParam,
			Date start, Date end, Integer status, Integer catalog,String searchString) {
		if(searchString==null)
			searchString="";
		
		/*String hql = "FROM QAChkPlan where 1=1 ";
		if(studyNoParam!=null)
		{
			hql +=" and qastudyChkIndex.studyNo=:studyNo ";
		}
		if(start!=null)
		{
			hql +=" and planChkTime>=:start ";
		}
		if(end!=null)
		{
			hql +=" and planChkTime<=:end ";
		}
		if(catalog!=null&&catalog!=0)
		{
			<option value="0">全部</option>
			<option value="1">基于研究的检查</option>
			<option value="2">基于过程的检查</option>
			<option value="3">基于设施的检查</option>
			hql +=" and chkPlanType=:catalog ";
		}
		if(status!=null&&status!=0)//0代表全部 1未实施，2已实施
		{
			if(status==1)
			{
				hql +=" and chkIndex is  null ";
			}else {
				hql +=" and chkIndex is not null ";
			}
		}
		hql+=" and (chkItemName like :searchString or taskName like :searchString or qastudyChkIndex.studyNo like :searchString)" +
				"  order by planChkTime desc,qastudyChkIndex.studyNo desc  ";*/
		String sql = "select [chkPlanID],[chkPlanType] ,splan.[studyNo] ,[ScheduleChkItemID] ,[chkItemID] ,[chkItemName] ,[taskNameID] ,[taskName]" +
				"      ,[scheduleID] ,[ScheduleName] ,[createTime] ,[planChkTime] ,[planChkArea] ,[chkFinishedFlag] ,[planChkOperator]" +
				"      ,[chkOperator] ,[chkTime] ,[chkPlanVersion] ,[scheduleTime] ,[chkIndexID],[number],[SOPFlag],[tempChkOperatorFlag]" +
				"      ,[tempChkOperator],[tempChkOperatorApplyTime],[tempChkOperatorApprovalTime]" +
				"	   ,item.studyName,studyChkIndex.inspector,studyChkIndex.chkPlanState,studyChkIndex.chkPlanFinishFlag,studyChkIndex.reportState" +
				"	from(" +
				"		select [chkPlanID],[chkPlanType] ,[studyNo] ,[ScheduleChkItemID] ,[chkItemID] ,[chkItemName] ,[taskNameID] ,[taskName]" +
				"      ,[scheduleID] ,[ScheduleName] ,[createTime] ,[planChkTime] ,[planChkArea] ,[chkFinishedFlag] ,[planChkOperator]" +
				"      ,[chkOperator] ,[chkTime] ,[chkPlanVersion] ,[scheduleTime] ,[chkIndexID],[number],[SOPFlag],[tempChkOperatorFlag]" +
				"      ,[tempChkOperator],[tempChkOperatorApplyTime],[tempChkOperatorApprovalTime]  from [CoresQA].[dbo].[QAChkPlan]  where 1=1";
		                    
		if(studyNoParam!=null)
		{
			sql +=" and studyNo=:studyNo ";
		}
		if(start!=null)
		{
			sql +=" and planChkTime>=:start ";
		}
		if(end!=null)
		{
			sql +=" and planChkTime<=:end ";
		}
		if(catalog!=null&&catalog!=0)
		{
			
			sql +=" and chkPlanType=:catalog ";
		}
		if(status!=null&&status!=0)//0代表全部 1未实施，2已实施
		{
			if(status==1)
			{
				sql +=" and chkIndexID is  null ";
			}else {
				sql +=" and chkIndexID is not null ";
			}
		}
	
		sql+=" and (chkItemName like :searchString or taskName like :searchString or studyNo like :searchString)" +
				") as splan" +
				" left join [CoresContract].[dbo].[tblStudyItem] item" +
				" on item.studyNo=splan.studyNo" +
				" left join [CoresQA].[dbo].[QAStudyChkIndex] studyChkIndex" +
				" on studyChkIndex.studyNo = splan.studyNo" +
				" order by planChkTime desc,splan.studyNo desc";
		
		Query query = getSession().createSQLQuery(sql);
		if(studyNoParam!=null)//null是这页面的请求，""是子页面的请求
		{
			query.setString("studyNo", studyNoParam);
		}
		if(start!=null)
		{
			query.setString("start", DateUtil.dateToString(start,"yyyy-MM-dd"));
		}
		if(end!=null)
		{
			query.setString("end", DateUtil.dateToString(end,"yyyy-MM-dd"));
		}
		if(catalog!=null&&catalog!=0)
		{
			query.setInteger("catalog", catalog);
		}
		query.setString("searchString", "%"+searchString+"%");
		
		List<Map<String,Object>> list = query.setResultTransformer(new MapResultTransformer()).list();	
		
	
		return list;
	
	}
	@SuppressWarnings("unchecked")
	public Integer getMaxVersionByStudyNo(String studyNo) {
		List<QAChkPlan> list = getSession().createQuery(" FROM QAChkPlan where qastudyChkIndex.studyNo=:studyNo order by chkPlanVersion desc")
											.setString("studyNo",studyNo)
											.list();
	
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getChkPlanVersion();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public QAChkPlan getByChkIndex(String chkIndexId) {
		List<QAChkPlan> list = getSession().createQuery(" FROM QAChkPlan where chkIndex.chkIndexId=:chkIndexId")
											.setString("chkIndexId",chkIndexId)
											.list();
		if(list!=null&&list.size()>0)
		{
			return  list.get(0);
			
		}
		return null;
	}

	
	
	public Integer getVersionByStudyNo(String studyNoParam) {
		String sql = "SELECT distinct [chkPlanVersion] FROM [CoresQA].[dbo].[QAChkPlan] where studyNo=:studyNo";
		
		Query query = getSession().createSQLQuery(sql)
								.setParameter("studyNo",studyNoParam);
		
		List<Integer> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		else {
			return 0;
		}
	}

	public QAChkIndexService getqAChkIndexService() {
		return qAChkIndexService;
	}
	public void setqAChkIndexService(QAChkIndexService qAChkIndexService) {
		this.qAChkIndexService = qAChkIndexService;
	}
}
