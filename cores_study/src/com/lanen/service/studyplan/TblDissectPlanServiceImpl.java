package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlanHis;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblDissectPlanHis;
import com.lanen.model.studyplan.TblStudyPlan;

@Service
public class TblDissectPlanServiceImpl extends BaseDaoImpl<TblDissectPlan> implements TblDissectPlanService {

	@SuppressWarnings("unchecked")
	public List<TblDissectPlan> getByStudyNo(TblStudyPlan studyPlan) {
		return getSession().createQuery("FROM TblDissectPlan WHERE tblStudyPlan = ? ORDER BY id").setParameter(0, studyPlan).list();
	}

	@SuppressWarnings("unchecked")
	public boolean uniqueCheck(TblStudyPlan studyPlan , int dissectNumPara , String disId) {
		List<TblDissectPlan> retList = new ArrayList<TblDissectPlan>();
		if(disId != null){
			retList = getSession().createQuery("FROM TblDissectPlan WHERE tblStudyPlan = ? AND dissectNum = ? AND id != ? ORDER BY id").setParameter(0, studyPlan).setParameter(1, dissectNumPara).setParameter(2, disId).list();
		}else {
			retList = getSession().createQuery("FROM TblDissectPlan WHERE tblStudyPlan = ? AND dissectNum = ? ORDER BY id").setParameter(0, studyPlan).setParameter(1, dissectNumPara).list();
		}
			if(retList != null && !retList.isEmpty()){
			return false;
		}else {
			return true;
		}
	}

	public int getNextNum(TblStudyPlan studyPlan) {
		List<TblDissectPlan> objList = getByStudyNo(studyPlan);
		if(objList != null){
			return objList.size()+1;
		}else {
			return 1;
		}
		
	}

	public void save(TblDissectPlan dissectPlan,List<TblAnimalDetailDissectPlan> detailDissectPlans) {
		dissectPlan.setId(getKey("TblDissectPlan"));
	    save(dissectPlan);
	    for(TblAnimalDetailDissectPlan obj: detailDissectPlans){
	    	getSession().update(obj);
	    }
	
	}

	public void update(TblDissectPlan dissectPlan,List<TblAnimalDetailDissectPlan> newDetailDissectPlans) {
		//update(dissectPlan);
		if(null!=newDetailDissectPlans && newDetailDissectPlans.size()>0){
			//设置新的数据
			for(TblAnimalDetailDissectPlan obj: newDetailDissectPlans){
				getSession().update(obj);
			}
		}
	}
	
	@Resource
	private TblAnimalDetailDissectPlanHisService tblAnimalDetailDissectPlanHisService;
	public void updateAndSaveHis(List<TblAnimalDetailDissectPlan> newDetailDissectPlans) {
		///update(dissectPlan);
		if(null!=newDetailDissectPlans && newDetailDissectPlans.size()>0){
			//设置新的数据
			for(TblAnimalDetailDissectPlan obj: newDetailDissectPlans){
				getSession().update(obj);
			    TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(obj.getTblStudyPlan().getStudyNo());
				TblAnimalDetailDissectPlanHis animalDetailDissectPlanHis = new TblAnimalDetailDissectPlanHis();
				animalDetailDissectPlanHis.setId(tblAnimalDetailDissectPlanHisService.getKey());
				animalDetailDissectPlanHis.setAnimalCode(obj.getAnimalCode());
				animalDetailDissectPlanHis.setDissectNum(obj.getDissectNum());
				animalDetailDissectPlanHis.setGender(obj.getGender());
				animalDetailDissectPlanHis.setGroupId(obj.getGroupId());
				animalDetailDissectPlanHis.setOldID(obj.getId());
				animalDetailDissectPlanHis.setOperate("编辑");
				animalDetailDissectPlanHis.setOperateDate(new Date());
				animalDetailDissectPlanHis.setTblApplyReviseID(tblApplyRevise.getId());
				animalDetailDissectPlanHis.setStudyNo(tblApplyRevise.getStudyNo());
				tblAnimalDetailDissectPlanHisService.save(animalDetailDissectPlanHis);
			}
		}
		
	}
	
