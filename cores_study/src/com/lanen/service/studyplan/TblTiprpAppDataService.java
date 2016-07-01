package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblWeighInd;

public interface TblTiprpAppDataService extends BaseDao<TblTiprpAppData> {	
	
	void saveAllAppDatas(List<TblTiprpAppData> animalList);
    
	/**
	 * 查询申请总数
	 * @param tblStudyPlan
	 * @return
	 */
	Long getTotalByStudyNo(String StudyNo);
	
	List<TblTiprpAppData> getTiprpAppData(String StudyNo , int AppSn);
	
	

}
