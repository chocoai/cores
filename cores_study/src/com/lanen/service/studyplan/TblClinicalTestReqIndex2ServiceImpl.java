package com.lanen.service.studyplan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;
import com.lanen.model.studyplan.TblStudyPlan;
@Service
public class TblClinicalTestReqIndex2ServiceImpl extends BaseLongDaoImpl<TblClinicalTestReqIndex2> implements TblClinicalTestReqIndex2Service {

	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestReqIndex2> findByStudyNoAndReqNO(
			String studyNoPara, int reqNoPara) {
		TblStudyPlan  tblStudyPlan =tblStudyPlanService.getById(studyNoPara);
		List<TblClinicalTestReqIndex2> list =getSession().createQuery("FROM TblClinicalTestReqIndex2 o 	WHERE o.tblStudyPlan = ? and o.reqNo= ? ORDER BY " +
				"case when o.animalCode is null or o.animalCode ='' then '0000' else o.animalCode end , o.aniSerialNum ")
		.setParameter(0, tblStudyPlan)
		.setParameter(1, reqNoPara)
		.list();
		return list;
	}

}
