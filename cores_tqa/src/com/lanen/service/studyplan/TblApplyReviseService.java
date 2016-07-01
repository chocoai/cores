package com.lanen.service.studyplan;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblApplyRevise;

public interface TblApplyReviseService extends BaseDao<TblApplyRevise>{
	
	TblApplyRevise getByStudyNo(String studyNo);
	
	/**
	 * 备份一份版本
	 * @param studyNo
	 */
	void backUpAllByStudyNo(String studyNo);


}
