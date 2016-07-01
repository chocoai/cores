package com.lanen.view.action.schdeule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblStudyRes;
import com.lanen.model.schedule.TblTOLeader;
import com.lanen.model.schedule.TblTOLeaderJson;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyResService;
import com.lanen.service.schdeule.TblTOLeaderService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 任务操作
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class TblTOLeaderAction extends BaseAction<TblTOLeader> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	@Resource
	private TblTOLeaderService tblTOLeaderService;
	
	@Resource
	private TblStudyResService tblStudyResService;
	
	@Resource
	private TblResManagerService tblResManagerService;
	
	@Resource
	private UserService UserService;

	@Resource
	private TblESService tblESService;

	@Resource
	private TblESLinkService tblESLinkService;

	private String scheduleIds;

	private String resMans;

	private String studyNo;
	
	private String userNamesStr;
	
	private String ids;
	
	public String addUI() {
		String[] array = null;
		if (scheduleIds.contains(",")) {
			array = scheduleIds.split(",");
		}
		if (null == array) {
			if (null != scheduleIds && !scheduleIds.equals("")) {
				TblSchedulePlan schedule = tblSchedulePlanService.getById(scheduleIds);
				String tOLeader = "";
				List<TblTOLeader> list2 = tblTOLeaderService.getByLeaderList(schedule.getScheduleID());
				for (TblTOLeader obj : list2) {
					if (tOLeader != "") {
						tOLeader = tOLeader + ",";
					}
					tOLeader = tOLeader + UserService.getRealNameByUserName(obj.gettOLeader());
				}
				ActionContext.getContext().put("taskName",schedule.getTaskName());
				ActionContext.getContext().put("scheduleId", scheduleIds);
				ActionContext.getContext().put("tOLeader", tOLeader);
			}
		} else {
			String name = "";
			String scheduleId = "";
			for (int i = 0; i < array.length; i++) {
				TblSchedulePlan schedule = tblSchedulePlanService.getById(array[i]);
				String tOLeader = "";
				List<TblTOLeader> list2 = tblTOLeaderService.getByLeaderList(schedule.getScheduleID());
				for (TblTOLeader obj : list2) {
					if (tOLeader != "") {
						tOLeader = tOLeader + ",";
					}
					tOLeader = tOLeader +UserService.getRealNameByUserName(obj.gettOLeader());
				}

				if (name != "") {
					name = name + ",";
				}
				if (scheduleId != "") {
					scheduleId = scheduleId + ",";
				}
				name = name + schedule.getTaskName();
				scheduleId = scheduleId + schedule.getScheduleID();

				ActionContext.getContext().put("taskName", name);
				ActionContext.getContext().put("scheduleId", scheduleId);
				ActionContext.getContext().put("tOLeader", tOLeader);
			}
		}
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(nowDate);
		ActionContext.getContext().put("nowDay", str);
		return "addUI";
	}

	public void addAllTOLeader() {
		String[] array = null;
		if (scheduleIds.contains(",")) {
			array = scheduleIds.split(",");
		} else {
			array = new String[1];
			array[0] = scheduleIds;
		}
		List<User> newlist = new ArrayList<User>();
		int taskKind;
			for (int i = 0; i < array.length; i++) {
				// 查询这个日程
				TblSchedulePlan schedule = tblSchedulePlanService.getById(array[i]);
				String scheduleId = schedule.getScheduleID();// 日程ID
				taskKind = schedule.getTaskKind();
				List<TblTOLeader> list = tblTOLeaderService.getByLeaderList(scheduleId);
				if(taskKind == 1){
					List<User> userlist= userService.findByPrivilegeName("日程管理-列表-委托管理");
					for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 2){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-动物试验");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 3){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-临床检验");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 4){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-毒性病理");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 5){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-QA管理");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 6){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-供试品管理");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 7){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-分析");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}else if(taskKind == 8){
		 			List<User> userlist= userService.findByPrivilegeName("日程管理-列表-生态毒理");
		 			for (User entry : userlist) {
						User user2 = new User();
						user2.setId(entry.getId());
						user2.setRealName(entry.getRealName());
					for (TblTOLeader obj : list) {
						if (obj.gettOLeader().equals(entry.getId())) {
							user2.setRemark("1");
						}
					}newlist.add(user2);
					}
		 		}
				
				
			}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", newlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}

	/**房间负责人*/
	public void addTOLeader() {
		List<User> newlist = new ArrayList<User>();
		String[] array = null;
		if (scheduleIds.contains(",")) {
			array = scheduleIds.split(",");
		} else {
			array = new String[1];
			array[0] = scheduleIds;
		}
		for (int i = 0; i < array.length; i++) {
			// 查询这个日程
			TblSchedulePlan schedule = tblSchedulePlanService.getById(array[i]);
			String studyNo = schedule.getTaskCode();// 课题编号
			String scheduleId = schedule.getScheduleID();// 日程ID
			List<TblTOLeader> TOLlist = tblTOLeaderService.getByLeaderList(scheduleId);// 根据日程ID查询，任务负责人
			List<TblStudyRes> studyRes = tblStudyResService.getByStudyNo(studyNo);// 根据专题编号查询专题所在的资源
			List<TblResManager> list = tblResManagerService.getByHouseId(studyRes.get(0).getResId());// 查询资源的负责人的用户名
			for (TblResManager manager : list) {
				User user2 = new User();
				boolean falg = true;
				user2.setId(manager.getResManager());
				user2.setRealName(UserService.getRealNameByUserName(manager.getResManager()));
				for (User obj1 : newlist) {
					if (obj1.getId().equals(manager.getResManager())) {
						falg = false;
					}
				}
				for (TblTOLeader obj1 : TOLlist) {
					if (obj1.gettOLeader().equals(manager.getResManager())) {
						user2.setRemark("1");
					}
				}
				if (falg) {
					newlist.add(user2);
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", newlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	
	/**楼层负责人*/
	public void addFloorTOLeader() {
		List<User> newlist = new ArrayList<User>();
		String[] array = null;
		if (scheduleIds.contains(",")) {
			array = scheduleIds.split(",");
		} else {
			array = new String[1];
			array[0] = scheduleIds;
		}
		for (int i = 0; i < array.length; i++) {
			// 查询这个日程
			TblSchedulePlan schedule = tblSchedulePlanService.getById(array[i]);
			String studyNo = schedule.getTaskCode();// 课题编号
			String scheduleId = schedule.getScheduleID();// 日程ID
			List<TblTOLeader> TOLlist = tblTOLeaderService.getByLeaderList(scheduleId);// 根据日程ID查询，任务负责人
			List<TblStudyRes> studyRes = tblStudyResService.getByStudyNo(studyNo);// 根据专题编号查询专题所在的资源
			//资源id
			String rid = studyRes.get(0).getResId();
			//根据资源id找寻父类（楼层）
			TblAnimalHouse animalHouse = tblAnimalHouseService.getById(rid);
			List<TblResManager> list = tblResManagerService.getByHouseId(animalHouse.getParentId());// 查询资源的负责人的用户名
			for (TblResManager manager : list) {
				User user2 = new User();
				boolean falg = true;
				user2.setId(manager.getResManager());
				user2.setRealName(UserService.getRealNameByUserName(manager.getResManager()));
				for (User obj1 : newlist) {
					if (obj1.getId().equals(manager.getResManager())) {
						falg = false;
					}
				}
				for (TblTOLeader obj1 : TOLlist) {
					if (obj1.gettOLeader().equals(manager.getResManager())) {
						user2.setRemark("1");
					}
				}
				if (falg) {
					newlist.add(user2);
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", newlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void add() {
		String[] array = null;
		/**
		 * 查询的负责人的数组
		 */
		if (resMans.contains(",")) {
			array = resMans.split(",");
		} else {
			array = new String[1];
			array[0] = resMans;
		}
		/**
		 * 查询的负责任务的ID数组
		 */
		List<TblTOLeader> tolist = tblTOLeaderService.getByLeaderList(model.getScheduleId());
		List<TblTOLeader> list = new ArrayList<TblTOLeader>();
		for (int j = 0; j < array.length; j++) {
			TblTOLeader leader = new TblTOLeader();
			leader.setScheduleId(model.getScheduleId());
			leader.setStartDate(model.getStartDate());
			leader.setEndDate(model.getEndDate());
			leader.settOLeader(array[j]);
			list.add(leader);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		boolean falg = true;
		for (TblTOLeader obj : list) {// 刚添加的人
			for (TblTOLeader tol : tolist) {// 已存在的人员
				// 如果刚添加
				// 的人员和已存在的人员重复，则比较已存在的人员结束时期是否有，没有的话的则不能添加，有的话则比较刚添加的开始日期，和已结束的结束日期
				if (tol.gettOLeader().equals(obj.gettOLeader())
						&& ((null == tol.getEndDate()) || (tol.getEndDate() != null && obj
								.getStartDate().before(tol.getEndDate())))) {
					falg = false;
					String name = UserService.getRealNameByUserName(obj.gettOLeader());
					map.put("msg", name);
					break;

				}
			}
		}
		if (falg) {
			tblTOLeaderService.saveAllLeaderList(list);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
   
    /**任务负责人签字确认*/
	public void signTOLeader() {
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(403);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("任务负责人签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		TblTOLeader tOLeader = tblTOLeaderService.getById(model.getId());
		tOLeader.setSignId(esId);
		tOLeader.setEndDate(model.getEndDate());
		Date endDate = tOLeader.getEndDate();
		if (null != endDate) {
			tOLeader.setEndDateSignId(esId);
		}
		esLink.setTableName("TblTOLeader");
		esLink.setDataId(model.getScheduleId());
		esLink.setTblES(es);
		esLink.setEsType(403);
		esLink.setEsTypeDesc("任务负责人签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		
		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			tblTOLeaderService.update(tOLeader);
			// 日志录入
			writeLog("签字", "任务负责人签字确认，签字");
			json.setMsg("签字成功");
		}catch(Exception e){
		     json.setSuccess(false);
		     json.setMsg("与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
		
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
    /**跳转至编辑页面*/
	public String editUI() {
		ActionContext.getContext().put("scheduleId", scheduleIds);
		TblSchedulePlan schedule = tblSchedulePlanService.getById(scheduleIds);
		String taskName = schedule.getTaskName();
		ActionContext.getContext().put("taskName", studyNo+":"+taskName);
		ActionContext.getContext().put("studyNo", studyNo);
		ActionContext.getContext().put("taskKind", schedule.getTaskKind());
		return "editUI";
	}

	public void loadListTOL() {
		TblSchedulePlan schedule = tblSchedulePlanService.getById(model.getScheduleId());
		List<TblTOLeader> TOLlist = tblTOLeaderService.getByLeaderList(schedule.getScheduleID());

		List<TblTOLeaderJson> list = new ArrayList<TblTOLeaderJson>();
		for (TblTOLeader tol : TOLlist) {
			TblTOLeaderJson Json = new TblTOLeaderJson();
			Json.setId(tol.getId());
			Json.setScheduleId(tol.getScheduleId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = "";
			String endTime = "";
			if (null != tol.getStartDate()) {
				startTime = sdf.format(tol.getStartDate());
			}
			String endTimeAndName = "";
			if (null != tol.getEndDate()) {
				endTime = sdf.format(tol.getEndDate());
				String esignid = tol.getEndDateSignId();
				if (null != esignid) {
					TblES tblES = tblESService.getById(esignid);
					endTimeAndName = endTime + "(" + tblES.getSigner() + ")";
				}else{
					endTimeAndName = endTime;
				}
				Json.setEndDate(endTimeAndName);
			} else {
				Json.setFinsh("1");
			}

			Json.setStartDate(startTime);
			Json.setTaskName(schedule.getTaskName());
			String name = UserService.getRealNameByUserName(tol.gettOLeader());
			Json.settOLeader(name);
			String signId = tol.getSignId();
			if (null != signId && !signId.equals("")) {
				TblES tblES = tblESService.getById(signId);
				Json.setSignId(tblES.getSigner());
			} 
			list.add(Json);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void delectTOLeader() {
		tblTOLeaderService.delete(model.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**保存全部*/
	public void saveALLTOLeader() throws ParseException {
		// resMans传过来的字符串 tolraderId+A+人员按字符分开
		List<Map<String, String>> tOLList = new ArrayList<Map<String, String>>();
		String[] array = null;
		if (resMans.contains(",")) {
			array = resMans.split(",");
		}

		if (null == array) {
			String[] sol = resMans.split("A");
			// 按字符A截断sol的长度大于1则是含有结束时间，负责结束时间为null
			if (sol.length > 1) {
				Map<String,String> map = new HashMap<String,String>();
				String id = sol[0];
				String endTime = sol[1];
				map.put(id, endTime);
				tOLList.add(map);
			}
		} else {
			for (int i = 0; i < array.length; i++) {
				String[] sol = array[i].split("A");
				if (sol.length > 1) {
					Map<String,String> map = new HashMap<String,String>();
					String id = sol[0];
					String endTime = sol[1];
					map.put(id, endTime);
					tOLList.add(map);
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}
	
	public void setupInvalid(){
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		String esType = "420";
		es.setEsType(Integer.parseInt(esType));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("专题任务操作者设置无效签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		tblESService.save(es);
		
		TblTOLeader toleader = tblTOLeaderService.getById(model.getId());
		toleader.setEndDate(model.getEndDate());
		toleader.setEndDateSignId(esId);
		tblTOLeaderService.update(toleader);
 
        esLink.setTableName("TblTOLeader");
    	esLink.setDataId(model.getId());
    	esLink.setTblES(es);
    	esLink.setEsType(Integer.parseInt(esType));
    	esLink.setEsTypeDesc("专题任务操作者设置无效签字");
    	esLink.setRecordTime(new Date());
    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    	tblESLinkService.save(esLink);
    	// 日志录入
    	writeLog("签字", "专题任务操作者设置无效签字，签字");

		json.setMsg("签字成功");
		json.setSuccess(true);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 签字确认
	 */
	public void signAllTOLeader() {
		String id = resMans;
		Json json = new Json();
		TblTOLeader toleader = tblTOLeaderService.getById(id);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(404);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("任务负责人保存签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		tblESService.save(es);
		toleader.setSignId(esId);
		toleader.setEndDate(model.getEndDate());
		Date endDate = toleader.getEndDate();
		if (null != endDate) {
			toleader.setEndDateSignId(esId);
		}
		esLink.setTableName("TblTOLeader");
		esLink.setDataId(id);
		esLink.setTblES(es);
		esLink.setEsType(404);
		esLink.setEsTypeDesc("任务负责人保存签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		// 日志录入
		tblTOLeaderService.update(toleader);
		writeLog("签字", "任务负责人保存签字确认，签字");
		json.setMsg("签字成功");
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	public void signAll(){
		List<String> singIdList = new ArrayList<String>();
		if(ids.contains(",")){
			String[] strarray = ids.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				singIdList.add(strarray[j]);
			}
		}else{
			singIdList.add(ids);
		}
		Json json = new Json();
		try{
			List<TblTOLeader> list = new ArrayList<TblTOLeader>();
			for (int i = 0; i < singIdList.size(); i++) {
				String id = singIdList.get(i);
				TblTOLeader toleader = tblTOLeaderService.getById(id);
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				// 签名链接
				TblESLink esLink = new TblESLink();
				// 电子签名
				TblES es = new TblES();
				es.setEsType(404);
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("任务负责人保存签字确认");
				es.setDateTime(new Date());
				String esId = tblESService.getKey("TblES");
				es.setEsId(esId);
				tblESService.save(es);
				toleader.setSignId(esId);
				Date endDate = toleader.getEndDate();
				if (null != endDate) {
					toleader.setEndDateSignId(esId);
				}
				list.add(toleader);
				esLink.setTableName("TblTOLeader");
				esLink.setDataId(id);
				esLink.setTblES(es);
				esLink.setEsType(404);
				esLink.setEsTypeDesc("任务负责人保存签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				// 日志录入
				writeLog("签字", "任务负责人保存签字确认，签字");
				json.setMsg("签字成功");
			}
			tblTOLeaderService.updateAll(list);
			json.setSuccess(true);
		}catch(Exception e){
		     json.setSuccess(false);
		     json.setMsg("与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}
	
	
	/**保存资源负责人（add or  edit）*/
	public void save() throws Exception {
		Json json = new Json();
		Date startDate = model.getStartDate();
		//用户名列表
		List<String> userNameList = null;
		if(null != userNamesStr && userNamesStr.length() >= 1 ){
			userNameList = new ArrayList<String>();
			String[] userNames = userNamesStr.split(",");
			for(int j = 0;j < userNames.length;j++){
				userNameList.add(userNames[j]);
			}
		}
		List<String> ids = new ArrayList<String>();
		if(model.getScheduleId() == null ){
			//资源id 为空
			json.setMsg("与服务器交互错误");
		}else if(startDate == null){
			json.setMsg("请选择开始日期");
		}else if(userNameList ==null || userNameList.size()<1){
			json.setMsg("请选择常规任务操作者");
		}else{
			List<TblTOLeader> list = new ArrayList<TblTOLeader>();
			TblTOLeader leader =null;
			for(String userName:userNameList){
				//是否，资源负责人已存在且日期区间重叠
				boolean isExist = tblTOLeaderService.isExist(model.getScheduleId(),userName,startDate,model.getEndDate());
				if(isExist){
					String reamName = userService.getRealNameByUserName(userName);
					json.setMsg(reamName+",该负责人(选择日期区间内)已存在");
					break;
				}else{
					leader  = new TblTOLeader();
					String id = tblTOLeaderService.getKey();
					leader.setId(id);
					leader.settOLeader(userName);
					leader.setScheduleId(model.getScheduleId());
					leader.setStartDate(model.getStartDate());
					leader.setEndDate(model.getEndDate());
					list.add(leader);
					ids.add(id);
				}
				if(!isExist){
					tblTOLeaderService.saveAllLeaderList(list);
					json.setMsg("添加成功");
					json.setSuccess(true);
				}
			}
		}
		json.setObj(ids);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**房间*/
	public void addSOLeader() {
		List<?> list = tblTOLeaderService.studyresresManagersoleader(model.getScheduleId(),studyNo);
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id);
       	 	map.put("text",text);
       	 	mapList.add(map);
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	
	public void addFloorSOLeader() {
		List<?> list = tblTOLeaderService.studyresanimalhouseresManagersoleader(model.getScheduleId(),studyNo);
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id);
       	 	map.put("text",text);
       	 	mapList.add(map);
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	/**楼层*/
	public void selectAllSOLeader() {
        String scheduleId = model.getScheduleId();
        //判断日程类别
        TblSchedulePlan schedulePlan = tblSchedulePlanService.getById(scheduleId);
        int taskKind = schedulePlan.getTaskKind();
		User user1 =(User) ActionContext.getContext().getSession().get("user");
		List<Object> list = new ArrayList<Object>();
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理") && (taskKind == 1)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-委托管理");
			list.addAll(list1);
		} 
 
		//动物试验的可以直接提取所有人
		if(taskKind == 2){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-动物试验");
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")&& (taskKind == 3)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-临床检验");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")&& (taskKind == 4)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-毒性病理");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")&& (taskKind == 5)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-QA管理");
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")&& (taskKind == 6)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-供试品管理");
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")&& (taskKind == 7)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-分析");
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")&& (taskKind == 8)){
			List<?> list1 = tblTOLeaderService.findUserNameRealNameByResIdPrivilegeName(scheduleId,"日程管理-列表-生态毒理");
			list.addAll(list1);
		}
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id1 = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id1);
       	 	map.put("text",text);
	       	boolean falg = true;
	     	for(Map<String,String> obj1:mapList){
	     		if(obj1.equals(map)){
	     			falg = false;
	     			break;
	     		}
	     	}
	     	if(falg){
	     		 mapList.add(map);
	     	}
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);

	}

	/**
	 * 写日志
	 * 
	 * @return
	 */
	private void writeLog(String operatType, String operatContent) {
		// 记录设备登记日志
		TblLog tblLog = new TblLog();
		tblLog.setSystemName(SystemMessage.getSystemName());// 系统名称
		tblLog.setSystemVersion(SystemMessage.getSystemVersion());// 系统版本
		tblLog.setOperatType(operatType);
		tblLog.setOperatOject("日常任务负责人");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}

	public String getScheduleIds() {
		return scheduleIds;
	}

	public void setScheduleIds(String scheduleIds) {
		this.scheduleIds = scheduleIds;
	}

	public String getResMans() {
		return resMans;
	}

	public void setResMans(String resMans) {
		this.resMans = resMans;
	}

	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

	public String getUserNamesStr() {
		return userNamesStr;
	}

	public void setUserNamesStr(String userNamesStr) {
		this.userNamesStr = userNamesStr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	

}
