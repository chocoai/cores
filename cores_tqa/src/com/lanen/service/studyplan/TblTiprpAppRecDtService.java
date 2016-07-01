package com.lanen.service.studyplan;

import java.util.Date;
import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblTiprpAppRecDt;


public interface TblTiprpAppRecDtService extends BaseDao<TblTiprpAppRecDt> {
	
	List<TblTiprpAppRecDt> getByStudyNo(String StudyNo,int appsn);
	
	void saveAllTiprpAppRecDt(List<TblTiprpAppRecDt> animalList);
	
	List<TblTiprpAppRecDt> getTiprpRecDtlist(String StudyNo , int AppSn);
	
	List<TblTiprpAppRecDt> getTiprpRecDttime(String StudyNo , int AppSn,Date RecDt);

}
