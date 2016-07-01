package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictAnimalStrain;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblWeighInd;

public interface TblWeightIndService extends BaseDao<TblWeighInd>  {
	
	/**
	 * 保存多条动物信息
	 * @param animalList
	 */
	void saveAllAnimalWeighInds(List<TblWeighInd> animalList);
	
   /**
    * 查询动物
    * @param id
    * @return
    */
	List<TblWeighInd> getByStudyNo(String studyNoPara);
	
	List<TblWeighInd> getByWeighSn(String studyNoPara);
	
	List<TblWeighInd> gettblWeighInd(String studyNo);
	
	List<TbLWeighData> getTbLWeighData(String StudyNo , int AppSn);
	
	List<TblWeighInd> getnewtblTblWeighIndInd(String studyNo, int AppSn);

}
