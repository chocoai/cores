package com.lanen.service.qa;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictStudyGroup;


public interface DictStudyGroupService extends BaseDao<DictStudyGroup> {
	/**
	 * 保存之前判断是否已经存在
	 */
	boolean isExistByStudyGroupName(String studyGroupName);

}
