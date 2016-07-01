package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictChkItemStudyGroupReg;

public interface DictChkItemStudyGroupRegService extends BaseDao<DictChkItemStudyGroupReg>{
	/**
	 * 
	 * @param studyGroupId
	 * @return
	 */
	List<DictChkItemStudyGroupReg> getByStudyGroupId(String studyGroupId);
	/**
	 * 
	 * @param studyGroupId
	 * @param chkType
	 * @return
	 */
	List<DictChkItemStudyGroupReg> getByStudyGroupAndChkType(String studyGroupId,Integer chkType);
	/**
	 * 
	 * @param chkItemId
	 * @return
	 */
	List<DictChkItemStudyGroupReg> getByChkItemId(String chkItemId);
	/**
	 * 
	 * @param chkItemId
	 * @param chkTblId
	 * @return
	 */
	DictChkItemStudyGroupReg getByChkItemIdAndChkTblId(String chkItemId,String chkTblId);
	
}
