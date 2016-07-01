package com.lanen.service.qa.impl;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.util.DateUtil;
@Service
public class QAStudyChkIndexServiceImpl extends BaseDaoImpl<QAStudyChkIndex> implements	QAStudyChkIndexService {
	
	@SuppressWarnings("unchecked")
	public QAStudyChkIndex getByStudyNo(String studyNo)
	{
		Query query = getSession().createQuery("from QAStudyChkIndex where studyNo=:studyNo")
									.setString("studyNo", studyNo);
		List<QAStudyChkIndex> list = query.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
		
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> getQAStudyChkIndexByStudyNo(String studyNo)
	{
		Query query = getSession().createSQLQuery(" select studyNo,inspector from [CoresQA].[dbo].[QAStudyChkIndex] where studyNo=:studyNo")
									.setString("studyNo", studyNo)
									.setResultTransformer(new MapResultTransformer());
		List<Map<String,Object>> list = query.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public void updateScheduleChangedFlag(String studyNo,Integer scheduleChangedFlag)
	{
		Query query = getSession().createSQLQuery(" update [CoresQA].[dbo].[QAStudyChkIndex] " +
				"set scheduleChangedFlag=:scheduleChangedFlag,scheduleState=1,scheduleSubmitTime=GETDATE() " +
				"where studyNo=:studyNo")
									.setString("studyNo", studyNo)
									.setInteger("scheduleChangedFlag", scheduleChangedFlag);
		query.executeUpdate();

	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getStudyNoByDateAndCondition(Integer studyState,Date start, Date end,
			String studyNo,String realName) {
		
		String sql =" select distinct chkStudyNo.studyNo,item.id itemId,case when chkStudyNo.inspector is not null then chkStudyNo.inspector else item.qa end as qa," +
				" case when chkStudyNo.sd is not null then chkStudyNo.sd else item.sd end as sd,dictType.studyTypeCode,dictType.studyName" +
		" from ( SELECT chkIndex.[studyNo],studyPlanState,sd,inspector,reportState,chkPlanState   FROM [CoresQA].[dbo].[QAStudyChkIndex] as chkIndex where 1=1 ";
		if(realName!=null&&!"".equals(realName))
		{
			//QA
			/*sql+=" and ([inspector]=:realName or :realName in (" +
					" SELECT distinct [planChkOperator] FROM [CoresQA].[dbo].[QAChkPlan] chkPlan" +
					"  left join [CoresQA].[dbo].[QAChkIndex] chkIndex on chkIndex.chkIndexID=chkPlan.chkIndexID " +
					"  left join [CoresQA].[dbo].[QAChkReport] report on report.chkReportCode=chkIndex.chkReportCode " +
					"  where chkPlan.studyNo=chkIndex.[studyNo]  and (report.rptState is null or report.rptState!=9) " +//报告还没完成检查计划的计划检查人员
					") )";
					*/
			sql+=" and ([inspector]=:realName or :realName in " +
				 " ( SELECT distinct [planChkOperator] " +
				 "	from (" +
				 " 	  (select [planChkOperator],chkIndexID FROM [CoresQA].[dbo].[QAChkPlan] chkPlan   where chkPlan.studyNo=chkIndex.[studyNo]  ) as oneChkPlan " +
				 "		left join [CoresQA].[dbo].[QAChkIndex] chkIndex  on  chkIndex.chkIndexID=oneChkPlan.chkIndexID   " +
				 "		left join [CoresQA].[dbo].[QAChkReport] report on report.chkReportCode=chkIndex.chkReportCode  " + 
				 "		) " +
				 "	where (report.rptState is null or report.rptState!=9) " +
				 " )"+
				 ") ";
			
		}
		if(studyState==-1)//选择是已完成专题
		{
			sql+=	"  and (chkPlanFinishFlag=1 or reportState=1)" +
			" ) as chkStudyNo" +
			"  left join" +
			"  [CoresStudy].[dbo].[tblStudyPlan] as studyPlan on studyPlan.studyNo=chkStudyNo.studyNo" ;

		}else {
			/*20：全部未完成
			 * <option value="0">待任命QA</option>
  		 	<option value="1">未制定检查计划</option>
  		 	<option value="2">检查计划待审批</option>
  		 	<option value="3">申请计划变更待审批</option>
  		 	<option value="4">日程变更需修改检查计划</option>
  		 	<option value="5">专题方案待接收</option>
  		 	<option value="6">专题待检查</option>
  		 	<option value="7">报告待审批</option>
  		 	<option value="8">报告待处理回复或延迟</option>
  		 	<option value="9">报告待再检查</option>
  		 	<option value="10">专题报告待接收</option>*/
			sql+="  and studyNo like :studyNo" ;
			sql+=	"  and (chkPlanFinishFlag is null or chkPlanFinishFlag!=1) and (reportState is null or reportState!=1)"  ;
			if(studyState==20)
			{
				//20是全部未完成，不需要加
				sql+=") as chkStudyNo" ;
			}else{
				if(studyState==3 || studyState==5 || studyState==6 ||studyState==7||studyState==8||studyState==9||studyState==10){
					if(studyState==3)
					{
						sql+=") as chkStudyNo" +
							" join" +
							" [CoresQA].[dbo].[QAChkPlanChangeIndex] as change " +
							" on change.studyNo=chkStudyNo.studyNo and [changeState]=1";
					}else if(studyState==5){
						sql+=") as chkStudyNo" +
							"	join " +
							" [CoresQA].[dbo].[TblStudyFileIndex] as studyFileIndex " +
							" on studyFileIndex.studyNo=chkStudyNo.studyNo " +
							" and fileType=1 and fileState=1 and confirmer is null";
					}else if(studyState==10){
						sql+=") as chkStudyNo" +
							"	join " +
							" [CoresQA].[dbo].[TblStudyFileIndex] as studyFileIndex " +
							" on studyFileIndex.studyNo=chkStudyNo.studyNo " +
							" and fileType=2 and fileState=1 and confirmer is null";
					}else if(studyState==6){
						
						sql+="  ) as chkStudyNo" + //检查计划是完成状态的
								"	 left join " +
								"    (select [studyNo] from [CoresQA].[dbo].[QAChkPlan]" +
								"	 where (chkFinishedFlag is null or chkFinishedFlag=0) " +
								" ) as chkplan " +
								" on (chkplan.studyNo=chkStudyNo.studyNo and chkStudyNo.chkPlanState=2)" +
								" left join " +
								" (select studyNo from [CoresQA].[dbo].[TblStudyFileIndex]  where fileState=1 and confirmer is not null ) as fileIndex" +
								" on fileIndex.studyNo=chkStudyNo.studyNo ";
						
					}else if(studyState==7){
						sql+=") as chkStudyNo" +
							" join " +
							" (select [studyNo],chkReportCode from [CoresQA].[dbo].[QAChkIndex] " +
							"	where chkReportCode is not null" +
							" )as chkIndex on chkIndex.studyNo=chkStudyNo.studyNo" +
							" join" +
							" (select chkReportCode from [CoresQA].[dbo].[QAChkReport]" +
							"   where rptState=1" +
							" ) as chkreport" +
							" on chkreport.chkReportCode=chkIndex.chkReportCode ";
						
					}else if(studyState==8){
						//回复中，FM批复过回复，并且qa没有接收
						sql+=") as chkStudyNo" +
						" join " +
						" (select [studyNo],chkReportCode from [CoresQA].[dbo].[QAChkIndex] " +
						"	where chkReportCode is not null" +
						" )as chkIndex on chkIndex.studyNo=chkStudyNo.studyNo" +
						" join" +
						" (select chkReportCode from [CoresQA].[dbo].[QAChkReport]" +
						"   where rptState=3  and replyFMApprovalTime is not null and replyInspectorReceiveTime is null " +
						" ) as chkreport" +
						" on chkreport.chkReportCode=chkIndex.chkReportCode ";
					}else if(studyState==9){
						//needReChk==1回复的再检查，needDelay=3 and delayState=0需要延迟整改但是还没有完成
						
						sql+=") as chkStudyNo" +
						" join " +
						" (select [studyNo],chkReportCode from [CoresQA].[dbo].[QAChkIndex] " +
						"	where chkReportCode is not null" +
						" )as chkIndex on chkIndex.studyNo=chkStudyNo.studyNo" +
						" join" +
						" (select chkReportCode from [CoresQA].[dbo].[QAChkReport]" +
						"  where (rptState=3 or rptState=4 or rptState=5) " +
						"			and (needReChk=1 or (needDelay=3 and delayState=0)) " +
						" ) as chkreport" +
						" on chkreport.chkReportCode=chkIndex.chkReportCode ";
						
					}
				}else {
					if(studyState==0){
						sql+=" and inspector is null ";
					}else if(studyState==1){
						sql+=" and (chkPlanState is null or chkPlanState=0 or chkPlanState=-1) ";
					}else if(studyState==2){
						sql+=" and chkPlanState=1 ";
					}else if(studyState==4){
						sql+=" and scheduleChangedFlag=1 ";
					}
					sql+=" ) as chkStudyNo" ;
				}
			}
		}
		sql+=" left join [CoresContract].[dbo].[tblStudyItem] item" +
			"	on chkStudyNo.studyNo=item.studyNo" +
			"	left join [CoresSystemSet].[dbo].[dictStudyType] dictType " +
			"	on dictType.studyTypeCode = item.studyTypeCode ";
		if(studyState==6){
			sql+=" where fileIndex.studyNo is not null or chkplan.studyNo is not null";
		}
		if(studyState==-1){
			sql+="  where " +//(studyPlanState is null or studyPlanState=0) or专题已完成，方案没有在QA中管理
				" (studyPlan.studyNo is null) or (studyStartDate between :start and :end) ";
		}
		sql+=" order by chkStudyNo.studyNo desc ";
			
		Query query = getSession().createSQLQuery( sql);
		if(realName!=null&&!"".equals(realName)){
			query.setParameter("realName", realName);
		}
		
		if(studyState==-1){
			query.setParameter("start", DateUtil.dateToString(start,"yyyy-MM-dd"));
			query.setParameter("end", DateUtil.dateToString(end,"yyyy-MM-dd"));
		}else {
			query.setParameter("studyNo", "%"+studyNo+"%");
			
		}
		List<Map<String,Object>> list = query
										.setResultTransformer(new MapResultTransformer())
										.list();
		
		
		if(list!=null&&list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllStatesByCondition(String studyNoParam,User user,Integer status,Integer catalog,String searchString)
	{
		String sql = " "+
		" select chkStudyNo.studyNo,inspector,chkPlanState,[changeState],chkStudyNo.scheduleChangedFlag,chkStudyNo.confirmer,fileType, "+
		"	case when chkStudyNo.inspector is null then '待任命QA' else  "+
		"		case when chkPlanState is null or chkPlanState!=2 then '检查计划未制定完成' else "+//--检查计划未制定完成包括待审批,action中分开处理
		"			case when [changeState]=1 then '检查计划变更待审批' else "+
		"				case when chkStudyNo.scheduleChangedFlag=1 then '日程变更需修改检查计划' else "+//--处理是制定检查计划
		"					'' "+
		"				end "+
		"			end "+
		"		end "+
		"	end as scheduleChangeState, "+
		"	case when  fileState=1  then  "+//--提交状态的方案或报告
		"		case when fileType=1 then  "+//--专题方案
		"				case when  chkStudyNo.confirmer is null	then '专题方案待接收' else '专题方案待检查' end  "+
		"			else case when fileType=2 then  "+//--专题报告 
		"					case when  chkStudyNo.confirmer is null	then '专题报告待接收' else '专题报告待检查' end  "+
		"				 end "+
		"		end "+
		"	else "+
		"		'' "+
		"	end as studyFileState,item.studyName "+
		"	 "+
		"	from ( SELECT chkIndex.[studyNo],inspector,studyPlanState,chkPlanState,scheduleChangedFlag,fileState,fileType,confirmer,[changeState]  FROM [CoresQA].[dbo].[QAStudyChkIndex] as chkIndex  "+
		"			left join [CoresQA].[dbo].[TblStudyFileIndex] studyFileIndex on studyFileIndex.studyNo=chkIndex.studyNo  and fileState=1 "+// --可能是两条，一条方案一条报告 还没有完成的方案或报告
		"			left join [CoresQA].[dbo].[QAChkPlanChangeIndex] as change on change.studyNo=chkIndex.studyNo and [changeState]=1  "+//--没有处理完成的检查计划变更
		"		  where 1=1  "+
		"	 "+
		"		   "+	
		"		 and (chkPlanFinishFlag is null or chkPlanFinishFlag!=1) and (reportState is null or reportState!=1) "+//--未完成专题
		" "+
		"	) as chkStudyNo " +
		"	left join [CoresContract].[dbo].[tblStudyItem] item " +
		"	on item.studyNo=chkStudyNo.studyNo"+ 
		" where  1=1";
		if(user!=null&&!"".equals(user))
		{
			sql+=" and  ([inspector]=:inspector )";
		}
		if(studyNoParam!=null&&!"".equals(studyNoParam))
		{
			sql+="   and  (chkStudyNo.[studyNo]=:studyNo ) ";
		}
		if(searchString!=null&&!"".equals(searchString))
		{
			sql+=" and chkStudyNo.studyNo like :searchString ";
		}
		
		if((status!=null&&status!=0&&status!=1) //0代表全部 1未实施，2已实施
				||(catalog!=null&&catalog!=0&&catalog!=1))//如果不是全部或者基于研究的检查就直接false
		{
			sql+="1=0";//必须是未实施和基于研究的检查
		}
		
		sql+="	order by studyNo desc	";
		Query query = getSession().createSQLQuery(sql);
		
		if(user!=null&&!"".equals(user))
		{
			query.setString("inspector",user.getRealName());
		}
		if(studyNoParam!=null&&!"".equals(studyNoParam))
		{
			query.setString("studyNo",studyNoParam);
		}
		if(searchString!=null&&!"".equals(searchString))
		{
			query.setString("searchString",searchString);
		}
		
		
		List<Map<String, Object>> mapList = query.setResultTransformer(new MapResultTransformer())
												.list();
		
		return mapList;
	}
	
	public boolean isExistByStudyNo(String studyNoPara) {
		String sql = "SELECT studyNo  FROM [CoresQA].[dbo].[QAStudyChkIndex]" +
				"  where studyNo like :studyNo ";
		String studyNo = (String) getSession().createSQLQuery(sql)
												.setParameter("studyNo", studyNoPara)
												.uniqueResult();
		
		if(studyNo!=null&&!"".equals(studyNo))
			return true;
		return false;
	}
	
}
