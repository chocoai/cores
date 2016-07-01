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
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.path.TblAnatomyAnimal;
import com.lanen.model.path.TblAnatomyReq;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;

@Service
public class TblAnatomyTaskServiceImpl extends BaseDaoImpl<TblAnatomyTask> implements TblAnatomyTaskService{

	/**
	 * 解剖申请service
	 */
	@Resource
	private TblAnatomyReqService tblAnatomyReqService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblESService tblESService;
	@Resource
	private UserService userService;
	
	public int getNextAnatomyNumByStudyNo(String studyNo) {
		String hql = "FROM TblAnatomyTask where studyNo = ? ";
		List<?> list = getSession().createQuery(hql)
									.setParameter(0, studyNo)
									.list();
		if(null != list && list.size()>0){
			return list.size()+1;
		}
		
		return 1;
	}

	public Map<String, String> save(TblAnatomyTask tblAnatomyTask, String reqId) {
		Map<String,String> map = new HashMap<String,String>();
		
		TblAnatomyReq tblAnatomyReq = tblAnatomyReqService.getById(reqId);
		if(null != tblAnatomyReq){
			boolean exist = isExistBy(tblAnatomyReq.getStudyNo(),tblAnatomyReq.getReqNo());
			if(!exist){
				//解剖任务
				String taskId = getKey("TblAnatomyTask");
				tblAnatomyTask.setTaskId(taskId);
				tblAnatomyTask.setStudyNo(tblAnatomyReq.getStudyNo());
				tblAnatomyTask.setAnatomyReqNo(tblAnatomyReq.getReqNo());
				tblAnatomyTask.setTaskCreateTime(new Date());
				tblAnatomyTask.setTempFlag(0);//非临时
				int anatomyNum = getNextAnatomyNumByStudyNo(tblAnatomyReq.getStudyNo());
				tblAnatomyTask.setAnatomyNum(anatomyNum);
				tblAnatomyTask.setIsplan(tblAnatomyReq.getAnatomyRsn() == 1? 1:0);
				tblAnatomyTask.setAnatomyPlanNum(tblAnatomyReq.getAnatomyPlanNum());
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
				
				getSession().save(tblAnatomyTask);
				map.put("taskId", tblAnatomyTask.getTaskId());
				map.put("success", "true");
			}else{
				map.put("success", "false");
				map.put("msg", "该申请已创建解剖任务！");
			}
		}else{
			map.put("success", "false");
			map.put("msg", "申请Id不存在！");
		}
		return map;
	}

