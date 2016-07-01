package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.service.qa.QAApprovalOpinionService;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
@Service
public class QAApprovalOpinionServiceImpl extends BaseDaoImpl<QAApprovalOpinion> implements QAApprovalOpinionService{

	@SuppressWarnings("unchecked" +
			"")
	public QAApprovalOpinion getByReportAndType(String chkReportCode,int approvalType,int operatorType) {
		String sql = "SELECT [approvalRecordID] ,[approvalType],[objectCode],[objectVersion],[operatorType],[approvalResultFlag],[approvalResult] ,[approvalOpinion],[approvalTime]  ,[approvalName]" +
				"  FROM [CoresQA].[dbo].[QAApprovalOpinion]" +
				"  where objectCode=:chkReportCode and approvalType=:approvalType and operatorType=:operatorType order by approvalTime desc";//审批人FM，审批的是回复
		List<Map<String, Object>> opinions = getSession().createSQLQuery(sql)
												.setParameter("chkReportCode", chkReportCode)
												.setParameter("approvalType", approvalType)
												.setParameter("operatorType", operatorType)
												.setResultTransformer(new MapResultTransformer())
												.list();
		if(opinions!=null&&opinions.size()>0)
		{
			Map<String,Object> map = opinions.get(0);
			QAApprovalOpinion opinion = new QAApprovalOpinion();
			opinion.setApprovalName((String)map.get("approvalName"));
			opinion.setApprovalOpinion((String)map.get("approvalOpinion"));
			opinion.setApprovalRecordId((String) map.get("approvalRecordID"));
			opinion.setApprovalResult((String)map.get("approvalResult"));
			opinion.setApprovalResultFlag((Integer)map.get("approvalResultFlag"));
			opinion.setApprovalTime((Date)map.get("approvalTime"));
			opinion.setApprovalType((Integer)map.get("approvalType"));
			opinion.setObjectCode((String)map.get("objectCode"));
			opinion.setObjectVersion((Integer)map.get("objectVersion"));
			opinion.setOperatorType((Integer)map.get("operatorType"));
			
			return opinion;
		
		}else {
			return null;
		}
	}

}
