package com.lanen.service.path;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.jsonAndModel.Json;
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblVisceraFixed;
import com.lanen.model.path.TblVisceraFixedCompare;

@Service
public class TblVisceraFixedServiceImpl extends BaseDaoImpl<TblVisceraFixed> implements TblVisceraFixedService{

	/**
	 * 剖检动物Service
	 */
	@Resource
	private TblAnatomyAnimalService tblAnatomyAnimalService;
	@Resource
	private TblAnatomyTaskService tblAnatomyTaskService;
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getVisceraMapListByTaskIdSessionIdAnimalCode(
			String taskId, String sessionId, String animalCode) {
		TblAnatomyTask t=tblAnatomyTaskService.getById(taskId);
		String studyNo=t.getStudyNo();
		TblAnatomyAnimal tblAnatomyAnimal=tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
		int gender=tblAnatomyAnimal.getGender();
//		String sql = "select u.visceraType,u.visceraName,u.visceraCode,viscera.sn"+
//					" from "+
//					" 	(select reqPlanCheck.visceraType,reqPlanCheck.visceraName,reqPlanCheck.visceraCode"+
//					" 	from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as reqPlanCheck"+
//					" 		on task.studyNo = reqPlanCheck.studyNo and task.anatomyReqNo = reqPlanCheck.reqNo"+
//					" 	where task.taskId =:taskId and reqPlanCheck.visceraFixedFlag=1" +
//					" 	union"+
//					" 	select anatomyCheck.visceraType,anatomyCheck.visceraName,anatomyCheck.visceraCode"+
//					" 	from CoresStudy.dbo.TblAnatomyCheck as anatomyCheck "+
//					" 	where anatomyCheck.studyNo=:studyNo and anatomyCheck.animalCode=:animalCode and anatomyCheck.visceraCode is not null)as u"+
//					" 	left join CoresSystemSet.dbo.dictViscera as viscera"+
//					" 	on u.visceraCode = viscera.visceraCode" +
//					"   where u.visceraCode not in " +
//					"    (select vmissing.visceraCode from CoresStudy.dbo.tblVisceraMissing as vmissing "+
//					"	   join  CoresStudy.dbo.tblAnatomyTask as at "+
//					"	   on vmissing.studyNo=at.studyNo "+
//					"	   where at.taskId=:taskId  and vmissing.animalCode=:animalCode and vmissing.subVisceraName is  null and vmissing.sessionId=:sessionId)" +
//					"    and u.visceraCode not in " +
//					"    (select visceraCode from CoresStudy.dbo.tblVisceraFixed as tvf" +
//					"       where tvf.sessionId=:sessionId and tvf.animalCode=:animalCode and tvf.visceraCode is not null )" +
//					"    and u.visceraCode not in " +
//					"    (select visceraCode from CoresStudy.dbo.TblAnatomyCheck " +
//					"      where studyNo=:studyNo  and animalCode=:animalCode and autolyzaFlag=1)" +
//					"     and  (viscera.gender=0 or viscera.gender=:gender)"+
//					" order by viscera.snFixed";
		
		
		
//		String sql = "select u.visceraType,u.visceraName,u.visceraCode,viscera.sn" +
//		" from " +
//		" (select reqPlanCheck.visceraType,reqPlanCheck.visceraName,reqPlanCheck.visceraCode" +
//		" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as reqPlanCheck" +
//		" 	on task.studyNo = reqPlanCheck.studyNo and task.anatomyReqNo = reqPlanCheck.reqNo" +
//		" where task.taskId ='"+taskId+"' and reqPlanCheck.visceraFixedFlag=1" +
//		" union" +
//		" select anatomyCheck.visceraType,anatomyCheck.visceraName,anatomyCheck.visceraCode" +
//		" from CoresStudy.dbo.TblAnatomyCheck as anatomyCheck " +
//		" where anatomyCheck.studyNo='"+studyNo+"' " +
//		"  and anatomyCheck.animalCode='"+animalCode+"' and anatomyCheck.visceraCode is not null)as u" +
//		" left join CoresSystemSet.dbo.dictViscera as viscera" +
//		" on u.visceraCode = viscera.visceraCode" +
//		" where u.visceraCode not in " +
//		" (select vmissing.visceraCode from CoresStudy.dbo.tblVisceraMissing as vmissing" +
//		" 	join  CoresStudy.dbo.tblAnatomyTask as at" +
//		" 	on vmissing.studyNo=at.studyNo" +
//		" where at.taskId='"+taskId+"'  and vmissing.animalCode='"+animalCode+"' and vmissing.subVisceraName is  null and vmissing.sessionId='"+sessionId+"')" +
//		" and u.visceraCode not in " +
//		" (select visceraCode from CoresStudy.dbo.tblVisceraFixed as tvf" +
//		" 	where tvf.sessionId='"+sessionId+"' and tvf.animalCode='"+animalCode+"' and tvf.visceraCode is not null )" +
//		" and u.visceraCode not in " +
//		" (select visceraCode from CoresStudy.dbo.TblAnatomyCheck " +
//		" 	where studyNo='"+studyNo+"'  and animalCode='"+animalCode+"' and autolyzaFlag=1)" +
//		" 	and  (viscera.gender=0 or viscera.gender="+gender+")" +
//		" order by viscera.snFixed";
		
		String sql = "		select distinct u.visceraType,u.visceraName,u.visceraCode,viscera.snFixed "+
					" 		from "+
					" 			(	select reqPlanCheck.visceraType,reqPlanCheck.visceraName,reqPlanCheck.visceraCode"+
					" 				from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as reqPlanCheck"+
					" 					on task.studyNo = reqPlanCheck.studyNo and task.anatomyReqNo = reqPlanCheck.reqNo"+
					" 				where task.taskId ='"+taskId+"' and reqPlanCheck.visceraFixedFlag=1"+
					" 			union"+
					" 				select anatomyCheck.visceraType,anatomyCheck.visceraName,anatomyCheck.visceraCode"+
					" 				from CoresStudy.dbo.TblAnatomyCheck as anatomyCheck "+
					" 				where anatomyCheck.studyNo=:studyNo and anatomyCheck.animalCode=:animalCode and anatomyCheck.visceraCode is not null" +
					" 					  and anatomyCheck.autolyzaFlag = 0 "+
					" 		)as u"+
					" 			left join CoresSystemSet.dbo.dictViscera as viscera"+
					" 			on u.visceraCode = viscera.visceraCode"+
					" 			left join CoresSystemSet.dbo.dictViscera as dictviscera2 on dictviscera2.pVisceraCode = u.visceraCode "+

					" 			left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+
					" 		on u.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag != 0 and "+
					" 		 anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode and"+
					" 		( isnull(dictviscera2.visceraCode,'') = '' or  isnull(anatomycheck.subVisceraCode,'') = '' or"+
					" 		 isnull(dictviscera2.visceraCode,'') = isnull(anatomycheck.subVisceraCode,''))  "+

					" 		 left join CoresStudy.dbo.tblVisceraMissing as missing on u.visceraCode = missing.visceraCode "+
					" 		and missing.animalCode = :animalCode and missing.studyNo = :studyNo and "+
					" 		( isnull(dictviscera2.visceraCode,'') = '' or  isnull(missing.subVisceraCode,'') = '' or"+
					" 		 isnull(dictviscera2.visceraCode,'') = isnull(missing.subVisceraCode,''))  "+

					" 		where  anatomycheck.visceraCode is null and missing.visceraCode is null  and"+
					" 			 u.visceraCode not in "+
					" 		(select visceraCode from CoresStudy.dbo.tblVisceraFixed as tvf"+
					" 		where tvf.sessionId=:sessionId and tvf.animalCode=:animalCode and tvf.visceraCode is not null ) " +
					" and  (viscera.gender=0 or viscera.gender = :gender ) "+
		
					" 	order by viscera.snFixed";
		
		
//		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
//														.setParameter("taskId", taskId)
//														.setParameter("sessionId", sessionId)
//														.setParameter("studyNo", studyNo)
//														.setParameter("animalCode", animalCode)
//														.setParameter("gender", gender)
//														.setResultTransformer(new MapResultTransformer())
		List<Map<String,Object>> mapList = null;
		SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
//		createSQLQuery.setParameter("taskId", taskId);
		createSQLQuery.setParameter("sessionId", sessionId);
		createSQLQuery.setParameter("studyNo", studyNo);
		createSQLQuery.setParameter("animalCode", animalCode);
		createSQLQuery.setParameter("gender", gender);
		createSQLQuery.setResultTransformer(new MapResultTransformer());
		mapList = createSQLQuery.list();
		return mapList;
	}

