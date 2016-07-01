package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.util.DateUtil;

@Service
public class QAChkReportRecordServiceImpl extends BaseDaoImpl<QAChkReportRecord> implements QAChkReportRecordService {

	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getByReportCode(String chkReportCode) {
		String hql = "from QAChkReportRecord where qachkReport.chkReportCode=:chkReportCode";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setString("chkReportCode", chkReportCode)
													.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getByReportCodes(String[] chkReportCode) {
		String hql = "from QAChkReportRecord where qachkReport.chkReportCode in (:chkReportCode) " +
				" order by qachkReport.chkReportCode desc ";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setParameterList("chkReportCode", chkReportCode)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getReChkByReportCode(String chkReportCode) {
		String hql = "from QAChkReportRecord where qachkReport.chkReportCode=:chkReportCode " +
				" and (chkResultFlag=-1 or needDelay=1)";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setString("chkReportCode", chkReportCode)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getReChkByReportCodes(String[] chkReportCode) {
		String hql = "from QAChkReportRecord where qachkReport.chkReportCode in (:chkReportCode) " +
				" and (chkResultFlag=-1 or needDelay=1)";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setParameterList("chkReportCode", chkReportCode)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public QAChkReportRecord getByChkRecordId(String chkRecordId) {
		String hql = "from QAChkReportRecord where chkRecordId=:chkRecordId ";
		List<QAChkReportRecord> list = getSession().createQuery(hql)
													.setString("chkRecordId", chkRecordId)
													.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<QAChkReportRecord> getByCondition(String studyNoParam,Date start, Date end, Integer status, 
			Integer catalog,String searchString) {
		if(searchString==null)
			searchString="";
		
		String hql = "FROM QAChkReportRecord where (qachkReport.rptState=3 or qachkReport.rptState=5 or qachkReport.rptState=4) " +
				" and qachkReport.needDelay=3 and (qachkReport.delayState is null or qachkReport.delayState=0) ";
		if(studyNoParam!=null)
		{
			hql +=" and studyNo=:studyNo ";
		}
		if(start!=null)
		{
			hql +=" and delayPlanFinishDate>=:start ";
		}
		if(end!=null)
		{
			hql +=" and delayPlanFinishDate<=:end ";
		}
		if(catalog!=null&&catalog!=0&&catalog!=1)
		{
			/*<option value="0">全部</option>
			<option value="1">基于研究的检查</option>
			<option value="2">基于过程的检查</option>
			<option value="3">基于设施的检查</option>*/
			//hql +=" and chkPlanType=:catalog ";
			hql+=" and 1=0 ";//如果不是全部或者基于研究的检查就直接false
		}
		if(status!=null&&status!=0)//0代表全部 1未实施，2已实施
		{
			if(status==1)
			{
				hql += " and delayFinishTime is null ";
			}else {
				hql+=" and 1=0 ";//如果不是全部或者基于研究的检查就直接false
				
			}
		}else {
			hql += " and delayFinishTime is null ";
		}
		
		hql+=" and ( studyNo like :searchString ) " ;
		hql+= "  order by delayPlanFinishDate desc, studyNo desc  ";
		
		Query query = getSession().createQuery(hql);
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
		
		List<QAChkReportRecord> list = query.list();	
		
		

		return list;
	}

}
