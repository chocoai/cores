package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.DictTestItemType;

public interface DictTestItemTypeService extends BaseDao<DictTestItemType>{
	/**
	 * 获取所有
	 * @return
	 */
	List<DictTestItemType> getAll();

	/**
	 * 保存或更新实体
	 * @param model
	 */
	void svaeOrUpdate(DictTestItemType model);

	/**
	 * 判断tiCode 是否存在
	 * @param tiCode
	 * @return
	 */
	boolean isExistByTiCode(String tiCode);
	/**
	 * 判断tiType 是否存在
	 * @param tiCode
	 * @return
	 */
	boolean isExistByTiType(String tiType);
	/**
	 * 判断tiType 是否存在(除tiCode以外)
	 * @param tiCode
	 * @return
	 */
	boolean isExistByTiType(String tiType, String tiCode);

}
