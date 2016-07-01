package com.lanen.service.path;


import org.hibernate.Query;
import org.springframework.stereotype.Service;





import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblAnatomyCheck;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblVisceraWeight;
import com.lanen.model.path.TblVisceraWeightHis;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;

@Service
public class TblVisceraWeightServiceImpl extends BaseDaoImpl<TblVisceraWeight> 
						implements TblVisceraWeightService{

	@Resource
	private TblAnatomyTaskService tblAnatomyTaskService;
	@Resource
	private TblAnatomyReqAttachedVisceraService tblAnatomyReqAttachedVisceraService;
	
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblAnatomyAnimalService tblAnatomyAnimalService;
	/**
	 * 称重记录历史记录service
	 */
	@Resource
	private TblVisceraWeightHisService tblVisceraWeightHisService;
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListByTaskIdAnimalCode(
			String taskId, String animalCode, boolean isFixed) {
		List<Map<String,Object>> mapList = null;
		if(null != taskId && null != animalCode){
			TblAnatomyTask tblAnatomyTask  = tblAnatomyTaskService.getById(taskId);
			String studyNo = tblAnatomyTask.getStudyNo();
			Integer reqNo  = tblAnatomyTask.getAnatomyReqNo();
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
			String animalTypeName = "";
			if(null != tblStudyPlan){
				if(null != tblStudyPlan.getAnimalType()){
					animalTypeName = tblStudyPlan.getAnimalType();
				}
			}
			TblAnatomyAnimal tblAnatomyAnimal = tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
			Integer gender = 0;
			if(null != tblAnatomyAnimal){
				gender = tblAnatomyAnimal.getGender();
			}
			if(!isFixed){
				//1.固定前称重
//				String sql = "select reqWeight.id,reqWeight.visceraType,reqWeight.visceraCode,reqWeight.visceraName,"+
//							" 		reqWeight.attachedVisceraFlag,reqWeight.partVisceraSeparateWeigh,"+
//							" 		dict.visceraCode as subVisceraCode,dict.visceraName as subVisceraName"+
//							" from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as reqWeight left join CoresSystemSet.dbo.dictViscera as dict"+
//							" 	on reqWeight.visceraCode = dict.pVisceraCode  and reqWeight.partVisceraSeparateWeigh = 1 "+//--
//							" where reqWeight.studyNo = :studyNo and reqWeight.reqNo = reqNo and reqWeight.fixedWeighFlag = 0"+
//							" union "+
//							" select '' as id,anatomycheck.visceraType,anatomycheck.visceraCode,anatomycheck.visceraName,0 as attachedVisceraFlag,"+
//							" 		0 as partVisceraSeparateweigh,anatomycheck.subVisceraCode,anatomycheck.subVisceraName"+
//							" from CoresStudy.dbo.TblAnatomyCheck as anatomycheck"+
//							" where anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode and "+
//							" 	("+
//							" 	((anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode = '') and "+
//							" 		anatomycheck.visceraCode "+
//							" 		not in ( select reqWeight.visceraCode"+
//							" 				from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as reqWeight left join CoresSystemSet.dbo.dictViscera as dict"+
//							" 					on reqWeight.visceraCode = dict.pVisceraCode and reqWeight.partVisceraSeparateWeigh = 1 "+//-- 
//							" 				where reqWeight.studyNo = :studyNo and reqWeight.reqNo = :reqNo and reqWeight.fixedWeighFlag = 0) )"+
//							" 	or "+
//							" 	(anatomycheck.subVisceraCode is not null and anatomycheck.subVisceraCode "+
//							" 		not in(select dict.visceraCode as subVisceraCode"+
//							" 				from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as reqWeight left join CoresSystemSet.dbo.dictViscera as dict"+
//							" 					on reqWeight.visceraCode = dict.pVisceraCode and reqWeight.partVisceraSeparateWeigh = 1  "+//-- 
//							" 				where reqWeight.studyNo = :studyNo and reqWeight.reqNo = :reqNo and reqWeight.fixedWeighFlag = 0))"+
//							" 	)";
				
//                String sql  = "select distinct tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.snWeight as sn ,dict2.snWeight as subsn "+
//		  		        " ,tblv.id ,tblv.attachedVisceraFlag , tblv.partVisceraSeparateWeigh  "+
//                        " from (  "+
//						" select distinct tblc.id,tblc.attachedVisceraFlag,tblc.partVisceraSeparateWeigh, tblc.visceraType,tblc.visceraCode,tblc.visceraName,dictviscera.visceraCode as subVisceraCode,dictviscera.visceraName as subVisceraName "+
//						" from CoresStudy.dbo.TblAnatomyReqVisceraWeigh as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
//						" 	on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  "+
//						" 		left join ( "+
//						" 							select v.visceraCode,v.visceraName,v.pVisceraCode "+
//						" 							from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal "+
//					 	" 								on vAnimal.visceraCode = v.visceraCode "+
//					 	" 							where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName) "+
//						" 							) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode "+
//					 	" where tblt.taskId =  :taskId and tblc.fixedWeighFlag != 1 "+
//						" union  "+
//						" select distinct '' as id,0 as attachedVisceraFlag ,0 as partVisceraSeparateweigh,tblc.visceraType,tblc.visceraCode,tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName  "+
//						" from CoresStudy.dbo.TblPathSession as tblp left join CoresStudy.dbo.TblAnatomyCheck as tblc  "+
//						" 	on tblp.sessionId = tblc.sessionId  "+
//					    " where tblp.taskId =  :taskId  and tblc.visceraType is not null and tblc.visceraCode is not null "+
//						"     and tblc.visceraName is not null and tblc.animalCode = :animalCode and " +
//						"  	 tblc.visceraCode not in  (select distinct tblc.visceraCode "+
//						"						 from CoresStudy.dbo.TblAnatomyReqVisceraWeigh as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
//						"						 	on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo   "+
//						"						 		left join ( "+
//						"						 							select v.visceraCode,v.visceraName,v.pVisceraCode "+
//						"						 							from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal "+
//						"				 	 								on vAnimal.visceraCode = v.visceraCode "+
//						"				 	 							where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName) "+
//						"					 							) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode "+
//						"			 	 where tblt.taskId =  :taskId and tblc.fixedWeighFlag != 1 )"+
//						" ) as tblv left join CoresSystemSet.dbo.dictViscera as dict "+
//						" 	on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2 "+
//						" 	 	on tblv.subvisceraCode = dict2.visceraCode "+
//						" 				 where (dict.gender =0 or dict.gender = :gender) "+
//						"  and 	( "+
//						" 		 tblv.subVisceraCode not in (  "+
//						" 						    select tbla.subVisceraCode   "+
//						" 					 		    from CoresStudy.dbo.tblVisceraMissing as tbla where tbla.studyNo = :studyNo and tbla.animalCode =  :animalCode and tbla.subVisceraCode is not null   "+
//						" 					  ) and tblv.visceraCode not in (   "+
//						" 					 	   select tbla.visceraCode   "+ 
//						" 					     from CoresStudy.dbo.tblVisceraMissing as tbla where tbla.studyNo = :studyNo and tbla.animalCode = :animalCode and tbla.subVisceraCode is null  and  tbla.visceraCode is not null    "+
//						" 					  )  or tblv.subVisceraCode is null "+
//						" 		 ) " +
//						"  and  tblv.visceraCode not in ( select tblc.visceraCode from CoresStudy.dbo.TblAnatomyReqVisceraWeigh as tblc where tblc.fixedWeighFlag = 1 and tblc.studyNo =  :studyNo"+
//						" and tblc.reqNo = :reqNo ) "+
//						"  order by sn,subsn";
//				String sql = "select weigh.visceraCode,weigh.visceraName,weigh.visceraType,weigh.id, "+
//				"  weigh.partVisceraSeparateWeigh,weigh.attachedVisceraFlag, "+
//				"  	dictviscera2.visceraCode as subVisceraCode,dictviscera2.visceraName as subVisceraName "+
//				"  from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh "+
//				"  	on task.studyNo = weigh.studyNo and task.anatomyReqNo = weigh.reqNo "+
//				"  	left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+
//				"  	on weigh.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag = 1  "+
//				"  	and anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode "+
//				"  	left join CoresStudy.dbo.tblVisceraMissing as missing on weigh.visceraCode = missing.visceraCode "+
//				"  	and missing.animalCode = :animalCode and missing.studyNo = :studyNo "+
//				"  	left join CoresStudy.dbo.tblAnatomyAnimal as animal on  animal.animalCode = :animalCode and animal.studyNo = :studyNo "+
//				"  	left join CoresSystemSet.dbo.dictViscera as dictviscera on weigh.visceraCode = dictviscera.visceraCode  "+
//				"  	left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = weigh.visceraCode "+
//				"  where task.taskId = :taskId and weigh.fixedWeighFlag = 0 and anatomycheck.visceraCode is null and missing.visceraCode is null "+
//				"  		and (animal.gender = dictviscera.gender or dictviscera.gender = 0) "+
//				"  order by dictviscera.snWeight,dictviscera2.snWeight";

				String sql = "select weigh.visceraCode,weigh.visceraName,weigh.visceraType,weigh.id, "+
							" 	weigh.partVisceraSeparateWeigh,weigh.attachedVisceraFlag, "+
							" 	dictviscera2.visceraCode as subVisceraCode,dictviscera2.visceraName as subVisceraName "+
							" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh "+
							" 	on task.studyNo = weigh.studyNo and task.anatomyReqNo = weigh.reqNo "+
	
							" 	left join CoresStudy.dbo.tblAnatomyAnimal as animal on  animal.animalCode = :animalCode and animal.studyNo = :studyNo  "+
							" 	left join CoresSystemSet.dbo.dictViscera as dictviscera on weigh.visceraCode = dictviscera.visceraCode  "+
							" 	left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = weigh.visceraCode "+
	
							" 	left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+
	
							" 	on weigh.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag != 0 and "+
							" 	 anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode and"+
							" 	( isnull(dictviscera2.visceraCode,'') = '' or  isnull(anatomycheck.subVisceraCode,'') = '' or"+
							" 	 isnull(dictviscera2.visceraCode,'') = isnull(anatomycheck.subVisceraCode,''))  "+

							" 	 left join CoresStudy.dbo.tblVisceraMissing as missing on weigh.visceraCode = missing.visceraCode "+
							" 	and missing.animalCode = :animalCode and missing.studyNo = :studyNo and "+
							" 	( isnull(dictviscera2.visceraCode,'') = '' or  isnull(missing.subVisceraCode,'') = '' or"+
							" 	 isnull(dictviscera2.visceraCode,'') = isnull(missing.subVisceraCode,''))  "+

							" where task.taskId = :taskId and weigh.fixedWeighFlag = 0 "+
							" and anatomycheck.visceraCode is null "+
							" and missing.visceraCode is null "+
							" 	and (animal.gender = dictviscera.gender or dictviscera.gender = 0) "+
							" order by dictviscera.snWeight,dictviscera2.snWeight";
				
				mapList = getSession().createSQLQuery(sql)
									.setParameter("taskId", taskId)
									.setParameter("animalCode", animalCode)
									.setParameter("studyNo", studyNo)
									.setResultTransformer(new MapResultTransformer())
									.list();
			}else{
				//2.固定后称重
//				String sql = "select reqWeight.id,reqWeight.visceraType,reqWeight.visceraCode,reqWeight.visceraName,"+
//							 "		reqWeight.attachedVisceraFlag,reqWeight.partVisceraSeparateWeigh,"+
//							 "		dict.visceraCode as subVisceraCode,dict.visceraName as subVisceraName"+
//							 " from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as reqWeight left join CoresSystemSet.dbo.dictViscera as dict"+
//							 " 		on reqWeight.visceraCode = dict.pVisceraCode and reqWeight.partVisceraSeparateWeigh = 1 "+
//							 " where reqWeight.studyNo = :studyNo and reqWeight.reqNo = :reqNo and reqWeight.fixedWeighFlag = 1";
//				  String sql  = "select tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.snWeight as sn ,dict2.snWeight as subsn " +
//				  		" ,tblv.id ,tblv.attachedVisceraFlag , tblv.partVisceraSeparateWeigh  "+
//			        " from (  "+
//					" select distinct tblc.id,tblc.attachedVisceraFlag,tblc.partVisceraSeparateWeigh,tblc.visceraType,tblc.visceraCode,tblc.visceraName,dictviscera.visceraCode as subVisceraCode,dictviscera.visceraName as subVisceraName "+
//					" from CoresStudy.dbo.TblAnatomyReqVisceraWeigh as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
//					" 	on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  "+
//					" 		left join ( "+
//					" 							select v.visceraCode,v.visceraName,v.pVisceraCode "+
//					" 							from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal "+
//				 	" 								on vAnimal.visceraCode = v.visceraCode "+
//				 	" 							where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName) "+
//					" 							) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode "+
//				 	" where tblt.taskId =  :taskId and tblc.fixedWeighFlag = 1 "+
//					" ) as tblv left join CoresSystemSet.dbo.dictViscera as dict "+
//					" 	on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2 "+
//					" 	 	on tblv.subvisceraCode = dict2.visceraCode "+
//					" 				 where dict.gender =0 or dict.gender = :gender "+
//					"  order by sn,subsn";
//				  String sql = "select tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.snWeight as sn ,dict2.snWeight as subsn  "+
//					"   ,tblv.id ,tblv.attachedVisceraFlag , tblv.partVisceraSeparateWeigh   "+
//					"  			         from (   "+
//					"   select distinct tblc.id,tblc.attachedVisceraFlag,tblc.partVisceraSeparateWeigh,tblc.visceraType,tblc.visceraCode,tblc.visceraName,dictviscera.visceraCode as subVisceraCode,dictviscera.visceraName as subVisceraName "+ 
//					"   from CoresStudy.dbo.TblAnatomyReqVisceraWeigh as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt   "+
//					"   	on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo   "+
//					"  				left join (  "+
//					"   				select v.visceraCode,v.visceraName,v.pVisceraCode  "+
//					"   					from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal  "+
//					"   					 on vAnimal.visceraCode = v.visceraCode  "+
//					"   						where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName )  "+
//					"   					) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode  "+
//					"   where tblt.taskId =  :taskId and tblc.fixedWeighFlag = 1  "+
//					"   ) as tblv left join CoresSystemSet.dbo.dictViscera as dict  "+
//					"   	on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2  "+
//					"   	 	on tblv.subvisceraCode = dict2.visceraCode  "+
//					"   		where (dict.gender =0 or dict.gender = :gender )"+
//					"  	and ( "+
//					"  	 tblv.subVisceraCode not in (  "+
//					"  		select tbla.subVisceraCode  "+
//					"  		  from CoresStudy.dbo.tblVisceraMissing as tbla where tbla.studyNo = :studyNo and tbla.animalCode =  :animalCode and tbla.subVisceraCode is not null   "+
//					"  	) and tblv.visceraCode not in (   "+
//					"  		select tbla.visceraCode   "+
//					"  		from CoresStudy.dbo.tblVisceraMissing as tbla where tbla.studyNo = :studyNo and tbla.animalCode = :animalCode and tbla.subVisceraCode is null  and  tbla.visceraCode is not null   "+ 
//					"  		 )  or tblv.subVisceraCode is null "+
//					"  	 ) "+
//					"   order by sn,subsn";
//				String sql = "select weigh.visceraCode,weigh.visceraName,weigh.visceraType,weigh.id, "+
//				"  weigh.partVisceraSeparateWeigh,weigh.attachedVisceraFlag, "+
//				"  	dictviscera2.visceraCode as subVisceraCode,dictviscera2.visceraName as subVisceraName "+
//				"  from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh "+
//				"  	on task.studyNo = weigh.studyNo and task.anatomyReqNo = weigh.reqNo "+
//				"  	left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+
//				"  	on weigh.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag = 1  "+
//				"  	and anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode "+
//				"  	left join CoresStudy.dbo.tblVisceraMissing as missing on weigh.visceraCode = missing.visceraCode "+
//				"  	and missing.animalCode = :animalCode and missing.studyNo = :studyNo "+
//				"  	left join CoresStudy.dbo.tblAnatomyAnimal as animal on  animal.animalCode = :animalCode and animal.studyNo = :studyNo "+
//				"  	left join CoresSystemSet.dbo.dictViscera as dictviscera on weigh.visceraCode = dictviscera.visceraCode  "+
//				"  	left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = weigh.visceraCode "+
//				"  where task.taskId = :taskId and weigh.fixedWeighFlag = 1 and anatomycheck.visceraCode is null and missing.visceraCode is null "+
//				"  		and (animal.gender = dictviscera.gender or dictviscera.gender = 0) "+
//				"  order by dictviscera.snWeight,dictviscera2.snWeight";
				
				String sql = "select weigh.visceraCode,weigh.visceraName,weigh.visceraType,weigh.id, "+
				" 	weigh.partVisceraSeparateWeigh,weigh.attachedVisceraFlag, "+
				" 	dictviscera2.visceraCode as subVisceraCode,dictviscera2.visceraName as subVisceraName "+
				" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh "+
				" 	on task.studyNo = weigh.studyNo and task.anatomyReqNo = weigh.reqNo "+

				" 	left join CoresStudy.dbo.tblAnatomyAnimal as animal on  animal.animalCode = :animalCode and animal.studyNo = :studyNo  "+
				" 	left join CoresSystemSet.dbo.dictViscera as dictviscera on weigh.visceraCode = dictviscera.visceraCode  "+
				" 	left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = weigh.visceraCode "+

				" 	left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+

				" 	on weigh.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag != 0 and "+
				" 	 anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode and"+
				" 	( isnull(dictviscera2.visceraCode,'') = '' or  isnull(anatomycheck.subVisceraCode,'') = '' or"+
				" 	 isnull(dictviscera2.visceraCode,'') = isnull(anatomycheck.subVisceraCode,''))  "+

				" 	 left join CoresStudy.dbo.tblVisceraMissing as missing on weigh.visceraCode = missing.visceraCode "+
				" 	and missing.animalCode = :animalCode and missing.studyNo = :studyNo and "+
				" 	( isnull(dictviscera2.visceraCode,'') = '' or  isnull(missing.subVisceraCode,'') = '' or"+
				" 	 isnull(dictviscera2.visceraCode,'') = isnull(missing.subVisceraCode,''))  "+

				" where task.taskId = :taskId and weigh.fixedWeighFlag = 1 "+
				" and anatomycheck.visceraCode is null "+
				" and missing.visceraCode is null "+
				" 	and (animal.gender = dictviscera.gender or dictviscera.gender = 0) "+
				" order by dictviscera.snWeight,dictviscera2.snWeight";
			mapList = getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setParameter("animalCode", animalCode)
								.setParameter("studyNo", studyNo)
								.setResultTransformer(new MapResultTransformer())
								.list();
			}
			if(null != mapList && mapList.size() > 0){
				for(Map map : mapList){
					Integer attachedVisceraFlag = (Integer) map.get("attachedVisceraFlag");
					if(null != attachedVisceraFlag && attachedVisceraFlag == 1){
						String attachedVisceraNames = "";
						String id = (String) map.get("id");
						attachedVisceraNames = tblAnatomyReqAttachedVisceraService.getAttachedVisceraNamesByPid(id);
						map.put("attachedVisceraNames", attachedVisceraNames);
					}
				}
			}
		}
		return mapList;
	}

	public void saveOne(TblVisceraWeight tblVisceraWeight) {
		String id = getKey();
		tblVisceraWeight.setId(id);
		getSession().save(tblVisceraWeight);
	}

	public String saveOrUpdateOne(TblVisceraWeight tblVisceraWeight) {
		TblVisceraWeight oldObj = this.getOne(tblVisceraWeight.getStudyNo(),
				tblVisceraWeight.getAnimalCode(), tblVisceraWeight.getVisceraCode()
				,tblVisceraWeight.getSubVisceraCode());
		if(null != oldObj){
			oldObj.setSessionId(tblVisceraWeight.getSessionId());
			oldObj.setStudyNo(tblVisceraWeight.getStudyNo());
			oldObj.setAnimalCode(tblVisceraWeight.getAnimalCode());
//			oldObj.setVisceraType(tblVisceraWeight.getVisceraType());
//			oldObj.setVisceraCode(tblVisceraWeight.getVisceraCode());
//			oldObj.setVisceraName(visceraName);
//			oldObj.setSubVisceraCode(subVisceraCode);
//			oldObj.setSubVisceraName(subVisceraName);
			oldObj.setWeight(tblVisceraWeight.getWeight());
			oldObj.setWeightUnit(tblVisceraWeight.getWeightUnit());
			oldObj.setAttachedVisceraFlag(tblVisceraWeight.getAttachedVisceraFlag());
			oldObj.setFixedWeightFlag(tblVisceraWeight.getFixedWeightFlag());
			oldObj.setAttachedVisceraNames(tblVisceraWeight.getAttachedVisceraNames());
			oldObj.setOperator(tblVisceraWeight.getOperator());
			oldObj.setOperateTime(tblVisceraWeight.getOperateTime());
			oldObj.setBalCode(tblVisceraWeight.getBalCode());
			oldObj.setBalValidDate(tblVisceraWeight.getBalValidDate());
			oldObj.setHostName(tblVisceraWeight.getHostName());
			oldObj.setCalIndexId(tblVisceraWeight.getCalIndexId());
			getSession().update(oldObj);
			return oldObj.getId();
		}else{
			String id = getKey();
			tblVisceraWeight.setId(id);
			getSession().save(tblVisceraWeight);
			return id;
		}
		
	}

	public TblVisceraWeight getOne(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		
		String hql = "from TblVisceraWeight where studyNo = :studyNo and " +
				"	animalCode = :animalCode and visceraCode = :visceraCode  ";
				
		if(subVisceraCode == null){
			hql = hql +" and subVisceraCode is null ";
		}else{
			hql = hql +" and ( subVisceraCode = :subVisceraCode) ";
		}
		Query query= getSession().createQuery(hql).setParameter("studyNo", studyNo)
		.setParameter("animalCode", animalCode)
		.setParameter("visceraCode", visceraCode);
		if(subVisceraCode != null){
			query.setParameter("subVisceraCode", subVisceraCode);
		}
		TblVisceraWeight tblVisceraWeight = (TblVisceraWeight) query.uniqueResult();
		return tblVisceraWeight;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListBySessionID(
			List<String> sessionID, String studyNoSelected, String animalCodeSelected, String visceraNameSelected) {
		
		String sql = " select tblw.id ,tblw.sessionId, tblw.studyNo , tblw.animalCode , tblw.visceraName , tblw.subVisceraName , tblw.weight , tblw.weightUnit ," +
				" tblw.fixedWeightFlag , tblw.attachedVisceraNames,tblw.operator , tblw.operateTime, tblw.balCode,tbla.realName " +
				" from CoresStudy.dbo.tblVisceraWeight   as tblw " +
				" left join CoresUserPrivilege.dbo.tbluser as tbla " +
				" on tblw.operator = tbla.userName " +
				" where tblw.sessionId in ( :sessionId )" ;
		if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
			sql=sql+" and tblw.studyNo=:studyNoSelected";
		}
		if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
			sql=sql+" and tblw.animalCode=:animalCodeSelected";
		}
		if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
			sql=sql+" and tblw.visceraName=:visceraNameSelected";
		}
		sql=sql+" order by  tblw.studyNo,tblw.animalCode ";
		Query query=getSession().createSQLQuery(sql);
		query.setParameterList("sessionId", sessionID);
		if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
			query.setParameter("studyNoSelected", studyNoSelected);
		}
		if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
			query.setParameter("animalCodeSelected", animalCodeSelected);
		}
		if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
			query.setParameter("visceraNameSelected", visceraNameSelected);
		}
		List<Map<String,Object>> mapList = query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<TblVisceraWeight> getList(String studyNo, String animalCode) {
		String hql = "from TblVisceraWeight where studyNo = :studyNo and " +
					"	animalCode = :animalCode ";

		List<TblVisceraWeight> tblVisceraWeightList =  getSession().createQuery(hql)
											.setParameter("studyNo", studyNo)
											.setParameter("animalCode", animalCode)
											.list();
		return tblVisceraWeightList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOtherAnimalWeightMapList(
			String studyNo, String animalCode, String visceraCode,
			String subVisceraCode) {
		List<Map<String,Object>> mapList = null;
		if(null != studyNo && null != animalCode && null != visceraCode){
			
			String sql = "select animalCode,weight,weightUnit"+
						" from CoresStudy.dbo.tblVisceraWeight "+
						" where studyNo = :studyNo and visceraCode = :visceraCode  and ";
			if(subVisceraCode == null ){
				sql = sql +" ( subVisceraCode is  null ) and ";
			}else{
				sql = sql +"( :subVisceraCode is null or subVisceraCode = :subVisceraCode) and ";
			}			
					
//				sql = sql +	" 	animalCode in (select animalCode"+
//						" 					from CoresStudy.dbo.tblAnimalDetailDissectPlan"+
//						" 					where studyNo = :studyNo and animalCode != :animalCode   and "+
//						" 							groupId in	(select groupId"+
//						" 							from CoresStudy.dbo.tblAnimalDetailDissectPlan"+
//						" 							where animalCode = :animalCode and studyNo = :studyNo))";
				sql = sql +	" 	animalCode in (select a1.animalCode"+
				" 					from CoresStudy.dbo.tblAnimalDetailDissectPlan as a1 left join CoresStudy.dbo.tblAnimalDetailDissectPlan as a2" +
				"						on a1.studyNo = a2.studyNo and a1.groupId = a2.groupId and a1.gender = a2.gender and a2.animalCode = :animalCode "+
				" 					where a1.studyNo = :studyNo and a1.animalCode != :animalCode and a2.animalCode is not NULL )";
			
									
									
			Query query=getSession().createSQLQuery(sql);
			query.setParameter("studyNo", studyNo)
			.setParameter("animalCode", animalCode)
			.setParameter("visceraCode", visceraCode);
			if(subVisceraCode != null ){
				query.setParameter("subVisceraCode", subVisceraCode);
			}
			 mapList = query
			.setResultTransformer(new MapResultTransformer())
			.list();
		}
		
		return mapList;
	}

	public int countAnimalWeightBySessionID(
			List<String> sessionID) {
		String sql = "select distinct animalCode from CoresStudy.dbo.tblVisceraWeight as tblv where tblv.sessionId in ( :sessionID )";
		List<?> list = getSession().createSQLQuery(sql).setParameterList("sessionID", sessionID).list();
		if(null != list){
			return list.size();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintVisceraWeightServiceBySession(
			String sessionid) {
		String sql = " select tbla.animalCode,tbla.gender,tblb.subVisceraName,tblb.visceraName,tblb.weight,tblb.weightUnit " +
				" ,dv.snWeight,dv2.snWeight as snWeight2, tblb.attachedVisceraFlag,tblb.attachedVisceraNames" +
				" from CoresStudy.dbo.tblAnatomyAnimal as tbla "+
		 " left join CoresStudy.dbo.tblVisceraWeight as tblb "+
		 " on (tbla.visceraWeightSessionId = tblb.sessionId or tbla.visceraFixedWeightSessionId  = tblb.sessionId ) "+
		 "    and tbla.animalCode = tblb.animalCode " +
		 " left join CoresSystemSet.dbo.dictViscera  as dv on tblb.visceraCode = dv.visceraCode"+
		 " 		left join CoresSystemSet.dbo.dictViscera  as dv2 on tblb.subVisceraCode = dv2.visceraCode"+
		 " where sessionId = :sessionId ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
		.setParameter("sessionId", sessionid)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndCodeList(String taskId,
			String sessionId) {
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblVisceraWeight";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (2,3,6,7,8))";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameter("taskId", taskId);
		}
		List<Map<String, Object>> list=query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndCodeLists(String taskId,
			String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblVisceraWeight";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (2,3,6,7,8))";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameterList("taskId", taskList);
		}
		List<Map<String, Object>> list=query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	public Json deleteOne(String id, String reason, String operator) {
		Json json = new Json();
		if(null != id || !"".equals(id) || null != reason 
			|| !"".equals(reason) || null != operator || !"".equals(operator)){
			TblVisceraWeight tblVisceraWeight = getById(id);
			if(null != tblVisceraWeight){
				boolean weightFinishFlag = tblAnatomyAnimalService
				.isWeightFinish(tblVisceraWeight.getStudyNo(),tblVisceraWeight.getAnimalCode(),tblVisceraWeight.getFixedWeightFlag());
				if(weightFinishFlag){
					json.setMsg("称重完成，不可以删除！");
				}else{
					TblVisceraWeightHis his = new TblVisceraWeightHis();
					his.setId(tblVisceraWeightHisService.getKey());
					his.setAnimalCode(tblVisceraWeight.getAnimalCode());
					his.setAttachedVisceraFlag(tblVisceraWeight.getAttachedVisceraFlag());
					his.setAttachedVisceraNames(tblVisceraWeight.getAttachedVisceraNames());
					his.setBalCode(tblVisceraWeight.getBalCode());
					his.setBalValidDate(tblVisceraWeight.getBalValidDate());
					his.setCalIndexId(tblVisceraWeight.getCalIndexId());
					his.setFixedWeightFlag(tblVisceraWeight.getFixedWeightFlag());
					his.setHostName(tblVisceraWeight.getHostName());
					his.setOperateTime(tblVisceraWeight.getOperateTime());
					his.setOperator(tblVisceraWeight.getOperator());
					his.setSessionId(tblVisceraWeight.getSessionId());
					his.setStudyNo(tblVisceraWeight.getStudyNo());
					his.setSubVisceraCode(tblVisceraWeight.getSubVisceraCode());
					his.setSubVisceraName(tblVisceraWeight.getSubVisceraName());
					his.setVisceraCode(tblVisceraWeight.getVisceraCode());
					his.setVisceraName(tblVisceraWeight.getVisceraName());
					his.setVisceraType(tblVisceraWeight.getVisceraType());
					his.setWeight(tblVisceraWeight.getWeight());
					his.setWeightUnit(tblVisceraWeight.getWeightUnit());
					
					his.setOperate("删除");
					his.setOperateRsn(reason);
					his.setOperateDate(new Date());
					his.setOperater(operator);
					his.setOldId(tblVisceraWeight.getId());
					//保存历史记录
					tblVisceraWeightHisService.save(his);
					this.delete(id);
					json.setSuccess(true);
				}
			}
		}else{
			json.setMsg("id为空或reason为空或operator为空！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeBySessionId(String taskId,
			String sessionId) {
		String sql="select animalCode,autolyzeFlag from CoresStudy.dbo.TblAnatomyAnimal";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraWeightSessionId=:sessionId or visceraFixedWeightSessionId=:sessionId";
		}else{
			sql=sql+" where  visceraWeightSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (2,3,6,7,8))" +
					"    or visceraFixedWeightSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (2,3,6,7,8))";
		}
		sql=sql+" order by animalCode";
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameter("taskId", taskId);
		}
		List<Map<String, Object>> list=query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeBySessionIds(String taskId,
			String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql="select animalCode,autolyzeFlag from CoresStudy.dbo.TblAnatomyAnimal";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraWeightSessionId=:sessionId or visceraFixedWeightSessionId=:sessionId";
		}else{
			sql=sql+" where  visceraWeightSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (2,3,6,7,8))" +
					"    or visceraFixedWeightSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (2,3,6,7,8))";
		}
		sql=sql+" order by animalCode";
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameterList("taskId", taskList);
		}
		List<Map<String, Object>> list=query.setResultTransformer(new MapResultTransformer()).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblVisceraWeight> getList(String studyNo, String animalCode,
			boolean fixedFlag) {
		String hql = "from TblVisceraWeight where studyNo = :studyNo and " +
				"	animalCode = :animalCode and fixedWeightFlag = :fixedWeightFlag";
		
		List<TblVisceraWeight> tblVisceraWeightList =  getSession().createQuery(hql)
										.setParameter("studyNo", studyNo)
										.setParameter("animalCode", animalCode)
										.setParameter("fixedWeightFlag", fixedFlag ? 1:0)
										.list();
		return tblVisceraWeightList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByConditions(String taskId,
			String sessionId, String visceraName, String animalCode) {
		String sql = " select tblw.id ,tblw.sessionId, tblw.studyNo , tblw.animalCode , tblw.visceraName , tblw.subVisceraName , tblw.weight , tblw.weightUnit ," +
				" tblw.fixedWeightFlag , tblw.attachedVisceraNames,tblw.operator , tblw.operateTime, tblw.balCode,tbla.realName " +
				" from CoresStudy.dbo.tblVisceraWeight   as tblw " +
				" left join CoresUserPrivilege.dbo.tbluser as tbla " +
				" on tblw.operator = tbla.userName " +
				
				" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
				" on dictViscera.visceraCode = tblw.visceraCode " +
				" left join CoresSystemSet.dbo.dictViscera as dictViscera2 " +
				"  on dictViscera2.visceraCode = tblw.subVisceraCode " +
				
				" where " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" tblw.sessionId=:sessionId  ";
		}else{
			sql=sql+" tblw.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId=:taskId and sessionType in (2,3,6,7,8))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblw.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblw.visceraName=:visceraName";
		}
		//sql=sql+" order by  tblw.animalCode,tblw.id ";
		sql=sql+" order by  dictViscera.snWeight" +
				" ,dictViscera2.snWeight";
		Query query =getSession().createSQLQuery(sql);
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameter("taskId", taskId);
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			query.setParameter("animalCode", animalCode);
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			query.setParameter("visceraName", visceraName);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByConditionss(String taskId,
			String sessionId, String visceraName, String animalCode) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql = " select tblw.id ,tblw.sessionId, tblw.studyNo , tblw.animalCode , tblw.visceraName , tblw.subVisceraName , tblw.weight , tblw.weightUnit ," +
				" tblw.fixedWeightFlag , tblw.attachedVisceraNames,tblw.operator , tblw.operateTime, tblw.balCode,tbla.realName " +
				" from CoresStudy.dbo.tblVisceraWeight   as tblw " +
				" left join CoresUserPrivilege.dbo.tbluser as tbla " +
				" on tblw.operator = tbla.userName " +
				
				" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
				" on dictViscera.visceraCode = tblw.visceraCode " +
				" left join CoresSystemSet.dbo.dictViscera as dictViscera2 " +
				"  on dictViscera2.visceraCode = tblw.subVisceraCode " +
				
				" where " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" tblw.sessionId=:sessionId  ";
		}else{
			sql=sql+" tblw.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId in (:taskId) and sessionType in (2,3,6,7,8))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblw.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblw.visceraName=:visceraName";
		}
		//sql=sql+" order by  tblw.animalCode,tblw.id ";
		sql=sql+" order by  dictViscera.snWeight" +
				" ,dictViscera2.snWeight";
		Query query =getSession().createSQLQuery(sql);
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			query.setParameter("sessionId", sessionId);
		}else{
			query.setParameterList("taskId", taskList);
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			query.setParameter("animalCode", animalCode);
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			query.setParameter("visceraName", visceraName);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<TblVisceraWeight> getVisceraWeightByStudyNoAndReqNo(String studyNo,Integer reqNo,String visceraName) {
		System.out.println("reqId="+reqNo);
		String sql = " select tblw.id ,tblw.sessionId, tblw.studyNo , tblw.animalCode ,tblw.visceraCode, tblw.visceraName , tblw.subVisceraName , tblw.weight , tblw.weightUnit ," +
		" tblw.fixedWeightFlag ,tblw.balCode, tblw.attachedVisceraNames,tblw.operator , tblw.operateTime, tblw.balCode,tbla.realName " +
		" from CoresStudy.dbo.tblVisceraWeight   as tblw " +
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblw.operator = tbla.userName " +
		" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
		" on dictViscera.visceraCode = tblw.visceraCode " +
		" left join CoresStudy.dbo.tblPathSession as pathSession on tblw.sessionId=pathSession.sessionId" +
		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo = tblw.studyNo and task.taskId like pathSession.taskId" +
		
		" left join CoresSystemSet.dbo.dictViscera as dictViscera2 " +
		"  on dictViscera2.visceraCode = tblw.subVisceraCode " +
		
		" where tblw.studyNo=:studyNo and task.anatomyReqNo=:reqNo ";
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			sql += " and tblw.visceraName=:visceraName";
		}
		sql=sql+" and (pathSession.sessionType in (2,3,6,7) or (pathSession.sessionType=8 and task.visceraFixedWeightFinishSign is not null)) ";//是脏器称重的数据
		
		sql += " order by dictViscera.snWeight" +
				" ,dictViscera2.snWeight";
		System.out.println("weight sql="+sql);
		Query query =  getSession().createSQLQuery(sql)
								.setParameter("studyNo", studyNo)
								.setParameter("reqNo", reqNo);
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			query.setParameter("visceraName", visceraName);
		}
		List<Map<String,Object>> tempTblVisceraWeightList = query.setResultTransformer(new MapResultTransformer()).list();	
		List<TblVisceraWeight> tblVisceraWeightList = new ArrayList<TblVisceraWeight>();
		for(Map<String, Object> map:tempTblVisceraWeightList)
		{
			TblVisceraWeight weight = new TblVisceraWeight();
			weight.setId((String)map.get("id"));
			weight.setSessionId((String)map.get("sessionId"));
			weight.setStudyNo((String)map.get("studyNo"));
			weight.setAnimalCode((String)map.get("animalCode"));
			weight.setVisceraName((String)map.get("visceraName"));
			weight.setSubVisceraName((String)map.get("subVisceraName"));
			weight.setAttachedVisceraNames((String)map.get("attachedVisceraNames"));
			if( null != weight.getSubVisceraName() && !weight.getSubVisceraName().equals("") ){
//				wvisceraName = wvisceraName+" "+subVisceraName;
				weight.setVisceraName(weight.getSubVisceraName());
			}else if(null!= weight.getAttachedVisceraNames()&&!weight.getAttachedVisceraNames().equals("")){
//				wvisceraName = wvisceraName+" "+subVisceraName;
				weight.setVisceraName(weight.getVisceraName()+"("+weight.getAttachedVisceraNames()+")");
			}
			weight.setWeight((String)map.get("weight"));
			weight.setWeightUnit((String)map.get("weightUnit"));
			weight.setFixedWeightFlag((Integer)map.get("fixedWeightFlag"));
			weight.setBalCode((String)map.get("balCode"));
		
			weight.setOperator((String)map.get("realName"));
			weight.setOperateTime((Date) map.get("operateTime"));
		
			tblVisceraWeightList.add(weight);
		}
		
		return tblVisceraWeightList;
		
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraWeightVisceraByStudyNoAndReqNo(String studyNo,Integer reqNo)
	{
		String sql = " select distinct(tblw.visceraName)  " +
		" from CoresStudy.dbo.tblVisceraWeight   as tblw " +
		" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
		" on dictViscera.visceraCode = tblw.visceraCode " +
		" left join CoresStudy.dbo.tblPathSession as pathSession on tblw.sessionId=pathSession.sessionId" +
		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo = tblw.studyNo and task.taskId like pathSession.taskId" +
		"  where tblw.visceraName is not null and tblw.studyNo=:studyNo and task.anatomyReqNo=:reqNo";
		sql=sql+" and (pathSession.sessionType in (2,3,6,7) or (pathSession.sessionType=8 and task.visceraFixedWeightFinishSign is not null))";//是脏器称重的数据
		
		List<Map<String,Object>> tempTblVisceraWeightList =  getSession().createSQLQuery(sql)
		.setParameter("studyNo", studyNo)
		.setParameter("reqNo", reqNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		
		List<Map<String, Object>> tblVisceraWeightList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("visceraName","全部");
		tblVisceraWeightList.add(map1);
		for(Map<String, Object> map:tempTblVisceraWeightList)
		{
			tblVisceraWeightList.add(map);
		}
		
		return tblVisceraWeightList;
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListByTaskIdStudyNo(
			String taskId, String studyNo) {
		String sql = "select weigh.visceraCode,weigh.visceraName,weigh.visceraType,weigh.id, "+
					" 			weigh.partVisceraSeparateWeigh,weigh.attachedVisceraFlag, "+
					" 			dictviscera2.visceraCode as subVisceraCode,dictviscera2.visceraName as subVisceraName ,dictviscera.gender"+
					" from CoresStudy.dbo.tblPathPlanVisceraWeigh as weigh "+
					" 		left join CoresSystemSet.dbo.dictViscera as dictviscera on weigh.visceraCode = dictviscera.visceraCode  "+
					" 	left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = weigh.visceraCode "+
					" where weigh.studyNo = :studyNo and weigh.fixedWeighFlag = 0 "+
					" order by dictviscera.snWeight,dictviscera2.snWeight";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<TblVisceraWeight> getListByTaskId(String taskId) {
		String hql = "select c from TblVisceraWeight c , com.lanen.model.path.TblAnatomyAnimal a " +
				" where	c.studyNo = a.studyNo and c.animalCode = a.animalCode " +
				"  and a.taskId = ? " +
				" order by c.animalCode ";
		List<TblVisceraWeight> list = getSession().createQuery(hql)
												.setParameter(0, taskId)
												.list();
		return list;
	}
	


}
