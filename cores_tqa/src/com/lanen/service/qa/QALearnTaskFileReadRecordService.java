package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QALearnTaskFileReadRecord;

public interface QALearnTaskFileReadRecordService extends
		BaseDao<QALearnTaskFileReadRecord> {
	/**
	 * 是否存在学习记录
	 * @param learnRecordId
	 * @param fileRegId
	 * @param attachmentId
	 * @return
	 */
	boolean isExistByLearnRecFileAndAttach(String learnRecordId,String fileRegId,String attachmentId);
	/**
	 * 获取学习记录
	 * @param learnRecordId
	 * @param fileRegId
	 * @param attachmentId
	 * @return
	 */
	QALearnTaskFileReadRecord getByLearnRecFileAndAttach(String learnRecordId,String fileRegId,String attachmentId);
	/**
	 * 
	 * @param learnRecordId
	 * @param fileRegId
	 * @return
	 */
	List<QALearnTaskFileReadRecord> getByLearnRecFile(String learnRecordId,String fileRegId);
	/**
	 * 任务下的所有文件的所有附件的个数
	 * @param learnTaskId
	 * @return
	 */
	Integer getAllTaskAttachNum(String learnTaskId);
	/**
	 * 任务下的所有文件的所有附件已完成的个数
	 * @param learnTaskId
	 * @return
	 */
	Integer getAllTaskAttachFinishNum(String learnRecordID);
	/**
	 * 获取任务下的所有学习记录
	 * @param learnTaskId
	 * @return
	 */
	List<QALearnTaskFileReadRecord> getByTask(String learnTaskId);
}
