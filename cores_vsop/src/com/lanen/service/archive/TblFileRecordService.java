package com.lanen.service.archive;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileRecord;

public interface TblFileRecordService extends BaseDao<TblFileRecord> {

	Integer getMaxSnByArchiveCode(String archiveCode);
	
	List<TblFileRecord> getByArchiveCode(String archiveCode);
	
	/**
	 * 
	 * @param archiveTypeFlag
	 * @return
	 */
	String getLastFileOperateByType(Integer archiveTypeFlag);
	/**
	 * 
	 * @param archiveTypeFlag
	 * @return
	 */
	List<Map<String, Object>> getLastFileOperateListByType(Integer archiveTypeFlag);
	
	/**
	 * 
	 * @param codeAndSnList
	 * @return
	 */
	List<String> getExistByCodeAndSnList(List<String> codeAndSnList);
}
