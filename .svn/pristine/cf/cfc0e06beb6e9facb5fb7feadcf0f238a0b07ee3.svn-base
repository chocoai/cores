package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAFileType;


public interface QAFileTypeService extends BaseDao<QAFileType> {
	/**
	 * 保存或者编辑之前判断该数据是否已经存在
	 * @param fileType
	 * @param fileTypeName
	 * @param parentId
	 * @return
	 */
	boolean isExistByFileTypeAndNameAndParent(Integer fileType,String fileTypeName,String parentId);
	/**
	 * 获取相同文件类型的文件
	 * @param fileType
	 * @return
	 */
	List<QAFileType> getListByFileType(Integer fileType);
	/**
	 * 根据parentId获取list
	 * @param fileTypeId
	 * @return
	 */
	List<QAFileType> getListByParentId(String fileTypeId);

}
