package com.lanen.service.qa;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkPlanChangeIndex;

public interface QAChkPlanChangeIndexService extends BaseDao<QAChkPlanChangeIndex>{
	/**
	 * 获取本专题的最大版本号
	 * @param studyNoParam
	 * @return
	 */
	int getMaxSnByStudyNo(String studyNoParam);
	/**
	 * 获取本专题是否存在一个还没有批复的申请
	 * @param studyNoParam
	 * @return
	 */
	boolean getByStudyNoAndStatue(String studyNoParam);
	/**
	 * 专题是否存在没提交的变更申请
	 * @param studyNoParam
	 * @return
	 */
	QAChkPlanChangeIndex getByStudyNoAndNoCommit(String studyNoParam);
	/**
	 * 获取需要审批的检查计划申请
	 * @param studyNo
	 * @return
	 */
	QAChkPlanChangeIndex getByStudyNoAndNoApproval(String studyNo);
	/**
	 * 获取专题的对应版本的还没有批复的申请修改信息
	 * @param studyNoParam
	 * @param sn
	 * @return
	 */
	public QAChkPlanChangeIndex getByStudyNoSnStatus(String studyNoParam,Integer sn);
}
