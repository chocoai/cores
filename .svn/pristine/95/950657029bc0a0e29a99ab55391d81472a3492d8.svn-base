package com.lanen.service.path;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.path.TblAnatomyReq;
import com.lanen.model.path.TblAnatomyReqAnimalList;
import com.lanen.model.path.TblAnatomyReqAnimalListHis;
import com.lanen.model.path.TblAnatomyReqAttachedViscera;
import com.lanen.model.path.TblAnatomyReqAttachedVisceraHis;
import com.lanen.model.path.TblAnatomyReqHis;
import com.lanen.model.path.TblAnatomyReqPathPlanCheck;
import com.lanen.model.path.TblAnatomyReqPathPlanCheckHis;
import com.lanen.model.path.TblAnatomyReqVisceraWeigh;
import com.lanen.model.path.TblAnatomyReqVisceraWeighHis;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblPathPlanAttachedViscera;
import com.lanen.model.path.TblPathPlanCheck;
import com.lanen.model.path.TblPathPlanVisceraWeigh;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.util.CopyUtil;
import com.lanen.util.SystemMessage;
/**
 * 动物解剖申请    serviceImpl
 * @author 
 */ 
@Service
public class TblAnatomyReqServiceImpl extends BaseDaoImpl<TblAnatomyReq> implements
		TblAnatomyReqService {
	//病理计划-脏器/组织学检查service
	@Resource
	private TblPathPlanCheckService tblPathPlanCheckService;
	@Resource
	private TblLogService tblLogService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	//病理计划-脏器称重     service 
	@Resource
	private TblPathPlanVisceraWeighService tblPathPlanVisceraWeighService;
	//病理计划-脏器称重-附件脏器     service 
	@Resource
	private TblPathPlanAttachedVisceraService tblPathPlanAttachedVisceraService;
	//动物详细解剖计划表     service 
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	//解剖动物列表     service 
	@Resource
	private TblAnatomyReqAnimalListService tblAnatomyReqAnimalListService;
	//解剖任务     service 
	@Resource
	private TblAnatomyTaskService tblAnatomyTaskService;
	@Resource
	private UserService userService;
	
	//解剖申请-脏器/组织学检查 servicee 
	@Resource
	private TblAnatomyReqPathPlanCheckService tblAnatomyReqPathPlanCheckService;
	//解剖申请-脏器称重 service
	@Resource
	private TblAnatomyReqVisceraWeighService tblAnatomyReqVisceraWeighService;
	//解剖申请-脏器称重-附加脏器 service
	@Resource
	private TblAnatomyReqAttachedVisceraService tblAnatomyReqAttachedVisceraService;
	
	public List<?> getListByStudyNo(String studyNoPara) {
		String sql="select " +
					   " (select count(aral.id) " +
					   "  from CoresStudy.dbo.tblAnatomyReqAnimalList as aral " +
					   "  where aral.studyNo=ar.studyNo and aral.anatomyReqNo=ar.reqNo" +
					   "  ) as animalNumber,"+
	                   " ar.id,ar.studyNo,ar.reqNo,ar.submitter ,ar.submitDate,ar.anatomyRsn," +
	                   " ar.anatomyPlanNum ,ar.testPhase ,ar.beginDate ,ar.endDate,ar.visceraWeighFlag," +
	                   " ar.visceraFixedFlag ,ar.anatomyCheckFlag,ar.histopathCheckFlag, "+
	                   " ar.tempFlag ,ar.createTime ,ar.author,ar.submitFlag ," +
	                   " (select u.realName from CoresUserPrivilege.dbo.tbluser u where u.id=ar.author ) as authorName" +
	                   ",task.anatomyCheckFinishSign,task.visceraFixedWeightFinishSign ,his.id as hisId"+
                   " from CoresStudy.dbo.tblAnatomyReq as ar " +
                   		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=ar.studyNo and task.anatomyReqNo=ar.reqNo " +
                   		" left join CoresStudy.dbo.tblAnatomyReqHis as his on his.studyNo=ar.studyNo and his.reqNo=ar.reqNo " +
                   " where ar.studyNo=:studyNoPara " +
                   		" order by ar.reqNo";
		List<?> list=getSession().createSQLQuery(sql).setParameter("studyNoPara", studyNoPara).list();
		return list;
	}

	public Integer getReqNoByStudyNo(String studyNoPara) {
		int sn=0;
		String sql ="select Max(ppvw.reqNo)"+
					" from tblAnatomyReq as ppvw " +
					" where ppvw.studyNo=:studyNo";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNoPara).uniqueResult();
		if(maxSn!=null&&maxSn!=0){
			sn=maxSn+1;
		}else{
			sn=1;
		}
		return sn;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListBySubmitDate(
			String beginDateStr, String endDateStr) {
		List<Map<String,Object>> list = null;
		if(beginDateStr.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}") && endDateStr.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}")){
			String sql = " select req.submitFlag,req.id,req.reqNo,req.studyNo,convert(varchar(10),req.beginDate,120) as beginDate," +
						" convert(varchar(10),req.submitDate,120) as submitDate,u.realName as submitter,"+
						" 	convert(varchar(10),req.endDate,120) as endDate ,req.tempFlag,req.anatomyCheckFlag,"+
						"  	req.histopathCheckFlag,req.visceraWeighFlag,req.visceraFixedFlag,study.animalType,"+
						" 	animal2.animalNum,task.taskId,task.anatomyCheckFinishSign,task.histopathCheckFinishSign,"+
						" 	task.visceraWeightFinishSign,task.visceraFixedWeightFinishSign,task.histopathReviewRequirement,"+
						" 	task.histopathReviewFinalSign"+
						" from CoresStudy.dbo.tblAnatomyReq as req left join CoresStudy.dbo.tblStudyPlan as study on"+
						" 	req.studyNo = study.studyNo left join" +
						"  CoresUserPrivilege.dbo.tblUser as u on req.submitter = u.userName left join  "+
						" 	(	select animal.studyNo,animal.anatomyReqNo,count(*) as animalNum"+
						" 		from CoresStudy.dbo.tblAnatomyReqAnimalList as animal"+
						" 		group by animal.studyNo,animal.anatomyReqNo) as animal2 on req.reqNo = animal2.anatomyReqNo and"+
						" 		animal2.studyNo = req.studyNo left join "+
						" 		CoresStudy.dbo.tblAnatomyTask as task on task.studyNo = req.studyNo and task.anatomyReqNo = req.reqNo"+
		
						" where " +
						"(submitFlag = 1 or submitFlag = -1 )" +
//						"(submitFlag = 1 )" +
						" and "+
						" 	( convert(varchar(10),req.submitDate,120) between :beginDate and  :endDate"+
						" 	or convert(varchar(10),req.submitDate,120) between :endDate and  :beginDate)" +
						" order by req.submitDate desc ";
						list = getSession().createSQLQuery(sql)
								.setParameter("beginDate", beginDateStr)
								.setParameter("endDate", endDateStr)
								.setResultTransformer(new MapResultTransformer())
								.list();
		}
		return list;
	}

	public String saveTempReqAndAnatomyTask(TblAnatomyReq tblAnatomyReq,
			List<String> animalCodeList) {
		String reqid = getKey();
		tblAnatomyReq.setId(reqid);
		String studyNo = tblAnatomyReq.getStudyNo();
		int reqNo = this.getReqNoByStudyNo(studyNo);
		tblAnatomyReq.setReqNo(reqNo);
		int visceraWeighFlag = 0; //需脏器称重标识
		int anatomyCheckFlag = 0;  //需剖检标识
		int visceraFixedFlag = 0;  //需脏器固定标识
		int histopathCheckFlag = 0; //需镜检标识
		//病理计划-脏器/组织学检查  列表
		List<TblPathPlanCheck> tblPathPlanCheckList = tblPathPlanCheckService.getListByStudyNo(studyNo);
		//病理计划-脏器称重 列表
		List<TblPathPlanVisceraWeigh> tblPathPlanVisceraWeighList = tblPathPlanVisceraWeighService.getListByStudyNo(studyNo);
		
		//解剖申请-脏器/组织学检查 list(待保存的)
		List<TblAnatomyReqPathPlanCheck> tblAnatomyReqPathPlanCheckList = new ArrayList<TblAnatomyReqPathPlanCheck>() ;
		//解剖申请-脏器/组织学检查
		TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck = null;
		if(null != tblPathPlanCheckList && tblPathPlanCheckList.size()>0){
			for(TblPathPlanCheck tblPathPlanCheck:tblPathPlanCheckList){
				tblAnatomyReqPathPlanCheck = new TblAnatomyReqPathPlanCheck();
				String id = getKey("TblAnatomyReqPathPlanCheck");
				tblAnatomyReqPathPlanCheck.setId(id);
				tblAnatomyReqPathPlanCheck.setReqNo(reqNo);
				tblAnatomyReqPathPlanCheck.setStudyNo(studyNo);
				tblAnatomyReqPathPlanCheck.setVisceraType(tblPathPlanCheck.getVisceraType());
				tblAnatomyReqPathPlanCheck.setVisceraCode(tblPathPlanCheck.getVisceraCode());
				tblAnatomyReqPathPlanCheck.setVisceraName(tblPathPlanCheck.getVisceraName());
				tblAnatomyReqPathPlanCheck.setGender(tblPathPlanCheck.getGender());
				tblAnatomyReqPathPlanCheck.setAtanomyCheckFlag(tblPathPlanCheck.getAtanomyCheckFlag());
				tblAnatomyReqPathPlanCheck.setHistopathCheckFlag(tblPathPlanCheck.getHistopathCheckFlag());
				
				tblAnatomyReqPathPlanCheck.setVisceraFixedFlag(tblPathPlanCheck.getVisceraFixedFlag());
				
				tblAnatomyReqPathPlanCheck.setSn(tblPathPlanCheck.getSn());
				
				if(tblPathPlanCheck.getAtanomyCheckFlag() == 1){
					anatomyCheckFlag = 1;
				}
				if(tblPathPlanCheck.getVisceraFixedFlag() == 1){
					visceraFixedFlag = 1;
				}
				if(tblPathPlanCheck.getHistopathCheckFlag() == 1){
					histopathCheckFlag = 1;
				}
				tblAnatomyReqPathPlanCheckList.add(tblAnatomyReqPathPlanCheck);
			}
		}
		
		//解剖申请-脏器称重 list(待保存的)
		List<TblAnatomyReqVisceraWeigh> tblAnatomyReqVisceraWeighList = new ArrayList<TblAnatomyReqVisceraWeigh>() ;
		//解剖申请-脏器称重
		TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh = null;
		//解剖申请-脏器称重-附件脏器 list(待保存的)
		List<TblAnatomyReqAttachedViscera> tblAnatomyReqAttachedVisceraList = new ArrayList<TblAnatomyReqAttachedViscera>() ;
		//解剖申请-脏器称重-附件脏器
		TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera = null;
		
		if(null != tblPathPlanVisceraWeighList && tblPathPlanVisceraWeighList.size()>0){
			for(TblPathPlanVisceraWeigh tblPathPlanVisceraWeigh :tblPathPlanVisceraWeighList){
				tblAnatomyReqVisceraWeigh = new TblAnatomyReqVisceraWeigh();
				String pid = getKey("TblAnatomyReqVisceraWeigh");
				tblAnatomyReqVisceraWeigh.setId(pid);
				tblAnatomyReqVisceraWeigh.setReqNo(reqNo);
				tblAnatomyReqVisceraWeigh.setStudyNo(studyNo);
				tblAnatomyReqVisceraWeigh.setVisceraType(tblPathPlanVisceraWeigh.getVisceraType());
				tblAnatomyReqVisceraWeigh.setVisceraCode(tblPathPlanVisceraWeigh.getVisceraCode());
				tblAnatomyReqVisceraWeigh.setVisceraName(tblPathPlanVisceraWeigh.getVisceraName());
				tblAnatomyReqVisceraWeigh.setAttachedVisceraFlag(tblPathPlanVisceraWeigh.getAttachedVisceraFlag());
				tblAnatomyReqVisceraWeigh.setFixedWeighFlag(tblPathPlanVisceraWeigh.getFixedWeighFlag());
				tblAnatomyReqVisceraWeigh.setPartVisceraSeparateWeigh(tblPathPlanVisceraWeigh.getPartVisceraSeparateWeigh());
				tblAnatomyReqVisceraWeigh.setSn(tblPathPlanVisceraWeigh.getSn());
				tblAnatomyReqVisceraWeighList.add(tblAnatomyReqVisceraWeigh);
				visceraWeighFlag = 1;
				//附加脏器
				if(tblPathPlanVisceraWeigh.getAttachedVisceraFlag() == 1){
					//病理计划-脏器称重-附件称重list
					List<TblPathPlanAttachedViscera> tblPathPlanAttachedVisceraList =tblPathPlanAttachedVisceraService.getListByPid(tblPathPlanVisceraWeigh.getId());
					if(null != tblPathPlanAttachedVisceraList && tblPathPlanAttachedVisceraList.size()>0){
						for(TblPathPlanAttachedViscera tblPathPlanAttachedViscera:tblPathPlanAttachedVisceraList){
							tblAnatomyReqAttachedViscera = new TblAnatomyReqAttachedViscera();
							String id = getKey("TblAnatomyReqAttachedViscera");
							tblAnatomyReqAttachedViscera.setId(id);
							tblAnatomyReqAttachedViscera.setAnatomyReqVisceraWeighID(pid);
							tblAnatomyReqAttachedViscera.setVisceraType(tblPathPlanAttachedViscera.getVisceraType());
							tblAnatomyReqAttachedViscera.setVisceraCode(tblPathPlanAttachedViscera.getVisceraCode());
							tblAnatomyReqAttachedViscera.setVisceraName(tblPathPlanAttachedViscera.getVisceraName());
							tblAnatomyReqAttachedVisceraList.add(tblAnatomyReqAttachedViscera);
						}
					}
				}
			}
		}
		
		tblAnatomyReq.setVisceraWeighFlag(visceraWeighFlag);
		tblAnatomyReq.setAnatomyCheckFlag(anatomyCheckFlag);
		tblAnatomyReq.setVisceraFixedFlag(visceraFixedFlag);
		tblAnatomyReq.setHistopathCheckFlag(histopathCheckFlag);
		
		//动物详细解剖列表列表
		List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList = tblAnimalDetailDissectPlanService.getListByStudyNoAndAnimalCodeList(studyNo, animalCodeList);
		//申请解剖动物列表（待保存）
		List<TblAnatomyReqAnimalList> tblAnatomyReqAnimalListList = new ArrayList<TblAnatomyReqAnimalList>();
		TblAnatomyReqAnimalList tblAnatomyReqAnimalList = null;
		if(null != tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size() > 0){
			for(TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan :tblAnimalDetailDissectPlanList){
				tblAnatomyReqAnimalList = new TblAnatomyReqAnimalList();
				String id = getKey("TblAnatomyReqAnimalList");
				tblAnatomyReqAnimalList.setId(id);
				tblAnatomyReqAnimalList.setStudyNo(studyNo);
				tblAnatomyReqAnimalList.setAnatomyReqNo(reqNo);
				tblAnatomyReqAnimalList.setAnimalCode(tblAnimalDetailDissectPlan.getAnimalCode());
				tblAnatomyReqAnimalList.setGender(tblAnimalDetailDissectPlan.getGender());
				tblAnatomyReqAnimalList.setGroupID(tblAnimalDetailDissectPlan.getGroupId());
				tblAnatomyReqAnimalListList.add(tblAnatomyReqAnimalList);
			}
		}else{
			getSession().getTransaction().rollback();
			return null;
		}
		//解剖任务
		TblAnatomyTask tblAnatomyTask = new TblAnatomyTask();
		String taskId = getKey("TblAnatomyTask");
		tblAnatomyTask.setTaskId(taskId);
		tblAnatomyTask.setStudyNo(studyNo);
		tblAnatomyTask.setAnatomyReqNo(reqNo);
		tblAnatomyTask.setTaskCreater(tblAnatomyReq.getAuthor());
		tblAnatomyTask.setTaskCreateTime(tblAnatomyReq.getCreateTime());
		tblAnatomyTask.setTempFlag(1);//临时
		int anatomyNum = tblAnatomyTaskService.getNextAnatomyNumByStudyNo(studyNo);
		tblAnatomyTask.setAnatomyNum(anatomyNum);
		String anatomyRsn = "";
		switch (tblAnatomyReq.getAnatomyRsn()) {
		case 1:
			anatomyRsn = "计划解剖";
			break;
		case 2:
			anatomyRsn = "濒死解剖";
			break;
		case 3:
			anatomyRsn = "死亡解剖";
			break;

		default:
			break;
		}
		tblAnatomyTask.setAnatomyRsn(anatomyRsn);
		
		//保存临时解剖申请
		getSession().save(tblAnatomyReq);
		//保存剖检、镜检
//		List<TblAnatomyReqPathPlanCheck> tblAnatomyReqPathPlanCheckList 
		if(null != tblAnatomyReqPathPlanCheckList && tblAnatomyReqPathPlanCheckList.size()>0){
			for(TblAnatomyReqPathPlanCheck obj:tblAnatomyReqPathPlanCheckList){
				getSession().save(obj);
			}
		}
		//保存称重
//		List<TblAnatomyReqVisceraWeigh> tblAnatomyReqVisceraWeighList 
		if(null != tblAnatomyReqVisceraWeighList && tblAnatomyReqVisceraWeighList.size()>0){
			for(TblAnatomyReqVisceraWeigh obj:tblAnatomyReqVisceraWeighList){
				getSession().save(obj);
			}
		}
//		List<TblAnatomyReqAttachedViscera> tblAnatomyReqAttachedVisceraList
		if(null != tblAnatomyReqAttachedVisceraList && tblAnatomyReqAttachedVisceraList.size()>0){
			for(TblAnatomyReqAttachedViscera obj:tblAnatomyReqAttachedVisceraList){
				getSession().save(obj);
			}
		}
		//解剖动物列表
//		List<TblAnatomyReqAnimalList> tblAnatomyReqAnimalListList
		if(null != tblAnatomyReqAnimalListList && tblAnatomyReqAnimalListList.size()>0){
			for(TblAnatomyReqAnimalList obj:tblAnatomyReqAnimalListList){
				getSession().save(obj);
			}
		}
		//保存临时解剖任务
		getSession().save(tblAnatomyTask);
		return taskId;
	}

	public void addSave(TblAnatomyReq tblAnatomyReq,
			List<TblAnatomyReqAnimalList> listAnimals,
			List<TblAnatomyReqPathPlanCheck> listChecks,
			List<TblAnatomyReqVisceraWeigh> listWeighs,
			List<TblAnatomyReqAttachedViscera> listAttachedVisceras) {
		//保存解剖申请
		getSession().save(tblAnatomyReq);
		//保存解剖申请-动物列表
		for(TblAnatomyReqAnimalList tblAnatomyReqAnimalList: listAnimals){
			getSession().save(tblAnatomyReqAnimalList);
		}
		//保存解剖申请-脏器/组织学检查
		if(listChecks!=null&&listChecks.size()>0){
			for(TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck:listChecks){
				getSession().save(tblAnatomyReqPathPlanCheck);
			}
		}
		//保存解剖申请-脏器称重
		if(listWeighs!=null&&listWeighs.size()>0){
			for(TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh:listWeighs){
				getSession().save(tblAnatomyReqVisceraWeigh);
			}
		}
		//保存解剖申请-脏器称重-附加脏器
		if(listAttachedVisceras!=null&&listAttachedVisceras.size()>0){
			for(TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera:listAttachedVisceras){
				getSession().save(tblAnatomyReqAttachedViscera);
			}
		}
	}

	public void editSave(List<TblAnatomyReqAnimalList> listOldAnimals,
			List<TblAnatomyReqPathPlanCheck> listOldChecks,
			List<TblAnatomyReqVisceraWeigh> listOldWeighs,
			List<List<TblAnatomyReqAttachedViscera>> listOldAttachedVisceras,
			TblAnatomyReq tblAnatomyReq,
			List<TblAnatomyReqAnimalList> listAnimals,
			List<TblAnatomyReqPathPlanCheck> listChecks,
			List<TblAnatomyReqVisceraWeigh> listWeighs,
			List<TblAnatomyReqAttachedViscera> listAttachedVisceras) {
		//删除原有的解剖申请-动物列表
		if(listOldAnimals!=null&&listOldAnimals.size()>0){
			for(TblAnatomyReqAnimalList tblAnatomyReqAnimalList:listOldAnimals){
				getSession().delete(tblAnatomyReqAnimalList);
			}
		}
		//删除原有的解剖申请-脏器/组织学检查
		if(listOldChecks!=null&&listOldChecks.size()>0){
			for(TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck:listOldChecks){
				getSession().delete(tblAnatomyReqPathPlanCheck);
			}
		}
		//删除原有的解剖申请-脏器称重
		if(listOldWeighs!=null&&listOldWeighs.size()>0){
			for(TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh:listOldWeighs){
				getSession().delete(tblAnatomyReqVisceraWeigh);
			}
		}
		//删除原有的解剖申请-脏器称重-附加脏器
		if(listOldAttachedVisceras!=null&&listOldAttachedVisceras.size()>0){
			for(List<TblAnatomyReqAttachedViscera> list:listOldAttachedVisceras){
				for(TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera:list){
					getSession().delete(tblAnatomyReqAttachedViscera);
				}
			}
		}
		//更新解剖申请
		getSession().update(tblAnatomyReq);
		//保存解剖申请-动物列表
		for(TblAnatomyReqAnimalList tblAnatomyReqAnimalList: listAnimals){
			getSession().save(tblAnatomyReqAnimalList);
		}
		//保存解剖申请-脏器/组织学检查
		if(listChecks!=null&&listChecks.size()>0){
			for(TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck:listChecks){
				getSession().save(tblAnatomyReqPathPlanCheck);
			}
		}
		//保存解剖申请-脏器称重
		if(listWeighs!=null&&listWeighs.size()>0){
			for(TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh:listWeighs){
				getSession().save(tblAnatomyReqVisceraWeigh);
			}
		}
		//保存解剖申请-脏器称重-附加脏器
		if(listAttachedVisceras!=null&&listAttachedVisceras.size()>0){
			for(TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera:listAttachedVisceras){
				getSession().save(tblAnatomyReqAttachedViscera);
			}
		}
	}

	public void deleteReqAndRelated(TblAnatomyReq tblAnatomyReq,
			List<TblAnatomyReqAnimalList> listAnimals,
			List<TblAnatomyReqPathPlanCheck> listChecks,
			List<TblAnatomyReqVisceraWeigh> listWeighs,
			List<List<TblAnatomyReqAttachedViscera>> listAttachedVisceras) {
		//删除解剖申请
		getSession().delete(tblAnatomyReq);
		//删除解剖申请-动物列表
		for(TblAnatomyReqAnimalList tblAnatomyReqAnimalList: listAnimals){
			getSession().delete(tblAnatomyReqAnimalList);
		}
		//删除解剖申请-脏器/组织学检查
		if(listChecks!=null&&listChecks.size()>0){
			for(TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck:listChecks){
				getSession().delete(tblAnatomyReqPathPlanCheck);
			}
		}
		//删除解剖申请-脏器称重
		if(listWeighs!=null&&listWeighs.size()>0){
			for(TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh:listWeighs){
				getSession().delete(tblAnatomyReqVisceraWeigh);
			}
		}
		//删除解剖申请-脏器称重-附加脏器
		if(listAttachedVisceras!=null&&listAttachedVisceras.size()>0){
			for(List<TblAnatomyReqAttachedViscera> list:listAttachedVisceras){
				for(TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera:list){
					getSession().delete(tblAnatomyReqAttachedViscera);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNoReqNo(String studyNo,
			Integer reqNo) {
		String sql = "select animal.groupID,animal.animalCode,animal.gender,dose.dosage,dose.femaleNum, 1 as endFlag"+
					" from CoresStudy.dbo.tblAnatomyReqAnimalList animal left join"+
					" 	CoresStudy.dbo.tblDoseSetting as dose on animal.studyNo = dose.studyNo"+
					" 	and animal.groupID = dose.dosageNum "+
					" where animal.studyNo = :studyNo and animal.anatomyReqNo = :reqNo";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setParameter("reqNo", reqNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public boolean isHasAnatomyByStudyNoAnimalCode(String studyNo,
			String animalCode) {
		//判断是否存在
		String sql_ = "select dis.animalCode"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as dis "+
					" where dis.animalCode = :animalCode and dis.studyNo = :studyNo ";
		List<?> list_ = getSession().createSQLQuery(sql_)
					.setParameter("studyNo", studyNo)
					.setParameter("animalCode", animalCode)
					.list();
		if(null == list_ || list_.size() < 1){
			return true;
		}
		//存在，判断是否已解剖
		String sql = "select dis.animalCode"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as dis left join CoresStudy.dbo.tblAnatomyReqAnimalList as ani"+
					" 	on dis.studyNo = ani.studyNo and dis.animalCode = ani.animalCode left join CoresStudy.dbo.tblAnatomyReq as req"+
					" 	on ani.studyNo = req.studyNo and ani.anatomyReqNo = req.reqNo and req.submitFlag = 1"+
					" where dis.animalCode = :animalCode and dis.studyNo = :studyNo and req.id is not null";
		List<?> list = getSession().createSQLQuery(sql)
								.setParameter("studyNo", studyNo)
								.setParameter("animalCode", animalCode)
								.list();
		if(null != list && list.size() > 0){
			return true;
		}
		
		return false;
	}

	public List<String> getPathSdCodeList(String studyNo) {
		List<String> list = new ArrayList<String>();
		String sql = "select pathSDCode"+
		" from CoresContract.dbo.tblStudyItem"+
				" where studyNo = :studyNo";
		String pathSdCode = (String) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.uniqueResult();
		if(null != pathSdCode && !"".equals(pathSdCode)){
			list.add(pathSdCode);
		}
		List<User> list2= userService.findByPrivilegeName2("病理负责人");
		if(null != list2 && list2.size() > 0){
			for(User user:list2){
				list.add(user.getUserName());
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public Json setCancel(String studyNo, String animalCode, User user, String reason){
		Json json = new Json();
		String hql = "from TblAnatomyReqAnimalList where studyNo = ? and animalCode = ? and cancelFlag = 0";
		List<TblAnatomyReqAnimalList> animalList = getSession().createQuery(hql)
														.setParameter(0, studyNo)
														.setParameter(1, animalCode)
														.list();
		if(null != animalList && animalList.size() == 1){
			boolean anatomy = isAnatomy(studyNo,animalCode);
			if(anatomy){
				json.setMsg("动物（"+animalCode+"）已解剖");
			}else{
				
				
				//更新
				TblAnatomyReqAnimalList animal = animalList.get(0);
				
				String operator = user.getRealName();
				String signId = writeES(animal.getId(),"TblAnatomyReqAnimalList",201,"从解剖任务中排除该动物",operator);
				
				animal.setCancelFlag(1);
				animal.setReason(reason);
				animal.setSignId(signId);
				
				String operatContent = "专题编号："+studyNo+" 动物编号："+animalCode+"  从解剖任务中排除";
				writeLog("从解剖任务中排除该动物",operatContent,operator);
				
				json.setSuccess(true);
			}
		}else{
			json.setMsg("动物（"+animalCode+"）已标记为解剖前已死亡！");
		}
		
		
		
		return json;
	}
	
	private boolean isAnatomy(String studyNo,String animalCode){
		String sql = " select count(id)"+
					" from CoresStudy.dbo.tblAnatomyAnimal "+
					" where studyNo = ? and animalCode = ? ";
		Integer count  = (Integer) getSession().createSQLQuery(sql)
													.setParameter(0, studyNo)
													.setParameter(1, animalCode)
													.uniqueResult();
		
		return count > 0;
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent,String operator){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("解剖申请-动物列表");
		  tblLog.setOperator(operator);
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost("");
		  tblLogService.save(tblLog);
	}
	
	/**
	 * 签字
	 */
	private String writeES(String dataId,String tableName,int estype,String typeDesc,String operator){
		
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		es.setSigner(operator);
        es.setEsType(estype);
        es.setEsTypeDesc(typeDesc);
        es.setDateTime(new Date());
        String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
        esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(estype);
        esLink.setEsTypeDesc(typeDesc);
        esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESService.save(es);
			tblESLinkService.save(esLink);
		return esId;
	}

	public void change(TblAnatomyReq tblAnatomyReq) {
		String studyNo = tblAnatomyReq.getStudyNo();
		int reqNo = tblAnatomyReq.getReqNo();
		//1.保存历史记录（判断是否需要保存）
		boolean isExist = isExistHis(studyNo,reqNo);
		if(!isExist){//不存的话，保存历史记录
			//1）保存申请历史
			TblAnatomyReqHis tblAnatomyReqHis = new TblAnatomyReqHis();
			try {
				CopyUtil.Copy(tblAnatomyReq, tblAnatomyReqHis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			getSession().save(tblAnatomyReqHis);
			
			List<TblAnatomyReqAnimalList> animalList = 
				tblAnatomyReqAnimalListService.getListByStudyNoAndReqNo(studyNo,reqNo);
			if(null != animalList && animalList.size() > 0){
				TblAnatomyReqAnimalListHis  tblAnatomyReqAnimalListHis= null;
				for(TblAnatomyReqAnimalList obj:animalList){
					tblAnatomyReqAnimalListHis = new TblAnatomyReqAnimalListHis();
					try {
						CopyUtil.Copy(obj, tblAnatomyReqAnimalListHis);
					} catch (Exception e) {
						e.printStackTrace();
					}
					getSession().save(tblAnatomyReqAnimalListHis);
				}
			}
			List<TblAnatomyReqPathPlanCheck> tblAnatomyReqPathPlanCheckList = 
				tblAnatomyReqPathPlanCheckService.getListByStudyNoAndReqNo(studyNo, reqNo);
			if(null != tblAnatomyReqPathPlanCheckList && tblAnatomyReqPathPlanCheckList.size() > 0){
				TblAnatomyReqPathPlanCheckHis tblAnatomyReqPathPlanCheckHis = null;
				for(TblAnatomyReqPathPlanCheck obj:tblAnatomyReqPathPlanCheckList){
					tblAnatomyReqPathPlanCheckHis = new TblAnatomyReqPathPlanCheckHis();
					try {
						CopyUtil.Copy(obj, tblAnatomyReqPathPlanCheckHis);
					} catch (Exception e) {
						e.printStackTrace();
					}
					getSession().save(tblAnatomyReqPathPlanCheckHis);
				}
			}
			List<TblAnatomyReqVisceraWeigh> tblAnatomyReqVisceraWeighList = 
				tblAnatomyReqVisceraWeighService.getListByStudyAndReqNo(studyNo, reqNo);
			if(null != tblAnatomyReqVisceraWeighList && tblAnatomyReqVisceraWeighList.size() > 0){
				TblAnatomyReqVisceraWeighHis  tblAnatomyReqVisceraWeighHis = null;
				for(TblAnatomyReqVisceraWeigh obj:tblAnatomyReqVisceraWeighList){
					tblAnatomyReqVisceraWeighHis = new TblAnatomyReqVisceraWeighHis();
					try {
						CopyUtil.Copy(obj, tblAnatomyReqVisceraWeighHis);
					} catch (Exception e) {
						e.printStackTrace();
					}
					getSession().save(tblAnatomyReqVisceraWeighHis);
					if(obj.getAttachedVisceraFlag() == 1){
						List<TblAnatomyReqAttachedViscera> attachedVisceraList = 
							tblAnatomyReqAttachedVisceraService.getListByPid(obj.getId());
						if(null != attachedVisceraList  && attachedVisceraList.size() > 0){
							TblAnatomyReqAttachedVisceraHis tblAnatomyReqAttachedVisceraHis = null;
							for(TblAnatomyReqAttachedViscera obj2:attachedVisceraList){
								tblAnatomyReqAttachedVisceraHis = new TblAnatomyReqAttachedVisceraHis();
								try {
									CopyUtil.Copy(obj2, tblAnatomyReqAttachedVisceraHis);
								} catch (Exception e) {
									e.printStackTrace();
								}
								getSession().save(tblAnatomyReqAttachedVisceraHis);
							}
						}
					}
				}
			}
			
		}
		//2.置为编辑状态
		tblAnatomyReq.setSubmitFlag(2);
		tblAnatomyReq.setSubmitter(null);
		tblAnatomyReq.setSubmitDate(null);
		getSession().update(tblAnatomyReq);
	}

	public boolean isExistHis(String studyNo, int reqNo) {
		String sql = "select count(id)"+
					" from CoresStudy.dbo.tblAnatomyReqHis"+
					" where studyNo = ? and reqNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.setParameter(1, reqNo)
									.uniqueResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public List<String> getErrorAnimalCodeList(String studyNo,
			List<String> animalCodeList) {
		String sql = "select animalCode"+
					" from CoresStudy.dbo.tblAnatomyAnimal "+
					" where studyNo = :studyNo and animalCode in (:animalCodeList)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameterList("animalCodeList", animalCodeList)
										.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getErrorCheckVisceraCodeList(String studyNo,
			List<String> visceraCodeListCheck) {
		String sql = "select visceraCode"+
					" from CoresStudy.dbo.TblAnatomyCheck" +
					" where studyNo = :studyNo and visceraCode in (:visceraCodeListCheck)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameterList("visceraCodeListCheck", visceraCodeListCheck)
										.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getErrorFixedVisceraCodeList(String studyNo,
			List<String> visceraCodeListFixed) {
		String sql = "select visceraCode"+
		" from CoresStudy.dbo.tblVisceraFixed" +
		" where studyNo = :studyNo and visceraCode in (:visceraCodeListFixed)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameterList("visceraCodeListFixed", visceraCodeListFixed)
										.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getErrorWeighVisceraCodeList(String studyNo,
			List<String> visceraCodeListWeigh) {
		String sql = "select visceraCode"+
		" from CoresStudy.dbo.tblVisceraWeight" +
		" where studyNo = :studyNo and visceraCode in (:visceraCodeListWeigh)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameterList("visceraCodeListWeigh", visceraCodeListWeigh)
										.list();
		return list;
	}

	public TblAnatomyReqHis getByHisId(String reqId) {
		String hql = "from TblAnatomyReqHis where id = ?";
		TblAnatomyReqHis his = (TblAnatomyReqHis) getSession().createQuery(hql).setParameter(0, reqId).uniqueResult();
		return his;
	}

	@SuppressWarnings("unchecked")
	public Json isComeOut(String studyNo, List<String> animalCodeList) {
		Json json = new Json();
		String sql = "select A02 "+
					" from yydb.dbo.TBANIMAL as a"+
					" where a.A01 = :studyNo and a.A02 in (:animalCodeList) and a.A06 = 0";
		List<Integer> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameterList("animalCodeList", animalCodeList)
										.list();
		if(null != list && list.size() > 0){
			json.setSuccess(false);
			String msg = "动物（" ;
			int i = 0;
			for(Integer animalCode :list){
				if(i == 2){
					break;
				}
				if(i == 0){
					msg = msg +animalCode;
				}else {
					msg = msg +"、"+animalCode;
				}
				i++;
			}
			if( i==2 ){
				msg = msg+"等";
			}
			
			msg=msg+"）未传出，是否继续？";
			json.setMsg(msg);
		}else{
			json.setSuccess(true);
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public int getAnatomyRsn(String studyNo, Integer reqNo) {
		String hql = "from TblAnatomyReq where studyNo = ? and reqNo = ?";
		List<TblAnatomyReq> list = getSession().createQuery(hql).setParameter(0, studyNo).setParameter(1, reqNo).list();
		if(null != list && list.size() > 0){
			return list.get(0).getAnatomyRsn();
		}
		return 1;
	}
	
}
