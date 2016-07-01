package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblLog;
import com.lanen.util.DateUtil;

public interface TblLog2Service extends BaseDao<TblLog> {

	/**
	 * 
	 * @param archiveTypeFlag
	 * @return
	 */
	Map<String, Object> getByCondition(Integer archiveTypeFlag,Date logStartDate,Date logEndDate,String searchString,Integer page,Integer rows);
	
}
