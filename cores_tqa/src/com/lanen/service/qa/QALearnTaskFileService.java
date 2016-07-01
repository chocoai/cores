package com.lanen.service.qa;

import java.util.List;

import com.lanen.model.User;
import com.lanen.model.qa.QALearnTaskFile;

import com.lanen.base.BaseDao;

public interface QALearnTaskFileService extends BaseDao<QALearnTaskFile> {
	List<QALearnTaskFile> getByFileRegId(String fileRegId);
	
	/**
	 * 获取任务下的文件
	 * @param taskId
	 * @return
	 */
	List<QALearnTaskFile> getFileListByTaskId(String taskId);
	/**
	 * 判断任务下是否已经包含该文件
	 * @param learnTaskId
	 * @param fileRegId
	 * @return
	 */
	boolean isExistByTaskAndFile(String learnTaskId,String fileRegId);
	
	
	
}
