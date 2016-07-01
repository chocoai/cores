package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
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
	 * 
	 * @param studyNoParam
	 * @param startChkDate
	 * @param endChkDate
	 * @param chkItemId
	 * @param chkIndexStatus
	 * @return
	 */
	List<QAChkRecord>getByStudyNoTimeItemStatusAndUser(String studyNoParam,Date startChkDate,Date endChkDate,String qaIndexId,Integer chkIndexStatus,String realName);
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
	 * @param qaIndexIds
	 * @return
	 */
	List<Map<String, Object>> getByStudyAndChkTimeAndNoReport(String studyNoParam,Date startChkDate,Date endChkDate,String[] qaIndexId,User user);
	/**
	 * 
	 * @param tblRegId
	 * @return
	 */
	List<QAChkRecord> getByTblRegId(String tblRegId);
	/**
	 * 
	 * @return
	 */
	List<QAChkRecord> getByChkIndexId(String chkIndexId);
	/**
	 * 
	 * @param chkIndexId
	 * @return
	 */
	Map<String, Object> getSizeAndValidByChkIndexId(String chkIndexId);
}
