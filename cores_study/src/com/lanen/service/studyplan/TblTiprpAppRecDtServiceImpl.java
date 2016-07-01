package com.lanen.service.studyplan;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblTiprpAppRecDt;
import com.lanen.model.studyplan.TblWeighInd;


@Service
public class TblTiprpAppRecDtServiceImpl extends BaseDaoImpl<TblTiprpAppRecDt>  implements TblTiprpAppRecDtService{

	@SuppressWarnings("unchecked")
	public List<TblTiprpAppRecDt> getByStudyNo(String StudyNo, int appsn) {
		return getSession().createQuery("FROM TblTiprpAppRecDt t WHERE t.StudyNo = ? and t.AppSn= ? ")
        .setParameter(0, StudyNo).setParameter(1, appsn)//
        .list();
        
	}

	public void saveAllTiprpAppRecDt(List<TblTiprpAppRecDt> animalList) {
		for(TblTiprpAppRecDt obj : animalList){
			obj.setId(getKey("TblTiprpAppRecDt"));
			save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TblTiprpAppRecDt> getTiprpRecDtlist(String StudyNo, int AppSn) {
		return getSession().createQuery("FROM TblTiprpAppRecDt t WHERE t.StudyNo = ? and t.AppSn = ? ").
		setParameter(0, StudyNo).setParameter(1, AppSn).list();
	}

	@SuppressWarnings("unchecked")
	public List<TblTiprpAppRecDt> getTiprpRecDttime(String StudyNo, int AppSn,
			Date RecDt) {
		return getSession().createQuery("FROM TblTiprpAppRecDt t WHERE t.StudyNo = ? and t.AppSn = ? and  RecDt = ? ").
		setParameter(0, StudyNo).setParameter(1, AppSn).setParameter(2, RecDt).list();
	}





	

}
