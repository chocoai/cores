package com.lanen.service.studyplan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanAttachedVisceraVersion;
import com.lanen.model.path.TblPathPlanCheck;
import com.lanen.model.path.TblPathPlanCheckVersion;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
import com.lanen.model.path.TblPathPlanVisceraWeighVersion;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblSchedulePlanVersion;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlanVersion;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblDissectPlanVersion;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblDoseSettingVersion;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblStudyPlanVersion;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.model.studyplan.TblTestIndexPlanVersion;
import com.lanen.service.path.TblPathPlanAttachedVisceraService;
import com.lanen.service.path.TblPathPlanAttachedVisceraVersionService;
import com.lanen.service.path.TblPathPlanCheckService;
import com.lanen.service.path.TblPathPlanCheckVersionService;
import com.lanen.service.path.TblPathPlanVisceraWeighService;
import com.lanen.service.path.TblPathPlanVisceraWeighVersionService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblSchedulePlanVersionService;


@Service
public class TblApplyReviseServiceImpl extends BaseDaoImpl<TblApplyRevise> implements TblApplyReviseService{
	
	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 试验计划Service
	 */
	@Resource
	private  TblStudyPlanVersionService tblStudyPlanVersionService;
	
	/**
	  * 剂量设置Service
	  */
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	
	/**
	  * 剂量设置Service
	  */
	@Resource
	private TblDoseSettingVersionService tblDoseSettingVersionService;
	
	/**
	 * 解剖计划Service
	 */
	@Resource
	private TblDissectPlanService tblDissectPlanService;
	
	/**
	 * 解剖计划Service
	 */
	@Resource
	private TblDissectPlanVersionService tblDissectPlanVersionService;
	
	/**
	 * 动物详细解剖
	 */
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	
	/**
	 * 动物详细解剖
	 */
	@Resource
	private TblAnimalDetailDissectPlanVersionService tblAnimalDetailDissectPlanVersionService;
	
	 
	
	/**
	 * 课题计划检验指标Service
	 */
	@Resource
	private TblTestIndexPlanService tblTestIndexPlanService;
	
	/**
	 * 课题计划检验指标Service
	 */
	@Resource
	private TblTestIndexPlanVersionService tblTestIndexPlanVersionService;
	
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	@Resource
	private TblSchedulePlanVersionService tblSchedulePlanVersionService;
	
	/**
	 * 病理计划-脏器称重-附加脏器  
	 */
	@Resource
	private TblPathPlanAttachedVisceraService tblPathPlanAttachedVisceraService;
	
	@Resource
	private TblPathPlanAttachedVisceraVersionService tblPathPlanAttachedVisceraVersionService;

	/**
	 * 病理计划-脏器/组织学检查版本
	 */
	@Resource
	private TblPathPlanCheckService tblPathPlanCheckService;
	
	@Resource
	private TblPathPlanCheckVersionService tblPathPlanCheckVersionService;
	
