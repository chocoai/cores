package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictChkItemStudyGroupReg;

public interface DictChkItemStudyGroupRegService extends BaseDao<DictChkItemStudyGroupReg>{

	List<DictChkItemStudyGroupReg> getByStudyGroupId(String studyGroupId);
	
	List<DictChkItemStudyGroupReg> getByStudyGroupAndChkType(String studyGroupId,Integer chkType);
	
	List<DictChkItemStudyGroupReg> getByChkItemId(String chkItemId);
	
	DictChkItemStudyGroupReg getByChkItemIdAndChkTblId(String chkItemId,String chkTblId);
	
}
