package com.lanen.service.path;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.TblHistopathCheck;
import com.lanen.model.path.TblPathStudyIndex;
import com.lanen.model.path.TblTissueSliceIndex;
import com.lanen.model.path.TblTissueSliceViscera;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.StringUtil;

@Service
public class TblHistopathCheckServiceImpl extends BaseDaoImpl<TblHistopathCheck> implements TblHistopathCheckService{

//	/**
//	 * 解剖任务service
//	 */
//	@Resource
//	private TblAnatomyTaskService tblAnatomyTaskService;
//	/**
//	 * 组织切片索引
//	 */
//	@Resource
//	private TblTissueSliceIndexService tblTissueSliceIndexService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	@Resource
	private UserService userService;
	@Resource
	private TblPathStudyIndexService tblPathStudyIndexService;
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalByStudyNoAnimalCodeList(
			String studyNo, List<String> animalCodeList) {
		String sql = "select distinct a.animalCode,a.gender,hc.isNoAbnormal,hc3.resultNum"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a"+
					" 	left join CoresStudy.dbo.tblHistopathCheck as hc on hc.studyNo = a.studyNo"+
					" 		and hc.isNoAbnormal = 0  and hc.historyFlag = 0  and hc.animalCode = a.animalCode"+
					" left join ("+
					" 		select hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum"+
					" 		from CoresStudy.dbo.tblHistopathCheck as hc2"+
					" 		where hc2.studyNo = :studyNo and hc2.historyFlag = 0"+
					" 		group by hc2.studyNo,hc2.animalCode"+
					" 	) as hc3 on  hc3.studyNo = a.studyNo  and hc3.animalCode = a.animalCode"+
					" where a.studyNo = :studyNo and a.animalCode in (:animalCodeList)";
		
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setParameterList("animalCodeList", animalCodeList)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalByTissueSliceIndex(
			TblTissueSliceIndex tblTissueSliceIndex) {
		List<Map<String, Object>> listmap = null;
		if(null != tblTissueSliceIndex){
			String indexId = tblTissueSliceIndex.getId();
			String taskId = "";
//			taskId = tblTissueSliceIndex.getTaskId();  
			String sql = "";
			if(tblTissueSliceIndex.getSliceCodeType() != 1){
				
//				select distinct adp.animalCode,adp.gender,hc.isNoAbnormal,hc3.resultNum
//				from CoresStudy.dbo.tblTissueSliceIndex as sIndex left join CoresStudy.dbo.tblTissueSliceScope as scope
//					on sIndex.id = scope.tissueSliceIndexId left join CoresStudy.dbo.tblAnimalDetailDissectPlan as adp
//					on scope.groupId = adp.groupId and sIndex.studyNo = adp.studyNo and(sIndex.gender = 0 or adp.gender = sIndex.gender)
//					INNER JOIN (
//						--任务下对应的所有动物（自溶的除外）
//						select task.taskId,reqAnimal.animalCode
//						from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqAnimal
//							on task.studyNo = reqAnimal.studyNo  and task.anatomyReqNo = reqAnimal.anatomyReqNo  and reqAnimal.cancelFlag = 0
//							left join CoresStudy.dbo.tblAnatomyAnimal as anaAnimal on anaAnimal.studyNo = reqAnimal.studyNo and 
//							task.taskId = anaAnimal.taskId and anaAnimal.animalCode = reqAnimal.animalCode
//						where task.taskId = '20000000050' and ( anaAnimal.autolyzeFlag is null or anaAnimal.autolyzeFlag = 0 )
//					) as r 
//					on adp.animalCode = r.animalCode
//
//					left join CoresStudy.dbo.tblHistopathCheck as hc on hc.taskId = sIndex.taskId and hc.studyNo = sIndex.studyNo
//						and hc.isNoAbnormal = 0  and hc.historyFlag = 0
//				left join (
//						select hc2.taskId,hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum
//						from CoresStudy.dbo.tblHistopathCheck as hc2
//						group by hc2.taskId,hc2.studyNo ,hc2.animalCode
//					) as hc3 on  hc3.taskId = sIndex.taskId and hc3.studyNo = sIndex.studyNo  and hc3.animalCode = adp.animalCode
//				where sIndex.id = '20000000009' 
				
				sql= "select distinct adp.animalCode,adp.gender,hc.isNoAbnormal,hc3.resultNum"+
					" from CoresStudy.dbo.tblTissueSliceIndex as sIndex left join CoresStudy.dbo.tblTissueSliceScope as scope"+
					" 	on sIndex.id = scope.tissueSliceIndexId left join CoresStudy.dbo.tblAnimalDetailDissectPlan as adp"+
					" 	on scope.groupId = adp.groupId and sIndex.studyNo = adp.studyNo and(sIndex.gender = 0 or adp.gender = sIndex.gender)"+
					" 	INNER JOIN ("+
//		--任务下对应的所有动物（自溶的除外）
					" 		select task.taskId,reqAnimal.animalCode"+
					" 		from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqAnimal"+
					" 			on task.studyNo = reqAnimal.studyNo  and task.anatomyReqNo = reqAnimal.anatomyReqNo  and reqAnimal.cancelFlag = 0 "+
					" 			left join CoresStudy.dbo.tblAnatomyAnimal as anaAnimal on anaAnimal.studyNo = reqAnimal.studyNo and "+
					" 			task.taskId = anaAnimal.taskId and anaAnimal.animalCode = reqAnimal.animalCode"+
					" 		where task.taskId = :taskId and ( anaAnimal.autolyzeFlag is null or anaAnimal.autolyzeFlag = 0 )"+
					" 	) as r "+
					" 	on adp.animalCode = r.animalCode"+

					" 	left join CoresStudy.dbo.tblHistopathCheck as hc on hc.taskId = sIndex.taskId and hc.studyNo = sIndex.studyNo"+
					" 		and hc.isNoAbnormal = 0  and hc.historyFlag = 0 " +
					" left join (" +
					" 		select hc2.taskId,hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum" +
					" 		from CoresStudy.dbo.tblHistopathCheck as hc2" +
					" 		group by hc2.taskId,hc2.studyNo,hc2.animalCode " +
					" 	) as hc3 on  hc3.taskId = sIndex.taskId and hc3.studyNo = sIndex.studyNo  and hc3.animalCode = adp.animalCode"+
					" where sIndex.id = :indexId ";
				
				listmap = getSession().createSQLQuery(sql)
										.setParameter("indexId", indexId)
										.setParameter("taskId", taskId)
										.setResultTransformer(new MapResultTransformer())
										.list();
			}else{
//				select distinct sn.animalCode, 0 as gender,hc.isNoAbnormal,hc3.resultNum
//				from CoresStudy.dbo.tblTissueSliceIndex as sIndex left join CoresStudy.dbo.tblTissueSliceSn as sn
//					on sIndex.id = sn.tissueSliceIndexId left join CoresStudy.dbo.tblHistopathCheck as hc 
//					on hc.taskId = sIndex.taskId and hc.studyNo = sIndex.studyNo and hc.isNoAbnormal = 0 and hc.historyFlag = 0
//				left join (
//						select hc2.taskId,hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum
//						from CoresStudy.dbo.tblHistopathCheck as hc2
//						group by hc2.taskId,hc2.studyNo,hc2.animalCode 
//					) as hc3 on  hc3.taskId = sIndex.taskId and hc3.studyNo = sIndex.studyNo  and hc3.animalCode = sn.animalCode
//				where sIndex.id = '20000000010' 
				sql = "select distinct sn.animalCode, 0 as gender,hc.isNoAbnormal,hc3.resultNum"+
				" from CoresStudy.dbo.tblTissueSliceIndex as sIndex left join CoresStudy.dbo.tblTissueSliceSn as sn"+
				" 	on sIndex.id = sn.tissueSliceIndexId left join CoresStudy.dbo.tblHistopathCheck as hc "+
				" 	on hc.taskId = sIndex.taskId and hc.studyNo = sIndex.studyNo and hc.isNoAbnormal = 0 and hc.historyFlag = 0" +
				" left join ("+
				" 		select hc2.taskId,hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum"+
				" 		from CoresStudy.dbo.tblHistopathCheck as hc2"+
				" 		group by hc2.taskId,hc2.studyNo,hc2.animalCode "+
				" 	) as hc3 on  hc3.taskId = sIndex.taskId and hc3.studyNo = sIndex.studyNo and hc3.animalCode = sn.animalCode"+
				
				" where sIndex.id = :indexId ";
				listmap = getSession().createSQLQuery(sql)
									.setParameter("indexId", indexId)
									.setResultTransformer(new MapResultTransformer())
									.list();
			}
		}
		return listmap;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeVisceraByTissueSliceSnIdListAnimalCode(
			List<String> tissueSliceSnIdList, String studyNo,
			String animalCode, Integer gender) {
		String sql = "select b.animalCode,b.sliceCode,"+
					" 	b.tissueSliceVisceraId as sliceVisceraId,sv.visceraType,sv.visceraCode,sv.visceraFixedRecordId,"+
				  
					" 				(case when isnull(sv.visceraCode,'')= '' then ''  "+
					" 				when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 					when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 					else '' end "+
					" 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 						+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 						)    as visceraOrTissueName,"+
					" 			sv.visceraName,sv.subVisceraCode,sv.subVisceraName,"+
					" 			sv.specialFlag,sv.anatomyPosCode,sv.anatomyPos,sv.anatomyFindingCode,"+
					" 		sv.anatomyFindingFlag,sv.anatomyFingding,sv.bodySurfacePos,sv.isHandwork,hc2.tissueSliceVisceraRecordId as sueSliceVisceraRecordId_hascheck" +
					" ,hc3.anatomyfinding"+
					//TODO 排除表加入子脏器问题
					" from CoresStudy.dbo.tissueSliceBatch(:studyNo) as b left join CoresStudy.dbo.tblTissueSliceViscera"+
					" 	as sv on b.tissueSliceVisceraId = sv.id left join CoresSystemSet.dbo.dictViscera as dv"+
					" 				on sv.visceraCode = dv.visceraCode and (dv.gender =0 or dv.gender = :gender) "+
					" 				left join (select distinct hc.tissueSliceVisceraRecordId "+
					" 							from CoresStudy.dbo.tblHistopathCheck as hc  "+
					" 							where hc.studyNo = :studyNo and hc.animalCode = :animalCode and hc.historyFlag = 0"+
					" 							)as hc2 "+
					" 			on  b.tissueSliceVisceraId = hc2.tissueSliceVisceraRecordId" +
					" left join(" +
					" 						select distinct sv.visceraCode,sv.subVisceraCode,sv.anatomyFindingCode,sv.bodySurfacePos" +
					" 							,isnull(sv.visceraCode,'')+isnull(sv.anatomyFindingCode,'')+isnull(sv.bodySurfacePos,'') as anatomyfinding" +
					" 					from CoresStudy.dbo.tblHistopathCheck as hc  join CoresStudy.dbo.tblTissueSliceViscera as sv" +
					" 						on hc.tissueSliceVisceraRecordId = sv.id and sv.appendFlag = 1" +
					" 					where hc.studyNo = :studyNo and hc.animalCode = :animalCode and hc.historyFlag = 0" +
					" 			) as hc3 on isnull(sv.visceraCode,'')= isnull(hc3.visceraCode,'') and isnull(sv.subVisceraCode,'') = " +
					" 				isnull(hc3.subVisceraCode,'') and isnull(sv.anatomyFindingCode,'')= isnull(hc3.anatomyFindingCode,'')" +
					" 				and isnull(sv.bodySurfacePos,'') = isnull(hc3.bodySurfacePos,'')"+
					" where sv.tissueSliceSnId in (:tissueSliceSnIdList) and b.animalCode = :animalCode "+
					" 	and (dv.visceraCode is not null or isnull(sv.visceraCode,'') = '') " +
					" order by case when b.sliceCode like '%g-_' then 101 "+
					" 	      when Substring(b.sliceCode,1,1) = 'T' then 200+ cast(Substring(b.sliceCode,2,len(b.sliceCode)-1) as int )"+
					" 		  else cast(b.sliceCode as int ) end ,b.sliceCode,b.animalCode,b.tissueSliceVisceraId";
		
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setParameter("gender", gender)
				.setParameterList("tissueSliceSnIdList", tissueSliceSnIdList)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeVisceraByTissueSliceIndex(
			TblTissueSliceIndex tblTissueSliceIndex, String animalCode,
			Integer gender) {
		List<Map<String, Object>> listmap = null;
		if(null != tblTissueSliceIndex && null != animalCode){
			String indexId = tblTissueSliceIndex.getId();
//			String taskId = tblTissueSliceIndex.getTaskId();
			String studyNo = tblTissueSliceIndex.getStudyNo();
			String sql = "";
			if(tblTissueSliceIndex.getSliceCodeType() != 1){
				
//				select sn.animalCode,sn.sliceCode,viscera.id as tissueSliceVisceraRecordId,viscera.visceraType,viscera.visceraCode,viscera.visceraFixedRecordId,
//				  
//				(case when isnull(viscera.visceraCode,'')= '' then ''  
//					when isnull(viscera.subVisceraCode,'') = '' then viscera.visceraName+' '
//						when isnull(viscera.subVisceraCode,'') != '' then viscera.subVisceraName+' ' 
//						else '' end 
//					+ case when isnull(viscera.bodySurfacePos,'') = '' then '' else (viscera.bodySurfacePos+' ') end
//							+case when isnull(viscera.anatomyPos,'') = '' then '' else (viscera.anatomyPos+' ') end
//						+case when isnull(viscera.anatomyFingding,'') = '' then '' else (viscera.anatomyFingding+' ') end
//							)    as visceraOrTissueName,
//				viscera.visceraName,viscera.subVisceraCode,viscera.subVisceraName,
//				viscera.specialFlag,viscera.anatomyPosCode,viscera.anatomyPos,viscera.anatomyFindingCode,
//			viscera.anatomyFindingFlag,viscera.anatomyFingding,viscera.bodySurfacePos,viscera.isHandwork,hc2.tissueSliceVisceraRecordId as sueSliceVisceraRecordId_hascheck
//					
//		from CoresStudy.dbo.tblTissueSliceSn as sn  left join CoresStudy.dbo.tblTissueSliceViscera as viscera
//				on sn.id = viscera.tissueSliceSnId  
//				inner join CoresSystemSet.dbo.dictViscera as dv
//				on viscera.visceraCode = dv.visceraCode and (dv.gender =0 or dv.gender = 1)
//			left join (select distinct hc.tissueSliceVisceraRecordId from CoresStudy.dbo.tblHistopathCheck as hc  where hc.studyNo = 'zz07' and hc.animalCode = '10003' and hc.historyFlag = 0)as hc2 
//			on  viscera.id = hc2.tissueSliceVisceraRecordId
//
//		where sn.tissueSliceIndexId = '20000000031' 
//			and not exists( 
//				select c.visceraCode
//				from 
//				--CoresStudy.dbo.TblAnatomyCheck 
//				(
//					--解剖所见（编辑后最终结果）
//					select se.taskId,tblac.id, tblac.studyNo,tblac.animalCode ,tblac.visceraType,tblac.visceraCode,tblac.visceraName,tblac.subVisceraCode,tblac.subVisceraName,
//						tblac.specialFlag,tblac.anatomyPosCode,tblac.anatomyPos,tblac.anatomyFindingCode,tblac.anatomyFindingFlag,tblac.anatomyFingding,
//						tblac.bodySurfacePos,tblac.autolyzaFlag			 
//					from CoresStudy.dbo.tblPathSession as se left join  CoresStudy.dbo.TblAnatomyCheck  as tblac on se.sessionId = tblac.sessionId
//						and tblac.autolyzaFlag = 0	
//						left join CoresStudy.dbo.tblAnatomyCheckEdit as edit on tblac.id = edit.oldId and edit.delFlag = 0
//					where se.taskId ='20000000074' and tblac.sessionId is not null and edit.id is null
//  
//					union
//
//					select edit2.taskId,null as id,edit2.studyNo,edit2.animalCode ,edit2.visceraType,edit2.visceraCode,edit2.visceraName,edit2.subVisceraCode,edit2.subVisceraName,
//						edit2.specialFlag,edit2.anatomyPosCode,edit2.anatomyPos,edit2.anatomyFindingCode,edit2.anatomyFindingFlag,edit2.anatomyFingding,
//						edit2.bodySurfacePos,edit2.autolyzaFlag		
//					from CoresStudy.dbo.tblAnatomyCheckedit	as edit2 	
//					where edit2.taskId = '20000000074' and edit2.delFlag = 0 	and edit2.autolyzaFlag = 0
//					and (edit2.editType = 1 or edit2.editType = 2 ) 
//				)
//				
//				
//				as c
//				where c.studyNo = 'zz07' and c.animalCode ='10003'and c.autolyzaFlag > 0 and c.visceraCode is not null 
//					and  c.visceraCode = viscera.visceraCode and (isnull(c.subVisceraCode,'') = isnull(viscera.subVisceraCode,'') 
//					or c.subVisceraCode is null)
//				)
//			and not exists( 
//				select c.visceraCode
//				from CoresStudy.dbo.tblVisceraMissing as c
//				where c.studyNo = 'zz07' and c.animalCode ='10003' and c.visceraCode is not null 
//					and  c.visceraCode = viscera.visceraCode and (isnull(c.subVisceraCode,'') = isnull(viscera.subVisceraCode,'') 
//					or c.subVisceraCode is null)
//				)
//		order by  cast(REPLACE(sn.sliceCode,'T','') as int )

				
				sql= "select viscera.id as sliceVisceraId,sn.animalCode,sn.sliceCode,viscera.id as tissueSliceVisceraRecordId," +
						" viscera.visceraType,viscera.visceraCode,viscera.visceraFixedRecordId,"+
							  
						"  		(case when isnull(viscera.visceraCode,'')= '' then ''  "+
						" 			when isnull(viscera.subVisceraCode,'') = '' then viscera.visceraName+' '"+
						" 				when isnull(viscera.subVisceraCode,'') != '' then viscera.subVisceraName+' ' "+
						" 				else '' end "+
						" 			+ case when isnull(viscera.bodySurfacePos,'') = '' then '' else (viscera.bodySurfacePos+' ') end"+
						" 					+case when isnull(viscera.anatomyPos,'') = '' then '' else (viscera.anatomyPos+' ') end"+
						" 				+case when isnull(viscera.anatomyFingding,'') = '' then '' else (viscera.anatomyFingding+' ') end"+
						" 					)    as visceraOrTissueName,"+
						" 	viscera.visceraName,viscera.subVisceraCode,viscera.subVisceraName,"+
						" 		viscera.specialFlag,viscera.anatomyPosCode,viscera.anatomyPos,viscera.anatomyFindingCode,"+
						" 	viscera.anatomyFindingFlag,viscera.anatomyFingding,viscera.bodySurfacePos,viscera.isHandwork," +
						" hc2.tissueSliceVisceraRecordId as sueSliceVisceraRecordId_hascheck"+
	
						" 		from CoresStudy.dbo.tblTissueSliceSn as sn  left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
						" 		on sn.id = viscera.tissueSliceSnId  "+
						" 		inner join CoresSystemSet.dbo.dictViscera as dv"+
						" 		on viscera.visceraCode = dv.visceraCode and (dv.gender =0 or dv.gender = :gender)"+
						" 		left join (select distinct hc.tissueSliceVisceraRecordId " +
						"					from CoresStudy.dbo.tblHistopathCheck as hc  " +
						"					where hc.studyNo = :studyNo and hc.animalCode = :animalCode and hc.historyFlag = 0" +
						"				)as hc2"+ 
						"		on  viscera.id = hc2.tissueSliceVisceraRecordId"+
						" where sn.tissueSliceIndexId = :indexId "+
						" 	and not exists( "+
						" 		select c.visceraCode"+
						" 		from " +
//						"				CoresStudy.dbo.TblAnatomyCheck" +
						"		(" +
//						"			--解剖所见（编辑后最终结果）" +
						"			select se.taskId,tblac.id, tblac.studyNo,tblac.animalCode ,tblac.visceraType,tblac.visceraCode,tblac.visceraName,tblac.subVisceraCode,tblac.subVisceraName," +
						"				tblac.specialFlag,tblac.anatomyPosCode,tblac.anatomyPos,tblac.anatomyFindingCode,tblac.anatomyFindingFlag,tblac.anatomyFingding," +
						"				tblac.bodySurfacePos,tblac.autolyzaFlag			 " +
						"			from CoresStudy.dbo.tblPathSession as se left join  CoresStudy.dbo.TblAnatomyCheck  as tblac on se.sessionId = tblac.sessionId" +
						"				and tblac.autolyzaFlag = 0	" +
						"				left join CoresStudy.dbo.tblAnatomyCheckEdit as edit on tblac.id = edit.oldId and edit.delFlag = 0" +
						"			where se.taskId ='20000000074' and tblac.sessionId is not null and edit.id is null" +
				        "" +
						"			union" +
				        "" +
						"			select edit2.taskId,null as id,edit2.studyNo,edit2.animalCode ,edit2.visceraType,edit2.visceraCode,edit2.visceraName,edit2.subVisceraCode,edit2.subVisceraName," +
						"				edit2.specialFlag,edit2.anatomyPosCode,edit2.anatomyPos,edit2.anatomyFindingCode,edit2.anatomyFindingFlag,edit2.anatomyFingding," +
						"				edit2.bodySurfacePos,edit2.autolyzaFlag		" +
						"			from CoresStudy.dbo.tblAnatomyCheckedit	as edit2 	" +
						"			where edit2.taskId = '20000000074' and edit2.delFlag = 0 	and edit2.autolyzaFlag = 0" +
						"			and (edit2.editType = 1 or edit2.editType = 2 ) " +
						"		)		" +
						" as c"+
						" 		where c.studyNo = :studyNo and c.animalCode =:animalCode and c.autolyzaFlag > 0 and c.visceraCode is not null "+
						" 			and  c.visceraCode = viscera.visceraCode and (isnull(c.subVisceraCode,'') = isnull(viscera.subVisceraCode,'') "+
						" 			or c.subVisceraCode is null)"+
						" 		)"+
						" 	and not exists( "+
						" 		select c.visceraCode"+
						" 		from CoresStudy.dbo.tblVisceraMissing as c"+
						" 		where c.studyNo = :studyNo and c.animalCode =:animalCode and c.visceraCode is not null "+
						" 			and  c.visceraCode = viscera.visceraCode and (isnull(c.subVisceraCode,'') = isnull(viscera.subVisceraCode,'') "+
						" 			or c.subVisceraCode is null)"+
						" 		) " +
						" order by  cast(REPLACE(sn.sliceCode,'T','') as int )";
				
				listmap = getSession().createSQLQuery(sql)
										.setParameter("indexId", indexId)
										.setParameter("studyNo", studyNo)
										.setParameter("animalCode", animalCode)
										.setParameter("gender", gender)
										.setResultTransformer(new MapResultTransformer())
										.list();
			}else{

				sql = "select viscera.id as sliceVisceraId,sn.animalCode,sn.sliceCode,viscera.id as tissueSliceVisceraRecordId,viscera.visceraType,viscera.visceraCode,visceraFixedRecordId,"+
				"					  "+
			"				 	(case when isnull(viscera.visceraCode,'')= '' then ''  "+
			"				 			when isnull(viscera.subVisceraCode,'') = '' then viscera.visceraName+' '"+
			"				 			when isnull(viscera.subVisceraCode,'') != '' then viscera.subVisceraName+' ' "+
			"				 			else '' end "+
			"				 		+ case when isnull(viscera.bodySurfacePos,'') = '' then '' else (viscera.bodySurfacePos+' ') end"+
			"				 		  		+case when isnull(viscera.anatomyPos,'') = '' then '' else (viscera.anatomyPos+' ') end"+
			"			 		  		+case when isnull(viscera.anatomyFingding,'') = '' then '' else (viscera.anatomyFingding+' ') end"+
			"				 		  		)    as visceraOrTissueName,"+
			"					viscera.visceraName,viscera.subVisceraCode,viscera.subVisceraName,"+
			"				 	viscera.specialFlag,viscera.anatomyPosCode,viscera.anatomyPos,viscera.anatomyFindingCode,"+
			"					viscera.anatomyFindingFlag,viscera.anatomyFingding,viscera.bodySurfacePos,viscera.isHandwork," +
			" hc2.tissueSliceVisceraRecordId as sueSliceVisceraRecordId_hascheck"+
			
			"				 from CoresStudy.dbo.tblTissueSliceSn as sn  left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
			"				 	on sn.id = viscera.tissueSliceSnId  "+
			" 		left join (select distinct hc.tissueSliceVisceraRecordId " +
			"					from CoresStudy.dbo.tblHistopathCheck as hc  " +
			"					where hc.studyNo = :studyNo and hc.animalCode = :animalCode and hc.historyFlag = 0" +
			"				)as hc2"+ 
			"		on  viscera.id = hc2.tissueSliceVisceraRecordId"+
				" where sn.tissueSliceIndexId = :indexId and sn.animalCode = :animalCode" +
							" order by sn.animalCode";
				listmap = getSession().createSQLQuery(sql)
									.setParameter("indexId", indexId)
									.setParameter("studyNo", studyNo)
									.setParameter("animalCode", animalCode)
									.setResultTransformer(new MapResultTransformer())
									.list();
			}
		}
		return listmap;
	}

	public Json checkRecord(String studyNo, String animalCode,
			String sliceVisceraId, String visceraCode, String subVisceraCode) {
		Json json  = new Json();
		
		List<String> sliceVisceraIdList = getTissueSliceIdList(studyNo,animalCode,sliceVisceraId);
		
		
		String sql = "select count(id)"+
					" from CoresStudy.dbo.tblHistopathCheck"+
					" where studyNo = :studyNo and animalCode = :animalCode " +
					" and tissueSliceVisceraRecordId in (:sliceVisceraIdList) and historyFlag = 0  " +
					" and isnull(visceraCode,'') = :visceraCode and " +
					" ( isnull(subVisceraCode,'') = :subVisceraCode or isnull(subVisceraCode,'') = '' or :subVisceraCode = '' )";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setParameter("animalCode", animalCode)
									.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
									.setParameter("visceraCode", visceraCode != null ? visceraCode.trim():"")
									.setParameter("subVisceraCode", subVisceraCode != null ? subVisceraCode.trim():"")
									.uniqueResult();
		if(count > 0){
			sql = "select count(id)"+
					" from CoresStudy.dbo.tblHistopathCheck"+
					" where studyNo = :studyNo and animalCode = :animalCode and isNoAbnormal = 1 " +
					" and tissueSliceVisceraRecordId in (:sliceVisceraIdList) and historyFlag = 0 " +
					"  and isnull(visceraCode,'') = :visceraCode and " +
					" ( isnull(subVisceraCode,'') = :subVisceraCode or isnull(subVisceraCode,'') = '' or :subVisceraCode = '' )";
			Integer count2 = (Integer) getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameter("animalCode", animalCode)
										.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
										.setParameter("visceraCode", visceraCode != null ? visceraCode.trim():"")
										.setParameter("subVisceraCode", subVisceraCode != null ? subVisceraCode.trim():"")
										.uniqueResult();
			if(count2 > 0){
				json.setMsg("1");
			}else{
				json.setMsg("0");
			}
		}else{
			json.setSuccess(true);
		}
		
		return json;
	}
	
	/**获取对应专题下切片脏器Id对应的脏器或组织在   该专题下的所有切片脏器Id列表（包括追加的，追加的专题和动物编号都得对应）
	 * @param studyNo
	 * @param animalCode
	 * @param tissueSliceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String> getTissueSliceIdList(String studyNo,String animalCode,String tissueSliceId){
		List<String> list = null;
		String sql = "select slicev2.id"+
					" from CoresStudy.dbo.tblTissueSliceViscera as slicev left join CoresStudy.dbo.tblTissueSliceViscera "+
					" 	as slicev2 on slicev2.appendStudyNo = :studyNo and slicev2.appendAnimalCode = :animalCode "+
					" 	and isnull(slicev.visceraCode,'') = isnull(slicev2.visceraCode,'') and isnull(slicev.subVisceraCode,'')"+
					" 	= isnull(slicev2.subVisceraCode,'') and isnull(slicev.anatomyFindingCode,'') = isnull(slicev2.anatomyFindingCode,'')"+
					" 	and isnull(slicev.bodySurfacePos,'') = isnull(slicev2.bodySurfacePos,'') "+
					" where slicev.id = :tissueSliceId  and slicev2.id is not null "+
					" union"+
					" select sliceCode.tissueSliceVisceraId as id"+
					" from CoresStudy.dbo.tblTissueSliceViscera as slicev  left join "+
					" 	CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode on "+
					" 	isnull(slicev.visceraCode,'') = isnull(sliceCode.visceraCode,'') and isnull(slicev.subVisceraCode,'')"+
					" 	= isnull(sliceCode.subVisceraCode,'') and isnull(slicev.anatomyFindingCode,'') = isnull(sliceCode.anatomyFindingCode,'')"+
					" 	and isnull(slicev.bodySurfacePos,'') = isnull(sliceCode.bodySurfacePos,'')"+
					" where slicev.id = :tissueSliceId and sliceCode.tissueSliceVisceraId is not null ";
		list = getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.setParameter("animalCode", animalCode)
							.setParameter("tissueSliceId", tissueSliceId)
							.list();
		return list;
	}

	public void saveOne(TblHistopathCheck tblHistopathCheck) {
		tblHistopathCheck.setId(getKey());
		tblHistopathCheck.setOperateTime(new Date());
		getSession().save(tblHistopathCheck);
	}

	@SuppressWarnings("unchecked")
	public List<TblHistopathCheck> getListByStudyNoAnimalCodeSliceVisceraId(
			String studyNo, String animalCode, String sliceVisceraId) {
		
		List<String> sliceVisceraIdList = getTissueSliceIdList(studyNo,animalCode,sliceVisceraId);
		
		String hql = "from TblHistopathCheck "+
					" where studyNo = :studyNo and animalCode = :animalCode and " +
					" tissueSliceVisceraRecordId in (:sliceVisceraIdList) and  historyFlag = 0";
		List<TblHistopathCheck> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblHistopathCheck> getListByStudyNoAnimalCodeSliceVisceraId2(
			String studyNo, String animalCode, String sliceVisceraId) {
		List<String> sliceVisceraIdList = getTissueSliceIdList(studyNo,animalCode,sliceVisceraId);
		
		String hql = "from TblHistopathCheck "+
		" where studyNo = :studyNo and animalCode = :animalCode and " +
		" tissueSliceVisceraRecordId in (:sliceVisceraIdList) and  (historyFlag = 0 or historyFlag = 2)";
		List<TblHistopathCheck> list = getSession().createQuery(hql)
												.setParameter("studyNo", studyNo)
												.setParameter("animalCode", animalCode)
												.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
												.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNoSortMethord(String studyNo, int sortMethod){
		String sql = "select histopathCheck.id,histopathCheck.histopathReviewOpinion,histopathCheck.animalCode,histopathCheck.tissueSliceVisceraRecordId,histopathCheck.isNoAbnormal,histopathCheck.tumorFlag,"+
					" 	histopathCheck.metastasisFlag,histopathCheck.histoPos,histopathCheck.lesionFinding,histopathCheck.primaryViscera,"+
					" 	histopathCheck.primaryTumor,convert(varchar(10),histopathCheck.operateTime,120) as operateTime, " +
					" case when isnull(histopathCheck.visceraCode,'') != '' then " +
					"					(" +
					"						case when isnull(histopathCheck.subVisceraCode,'') != '' then histopathCheck.subVisceraName+' '" +
					"						 else histopathCheck.visceraName end " +
					"					 )"+
					" 					else  "+
					" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
					" 						when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
					" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
					" 						else '' end "+
					" 						+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
					" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
					" 						+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
					" 					) end   as visceraOrTissueName" +
					" ,histopathCheck.historyFlag," +
					" histopathCheck.level,histopathCheck.tumorNum,histopathCheck.tumorPos,convert(varchar(10),histopathCheck.tumorOccurDate,120) as tumorOccurDate,"+
					" 		histopathCheck.tumorCharacter,histopathCheck.remark,isnull(histopathCheck.visceraCode,'') as visceraCode"+
					" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
					" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
					" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode"+
					" where histopathCheck.studyNo = ? and  ( histopathCheck.historyFlag = 0 or histopathCheck.historyFlag = 2)";
		if(sortMethod == 1){
			sql = sql + " order by animalCode,dv.sn";
		}else{
			sql = sql + " order by dv.sn,animalCode";
		}
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
													.setParameter(0, studyNo)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNoAnimalCodeVisceraCode(
			String studyNo, String animalCode, String visceraCode) {

//		select 	histopathCheck.histoPos,histopathCheck.lesionFinding,
//			convert(varchar(10),histopathCheck.operateTime,120) as operateTime,
//						(case when isnull(sliceViscera.visceraCode,'')= '' then ''  
//						when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '
//							when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' 
//							else '' end 
//						+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end
//								+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end
//							+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end
//								)    as visceraOrTissueName,
//			sliceViscera.visceraCode,sliceViscera.visceraName
//		from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera
//			on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id 
//			left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode
//		where histopathCheck.studyNo = 'test' and histopathCheck.animalCode = '2211'  and isNoAbnormal = 0 and tumorFlag = 2  and historyFlag = 0
//		order by dv.sn
		String sql = "select "+
					" 	histopathCheck.histoPos,histopathCheck.lesionFinding,"+
					" 	convert(varchar(10),histopathCheck.operateTime,120) as operateTime,"+
					" 				(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
					" 				when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
					" 					when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
					" 					else '' end "+
					" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
					" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
					" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
					" 						)    as visceraOrTissueName," +
					" 	sliceViscera.visceraCode,sliceViscera.visceraName"+
					" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
					" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
					" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode"+
					" where histopathCheck.studyNo = :studyNo and histopathCheck.animalCode = :animalCode " +
					"   and ( isnull(:visceraCode,'') = '' or sliceViscera.visceraCode != :visceraCode ) " +
					"  and isNoAbnormal = 0 and tumorFlag = 2  and historyFlag = 0 "+
					" order by dv.sn";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setParameter("visceraCode", visceraCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getReferMapListByStudyNoAnimalCodeVisceraCode(
			String studyNo, String animalCode, String visceraCode) {
//	select 	histopathCheck.id,histopathCheck.animalCode, histopathCheck.tumorFlag,histopathCheck.metastasisFlag,
//		histopathCheck.histoPos,histopathCheck.lesionFinding,
//		convert(varchar(10),histopathCheck.operateTime,120) as operateTime,
//					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  
//					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '
//						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' 
//						else '' end 
//					+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end
//							+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end
//						+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end
//							)    as visceraOrTissueName,
//		sliceViscera.visceraCode,sliceViscera.visceraName
//	from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera
//		on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id 
//		left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode
//	where histopathCheck.studyNo = 'test' and histopathCheck.animalCode != '2212'  and isNoAbnormal = 0 and metastasisFlag = 0  and historyFlag = 0
//			and (isnull(sliceViscera.visceraCode,'') = isnull('50000000126',''))
//	order by histopathCheck.animalCode,dv.sn
		String sql = "select histopathCheck.id,histopathCheck.animalCode, histopathCheck.tumorFlag,histopathCheck.metastasisFlag,	" +
				" histopathCheck.histoPos,histopathCheck.lesionFinding,"+
					" convert(varchar(10),histopathCheck.operateTime,120) as operateTime,"+
					" 						(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
					" 	when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
					" 		when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
					" 		else '' end "+
					" 	+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
					" 			+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
					" 		+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
					" 			)    as visceraOrTissueName,"+
					" 			sliceViscera.visceraCode,sliceViscera.visceraName"+
					" 		from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
					" 			on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
					" 			left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode"+
					" 		where histopathCheck.studyNo = :studyNo and histopathCheck.animalCode != :animalCode  and isNoAbnormal = 0 and metastasisFlag = 0  and historyFlag = 0 "+
					" 				and (isnull(sliceViscera.visceraCode,'') = isnull(:visceraCode,''))"+
					" 		order by histopathCheck.animalCode,dv.sn";
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setParameter("visceraCode", visceraCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}


	public void saveOne(String id, int tumorNum, String operator) {
		TblHistopathCheck tblHistopathCheck = new TblHistopathCheck();
		tblHistopathCheck .setId(getKey());
		tblHistopathCheck.setOperator(operator);
		tblHistopathCheck.setOperateTime(new Date());
		getSession().save(tblHistopathCheck);
		
	}

	public void saveOne(String referId, TblHistopathCheck tblHistopathCheck) {
		tblHistopathCheck .setId(getKey());
		tblHistopathCheck.setOperateTime(new Date());
		tblHistopathCheck.setRefId(referId);
		TblHistopathCheck old = this.getById(referId);
		
		tblHistopathCheck.setIsNoAbnormal(old.getIsNoAbnormal());
		tblHistopathCheck.setTumorFlag(old.getTumorFlag());
		tblHistopathCheck.setMetastasisFlag(old.getMetastasisFlag());
		tblHistopathCheck.setHistoPosCode(old.getHistoPosCode());
		tblHistopathCheck.setHistoPos(old.getHistoPos());
		tblHistopathCheck.setLesionFinding(old.getLesionFinding());
		tblHistopathCheck.setLesionFindingCode(old.getLesionFindingCode());
		tblHistopathCheck.setLevel(old.getLevel());
		tblHistopathCheck.setRemark(old.getRefId());
		
		tblHistopathCheck.setTumorPos(old.getTumorPos());
		tblHistopathCheck.setTumorCharacter(old.getTumorCharacter());
		tblHistopathCheck.setTumorOccurDate(old.getTumorOccurDate());
		
		getSession().save(tblHistopathCheck);
		
	}

	public void updateFreqCount() {
		Query query = getSession().createSQLQuery("{Call updateFreqCount_histopath()}");
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClinicalDataByStudyNoAnimalCodeTestItemTestDate(
			String studyNo, String animalCode, Integer testItem, String testDate) {
//		select testIndex,testIndexAbbr,testData,testIndexUnit
//		from CoresStudy.dbo.tblClinicalTestData left join 
//			CoresSystemSet.dbo.dictBioChem as dict
//			--CoresSystemSet.dbo.dictHemat as dict
//			--CoresSystemSet.dbo.dictBloodCoag as dict
//			--CoresSystemSet.dbo.dictUrine as dict
//			on dict.abbr = testIndexAbbr
//		where studyNo = '2013-030-01-14' and animalCode = '1026'  and es = 1 and validFlag = 1 
//				and testItem = 1 and convert(varchar(10),collectionTime,120) = '2015-05-04'
//		order by dict.orderNo
		String sql = "select testIndex,testIndexAbbr,testData,testIndexUnit"+
					" from CoresStudy.dbo.tblClinicalTestData left join ";
		switch (testItem) {
		case 1:
			sql = sql+	" 	CoresSystemSet.dbo.dictBioChem as dict";
			break;
		case 2:
			sql = sql+	" 	CoresSystemSet.dbo.dictHemat as dict";
			break;
		case 3:
			sql = sql+	" 	CoresSystemSet.dbo.dictBloodCoag as dict";
			break;
		case 4:
			sql = sql+	" 	CoresSystemSet.dbo.dictUrine as dict";
			break;

		default:
			break;
		}
		
					
		sql = sql+	" 	on dict.abbr = testIndexAbbr"+
					" where studyNo = :studyNo and animalCode = :animalCode  and es = 1 and validFlag = 1 "+
					" 		and testItem = :testItem and convert(varchar(10),collectionTime,120) = :testDate "+
					" order by dict.orderNo";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setParameter("testItem", testItem)
				.setParameter("testDate", testDate)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClinicalDataByStudyNoAnimalCodeTestItemTestDate(
			String studyNo, String animalCode) {
//		select distinct testItem,convert(varchar(10),collectionTime,120) as testDate
//		from CoresStudy.dbo.tblClinicalTestData 
//		where studyNo = '2013-030-01-14' and animalCode = '1026'  and es = 1 and validFlag = 1
//		order by convert(varchar(10),collectionTime,120)
		String sql = "select distinct testItem,convert(varchar(10),collectionTime,120) as testDate"+
					" from CoresStudy.dbo.tblClinicalTestData "+
					" where studyNo = :studyNo and animalCode = :animalCode  and es = 1 and validFlag = 1"+
					" order by convert(varchar(10),collectionTime,120)";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
					.setParameter("studyNo", studyNo)
					.setParameter("animalCode", animalCode)
					.setResultTransformer(new MapResultTransformer())
					.list();
		return maplist;
	}

	public Json deleteOne(String id, String reason, String operator) {
		Json json = new Json();
		
		TblHistopathCheck histopathCheck = this.getById(id);
		if(null != histopathCheck){
			int historyFlag = 0;//删除失败
//			TblAnatomyTask task = tblAnatomyTaskService.getById(histopathCheck.getTaskId());
			TblPathStudyIndex pathStudy = tblPathStudyIndexService.getByStudyNo(histopathCheck.getStudyNo());
			//镜检完毕/提交签字
			String histopathCheckFinishSign = null;
			if(null != pathStudy){
				histopathCheckFinishSign = pathStudy.getHistopathCheckFinishSign();
			}
			if(null == histopathCheckFinishSign || "".equals(histopathCheckFinishSign)){
				historyFlag = 1;//提交复查前删除
			}else{
//				int histopathReviewRequirement = task.getHistopathReviewRequirement();
//				if(histopathReviewRequirement == 1){
//					//0：未提交，1：提交，2：完成复审，3：完成复审修改（最终确认）
//					int histopathReviewFlag = task.getHistopathReviewFlag();
//					if(histopathReviewFlag == 2){
//						historyFlag = 2;//复核完成后，最终签字前删除
//					}else{
//						json.setMsg("镜检结果复核中或已最终签字，不可以删除！");
//					}
//				}else{
//					json.setMsg("镜检完毕，不可以删除！");
//				}
				json.setMsg("镜检已提交复查，不可以删除！");
			}
			
			if(historyFlag > 0){
//				String deleteSignId = writeES(id,"TblHistopathCheck",251,"组织学所见删除",operator);
				histopathCheck.setHistoryFlag(historyFlag);
//				histopathCheck.setDeleteSignId(deleteSignId);
//				histopathCheck.setReason(reason);
				getSession().update(histopathCheck);
				
				json.setSuccess(true);
			}
			
		}else{
			json.setMsg("数据不存在！");
		}
		return json;
	}
	public Json deleteOne2(String id, String reason, String operator) {
		Json json = new Json();
		
		TblHistopathCheck histopathCheck = this.getById(id);
		if(null != histopathCheck){
			int historyFlag = 0;//删除失败
			TblPathStudyIndex pathStudy = tblPathStudyIndexService.getByStudyNo(histopathCheck.getStudyNo());
			//镜检完毕/提交签字
			String histopathCheckFinishSign = pathStudy.getHistopathCheckFinishSign();
			if(null == histopathCheckFinishSign || "".equals(histopathCheckFinishSign)){
				//historyFlag = 1;//提交复查前删除
				json.setMsg("镜检未提交，不可以删除！");
			}else{
//				int histopathReviewRequirement = task.getHistopathReviewRequirement();
//				if(histopathReviewRequirement == 1){
					//0：未提交，1：提交，2：完成复审，3：完成复审修改（最终确认）
					int histopathReviewFlag = pathStudy.getHistopathReviewFlag();
					if(histopathReviewFlag == 2){
						historyFlag = 2;//复核完成后，最终签字前删除
					}else{
						json.setMsg("镜检结果复核中或已最终签字，不可以删除！");
					}
//				}else{
//					json.setMsg("镜检完毕，不可以删除！");
//				}
			}
			
			if(historyFlag > 0){
				String deleteSignId = writeES(id,"TblHistopathCheck",251,"组织学所见删除",operator);
				histopathCheck.setHistoryFlag(historyFlag);
				histopathCheck.setDeleteSignId(deleteSignId);
				histopathCheck.setReason(reason);
				getSession().update(histopathCheck);
				
				json.setSuccess(true);
			}
			
		}else{
			json.setMsg("数据不存在！");
		}
		return json;
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

	public Json checkFinishCheck(String studyNo) {
		Json json = new Json();
		boolean success = true;
		TblPathStudyIndex pathStudyPlan = tblPathStudyIndexService.getByStudyNo(studyNo);
		if(null != pathStudyPlan && pathStudyPlan.getHistopathReviewFlag() > 0){
			success = false;
			json.setMsg("镜检已提交，不可重复提交！");
		}else{
			List<Map<String,Object>> mapList = getNoCheckTissueSliceVisceraMapList(studyNo);
			if(null != mapList && mapList.size() > 0){
				Map<String,Object> map = mapList.get(0);
				String visceraOrTissueName = (String) map.get("visceraOrTissueName");
				String animalCode = (String) map.get("animalCode");
				String sliceCode = (String) map.get("sliceCode");
				json.setMsg(visceraOrTissueName+"（动物编号："+animalCode+" 切片编号："+sliceCode+"）未检查！");
				success = false;
			}
		}
		
		json.setSuccess(success);
		return json;
	}

	/**查询对应专题所有已取材的但未检查的切片脏器
	 * @param studyNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getNoCheckTissueSliceVisceraMapList(
			String studyNo) {
//		String sql = "select v.animalCode,v.sliceCode,(case when isnull(sv.visceraCode,'')= '' then ''  "+
//					" 					when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
//					" 						when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
//					" 					else '' end "+
//					" 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
//					" 						+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
//					" 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
//					" 						)    as visceraOrTissueName"+
//					//TODO 排除表加入子脏器问题
//							" from CoresStudy.dbo.tissueSliceBatch(:studyNo) as v left join	"+
//							" 	CoresStudy.dbo.tblAnimalDetailDissectPlan as animal on v.studyNo= animal.studyNo and v.animalCode = animal.animalCode"+
//							" 	left join	 CoresStudy.dbo.tblTissueSliceViscera as sv on v.tissueSliceVisceraId = sv.id"+
//							" 	left join CoresSystemSet.dbo.dictViscera as dv on dv.visceraCode = sv.visceraCode and (dv.gender = 0 or dv.gender = animal.gender)"+
//							" 							"+
//							" 	left join 	("+
//							" 		select hc.studyNo,hc.animalCode,sv.visceraCode,sv.subVisceraCode,sv.anatomyFindingCode,sv.bodySurfacePos"+
//							" 		from CoresStudy.dbo.tblHistopathCheck as hc join "+
//							" 			CoresStudy.dbo.tblTissueSliceViscera as sv on hc.tissueSliceVisceraRecordId = sv.id"+
//							" 		where hc.studyNo = :studyNo and hc.historyFlag = 0"+
//							" 	)as r on v.studyNo = r.studyNo and v.animalCode = r.animalCode and isnull(sv.visceraCode,'') = isnull(r.visceraCode,'')"+
//							" 	and isnull(sv.subVisceraCode,'') = isnull(r.subVisceraCode,'') and isnull(sv.anatomyFindingCode,'') = isnull(r.anatomyFindingCode,'')"+
//							" 	and isnull(sv.bodySurfacePos,'') = isnull(r.bodySurfacePos,'')"+
//							" where (dv.visceraCode is not null or isnull(sv.visceraCode,'') = '' ) and r.studyNo is null"+
//							" order by v.animalCode,case when v.sliceCode like '%g-_' then 101 "+
//							" 		      when Substring(v.sliceCode,1,1) = 'T' then 200+ cast(Substring(v.sliceCode,2,len(v.sliceCode)-1) as int )"+
//							" 			  else cast(v.sliceCode as int ) end ,v.sliceCode";
		
		String sql = "select v.animalCode,v.sliceCode,(case when isnull(sv.visceraCode,'')= '' then ''  "+
					" 					 					when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 				 						when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 				 					else '' end "+
					" 				 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 				 						+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 				 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 				 						)    as visceraOrTissueName"+
//--//TODO 排除表加入子脏器问
					" from CoresStudy.dbo.tissueSliceBatch(:studyNo) as v left join	"+
					" 	CoresStudy.dbo.tblAnimalDetailDissectPlan as animal on v.studyNo= animal.studyNo and v.animalCode = animal.animalCode"+
					" 			left join	 CoresStudy.dbo.tblTissueSliceViscera as sv on v.tissueSliceVisceraId = sv.id"+
					" 		left join CoresSystemSet.dbo.dictViscera as dv on dv.visceraCode = sv.visceraCode and (dv.gender = 0 or dv.gender = animal.gender)"+
//			--查询子脏器
					" 		left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(sv.subVisceraCode,'') = '' and dv2.pVisceraCode = sv.visceraCode"+
					" 		and isnull(sv.anatomyFindingCode,'') = ''"+
//			--排除表
					" 			left join CoresStudy.dbo.tblTissueSliceBatchExcluded as be on v.studyNo = be.studyNo and v.animalCode = be.animalCode and "+
					" 				sv.visceraCode = be.visceraCode and ( ISNULL(sv.subVisceraCode,'') = ISNULL(be.subVisceraCode,'') or "+
					" 					ISNULL(dv2.visceraCode,'') = ISNULL(be.subVisceraCode,'') or ISNULL(be.subVisceraCode,'') = '' ) "+
					" 				and isnull(sv.anatomyFindingCode,'') = '' and v.tissueSliceVisceraId = be.tissueSliceVisceraId"+
//			--镜检结果			 							
					" left join 	("+
					" 				select hc.studyNo,hc.animalCode,case when isnull(hc.visceraCode,'') != '' then isnull(hc.visceraCode,'') else sv.visceraCode end as visceraCode,"+
					" 			case when isnull(hc.visceraCode,'') != '' then isnull(hc.subVisceraCode,'') else sv.subVisceraCode end as subVisceraCode,"+
					" 			sv.anatomyFindingCode,sv.bodySurfacePos"+
					" 			from CoresStudy.dbo.tblHistopathCheck as hc join "+
					" 				CoresStudy.dbo.tblTissueSliceViscera as sv on hc.tissueSliceVisceraRecordId = sv.id"+
					" 			where hc.studyNo = :studyNo and hc.historyFlag = 0"+
					" 		)as r on v.studyNo = r.studyNo and v.animalCode = r.animalCode and isnull(sv.visceraCode,'') = isnull(r.visceraCode,'')"+
					" 			and( isnull(r.subVisceraCode,'') = '' or isnull(r.subVisceraCode,'') = isnull(sv.subVisceraCode,'')  "+
					" 				or isnull(r.subVisceraCode,'') = isnull(dv2.visceraCode,'') )"+
					" 		and isnull(sv.anatomyFindingCode,'') = isnull(r.anatomyFindingCode,'')"+
					" 		and isnull(sv.bodySurfacePos,'') = isnull(r.bodySurfacePos,'')"+
					" where (dv.visceraCode is not null or isnull(sv.anatomyFindingCode,'') = '') and be.id is null and r.studyNo is null"+

					" order by v.animalCode,case when v.sliceCode like '%g-_' then 101 "+
					" 			when Substring(v.sliceCode,1,1) = 'T' then 200+ cast(Substring(v.sliceCode,2,len(v.sliceCode)-1) as int )"+
					" 			else cast(v.sliceCode as int ) end ,v.sliceCode";
		
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return list;
	}

	public void checkFinishSign(TblPathStudyIndex tblPathStudyIndex, String operator) {
		String id = getKey("TblPathStudyIndex");
		String studyNo = tblPathStudyIndex.getStudyNo();
		String histopathCheckFinishSign = writeES(studyNo, "TblPathStudyIndex", 253, "镜检完成签字", operator);
		int histopathReviewFlag = 1;
		tblPathStudyIndex.setId(id);
		tblPathStudyIndex.setHistopathCheckFinishSign(histopathCheckFinishSign);
		tblPathStudyIndex.setHistopathReviewFlag(histopathReviewFlag);
		tblPathStudyIndex.setHistopathReviewSubmitTime(new Date());
		
		
		getSession().save(tblPathStudyIndex);
		
		//专题下 所有镜检记录 histopathReviewFlag 都置为1
		String sql = "update CoresStudy.dbo.tblHistopathCheck set histopathReviewFlag = 1"+
					" from CoresStudy.dbo.tblHistopathCheck as c"+
					" where c.studyNo = ? ";
		getSession().createSQLQuery(sql).setParameter(0, studyNo).executeUpdate();
	}

	public void reviewOpinion(String id, String opinion) {
		TblHistopathCheck obj = this.getById(id);
		obj.setHistopathReviewFlag(2);
		obj.setHistopathReviewOpinion(opinion);
		Date histopathReviewTime = new Date();
		obj.setHistopathReviewTime(histopathReviewTime);
		getSession().update(obj);
	}

	public void reviewFinishSign(String studyNo, String operator) {
		
		TblPathStudyIndex tblPathStudyIndex = tblPathStudyIndexService.getByStudyNo(studyNo);
		String histopathReviewSign = writeES(studyNo, "TblPathStudyIndex", 254, "镜检复核完成签字", operator);
		int histopathReviewFlag = 2;
		tblPathStudyIndex.setHistopathReviewFlag(histopathReviewFlag);
		tblPathStudyIndex.setHistopathReviewSign(histopathReviewSign);
		getSession().update(tblPathStudyIndex);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getStudyNoAnimalCodeVisceraCodeById(String id) {
//		select c.studyNo,c.animalCode,v.visceraCode
//		from CoresStudy.dbo.tblHistopathCheck as c left join CoresStudy.dbo.tblTissueSliceViscera as v
//			on c.tissueSliceVisceraRecordId = v.id 
//		where c.id = '20000000058'
		String sql = "select c.studyNo,c.animalCode,v.visceraCode"+
					" from CoresStudy.dbo.tblHistopathCheck as c left join CoresStudy.dbo.tblTissueSliceViscera as v"+
					" 	on c.tissueSliceVisceraRecordId = v.id "+
					" where c.id = ? ";
		Map<String,Object> map = (Map<String, Object>) getSession().createSQLQuery(sql)
											.setParameter(0, id)
											.setResultTransformer(new MapResultTransformer())
											.uniqueResult();
		return map;
	}

	public void reviewFinalSign(String studyNo, String operator) {
		
		TblPathStudyIndex tblPathStudyIndex = tblPathStudyIndexService.getByStudyNo(studyNo);
		String histopathReviewFinalSign = writeES(studyNo, "TblPathStudyIndex", 255, "镜检最终签字", operator);
		int histopathReviewFlag = 3;
		tblPathStudyIndex.setHistopathReviewFlag(histopathReviewFlag);
		tblPathStudyIndex.setHistopathReviewFinalSign(histopathReviewFinalSign);
		getSession().update(tblPathStudyIndex);
		
	}

	public Json finalSignCheck(String studyNo) {
		Json json = new Json();
		boolean success = true;
		TblPathStudyIndex pathStudyPlan = tblPathStudyIndexService.getByStudyNo(studyNo);
		if(pathStudyPlan.getHistopathReviewFlag() == 3){
			success = false;
			json.setMsg("镜检已最终签字！");
		}else if(pathStudyPlan.getHistopathReviewFlag() < 2){
			success = false;
			json.setMsg("镜检未复查完成！");
		}{
			List<Map<String,Object>> mapList = getNoCheckTissueSliceVisceraMapList(studyNo);
			if(null != mapList && mapList.size() > 0){
				Map<String,Object> map = mapList.get(0);
				String visceraOrTissueName = (String) map.get("visceraOrTissueName");
				String animalCode = (String) map.get("animalCode");
				String sliceCode = (String) map.get("sliceCode");
				json.setMsg(visceraOrTissueName+"（动物编号："+animalCode+" 切片编号："+sliceCode+"）未检查！");
				success = false;
			}
		}
		
		json.setSuccess(success);
		return json;
	}

	public boolean isHasHistopathPriviege(String userId, String studyNo) {
//		（1、病理负责人，可以     
//		  2、病理专题负责人，对应专题可以    ，
//		  3、同行评议人     ，对应任务且该任务镜检已提交 可以）
		boolean pass = false;
		pass = userService.isHasPrivilege(userId, "病理负责人");
		if(!pass){
//			TblAnatomyTask task = tblAnatomyTaskService.getById(taskId);
//			String studyNo = task.getStudyNo();
			String sql = "select pathSDCode"+
						" from CoresContract.dbo.tblStudyItem "+
						" where studyNo = :studyNo ";
			String pathSd = (String) getSession().createSQLQuery(sql)
												.setParameter("studyNo", studyNo)
												.uniqueResult();
			if(null == pathSd || "".equals(pathSd) || !userId.equals(pathSd)){
				//
//				int histopathReviewFlag = task.getHistopathReviewFlag();
//				String histopathReviewer = task.getHistopathReviewer();
				
				TblPathStudyIndex tblPathStudyIndex = tblPathStudyIndexService.getByStudyNo(studyNo);
				if(null != tblPathStudyIndex){
					int histopathReviewFlag = tblPathStudyIndex.getHistopathReviewFlag();
					String histopathReviewer = tblPathStudyIndex.getHistopathReviewer();
					if(histopathReviewFlag > 0 && null != histopathReviewer && userId.equals(histopathReviewer)){
						pass = true;
					}
				}
			}else{
				pass = true;
			}
		}
		
		return pass;
	}

	public int getUserFlag(String studyNo, String userName) {
		int userFlag = 0;
		boolean pass = false;
		pass = userService.isHasPrivilege(userName, "病理负责人");
		if(!pass){
			String sql = "select pathSDCode"+
						" from CoresContract.dbo.tblStudyItem "+
						" where studyNo = :studyNo ";
			String pathSd = (String) getSession().createSQLQuery(sql)
												.setParameter("studyNo", StringUtil.studyNoRemoveFN(studyNo))
												.uniqueResult();
			if(null == pathSd || "".equals(pathSd) || !userName.equals(pathSd)){
				//
				int histopathReviewFlag  = 0;
				String histopathReviewer = null;
				TblPathStudyIndex pathStudyPlan = tblPathStudyIndexService.getByStudyNo(studyNo);
				if(null != pathStudyPlan){
					histopathReviewFlag = pathStudyPlan.getHistopathReviewFlag();
					histopathReviewer = pathStudyPlan.getHistopathReviewer();
				}
				if(histopathReviewFlag > 0 && null != histopathReviewer && userName.equals(histopathReviewer)){
					userFlag = 2;
				}
			}else{
				userFlag = 1;
			}
		}else{
			userFlag = 1;
		}
		return userFlag;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintResultMapListByStudyNo(String studyNo,int sortType) {

		String sql = "select distinct histopathCheck.animalCode,"+
					" 	case  when histopathCheck.isNoAbnormal = 1 then '未见异常'  " +
					"    when histopathCheck.isNoAbnormal = -1 then '缺失' "+
		" 		when isnull(histopathCheck.histoPos,'') = '' then histopathCheck.lesionFinding "+
		" 		when isnull(histopathCheck.histoPos,'') != '' then isnull(histopathCheck.histoPos,'')+' '+histopathCheck.lesionFinding "+
		" 		else '' end as checkResult," +
		"   case when isnull(histopathCheck.visceraCode,'') != '' then "+
		" 		("+
		" 		case when isnull(histopathCheck.subVisceraCode,'') = '' then histopathCheck.visceraName"+
		" 	 		else histopathCheck.subVisceraName end"+
				" 	) "+
				" 	else "+
		" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
		" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
		" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
		" 						else '' end "+
		" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
		" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
		" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
		" 						)  end  as visceraOrTissueName,dv.sn"+
		" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
		" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
		" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode "+
		" where histopathCheck.studyNo = ?  and historyFlag = 0  and isNoAbnormal != -1 ";
//		" order by animalCode,dv.sn";
		
		if(sortType == 1){
			sql = sql + " 	order by animalCode,dv.sn";
		}else{
			sql = sql + " 	order by dv.sn,animalCode";
		}
				
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter(0, studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintResultMapListByStudyNo_1(String studyNo,int sortType) {
		
		
		String sql = "";
		TblPathStudyIndex pathStudyPlan = tblPathStudyIndexService.getByStudyNo(studyNo);
		if(null == pathStudyPlan || pathStudyPlan.getHistopathReviewFlag() < 1){
			sql = "select distinct histopathCheck.animalCode,"+
			" 	case  when histopathCheck.isNoAbnormal = 1 then '未见异常'  " +
			"    when histopathCheck.isNoAbnormal = -1 then '缺失' "+
			" 		when isnull(histopathCheck.histoPos,'') = '' then histopathCheck.lesionFinding "+
			" 		when isnull(histopathCheck.histoPos,'') != '' then isnull(histopathCheck.histoPos,'')+' '+histopathCheck.lesionFinding "+
			" 		else '' end as checkResult," +
			"   case when isnull(histopathCheck.visceraCode,'') != '' then "+
			" 		("+
			" 		case when isnull(histopathCheck.subVisceraCode,'') = '' then histopathCheck.visceraName"+
			" 	 		else histopathCheck.subVisceraName end"+
			" 	) "+
			" 	else "+
			" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
			" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
			" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
			" 						else '' end "+
			" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
			" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
			" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
			" 						)  end  as visceraOrTissueName,dv.sn"+
			" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
			" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
			" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode "+
			" where histopathCheck.studyNo = ?  and historyFlag = 0  and isNoAbnormal != -1 ";
		}else{
			sql = "select distinct histopathCheck.animalCode,"+
			" 	case  when histopathCheck.isNoAbnormal = 1 then '未见异常'  " +
			"    when histopathCheck.isNoAbnormal = -1 then '缺失' "+
			" 		when isnull(histopathCheck.histoPos,'') = '' then histopathCheck.lesionFinding "+
			" 		when isnull(histopathCheck.histoPos,'') != '' then isnull(histopathCheck.histoPos,'')+' '+histopathCheck.lesionFinding "+
			" 		else '' end as checkResult," +
			"   case when isnull(histopathCheck.visceraCode,'') != '' then "+
			" 		("+
			" 		case when isnull(histopathCheck.subVisceraCode,'') = '' then histopathCheck.visceraName"+
			" 	 		else histopathCheck.subVisceraName end"+
			" 	) "+
			" 	else "+
			" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
			" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
			" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
			" 						else '' end "+
			" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
			" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
			" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
			" 						)  end  as visceraOrTissueName,dv.sn"+
			" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
			" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
			" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode "+
			//与
			" where histopathCheck.studyNo = ?  and  isNoAbnormal != -1 and histopathCheck.histopathReviewFlag > 0 " +
			" and (historyFlag = 2 or  historyFlag = 0)";
		}
//		" order by animalCode,dv.sn";
		
		if(sortType == 1){
			sql = sql + " 	order by animalCode,dv.sn";
		}else{
			sql = sql + " 	order by dv.sn,animalCode";
		}
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
		.setParameter(0, studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return maplist;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintResultMapListByStudyNo_2(String studyNo,int sortType) {
		
		String sql = "select distinct histopathCheck.animalCode,"+
		" 	case  when histopathCheck.isNoAbnormal = 1 then '未见异常'  " +
		"    when histopathCheck.isNoAbnormal = -1 then '缺失' "+
		" 		when isnull(histopathCheck.histoPos,'') = '' then histopathCheck.lesionFinding "+
		" 		when isnull(histopathCheck.histoPos,'') != '' then isnull(histopathCheck.histoPos,'')+' '+histopathCheck.lesionFinding "+
		" 		else '' end as checkResult," +
		"   case when isnull(histopathCheck.visceraCode,'') != '' then "+
		" 		("+
		" 		case when isnull(histopathCheck.subVisceraCode,'') = '' then histopathCheck.visceraName"+
		" 	 		else histopathCheck.subVisceraName end"+
		" 	) "+
		" 	else "+
		" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
		" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
		" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
		" 						else '' end "+
		" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
		" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
		" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
		" 						)  end  as visceraOrTissueName,dv.sn ,histopathCheck.histopathReviewOpinion as reviewOpinion"+
		" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
		" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
		" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode "+
		" where histopathCheck.studyNo = ?  and isNoAbnormal != -1 and histopathCheck.histopathReviewFlag = 2";
//		" order by animalCode,dv.sn";
		
		if(sortType == 1){
			sql = sql + " 	order by animalCode,dv.sn";
		}else{
			sql = sql + " 	order by dv.sn,animalCode";
		}
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
		.setParameter(0, studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return maplist;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPrintResultMapListByStudyNo_3(String studyNo,int sortType) {
		
		String sql = "";
		TblPathStudyIndex pathStudyPlan = tblPathStudyIndexService.getByStudyNo(studyNo);
		if(null == pathStudyPlan || pathStudyPlan.getHistopathReviewFlag() < 1){
			return null;
		}else{
			sql = "select histopathCheck.animalCode,"+
			" 	case  when histopathCheck.isNoAbnormal = 1 then '未见异常'  " +
			"    when histopathCheck.isNoAbnormal = -1 then '缺失' "+
			" 		when isnull(histopathCheck.histoPos,'') = '' then histopathCheck.lesionFinding "+
			" 		when isnull(histopathCheck.histoPos,'') != '' then isnull(histopathCheck.histoPos,'')+' '+histopathCheck.lesionFinding "+
			" 		else '' end as checkResult," +
			"   case when isnull(histopathCheck.visceraCode,'') != '' then "+
			" 		("+
			" 		case when isnull(histopathCheck.subVisceraCode,'') = '' then histopathCheck.visceraName"+
			" 	 		else histopathCheck.subVisceraName end"+
			" 	) "+
			" 	else "+
			" 					(case when isnull(sliceViscera.visceraCode,'')= '' then ''  "+
			" 					when isnull(sliceViscera.subVisceraCode,'') = '' then sliceViscera.visceraName+' '"+
			" 						when isnull(sliceViscera.subVisceraCode,'') != '' then sliceViscera.subVisceraName+' ' "+
			" 						else '' end "+
			" 				+ case when isnull(sliceViscera.bodySurfacePos,'') = '' then '' else (sliceViscera.bodySurfacePos+' ') end"+
			" 						+case when isnull(sliceViscera.anatomyPos,'') = '' then '' else (sliceViscera.anatomyPos+' ') end"+
			" 					+case when isnull(sliceViscera.anatomyFingding,'') = '' then '' else (sliceViscera.anatomyFingding+' ') end"+
			" 						)  end  as visceraOrTissueName,dv.sn," +
			" case when histopathCheck.historyFlag = 2 then '删除' when histopathCheck.histopathReviewFlag = 0 then '添加' else '' end  as operateType"+
			" from CoresStudy.dbo.tblHistopathCheck as histopathCheck left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera"+
			" 	on histopathCheck.tissueSliceVisceraRecordId = sliceViscera.id "+
			" 	left join CoresSystemSet.dbo.dictViscera  as dv on dv.visceraCode = sliceViscera.visceraCode "+
			//与
			" where histopathCheck.studyNo = ?  and  isNoAbnormal != -1 " +
			" and (historyFlag = 2 or  histopathCheck.histopathReviewFlag = 0)";
		}
//		" order by animalCode,dv.sn";
		
		if(sortType == 1){
			sql = sql + " 	order by animalCode,dv.sn,histopathCheck.id";
		}else{
			sql = sql + " 	order by dv.sn,animalCode,histopathCheck.id";
		}
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
		.setParameter(0, studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListForDeathReason(String studyNo,
			String animalCode) {
		String sql = "select id,lesionFinding"+
					" from CoresStudy.dbo.tblHistopathCheck "+
					" where isNoAbnormal = 0 and studyNo = :studyNo and animalCode = :animalCode ";
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeVisceraByTissueSliceSnIdList(
			List<String> tissueSliceSnIdList, String studyNo) {
		String sql = " select r.animalCode,r.sliceCode,r.sliceVisceraId,r.visceraType,r.visceraCode,r.visceraFixedRecordId,"+
					" 	r.visceraOrTissueName,r.visceraName,r.subVisceraCode,r.subVisceraName,r.specialFlag,"+
					" 	r.anatomyPosCode,r.anatomyPos,r.anatomyFindingCode,r.anatomyFindingFlag,r.anatomyFingding,"+
					" 	r.bodySurfacePos,r.isHandwork"+
					" from (" +
				" select distinct case when b.sliceCode like b.animalCode+'g-_' then b.animalCode else '' end animalCode,b.sliceCode,"+
					" 	b.tissueSliceVisceraId as sliceVisceraId,sv.visceraType,sv.visceraCode,sv.visceraFixedRecordId,"+
				  
					" 				(case when isnull(sv.visceraCode,'')= '' then ''  "+
					" 				when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 					when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 					else '' end "+
					" 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 						+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 						)    as visceraOrTissueName,"+
					" 			sv.visceraName,sv.subVisceraCode,sv.subVisceraName,"+
					" 			sv.specialFlag,sv.anatomyPosCode,sv.anatomyPos,sv.anatomyFindingCode,"+
					" 		sv.anatomyFindingFlag,sv.anatomyFingding,sv.bodySurfacePos,sv.isHandwork"+
					//TODO 排除表加入子脏器问题
					" from CoresStudy.dbo.tissueSliceBatch(:studyNo) as b left join CoresStudy.dbo.tblTissueSliceViscera"+
					" 	as sv on b.tissueSliceVisceraId = sv.id "+
			
					" where sv.tissueSliceSnId in (:tissueSliceSnIdList) " +
					" ) as r" +
					" order by case when r.sliceCode like '%g-_' then 101 " +
					" 		      when Substring(r.sliceCode,1,1) = 'T' then 200+ cast(Substring(r.sliceCode,2,len(r.sliceCode)-1) as int )" +
					" 			  else cast(r.sliceCode as int ) end ,r.sliceCode,r.animalCode,r.sliceVisceraId";
		
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameterList("tissueSliceSnIdList", tissueSliceSnIdList)
			.setResultTransformer(new MapResultTransformer())
			.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalByStudyNoAnimalCodeListSliceVisceraId(
			String studyNo, List<String> animalCodeList, String sliceVisceraId) {
		String sql = "select distinct a.animalCode,a.gender,hc.isNoAbnormal,hc3.resultNum"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a"+
					" 	left join CoresStudy.dbo.tblHistopathCheck as hc on hc.studyNo = a.studyNo"+
					" 		and hc.isNoAbnormal = 0  and hc.historyFlag = 0 and hc.animalCode = a.animalCode"+
					" 	left join ("+
					" 		select hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum"+
					" 		from CoresStudy.dbo.tblHistopathCheck as hc2"+
					" 		where hc2.studyNo = :studyNo and hc2.historyFlag = 0"+
					" 	group by hc2.studyNo,hc2.animalCode"+
					" ) as hc3 on  hc3.studyNo = a.studyNo  and hc3.animalCode = a.animalCode"+
					" where a.studyNo = :studyNo and "+
					" 	a.animalCode in ("+
					" 		select b.animalCode"+
					//TODO 排除表加入子脏器问题
					" 		from CoresStudy.dbo.tissueSliceBatch(:studyNo) as b left join CoresStudy.dbo.tblTissueSliceViscera"+
					" 		as sv on b.tissueSliceVisceraId = sv.id left join CoresSystemSet.dbo.dictViscera as dv"+
					" 					on sv.visceraCode = dv.visceraCode left join CoresStudy.dbo.tblAnimalDetailDissectPlan as a"+
					" 		on a.studyNo = :studyNo and b.animalCode = a.animalCode "+
					" 		and (dv.gender = 0 or dv.gender = a.gender or isnull(sv.visceraCode ,'')= '')"+
			
					" 	where b.tissueSliceVisceraId = :sliceVisceraId and a.animalCode is not null and b.animalCode in(:animalCodeList)"+
					" 	)";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameter("sliceVisceraId", sliceVisceraId)
			.setParameterList("animalCodeList", animalCodeList)
			.setResultTransformer(new MapResultTransformer())
			.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllSliceCodeAnimalCodeSliceViscersId(String studyNo, 
			List<String> animalCodeList, List<String> tissueSliceSnIdList) {
		String sql = "select b.sliceCode,b.animalCode,"+
					" 	b.tissueSliceVisceraId as sliceVisceraId,sv.visceraType,sv.visceraCode,sv.visceraFixedRecordId,"+
				  
					" 				(case when isnull(sv.visceraCode,'')= '' then ''  "+
					" 					when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 						when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 						else '' end "+
					" 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 						+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 						)    as visceraOrTissueName,"+
					" 			sv.visceraName,sv.subVisceraCode,sv.subVisceraName,"+
					" 			sv.specialFlag,sv.anatomyPosCode,sv.anatomyPos,sv.anatomyFindingCode,"+
					" 		sv.anatomyFindingFlag,sv.anatomyFingding,sv.bodySurfacePos,sv.isHandwork"+
					//TODO 排除表加入子脏器问题
					" from CoresStudy.dbo.tissueSliceBatch(:studyNo) as b left join CoresStudy.dbo.tblTissueSliceViscera"+
					" 	as sv on b.tissueSliceVisceraId = sv.id left join CoresSystemSet.dbo.dictViscera as dv"+
					" 		on sv.visceraCode = dv.visceraCode left join CoresStudy.dbo.tblAnimalDetailDissectPlan as a"+
					" 		on a.studyNo = :studyNo and b.animalCode = a.animalCode "+
					" 		and (dv.gender = 0 or dv.gender = a.gender or isnull(sv.visceraCode ,'')= '')"+
					" where sv.tissueSliceSnId in (:tissueSliceSnIdList) and b.animalCode in (:animalCodeList) "+
					" 	and a.animalCode is not null"+
					" order by case when b.sliceCode like '%g-_' then 101 "+
					" 		      when Substring(b.sliceCode,1,1) = 'T' then 200+ cast(Substring(b.sliceCode,2,len(b.sliceCode)-1) as int )"+
					" 		  else cast(b.sliceCode as int ) end ,b.sliceCode,b.animalCode,b.tissueSliceVisceraId";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameterList("tissueSliceSnIdList", tissueSliceSnIdList)
			.setParameterList("animalCodeList", animalCodeList)
			.setResultTransformer(new MapResultTransformer())
			.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public String getSliceVisceraId(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode,
			String anatomyFindingCode, String bodySurfacePos) {
		String sql = "select id "+
					" from CoresStudy.dbo.tblTissueSliceViscera "+
					" where appendFlag = 1 and  appendStudyNo = :studyNo and appendAnimalCode = :animalCode " +
					" and isnull(visceraCode,'') = :visceraCode"+
					" 	and isnull(subVisceraCode,'') = :subVisceraCode " +
					" and isnull(anatomyFindingCode,'') = :anatomyFindingCode "+
					" 	and isnull(bodySurfacePos,'') = :bodySurfacePos ";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameter("animalCode", animalCode)
										.setParameter("visceraCode", visceraCode != null ? visceraCode :"")
										.setParameter("subVisceraCode", subVisceraCode != null ? subVisceraCode :"")
										.setParameter("anatomyFindingCode", anatomyFindingCode != null ? anatomyFindingCode :"")
										.setParameter("bodySurfacePos", bodySurfacePos != null ? bodySurfacePos :"")
										.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public String saveHandWordRecord(TblTissueSliceViscera tblTissueSliceViscera,
			TblHistopathCheck tblHistopathCheck) {
		String sliceVisceraId = getKey("TblTissueSliceViscera");
		tblTissueSliceViscera.setId(sliceVisceraId);
		getSession().save(tblTissueSliceViscera);
		
		
		String id = getKey();
		tblHistopathCheck.setId(id);
		tblHistopathCheck.setTissueSliceVisceraRecordId(sliceVisceraId);
		tblHistopathCheck.setOperateTime(new Date());
		getSession().save(tblHistopathCheck);
		
		return sliceVisceraId;
	}

	public String saveOne(String referId, TblHistopathCheck tblHistopathCheck,
			TblTissueSliceViscera tblTissueSliceViscera) {
		String sliceVisceraId = getKey("TblTissueSliceViscera");
		tblTissueSliceViscera.setId(sliceVisceraId);
		getSession().save(tblTissueSliceViscera);
		
		
		String id = getKey();
		tblHistopathCheck.setId(id);
		tblHistopathCheck.setTissueSliceVisceraRecordId(sliceVisceraId);
		tblHistopathCheck.setOperateTime(new Date());
		tblHistopathCheck.setRefId(referId);
		
		TblHistopathCheck old = this.getById(referId);
		
		tblHistopathCheck.setIsNoAbnormal(old.getIsNoAbnormal());
		tblHistopathCheck.setTumorFlag(old.getTumorFlag());
		tblHistopathCheck.setMetastasisFlag(old.getMetastasisFlag());
		tblHistopathCheck.setHistoPosCode(old.getHistoPosCode());
		tblHistopathCheck.setHistoPos(old.getHistoPos());
		tblHistopathCheck.setLesionFinding(old.getLesionFinding());
		tblHistopathCheck.setLesionFindingCode(old.getLesionFindingCode());
		tblHistopathCheck.setLevel(old.getLevel());
		tblHistopathCheck.setRemark(old.getRefId());
		
		tblHistopathCheck.setTumorPos(old.getTumorPos());
		tblHistopathCheck.setTumorCharacter(old.getTumorCharacter());
		tblHistopathCheck.setTumorOccurDate(old.getTumorOccurDate());
		
		getSession().save(tblHistopathCheck);
		
		return sliceVisceraId;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalByStudyNo(String studyNo) {
		String sql = "select distinct a.animalCode,a.gender,hc1.isNoAbnormal,hc3.resultNum"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a join CoresStudy.dbo.tblHistopathCheck as hc0"+
					" 	on hc0.studyNo = a.studyNo and hc0.animalCode = a.animalCode"+
					" 	left join CoresStudy.dbo.tblHistopathCheck as hc1 on hc1.studyNo = a.studyNo"+
					" 		and hc1.isNoAbnormal = 0  and hc1.historyFlag = 0  and hc1.animalCode = a.animalCode"+
					" 	left join ("+
					" 		select hc2.studyNo,hc2.animalCode,count(hc2.id) as resultNum"+
					" 		from CoresStudy.dbo.tblHistopathCheck as hc2"+
					" 		where hc2.studyNo = :studyNo and hc2.historyFlag = 0"+
					" 		group by hc2.studyNo,hc2.animalCode"+
					" 	) as hc3 on  hc3.studyNo = a.studyNo  and hc3.animalCode = a.animalCode"+
					" where a.studyNo = :studyNo ";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTissueSliceViscera(String studyNo,
			String animalCode) {
		String sql = "select distinct c.animalCode,"+
					" 	c.tissueSliceVisceraRecordId as sliceVisceraId,sv.visceraType,sv.visceraCode,sv.visceraFixedRecordId,	"+
					" 	case when isnull(sv.anatomyFingding,'') != '' then 	(case when isnull(sv.visceraCode,'')= '' then ''  "+
					" 				when isnull(sv.subVisceraCode,'') = '' then sv.visceraName+' '"+
					" 					when isnull(sv.subVisceraCode,'') != '' then sv.subVisceraName+' ' "+
					" 					else '' end "+
					" 				+ case when isnull(sv.bodySurfacePos,'') = '' then '' else (sv.bodySurfacePos+' ') end"+
					" 					 	+case when isnull(sv.anatomyPos,'') = '' then '' else (sv.anatomyPos+' ') end"+
					" 					+case when isnull(sv.anatomyFingding,'') = '' then '' else (sv.anatomyFingding+' ') end"+
					" 						) "+
					" 		 else (	case when isnull(c.subVisceraName,'') != '' then c.subVisceraName"+
					" 					else  c.visceraName end"+
					" 		   ) "+
					" 		 end   as visceraOrTissueName,"+
					" 			sv.visceraName, "+
					" 			case when isnull(sv.anatomyFingding,'') != '' then  sv.subVisceraCode else c.subVisceraCode end as subVisceraCode,"+
					" 			case when isnull(sv.anatomyFingding,'') != '' then sv.subVisceraName else c.subVisceraName end as subVisceraName,"+
					" 			sv.specialFlag,sv.anatomyPosCode,sv.anatomyPos,sv.anatomyFindingCode,"+
					" 		sv.anatomyFindingFlag,sv.anatomyFingding,sv.bodySurfacePos,dv.sn"+
					" from CoresStudy.dbo.tblHistopathCheck as c left join CoresStudy.dbo.tblTissueSliceViscera"+
					" 	as sv on c.tissueSliceVisceraRecordId = sv.id left join CoresSystemSet.dbo.dictViscera as dv"+
					" 			on sv.visceraCode = dv.visceraCode"+
					" where c.studyNo = :studyNo and c.animalCode = :animalCode and (c.historyFlag = 1 and c.histopathReviewFlag = 0 or c.historyFlag = 0)" +
					" and c.isNoAbnormal != -1 "+
					" order by dv.sn";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return list;
	}

	public Json checkRecord(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode,
			String anatomyFindingCode, String bodySurfacePos) {
		Json json  = new Json();
		
		List<String> sliceVisceraIdList = getTissueSliceIdList(studyNo,animalCode,visceraCode,subVisceraCode,anatomyFindingCode,bodySurfacePos);
		if(null != sliceVisceraIdList && sliceVisceraIdList.size() > 0){
			String sql = "select count(id)"+
			" from CoresStudy.dbo.tblHistopathCheck"+
			" where studyNo = :studyNo and animalCode = :animalCode " +
			" and tissueSliceVisceraRecordId in (:sliceVisceraIdList) and historyFlag = 0 ";
			Integer count = (Integer) getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameter("animalCode", animalCode)
			.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
			.uniqueResult();
			if(count > 0){
				sql = "select count(id)"+
				" from CoresStudy.dbo.tblHistopathCheck"+
				" where studyNo = :studyNo and animalCode = :animalCode and isNoAbnormal = 1 " +
				" and tissueSliceVisceraRecordId in (:sliceVisceraIdList) and historyFlag = 0 ";
				Integer count2 = (Integer) getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("animalCode", animalCode)
				.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
				.uniqueResult();
				if(count2 > 0){
					json.setMsg("1");
				}else{
					json.setMsg("0");
				}
			}else{
				json.setSuccess(true);
			}
		}else{
			json.setSuccess(true);
		}
		
		return json;
	}
	/**获取对应专题下脏器或组织在   该专题下的所有切片脏器Id列表（包括追加的，追加的专题和动物编号都得对应）
	 * @param studyNo
	 * @param animalCode
	 * @param tissueSliceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String> getTissueSliceIdList(String studyNo,
			String animalCode, String visceraCode, String subVisceraCode,
			String anatomyFindingCode, String bodySurfacePos) {
		List<String> list = null;
		String sql = "select slicev.id"+
					" from CoresStudy.dbo.tblTissueSliceViscera as slicev "+
					" where slicev.appendStudyNo = :studyNo and slicev.appendAnimalCode = :animalCode"+
					" 	and isnull(slicev.visceraCode,'') = :visceraCode and isnull(slicev.subVisceraCode,'')	= :subVisceraCode "+
					" 	and isnull(slicev.anatomyFindingCode,'') = :anatomyFindingCode	and isnull(slicev.bodySurfacePos,'') = :bodySurfacePos"+
					" union"+
					" select slicev.tissueSliceVisceraId as id"+
					" from CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as slicev"+
					" where isnull(slicev.visceraCode,'') = :visceraCode and isnull(slicev.subVisceraCode,'')	= :subVisceraCode "+
					" 	and isnull(slicev.anatomyFindingCode,'') = :anatomyFindingCode	and isnull(slicev.bodySurfacePos,'') = :bodySurfacePos";
		list = getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.setParameter("animalCode", animalCode)
							.setParameter("visceraCode", visceraCode != null ? visceraCode:"")
							.setParameter("subVisceraCode", subVisceraCode != null ? subVisceraCode:"")
							.setParameter("anatomyFindingCode", anatomyFindingCode != null ? anatomyFindingCode:"")
							.setParameter("bodySurfacePos", bodySurfacePos != null ? bodySurfacePos:"")
							.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblHistopathCheck> getListByStudyNoAnimalCodeSliceViscera(
			String studyNo, String animalCode, String visceraCode,
			String subVisceraCode, String anatomyFindingCode,
			String bodySurfacePos) {
		List<String> sliceVisceraIdList = 
			getTissueSliceIdList(studyNo,animalCode,visceraCode,subVisceraCode,anatomyFindingCode,bodySurfacePos);
		List<TblHistopathCheck> list = null;
		if(null != sliceVisceraIdList && sliceVisceraIdList.size() > 0){
			String hql = "from TblHistopathCheck "+
			" where studyNo = :studyNo and animalCode = :animalCode and " +
			" tissueSliceVisceraRecordId in (:sliceVisceraIdList) and  (historyFlag = 0 or historyFlag = 2)";
			 list = getSession().createQuery(hql)
							.setParameter("studyNo", studyNo)
							.setParameter("animalCode", animalCode)
							.setParameterList("sliceVisceraIdList", sliceVisceraIdList)
							.list();
			
		}
		return list;
	}

	public void missingRegister(TblHistopathCheck tblHistopathCheck) {
		String dataId =getKey("TblHistopathCheck");
		tblHistopathCheck.setId(dataId);
		// 
		String esId = writeES(dataId , "TblHistopathCheck", 712, "镜检缺失登记", tblHistopathCheck.getOperator());
		
		tblHistopathCheck.setDeleteSignId(esId);
		
		getSession().save(tblHistopathCheck);
		
	}

	public String saveHandWordRecordAndMissingRegister(
			TblTissueSliceViscera tblTissueSliceViscera,
			TblHistopathCheck tblHistopathCheck) {
		
		String sliceVisceraId = getKey("TblTissueSliceViscera");
		tblTissueSliceViscera.setId(sliceVisceraId);
		getSession().save(tblTissueSliceViscera);
		
		String id = getKey();
		tblHistopathCheck.setId(id);
		tblHistopathCheck.setTissueSliceVisceraRecordId(sliceVisceraId);
		
		String esId = writeES(id , "TblHistopathCheck", 712, "镜检缺失登记", tblHistopathCheck.getOperator());
		
		tblHistopathCheck.setDeleteSignId(esId);
		getSession().save(tblHistopathCheck);
		
		return sliceVisceraId;
	}

	public boolean isRegisterMissing(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		if(null != studyNo && !"".equals(studyNo) && null != animalCode && !"".equals(animalCode)
				&& null != visceraCode && !"".equals(visceraCode)){
			String sql = "select count(c.id)"+
						" from CoresStudy.dbo.tblHistopathCheck as c"+
						" where c.studyNo = :studyNo and c.animalCode = :animalCode and c.isNoAbnormal = -1"+
						" 	and c.visceraCode = :visceraCode and ( isnull(c.subVisceraCode,'') = '' or isnull(c.subVisceraCode,'') = :subVisceraCode)";
			Integer count = (Integer) getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.setParameter("animalCode", animalCode)
										.setParameter("visceraCode", visceraCode)
										.setParameter("subVisceraCode", subVisceraCode == null ? "":subVisceraCode)
										.uniqueResult();
			return count > 0;
		}
		return false;
	}

}
