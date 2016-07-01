package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.clinicaltest.PoolSpecimenCode;

public interface PoolSpecimenCodeService extends BaseDao<PoolSpecimenCode>{
	
	
	
	/** 判断  申请单是否   已经产生检验编号
	 * @param studyNo
	 * @param reqNo
	 * @param testItem
	 * @return
	 */
	boolean isExistSpecimenCode(String studyNo,String modelNumber,int reqNo,int testItem);
	
	
	
	
	
	/**获得检验编号map列表  Map<animalId,specimenCode>
	 * @param studyNo
	 * @param reqNo
	 * @param testItem
	 * @param animalIdList
	 * @return
	 */
	Map<String,String> getMuchNextSpecimenCode(String studyNo,int reqNo,int testItem,String modelNumber,List<String> animalIdList);
	
	/** 判断  申请单是否   已经产生检验编号
	 * @param studyNo
	 * @param reqNo
	 * @return
	 */
	boolean isExistSpecimenCode(String studyNo, int reqNo);
	/**
	 * 根据reqNo和测试项目获取标本的测试仪器id
	 * @param studyNo
	 * @param reqNo
	 * @param testItem
	 * @return
	 */
	String getSpecimenModelNumber(String studyNo, int reqNo,int testItem);
}
