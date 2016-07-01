package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QAFileReg;


public interface QAFileRegService extends BaseDao<QAFileReg> {
	/**
	 * 获取某类型下的所有文件
	 * @param fileTypeId
	 * @return
	 */
	List<QAFileReg> getByType(String fileTypeId);
	
	boolean isExist(QAFileReg model,String fileTypeId);
	/**
	 * 查询满足条件的文件
	 * @param fileType
	 * @param fileStateCondition
	 * @param fileSearchCondition
	 * @return
	 */
	List<QAFileReg> getByCondition(Integer fileType,String fileStateCondition,String fileSearchCondition);
	List<QAFileReg> getByConditionAndUser(Integer fileType,String fileStateCondition,String fileSearchCondition,User user);
	
}
