package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblApplyRevise;

public interface TblApplyReviseService extends BaseDao<TblApplyRevise>{
	
	TblApplyRevise getByStudyNo(String studyNo);
	/**
	 *获取没有批复的申请
	 **/
	List<TblApplyRevise> getByStudyNo2(String studyNo);
	
	/**
	 * 备份一份版本
	 * @param studyNo
	 */
	void backUpAllByStudyNo(String studyNo);
	
	/**
	 * 
	 * @return
	 */
	TblApplyRevise getByStudyNoAndType(String studyNoPara,int type);
	/**
	 * 
	 * @param studyNoPara
	 * @param version
	 * @return
	 */
	TblApplyRevise getByStudyNoAndVersion(String studyNoPara,int type,int version);
	/**
	 * 
	 * @param studyNoPara
	 * @param type
	 * @return
	 */
	Integer getMaxVersionByStudyNoAndType(String studyNoPara,int type);
	/**
	 * 
	 * @param studyNoPara
	 * @param type
	 * @param version
	 * @return
	 */
	Integer getPresVersionByStudyNoTypeAndVersion(String studyNoPara,int type,int version);
	/**
	 * 
	 * @param studyNoPara
	 * @param type
	 * @param version
	 * @return
	 */
	Integer getNextVersionByStudyNoTypeAndVersion(String studyNoPara,int type,int version);
}
