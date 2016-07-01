package com.lanen.service.archive;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.archive.DictAdministrationType;

public interface DictAdministrationTypeService extends BaseDao<DictAdministrationType> {
	/**
	 * 获取所有的数据按类别排序
	 * @return
	 */
	List<DictAdministrationType> getAll();
	
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
	DictAdministrationType getByArchiveTypeCode(String archiveTypeCode);
	/**
	 * 根据typeCode删除记录
	 * @param archiveTypeCode
	 */
	void deleteByTypeCode(String archiveTypeCode);
	/**
	 * 
	 * @return
	 */
	Integer getMaxSn();
	
}
