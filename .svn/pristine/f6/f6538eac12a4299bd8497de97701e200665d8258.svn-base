package com.lanen.service.archive;

import java.util.List;
import java.util.Set;

import com.lanen.base.BaseDao;
import com.lanen.model.archive.DictArchiveType;

public interface DictArchiveTypeService extends BaseDao<DictArchiveType> {
	/**
	 * 获取所有的数据按类别排序
	 * @return
	 */
	List<DictArchiveType> getAll();
	/**
	 * 根据ArchiveTypeFlag查询满足条件的记录
	 * @param archiveTypeFlag
	 * @return
	 */
	List<DictArchiveType> getByArchiveTypeFlag(Integer archiveTypeFlag);
	/**
	 * 判断是否存在类别名
	 * @param archiveTypeName
	 * @return
	 */
	boolean hasTypeName(String archiveTypeName);
	/**
	 * 判断是否存在类别代码
	 * @param archiveTypeCode
	 * @return
	 */
	boolean hasTypeCode(String archiveTypeCode);
	/**
	 * 根据类别代码获取类别
	 * @param archiveTypeCode
	 * @return
	 */
	DictArchiveType getByArchiveTypeCode(String archiveTypeCode);
	/**
	 * 根据typeCode删除记录
	 * @param archiveTypeCode
	 */
	void deleteByTypeCode(String archiveTypeCode);
	/**
	 * 
	 * @param codes
	 * @param archiveType
	 * @return
	 */
	List<String> getExistTypeCodeByList(List<String> codes,Integer archiveType);

	
}
