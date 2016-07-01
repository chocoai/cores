package com.lanen.service.qa;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QALearnTask;

public interface QALearnTaskService extends BaseDao<QALearnTask>{
	/**
	 * 
	 * @param qALearnTask
	 * @return
	 */
	boolean isFinishedByTask(QALearnTask qALearnTask);
	/**
	 * 
	 * @return
	 */
	List<QALearnTask> getAll(Integer learnState);
	/**
	 * 
	 */
	List<QALearnTask> getByExceptState(Integer learnState,Integer exceptState);
	/**
	 * 
	 * @param FileTypeId
	 * @return
	 */
	boolean isExistTaskByFileType(String FileTypeId);
	/**
	 * 
	 * @param fileRegId
	 * @return
	 */
	boolean isExistTaskByFile(String fileRegId);
	/**
	 * 
	 * @param learnTaskId
	 * @return
	 */
	List<Map<String,Object>> isExistAttByTask(String learnTaskId);
	/**
	 * 
	 * @param learnTaskId
	 * @return
	 */
	boolean isExistReaderByTask(String learnTaskId);
}
