package com.lanen.service.qa;

import java.util.List;

import com.lanen.model.User;
import com.lanen.model.qa.TblStudyFileDis;

import com.lanen.base.BaseDao;

public interface TblStudyFileDisService extends BaseDao<TblStudyFileDis>{
	/**
	 * 用户是否存在没有完成的文件分发
	 * @param user
	 * @return
	 */
	boolean isExistNoFinishByUser(User user);
	/**
	 * 
	 * @param studyNo
	 * @param user
	 * @return
	 */
	TblStudyFileDis getNoFinishByStudyNoAndUser(String studyNo,User user);
	/**
	 * 获取已经存在分发的用户
	 * @param studyNo
	 * @param users
	 * @return
	 */
	List<User> getByStudyNoAndUser(String studyNo,List<User> users);
}