	public boolean isExistBy(String studyNo, int reqNo) {
		String hql = "FROM TblAnatomyTask where studyNo = ? and anatomyReqNo = ? ";
		
		List<?> list = getSession().createQuery(hql)
									.setParameter(0, studyNo)
									.setParameter(1, reqNo)
									.list();
		if(null != list && list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByStudyStartDate(
			String beginDateStr, String endDateStr) {
		List<Map<String,Object>> mapList = null;
		if(null != beginDateStr && !"".equals(beginDateStr) && null != endDateStr && !"".equals(endDateStr) ){
			String sql = "select study.studyNo,task.taskId,task.anatomyNum,convert(varchar(10),task.taskCreateTime,120) as taskCreateTime"+
						" from CoresStudy.dbo.tblStudyPlan as study left join CoresStudy.dbo.tblAnatomyTask as task "+
						" on study.studyNo = task.studyNo"+
						" where ( convert(varchar(20),study.studyStartDate,120) between :beginDateStr and :endDateStr  or "+
						" convert(varchar(20),study.studyStartDate,120) between :endDateStr and :beginDateStr ) and "+
						" task.taskId is not null"+
						" order by study.studyStartDate desc";
			mapList = getSession().createSQLQuery(sql)
									.setParameter("beginDateStr", beginDateStr)
									.setParameter("endDateStr", endDateStr)
									.setResultTransformer(new MapResultTransformer())
									.list();
		}
		return mapList;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTaskDetailListByStudyCreateDate(User user,String beginDateStr, String endDateStr) {
		List<Map<String,Object>> mapList = null;
		if(null != beginDateStr && !"".equals(beginDateStr) && null != endDateStr && !"".equals(endDateStr) ){
		/*	tblappointPathSD
			   pathSD:病理专题负责人
		病理负责人是权限：
			List<User> findByPrivilegeName(String privilegeName);
		病理负责人
		tblappointSD:专题负责人*/
			
			List<User> userList = userService.findByPrivilegeName("病理负责人");
			System.out.println("传过来的userId是："+user.getId());
			boolean flag = false;
			for(User tempUser:userList)
			{
				if(tempUser.getId().equals(user.getId()))
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				System.out.println("当前用户是病理负责人");
			}
			String sql = "select task.studyNo,task.anatomyNum,u.realName as taskCreater,convert(varchar(10),task.taskCreateTime,120) as taskCreateTime ,taskId," +
			" study.studydirector,convert(varchar(10),req.beginDate,120) as planBeginDate,studyitem.pathSD,sd.sd "+
			" from CoresStudy.dbo.tblAnatomyTask as task left join CoresUserPrivilege.dbo.tbluser as u"+
			" on task.taskCreater = u.userName " +
			" left join CoresStudy.dbo.view_studyNoSD as sd on sd.studyNo=task.studyNo "+
			" left join CoresContract.dbo.tblStudyItem as studyitem on studyitem.studyNo=task.studyNo "+
			" left join CoresStudy.dbo.tblStudyPlan as study on task.studyNo = study.studyNo " +
			" left join CoresStudy.dbo.tblAnatomyReq as req on req.studyNo = task.studyNo and req.reqNo = task.anatomyReqNo "+
			" where ( (convert(varchar(10),task.taskCreateTime,120) between :beginDateStr and :endDateStr  or "+
			" convert(varchar(10),task.taskCreateTime,120) between :endDateStr and :beginDateStr) )and task.tempFlag != 2 ";
			
			//看看用户是否是病理专题负责人，病理负责人或者课题的负责人
			if(!flag)
			{
			 sql+=" and ( (studyitem.pathSD=:userName) or (sd.sd=:userName) or (u.realName=:userName)) ";
			}
			sql+=" order by task.taskCreateTime desc";
			 Query query = getSession().createSQLQuery(sql)
									.setParameter("beginDateStr", beginDateStr)
									.setParameter("endDateStr", endDateStr);
			if(!flag)
				query.setParameter("userName", user.getRealName().trim());
			
			mapList = query.setResultTransformer(new MapResultTransformer()).list();
			System.out.println("detail sql="+sql);
			System.out.println(beginDateStr+"====="+endDateStr+" userName = "+user.getRealName());
		}
		System.out.println("result list"+mapList);
		if(mapList!=null)
		{
			System.out.println("maplist size="+mapList.size());
		}
		
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByTaskCreateDate(
			String beginDateStr, String endDateStr) {
		List<Map<String,Object>> mapList = null;
		if(null != beginDateStr && !"".equals(beginDateStr) && null != endDateStr && !"".equals(endDateStr) ){
			String sql = "select task.studyNo,task.anatomyNum,u.realName as taskCreater,convert(varchar(10),task.taskCreateTime,120) as taskCreateTime ,taskId," +
						" 	viewstudy.sd as studydirector,convert(varchar(10),req.beginDate,120) as planBeginDate "+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresUserPrivilege.dbo.tbluser as u"+
						" 	on task.taskCreater = u.userName " +
						" 	left join CoresStudy.dbo.tblStudyPlan as study on task.studyNo = study.studyNo " +
						" 	left join CoresStudy.dbo.tblAnatomyReq as req on req.studyNo = task.studyNo and req.reqNo = task.anatomyReqNo " +
						" left join CoresStudy.dbo.view_studyNoSD as viewstudy on viewstudy.studyNo = study.studyNo"+
						" where( convert(varchar(10),task.taskCreateTime,120) between :beginDateStr and :endDateStr  or "+
						" 	convert(varchar(10),task.taskCreateTime,120) between :endDateStr and :beginDateStr ) and task.tempFlag != 2 "+
						" 	order by task.taskCreateTime desc";
			mapList = getSession().createSQLQuery(sql)
									.setParameter("beginDateStr", beginDateStr)
									.setParameter("endDateStr", endDateStr)
									.setResultTransformer(new MapResultTransformer())
									.list();
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByTaskIdList(
			List<String> taskIdList2) {
		List<Map<String,Object>> mapList = null;
		if(null != taskIdList2 && taskIdList2.size() > 0 ){
			String sql = "select task.studyNo,task.anatomyNum,u.realName as taskCreater,convert(varchar(10),task.taskCreateTime,120) as taskCreateTime ,taskId"+
						" ,task.anatomyRsn,animal2.animalNum,study.animalType"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresUserPrivilege.dbo.tbluser as u"+
						" on task.taskCreater = u.userName    left join"+
						" (	select animal.studyNo,animal.anatomyReqNo,count(*) as animalNum"+
						" 		from CoresStudy.dbo.tblAnatomyReqAnimalList as animal"+
						" 		group by animal.studyNo,animal.anatomyReqNo) as animal2"+
						" on task.studyNo = animal2.studyNo and task.anatomyReqNo = animal2.anatomyReqNo"+
						" left join CoresStudy.dbo.tblStudyPlan as study on task.studyNo = study.studyNo"+
						" where task.taskId in (:taskIdList)"+
						" order by task.taskId";
			mapList = getSession().createSQLQuery(sql)
									.setParameterList("taskIdList", taskIdList2)
									.setResultTransformer(new MapResultTransformer())
									.list();
		}
		return mapList;
	}

	public Integer getReqFixedVisceraNumberByTaskIdList(List<String> taskIdList) {
		String sql="select "+
				   " count(arpc.id) "+
				   " FROM CoresStudy.dbo.tblAnatomyReqPathPlanCheck as arpc "+
				   " join CoresStudy.dbo.tblAnatomyTask as at "+
				   " on arpc.studyNo=at.studyNo and arpc.reqNo=at.anatomyReqNo ";
		Integer fixedVisceraCount=0;
		if(null!=taskIdList && taskIdList.size()>0){
			String taskId=" where at.taskId in (";
			for(int i=0; i<taskIdList.size();i++){
				if(i!=taskIdList.size()-1){
					taskId=taskId+taskIdList.get(i)+",";
				}else{
					taskId=taskId+taskIdList.get(i)+")";
				}
			}
			sql=sql+taskId;
			Query query=getSession().createSQLQuery(sql);
			fixedVisceraCount=(Integer) query.uniqueResult();
		}
		return fixedVisceraCount;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStudyNoByTaskIdList(
			List<String> taskIdList) {
		String sql="select distinct(studyNo) " +
				   "  FROM CoresStudy.dbo.tblAnatomyTask " +
				   "  where taskId in (:taskIdList)";
		List<Map<String, Object>> mapList=getSession().createSQLQuery(sql)
		                                              .setParameterList("taskIdList", taskIdList)
		                                              .setResultTransformer(new MapResultTransformer())
		                                              .list();
		                                  
		return mapList;
	}
	
	public int countAnimalWeightByTaskIdList(List<String> taskIdList) {
		String sql = "  select distinct tblw.visceraCode from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as tblw  "+
		 "  left join CoresStudy.dbo.tblAnatomyTask as tbla  "+
		 "   on  tbla.studyNo = tblw.studyNo and tbla.AnatomyReqNo = tblw.reqNo  "+
		 "   where tbla.taskId in (:taskIdList)";
		List<?> list = getSession().createSQLQuery(sql).setParameterList("taskIdList", taskIdList).list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(String taskId) {
		Map<String,Object> map = null;
		if(null != taskId){
			String sql = "select task.studyNo,task.anatomyRsn,tbluser.realName as taskCreater, "+
						" 	convert(varchar(10),taskCreateTime,120) as taskDateStr,convert(varchar(10),req.submitDate,120) as reqDateStr, "+
						" 	viewstudy.sd sd,study.animalType,study.animalStrain,animal.animalNum ," +
						"  convert(varchar(10),req.beginDate,120) as reqBeginDateStr,convert(varchar(10),req.endDate,120) as reqEndDateStr," +
						" task.anatomyNum"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresUserPrivilege.dbo.tbluser as tbluser "+
						" 	on task.taskCreater = tbluser.userName left join CoresStudy.dbo.tblAnatomyReq as req "+
						" 	on task.studyNo = req.studyNo and task.anatomyReqNo = req.reqNo left join CoresStudy.dbo.tblStudyPlan as study "+
						" 	on task.studyNo = study.studyNo  "+
						" 	left join ( "+
						" 			select animallist.studyNo,animallist.anatomyReqNo,count(*)as animalNum "+
						" 			from CoresStudy.dbo.tblAnatomyReqAnimalList as animallist "+
						" 			group by animallist.studyNo,animallist.anatomyReqNo			 "+																
						" 	)as animal on animal.studyNo = task.studyNo and task.anatomyReqNo = animal.anatomyReqNo "+
						"  left join CoresStudy.dbo.view_studyNoSD as viewstudy on viewstudy.studyNo = task.studyNo "+
						" where task.taskId = :taskId ";
			map  = (Map<String, Object>) getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setResultTransformer(new MapResultTransformer())
								.uniqueResult();
		}
		return map;
	}

	public Json checkBeforeSign(String taskId, String itemName) {
		Json json = new Json();
		if(null != taskId && null !=itemName ){
			TblAnatomyTask tblAnatomyTask = getById(taskId);
			String anatomyCheckFinishSign = tblAnatomyTask.getAnatomyCheckFinishSign();
			String visceraFixedWeightFinishSign = tblAnatomyTask.getVisceraFixedWeightFinishSign();
			if("解剖".equals(itemName)){
				//1.是否已签字
				if(null != anatomyCheckFinishSign && !"".equals(anatomyCheckFinishSign)){
					json.setMsg("解剖已完成签字，不可重复签字！");
				}else{
					//2.任务下动物是否已解剖，解剖是否签字
					Json anatomyJson = this.checkAnimalAnatomy(taskId);
					if(anatomyJson.isSuccess()){
						//3.是否需要称重，若需要，称重是否完成，称重是否签字(自溶算称重完成签字)
						boolean hasWeigh = this.hasWeigh(taskId);
						if(hasWeigh){
							Json weighJson = this.checkVisceraWeigh(taskId);
							if(!weighJson.isSuccess()){
								json.setMsg(weighJson.getMsg());
								return json;
							}
						}
						//4.是否需要固定，若需要，固定是否完成，固定是否签字(自溶算称重完成签字)
						boolean hasFixed = this.hasFixed(taskId);
						if(hasFixed){
							Json fixedJson = this.checkdVisceraFixed(taskId);
							if(!fixedJson.isSuccess()){
								json.setMsg(fixedJson.getMsg());
								return json;
							}
						}
						json.setSuccess(true);
					}else{
						json.setMsg(anatomyJson.getMsg());
					}
				}
				//
			}else if("固定后称重".equals(itemName)){
				//1.解剖是否签字，固定后称重是否已签字
				if(null != anatomyCheckFinishSign && !"".equals(anatomyCheckFinishSign)){
					if(null != visceraFixedWeightFinishSign && !"".equals(visceraFixedWeightFinishSign)){
						json.setMsg("固定后称重已完成签字，不可重复签字！");
					}else{
						boolean hasFixedWeigh = this.hasFixedWeigh(taskId);
						if(hasFixedWeigh){
							Json fixedJson = this.CheckVisceraFixedWeigh(taskId);
							if(!fixedJson.isSuccess()){
								json.setMsg(fixedJson.getMsg());
							}else{
								json.setSuccess(true);
							}
						}else{
							json.setMsg("该任务下无‘固定后称重’项目！");
						}
					}
				}else{
					json.setMsg("解剖未签字确认！");
				}
				//2.任务下会话是否都已签字（无数据的除外）
				//3.动物数量是否齐全
				//4.各项操作是否完成，（注意自溶）
				//
			}else{
				json.setMsg("此项目不存在！");
			}
		}else{
			json.setMsg("参数错误！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Json CheckVisceraFixedWeigh(String taskId) {
		// 检查动物 固定情况
		Json json = new Json();
		if(null != taskId){
			//申请的动物号，会话Id，签字
			List<Map<String,Object>> mapList = null;
			String sql = "select reqanimal.animalCode,anatomyanimal.autolyzeFlag,anatomyanimal.visceraFixedWeightSessionId,pathsession.sessionFinishSign"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqanimal"+
						" 	on task.studyNo = reqanimal.studyNo and task.anatomyReqNo = reqanimal.anatomyReqNo"+
						" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal"+
						" 	on reqanimal.studyNo = anatomyanimal.studyNo and reqanimal.animalCode = anatomyanimal.animalCode"+
						" 	left join CoresStudy.dbo.tblPathSession as pathsession "+
						" 	on anatomyanimal.visceraFixedWeightSessionId = pathsession.sessionId"+
						" where task.taskId=:taskId " +
						" and reqanimal.cancelFlag = 0";
			mapList = getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setResultTransformer(new MapResultTransformer())
								.list();
			if(null != mapList){
				for(Map<String,Object> map:mapList){
					String animalCode = (String) map.get("animalCode");
					String visceraFixedWeightSessionId = (String) map.get("visceraFixedWeightSessionId");
					Integer autolyzeFlag = (Integer) map.get("autolyzeFlag");
					String sessionFinishSign = (String) map.get("sessionFinishSign");
					if(null != autolyzeFlag && autolyzeFlag == 1){
						//自溶了
						continue;
					}else if(null == visceraFixedWeightSessionId || "".equals(visceraFixedWeightSessionId)){
						json.setMsg("动物（"+animalCode+"）脏器未固定后称重！");
						return json;
					}else if(null == sessionFinishSign || "".equals(sessionFinishSign)){
						json.setMsg("动物（"+animalCode+"）脏器固定后称重会话未签字确认！");
						return json;
					}
				}
				json.setSuccess(true);
			}else{
				json.setMsg("申请列表为空！");
			}
		}else{
			json.setMsg("任务编号为空！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Json checkdVisceraFixed(String taskId) {
		// 检查动物 固定情况
		Json json = new Json();
		if(null != taskId){
			//申请的动物号，会话Id，签字
			List<Map<String,Object>> mapList = null;
			String sql = "select reqanimal.animalCode,anatomyanimal.autolyzeFlag,anatomyanimal.visceraFixedSessionId,pathsession.sessionFinishSign"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqanimal"+
						" 	on task.studyNo = reqanimal.studyNo and task.anatomyReqNo = reqanimal.anatomyReqNo"+
						" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal"+
						" 	on reqanimal.studyNo = anatomyanimal.studyNo and reqanimal.animalCode = anatomyanimal.animalCode"+
						" 	left join CoresStudy.dbo.tblPathSession as pathsession "+
						" 	on anatomyanimal.visceraFixedSessionId = pathsession.sessionId"+
						" where task.taskId=:taskId " +
						"  and reqanimal.cancelFlag = 0 ";
			mapList = getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setResultTransformer(new MapResultTransformer())
								.list();
			if(null != mapList){
				for(Map<String,Object> map:mapList){
					String animalCode = (String) map.get("animalCode");
					String visceraFixedSessionId = (String) map.get("visceraFixedSessionId");
					Integer autolyzeFlag = (Integer) map.get("autolyzeFlag");
					String sessionFinishSign = (String) map.get("sessionFinishSign");
					if(null != autolyzeFlag && autolyzeFlag == 1){
						//自溶了
						continue;
					}else if(null == visceraFixedSessionId || "".equals(visceraFixedSessionId)){
						json.setMsg("动物（"+animalCode+"）脏器未固定！");
						return json;
					}else if(null == sessionFinishSign || "".equals(sessionFinishSign)){
						json.setMsg("动物（"+animalCode+"）脏器固定会话未签字确认！");
						return json;
					}
				}
				json.setSuccess(true);
			}else{
				json.setMsg("申请列表为空！");
			}
		}else{
			json.setMsg("任务编号为空！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Json checkVisceraWeigh(String taskId) {
		// 检查动物 称重情况
		Json json = new Json();
		if(null != taskId){
			//申请的动物号，会话Id，签字
			List<Map<String,Object>> mapList = null;
			String sql = "select reqanimal.animalCode,anatomyanimal.autolyzeFlag,anatomyanimal.visceraWeightSessionId,pathsession.sessionFinishSign"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqanimal"+
						" 	on task.studyNo = reqanimal.studyNo and task.anatomyReqNo = reqanimal.anatomyReqNo"+
						" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal"+
						" 	on reqanimal.studyNo = anatomyanimal.studyNo and reqanimal.animalCode = anatomyanimal.animalCode"+
						" 	left join CoresStudy.dbo.tblPathSession as pathsession "+
						" 	on anatomyanimal.visceraWeightSessionId = pathsession.sessionId"+
						" where task.taskId=:taskId " +
						"  and reqanimal.cancelFlag = 0 ";
			mapList = getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setResultTransformer(new MapResultTransformer())
								.list();
			if(null != mapList){
				for(Map<String,Object> map:mapList){
					String animalCode = (String) map.get("animalCode");
					String visceraWeightSessionId = (String) map.get("visceraWeightSessionId");
					Integer autolyzeFlag = (Integer) map.get("autolyzeFlag");
					String sessionFinishSign = (String) map.get("sessionFinishSign");
					if(null != autolyzeFlag && autolyzeFlag == 1){
						//自溶了
						continue;
					}else if(null == visceraWeightSessionId || "".equals(visceraWeightSessionId)){
						json.setMsg("动物（"+animalCode+"）脏器未称重！");
						return json;
					}else if(null == sessionFinishSign || "".equals(sessionFinishSign)){
						json.setMsg("动物（"+animalCode+"）脏器称重会话未签字确认！");
						return json;
					}
				}
				json.setSuccess(true);
			}else{
				json.setMsg("申请列表为空！");
			}
		}else{
			json.setMsg("任务编号为空！");
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	public Json checkAnimalAnatomy(String taskId) {
		// 检查动物 解剖情况
		Json json = new Json();
		if(null != taskId){
			//申请的动物号，会话Id，签字
			List<Map<String,Object>> mapList = null;
			String sql = "select reqanimal.animalCode,anatomyanimal.anatomySessionId,pathsession.sessionFinishSign"+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyReqAnimalList as reqanimal"+
						" 	on task.studyNo = reqanimal.studyNo and task.anatomyReqNo = reqanimal.anatomyReqNo"+
						" 	left join CoresStudy.dbo.tblAnatomyAnimal as anatomyanimal"+
						" 	on reqanimal.studyNo = anatomyanimal.studyNo and reqanimal.animalCode = anatomyanimal.animalCode"+
						" 	left join CoresStudy.dbo.tblPathSession as pathsession "+
						" 	on anatomyanimal.anatomySessionId = pathsession.sessionId"+
						" where task.taskId=:taskId " +
						"  and reqanimal.cancelFlag = 0 ";
			mapList = getSession().createSQLQuery(sql)
								.setParameter("taskId", taskId)
								.setResultTransformer(new MapResultTransformer())
								.list();
			if(null != mapList){
				for(Map<String,Object> map:mapList){
					String animalCode = (String) map.get("animalCode");
					String anatomySessionId = (String) map.get("anatomySessionId");
					String sessionFinishSign = (String) map.get("sessionFinishSign");
					if(null == anatomySessionId || "".equals(anatomySessionId)){
						json.setMsg("动物（"+animalCode+"）未解剖！");
						return json;
					}else if(null == sessionFinishSign || "".equals(sessionFinishSign)){
						json.setMsg("动物（"+animalCode+"）解剖会话未签字确认！");
						return json;
					}
				}
				json.setSuccess(true);
			}else{
				json.setMsg("申请列表为空！");
			}
		}else{
			json.setMsg("任务编号为空！");
		}
		return json;
	}

	public boolean hasFixedWeigh(String taskId) {
		String sql = "select visceraweigh.visceraNum "+
		" from CoresStudy.dbo.tblAnatomyTask as task left join"+
		" 	(select weigh.studyNo,weigh.reqNo,count(*) as visceraNum"+
		" 		from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh"+
		" 		where weigh.fixedWeighFlag = 1"+
		" 		group by weigh.studyNo,weigh.reqNo) as visceraweigh"+
		" 	on task.studyNo = visceraweigh.studyNo and task.anatomyReqNo = visceraweigh.reqNo"+
		" where task.taskId=:taskId";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("taskId", taskId)
									.uniqueResult();
		if(null == count || count == 0){
			return false;
		}else{
			return true;
		}
	}
	public boolean hasWeigh(String taskId) {
		String sql = "select visceraweigh.visceraNum "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join"+
					" 	(select weigh.studyNo,weigh.reqNo,count(*) as visceraNum"+
					" 		from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh"+
					" 		where weigh.fixedWeighFlag = 0"+
					" 		group by weigh.studyNo,weigh.reqNo) as visceraweigh"+
					" 	on task.studyNo = visceraweigh.studyNo and task.anatomyReqNo = visceraweigh.reqNo"+
					" where task.taskId=:taskId";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("taskId", taskId)
									.uniqueResult();
		if(null == count || count == 0){
			return false;
		}else{
			return true;
		}
	}

	public boolean hasFixed(String taskId) {
		String sql = "select reqplancheck.Num "+
					" from CoresStudy.dbo.tblAnatomyTask as task left join"+
					" 	(select plancheck.studyNo,plancheck.reqNo,count(*) as Num"+
					" 	from CoresStudy.dbo.tblAnatomyReqPathPlanCheck as plancheck"+
					" 	where plancheck.visceraFixedFlag = 1"+
					" 	group by plancheck.studyNo,plancheck.reqNo) as reqplancheck"+
					" 	on task.studyNo = reqplancheck.studyNo and task.anatomyReqNo = reqplancheck.reqNo"+
					" where task.taskId=:taskId";
			Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("taskId", taskId)
									.uniqueResult();
			if(null == count || count == 0){
			return false;
			}else{
			return true;
			}
	}


	public void sign(String taskId, String itemName,String signer) {
		if(null != taskId && null != itemName && null != signer){
			TblAnatomyTask tblAnatomyTask = getById(taskId);
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String esId = tblESService.getKey("TblES");
			es.setDateTime(new Date());
			es.setEsId(esId);
			es.setSigner(signer);
			esLink.setTableName("TblAnatomyTask");
			esLink.setDataId(taskId);
			esLink.setTblES(es);
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			if(itemName.equals("解剖")){
				tblAnatomyTask.setAnatomyCheckFinishSign(esId);
				es.setEsType(703);
				es.setEsTypeDesc("解剖完成签字");
				esLink.setEsType(703);
				esLink.setEsTypeDesc("解剖完成签字");
			}else if(itemName.equals("固定后称重")){
				tblAnatomyTask.setVisceraFixedWeightFinishSign(esId);
				es.setEsType(704);
				es.setEsTypeDesc("固定后称重完成签字");
				esLink.setEsType(704);
				esLink.setEsTypeDesc("固定后称重完成签字");
			}
			tblESService.save(es);
			tblESLinkService.save(esLink);
			getSession().update(tblAnatomyTask);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoAnatomyAnimal(String taskId) {
		String sql="select animalCode from CoresStudy.dbo.tblAnatomyReqAnimalList as ara "+
					"  join CoresStudy.dbo.tblAnatomyTask as at "+
					"  on at.anatomyReqNo=ara.anatomyReqNo and at.studyNo=ara.studyNo "+
					"  where at.taskId=:taskId and ara.cancelFlag = 0 and ara.animalCode not in "+
					"  (select animalCode  "+
					"       from CoresStudy.dbo.tblAnatomyAnimal  "+
					"	   where taskId=:taskId) "+
					"  order by animalCode";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		                               .setParameter("taskId", taskId)
		                               .setResultTransformer(new MapResultTransformer())
		                               .list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoAnatomyAnimals(String taskId) {
		List<String> taskList = new ArrayList<String>();
		for(String str:taskId.split(","))
		{
			taskList.add(str);
		}
		
		String sql="select animalCode from CoresStudy.dbo.tblAnatomyReqAnimalList as ara "+
					"  join CoresStudy.dbo.tblAnatomyTask as at "+
					"  on at.anatomyReqNo=ara.anatomyReqNo and at.studyNo=ara.studyNo "+
					"  where at.taskId in (:taskId) and ara.cancelFlag = 0 and ara.animalCode not in "+
					"  (select animalCode  "+
					"       from CoresStudy.dbo.tblAnatomyAnimal  "+
					"	   where taskId in (:taskId) ) "+
					"  order by animalCode";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		                               .setParameterList("taskId", taskList)
		                               .setResultTransformer(new MapResultTransformer())
		                               .list();
		return list;
	}

	public boolean isVisceraWeighFinish(String taskId, String animalCode) {
		if(null != taskId && null != animalCode){
			String sql = "select a.visceraWeighFinishFlag"+
						" from CoresStudy.dbo.tblAnatomyAnimal as a"+
						" where a.taskId = :taskId and a.animalCode = :animalCode";
			Integer visceraWeighFinishFlag = (Integer) getSession().createSQLQuery(sql)
															.setParameter("taskId", taskId)
															.setParameter("animalCode", animalCode)
															.uniqueResult();
			if(null != visceraWeighFinishFlag && visceraWeighFinishFlag == 1){
				return true;
			}else{
				String sql2= "select count(weigh.id)"+
							" from CoresStudy.dbo.tblAnatomyReqVisceraWeigh as weigh left join "+
							"      CoresStudy.dbo.tblAnatomyTask as task on weigh.studyNo = task.studyNo "+
							" 	 and weigh.reqNo = task.anatomyReqNo"+
							" where task.taskId = :taskId and weigh.fixedWeighFlag = 0";
				Integer count = (Integer) getSession().createSQLQuery(sql2)
													.setParameter("taskId", taskId)
													.uniqueResult();
				if(null != count && count == 0){
					return true;
				}
			}
		}
		return false;
	}

	public Boolean isExistByStudyNoReqNo(String studyNo, int reqNo) {
		String sql = "select count(task.taskId)" +
				"from  CoresStudy.dbo.tblAnatomyTask as task " +
				" where task.studyNo = ? and task.anatomyReqNo = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
											.setParameter(0, studyNo)
											.setParameter(1, reqNo)
											.uniqueResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNo(String studyNo) {
		String sql = "select task.taskId,task.anatomyRsn,convert(varchar(10),task.taskCreateTime,120) as createDate"+
					" 	,convert(varchar(10),req.beginDate,120) as beginDate,"+
					" 	convert(varchar(10),req.endDate,120) as endDate,u.realName as creator,animal2.animalNum"+
					" from CoresStudy.dbo.tblAnatomyTask task left join CoresStudy.dbo.tblAnatomyReq as req "+
					" 	on task.anatomyReqNo = req.reqNo and task.studyNo = req.studyNo left join CoresUserPrivilege.dbo.tbluser as u"+
					" 	on task.taskCreater = u.userName  left join "+
					" 	(	select animal.studyNo,animal.anatomyReqNo,count(*) as animalNum"+
					" 		from CoresStudy.dbo.tblAnatomyReqAnimalList as animal"+
					" 		where animal.studyNo = :studyNo "+
					" 		group by animal.studyNo,animal.anatomyReqNo) as animal2 "+
					" 	on task.studyNo = animal2.studyNo and task.anatomyReqNo = animal2.anatomyReqNo"+
					" where task.studyNo = :studyNo ";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		        .setParameter("studyNo", studyNo)
		        .setResultTransformer(new MapResultTransformer())
		        .list();
		return list;
	}

	public TblAnatomyTask getByStudyNoReqNo(String studyNo, int reqNo) {
		String hql = "from TblAnatomyTask where studyNo = ? and anatomyReqNo = ?";
		TblAnatomyTask tblAnatomyTask = (TblAnatomyTask) getSession().createQuery(hql)
													.setParameter(0, studyNo)
													.setParameter(1, reqNo)
													.uniqueResult();
		return tblAnatomyTask;
	}

	public boolean isWeightByTaskId(String taskId) {
		String sql = "select count(s.sessionId)"+
					" from CoresStudy.dbo.tblpathsession as s join CoresStudy.dbo.tblVisceraWeight as w"+
					" 	on s.sessionId = w.sessionId"+
					" where s.taskId = :taskId";
		Integer count = (Integer) getSession().createSQLQuery(sql).setParameter("taskId", taskId).uniqueResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoSelectAnimalByStudyNo(String studyNo) {
		String sql = "select a.animalCode,a.gender,do.dosageNum,do.dosageDesc"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a left join CoresStudy.dbo.tblDoseSetting as do"+
					" 	on a.studyNo = do.studyNo and a.groupId = do.dosageNum"+
					" where a.studyNo = :studyNo and a.animalCode not in"+
					" 	("+
					" 		select ani.animalCode"+
					" 		from CoresStudy.dbo.tblAnatomyReq as req join CoresStudy.dbo.tblAnatomyReqAnimalList as ani"+
					" 			on req.studyNo = ani.studyNo and req.reqNo = ani.anatomyReqNo"+
					" 		where req.studyNo = :studyNo and req.submitFlag > 0 and ani.cancelFlag = 0"+
					" 		union"+
					" 		select ani.animalCode"+
					" 		from CoresStudy.dbo.tblAnatomyTask as task  join CoresStudy.dbo.tblAnatomyAnimal as ani"+
					" 			on task.studyNo = ani.studyNo and task.taskId = ani.taskId"+
					" 		where task.studyNo = :studyNo and task.tempFlag = 2"+
					" 	)";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		        .setParameter("studyNo", studyNo)
		        .setResultTransformer(new MapResultTransformer())
		        .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSelectAnimalByStudyNoAnatomyPlanNum(
			String studyNo, int i) {
		String sql = "select a.animalCode,a.gender,do.dosageNum,do.dosageDesc"+
		" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a left join CoresStudy.dbo.tblDoseSetting as do"+
		" 	on a.studyNo = do.studyNo and a.groupId = do.dosageNum"+
		" where a.studyNo = :studyNo and a.dissectNum = :anatomyPlanNum and a.animalCode not in"+
		" 	("+
		" 		select ani.animalCode"+
		" 		from CoresStudy.dbo.tblAnatomyReq as req join CoresStudy.dbo.tblAnatomyReqAnimalList as ani"+
		" 			on req.studyNo = ani.studyNo and req.reqNo = ani.anatomyReqNo"+
		" 		where req.studyNo = :studyNo and req.submitFlag > 0 and ani.cancelFlag = 0"+
		" 		union"+
		" 		select ani.animalCode"+
		" 		from CoresStudy.dbo.tblAnatomyTask as task  join CoresStudy.dbo.tblAnatomyAnimal as ani"+
		" 			on task.studyNo = ani.studyNo and task.taskId = ani.taskId"+
		" 		where task.studyNo = :studyNo and task.tempFlag = 2"+
		" 	)";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		    .setParameter("studyNo", studyNo)
		    .setParameter("anatomyPlanNum", i)
		    .setResultTransformer(new MapResultTransformer())
		    .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSelectAnimalByTaskId(String taskId) {
		String sql = "select ani.animalCode,ani.gender,ani.groupId as dosageNum,do.dosageDesc,convert(varchar(10),ani.anatomyBeginTime,120) as anatomyDate," +
				"	convert(varchar(10),ani.deadDate,120) as deadDate"+
					" from CoresStudy.dbo.tblAnatomyTask as task  join CoresStudy.dbo.tblAnatomyAnimal as ani"+
					" 	on task.studyNo = ani.studyNo and task.taskId = ani.taskId	join CoresStudy.dbo.tblDoseSetting as do"+
					" 	on ani.studyNo = do.studyNo and ani.groupId = do.dosageNum"+
					" where task.taskId = :taskId and task.tempFlag = 2";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		        .setParameter("taskId", taskId)
		        .setResultTransformer(new MapResultTransformer())
		        .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getAnatomyPlanNumListByStudyNo(String studyNo) {
		String sql = "select dissectNum"+
		" from CoresStudy.dbo.tblDissectPlan as d"+
		" where d.studyNo = :studyNo "+
		" order by dissectNum";
		List<Integer> list = getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Json checkAnatomyOrWeigh(String studyNo,
			List<String> selectAnimalCodeList) {
		Json json = new Json();
		String sql = "select distinct ani.animalCode"+
		" from CoresStudy.dbo.tblAnatomyAnimal as ani left join CoresStudy.dbo.TblAnatomyCheck as ch"+
		" 	on ani.studyNo = ch.studyNo and ani.animalCode = ch.animalCode left join "+
		" 	CoresStudy.dbo.tblVisceraWeight as we on ani.studyNo = we.studyNo and ani.animalCode = "+
		" 	we.animalCode"+
		" where ani.studyNo = :studyNo and ani.animalCode in (:selectAnimalCodeList) and"+
		" 	(ani.autolyzeFlag = 1 or ch.id is not null or we.id is not null)";
		List<String> animalCodeList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNo)
													.setParameterList("selectAnimalCodeList", selectAnimalCodeList)
													.list();
		if(null != animalCodeList && animalCodeList.size() > 0){
			json.setSuccess(false);
			String msg = animalCodeList.get(0)+" 已解剖，不可以删除！";
			json.setMsg(msg);
		}else{
			json.setSuccess(true);
		}
		return json;
	}

	public String saveAll(TblAnatomyTask task,
			List<TblAnatomyAnimal> tblAnatomyAnimalList) {
		//解剖任务
		String taskId = getKey("TblAnatomyTask");
		task.setTaskId(taskId);
		int anatomyNum = getNextAnatomyNumByStudyNo(task.getStudyNo());
		task.setAnatomyNum(anatomyNum);
		task.setTaskCreateTime(new Date());
		
		for(TblAnatomyAnimal obj:tblAnatomyAnimalList){
			String id = getKey("TblAnatomyAnimal");
			obj.setId(id);
			obj.setTaskId(taskId);
		}
		
		//2.保存
		getSession().save(task);
		for(TblAnatomyAnimal obj:tblAnatomyAnimalList){
			getSession().save(obj);
		}
		return taskId;
	}

	@SuppressWarnings("unchecked")
	public List<String> getStudyNoListHasAdditionalTask() {
		String sql = "select distinct studyNo"+
					" from CoresStudy.dbo.tblAnatomyTask"+
					" where tempFlag = 2"+
					" order by studyNo";
		List<String> studyNoList = getSession().createSQLQuery(sql).list();
		return studyNoList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdditionalTaskMapListByStudyNo(
			String studyNo) {
		String sql = "select task.taskId,task.anatomyRsn,convert(varchar(10),ani.anatomyBeginTime,120) as anatomyDate,"+
		" 	isnull(es.signer,'') as signer,count(task.taskId) as animalNum"+
		" from CoresStudy.dbo.tblAnatomyTask as task left join CoresStudy.dbo.tblAnatomyAnimal as ani"+
		" 	on task.taskId = ani.taskId left join CoresUserPrivilege.dbo.tblES as es on "+
		" 	task.anatomyCheckFinishSign = es.esId"+
		" where task.studyNo = :studyNo and task.tempFlag = 2"+
		" group by task.taskId,task.anatomyRsn,ani.anatomyBeginTime,es.signer";
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNo", studyNo)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	public void updateAll(TblAnatomyTask task,
			List<TblAnatomyAnimal> tblAnatomyAnimalList) {
		//解剖任务
		String taskId = task.getTaskId();
		//1.查询该任务线，原自溶动物列表
		List<String> animalCodeList = getAutolyzeAnimalCodeListByTaskId(taskId);
		for(TblAnatomyAnimal obj:tblAnatomyAnimalList){
			String id = getKey("TblAnatomyAnimal");
			obj.setId(id);
			obj.setTaskId(taskId);
			//设置自溶标记
			if(null != animalCodeList && animalCodeList.contains(obj.getAnimalCode())){
				obj.setAutolyzeFlag(1);
			}
		}
		//2.删除原动物列表
		String sql = "delete CoresStudy.dbo.tblAnatomyAnimal "+
					" from CoresStudy.dbo.tblAnatomyAnimal as ani"+
					" where ani.taskId = ? ";
		getSession().createSQLQuery(sql).setParameter(0, taskId).executeUpdate();
		//3.更新、保存
		getSession().update(task);
		for(TblAnatomyAnimal obj:tblAnatomyAnimalList){
			getSession().save(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	private List<String> getAutolyzeAnimalCodeListByTaskId(String taskId) {
		String sql = "select ani.animalCode"+
					" from CoresStudy.dbo.tblAnatomyAnimal as ani"+
					" where ani.taskId = ? and ani.autolyzeFlag = 1";
		List<String> animalCodeList = getSession().createSQLQuery(sql).setParameter(0, taskId).list();
		
		return animalCodeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNoSelectAnimalByStudyNoTaskId(
			String studyNo, String taskId) {
		String sql = "select a.animalCode,a.gender,do.dosageNum,do.dosageDesc"+
				" from CoresStudy.dbo.tblAnimalDetailDissectPlan as a left join CoresStudy.dbo.tblDoseSetting as do"+
				" 	on a.studyNo = do.studyNo and a.groupId = do.dosageNum"+
				" where a.studyNo = :studyNo and a.animalCode not in"+
				" 	("+
				" 		select ani.animalCode"+
				" 		from CoresStudy.dbo.tblAnatomyReq as req join CoresStudy.dbo.tblAnatomyReqAnimalList as ani"+
				" 			on req.studyNo = ani.studyNo and req.reqNo = ani.anatomyReqNo"+
				" 		where req.studyNo = :studyNo and req.submitFlag > 0 and ani.cancelFlag = 0"+
				" 		union"+
				" 		select ani.animalCode"+
				" 		from CoresStudy.dbo.tblAnatomyTask as task  join CoresStudy.dbo.tblAnatomyAnimal as ani"+
				" 			on task.studyNo = ani.studyNo and task.taskId = ani.taskId"+
				" 		where task.studyNo = :studyNo and task.tempFlag = 2 and task.taskId != :taskId "+
				" 	)";
		List<Map<String, Object>> list=getSession().createSQLQuery(sql)
		    .setParameter("studyNo", studyNo)
		    .setParameter("taskId", taskId)
		    .setResultTransformer(new MapResultTransformer())
		    .list();
		return list;
	}

	public void additionalTaskSign(String taskId, String signer) {
		if(null != taskId && null != signer){
			TblAnatomyTask tblAnatomyTask = getById(taskId);
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String esId = tblESService.getKey("TblES");
			es.setDateTime(new Date());
			es.setEsId(esId);
			es.setSigner(signer);
			esLink.setTableName("TblAnatomyTask");
			esLink.setDataId(taskId);
			esLink.setTblES(es);
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				es.setEsType(710);
				es.setEsTypeDesc("补录解剖数据");
				esLink.setEsType(710);
				esLink.setEsTypeDesc("补录解剖数据");
			tblESService.save(es);
			tblESLinkService.save(esLink);
			
			tblAnatomyTask.setAnatomyCheckFinishSign(esId);
			getSession().update(tblAnatomyTask);
			
			//更新动物表中  ，解剖完成标识   、称重完成标识
			String sql = "update CoresStudy.dbo.tblAnatomyAnimal "+
						"  set anatomyCheckFinishFlag = 1,visceraWeighFinishFlag = 1"+
						" from CoresStudy.dbo.tblAnatomyAnimal as ani"+
						" where ani.taskId = ? ";
			getSession().createSQLQuery(sql).setParameter(0, taskId).executeUpdate();
		}
		
	}
	
	public void additionalTaskSign(String taskId, String signer,String peerSigner) {
		if(null != taskId && null != signer && null != peerSigner){
			TblAnatomyTask tblAnatomyTask = getById(taskId);
			//签名链接
			TblESLink esLink0 = new TblESLink();
			//电子签名
			TblES es0 = new TblES();
			String esId0 = tblESService.getKey("TblES");
			es0.setDateTime(new Date());
			es0.setEsId(esId0);
			es0.setSigner(signer);
			esLink0.setTableName("TblAnatomyTask");
			esLink0.setDataId(taskId);
			esLink0.setTblES(es0);
			esLink0.setRecordTime(new Date());
			esLink0.setLinkId(tblESLinkService.getKey("TblESLink"));
				es0.setEsType(710);
				es0.setEsTypeDesc("补录解剖数据");
				esLink0.setEsType(710);
				esLink0.setEsTypeDesc("补录解剖数据");
			tblESService.save(es0);
			tblESLinkService.save(esLink0);
			
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String esId = tblESService.getKey("TblES");
			es.setDateTime(new Date());
			es.setEsId(esId);
			es.setSigner(peerSigner);
			esLink.setTableName("TblAnatomyTask");
			esLink.setDataId(taskId);
			esLink.setTblES(es);
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				es.setEsType(711);
				es.setEsTypeDesc("补录解剖数据复核");
				esLink.setEsType(711);
				esLink.setEsTypeDesc("补录解剖数据复核");
			tblESService.save(es);
			tblESLinkService.save(esLink);
			
			tblAnatomyTask.setAnatomyCheckFinishSign(esId0);
			tblAnatomyTask.setPeerAnatomyCheckFinishSign(esId);
			getSession().update(tblAnatomyTask);
			
			//更新动物表中  ，解剖完成标识   、称重完成标识
			String sql = "update CoresStudy.dbo.tblAnatomyAnimal "+
						"  set anatomyCheckFinishFlag = 1,visceraWeighFinishFlag = 1"+
						" from CoresStudy.dbo.tblAnatomyAnimal as ani"+
						" where ani.taskId = ? ";
			getSession().createSQLQuery(sql).setParameter(0, taskId).executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByStudyNo(String studyNo) {
		List<Map<String,Object>> mapList = null;
		if(null != studyNo && !"".equals(studyNo) ){
			String sql = "select task.studyNo,task.anatomyNum,u.realName as taskCreater,convert(varchar(10),task.taskCreateTime,120) as taskCreateTime ,taskId," +
						" 	viewstudy.sd as studydirector,convert(varchar(10),req.beginDate,120) as planBeginDate "+
						" from CoresStudy.dbo.tblAnatomyTask as task left join CoresUserPrivilege.dbo.tbluser as u"+
						" 	on task.taskCreater = u.userName " +
						" 	left join CoresStudy.dbo.tblStudyPlan as study on task.studyNo = study.studyNo " +
						" 	left join CoresStudy.dbo.tblAnatomyReq as req on req.studyNo = task.studyNo and req.reqNo = task.anatomyReqNo " +
						" left join CoresStudy.dbo.view_studyNoSD as viewstudy on viewstudy.studyNo = study.studyNo"+
						" where task.studyNo = :studyNo and task.tempFlag != 2 "+
						" 	order by task.taskCreateTime desc";
			mapList = getSession().createSQLQuery(sql)
									.setParameter("studyNo", studyNo)
									.setResultTransformer(new MapResultTransformer())
									.list();
		}
		return mapList;
	}

}
