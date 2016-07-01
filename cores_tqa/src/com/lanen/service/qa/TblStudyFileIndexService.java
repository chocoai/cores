package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.TblStudyFileIndex;

public interface TblStudyFileIndexService extends BaseDao<TblStudyFileIndex>{

	/**
	 * 最多有两条数据
	 * @param studyNo
	 * @return
	 */
	List<TblStudyFileIndex> getByStudyNo(String studyNo);
	
	/**
	 * 一个专题最多两条记录，一个是专题，一个是报告
	 * @param studyNo
	 * @param fileType
	 * @return
	 */
	TblStudyFileIndex getByStudyNoAndFileType(String studyNo,Integer fileType);
	
}
