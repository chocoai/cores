package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.service.qa.QAApprovalOpinionService;
@Service
public class QAApprovalOpinionServiceImpl extends BaseDaoImpl<QAApprovalOpinion> implements QAApprovalOpinionService {

	@SuppressWarnings("unchecked")
	public QAApprovalOpinion getByTypeAndReportAndOperator(
			Integer approvalType, String objectCode, Integer operatorType) {
		String sql = "from QAApprovalOpinion where approvalType=:approvalType and objectCode=:objectCode and operatorType=:operatorType";
		List<QAApprovalOpinion> list = getSession().createQuery(sql)
													.setInteger("approvalType", approvalType)
													.setString("objectCode", objectCode)
													.setInteger("operatorType", operatorType)
													.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public QAApprovalOpinion getByTypeAndReportAndResultAndOperator(
			Integer approvalType, String objectCode,Integer resultFlag, Integer operatorType) {
		String sql = "from QAApprovalOpinion where approvalType=:approvalType and objectCode=:objectCode " +
				" and approvalResultFlag=:resultFlag and operatorType=:operatorType";
		List<QAApprovalOpinion> list = getSession().createQuery(sql)
													.setInteger("approvalType", approvalType)
													.setString("objectCode", objectCode)
													.setInteger("resultFlag", resultFlag)
													.setInteger("operatorType", operatorType)
													.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		
		
		return null;
	}
}
