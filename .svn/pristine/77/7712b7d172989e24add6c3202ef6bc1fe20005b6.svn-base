package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAChkRecord;

public interface QAChkRecordService extends BaseDao<QAChkRecord> {
	
	/**
	 * 
	 * @param qaIndexId
	 * @param chkTblId
	 * @param chkTblContentId
	 */
	boolean isExistRecord(String qaIndexId,String chkTblContentId);
	/**
	 * 
	 * @param qaIndexId
	 * @return
	 */
	List<QAChkRecord> getListByIndexIdAndTblId(String qaIndexId);
	/**
	 * 
	 * @param studyNoParam
	 * @return
	 */
	List<QAChkRecord> getByStudyNo(String studyNoParam);
	/**
	 * 查找特定日期中的特定检查索引下的检查记录
	 * @param studyNoParam
	 * @param startChkDate
	 * @param qaIndexId
	 * @return
	 */
	List<QAChkRecord> getByStudyAndChkTime(String studyNoParam,Date startChkDate,Date endChkDate,String qaIndexId);
	/**
	 * 查找特定日期中的特定检查索引下的还没有写如报告的检查记录
	 * @param studyNoParam
	 * @param startChkDate
	 * @param qaIndexId
	 * @return
	 */
	List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(String studyNoParam,Date startChkDate,Date endChkDate,String qaIndexId);

}
