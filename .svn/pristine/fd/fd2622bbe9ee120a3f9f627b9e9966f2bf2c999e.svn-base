package com.lanen.view.action.studyplan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Columns;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblSchedulePlanHis;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanHisService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyInfoService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.schdeule.TblTaskTypeService;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class TblSchedulePlanAction extends BaseAction<TblSchedulePlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 试验计划Service
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	@Resource
	private TblSchedulePlanHisService tblSchedulePlanHisService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	@Resource
	private TblTaskTypeService tblTaskTypeService;
	@Resource
	private TblResManagerService tblResManagerService;
	/** 用户Service*/
	@Resource
	private UserService UserService;
	//任务类型负责人
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	// 课题编号
	private String studyNoPara;

	private TblSchedulePlan tblSchedulePlan;

	private String oldDatetime;

	// 签字类型
	private String esType;

	@Resource
	private TblESService tblESService;

	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	
	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;

	private String isValidationPara;

	private String[] getTaskNames;
	
	private String taskName;

	private String startime;
	private String  endtime;
	private String sameTaskName;
	//课题成员标记
	private String member;
	
	// 预览
	public String previewSchedulePlan() {
		//List<TblSchedulePlan> tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,
		//		studyNoPara, 2);
		String isValidationPara = request.getParameter("isValidationPara");
		String displaytype = request.getParameter("disPlaytype");
		ActionContext.getContext().put("isValidationPara", isValidationPara);
		ActionContext.getContext().put("disPlaytype", displaytype);
		ActionContext.getContext().put("left_member", member);
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		
		if(studyPlan.getScheduleState()==null)
			studyPlan.setScheduleState(0);
		ActionContext.getContext().put("scheduleState", studyPlan.getScheduleState());
		return "preview";

	}
	
	public String list() {
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		String Studydirector = studyPlan.getStudydirector();
		String usernameString = tempUser.getRealName();
		if (!Studydirector.equals(usernameString)) {
			ActionContext.getContext().put("left_member", "readonly");
		}
		if(studyPlan.getScheduleState()==null)
			studyPlan.setScheduleState(0);
		
		ActionContext.getContext().put("scheduleState", studyPlan.getScheduleState());
		return "list";
	}

	public void selecttblSchedulePlan() {
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlanList(2,studyNoPara, 2);
		List<TblSchedulePlan> showtblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		for (TblSchedulePlan obj : tblSchedulePlanlist) {
			String StartTime = new SimpleDateFormat("yyyy-MM-dd").format(obj.getStartTime());
			String EndTime = new SimpleDateFormat("yyyy-MM-dd").format(obj.getEndTime());
			obj.setShowstartTime(StartTime);
			obj.setShowendTime(EndTime);
			String  singID = obj.getSignId();
			if(singID != null){
				 TblES tblES =  tblESService.getById(singID);
				 String name = tblES.getSigner();
				 obj.setSignName(name);
			}
			List<TblSchedulePlanHis> tblSchedulePlanHisList  = tblSchedulePlanHisService.getSchedulePlan(obj.getScheduleID()); 
			if(null != tblSchedulePlanHisList && tblSchedulePlanHisList.size() > 0){
				obj.setRemark("1");
			}
			showtblSchedulePlanlist.add(obj);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblSchedulePlanlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void selectTaskItemType() {
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,
				studyNoPara, 2);
		Set<String> taskNameSet = new HashSet<String>();
		for (TblSchedulePlan tblSchedulePlan : tblSchedulePlanlist) {
			String taskName = tblSchedulePlan.getTaskName();
			taskNameSet.add(taskName);
		}
		List<TblSchedulePlan> tblSchedulePlanlist1 = new ArrayList<TblSchedulePlan>();
		for (String obj : taskNameSet) {
			TblSchedulePlan tblSchedulePlan = new TblSchedulePlan();
			tblSchedulePlan.setTaskName(obj);
			tblSchedulePlanlist1.add(tblSchedulePlan);
		}
		TblSchedulePlan tblSchedulePlan = new TblSchedulePlan();
		tblSchedulePlan.setTaskName("全部");
		tblSchedulePlanlist1.add(tblSchedulePlan);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblSchedulePlanlist1);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/** 保存日程安排*/
	public void toSavetblSchedulePlan() {
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String scheduleID = tblSchedulePlanService.getKey();// 日程ID
		tblSchedulePlan.setScheduleID(scheduleID);
		tblSchedulePlan.setCodeType(2);
		tblSchedulePlan.setTaskType(2);
		tblSchedulePlan.setCreater(tempUser.getId());
		tblSchedulePlan.setTaskKind(model.getTaskKind());
		tblSchedulePlan.setCreateDate(new Date());
		if (tblSchedulePlan.getEndTime() == null) {
			if(tblSchedulePlan.getPeriod() == 0){
				tblSchedulePlan.setEndTime(tblSchedulePlan.getStartTime());
				tblSchedulePlan.setTaskEndType(1);
			}else{
				int num = tblSchedulePlan.getTaskEndNum();
				int period = tblSchedulePlan.getPeriod();
				Calendar cl = Calendar.getInstance();
				cl.setTime(tblSchedulePlan.getStartTime());
				int periodUnit = tblSchedulePlan.getPeriodUnit();
				if (periodUnit == 1) {
					cl.add(Calendar.DATE, num * period - 1);
				} else if (periodUnit == 2) {
					cl.add(Calendar.WEEK_OF_YEAR, num * period - 1);
				} else if (periodUnit == 3) {
					cl.add(Calendar.MONTH, num * period - 1);
				} else if (periodUnit == 4) {
					cl.add(Calendar.YEAR, num * period - 1);
				}

				tblSchedulePlan.setEndTime(cl.getTime());
				tblSchedulePlan.setTaskEndType(1);
			}
			
		} else {
			tblSchedulePlan.setEnableDate(tblSchedulePlan.getEndTime());
			tblSchedulePlan.setTaskEndType(2);
		}

		if(tblSchedulePlan.getPeriod() == 0){
			tblSchedulePlan.setPeriod(1);
		}
		if(tblSchedulePlan.getPeriodUnit() == 0){
			tblSchedulePlan.setPeriodUnit(1);
		}
		
		tblSchedulePlanService.save(tblSchedulePlan);
		
		//根据课题编号获得试验计划的信息
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(tblSchedulePlan.getTaskCode());
		if(tblStudyPlan.getStudyState().equals("3")){
			TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(tblSchedulePlan.getTaskCode());
			String hid = tblSchedulePlanHisService.getKey();// 日程ID
			TblSchedulePlanHis schedulePlanHis = new TblSchedulePlanHis();
			schedulePlanHis.setId(hid);
			schedulePlanHis.setScheduleID(tblSchedulePlan.getScheduleID());
			schedulePlanHis.setTaskType(tblSchedulePlan.getTaskType());
			schedulePlanHis.setTaskCode(tblSchedulePlan.getTaskCode());
			schedulePlanHis.setCodeType(tblSchedulePlan.getCodeType());
			schedulePlanHis.setEnableDate(tblSchedulePlan.getEnableDate());
			schedulePlanHis.setStartDay(tblSchedulePlan.getStartDay());
			schedulePlanHis.setStartTime(tblSchedulePlan.getStartTime());
			schedulePlanHis.setEndTime(tblSchedulePlan.getEndTime());
			schedulePlanHis.setPeriod(tblSchedulePlan.getPeriod());
			schedulePlanHis.setPeriodUnit(tblSchedulePlan.getPeriodUnit());
			schedulePlanHis.setTaskEndNum(tblSchedulePlan.getTaskEndNum());
			schedulePlanHis.setTaskEndDate(tblSchedulePlan.getTaskEndDate());
			schedulePlanHis.setTaskEndState(tblSchedulePlan.getTaskEndState());
			schedulePlanHis.setTaskEndType(tblSchedulePlan.getTaskEndType());
			schedulePlanHis.setTaskItemType(tblSchedulePlan.getTaskItemType());
			schedulePlanHis.setTaskName(tblSchedulePlan.getTaskName());
			schedulePlanHis.setValidFlag(tblSchedulePlan.getValidFlag());
			schedulePlanHis.setCreater(tblSchedulePlan.getCreater());
			schedulePlanHis.setCreateDate(tblSchedulePlan.getCreateDate());
			schedulePlanHis.setRemark(tblSchedulePlan.getRemark());
			schedulePlanHis.setFinishFlag(tblSchedulePlan.getFinishFlag());
			schedulePlanHis.setSignId(tblSchedulePlan.getSignId());
			schedulePlanHis.setTaskKind(tblSchedulePlan.getTaskKind());
			schedulePlanHis.setTblApplyReviseID(tblApplyRevise.getId());
			schedulePlanHis.setOldid(tblSchedulePlan.getScheduleID());
			schedulePlanHis.setOperate("添加");
			schedulePlanHis.setOperateDate(new Date());
			tblSchedulePlanHisService.save(schedulePlanHis);
		}
		json.setSuccess(true);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);

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
		tblLog.setOperatOject("日程安排");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}

	// 编辑日程安排
	public void toupdatetblSchedulePlan() throws ParseException {
		String  scheduleID = tblSchedulePlan.getScheduleID();
		//原来要编辑的日程
		TblSchedulePlan schedulePlan  = tblSchedulePlanService.getById(scheduleID);
		Date date = schedulePlan.getStartTime();
		//Date enddate = schedulePlan.getEndTime();
		//编辑后日程的内容
		//Date afterStarTime =  tblSchedulePlan.getStartTime();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		//原来要编辑的日程开始时间 s1
	    String s1 = sdf.format(date);  
	    //修改后的开始时间  s2
	   // String s2 = sdf.format(afterStarTime);  
	   //原来要编辑的日程结束时间 s3
		//String s3 = sdf.format(enddate);  
		String singID = schedulePlan.getSignId();
		//如果签字了,将编辑前的日程写入历时记录里
        if(null != singID && singID!= "" ){
        	TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(schedulePlan.getTaskCode());
        	String id = tblSchedulePlanHisService.getKey();// 日程ID
        	TblSchedulePlanHis schedulePlanHis = new TblSchedulePlanHis();
        	schedulePlanHis.setId(id);
        	schedulePlanHis.setScheduleID(schedulePlan.getScheduleID());
        	schedulePlanHis.setTaskType(schedulePlan.getTaskType());
        	schedulePlanHis.setTaskCode(schedulePlan.getTaskCode());
        	schedulePlanHis.setCodeType(schedulePlan.getCodeType());
        	schedulePlanHis.setEnableDate(schedulePlan.getEnableDate());
        	schedulePlanHis.setStartDay(schedulePlan.getStartDay());
        	schedulePlanHis.setStartTime(schedulePlan.getStartTime());
        	schedulePlanHis.setEndTime(schedulePlan.getEndTime());
        	schedulePlanHis.setPeriod(schedulePlan.getPeriod());
        	schedulePlanHis.setPeriodUnit(schedulePlan.getPeriodUnit());
        	schedulePlanHis.setTaskEndNum(schedulePlan.getTaskEndNum());
        	schedulePlanHis.setTaskEndDate(schedulePlan.getTaskEndDate());
        	schedulePlanHis.setTaskEndState(schedulePlan.getTaskEndState());
        	schedulePlanHis.setTaskEndType(schedulePlan.getTaskEndType());
        	schedulePlanHis.setTaskItemType(schedulePlan.getTaskItemType());
        	schedulePlanHis.setTaskName(schedulePlan.getTaskName());
        	schedulePlanHis.setValidFlag(schedulePlan.getValidFlag());
        	schedulePlanHis.setCreater(schedulePlan.getCreater());
        	schedulePlanHis.setCreateDate(schedulePlan.getCreateDate());
        	schedulePlanHis.setRemark(schedulePlan.getRemark());
        	schedulePlanHis.setFinishFlag(schedulePlan.getFinishFlag());
        	schedulePlanHis.setSignId(schedulePlan.getSignId());
        	schedulePlanHis.setTaskKind(schedulePlan.getTaskKind());
        	if(tblApplyRevise != null){
        		schedulePlanHis.setTblApplyReviseID(tblApplyRevise.getId());
        	}
        	schedulePlanHis.setOldid(schedulePlan.getScheduleID());
        	schedulePlanHis.setOperate("编辑");
        	schedulePlanHis.setOperateDate(new Date());
        	tblSchedulePlanHisService.save(schedulePlanHis);
        }		
	    
        Json json = new Json();
		json.setSuccess(true);
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		es.setEsType(Integer.parseInt(esType));
		if (esType.equals("15")) {
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("修改日程安排");
		}
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
	   /**
	    * 编辑日程
	    */
		String scheduleID1 = tblSchedulePlanService.getKey();// 日程ID
		tblSchedulePlan.setScheduleID(scheduleID1);
		tblSchedulePlan.setCodeType(2);
		tblSchedulePlan.setTaskType(2);
		tblSchedulePlan.setCreater(tempUser.getId());
		tblSchedulePlan.setCreateDate(new Date());
		tblSchedulePlan.setSignId(esId);
		tblSchedulePlan.setTaskKind(tblSchedulePlan.getTaskKind());
		tblSchedulePlan.setRemark(tblSchedulePlan.getRemark());
		tblSchedulePlan.setValidFlag(1);
		if (tblSchedulePlan.getEndTime() == null) {
			if(tblSchedulePlan.getPeriod() == 0){
				tblSchedulePlan.setEndTime(tblSchedulePlan.getStartTime());
				tblSchedulePlan.setTaskEndType(1);
			}else{
				int num = tblSchedulePlan.getTaskEndNum();
				int period = tblSchedulePlan.getPeriod();
				Calendar cl = Calendar.getInstance();
				cl.setTime(tblSchedulePlan.getStartTime());
				int periodUnit = tblSchedulePlan.getPeriodUnit();
				if (periodUnit == 1) {
					cl.add(Calendar.DATE, num * period - 1);
				} else if (periodUnit == 2) {
					cl.add(Calendar.WEEK_OF_YEAR, num * period - 1);
				} else if (periodUnit == 3) {
					cl.add(Calendar.MONTH, num * period - 1);
				} else if (periodUnit == 4) {
					cl.add(Calendar.YEAR, num * period - 1);
				}

				tblSchedulePlan.setEndTime(cl.getTime());
				tblSchedulePlan.setTaskEndType(1);
			}
			
		} else {
			tblSchedulePlan.setEnableDate(tblSchedulePlan.getEndTime());
			tblSchedulePlan.setTaskEndType(2);
		}

		if(tblSchedulePlan.getPeriod() == 0){
			tblSchedulePlan.setPeriod(1);
		}
		if(tblSchedulePlan.getPeriodUnit() == 0){
			tblSchedulePlan.setPeriodUnit(1);
		}
		tblSchedulePlanService.save(tblSchedulePlan);
		//根据编辑前后日程起始日期对比
		//编辑后开始的时间在原来的时间开始后面 或者两天是同一天
		Date nowDate = new Date();
		String s4 = sdf.format(nowDate);
		//原来要编辑的日程开始时间 s1
	    //修改后的开始时间  s2
		//原来要编辑的日程结束时间 s3
        //if(s1.equals(s3)){
		if(s1.equals(s4) || date.after(nowDate)){
			tblSchedulePlanService.delete(schedulePlan.getScheduleID());
		}else{
//			if(afterStarTime.after(nowDate) || s1.equals(s4) ||   s2.equals(s4)){
//				schedulePlan.setEndTime(nowDate);
//				tblSchedulePlanService.update(schedulePlan);
//				//编辑后的开始时间在原来开始时间之前
//			}else if(afterStarTime.before(nowDate)){
				schedulePlan.setEndTime(nowDate);
				tblSchedulePlanService.update(schedulePlan);
//			}
		}
		
		tblESService.save(es);
		esLink.setTableName("TblSchedulePlan");
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		if (esType.equals("15")) {
			esLink.setEsTypeDesc("修改日程安排签字确认");
		}
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		// 日志录入
		if (esType.equals("15")) {
			writeLog("签字", "课题：" + studyNoPara + "修改日程安排，签字");
		}
		json.setMsg("签字成功");
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	
   //跳转到多个课题下相同任务的页面
	@SuppressWarnings("static-access")
	public String jointInquirySchedulePlan(){
		Date nowDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		calendar.add(calendar.MONTH, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
	    String endtime = new SimpleDateFormat("yyyy-MM-dd").format(date);
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    	String startime=sdf.format(nowDate); 
		String isValidationPara = request.getParameter("isValidationPara");
		String displaytype = request.getParameter("disPlaytype");
		String taskName = request.getParameter("taskName");
		ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		ActionContext.getContext().put("taskName", taskName);
		ActionContext.getContext().put("isValidationPara", isValidationPara);
		ActionContext.getContext().put("disPlaytype", displaytype);
		return "jointInquiry";
	}
	//列表显示
	public void loadRowsAndColumns() throws ParseException {
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,studyNoPara, 2);
		List<String> taskNamesList = new ArrayList<String>();
		if (null != getTaskNames && getTaskNames.length > 0) {
			for (int i = 0; i < getTaskNames.length; i++) {
				String taskNames = getTaskNames[i];
				String[] strarray = taskNames.split(",");
				for (int j = 0; j < strarray.length; j++) {
					taskNamesList.add(strarray[j]);
				}
			}
		}
		String jsonStr = tblSchedulePlanList2RowsAndColumnsJson(
				tblSchedulePlanlist, isValidationPara, taskNamesList);
		writeJson(jsonStr);
	}

	//表格显示
	public void loadRowsAndColumns2() throws ParseException {
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,
				studyNoPara, 2);
		List<String> taskNamesList = new ArrayList<String>();
		if (null != getTaskNames && getTaskNames.length > 0) {
			for (int i = 0; i < getTaskNames.length; i++) {
				String taskNames = getTaskNames[i];
				String[] strarray = taskNames.split(",");
				for (int j = 0; j < strarray.length; j++) {
					taskNamesList.add(strarray[j]);
				}
			}
		}
		String jsonStr = tblSchedulePlanList2RowsAndColumnsJson2(
				tblSchedulePlanlist, isValidationPara, taskNamesList);
		writeJson(jsonStr);
	}

	//相同任务名称的日程组合
	public void loadRowsAndColumns3() throws ParseException{
		String jsonStr = tblSchedulePlanList2RowsAndColumnsJson3(startime,endtime,sameTaskName,isValidationPara);
		writeJson(jsonStr);
	}
	//传入参数开始时间结束时间任务名称是否显示空白日期
	@SuppressWarnings("static-access")
	public String tblSchedulePlanList2RowsAndColumnsJson3(String startime,String endtime,String taskName,String isValidationPara) throws ParseException{
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date stardate = formatter.parse(startime);
		Date enddate = formatter.parse(endtime);
		tblSchedulePlanlist = tblSchedulePlanService.getSameTaskNameSchedulePlan(stardate, enddate, taskName,2,2);
		if (null!=  tblSchedulePlanlist && tblSchedulePlanlist.size() > 0) {
		// 显示时间的list
		List<String> datetimelist = new ArrayList<String>();
		
		// 检验指标缩写set
		List<String> testIndexAbbrSet = new ArrayList<String>();
		if (isValidationPara.equals("1")) {
			for (TblSchedulePlan obj :tblSchedulePlanlist) {
				testIndexAbbrSet.add(obj.getTaskName());
			}
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");            
            String mix = startime;
            Date mixDate = fmt.parse(mix);
            String max = endtime;
            Date maxDate = fmt.parse(max);
			Calendar cal = Calendar.getInstance();     
	        cal.setTime(mixDate);     
	        long time1 = cal.getTimeInMillis();                  
	        cal.setTime(maxDate);     
	        long time2 = cal.getTimeInMillis();          
	        long between_days=(time2-time1)/(1000*3600*24);     
			for (int i = 0; i <= between_days; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(mixDate);
				calendar.add(calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
				Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			    datetimelist.add(dateStr);
			}
		}else{
			// 只显示有日程的日期
			for (TblSchedulePlan obj : tblSchedulePlanlist) {
				testIndexAbbrSet.add(obj.getTaskName());
				if (!datetimelist.contains(obj.getDateTime())) {
					datetimelist.add(obj.getDateTime());
				}
			}
		}
		List<String> datetimelistForList = new ArrayList<String>();

		Map<String, String> datetimeMap = new HashMap<String, String>();

		if (null != tblSchedulePlanlist) {
			for (TblSchedulePlan obj : tblSchedulePlanlist) {
				datetimelistForList.add(obj.getDateTime());
				datetimeMap.put(obj.getDateTime(), obj.getTaskName());
			}
		}
		
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		// 检验指标缩写list
		List<String> testIndexAbbrList = new ArrayList<String>(
				testIndexAbbrSet);
		// 列list（列 columns）
		List<List<Columns>> columnsList = tblSchedulePlanListSameColumns(
				testIndexAbbrList, tblSchedulePlanlist,startime);
		Map<String, String> rowMap = null;
		for (String datetime : datetimelist) {
			rowMap = new HashMap<String, String>();
			rowMap.put("datetime", datetime);
			// 根据时间算星期
			String year = datetime.substring(0, datetime.indexOf('-'));
			String month = datetime.substring(5, datetime.lastIndexOf('-'));
			String day = datetime.substring(datetime.lastIndexOf('-') + 1,
					datetime.length());
			Calendar calendar = Calendar.getInstance();// 获得一个日历
			calendar.set(Integer.valueOf(year).intValue(), Integer.valueOf(
					month).intValue() - 1, Integer.valueOf(day).intValue());// 设置当前时间,月份是从0月开始计算
			int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
			String[] str = { "", "日", "一", "二", "三", "四", "五", "六", };
			rowMap.put("week", str[number]);
			
			for (TblSchedulePlan obj : tblSchedulePlanlist) {
				if (obj.getDateTime().equals(datetime)) {
					rowMap.put(obj.getTaskCode(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√");
				}
			}

			rowsList.add(rowMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("columns", columnsList);
		map.put("rows", rowsList);
		map.put("success", true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		return jsonStr;
		}
		return "";
	}
	
	//相同项目
	public List<List<Columns>> tblSchedulePlanListSameColumns(
			List<String> testIndexAbbrList, List<TblSchedulePlan> tblSchedulePlanlist,String startime) {
		List<Columns> list = new ArrayList<Columns>();
		Columns columns = null;
		columns = new Columns();
		columns.setTitle("日程时间安排");
		columns.setColspan(2);
		columns.setWidth(80);
		list.add(columns);
		List<String> taskCodelist = new ArrayList<String>();
		for (TblSchedulePlan obj : tblSchedulePlanlist) {
			if (!taskCodelist.contains(obj.getTaskCode())) {
				taskCodelist.add(obj.getTaskCode());
			}
		}
		for (String obj : taskCodelist) {
			columns = new Columns();
			columns.setField(obj);
			columns.setTitle(obj);
			columns.setRowspan(2);
			columns.setWidth(100);
			list.add(columns);
		}
		List<Columns> list2 = new ArrayList<Columns>();
		columns = new Columns();
		columns = new Columns();
		columns.setField("datetime");
		columns.setTitle("日期");
		columns.setWidth(80);
		columns.setColspan(1);
		list2.add(columns);
		columns = new Columns();
		columns = new Columns();
		columns.setField("week");
		columns.setTitle("星期");
		columns.setWidth(60);
		columns.setColspan(1);
		list2.add(columns);
		List<List<Columns>> list12 = new ArrayList<List<Columns>>();
		list12.add(list);
		list12.add(list2);
		return list12;
	}
	
	//表格显示
	@SuppressWarnings("static-access")
	public String tblSchedulePlanList2RowsAndColumnsJson(
			List<TblSchedulePlan> list, String isValidationPara,
			List<String> getTaskNames) throws ParseException {
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		List<List<Columns>> columnsList = new ArrayList<List<Columns>>();
		List<List<Columns>> frozenColumnsList = new ArrayList<List<Columns>>();
	
		if (list != null && list.size() > 0) {
			// 显示时间的list
			List<String> datetimelist = new ArrayList<String>();
			// 时间的list
			List<Date> dateTimeAlllist = new ArrayList<Date>();
			// 检验指标缩写set
			List<String> testIndexAbbrSet = new ArrayList<String>();
			if (isValidationPara.equals("1")) {
				// 显示空日程
				if (null != getTaskNames && getTaskNames.size() > 0) {
					for (String taskName : getTaskNames) {
						testIndexAbbrSet.add(taskName);
					}
				} else {
					if (list != null && list.size() > 0) {
						for (TblSchedulePlan obj : list) {
							testIndexAbbrSet.add(obj.getTaskName());
						}
					}
				}
				Date mixDate = null;
				Date maxDate = null;
				for (TblSchedulePlan obj : list) {
					if(mixDate == null && maxDate == null){
						mixDate = obj.getDateTimeDate();
						maxDate = obj.getDateTimeDate();
					}else{
						if(mixDate.after(obj.getDateTimeDate())){
							mixDate = obj.getStartTime();
						}else if(maxDate.before(obj.getDateTimeDate())){
							maxDate = obj.getDateTimeDate();
						}
					}
				}
				
				if(mixDate!=null&&maxDate!=null)
				{
					Calendar cal = Calendar.getInstance();     
			        cal.setTime(mixDate);     
			        long time1 = cal.getTimeInMillis();                  
			        cal.setTime(maxDate);     
			        long time2 = cal.getTimeInMillis();          
			        long between_days=(time2-time1)/(1000*3600*24);     
					for (int i = 0; i <= between_days; i++) {
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(mixDate);
						calendar.add(calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
						Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
					    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
					    datetimelist.add(dateStr);
					}
				}
			} else {
				if (null != getTaskNames && getTaskNames.size() > 0) {
					for (TblSchedulePlan obj : list) {
						for (String taskName : getTaskNames) {
							if (taskName.equals(obj.getTaskName())) {
								testIndexAbbrSet.add(obj.getTaskName());
								if (!datetimelist.contains(obj.getDateTime())) {
									datetimelist.add(obj.getDateTime());
									dateTimeAlllist.add(obj.getDateTimeDate());
								}
							}
						}
					}

				} else {
					// 只显示有日程的日期
					if (list != null && list.size() > 0) {
						for (TblSchedulePlan obj : list) {
							testIndexAbbrSet.add(obj.getTaskName());
							if (!datetimelist.contains(obj.getDateTime())) {
								datetimelist.add(obj.getDateTime());
								dateTimeAlllist.add(obj.getDateTimeDate());
							}
						}
					}
				}
			}
			List<String> datetimelistForList = new ArrayList<String>();
			Map<String, String> datetimeMap = new HashMap<String, String>();
			if (null != list) {
				for (TblSchedulePlan obj : list) {
					datetimelistForList.add(obj.getDateTime());
					datetimeMap.put(obj.getDateTime(), obj.getTaskName());
				}
			}
			
			// 检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			// 列list（列 columns）
			Map<String, String> rowMap = null;
			for (String datetime : datetimelist) {
				rowMap = new HashMap<String, String>();
				rowMap.put("datetime", datetime);
				// 根据时间算星期
				String year = datetime.substring(0, datetime.indexOf('-'));
				String month = datetime.substring(5, datetime.lastIndexOf('-'));
				String day = datetime.substring(datetime.lastIndexOf('-') + 1,
						datetime.length());
				Calendar calendar = Calendar.getInstance();// 获得一个日历
				calendar.set(Integer.valueOf(year).intValue(), Integer.valueOf(
						month).intValue() - 1, Integer.valueOf(day).intValue());// 设置当前时间,月份是从0月开始计算
				int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
				String[] str = { "", "日", "一", "二", "三", "四", "五", "六", };
				rowMap.put("week", str[number]);
				for (TblSchedulePlan obj : list) {
					if (obj.getDateTime().equals(datetime)) {
						rowMap.put(obj.getTaskName(), "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√");
					}
				}
				rowsList.add(rowMap);
			}
			Map<String, List<List<Columns>>> map = tblSchedulePlanList2Columns(testIndexAbbrList, studyNoPara);
			columnsList = map.get("columns");
			
			frozenColumnsList = map.get("frozenColumns");
			//columnsList = tblSchedulePlanList2Columns(testIndexAbbrList, studyNoPara);
		}else {
			Map<String, List<List<Columns>>> map = tblSchedulePlanList2Columns(new ArrayList<String>(), studyNoPara);
			columnsList = map.get("columns");
			
			frozenColumnsList = map.get("frozenColumns");
			//columnsList = tblSchedulePlanList2Columns(new ArrayList<String>(), studyNoPara);

		}
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("columns", columnsList);
			map.put("frozenColumns", frozenColumnsList);
			map.put("rows", rowsList);
			map.put("success", true);
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			return jsonStr;
		//}
		//return "";

	}

	//列表显示
	@SuppressWarnings("static-access")
	public String tblSchedulePlanList2RowsAndColumnsJson2(
			List<TblSchedulePlan> list, String isValidationPara,
			List<String> getTaskNames) throws ParseException {
		List<List<Columns>> columnsList = new ArrayList<List<Columns>>();
		List<List<Columns>> frozenColumnsList = new ArrayList<List<Columns>>();
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		
		if (list != null && list.size() > 0) {
			// 显示时间的list
			List<String> datetimelist = new ArrayList<String>();
			// 时间的list
			List<Date> dateTimeAlllist = new ArrayList<Date>();
			// 检验指标缩写set
			List<String> testIndexAbbrSet = new ArrayList<String>();
			if (isValidationPara.equals("1")) {
				// 显示空日程
				if (null != getTaskNames && getTaskNames.size() > 0) {
					for (String taskName : getTaskNames) {
						testIndexAbbrSet.add(taskName);
					}
				} else {
					for (TblSchedulePlan obj : list) {
						testIndexAbbrSet.add(obj.getTaskName());
					}
				}
				Date mixDate = null;
				Date maxDate = null;
				for (TblSchedulePlan obj : list) {
					if(mixDate == null && maxDate == null){
						mixDate = obj.getDateTimeDate();
						maxDate = obj.getDateTimeDate();
					}else{
						if(mixDate.after(obj.getDateTimeDate())){
							mixDate = obj.getStartTime();
						}else if(maxDate.before(obj.getDateTimeDate())){
							maxDate = obj.getDateTimeDate();
						}
					}
				}
				Calendar cal = Calendar.getInstance();     
		        cal.setTime(mixDate);     
		        long time1 = cal.getTimeInMillis();                  
		        cal.setTime(maxDate);     
		        long time2 = cal.getTimeInMillis();          
		        long between_days=(time2-time1)/(1000*3600*24);     
				for (int i = 0; i <= between_days; i++) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(mixDate);
					calendar.add(calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
					Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
				    String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
				    datetimelist.add(dateStr);
				}
			} else {
				// 只显示有日程的日期	
				if (null != getTaskNames && getTaskNames.size() > 0) {
					for (TblSchedulePlan obj : list) {
						if(getTaskNames.contains(obj.getTaskName())){
							if (!datetimelist.contains(obj.getDateTime())) {
								datetimelist.add(obj.getDateTime());
								dateTimeAlllist.add(obj.getDateTimeDate());
							}
						}
					}
				} else {
					for (TblSchedulePlan obj : list) {
						testIndexAbbrSet.add(obj.getTaskName());
						if (!datetimelist.contains(obj.getDateTime())) {
							datetimelist.add(obj.getDateTime());
							dateTimeAlllist.add(obj.getDateTimeDate());
						}
					}
				}
			}
			List<String> datetimelistForList = new ArrayList<String>();
			Map<String, String> datetimeMap = new HashMap<String, String>();
			if (null != list) {
				for (TblSchedulePlan obj : list) {
					datetimelistForList.add(obj.getDateTime());
					datetimeMap.put(obj.getDateTime(), obj.getTaskName());
				}
			}
			// 检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			Map<String, String> rowMap = null;
			for (String datetime : datetimelist) {
				rowMap = new HashMap<String, String>();
				rowMap.put("datetime", datetime);
				// 根据时间算星期
				String year = datetime.substring(0, datetime.indexOf('-'));
				String month = datetime.substring(5, datetime.lastIndexOf('-'));
				String day = datetime.substring(datetime.lastIndexOf('-') + 1,
						datetime.length());
				Calendar calendar = Calendar.getInstance();// 获得一个日历
				calendar.set(Integer.valueOf(year).intValue(), Integer.valueOf(
						month).intValue() - 1, Integer.valueOf(day).intValue());// 设置当前时间,月份是从0月开始计算
				int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
				String[] str = { "", "日", "一", "二", "三", "四", "五", "六", };
				rowMap.put("week", str[number]);
				String taskName = null;
				if (null != getTaskNames && getTaskNames.size() > 0) {
					for(TblSchedulePlan obj : list){
						if (obj.getDateTime().equals(datetime)) {
                          if(getTaskNames.contains(obj.getTaskName())){
                        	  if (taskName != null) {
  								taskName = taskName + " , " + obj.getTaskName();
  							} else {
  								taskName = obj.getTaskName();
  							}  
                          }
							
						}
					}
				} else {
					for (TblSchedulePlan obj : list) {
						if (obj.getDateTime().equals(datetime)) {

							if (taskName != null) {
								taskName = taskName + " , " + obj.getTaskName();
							} else {
								taskName = obj.getTaskName();
							}
						}
					}

				}
				rowMap.put("taslItemType", taskName);
				rowsList.add(rowMap);
			}
			// 列list（列 columns）
			Map<String, List<List<Columns>>> map = tblSchedulePlanList2Columns2(testIndexAbbrList, studyNoPara);
			columnsList = map.get("columns");
			
			frozenColumnsList = map.get("frozenColumns");
			
		}else {
			// 列list（列 columns）
			Map<String, List<List<Columns>>> map = tblSchedulePlanList2Columns2(new ArrayList<String>(), studyNoPara);
			columnsList = map.get("columns");
			
			frozenColumnsList = map.get("frozenColumns");
		}
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("columns", columnsList);
			map.put("frozenColumns", frozenColumnsList);
			map.put("rows", rowsList);
			map.put("success", true);
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			return jsonStr;
		

	}
   //列表显示
	public Map<String,List<List<Columns>>> tblSchedulePlanList2Columns(List<String> testIndexAbbrList, String studyNoPara) {
		List<Columns> list = new ArrayList<Columns>();
		Columns columns = null;
		
		// 指标
		List<String> tasknamelist = new ArrayList<String>();
		for (String taskname : testIndexAbbrList) {
			if (!tasknamelist.contains(taskname)) {
				tasknamelist.add(taskname);
			}
		}
		for (String obj : tasknamelist) {
			columns = new Columns();
			columns.setField(obj);
			columns.setTitle(obj);
			columns.setRowspan(2);
			columns.setWidth(50);
			list.add(columns);
		}
		List<Columns> listHeader = new ArrayList<Columns>();
		columns = new Columns();
		columns.setTitle("日程时间安排");
		columns.setColspan(2);
		columns.setWidth(80);
		listHeader.add(columns);
		
		List<Columns> list2 = new ArrayList<Columns>();
		columns = new Columns();
		columns = new Columns();
		columns.setField("datetime");
		columns.setTitle("日期");
		columns.setWidth(80);
		columns.setColspan(1);
		list2.add(columns);
		columns = new Columns();
		columns = new Columns();
		columns.setField("week");
		columns.setTitle("星期");
		columns.setWidth(60);
		columns.setColspan(1);
		list2.add(columns);
		List<List<Columns>> list12 = new ArrayList<List<Columns>>();
		list12.add(listHeader);
		list12.add(list2);
		List<List<Columns>> columnsList = new ArrayList<List<Columns>>();
		columnsList.add(list);
		Map<String,List<List<Columns>>> map = new HashMap<String, List<List<Columns>>>();
		map.put("columns", columnsList);
		map.put("frozenColumns", list12);
		return map;
	}

	//表格显示
	public Map<String,List<List<Columns>>> tblSchedulePlanList2Columns2(List<String> testIndexAbbrList, String studyNoPara) {
		List<Columns> list = new ArrayList<Columns>();
		Columns columns = null;
		// 指标
		columns = new Columns();
		columns.setField("taslItemType");
		columns.setTitle("任务类型");
		columns.setRowspan(2);
		columns.setWidth(360);
		list.add(columns);
		
		List<Columns> listHeader = new ArrayList<Columns>();
		columns = new Columns();
		columns.setTitle("日程时间安排");
		columns.setColspan(2);
		columns.setWidth(80);
		listHeader.add(columns);
		List<Columns> list2 = new ArrayList<Columns>();
		columns = new Columns();
		columns = new Columns();
		columns.setField("datetime");
		columns.setTitle("日期");
		columns.setWidth(80);
		columns.setColspan(1);
		list2.add(columns);
		columns = new Columns();
		columns = new Columns();
		columns.setField("week");
		columns.setTitle("星期");
		columns.setWidth(60);
		columns.setColspan(1);
		list2.add(columns);
		List<List<Columns>> list12 = new ArrayList<List<Columns>>();
		list12.add(listHeader);
		list12.add(list2);
		List<List<Columns>> columnsL = new ArrayList<List<Columns>>();
		columnsL.add(list);
		Map<String,List<List<Columns>>> map = new HashMap<String, List<List<Columns>>>();
		map.put("columns", columnsL);
		map.put("frozenColumns", list12);
		return map;
	}

	//签字  对整个专题
	public void  schedulePlanSign(){
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		Json json = new Json();
		json.setSuccess(true);
		
		TblStudyPlan studyPlan = tblStudyPlanService.getByStudyNo(studyNoPara);
		if(studyPlan.getScheduleState()==null||studyPlan.getScheduleState()!=1)
		{
		
			studyPlan.setScheduleState(1);
			tblStudyPlanService.update(studyPlan);
			
			
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			// 验证通过则进行一下操作
	        es.setEsType(21);
		    es.setSigner(tempUser.getRealName());
		    es.setEsTypeDesc("日程签字确认");
		    String esid = tblESService.getKey("TblES");
			es.setDateTime(new Date());
			es.setEsId(esid);
			tblESService.save(es);
			/*
			List<String> singIdList = new ArrayList<String>();
			if (null != getTaskNames && getTaskNames.length > 0) {
				for (int i = 0; i < getTaskNames.length; i++) {
					String taskNames = getTaskNames[i];
					String[] strarray = taskNames.split(",");		
					for (int j = 0; j < strarray.length; j++) {
						singIdList.add(strarray[j]);
					}
				}
				tblSchedulePlanService.updateAllTblSchedulePlans(singIdList,esid);
			}
			*/
			esLink.setTableName("TblSchedulePlan");
			esLink.setDataId(studyNoPara);
			esLink.setTblES(es);
			esLink.setEsType(21);
			esLink.setEsTypeDesc("日程签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			// 日志录入
			writeLog("签字","日程签字签字确认，签字");
			json.setMsg("签字成功");
			//发消息
			//当前时间
			
			
			
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			/*
			int i = singIdList.size();
			String[] ids = new String[i] ;
			int j=0;
			for(String id:singIdList){
				ids[j]=id;
				j++;
			*/
				List<TblSchedulePlan> tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,studyNoPara,2);
				List<String> list = new ArrayList<String>();
				for(TblSchedulePlan obj:tblSchedulePlanlist){
					if(!list.contains(obj.getTaskKind()+"")){
						list.add(obj.getTaskKind()+"");
					}
				}
			//}
			//接收者列表
			List<String> receiverList;
			//if(list != null && !list.equals("")){
				List<TblTaskTypeLeader> tblTaskTypeLeaderList  = tblTaskTypeLeaderService.getAllTblTaskTypeLeaderListByTaskTypeID(list);
				for(TblTaskTypeLeader  leader:tblTaskTypeLeaderList){
					String planMsg = "";
					for(TblSchedulePlan plan:tblSchedulePlanlist){
						if(leader.getTaskTypeID().equals(plan.getTaskKind()+"") ){
							if(planMsg != ""){
								planMsg = planMsg +" , ";
							}
							planMsg = planMsg +plan.getTaskName();
						}else{
							continue;
						}
					}
					//通知消息
					TblNotification tblNotification = new TblNotification();
					tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题　"+studyNoPara+"　日程提交提醒");//消息头
					//userService.getRealNameByUserName(leader.getTaskLeader())+"您好
					String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
					"专题编号：　"+studyNoPara+"，具体日程为："+planMsg+"，特此提醒！";
					tblNotification.setMsgContent(msgContent);
					tblNotification.setMsgType(1);//系统消息
					tblNotification.setSender(getCurrentRealName());// 发送者
					tblNotification.setSendTime(new Date());// 发送时间
					receiverList = new ArrayList<String>();
					receiverList.add(leader.getTaskLeader());
					tblNotificationService.save(tblNotification,receiverList);
					planMsg = "";
				}
			//}
			//区域负责人接受
			TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(studyNoPara);
			if(null != tblStudyInfo){
				String resid = tblStudyInfo.getResID();
				List<TblResManager> managerList = tblResManagerService.getByHouseId(resid);
				if(null != managerList && list.contains("2")){
					 for(TblResManager obj:managerList){
						String planMsg = "";
						for(TblSchedulePlan plan:tblSchedulePlanlist){
							if( 2 == plan.getTaskKind()){
								if(planMsg != ""){
									planMsg = planMsg +" , ";
								}
								planMsg = planMsg +plan.getTaskName();
							}
						}
						//通知消息
						TblNotification tblNotification = new TblNotification();
						TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
						 TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
						tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题　"+studyNoPara+"　日程提交提醒");//消息头
						//String msgContent = userService.getRealNameByUserName(obj.getResManager())+"您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
						"专题编号：　 "+studyNoPara+"　，具体日程为："+planMsg+"，安置在你所负责的区域 （"
						+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"），请审核此专题日程，特此提醒！";
						tblNotification.setMsgContent(msgContent);
						tblNotification.setMsgType(1);//系统消息
						
						tblNotification.setSender(getCurrentRealName());// 发送者
						
						tblNotification.setSendTime(new Date());// 发送时间
						List<String> resManagerList = new ArrayList<String>();
						resManagerList.add(obj.getResManager());
						tblNotificationService.save(tblNotification,resManagerList);
					 }
				}
				
			}
			
		}else {
			json.setSuccess(false);
			json.setMsg("已经签过字，请不要重复签字");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	public void delSchedulePlan(){
		List<String> singIdList = new ArrayList<String>();
		if (null != getTaskNames && getTaskNames.length > 0) {
			for (int i = 0; i < getTaskNames.length; i++) {
				String taskNames = getTaskNames[i];
				String[] strarray = taskNames.split(",");
				for (int j = 0; j < strarray.length; j++) {
					singIdList.add(strarray[j]);
				}
			}
			tblSchedulePlanService.delectTblSchedulePlans(singIdList);
			Json json = new Json();
			json.setSuccess(true);
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			// 验证通过则进行一下操作
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			es.setEsType(Integer.parseInt(esType));
			if (esType.equals("16")) {
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("删除日程安排");
			}
			es.setDateTime(new Date());
			es.setEsId(tblESService.getKey("TblES"));
			tblESService.save(es);

			esLink.setTableName("TblSchedulePlan");
			esLink.setDataId(studyNoPara);
			esLink.setTblES(es);
			esLink.setEsType(Integer.parseInt(esType));
			if (esType.equals("16")) {
				esLink.setEsTypeDesc("删除日程安排签字确认");
			}
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			// 日志录入
			if (esType.equals("16")) {
				writeLog("签字", "课题：" + studyNoPara + "删除("+singIdList+")日程安排，签字");
			}
			json.setMsg("签字成功");
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}
	}
	
	/**
	 * 未签字时，直接执行更新操作
	 * @return
	 */
	
	public void noSignUpdateSchedulePlan(){
		TblSchedulePlan schedulePlan  = tblSchedulePlanService.getById(model.getScheduleID());
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		schedulePlan.setCreater(tempUser.getId());
		schedulePlan.setCreateDate(new Date());
		schedulePlan.setStartTime(tblSchedulePlan.getStartTime());
		schedulePlan.setEndTime(tblSchedulePlan.getEndTime());
		if (tblSchedulePlan.getEndTime() == null) {
			if(tblSchedulePlan.getPeriod() == 0){
				schedulePlan.setEndTime(tblSchedulePlan.getStartTime());
				schedulePlan.setTaskEndType(1);
			}else{
				int num = tblSchedulePlan.getTaskEndNum();
				int period = tblSchedulePlan.getPeriod();
				Calendar cl = Calendar.getInstance();
				cl.setTime(tblSchedulePlan.getStartTime());
				int periodUnit = tblSchedulePlan.getPeriodUnit();
				if (periodUnit == 1) {
					cl.add(Calendar.DATE, num * period - 1);
				} else if (periodUnit == 2) {
					cl.add(Calendar.WEEK_OF_YEAR, num * period - 1);
				} else if (periodUnit == 3) {
					cl.add(Calendar.MONTH, num * period - 1);
				} else if (periodUnit == 4) {
					cl.add(Calendar.YEAR, num * period - 1);
				}

				schedulePlan.setEndTime(cl.getTime());
				schedulePlan.setTaskEndType(1);
			}
			
		} else {
			schedulePlan.setEnableDate(tblSchedulePlan.getEndTime());
			schedulePlan.setTaskEndType(2);
		}

		if(tblSchedulePlan.getPeriod() == 0){
			schedulePlan.setPeriod(1);
		}else{
			schedulePlan.setPeriod(tblSchedulePlan.getPeriod());
		}
		if(tblSchedulePlan.getPeriodUnit() == 0){
			schedulePlan.setPeriodUnit(1);
		}else{
			schedulePlan.setPeriodUnit(tblSchedulePlan.getPeriodUnit());
		}
		schedulePlan.setRemark(tblSchedulePlan.getRemark());
		
		tblSchedulePlanService.update(schedulePlan);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success",true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	//加载检测项目树形值
	public void loadComboTreeData() throws Exception{
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		List<TblTaskType> tblTaskTypeList = tblTaskTypeService.getTblTaskTypeList();
		List<String> kindList = new ArrayList<String>();
		//任务类别 1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理 7 ：分析 8 ：生态毒理
		kindList.add("委托管理");
		kindList.add("动物试验");
		kindList.add("临床检验 ");
		kindList.add("毒性病理");
		kindList.add("QA管理");
		kindList.add("供试品管理");
		kindList.add("分析");
		kindList.add("生态毒理");
		ComboTreeModel ctm =null;
		for (int i = 1; i < 9; i++) {
			ctm = new ComboTreeModel();
			ctm.setId(i+"");
			ctm.setText(kindList.get(i-1));
			List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
			if(null != tblTaskTypeList){
				for (TblTaskType obj:tblTaskTypeList) {
					if(obj.getTaskKind() == i){
						ComboTreeModel comboTreeModel = new ComboTreeModel();
						comboTreeModel.setId(obj.getTaskName());
						comboTreeModel.setText(obj.getTaskName());
						children.add(comboTreeModel);
					}
				}
			}
			if(children.size()>0){
				ctm.setState("closed");
				ctm.setChildren(children);
				list.add(ctm);
			}
		}
		ctm =new ComboTreeModel();
		ctm.setId(0+"");
		ctm.setText("自定义");
		list.add(ctm);
		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		System.out.print(json);
		writeJson(json);
		
	}
	
	public void sameTaskName(){
		Map<String,Object> map = new HashMap<String,Object>();
		boolean falg = true;
		List<TblTaskType> tblTaskTypeList = tblTaskTypeService.getTblTaskTypeListhaveV();
		System.out.println(sameTaskName);
		for(TblTaskType obj:tblTaskTypeList){
			 if(sameTaskName.equals(obj.getTaskName())){
				 falg = false;
				 int kind = obj.getTaskKind();
				//任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理7分析8生态
				 String name= "";
				 if(kind == 1){
					 name = "委托管理";
				 }else if(kind == 2){
					 name = "动物试验";
				 }else if(kind == 3){
					 name = "临床检验 ";
				 }else if(kind == 4){
					 name = "毒性病理";
				 }else if(kind == 5){
					 name = "QA管理";
				 }else if(kind == 6){
					 name = "供试品管理";
				 }else if(kind == 7){
					 name = "分析";
				 }else if(kind == 8){
					 name = "生态毒理";
				 }
				 map.put("msg",name);
				 break;
			 }
		}
		map.put("success",falg);
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	public void isExistTblSchedulePlan()
	{
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		
		//tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlan(2,studyNoPara, 2);//存储过程，产生日期交叉显示的效果
		tblSchedulePlanlist = tblSchedulePlanService.getSchedulePlanList(2,studyNoPara, 2);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(tblSchedulePlanlist!=null&&tblSchedulePlanlist.size()>0)
		{
			map.put("success", true);
			map.put("tblSchedulePlanlist", tblSchedulePlanlist);
			
		}else {
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		writeJson(json);
	}
	
	
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public TblSchedulePlan getTblSchedulePlan() {
		return tblSchedulePlan;
	}

	public void setTblSchedulePlan(TblSchedulePlan tblSchedulePlan) {
		this.tblSchedulePlan = tblSchedulePlan;
	}

	public TblSchedulePlanService getTblSchedulePlanService() {
		return tblSchedulePlanService;
	}

	public void setTblSchedulePlanService(
			TblSchedulePlanService tblSchedulePlanService) {
		this.tblSchedulePlanService = tblSchedulePlanService;
	}

	public String getOldDatetime() {
		return oldDatetime;
	}

	public void setOldDatetime(String oldDatetime) {
		this.oldDatetime = oldDatetime;
	}

	public String getEsType() {
		return esType;
	}

	public void setEsType(String esType) {
		this.esType = esType;
	}

	public TblESService getTblESService() {
		return tblESService;
	}

	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}

	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}

	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}

	public String getIsValidationPara() {
		return isValidationPara;
	}

	public void setIsValidationPara(String isValidationPara) {
		this.isValidationPara = isValidationPara;
	}

	public String[] getGetTaskNames() {
		return getTaskNames;
	}

	public void setGetTaskNames(String[] getTaskNames) {
		this.getTaskNames = getTaskNames;
	}

	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public TblSchedulePlanHisService getTblSchedulePlanHisService() {
		return tblSchedulePlanHisService;
	}

	public void setTblSchedulePlanHisService(
			TblSchedulePlanHisService tblSchedulePlanHisService) {
		this.tblSchedulePlanHisService = tblSchedulePlanHisService;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSameTaskName() {
		return sameTaskName;
	}

	public void setSameTaskName(String sameTaskName) {
		this.sameTaskName = sameTaskName;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	
}