	public Json deleteOne(String id) {
		Json json = new Json();
		TblVisceraFixed tblVisceraFixed = getById(id);
		if(null != tblVisceraFixed){
			boolean visceraFixedFinishFlag = tblAnatomyAnimalService
			.isVisceraFixedFinish(tblVisceraFixed.getStudyNo(),tblVisceraFixed.getAnimalCode());
			if(!visceraFixedFinishFlag){
				getSession().delete(tblVisceraFixed);
				json.setSuccess(true);
			}else{
				json.setMsg("该动物脏器固定已完成,不可以删除！");
			}
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<TblVisceraFixed> getListBySessionIdAnimalCode(String sessionId,
			String animalCode) {
		String hql = " from TblVisceraFixed where sessionId = :sessionId and animalCode = :animalCode and fixedType=0";
		List<TblVisceraFixed> list = getSession().createQuery(hql)
													.setParameter("sessionId", sessionId)
													.setParameter("animalCode", animalCode)
													.list();
		return list;
	}
	//(map:visceraType,visceraName,visceraCode,sn)
	public Json saveList(String sessionId, String studyNo, String animalCode,
			String userName, Date date,
			List<Map<String, Object>> selectedMapList) {
		Json json = new Json();
		if(null != sessionId && null != studyNo && null != animalCode &&
				null != selectedMapList){
			boolean visceraFixedFinishFlag = tblAnatomyAnimalService
				.isVisceraFixedFinish(studyNo,animalCode);
			if(!visceraFixedFinishFlag){
				TblVisceraFixed tblVisceraFixed = null;
				for(Map<String,Object> map:selectedMapList){
					tblVisceraFixed = new TblVisceraFixed();
					String id = getKey();
					Integer visceraType = (Integer) map.get("visceraType");
					String visceraName = (String) map.get("visceraName");
					String visceraCode = (String) map.get("visceraCode");
					
					tblVisceraFixed.setId(id);
					tblVisceraFixed.setSessionId(sessionId);
					tblVisceraFixed.setStudyNo(studyNo);
					tblVisceraFixed.setAnimalCode(animalCode);
					tblVisceraFixed.setVisceraType(visceraType);
					tblVisceraFixed.setVisceraCode(visceraCode);
					tblVisceraFixed.setVisceraName(visceraName);
					tblVisceraFixed.setOperateTime(date);
					tblVisceraFixed.setOperator(userName);
					
					getSession().save(tblVisceraFixed);
				}
				
				json.setSuccess(true);
			}else{
				json.setMsg("该动物脏器固定已完成,不可以在添加！");
			}
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListBySessionIdAnimalCodeVisceraName(
			List<String> sessionIds, String studyNoSelected,String animalCodeSelected, String visceraNameSelected) {
	String sql="SELECT "+
			   "    vf.id,vf.sessionId,vf.studyNo,vf.animalCode,vf.fixedType,vf.visceraType "+
			   "   ,vf.visceraCode,vf.visceraName,vf.subVisceraCode,vf.subVisceraName "+
			   "   ,(select  "+
			   "      realName  "+
				"	  from  CoresUserPrivilege.dbo.tbluser  "+
				"	  where userName=vf.operator "+
				"	) as operator "+
				"  ,operateTime " +
				" ,dv.snFixed"+
			  " FROM CoresStudy.dbo.tblVisceraFixed as vf " +
			  " left join CoresSystemSet.dbo.dictViscera  as dv on vf.visceraCode = dv.visceraCode"+
			  " where vf.sessionId in (:sessionIds)";
			if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
				sql=sql+" and vf.studyNo=:studyNoSelected";
			}
			if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
				sql=sql+" and vf.animalCode=:animalCodeSelected";
			}
			if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
				sql=sql+" and vf.visceraName=:visceraNameSelected";
			}
			sql=sql+" order by  vf.studyNo,vf.animalCode ";
	
			Query query=getSession().createSQLQuery(sql);
			query.setParameterList("sessionIds", sessionIds);
			if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
				query.setParameter("studyNoSelected", studyNoSelected);
			}
			if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
				query.setParameter("animalCodeSelected", animalCodeSelected);
			}
			if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
				query.setParameter("visceraNameSelected", visceraNameSelected);
			}
			List<Map<String,Object>> mapList = query.setResultTransformer(new MapResultTransformer()).list();
		
		System.out.println(mapList.size());
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListBySessionIdAnimalCodeVisceraName2(
			List<String> sessionIds, String studyNoSelected,String animalCodeSelected, String visceraNameSelected) {
	/*String sql = "select  vf.studyNo, abc.animalCode,abc.visceraName,missing.subVisceraCode,missing.subVisceraName,vf._fixedFlag, tblc._autolyzaFlag,missing._missFlag,missing._missType, missing.missingRsn, "+ 
					 " case when missingRsn is not null then  "+
		 " (select realName    from  CoresUserPrivilege.dbo.tbluser where userName=missing.operator )  "+
		 " else case when  autolyzaFlag=1 then "+
		 " (select realName    from  CoresUserPrivilege.dbo.tbluser where userName=tblc.operator )  "+
		 " else  "+
		"	(select realName  from  CoresUserPrivilege.dbo.tbluser where userName=vf.operator ) "+
		"	end  "+
		 "	end as operator,  "+
		 "	 case when missingRsn is not null then  "+
		 "	 missing.operateTime   "+
		"			 	 else case when  autolyzaFlag=1 then  "+
		 "	 tblc.operateTime   "+
		 "	 else  "+
		 "	vf.operateTime   "+
		 "	end  "+
		 "	end as operateTime,dv.snFixed "+
		" from (  "+
		" select studyNoReqNo.studyNo,studyNoReqNo.anatomyReqNo,animalCodes.animalCode,visceraNames.visceraName  "+
		"  from   "+
		"  ("+
		"	select distinct animalCode from tblAnatomyAnimal where visceraFixedSessionId in (:sessionIds)) as animalCodes "+ 
		"	cross join"+
		"	(select studyNo,anatomyReqNo from [tblAnatomyTask] where taskId in "+
		"			(select taskId from tblPathSession where sessionId in (:sessionIds))"+
		"	) as studyNoReqNo"+
		"	 cross join  "+
		"	("+
		"		select distinct visceraName from"+
		"		("+
		"	 		select visceraName"+
		"			from [tblAnatomyReqPathPlanCheck] as reqVisceraNames"+
		"			inner join"+
		"			(select studyNo,anatomyReqNo from [tblAnatomyTask] where taskId in"+
		"				(select taskId from tblPathSession where sessionId in (:sessionIds))"+
		"			) as studyNoReqNo"+
		"			on studyNoReqNo.studyNo=reqVisceraNames.studyNo and studyNoReqNo.anatomyReqNo=reqVisceraNames.reqNo"+
		"			where visceraFixedFlag=1  and visceraname is not null "+

		"			union "+

		"			select visceraName from TblAnatomyCheck as tac "+
		"			inner join "+
		"			(select studyNo,anatomyReqNo from [tblAnatomyTask] where taskId in(select taskId from tblPathSession where sessionId in (:sessionIds))"+
		"			) as studyNoReqNo "+
		"			on studyNoReqNo.studyNo=tac.studyNo "+
		"			where animalCode in		(select distinct animalCode from tblAnatomyAnimal where visceraFixedSessionId in (:sessionIds))"+
		"			and  autolyzaFlag=0 and visceraname is not null "+


		"		) as visceraNames1"+
		"	)as visceraNames"+
		
		"  ) as abc  "+
		"  left join  "+
		"  (select *,'Y' _fixedFlag  from TblVisceraFixed ) as vf   "+
		"  on vf.animalCode=abc.animalCode and vf.visceraName=abc.visceraName  and vf.studyNo=abc.studyNo"+
		"  left join  "+
		"  (	 select studyNo,animalCode,visceraCode,visceraName,subVisceraCode,subVisceraName,'缺失' _missType,missingRsn,'Y' _missFlag,operator,operateTime from tblVisceraMissing as miss where  missingRsn is not null "+
		"	 union "+
		"	 select tblc2.studyNo, tblc2.animalCode,tblc2.visceraCode,tblc2.visceraName,tblc2.subVisceraCode,tblc2.subVisceraName,tblc2.anatomyFingding as _missType, missingRsn,'Y2' _missFlag,operator,operateTime "+
		"	 from TblAnatomyCheck as tblc2"+
		"		left join"+
		"	tblVisceraQueRu as queru on queru.anatomyCheckId=tblc2.id where  autolyzaFlag=2 "+

		"  ) as missing  "+
		"  on missing.animalCode=abc.animalCode and missing.visceraName=abc.visceraName and missing.studyNo=abc.studyNo "+
		" left join  "+
		"  (select *,'Y' _autolyzaFlag from TblAnatomyCheck where autolyzaFlag=1) as tblc  "+
		" 	 on tblc.animalCode=abc.animalCode and tblc.visceraName=abc.visceraName  and tblc.studyNo=abc.studyNo"+
		" left join CoresSystemSet.dbo.dictViscera  as dv on (vf.visceraCode = dv.visceraCode or missing.visceraCode = dv.visceraCode or	tblc.visceraCode = dv.visceraCode) "+
		" where (_fixedFlag is not null or _autolyzaFlag is not null or _missFlag is not null) ";*/
		String sql = "select u.studyNo,u.reqNo,u.animalCode,u.visceraCode,u.visceraName, "+
	" fixed.id as fixedId, "+
	" case when fixed.id is not null then 'Y' else '' end as _fixedFlag, "+
	" missing.id as missingId, "+
	" acheck.id as checkId, "+
	" case when acheck.id is not null and acheck.autolyzaFlag =1 then 'Y' else '' end as _autolyzaFlag, "+
	" case when acheck.id is not null and acheck.autolyzaFlag =2 then 'Y2'  "+
	" when missing.id is not null then 'Y' "+
	" else '' end as _missFlag,"+
	" case when acheck.id is not null and acheck.autolyzaFlag =2 then acheck.anatomyFingding "+
	" when  missing.id is not null then '缺失'"+
	" else null end as  _missType, "+
	
	" case when acheck.id is not null and acheck.autolyzaFlag =2 then qr.missingRsn  "+
	" when missing.id is not null then missing.missingRsn "+
	" else '' end as missingRsn, "+

	" case when missing.subVisceraName is not null then missing.subVisceraName "+
	" when acheck.subVisceraName is not null then acheck.subVisceraName "+
	" else null end as subVisceraName,dv.snFixed,u.visceraFixedFinishTime"+

" from ( "+
	
	"	select distinct anatomyanimal.studyNo,anatomyanimal.animalCode,anatomycheck.visceraCode,anatomycheck.visceraName,task.anatomyReqNo as reqNo,anatomyanimal.visceraFixedFinishTime "+
	"	from CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal  "+
	"		left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck on anatomyanimal.studyNo = anatomycheck.studyNo and  "+
	"		anatomyanimal.animalCode = anatomycheck.animalCode and anatomycheck.autolyzaFlag = 0  "+
	"		and anatomycheck.visceraCode is not null "+
	"		left join CoresStudy.dbo.tblanatomytask as task on anatomyanimal.taskId = task.taskId "+
	"	where anatomyanimal.visceraFixedSessionId in (:sessionIds) and anatomycheck.visceraCode is not null "+

	"	union "+
		
	"	select anatomyanimal.studyNo,anatomyanimal.animalCode,plancheck.visceraCode,plancheck.visceraName,plancheck.reqNo,anatomyanimal.visceraFixedFinishTime "+
	"	from  CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal  left join  CoresStudy.dbo.tblPathSession as psession "+ 
	"		on anatomyanimal.visceraFixedSessionId = psession.sessionId "+
	"		left join CoresStudy.dbo.tblAnatomyTask as task  "+
	"		on psession.taskId = task.taskId left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck "+
	"		on task.studyNo = plancheck.studyNo and task.anatomyReqNo = plancheck.reqNo and plancheck.visceraFixedFlag = 1 "+
	"	where anatomyanimal.visceraFixedSessionId in (:sessionIds ) "+
	" ) as u  "+
	 
	"  left join CoresStudy.dbo.tblVisceraFixed as fixed on fixed.studyNo = u.studyNo and fixed.animalCode = u.animalCode  "+
	"   and fixed.visceraCode = u.visceraCode "+
	   
	"  left join CoresStudy.dbo.tblVisceraMissing as missing on 	missing.studyNo = u.studyNo and missing.animalCode = u.animalCode  "+
	 "  and missing.visceraCode = u.visceraCode  "+
	   
	 "  left join CoresStudy.dbo.TblAnatomyCheck as acheck on acheck.studyNo = u.studyNo and acheck.animalCode = u.animalCode  "+
	 "  and acheck.visceraCode = u.visceraCode and (acheck.autolyzaFlag = 1 or acheck.autolyzaFlag = 2)  "+
	 "  left join CoresStudy.dbo.tblVisceraQueRu as qr on acheck.id = qr.anatomyCheckId "+

	 "  left join CoresSystemSet.dbo.dictViscera  as dv on (u.visceraCode = dv.visceraCode) "+
	 "  where  fixed.id is not null or missing.id is not null or acheck.id is not null ";
	
			if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
				sql=sql+" and vf.studyNo=:studyNoSelected";
			}
			if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
				sql=sql+" and vf.animalCode=:animalCodeSelected";
			}
			if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
				sql=sql+" and vf.visceraName=:visceraNameSelected";
			}
		
			sql=sql+" order by  dv.snFixed ";
	
			Query query=getSession().createSQLQuery(sql);
			query.setParameterList("sessionIds", sessionIds);
			if(null!=studyNoSelected && !studyNoSelected.equals("全部") && !studyNoSelected.equals("")){
				query.setParameter("studyNoSelected", studyNoSelected);
			}
			if(null!=animalCodeSelected && !animalCodeSelected.equals("全部") && !animalCodeSelected.equals("")){
				query.setParameter("animalCodeSelected", animalCodeSelected);
			}
			if(null!=visceraNameSelected && !visceraNameSelected.equals("全部") && !visceraNameSelected.equals("")){
				query.setParameter("visceraNameSelected", visceraNameSelected);
			}
			List<Map<String,Object>> mapList = query.setResultTransformer(new MapResultTransformer()).list();
		System.out.println("sql="+sql);
		System.out.println(mapList.size());
		return mapList;
	}
	public Integer getAnimalCountBySessionIds(List<String> sessionIds) {
		Integer animalCount=0;
		String sql="select sum(vf.perCount) from " +
				   " (select count(distinct(animalCode)) as perCount " +
				   "   FROM CoresStudy.dbo.tblVisceraFixed " +
				   "   where sessionId in (:sessionIds)" +
				   "   group by studyNo) as vf";
			animalCount=(Integer) getSession().createSQLQuery(sql)
			                                  .setParameterList("sessionIds", sessionIds)
			                                  .uniqueResult();
			if(animalCount==null){
				animalCount=0;
			}
		return animalCount;
	}

	public void saveAbnFixedList(List<String> abnAnotomyCheckIdList,
			List<TblVisceraFixed> listFixed) {
//		List<TblVisceraFixed> list2=getListByAnatomyCheckId(abnAnotomyCheckIdList);
//		if(null!=list2 && list2.size()>0){
//			for(TblVisceraFixed t:list2){
//				getSession().delete(t);
//			}
//		}
		if(null!=listFixed && listFixed.size()>0){
			for(TblVisceraFixed t1:listFixed){
				getSession().save(t1);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<TblVisceraFixed> getListByAnatomyCheckId(
			List<String> abnAnotomyCheckIdList) {
		System.out.print(abnAnotomyCheckIdList.size());
		List<TblVisceraFixed> list=null;
		String hql=" from TblVisceraFixed where anatomyCheckRecordId in (:abnAnotomyCheckIdList)";
		list=getSession().createQuery(hql).setParameterList("abnAnotomyCheckIdList", abnAnotomyCheckIdList).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public TblVisceraFixed getByAnatomyCheckId(String anatomyCheckRecordId) {
		String hql=" from TblVisceraFixed where anatomyCheckRecordId=:anatomyCheckRecordId";
		List<TblVisceraFixed> list=getSession().createQuery(hql)
		                                            .setParameter("anatomyCheckRecordId", anatomyCheckRecordId)
		                                            .list();
		TblVisceraFixed tblVisceraFixed=null;
		if(null!=list && list.size()>0){
			tblVisceraFixed=list.get(0);
		}
		return tblVisceraFixed;
	}

	@SuppressWarnings("unchecked")
	public List<TblVisceraFixed> getListByStudyNoAnimalCode(String studyNo,
			String animalCode) {
		String hql = " from TblVisceraFixed where studyNo = :studyNo and animalCode = :animalCode and fixedType=1";
		List<TblVisceraFixed> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListBySessionid(String sessionid) {
		String sql =" select tblv.animalCode,tbla.visceraName,tbla.subVisceraName,tbla.anatomyPos,tbla.anatomyFingding,tbla.bodySurfacePos, "+
		"  tbla.pos,tbla.shape,tbla.color,tbla.texture,tbla.number,tbla.range,tbla.lesionDegree,tbla.size ,	  tblv.id ,tblv.studyNo,tblv.animalCode, "+
		"   tblv.visceraName, tblv.subVisceraName,tblv.operator,tblv.operateTime "+
		" from CoresStudy.dbo.tblVisceraFixed as tblv left join  "+
		" CoresStudy.dbo.TblAnatomyCheck as tbla  "+
		" on tblv.anatomyCheckRecordId = tbla.id "+
		" where tblv.sessionId= :sessionid and tbla.id is not null  ";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql).setParameter("sessionid", sessionid).setResultTransformer(new MapResultTransformer()).list();
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNormallessListByTaskIdAndSessionid(String taskId,String sessionid) {
		String sql =" select tblv.animalCode,tbla.visceraName,tbla.subVisceraName,tbla.anatomyPos,tbla.anatomyFingding,tbla.bodySurfacePos, "+
		"  tbla.pos,tbla.shape,tbla.color,tbla.texture,tbla.number,tbla.range,tbla.lesionDegree,tbla.size ,	  tblv.id ,tblv.studyNo,tblv.animalCode, "+
		"   tblv.visceraName, tblv.subVisceraName,tblv.operator,tblv.operateTime "+
		" from CoresStudy.dbo.tblVisceraFixed as tblv left join  "+
		" CoresStudy.dbo.TblAnatomyCheck as tbla  "+
		" on tblv.anatomyCheckRecordId = tbla.id ";
		if(sessionid!=null&&!"".equals(sessionid)&&!"全部".equals(sessionid)){
		   sql += " where tblv.sessionId= :sessionid and tbla.id is not null";
		}else {
			sql +=  " join CoresStudy.dbo.tblPathSession as pathSession on tblv.sessionId=pathSession.sessionId" +
			" join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblv.studyNo and task.taskId=pathSession.taskId" +
			" and task.taskId=:taskId  where tbla.id is not null";
		}
		System.out.println("查询备注的sql="+sql);
		Query query = getSession().createSQLQuery(sql);
		if(sessionid!=null&&!"".equals(sessionid)&&!"全部".equals(sessionid))
		{
			query.setParameter("sessionid", sessionid);
		}else {
			query.setParameter("taskId",taskId);
		}
		List<Map<String,Object>> mapList = query.setResultTransformer(new MapResultTransformer()).list();
		System.out.println("taskId is "+taskId+" 结果是："+mapList.size());
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNormallessListByTaskIdAndSessionids(String taskId,String sessionid) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		String sql =" select tblv.animalCode,tbla.visceraName,tbla.subVisceraName,tbla.anatomyPos,tbla.anatomyFingding,tbla.bodySurfacePos, "+
		"  tbla.pos,tbla.shape,tbla.color,tbla.texture,tbla.number,tbla.range,tbla.lesionDegree,tbla.size ,	  tblv.id ,tblv.studyNo,tblv.animalCode, "+
		"   tblv.visceraName, tblv.subVisceraName,tblv.operator,tblv.operateTime "+
		" from CoresStudy.dbo.tblVisceraFixed as tblv left join  "+
		" CoresStudy.dbo.TblAnatomyCheck as tbla  "+
		" on tblv.anatomyCheckRecordId = tbla.id ";
		if(sessionid!=null&&!"".equals(sessionid)&&!"全部".equals(sessionid)){
		   sql += " where tblv.sessionId= :sessionid and tbla.id is not null";
		}else {
			sql +=  " join CoresStudy.dbo.tblPathSession as pathSession on tblv.sessionId=pathSession.sessionId" +
			" join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblv.studyNo and task.taskId=pathSession.taskId" +
			" and task.taskId in (:taskId)  where tbla.id is not null";
		}
		System.out.println("查询备注的sql="+sql);
		Query query = getSession().createSQLQuery(sql);
		if(sessionid!=null&&!"".equals(sessionid)&&!"全部".equals(sessionid))
		{
			query.setParameter("sessionid", sessionid);
		}else {
			query.setParameterList("taskId",taskList);
		}
		List<Map<String,Object>> mapList = query.setResultTransformer(new MapResultTransformer()).list();
		System.out.println("taskId is "+taskId+" 结果是："+mapList.size());
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNormallessListByStudyNoAndReqNo(
			String studyNo, int reqNo) {
		String sql =" select tblv.animalCode,tbla.visceraName,tbla.subVisceraName,tbla.anatomyPos,tbla.anatomyFingding,tbla.bodySurfacePos, "+
		"  tbla.pos,tbla.shape,tbla.color,tbla.texture,tbla.number,tbla.range,tbla.lesionDegree,tbla.size ,	  tblv.id ,tblv.studyNo,tblv.animalCode, "+
		"   tblv.visceraName, tblv.subVisceraName,tblv.operator,tblv.operateTime "+
		" from CoresStudy.dbo.tblVisceraFixed as tblv left join  "+
		" CoresStudy.dbo.TblAnatomyCheck as tbla  "+
		" on tblv.anatomyCheckRecordId = tbla.id ";
	
		sql +=  " join CoresStudy.dbo.tblPathSession as pathSession on tblv.sessionId=pathSession.sessionId" +
			" join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=tblv.studyNo and task.taskId=pathSession.taskId" +
			" and task.studyNo=:studyNo and task.anatomyReqNo=:reqNo  where tbla.id is not null";
		
		System.out.println("查询备注的sql="+sql);
		Query query = getSession().createSQLQuery(sql);
		
		query.setParameter("studyNo",studyNo);
		query.setParameter("reqNo", reqNo);
		
		List<Map<String,Object>> mapList = query.setResultTransformer(new MapResultTransformer()).list();
		System.out.println("studyNo is "+studyNo+" reqNo is "+reqNo+" 结果是："+mapList.size());
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public TblVisceraFixed getByVisceraName(String studyNo, String animalCode,
			String visceraName) {
		String hql=" from TblVisceraFixed where studyNo=:studyNo and animalCode=:animalCode and visceraName=:visceraName";
		List<TblVisceraFixed>  list=getSession().createQuery(hql)
		                                            .setParameter("studyNo", studyNo)
		                                            .setParameter("animalCode", animalCode)
		                                            .setParameter("visceraName", visceraName)
		                                            .list();
		TblVisceraFixed tblVisceraFixed=null;
		if(null!=list && list.size()>0){
			tblVisceraFixed=list.get(0);
		}
		return tblVisceraFixed;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNeedNotWeighMapList(String taskId,
			String sessionId, String animalCode, String studyNo) {
		TblAnatomyAnimal tblAnatomyAnimal=tblAnatomyAnimalService.getByTaskIdAnimalCode(taskId, animalCode);
		int gender=tblAnatomyAnimal.getGender();
		String sql = "select u.visceraType,u.visceraName,u.visceraCode,viscera.sn"+
					" from "+
					" 	(select reqPlanCheck.visceraType,reqPlanCheck.visceraName,reqPlanCheck.visceraCode"+
					" 	from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqPathPlanCheck as reqPlanCheck"+
					" 		on task.studyNo = reqPlanCheck.studyNo and task.anatomyReqNo = reqPlanCheck.reqNo"+
					" 	where task.taskId =:taskId and reqPlanCheck.visceraFixedFlag=1" +
					" 	union"+
					" 	select anatomyCheck.visceraType,anatomyCheck.visceraName,anatomyCheck.visceraCode"+
					" 	from CoresStudy.dbo.TblAnatomyCheck as anatomyCheck "+
					" 	where anatomyCheck.studyNo =:studyNo and anatomyCheck.animalCode=:animalCode and anatomyCheck.visceraCode is not null )as u"+
					" 	left join CoresSystemSet.dbo.dictViscera as viscera"+
					" 	on u.visceraCode = viscera.visceraCode" +
					"   where u.visceraCode not in " +
					"    (select vmissing.visceraCode from CoresStudy.dbo.tblVisceraMissing as vmissing "+
					"	   join  CoresStudy.dbo.tblAnatomyTask as at "+
					"	   on vmissing.studyNo=at.studyNo "+
					"	   where at.taskId=:taskId  and vmissing.animalCode=:animalCode and vmissing.subVisceraName is  null and vmissing.studyNo =:studyNo)" +
					"    and u.visceraCode not in " +
					"    (select visceraCode from CoresStudy.dbo.tblVisceraFixed as tvf" +
					"       where tvf.sessionId=:sessionId and tvf.studyNo=:studyNo and tvf.animalCode=:animalCode)" +
					"    and u.visceraCode not in " +
					"    (select visceraCode from CoresStudy.dbo.TblAnatomyCheck " +
					"      where studyNo =:studyNo and studyNo=:studyNo  and animalCode=:animalCode and autolyzaFlag=1)" +
					"    and  (viscera.gender=0 or viscera.gender=:gender)" +
					"    and u.visceraCode not in " +
					"     (select arvw.visceraCode  " +
					"		 from CoresStudy.dbo.tblAnatomyReqVisceraWeigh  as arvw " +
					"		 join CoresStudy.dbo.tblAnatomyTask as tat " +
					"		 on arvw.studyNo=tat.studyNo and arvw.reqNo=tat.anatomyReqNo " +
					"		 where taskId=:taskId)"+
					" order by viscera.snFixed";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("taskId", taskId)
														.setParameter("sessionId", sessionId)
														.setParameter("animalCode", animalCode)
														.setParameter("studyNo", studyNo)
														.setParameter("gender", gender)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraNameAndCodeList(String taskId,
			String sessionId) {
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblVisceraFixed";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (4,5,6,7))";
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
		String sql="select distinct(visceraName),visceraCode from CoresStudy.dbo.TblVisceraFixed";
		if(null!=sessionId && !"全部".equals(sessionId) && !"".equals(sessionId)){
			sql=sql+" where visceraCode is not null and sessionId=:sessionId";
		}else{
			sql=sql+" where visceraCode is not null and sessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (4,5,6,7))";
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
			sql=sql+" where visceraFixedSessionId=:sessionId ";
		}else{
			sql=sql+" where  visceraFixedSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId=:taskId and sessionType in (4,5,6,7))" ;
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
			sql=sql+" where visceraFixedSessionId=:sessionId ";
		}else{
			sql=sql+" where  visceraFixedSessionId in " +
					"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
					"       where taskId in (:taskId) and sessionType in (4,5,6,7,8))" ;
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
			String sessionId, String visceraName, String animalCode) {
		String sql = " SELECT "+
			 " vf.id,vf.sessionId,vf.studyNo,animalCode,vf.visceraType "+ 
			 "     ,vf.visceraCode "+
			 "	  ,case when vf.visceraName is null then  "+
			 "	  (select case when bodySurfacePos is null then  "+ 
			 "	   ( case when subVisceraName is null then vf.visceraName else subVisceraName end) else bodySurfacePos  end  "+
			 "	    +anatomyFingding "+
			 "	    from CoresStudy.dbo.TblAnatomyCheck where id=vf.anatomyCheckRecordId) else vf.visceraName end as visceraName "+
			 "	  ,subVisceraCode,subVisceraName  "+
			 "     ,(select  realName  from  CoresUserPrivilege.dbo.tbluser   "+
			 "		  where userName=vf.operator  "+
			 "		) as operator  "+
			 "	  ,operateTime ,fixedType,anatomyCheckRecordId "+
			 "  FROM CoresStudy.dbo.tblVisceraFixed as vf " +
			 " 	left join CoresSystemSet.dbo.dictViscera as viscera"+
			 " 	on vf.visceraCode = viscera.visceraCode" +
				" where " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" vf.sessionId=:sessionId  ";
		}else{
			sql=sql+" vf.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId=:taskId and sessionType in (4,5,6,7))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and vf.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and vf.visceraName=:visceraName";
		}
		//sql=sql+" and vf.anatomyCheckRecordId is null  order by  vf.animalCode,vf.id ";
		sql=sql+" and vf.anatomyCheckRecordId is null  order by  viscera.snFixed ";
		
		//System.out.println("对比固定的sql="+sql);
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
		String sql = " SELECT "+
			 " vf.id,vf.sessionId,vf.studyNo,animalCode,vf.visceraType "+ 
			 "     ,vf.visceraCode "+
			 "	  ,case when vf.visceraName is null then  "+
			 "	  (select case when bodySurfacePos is null then  "+ 
			 "	   ( case when subVisceraName is null then vf.visceraName else subVisceraName end) else bodySurfacePos  end  "+
			 "	    +anatomyFingding "+
			 "	    from CoresStudy.dbo.TblAnatomyCheck where id=vf.anatomyCheckRecordId) else vf.visceraName end as visceraName "+
			 "	  ,subVisceraCode,subVisceraName  "+
			 "     ,(select  realName  from  CoresUserPrivilege.dbo.tbluser   "+
			 "		  where userName=vf.operator  "+
			 "		) as operator  "+
			 "	  ,operateTime ,fixedType,anatomyCheckRecordId "+
			 "  FROM CoresStudy.dbo.tblVisceraFixed as vf " +
			 " 	left join CoresSystemSet.dbo.dictViscera as viscera"+
			 " 	on vf.visceraCode = viscera.visceraCode" +
				" where " ;
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sql=sql+" vf.sessionId=:sessionId  ";
		}else{
			sql=sql+" vf.sessionId in " +
			"    (SELECT sessionId FROM CoresStudy.dbo.tblPathSession " +
			"       where taskId in (:taskId) and sessionType in (4,5,6,7))";
		}
		if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
			sql=sql+" and vf.animalCode=:animalCode";
		}
		if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
			sql=sql+" and vf.visceraName=:visceraName";
		}
		//sql=sql+" and vf.anatomyCheckRecordId is null  order by  vf.animalCode,vf.id ";
		sql=sql+" and vf.anatomyCheckRecordId is null  order by  viscera.snFixed ";
		
		//System.out.println("对比固定的sql="+sql);
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
	public List<Map<String,Object>> getListByConditions2(String taskId,String sessionId, String visceraName, String animalCode) {
	//获取sessionId
		System.out.println("sessionId="+sessionId+" taskId="+taskId);
		List<String> sessionids = new ArrayList<String>();
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sessionids.add(sessionId);
		}else if(taskId!=null){
			
			String sqlString = "SELECT sessionId FROM [CoresStudy].[dbo].[tblPathSession] where taskId=:taskId and sessionType in (4,5,6,7)";
			Query query = getSession().createSQLQuery(sqlString);
			query.setParameter("taskId", taskId);
			sessionids=query.list();
		}
		System.out.println("sessionids="+sessionids);
		if(sessionids!=null&&sessionids.size()>0)
		{
		 List<Map<String,Object>> allMaps =  getListBySessionIdAnimalCodeVisceraName2(sessionids, null,null,null);
		 List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		 System.out.println("allMaps.size()="+allMaps.size());
		 for(Map<String,Object> map:allMaps)
		 {
			 String animalCode1 = (String)map.get("animalCode");
			 String visceraName1 = (String)map.get("visceraName");
			boolean flag = true;
			if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
				//sql=sql+" and abc.animalCode=:animalCode";
				if(!animalCode.equals(animalCode1))
				{
					flag = false;
				}
			}
			boolean flag2 = true;
			if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
				//sql=sql+" and ab.cvisceraName=:visceraName";
				if(!visceraName.equals(visceraName1))
				{
					flag2 = false;
				}
			}
			if(flag&&flag2)
				mapList.add(map);
		 }

	
	
		System.out.println(mapList.size());
		return mapList;
		}
		return new ArrayList<Map<String,Object>>();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getListByConditions2s(String taskId,String sessionId, String visceraName, String animalCode) {
	
		List<String> taskList=new ArrayList<String>();
		if(taskId!=null)
		{
			for(String str:taskId.split(","))
			{
				taskList.add(str);
			}
		}
		//获取sessionId
		System.out.println("sessionId="+sessionId+" taskId="+taskId);
		List<String> sessionids = new ArrayList<String>();
		if(null!=sessionId && !sessionId.equals("全部") && !sessionId.equals("")){
			sessionids.add(sessionId);
		}else if(taskId!=null){
			
			String sqlString = "SELECT sessionId FROM [CoresStudy].[dbo].[tblPathSession] where taskId in (:taskId) and sessionType in (4,5,6,7)";
			Query query = getSession().createSQLQuery(sqlString);
			query.setParameterList("taskId", taskList);
			sessionids=query.list();
		}
		System.out.println("sessionids="+sessionids);
		if(sessionids!=null&&sessionids.size()>0)
		{
		 List<Map<String,Object>> allMaps =  getListBySessionIdAnimalCodeVisceraName2(sessionids, null,null,null);
		 List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		 System.out.println("allMaps.size()="+allMaps.size());
		 for(Map<String,Object> map:allMaps)
		 {
			 String animalCode1 = (String)map.get("animalCode");
			 String visceraName1 = (String)map.get("visceraName");
			boolean flag = true;
			if(null!=animalCode && !animalCode.equals("全部") && !animalCode.equals("")){
				//sql=sql+" and abc.animalCode=:animalCode";
				if(!animalCode.equals(animalCode1))
				{
					flag = false;
				}
			}
			boolean flag2 = true;
			if(null!=visceraName && !visceraName.equals("全部") && !visceraName.equals("")){
				//sql=sql+" and ab.cvisceraName=:visceraName";
				if(!visceraName.equals(visceraName1))
				{
					flag2 = false;
				}
			}
			if(flag&&flag2)
				mapList.add(map);
		 }

	
	
		System.out.println(mapList.size());
		return mapList;
		}
		return new ArrayList<Map<String,Object>>();
	}
	@SuppressWarnings("unchecked")
	public List<TblVisceraFixed> getVisceraFixedByStudyNoAndReqNo(String studyNo,Integer reqNo,String visceraName) {
		String sql = " SELECT  vf.id,vf.sessionId,vf.studyNo,vf.fixedType,vf.animalCode,vf.visceraType,vf.visceraCode "+
		 "	  ,case when vf.visceraName is null then  "+
		 "	  (select case when bodySurfacePos is null then  "+ 
		 "	   ( case when subVisceraName is null then visceraName else subVisceraName end) else bodySurfacePos  end  "+
		 "	    +anatomyFingding "+
		 "	    from CoresStudy.dbo.TblAnatomyCheck where id=vf.anatomyCheckRecordId) else vf.visceraName end as visceraName "+
		 "	  ,vf.subVisceraCode,vf.subVisceraName  "+
		 "     ,(select realName  from  CoresUserPrivilege.dbo.tbluser   "+
		 "		  where userName=vf.operator) as operator  "+
		 "	  ,vf.operateTime ,vf.fixedType,vf.anatomyCheckRecordId,anatomyAnimal.visceraFixedFinishTime "+
		 "  FROM CoresStudy.dbo.tblVisceraFixed as vf " +
		 " join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal " +
		 " on anatomyAnimal.animalCode=vf.animalCode and anatomyAnimal.studyNo=vf.studyNo " +
		 " left join CoresSystemSet.dbo.dictViscera as dictViscera " +
			" on dictViscera.visceraCode = vf.visceraCode " +
			"  join CoresStudy.dbo.tblPathSession as pathSession on vf.sessionId=pathSession.sessionId" +
			"  join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=vf.studyNo " +
			" and task.taskId=pathSession.taskId and task.taskId=anatomyAnimal.taskId" +
			"  where vf.studyNo=:studyNo and task.anatomyReqNo=:reqNo" ;
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			sql += " and vf.visceraName = :visceraName";
		}
		sql=sql+" and pathSession.sessionType in (4,5,6,7) ";//是脏器固定的数据
		sql += " and vf.anatomyCheckRecordId is null order by dictViscera.snFixed";
		System.out.println("后台固定表的sql="+sql);
		//String hql=" from TblVisceraFixed where studyNo=:studyNo";
		Query query = getSession().createSQLQuery(sql)
		                          .setParameter("studyNo", studyNo)
		                          .setParameter("reqNo", reqNo);
		if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
		{
			query.setParameter("visceraName", visceraName);
		}
		List<Map<String,Object>>  temp = query.setResultTransformer(new MapResultTransformer())
		                                            .list();
		
		List<TblVisceraFixed> list = new ArrayList<TblVisceraFixed>();
		for(Map<String,Object> map:temp)
		{
			TblVisceraFixed fixed = new TblVisceraFixed();
			//id,sessionId,studyNo,animalCode,visceraType,visceraCode,visceraName,subVisceraCode,subVisceraName
			//,,operator, ,operateTime ,fixedType,anatomyCheckRecordId "+
			fixed.setId((String)map.get("id"));
			fixed.setSessionId((String)map.get("sessionId"));
			fixed.setStudyNo((String)map.get("studyNo"));
			fixed.setAnimalCode((String)map.get("animalCode"));
			fixed.setFixedType((Integer)map.get("fixedType"));
			fixed.setVisceraType((Integer)map.get("visceraType"));
			fixed.setVisceraCode((String)map.get("visceraCode"));
			fixed.setVisceraName((String)map.get("visceraName"));
			fixed.setSubVisceraCode((String)map.get("subVisceraCode"));
			fixed.setSubVisceraName((String)map.get("subVisceraName"));
			fixed.setOperator((String)map.get("operator"));
		//	fixed.setOperateTime((Date)map.get("operateTime"));
			fixed.setOperateTime((Date)map.get("visceraFixedFinishTime"));
			fixed.setFixedType((Integer)map.get("fixedType"));
			fixed.setAnatomyCheckRecordId((String)map.get("anatomyCheckRecordId"));
			
			list.add(fixed);
		}
		return list;
		
	}
	public List<List<TblVisceraFixedCompare>> getVisceraFixedByStudyNoAndReqNo2(
			String studyNo, Integer reqNo, String visceraName) {
		List<String> sessionids = new ArrayList<String>();
		String sqlString = "SELECT sessionId FROM CoresStudy.dbo.tblPathSession where  sessionType in (4,5,6,7) and taskId in (select taskId from CoresStudy.dbo.[tblAnatomyTask] where studyNo=:studyNo and anatomyReqNo=:reqNo )";
		Query query = getSession().createSQLQuery(sqlString);
		query.setParameter("studyNo", studyNo);
		query.setParameter("reqNo", reqNo);
		sessionids=query.list();
		if(sessionids!=null&&sessionids.size()>0)
		{
			 List<Map<String,Object>> allMaps =  getListBySessionIdAnimalCodeVisceraName2(sessionids, null,null,null);
			 List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			 System.out.println("allMaps.size()="+allMaps.size());
			 for(Map<String,Object> map:allMaps)
			 {
				 String visceraName1 = (String)map.get("visceraName");		
				boolean flag2 = true;
				if(visceraName!=null&&!"".equals(visceraName)&&!"全部".equals(visceraName))
				{
					//sql=sql+" and ab.cvisceraName=:visceraName";
					if(!visceraName.equals(visceraName1))
					{
						flag2 = false;
					}
				}
				if(flag2)
					mapList.add(map);
			}
			 
				List<List<TblVisceraFixedCompare>> list = new ArrayList<List<TblVisceraFixedCompare>>();
				String fixedRemarkString = "";
				for(Map<String,Object> map:mapList)
				{
					TblVisceraFixedCompare fixed = new TblVisceraFixedCompare();
					fixed.setId((String)map.get("id"));
					fixed.setStudyNo((String)map.get("studyNo"));
					String animalCode1 = (String)map.get("animalCode");
					fixed.setAnimalCode(animalCode1);
					fixed.setVisceraCode((String)map.get("visceraCode"));
					String visceraName1 = (String)map.get("visceraName");
					fixed.setVisceraName(visceraName1);
					fixed.setSubVisceraCode((String)map.get("subVisceraCode"));
					String subVisceraName = (String)map.get("subVisceraName");
					fixed.setSubVisceraName(subVisceraName);
					fixed.setOperator((String)map.get("operator"));
					String _fixedFlag = (String)map.get("_fixedFlag");
					fixed.set_fixedFlag(_fixedFlag);
					String _autolyzaFlag = (String)map.get("_autolyzaFlag");
					fixed.set_autolyzaFlag(_autolyzaFlag);
					String _missType = (String)map.get("_missType");
					fixed.set_missType(_missType);
					String _missFlag = (String)map.get("_missFlag");
					fixed.set_missFlag(_missFlag);
					String missingRsn = (String)map.get("missingRsn");
					fixed.setMissingRsn(missingRsn);
					fixed.setOperateTime((Date)map.get("visceraFixedFinishTime"));
					
					if(_missFlag!=null&&("Y".equals(_missFlag)||"Y2".equals(_missFlag)))
					{
						if(subVisceraName!=null)
						{
							if(missingRsn!=null)
								fixedRemarkString += " "+animalCode1+" "+subVisceraName+_missType+":"+missingRsn+";";
							else
								fixedRemarkString += " "+animalCode1+" "+subVisceraName+_missType+";";
						}else
						{
							if(missingRsn!=null)
								fixedRemarkString += " "+animalCode1+" "+visceraName1+_missType+":"+missingRsn+";";
							else
								fixedRemarkString += " "+animalCode1+" "+visceraName1+_missType+";";
						}
					}
					
					
					boolean flag = false;
					//判断是否已经存在animalcode和visceraName相同的list
					List<TblVisceraFixedCompare> tempAnimalVisceraList = null;
					for(List<TblVisceraFixedCompare> animalVisceraList : list)
					{
						if(animalVisceraList.get(0).getAnimalCode().equals(animalCode1)&&animalVisceraList.get(0).getVisceraName().equals(visceraName1))
						{
							flag = true;
							tempAnimalVisceraList = animalVisceraList;
							break;
						}
					}
					if(!flag)
					{
						List<TblVisceraFixedCompare> listVisceraFixed = new ArrayList<TblVisceraFixedCompare>();
						listVisceraFixed.add(fixed);
						list.add(listVisceraFixed);

					}else
					{
						if(tempAnimalVisceraList!=null)
						{
							tempAnimalVisceraList.add(fixed);
						}
					}
				}
				
				/*for(List<TblVisceraFixedCompare> vfss:list)
				{
					System.out.println("==========");
					for(int i=0;i<vfss.size();i++)
					{
						TblVisceraFixedCompare vf = vfss.get(i);
						System.out.println("===="+vf.getAnimalCode()+"=="+vf.getVisceraName()+"=="+vf.getSubVisceraName());

					}
					
				}*/
				return list;
		}

	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVisceraFixedVisceraByStudyNoAndReqNo(String studyNo,Integer reqNo)
	{
		String sql = " SELECT  distinct(vf.visceraName) "+
		 "  FROM CoresStudy.dbo.tblVisceraFixed as vf " +
		 " left join CoresSystemSet.dbo.dictViscera as dictViscera " +
			" on dictViscera.visceraCode = vf.visceraCode " +
			"  join CoresStudy.dbo.tblPathSession as pathSession on vf.sessionId=pathSession.sessionId" +
			"  join CoresStudy.dbo.tblAnatomyTask as task on task.studyNo=vf.studyNo " +
			" and task.taskId like pathSession.taskId" +
			"  where vf.visceraName is not null and vf.studyNo=:studyNo and task.anatomyReqNo=:reqNo " ;
		sql=sql+"  and pathSession.sessionType in (4,5,6,7) ";//是脏器固定的数据
		
		//String hql=" from TblVisceraFixed where studyNo=:studyNo";
		List<Map<String,Object>>  list=getSession().createSQLQuery(sql)
		                                            .setParameter("studyNo", studyNo)
		                                            .setParameter("reqNo", reqNo)
		                                            .setResultTransformer(new MapResultTransformer())
		                                            .list();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("visceraName", "全部");
		list.add(0,map);
		
		return list;
	}

	public boolean isAllMissing(String studyNo, String animalCode,
			String visceraCode, String subVisceraCode) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.dictViscera as dv left join CoresSystemSet.dbo.dictViscera as dv2"+
					" 	on dv.visceraCode = dv2.pVisceraCode"+

					" 	left join CoresStudy.dbo.TblAnatomyCheck as anatomycheck  "+
					" 					 		on dv.visceraCode = anatomycheck.visceraCode and anatomycheck.autolyzaFlag > 0 and "+
					" 					 		 anatomycheck.studyNo = :studyNo and anatomycheck.animalCode = :animalCode and"+
					" 					 		( isnull(dv2.visceraCode,'') = '' or  isnull(anatomycheck.subVisceraCode,'') = '' or"+
					" 					 		 isnull(dv2.visceraCode,'') = isnull(anatomycheck.subVisceraCode,'')) "+

					" 	left join CoresStudy.dbo.tblVisceraMissing as missing on dv.visceraCode = missing.visceraCode "+
					" 					 		and missing.animalCode = :animalCode and missing.studyNo = :studyNo and "+
					" 							( isnull(dv2.visceraCode,'') = '' or  isnull(missing.subVisceraCode,'') = '' or"+
					" 					 		 isnull(dv2.visceraCode,'') = isnull(missing.subVisceraCode,'')) "+

					" where dv.visceraCode = :visceraCode and dv2.pVisceraCode  is not null and dv2.visceraCode != :subVisceraCode and "+
					"  anatomycheck.id is null and missing.id is null ";
		
		Integer count = (Integer) getSession().createSQLQuery(sql)
						.setParameter("studyNo", studyNo)
						.setParameter("animalCode", animalCode)
						.setParameter("visceraCode", visceraCode)
						.setParameter("subVisceraCode", subVisceraCode)
						.uniqueResult();
		
		return count < 1;
	}

	public boolean isFixed(String studyNo, String animalCode, String visceraCode) {
		//子脏器
		String sql = "select count(id) "+
					" from CoresStudy.dbo.tblVisceraFixed as fixed  "+
					" where fixed.studyNo = ? and fixed.animalCode = ? and fixed.visceraCode = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
							.setParameter(0, studyNo)
							.setParameter(1, animalCode)
							.setParameter(2, visceraCode)
							.uniqueResult();
		
		return count > 0;
	}

	

	
	
}
