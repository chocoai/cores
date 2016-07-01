package com.lanen.service.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblAnatomyCheck;
import com.lanen.model.path.TblVisceraFixed;
import com.lanen.model.path.TblVisceraFixedHis;
import com.lanen.model.path.TblVisceraQueRu;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.TblStudyPlanService;
/**
 * 剖检记录
 * @author Administrator
 *
 */
@Service
public class TblAnatomyCheckServiceImpl extends BaseDaoImpl<TblAnatomyCheck> implements TblAnatomyCheckService{

	/**
	 * 剖检动物Service
	 */
	@Resource
	private TblAnatomyAnimalService tblAnatomyAnimalService;
	
	/**
	 * 脏器固定Service
	 */
	@Resource
	private TblVisceraFixedService tblVisceraFixedService;
	/**
	 * 专题计划service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraCodeAndName(String taskId,String studyNo,String animalCode) {
		TblAnatomyAnimal tblAnatomyAnimal = tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
		Integer gender = 0;
		if(null != tblAnatomyAnimal){
			gender = tblAnatomyAnimal.getGender();
		}
		String animalTypeName = "";
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
		if(null != tblStudyPlan){
			if(null != tblStudyPlan.getAnimalType()){
				animalTypeName = tblStudyPlan.getAnimalType();
			}
		}
		String sql = "select tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.sn ,dict2.sn as subsn"+ 
					" from (  "+
					" 	select distinct tblc.visceraType,tblc.visceraCode,tblc.visceraName,dictviscera.visceraCode as subVisceraCode,dictviscera.visceraName as subVisceraName "+
					" 	from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
					" 		on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  " +
					" 			left join ("+
					" 								select v.visceraCode,v.visceraName,v.pVisceraCode "+
					" 								from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal"+
					" 									on vAnimal.visceraCode = v.visceraCode"+
					" 								where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName)"+
		
					" 								) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode"+
					" 	where tblt.taskId =  :taskId and tblc.atanomyCheckFlag = 1" +
					" 	union  "+
					" 	select distinct tblc.visceraType,tblc.visceraCode,tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName  "+
					" 	from CoresStudy.dbo.TblPathSession as tblp left join CoresStudy.dbo.TblAnatomyCheck as tblc  "+
					" 		on tblp.sessionId = tblc.sessionId  "+
					" 	where tblp.taskId =  :taskId  and tblc.visceraType is not null and tblc.visceraCode is not null " +
					"        and tblc.visceraName is not null and tblc.animalCode = :animalCode"+
					" 	) as tblv left join CoresSystemSet.dbo.dictViscera as dict "+
					" 		on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2 "+
					" 		 	on tblv.subvisceraCode = dict2.visceraCode "+
					" where dict.gender =0 or dict.gender = :gender"+
					" order by sn,subsn";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
														.setParameter("taskId", taskId)
														.setParameter("animalCode", animalCode)
														.setParameter("gender", gender)
														.setParameter("animalTypeName", animalTypeName)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOtherVisceraCodeAndName(String taskId,String studyNo,String animalCode) {
		Integer gender = 0;
		TblAnatomyAnimal tblAnatomyAnimal = tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
		if(null != tblAnatomyAnimal){
			gender = tblAnatomyAnimal.getGender();
		}
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
					" 	where( viscera.pVisceraCode is null or viscera.pVisceraCode = '') and (gender = 0 or gender = :gender)"+
					" 		and (vAnimal.animalTypeName = :animalTypeName or viscera.animalFlag = 1)    ) as viscera1 "+
					" 		"+
					" 	left join "+
					" 	"+
					" 	("+//--    二级脏器
					" 	select subvis.visceraCode,subvis.visceraName,subvis.pVisceraCode,subvis.animalFlag,subvis.gender,"+
					" 		vAnimal.animalTypeName,subvis.sn as subSN"+
					" 	from CoresSystemSet.dbo.dictViscera as subvis left join CoresSystemSet.dbo.dictVisceraAnimal vAnimal"+
					" 		on subvis.animalFlag = 0 and subvis.visceraCode = vAnimal.visceraCode"+
					" 	where( subvis.pVisceraCode is not null and subvis.pVisceraCode <>'') and (gender = 0 or gender = :gender)"+
					" 		and (vAnimal.animalTypeName = :animalTypeName or subvis.animalFlag = 1)     )as viscera2"+
					" 		"+
					" 	on viscera1.visceraCode = viscera2.pVisceraCode"+
					" 	"+
					" where ( viscera1.visceraCode not in ("+// -- 计划或异常的一级脏器
					" 										select distinct tblc.visceraCode"+
					" 										from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt "+
					" 											on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  "+
					" 										where tblt.taskId =  :taskId and tblc.atanomyCheckFlag = 1"+
					" 										"+
					" 										union  "+
					" 										select distinct tblc.visceraCode"+
					" 										from CoresStudy.dbo.TblPathSession as tblp left join CoresStudy.dbo.TblAnatomyCheck as tblc "+
					" 											on tblp.sessionId = tblc.sessionId  "+
					" 										where tblp.taskId = :taskId  and tblc.visceraType is not null and tblc.visceraCode is not null "+
					" 										   and tblc.visceraName is not null and tblc.animalCode = :animalCode "+
					" 										   and (tblc.subVisceraCode is null or tblc.subVisceraCode = '')"+
					" 									)"+
					" 		and  viscera2.visceraCode is null)  "+
					" 		"+
					" 		or "+
					" 		(viscera2.visceraCode is not null and viscera2.visceraCode not in ("+//--异常的二级脏器
					" 											select distinct tblc.subVisceraCode "+
					" 											from CoresStudy.dbo.TblPathSession as tblp left join CoresStudy.dbo.TblAnatomyCheck as tblc "+
					" 												on tblp.sessionId = tblc.sessionId  "+
					" 											where tblp.taskId = :taskId  and tblc.visceraType is not null and tblc.visceraCode is not null "+
					" 												and tblc.visceraName is not null and tblc.animalCode = :animalCode and "+
					" 												tblc.subVisceraCode is not null and tblc.subVisceraCode <>''	)" +
					"			and viscera1.visceraCode not in(" +//且未在计划中
					"											select distinct tblc.visceraCode"+
					" 											from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt "+
					" 												on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  "+
					" 											where tblt.taskId =  :taskId and tblc.atanomyCheckFlag = 1"+
					"											)"+
					" 		)"+
					" order by viscera1.sn,viscera2.subSN";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
														.setParameter("taskId", taskId)
														.setParameter("animalCode", animalCode)
														.setParameter("gender", gender)
														.setParameter("animalTypeName", animalTypeName)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraMapList() {
		String sql = "select dictViscera.visceraCode,dictViscera.visceraName,dictViscera.visceraType,"+
					" 		dictViscera2.visceraCode as subVisceraCode,dictViscera2.visceraName as subVisceraName"+
					" from CoresSystemSet.dbo.dictViscera as dictViscera left join CoresSystemSet.dbo.dictViscera  as dictViscera2"+
					" 	on dictViscera.visceraCode = dictViscera2.pVisceraCode "+
					" where (dictViscera.pVisceraCode is null or dictViscera.pVisceraCode = '' )"+
					" order by dictViscera.sn";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnatomyCheck> getListBySessionId(String sessionId, String animalCode) {
		String hql = "From TblAnatomyCheck where sessionId = ? and animalCode = ?";
		List<TblAnatomyCheck> list =  getSession().createQuery(hql)
												.setParameter(0, sessionId)
												.setParameter(1, animalCode)
												.list();
		return list;
	}

	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblESService tblESService;
	
//	@Resource
//	private UserService userService;
	
	public Json saveOne(TblAnatomyCheck tblAnatomyCheck) {
		Json json = new Json();
		//检查该动物是否已经剖检完成
		boolean anatomyFinishFlag = tblAnatomyAnimalService
			.isAnatomyFinish(tblAnatomyCheck.getStudyNo(),tblAnatomyCheck.getAnimalCode());
		if(!anatomyFinishFlag){
			String id = getKey();
			tblAnatomyCheck.setId(id);
			
			int autolyzaFlag = tblAnatomyCheck.getAutolyzaFlag(); 
			if(1 == autolyzaFlag){
				String esId = tblESService.getKey("TblES");
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				es.setEsType(701);
				es.setDateTime(new Date());
				es.setEsId(esId);
				esLink.setTableName("TblAnatomyCheck");
				esLink.setDataId(tblAnatomyCheck.getId());
				esLink.setTblES(es);
				esLink.setEsType(701);
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				esLink.setEsTypeDesc("解剖所见,脏器自溶登记");
				es.setSigner(tblAnatomyCheck.getOperator());
				es.setEsTypeDesc("解剖所见,脏器自溶登记");
				tblESService.save(es);
				tblESLinkService.save(esLink);
				tblAnatomyCheck.setAutolyzeSign(esId);
			}
			getSession().save(tblAnatomyCheck);
			json.setSuccess(true);
			json.setMsg(id);
		}else{
			json.setMsg("该动物解剖已完成！");
		}
		return json;
	}

	public Json deleteOne(String id) {
		Json json = new Json();
		TblAnatomyCheck tblAnatomyCheck = getById(id);
		if(null != tblAnatomyCheck ){
			boolean anatomyFinishFlag = tblAnatomyAnimalService
			.isAnatomyFinish(tblAnatomyCheck.getStudyNo(),tblAnatomyCheck.getAnimalCode());
			if(!anatomyFinishFlag){
				getSession().delete(tblAnatomyCheck);
				String sql = "delete  CoresStudy.dbo.tblVisceraQueRu"+
							" from CoresStudy.dbo.tblVisceraQueRu as qr"+
							" where qr.anatomyCheckId = ? ";
				getSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();
				
				json.setSuccess(true);
			}else{
				json.setMsg("该动物解剖已完成,不可以删除！");
			}
		}
		return json;
	}

	public int getAnatomyCheckSumBySessionID(List<String> sessionID) {
		String sql = "select distinct animalCode,sessionId from CoresStudy.dbo.TblAnatomyCheck  as tbla where tbla.sessionId in ( :sessionId ) ";
		List<?> list =  getSession().createSQLQuery(sql)
		.setParameterList("sessionId", sessionID)
		.list();
		if(null != list && list.size() > 0 ){
			return 	list.size();
		}else{
			return 0;
		}
		
	}

	@SuppressWarnings("unchecked")   
	public List<Map<String, Object>> getAnatomyCheckBySessionIDs(
			List<String> sessionID, String studyNoSelected, String animalCodeSelected, String visceraNameSelected) {
		String sql = "select distinct tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName , " +
				"  tblc.operator , tblc.operateTime ,tbla.realName,tblc.autolyzaFlag,tblc.bodySurfacePos " +
				" ,u.realName as anatomyOperator," +
				" case when tblc.autolyzaFlag = 1 then '自溶' " +
				"  when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)  " +
				"    when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding " +
				" else" +
				"  	( case when isnull(tblc.bodySurfacePos,'') = '' then '' else tblc.bodySurfacePos+' ' end" +
				"  	+case when isnull(tblc.anatomyPos,'') = '' then '' else tblc.anatomyPos+' ' end" +
				" 	 	+case when isnull(tblc.pos,'') = '' then '' else tblc.pos+' ' end" +
				"  	+case when isnull(tblc.number,'') = '' then '' else tblc.number+' ' end" +
				"  	+case when isnull(tblc.range,'') = '' then '' else tblc.range+' ' end" +
				" 	+case when isnull(tblc.size,'') = '' then '' else tblc.size+' ' end" +
				"  	+case when isnull(tblc.color,'') = '' then '' else tblc.color+' ' end" +
				"  	+case when isnull(tblc.texture,'') = '' then '' else tblc.texture+' ' end" +
				" 	 	+case when isnull(tblc.shape,'') = '' then '' else tblc.shape+' ' end" +
				"  	+case when isnull(tblc.anatomyFingding,'') = '' then '' else tblc.anatomyFingding+' ' end" +
				"  	+case when isnull(tblc.lesionDegree,'') = '' then '' else tblc.lesionDegree+' ' end" +
				
				"  	) end  as anatomyFingding" +
				"  from CoresStudy.dbo.TblAnatomyCheck  as tblc " +
				//缺如
				"   left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId" +
				
				" left join CoresUserPrivilege.dbo.tbluser as tbla " +
				" on tblc.operator = tbla.userName " +
				" left join CoresStudy.dbo.tblAnatomyAnimal as taskanimal on tblc.sessionId = taskanimal.anatomySessionId " +
				" and tblc.animalCode = taskanimal.animalCode and tblc.studyNo = taskanimal.studyNo" +
				" left join CoresUserPrivilege.dbo.tbluser as u on taskanimal.anatomyOperator = u.userName " +
				" where tblc.sessionId in ( :sessionId ) " ;
		if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
			sql=sql+" and tblc.studyNo=:studyNoSelected";
		}
		if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
			sql=sql+" and tblc.animalCode=:animalCodeSelected";
		}
		if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
			sql=sql+" and tblc.visceraName=:visceraNameSelected";
		}
		sql=sql+" order by  tblc.studyNo,tblc.animalCode ";
		Query query =getSession().createSQLQuery(sql);
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
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameBySessionIDsAndTaskIdList(
			List<String> sessionID, List<String> taskIdList) {
		String sql = "select dv.visceraName from CoresSystemSet.dbo.dictViscera as dv join " +
				"(select distinct visceraName from CoresStudy.dbo.TblAnatomyCheck " + 
		"           where sessionID in (:sessionIds) " + 
		"         union " + 
		"         select distinct visceraName from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as tblw " + 
		"          left join CoresStudy.dbo.tblAnatomyTask as tblt  " + 
		"          on tblw.studyNo = tblt.studyNo and tblw.ReqNo = tblt.anatomyReqNo  " + 
		"          where tblt.taskId in ( :taskIdLists ) " + 
		"         union " + 
		"         select distinct visceraName from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as tblp " + 
		"          left join CoresStudy.dbo.tblAnatomyTask as tblt  " + 
		"          on tblp.studyNo = tblt.studyNo and tblp.ReqNo = tblt.anatomyReqNo  " + 
		"          where tblt.taskId in ( :taskIdLists ) ) as av " +
		"      on dv.visceraName=av.visceraName " +
		"   order by dv.sn";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
		.setParameterList("sessionIds", sessionID).setParameterList("taskIdLists", taskIdList)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}

	public Json updateOne(TblAnatomyCheck tblAnatomyCheck) {
		Json json = new Json();
		//检查该动物是否已经剖检完成
		boolean anatomyFinishFlag = tblAnatomyAnimalService
			.isAnatomyFinish(tblAnatomyCheck.getStudyNo(),tblAnatomyCheck.getAnimalCode());
		if(!anatomyFinishFlag){
			getSession().update(tblAnatomyCheck);
			json.setSuccess(true);
		}else{
			json.setMsg("该动物解剖已完成！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraCodeAndNameByStudyNo(
			String studyNo) {
		String sql = " select tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.sn ,dict2.sn as subsn "+
		  " from (  "+
		  "	   select distinct tblc.visceraType,tblc.visceraCode,tblc.visceraName,null as subVisceraCode,null as subVisceraName "+
		  "	   from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
		  "			on tblc.studyNo = tblt.studyNo and tblc.reqNo = tblt.anatomyReqNo  "+
		  "	   where tblc.studyNo  =  :studyNo  "+
		  "	   union  "+
		  "	   select distinct tblc.visceraType,tblc.visceraCode,tblc.visceraName,tblc.subVisceraCode,tblc.subVisceraName  "+
		  "	   from CoresStudy.dbo.TblPathSession as tblp left join CoresStudy.dbo.TblAnatomyCheck as tblc  "+
		  "			on tblp.sessionId = tblc.sessionId  "+
		  "	   where tblc.studyNo  =  :studyNo and tblc.visceraType is not null and tblc.visceraCode is not null and tblc.visceraName is not null "+
		  "	) as tblv left join CoresSystemSet.dbo.dictViscera as dict "+
		  "			 on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2 "+
		  "			 on tblv.subvisceraCode = dict2.visceraCode "+
		  "		order by sn,subsn ";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
		.setParameter("studyNo", studyNo)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListByStudyNoAndAnimalCode(String studyNo,
			String animalCode) {
		List<Map<String,Object>> list=null;
		String sql=" select id,visceraType,visceraCode,visceraName,autolyzaFlag,anatomyPos,bodySurfacePos,specialFlag,anatomyFingding " +
				"  from TblAnatomyCheck where studyNo=:studyNo and animalCode=:animalCode and specialFlag=1 and id not in" +
				   " (select anatomyCheckRecordId from CoresStudy.dbo.tblVisceraFixed as vf " +
				   "   where vf.animalCode=:animalCode and studyNo=:studyNo and anatomyCheckRecordId  is not null) ";
		list=getSession().createSQLQuery(sql)
		                                       .setParameter("studyNo", studyNo)
		                                       .setParameter("animalCode", animalCode)
		                                       .setResultTransformer(new MapResultTransformer())
		                                       .list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListByStudyNoAndAnimalCode(String studyNo,
			String animalCode,boolean isShowOther) {
		List<Map<String,Object>> list=null;
		String sql=" select id,visceraType,visceraCode,visceraName,subVisceraName,autolyzaFlag,anatomyPos,bodySurfacePos,specialFlag,anatomyFingding " +
		"  from TblAnatomyCheck " +
		"	where studyNo=:studyNo and animalCode=:animalCode and autolyzaFlag = 0 ";
		if(!isShowOther){
			sql = sql+" and specialFlag=1 " ;
		}
		sql = sql+" and id not in" +
		" (select anatomyCheckRecordId from CoresStudy.dbo.tblVisceraFixed as vf " +
		"   where vf.animalCode=:animalCode and studyNo=:studyNo and anatomyCheckRecordId  is not null) ";
		list=getSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setParameter("animalCode", animalCode)
						.setResultTransformer(new MapResultTransformer())
						.list();
		return list;
	}

	public boolean isHasAutolyze(String sessionId, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where sessionId = ? and animalCode = ? and autolyzaFlag =1"+
						"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
						"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, sessionId)
								.setParameter(1, animalCode)
								.setParameter(2, visceraCode)
								.setParameter(3, subVisceraCode)
								.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where sessionId = ? and animalCode = ? and autolyzaFlag =1"+
						"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
										.setParameter(0, sessionId)
										.setParameter(1, animalCode)
										.setParameter(2, visceraCode)
										.uniqueResult();
		}
		return  count > 0;
	}
	public boolean isHasRecord(String sessionId, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
			" from CoresStudy.dbo.TblAnatomyCheck "+
			" where sessionId = ? and animalCode = ? "+
//			"    and visceraCode = ? and subVisceraCode is not null" +
//			"  and subVisceraCode = ?";
			"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
			"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
			.setParameter(0, sessionId)
			.setParameter(1, animalCode)
			.setParameter(2, visceraCode)
			.setParameter(3, subVisceraCode)
			.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
			" from CoresStudy.dbo.TblAnatomyCheck "+
			" where sessionId = ? and animalCode = ? "+
			"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
			.setParameter(0, sessionId)
			.setParameter(1, animalCode)
			.setParameter(2, visceraCode)
			.uniqueResult();
		}
		return  count > 0;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndisHasAutolyzeMap(
			String sessionId, String studyNo, String animalCode,
			List<String> notFixedVisceraList) {
		String sql="select visceraName,autolyzaFlag " +
				   " FROM CoresStudy.dbo.TblAnatomyCheck "+ 
                   " where sessionId=:sessionId and studyNo=:studyNo and " +
                   " animalCode=:animalCode and visceraName in(:notFixedVisceraList)";
		List<Map<String, Object>> list=null;
		list=getSession().createSQLQuery(sql)
		                 .setParameter("sessionId", sessionId)
		                 .setParameter("studyNo", studyNo)
		                 .setParameter("animalCode", animalCode)
		                 .setParameterList("notFixedVisceraList", notFixedVisceraList)
		                 .setResultTransformer(new MapResultTransformer())
		                 .list();
		return list;
	}

	public void updateFreqCount() {
		Query query = getSession().createSQLQuery("{Call updateFreqCount()}");
		query.executeUpdate();
	}



	@SuppressWarnings("unchecked")
	public TblAnatomyCheck getByVisceraName(String sessionId, String studyNo,
			String animalCode, String visceraName) {
		String hql=" from TblAnatomyCheck where sessionId=:sessionId and studyNo=:studyNo and" +
				   " animalCode=:animalCode and visceraName=:visceraName  ";
		List<TblAnatomyCheck> list=getSession().createQuery(hql)
		                 .setParameter("sessionId", sessionId)
		                 .setParameter("studyNo", studyNo)
		                 .setParameter("animalCode", animalCode)
		                 .setParameter("visceraName", visceraName)
		                 .setResultTransformer(new MapResultTransformer())
		                 .list();
		TblAnatomyCheck tblAnatomyCheck=null;
		if(null!=list && list.size()>0){
			tblAnatomyCheck=list.get(0);
		}
		return tblAnatomyCheck;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTbWatchListByStudyNoAndAnimalCode(
//			A01 专题编号,A02 动物号 ,A04 观察时间,A06 症状,FPOS 部位,FDEGREE 程度,FQOM硬度 ,FLEN 长 "+
//			 "     ,FWIDTH 宽,FNUM 数量,FCOLOR 颜色, "+
//			 "     ,LENUNIT 长单位,WIDTHUNIT 宽单位,SYMPTOMDISAPPEAR 1 症状消除,NUMUNIT 数量单位
			String studyNo, String animalCode) {
		String sql = " select  A01,A02,A04,A06,FPOS,FDEGREE,FQOM,FLEN "+
		 "     ,FWIDTH,FNUM,FCOLOR "+
		 "     ,LENUNIT,WIDTHUNIT,SYMPTOMDISAPPEAR,NUMUNIT "+
//		 "  from YYDB.dbo.TBWATCHLIST where A01 = :studyNo and A02 = :animalCode  ";
		 "  from YYDB.dbo.TBWATCHLIST where A01 = '"+studyNo+"' and A02 = '"+animalCode+"'  ";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
//		                 .setParameter("studyNo", studyNo)
//		                 .setParameter("animalCode", animalCode)
		                 .setResultTransformer(new MapResultTransformer())
		                 .list();
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTblAnatomyCheckPrint(
			List<String> sessionidList) {
//		String sql = " select result.studyNo,result.animalCode,case result.gender when 1 then '♂'when 2 then '♀' else '' end as gender ,result.anatomyRsn,"+
//		" 				result.anatomyBeginTime,result.anatomyOperator,"+
//		" 				result.animalType ,result.animalStrain " +
//		"               ,result.visceraCode,result.visceraName,"+
//		" 				result.anatomyCheckResult"+
//		" from (" +
//				"	select animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//					" 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//					" 		study.animalType ,study.animalStrain,plancheck.visceraCode,plancheck.visceraName,"+
//					" 		'未见异常' as anatomyCheckResult"+
//					" from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
//					" 	on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//					" 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
//					" 	on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
//					" 	on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
//
//					" 	left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck "+
//					" 	on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and "+
//					" 	plancheck.visceraCode = anatomcheck.visceraCode" +
//					" left join CoresSystemSet.dbo.dictViscera as dictviscera on plancheck.visceraCode = dictviscera.visceraCode"+
//					" where animalAnimal.anatomySessionId in (:sessionidList) and anatomcheck.id is null " +
//					" and (dictviscera.gender = 0 or dictviscera .gender = animalAnimal.gender)"+
//
//					" union"+
//
//					" select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//					" 	convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//					" 	study.animalType ,study.animalStrain,"+
//					" 	anatomycheck.visceraCode," +
//					"    case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
//					" 	 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
//					" 	else anatomycheck.subVisceraName end ) end as visceraName ," +
//					"    case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
//					" 	( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
//					" 	+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
//					" 	+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
//					" 	+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
//					" 	+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
//					" 	+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
//					" 	+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
//					" 	+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
//					" 	+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
//					" 	+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
//					" 	+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
//					
//					" 	) end  as anatomyCheckResult"+
//
//					" from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
//					" 	on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
//					" 	left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//					" 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
//					" 	on animalAnimal.studyNo = study.studyNo"+
//					" where anatomycheck.sessionId in (:sessionidList) ) " +
//					" as result left join CoresSystemSet.dbo.dictViscera as dictviscera" +
//					" 		on dictviscera.visceraCode = result.visceraCode" +
//					" 		" +
//					" order by result.studyNo,result.animalCode,case when dictviscera.sn is null then '999999' else dictviscera.sn end ";
		
		//以上为原逻辑，后改成  成对脏器，其他子脏器未见异常也得显示
		
//		String sql = "select result.studyNo,result.animalCode,case result.gender when 1 then '♂'when 2 then '♀' else '' end as gender ,result.anatomyRsn,"+
//					"		 				result.anatomyBeginTime,result.anatomyOperator,"+
//					"		 				result.animalType ,result.animalStrain "+
//					"		               ,result.visceraCode,result.visceraName,"+
//					"		 				result.anatomyCheckResult"+
//					"	 from ("+
//					"					select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//					"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//					"					 		study.animalType ,study.animalStrain,"+
//					"						case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
//					"						case when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
//					"				 		'未见异常' as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
//					"				 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
//					"		 	on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//					"	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
//					"	on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
//					"	on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
//					"	left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
//					"	on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
//					"	plancheck.visceraCode = anatomcheck.visceraCode"+
//					"	left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
//					"	left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
//					"	and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
//					"	left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
//					"				 where animalAnimal.anatomySessionId in (:sessionidList) and (anatomcheck.id is null or dv2.visceraCode is not null ) "+
//					"				 and (dv.gender = 0 or dv .gender = animalAnimal.gender)"+
//
//					"				 union"+
//
//					"				 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//					"		 	convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//						"			 	study.animalType ,study.animalStrain,"+
//						"			 	case when isnull(anatomycheck.visceraCode,'') = '' then null "+
//						"				when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
//						"				else anatomycheck.subVisceraCode end as visceraCode,"+
//						"			    case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
//						"			 	 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
//						"					 	else anatomycheck.subVisceraName end ) end as visceraName ,"+
//						"				    case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
//						"				( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
//						"					 	+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
//						"					 	+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
//						"					 	+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
//						"		 	+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
//						"					 	+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
//						"					 	+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
//						"					 	+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
//						"		 	+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
//						"					 	+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
//						"					 	+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
//						"										 	) end  as anatomyCheckResult,"+
//						"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
//						"				dv2.sn as sn2"+
//
//						"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
//						"				 	on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
//						"				 	left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//						"				 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
//						"				 	on animalAnimal.studyNo = study.studyNo "+
//						"					left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
//						"					left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
//						"					 where anatomycheck.sessionId in (:sessionidList) "+
//					 
//						"			)  as result "+
//						" 		left join "+
//						"		("+
//					"			select result.studyNo ,result.animalCode ,result.visceraCode"+
//					"			from ("+
//						"						select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//						"					 			convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//						"					 			study.animalType ,study.animalStrain,"+
//						"								case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
//						"								case when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
//						"					 			'未见异常' as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
//						"						 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
//						"					 		on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//						"					 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
//						"				 		on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
//							"					 		on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
//
//							"					 		left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
//							"					 		on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
//							"				 		plancheck.visceraCode = anatomcheck.visceraCode"+
//							"						 left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
//							"					 left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
//								"					 and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
//								"					 left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
//							"				 where animalAnimal.anatomySessionId in (:sessionidList) and (anatomcheck.id is null or dv2.visceraCode is not null ) "+
//						"					 and (dv.gender = 0 or dv .gender = animalAnimal.gender)"+
//
//						"						 union"+
//
//						"					 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//						"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//						"					 		study.animalType ,study.animalStrain,"+
//							"					 		case when isnull(anatomycheck.visceraCode,'') = '' then null "+
//							"								when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
//							"								else anatomycheck.subVisceraCode end as visceraCode,"+
//							"							case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
//							"					 		 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
//							"					 		else anatomycheck.subVisceraName end ) end as visceraName ,"+
//							"							case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
//							"					 		( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
//							"				 		+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
//							"				 		+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
//							"				 		+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
//							"				 		+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
//							"				 		+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
//							"				 		+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
//							"				 		+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
//							"				 		+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
//							"				 		+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
//							"				 		+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
//							"									 		) end  as anatomyCheckResult,"+
//							"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
//							"						dv2.sn as sn2"+
//
//							"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
//							"				 		on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
//							"				 		left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//							"				 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
//							"				 		on animalAnimal.studyNo = study.studyNo "+
//							"						left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
//							"						left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
//							"					 where anatomycheck.sessionId in (:sessionidList) "+
//					 
//							"							) as result "+
//							"				group by result.studyNo ,result.animalCode ,result.visceraCode"+
//							"				having count(result.anatomyCheckResult) > 1"+
//							"		) as result2 on result.studyNo = result2.studyNo and result.animalCode = result2.animalCode and result.visceraCode = result2.visceraCode"+
//			
//							"	where result2.studyNo is null or result.anatomyCheckResult != '未见异常'		"+
//					 		
//							"	 order by result.studyNo,result.animalCode,result.sn1,result.sn2 ";
		//上次动物自溶未处理
		
		
//		String sql = "select result.studyNo,result.animalCode,case result.gender when 1 then '♂'when 2 then '♀' else '' end as gender ,result.anatomyRsn,"+
//		"		 				result.anatomyBeginTime,result.anatomyOperator,"+
//		"		 				result.animalType ,result.animalStrain "+
//		"		               ,result.visceraCode,result.visceraName,"+
//		"		 				result.anatomyCheckResult"+
//		"	 from ("+
//		"					select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//		"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//		"					 		study.animalType ,study.animalStrain,"+
//		"						case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
//								//动物自溶
//		"						case when animalAnimal.autolyzeFlag = 1 then 'NA'" +
//		"						when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
//								//动物自溶
//		"				 		case when animalAnimal.autolyzeFlag = 1 then '动物自溶' else '未见异常' end" +
//		"							 as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
//		"				 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
//		"		 	on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//		"	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
//		"	on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
//		"	on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1" +
//								//动物自溶
//		"  and animalAnimal.autolyzeFlag = 0"+
//		"	left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
//		"	on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
//		"	plancheck.visceraCode = anatomcheck.visceraCode"+
//					//动物自溶
//		"  and animalAnimal.autolyzeFlag = 0"+
//		"	left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
//		"	left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
//		"	and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
//		"	left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
//		"				 where animalAnimal.anatomySessionId in (:sessionidList) and " +
//		//动物自溶
//		" (" +
//		" (anatomcheck.id is null or dv2.visceraCode is not null ) "+
//		"				 and (dv.gender = 0 or dv .gender = animalAnimal.gender)" +
//		//动物自溶
//		"  or animalAnimal.autolyzeFlag = 1)"+
//		
//		"				 union"+
//		
//		"				 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//		"		 	convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//		"			 	study.animalType ,study.animalStrain,"+
//		"			 	case when isnull(anatomycheck.visceraCode,'') = '' then null "+
//		"				when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
//		"				else anatomycheck.subVisceraCode end as visceraCode,"+
//		"			    case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
//		"			 	 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
//		"					 	else anatomycheck.subVisceraName end ) end as visceraName ,"+
//		"				    case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
//		"				( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
//		"					 	+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
//		"					 	+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
//		"					 	+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
//		"		 	+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
//		"					 	+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
//		"					 	+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
//		"					 	+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
//		"		 	+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
//		"					 	+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
//		"					 	+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
//		"										 	) end  as anatomyCheckResult,"+
//		"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
//		"				dv2.sn as sn2"+
//		
//		"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
//		"				 	on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
//		"				 	left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//		"				 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
//		"				 	on animalAnimal.studyNo = study.studyNo "+
//		"					left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
//		"					left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
//		"					 where anatomycheck.sessionId in (:sessionidList) "+
//		
//		"			)  as result "+
//		" 		left join "+
//		"		("+
//		"			select result.studyNo ,result.animalCode ,result.visceraCode"+
//		"			from ("+
//		"						select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//		"					 			convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//		"					 			study.animalType ,study.animalStrain,"+
//		"								case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
//		"								case when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
//		"					 			'未见异常' as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
//		"						 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
//		"					 		on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//		"					 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
//		"				 		on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
//		"					 		on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
//		
//		"					 		left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
//		"					 		on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
//		"				 		plancheck.visceraCode = anatomcheck.visceraCode"+
//		"						 left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
//		"					 left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
//		"					 and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
//		"					 left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
//		"				 where animalAnimal.anatomySessionId in (:sessionidList) and (anatomcheck.id is null or dv2.visceraCode is not null ) "+
//		"					 and (dv.gender = 0 or dv .gender = animalAnimal.gender)"+
//		
//		"						 union"+
//		
//		"					 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
//		"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
//		"					 		study.animalType ,study.animalStrain,"+
//		"					 		case when isnull(anatomycheck.visceraCode,'') = '' then null "+
//		"								when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
//		"								else anatomycheck.subVisceraCode end as visceraCode,"+
//		"							case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
//		"					 		 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
//		"					 		else anatomycheck.subVisceraName end ) end as visceraName ,"+
//		"							case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
//		"					 		( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
//		"				 		+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
//		"				 		+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
//		"				 		+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
//		"				 		+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
//		"				 		+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
//		"				 		+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
//		"				 		+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
//		"				 		+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
//		"				 		+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
//		"				 		+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
//		"									 		) end  as anatomyCheckResult,"+
//		"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
//		"						dv2.sn as sn2"+
//		
//		"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
//		"				 		on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
//		"				 		left join CoresUserPrivilege.dbo.tbluser as tbluser"+
//		"				 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
//		"				 		on animalAnimal.studyNo = study.studyNo "+
//		"						left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
//		"						left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
//		"					 where anatomycheck.sessionId in (:sessionidList) "+
//		
//		"							) as result "+
//		"				group by result.studyNo ,result.animalCode ,result.visceraCode"+
//		"				having count(result.anatomyCheckResult) > 1"+
//		"		) as result2 on result.studyNo = result2.studyNo and result.animalCode = result2.animalCode and result.visceraCode = result2.visceraCode"+
//		
//		"	where result2.studyNo is null or result.anatomyCheckResult != '未见异常'		"+
//		
//		"	 order by result.studyNo,result.animalCode,result.sn1,result.sn2 ";
		
		
		//成对脏器，其他子脏器未见异常也得显示(暂不处理，动物自溶已处理)
		String sql = " select result.studyNo,result.animalCode,case result.gender when 1 then '♂'when 2 then '♀' else '' end as gender ,result.anatomyRsn,"+
		" 				result.anatomyBeginTime,result.anatomyOperator,"+
		" 				result.animalType ,result.animalStrain " +
		"               ,result.visceraCode,result.visceraName,"+
		" 				result.anatomyCheckResult"+
		" from (" +
				"	select animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
					" 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
					" 		study.animalType ,study.animalStrain,plancheck.visceraCode, " +
					//动物自溶
					" case when animalAnimal.autolyzeFlag = 1 then 'NA' else plancheck.visceraName end as visceraName ," +
//					" plancheck.visceraName,"+
					//动物自溶
					" case when animalAnimal.autolyzeFlag = 1 then '动物自溶' else" +
					"		'未见异常' end as anatomyCheckResult"+
					
					" from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
					" 	on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
					" 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
					" 	on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
					" 	on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
					//动物自溶
					"  and animalAnimal.autolyzeFlag = 0 	" +
					"left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck "+
					" 	on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and "+
					" 	plancheck.visceraCode = anatomcheck.visceraCode" +
					//动物自溶
					" and animalAnimal.autolyzeFlag = 0" +
					" left join CoresSystemSet.dbo.dictViscera as dictviscera on plancheck.visceraCode = dictviscera.visceraCode"+
					" where animalAnimal.anatomySessionId in (:sessionidList) and " +
					//动物自溶
					"( " +
					" anatomcheck.id is null " +
					" and (dictviscera.gender = 0 or dictviscera .gender = animalAnimal.gender)" +
					//动物自溶
					" or animalAnimal.autolyzeFlag = 1)"+

					" union"+

					" select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
					" 	convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
					" 	study.animalType ,study.animalStrain,"+
					" 	anatomycheck.visceraCode," +
					"    case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
					" 	 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
					" 	else anatomycheck.subVisceraName end ) end as visceraName ," +
					"    case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
					" 	( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
					" 	+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
					" 	+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
					" 	+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
					" 	+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
					" 	+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
					" 	+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
					" 	+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
					" 	+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
					" 	+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
					" 	+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
					
					" 	) end  as anatomyCheckResult"+

					" from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
					" 	on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
					" 	left join CoresUserPrivilege.dbo.tbluser as tbluser"+
					" 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
					" 	on animalAnimal.studyNo = study.studyNo"+
					" where anatomycheck.sessionId in (:sessionidList) ) " +
					" as result left join CoresSystemSet.dbo.dictViscera as dictviscera" +
					" 		on dictviscera.visceraCode = result.visceraCode" +
					" 		" +
					" order by result.studyNo,result.animalCode,case when dictviscera.sn is null then '999999' else dictviscera.sn end ";
		
		List<Map<String,Object>> list = getSession().createSQLQuery(sql).setParameterList("sessionidList", sessionidList)
										.setResultTransformer(new MapResultTransformer())
								        .list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTblAnatomyCheckPrint_new(
			List<String> sessionidList) {
		
		String sql = "select result.studyNo,result.animalCode,case result.gender when 1 then '♂'when 2 then '♀' else '' end as gender ,result.anatomyRsn,"+
		"		 				result.anatomyBeginTime,result.anatomyOperator,"+
		"		 				result.animalType ,result.animalStrain "+
		"		               ,result.visceraCode,result.visceraName,"+
		"		 				result.anatomyCheckResult"+
		"	 from ("+
		"					select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
		"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
		"					 		study.animalType ,study.animalStrain,"+
		"						case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
								//动物自溶
		"						case when animalAnimal.autolyzeFlag = 1 then 'NA'" +
		"						when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
								//动物自溶
		"				 		case when animalAnimal.autolyzeFlag = 1 then '动物自溶' else '未见异常' end" +
		"							 as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
		"				 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
		"		 	on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
		"	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
		"	on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
		"	on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1" +
								//动物自溶
		"  and animalAnimal.autolyzeFlag = 0"+
		"	left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
		"	on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
		"	plancheck.visceraCode = anatomcheck.visceraCode"+
					//动物自溶
		"  and animalAnimal.autolyzeFlag = 0"+
		"	left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
		"	left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
		"	and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
		"	left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
		"				 where animalAnimal.anatomySessionId in (:sessionidList) and " +
		//动物自溶
		" (" +
		" (anatomcheck.id is null or dv2.visceraCode is not null ) "+
		"				 and (dv.gender = 0 or dv .gender = animalAnimal.gender)" +
		//动物自溶
		"  or animalAnimal.autolyzeFlag = 1)"+
		
		"				 union"+
		
		"				 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
		"		 	convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
		"			 	study.animalType ,study.animalStrain,"+
		"			 	case when isnull(anatomycheck.visceraCode,'') = '' then null "+
		"				when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
		"				else anatomycheck.subVisceraCode end as visceraCode,"+
		"			    case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
		"			 	 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
		"					 	else anatomycheck.subVisceraName end ) end as visceraName ,"+
		"				    case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
		"				( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
		"					 	+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
		"					 	+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
		"					 	+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
		"		 	+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
		"					 	+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
		"					 	+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
		"					 	+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
		"		 	+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
		"					 	+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
		"					 	+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
		"										 	) end  as anatomyCheckResult,"+
		"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
		"				dv2.sn as sn2"+
		
		"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
		"				 	on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
		"				 	left join CoresUserPrivilege.dbo.tbluser as tbluser"+
		"				 	on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
		"				 	on animalAnimal.studyNo = study.studyNo "+
		"					left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
		"					left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
		"					 where anatomycheck.sessionId in (:sessionidList) "+
		
		"			)  as result "+
		" 		left join "+
		"		("+
		"			select result.studyNo ,result.animalCode ,result.visceraCode"+
		"			from ("+
		"						select distinct animalAnimal.studyNo,animalAnimal.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
		"					 			convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
		"					 			study.animalType ,study.animalStrain,"+
		"								case when dv3.visceraCode is not null then dv3.visceraCode else plancheck.visceraCode end as visceraCode,"+
		"								case when dv3.visceraCode is not null then dv3.visceraName else plancheck.visceraName end as visceraName,"+
		"					 			'未见异常' as anatomyCheckResult,dv.sn as sn1,dv3.sn as sn2"+
		"						 from tblAnatomyAnimal as animalAnimal left join CoresStudy.dbo.tblStudyPlan as study "+
		"					 		on animalAnimal.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tbluser"+
		"					 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblAnatomyTask as task"+
		"				 		on animalAnimal.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
		"					 		on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.atanomyCheckFlag = 1"+
		
		"					 		left join CoresStudy.dbo.TblAnatomyCheck as anatomcheck"+
		"					 		on animalAnimal.anatomySessionId = anatomcheck.sessionId and animalAnimal.animalCode = anatomcheck.animalCode and"+
		"				 		plancheck.visceraCode = anatomcheck.visceraCode"+
		"						 left join CoresSystemSet.dbo.dictViscera as dv on plancheck.visceraCode = dv.visceraCode"+
		"					 left join CoresSystemSet.dbo.dictViscera as dv2  on  anatomcheck.id is not null and isnull(anatomcheck.subVisceraCode,'') != '' "+
		"					 and  plancheck.visceraCode = dv2.visceraCode and dv2.isPart = 1 "+
		"					 left join CoresSystemSet.dbo.dictViscera dv3 on dv2.visceraCode = dv3.pVisceraCode "+
		"				 where animalAnimal.anatomySessionId in (:sessionidList) and (anatomcheck.id is null or dv2.visceraCode is not null ) "+
		"					 and (dv.gender = 0 or dv .gender = animalAnimal.gender)"+
		
		"						 union"+
		
		"					 select anatomycheck.studyNo,anatomycheck.animalCode,animalAnimal.gender,animalAnimal.deadRsn as anatomyRsn,"+
		"					 		convert(varchar(16),animalAnimal.anatomyBeginTime,120) as anatomyBeginTime,tbluser.realName as anatomyOperator,"+
		"					 		study.animalType ,study.animalStrain,"+
		"					 		case when isnull(anatomycheck.visceraCode,'') = '' then null "+
		"								when  isnull(anatomycheck.subVisceraCode,'') = '' then anatomycheck.visceraCode "+
		"								else anatomycheck.subVisceraCode end as visceraCode,"+
		"							case when anatomycheck.visceraCode is null or anatomycheck.visceraCode = '' then '-' else "+
		"					 		 (case when anatomycheck.subVisceraCode is null or anatomycheck.subVisceraCode ='' then anatomycheck.visceraName "+
		"					 		else anatomycheck.subVisceraName end ) end as visceraName ,"+
		"							case when anatomycheck.autolyzaFlag = 1 then '自溶' else"+
		"					 		( case when isnull(anatomycheck.bodySurfacePos,'') = '' then '' else anatomycheck.bodySurfacePos+' ' end"+
		"				 		+case when isnull(anatomycheck.anatomyPos,'') = '' then '' else anatomycheck.anatomyPos+' ' end"+
		"				 		+case when isnull(anatomycheck.pos,'') = '' then '' else anatomycheck.pos+' ' end"+
		"				 		+case when isnull(anatomycheck.number,'') = '' then '' else anatomycheck.number+' ' end"+
		"				 		+case when isnull(anatomycheck.range,'') = '' then '' else anatomycheck.range+' ' end"+
		"				 		+case when isnull(anatomycheck.size,'') = '' then '' else anatomycheck.size+' ' end"+
		"				 		+case when isnull(anatomycheck.color,'') = '' then '' else anatomycheck.color+' ' end"+
		"				 		+case when isnull(anatomycheck.texture,'') = '' then '' else anatomycheck.texture+' ' end"+
		"				 		+case when isnull(anatomycheck.shape,'') = '' then '' else anatomycheck.shape+' ' end"+
		"				 		+case when isnull(anatomycheck.anatomyFingding,'') = '' then '' else anatomycheck.anatomyFingding+' ' end"+
		"				 		+case when isnull(anatomycheck.lesionDegree,'') = '' then '' else anatomycheck.lesionDegree+' ' end"+
		"									 		) end  as anatomyCheckResult,"+
		"						case when dv1.sn is not null  then dv1.sn else '999999' end as sn1,"+
		"						dv2.sn as sn2"+
		
		"					 from CoresStudy.dbo.TblAnatomyCheck as anatomycheck  left join tblAnatomyAnimal as animalAnimal"+
		"				 		on anatomycheck.sessionId = animalAnimal.anatomySessionId and anatomycheck.animalCode = animalAnimal.animalCode"+
		"				 		left join CoresUserPrivilege.dbo.tbluser as tbluser"+
		"				 		on animalAnimal.anatomyOperator = tbluser.userName left join CoresStudy.dbo.tblStudyPlan as study "+
		"				 		on animalAnimal.studyNo = study.studyNo "+
		"						left join CoresSystemSet.dbo.dictViscera as dv1 on isnull(anatomycheck.visceraCode,'') = dv1.visceraCode"+
		"						left join CoresSystemSet.dbo.dictViscera as dv2 on isnull(anatomycheck.subVisceraCode,'') = dv2.visceraCode"+
		"					 where anatomycheck.sessionId in (:sessionidList) "+
		
		"							) as result "+
		"				group by result.studyNo ,result.animalCode ,result.visceraCode"+
		"				having count(result.anatomyCheckResult) > 1"+
		"		) as result2 on result.studyNo = result2.studyNo and result.animalCode = result2.animalCode and result.visceraCode = result2.visceraCode"+
		
		"	where result2.studyNo is null or result.anatomyCheckResult != '未见异常'		"+
		
		"	 order by result.studyNo,result.animalCode,result.sn1,result.sn2 ";
		
		List<Map<String,Object>> list = getSession().createSQLQuery(sql).setParameterList("sessionidList", sessionidList)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndCodeList(String taskId,
			String sessionId) {
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblAnatomyCheck";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (1,3,5,7))";
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
		
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblAnatomyCheck";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (1,3,5,7))";
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
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndCodeListWithEdits(String taskId,
			String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		
		String sql="select distinct visceraName,visceraCode from" +
				" (" +
				"	(select id,animalCode,null taskId,sessionId,studyNo,visceraName,visceraCode from CoresStudy.dbo.TblAnatomyCheck where id not in (select oldId from  CoresStudy.dbo.TblAnatomyCheckEdit where (editType=3 or editType=2) and delFlag=0) )" +
				"	union " +
				"	(select id,animalCode,taskId,null sessionId,studyNo,visceraName,visceraCode from CoresStudy.dbo.TblAnatomyCheckEdit where (editType=1 or editType=2) and delFlag=0 and " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql+="	taskId=(select taskId from CoresStudy.dbo.tblPathSession where sessionId=:sessionId))" ;
		}else {
			sql+="	taskId in (:taskId) " ;
		}
		
		sql+=	")" +
				" )as tblc";
		
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and (sessionId=:sessionId or" +
					"  animalCode in (select animalCode from [CoresStudy].[dbo].[tblAnatomyAnimal] where anatomySessionId=:sessionId))";
		}else{
			sql=sql+" where visceraCode is not null and (sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (1,3,5,7)) or sessionId is null) ";
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


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyFindingBySessionId(
			String taskId, String sessionId) {
		String sql="select distinct(anatomyFingding) from CoresStudy.dbo.TblAnatomyCheck";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where anatomyFingding is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where anatomyFingding is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (1,3,5,7))";
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
	public List<Map<String, Object>> getAnatomyFindingBySessionIds(
			String taskId, String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql="select distinct(anatomyFingding) from CoresStudy.dbo.TblAnatomyCheck";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where anatomyFingding is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where anatomyFingding is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (1,3,5,7))";
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
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyFindingBySessionIdWithEdits(
			String taskId, String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql="  select distinct anatomyFingding from" +
				" (" +
				"	(select id,animalCode,null taskId,sessionId,anatomyFingding from CoresStudy.dbo.TblAnatomyCheck where id not in (select oldId from  CoresStudy.dbo.TblAnatomyCheckEdit where (editType=3 or editType=2) and delFlag=0) )" +
				"	union " +
				"	(select id,animalCode,taskId,null sessionId,anatomyFingding from CoresStudy.dbo.TblAnatomyCheckEdit where (editType=1 or editType=2) and delFlag=0 and " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql+="	taskId=(select taskId from CoresStudy.dbo.tblPathSession where sessionId=:sessionId)" ;
		}else {
			sql+="	taskId in (:taskId)  " ;
		}
		sql +=  ")" +
				" )as tblc";
		
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where anatomyFingding is not null and (sessionId=:sessionId " +
					" or animalCode in (select animalCode from [CoresStudy].[dbo].[tblAnatomyAnimal] where anatomySessionId=:sessionId)) ";
		}else{
			sql=sql+" where anatomyFingding is not null and (sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (1,3,5,7)) or sessionId is null) ";
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

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeBySessionId(String taskId,
			String sessionId) {
		String sql="select animalCode,autolyzeFlag from CoresStudy.dbo.TblAnatomyAnimal";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where animalCode is not null and anatomySessionId=:sessionId";
		}else{
			sql=sql+" where animalCode is not null and anatomySessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (1,3,5,7))";
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
			sql=sql+" where animalCode is not null and anatomySessionId=:sessionId";
		}else{
			sql=sql+" where animalCode is not null and anatomySessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (1,3,5,7))";
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
	public List<Map<String, Object>> getListByConditions(String taskId,
			String sessionId, String visceraName, String antomyFinding,
			String animalCode) {
		String sql = "select tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName , tblc.anatomyFingding ," +
		"  tblc.operator , tblc.operateTime ,tbla.realName,tblc.autolyzaFlag,tblc.bodySurfacePos " +
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc" +
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblc.operator = tbla.userName " +
		" where  " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" tblc.sessionId=:sessionId  ";
		}else{
			sql=sql+" tblc.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId=:taskId and sessionType in (1,3,5,7))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblc.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblc.visceraName=:visceraName";
		}
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			sql=sql+" and tblc.anatomyFingding=:antomyFinding";
		}
		sql=sql+" order by  tblc.animalCode, tblc.id";
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
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			query.setParameter("antomyFinding", antomyFinding);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
		
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByConditions2(String taskId,
			String sessionId, String visceraName, String antomyFinding,
			String animalCode) {
				
		String sql = "select tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName," +
		"  tblc.operator , tblc.operateTime ,tbla.realName,tblc.autolyzaFlag,tblc.bodySurfacePos, " +
		" case when tblc.autolyzaFlag = 1 then '自溶' " +
		"  when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)  " +
		"    when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding " +
		" else" +
		"  	( case when isnull(tblc.bodySurfacePos,'') = '' then '' else tblc.bodySurfacePos+' ' end" +
		"  	+case when isnull(tblc.anatomyPos,'') = '' then '' else tblc.anatomyPos+' ' end" +
		" 	 	+case when isnull(tblc.pos,'') = '' then '' else tblc.pos+' ' end" +
		"  	+case when isnull(tblc.number,'') = '' then '' else tblc.number+' ' end" +
		"  	+case when isnull(tblc.range,'') = '' then '' else tblc.range+' ' end" +
		" 	+case when isnull(tblc.size,'') = '' then '' else tblc.size+' ' end" +
		"  	+case when isnull(tblc.color,'') = '' then '' else tblc.color+' ' end" +
		"  	+case when isnull(tblc.texture,'') = '' then '' else tblc.texture+' ' end" +
		" 	 	+case when isnull(tblc.shape,'') = '' then '' else tblc.shape+' ' end" +
		"  	+case when isnull(tblc.anatomyFingding,'') = '' then '' else tblc.anatomyFingding+' ' end" +
		"  	+case when isnull(tblc.lesionDegree,'') = '' then '' else tblc.lesionDegree+' ' end" +
		
		"  	) end  as anatomyFingding" +
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc" +
		
		//缺如
		"   left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId" +
		
		
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblc.operator = tbla.userName " +
		" where  " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" tblc.sessionId=:sessionId  ";
		}else{
			sql=sql+" tblc.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId=:taskId and sessionType in (1,3,5,7))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblc.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblc.visceraName=:visceraName";
		}
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			sql=sql+" and tblc.anatomyFingding=:antomyFinding";
		}
		sql=sql+" order by  tblc.animalCode, tblc.id";
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
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			query.setParameter("antomyFinding", antomyFinding);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByConditions2s(String taskId,
			String sessionId, String visceraName, String antomyFinding,
			String animalCode) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		
		String sql = "select tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName," +
		"  tblc.operator , tblc.operateTime ,tbla.realName,tblc.autolyzaFlag,tblc.bodySurfacePos, " +
		" case when tblc.autolyzaFlag = 1 then '自溶' " +
		"  when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)  " +
		"    when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding " +
		" else" +
		"  	( case when isnull(tblc.bodySurfacePos,'') = '' then '' else tblc.bodySurfacePos+' ' end" +
		"  	+case when isnull(tblc.anatomyPos,'') = '' then '' else tblc.anatomyPos+' ' end" +
		" 	 	+case when isnull(tblc.pos,'') = '' then '' else tblc.pos+' ' end" +
		"  	+case when isnull(tblc.number,'') = '' then '' else tblc.number+' ' end" +
		"  	+case when isnull(tblc.range,'') = '' then '' else tblc.range+' ' end" +
		" 	+case when isnull(tblc.size,'') = '' then '' else tblc.size+' ' end" +
		"  	+case when isnull(tblc.color,'') = '' then '' else tblc.color+' ' end" +
		"  	+case when isnull(tblc.texture,'') = '' then '' else tblc.texture+' ' end" +
		" 	 	+case when isnull(tblc.shape,'') = '' then '' else tblc.shape+' ' end" +
		"  	+case when isnull(tblc.anatomyFingding,'') = '' then '' else tblc.anatomyFingding+' ' end" +
		"  	+case when isnull(tblc.lesionDegree,'') = '' then '' else tblc.lesionDegree+' ' end" +
		
		"  	) end  as anatomyFingding" +
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc" +
		
		//缺如
		"   left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId" +
		
		
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblc.operator = tbla.userName " +
		" where  " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" tblc.sessionId=:sessionId  ";
		}else{
			sql=sql+" tblc.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId in (:taskId) and sessionType in (1,3,5,7))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblc.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblc.visceraName=:visceraName";
		}
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			sql=sql+" and tblc.anatomyFingding=:antomyFinding";
		}
		sql=sql+" order by  tblc.animalCode, tblc.id";
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
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			query.setParameter("antomyFinding", antomyFinding);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByConditions2WithEdits(String taskId,
			String sessionId, String visceraName, String antomyFinding,
			String animalCode) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		
		String sql = "select tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName," +
		"  tblc.operator , tblc.operateTime ,tbla.realName,tblc.autolyzaFlag,tblc.bodySurfacePos, " +
		" case when tblc.autolyzaFlag = 1 then '自溶' " +
		"  when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)  " +
		"    when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding " +
		" else" +
		"  	( case when isnull(tblc.bodySurfacePos,'') = '' then '' else tblc.bodySurfacePos+' ' end" +
		"  	+case when isnull(tblc.anatomyPos,'') = '' then '' else tblc.anatomyPos+' ' end" +
		" 	 	+case when isnull(tblc.pos,'') = '' then '' else tblc.pos+' ' end" +
		"  	+case when isnull(tblc.number,'') = '' then '' else tblc.number+' ' end" +
		"  	+case when isnull(tblc.range,'') = '' then '' else tblc.range+' ' end" +
		" 	+case when isnull(tblc.size,'') = '' then '' else tblc.size+' ' end" +
		"  	+case when isnull(tblc.color,'') = '' then '' else tblc.color+' ' end" +
		"  	+case when isnull(tblc.texture,'') = '' then '' else tblc.texture+' ' end" +
		" 	 	+case when isnull(tblc.shape,'') = '' then '' else tblc.shape+' ' end" +
		"  	+case when isnull(tblc.anatomyFingding,'') = '' then '' else tblc.anatomyFingding+' ' end" +
		"  	+case when isnull(tblc.lesionDegree,'') = '' then '' else tblc.lesionDegree+' ' end" +
		
		"  	) end  as anatomyFingding" +
		"  from ( " +
		" 		select id,null taskId,sessionId,studyNo,animalCode,visceraName,subVisceraName,operator,operateTime,autolyzaFlag,bodySurfacePos,anatomyFingding" +
		"			,anatomyPos,pos,number,range,size,color,texture,shape,lesionDegree from CoresStudy.dbo.TblAnatomyCheck acheck where id not in (select oldId from CoresStudy.dbo.TblAnatomyCheckEdit where (editType=3 or editType=2) and delFlag=0)" +
		" 		union " +
		"		(select id,taskId,null sessionId,studyNo,animalCode,visceraName,subVisceraName,operator,operateTime,autolyzaFlag,bodySurfacePos,anatomyFingding" +
		"					,anatomyPos,pos,number,range,size,color,texture,shape,lesionDegree from CoresStudy.dbo.TblAnatomyCheckEdit edit " +
		"			where (editType=1 or editType=2) and delFlag=0 and " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql+="	edit.taskId=(select taskId from CoresStudy.dbo.tblPathSession where sessionId=:sessionId))" ;
		}else {
			sql+="	edit.taskId in (:taskId) ) " ;
		}
		sql+="  )as tblc " +
		
		//缺如
		"   left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId" +
		
		
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblc.operator = tbla.userName " +
		" where  " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" (tblc.sessionId=:sessionId " +
					" or tblc.animalCode in (select animalCode from [CoresStudy].[dbo].[tblAnatomyAnimal] where anatomySessionId=:sessionId)) ";
		}else{
			sql=sql+" (tblc.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId in (:taskId) and sessionType in (1,3,5,7))" +
			" or (tblc.sessionId is null and tblc.studyNo in (select studyNo " +
			"	from CoresStudy.dbo.tblAnatomyTask where taskId in  (:taskId)  )  ))";//sessionId is null是edit中的数据
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and tblc.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and tblc.visceraName=:visceraName";
		}
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			sql=sql+" and tblc.anatomyFingding=:antomyFinding";
		}
		sql=sql+" order by  tblc.animalCode, tblc.id";
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
		if(null!=antomyFinding && !antomyFinding.equals("全部") && !antomyFinding.equals("")){
			query.setParameter("antomyFinding", antomyFinding);
		}
		List<Map<String,Object>> mapList =  query
											.setResultTransformer(new MapResultTransformer())
											.list();
		return mapList;
		
	}
	@SuppressWarnings("unchecked")
	public List<TblAnatomyCheck> getAnatomyCheckByStudyNoAndReqNo(String studyNo,Integer reqNo,String visceraName,String finding) {
		System.out.println("reqNo"+reqNo);
		List<TblAnatomyCheck> list = new ArrayList<TblAnatomyCheck>();
		String sql = "select tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName," +
		"  tblc.operator , tblc.operateTime ,tblc.autolyzaFlag,tblc.bodySurfacePos, " +
		" case when tblc.autolyzaFlag = 1 then '自溶'" +
		"  when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') !='' then (tblc.anatomyFingding+':'+qr.missingRsn)  " +
		"    when tblc.autolyzaFlag = 2 and isnull(qr.missingRsn,'') ='' then tblc.anatomyFingding " +
		" else" +
		"  	( case when isnull(tblc.bodySurfacePos,'') = '' then '' else (tblc.bodySurfacePos+' ') end" +
		"  	+case when isnull(tblc.anatomyPos,'') = '' then '' else (tblc.anatomyPos+' ') end" +
		" 	 	+case when isnull(tblc.pos,'') = '' then '' else (tblc.pos+' ') end" +
		"  	+case when isnull(tblc.number,'') = '' then '' else (tblc.number+' ') end" +
		"  	+case when isnull(tblc.range,'') = '' then '' else (tblc.range+' ') end" +
		" 	+case when isnull(tblc.size,'') = '' then '' else (tblc.size+' ') end" +
		"  	+case when isnull(tblc.color,'') = '' then '' else (tblc.color+' ') end" +
		"  	+case when isnull(tblc.texture,'') = '' then '' else (tblc.texture+' ') end" +
		" 	 	+case when isnull(tblc.shape,'') = '' then '' else (tblc.shape+' ') end" +
		"  	+case when isnull(tblc.anatomyFingding,'') = '' then '' else (tblc.anatomyFingding+' ') end" +
		"  	+case when isnull(tblc.lesionDegree,'') = '' then '' else (tblc.lesionDegree+' ') end" +
		"  	) end  as anatomyFingding,tbla.realName" +
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc " +
		//缺如
		"   left join CoresStudy.dbo.tblVisceraQueRu as qr on tblc.autolyzaFlag = 2 and tblc.id = qr.anatomyCheckId" +
		
		" left join CoresUserPrivilege.dbo.tbluser as tbla " +
		" on tblc.operator = tbla.userName " +
		" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
		" on dictViscera.visceraCode = tblc.visceraCode " +
		" left join CoresStudy.dbo.tblPathSession as pathSession on tblc.sessionId=pathSession.sessionId " +
		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblc.studyNo and task.taskId = pathSession.taskId" +
		"  where tblc.studyNo=:studyNo and task.anatomyReqNo=:reqNo " ;
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			sql+=" and tblc.visceraName = :visceraName ";
		}
		if(finding!=null&&!"".equals(finding)&&!"全部".equals(finding))
		{
			sql+=" and tblc.anatomyFingding=:finding";
		}
		
		sql+=" order by tblc.animalCode";
		
		//String hqlString = "from TblAnatomyCheck where studyNo=?";
		Query query = getSession().createSQLQuery(sql)
					.setParameter("studyNo", studyNo)
					.setParameter("reqNo", reqNo);
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			query.setParameter("visceraName", visceraName);
		}
		if(finding!=null&&!"".equals(finding)&&!"全部".equals(finding))
		{
			query.setParameter("finding",finding);
		}
		List<Map<String,Object>> temp = query.setResultTransformer(new MapResultTransformer())
								.list();
		for(Map<String, Object> map:temp)
		{
			TblAnatomyCheck check = new TblAnatomyCheck();
			//tblc.id,tblc.sessionId,tblc.studyNo,tblc.animalCode , tblc.visceraName , tblc.subVisceraName," +
			//"  tblc.operator , tblc.operateTime ,tblc.autolyzaFlag,tblc.bodySurfacePos," +
			//"anatomyFingding,tbla.realName
			check.setId((String)map.get("id"));
			check.setSessionId((String)map.get("sessionId"));
			check.setStudyNo((String)map.get("studyNo"));
			check.setAnimalCode((String)map.get("animalCode"));
			check.setVisceraName((String)map.get("visceraName"));
			check.setSubVisceraName((String)map.get("subVisceraName"));
			check.setOperator((String)map.get("realName"));
			check.setOperateTime((Date)map.get("operateTime"));
			check.setAutolyzaFlag((Integer)map.get("autolyzaFlag"));
			check.setBodySurfacePos((String)map.get("bodySurfacePos"));
			check.setAnatomyFingding((String)map.get("anatomyFingding"));
			
			list.add(check);
			
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyCheckVisceraByStudyNoAndReqNo(String studyNo,
			Integer reqNo) {
		String sql = "select distinct(tblc.visceraName),tblc.visceraCode"+
		//String sql = "select distinct(visceraName),visceraCode,distinct(anatomyFingding) "+
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc " +
		" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
		" on dictViscera.visceraCode = tblc.visceraCode " +
		" left join CoresStudy.dbo.tblPathSession as pathSession on tblc.sessionId=pathSession.sessionId " +
		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblc.studyNo and task.taskId = pathSession.taskId" +
		"  where tblc.visceraName is not null and tblc.studyNo=:studyNo and task.anatomyReqNo=:reqNo";
		//" order by dictViscera.sn";
		
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
								.setParameter("studyNo", studyNo)
								.setParameter("reqNo", reqNo)
								.setResultTransformer(new MapResultTransformer())
								.list();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("visceraName", "全部");
		list.add(0,map);
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomyCheckFindingByStudyNoAndReqNo(String studyNo,
			Integer reqNo) {
		System.out.println("get finding reqNo "+reqNo);
		String sql = "select distinct(anatomyFingding) "+
		//String sql = "select distinct(visceraName),visceraCode,distinct(anatomyFingding) "+
		"  from CoresStudy.dbo.TblAnatomyCheck  as tblc " +
		" left join CoresSystemSet.dbo.dictViscera as dictViscera " +
		" on dictViscera.visceraCode = tblc.visceraCode " +
		" left join CoresStudy.dbo.tblPathSession as pathSession on tblc.sessionId=pathSession.sessionId " +
		" left join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblc.studyNo and task.taskId = pathSession.taskId" +
		"  where anatomyFingding is not null and tblc.studyNo=:studyNo and task.anatomyReqNo=:reqNo";
		//" order by dictViscera.sn";
		
		System.out.println(sql);
		//String hqlString = "from TblAnatomyCheck where studyNo=?";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql)
								.setParameter("studyNo", studyNo)
								.setParameter("reqNo", reqNo)
								.setResultTransformer(new MapResultTransformer())
								.list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("anatomyFingding", "全部");
		list.add(0,map);
		
		System.out.println("list==== "+list.size());
			
		return list;
	}


	public boolean isMissing(String sessionId, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where sessionId = ? and animalCode = ? and autolyzaFlag = 2"+
						"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
						"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, sessionId)
								.setParameter(1, animalCode)
								.setParameter(2, visceraCode)
								.setParameter(3, subVisceraCode)
								.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where sessionId = ? and animalCode = ? and autolyzaFlag = 2"+
						"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
										.setParameter(0, sessionId)
										.setParameter(1, animalCode)
										.setParameter(2, visceraCode)
										.uniqueResult();
		}
		return  count > 0;
	}


	public Json saveOne(TblAnatomyCheck tblAnatomyCheck, String missingRsn) {
		Json json = new Json();
		//检查该动物是否已经剖检完成
		boolean anatomyFinishFlag = tblAnatomyAnimalService
			.isAnatomyFinish(tblAnatomyCheck.getStudyNo(),tblAnatomyCheck.getAnimalCode());
		if(!anatomyFinishFlag){
			String anatomyCheckId = getKey();
			tblAnatomyCheck.setId(anatomyCheckId);
			
			int autolyzaFlag = tblAnatomyCheck.getAutolyzaFlag(); 
			if(2 == autolyzaFlag){
				TblVisceraQueRu t=new TblVisceraQueRu();
				String id = getKey("TblVisceraQueRu");
				t.setId(id);
				t.setAnimalCode(tblAnatomyCheck.getAnimalCode());
				t.setSessionId(tblAnatomyCheck.getSessionId());
				t.setStudyNo(tblAnatomyCheck.getStudyNo());
				t.setMissingRsn(missingRsn);
				t.setVisceraCode(tblAnatomyCheck.getVisceraCode());
				t.setVisceraName(tblAnatomyCheck.getVisceraName());
				t.setVisceraType(tblAnatomyCheck.getVisceraType());
				t.setSubVisceraCode(tblAnatomyCheck.getSubVisceraCode());
				t.setSubVisceraName(tblAnatomyCheck.getSubVisceraName());
				t.setAnatomyCheckId(anatomyCheckId);
				getSession().save(t);
			}
			getSession().save(tblAnatomyCheck);
			json.setSuccess(true);
			json.setMsg(anatomyCheckId);
		}else{
			json.setMsg("该动物解剖已完成！");
		}
		return json;
		
	}


	public String getMissRsnByAnatomyCheckId(String anatomyCheckId) {
		String sql = "select qr.missingRsn"+
					" from CoresStudy.dbo.tblVisceraQueRu as qr"+
					" where qr.anatomyCheckId =  ? ";
		String missingRsn = (String) getSession().createSQLQuery(sql).setParameter(0, anatomyCheckId).uniqueResult();
		if(null == missingRsn){
			missingRsn = "";
		}
		return missingRsn;
	}


	public Json saveOne_1(TblAnatomyCheck tblAnatomyCheck, String missingRsn) {
		Json json = new Json();
		String anatomyCheckId = getKey();
		tblAnatomyCheck.setId(anatomyCheckId);
			
		int autolyzaFlag = tblAnatomyCheck.getAutolyzaFlag(); 
		if(2 == autolyzaFlag){
			TblVisceraQueRu t=new TblVisceraQueRu();
			String id = getKey("TblVisceraQueRu");
			t.setId(id);
			t.setAnimalCode(tblAnatomyCheck.getAnimalCode());
			t.setSessionId(tblAnatomyCheck.getSessionId());
			t.setStudyNo(tblAnatomyCheck.getStudyNo());
			t.setMissingRsn(missingRsn);
			t.setVisceraCode(tblAnatomyCheck.getVisceraCode());
			t.setVisceraName(tblAnatomyCheck.getVisceraName());
			t.setVisceraType(tblAnatomyCheck.getVisceraType());
			t.setSubVisceraCode(tblAnatomyCheck.getSubVisceraCode());
			t.setSubVisceraName(tblAnatomyCheck.getSubVisceraName());
			t.setAnatomyCheckId(anatomyCheckId);
			getSession().save(t);
			getSession().save(tblAnatomyCheck);
			json.setSuccess(true);
			json.setMsg(anatomyCheckId);
		}
		return json;
	}


	public Json saveOne_1(TblAnatomyCheck tblAnatomyCheck) {
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheck.setId(id);
		
		getSession().save(tblAnatomyCheck);
		
		json.setSuccess(true);
		json.setMsg(id);
		return json;
	}
	public Json saveOne_2(TblAnatomyCheck tblAnatomyCheck) {
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheck.setId(id);
		
		//保存固定记录
		TblVisceraFixed tblVisceraFixed = new TblVisceraFixed();
		tblVisceraFixed.setId(tblVisceraFixedService.getKey());
		tblVisceraFixed.setSessionId(tblAnatomyCheck.getSessionId());
		tblVisceraFixed.setStudyNo(tblAnatomyCheck.getStudyNo());
//		tblVisceraFixed.setOperateTime(new Date());
		tblVisceraFixed.setOperateTime(tblAnatomyCheck.getOperateTime());
		tblVisceraFixed.setOperator(tblAnatomyCheck.getOperator());
		tblVisceraFixed.setAnimalCode(tblAnatomyCheck.getAnimalCode());
		tblVisceraFixed.setFixedType(1);
		tblVisceraFixed.setAnatomyCheckRecordId(id);
		tblVisceraFixedService.save(tblVisceraFixed);
		getSession().save(tblAnatomyCheck);
		json.setSuccess(true);
		json.setMsg(id);
		return json;
	}


	public Json updateOne_1(TblAnatomyCheck tblAnatomyCheck) {
		Json json = new Json();
		String id = tblAnatomyCheck.getId();
		//更新
		getSession().update(tblAnatomyCheck);
		String sql = "delete  CoresStudy.dbo.tblVisceraQueRu"+
				" from CoresStudy.dbo.tblVisceraQueRu as qr"+
				" where qr.anatomyCheckId = ? ";
		getSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();
		
		json.setSuccess(true);
		json.setMsg(id);
		return json;
	}


	public Json updateOne_1(TblAnatomyCheck tblAnatomyCheck, String missingRsn) {
		Json json = new Json();
		String id =tblAnatomyCheck.getId();
		//删除缺如原因
		String sql = "delete  CoresStudy.dbo.tblVisceraQueRu"+
					" from CoresStudy.dbo.tblVisceraQueRu as qr"+
					" where qr.anatomyCheckId = ? ";
		getSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();
		int autolyzaFlag = tblAnatomyCheck.getAutolyzaFlag(); 
		if(2 == autolyzaFlag){
			TblVisceraQueRu t=new TblVisceraQueRu();
			String id2 = getKey("TblVisceraQueRu");
			t.setId(id2);
			t.setAnimalCode(tblAnatomyCheck.getAnimalCode());
			t.setSessionId(tblAnatomyCheck.getSessionId());
			t.setStudyNo(tblAnatomyCheck.getStudyNo());
			t.setMissingRsn(missingRsn);
			t.setVisceraCode(tblAnatomyCheck.getVisceraCode());
			t.setVisceraName(tblAnatomyCheck.getVisceraName());
			t.setVisceraType(tblAnatomyCheck.getVisceraType());
			t.setSubVisceraCode(tblAnatomyCheck.getSubVisceraCode());
			t.setSubVisceraName(tblAnatomyCheck.getSubVisceraName());
			t.setAnatomyCheckId(id);
			getSession().save(t);
			//更新
			getSession().update(tblAnatomyCheck);
			json.setSuccess(true);
			json.setMsg(id);
		}
		
		json.setSuccess(true);
		json.setMsg(id);
		return json;
	}


	public void deleteOne_1(String id) {
		TblAnatomyCheck tblAnatomyCheck = getById(id);
		getSession().delete(tblAnatomyCheck);
		String sql = "delete  CoresStudy.dbo.tblVisceraQueRu"+
					" from CoresStudy.dbo.tblVisceraQueRu as qr"+
					" where qr.anatomyCheckId = ? ";
		getSession().createSQLQuery(sql).setParameter(0, id).executeUpdate();
		
	}


	@SuppressWarnings("unchecked")
	public List<TblAnatomyCheck> getListByStudyNoAnimalCode(String studyNo,
			String animalCode) {
		String hql = "From TblAnatomyCheck where studyNo = ? and animalCode = ?";
		List<TblAnatomyCheck> list =  getSession().createQuery(hql)
												.setParameter(0, studyNo)
												.setParameter(1, animalCode)
												.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraCodeAndName_additional(
			String taskId, String studyNo, String animalCode) {
		TblAnatomyAnimal tblAnatomyAnimal = tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
		Integer gender = 0;
		if(null != tblAnatomyAnimal){
			gender = tblAnatomyAnimal.getGender();
		}
		String animalTypeName = "";
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
		if(null != tblStudyPlan){
			if(null != tblStudyPlan.getAnimalType()){
				animalTypeName = tblStudyPlan.getAnimalType();
			}
		}
		String sql = "select tblv.visceraType,tblv.visceraCode,tblv.visceraName,tblv.subVisceraCode,tblv.subVisceraName,dict.sn ,dict2.sn as subsn"+ 
					" from (  "+
					" 	select distinct tblc.visceraType,tblc.visceraCode,tblc.visceraName,dictviscera.visceraCode as subVisceraCode,dictviscera.visceraName as subVisceraName "+
					" 	from CoresStudy.dbo.tblPathPlanCheck as tblc left join CoresStudy.dbo.tblAnatomyTask as tblt  "+
					" 		on tblc.studyNo = tblt.studyNo   " +
					" 			left join ("+
					" 								select v.visceraCode,v.visceraName,v.pVisceraCode "+
					" 								from CoresSystemSet.dbo.dictViscera as v left join CoresSystemSet.dbo.dictVisceraAnimal as vAnimal"+
					" 									on vAnimal.visceraCode = v.visceraCode"+
					" 								where v.level = 2 and ( v.animalFlag = 1 or vAnimal.animalTypeName = :animalTypeName)"+
		
					" 								) as dictviscera on dictviscera.pVisceraCode = tblc.visceraCode"+
					" 	where tblt.taskId =  :taskId and tblc.atanomyCheckFlag = 1" +
					" 	"+
					" 	) as tblv left join CoresSystemSet.dbo.dictViscera as dict "+
					" 		on tblv.visceraCode = dict.visceraCode left join CoresSystemSet.dbo.dictViscera as dict2 "+
					" 		 	on tblv.subvisceraCode = dict2.visceraCode "+
					" where dict.gender =0 or dict.gender = :gender"+
					" order by sn,subsn";
		List<Map<String,Object>> mapList =  getSession().createSQLQuery(sql)
														.setParameter("taskId", taskId)
//														.setParameter("animalCode", animalCode)
														.setParameter("gender", gender)
														.setParameter("animalTypeName", animalTypeName)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}


	public boolean isHasAutolyze_1(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where studyNo = ? and animalCode = ? and autolyzaFlag =1"+
						"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
						"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, studyNo)
								.setParameter(1, animalCode)
								.setParameter(2, visceraCode)
								.setParameter(3, subVisceraCode)
								.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where studyNo = ? and animalCode = ? and autolyzaFlag =1"+
						"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
										.setParameter(0, studyNo)
										.setParameter(1, animalCode)
										.setParameter(2, visceraCode)
										.uniqueResult();
		}
		return  count > 0;
	}


	public boolean isHasRecord_1(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
			" from CoresStudy.dbo.TblAnatomyCheck "+
			" where studyNo = ? and animalCode = ? "+
//			"    and visceraCode = ? and subVisceraCode is not null" +
//			"  and subVisceraCode = ?";
			"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
			"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
			.setParameter(0, studyNo)
			.setParameter(1, animalCode)
			.setParameter(2, visceraCode)
			.setParameter(3, subVisceraCode)
			.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
			" from CoresStudy.dbo.TblAnatomyCheck "+
			" where studyNo = ? and animalCode = ? "+
			"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
			.setParameter(0, studyNo)
			.setParameter(1, animalCode)
			.setParameter(2, visceraCode)
			.uniqueResult();
		}
		return  count > 0;
	}


	public boolean isMissing_1(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		Integer count  ;
		if(null != subVisceraCode){
			//子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where studyNo = ? and animalCode = ? and autolyzaFlag = 2"+
						"    and ( (visceraCode = ? and  isnull(subVisceraCode,'') ='') " +
						"  or subVisceraCode = ? )";
			count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, studyNo)
								.setParameter(1, animalCode)
								.setParameter(2, visceraCode)
								.setParameter(3, subVisceraCode)
								.uniqueResult();
			
		}else{
			//非子脏器
			String sql = "select count(id) "+
						" from CoresStudy.dbo.TblAnatomyCheck "+
						" where studyNo = ? and animalCode = ? and autolyzaFlag = 2"+
						"    and visceraCode = ? ";
			count = (Integer) getSession().createSQLQuery(sql)
										.setParameter(0, studyNo)
										.setParameter(1, animalCode)
										.setParameter(2, visceraCode)
										.uniqueResult();
		}
		return  count > 0;
	}


	@SuppressWarnings("unchecked")
	public List<TblAnatomyCheck> getListByTaskId(String taskId) {
		String hql = "select c from TblAnatomyCheck c , com.lanen.model.path.TblAnatomyAnimal a " +
				" where	c.studyNo = a.studyNo and c.animalCode = a.animalCode " +
				"  and a.taskId = ? " +
				" order by c.animalCode ";
		List<TblAnatomyCheck> list = getSession().createQuery(hql)
												.setParameter(0, taskId)
												.list();
		return list;
	}


	public Json saveOne_3(TblAnatomyCheck tblAnatomyCheck, String operateRsn) {
		Json json = new Json();
		String id = getKey();
		tblAnatomyCheck.setId(id);
		
		//保存固定记录
		TblVisceraFixed tblVisceraFixed = new TblVisceraFixed();
		String fixedId = tblVisceraFixedService.getKey();
		tblVisceraFixed.setId(fixedId);
		tblVisceraFixed.setSessionId(tblAnatomyCheck.getSessionId());
		tblVisceraFixed.setStudyNo(tblAnatomyCheck.getStudyNo());
//		tblVisceraFixed.setOperateTime(new Date());
		tblVisceraFixed.setOperateTime(tblAnatomyCheck.getOperateTime());
		tblVisceraFixed.setOperator(tblAnatomyCheck.getOperator());
		tblVisceraFixed.setAnimalCode(tblAnatomyCheck.getAnimalCode());
		tblVisceraFixed.setFixedType(1);
		tblVisceraFixed.setAnatomyCheckRecordId(id);
		tblVisceraFixedService.save(tblVisceraFixed);
		
		//固定历史记录
		TblVisceraFixedHis tblVisceraFixedHis = new TblVisceraFixedHis();
		tblVisceraFixedHis.setId(getKey("TblVisceraFixedHis"));
		tblVisceraFixedHis.setSessionId(tblAnatomyCheck.getSessionId());
		tblVisceraFixedHis.setStudyNo(tblAnatomyCheck.getStudyNo());
		tblVisceraFixedHis.setOperateTime(tblAnatomyCheck.getOperateTime());
		tblVisceraFixedHis.setOperator(tblAnatomyCheck.getOperator());
		tblVisceraFixedHis.setAnimalCode(tblAnatomyCheck.getAnimalCode());
		tblVisceraFixedHis.setFixedType(1);
		tblVisceraFixedHis.setAnatomyCheckRecordId(id);
		
		tblVisceraFixedHis.setOperate("添加");
		tblVisceraFixedHis.setOperateDate(tblAnatomyCheck.getOperateTime());
		tblVisceraFixedHis.setOperater(tblAnatomyCheck.getOperator());
		tblVisceraFixedHis.setOperateRsn(operateRsn);
		tblVisceraFixedHis.setOldId(fixedId);
		getSession().save(tblVisceraFixedHis);
		
		getSession().save(tblAnatomyCheck);
		json.setSuccess(true);
		json.setMsg(id);
		return json;
	}

}
