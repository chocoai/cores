package com.lanen.service.archive;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileIndex;

public interface TblFileIndexService extends BaseDao<TblFileIndex> {
	/**
	 * 判断是否存在档案编号
	 * @param archiveCodeString
	 * @return
	 */
	boolean isExistArchiveCode(String archiveCodeString);
	/**
	 * 获取最大的档案编号
	 * @param archiveTypeCode
	 * @return
	 */
	String getMaxCodeByTypeCode(String archiveTypeCode);
	/**
	 * 
	 * @param storePosition
	 * @return
	 */
	List<String> getStudyRecordByPosition(String storePosition,String archiveCode);
}
