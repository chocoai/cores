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
import com.lanen.model.path.TblAnatomyOperator;
import com.lanen.model.path.TblPathSession;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

@Service
public class TblPathSessionServiceImpl extends BaseDaoImpl<TblPathSession> implements TblPathSessionService{

	public List<String> saveList(int sessionType, String userName,
			List<String> taskIdList) {
		List<String> sessionIdList = null;
		if(sessionType > 0 && null != userName && null != taskIdList && taskIdList.size() > 0){
			sessionIdList = new ArrayList<String>();
			TblPathSession tblPathSession = null;
			String sessionId = "";
			Date currentDate = new Date();
			for(String taskId :taskIdList){
				tblPathSession = new TblPathSession();
				sessionId = getKey();
				sessionIdList.add(sessionId);
				
				tblPathSession.setSessionId(sessionId);
				tblPathSession.setTaskId(taskId);
				tblPathSession.setSessionType(sessionType);
				tblPathSession.setCreatedTime(currentDate);
				tblPathSession.setSessionCreator(userName);
				
				getSession().save(tblPathSession);
				
			}
				
		}
		return sessionIdList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapList(List<String> taskIdList,
			int sessionType) {
		List<Map<String,Object>> mapList = null;
		if(null != taskIdList && taskIdList.size() > 0 && sessionType > -1){
			List<Integer> sessionTypeList = new ArrayList<Integer>();
			switch (sessionType) {
			case 0:
				sessionTypeList.add(1);
				sessionTypeList.add(2);
				sessionTypeList.add(3);
				sessionTypeList.add(4);
				sessionTypeList.add(5);
				sessionTypeList.add(6);
				sessionTypeList.add(7);
				sessionTypeList.add(8);
				break;
			case 1:
				sessionTypeList.add(1);
				sessionTypeList.add(3);
				sessionTypeList.add(5);
				sessionTypeList.add(7);
				break;
			case 2:
				sessionTypeList.add(2);
				sessionTypeList.add(3);
				sessionTypeList.add(6);
				sessionTypeList.add(7);
				break;
			case 4:
				sessionTypeList.add(4);
				sessionTypeList.add(5);
				sessionTypeList.add(6);
				sessionTypeList.add(7);
				break;
			case 8:
				sessionTypeList.add(8);
				break;

			default:
				break;
			}
			String sql = "select session.sessionId,session.taskId,session.sessionType,u.realName as sessionCreator,"+
						" convert(varchar(16),session.createdTime,120) as createTime,es.signer as finishSigner,"+
						" convert(varchar(10),es.dateTime,120) as finishSignDate ,task.studyNo"+
						" from CoresStudy.dbo.tblPathSession as session left join CoresUserPrivilege.dbo.tbluser as u"+
						" on session.sessionCreator = u.userName left join CoresUserPrivilege.dbo.tblES as es "+
						" on session.sessionFinishSign = es.esId  left join CoresStudy.dbo.tblAnatomyTask as task"+
						" on session.taskId = task.taskId"+
						" where session.taskId in (:taskIdList) and  session.sessionType in (:sessionTypeList)"+
						" order by session.sessionId desc ";
			mapList = getSession().createSQLQuery(sql)
									.setParameterList("taskIdList", taskIdList)
									.setParameterList("sessionTypeList", sessionTypeList)
									.setResultTransformer(new MapResultTransformer())
									.list();
		}
		return mapList;
	}

	public String getSessionIdByTaskIdSessionIdList(String taskId,
			List<String> sessionIdList) {
		if(null != sessionIdList && sessionIdList.size() > 0 && null != taskId){
			for(String sessionId :sessionIdList){
				TblPathSession tblPathSession = getById(sessionId);
				if(null != tblPathSession && taskId.equals(tblPathSession.getTaskId())){
					return sessionId;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getTaskIdSessionIdMapBySessionIdList(
			List<String> sessionIdList) {
		Map<String, String> map = null;
		String hql = "From TblPathSession where sessionId in (:sessionIdList)";
		List<TblPathSession> list = getSession().createQuery(hql)
												.setParameterList("sessionIdList", sessionIdList)
												.list();
		if(null != list && list.size() > 0){
			map = new HashMap<String,String>();
			for(TblPathSession obj:list){
				map.put(obj.getTaskId(), obj.getSessionId());
			}
		}
		
		return map;
	}
	
	
	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblESService tblESService;
	
	@Resource
	private UserService userService;
	
	public void updateListBySessionIdList(String userName,
			List<String> sessionIdList) {
		for(String sessionId:sessionIdList){
			TblPathSession tblPathSession= getById(sessionId);
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String esId = tblESService.getKey("TblES");
			es.setEsType(702);
			es.setDateTime(new Date());
			es.setEsId(esId);
			esLink.setTableName("TblPathSession");
			esLink.setDataId(tblPathSession.getSessionId());
			esLink.setTblES(es);
			esLink.setEsType(702);
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			esLink.setEsTypeDesc("数据确认");
			es.setSigner(userService.getRealNameByUserName(userName));
			es.setEsTypeDesc("数据确认");
			tblESService.save(es);
			tblESLinkService.save(esLink);
			tblPathSession.setSessionFinishSign(esId);
			getSession().update(tblPathSession);
		}
	}

	public List<String> saveList(int sessionType, String userName,
			List<String> taskIdList, List<String> userNameList) {
		List<String> sessionIdList = null;
		if(sessionType > 0 && null != userName && null != taskIdList && taskIdList.size() > 0
				){
			sessionIdList = new ArrayList<String>();
			TblPathSession tblPathSession = null;
			TblAnatomyOperator tblAnatomyOperator = null;
			String sessionId = "";
			String id = "";
			Date currentDate = new Date();
			for(String taskId :taskIdList){
				tblPathSession = new TblPathSession();
				sessionId = getKey();
				sessionIdList.add(sessionId);
				
				tblPathSession.setSessionId(sessionId);
				tblPathSession.setTaskId(taskId);
				tblPathSession.setSessionType(sessionType);
				tblPathSession.setCreatedTime(currentDate);
				tblPathSession.setSessionCreator(userName);
				
				getSession().save(tblPathSession);
				if(null != userNameList && userNameList.size() > 0){
					//保存解剖操作者
					for(String operator :userNameList){
						tblAnatomyOperator = new TblAnatomyOperator();
						id = getKey("TblAnatomyOperator");
						tblAnatomyOperator.setId(id);
						tblAnatomyOperator.setSessionId(sessionId);
						tblAnatomyOperator.setAnatomyOperator(operator);
						
						getSession().save(tblAnatomyOperator);
					}
				}
				
			}
				
		}
		return sessionIdList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserNameRealName(
			List<String> sessionIdList) {
		List<Map<String,Object>> list = null;
		if(null != sessionIdList && sessionIdList.size() > 0){
			String sql = "select op.anatomyOperator as userName ,us.realName"+
						" from CoresStudy.dbo.tblAnatomyOperator as op left join "+
						" 	CoresUserPrivilege.dbo.tbluser as us on "+
						" 	op.anatomyOperator = us.userName"+
						" where op.sessionId in (:sessionIdList)";
			
			list = getSession().createSQLQuery(sql)
								.setParameterList("sessionIdList", sessionIdList)
								.setResultTransformer(new MapResultTransformer())
								.list();
		}
		return list;
	}

	public void updateOperatorList(String sessionId, List<String> userNameList) {
		String sql = "delete "+
					" from CoresStudy.dbo.tblAnatomyOperator"+
					" where sessionId = :sessionId";
		getSession().createSQLQuery(sql).setParameter("sessionId", sessionId).executeUpdate();
		TblAnatomyOperator tblAnatomyOperator = null;
		String id = "";
		//保存解剖操作者
		for(String operator :userNameList){
			tblAnatomyOperator = new TblAnatomyOperator();
			id = getKey("TblAnatomyOperator");
			tblAnatomyOperator.setId(id);
			tblAnatomyOperator.setSessionId(sessionId);
			tblAnatomyOperator.setAnatomyOperator(operator);
			
			getSession().save(tblAnatomyOperator);
		}
	}

	public List<String> getSessionIdList(String taskId) {
		List<String> list = null;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllPathSD(List<String> studyNoList) {
		String sql = " select tbls.pathSD from CoresSchedule.dbo.tblAppointPathSD as tbls where tbls.studyNo in (:studyNoList) and tbls.cancelDate is null ";
		List<Map<String,Object>> list = getSession().createSQLQuery(sql).setParameterList("studyNoList", studyNoList)
		                               .setResultTransformer(new MapResultTransformer())
		.list();
		return list;
	}

	public String getPathSD(String studyNo) {
		String sql = " select tbls.pathSD from CoresSchedule.dbo.tblAppointPathSD as tbls where tbls.studyNo = ? ";
		String pathSD = (String) getSession().createSQLQuery(sql)
											.setParameter(0, studyNo)
											.uniqueResult();
		return pathSD;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomySessionListByTaskId(String taskId,int tabIndex) {
		String sql="SELECT sessionId,taskId,sessionType" +
				   "   ,(select realName from CoresUserPrivilege.dbo.tbluser where userName=sessionCreator)  as sessionCreator "+
				   "   ,createdTime "+
				   "   ,sessionFinishSign "+
				   "   ,sessionReviewSign "+
				   "   ,closeRsn "+
				   "   ,balValidationId "+
				   " FROM CoresStudy.dbo.tblPathSession";
		if(tabIndex==1){
			sql=sql+" where taskId=:taskId and sessionType in (1,3,5,7)";
		}else if(tabIndex==2){
			sql=sql+" where taskId=:taskId and sessionType in (2,3,6,7,8)";
		}else if(tabIndex==3){
			sql=sql+" where taskId=:taskId and sessionType in (4,5,6,7)";
		}else{
			sql=sql+" where taskId=:taskId ";
		}
		List<Map<String, Object>> list=getSession().createSQLQuery(sql).setParameter("taskId", taskId)
		                                      .setResultTransformer(new MapResultTransformer())
		                                      .list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAnatomySessionListByTaskIds(
			String taskIds, int tabIndex) {
		// TODO Auto-generated method stub
		String[] taskIdList = taskIds.split(",");
		List<String> taskList=new ArrayList<String>();
		for(String task: taskIdList)
		{
			taskList.add(task);
		}
		String sql="SELECT sessionId,taskId,sessionType" +
				   "   ,(select realName from CoresUserPrivilege.dbo.tbluser where userName=sessionCreator)  as sessionCreator "+
				   "   ,createdTime "+
				   "   ,sessionFinishSign "+
				   "   ,sessionReviewSign "+
				   "   ,closeRsn "+
				   "   ,balValidationId "+
				   " FROM CoresStudy.dbo.tblPathSession";
		if(tabIndex==1){
			sql=sql+" where taskId in (:taskId) and sessionType in (1,3,5,7)";
		}else if(tabIndex==2){
			sql=sql+" where taskId in (:taskId) and sessionType in (2,3,6,7,8)";
		}else if(tabIndex==3){
			sql=sql+" where taskId in (:taskId) and sessionType in (4,5,6,7)";
		}else{
			sql=sql+" where taskId in (:taskId) ";
		}
		
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
										   .setParameterList("taskId", taskList)
		                                   .setResultTransformer(new MapResultTransformer())
		                                   .list();
		return list;
	}

}
