package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblTiprpAppRecDt;
import com.lanen.model.studyplan.TblWeighInd;

public interface TblTiprpAppIndService extends BaseDao<TblTiprpAppInd> {
	
	/**
	 * 通过试验计划（课题编号）获取供试品信息
	 * @return List<TblAnimal>
	 */
	TblTiprpAppInd getByStudyNo(String studyNo);
	
	TblTiprpAppInd getOldByStudyNo(String studyNo);
	
	TblTiprpAppInd getByStudyNoAppSn(String studyNo,int AppSn);
	
	List<TblTiprpAppInd> gettblTiprpAppInd(String studyNo);
	
	void saveAllTblTiprpAppInd(List<TblTiprpAppInd> animalList);
	
	/**
	 * 查询申请总数
	 * @param tblStudyPlan
	 * @return
	 */
	Long getTotalByStudyNo(String StudyNo);

	void saveAllTilprpAll(TblTiprpAppInd tblTiprpAppInd,
			List<TblTiprpAppData> datalist, List<TblTiprpAppRecDt> recDtlist);

	
	
}
