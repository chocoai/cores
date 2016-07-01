package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QAFileRegReader;

public interface QAFileRegReaderService extends BaseDao<QAFileRegReader>{

	QAFileRegReader getByFileRegIdAndUser(User user,String fileRegId);
	
	List<QAFileRegReader> getByFileRegId(String fileRegId);
	/**
	 * 获取学习任务的学习者
	 * @param learnTaskId
	 * @return
	 */
	List<QAFileRegReader> getByTask(String learnTaskId);
	/**
	 * 判断任务学习者是否已经存在
	 * @param learnTaskId
	 * @param realName
	 * @return
	 */
	boolean isExistByTaskAndReader(String learnTaskId,String realName);
	/**
	 * 
	 * @param user
	 * @return
	 */
	List<QAFileRegReader> getByUser(User user,Integer learnState);
	/**
	 * 根据任务和学习者获取学着者记录
	 * @param learnTaskId
	 * @param user
	 * @return
	 */
	QAFileRegReader getbyTaskAndUser(String learnTaskId,User user);
}
