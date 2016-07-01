package com.lanen.service.studyplan;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblWeighInd;
@Service
public class TbLWeightDataServiceImpl extends BaseDaoImpl<TbLWeighData>  implements TbLWeightDataService{
	
	public void saveAllAnimalWeighData(List<TbLWeighData> animalList) {
		for(TbLWeighData obj : animalList){
			obj.setId(getKey("TbLWeighData"));
			save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TbLWeighData> getByStudyNoWithPageRows(String StudyNo,
			int page, int rows) {
		if(page==0 ||rows==0){
			page=1;
			rows =2000;
		}
		List<TbLWeighData> list = getSession().createQuery("FROM TbLWeighData t WHERE t.StudyNo=? and t.WeighSn = ( " +
				" select max(n.WeighSn) FROM TbLWeighData n WHERE n.StudyNo = ?  )  Order By t.AniCode")
		.setParameter(0, StudyNo)//
		.setParameter(1, StudyNo)
		.setFirstResult((page-1)*rows)//
		.setMaxResults(page*rows)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TbLWeighData> getByStudyNo(String StudyNo) {
		List list = getSession().createQuery("FROM TbLWeighData t WHERE t.StudyNo = ? and t.WeighSn=(" +
				" select max(n.WeighSn) FROM TbLWeighData n WHERE n.StudyNo = ? " +
				" ) ORDER BY t.AniCode ").setParameter(0, StudyNo).setParameter(1, StudyNo).list();
		if(list == null && list.size()==0){
			return null;
		}else{
			return list;
		}
	}
	 
	@SuppressWarnings("unchecked")
	public List<TbLWeighData> getByWeighSn(String StudyNo) {
		return getSession().createQuery("FROM TbLWeighData t WHERE t.StudyNo = ? and t.WeighSn=((" +
				" select max(n.WeighSn) FROM TbLWeighData n WHERE n.StudyNo = ? " +
				" )  - 1 )ORDER BY t.AniCode ").setParameter(0, StudyNo).setParameter(1, StudyNo).list();
	}
	
	public void updateAnimals(List<TbLWeighData> animalList) {
		for(TbLWeighData obj : animalList){
			update(obj);
		}
		
	}

	public TbLWeighData getByIdWeight(String id,String StudyNo) {
		List list = getSession().createQuery("FROM TbLWeighData t WHERE t.AniCode = ? and t.StudyNo = ? and t.WeighSn=(" +
		" Select max( n.WeighSn ) FROM TbLWeighData n WHERE n.StudyNo = ?  ) ")
		.setParameter(0, id)
		.setParameter(1, StudyNo)
		.setParameter(2, StudyNo).list();
		if(list == null ){
			return null;
		}else{
			return (TbLWeighData) list.get(0);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public TbLWeighData getByStudyPlanAndAnimalId(String StudyNo, String anicode) {
		List<TbLWeighData>  retList = getSession().createQuery("FROM TbLWeighData t WHERE t.StudyNo=? and t.AniCode=? and t.WeighSn=(" +
				" Select max( n.WeighSn ) FROM TbLWeighData n WHERE n.StudyNo = ? )" )
								.setParameter(0, StudyNo).setParameter(1, anicode).setParameter(2,StudyNo).list();
		if(retList!=null&&retList.size()>0){
			return retList.get(0);
		}else {
			return null;
		}
	}

	
	

}
