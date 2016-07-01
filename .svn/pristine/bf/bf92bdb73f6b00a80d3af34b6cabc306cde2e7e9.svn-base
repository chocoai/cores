package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileContentContract;
import com.lanen.model.TblFileRecordSmplReserve;

public interface TblFileRecordSmplReserveService extends BaseDao<TblFileRecordSmplReserve> {

	/**
	 * 
	 * @param fileStartDate
	 * @param fileEndDate
	 * @param keepEndDate
	 * @param isDestory
	 * @param isValid
	 * @param searchString
	 * @return
	 */
	Map<String, Object> getByCondition(Integer isSmplKeepEndDate,Date smplKeepEndDate,Integer isDestroySmpl,String smplType,Date fileStartDate,Date fileEndDate,Date keepEndDate,Integer isDestory,Integer isValid,String searchString,Integer page,Integer rows);
	/**
	 * 
	 * @param archiveCode
	 * @return
	 */
	List<TblFileRecordSmplReserve> getByArchiveCode(String archiveCode);
	/**
	 * 
	 * @param archiveCode
	 * @return
	 */
	Integer getMaxSnByArchiveCode(String archiveCode);
	/**
	 * 
	 * @param smplCode
	 * @return
	 */
	List<Map<String, Object>> getSmplListByCode(String smplCode);
}
