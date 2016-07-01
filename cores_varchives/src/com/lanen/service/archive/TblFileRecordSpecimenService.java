package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileRecordSpecimen;

public interface TblFileRecordSpecimenService extends BaseDao<TblFileRecordSpecimen>{

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
	Map<String, Object> getByCondition(Integer isDestroySpecimen,Integer specimentTypeFlag,Date fileStartDate,Date fileEndDate,Date keepEndDate,Integer isDestory,Integer isValid,String searchString,Integer page,Integer rows);
	/**
	 * 
	 * @param archiveCode
	 * @return
	 */
	List<TblFileRecordSpecimen> getByArchiveCode(String archiveCode);
	/**
	 * 
	 * @param archiveCode
	 * @return
	 */
	Integer getMaxSnByArchiveCode(String archiveCode);
	
}
