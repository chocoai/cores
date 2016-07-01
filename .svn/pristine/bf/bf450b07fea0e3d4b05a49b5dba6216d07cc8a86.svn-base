package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictAnimalStrain;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblWeighInd;

@Service
public class TblWeightIndServiceImpl extends BaseDaoImpl<TblWeighInd>  implements TblWeightIndService {


	public void saveAllAnimalWeighInds(List<TblWeighInd> animalList) {
		for(TblWeighInd obj : animalList){
			obj.setId(getKey("TblWeighInd"));
			save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TblWeighInd> getByStudyNo(String studyNoPara) {
		return getSession().createQuery("FROM TblWeighInd t WHERE t.StudyNo = ? and t.WeighSn=( " +
				" select max(n.WeighSn) FROM TblWeighInd n WHERE n.StudyNo = ? ) ")
		.setParameter(0, studyNoPara).setParameter(1, studyNoPara)//
		.list();
	}

	public List<TblWeighInd> gettblWeighInd(String studyNo) {
		return getSession().createQuery("FROM TblWeighInd t WHERE t.StudyNo = ? ").
		setParameter(0, studyNo).list();
	}

	public List<TbLWeighData> getTbLWeighData(String StudyNo, int AppSn) {
		return getSession().createQuery("FROM TbLWeighData t WHERE t.StudyNo = ? and t.WeighSn = ? Order by t.AniCode ").
		setParameter(0, StudyNo).setParameter(1, AppSn).list();
	}
	@SuppressWarnings("unchecked")
	public List<TblWeighInd> getnewtblTblWeighIndInd(String studyNo, int AppSn) {
		return getSession().createQuery("FROM TblWeighInd t WHERE t.StudyNo = ? and t.WeighSn = ? ").
		setParameter(0, studyNo).setParameter(1, AppSn).list();
	}

	public List<TblWeighInd> getByWeighSn(String studyNoPara) {
			return getSession().createQuery("FROM TblWeighInd t WHERE t.StudyNo = ? and t.WeighSn=( " +
					" (select max(n.WeighSn) FROM TblWeighInd n WHERE n.StudyNo = ? ) - 1) ")
			.setParameter(0, studyNoPara).setParameter(1, studyNoPara)//
			.list();
	}


}
