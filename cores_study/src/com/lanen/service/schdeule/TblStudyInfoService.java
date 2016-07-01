package com.lanen.service.schdeule;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.model.schedule.TblStudyInfo;

public interface TblStudyInfoService extends BaseDao<TblStudyInfo>{
	
	/**
	 * 生成树形下拉选
	 * @return
	 */
	List<ComboTreeModel> loadAnimalHouseTable();
	
	/**
	 * 判断专题是否存在
	 * @param name
	 * @return
	 */
	boolean isExistByStudyNo(String studyNo);
	/**
	 * 根据专题编号获得实体
	 * @param studyNo
	 * @return
	 */
	TblStudyInfo getByStudyNo(String studyNo);

}
