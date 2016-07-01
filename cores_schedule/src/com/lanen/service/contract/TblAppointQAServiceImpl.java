package com.lanen.service.contract;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblAppointQA;
import com.lanen.model.contract.TblStudyItem;
@Service
public class TblAppointQAServiceImpl extends BaseDaoImpl<TblAppointQA> implements TblAppointQAService{

	@SuppressWarnings("unchecked")
	public void saveAll(List<TblAppointQA> list) {
		for(TblAppointQA obj : list){
			List<TblAppointQA> list1 = getSession().createQuery("From TblAppointQA where studyNo = ? ")
			.setParameter(0, obj.getStudyNo())
			.list();
			if(null != list1 && list1.size()>0){
				delete(list1.get(0).getId());
			}
			getSession().save(obj);
		}
		
	}

	public void updateAll(List<TblAppointQA> list, List<TblStudyItem> list2) {
		for(TblAppointQA obj : list){
			getSession().update(obj);
		}
		for(TblStudyItem obj : list2){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set qa = :qa  ,qaCode = :qaCode,qaState = 1 ,qaAppointDate = :qaAppointDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("qa", obj.getQa());
			query.setParameter("qaCode", obj.getQaCode());
			query.setParameter("id", obj.getId());
			query.setParameter("qaAppointDate", new Date());
			query.executeUpdate();
		}
		
	}

	public void updateAgainAll(List<TblAppointQA> list,
			List<TblAppointQA> list2, List<TblStudyItem> list3) {
		for(TblAppointQA obj : list){
			getSession().update(obj);
		}
		for(TblAppointQA obj : list2){
			getSession().save(obj);
		}
		for(TblStudyItem obj : list3){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set qa = :qa  ,qaCode = :qaCode,qaState = 1 ,qaAppointDate = :qaAppointDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("qa", obj.getQa());
			query.setParameter("qaCode", obj.getQaCode());
			query.setParameter("id", obj.getId());
			query.setParameter("qaAppointDate", new Date());
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public TblAppointQA getByStudyNo(String studyNo) {
		List<TblAppointQA> list1 = getSession().createQuery("From TblAppointQA where studyNo = ?  and state = 0")
		.setParameter(0, studyNo)
		.list();
		if(null != list1 && list1.size()>0){
			return list1.get(0);
		}else{
			return null;
		}
	}



}