	/**
	 * 病理计划-脏器称重     service 
	 */
	@Resource
	private TblPathPlanVisceraWeighService tblPathPlanVisceraWeighService;
	@Resource
	private TblPathPlanVisceraWeighVersionService tblPathPlanVisceraWeighVersionService;
	@SuppressWarnings("unchecked")
	public TblApplyRevise getByStudyNo(String studyNo) {
		String hql = " FROM TblApplyRevise  where studyNo = :studyNo order by applyDate desc ";
		List<TblApplyRevise> list =getSession().createQuery(hql)
		.setParameter("studyNo", studyNo)
		.list();
		
		if(null != list && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	@SuppressWarnings("unchecked")
	public TblApplyRevise getByStudyNoAndType(String studyNo,int type) {
		String hql = " FROM TblApplyRevise  where studyNo = :studyNo and type=:type order by applyDate desc ";
		List<TblApplyRevise> list =getSession().createQuery(hql)
		.setParameter("studyNo", studyNo)
		.setParameter("type", type)
		.list();
		
		if(null != list && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public TblApplyRevise getByStudyNoAndVersion(String studyNo,int type,int version)
	{
		String hql = " FROM TblApplyRevise  where studyNo = :studyNo and type=:type and version = :version order by applyDate desc ";
		List<TblApplyRevise> list =getSession().createQuery(hql)
										.setParameter("studyNo", studyNo)
										.setParameter("type", type)
										.setParameter("version", version)
										.list();
		
		if(null != list && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

	public Integer getMaxVersionByStudyNoAndType(String studyNo,int type){
		String nextV = "SELECT top 1  [version] FROM [CoresStudy].[dbo].[tblApplyRevise]" +
		"		where studyNo=:studyNo and type=:type " +
		"		and applyFlag =1 order by version desc";
		Object nextVersion = getSession().createSQLQuery(nextV)
									.setParameter("studyNo", studyNo)
									.setParameter("type", type)
									.uniqueResult();
		if(nextVersion!=null){
			return (Integer)nextVersion;
		}else
			return null;
	}

	public Integer getPresVersionByStudyNoTypeAndVersion(String studyNoPara,int type,int version){
		String nextV = "SELECT top 1  [version] FROM [CoresStudy].[dbo].[tblApplyRevise]" +
					"		where studyNo=:studyNo and type=:type " +
					"		and applyFlag =1 and version<:version " +
					" order by version desc";
		Object nextVersion = getSession().createSQLQuery(nextV)
									.setParameter("studyNo", studyNoPara)
									.setParameter("type", type)
									.setParameter("version", version)
									.uniqueResult();
		if(nextVersion!=null)
			return (Integer)nextVersion;
		else return null;
	}
	public Integer getNextVersionByStudyNoTypeAndVersion(String studyNoPara,int type,int version){
		String nextV = "SELECT top 1  [version] FROM [CoresStudy].[dbo].[tblApplyRevise]" +
					"		where studyNo=:studyNo and type=:type " +
					"		and applyFlag =1 and version>:version" +
					"	order by version asc";
		Object nextVersion = getSession().createSQLQuery(nextV)
									.setParameter("studyNo", studyNoPara)
									.setParameter("type", type)
									.setParameter("version", version)
									.uniqueResult();
		if(nextVersion!=null)
			return (Integer)nextVersion;
		else return null;
	}
	@SuppressWarnings("unchecked")
	public List<TblApplyRevise> getByStudyNo2(String studyNo) {
		String hql = " FROM TblApplyRevise  where studyNo = :studyNo and applyFlag=0  order by applyDate desc ";
		List<TblApplyRevise> list =getSession().createQuery(hql)
		.setParameter("studyNo", studyNo)
		.list();
		
		if(null != list && list.size() > 0){
			return list;
		}else{
			return null;
		}
		
	}
	public void backUpAllByStudyNo(String studyNo) {
		TblApplyRevise applyRevise  = getByStudyNo(studyNo);
		int ver = applyRevise.getVersion();
		TblStudyPlan tblStudyPlan =tblStudyPlanService.getById(studyNo);
		//备份试验计划
		TblStudyPlanVersion tblStudyPlanVersion = new TblStudyPlanVersion();
		tblStudyPlanVersion.setId(tblStudyPlanVersionService.getKey());
		tblStudyPlanVersion.setAnimalCodeMode(tblStudyPlan.getAnimalCodeMode());
		tblStudyPlanVersion.setAnimalImportDate(tblStudyPlan.getAnimalImportDate());
		tblStudyPlanVersion.setAnimalStrain(tblStudyPlan.getAnimalStrain());
		tblStudyPlanVersion.setAnimalType(tblStudyPlan.getAnimalType());
		tblStudyPlanVersion.setClient(tblStudyPlan.getClient());
		tblStudyPlanVersion.setClinicalTestDirector(tblStudyPlan.getClinicalTestDirector());
		tblStudyPlanVersion.setDosageUnit(tblStudyPlan.getDosageUnit());
		tblStudyPlanVersion.setDoseSettingFlag(tblStudyPlan.getDoseSettingFlag());
		tblStudyPlanVersion.setIsGLP(tblStudyPlan.getIsGLP());
		tblStudyPlanVersion.setIsIndentical(tblStudyPlan.getIsIndentical());
		tblStudyPlanVersion.setIsNoGender(tblStudyPlan.getIsNoGender());
		tblStudyPlanVersion.setIsValidation(tblStudyPlan.getIsValidation());
		tblStudyPlanVersion.setPathDirector(tblStudyPlan.getPathDirector());
		tblStudyPlanVersion.setPreStudyDate(tblStudyPlan.getPreStudyDate());
		tblStudyPlanVersion.setQa(tblStudyPlan.getQa());
		tblStudyPlanVersion.setSmplCode(tblStudyPlan.getSmplCode());
		tblStudyPlanVersion.setStudyBeginDate(tblStudyPlan.getStudyBeginDate());
		tblStudyPlanVersion.setStudydirector(tblStudyPlan.getStudydirector());
		tblStudyPlanVersion.setStudyName(tblStudyPlan.getStudyName());
		tblStudyPlanVersion.setStudyNo(studyNo);
		tblStudyPlanVersion.setStudyStartDate(tblStudyPlan.getStudyStartDate());
		tblStudyPlanVersion.setStudyState(tblStudyPlan.getStudyState());
		tblStudyPlanVersion.setStudyTypeCode(tblStudyPlan.getStudyTypeCode());
		tblStudyPlanVersion.setTemp(tblStudyPlan.getTemp());
		tblStudyPlanVersion.setThicknessUnit(tblStudyPlan.getThicknessUnit());
		tblStudyPlanVersion.setVersion(ver);
		tblStudyPlanVersion.setVolumeUnit(tblStudyPlan.getVolumeUnit());
		tblStudyPlanVersionService.save(tblStudyPlanVersion);
		
		List<TblDoseSetting> dlist= tblDoseSettingService.getByStudyNo(tblStudyPlan);
		for(TblDoseSetting doseSetting:dlist){
			TblDoseSettingVersion tblDoseSettingVersion = new TblDoseSettingVersion();
			tblDoseSettingVersion.setId(tblDoseSettingVersionService.getKey());
			tblDoseSettingVersion.setDosage(doseSetting.getDosage());
			tblDoseSettingVersion.setDosageDesc(doseSetting.getDosageDesc());
			tblDoseSettingVersion.setDosageNum(doseSetting.getDosageNum());
			tblDoseSettingVersion.setFemaleNum(doseSetting.getFemaleNum());
			tblDoseSettingVersion.setFemaleThickness(doseSetting.getFemaleThickness());
			tblDoseSettingVersion.setFemaleVolume(doseSetting.getFemaleVolume());
			tblDoseSettingVersion.setMaleNum(doseSetting.getMaleNum());
			tblDoseSettingVersion.setMaleThickness(doseSetting.getMaleThickness());
			tblDoseSettingVersion.setMaleVolume(doseSetting.getMaleVolume());
			tblDoseSettingVersion.setStudyNo(studyNo);
			tblDoseSettingVersion.setVersion(ver);
			tblDoseSettingVersion.setFemaleDosage(doseSetting.getFemaleDosage());
			tblDoseSettingVersionService.save(tblDoseSettingVersion);
		}
		
		 List<TblTestIndexPlan> tlist = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
		 for(TblTestIndexPlan testIndexPlan:tlist){
			 TblTestIndexPlanVersion testIndexPlanVersion = new TblTestIndexPlanVersion();
			 testIndexPlanVersion.setId(tblTestIndexPlanVersionService.getKey());
			 testIndexPlanVersion.setPrecision(testIndexPlan.getPrecision());
			 testIndexPlanVersion.setStudyNo(studyNo);
			 testIndexPlanVersion.setTestIndex(testIndexPlan.getTestIndex());
			 testIndexPlanVersion.setTestIndexAbbr(testIndexPlan.getTestIndexAbbr());
			 testIndexPlanVersion.setTestItem(testIndexPlan.getTestItem());
			 testIndexPlanVersion.setVersion(ver);
			 tblTestIndexPlanVersionService.save(testIndexPlanVersion);
		 }
		 
		List<TblDissectPlan> dclist = tblDissectPlanService.getByStudyNo(tblStudyPlan);
		for(TblDissectPlan dissectPlan:dclist){
			TblDissectPlanVersion tblDissectPlanVersion = new TblDissectPlanVersion();
			tblDissectPlanVersion.setId(tblDissectPlanVersionService.getKey());
			tblDissectPlanVersion.setBeginDate(dissectPlan.getBeginDate());
			tblDissectPlanVersion.setDissectNum(dissectPlan.getDissectNum());
			tblDissectPlanVersion.setEndDate(dissectPlan.getEndDate());
			tblDissectPlanVersion.setStudyNo(studyNo);
			tblDissectPlanVersion.setVersion(ver);
			tblDissectPlanVersionService.save(tblDissectPlanVersion);
		}
		
		List<TblAnimalDetailDissectPlan> alist = tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan);
		for(TblAnimalDetailDissectPlan animalDetailDissectPlan:alist){
			TblAnimalDetailDissectPlanVersion  version  = new TblAnimalDetailDissectPlanVersion(); 
			version.setId(tblAnimalDetailDissectPlanVersionService.getKey());
			version.setAnimalCode(animalDetailDissectPlan.getAnimalCode());
			version.setDissectNum(animalDetailDissectPlan.getDissectNum());
			version.setGender(animalDetailDissectPlan.getGender());
			version.setGroupId(animalDetailDissectPlan.getGroupId());
			version.setStudyNo(studyNo);
			version.setVersion(ver);
			tblAnimalDetailDissectPlanVersionService.save(version);
		}
		
		List<TblSchedulePlan> schedulePlanList = tblSchedulePlanService.getSchedulePlanList(2, studyNo, 2);
		for(TblSchedulePlan schedulePlan:schedulePlanList){
			TblSchedulePlanVersion version = new TblSchedulePlanVersion();
			version.setId(tblSchedulePlanVersionService.getKey());
			version.setScheduleID(schedulePlan.getScheduleID());
			version.setTaskType(schedulePlan.getTaskType());
			version.setTaskCode(schedulePlan.getTaskCode());
			version.setCodeType(schedulePlan.getCodeType());
			version.setEnableDate(schedulePlan.getEnableDate());
			version.setStartDay(schedulePlan.getStartDay());
			version.setStartTime(schedulePlan.getStartTime());
			version.setEndTime(schedulePlan.getEndTime());
			version.setPeriod(schedulePlan.getPeriod());
			version.setPeriodUnit(schedulePlan.getPeriodUnit());
			version.setTaskEndNum(schedulePlan.getTaskEndNum());
			version.setTaskEndDate(schedulePlan.getTaskEndDate());
			version.setTaskEndState(schedulePlan.getTaskEndState());
			version.setTaskEndType(schedulePlan.getTaskEndType());
			version.setTaskItemType(schedulePlan.getTaskItemType());
			version.setTaskName(schedulePlan.getTaskName());
			version.setValidFlag(schedulePlan.getValidFlag());
			version.setCreater(schedulePlan.getCreater());
			version.setCreateDate(schedulePlan.getCreateDate());
			version.setRemark(schedulePlan.getRemark());
			version.setFinishFlag(schedulePlan.getFinishFlag());
			version.setSignId(schedulePlan.getSignId());
			version.setTaskKind(schedulePlan.getTaskKind());
			version.setOldid(schedulePlan.getScheduleID());
			version.setVersion(ver);
			tblSchedulePlanVersionService.save(version);
		}
		
		List<TblPathPlanAttachedViscera> pathPlanAttachedVisceraList =  tblPathPlanAttachedVisceraService.getListByStudyNo(studyNo);
		for(TblPathPlanAttachedViscera his:pathPlanAttachedVisceraList){
			TblPathPlanAttachedVisceraVersion version = new TblPathPlanAttachedVisceraVersion();
			version.setId(tblPathPlanAttachedVisceraVersionService.getKey());
			version.setVisceraCode(his.getVisceraCode());
			version.setVisceraName(his.getVisceraName());
			version.setVisceraType(his.getVisceraType());
			version.setVisceraWeighPlanID(his.getVisceraWeighPlanID());
			version.setVersion(ver);
			tblPathPlanAttachedVisceraVersionService.save(version);
		}
		
		List<TblPathPlanCheck> pathPlanCheckList = tblPathPlanCheckService.getListByStudyNo(studyNo);
		for(TblPathPlanCheck his:pathPlanCheckList){
			TblPathPlanCheckVersion version = new TblPathPlanCheckVersion();
			version.setId(tblPathPlanCheckVersionService.getKey());
			version.setAtanomyCheckFlag(his.getAtanomyCheckFlag());
			version.setGender(his.getGender());
			version.setHistopathCheckFlag(his.getHistopathCheckFlag());
			version.setSn(his.getSn());
			version.setStudyNo(his.getStudyNo());
			version.setVisceraCode(his.getVisceraCode());
			version.setVisceraFixedFlag(his.getVisceraFixedFlag());
			version.setVisceraName(his.getVisceraName());
			version.setVisceraType(his.getVisceraType());
			version.setVersion(ver);
			tblPathPlanCheckVersionService.save(version);
		}
		
		List<TblPathPlanVisceraWeigh> pathPlanVisceraWeighList= tblPathPlanVisceraWeighService.getListByStudyNo(studyNo);
		for(TblPathPlanVisceraWeigh his:pathPlanVisceraWeighList){
			TblPathPlanVisceraWeighVersion version = new TblPathPlanVisceraWeighVersion();
			version.setId(tblPathPlanVisceraWeighVersionService.getKey());
			version.setPid(his.getId());
			version.setAttachedVisceraFlag(his.getAttachedVisceraFlag());
			version.setFixedWeighFlag(his.getFixedWeighFlag());
			version.setPartVisceraSeparateWeigh(his.getPartVisceraSeparateWeigh());
			version.setSn(his.getSn());
			version.setStudyNo(his.getStudyNo());
			version.setVisceraCode(his.getVisceraCode());
			version.setVisceraName(his.getVisceraName());
			version.setVisceraType(his.getVisceraType());
			version.setVersion(ver);
			tblPathPlanVisceraWeighVersionService.save(version);
		}
	}

}
