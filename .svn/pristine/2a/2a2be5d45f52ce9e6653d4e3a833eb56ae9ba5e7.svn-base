package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkSop;

public interface QAChkSopService extends BaseDao<QAChkSop>{
	/**
	 * 获取检查索引下的Sop
	 * @param qaIndexId
	 * @return
	 */
	List<QAChkSop> getByChkIndexId(String qaIndexId);
	/**
	 * 获取检查索引列表的Sop
	 * @param qaIndexId
	 * @return
	 */
	List<QAChkSop> getByChkIndexIds(List<String> qaIndexId);
	/**
	 * 判断是否存在
	 * @param qaIndexId
	 * @param fileRegId
	 * @return
	 */
	boolean isExistByIndexIdAndFileId(String qaIndexId,String fileRegId);
}
