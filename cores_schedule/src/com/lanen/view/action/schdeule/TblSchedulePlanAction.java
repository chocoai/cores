package com.lanen.view.action.schdeule;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Columns;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Department;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblSOLeader;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeField;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.DepartmentService;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyInfoService;
import com.lanen.service.schdeule.TblTaskTypeFieldService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.schdeule.TblTaskTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblSchedulePlanAction extends BaseAction<TblSchedulePlan>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6544825363028085530L;
	
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	@Resource
	private TblTaskTypeFieldService tblTaskTypeFieldService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	@Resource
	private TblResManagerService tblResManagerService;
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	
	
	/** 用户Service*/
	@Resource
	private UserService UserService;
	@Resource
	private TblTaskTypeService tblTaskTypeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	
	private String studyNoPara;
	private String[] getTaskNames;
	private String isValidationPara;//判断空白日期
	private String taskKind1;
	private String description;
	private boolean allDate;//是否显示全部时间
	private String studyNos;//课题
	private String leaders;//人员
	private String taskNames;//任务名称
	private String houseNames;//资源
	private String tableHeight;
	public String list(){
		Date nowDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MONTH, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    	String startime=sdf.format(nowDate); 
    	String endtime = new SimpleDateFormat("yyyy-MM-dd").format(date);
    	ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		return "list";
	}
	
	public String selectSchedulePan(){
		ActionContext.getContext().put("taskKind", model.getTaskKind());
		Date nowDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MONTH, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    	String startime=sdf.format(nowDate); 
    	String endtime = new SimpleDateFormat("yyyy-MM-dd").format(date);
    	ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		return "select";
	}
	
	public void selectAllUsers(){
		//'委托管理';'临床检验';'毒性病理';'QA管理';'供试品管理';'分析';
		 List<User> newlist = new ArrayList<User>();
		if(model.getTaskKind() == 1){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-委托管理");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 2){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-动物试验");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 3){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-临床检验");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 4){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-毒性病理");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 5){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-QA管理");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 6){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-供试品管理");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 7){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-分析");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }else if(model.getTaskKind() == 7){
			 List<User> userlist= UserService.findByPrivilegeName("日程管理-列表-生态毒理");
			 for (User entry : userlist) {
	        	  User user2 = new User();
	        	  user2.setId( entry.getId());
	        	  user2.setRealName(entry.getRealName());
				  newlist.add(user2);
	        } 
		 }
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", newlist);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	/**专题列表*/
	public void selectStudyNoTable(){		
		List<?>  list = tblSchedulePlanService.getAllByTimeschedulePlanList(model.getStartTime(), model.getEndTime(),taskKind1);
		
		List<TblSOLeader> slist= new ArrayList<TblSOLeader>();
		
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblSOLeader soLeader = new TblSOLeader();
				Object objs =  list.get(i);
				if(objs  != null ){
					soLeader.setId((String)objs);
					soLeader.setStudyNo((String)objs);
					slist.add(soLeader);
				}
			}
		}
		
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", slist);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	/**任务名称*/
	public void selectTaskNameTable(){
		List<TblTaskType> list = new ArrayList<TblTaskType>();
			//查询一类下面的任务名称集合
			List<TblTaskTypeField> tasklist= tblTaskTypeFieldService.getByTaskKind2(Integer.valueOf(taskKind1).intValue());
			for(TblTaskTypeField obj:tasklist){
				String ttId=obj.getTttId();
				TblTaskType taskType = tblTaskTypeService.getById(ttId);
				taskType.setId(taskType.getId());
				taskType.setTaskName(taskType.getTaskName());
			    list.add(taskType);
			}
			List<String> tasklist2 = tblSchedulePlanService.getTaskNameNoInTaskType(taskKind1);
			   for(String obj:tasklist2){
				   TblTaskType taskType = new TblTaskType();
				   taskType.setId(obj);
				   taskType.setTaskName(obj);
				   list.add(taskType);
			   }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	
	public void getSchedulePlandatagrid(){
		//将课题编号转换成list
		List<String>  studyNolist;
		if (null != studyNos && studyNos.length() >= 1 ) {
			studyNolist= new ArrayList<String>();
				String[] strarray = studyNos.split(",");
				for (int j = 0; j < strarray.length; j++) {
					studyNolist.add(strarray[j]);
				}
		}else{
			studyNolist =null;
		}
		//人员转换成list
		List<String>   leaderList;
		if (null != leaders && leaders.length() >= 1 ) {
			leaderList= new ArrayList<String>();
				String[] strarray = leaders.split(",");
				for (int j = 0; j < strarray.length; j++) {
					leaderList.add(strarray[j]);
				}
		}else{
			leaderList = null;
		}
		//任务名称
		List<String> taskList;
		if(null != taskNames && taskNames.length() >= 1 ){
			taskList = new ArrayList<String>();
			String[] strarray = taskNames.split(",");
			for(int j = 0;j < strarray.length;j++){
				 taskList.add(strarray[j]);
			}
		}else{
			taskList = null;
		}
		//资源
		List<String> houseList;
		if(null != houseNames && houseNames.length() >= 1 ){
			houseList = new ArrayList<String>();
			String[] strarray = houseNames.split(",");
			for(int j = 0;j < strarray.length;j++){
				houseList.add(strarray[j]);
			}
		}else{
			houseList = null;
		}
		Date startTime = model.getStartTime();
		Date endTime=model.getEndTime();	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String end=sdf.format(endTime); 
		String star=sdf.format(startTime); 
		ActionContext.getContext().put("endtime", end);
		ActionContext.getContext().put("startime", star);
		ActionContext.getContext().put("description", description);
		Map<String,Object> map =tblSchedulePlanService.getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType
		(startTime, endTime,model.getTaskKind() , 2, 2, allDate,studyNolist,houseList,taskList,leaderList);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
		
	}
	
	public void getOneDateSchedulePlandatagrid(){
		ActionContext.getContext().put("description", description);
		//将课题编号转换成list
		List<String>  studyNolist;
		if (null != studyNos && studyNos.length() >= 1 ) {
			studyNolist= new ArrayList<String>();
				String[] strarray = studyNos.split(",");
				for (int j = 0; j < strarray.length; j++) {
					studyNolist.add(strarray[j]);
				}
		}else{
			studyNolist =null;
		}
		//人员转换成list
		List<String>   leaderList;
		if (null != leaders && leaders.length() >= 1 ) {
			leaderList= new ArrayList<String>();
				String[] strarray = leaders.split(",");
				for (int j = 0; j < strarray.length; j++) {
					leaderList.add(strarray[j]);
				}
		}else{
			leaderList = null;
		}
		//任务名称
		List<String> taskList;
		if(null != taskNames && taskNames.length() >= 1 ){
			taskList = new ArrayList<String>();
			String[] strarray = taskNames.split(",");
			for(int j = 0;j < strarray.length;j++){
				 taskList.add(strarray[j]);
			}
		}else{
			taskList = null;
		}
		//资源
		List<String> houseList;
		if(null != houseNames && houseNames.length() >= 1 ){
			houseList = new ArrayList<String>();
			String[] strarray = houseNames.split(",");
			for(int j = 0;j < strarray.length;j++){
				houseList.add(strarray[j]);
			}
		}else{
			houseList = null;
		}
		Date startTime = model.getStartTime();
		Date endTime=model.getEndTime();	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String end=sdf.format(endTime); 
		String star=sdf.format(startTime); 
		ActionContext.getContext().put("endtime", end);
		ActionContext.getContext().put("startime", star);
		ActionContext.getContext().put("description", description);
		Map<String,Object> map = tblSchedulePlanService.getOneDateScheduleLeaderNamebyDateTaskKindCodeTypeTaskType
		(startTime, endTime,model.getTaskKind() , 2, 2, allDate,studyNolist,houseList,taskList,leaderList);//getOneDateScheduleLeaderNamebyDateTaskKindCodeTypeTaskType
		String json = JsonPluginsUtil.beanToJson(map);
		System.out.println(json);
		writeJson(json);
	}
	
	
	
	/**签字审核窗口预览,列表显示*/
	public void loadRowsAndColumns() throws ParseException {
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
		String jsonStr = tblSchedulePlanList2RowsAndColumnsJson(
				tblSchedulePlanlist, isValidationPara, taskNamesList);
		writeJson(jsonStr);
	}

	/**表格显示*/
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
	/*
	public void loadRowsAndColumns3() throws ParseException{
		String jsonStr = tblSchedulePlanList2RowsAndColumnsJson3(starttime,endtime,sameTaskName,isValidationPara);
		writeJson(jsonStr);
	}*/
	
	/**传入参数开始时间结束时间任务名称是否显示空白日期*/
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
				calendar.add(Calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
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
					rowMap.put(obj.getTaskCode(), "&nbsp;&nbsp;√");
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
	
	/**相同项目*/
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
	
	/**表格显示*/
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
						calendar.add(Calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
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
		//	List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
			// 检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			// 列list（列 columns）
		//	List<List<Columns>> columnsList = tblSchedulePlanList2Columns(testIndexAbbrList, studyNoPara);
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
						rowMap.put(obj.getTaskName(), "&nbsp;&nbsp;√");
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

	/**列表显示*/
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
					calendar.add(Calendar.DATE, i);// 把日期往后增加一天.整数往后推,负数往前移动
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
			//List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
			// 检验指标缩写list
			List<String> testIndexAbbrList = new ArrayList<String>(testIndexAbbrSet);
			// 列list（列 columns）
			//List<List<Columns>> columnsList = tblSchedulePlanList2Columns2(testIndexAbbrList, studyNoPara);
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
		//}
		//return "";

	}
    
	/**列表显示*/
	public Map<String,List<List<Columns>>> tblSchedulePlanList2Columns(List<String> testIndexAbbrList, String studyNoPara) {
		List<Columns> list = new ArrayList<Columns>();
		Columns columns = null;
		
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

	/**表格显示*/
	public Map<String,List<List<Columns>>>  tblSchedulePlanList2Columns2(List<String> testIndexAbbrList, String studyNoPara) {
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
	
	
	
	/**人员树形下拉选*/
	public void loadComboTreeData() throws Exception{
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		//List<TblTaskType> tblTaskTypeList = tblTaskTypeService.getTblTaskTypeList();
		List<Department> departmentList = departmentService.findAll();
		
		ComboTreeModel ctm =null;
		for(Department department:departmentList){
			ctm = new ComboTreeModel();
			ctm.setId(department.getId()+"");
			ctm.setText(department.getName());
			Map<String, String> userMap = userService.getRealName(department.getId());
			List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
			for(Map.Entry<String, String> entry: userMap.entrySet())    {
				ComboTreeModel comboTreeModel = new ComboTreeModel();
				comboTreeModel.setId(entry.getKey());
				comboTreeModel.setText(entry.getValue());
				comboTreeModel.setIconCls("icon-space");
				children.add(comboTreeModel);
			}
			if(children.size()>0){
				ctm.setState("closed");
				ctm.setChildren(children);
				list.add(ctm);
			}
			
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		writeJson(json);
		
	}

	public void  schedulePlanSign(){
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		Json json = new Json();
		json.setSuccess(true);
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
		
		TblStudyPlan studyPlan = tblStudyPlanService.getByStudyNo(studyNoPara);
		if(studyPlan!=null)
		{
			studyPlan.setScheduleState(1);
			tblStudyPlanService.update(studyPlan);
		}
		
		//qa专题检查索引中更新日程有关信息
		QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoPara);
		if (studyChkIndex!=null) {
			studyChkIndex.setScheduleState(1);
			studyChkIndex.setScheduleSubmitTime(new Date());
			studyChkIndex.setScheduleChangedFlag(1);
			qAStudyChkIndexService.update(studyChkIndex);
		}
		
		
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
		if(list!=null&& !list.equals("")&&list.size()>0)
		{
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
	
	public String getTaskNames() {
		return taskNames;
	}

	public void setTaskNames(String taskNames) {
		this.taskNames = taskNames;
	}

	public String getLeaders() {
		return leaders;
	}

	public void setLeaders(String leaders) {
		this.leaders = leaders;
	}

	public String getTaskKind1() {
		return taskKind1;
	}

	public void setTaskKind1(String taskKind1) {
		this.taskKind1 = taskKind1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAllDate() {
		return allDate;
	}

	public void setAllDate(boolean allDate) {
		this.allDate = allDate;
	}

	public String getStudyNos() {
		return studyNos;
	}

	public void setStudyNos(String studyNos) {
		this.studyNos = studyNos;
	}

	public String getHouseNames() {
		return houseNames;
	}

	public void setHouseNames(String houseNames) {
		this.houseNames = houseNames;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public String[] getGetTaskNames() {
		return getTaskNames;
	}

	public void setGetTaskNames(String[] getTaskNames) {
		this.getTaskNames = getTaskNames;
	}

	public String getIsValidationPara() {
		return isValidationPara;
	}

	public void setIsValidationPara(String isValidationPara) {
		this.isValidationPara = isValidationPara;
	}

	public String getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(String tableHeight) {
		this.tableHeight = tableHeight;
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

	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}

	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}

	public TblAnimalHouseService getTblAnimalHouseService() {
		return tblAnimalHouseService;
	}

	public void setTblAnimalHouseService(TblAnimalHouseService tblAnimalHouseService) {
		this.tblAnimalHouseService = tblAnimalHouseService;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}

	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	
	
	

}
