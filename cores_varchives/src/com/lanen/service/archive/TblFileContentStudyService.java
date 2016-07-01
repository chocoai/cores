package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileContentStudy;

public interface TblFileContentStudyService extends BaseDao<TblFileContentStudy> {
	
	/**
	 * 
	 * @param studyNoType
	 * @param fileStartDate
	 * @param fileEndDate
	 * @param keepEndDate
	 * @param isDestory
	 * @param isValid
	 * @param searchString
	 * @return
	 */
	Map<String, Object> getByCondition(Integer studyNoType,Date fileStartDate,Date fileEndDate,Date keepEndDate,Integer isDestory,Integer isValid,String searchString,Integer page,Integer rows);
}