package com.lanen.service.qa.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
@Service
public class DictChkItemStudyGroupRegServiceImpl extends BaseDaoImpl<DictChkItemStudyGroupReg> implements DictChkItemStudyGroupRegService{

	@SuppressWarnings("unchecked")
	public List<DictChkItemStudyGroupReg> getByChkItemId(String chkItemId) {
		String hql="from DictChkItemStudyGroupReg reg where reg.dictQacheckItem.chkItemId=:chkItemId ";
		List<DictChkItemStudyGroupReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public DictChkItemStudyGroupReg getByChkItemIdAndChkTblId(String chkItemId,String studyGroupId) {
		String hql="from DictChkItemStudyGroupReg reg where reg.dictQacheckItem.chkItemId=:chkItemId and reg.dictStudyGroup.studyGroupId=:studyGroupId";
		List<DictChkItemStudyGroupReg> list = getSession().createQuery(hql)
											.setString("chkItemId", chkItemId)
											.setString("studyGroupId", studyGroupId)
											.list();
		if(list!=null&&list.size()>0)
			return list.get(0);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	public List<DictChkItemStudyGroupReg> getByStudyGroupId(String studyGroupId) {
		String hql="from DictChkItemStudyGroupReg reg where reg.dictStudyGroup.studyGroupId=:studyGroupId ";
		List<DictChkItemStudyGroupReg> list = getSession().createQuery(hql)
											.setString("studyGroupId", studyGroupId)
											.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictChkItemStudyGroupReg> getByStudyGroupAndChkType(String studyGroupId,Integer chkType)
	{
		//chkindex 1:研究；2：过程；3：设施；4方案；5：报告
		//chkitem 1：方案；2：报告；3：变更；4：基于研究的检查项
		String hql="from DictChkItemStudyGroupReg reg where reg.dictStudyGroup.studyGroupId=:studyGroupId " +
				"	and dictQacheckItem.chkItemType=:chkItemType";
		Query query = getSession().createQuery(hql)
								.setString("studyGroupId", studyGroupId);
		if(chkType==1)
			query.setInteger("chkItemType", 4);
		if(chkType==4)
			query.setInteger("chkItemType", 1);
		if(chkType==5)
			query.setInteger("chkItemType", 2);
		
		List<DictChkItemStudyGroupReg> list = query.list();
		return list;
	}
	
}
