package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictStudyType;

public interface DictStudyTypeService extends BaseDao<DictStudyType> {

	/**
	 * 查询所有列表
	 * @return
	 */
	List<DictStudyType> getAll();

	/**
	 * 判断课题类别编码是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByCode(String code);
	
	/***
	 * 判断供试品类型代码是否存在
	 * @param tiCode
	 * @return
	 */
	boolean isExistByTiCode(String tiCode);
	/**
	 * 根据供试品类型代码查询课题类别
	 * @param tiCode
	 * @return
	 */
	List<DictStudyType> getByTiCode(String tiCode);
	/**
	 * 判断课题名称是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByStudyName(String studyName);
	/**
	 * 判断课题名称是否存在(自己除外)
	 * @param name
	 * @return
	 */
	boolean isExistByNameCode(String studyTypeCode, String studyName);

	/**根据专题类别名称和公式编号查询专题类别
	 * @param studyName
	 * @param tiNo
	 * @return
	 */
	DictStudyType getByStudyNametiNo(String studyName, String tiNo);

}
