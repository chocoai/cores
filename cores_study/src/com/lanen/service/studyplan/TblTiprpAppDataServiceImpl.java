package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblTiprpAppData;
@Service
public class TblTiprpAppDataServiceImpl extends BaseDaoImpl<TblTiprpAppData>  implements TblTiprpAppDataService{
                                       
	public void saveAllAppDatas(List<TblTiprpAppData> animalList) {
		for(TblTiprpAppData obj : animalList){
			obj.setId(getKey("TblTiprpAppData"));
			save(obj);
		}
	}

	public Long getTotalByStudyNo(String StudyNo) {
		return (Long) getSession().createQuery("select count(*) FROM TblTiprpAppData t WHERE t.StudyNo=? ")//
		.setParameter(0, StudyNo)//
		.uniqueResult();
	}



	@SuppressWarnings("unchecked")
	public List<TblTiprpAppData> getTiprpAppData(String StudyNo,
			int AppSn) {
		return getSession().createQuery("FROM TblTiprpAppData t WHERE t.StudyNo = ? and t.AppSn = ? Order by t.AniCode ").
		setParameter(0, StudyNo).setParameter(1, AppSn).list();
	}
	

}
