package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblTiprpAppRecDt;
@Service
public class TblTiprpAppIndServiceImpl extends BaseDaoImpl<TblTiprpAppInd>  implements TblTiprpAppIndService{

	public TblTiprpAppInd getByStudyNo(String studyNo) {
		List list =getSession().createQuery("FROM TblTiprpAppInd t WHERE t.StudyNo = ? and t.AppSn=( " +
				" select max(n.AppSn) FROM TblTiprpAppInd n WHERE n.StudyNo = ?) ")
		.setParameter(0, studyNo).setParameter(1, studyNo)//
		.list();
		if(null != list && !list.isEmpty() ){
			return (TblTiprpAppInd) list.get(0);
		}else{
			return null;
		}
	}
	
	public TblTiprpAppInd getOldByStudyNo(String studyNo) {
		List list =getSession().createQuery("FROM TblTiprpAppInd t WHERE t.StudyNo = ? and t.AppSn=( " +
				" (select max(n.AppSn) FROM TblTiprpAppInd n WHERE n.StudyNo = ?)-1 ) ")
		.setParameter(0, studyNo).setParameter(1, studyNo)//
		.list();
		if(null != list && !list.isEmpty() ){
			return (TblTiprpAppInd) list.get(0);
		}else{
			return null;
		}
	}
 
	public TblTiprpAppInd getByStudyNoAppSn(String studyNo, int AppSn) {
		List list = getSession().createQuery("FROM TblTiprpAppInd t WHERE t.StudyNo = ? and t.AppSn= ?").
		setParameter(0, studyNo).setParameter(1, AppSn).list();
		if(null != list ){
			return (TblTiprpAppInd) list.get(0);
		}else{
			return null;
		}
	}
	
	public void saveAllTblTiprpAppInd(List<TblTiprpAppInd> animalList) {
		for(TblTiprpAppInd obj : animalList){
			obj.setId(getKey("TblTiprpAppInd"));
			save(obj);
		}
		
	}
	
	
	public Long getTotalByStudyNo(String StudyNo) {
		return (Long) getSession().createQuery("select count(*) FROM TblTiprpAppInd t WHERE t.StudyNo=? ")//
		.setParameter(0, StudyNo)//
		.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<TblTiprpAppInd> gettblTiprpAppInd(String studyNo) {
		return getSession().createQuery("FROM TblTiprpAppInd t WHERE t.StudyNo = ? ").
		setParameter(0, studyNo).list();
	}

	public void saveAllTilprpAll(TblTiprpAppInd tblTiprpAppInd,
			List<TblTiprpAppData> datalist, List<TblTiprpAppRecDt> recDtlist) {
		getSession().save(tblTiprpAppInd);
		for(TblTiprpAppData obj : datalist){
			obj.setId(getKey("TblTiprpAppData"));
			getSession().save(obj);
		}
		for(TblTiprpAppRecDt obj : recDtlist){
			obj.setId(getKey("TblTiprpAppRecDt"));
			getSession().save(obj);
		}
		
		
	}

	
	
	
}
