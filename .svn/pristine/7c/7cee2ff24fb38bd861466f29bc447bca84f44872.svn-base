package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblStudyMember;
@Service
public class TblStudyMemberServiceImpl extends BaseDaoImpl<TblStudyMember> implements TblStudyMemberService {

	@SuppressWarnings("unchecked")
	public List<TblStudyMember> getByStudyNo(String StudyNo) {
		List<TblStudyMember> tblStudyMembernList = getSession().createQuery("FROM TblStudyMember t WHERE t.StudyNo = ?").setParameter(0, StudyNo).list();
		return tblStudyMembernList;
	}

	public void delectTblStudyMemberList(List<TblStudyMember> list) {
		for(TblStudyMember obj : list){
			delete(obj.getId());
		}
		
	}

	public void saveTblStudyMemberList(List<TblStudyMember> list) {
		for(TblStudyMember obj : list){
			obj.setId(getKey("TblStudyMember"));
			save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<String> getByMember(String memberName) {
		List<String> StudyNoList = getSession().createQuery("SELECT t.StudyNo FROM TblStudyMember t WHERE t.Member = ?").setParameter(0, memberName).list();
		return StudyNoList;
	}


	

}
