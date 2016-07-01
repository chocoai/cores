package com.lanen.service.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblTissueSliceIndex;
import com.lanen.model.path.TblTissueSliceSn;
import com.lanen.model.path.TblTissueSliceViscera;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.TblStudyPlanService;

@Service
public class TblTissueSliceIndexServiceImpl extends BaseDaoImpl<TblTissueSliceIndex> implements TblTissueSliceIndexService {

	/**
	 * 解剖任务service
	 */
	@Resource
	private TblAnatomyTaskService tblAnatomyTaskService;
	
	/**
	 * 解剖计划service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 会话service
	 */
	@Resource
	private TblPathSessionService tblPathSessionService;
	
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	public List<Map<String, Object>> getFiexdVisceraMapList(String taskId) {
		List<Map<String,Object>> mapList = null;
		if(null != taskId){
			List<String> sessionIdList = tblPathSessionService.getSessionIdList(taskId);
			String animalType  = "";
			TblAnatomyTask tblAnatomyTask = tblAnatomyTaskService.getById(taskId);
			if(null != tblAnatomyTask){
				String studyNo = tblAnatomyTask.getStudyNo();
				TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
				if(null != tblStudyPlan){
					animalType = tblStudyPlan.getAnimalType();
				}
			}
		}
		return mapList;
	}

