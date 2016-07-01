package com.lanen.service.studyplan;


import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblStudyPlan;

public interface TblStudyPlanService extends BaseDao<TblStudyPlan> {


	
	/**根据课题编号获得专题计划
	 * @param studyNo 
	 * @return
	 */
	TblStudyPlan getByStudyNo(String studyNo);
	/**
	 * 
	 * @param studyNo
	 * @return
	 */
	String getSDByStudyNo(String studyNo) ;
	
	
}
