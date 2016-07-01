package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseLongDao;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;

public interface TblClinicalTestReqIndex2Service extends BaseLongDao<TblClinicalTestReqIndex2>{

	/**
	 * 查询临检申请单2-动物编号  列表
	 * @param studyNoPara
	 * @param reqNoPara
	 * @return
	 */
	List<TblClinicalTestReqIndex2> findByStudyNoAndReqNO(String studyNoPara,
			int reqNoPara);

}