	public TblTissueSliceIndex getByStudyNo(String studyNo) {
		String hql = "from TblTissueSliceIndex where studyNo = ? and sliceCodeType = 0";
		TblTissueSliceIndex tblTissueSliceIndex = (TblTissueSliceIndex) getSession().createQuery(hql)
															.setParameter(0, studyNo)
															.uniqueResult();
		return tblTissueSliceIndex;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTaskIdSliceCodeType(
			String taskId, int sliceCodeType) {
		String sql = "select sliceIndex.taskId,sliceIndex.sliceCodeType," +
					 	" sliceIndex.operatorSign,sliceIndex.gender,scope.groupId" +
					" from CoresStudy.dbo.tblTissueSliceIndex as sliceIndex " +
						" left join CoresStudy.dbo.tblTissueSliceScope as scope" +
						" 	on sliceIndex.id = scope.tissueSliceIndexId " +
					" where sliceIndex.taskId = :taskId and sliceIndex.sliceCodeType = :sliceCodeType";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("taskId", taskId)
														.setParameter("sliceCodeType", sliceCodeType)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceSnVisceraMapListByTaskId(
			String taskId) {
		String sql = "select sliceSn.sliceCode,sliceViscera.visceraType,sliceViscera.visceraCode,sliceViscera.visceraName," +
						" sliceViscera.subVisceraCode,sliceViscera.subVisceraName,dictViscera.gender,dictViscera.sn,dictViscera2.sn as subSn" +
						" from CoresStudy.dbo.tblTissueSliceIndex as sliceIndex left join CoresStudy.dbo.tblTissueSliceSn as sliceSn" +
						" 	on sliceIndex.id = sliceSn.tissueSliceIndexId left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera" +
						" 	on sliceSn.id = sliceViscera.tissueSliceSnId left join CoresSystemSet.dbo.dictViscera as dictViscera" +
						" 	on sliceViscera.visceraCode = dictViscera.visceraCode left join CoresSystemSet.dbo.dictViscera as dictViscera2" +
						" 	on sliceViscera.subVisceraCode is not null and sliceViscera.subVisceraCode = dictViscera2.visceraCode" +
						" where sliceIndex.taskId = :taskId and sliceIndex.sliceCodeType = 0" +
						" order by cast(sliceSn.sliceCode as int) ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
				.setParameter("taskId", taskId)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListByStudyNo(String studyNo, boolean showHistopathViscera) {
		//1.判断是否有病理计划
		String count_sql = "select count(id) "+
						" from CoresStudy.dbo.tblPathPlanCheck "+
						" where studyNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(count_sql).setParameter(0, studyNo).uniqueResult();
		
		String sql ="";
		if(null != count && count >0 ){
			//2.有病理计划
			sql = "select ppc.visceraType,ppc.visceraCode,ppc.visceraName,dictViscera.sn,"+
				" 	dictViscera2.visceraCode as subVisceraCode,dictViscera2.visceraName as subVisceraName,dictViscera2.sn as subSn"+
				" from CoresStudy.dbo.tblPathPlanCheck as ppc left join CoresSystemSet.dbo.dictViscera as dictViscera"+
				" 	on  ppc.visceraCode = dictViscera.visceraCode left join CoresSystemSet.dbo.dictViscera as dictViscera2 "+
				" 	on ppc.visceraCode = dictViscera2.pVisceraCode"+
				" where ppc.studyNo = :studyNo ";
			if(showHistopathViscera){
				//仅加载镜检脏器
				sql = sql +" and ppc.histopathCheckFlag = 1 ";
			}
			sql = sql +" order by dictViscera.sn,dictViscera2.sn";
		}else{
			//3.无病理计划
			sql = "select distinct reqc.visceraType,reqc.visceraCode,reqc.visceraName,dictViscera.sn,"+
				" 	dictViscera2.visceraCode as subVisceraCode,dictViscera2.visceraName as subVisceraName,dictViscera2.sn as subSn"+
				" from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as reqc left join CoresSystemSet.dbo.dictViscera as dictViscera"+
				" 	on  reqc.visceraCode = dictViscera.visceraCode left join CoresSystemSet.dbo.dictViscera as dictViscera2 "+
				" 	on reqc.visceraCode = dictViscera2.pVisceraCode"+
				" where reqc.studyNo = :studyNo ";
			if(showHistopathViscera){
				//仅加载镜检脏器
				sql = sql +" and reqc.histopathCheckFlag = 1";
			}
			sql = sql +	" order by dictViscera.sn,dictViscera2.sn";
		}
		
		
		List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public TblTissueSliceIndex addOneFor02(TblTissueSliceIndex tblTissueSliceIndex,
			List<String> sliceCodeList, List<Map<String, Object>> visceraMapList){
		// 切片范围被删除问题
		//1.准备切片索引
		String sliceIndexId = getKey();
		tblTissueSliceIndex.setCreateTime(new Date());
		tblTissueSliceIndex.setId(sliceIndexId);
		
		//3准备切片编号
		//4.准备切片脏器
		//待保存
		List<TblTissueSliceSn> tblTissueSliceSnList = new ArrayList<TblTissueSliceSn>();
		//待保存
		List<TblTissueSliceViscera> tblTissueSliceVisceraList = new ArrayList<TblTissueSliceViscera>();
		TblTissueSliceSn tblTissueSliceSn = null;
		TblTissueSliceViscera tblTissueSliceViscera = null;
		for(String sliceCode:sliceCodeList){
			tblTissueSliceSn = new TblTissueSliceSn();
			String sliceSnId = getKey("TblTissueSliceSn");
			tblTissueSliceSn.setId(sliceSnId);
			tblTissueSliceSn.setSliceCode(sliceCode);
			tblTissueSliceSn.setTissueSliceIndexId(sliceIndexId);
			tblTissueSliceSnList.add(tblTissueSliceSn);
			
			for(Map<String,Object> map:visceraMapList){
				String currentSliceCode = (String) map.get("sliceCode");
				if(sliceCode.equals(currentSliceCode)){
					Integer visceraType = (Integer) map.get("visceraType");
					String visceraCode = (String) map.get("visceraCode");
					String visceraName = (String) map.get("visceraName");
					String subVisceraCode = (String) map.get("subVisceraCode");
					String subVisceraName = (String) map.get("subVisceraName");
					
					tblTissueSliceViscera = new TblTissueSliceViscera();
					tblTissueSliceViscera.setId(getKey("TblTissueSliceViscera"));
					tblTissueSliceViscera.setTissueSliceSnId(sliceSnId);
					tblTissueSliceViscera.setVisceraType(visceraType);
					tblTissueSliceViscera.setVisceraCode(visceraCode);
					tblTissueSliceViscera.setVisceraName(visceraName);
					tblTissueSliceViscera.setSubVisceraCode(subVisceraCode);
					tblTissueSliceViscera.setSubVisceraName(subVisceraName);
					tblTissueSliceVisceraList.add(tblTissueSliceViscera);
				}
			}
		}
		
		//5.保存
		getSession().save(tblTissueSliceIndex);
		
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			getSession().save(obj);
		}
		for(TblTissueSliceViscera obj:tblTissueSliceVisceraList){
			getSession().save(obj);
		}
		return tblTissueSliceIndex; 
	}

	public TblTissueSliceIndex updateOneFor02(TblTissueSliceIndex tblTissueSliceIndex,
			List<String> sliceCodeList, List<Map<String, Object>> visceraMapList){
		//1.准备切片索引  切片范围被删除问题
		String sliceIndexId = tblTissueSliceIndex.getId();
//		tblTissueSliceIndex.setCreateTime(new Date());
		
		//3准备切片编号
		//4.准备切片脏器
		//待保存
		List<TblTissueSliceSn> tblTissueSliceSnList = new ArrayList<TblTissueSliceSn>();
		//待保存
		List<TblTissueSliceViscera> tblTissueSliceVisceraList = new ArrayList<TblTissueSliceViscera>();
		TblTissueSliceSn tblTissueSliceSn = null;
		TblTissueSliceViscera tblTissueSliceViscera = null;
		for(String sliceCode:sliceCodeList){
			tblTissueSliceSn = new TblTissueSliceSn();
			String sliceSnId = getKey("TblTissueSliceSn");
			tblTissueSliceSn.setId(sliceSnId);
			tblTissueSliceSn.setSliceCode(sliceCode);
			tblTissueSliceSn.setTissueSliceIndexId(sliceIndexId);
			tblTissueSliceSnList.add(tblTissueSliceSn);
			
			for(Map<String,Object> map:visceraMapList){
				String currentSliceCode = (String) map.get("sliceCode");
				if(sliceCode.equals(currentSliceCode)){
					Integer visceraType = (Integer) map.get("visceraType");
					String visceraCode = (String) map.get("visceraCode");
					String visceraName = (String) map.get("visceraName");
					String subVisceraCode = (String) map.get("subVisceraCode");
					String subVisceraName = (String) map.get("subVisceraName");
					
					tblTissueSliceViscera = new TblTissueSliceViscera();
					tblTissueSliceViscera.setId(getKey("TblTissueSliceViscera"));
					tblTissueSliceViscera.setTissueSliceSnId(sliceSnId);
					tblTissueSliceViscera.setVisceraType(visceraType);
					tblTissueSliceViscera.setVisceraCode(visceraCode);
					tblTissueSliceViscera.setVisceraName(visceraName);
					tblTissueSliceViscera.setSubVisceraCode(subVisceraCode);
					tblTissueSliceViscera.setSubVisceraName(subVisceraName);
					tblTissueSliceVisceraList.add(tblTissueSliceViscera);
				}
			}
		}
		//5.删除原切片脏器，编号
		String sql_viscera = "delete from CoresStudy.dbo.tblTissueSliceViscera"+
							" from CoresStudy.dbo.tblTissueSliceViscera as viscera left join CoresStudy.dbo.tblTissueSliceSn as sn"+
							" 	on viscera.tissueSliceSnId = sn.id"+
							" where sn.tissueSliceIndexId = :tissueSliceIndexId";
		getSession().createSQLQuery(sql_viscera)
					.setParameter("tissueSliceIndexId", sliceIndexId)
					.executeUpdate();
		
		String sql_sn = "delete"+
						" from CoresStudy.dbo.tblTissueSliceSn"+
						" where tissueSliceIndexId = :tissueSliceIndexId";
		getSession().createSQLQuery(sql_sn)
					.setParameter("tissueSliceIndexId", sliceIndexId)
					.executeUpdate();
		
		//5.保存
		getSession().update(tblTissueSliceIndex);
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			getSession().save(obj);
		}
		for(TblTissueSliceViscera obj:tblTissueSliceVisceraList){
			getSession().save(obj);
		}
		return tblTissueSliceIndex;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getRemainVisceraMapListByStudyNo(String studyNo){
		//1.判断是否有病理计划
		String count_sql = "select count(id) "+
						" from CoresStudy.dbo.tblPathPlanCheck "+
						" where studyNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(count_sql).setParameter(0, studyNo).uniqueResult();
		
		String sql ="";
		if(null != count && count >0 ){
			//2.有病理计划
			sql = "select  ppc.visceraCode,ppc.visceraName"+
				" from CoresStudy.dbo.tblPathPlanCheck as ppc left join "+
				" 	("+
				" 		select tsv.visceraCode"+
				" 		from  CoresStudy.dbo.tblTissueSliceIndex as tsi"+
				" 			  left join CoresStudy.dbo.tblTissueSliceSn as tss"+
				" 			on tsi.id = tss.tissueSliceIndexId left join CoresStudy.dbo.tblTissueSliceViscera as tsv"+
				" 			on tss.id = tsv.tissueSliceSnId "+
				" 		where tsi.studyNo = :studyNo and tsi.sliceCodeType = 0"+
				" 	) as r on ppc.visceraCode = r.visceraCode"+
				" where ppc.studyNo = :studyNo and ppc.histopathCheckFlag = 1 and ppc.visceraCode is not null and r.visceraCode is null";
		}else{
			//3.无病理计划
			sql = "select  ppc.visceraCode,ppc.visceraName"+
				" from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as ppc left join "+
				" 	("+
				" 		select tsv.visceraCode"+
				" 		from  CoresStudy.dbo.tblTissueSliceIndex as tsi"+
				" 			  left join CoresStudy.dbo.tblTissueSliceSn as tss"+
				" 			on tsi.id = tss.tissueSliceIndexId left join CoresStudy.dbo.tblTissueSliceViscera as tsv"+
				" 			on tss.id = tsv.tissueSliceSnId "+
				" 		where tsi.studyNo = :studyNo and tsi.sliceCodeType = 0"+
				" 	) as r on ppc.visceraCode = r.visceraCode"+
				" where ppc.studyNo = :studyNo and ppc.histopathCheckFlag = 1 and ppc.visceraCode is not null and r.visceraCode is null";
			
		}
		
		List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListByStudyNo(String studyNo){
		String sql = "select sliceIndex.id,sliceIndex.sliceCodeType,sliceIndex.operatorSign,"+
					" 	convert(varchar(10),sliceIndex.createTime,120) as createTime,es.signer as creator"+
					" from CoresStudy.dbo.tblTissueSliceIndex as sliceIndex left join CoresUserPrivilege.dbo.tblES"+
					" 	as es on sliceIndex.operatorSign = es.esId"+
					" where sliceIndex.studyNo = :studyNo";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getGroupMapListByIndexId(String indexId) {
		String sql = "select dose.dosageNum,dose.dosageDesc"+
					" from coresstudy.dbo.tblTissueSliceIndex as sliceindex left join CoresStudy.dbo.tblTissueSliceScope as scope"+
					" 	on sliceindex.id = scope.tissueSliceIndexId left join coresstudy.dbo.tblDoseSetting as dose"+
					" 	on scope.groupId = dose.dosageNum and sliceindex.studyNo = dose.studyNo"+
					" where sliceindex.id = :indexId"+
					" order by dose.dosageNum ";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
				.setParameter("indexId", indexId)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return maplist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFixedVisceraTissueCodeByStudyNo(
			String studyNo) {
		String sql = "select r.animalCode,r.visceraFixedRecordId,"+
					" 		r.visceraOrTissueName,r.visceraType,r.visceraCode,"+
					"  		r.visceraName,r.subVisceraCode,r.subVisceraName,"+
					" 		r.specialFlag,r.anatomyPosCode,r.anatomyPos,r.anatomyFindingCode,"+
					" 		r.anatomyFindingFlag,r.anatomyFingding,r.bodySurfacePos"+
					" from "+
					" (select vf.animalCode,vf.id as visceraFixedRecordId,"+
					" 		vf.visceraName as visceraOrTissueName,vf.visceraType,vf.visceraCode,"+
					" 		vf.visceraName,vf.subVisceraCode,vf.subVisceraName,"+
					" 		0 as specialFlag,'' as anatomyPosCode,'' as anatomyPos,'' as anatomyFindingCode,"+
					" 		0 as anatomyFindingFlag,'' as anatomyFingding,'' as bodySurfacePos"+
					" from CoresStudy.dbo.tblVisceraFixed as vf left join "+
					" 	CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode"+
					" 	on sliceCode.sliceCodeType = 0 and vf.visceraCode = sliceCode.visceraCode"+
					" 	left join CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode2"+
					" 	on sliceCode2.sliceCodeType = 1 and vf.animalCode = sliceCode2.animalCode and  vf.visceraCode = sliceCode2.visceraCode"+
					" where vf.studyNo = :studyNo and isnull(vf.anatomyCheckRecordId,'') = '' "+
					" 	and sliceCode.tissueSliceIndexId is null and sliceCode2.tissueSliceIndexId is null"+
					" union"+
					" select vf.animalCode,vf.id as visceraFixedRecordId,"+
					" 	(case when isnull(tblc.visceraCode,'')= '' then ''  "+
					" 			when isnull(tblc.subVisceraCode,'') = '' then tblc.visceraName+' '"+
					" 			when isnull(tblc.subVisceraCode,'') != '' then tblc.subVisceraName+' ' "+
					" 			else '' end "+
					" 			+case when isnull(tblc.bodySurfacePos,'') = '' then '' else (tblc.bodySurfacePos+' ') end"+
					" 			+case when isnull(tblc.anatomyPos,'') = '' then '' else (tblc.anatomyPos+' ') end"+
					" 			+case when isnull(tblc.anatomyFingding,'') = '' then '' else (tblc.anatomyFingding+' ') end"+
					" 	)   as visceraOrTissueName,tblc.visceraType,tblc.visceraCode,"+
					" 			tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName,"+
					" 			tblc.specialFlag,tblc.anatomyPosCode,tblc.anatomyPos,tblc.anatomyFindingCode,"+
					" 		tblc.anatomyFindingFlag,tblc.anatomyFingding,tblc.bodySurfacePos"+
					" from CoresStudy.dbo.tblVisceraFixed as vf left join CoresStudy.dbo.TblAnatomyCheck as tblc"+
					" 	on vf.anatomyCheckRecordId = tblc.id left join CoresStudy.dbo.tblTissueSliceIndex as si"+
					" 		on vf.studyNo = si.studyNo  "+
					" 		left join CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode2"+
					" 	on sliceCode2.sliceCodeType = 1 and vf.animalCode = sliceCode2.animalCode and  vf.id = sliceCode2.visceraFixedRecordId"+
					" where vf.studyNo = :studyNo and isnull(vf.anatomyCheckRecordId,'') != ''   and tblc.id is not null "+
					" 	and sliceCode2.tissueSliceIndexId is null"+
					" ) as r"+
					" order by r.animalCode";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyCheckTissueCodeByStudyNo(String studyNo){
		
		String sql = "select tblc.animalCode,'' as visceraFixedRecordId,"+
					" 	(case when isnull(tblc.visceraCode,'')= '' then ''  "+
					" 			when isnull(tblc.subVisceraCode,'') = '' then tblc.visceraName+' '"+
					" 			when isnull(tblc.subVisceraCode,'') != '' then tblc.subVisceraName+' ' "+
					" 			else '' end "+
					" 			+case when isnull(tblc.bodySurfacePos,'') = '' then '' else (tblc.bodySurfacePos+' ') end"+
					" 			+case when isnull(tblc.anatomyPos,'') = '' then '' else (tblc.anatomyPos+' ') end"+
					" 			+case when isnull(tblc.anatomyFingding,'') = '' then '' else (tblc.anatomyFingding+' ') end"+
					" 	)   as visceraOrTissueName,tblc.visceraType,tblc.visceraCode,"+
					" 			tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName,"+
					" 			tblc.specialFlag,tblc.anatomyPosCode,tblc.anatomyPos,tblc.anatomyFindingCode,"+
					" 		tblc.anatomyFindingFlag,tblc.anatomyFingding,tblc.bodySurfacePos"+
					" from CoresStudy.dbo.tblAnatomyCheck as tblc left join CoresStudy.dbo.tblVisceraFixed as vf"+
					" 	on tblc.id = vf.anatomyCheckRecordId  left join "+
					" 	CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode"+
					" 	on sliceCode.sliceCodeType = 1 and tblc.animalCode = sliceCode.animalCode "+
					" 	and isnull(tblc.anatomyFindingCode,'') = isnull(sliceCode.anatomyFindingCode,'') "+
					" 	and isnull(tblc.visceraCode,'') = isnull(sliceCode.visceraCode,'')"+
					" 	and isnull(tblc.subVisceraCode,'') = isnull(sliceCode.subVisceraCode,'')  "+
					" 	and isnull(tblc.anatomyPosCode,'') = isnull(sliceCode.anatomyPosCode,'') "+
					" 	and isnull(tblc.bodySurfacePos,'') = isnull(sliceCode.bodySurfacePos,'') "+
					" where tblc.studyNo = :studyNo and tblc.autolyzaFlag = 0 and vf.id is null and sliceCode.tissueSliceIndexId is null" +
					" order by tblc.animalCode";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public TblTissueSliceIndex addOneFor1(TblTissueSliceIndex tblTissueSliceIndex,
			List<TblTissueSliceSn> tblTissueSliceSnList,
			List<TblTissueSliceViscera> tblTissueSliceVisceraList) {
		//1.准备切片索引
		String sliceIndexId = getKey();
		tblTissueSliceIndex.setCreateTime(new Date());
		tblTissueSliceIndex.setId(sliceIndexId);
		
		//2.准备切片编号
		//3.准备切片脏器
		int index = 0;
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			String sliceSnId = getKey("TblTissueSliceSn");
			obj.setTissueSliceIndexId(sliceIndexId);
			obj.setId(sliceSnId);
			
			TblTissueSliceViscera tblTissueSliceViscera = tblTissueSliceVisceraList.get(index);
			tblTissueSliceViscera.setId(getKey("TblTissueSliceViscera"));
			tblTissueSliceViscera.setTissueSliceSnId(sliceSnId);
			
			index++;
		}
		
		//4.保存
		getSession().save(tblTissueSliceIndex);
		
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			getSession().save(obj);
		}
		for(TblTissueSliceViscera obj:tblTissueSliceVisceraList){
			getSession().save(obj);
		}
		
		return tblTissueSliceIndex;
	}

	public void updateOne(TblTissueSliceIndex tblTissueSliceIndex,
			List<TblTissueSliceSn> tblTissueSliceSnList,
			List<TblTissueSliceViscera> tblTissueSliceVisceraList) {
		//1.准备切片索引
		String sliceIndexId = tblTissueSliceIndex.getId();
		tblTissueSliceIndex.setCreateTime(new Date());
		
		//2准备切片编号
		//3.准备切片脏器
		int index = 0;
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			String sliceSnId = getKey("TblTissueSliceSn");
			obj.setTissueSliceIndexId(sliceIndexId);
			obj.setId(sliceSnId);
			
			TblTissueSliceViscera tblTissueSliceViscera = tblTissueSliceVisceraList.get(index);
			tblTissueSliceViscera.setId(getKey("TblTissueSliceViscera"));
			tblTissueSliceViscera.setTissueSliceSnId(sliceSnId);
			
			index++;
		}
		//4.删除原切片范围，脏器，编号
		
		String sql_viscera = "delete from CoresStudy.dbo.tblTissueSliceViscera"+
							" from CoresStudy.dbo.tblTissueSliceViscera as viscera left join CoresStudy.dbo.tblTissueSliceSn as sn"+
							" 	on viscera.tissueSliceSnId = sn.id"+
							" where sn.tissueSliceIndexId = :tissueSliceIndexId";
		getSession().createSQLQuery(sql_viscera)
					.setParameter("tissueSliceIndexId", sliceIndexId)
					.executeUpdate();
		
		String sql_sn = "delete"+
						" from CoresStudy.dbo.tblTissueSliceSn"+
						" where tissueSliceIndexId = :tissueSliceIndexId";
		getSession().createSQLQuery(sql_sn)
					.setParameter("tissueSliceIndexId", sliceIndexId)
					.executeUpdate();
		
		//5.保存
		getSession().update(tblTissueSliceIndex);
		
		for(TblTissueSliceSn obj:tblTissueSliceSnList){
			getSession().save(obj);
		}
		for(TblTissueSliceViscera obj:tblTissueSliceVisceraList){
			getSession().save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeByIndexId(String id) {
//		String sql = "select sn.animalCode,sn.sliceCode,viscera.visceraType,viscera.visceraCode,visceraFixedRecordId,"+
//					" case when isnull(visceraFixedRecordId,'') ='' then viscera.visceraName else "+
//					" 	(case when isnull(tblc.visceraCode,'')= '' then ''  "+
//					" 			when isnull(tblc.subVisceraCode,'') = '' then tblc.visceraName+' '"+
//					" 			when isnull(tblc.subVisceraCode,'') != '' then tblc.subVisceraName+' ' "+
//					" 			else '' end "+
//					" 		+ case when isnull(tblc.bodySurfacePos,'') = '' then '' else (tblc.bodySurfacePos+' ') end"+
//					" 		  		+case when isnull(tblc.anatomyPos,'') = '' then '' else (tblc.anatomyPos+' ') end"+
//					" 		 	 	+case when isnull(tblc.pos,'') = '' then '' else (tblc.pos+' ') end"+
//					" 		  		+case when isnull(tblc.number,'') = '' then '' else (tblc.number+' ') end"+
//					" 		  		+case when isnull(tblc.range,'') = '' then '' else (tblc.range+' ') end"+
//					" 		 		+case when isnull(tblc.size,'') = '' then '' else (tblc.size+' ') end"+
//					" 		  		+case when isnull(tblc.color,'') = '' then '' else (tblc.color+' ') end"+
//					" 		  		+case when isnull(tblc.texture,'') = '' then '' else (tblc.texture+' ') end"+
//					" 		 	 	+case when isnull(tblc.shape,'') = '' then '' else (tblc.shape+' ') end"+
//					" 		  		+case when isnull(tblc.anatomyFingding,'') = '' then '' else (tblc.anatomyFingding+' ') end"+
//					" 		  		+case when isnull(tblc.lesionDegree,'') = '' then '' else (tblc.lesionDegree+' ') end"+
//					" 		  		)  end  as visceraOrTissueName"+
//	
//					" from CoresStudy.dbo.tblTissueSliceSn as sn  left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
//					" 	on sn.id = viscera.tissueSliceSnId left join CoresStudy.dbo.tblVisceraFixed as fixed"+
//					" 	on viscera.visceraFixedRecordId = fixed.id left join CoresStudy.dbo.TblAnatomyCheck as tblc "+
//					" 	on fixed.anatomyCheckRecordId = tblc.id "+
//
//					" where sn.tissueSliceIndexId = :id";
		String sql = "select sn.animalCode,sn.sliceCode,viscera.visceraType,viscera.visceraCode,visceraFixedRecordId,"+
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
	"					viscera.anatomyFindingFlag,viscera.anatomyFingding,viscera.bodySurfacePos,viscera.isHandwork"+
	
	"				 from CoresStudy.dbo.tblTissueSliceSn as sn  left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
	"				 	on sn.id = viscera.tissueSliceSnId  "+

					" where sn.tissueSliceIndexId = :id";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("id", id)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoSliceCodeListByStudyNo(String studyNo){
		
		String sql = "select r.animalCode,r.visceraFixedRecordId,"+
					" 		r.visceraOrTissueName,r.visceraType,r.visceraCode,"+
					" 		r.visceraName,r.subVisceraCode,r.subVisceraName,"+
					" 		r.specialFlag,r.anatomyPosCode,r.anatomyPos,r.anatomyFindingCode,"+
					"  		r.anatomyFindingFlag,r.anatomyFingding,r.bodySurfacePos"+
					" from "+
					" (select vf.animalCode,vf.id as visceraFixedRecordId,"+
					" 		vf.visceraName as visceraOrTissueName,vf.visceraType,vf.visceraCode,"+
					" 		vf.visceraName,vf.subVisceraCode,vf.subVisceraName,"+
					" 		0 as specialFlag,'' as anatomyPosCode,'' as anatomyPos,'' as anatomyFindingCode,"+
					" 		0 as anatomyFindingFlag,'' as anatomyFingding,'' as bodySurfacePos"+
					" from CoresStudy.dbo.tblVisceraFixed as vf left join "+
					" 	CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode"+
					" 	on sliceCode.sliceCodeType = 0 and vf.visceraCode = sliceCode.visceraCode"+
					" 	left join CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode2"+
					" 	on sliceCode2.sliceCodeType = 1 and vf.animalCode = sliceCode2.animalCode and  vf.visceraCode = sliceCode2.visceraCode"+
					" where vf.studyNo = :studyNo and isnull(vf.anatomyCheckRecordId,'') = '' "+
					" 	and sliceCode.tissueSliceIndexId is null and sliceCode2.tissueSliceIndexId is null"+
					" union"+
					" select tblc.animalCode,vf.id as visceraFixedRecordId,"+
					" 	(case when isnull(tblc.visceraCode,'')= '' then ''  "+
					" 			when isnull(tblc.subVisceraCode,'') = '' then tblc.visceraName+' '"+
					" 			when isnull(tblc.subVisceraCode,'') != '' then tblc.subVisceraName+' ' "+
					" 			else '' end "+
					" 			+case when isnull(tblc.bodySurfacePos,'') = '' then '' else (tblc.bodySurfacePos+' ') end"+
					" 			+case when isnull(tblc.anatomyPos,'') = '' then '' else (tblc.anatomyPos+' ') end"+
					" 			+case when isnull(tblc.anatomyFingding,'') = '' then '' else (tblc.anatomyFingding+' ') end"+
					" 	)   as visceraOrTissueName,tblc.visceraType,tblc.visceraCode,"+
					" 			tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName,"+
					" 			tblc.specialFlag,tblc.anatomyPosCode,tblc.anatomyPos,tblc.anatomyFindingCode,"+
					" 		tblc.anatomyFindingFlag,tblc.anatomyFingding,tblc.bodySurfacePos"+
					" from CoresStudy.dbo.tblAnatomyCheck as tblc left join CoresStudy.dbo.tblVisceraFixed as vf"+
					" 	on tblc.id = vf.anatomyCheckRecordId  left join "+
					" 	CoresStudy.dbo.tissueSliceCode_IndexSnViscera(:studyNo) as sliceCode"+
					" 	on sliceCode.sliceCodeType = 1 and tblc.animalCode = sliceCode.animalCode "+
					" 	and isnull(tblc.anatomyFindingCode,'') = isnull(sliceCode.anatomyFindingCode,'') "+
					" 	and isnull(tblc.visceraCode,'') = isnull(sliceCode.visceraCode,'')"+
					" 	and isnull(tblc.subVisceraCode,'') = isnull(sliceCode.subVisceraCode,'')  "+
					" 	and isnull(tblc.anatomyPosCode,'') = isnull(sliceCode.anatomyPosCode,'') "+
					" 	and isnull(tblc.bodySurfacePos,'') = isnull(sliceCode.bodySurfacePos,'') "+
					" where tblc.studyNo = :studyNo and tblc.autolyzaFlag = 0  and sliceCode.tissueSliceIndexId is null"+
					" ) as r"+
					" order by r.animalCode";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
		
	}

	@SuppressWarnings("unchecked")
	public List<TblTissueSliceIndex> getAddToListByStudyNo(String studyNo){
		String hql = "from TblTissueSliceIndex where studyNo = ? and sliceCodeType = 2" +
				" order by createTime ";
		List<TblTissueSliceIndex> tblTissueSliceIndexList = getSession().createQuery(hql)
															.setParameter(0, studyNo)
															.list();
		return tblTissueSliceIndexList;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblTissueSliceIndex> getFCGListByStudyNo(String studyNo){
		String hql = "from TblTissueSliceIndex where studyNo = ? and sliceCodeType = 1" +
		" order by createTime ";
		List<TblTissueSliceIndex> tblTissueSliceIndexList = getSession().createQuery(hql)
		.setParameter(0, studyNo)
		.list();
		return tblTissueSliceIndexList;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getGroupIdListByIndexId(String id) {
		String sql = "select scope.groupId"+
					" from coresstudy.dbo.tblTissueSliceIndex as sliceindex left join CoresStudy.dbo.tblTissueSliceScope as scope"+
					" 	on sliceindex.id = scope.tissueSliceIndexId "+
					" where sliceindex.id = :indexId"+
					" order by scope.groupId ";
			List<Integer> groupNumlist = getSession().createSQLQuery(sql)
													.setParameter("indexId", id)
													.list();
		return groupNumlist;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceSnVisceraMapListByIndexId(String id) {
		String sql = "select sliceSn.sliceCode,sliceViscera.visceraType,sliceViscera.visceraCode,sliceViscera.visceraName," +
					" sliceViscera.subVisceraCode,sliceViscera.subVisceraName,dictViscera.sn,dictViscera2.sn as subSn" +
					" from CoresStudy.dbo.tblTissueSliceIndex as sliceIndex left join CoresStudy.dbo.tblTissueSliceSn as sliceSn" +
					" 	on sliceIndex.id = sliceSn.tissueSliceIndexId left join CoresStudy.dbo.tblTissueSliceViscera as sliceViscera" +
					" 	on sliceSn.id = sliceViscera.tissueSliceSnId left join CoresSystemSet.dbo.dictViscera as dictViscera" +
					" 	on sliceViscera.visceraCode = dictViscera.visceraCode left join CoresSystemSet.dbo.dictViscera as dictViscera2" +
					" 	on sliceViscera.subVisceraCode is not null and sliceViscera.subVisceraCode = dictViscera2.visceraCode" +
					" where sliceIndex.id = :indexId " +
					" order by sliceIndex.sliceCodeType,sliceIndex.createTime,sliceSn.animalCode,"+
					" 	(case when sliceIndex.sliceCodeType = 0 then cast(sliceSn.sliceCode as int )"+
					" 		else 9999 end )";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("indexId", id)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public Integer getSliceCodeNum(String studyNo) {
		String sql = "select count(sliceSn.sliceCode)"+
					" from CoresStudy.dbo.tblTissueSliceIndex as sliceIndex left join CoresStudy.dbo.tblTissueSliceSn as sliceSn"+
					" 	on sliceIndex.id = sliceSn.tissueSliceIndexId "+
					" where sliceIndex.studyNo = :studyNo and sliceSn.sliceCode is not null and isnull(sliceIndex.operatorSign,'') != '' " +
					"  and (sliceIndex.sliceCodeType = 0 or sliceIndex.sliceCodeType = 2)";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).uniqueResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSliceCodeByStudyNo(String studyNo) {
		String sql = "select ssindex.id as indexId, sn.sliceCode,"+
" 					  "+
" 					 	(case when isnull(viscera.visceraCode,'')= '' then ''  "+
" 					 			when isnull(viscera.subVisceraCode,'') = '' then viscera.visceraName+' '"+
" 					 			when isnull(viscera.subVisceraCode,'') != '' then viscera.subVisceraName+' ' "+
" 					 			else '' end "+
" 					 		+ case when isnull(viscera.bodySurfacePos,'') = '' then '' else (viscera.bodySurfacePos+' ') end"+
" 					 		  		+case when isnull(viscera.anatomyPos,'') = '' then '' else (viscera.anatomyPos+' ') end"+
" 				 		  		+case when isnull(viscera.anatomyFingding,'') = '' then '' else (viscera.anatomyFingding+' ') end"+
" 					 		  		)    as visceraOrTissueName"+
	
					" from CoresStudy.dbo.tblTissueSliceIndex as ssindex left join  CoresStudy.dbo.tblTissueSliceSn as sn  "+
					" 	on ssindex.id = sn.tissueSliceIndexId"+
					" 	left join CoresStudy.dbo.tblTissueSliceViscera as viscera"+
					" 	on sn.id = viscera.tissueSliceSnId " +
					" where ssindex.studyNo = :studyNo "+
					" order by ssindex.sliceCodeType,ssindex.createTime,sn.animalCode,"+
					" 	(case when ssindex.sliceCodeType = 0 then cast(sn.sliceCode as int )"+
					" 		else 9999 end )";
		List<Map<String,Object>> maplist = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return maplist;
	}

	public String getPreTaskIdByStudyNo(String studyNo) {
		String sql = "select TOP (1) t.taskId"+
					" from CoresStudy.dbo.tblTissueSliceIndex as t"+
					" where t.studyNo = :studyNo and t.sliceCodeType = 0"+
					" order by t.createTime desc ";
		String preTaskId = (String) getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).uniqueResult();
		return preTaskId;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOtherVisceraCodeAndName(String studyNo,
			boolean showHistopathViscera) {
		//1.判断是否有病理计划
		String count_sql = "select count(id) "+
						" from CoresStudy.dbo.tblPathPlanCheck "+
						" where studyNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(count_sql).setParameter(0, studyNo).uniqueResult();
		
		String sql ="";
		if(null != count && count >0 ){
			//2.有病理计划
			sql = "select dv.visceraType,dv.visceraName,dv.visceraCode,dv.sn"+
				" 					 	,dv2.visceraCode as subVisceraCode,dv2.visceraName as subVisceraName,dv2.sn as subSn"+
				" from CoresStudy.dbo.tblStudyPlan as study "+
				" 	left join CoresSystemSet.dbo.dictViscera as dv on isnull(dv.pVisceraCode,'') = ''"+
				" 	left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal on dv.animalFlag = 0 "+
				" 		and dv.visceraCode = dvAnimal.visceraCode and dvAnimal.animalTypeName = study.animalType"+
				" 	left join CoresStudy.dbo.tblPathPlanCheck as ppc"+
				" 		on ppc.studyNo = study.studyNo and dv.visceraCode= ppc.visceraCode	"+
				" 		and ((1 = :showHistopathViscera and ppc.histopathCheckFlag = 1) or(0 = :showHistopathViscera ))"+
				" 	left join CoresSystemSet.dbo.dictViscera as dv2 on dv.visceraCode = dv2.pVisceraCode"+
				" 	left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal2 on dv2.animalFlag = 0 "+
				" 		and dv2.visceraCode = dvAnimal2.visceraCode and dvAnimal2.animalTypeName = study.animalType "+
				" 		"+
				" where study.studyNo = :studyNo and isnull(dv.pVisceraCode,'') = '' and (dv.animalFlag = 1 or dvAnimal.visceraCode is not null)"+
				" 	and ppc.visceraCode is null "+
				" 	and (dv2.animalFlag = 1 or dvAnimal2.visceraCode is not null or dv2.visceraCode is null)"+
				" order by dv.sn,dv2.sn";
		}else{
			//3.无病理计划
			sql = "select dv.visceraType,dv.visceraName,dv.visceraCode,dv.sn"+
				" 					 	,dv2.visceraCode as subVisceraCode,dv2.visceraName as subVisceraName,dv2.sn as subSn"+
				" from CoresStudy.dbo.tblStudyPlan as study "+
				" 	left join CoresSystemSet.dbo.dictViscera as dv on isnull(dv.pVisceraCode,'') = ''"+
				" 	left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal on dv.animalFlag = 0 "+
				" 		and dv.visceraCode = dvAnimal.visceraCode and dvAnimal.animalTypeName = study.animalType"+
				" 	left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as ppc"+
				" 		on ppc.studyNo = study.studyNo and dv.visceraCode= ppc.visceraCode	"+
				" 		and ((1 = :showHistopathViscera and ppc.histopathCheckFlag = 1) or(0 = :showHistopathViscera ))"+
				" 	left join CoresSystemSet.dbo.dictViscera as dv2 on dv.visceraCode = dv2.pVisceraCode"+
				" 	left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal2 on dv2.animalFlag = 0 "+
				" 		and dv2.visceraCode = dvAnimal2.visceraCode and dvAnimal2.animalTypeName = study.animalType "+
				" 		"+
				" where study.studyNo = :studyNo and isnull(dv.pVisceraCode,'') = '' and (dv.animalFlag = 1 or dvAnimal.visceraCode is not null)"+
				" 	and ppc.visceraCode is null "+
				" 	and (dv2.animalFlag = 1 or dvAnimal2.visceraCode is not null or dv2.visceraCode is null)"+
				" order by dv.sn,dv2.sn";
			
		}
		List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setParameter("showHistopathViscera", showHistopathViscera ? 1:0)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListByStudyNoVisceraCode(
			String studyNo, String visceraCode) {
		String sql = "select dv.visceraType,dv.visceraName,dv.visceraCode,dv.sn"+
					" 	,dv2.visceraCode as subVisceraCode,dv2.visceraName as subVisceraName,dv2.sn as subSn"+
					" from CoresStudy.dbo.tblStudyPlan as study "+
					" 		left join CoresSystemSet.dbo.dictViscera as dv 	on dv.visceraCode = :visceraCode "+
					" 		left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal on dv.animalFlag = 0 "+
					" 			and dv.visceraCode = dvAnimal.visceraCode and dvAnimal.animalTypeName = study.animalType"+
					" 		left join CoresSystemSet.dbo.dictViscera as dv2 on dv.visceraCode = dv2.pVisceraCode"+
					" 		left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal2 on dv2.animalFlag = 0 "+
					" 			and dv2.visceraCode = dvAnimal2.visceraCode and dvAnimal2.animalTypeName = study.animalType "+
					
					" where study.studyNo = :studyNo and isnull(dv.pVisceraCode,'') = '' and (dv.animalFlag = 1 or dvAnimal.visceraCode is not null)"+
					" 		and (dv2.animalFlag = 1 or dvAnimal2.visceraCode is not null or dv2.visceraCode is null)"+
					" order by dv.sn,dv2.sn";
			
			List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setParameter("visceraCode", visceraCode)
														.setResultTransformer(new MapResultTransformer())
														.list();
			return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapListByStudyNoVisceraCodeList(
			String studyNo,List<String> visceraCodeList) {
		String sql = "select dv.visceraType,dv.visceraName,dv.visceraCode,dv.gender,dv.sn"+
					" 	,dv2.visceraCode as subVisceraCode,dv2.visceraName as subVisceraName,dv2.sn as subSn"+
					" from CoresStudy.dbo.tblStudyPlan as study "+
					" 		left join CoresSystemSet.dbo.dictViscera as dv 	on dv.visceraCode in (:visceraCodeList) "+
					" 		left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal on dv.animalFlag = 0 "+
					" 			and dv.visceraCode = dvAnimal.visceraCode and dvAnimal.animalTypeName = study.animalType"+
					" 		left join CoresSystemSet.dbo.dictViscera as dv2 on dv.visceraCode = dv2.pVisceraCode"+
					" 		left join CoresSystemSet.dbo.dictVisceraAnimal as dvAnimal2 on dv2.animalFlag = 0 "+
					" 			and dv2.visceraCode = dvAnimal2.visceraCode and dvAnimal2.animalTypeName = study.animalType "+
					
					" where study.studyNo = :studyNo and isnull(dv.pVisceraCode,'') = '' and (dv.animalFlag = 1 or dvAnimal.visceraCode is not null)"+
					" 		and (dv2.animalFlag = 1 or dvAnimal2.visceraCode is not null or dv2.visceraCode is null)"+
					" order by dv.sn,dv2.sn";
		
		List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setParameterList("visceraCodeList", visceraCodeList)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getAnimalCodeListByStudyNo(String studyNo){
		String sql = "SELECT animalCode,gender"+
					" FROM [CoresStudy].[dbo].[tblAnimalDetailDissectPlan]"+
					" WHERE   studyNo = ? "+
					" order by animalCode";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.setResultTransformer(new MapResultTransformer())
									.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>  getAllVisceraCodeAndName(String studyNo,int gender) {
		String animalTypeName = "";
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
		if(null != tblStudyPlan){
			if(null != tblStudyPlan.getAnimalType()){
				animalTypeName = tblStudyPlan.getAnimalType();
			}
		}
		String sql = "select viscera1.visceraCode,viscera1.visceraName,viscera1.visceraType,viscera2.visceraCode as subVisceraCode,"+
		" 	viscera2.visceraName as subVisceraName,viscera1.sn,viscera2.subSN"+
		" from("+//--    一级脏器
		" 	select viscera.visceraCode,viscera.visceraName,viscera.visceraType,viscera.animalFlag,viscera.gender,"+
		" 		vAnimal.animalTypeName,viscera.sn"+
		" 	from CoresSystemSet.dbo.dictViscera as viscera left join CoresSystemSet.dbo.dictVisceraAnimal vAnimal"+
		" 		on viscera.animalFlag = 0 and viscera.visceraCode = vAnimal.visceraCode"+
		" 	where( viscera.pVisceraCode is null or viscera.pVisceraCode = '') and (gender = 0 or :gender = 0 or gender = :gender)"+
		" 		and (vAnimal.animalTypeName = :animalTypeName or viscera.animalFlag = 1)    ) as viscera1 "+
		" 		"+
		" 	left join "+
		" 	"+
		" 	("+//--    二级脏器
		" 	select subvis.visceraCode,subvis.visceraName,subvis.pVisceraCode,subvis.animalFlag,subvis.gender,"+
		" 		vAnimal.animalTypeName,subvis.sn as subSN"+
		" 	from CoresSystemSet.dbo.dictViscera as subvis left join CoresSystemSet.dbo.dictVisceraAnimal vAnimal"+
		" 		on subvis.animalFlag = 0 and subvis.visceraCode = vAnimal.visceraCode"+
		" 	where( subvis.pVisceraCode is not null and subvis.pVisceraCode <>'') and (gender = 0 or :gender = 0 or gender = :gender)"+
		" 		and (vAnimal.animalTypeName = :animalTypeName or subvis.animalFlag = 1)     )as viscera2"+
		" 		"+
		" 	on viscera1.visceraCode = viscera2.pVisceraCode"+
		" 	"+
		" order by viscera1.sn,viscera2.subSN";
		
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
		.setParameter("animalTypeName", animalTypeName)
		.setParameter("gender", gender)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<TblTissueSliceIndex> getAllByTaskId(String taskId) {
		String hql = "FROM      TblTissueSliceIndex"+
					" WHERE   taskId = ? "+
					" ORDER BY sliceCodeType, id";
		List<TblTissueSliceIndex> list = getSession().createQuery(hql)
													.setParameter(0, taskId)
													.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblTissueSliceIndex> getAllSignedByTaskId(String taskId) {
		String hql = "FROM   TblTissueSliceIndex"+
					" WHERE   taskId = ?  and operatorSign is not null and operatorSign != '' "+
					" ORDER BY sliceCodeType, id";
		List<TblTissueSliceIndex> list = getSession().createQuery(hql)
												.setParameter(0, taskId)
												.list();
		return list;
	}

	public TblTissueSliceIndex signTissueSliceIndex(
			TblTissueSliceIndex tblTissueSliceIndex, String realName) {
		List<String> dataIdList = new ArrayList<String>();
		dataIdList.add(tblTissueSliceIndex.getId());
		String operatorSign = writeES(realName,263,"组织取材编号签字确认",
				"TblTissueSliceIndex",dataIdList);
		tblTissueSliceIndex.setOperatorSign(operatorSign);
		getSession().update(tblTissueSliceIndex);
		return tblTissueSliceIndex;
	}

	/**签字
	 * @param siger
	 * @param esType
	 * @param esTypeDesc
	 * @param tableName
	 * @param dataIdList
	 * @return
	 */
	private String writeES(String siger,int esType,String esTypeDesc,String tableName,List<String> dataIdList){
		String esId = tblESService.getKey("TblES");
		Date date = new Date();
		
		TblES tblES=new TblES();
		tblES.setEsId(esId);
		tblES.setSigner(siger);
		tblES.setEsType(esType);
		tblES.setEsTypeDesc(esTypeDesc);
		tblES.setDateTime(date);
		tblESService.save(tblES);//保存  电子签名
		//签名链接表
		TblESLink tblESLink = null;
		for(String dataId :dataIdList){
			//签名链接表
			tblESLink =new TblESLink();
			tblESLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLink.setDataId(dataId);
			tblESLink.setTableName(tableName);
			tblESLink.setEsType(esType);
			tblESLink.setEsTypeDesc(esTypeDesc);
			tblESLink.setRecordTime(date);
			tblESLink.setTblES(tblES);
			tblESLinkService.save(tblESLink);
		}
		
		return esId;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getAnimalCode2NumberMap(String studyNo) {
		String sql = "select ss.animalCode,count(ss.sliceCode) as number"+
					" from CoresStudy.dbo.tblTissueSliceIndex as si left join "+
					" 	CoresStudy.dbo.tblTissueSliceSn as ss on si.id = ss.tissueSliceIndexId"+
					" where si.studyNo = :studyNo and si.sliceCodeType = 1 and isnull(si.operatorSign,'') != ''"+
					" group by ss.animalCode";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		Map<String,Integer> animalCode2NumberMap = new HashMap<String,Integer>();
		if(null != mapList && mapList.size() > 0){
			for(Map<String,Object> obj:mapList){
				String animalCode = (String) obj.get("animalCode");
				Integer number = (Integer) obj.get("number");
				animalCode2NumberMap.put(animalCode, number);
			}
		}
		return animalCode2NumberMap;
	}
}
