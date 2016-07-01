package com.lanen.service.archive;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.archive.DictSoptype;

public interface DictSOPTypeService extends BaseDao<DictSoptype> {
	/**
	 * 新增之前判断是否已经存在
	 * @return
	 */
	boolean isExist(String sopName,String sopTypeCode);
	/**
	 * 更新之前判断是否存在出来自身的之外
	 * @return
	 */
	boolean isExistExceptOne(String id,String sopName,String sopTypeCode);
	/**
	 * 获取一类下的sn最大值
	 * @param pid
	 * @return
	 */
	Integer getMaxSnByPid(String pid);
	/**
	 * 判断是否存在子类型
	 * @param id
	 * @return
	 */
	boolean isExistChild(String id);
	/**
	 * 
	 * @param soptypeCode
	 * @return
	 */
	DictSoptype getByCode(String soptypeCode);
	/**
	 * 获取list中已经存在的list
	 * @param codes
	 * @return
	 */
	List<String> getExistSOPTypeCodeByList(List<String> codes);

}
