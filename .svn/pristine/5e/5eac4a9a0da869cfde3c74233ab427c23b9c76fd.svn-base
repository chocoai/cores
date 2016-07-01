package com.lanen.service.studyplan;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblStudyPlan;

public interface TblAnimalDetailDissectPlanService extends BaseDao<TblAnimalDetailDissectPlan> {

	/**
	 * 根据条件查询
	 * @param studyPlan
	 * @return
	 */
	List<TblAnimalDetailDissectPlan> getByStudyPlan(TblStudyPlan studyPlan, int number);

	/**
	 * 查询该试验计划下的所有实体
	 * @param tblStudyPlan
	 * @return
	 */
	List<TblAnimalDetailDissectPlan> getByStudyPlan(TblStudyPlan tblStudyPlan);
	/**
	 * 根据实验计划和动物编号查询实体
	 * @param tblStudyPlan
	 * @param animalCode
	 * @return
	 */
	TblAnimalDetailDissectPlan getByStudyPlanAndAnimalCode(
			TblStudyPlan tblStudyPlan, String animalCode);
	/**
	 * 查询该试验计划下的所有实体(解剖次数为0除外)
	 * @param tblStudyPlan
	 * @return
	 */
	List<TblAnimalDetailDissectPlan> getByStudyPlanAndNo0(
			TblStudyPlan tblStudyPlan);
	
	/**
	 * 根据课题编号和解剖次数查询实体
	 * @param studyNo
	 * @param dissectNum
	 * @return
	 */
	List<Map<String, Object>> getByStudyNoAndDissectNum2(String studyNo,int dissectNum);
	/**
	 * 根据课题编号和解剖次数查询实体
	 * @param studyNo
	 * @param dissectNum
	 * @return
	 */
	List<TblAnimalDetailDissectPlan> getByStudyNoAndDissectNum(String studyNo,int dissectNum);

	/**查询该课题下，符合动物编号要求的list
	 * @param studyNo
	 * @param animalCodeList
	 * @return
	 */
	List<TblAnimalDetailDissectPlan> getListByStudyNoAndAnimalCodeList(
			String studyNo, List<String> animalCodeList);

	/**根据课题编号和动物编号查询动物组别
	 * @param studyNo
	 * @param animalCode
	 * @return
	 */
	Integer getGroupIdByStudyNoAndAnimalCode(String studyNo,String animalCode);
}
