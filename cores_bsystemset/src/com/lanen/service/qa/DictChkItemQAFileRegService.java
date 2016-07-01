package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictChkItemQAFileReg;

public interface DictChkItemQAFileRegService extends BaseDao<DictChkItemQAFileReg> {
	/**
	 * 根据文件id获取所有检查项和文件关系
	 * @param fileRegId
	 * @return
	 */
	List<DictChkItemQAFileReg> getByFileRegId(String fileRegId);
	/**
	 * 根据检查项id获取所有检查项和文件关系
	 * @param fileRegId
	 * @return
	 */
	List<DictChkItemQAFileReg> getByChkItemId(String chkItemId);
	/**
	 * 根据检查项id和文件id，获取检查项和文件关系
	 * @param fileRegId
	 * @return
	 */
	DictChkItemQAFileReg getByChkItemIdAndChkTblId(String chkItemId,String fileRegId);
	
}
