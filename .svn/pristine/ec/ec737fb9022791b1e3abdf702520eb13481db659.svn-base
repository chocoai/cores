package com.lanen.service.studyplan;

import java.util.List;

import org.hibernate.Query;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblWeighInd;

public interface TbLWeightDataService extends BaseDao<TbLWeighData>{
	
	/**
	 * 保存多条动物信息
	 * @param animalList
	 */
	void saveAllAnimalWeighData(List<TbLWeighData> animalList);
	
	/**
	 * 根据试验计划和 分页信息
	 * @param tblStudyPlan
	 * @param page
	 * @param rows
	 * @return
	 */
	List<TbLWeighData> getByStudyNoWithPageRows(String StudyNo, int page,
			int rows);
     
	/**
	 * 通过试验计划（课题编号）获取动物信息
	 * @return List<TblAnimal>
	 */
	List<TbLWeighData> getByStudyNo(String StudyNo);
	
	List<TbLWeighData> getByWeighSn(String StudyNo);
	
	/**
	 * 通过试验计划（课题编号）获取动物信息
	 * @return List<TblAnimal>
	 */
	TbLWeighData getByIdWeight(String id,String StudyNo);
	
	
	/**
	 * 更新多条动物信息
	 * @param animalList
	 */
	void updateAnimals(List<TbLWeighData> animalList);
	
	TbLWeighData getByStudyPlanAndAnimalId(String StudyNo, String anicode);
	
	

}