	public void deleteByStudyPlan(TblStudyPlan tblStudyPlan) {
		getSession().createQuery("delete FROM TblDissectPlan WHERE tblStudyPlan = ? ").setParameter(0, tblStudyPlan).executeUpdate();
		
	}
	public void save(TblDissectPlan dissectPlan) {
		dissectPlan.setId(getKey("TblDissectPlan"));
	    getSession().save(dissectPlan);
	}

	@SuppressWarnings("unchecked")
	public TblDissectPlan getByStudyNo(TblStudyPlan tblStudyPlan, int dissectNum) {
		List<TblDissectPlan> list=getSession().createQuery("FROM TblDissectPlan WHERE tblStudyPlan = ? and dissectNum = ? ")//
		.setParameter(0, tblStudyPlan)//
		.setParameter(1, dissectNum)
		.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Resource
	private TblDissectPlanHisService tblDissectPlanHisService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	public void saveAndSaveHis(TblDissectPlan dissectPlan) {
		dissectPlan.setId(getKey("TblDissectPlan"));
	    getSession().save(dissectPlan);
	    TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(dissectPlan.getTblStudyPlan().getStudyNo());
	    TblDissectPlanHis tblDissectPlanHis = new TblDissectPlanHis();
	    tblDissectPlanHis.setId(tblDissectPlanHisService.getKey());
	    tblDissectPlanHis.setBeginDate(dissectPlan.getBeginDate());
	    tblDissectPlanHis.setDissectNum(dissectPlan.getDissectNum());
	    tblDissectPlanHis.setEndDate(dissectPlan.getEndDate());
	    tblDissectPlanHis.setOldID(dissectPlan.getId());
	    tblDissectPlanHis.setOperate("添加");
	    tblDissectPlanHis.setOperateDate(new Date());
	    tblDissectPlanHis.setTblApplyReviseID(tblApplyRevise.getId());
	    tblDissectPlanHis.setStudyNo(tblApplyRevise.getStudyNo());
	    tblDissectPlanHisService.save(tblDissectPlanHis);
	}

	public void upDateAndSaveHis(TblDissectPlan dissectPlan) {
	    getSession().update(dissectPlan);
	    TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(dissectPlan.getTblStudyPlan().getStudyNo());
	    TblDissectPlanHis tblDissectPlanHis = new TblDissectPlanHis();
	    tblDissectPlanHis.setId(tblDissectPlanHisService.getKey());
	    tblDissectPlanHis.setBeginDate(dissectPlan.getBeginDate());
	    tblDissectPlanHis.setDissectNum(dissectPlan.getDissectNum());
	    tblDissectPlanHis.setEndDate(dissectPlan.getEndDate());
	    tblDissectPlanHis.setOldID(dissectPlan.getId());
	    tblDissectPlanHis.setOperate("编辑");
	    tblDissectPlanHis.setOperateDate(new Date());
	    tblDissectPlanHis.setTblApplyReviseID(tblApplyRevise.getId());
	    tblDissectPlanHis.setStudyNo(tblApplyRevise.getStudyNo());
	    tblDissectPlanHisService.save(tblDissectPlanHis);
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDissectPlanDateByDissectNum(
			String studyNoPara, int dissectNumPara) {
		String sql="select beginDate,endDate,describe from CoresStudy.dbo.tblDissectPlan where studyNo=:studyNoPara and dissectNum=:dissectNumPara";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		                                           .setParameter("studyNoPara", studyNoPara)
		                                           .setParameter("dissectNumPara", dissectNumPara)
		                                           .setResultTransformer(new MapResultTransformer())
		                                           .list();
		Map<String,Object> map=null;
		if(null!=list && list.size()>0){
			map=list.get(0);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDescribeList() {
		String sql="select distinct(describe) as text  FROM CoresStudy.dbo.tblDissectPlan where describe is not null and  describe!=''";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		                                           .setResultTransformer(new MapResultTransformer())
		                                           .list();
		return list;
	}





	
	
}
