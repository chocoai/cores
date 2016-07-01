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
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.clinicaltest.TblTrace;
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblAnatomyReqAnimalList;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblPathSession;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.clinicaltest.TblTraceService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
/**
 * 解剖动物信息
 * @author Administrator
 *
 */
@Service
public class TblAnatomyAnimalServiceImpl extends BaseDaoImpl<TblAnatomyAnimal>
implements TblAnatomyAnimalService{
	
	/**
	 * 解剖任务service
	 */
	@Resource
	private TblAnatomyTaskService tblAnatomyTaskService;
	/**
	 * 解剖申请动物service
	 */
	@Resource
	private TblAnatomyReqService tblAnatomyReqService;
	/**
	 * 解剖申请动物service
	 */
	@Resource
	private TblAnatomyReqAnimalListService tblAnatomyReqAnimalListService;
	/**
	 * 病理会话service
	 */
	@Resource
	private TblPathSessionService tblPathSessionService;
	/**
	 * 剖检信息service
	 */
	@Resource
	private TblAnatomyCheckService tblAnatomyCheckService;
	/**
	 * UserService
	 */
	@Resource
	private UserService userService;
	/**
	 * UserService
	 */
	@Resource
	private TblLogService tblLogService;
	@Resource
	private TblTraceService tblTraceService;

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTaskIdListSessionType(
			List<String> taskIdList, Integer sessionType, boolean showAnatomyNoFinishAnimal) {
		List<Map<String,Object>> mapList = null;
		if(null != taskIdList && taskIdList.size()>0 && null != sessionType){
			
			String sql = "";
			if(sessionType == 1 || sessionType == 3|| sessionType == 5 ||  sessionType == 7){
				//1,sessionType等于1,3,5,7   动物解剖（解剖和称重或解剖、称重和固定），展示的是未开始解剖的动物(且剔除 解剖前已死亡的)
				sql = "select req.studyNo,reqAnimal.animalCode,reqAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
						" ,'' as anatomyOperator ,req.anatomyRsn,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
					" from CoresStudy.dbo.tblAnatomyReq as req left join  CoresStudy.dbo.tblAnatomyReqAnimalList as reqAnimal"+
					" 		on req.reqNo = reqAnimal.anatomyReqNo and req.studyNo = reqAnimal.studyNo"+
					" 	left join CoresStudy.dbo.tblAnatomyTask as task "+
					" 		on req.studyNo = task.studyNo and req.reqNo = task.anatomyReqNo"+
					" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal "+
					" 		on task.taskId = anatomyAnimal.taskId and reqAnimal.animalCode = anatomyAnimal.animalCode"+
					" 	left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on reqAnimal.studyNo = animal.studyNo and reqAnimal.animalCode = animal.animalCode"+
					" where req.submitFlag = 1 and task.taskId in (:taskIdList) and anatomyAnimal.animalCode is null " +
					" and reqAnimal.cancelFlag = 0 "+
					" order by req.studyNo,reqAnimal.animalCode";
				
			}else if(sessionType == 2){
				if(!showAnatomyNoFinishAnimal){
					//2,sessionType等于2  脏器称重，展示的是已完成解剖的动物（ 未称重且已解剖完成,注意自溶标识）
					sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
					" ,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
					" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode " +
					"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
					" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
					" 		and anatomyAnimal.animalCode is not null " +
					"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
					"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
					" order by task.studyNo,anatomyAnimal.animalCode";
				}else{
					//2,sessionType等于2  脏器称重，展示的是已完成解剖的动物（ 未称重且已解剖完成(已解剖，可以未完成),注意自溶标识）
					sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
					" ,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
					" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode " +
					"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
					" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
					" 		and anatomyAnimal.animalCode is not null " +
					"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
					"		and (anatomyAnimal.anatomySessionId is not null and anatomyAnimal.anatomySessionId != '' ) "+
					" order by task.studyNo,anatomyAnimal.animalCode";
				}
				
			}else if(sessionType == 4){
				//3,sessionType等于4  脏器固定），展示的是已完成解剖的动物（且未开始固定的动物（称重已完成或不需要称重）,注意自溶标识）
//				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
//					  " 	,tbluser.realName as anatomyOperator,reqweigh.weighNum" +
//					  " from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal" +
//					  " 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal" +
//					  " 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode" +
//					  " 		left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName" +
//					  " 		left join (select weigh.studyNo,weigh.reqNo,COUNT(*) as weighNum" +
//					  " 					from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh" +
//					  " 					where weigh.fixedWeighFlag = 0" +
//					  " 					group by weigh.studyNo,weigh.reqNo) as reqweigh" +
//					  " 		on task.studyNo = reqweigh.studyNo and task.anatomyReqNo = reqweigh.reqNo" +
//					  " where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )" +
//					  " 	and anatomyAnimal.animalCode is not null" +
//					  " 	and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
//					  " 	and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) " +
//					  " 	and (anatomyAnimal.visceraWeighFinishFlag =1 or (reqweigh.weighNum < 1  or reqweigh.weighNum is null ) )" +
//
//					  " order by task.studyNo,anatomyAnimal.animalCode";
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
				" 	,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime " +
				" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal" +
				" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal" +
				" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode" +
				" 		left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName" +
				" 		" +
				" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )" +
				" 	and anatomyAnimal.animalCode is not null" +
				" 	and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
				" 	and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) " +
				
				" order by task.studyNo,anatomyAnimal.animalCode";
				
			}else if(sessionType == 6){
				if(!showAnatomyNoFinishAnimal){
					//4,sessionType等于6  脏器脏器固定和脏器称重，展示的是已完成解剖的动物（且未开始称重和固定的动物,注意自溶标识）
					sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
					"    ,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
					" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode"+
					"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
					" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
					" 		and anatomyAnimal.animalCode is not null " +
					"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
					"       and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
					"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
					" order by task.studyNo,anatomyAnimal.animalCode";
				}else{
					//4,sessionType等于6  脏器脏器固定和脏器称重，展示的是已完成解剖的动物（已解剖的动物，可以未完成,且未开始称重和固定的动物,注意自溶标识）
					sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
					"    ,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
					" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode"+
					"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
					" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
					" 		and anatomyAnimal.animalCode is not null " +
					"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
					"       and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
					"		and (anatomyAnimal.anatomySessionId is not null and anatomyAnimal.anatomySessionId != '') "+
					" order by task.studyNo,anatomyAnimal.animalCode";
				}
				
			}else if(sessionType == 8){
				//5,sessionType等于8   固定后称重，展示的是已固定的动物且未固定后称重（解剖已完成标识，固定已完成标识）
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId"+
				"    ,tbluser.realName as anatomyOperator ,convert(varchar(19),anatomyAnimal.anatomyBeginTime,120) as anatomyBeginTime "+
				" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
				" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
				" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode"+
				"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
				" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
				" 		and anatomyAnimal.animalCode is not null " +
				"       and anatomyAnimal.visceraFixedSessionId is not null and anatomyAnimal.visceraFixedSessionId != ''" +
				"		and (anatomyAnimal.visceraFixedWeightSessionId is null or anatomyAnimal.visceraFixedWeightSessionId = '') " +
				"		and anatomyAnimal.visceraFixedFinishFlag = 1" +
				"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
				" order by task.studyNo,anatomyAnimal.animalCode";
				
			}
			
			mapList = getSession().createSQLQuery(sql)
									.setParameterList("taskIdList", taskIdList)
									.setResultTransformer(new MapResultTransformer())
									.list();
			
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListBySessionIdList(
			List<String> sessionIdList) {
		List<Map<String,Object>> mapList = null;
		if(null != sessionIdList && sessionIdList.size() > 0){
			
			int sessionType = 0;
			TblPathSession tblPathSession = tblPathSessionService.getById(sessionIdList.get(0));
			if(null != tblPathSession){
				sessionType = tblPathSession.getSessionType();
			}
			
			String sql = "select distinct tblap.taskId,tblap.animalCode,tblap.studyNo,tblap.visceraWeighFinishFlag,tblap.visceraFixedFinishFlag," +
			"		tblap.visceraFixedWeighFinishFlag,tblu2.realName as anatomyOperator,tblap.anatomyBeginTime,"+
			"				     tblap.anatomyCheckFinishFlag,tblap.autolyzeFlag,tblu.realName as sessionCreator"+
			"	 from ( select tbla.taskId,tbla.studyNo,tbla.animalCode,tbla.visceraWeighFinishFlag,tbla.visceraFixedFinishFlag," +
			"		tbla.visceraFixedWeighFinishFlag,tbla.anatomyOperator,tbla.anatomyBeginTime,"+
			"					              tbla.AnatomyCheckFinishFlag,tbla.autolyzeFlag,tblp.sessionCreator   "+
			"				from CoresStudy.dbo.tblAnatomyAnimal as tbla left join CoresStudy.dbo.TblPathSession as tblp "+
			"					     on " ;
//			"						tbla.anatomySessionId = tblp.sessionId " +
//			"						or tbla.visceraFixedWeightSessionId = tblp.sessionId " +
//			"						or tbla.visceraWeightSessionId = tblp.sessionId  " +
//			"						or tbla.visceraFixedSessionId = tblp.sessionId  ";
			
			switch (sessionType) {
				case 1:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 2:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 3:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 4:
					sql = sql + " tbla.visceraFixedSessionId = tblp.sessionId ";
					break;
				case 5:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 6:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 7:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 8:
					sql = sql + " tbla.visceraFixedWeightSessionId = tblp.sessionId ";
					break;
	
				default:
					break;
			}
			
			
			sql = sql +	"				where tbla.anatomySessionId in (:sessionIdList)  "+
			"					       or tbla.visceraFixedWeightSessionId in (:sessionIdList)"+
			"					         or tbla.visceraFixedSessionId in (:sessionIdList)"+
			"					         or tbla.visceraWeightSessionId in (:sessionIdList)"+
			"				) as tblap left join CoresUserPrivilege.dbo.tbluser as tblu "+
			"				 on tblap.sessionCreator = tblu.userName left join CoresUserPrivilege.dbo.tbluser as tblu2"+
			"				 on tblap.anatomyOperator = tblu2.userName  ";
			mapList = getSession().createSQLQuery(sql)
			.setParameterList("sessionIdList", sessionIdList)
			.setResultTransformer(new MapResultTransformer())
			.list();
		}
		System.out.println(mapList);
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnimalCodeBySessionIdList(List<String> sessionIdList,String studyNo){
		List<Map<String,Object>> mapList = null;
		if(null!=studyNo){
			int sessionType = 0;
			TblPathSession tblPathSession = tblPathSessionService.getById(sessionIdList.get(0));
			if(null != tblPathSession){
				sessionType = tblPathSession.getSessionType();
			}
			
			String sql = "select distinct tblap.animalCode" +
			"		"+
			"	 from ( select tbla.taskId,tbla.studyNo,tbla.animalCode,tbla.visceraWeighFinishFlag,tbla.visceraFixedFinishFlag," +
			"		tbla.visceraFixedWeighFinishFlag,tbla.anatomyOperator,tbla.anatomyBeginTime,"+
			"					              tbla.AnatomyCheckFinishFlag,tbla.autolyzeFlag,tblp.sessionCreator   "+
			"				from CoresStudy.dbo.tblAnatomyAnimal as tbla left join CoresStudy.dbo.TblPathSession as tblp "+
			"					     on " ;
//			"						tbla.anatomySessionId = tblp.sessionId " +
//			"						or tbla.visceraFixedWeightSessionId = tblp.sessionId " +
//			"						or tbla.visceraWeightSessionId = tblp.sessionId  " +
//			"						or tbla.visceraFixedSessionId = tblp.sessionId  ";
			
			switch (sessionType) {
				case 1:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 2:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 3:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 4:
					sql = sql + " tbla.visceraFixedSessionId = tblp.sessionId ";
					break;
				case 5:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 6:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 7:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 8:
					sql = sql + " tbla.visceraFixedWeightSessionId = tblp.sessionId ";
					break;
	
				default:
					break;
			}
			
			
			sql = sql +	"				where tbla.studyNo=:studyNo and (tbla.anatomySessionId in (:sessionIdList)  "+
			"					       or tbla.visceraFixedWeightSessionId in (:sessionIdList)"+
			"					         or tbla.visceraFixedSessionId in (:sessionIdList)"+
			"					         or tbla.visceraWeightSessionId in (:sessionIdList))"+
			"				) as tblap left join CoresUserPrivilege.dbo.tbluser as tblu "+
			"				 on tblap.sessionCreator = tblu.userName left join CoresUserPrivilege.dbo.tbluser as tblu2"+
			"				 on tblap.anatomyOperator = tblu2.userName  ";
			mapList = getSession().createSQLQuery(sql)
			.setParameterList("sessionIdList", sessionIdList)
			.setParameter("studyNo", studyNo)
			.setResultTransformer(new MapResultTransformer())
			.list();
		}else{
			if(null != sessionIdList && sessionIdList.size() > 0){
				
				int sessionType = 0;
				TblPathSession tblPathSession = tblPathSessionService.getById(sessionIdList.get(0));
				if(null != tblPathSession){
					sessionType = tblPathSession.getSessionType();
				}
				
				String sql = "select distinct tblap.animalCode "+
				"	 from ( select tbla.taskId,tbla.studyNo,tbla.animalCode,tbla.visceraWeighFinishFlag,tbla.visceraFixedFinishFlag," +
				"		tbla.visceraFixedWeighFinishFlag,tbla.anatomyOperator,tbla.anatomyBeginTime,"+
				"					              tbla.AnatomyCheckFinishFlag,tbla.autolyzeFlag,tblp.sessionCreator   "+
				"				from CoresStudy.dbo.tblAnatomyAnimal as tbla left join CoresStudy.dbo.TblPathSession as tblp "+
				"					     on " ;
//				"						tbla.anatomySessionId = tblp.sessionId " +
//				"						or tbla.visceraFixedWeightSessionId = tblp.sessionId " +
//				"						or tbla.visceraWeightSessionId = tblp.sessionId  " +
//				"						or tbla.visceraFixedSessionId = tblp.sessionId  ";
				
				switch (sessionType) {
					case 1:
						sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
						break;
					case 2:
						sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
						break;
					case 3:
						sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
						break;
					case 4:
						sql = sql + " tbla.visceraFixedSessionId = tblp.sessionId ";
						break;
					case 5:
						sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
						break;
					case 6:
						sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
						break;
					case 7:
						sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
						break;
					case 8:
						sql = sql + " tbla.visceraFixedWeightSessionId = tblp.sessionId ";
						break;
		
					default:
						break;
				}
				
				
				sql = sql +	"				where tbla.anatomySessionId in (:sessionIdList)  "+
				"					       or tbla.visceraFixedWeightSessionId in (:sessionIdList)"+
				"					         or tbla.visceraFixedSessionId in (:sessionIdList)"+
				"					         or tbla.visceraWeightSessionId in (:sessionIdList)"+
				"				) as tblap left join CoresUserPrivilege.dbo.tbluser as tblu "+
				"				 on tblap.sessionCreator = tblu.userName left join CoresUserPrivilege.dbo.tbluser as tblu2"+
				"				 on tblap.anatomyOperator = tblu2.userName  ";
				mapList = getSession().createSQLQuery(sql)
				.setParameterList("sessionIdList", sessionIdList)
				.setResultTransformer(new MapResultTransformer())
				.list();
			}
		}
		return mapList;
		
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getListBySessionIdList2(
//			List<String> sessionIdList) {
//		String sql = "  select tblap.taskId,tblap.animalCode,tblap.studyNo,tblap.visceraWeightFinishSign,tblap.visceraFixedFinishSign, " +
//				     "  tblap.anatomyCheckFinishFlag,tblap.autolyzeFlag,tblu.realName, tblap.sessionId" +
//				     "         from ( " +
//				     "           select tbla.taskId,tbla.studyNo,tbla.animalCode,tbla.visceraWeightFinishSign,tbla.visceraFixedFinishSign, " +
//				     "           tbla.AnatomyCheckFinishFlag,tbla.autolyzeFlag,tblp.sessionCreator,tblp.sessionId as  sessionId" +
//				     "  from CoresStudy.dbo.tblAnatomyAnimal as tbla left join CoresStudy.dbo.TblPathSession as tblp " +
//				     "  on tbla.anatomySessionId = tblp.sessionId or tbla.visceraFixedWeightSessionId = tblp.sessionId or " +
//				     "   tbla.visceraFixedWeightSessionId = tblp.sessionId  or tbla.visceraFixedSessionId = tblp.sessionId  " +
//				     "  where tbla.anatomySessionId in (:sessionIdList )  " +
//				     "     or tbla.visceraFixedWeightSessionId in (:sessionIdList ) " +
//				     "      or tbla.visceraFixedSessionId in (:sessionIdList ) " +
//				     "   ) as tblap left join CoresUserPrivilege.dbo.tbluser as tblu " +
//				     "   on tblap.sessionCreator = tblu.userName ";
//		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
//												.setParameterList("sessionIdList", sessionIdList)
//		.setResultTransformer(new MapResultTransformer())
//		.list();
//		System.out.println(mapList);
//		return mapList;
//	}
	/* (non-Javadoc)
	 * @see com.lanen.service.path.TblAnatomyAnimalService#saveOrUpdate(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.lanen.service.path.TblAnatomyAnimalService#saveOrUpdate(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public Map<String, Object> saveOrUpdate(String taskId, String sessionId,
			Integer sessionType, String animalCode, String userName, Date anatomyBeginTime, Date deadDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", "参数错误");
		if(null != taskId && null != sessionId && null != sessionType && null != animalCode){
			if(sessionType == 1 || sessionType == 3 || sessionType == 5 || sessionType == 7){
				//1, sessionType 等于 1 ,3,5,7 动物解剖...，保存解剖动物信息
				TblAnatomyTask tblAnatomyTask = tblAnatomyTaskService.getById(taskId);
				if(null != tblAnatomyTask){
					String studyNo = tblAnatomyTask.getStudyNo();
					Integer reqNo = tblAnatomyTask.getAnatomyReqNo();
					TblAnatomyReqAnimalList tblAnatomyReqAnimal = tblAnatomyReqAnimalListService
									.getByStudyNoReqNoAnimalCode(studyNo,reqNo,animalCode);
					boolean exist = isExistByTaskIdAnimalCode(taskId,animalCode);
					if(exist){
						map.put("msg", "动物已解剖！");
						return map;
					}
					if(null != tblAnatomyReqAnimal){
						TblAnatomyAnimal tblAnatomyAnimal = new TblAnatomyAnimal();
						String id = getKey();
						tblAnatomyAnimal.setId(id);
						tblAnatomyAnimal.setStudyNo(studyNo);
						tblAnatomyAnimal.setGroupId(tblAnatomyReqAnimal.getGroupID());
						tblAnatomyAnimal.setGender(tblAnatomyReqAnimal.getGender());
						tblAnatomyAnimal.setAnimalCode(animalCode);
						tblAnatomyAnimal.setAnatomyPlanNum(tblAnatomyTask.getAnatomyPlanNum());
						tblAnatomyAnimal.setTaskId(taskId);
						
//						tblAnatomyAnimal.setDeadFlag(1);
						int anatomyRsn = tblAnatomyReqService.getAnatomyRsn(studyNo,reqNo);
						tblAnatomyAnimal.setDeadFlag(anatomyRsn);
						tblAnatomyAnimal.setDeadDate(deadDate);
						
						tblAnatomyAnimal.setDeadRsn(tblAnatomyTask.getAnatomyRsn());
						tblAnatomyAnimal.setAnatomySessionId(sessionId);//剖检会话id
						if(sessionType == 3){
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
						}else if(sessionType == 7){
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else if(sessionType == 5){
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}
						
						tblAnatomyAnimal.setAnatomyBeginTime(anatomyBeginTime);
						tblAnatomyAnimal.setAnatomyOperator(userName);
						//保存  成功
						getSession().save(tblAnatomyAnimal);
						
						map.put("msg", "");
						//TODO  map的其他信息
						map.put("taskId",taskId);
						map.put("studyNo",studyNo);
						map.put("animalCode", animalCode);
						String realName = "";
						TblPathSession tblPathSession = tblPathSessionService.getById(sessionId);
						if(null != tblPathSession){
							String sessionCreator = tblPathSession.getSessionCreator();
							User user = userService.getByUserName(sessionCreator);
							if(null != user){
								realName = user.getRealName();
							}
						}
						map.put("realName", realName);
						map.put("visceraWeightFinishSign", "");
						map.put("visceraFixedFinishSign", "");
						map.put("anatomyCheckFinishFlag", 0);
						map.put("autolyzeFlag", 0);
					}else{
						map.put("msg", "解剖申请动物不存在!");
						return map;
					}
				}else{
					map.put("msg", "解剖任务不存在!");
					return map;
				}
			}else if(sessionType == 2 || sessionType == 4 || sessionType == 6|| sessionType == 8){
				//2, sessionType 等于 2 ,4,6,8 脏器，解剖已完成，其他对应操作没做
				TblAnatomyAnimal tblAnatomyAnimal = getByTaskIdAnimalCode(taskId,animalCode);
				if(null != tblAnatomyAnimal){
					String visceraWeightSessionId = tblAnatomyAnimal.getVisceraWeightSessionId();
					String visceraFixedSessionId = tblAnatomyAnimal.getVisceraFixedSessionId();
					String visceraFixedWeightSessionId = tblAnatomyAnimal.getVisceraFixedWeightSessionId();
					if(sessionType == 2){
						if((null == visceraWeightSessionId || "".equals(visceraWeightSessionId)) ){
//							&&
//							(null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
//							(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))
							//称重会话
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
						}else{
							map.put("msg", "脏器已称重或已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 4){
						if((null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//固定会话
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else{
							map.put("msg", "脏器已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 6){
						if((null == visceraWeightSessionId || "".equals(visceraWeightSessionId)) &&
								(null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//称重会话
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
							//固定会话
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else{
							map.put("msg", "脏器已称重或已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 8){
						if(	(null != visceraFixedSessionId || !"".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//固定后称重会话
							tblAnatomyAnimal.setVisceraFixedWeightSessionId(sessionId);
						}else{
							map.put("msg", "脏器未固定或已固定后称重！");
							return map;
						}
					}
					//更新 
					getSession().update(tblAnatomyAnimal);
					
					map.put("msg", "");
					//TODO  map的其他信息
					map.put("taskId", taskId);
					map.put("studyNo", tblAnatomyAnimal.getStudyNo());
					map.put("animalCode", animalCode);
					String realName = "";
					TblPathSession tblPathSession = tblPathSessionService.getById(sessionId);
					if(null != tblPathSession){
						String sessionCreator = tblPathSession.getSessionCreator();
						User user = userService.getByUserName(sessionCreator);
						if(null != user){
							realName = user.getRealName();
						}
					}
					map.put("realName", realName);
					map.put("visceraWeightFinishSign", tblAnatomyAnimal.getVisceraWeightFinishSign());
					map.put("visceraFixedFinishSign", tblAnatomyAnimal.getVisceraFixedFinishSign());
					map.put("anatomyCheckFinishFlag", tblAnatomyAnimal.getAnatomyCheckFinishFlag());
					map.put("autolyzeFlag", tblAnatomyAnimal.getAutolyzeFlag());
					
				}else{
					map.put("msg", "解剖动物不存在!");
					return map;
				}
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> updateAnatomyAnimalDeadDate(String studyNo,String animalCode,String hostName,String realName,String reason, Date deadDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		TblAnatomyAnimal tblAnatomyAnimal = getByStudyNoAnimalCode(studyNo, animalCode);
		if(null != tblAnatomyAnimal){
			if( tblAnatomyAnimal.getAnatomyBeginTime()!=null &&
					tblAnatomyAnimal.getAnatomyBeginTime().after(deadDate)){
				
				String oldValue = DateUtil.dateToString(tblAnatomyAnimal.getDeadDate(),"yyyy-MM-dd HH:mm:ss");
				tblAnatomyAnimal.setDeadDate(deadDate);
				update(tblAnatomyAnimal);
				
				String newValue = 	DateUtil.dateToString(tblAnatomyAnimal.getDeadDate(),"yyyy-MM-dd HH:mm:ss");
				TblTrace trace = new TblTrace();
					
				trace.setId(getKey("TblTrace"));
				trace.setTableName("TblAnatomyAnimal");
				trace.setDataId(tblAnatomyAnimal.getId());
				trace.setHost(hostName);
				trace.setModifyReason(reason);
				trace.setModifyTime(new Date());
				trace.setOperateMode(1);//修改
				trace.setOperator(realName);
				trace.setNewValue(newValue);//新数据里存放的是：课题编号，申请编号，检测项目，检验编号，检验指标缩写，检测完成时间
				trace.setOldValue(oldValue);//原数据
				//保存修改痕迹
				tblTraceService.save(trace);
				
				map.put("success", true);
			}else{
				map.put("success", false);
				map.put("msg", "动物死亡日期应在动物解剖开始之前！");
				
			}
		}else{
			map.put("success", false);
			map.put("msg", "该动物不是解剖动物！");
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByTaskIdAnimalCode(String taskId, String animalCode) {
		String hql = "from TblAnatomyAnimal where taskId = :taskId and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("taskId", taskId)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public TblAnatomyAnimal getByTaskIdAnimalCode(String taskId,
			String animalCode) {
		String hql = "from TblAnatomyAnimal where taskId = :taskId and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("taskId", taskId)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean isAnatomyFinish(String studyNo, String animalCode) {
		String hql = "from TblAnatomyAnimal where studyNo = :studyNo and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			Integer anatomyCheckFinishFlag = list.get(0).getAnatomyCheckFinishFlag();
			if(null != anatomyCheckFinishFlag && anatomyCheckFinishFlag == 1){
				return true;
			}
		}
		return false;
	}

	public Json setAnatomyFinish(String studyNo, String animalCode) {
		Json json = new Json();
		TblAnatomyAnimal tblAnatomyAnimal = getByStudyNoAnimalCode(studyNo, animalCode);
		if(null != tblAnatomyAnimal){
			if( tblAnatomyAnimal.getAnatomyCheckFinishFlag() == 0){
				tblAnatomyAnimal.setAnatomyCheckFinishFlag(1);
				tblAnatomyAnimal.setAnatomyEndTime(new Date());
				getSession().update(tblAnatomyAnimal);
				json.setSuccess(true);
			}else{
				json.setMsg("解剖已完成，不可重复操作！");
			}
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public TblAnatomyAnimal getByStudyNoAnimalCode(String studyNo,
			String animalCode) {
		String hql = "from TblAnatomyAnimal where studyNo = :studyNo and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public Json setAutolyzeFlag(String taskId, String animalCode) {
		Json json = new Json();
		TblAnatomyAnimal tblAnatomyAnimal = getByTaskIdAnimalCode(taskId, animalCode);
		if(null != tblAnatomyAnimal){
			if( tblAnatomyAnimal.getAnatomyCheckFinishFlag() == 0 
					&& tblAnatomyAnimal.getAutolyzeFlag() == 0){
				
				List<?> list = tblAnatomyCheckService.getListBySessionId(tblAnatomyAnimal.getAnatomySessionId(), animalCode);
				if(null != list && list.size()>0){
					json.setMsg("已有剖检信息，不可设为自溶！");
				}else{
					tblAnatomyAnimal.setAutolyzeFlag(1);
					getSession().update(tblAnatomyAnimal);
					json.setSuccess(true);
				}
			}else{
				json.setMsg("解剖已完成或动物已设为自溶！");
			}
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public boolean isVisceraFixedFinish(String studyNo, String animalCode) {
		String hql = "from TblAnatomyAnimal where studyNo = :studyNo and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			Integer visceraFixedFinishFlag = list.get(0).getVisceraFixedFinishFlag();
			if(null != visceraFixedFinishFlag && 1 == visceraFixedFinishFlag){
				return true;
			}
		}
		return false;
	}

	public Json setVisceraFixedFinishSign(String studyNo, String animalCode) {
		Json json = new Json();
		TblAnatomyAnimal tblAnatomyAnimal = getByStudyNoAnimalCode(studyNo, animalCode);
		if(null != tblAnatomyAnimal){
			if(null == tblAnatomyAnimal.getVisceraFixedFinishSign() || 
					"".equals(tblAnatomyAnimal.getVisceraFixedFinishSign())){
				tblAnatomyAnimal.setVisceraFixedFinishSign("1");
				tblAnatomyAnimal.setVisceraFixedFinishTime(new Date());
				tblAnatomyAnimal.setVisceraFixedFinishFlag(1);
				getSession().update(tblAnatomyAnimal);
				json.setSuccess(true);
			}else{
				json.setMsg("该动物脏器固定已完成，不可重复操作！");
			}
		}
		return json;
	}

	public Json setVisceraWeightFinishSign(String studyNo, String animalCode,Boolean isFixed) {
		Json json = new Json();
		TblAnatomyAnimal tblAnatomyAnimal = getByStudyNoAnimalCode(studyNo, animalCode);
		
		
		
		if(null != tblAnatomyAnimal){
			if( (0 == tblAnatomyAnimal.getVisceraFixedWeighFinishFlag() && !isFixed)|| 
					(isFixed && 0 == tblAnatomyAnimal.getVisceraFixedWeighFinishFlag() )){
				if(isFixed){
					tblAnatomyAnimal.setVisceraFixedWeighFinishFlag(1);
				}else{
					tblAnatomyAnimal.setVisceraWeighFinishFlag(1);
					tblAnatomyAnimal.setVisceraWeighFinishTime(new Date());
				}
				
				getSession().update(tblAnatomyAnimal);
				json.setSuccess(true);
			}else{
				json.setMsg("该动物脏器称重已完成，不可重复操作！");
			}
		}
		return json;
	}
	@SuppressWarnings("unchecked")
	public List<TblAnatomyAnimal> getListBySessionTypeAndSessionId(
			int sessionType, String sessionId) {
		List<TblAnatomyAnimal> list=null;
		String hql="";
		if(sessionType==1){
			hql=hql+"from TblAnatomyAnimal  where anatomySessionID=:sessionId and autolyzeFlag!=1 and "+
                    " anatomyCheckFinishFlag!=1";	 				
		}else if(sessionType==2){
			hql=hql+"from TblAnatomyAnimal  where visceraWeightSessionId=:sessionId and autolyzeFlag!=1 and "+
                    " visceraWeighFinishFlag!=1 ";
		}else if(sessionType==3){
			hql=hql+"from TblAnatomyAnimal  where anatomySessionID=:sessionId and  visceraWeightSessionId=:sessionId and autolyzeFlag!=1 and "+
                    "(anatomyCheckFinishFlag!=1 or visceraWeighFinishFlag!=1 ) ";
		}else if(sessionType==4){
			hql=hql+"from TblAnatomyAnimal  where visceraFixedSessionId=:sessionId and autolyzeFlag!=1 and "+
                    "visceraFixedFinishFlag!=1";
		}else if(sessionType==5){
			hql=hql+"from TblAnatomyAnimal  where anatomySessionID=:sessionId and visceraFixedSessionId=:sessionId and autolyzeFlag!=1 and "+
                    "(anatomyCheckFinishFlag!=1 or  visceraFixedFinishFlag!=1) ";
		}else if(sessionType==6){
			hql=hql+"from TblAnatomyAnimal  where visceraWeightSessionId=:sessionId and visceraFixedSessionId=:sessionId  and autolyzeFlag!=1 and "+
                    "(visceraWeighFinishFlag!=1 or visceraFixedFinishFlag!=1) ";
		}else if(sessionType==7){
			hql=hql+"from TblAnatomyAnimal  where anatomySessionID=:sessionId and visceraWeightSessionId=:sessionId and visceraFixedSessionId=:sessionId and autolyzeFlag!=1 and "+
                    "(anatomyCheckFinishFlag!=1 or visceraWeighFinishFlag!=1 or visceraFixedFinishFlag!=1) ";
		}else if(sessionType==8){
			hql=hql+"from TblAnatomyAnimal  where visceraFixedWeightSessionId=:sessionId and autolyzeFlag!=1 and "+
                    " visceraFixedWeighFinishFlag!=1";
		}
		list=getSession().createQuery(hql).setParameter("sessionId", sessionId).list();
		return list;
	}

	public int getSumAutolyzeFlagAnimalCount(List<String> sessionIdList) {
		String sql ="select tbla.animalCode,tbla.anatomySessionId  " +
				" from CoresStudy.dbo.tblAnatomyAnimal as tbla " +
				" where tbla.anatomySessionId in (:sessionIdList) and tbla.autolyzeFlag = 1 " +
				" union " +
				" select distinct tblc.animalCode,tblc.sessionId " +
				" from CoresStudy.dbo.TblAnatomyCheck as tblc " +
				" where tblc.sessionId in (:sessionIdList) and tblc.autolyzaFlag = 1";
		List<?> list = getSession().createSQLQuery(sql).setParameterList("sessionIdList", sessionIdList).list();
		if(null != list && list.size() > 0){
			return list.size();
		}else{
			return 0;
		}
	
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListBySessionId(String sessionId) {
		String sql = "select animalCode,visceraFixedFinishTime from  CoresStudy.dbo.tblAnatomyAnimal where visceraFixedSessionId = :sessionId";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("sessionId", sessionId)
		.setResultTransformer(new MapResultTransformer())
		.list();
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public boolean isWeightFinish(String studyNo, String animalCode,
			int fixedWeightFlag) {
		String hql = "from TblAnatomyAnimal where studyNo = :studyNo and animalCode = :animalCode";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql)
													.setParameter("studyNo", studyNo)
													.setParameter("animalCode", animalCode)
													.list();
		if(null != list && list.size() > 0){
			Integer viseraWeightFinishFlag = list.get(0).getVisceraWeighFinishFlag();
			if(null != viseraWeightFinishFlag && viseraWeightFinishFlag == 1 && fixedWeightFlag == 0){
				return true;
			}
			Integer viseraFixedWeightFinishFlag = list.get(0).getVisceraFixedWeighFinishFlag();
			if(null != viseraFixedWeightFinishFlag && viseraFixedWeightFinishFlag == 1 && fixedWeightFlag == 1){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByStateAndSessionId(String taskId,
			String animalState, String sessionId) {
		String sql="SELECT "+
				   "    id,studyNo,groupId,gender,animalCode,anatomyPlanNum,taskId "+
				   "   ,autolyzeFlag,anatomySessionId,visceraWeightSessionId,visceraWeightFinishSign "+
				   "   ,visceraFixedWeightSessionId,visceraFixedSessionId,visceraFixedFinishSign "+
				   "   ,anatomyBeginTime,anatomyEndTime,anatomyCheckFinishFlag "+
				   "   ,histopathCheckFinishFlag,visceraWeighFinishTime,visceraWeighFinishFlag "+
				   "   ,visceraFixedFinishTime,visceraFixedFinishFlag" +
				   "   ,(select realName from CoresUserPrivilege.dbo.tbluser where userName=anatomyOperator) as anatomyOperator "+
				   "   ,visceraFixedWeighFinishFlag " +
				   "   ,case when  " +
	               "    (select count(id) from CoresStudy.dbo.TblAnatomyCheck where studyNo=taa.studyNo and animalCode=taa.animalCode)=0 then '无异常' " +
	               "     else '有异常' end as anatomyResult "+
				   "  FROM CoresStudy.dbo.tblAnatomyAnimal as taa where taskId=:taskId";
		if(null!=sessionId && !sessionId.equals("全部") && !"".equals(sessionId)){
			sql=sql+" and (anatomySessionId=:sessionId or visceraWeightSessionId=:sessionId " +
					"     or  visceraFixedWeightSessionId=:sessionId or visceraFixedSessionId=:sessionId)";
		}
		if(null!=animalState && animalState.equals("已剖")){
			sql =sql+" and  anatomySessionId is not null and autolyzeFlag!=1";
		}else if(null!=animalState && animalState.equals("自溶")){
			sql =sql+" and autolyzeFlag=1";
		}else if(null!=animalState && animalState.equals("未称重")){
			sql =sql+" and anatomySessionId is not null and autolyzeFlag!=1 and visceraWeightSessionId is null " +
					"  and visceraFixedWeightSessionId is null";
		}else if(null!=animalState && animalState.equals("未固定")){
			sql =sql+" and anatomySessionId is not null and autolyzeFlag!=1 and visceraFixedSessionId is null ";
        }
		sql =sql+" order by animalCode";
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !sessionId.equals("全部") && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}
		List<Map<String, Object>> list=query.setParameter("taskId", taskId)
		                                      .setResultTransformer(new MapResultTransformer())
		                                      .list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByStateAndSessionIds(String taskId,
			String animalState, String sessionId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		
		String sql="SELECT "+
				   "    id,studyNo,groupId,gender,animalCode,anatomyPlanNum,taskId,deadDate "+
				   "   ,autolyzeFlag,anatomySessionId,visceraWeightSessionId,deadDate,visceraWeightFinishSign "+
				   "   ,visceraFixedWeightSessionId,visceraFixedSessionId,visceraFixedFinishSign "+
				   "   ,anatomyBeginTime,anatomyEndTime,anatomyCheckFinishFlag "+
				   "   ,histopathCheckFinishFlag,visceraWeighFinishTime,visceraWeighFinishFlag "+
				   "   ,visceraFixedFinishTime,visceraFixedFinishFlag" +
				   "   ,(select realName from CoresUserPrivilege.dbo.tbluser where userName=anatomyOperator) as anatomyOperator "+
				   "   ,visceraFixedWeighFinishFlag " +
				   "   ,case when  " +
	               "    (select count(id) from CoresStudy.dbo.TblAnatomyCheck where studyNo=taa.studyNo and animalCode=taa.animalCode)=0 then '无异常' " +
	               "     else '有异常' end as anatomyResult "+
				   "  FROM CoresStudy.dbo.tblAnatomyAnimal as taa where taskId in (:taskId) ";
		if(null!=sessionId && !sessionId.equals("全部") && !"".equals(sessionId)){
			sql=sql+" and (anatomySessionId=:sessionId or visceraWeightSessionId=:sessionId " +
					"     or  visceraFixedWeightSessionId=:sessionId or visceraFixedSessionId=:sessionId)";
		}
		if(null!=animalState && animalState.equals("已剖")){
			sql =sql+" and  anatomySessionId is not null and autolyzeFlag!=1";
		}else if(null!=animalState && animalState.equals("自溶")){
			sql =sql+" and autolyzeFlag=1";
		}else if(null!=animalState && animalState.equals("未称重")){
			sql =sql+" and anatomySessionId is not null and autolyzeFlag!=1 and visceraWeightSessionId is null " +
					"  and visceraFixedWeightSessionId is null";
		}else if(null!=animalState && animalState.equals("未固定")){
			sql =sql+" and anatomySessionId is not null and autolyzeFlag!=1 and visceraFixedSessionId is null ";
        }
		sql =sql+" order by animalCode";
		Query query=getSession().createSQLQuery(sql);
		if(null!=sessionId && !sessionId.equals("全部") && !"".equals(sessionId)){
			query.setParameter("sessionId", sessionId);
		}
		List<Map<String, Object>> list=query.setParameterList("taskId", taskList)
		                                      .setResultTransformer(new MapResultTransformer())
		                                      .list();
		return list;
	}
	public Date getCurrentDate() {
		return new Date();
	}

	@SuppressWarnings("unchecked")
	public List<String> getNoFixedFinishAnimalList(List<String> sessionIdList) {
		String sql = "select t.animalCode"+
					" from CoresStudy.dbo.tblAnatomyAnimal as t"+
					" where t.visceraFixedSessionId in (:sessionIdList) and "+
					" 	t.visceraWeighFinishFlag = 1 and t.visceraFixedFinishFlag = 0";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameterList("sessionIdList", sessionIdList)
										.list();
		return list;
	}
	
	
	public Json deleteAimal(String studyNo, String animalCode,String operator) {
		// 1.无自溶标识
		//2.解剖完成标识为0
		//3.无解剖记录
		//4.无称重记录
		//5.无固定记录
		Json json = new Json();
		
		TblAnatomyAnimal animal = getByStudyNoAnimalCode(studyNo, animalCode);
		if(null != animal){
			if(animal.getAutolyzeFlag() == 0){
				if(animal.getAnatomyCheckFinishFlag() == 0){
					
					String sql = "select count(id)"+
								" from CoresStudy.dbo.TblAnatomyCheck "+
								" where studyNo = ? and animalCode = ?";
					Integer count_check = (Integer) getSession().createSQLQuery(sql)
														.setParameter(0, studyNo)
														.setParameter(1, animalCode)
														.uniqueResult();
					if(count_check == 0){
						String sql1 = "select count(id)"+
								" from CoresStudy.dbo.tblVisceraWeight "+
								" where studyNo = ? and animalCode = ?";
						Integer count_weigh = (Integer) getSession().createSQLQuery(sql1)
																	.setParameter(0, studyNo)
																	.setParameter(1, animalCode)
																	.uniqueResult();
						if(count_weigh == 0){
							String sql2 = "select count(id)"+
								" from CoresStudy.dbo.tblVisceraFixed "+
								" where studyNo = ? and animalCode = ?";
							Integer count_fixed = (Integer) getSession().createSQLQuery(sql2)
																		.setParameter(0, studyNo)
																		.setParameter(1, animalCode)
																		.uniqueResult();
							if(count_fixed == 0){
								//删除，保存日志
								getSession().delete(animal);
								String operatContent ="专题编号："+studyNo+" 动物编号："+animalCode+"  从解剖会话中删除";
								writeLog("从解剖会话中删除该动物",operatContent ,operator);
								json.setSuccess(true);
							}else{
								json.setMsg("该动物已有脏器固定记录，不可以删除！");
							}
						}else{
							json.setMsg("该动物已有脏器称重记录，不可以删除！");
						}
					}else{
						json.setMsg("该动物已有解剖记录，不可以删除！");
					}
				}else{
					json.setMsg("该动物已解剖完成，不可以删除！");
				}
			}else{
				json.setMsg("该动物已标记自溶，不可以删除！");
			}
		}else{
			json.setMsg("动物编号不存在！");
		}
		return json;
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
		  tblLog.setOperatOject("解剖动物列表");
		  tblLog.setOperator(operator);
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost("");
		  tblLogService.save(tblLog);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTaskId(String taskId) {
		String sql = "select ani.animalCode,ani.gender,ani.autolyzeFlag"+
					" from CoresStudy.dbo.tblAnatomyAnimal as ani"+
					" where ani.taskId = :taskId "+
					" order by ani.animalCode";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
													.setParameter("taskId", taskId)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListBySessionIdListOrderByAnatomyBeginTime(
			List<String> sessionIdList) {
		List<Map<String,Object>> mapList = null;
		if(null != sessionIdList && sessionIdList.size() > 0){
			
			int sessionType = 0;
			TblPathSession tblPathSession = tblPathSessionService.getById(sessionIdList.get(0));
			if(null != tblPathSession){
				sessionType = tblPathSession.getSessionType();
			}
			
			String sql = "select distinct tblap.taskId,tblap.animalCode,tblap.studyNo,tblap.visceraWeighFinishFlag,tblap.visceraFixedFinishFlag," +
			"		tblap.visceraFixedWeighFinishFlag,tblu2.realName as anatomyOperator,tblap.anatomyBeginTime,"+
			"				     tblap.anatomyCheckFinishFlag,tblap.autolyzeFlag,tblu.realName as sessionCreator"+
			"	 from ( select tbla.taskId,tbla.studyNo,tbla.animalCode,tbla.visceraWeighFinishFlag,tbla.visceraFixedFinishFlag," +
			"		tbla.visceraFixedWeighFinishFlag,tbla.anatomyOperator,tbla.anatomyBeginTime,"+
			"					              tbla.AnatomyCheckFinishFlag,tbla.autolyzeFlag,tblp.sessionCreator   "+
			"				from CoresStudy.dbo.tblAnatomyAnimal as tbla left join CoresStudy.dbo.TblPathSession as tblp "+
			"					     on " ;
//			"						tbla.anatomySessionId = tblp.sessionId " +
//			"						or tbla.visceraFixedWeightSessionId = tblp.sessionId " +
//			"						or tbla.visceraWeightSessionId = tblp.sessionId  " +
//			"						or tbla.visceraFixedSessionId = tblp.sessionId  ";
			
			switch (sessionType) {
				case 1:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 2:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 3:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 4:
					sql = sql + " tbla.visceraFixedSessionId = tblp.sessionId ";
					break;
				case 5:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 6:
					sql = sql + " tbla.visceraWeightSessionId = tblp.sessionId ";
					break;
				case 7:
					sql = sql + " tbla.anatomySessionId = tblp.sessionId ";
					break;
				case 8:
					sql = sql + " tbla.visceraFixedWeightSessionId = tblp.sessionId ";
					break;
	
				default:
					break;
			}
			
			
			sql = sql +	"				where tbla.anatomySessionId in (:sessionIdList)  "+
			"					       or tbla.visceraFixedWeightSessionId in (:sessionIdList)"+
			"					         or tbla.visceraFixedSessionId in (:sessionIdList)"+
			"					         or tbla.visceraWeightSessionId in (:sessionIdList)"+
			"				) as tblap left join CoresUserPrivilege.dbo.tbluser as tblu "+
			"				 on tblap.sessionCreator = tblu.userName left join CoresUserPrivilege.dbo.tbluser as tblu2"+
			"				 on tblap.anatomyOperator = tblu2.userName  " +
			" order by tblap.anatomyBeginTime ";
			mapList = getSession().createSQLQuery(sql)
			.setParameterList("sessionIdList", sessionIdList)
			.setResultTransformer(new MapResultTransformer())
			.list();
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<String> fixedTimeoutBySessionIdList(List<String> sessionIdList) {
		String hql = "from TblAnatomyAnimal where visceraFixedFinishFlag = 0 and visceraFixedSessionId in (:sessionIdList)";
		List<TblAnatomyAnimal> list = getSession().createQuery(hql).setParameterList("sessionIdList", sessionIdList).list();
		List<String> animalCodeList = new ArrayList<String>();
		Date date = new Date();
		if(null != list && list.size() > 0){
			for(TblAnatomyAnimal obj:list ){
				if(date.getTime()-obj.getAnatomyBeginTime().getTime() > 25*60*1000 ){
					animalCodeList.add(obj.getAnimalCode());
				}
			}
		}
		return animalCodeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByTaskIdListSessionType(
			List<String> taskIdList, Integer sessionType) {

		List<Map<String,Object>> mapList = null;
		if(null != taskIdList && taskIdList.size()>0 && null != sessionType){
			
			String sql = "";
			if(sessionType == 1 || sessionType == 3|| sessionType == 5 ||  sessionType == 7){
				//1,sessionType等于1,3,5,7   动物解剖（解剖和称重或解剖、称重和固定），展示的是未开始解剖的动物(且剔除 解剖前已死亡的)
				sql = "select req.studyNo,reqAnimal.animalCode,reqAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
						" ,'' as anatomyOperator ,req.anatomyRsn"+
					" from CoresStudy.dbo.tblAnatomyReq as req left join  CoresStudy.dbo.tblAnatomyReqAnimalList as reqAnimal"+
					" 		on req.reqNo = reqAnimal.anatomyReqNo and req.studyNo = reqAnimal.studyNo"+
					" 	left join CoresStudy.dbo.tblAnatomyTask as task "+
					" 		on req.studyNo = task.studyNo and req.reqNo = task.anatomyReqNo"+
					" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal "+
					" 		on task.taskId = anatomyAnimal.taskId and reqAnimal.animalCode = anatomyAnimal.animalCode"+
					" 	left join CoresStudy.dbo.tblAnimal as animal"+
					" 		on reqAnimal.studyNo = animal.studyNo and reqAnimal.animalCode = animal.animalCode"+
					" where req.submitFlag = 1 and task.taskId in (:taskIdList) and anatomyAnimal.animalCode is null " +
					" and reqAnimal.cancelFlag = 0 "+
					" order by req.studyNo,reqAnimal.animalCode";
				
			}else if(sessionType == 2){
				//2,sessionType等于2  脏器称重，展示的是已完成解剖的动物（ 未称重且已解剖完成,注意自溶标识）
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
						" ,tbluser.realName as anatomyOperator"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
						" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
						" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode " +
						"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
						" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
						" 		and anatomyAnimal.animalCode is not null " +
						"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
						"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
						" order by task.studyNo,anatomyAnimal.animalCode";
				
			}else if(sessionType == 4){
				//3,sessionType等于4  脏器固定），展示的是已完成解剖的动物（且未开始固定的动物（称重已完成或不需要称重）,注意自溶标识）
//				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
//					  " 	,tbluser.realName as anatomyOperator,reqweigh.weighNum" +
//					  " from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal" +
//					  " 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal" +
//					  " 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode" +
//					  " 		left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName" +
//					  " 		left join (select weigh.studyNo,weigh.reqNo,COUNT(*) as weighNum" +
//					  " 					from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh" +
//					  " 					where weigh.fixedWeighFlag = 0" +
//					  " 					group by weigh.studyNo,weigh.reqNo) as reqweigh" +
//					  " 		on task.studyNo = reqweigh.studyNo and task.anatomyReqNo = reqweigh.reqNo" +
//					  " where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )" +
//					  " 	and anatomyAnimal.animalCode is not null" +
//					  " 	and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
//					  " 	and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) " +
//					  " 	and (anatomyAnimal.visceraWeighFinishFlag =1 or (reqweigh.weighNum < 1  or reqweigh.weighNum is null ) )" +
//
//					  " order by task.studyNo,anatomyAnimal.animalCode";
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
				" 	,tbluser.realName as anatomyOperator" +
				" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal" +
				" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal" +
				" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode" +
				" 		left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName" +
				" 		" +
				" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )" +
				" 	and anatomyAnimal.animalCode is not null" +
				" 	and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
				" 	and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) " +
				
				" order by task.studyNo,anatomyAnimal.animalCode";
				
			}else if(sessionType == 6){
				//4,sessionType等于6  脏器脏器固定和脏器称重，展示的是已完成解剖的动物（且未开始称重和固定的动物,注意自溶标识）
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId" +
						"    ,tbluser.realName as anatomyOperator"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
						" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
						" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode"+
						"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
						" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
						" 		and anatomyAnimal.animalCode is not null " +
						"       and ( anatomyAnimal.visceraWeightSessionId is null or anatomyAnimal.visceraWeightSessionId = '')" +
						"       and (anatomyAnimal.visceraFixedSessionId is null or anatomyAnimal.visceraFixedSessionId = '')" +
						"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
						" order by task.studyNo,anatomyAnimal.animalCode";
				
			}else if(sessionType == 8){
				//5,sessionType等于8   固定后称重，展示的是已固定的动物且未固定后称重（解剖已完成标识，固定已完成标识）
				sql = "select task.studyNo,anatomyAnimal.animalCode,anatomyAnimal.gender,task.anatomyNum,animal.animalId,task.taskId"+
				"    ,tbluser.realName as anatomyOperator"+
				" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as anatomyAnimal"+
				" 		on task.taskId = anatomyAnimal.taskId left join CoresStudy.dbo.tblAnimal as animal"+
				" 		on task.studyNo = animal.studyNo and anatomyAnimal.animalCode = animal.animalCode"+
				"       left join CoresUserPrivilege.dbo.tbluser as tbluser on anatomyAnimal.anatomyOperator= tbluser.userName "+
				" where task.taskId in (:taskIdList)  and (anatomyAnimal.autolyzeFlag is null or anatomyAnimal.autolyzeFlag =0 )"+
				" 		and anatomyAnimal.animalCode is not null " +
				"       and anatomyAnimal.visceraFixedSessionId is not null and anatomyAnimal.visceraFixedSessionId != ''" +
				"		and (anatomyAnimal.visceraFixedWeightSessionId is null or anatomyAnimal.visceraFixedWeightSessionId = '') " +
				"		and anatomyAnimal.visceraFixedFinishFlag = 1" +
				"		and (anatomyAnimal.anatomyCheckFinishFlag = 1 ) "+
				" order by task.studyNo,anatomyAnimal.animalCode";
				
			}
			
			mapList = getSession().createSQLQuery(sql)
									.setParameterList("taskIdList", taskIdList)
									.setResultTransformer(new MapResultTransformer())
									.list();
			
		}
		return mapList;
	
	}

	public Map<String, Object> saveOrUpdate(String taskId, String sessionId,
			Integer sessionType, String animalCode, String userName,
			Date anatomyBeginTime) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", "参数错误");
		if(null != taskId && null != sessionId && null != sessionType && null != animalCode){
			if(sessionType == 1 || sessionType == 3 || sessionType == 5 || sessionType == 7){
				//1, sessionType 等于 1 ,3,5,7 动物解剖...，保存解剖动物信息
				TblAnatomyTask tblAnatomyTask = tblAnatomyTaskService.getById(taskId);
				if(null != tblAnatomyTask){
					String studyNo = tblAnatomyTask.getStudyNo();
					Integer reqNo = tblAnatomyTask.getAnatomyReqNo();
					TblAnatomyReqAnimalList tblAnatomyReqAnimal = tblAnatomyReqAnimalListService
									.getByStudyNoReqNoAnimalCode(studyNo,reqNo,animalCode);
					boolean exist = isExistByTaskIdAnimalCode(taskId,animalCode);
					if(exist){
						map.put("msg", "动物已解剖!");
						return map;
					}
					if(null != tblAnatomyReqAnimal){
						TblAnatomyAnimal tblAnatomyAnimal = new TblAnatomyAnimal();
						String id = getKey();
						tblAnatomyAnimal.setId(id);
						tblAnatomyAnimal.setStudyNo(studyNo);
						tblAnatomyAnimal.setGroupId(tblAnatomyReqAnimal.getGroupID());
						tblAnatomyAnimal.setGender(tblAnatomyReqAnimal.getGender());
						tblAnatomyAnimal.setAnimalCode(animalCode);
						tblAnatomyAnimal.setAnatomyPlanNum(tblAnatomyTask.getAnatomyPlanNum());
						tblAnatomyAnimal.setTaskId(taskId);
						
						tblAnatomyAnimal.setDeadFlag(1);
						tblAnatomyAnimal.setDeadRsn(tblAnatomyTask.getAnatomyRsn());
						tblAnatomyAnimal.setAnatomySessionId(sessionId);//剖检会话id
						if(sessionType == 3){
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
						}else if(sessionType == 7){
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else if(sessionType == 5){
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}
						
						tblAnatomyAnimal.setAnatomyBeginTime(anatomyBeginTime);
						tblAnatomyAnimal.setAnatomyOperator(userName);
						//保存  成功
						getSession().save(tblAnatomyAnimal);
						
						map.put("msg", "");
						//TODO  map的其他信息
						map.put("taskId",taskId);
						map.put("studyNo",studyNo);
						map.put("animalCode", animalCode);
						String realName = "";
						TblPathSession tblPathSession = tblPathSessionService.getById(sessionId);
						if(null != tblPathSession){
							String sessionCreator = tblPathSession.getSessionCreator();
							User user = userService.getByUserName(sessionCreator);
							if(null != user){
								realName = user.getRealName();
							}
						}
						map.put("realName", realName);
						map.put("visceraWeightFinishSign", "");
						map.put("visceraFixedFinishSign", "");
						map.put("anatomyCheckFinishFlag", 0);
						map.put("autolyzeFlag", 0);
					}else{
						map.put("msg", "解剖申请动物不存在!");
						return map;
					}
				}else{
					map.put("msg", "解剖任务不存在!");
					return map;
				}
			}else if(sessionType == 2 || sessionType == 4 || sessionType == 6|| sessionType == 8){
				//2, sessionType 等于 2 ,4,6,8 脏器，解剖已完成，其他对应操作没做
				TblAnatomyAnimal tblAnatomyAnimal = getByTaskIdAnimalCode(taskId,animalCode);
				if(null != tblAnatomyAnimal){
					String visceraWeightSessionId = tblAnatomyAnimal.getVisceraWeightSessionId();
					String visceraFixedSessionId = tblAnatomyAnimal.getVisceraFixedSessionId();
					String visceraFixedWeightSessionId = tblAnatomyAnimal.getVisceraFixedWeightSessionId();
					if(sessionType == 2){
						if((null == visceraWeightSessionId || "".equals(visceraWeightSessionId)) ){
//							&&
//							(null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
//							(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))
							//称重会话
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
						}else{
							map.put("msg", "脏器已称重或已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 4){
						if((null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//固定会话
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else{
							map.put("msg", "脏器已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 6){
						if((null == visceraWeightSessionId || "".equals(visceraWeightSessionId)) &&
								(null == visceraFixedSessionId || "".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//称重会话
							tblAnatomyAnimal.setVisceraWeightSessionId(sessionId);
							//固定会话
							tblAnatomyAnimal.setVisceraFixedSessionId(sessionId);
						}else{
							map.put("msg", "脏器已称重或已固定或已固定后称重！");
							return map;
						}
					}else if(sessionType == 8){
						if(	(null != visceraFixedSessionId || !"".equals(visceraFixedSessionId)) &&
								(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId))){
							//固定后称重会话
							tblAnatomyAnimal.setVisceraFixedWeightSessionId(sessionId);
						}else{
							map.put("msg", "脏器未固定或已固定后称重！");
							return map;
						}
					}
					//更新 
					getSession().update(tblAnatomyAnimal);
					
					map.put("msg", "");
					//TODO  map的其他信息
					map.put("taskId", taskId);
					map.put("studyNo", tblAnatomyAnimal.getStudyNo());
					map.put("animalCode", animalCode);
					String realName = "";
					TblPathSession tblPathSession = tblPathSessionService.getById(sessionId);
					if(null != tblPathSession){
						String sessionCreator = tblPathSession.getSessionCreator();
						User user = userService.getByUserName(sessionCreator);
						if(null != user){
							realName = user.getRealName();
						}
					}
					map.put("realName", realName);
					map.put("visceraWeightFinishSign", tblAnatomyAnimal.getVisceraWeightFinishSign());
					map.put("visceraFixedFinishSign", tblAnatomyAnimal.getVisceraFixedFinishSign());
					map.put("anatomyCheckFinishFlag", tblAnatomyAnimal.getAnatomyCheckFinishFlag());
					map.put("autolyzeFlag", tblAnatomyAnimal.getAutolyzeFlag());
					
				}else{
					map.put("msg", "解剖动物不存在!");
					return map;
				}
			}
		}
		return map;
	
	}
}
