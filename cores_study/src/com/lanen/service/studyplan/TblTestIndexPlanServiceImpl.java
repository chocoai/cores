package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.model.studyplan.TblTestIndexPlanHis;

@Service
public class TblTestIndexPlanServiceImpl extends BaseDaoImpl<TblTestIndexPlan> implements TblTestIndexPlanService {

	@SuppressWarnings("unchecked")
	public List<TblTestIndexPlan> getByStudyNo(TblStudyPlan studyPlan) {
		return getSession().createQuery("FROM TblTestIndexPlan WHERE tblStudyPlan = ? ORDER BY id")
			.setParameter(0, studyPlan).list();
	}

	public void deleteIndexPlans(List<TblTestIndexPlan> objList) {
		for(TblTestIndexPlan obj : objList){
			delete(obj.getId());
		}
		
	}

	public void saveIndexPlans(List<TblTestIndexPlan> objList) {

		for(TblTestIndexPlan obj : objList){
			obj.setId(getKey("TblTestIndexPlan"));
			save(obj);
		    TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(obj.getTblStudyPlan().getStudyNo());
		    if(null != tblApplyRevise){
		    	TblTestIndexPlanHis tblTestIndexPlanHis = new TblTestIndexPlanHis();
				tblTestIndexPlanHis.setId(tblTestIndexPlanHisService.getKey());
				tblTestIndexPlanHis.setOldID(obj.getId());
				tblTestIndexPlanHis.setOperate("添加");
				tblTestIndexPlanHis.setOperateDate(new Date());
				tblTestIndexPlanHis.setPrecision(obj.getPrecision());
				tblTestIndexPlanHis.setTblApplyReviseID(tblApplyRevise.getId());
				tblTestIndexPlanHis.setTestIndex(obj.getTestIndex());
				tblTestIndexPlanHis.setTestIndexAbbr(obj.getTestIndexAbbr());
				tblTestIndexPlanHis.setTestItem(obj.getTestItem());
				tblTestIndexPlanHis.setStudyNo(tblApplyRevise.getStudyNo());
				tblTestIndexPlanHisService.save(tblTestIndexPlanHis);
		    }
		
		}
		
	}

	@Resource
	private TblTestIndexPlanHisService tblTestIndexPlanHisService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	@SuppressWarnings("unchecked")
	public void deleteByStudyPlan(TblStudyPlan studyPlan) {
		String state = studyPlan.getStudyState();
		if(state.equals("3")){
			List<TblTestIndexPlan> list= getSession().createQuery("FROM TblTestIndexPlan WHERE tblStudyPlan = ?").setParameter(0, studyPlan).list();
			List<TblTestIndexPlanHis> listh = new ArrayList<TblTestIndexPlanHis>();
		    TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(studyPlan.getStudyNo());
			for(TblTestIndexPlan testIndexPlan:list){
				TblTestIndexPlanHis tblTestIndexPlanHis = new TblTestIndexPlanHis();
				tblTestIndexPlanHis.setId(tblTestIndexPlanHisService.getKey());
				tblTestIndexPlanHis.setOldID(testIndexPlan.getId());
				tblTestIndexPlanHis.setOperate("删除");
				tblTestIndexPlanHis.setOperateDate(new Date());
				tblTestIndexPlanHis.setPrecision(testIndexPlan.getPrecision());
				tblTestIndexPlanHis.setTblApplyReviseID(tblApplyRevise.getId());
				tblTestIndexPlanHis.setTestIndex(testIndexPlan.getTestIndex());
				tblTestIndexPlanHis.setTestIndexAbbr(testIndexPlan.getTestIndexAbbr());
				tblTestIndexPlanHis.setTestItem(testIndexPlan.getTestItem());
				tblTestIndexPlanHis.setStudyNo(tblApplyRevise.getStudyNo());
				listh.add(tblTestIndexPlanHis);
			}
			tblTestIndexPlanHisService.saveAll(listh);
		}
		getSession().createQuery("DELETE FROM TblTestIndexPlan WHERE tblStudyPlan = ?").setParameter(0, studyPlan).executeUpdate();
		
	}

	
}
