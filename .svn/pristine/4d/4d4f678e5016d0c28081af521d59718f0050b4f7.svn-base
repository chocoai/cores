package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileContentQacheck;

public interface TblFileContentQACheckService extends
		BaseDao<TblFileContentQacheck> {
	/**
	 * 
	 * @return
	 */
	Map<String, Object> getByCondition(Integer checkItemType, Date fileStartDate,Date fileEndDate,Date keepEndDate,Integer isDestory,Integer isValid,String searchString,Integer page,Integer rows);

}
