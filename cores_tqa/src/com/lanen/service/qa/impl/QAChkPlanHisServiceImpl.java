package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAChkPlanHis;
import com.lanen.service.qa.QAChkPlanHisService;
import com.lanen.util.DateUtil;
@Service
public class QAChkPlanHisServiceImpl extends BaseDaoImpl<QAChkPlanHis> implements QAChkPlanHisService{

	@SuppressWarnings("unchecked")
	public Integer getMaxVersionByStudyNo(String studyNo) {
		List<QAChkPlanHis> list = getSession().createQuery(" FROM QAChkPlanHis where studyNo=:studyNo order by chkPlanVersion desc")
												.setString("studyNo",studyNo)
												.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0).getChkPlanVersion();
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	public List<Integer> getVersionsByStudyNo(String studyNo) {
		List<Integer> list = getSession().createSQLQuery("SELECT distinct [chkPlanVersion]  FROM [CoresQA].[dbo].[QAChkPlanHis]" +
											" where studyNo=:studyNo order by [chkPlanVersion] desc")
											.setParameter("studyNo",studyNo)
											.list();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkPlanHis> getByStudyNoAndVersion(String studyNo,Integer versionInt) {
		List<QAChkPlanHis> list = getSession().createQuery("FROM QAChkPlanHis where studyNo=:studyNo and chkPlanVersion=:chkPlanVersion" +
												" order by chkPlanVersion desc")
											.setString("studyNo",studyNo)
											.setInteger("chkPlanVersion", versionInt)
											.list();

		return list;
	}
	@SuppressWarnings("unchecked")
	public List<QAChkPlanHis> getByStudyNoAndCondition(String studyNoParam,
			Date start, Date end, Integer status, Integer catalog,String searchString) {
		if(searchString==null)
			searchString="";
		
		String hql = "FROM QAChkPlanHis where chkPlanVersion=-1 ";
		if(studyNoParam!=null)
		{
			hql +=" and studyNo=:studyNo ";
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
			/*<option value="0">全部</option>
			<option value="1">基于研究的检查</option>
			<option value="2">基于过程的检查</option>
			<option value="3">基于设施的检查</option>*/
			hql +=" and chkPlanType=:catalog ";
		}
		if(status!=null&&status!=0)//0代表全部 1未实施，2已实施
		{
			if(status==1)
			{
				//hql +=" and chkIndex is  null ";
			}else {
				hql +=" and 1=-1 ";//已实施就为空
			}
		}
		hql+=" and (chkItemName like :searchString or taskName like :searchString or studyNo like :searchString)" +
				"  order by planChkTime desc,studyNo desc  ";
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
		
		List<QAChkPlanHis> list = query.list();	
		
		

		return list;
	
	}
	
	
}
