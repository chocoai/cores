package com.lanen.service.contract;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblAppointPathSD;
import com.lanen.model.contract.TblStudyItem;
@Service
public class tblAppointPathSDServiceImpl extends BaseDaoImpl<TblAppointPathSD> implements TblAppointPathSDService{

	@SuppressWarnings("unchecked")
	public void saveAll(List<TblAppointPathSD> list) {
		for(TblAppointPathSD obj : list){
			List<TblAppointPathSD> list1 = getSession().createQuery("From TblAppointPathSD where studyNo = ? ")
			.setParameter(0, obj.getStudyNo())
			.list();
			if(null != list1 && list1.size()>0){
				delete(list1.get(0).getId());
			}
			getSession().save(obj);
		}
		
	}

	public void updateAll(List<TblAppointPathSD> list, List<TblStudyItem> list2) {
		for(TblAppointPathSD obj : list){
			getSession().update(obj);
		}
		for(TblStudyItem obj : list2){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set pathSD = :pathSD  ,pathSDCode = :pathSDCode,paState = 1 ,pathSDDate = :pathSDDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("pathSD", obj.getPathSD());
			query.setParameter("pathSDCode", obj.getPathSDCode());
			query.setParameter("id", obj.getId());
			query.setParameter("pathSDDate", new Date());
			query.executeUpdate();
		}
		
	}

	public void updateAgainAll(List<TblAppointPathSD> list,
			List<TblAppointPathSD> list2, List<TblStudyItem> list3) {
		for(TblAppointPathSD obj : list){
			getSession().update(obj);
		}
		for(TblAppointPathSD obj : list2){
			getSession().save(obj);
		}
		for(TblStudyItem obj : list3){
			String sql = "update [CoresContract].[dbo].[tblStudyItem]  set pathSD = :pathSD  ,pathSDCode = :pathSDCode,paState = 1 ,pathSDDate = :pathSDDate where  id = :id ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("pathSD", obj.getPathSD());
			query.setParameter("pathSDCode", obj.getPathSDCode());
			query.setParameter("id", obj.getId());
			query.setParameter("pathSDDate", new Date());
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public TblAppointPathSD getByStudyNo(String studyNo) {
		List<TblAppointPathSD> list1 = getSession().createQuery("From TblAppointPathSD where studyNo = ?  and state = 0")
		.setParameter(0, studyNo)
		.list();
		if(null != list1 && list1.size()>0){
			return list1.get(0);
		}else{
			return null;
		}
	}

}
