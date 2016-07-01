package com.lanen.service.qa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;
import com.lanen.model.qa.QAStudyChkIndex;

public interface QAStudyChkIndexService extends BaseDao<QAStudyChkIndex>{

	QAStudyChkIndex getByStudyNo(String studyNo);
	
	/**
	 * 获取专题QA
	 * @param studyNo
	 * @return
	 */
	public Map<String,Object> getQAStudyChkIndexByStudyNo(String studyNo);
	
	/**
	 * 更改日程变更状态
	 * @param studyNo
	 * @param scheduleChangedFlag
	 */
	void updateScheduleChangedFlag(String studyNo,Integer scheduleChangedFlag);
	/**
	 * 
	 * @param studyState
	 * @param start
	 * @param end
	 * @param studyNoString
	 * @param realName
	 * @return
	 */
	List<Map<String,Object>> getStudyNoByDateAndCondition(Integer studyState,Date start,Date end,String studyNoString,String realName);
	/**
	 * 
	 * @param studyNoPara
	 * @return
	 */
	boolean isExistByStudyNo(String studyNoPara);
	/**
	 * 
	 * @param studyNoParam
	 * @param status
	 * @param catalog
	 * @param searchString
	 * @return
	 */
	List<Map<String, Object>> getAllStatesByCondition(String studyNoParam,User user,Integer status,Integer catalog,String searchString);
	
}
