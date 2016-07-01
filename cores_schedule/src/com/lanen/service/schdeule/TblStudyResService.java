package com.lanen.service.schdeule;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.schedule.TblStudyRes;

public interface TblStudyResService extends BaseDao<TblStudyRes> {
	
	/**
	 * 查询所有列表
	 * @return
	 */
	List<TblStudyRes> getAll(Date startDate , Date endDate);
	
	/**
	 * 根据状态查询所有列表
	 * @return
	 */
	List<TblStudyRes> getAllByState(int state);
	
	/**
	 * 所有的未分配的任务
	 * @param animalList
	 */
	void saveAllStudyRes(List<TblStudyRes> studyResList);
	
	void updateAll(List<TblStudyRes> list);
	
	/**
	 * 根据专题编号查询
	 * @param StudyNo
	 * @return
	 */
	List<TblStudyRes> getByStudyNo(String StudyNo);
	
	/**
	 * 根据ID集合删除
	 * @param list
	 */
	void detAllStudyRes(List<String> list);

	/**sr.id,sr.studyNo, house.resName,(case when es.signer is null then ''else es.signer end)as signer
	 * @param studyNo
	 * @return
	 */
	List<?> getResNameSignerByStudyNo(String studyNo);
	
	/**根据当前专题 和 权限名称，查询用户名、姓名（未被使用过的）
	 * @param currentResId
	 * @param string
	 * @return
	 */
	List<?> findUserNameRealNameByResIdPrivilegeName(String StudyNo,
			String string);
    /**
     * 根据课题编号查找资源负责人
     * @param StudyNo
     * @param string
     * @return
     */
	List<?> findUserNameRealNameByResId(String StudyNo);

	/** 判断 该专题下是否已经添加该资源
	 * @param studyNo
	 * @param currentResId
	 */
	boolean isExist(String studyNo, String currentResId);

	/**判断该课题下是否有已经申请的日期，有的话返回  aduitId 无返回""
	 * @param studyNo
	 * @return
	 */
	String getAduitByStudyNo(String studyNo);

	/**检查当前课题对应房间的 楼层或建筑是否有这个人
	 * @param studyNo
	 * @param theAuditId
	 * @return
	 */
	boolean isExistThisOne(String studyNo, String userName);
   
	/**检查当前课题对应房间的 楼层或建筑是否有这个人
	 * @param studyNo
	 * @param theAuditId
	 * @return
	 */
	boolean isExistThisStudyNoAndUser(String studyNo, String userName);
	
	
}
