package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblStudyMember;

public interface TblStudyMemberService extends BaseDao<TblStudyMember>{
	/**
	 *  通过课题编号获取所有
	 * @param String
	 * @return List<TblDoseSetting>
	 */
	List<TblStudyMember> getByStudyNo(String StudyNo);
	/**
	 * 保存
	 * @param list
	 */
	void saveTblStudyMemberList(List<TblStudyMember> list);
    /**
     * 删除
     * @param list
     */
	void delectTblStudyMemberList(List<TblStudyMember> list);
	/**
	 * 获得成员
	 * @param memberName
	 * @return
	 */
	List<String> getByMember(String memberName);
	
	
}
