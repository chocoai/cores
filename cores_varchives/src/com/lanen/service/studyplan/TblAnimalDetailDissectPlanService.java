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

	/**查询map列表，map中有 id,dosageNum,dosageDesc,gender,animalCode
	 * @param studyNoPara
	 * @return
	 */
	List<Map<String, Object>> getMapListByStudyNo(String studyNoPara);

	/**动物编号是否存在
	 * @param studyNo
	 * @param animalCode
	 * @return
	 */
	boolean isExistByStudyNoAnimalcode(String studyNo, String animalCode);

	/**动物编号是否存在(自己除外)
	 * @param studyNo
	 * @param animalCode
	 * @param codeId
	 * @return
	 */
	boolean isExistByIdStudyNoAnimalcode(String studyNo, String animalCode,
			String codeId);

	/**Map   中  groupId,gender,number
	 * @param studyNoPara
	 * @return
	 */
	List<Map<String, Object>> getNumberMapListByStudyNo(String studyNoPara);

	/**查询一下个待添加的剂量组和性别   dosageNum,gender
	 * @param studyNo
	 * @param groupId
	 * @param gender
	 * @return
	 */
	Map<String, Object> getNextByStudyNoGroupIdGender(String studyNo,
			int groupId, int gender);
}
