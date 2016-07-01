package com.lanen.service.qa;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAApprovalOpinion;

public interface QAApprovalOpinionService extends BaseDao<QAApprovalOpinion> {
	/**
	 * //approvalType 1：报告；2：回复；3：延迟整改；4：检查计划
					//objectCode 专题/报告编号
					//operatorType 1：FM；2：QAM；3：QA检查员
	 * @return
	 */
	QAApprovalOpinion getByTypeAndReportAndOperator(Integer approvalType,String objectCode,Integer operatorType);
	/**
	 * //approvalType 1：报告；2：回复；3：延迟整改；4：检查计划
					//objectCode 专题/报告编号
					//operatorType 1：FM；2：QAM；3：QA检查员
	 * resultFlag 1过，-1不过
	 * @return
	 */
	QAApprovalOpinion getByTypeAndReportAndResultAndOperator(Integer approvalType,String objectCode,Integer resultFlag,Integer operatorType);

}
