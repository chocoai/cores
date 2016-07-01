package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictStudyTestIndex;
import com.lanen.model.studyplan.DictStudyType;

public interface DictStudyTestIndexService extends BaseDao<DictStudyTestIndex> {
	/**
	 * 查询所有列表
	 * @return
	 */
	List<DictStudyTestIndex> getAll();
	
	/**
	 * 根据课题类别查询缺省检验指标
	 * @param obj
	 * @return
	 */
	List<DictStudyTestIndex> getByType(DictStudyType obj);
	
	/**
	 * 根据课题类别编码删除检验指标
	 * @param code
	 */
	void deleteByTypeCode(DictStudyType obj);
	
	/**
	 * 保存多个数据
	 * @param objList
	 */
	void saveAll(List<DictStudyTestIndex> objList);
	
	
}
