package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.DictChkArea;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictScheduleChkItem;
import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkPlanChangeIndex;
import com.lanen.model.qa.QAChkPlanHis;
import com.lanen.model.qa.QAChkPlan_JSON;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.schedule.TblSchedulePlan_Json;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeField;
import com.lanen.model.schedule.TblTaskTypeJson;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.DictChkAreaService;
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictScheduleChkItemService;
import com.lanen.service.qa.QAApprovalOpinionService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkPlanChangeIndexService;
import com.lanen.service.qa.QAChkPlanHisService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAFileRegReaderService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblTaskTypeFieldService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.schdeule.TblTaskTypeService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class QAChkPlanAction extends BaseAction<QAChkPlan>{

	private static final long serialVersionUID = -4669858662570640845L;
	@Resource
	private QAChkPlanService qAChkPlanService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	@Resource
	private DictScheduleChkItemService dictScheduleChkItemService;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private TblTaskTypeService tblTaskTypeService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private UserService userService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblTaskTypeFieldService tblTaskTypeFieldService;
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private QAChkPlanHisService qAChkPlanHisService;
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private QAChkPlanChangeIndexService qAChkPlanChangeIndexService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private QAChkReportRecordService qAChkReportRecordService;
	@Resource
	private DictChkAreaService dictChkAreaService;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;
	@Resource
	private QAChkReportService qAChkReportService;
	@Resource
	private QAChkIndexService qAChkIndexService;
	@Resource
	private QAApprovalOpinionService qAApprovalOpinionService;
	@Resource
	private QAFileRegReaderService qAFileRegReaderService;
	
	
	private String studyNoParam;
	private String index;
	
	//left中的查找专题条件
	private Date start;
	private Date end;
	private String studyNoString;
	
	private Integer status;
	private Integer catalog;
	private String searchString;
	
	//日程名称
	private String taskName;
	private String scheduleChkItemId;
	
	private String chkPlanIds;
	private String oneChkPlanId;
	private String oneChildChkPlanId;
	private String indexTitle;
	private String selectChkReportCode;
	
	private String fileIndexId;//方案或者报告索引的id
	
	//private String scheduleId;
	private Integer dealScheduleChange;
	
	//任命计划qa
	private String qa;
	
	private String QAMainPage;
	
	private String versionStr;
	
	private String studyStatus;
	private Integer newReport;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	//private Date startDate;
	
	private Integer result;
	private String reason;
	private Integer justChkPlan;
	
	private String dictChkItemsStartDateList;
	private String planChkAreas;
	
	private String afterTabOpenAction;
	
 	public void putChkPlanIdInSession()
	{
		
		ActionContext.getContext().getSession().put("oneChkPlanId",oneChkPlanId);
		ActionContext.getContext().getSession().put("oneChildChkPlanId",oneChildChkPlanId);	
		ActionContext.getContext().getSession().put("newReportForReportPage",newReport);
	}
	public String qAChkPlanMain()
	{
	//	ActionContext.getContext().put("indexTitle", indexTitle);
		oneChkPlanId = (String)ActionContext.getContext().getSession().get("oneChkPlanId");
		ActionContext.getContext().getSession().put("oneChkPlanId","");
		
		if(oneChkPlanId!=null&&!"".equals(oneChkPlanId))
		{
			if(oneChkPlanId.startsWith("second5"))//检查报告
			{
				String reportId = oneChkPlanId.substring(7);
				//QAChkReportRecord reportRecord2 = qAChkReportRecordService.getById(reportRecord);
				//ActionContext.getContext().put("selectReportRecord", reportRecord);
				QAChkReport report = qAChkReportService.getById(reportId);
				String studyNo = qAChkIndexService.getStudyNoByReportCode(reportId);
				ActionContext.getContext().put("selectChkReportCode", report.getChkReportCode());
				ActionContext.getContext().put("selectStudyNoPara", studyNo);
				ActionContext.getContext().put("indexText",4);
				
			}else if(oneChkPlanId.startsWith("second1")){//13任命QA，11接收专题方案，11检查专题方案，12接收专题报告，12检查专题报告
				Integer flag = Integer.valueOf(oneChkPlanId.substring(7,8));
				
				ActionContext.getContext().put("afterTabOpenAction",flag);
				
				String studyStudyNo = oneChkPlanId.substring(8);
				
				ActionContext.getContext().put("indexText",0);
				ActionContext.getContext().put("selectStudyNoPara", studyStudyNo);
			}else if(oneChkPlanId.startsWith("second2")){//未制定检查计划，检查计划待审批，检查计划变更待审批，日程变更待修改检查计划
				String studyStudyNo = oneChkPlanId.substring(7);
				
				ActionContext.getContext().put("indexText",1);//检查计划
				ActionContext.getContext().put("selectStudyNoPara", studyStudyNo);
			}else {
				QAChkPlan plan = qAChkPlanService.getById(oneChkPlanId);
				
				ActionContext.getContext().put("indexText",2);//检查记录
				String planStudyNo = plan.getQastudyChkIndex().getStudyNo();
				ActionContext.getContext().put("selectChkPlanId", oneChkPlanId);
				ActionContext.getContext().put("selectStudyNoPara", planStudyNo);
				
			}
			
		}
		return "qAChkPlanMain";
	}
	public String left()
	{
		start = DateUtil.getTodayDate();
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(start);
		calendar1.add(Calendar.DATE, 6);
		end = calendar1.getTime();
		ActionContext.getContext().put("startTime", DateUtil.dateToString(start, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(end, "yyyy-MM-dd"));
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean  falg = userService.checkPrivilege(user, "FM");
		boolean  falg1 = userService.checkPrivilege(user, "SD");
		boolean  falg2 = userService.checkPrivilege(user, "QA负责人");
		boolean  falg3 = userService.checkPrivilege(user, "QA");
		boolean  falg4 = userService.checkPrivilege(user, "病理负责人");
		boolean  falg5 = userService.checkPrivilege(user, "病理");
		if(falg){
			ActionContext.getContext().getSession().put("role", "FM");
		}else if(falg1){
			ActionContext.getContext().getSession().put("role", "SD");
		}else if(falg2){
			ActionContext.getContext().getSession().put("role", "QALead");
		}else if(falg3){
			ActionContext.getContext().getSession().put("role", "QA");
		}else if(falg4){
			ActionContext.getContext().getSession().put("role", "PathSDLead");
		}else if(falg5){
			ActionContext.getContext().getSession().put("role", "PathSD");
		}
        boolean falg6  = userService.checkPrivilege(user, "部门负责人");
		ActionContext.getContext().getSession().put("department", falg6);
		
		ActionContext.getContext().put("indexText",indexTitle);
		if(studyNoParam!=null&&!"".equals(studyNoParam))
		{
			ActionContext.getContext().put("selectStudyNoPara", studyNoParam);
			//ActionContext.getContext().put("selectSD", sd);
			//ActionContext.getContext().put("selectQA", qa);
		}
		if(oneChkPlanId!=null&&!"".equals(oneChkPlanId))
		{
			ActionContext.getContext().put("oneChkPlanId", oneChkPlanId);
		}
		if(selectChkReportCode!=null&&!"".equals(selectChkReportCode))
		{
			ActionContext.getContext().put("selectChkReportCode", selectChkReportCode);
		}
		if(afterTabOpenAction!=null&&!"".equals(afterTabOpenAction))
		{
			ActionContext.getContext().put("afterTabOpenAction", afterTabOpenAction);
		}
		return "left";
	}
	public String main()
	{
	   //studyNoParam=,oneChkPlanId,selectChkReportCode,index
		ActionContext.getContext().put("index",index);
		ActionContext.getContext().put("studyNoParam",studyNoParam);
		ActionContext.getContext().put("fileIndexId",fileIndexId);
			
		//检查计划的id
		ActionContext.getContext().put("oneChkPlanId", oneChkPlanId);
		//报告的报告编号
		ActionContext.getContext().put("selectChkReportCode", selectChkReportCode);
		ActionContext.getContext().put("afterTabOpenAction",afterTabOpenAction);
		
		return "main";
	}
	//基于研究的检查计划主页面和QA主页面的检查计划
	public String list()
	{

			User user = (User) ActionContext.getContext().getSession().get("user");
			boolean  falg = userService.checkPrivilege(user, "FM");
			boolean  falg1 = userService.checkPrivilege(user, "SD");
			boolean  falg2 = userService.checkPrivilege(user, "QA负责人");
			boolean  falg3 = userService.checkPrivilege(user, "QA");
			boolean  falg4 = userService.checkPrivilege(user, "病理负责人");
			boolean  falg5 = userService.checkPrivilege(user, "病理");
			if(falg){
				ActionContext.getContext().getSession().put("role", "FM");
			}else if(falg1){
				ActionContext.getContext().getSession().put("role", "SD");
			}else if(falg2){
				ActionContext.getContext().getSession().put("role", "QALead");
			}else if(falg3){
				ActionContext.getContext().getSession().put("role", "QA");
			}else if(falg4){
				ActionContext.getContext().getSession().put("role", "PathSDLead");
			}else if(falg5){
				ActionContext.getContext().getSession().put("role", "PathSD");
			}
	        boolean falg6  = userService.checkPrivilege(user, "部门负责人");
			ActionContext.getContext().getSession().put("department", falg6);
		
		
		
		
		if(studyNoParam!=null)//基于研究的主页面
		{
			ActionContext.getContext().put("studyNoParam",studyNoParam);
			if(!"".equals(studyNoParam))//选了专题的情况
			{
				//进入检查计划之前要检查日程是否有变更
				QAStudyChkIndex studyIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
				if(studyIndex!=null)
				{
					//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
					QAChkPlanChangeIndex change = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
					if(change!=null)
					{
						ActionContext.getContext().put("chkPlanChange",change.getChangeState());
					}else{
						ActionContext.getContext().put("chkPlanChange",-1);//不存在赋值-1
					}
					ActionContext.getContext().put("chkPlanState",studyIndex.getChkPlanState());
					ActionContext.getContext().put("studyReportState",studyIndex.getReportState());
					
				}
				if(studyIndex!=null&&studyIndex.getScheduleChangedFlag()!=null&&studyIndex.getScheduleChangedFlag()==1)//（动态标志，由SD提交日程所触发）0：无变更，1：变更，2：变更处理完毕
				{
					//有日程变更
					ActionContext.getContext().put("scheduleChanged",true);
					
				}
			}
			
		}else {
			ActionContext.getContext().put("QAPage","qaMainPage");
		}
		
		//User user = (User)ActionContext.getContext().getSession().get("user");
		String qa = null;
		QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(index!=null&&index.getReportState()!=null&&index.getReportState()==1)
		{
			ActionContext.getContext().put("haveChkPlanRight", false);
		}else {
			
			if(index!=null&&index.getInspector()!=null)
			{
				qa = index.getInspector();
			}else {
				TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				if(item!=null&&item.getQa()!=null)
					qa = item.getQa();			
			}
			if (user.getRealName().equals(qa)) {
				ActionContext.getContext().put("haveChkPlanRight", true);
			}else {
				ActionContext.getContext().put("haveChkPlanRight", false);
			}
			
		}
		Date reportStartDate = DateUtil.getTodayDate();
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(reportStartDate);
		calendar1.add(Calendar.DATE, 6);
		Date reportEndDate = calendar1.getTime();
		ActionContext.getContext().put("planStartDate", DateUtil.dateToString(reportStartDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("planEndDate",  DateUtil.dateToString(reportEndDate, "yyyy-MM-dd"));
		if(index!=null&&((index.getReportState()!=null&&index.getReportState()==1)||(index.getChkPlanFinishFlag()!=null&&index.getChkPlanFinishFlag()==1)))
		{
			ActionContext.getContext().put("studyFinishForPlan",  true);	
		}else {
			ActionContext.getContext().put("studyFinishForPlan",  false);	
		}
		
		return "list";
	}
	
	
	//left datagrid 数据
	public void getStudyList()
	{
		List<Map<String,Object>> listMaps = new ArrayList<Map<String,Object>>();
		Integer studyState=-1;
		if(studyStatus!=null)//选择的是未完成专题的
		{
			if(studyNoString==null)
				studyNoString="";
			studyState = Integer.parseInt(studyStatus);
			
		}else if(start==null||end==null)//选择的是已完成专题的
		{
			end = DateUtil.getTodayDate();
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(end);
			calendar1.add(Calendar.DATE, -6);
			start = calendar1.getTime();
		}
		User user = getCurrentUser();
		boolean qaLead = userService.checkPrivilege(user, "QA负责人");
		boolean isqa = userService.checkPrivilege(user, "QA");
		String realName="";
		if(qaLead){
			realName="";
		}else if(isqa){
			realName=user.getRealName();
		}
		//List<String> studyplans = tblStudyPlanService.getByStartDateAndCondition(start,end,studyNoString);
		List<Map<String,Object>> studyplans = qAStudyChkIndexService.getStudyNoByDateAndCondition(studyState,start,end,studyNoString,realName);
		if(studyplans!=null)
		{
			for(Map<String,Object> studyNoMap:studyplans)
			{
				//chkStudyNo.studyNo,item.id itemId,case when chkStudyNo.inspector is not null then chkStudyNo.inspector else item.qa end as qa,dictType.studyTypeCode,dictType.studyName
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("qa", studyNoMap.get("qa"));	
				map.put("itemId",studyNoMap.get("itemId") );
				if(studyNoMap.get("itemId")!=null&&!"".equals(studyNoMap.get("itemId")))
				{
					map.put("noItem", false);
				}else {
					map.put("noItem", true);
				}
				
				if(studyNoMap.get("reportState")!=null&&(Integer)studyNoMap.get("reportState")==1)
				{
					map.put("finish",true);
				}
				
				map.put("studyNo",studyNoMap.get("studyNo") );
				//map.put("studyName",studyName );
				map.put("sd",studyNoMap.get("sd") );
				
				map.put("studyTypeName",studyNoMap.get("studyName") );					
				
				listMaps.add(map);
			}
		}
		String json= JsonPluginsUtil.beanListToJson(listMaps);
		
		writeJson(json);
		
		
	}
	//添加检查计划的日程datagrid
	public void getScheduleList()
	{
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		List<TblSchedulePlan_Json> tblSchedulePlanlist = tblSchedulePlanService.getHasSubmitSchedulePlanJson(2,studyNoParam, 2);
		if(tblSchedulePlanlist!=null)
		{
			for(TblSchedulePlan_Json plan:tblSchedulePlanlist)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planDate",plan.getDateTime());
				map.put("taskName", plan.getTaskName());
				map.put("number", plan.getNumber());
				map.put("scheduleId", plan.getScheduleID());
				map.put("id",plan.getScheduleID()+"-"+plan.getDateTime());
				
				boolean flag = false;
				Map<String, Object> existMap = null;
				for(Map<String, Object> tempMap:listMaps)
				{
					if(tempMap.get("scheduleId").equals(plan.getScheduleID()))
					{
						flag = true;
						existMap = tempMap;
						
						break;
					}
				}
				if(flag)//maplist中存在，证明是周期日期
				{
					existMap.put("state", "closed");
					
					List<Map<String, Object>> children = null;
					if(existMap.get("children")!=null)
					{
						children = (List<Map<String, Object>>)existMap.get("children");
					}else {
						children = new ArrayList<Map<String,Object>>();  
						existMap.put("children", children);
					}
					children.add(map);
						
				}else {	//不存在
					listMaps.add(map);
				}
				
			}
		}
		String json = JsonPluginsUtil.beanListToJson(listMaps);
		writeJson(json);
	
	}
	public void getChkItemBySchedule()
	{
		List<Map<String, Object>> listMaps=new ArrayList<Map<String,Object>>();
		List<DictScheduleChkItem> items = dictScheduleChkItemService.getByScheduleName(model.getTaskName());
		for(DictScheduleChkItem item:items)
		{
			if(item.getDictQACheckItem().getChkItemType()==4)//1：方案；2：报告；3：变更；4：基于研究的检查项
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("chkItemName", item.getChkItemName());
				map.put("chkItemId", item.getDictQACheckItem().getChkItemId());
				map.put("scheduleChkItemId", item.getScheduleChkItemId());
				//map.put("state", "closed");
				//List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
				//DictQACheckItem dictQACheckItem=item.getDictQACheckItem();
				//List<DictChkItemChkTblReg> tblList = dictChkItemChkTblRegService.getByChkItemId(dictQACheckItem.getChkItemId());
				//Set<DictChkItemChkTblReg> set=dictQACheckItem.getDictChkItemChkTblRegs();//直接获取会出现nosession错误//检查项使用表格
				/*不要检查表了
				for(DictChkItemChkTblReg tblreg:tblList)
				{
					Map<String, Object> childMap = new HashMap<String, Object>();
					childMap.put("scheduleChkItemId", item.getScheduleChkItemId());
					
					childMap.put("chkItemId", tblreg.getDictQacheckItem().getChkItemId());
					
					childMap.put("chkTblId", tblreg.getDictQacheckTable().getChkTblId());
					childMap.put("chkTblName", tblreg.getDictQacheckTable().getChkTblName());
					childMap.put("chkItemName", "");
					children.add(childMap);
					
				}
				
				map.put("children",children);
				*/
				listMaps.add(map);
			}
		}
		String json = JsonPluginsUtil.beanListToJson(listMaps);
		writeJson(json);
		
	}
	public void getPlanListByCondition()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		if(QAMainPage!=null&&!"".equals(QAMainPage))
			studyNoParam = null;//是主页面
		List<Map<String,Object>> plans = qAChkPlanService.getByStudyNoAndCondition(studyNoParam,start,end,status,catalog,searchString);
		/*if(studyNoParam!=null)//一个专题的情况
		*/
		
		List<QAChkPlan_JSON> planJsons=new ArrayList<QAChkPlan_JSON>();
		//检查计划的记录
		for(Map<String,Object> plan:plans)
		{
			String inspector = (String)plan.get("inspector");
			Integer planState = (Integer)plan.get("chkPlanState");//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			
			if(!user.getRealName().equals(inspector)&&!user.getRealName().equals(plan.get("planChkOperator"))
					&&!userService.checkPrivilege(user, "QA负责人"))
			{
				continue;
			}
			if(!user.getRealName().equals(inspector)&&(planState==null||planState!=2))//如果不是qa，则state必须是2
			{
				continue;
			}	
			/*[chkPlanID],[chkPlanType] ,splan.[studyNo] ,[ScheduleChkItemID] ,[chkItemID] ,[chkItemName] ,[taskNameID] ,[taskName]
		      ,[scheduleID] ,[ScheduleName] ,[createTime] ,[planChkTime] ,[planChkArea] ,[chkFinishedFlag] ,[planChkOperator]
		      ,[chkOperator] ,[chkTime] ,[chkPlanVersion] ,[scheduleTime] ,[chkIndexID],[number],[SOPFlag],[tempChkOperatorFlag]
		      ,[tempChkOperator],[tempChkOperatorApplyTime],[tempChkOperatorApprovalTime],item.studyName*/
			QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
			planJSON.setStudyNo((String)plan.get("studyNo"));
			planJSON.setStudyName((String)plan.get("studyName"));
			planJSON.setChkFinishedFlag((Integer)plan.get("chkFinishedFlag"));
			//planJSON.setChkIndexID((String)plan.get("chkIndexID"));
			planJSON.setChkItemId((String)plan.get("chkItemID"));
			planJSON.setChkItemName((String)plan.get("chkItemName"));
			planJSON.setChkOperator((String)plan.get("chkOperator"));
			planJSON.setChkPlanId((String)plan.get("chkPlanID"));
			planJSON.setChkPlanType((Integer)plan.get("chkPlanType"));
			planJSON.setChkPlanVersion((Integer)plan.get("chkPlanVersion"));
			planJSON.setChkTime((Date)plan.get("chkTime"));
			planJSON.setCreateTime((Date)plan.get("createTime"));
			planJSON.setPlanChkArea((String)plan.get("planChkArea"));
			planJSON.setPlanChkOperator((String)plan.get("planChkOperator"));
			planJSON.setPlanChkTime((Date)plan.get("planChkTime"));
			//planJSON.setQastudyChkIndex((String)plan.get("qastudyChkIndex"));
			planJSON.setScheduleChkItemId((String)plan.get("scheduleChkItemID"));
			planJSON.setScheduleId((String)plan.get("scheduleID"));
			planJSON.setScheduleName((String)plan.get("scheduleName"));
			planJSON.setScheduleTime((Date)plan.get("scheduleTime"));
			planJSON.setTaskName((String)plan.get("taskName"));
			planJSON.setTaskNameId((String)plan.get("taskNameID"));
			planJSON.setChkPlanState((Integer)plan.get("chkPlanState"));//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			planJSON.setStudyReportState((Integer)plan.get("reportState"));
			planJSON.setNumber((Integer)plan.get("number"));
			planJSON.setSOPFlag((Integer)plan.get("SOPFlag"));
			planJSON.setTempChkOperator((String)plan.get("tempChkOperator"));
			planJSON.setTempChkOperatorFlag((Integer)plan.get("tempChkOperatorFlag"));
			planJSON.setOperation("开始检查");
			
			if((plan.get("chkPlanFinishFlag")!=null&&(Integer)plan.get("chkPlanFinishFlag")==1)
					||(plan.get("reportState")!=null&&(Integer)plan.get("reportState")==1))
			{
				planJSON.setHaveChkPlanRight(false);
			}else {
				//计划检查人员
				if(user.getRealName().equals(plan.get("chkOperator"))
						||user.getRealName().equals(plan.get("planChkOperator"))
						//||(user.getRealName().equals(inspector)&&(plan.getChkOperator()==null||"".equals(plan.getChkOperator())))
						)
				{
					planJSON.setHaveChkPlanRight(true);
				}else{
					planJSON.setHaveChkPlanRight(false);
				}
			}
				
			
			planJsons.add(planJSON);
		}
		
		// 	<option value="0">待任命QA</option>
  		// 	<option value="5">专题方案待接收</option>
  		 //	<option value="1">未制定检查计划</option>
  		// 	<option value="2">检查计划待审批</option>
  		// 	<option value="3">申请计划变更待审批</option>
  		// 	<option value="4">日程变更需修改检查计划</option>
  		// 	<option value="6">专题待检查</option>
  		// 	<option value="7">报告待审批</option>
  		// 	<option value="8">报告待处理回复或延迟</option>
  		// 	<option value="9">报告待再检查</option>
  		// 	<option value="10">专题报告待接收</option>
		
		//加上待任命QA，和检查计划以及方案报告的状态
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		boolean isQAM = false;
		if((justChkPlan==null||justChkPlan!=1)//不是仅检查计划
				&&(QAMainPage!=null&&!"".equals(QAMainPage)))//主页面
		{
			if(userService.checkPrivilege(user, "QA负责人"))
			{
				isQAM = true;
				mapList= qAStudyChkIndexService.getAllStatesByCondition(studyNoParam,null,status,catalog,searchString);
			}else{
				mapList= qAStudyChkIndexService.getAllStatesByCondition(studyNoParam,user,status,catalog,searchString);
			}
		}
		for(Map<String,Object> map:mapList)
		{
			String studyNo1 = (String)map.get("studyNo");
			String studyName = (String)map.get("studyName");
			String inspector1 = (String)map.get("inspector");
			//chkStudyNo.studyNo,inspector,chkPlanState,[changeState],chkStudyNo.scheduleChangedFlag,chkStudyNo.confirmer,fileType
			Integer chkPlanState1 = (Integer)map.get("chkPlanState");
			//Integer changeState1 = (Integer)map.get("changeState");
			//Integer scheduleChangedFlag1 = (Integer)map.get("scheduleChangedFlag");
			//String confirmer1 = (String)map.get("confirmer");
			//String fileType1 = (String)map.get("fileType");
			
			String scheduleChangeState1 = (String)map.get("scheduleChangeState");//待任命QA,检查计划未制定完成，检查计划变更待审批,日程变更需修改检查计划
			String studyFileState1 = (String)map.get("studyFileState");//专题方案待接收,专题方案待检查,专题报告待接收,专题报告待检查
			if(isQAM&&"待任命QA".equals(scheduleChangeState1)){
				TblStudyItem item = tblStudyItemService.getByStudyNoStudyItem(studyNo1);
				String itemId="";
				if(item!=null)
					itemId = item.getId();
				generateOnePlan(planJsons, studyNo1,studyName,"待任命QA", "待任命QA", "second13"+studyNo1,user,isQAM,"任命QA检查员",itemId,"任命专题QA检查员");
				
			}else if("检查计划未制定完成".equals(scheduleChangeState1))	{
				if(chkPlanState1==null||chkPlanState1==0)//0：草稿；1：提交；-1：QAM否决；2：通过
				{	
					if(user.getRealName().equals(inspector1))
					{
						generateOnePlan(planJsons, studyNo1,studyName,"未制定检查计划", "未制定检查计划", "second2"+studyNo1,user,true,"制定检查计划","","");
					}else{//QAM不显示未制定检查计划
						//generateOnePlan(planJsons, studyNo1, "未制定检查计划", "needPlan"+studyNo1,user,false,"制定检查计划");
					}
					
				}else if(chkPlanState1==1){
					if(isQAM){
						generateOnePlan(planJsons, studyNo1,studyName,"检查计划待审批", "检查计划待审批", "second2"+studyNo1,user,true,"审批检查计划","","同意检查计划");
					}
				}
			}else if("检查计划变更待审批".equals(scheduleChangeState1)){
				if(isQAM){
					//没有检查计划变更的制定，申请过变更还没提交的状态
					generateOnePlan(planJsons, studyNo1,studyName,"检查计划变更待审批", "检查计划变更待审批", "second2"+studyNo1,user,true,"审批检查计划变更","","同意检查计划变更");
				}
			}else if("日程变更需修改检查计划".equals(scheduleChangeState1)){
				if(user.getRealName().equals(inspector1)){
					generateOnePlan(planJsons, studyNo1,studyName,"日程变更需修改检查计划", "日程变更需修改检查计划", "second2"+studyNo1,user,true,"日程变更修改检查计划","","");
				}
			}
			if("专题方案待接收".equals(studyFileState1)){
				if(user.getRealName().equals(inspector1)){
					generateOnePlan(planJsons, studyNo1,studyName,"专题方案待接收", "专题方案待接收", "second11"+studyNo1,user,true,"接收专题方案","","");
				}
			}else if("专题方案待检查".equals(studyFileState1)){
				if(user.getRealName().equals(inspector1)){
					generateOnePlan(planJsons, studyNo1,studyName,"专题方案待检查", "专题方案待检查", "second11"+studyNo1,user,true,"检查专题方案","","");
				}
			}else if("专题报告待接收".equals(studyFileState1)){
				if(user.getRealName().equals(inspector1)){
					generateOnePlan(planJsons, studyNo1,studyName,"专题报告待接收", "专题报告待接收", "second12"+studyNo1,user,true,"接收专题报告","","");
				}
			}else if("专题报告待检查".equals(studyFileState1)){
				if(user.getRealName().equals(inspector1)){
					generateOnePlan(planJsons, studyNo1,studyName,"专题报告待检查", "专题报告待检查", "second12"+studyNo1,user,true,"检查专题报告","","");
				}
			}
			
			
		}
		//加上report的一些操作
		List<Map<String, Object>> reportMapList = new ArrayList<Map<String,Object>>();
		
		if((justChkPlan==null||justChkPlan!=1)//不是仅检查计划
				&&(QAMainPage!=null&&!"".equals(QAMainPage)))//主页面
		{
			if(isQAM)
			{
				reportMapList = qAChkReportService.getNeedDoByCondition(studyNoParam,null,status,catalog,searchString);
			}else{
				reportMapList = qAChkReportService.getNeedDoByCondition(studyNoParam,user,status,catalog,searchString);
				
			}
		}
		//,start,end只对延迟整改有效
		if(reportMapList!=null&&reportMapList.size()>0)
		{
			for(Map<String, Object> map:reportMapList)
			{
				//distinct(report.[chkReportCode]),studyNo,[rptState],[needReply],[replyState] "+
				//,[replyFMApprovalTime],[replyFMName],[replyFMReviewResult],[replyFMReveiwRemark],
				//[replyFMReveiwTime],[replyInspectorReceiveTime],[replyInspectorReciveName] "+
			//	,[needReChk],[reChkState],[needDelay],[delayState],[delayFMApprovalTime],[delayFMName],
				//[delayQAUReceiveTime],[delayQAUReceiveName] "+
				String chkReportCode = (String)map.get("chkReportCode");
				String studyNo = (String)map.get("studyNo");
				String studyName = (String)map.get("studyName");
				String inspector = (String)map.get("inspector");
				Integer rptState = (Integer)map.get("rptState");
				Integer replyState = (Integer)map.get("replyState");
				Date replyFMApprovalTime = (Date)map.get("replyFMApprovalTime");
				String replyFMName = (String)map.get("replyFMName");
				Integer replyFMReviewResult = (Integer)map.get("replyFMReviewResult");
				String replyFMReveiwRemark = (String)map.get("replyFMReveiwRemark");
				Date replyFMReveiwTime = (Date)map.get("replyFMReveiwTime");
				Date replyInspectorReceiveTime = (Date)map.get("replyInspectorReceiveTime");
				String replyInspectorReciveName = (String)map.get("replyInspectorReciveName");
				Integer needReChk = (Integer)map.get("needReChk");
				Integer reChkState = (Integer)map.get("reChkState");
				Integer needDelay = (Integer)map.get("needDelay");
				Integer delayState = (Integer)map.get("delayState");
				Date delayFMApprovalTime = (Date)map.get("delayFMApprovalTime");
				String delayFMName = (String)map.get("delayFMName");
				Date delayQAUReceiveTime = (Date)map.get("delayQAUReceiveTime");
				String delayQAUReceiveName = (String)map.get("delayQAUReceiveName");
				//qachkReport.needDelay=3 and (qachkReport.delayState is null or qachkReport.delayState=0
				//0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
				if(rptState==1)
				{
					//QAM待审批
					if(isQAM)
					{
						generateOnePlan(planJsons, studyNo,studyName,chkReportCode, "QAM待审批报告", "second5"+chkReportCode, user, true, "审批报告","","同意检查报告");
					}
						
				}else if(rptState==-1){
					//QA的
					if(user.getRealName().equals(inspector))
					{
						generateOnePlan(planJsons, studyNo,studyName, chkReportCode,"QAM退回报告", "second5"+chkReportCode, user, true, "编辑报告","","");
					}
					
				}else if(rptState==3){
					//FM和SD的不用处理
					//QA待接收回复或延迟整改
					if(user.getRealName().equals(inspector)){
						String receiveType = "";
						//replyFMApprovalTime!=null
						if(replyState!=null&&replyState==2&&(replyInspectorReceiveTime==null||"".equals(replyInspectorReceiveTime))){
							receiveType = "回复";
						}
						//delayFMApprovalTime!=null
						if(needDelay!=null&&needDelay==2&&(delayQAUReceiveTime==null||"".equals(delayQAUReceiveTime)))
						{
							if("".equals(receiveType))
							{
								receiveType = "延迟";
							}else{
								receiveType = "回复和延迟";
							}
						}
						if(!"".equals(receiveType))
							generateOnePlan(planJsons, studyNo,studyName,chkReportCode, "QA待接收"+receiveType, "second5"+chkReportCode, user, true, "接收报告"+receiveType,"","");
					}
						
				}else if(rptState==4||rptState==5){
					if(user.getRealName().equals(inspector)){
						//rptState=3 and (needReChk=1 or (needDelay=3 and delayState=0)) 
						//needDelay 0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
						//replyState 0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
						if(needReChk!=null&&needReChk==1){//
							generateOnePlan(planJsons,studyNo,studyName, chkReportCode, "QA待再检查回复", "second5"+chkReportCode, user, true, "QA再检查报告","","");
						}else if(needDelay==3 && delayState==0){//延迟整改
							generateOnePlan(planJsons,studyNo,studyName, chkReportCode, "QA待再检查延迟整改", "second5"+chkReportCode, user, true, "QA再检查报告","","");
							//根据报告获取延迟整改的日期。一个报告可以有多条
						}
					}
				}
				
				
			}
		}
		
		/*
		//加上report的延迟整改信息
		List<QAChkReportRecord> delayList = qAChkReportRecordService.getByCondition(studyNoParam,start,end,status,catalog,searchString);
		
		for(QAChkReportRecord reportRecord:delayList)
		{
			if(!user.getRealName().equals(reportRecord.getInspector())
					&&!userService.checkPrivilege(user, "QA负责人"))
			{
				continue;
			}
			QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
			planJSON.setStudyNo(reportRecord.getStudyNo());
			//"chkPlanId","chkPlanState","studyNo", "chkPlanType","chkItemId","chkItemName",
			//"scheduleTime","scheduleName","createTime","planChkTime","planChkArea",
			//"chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion",
			//"haveChkPlanRight","studyReportState"
			planJSON.setChkFinishedFlag(0);//0：未完成；1：已完成；-1取消
			//planJSON.setChkIndex(plan.getChkIndex());
			planJSON.setChkItemId("");
			planJSON.setChkItemName("延迟整改检查");
			planJSON.setChkOperator("");//还没有检查
			planJSON.setChkPlanId("second5"+reportRecord.getChkReportRecordId());
			planJSON.setChkPlanType(1);//1:研究；2：过程；3：设施
			planJSON.setChkPlanVersion(1);
			planJSON.setChkTime(null);
			planJSON.setCreateTime(reportRecord.getDelayQaconfirmTime());
			planJSON.setPlanChkArea("");
			planJSON.setPlanChkOperator(reportRecord.getInspector());
			planJSON.setPlanChkTime(reportRecord.getDelayPlanFinishDate());
			//planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
			//planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
			planJSON.setScheduleName("延迟整改检查");
			planJSON.setScheduleTime(reportRecord.getDelayPlanFinishDate());
			planJSON.setTaskName("延迟整改检查");
			planJSON.setTaskNameId("延迟整改检查");
			planJSON.setChkPlanState(2);//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			planJSON.setStudyReportState(3);
			planJSON.setRemark("延迟整改检查");
			planJSON.setSOPFlag(-1);
			planJSON.setOperation("检查延迟");
			
			//记录检查人员
			if(user.getRealName().equals(reportRecord.getInspector()))
			{
				planJSON.setHaveChkPlanRight(true);
			}else{
				planJSON.setHaveChkPlanRight(false);
			}
			
			planJsons.add(planJSON);
			
		}*/
		List<QAFileRegReader> fileNeedReadList = new ArrayList<QAFileRegReader>();
		//加上学习任务
		if((justChkPlan==null||justChkPlan!=1)//不是仅检查计划
				&&(QAMainPage!=null&&!"".equals(QAMainPage)))//主页面
		{
			fileNeedReadList = qAFileRegReaderService.getByUser(user, 1);
		}
		for(QAFileRegReader fileNeedRead:fileNeedReadList)
		{
			/*String fileNames = "";
			List<QALearnTaskFile> files = qALearnTaskFileService.getFileListByTaskId(fileNeedRead.getQalearnTask().getLearnTaskId());
			for(QALearnTaskFile taskFile:files)
			{
				QAFileReg fileReg = qAFileRegService.getById(taskFile.getFileRegId());
				fileNames+=fileReg.getFileName()+",";
			}
			if(fileNames.endsWith(","))
			{
				fileNames=fileNames.substring(0, fileNames.length()-1);
			}*/
			//generateOnePlan(plans2,studyNo1,scheduleName， taskName,planId, user ,boolean haveRight,String operation,String remark,String quickOperate)
			generateOnePlan(planJsons, "","", "未完成学习任务",fileNeedRead.getQalearnTask().getPurpose(), "second3"+fileNeedRead.getQalearnTask().getLearnTaskId(), user, true, "去学习","","");
			
		}
		
		
		
		try {
			Collections.sort(planJsons, new Comparator<QAChkPlan_JSON>() {
				
				public int compare(QAChkPlan_JSON arg0, QAChkPlan_JSON arg1) {
					// TODO Auto-generated method stub
					if((arg0==null||arg0.getPlanChkTime()==null)&&(arg1!=null&&arg1.getPlanChkTime()!=null))
					{
						return -1;
					}else if((arg1==null||arg1.getPlanChkTime()==null)&&(arg0!=null&&arg0.getPlanChkTime()!=null))
					{
						return 1;
					}else if((arg1==null||arg1.getPlanChkTime()==null)&&(arg0==null||arg0.getPlanChkTime()==null))
					{
						//按照日期，检查区域，专题排序。
						if((arg0==null||arg0.getPlanChkArea()==null)&&(arg1!=null&&arg1.getPlanChkArea()!=null))
							return -1;
						else if((arg1==null||arg1.getPlanChkArea()==null)&&(arg0!=null&&arg0.getPlanChkArea()!=null))
							return 1;
						else if((arg1!=null&&arg1.getPlanChkArea()!=null)&&(arg0!=null&&arg0.getPlanChkArea()!=null))
						{
							if((arg0==null||arg0.getStudyNo()==null)&&(arg1!=null&&arg1.getStudyNo()!=null))
							{
								return -1;
							}else if((arg1==null||arg1.getStudyNo()==null)&&(arg0!=null&&arg0.getStudyNo()!=null)){
								return 1;
							}else if((arg1!=null&&arg1.getStudyNo()!=null)&&(arg0!=null&&arg0.getStudyNo()!=null)){
								return arg1.getStudyNo().compareTo(arg0.getStudyNo());
							}else{
								return 0;
							}
							
						}else if((arg1!=null&&arg1.getPlanChkArea()!=null)&&(arg0!=null&&arg0.getPlanChkArea()!=null)){
							return arg1.getPlanChkArea().compareTo(arg0.getPlanChkArea());
						}else{
							return 0;
						}
						
					}else if((arg1!=null&&arg1.getPlanChkTime()!=null)&&(arg0!=null&&arg0.getPlanChkTime()!=null)){
						return arg1.getPlanChkTime().compareTo(arg0.getPlanChkTime());
					}else{
						return 0;
					}
					
				}
			});			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] _nory_format = {"chkPlanId","chkPlanState","studyNo","studyName", "chkPlanType","chkItemId","chkItemName","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","taskName","chkPlanVersion","haveChkPlanRight","studyReportState","remark",
				"number","SOPFlag","tempChkOperator","tempChkOperatorFlag","operation","quickOperate"};
		String json = JsonPluginsUtil.beanListToJson(planJsons, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);		
		
		
	}
	
	public void generateOnePlan(List<QAChkPlan_JSON> plans2,String studyNo1,String studyName,String scheduleName,String taskName,String planId,User user ,boolean haveRight,String operation,String remark,String quickOperate)
	{
		
		QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
		planJSON.setStudyNo(studyNo1);
		planJSON.setStudyName(studyName);
		planJSON.setChkFinishedFlag(0);//0：未完成；1：已完成；-1取消
		planJSON.setChkItemId("");
		planJSON.setChkItemName(taskName);
		planJSON.setChkOperator("");//还没有检查
		planJSON.setChkPlanId(planId);
		planJSON.setChkPlanType(1);//1:研究；2：过程；3：设施
		planJSON.setChkPlanVersion(1);
		planJSON.setChkTime(null);
	//	planJSON.setCreateTime("");
		planJSON.setPlanChkArea("");
		planJSON.setPlanChkOperator(user.getRealName());//当前QAM检查
		//planJSON.setPlanChkTime(reportRecord.getDelayPlanFinishDate());
		//planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
		//planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
		planJSON.setScheduleName(scheduleName);
		//planJSON.setScheduleTime(reportRecord.getDelayPlanFinishDate());
		planJSON.setTaskName(taskName);
		planJSON.setTaskNameId(taskName);
		planJSON.setChkPlanState(2);//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
		planJSON.setStudyReportState(1);
		planJSON.setRemark(taskName);
		planJSON.setSOPFlag(-1);
		planJSON.setOperation(operation);
		
		planJSON.setRemark(remark);//当是任命QA的时候是itemId
		planJSON.setQuickOperate(quickOperate);
		
		planJSON.setHaveChkPlanRight(haveRight);
		
		plans2.add(planJSON);
	}
	
	public void getPlanListByVersion()
	{
		List<QAChkPlan_JSON> planJsons=new ArrayList<QAChkPlan_JSON>();
		if(versionStr.contains(","))
		{
			//String realVersion = versionStr.substring(0, versionStr.indexOf(","));
			//qachkplan只有一个版本
			List<QAChkPlan> plans = qAChkPlanService.getByStudyNo(studyNoParam);
			for(QAChkPlan plan:plans)
			{
				QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
				planJSON.setStudyNo(plan.getQastudyChkIndex().getStudyNo());
				planJSON.setChkFinishedFlag(plan.getChkFinishedFlag());
				planJSON.setChkIndex(plan.getChkIndex());
				planJSON.setChkItemId(plan.getChkItemId());
				planJSON.setChkItemName(plan.getChkItemName());
				planJSON.setChkOperator(plan.getChkOperator());
				planJSON.setChkPlanId(plan.getChkPlanId());
				planJSON.setChkPlanType(plan.getChkPlanType());
				planJSON.setChkPlanVersion(plan.getChkPlanVersion());
				planJSON.setChkTime(plan.getChkTime());
				planJSON.setCreateTime(plan.getCreateTime());
				planJSON.setPlanChkArea(plan.getPlanChkArea());
				planJSON.setPlanChkOperator(plan.getPlanChkOperator());
				planJSON.setPlanChkTime(plan.getPlanChkTime());
				planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
				planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
				planJSON.setScheduleId(plan.getScheduleId());
				planJSON.setScheduleName(plan.getScheduleName());
				planJSON.setScheduleTime(plan.getScheduleTime());
				planJSON.setTaskName(plan.getTaskName());
				planJSON.setTaskNameId(plan.getTaskNameId());
				planJSON.setChkPlanState(plan.getQastudyChkIndex().getChkPlanState());//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
				planJSON.setChkPlanVersion(1);//1代表当前版本
				planJSON.setNumber(plan.getNumber());
				planJSON.setSOPFlag(plan.getSOPFlag());
				
				planJsons.add(planJSON);
			}
		}else {
			//历史版本数据
			List<QAChkPlanHis> planHis = qAChkPlanHisService.getByStudyNoAndVersion(studyNoParam,Integer.parseInt(versionStr));
			for(QAChkPlanHis plan:planHis)
			{
				QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
				planJSON.setStudyNo(plan.getStudyNo());
				planJSON.setChkFinishedFlag(plan.getChkFinishedFlag());
			//	planJSON.setChkIndex(plan.getChkIndex());
				planJSON.setChkItemId(plan.getChkItemId());
				planJSON.setChkItemName(plan.getChkItemName());
				planJSON.setChkOperator(plan.getChkOperator());
				planJSON.setChkPlanId(plan.getChkPlanId());
				planJSON.setChkPlanType(plan.getChkPlanType());
				planJSON.setChkPlanVersion(plan.getChkPlanVersion());
				planJSON.setChkTime(plan.getChkTime());
				planJSON.setCreateTime(plan.getCreateTime());
				planJSON.setPlanChkArea(plan.getPlanChkArea());
				planJSON.setPlanChkOperator(plan.getPlanChkOperator());
				planJSON.setPlanChkTime(plan.getPlanChkTime());
			//	planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
				planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
				planJSON.setScheduleId(plan.getScheduleId());
				planJSON.setScheduleName(plan.getScheduleName());
				planJSON.setScheduleTime(plan.getScheduleTime());
				planJSON.setTaskName(plan.getTaskName());
				planJSON.setTaskNameId(plan.getTaskNameId());
				planJSON.setNumber(plan.getNumber());
				planJSON.setSOPFlag(plan.getSOPFlag());
			//	planJSON.setChkPlanState(plan.getQastudyChkIndex().getChkPlanState());//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
				
				planJSON.setChkPlanVersion(2);//2代表历史版本
				
				planJsons.add(planJSON);
			}
		}
		Collections.sort(planJsons, new Comparator<QAChkPlan_JSON>() {

			public int compare(QAChkPlan_JSON o1, QAChkPlan_JSON o2) {
				// TODO Auto-generated method stub
				return o2.getPlanChkTime().compareTo(o1.getPlanChkTime());
			}
		});
		
		String[] _nory_format = {"chkPlanId","chkPlanState","studyNo", "chkPlanType","chkItemId","chkItemName","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion","SOPFlag"};
		String json = JsonPluginsUtil.beanListToJson(planJsons, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);		
	}
	
	public void getPlanVersions()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		List<Integer> versionList = qAChkPlanHisService.getVersionsByStudyNo(studyNoParam);
		QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		Integer chkplanCurVersion = qAChkPlanService.getVersionByStudyNo(studyNoParam);
		
		QAChkPlanChangeIndex changeIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		//boolean isExistChangeRecord = false;
		if(chkplanCurVersion==0&&(versionList==null||versionList.size()==0))
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 1+",true");
			map.put("text", "第"+1+"版本");
			mapList.add(map);
		}else {
			if(chkplanCurVersion>0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", chkplanCurVersion+",true");
				map.put("text", "第"+chkplanCurVersion+"版本");
				//map.put("selected", true);
				mapList.add(map);
			}else{
				Integer maxHisVersion = versionList.get(0);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", (maxHisVersion+1)+",true");
				map.put("text", "第"+(maxHisVersion+1)+"版本");
				//map.put("selected", true);
				mapList.add(map);
				
			}
			
			for(Integer ver:versionList)
			{
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", ver);
				if(ver==-1)
				{
					//isExistChangeRecord=true;
					//map2.put("text", "变更版本");	
				}else{
					map2.put("text", "第"+ver+"历史版本");
					mapList.add(map2);
				}
				
			}
		}
		
		
		
		//if(!isExistChangeRecord){
		if(studyChkIndex!=null&&(studyChkIndex.getChkPlanState()!=null&&studyChkIndex.getChkPlanState()==2)
				&&(changeIndex!=null))//存在还没处理的变更
		{
			versionList.add(0,-1);//变更版本
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", -1);
			map2.put("text", "变更版本");	
				
			mapList.add(0,map2);
		}	
		//}else{
			
		//}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	}
	
	public void save()
	{
		//boolean flag = true;
		//if(flag)
		//{
			Integer maxv = qAChkPlanService.getMaxVersionByStudyNo(studyNoParam);
			if(maxv==0)
			{
				maxv = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
				maxv+=1;//历史版本中的加上一
			}
			//scheduleId是id-时间
			/*
			if(model.getScheduleId()!=null&&!"".equals(model.getScheduleId()))//按日程安排一个一个指定检查计划
			{
				//scheduleId
				List<TblSchedulePlan_Json> scheduleDateList= new ArrayList<TblSchedulePlan_Json>();
				List<TblSchedulePlan_Json> tblSchedulePlanlist = tblSchedulePlanService.getHasSubmitSchedulePlanJson(2,studyNoParam, 2);
				List<QAChkPlan> plans = new ArrayList<QAChkPlan>();
				
				for(TblSchedulePlan_Json onePlan:tblSchedulePlanlist)
				{
					if(onePlan.getScheduleID().equals(model.getScheduleId()))
					{
						scheduleDateList.add(onePlan);
					}
				}
				tblSchedulePlanlist=null;
				
				TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				DictScheduleChkItem dictScheduleChkItem = dictScheduleChkItemService.getById(model.getScheduleChkItemId());
				QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
				if(qAStudyChkIndex==null)
				{
					String sd = "";
					if(item!=null)
					{
						sd = item.getSd();
					}else {
						sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
					}
					User user = (User) ActionContext.getContext().getSession().get("user");
					
					qAStudyChkIndex = new QAStudyChkIndex();
					
					qAStudyChkIndex.setChkPlanAuthor(user.getRealName());
					qAStudyChkIndex.setChkPlanCurVersion(1);
					qAStudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
					if(item!=null)
					{
						String qa = item.getQa();
						qAStudyChkIndex.setInspector(qa);
						qAStudyChkIndex.setInspectorAppointState(item.getQaState());//0：未任命；1：已任命
						qAStudyChkIndex.setInspectorAppointTime(item.getQaAppointDate());
					}
					qAStudyChkIndex.setReportState(0);//0：未完成；1：已完成
					qAStudyChkIndex.setSd(sd);
					qAStudyChkIndex.setStudyNo(studyNoParam);
					
					qAStudyChkIndex.setStudyPlanState(0);//方案审批状态：0：未完成；1：已完成
					
					//专题里面的日程信息
					TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNoParam);
					if(tblStudyPlan!=null)
					{
						qAStudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
						
					}
					
					qAStudyChkIndexService.save(qAStudyChkIndex);
					
				}	
				
				for(TblSchedulePlan_Json onePlan:scheduleDateList)
				{
					//boolean isExistScheduleItemId=qAChkPlanService.getByScheduleItemId(studyNoParam,scheduleChkItemId);
					if(onePlan.getNumber()==0)//单次任务或者多次任务的第一次任务
					{
						QAChkPlan qAChkPlan = new QAChkPlan();
						String key=qAChkPlanService.getKey("QAChkPlan");
						qAChkPlan.setChkPlanId(key);
						qAChkPlan.setChkItemId(model.getChkItemId());
						qAChkPlan.setChkItemName(model.getChkItemName());
						
						if(item!=null)
							qAChkPlan.setPlanChkOperator(item.getQa());
						
						qAChkPlan.setChkPlanType(1);//1:研究；2：过程；3：设施
						qAChkPlan.setChkPlanVersion(maxv);
						
						qAChkPlan.setNumber(onePlan.getNumber());
						qAChkPlan.setCreateTime(DateUtil.getTodayDate());
						qAChkPlan.setScheduleChkItemId(model.getScheduleChkItemId());
						qAChkPlan.setTaskNameId(dictScheduleChkItem.getTaskNameId());
						qAChkPlan.setTaskName(model.getScheduleName());
						
						DictChkArea dictChkArea = dictChkAreaService.getById(model.getPlanChkArea());
						if(dictChkArea!=null)
							qAChkPlan.setPlanChkArea(dictChkArea.getAreaName());
			
						qAChkPlan.setScheduleName(model.getScheduleName());
						
						qAChkPlan.setScheduleId(model.getScheduleId());
						qAChkPlan.setNumber(model.getNumber());
						qAChkPlan.setPlanChkTime(model.getPlanChkTime());
						qAChkPlan.setScheduleTime(model.getScheduleTime());
						if(qAStudyChkIndex.getInspector()!=null)
						{
							qAChkPlan.setPlanChkOperator(qAStudyChkIndex.getInspector());
						}else if(item!=null&&item.getQa()!=null)
						{
							qAChkPlan.setPlanChkOperator(item.getQa());
						}
	
						qAChkPlan.setQastudyChkIndex(qAStudyChkIndex);
						qAChkPlanService.save(qAChkPlan);
						plans.add(qAChkPlan);
					}else {
						Collections.sort(plans,new Comparator<QAChkPlan>() {
	
							public int compare(QAChkPlan plan1, QAChkPlan plan2) {
								if(plan1.getPlanChkTime().before(plan2.getPlanChkTime()))
								{
									return 1;
								}
								return -1;
							}
						});
						//查找是否超过三个月，如果超过三个月则再次生成计划
						
						for(QAChkPlan plan:plans)
						{
							Calendar calendar1 = new GregorianCalendar();
							calendar1.setTime(plan.getPlanChkTime());
							calendar1.add(Calendar.MONTH, 3);
							
							//计算出页面上的相差时间
							long addMillsSeconds=model.getPlanChkTime().getTime()-model.getScheduleTime().getTime();
							Date tempPlanDate=DateUtil.stringToDate(onePlan.getDateTime(),"yyyy-MM-dd");
							tempPlanDate.setTime(tempPlanDate.getTime()+addMillsSeconds);
							
							boolean flag = calendar1.getTime().after(tempPlanDate);
							if(!flag)
							{
								//时间间隔超过三个月
								QAChkPlan qAChkPlan = new QAChkPlan();
								String key=qAChkPlanService.getKey("QAChkPlan");
								qAChkPlan.setChkPlanId(key);
								qAChkPlan.setChkItemId(model.getChkItemId());
								qAChkPlan.setChkItemName(model.getChkItemName());
								
								if(item!=null)
									qAChkPlan.setPlanChkOperator(item.getQa());
								qAChkPlan.setChkPlanType(1);//1:研究；2：过程；3：设施
								qAChkPlan.setNumber(onePlan.getNumber());
								qAChkPlan.setChkPlanVersion(maxv);
								
								qAChkPlan.setCreateTime(DateUtil.getTodayDate());
							
								qAChkPlan.setScheduleChkItemId(model.getScheduleChkItemId());
								qAChkPlan.setTaskNameId(dictScheduleChkItem.getTaskNameId());
							
								qAChkPlan.setTaskName(model.getScheduleName());
								
								DictChkArea dictChkArea = dictChkAreaService.getById(model.getPlanChkArea());
								if(dictChkArea!=null)
									qAChkPlan.setPlanChkArea(dictChkArea.getAreaName());
					
								qAChkPlan.setScheduleName(model.getScheduleName());
								
								qAChkPlan.setScheduleId(model.getScheduleId());
								qAChkPlan.setNumber(model.getNumber());
							//	qAChkPlan.setPlanChkTime(model.getPlanChkTime());
							//	qAChkPlan.setScheduleTime(model.getScheduleTime());
								qAChkPlan.setScheduleTime(DateUtil.stringToDate(onePlan.getDateTime(),"yyyy-MM-dd"));
								
							
								qAChkPlan.setPlanChkTime(tempPlanDate);
						
								qAChkPlan.setQastudyChkIndex(qAStudyChkIndex);
							
								qAChkPlanService.save(qAChkPlan);
								plans.add(qAChkPlan);
							}
							break;
						}
					}
					
				}
			}else{//手动制定检查计划
				 */
				
				QAChkPlan qAChkPlan = new QAChkPlan();
				String key=qAChkPlanService.getKey("QAChkPlan");
				qAChkPlan.setChkPlanId(key);
				qAChkPlan.setChkItemId(model.getChkItemId());
				qAChkPlan.setChkItemName(model.getChkItemName());
				
				TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				
				//qAChkPlan.setChkOperator(item.getQa());
				if(item!=null)
					qAChkPlan.setPlanChkOperator(item.getQa());
				
				qAChkPlan.setChkPlanType(1);//1:研究；2：过程；3：设施
				qAChkPlan.setChkPlanVersion(maxv);
				qAChkPlan.setCreateTime(DateUtil.getTodayDate());
				qAChkPlan.setScheduleChkItemId(model.getScheduleChkItemId());
	
				DictScheduleChkItem dictScheduleChkItem = dictScheduleChkItemService.getById(model.getScheduleChkItemId());
	
				qAChkPlan.setTaskNameId(dictScheduleChkItem.getTaskNameId());
				qAChkPlan.setTaskName(model.getScheduleName());
				
				qAChkPlan.setNumber(0);
				qAChkPlan.setSOPFlag(model.getSOPFlag());
				
				//DictChkArea dictChkArea = dictChkAreaService.getById(model.getPlanChkArea());
				//if(dictChkArea!=null)
				qAChkPlan.setPlanChkArea(model.getPlanChkArea());
	
				qAChkPlan.setScheduleName(model.getScheduleName());
				
				//qAChkPlan.setScheduleId(model.getScheduleId());
				//qAChkPlan.setNumber(model.getNumber());
				
				qAChkPlan.setPlanChkTime(model.getPlanChkTime());
				qAChkPlan.setScheduleTime(model.getScheduleTime());
				
				QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
				
				if(qAStudyChkIndex==null)
				{
					String sd = "";
					if(item!=null)
					{
						sd = item.getSd();
					}else {
						sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
					}
					User user = (User) ActionContext.getContext().getSession().get("user");
					
					qAStudyChkIndex = new QAStudyChkIndex();
					
					qAStudyChkIndex.setChkPlanAuthor(user.getRealName());
					qAStudyChkIndex.setChkPlanCurVersion(1);
					qAStudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
					if(item!=null)
					{
						String qa = item.getQa();
						qAStudyChkIndex.setInspector(qa);
						qAStudyChkIndex.setInspectorAppointState(item.getQaState());//0：未任命；1：已任命
						qAStudyChkIndex.setInspectorAppointTime(item.getQaAppointDate());
					}
					qAStudyChkIndex.setReportState(0);//0：未完成；1：已完成
					qAStudyChkIndex.setSd(sd);
					qAStudyChkIndex.setStudyNo(studyNoParam);
					qAStudyChkIndex.setStudyPlanState(0);//方案审批状态：0：未完成；1：已完成
					//专题里面的日程信息
					TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNoParam);
					if(tblStudyPlan!=null)
					{
						qAStudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
						
					}
					qAStudyChkIndexService.save(qAStudyChkIndex);
					
				}			
				qAChkPlan.setQastudyChkIndex(qAStudyChkIndex);
			
				qAChkPlanService.save(qAChkPlan);
				
			//}
			
		//}
		
	}
	
	public void updatePlanTimeOrArea()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlan plan = qAChkPlanService.getById(model.getChkPlanId());
		if(model.getPlanChkTime()!=null&&!"".equals(model.getPlanChkTime()))
		{
			plan.setPlanChkTime(model.getPlanChkTime());
		}
		if(model.getPlanChkArea()!=null&&!"".equals(model.getPlanChkArea()))
		{
			//DictChkArea dictChkArea = dictChkAreaService.getById(model.getPlanChkArea());
			//if(dictChkArea!=null)
				plan.setPlanChkArea(model.getPlanChkArea());
		}
		if(model.getSOPFlag()!=null&&!"".equals(model.getSOPFlag()))
		{
			plan.setSOPFlag(model.getSOPFlag());
		}
		qAChkPlanService.update(plan);
		
		map.put("success",true);
		map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
		map.put("planChkArea", plan.getPlanChkArea());
		map.put("SOPFlag", plan.getSOPFlag());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void cancelChangeChkPlan()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlanChangeIndex change = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		if(change!=null)
		{
			if(change.getChangeState()==null||change.getChangeState()==0||change.getChangeState()==1)
			{
				
			  
				change.setChangeState(-2);//0：原始；1：提交；-1：否决；2：通过,-2:撤销
				
				qAChkPlanChangeIndexService.update(change);
				//把修改的plan删除了
				List<QAChkPlanHis> changeRecordList = qAChkPlanHisService.getByStudyNoAndVersion(studyNoParam, -1);
				for(QAChkPlanHis his:changeRecordList)
				{
					qAChkPlanHisService.delete(his.getChkPlanHisId());
				}
				
				map.put("success", true);
				
				if(change.getChangeState()==1){
					writeES("撤销检查计划变更申请", 844, "QAChkPlanChangeIndex", change.getChkPlanChangeIndexId());
				}
				
			}else{
				map.put("success", false);
				map.put("msg", "QAM已经处理了该检查计划变更申请！");
			}
			
		}else{
			map.put("success", false);
			map.put("msg", "不存在还没处理的检查计划变更申请");
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void applyTempChkOperator()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlan plan = qAChkPlanService.getById(model.getChkPlanId());
		plan.setTempChkOperator(userService.getByUserName(qa).getRealName());
		plan.setTempChkOperatorApplyTime(new Date());
		plan.setTempChkOperatorFlag(1);//0：默认，1：提申请，2：QAM批准，-1：不批准
		
		qAChkPlanService.update(plan);
		
		writeES("QA申请临时计划检查人", 832, "QAChkPlan", model.getChkPlanId());
		//日志录入
		writeLog("QA申请临时计划检查人","QA申请临时计划检查人","QA申请临时计划检查人,检查项："+plan.getChkItemName());
		
		//发邮件
		studyNoParam=plan.getQastudyChkIndex().getStudyNo();
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String studyNoName = "";
		if(studyItem!=null)
		{
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			
		}
		//通知QAM
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		String msgTitle="QA("+getCurrentRealName()+")申请"+plan.getTempChkOperator()+"为一个检查计划的QA检查员； 专题编号:　"+studyNoParam+"专题名称："+studyNoName+"　计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QA("+getCurrentRealName()+")于"+currentDate+"申请"+plan.getTempChkOperator()+"为一个检查计划的QA检查员；<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();
		String receiverList = "";
		
		List<User> users = userService.findByPrivilegeName("QA负责人");
		if(users!=null&&users.size()>0)
		{
			receiverList+=(users.get(0).getUserName())+",";
		}
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void approvalTempChkOperator()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlan plan = qAChkPlanService.getById(model.getChkPlanId());
		plan.setTempChkOperatorFlag(result);
		plan.setTempChkOperatorApprovalTime(new Date());
		String resultString = "退回";
		String oldPlanQA = plan.getPlanChkOperator();
		if(result==2)
		{
			resultString = "通过";
			plan.setPlanChkOperator(plan.getTempChkOperator());
			writeES("QAM通过了临时计划检查人的申请", 833, "QAChkPlan", model.getChkPlanId());
		}else{
			writeES("QAM退回了临时计划检查人的申请", 841, "QAChkPlan", model.getChkPlanId());
		}
		qAChkPlanService.update(plan);
		
		writeLog("审批临时计划检查人的申请","QAM"+resultString+"了临时计划检查人的申请","QAM"+resultString+"了临时计划检查人的申请");

		//发邮件
		studyNoParam=plan.getQastudyChkIndex().getStudyNo();
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String studyNoName = "";
		if(studyItem!=null)
		{
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			
		}
		//通知QAM
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		String msgTitle="QAM("+getCurrentRealName()+")"+resultString+"了临时计划检查人的申请； 专题编号:　"+studyNoParam+"专题名称："+studyNoName+"　计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QAM("+getCurrentRealName()+")于"+currentDate+""+resultString+"了临时计划检查人的申请；<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();
		String receiverList = "";
		
		//计划检查人员
		receiverList+=(userService.getByRealName(oldPlanQA).getUserName())+",";
		receiverList+=(userService.getByRealName(plan.getTempChkOperator()).getUserName())+",";
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		map.put("success", true);
		map.put("planChkOperator", plan.getPlanChkOperator());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void backupStudyPlan()
	{
		List<QAChkPlan> existPlans = qAChkPlanService.getByStudyNo(studyNoParam);
		for(QAChkPlan plan :existPlans)
		{
			QAChkPlanHis his = new QAChkPlanHis();
			his.setChkFinishedFlag(plan.getChkFinishedFlag());
			his.setChkItemId(plan.getChkItemId());
			his.setChkItemName(plan.getChkItemName());
			his.setChkOperator(plan.getChkOperator());
			his.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
			his.setChkPlanId(plan.getChkPlanId());
			his.setChkPlanType(plan.getChkPlanType());
			his.setChkPlanVersion(plan.getChkPlanVersion());
			his.setChkTime(plan.getChkTime());
			his.setCreateTime(plan.getCreateTime());
			his.setPlanChkArea(plan.getPlanChkArea());
			his.setPlanChkOperator(plan.getPlanChkOperator());
			his.setPlanChkTime(plan.getPlanChkTime());
			his.setScheduleChkItemId(plan.getScheduleChkItemId());
			his.setScheduleId(plan.getScheduleId());
			his.setScheduleName(plan.getScheduleName());
			his.setScheduleTime(plan.getScheduleTime());
			his.setStudyNo(plan.getQastudyChkIndex().getStudyNo());
			his.setTaskName(plan.getTaskName());
			his.setTaskNameId(plan.getTaskNameId());
			his.setSOPFlag(plan.getSOPFlag());
			his.setNumber(plan.getNumber());
			
			qAChkPlanHisService.save(his);
			
			plan.setChkPlanVersion(plan.getChkPlanVersion()+1);
			qAChkPlanService.update(plan);
			
			//qAChkPlanService.delete(plan.getChkPlanId());
		}
		QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getById(studyNoParam);
		qAStudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
		qAStudyChkIndex.setScheduleChangedFlag(2);
		qAStudyChkIndexService.update(qAStudyChkIndex);
		
		
	}
	public void delAllStudyPlan(String studyNoParam)
	{
		List<QAChkPlan> existPlans = qAChkPlanService.getByStudyNo(studyNoParam);
		for(QAChkPlan plan :existPlans)
		{
			qAChkPlanService.delete(plan.getChkPlanId());
		}
		
	}
	public void getCirclePlans(QAStudyChkIndex qastudyChkIndex ,List<QAChkPlan> plans,List<TblSchedulePlan_Json> tblSchedulePlanlist,String[] planChkAreaList)
	{
		//因为备份完以后，plan数据库就删除了。所以不用看了。
		//Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
		Integer currentVersion = qAChkPlanService.getMaxVersionByStudyNo(studyNoParam);
		if(currentVersion==0)
		{
			Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
			if(maxVersion==0)
			{
				currentVersion=1;
			}else{
				maxVersion=maxVersion+1;
			}
		}
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		//备份上一个版本的
		//backupStudyPlan();//可以不传参数吗？
		delAllStudyPlan(studyNoParam);
		
		Map<String,Integer> maxSnMap = new HashMap<String, Integer>();
		int scheduleIndex=0;
		Map<String,String> scheduleAreaMap = new HashMap<String, String>();
		
		for(TblSchedulePlan_Json schedulePlan: tblSchedulePlanlist)
		{
			List<DictScheduleChkItem> items = dictScheduleChkItemService.getByScheduleName(schedulePlan.getTaskName());
			for(int j=0;j<items.size();j++)
			{
				DictScheduleChkItem item = items.get(j);
				
				if(schedulePlan.getNumber()==0)
				{
					String planChkArea="";
					if(scheduleIndex<=planChkAreaList.length-1)
					{
					   planChkArea = planChkAreaList[scheduleIndex].trim();
					}
					if(planChkArea==null||"undefined".equals(planChkArea))
					{
						planChkArea="";
					}
					scheduleAreaMap.put(schedulePlan.getScheduleID(), planChkArea);
					if(j==(items.size()-1))
					{
						scheduleIndex+=1;
					}
					
					QAChkPlan plan = new QAChkPlan();
					//1：方案；2：报告；3：变更；4：基于研究的检查项
					DictQACheckItem qachkItem = dictQACheckItemService.getByItemName(4,item.getChkItemName());
					if(qachkItem!=null)//检查项是基于研究的检查项
					{
						
						plan.setChkItemId(qachkItem.getChkItemId());
						plan.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
						plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
						plan.setChkPlanVersion(currentVersion);
						plan.setCreateTime(new Date());
						plan.setNumber(0);
						plan.setChkItemName(item.getChkItemName());
						if(studyItem!=null)
						{
							plan.setPlanChkOperator(studyItem.getQa());
						}
						plan.setPlanChkTime(schedulePlan.getDateTimeDate());
						plan.setQastudyChkIndex(qastudyChkIndex);
						plan.setScheduleChkItemId(item.getScheduleChkItemId());
						plan.setScheduleId(schedulePlan.getScheduleID());
						plan.setScheduleName(schedulePlan.getTaskName());
						plan.setScheduleTime(schedulePlan.getDateTimeDate());
						plan.setTaskName(schedulePlan.getTaskName());
						plan.setTaskNameId(item.getTaskNameId());
						plan.setNumber(0);
						plan.setSOPFlag(model.getSOPFlag());
						plan.setPlanChkArea(planChkArea);
						
						plans.add(plan);
					}else {
						continue;
					}
				}else {
					Collections.sort(plans,new Comparator<QAChkPlan>() {

						public int compare(QAChkPlan plan1, QAChkPlan plan2) {
							if(plan1.getPlanChkTime().before(plan2.getPlanChkTime()))
							{
								return 1;
							}
							return -1;
						}
					});
					//周期的日程，三个月循环加入一次
					for(QAChkPlan onePlan:plans)
					{
						if(onePlan.getScheduleId().equals(schedulePlan.getScheduleID()))
						{
							Calendar calendar1 = new GregorianCalendar();
							calendar1.setTime(onePlan.getPlanChkTime());
							calendar1.add(Calendar.MONTH, 3);
							boolean flag = calendar1.getTime().after(schedulePlan.getDateTimeDate());
							//如果是同一天的就证明是一个日程多个检查项的。
							boolean flag2 = onePlan.getPlanChkTime().equals(schedulePlan.getDateTimeDate());
							if(!flag||flag2)
							{
								if(maxSnMap.get(onePlan.getScheduleId())!=null)
								{
									maxSnMap.put(onePlan.getScheduleId(), maxSnMap.get(onePlan.getScheduleId())+1);
								}else{
									maxSnMap.put(onePlan.getScheduleId(), 1);
								}
								//1：方案；2：报告；3：变更；4：基于研究的检查项
								DictQACheckItem qachkItem = dictQACheckItemService.getByItemName(4,item.getChkItemName());
								if(qachkItem!=null)//检查项是基于研究的检查项
								{
									//时间间隔超过三个月
									QAChkPlan plan = new QAChkPlan();
									
									plan.setChkItemId(qachkItem.getChkItemId());
									plan.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
									plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
									plan.setChkPlanVersion(currentVersion);
									plan.setCreateTime(new Date());
									plan.setNumber(maxSnMap.get(onePlan.getScheduleId()));
									plan.setSOPFlag(model.getSOPFlag());
									plan.setChkItemName(item.getChkItemName());
									if(studyItem!=null)
									{
										plan.setPlanChkOperator(studyItem.getQa());
									}
									plan.setPlanChkTime(schedulePlan.getDateTimeDate());
									plan.setQastudyChkIndex(qastudyChkIndex);
									plan.setScheduleChkItemId(item.getScheduleChkItemId());
									plan.setScheduleId(schedulePlan.getScheduleID());
									plan.setScheduleName(schedulePlan.getTaskName());
									plan.setScheduleTime(schedulePlan.getDateTimeDate());
									plan.setTaskName(schedulePlan.getTaskName());
									plan.setTaskNameId(item.getTaskNameId());
									plan.setPlanChkArea(scheduleAreaMap.get(schedulePlan.getScheduleID()));
									
									plans.add(plan);
								}else {
									continue;
								}
							}
							break;
						}
					}
					
				}
				
			}//所有的检查项结束
			
			
		}
		
	}
	public void getPlansByChkItemGroupReg(QAStudyChkIndex qastudyChkIndex,Integer days,List<QAChkPlan> plans, List<DictChkItemStudyGroupReg> regs,String[] startDateList,String[] planChkAreaList)
	{
		//因为备份完以后，plan数据库就删除了。所以不用看了。
		//Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
		Integer currentVersion = qAChkPlanService.getMaxVersionByStudyNo(studyNoParam);
		if(currentVersion==0)
		{
			Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
			if(maxVersion==0)
			{
				currentVersion=1;
			}else{
				maxVersion=maxVersion+1;
			}
		}
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		//备份上一个版本的
		//backupStudyPlan();//可以不传参数吗？
		delAllStudyPlan(studyNoParam);
		
		for(int j=0;j<regs.size();j++)
		{
			DictChkItemStudyGroupReg reg = regs.get(j);
			Date startDate = DateUtil.stringToDate(startDateList[j],"yyyy-MM-dd");
			String planChkArea = planChkAreaList[j].trim();
			if(planChkArea==null||"undefined".equals(planChkArea)){
				planChkArea="";
			}
			//1：方案；2：报告；3：变更；4：基于研究的检查项
			if(reg.getDictQacheckItem().getChkItemType()==4)
			{
				if(reg.getChkFreqFlag()!=null&&reg.getChkFreqFlag()==1)//单次的检查项
				{
					QAChkPlan plan = new QAChkPlan();
					plan.setChkItemId(reg.getDictQacheckItem().getChkItemId());
					plan.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
					plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
					plan.setChkPlanVersion(currentVersion);
					plan.setCreateTime(new Date());
					plan.setNumber(0);
					plan.setSOPFlag(model.getSOPFlag());
					plan.setChkItemName(reg.getDictQacheckItem().getChkItemName());
					if(studyItem!=null)
					{
						plan.setPlanChkOperator(studyItem.getQa());
					}
					//startDate从前台获取
					plan.setPlanChkTime(startDate);
					plan.setPlanChkArea(planChkArea);
					
					plan.setQastudyChkIndex(qastudyChkIndex);
					//plan.setScheduleChkItemId(reg.getDictQacheckItem().getScheduleChkItemId());
					//plan.setScheduleId(schedulePlan.getScheduleID());
					//plan.setScheduleName(schedulePlan.getTaskName());
					//plan.setScheduleTime(schedulePlan.getDateTimeDate());
					//plan.setTaskName(schedulePlan.getTaskName());
					//plan.setTaskNameId(item.getTaskNameId());
					
					
					plans.add(plan);
					
				}else if(reg.getChkFreqFlag()!=null){
					List<Date> dates =  getDateByPeried(startDate,reg.getChkFreq(),reg.getChkFreqUnit(),days);

					//时间间隔超过三个月
					QAChkPlan plan = new QAChkPlan();
					
					plan.setChkItemId(reg.getDictQacheckItem().getChkItemId());
					plan.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
					plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
					plan.setChkPlanVersion(currentVersion);
					plan.setCreateTime(new Date());
					plan.setNumber(0);
					plan.setSOPFlag(model.getSOPFlag());
					plan.setChkItemName(reg.getDictQacheckItem().getChkItemName());
					if(studyItem!=null)
					{
						plan.setPlanChkOperator(studyItem.getQa());
					}
					plan.setPlanChkTime(startDate);
					plan.setPlanChkArea(planChkArea);
					
					plan.setQastudyChkIndex(qastudyChkIndex);
				//	plan2.setScheduleChkItemId(item.getScheduleChkItemId());
				//	plan2.setScheduleId(schedulePlan.getScheduleID());
				//	plan2.setScheduleName(schedulePlan.getTaskName());
				//	plan2.setScheduleTime(schedulePlan.getDateTimeDate());
				//	plan2.setTaskName(schedulePlan.getTaskName());
				//	plan2.setTaskNameId(item.getTaskNameId());
					
					plans.add(plan);
					Date tempDate = startDate;
					
					Integer maxNum=0;
					
					for(int i=0;i<dates.size();i++)
					{
						Date date = dates.get(i);
						
						Calendar calendar1 = new GregorianCalendar();
						calendar1.setTime(tempDate);
						calendar1.add(Calendar.MONTH, 3);
						boolean flag = calendar1.getTime().after(date);
						if(!flag)
						{
							maxNum+=1;
							tempDate = date;
							//时间间隔超过三个月
							QAChkPlan plan2 = new QAChkPlan();
							
							plan2.setChkItemId(reg.getDictQacheckItem().getChkItemId());
							plan2.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
							plan2.setChkPlanType(1);//;//1:研究；2：过程；3：设施
							plan2.setChkPlanVersion(currentVersion);
							plan2.setCreateTime(new Date());
							plan2.setNumber(maxNum);
							plan2.setSOPFlag(model.getSOPFlag());
							plan2.setChkItemName(reg.getDictQacheckItem().getChkItemName());
							if(studyItem!=null)
							{
								plan2.setPlanChkOperator(studyItem.getQa());
							}
							plan2.setPlanChkTime(date);
							plan2.setPlanChkArea(planChkArea);
							
							plan2.setQastudyChkIndex(qastudyChkIndex);
						//	plan2.setScheduleChkItemId(item.getScheduleChkItemId());
						//	plan2.setScheduleId(schedulePlan.getScheduleID());
						//	plan2.setScheduleName(schedulePlan.getTaskName());
						//	plan2.setScheduleTime(schedulePlan.getDateTimeDate());
						//	plan2.setTaskName(schedulePlan.getTaskName());
						//	plan2.setTaskNameId(item.getTaskNameId());
							
							plans.add(plan2);
						}
						
					}
					
					
				}//多次的结束
				else{//没有频率的组检查项关系什么也不做
					
				}
				
				
			}
			
		}
		
		
		
	}
	public List<Date> getDateByPeried(Date startDate,Integer chkFreq,String chkFreqUnit,int days)
	{
		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate);
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(startDate);
		calendar1.add(Calendar.DATE, days);
		Date endDate = calendar1.getTime();
		//int dayMillSeconds = 24*3600*1000;
		//endDate.setTime(startDate.getTime()+days*dayMillSeconds);
		
		Date tempDate = startDate;
		if(chkFreq!=null)
		{
			while(tempDate.before(endDate))
			{
				if("天".equals(chkFreqUnit))
				{
					//tempDate.setTime(tempDate.getTime()+chkFreq*dayMillSeconds);
					calendar1.setTime(tempDate);
					calendar1.add(Calendar.DATE, chkFreq);
					tempDate = calendar1.getTime();
				}
				if("周".equals(chkFreqUnit))
				{
					//tempDate.setTime(tempDate.getTime()+dayMillSeconds*7);
					calendar1.setTime(tempDate);
					calendar1.add(Calendar.WEEK_OF_YEAR, chkFreq);
					tempDate = calendar1.getTime();
				}
				if("月".equals(chkFreqUnit))
				{
					//tempDate.setTime(tempDate.getTime()+dayMillSeconds*30);
					calendar1.setTime(tempDate);
					calendar1.add(Calendar.MONTH, chkFreq);
					tempDate = calendar1.getTime();
				}
				dates.add(tempDate);
			}
		}
		return dates;
	}
	//根据日程自动生成检查计划
	public void generatePlanBySchedule()
	{
		//Integer maxVersion = qAChkPlanService.getMaxVersionByStudyNo(studyNoParam);
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		QAStudyChkIndex qastudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(qastudyChkIndex==null)
		{
			String sd = "";
			if(studyItem!=null)
			{
				sd = studyItem.getSd();
			}else{
				sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
			}
				
			User user = (User) ActionContext.getContext().getSession().get("user");
			qastudyChkIndex = new QAStudyChkIndex();
			
			qastudyChkIndex.setChkPlanAuthor(user.getRealName());
			qastudyChkIndex.setChkPlanCurVersion(1);
			qastudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
			if(studyItem!=null)
			{
				String qa = studyItem.getQa();
				qastudyChkIndex.setInspector(qa);
				qastudyChkIndex.setInspectorAppointState(studyItem.getQaState());//0：未任命；1：已任命
				qastudyChkIndex.setInspectorAppointTime(studyItem.getQaAppointDate());
			}
			qastudyChkIndex.setReportState(0);//0：未完成；1：已完成
			qastudyChkIndex.setSd(sd);
			qastudyChkIndex.setStudyNo(studyNoParam);
			qastudyChkIndex.setStudyPlanState(0);//方案审批状态：0：未完成；1：已完成
			//专题里面的日程信息
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNoParam);
			if(tblStudyPlan!=null)
			{
				qastudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
			}
			qAStudyChkIndexService.save(qastudyChkIndex);
		}
		
		List<QAChkPlan> plans = new ArrayList<QAChkPlan>();
		List<TblSchedulePlan_Json> tblSchedulePlanlist = tblSchedulePlanService.getHasSubmitSchedulePlanJson(2,studyNoParam, 2);
		
		if(planChkAreas==null)
			planChkAreas="";
		String[] planChkAreaList = planChkAreas.split(",");
		
		getCirclePlans( qastudyChkIndex , plans, tblSchedulePlanlist,planChkAreaList);//保存循环的记录
		
		//保存进数据库
		for(QAChkPlan plan:plans)
		{
			qAChkPlanService.save(plan);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	//根据试验周期和试验检查项生成检查计划
	public void generatePlanByStudyType()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		
		QAStudyChkIndex qastudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(qastudyChkIndex==null)
		{
			String sd = "";
			if(studyItem!=null)
			{
				sd = studyItem.getSd();
			}else{
				sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
			}
				
			User user = (User) ActionContext.getContext().getSession().get("user");
			qastudyChkIndex = new QAStudyChkIndex();
			
			qastudyChkIndex.setChkPlanAuthor(user.getRealName());
			qastudyChkIndex.setChkPlanCurVersion(1);
			qastudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
			if(studyItem!=null)
			{
				String qa = studyItem.getQa();
				qastudyChkIndex.setInspector(qa);
				qastudyChkIndex.setInspectorAppointState(studyItem.getQaState());//0：未任命；1：已任命
				qastudyChkIndex.setInspectorAppointTime(studyItem.getQaAppointDate());
			}
			qastudyChkIndex.setReportState(0);//0：未完成；1：已完成
			qastudyChkIndex.setSd(sd);
			qastudyChkIndex.setStudyNo(studyNoParam);
			qastudyChkIndex.setStudyPlanState(0);//方案审批状态：0：未完成；1：已完成
			//专题里面的日程信息
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNoParam);
			if(tblStudyPlan!=null)
			{
				qastudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
			}
			qAStudyChkIndexService.save(qastudyChkIndex);
		}
		
		List<QAChkPlan> plans = new ArrayList<QAChkPlan>();
		
		
		DictStudyType dictStudyType = dictStudyTypeService.getByStudyTypeCode(studyItem.getStudyTypeCode());
		//chkindex chkType 1:研究；2：过程；3：设施；4方案；5：报告
		//chkItemType 1：方案；2：报告；3：变更；4：基于研究的检查项
		List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupAndChkType(dictStudyType.getStudyGroupID(),1);
		Integer days = dictStudyType.getStudyPeriod();
		String unit = dictStudyType.getStudyPeriodUnit();//天 周 月
		if(days!=null)
		{
			if("周".equals(unit))
			{
				days = days*7;
			}
			if("月".equals(unit))
			{
				days = days*30;
			}
			
			if(dictChkItemsStartDateList==null)//防止空指针
				dictChkItemsStartDateList="";
			if(planChkAreas==null)
				planChkAreas = "";
			
			String[] dateList = dictChkItemsStartDateList.split(",");
			String[] planChkAreaList = planChkAreas.split(",");
			
			if(regs!=null&&dateList.length>=regs.size()
					&&planChkAreaList!=null&&planChkAreaList.length>=regs.size())//所有检查项的开始日期都过来了
			{
				getPlansByChkItemGroupReg(qastudyChkIndex,days, plans, regs,dateList,planChkAreaList);
				//保存进数据库
				for(QAChkPlan plan:plans)
				{
					qAChkPlanService.save(plan);
				}
				map.put("success", true);
				
			}else{
				map.put("success", false);
				map.put("msg", "该试验相关检查项的开始检查日期限没有设置完整！");
			}
			
		}else{
			map.put("success", false);
			map.put("msg", "该试验没有设置周期！");
		}
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void getAllTaskType()
	{
		List<TblTaskTypeJson>  list = new ArrayList<TblTaskTypeJson>();
		//TODO根据权限显示
		List<Integer> taskKind = new ArrayList<Integer>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		 //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理  7分析 8生态毒理
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-委托管理")){
			taskKind.add(1);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-动物试验")){
			taskKind.add(2);
		////}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-临床检验")){
			taskKind.add(3);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-毒性病理")){
			taskKind.add(4);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-QA管理")){
			taskKind.add(5);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-供试品管理")){
			taskKind.add(6);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-分析")){
			taskKind.add(7);
		//}
		//if(userService.checkPrivilege(user, "日程管理-常规任务分配-生态毒理")){
			taskKind.add(8);
		//}
		
		List<Integer> taskList = tblTaskTypeService.gettaskKind(taskKind);
		List<TblTaskType>  objList=tblTaskTypeService.getAll(taskKind);
		for(Integer obj:taskList){
			TblTaskTypeJson  taskType = new TblTaskTypeJson();
			taskType.setId(obj+"");
			taskType.setTaskKind(obj);
			taskType.setTaskName(obj+"");
			taskType.setState("closed");
			taskType.setIconCls("icon-space");
			/*
			String canSee = "";
			// 前台显示负责人
			List<TblTaskTypeLeader>  leaderlist = tblTaskTypeLeaderService.getByTaskTypeIDList(obj+"");
			for(TblTaskTypeLeader leader:leaderlist){
				if(leader.getEndDate() == null || leader.getEndDate().equals("")|| leader.getEndDate().after(new Date())){
					if(canSee != ""){
						 canSee = canSee+",";
					 }
					String name = userService.getRealNameByUserName(leader.getTaskLeader());
					if( leader.getSignId()== null){
						canSee = canSee +"<a style='color:red;'>"+name+"</a>" ;
					}else{
						canSee = canSee +name ;
					}
					
				}
			}
			taskType.setCanSee(canSee);
			*/
			list.add(taskType);
		}
		for(TblTaskType obj:objList){
			TblTaskTypeJson  taskType = new TblTaskTypeJson();
			taskType.setId(obj.getId());
			taskType.setIconCls("icon-space");
			taskType.set_parentId(obj.getTaskKind()+"");
			taskType.setTaskName(obj.getTaskName());
			taskType.setValidFlag(obj.getValidFlag());
			List<TblTaskTypeField> seelist = tblTaskTypeFieldService.getByTaskTypeFieldId(obj.getId());
			String canSee = "";
			for(TblTaskTypeField field:seelist){
				 int kind = field.getTaskKind2();
				 if(canSee != ""){
					 canSee = canSee+",";
				 }
				 if(kind == 1){
					 canSee = canSee + "委托管理";
				 }else if(kind == 2){
					 canSee = canSee + "动物试验";
				 }else if(kind == 3){
					 canSee = canSee + "临床检验 ";
				 }else if(kind == 4){
					 canSee = canSee + "毒性病理";
				 }else if(kind == 5){
					 canSee = canSee + "QA管理";
				 }else if(kind == 6){
					 canSee = canSee + "供试品管理";
				 }else if(kind == 7){
					 canSee = canSee + "分析";
				 }else if(kind == 8){
					 canSee = canSee + "生态毒理";
				 }
			}
			taskType.setCanSee(canSee);
			list.add(taskType);
		}
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("rows",list);
		// map.put("taskIds",taskIds);
		 String json = JsonPluginsUtil.beanToJson(map);
	     writeJson(json);
	}
	
	public void getChkItemStudyGroupReg()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		TblStudyItem item= tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		if(item!=null)
		{
			DictStudyType dictStudyType = dictStudyTypeService.getByStudyTypeCode(item.getStudyTypeCode());
			
			if(dictStudyType.getStudyGroupID()!=null)
			{
				List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupId(dictStudyType.getStudyGroupID());
				for(DictChkItemStudyGroupReg reg:regs)
				{
					//1：方案；2：报告；3：变更；4：基于研究的检查项
					if(reg.getDictQacheckItem().getChkItemType()==4)
					{
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put("chkItemStudyGroupRegId", reg.getChkItemStudyGroupRegId());
						map.put("chkFreq", reg.getChkFreq());
						map.put("chkFreqFlag", reg.getChkFreqFlag());
						map.put("chkFreqUnit", reg.getChkFreqUnit());
						map.put("chkItemId", reg.getDictQacheckItem().getChkItemId());
						map.put("chkItemName", reg.getDictQacheckItem().getChkItemName());
						map.put("chkItemType", reg.getDictQacheckItem().getChkItemType());
						//map.put("", reg.getDictStudyGroup().getStudyGroupId());
						map.put("dictChkItemsStartDate", DateUtil.getNow("yyyy-MM-dd"));
						
						mapList.add(map);
					}
					
				}
			
			}
		
		}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
		
	}
	
	public void del()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkPlan plan =  qAChkPlanService.getById(model.getChkPlanId());
		if(plan!=null)
		{
			if(plan.getChkIndex()==null)
			{
				qAChkPlanService.delete(model.getChkPlanId());
				map.put("success", true);
			}else {
				map.put("success", false);
				map.put("msg", "该计划已经开始检查，不可以删除");
				
			}
		}else{
			map.put("success", false);
			map.put("msg", "该计划已经不存在！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void hasCommitted()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(qAStudyChkIndex.getChkPlanState()!=null&&qAStudyChkIndex.getChkPlanState()==1)
		{
			map.put("msg", "已经提交过了");
		}else {
			map.put("success", true);
		}
		/*else if(qAStudyChkIndex.getChkPlanState()==1){
			map.put("success", false);
			map.put("msg", "该专题检查计划已经被提交");
		}else if(qAStudyChkIndex.getChkPlanState()==2){
			map.put("success", false);
			map.put("msg", "该专题检查计划已经审批通过");
		}*/
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void commitPlans()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//签名写入
		writeES("提交基于研究的检查计划签字",801,"QAStudyChkIndex",studyNoParam);
		
		//日志录入
		writeLog("提交计划","检查计划提交","提交基于研究的检查计划,专题编号："+studyNoParam);
	
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);		
		qAStudyChkIndex.setChkPlanState(1);//0：草稿；1：提交；-1：QAM否决；2：通过
		qAStudyChkIndex.setChkPlanAuthor(user.getRealName());
		/*if(dealScheduleChange!=null&&dealScheduleChange==1)
		{
			//因为处理日程变更开始的时候已经备份了，但是检查计划还是老的检查计划，这次提交应该版本加1
			List<QAChkPlan> plans = qAChkPlanService.getByStudyNo(studyNoParam);
			for(QAChkPlan plan:plans)
			{
				plan.setChkPlanVersion(plan.getChkPlanVersion()+1);
			}
			qAStudyChkIndex.setScheduleChangedFlag(2);
			
		}*/
		qAStudyChkIndex.setChkPlanCurVersion(qAChkPlanService.getMaxVersionByStudyNo(studyNoParam));
				
		qAStudyChkIndexService.update(qAStudyChkIndex);
		
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String studyNoName = "";
		if(studyItem!=null)
		{
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			
		}
		//通知QAM去审批
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		String msgTitle="QA("+user.getRealName()+")，提交了检查计划； 专题编号:　"+studyNoParam+"专题名称："+studyNoName+"　需要审批";//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"提交了检查计划；<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>需要审批，特此提醒！";
		String receiverList = "";
		List<User> users = userService.findByPrivilegeName("QA负责人");
		if(users!=null&&users.size()>0)
		{
			receiverList+=(users.get(0).getUserName())+",";
		}
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void toAppointQA()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkPlan plan = qAChkPlanService.getById(model.getChkPlanId());
		User user=userService.getByUserName(qa);
		plan.setPlanChkOperator(user.getRealName());
		qAChkPlanService.update(plan);
		writeLog("重新指定计划QA",(plan.getTaskName()==null?"":plan.getTaskName())+":"+plan.getChkItemName(),"重新指定"+qa+"为检查计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName()+"的QA检查员");
		
		map.put("success", true);
		//发邮件
		studyNoParam=plan.getQastudyChkIndex().getStudyNo();
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String studyNoName = "";
		if(studyItem!=null)
		{
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			
		}
		//通知QA
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		String msgTitle="QAM("+getCurrentRealName()+")指派"+user.getRealName()+"为一个检查计划的QA检查员； 专题编号:　"+studyNoParam+"专题名称："+studyNoName+"　计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QAM("+getCurrentRealName()+")于"+currentDate+"指派"+user.getRealName()+"为一个检查计划的QA检查员；<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>计划："+(plan.getTaskName()==null?"":plan.getTaskName())+"->"+plan.getChkItemName();
		String receiverList = "";
		receiverList+=user.getUserName()+",";
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	//获取需要审批的检查计划
	public void getPlanList()
	{
		//0：草稿；1：提交；-1：QAM否决；2：通过
		List<QAChkPlan_JSON> planJsons=new ArrayList<QAChkPlan_JSON>();
		QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(index!=null&&index.getChkPlanState()!=null&&index.getChkPlanState()==1)
		{
			//Set<QAChkPlan> plans = index.getQachkPlans();
			//QAStudyChkIndex的主键是studyNo
			List<QAChkPlan> plans = qAChkPlanService.getByStudyNo(index.getStudyNo());
			for(QAChkPlan plan:plans)
			{
				QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
				planJSON.setStudyNo(plan.getQastudyChkIndex().getStudyNo());
				planJSON.setChkFinishedFlag(plan.getChkFinishedFlag());
				planJSON.setChkIndex(plan.getChkIndex());
				planJSON.setChkItemId(plan.getChkItemId());
				planJSON.setChkItemName(plan.getChkItemName());
				planJSON.setChkOperator(plan.getChkOperator());
				planJSON.setChkPlanId(plan.getChkPlanId());
				planJSON.setChkPlanType(plan.getChkPlanType());
				planJSON.setChkPlanVersion(plan.getChkPlanVersion());
				planJSON.setChkTime(plan.getChkTime());
				planJSON.setCreateTime(plan.getCreateTime());
				planJSON.setPlanChkArea(plan.getPlanChkArea());
				planJSON.setPlanChkOperator(plan.getPlanChkOperator());
				planJSON.setPlanChkTime(plan.getPlanChkTime());
				planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
				planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
				planJSON.setScheduleName(plan.getScheduleName());
				planJSON.setScheduleTime(plan.getScheduleTime());
				planJSON.setTaskName(plan.getTaskName());
				planJSON.setTaskNameId(plan.getTaskNameId());
				planJSON.setNumber(plan.getNumber());
				planJSON.setSOPFlag(plan.getSOPFlag());
				
				planJsons.add(planJSON);
			}
			
		}
		
		String[] _nory_format = {"chkPlanId","studyNo", "chkPlanType","chkItemId","chkItemName",
				"number","SOPFlag","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion"};
		String json = JsonPluginsUtil.beanListToJson(planJsons, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);		
	
	}
	public void saveChkPlanResult()
	{
		String resultString="";
		QAApprovalOpinion opinion = new QAApprovalOpinion();
		opinion.setApprovalName(getCurrentRealName());
		opinion.setApprovalTime(new Date());
		opinion.setApprovalOpinion(reason);
		opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
		
		opinion.setApprovalType(4);
		opinion.setObjectCode(studyNoParam);
		
		opinion.setOperatorType(2);
		if(status==2)
		{
			//签名写入
			writeES("基于研究的检查计划审批通过",810,"QAStudyChkIndex",studyNoParam);
			resultString="通过";
			//日志录入
			writeLog("审批计划","检查计划审批通过","审批通过基于研究的检查计划,专题检查索引编号："+studyNoParam);
			opinion.setApprovalResultFlag(1);//1：过；-1：未过
			opinion.setApprovalResult("通过");
		}else if(status==-1)
		{
			//签名写入
			writeES("基于研究的检查计划QAM否决",811,"QAStudyChkIndex",studyNoParam);
			resultString="否决";
			//日志录入
			writeLog("审批计划","QAM否决检查计划","QAM否决基于研究的检查计划,专题检查索引编号："+studyNoParam);
			opinion.setApprovalResultFlag(-1);//1：过；-1：未过
			opinion.setApprovalResult("否决");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		index.setChkPlanApprovalTime(new Date());
		User user = (User)ActionContext.getContext().getSession().get("user");
		index.setChkPlanApprover(user.getRealName());
		index.setChkPlanState(status);//-1或者2
		Integer versionInteger = qAChkPlanService.getVersionByStudyNo(studyNoParam);
		index.setChkPlanCurVersion(versionInteger);
		
		opinion.setObjectVersion(versionInteger);
		qAApprovalOpinionService.save(opinion);
		
		qAStudyChkIndexService.update(index);
		
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String studyNoName = "";
		if(studyItem!=null)
		{
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
		}
		//通知QA
		//当前时间
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
		
		String msgTitle="QAM"+resultString+"了检查计划,专题编号：　"+studyNoParam+"，专题名称："+studyNoName;
		
		String msgContent = "<br>";
		msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+resultString+"了检查计划，<br>专题编号： " +studyNoParam+"，<br>专题名称："+studyNoName+"  ，特此提醒";
		
		//接收者列表
		String receiverList = "";
		//获取QA
		String qa = index.getInspector();
		if(qa!=null&&!"".equals(qa))
		{
			User qaUser = userService.getByRealName(qa);
			receiverList+=(qaUser.getUserName())+",";
		}
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void loadComboTreeList()
	{
		List<ComboTreeModel> tree = new ArrayList<ComboTreeModel>();
		
		//ComboTreeModel ctm = new ComboTreeModel();
		//ctm = new ComboTreeModel();
		//ctm.setId("");
		//ctm.setText("");
		
		//tree.add(ctm);
		
		List<DictChkArea> list = dictChkAreaService.getAll();
		//生成树形结构
		getTree(list,tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
	}
	public void getTree(List<DictChkArea> list,List<ComboTreeModel> tree)
	{
		List<DictChkArea> noDealList = new ArrayList<DictChkArea>();
		ComboTreeModel ctm = null;
	
		for(int i=0;i<list.size();i++)
		{
			DictChkArea type=list.get(i);
			if(type.getParentAreaID()==null||"".equals(type.getParentAreaID()))
			{
				//没有父类就是第一级，直接加入tree
				ctm = new ComboTreeModel();
				ctm.setId(type.getAreaID());
				ctm.setText(type.getAreaName());
				
				tree.add(ctm);
				
			}else {//有父类的处理
				
				ComboTreeModel parent = getParent(type,tree);
				if(parent!=null)//父类不为空，并且父类在tree中存在
				{
					ComboTreeModel ctmChile = new ComboTreeModel();
					ctmChile.setId(type.getAreaID());
					ctmChile.setText(type.getAreaName());
					if(parent.getChildren()==null)
						parent.setChildren(new ArrayList<ComboTreeModel>());
					parent.setState("closed");
					parent.getChildren().add(ctmChile);
				}else {//父类不为空，并且tree中不存在,先处理list中的其他的
					noDealList.add(type);
				}
			}
			
		}
		if(noDealList.size()>0)
		{
			getTree(noDealList, tree);
		}
	}
	public ComboTreeModel getParent(DictChkArea type,List<ComboTreeModel> tree)
	{
		ComboTreeModel parent = null;
		for(ComboTreeModel model:tree)
		{
			if(model.getId().equals(type.getParentAreaID()))
			{
				parent=model;
				break;
			}
			if(model.getChildren()!=null)
			{
				parent=getParent(type,model.getChildren());
				if(parent!=null)
				{
					break;
				}
			}
		}
		
		return parent;
		
	}
	
	public void sendNotification()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> receList = new ArrayList<String>();
			if(receiverList!=null)
			{
				String[] resList = receiverList.split(",");
				if(resList!=null&&resList.length>0)
				{
					for(String str:resList)
					{
						receList.add(str);
					}
					
				}
			}
			//通知QAM去审批
			TblNotification tblNotification = new TblNotification();
			tblNotification.setId(tblNotificationService.getKey("TblNotification"));
			
			tblNotification.setMsgTitle(msgTitle);//消息头
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			
			tblNotificationService.save(tblNotification,receList);
			map.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg","发送邮件过程中出现异常"+e.getMessage());
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	private void writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(EsTypeDesc);
		es.setEsType(EsType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(EsType);
		esLink.setEsTypeDesc(EsTypeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}
	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
	}
	public String getStudyNoParam() {
		return studyNoParam;
	}
	public void setStudyNoParam(String studyNoParam) {
		this.studyNoParam = studyNoParam;
	}
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}
	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
	public TblStudyItemService getTblStudyItemService() {
		return tblStudyItemService;
	}
	public void setTblStudyItemService(TblStudyItemService tblStudyItemService) {
		this.tblStudyItemService = tblStudyItemService;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getStudyNoString() {
		return studyNoString;
	}
	public void setStudyNoString(String studyNoString) {
		this.studyNoString = studyNoString;
	}
	public TblSchedulePlanService getTblSchedulePlanService() {
		return tblSchedulePlanService;
	}
	public TblTaskTypeService getTblTaskTypeService() {
		return tblTaskTypeService;
	}
	public void setTblTaskTypeService(TblTaskTypeService tblTaskTypeService) {
		this.tblTaskTypeService = tblTaskTypeService;
	}
	public void setTblSchedulePlanService(
			TblSchedulePlanService tblSchedulePlanService) {
		this.tblSchedulePlanService = tblSchedulePlanService;
	}
	public String getChkPlanIds() {
		return chkPlanIds;
	}
	public void setChkPlanIds(String chkPlanIds) {
		this.chkPlanIds = chkPlanIds;
	}
	public String getTaskName() {
		return taskName;
	}
	public DictChkItemChkTblRegService getDictChkItemChkTblRegService() {
		return dictChkItemChkTblRegService;
	}
	public void setDictChkItemChkTblRegService(
			DictChkItemChkTblRegService dictChkItemChkTblRegService) {
		this.dictChkItemChkTblRegService = dictChkItemChkTblRegService;
	}
	public DictScheduleChkItemService getDictScheduleChkItemService() {
		return dictScheduleChkItemService;
	}
	public void setDictScheduleChkItemService(
			DictScheduleChkItemService dictScheduleChkItemService) {
		this.dictScheduleChkItemService = dictScheduleChkItemService;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getScheduleChkItemId() {
		return scheduleChkItemId;
	}
	public void setScheduleChkItemId(String scheduleChkItemId) {
		this.scheduleChkItemId = scheduleChkItemId;
	}
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	public String getIndexTitle() {
		return indexTitle;
	}
	public void setIndexTitle(String indexTitle) {
		this.indexTitle = indexTitle;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getQa() {
		return qa;
	}
	public void setQa(String qa) {
		this.qa = qa;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCatalog() {
		return catalog;
	}
	public void setCatalog(Integer catalog) {
		this.catalog = catalog;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getQAMainPage() {
		return QAMainPage;
	}
	public void setQAMainPage(String qAMainPage) {
		QAMainPage = qAMainPage;
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
	public TblTaskTypeFieldService getTblTaskTypeFieldService() {
		return tblTaskTypeFieldService;
	}
	public void setTblTaskTypeFieldService(
			TblTaskTypeFieldService tblTaskTypeFieldService) {
		this.tblTaskTypeFieldService = tblTaskTypeFieldService;
	}
	public TblTaskTypeLeaderService getTblTaskTypeLeaderService() {
		return tblTaskTypeLeaderService;
	}
	public void setTblTaskTypeLeaderService(
			TblTaskTypeLeaderService tblTaskTypeLeaderService) {
		this.tblTaskTypeLeaderService = tblTaskTypeLeaderService;
	}
	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}
	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}
	public QAChkPlanHisService getqAChkPlanHisService() {
		return qAChkPlanHisService;
	}
	public void setqAChkPlanHisService(QAChkPlanHisService qAChkPlanHisService) {
		this.qAChkPlanHisService = qAChkPlanHisService;
	}
	public String getFileIndexId() {
		return fileIndexId;
	}
	public void setFileIndexId(String fileIndexId) {
		this.fileIndexId = fileIndexId;
	}
	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}
	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}
	public QAChkPlanChangeIndexService getqAChkPlanChangeIndexService() {
		return qAChkPlanChangeIndexService;
	}
	public void setqAChkPlanChangeIndexService(
			QAChkPlanChangeIndexService qAChkPlanChangeIndexService) {
		this.qAChkPlanChangeIndexService = qAChkPlanChangeIndexService;
	}
	public Integer getDealScheduleChange() {
		return dealScheduleChange;
	}
	public void setDealScheduleChange(Integer dealScheduleChange) {
		this.dealScheduleChange = dealScheduleChange;
	}
	public String getVersionStr() {
		return versionStr;
	}
	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public String getOneChkPlanId() {
		return oneChkPlanId;
	}
	public void setOneChkPlanId(String oneChkPlanId) {
		this.oneChkPlanId = oneChkPlanId;
	}
	public TblTestItemService getTblTestItemService() {
		return tblTestItemService;
	}
	public void setTblTestItemService(TblTestItemService tblTestItemService) {
		this.tblTestItemService = tblTestItemService;
	}
	public DictStudyTypeService getDictStudyTypeService() {
		return dictStudyTypeService;
	}
	public void setDictStudyTypeService(DictStudyTypeService dictStudyTypeService) {
		this.dictStudyTypeService = dictStudyTypeService;
	}
	public QAChkReportRecordService getqAChkReportRecordService() {
		return qAChkReportRecordService;
	}
	public void setqAChkReportRecordService(
			QAChkReportRecordService qAChkReportRecordService) {
		this.qAChkReportRecordService = qAChkReportRecordService;
	}
	public String getSelectChkReportCode() {
		return selectChkReportCode;
	}
	public void setSelectChkReportCode(String selectChkReportCode) {
		this.selectChkReportCode = selectChkReportCode;
	}
	public String getOneChildChkPlanId() {
		return oneChildChkPlanId;
	}
	public void setOneChildChkPlanId(String oneChildChkPlanId) {
		this.oneChildChkPlanId = oneChildChkPlanId;
	}
	public Integer getNewReport() {
		return newReport;
	}
	public void setNewReport(Integer newReport) {
		this.newReport = newReport;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getJustChkPlan() {
		return justChkPlan;
	}
	public void setJustChkPlan(Integer justChkPlan) {
		this.justChkPlan = justChkPlan;
	}
	public String getDictChkItemsStartDateList() {
		return dictChkItemsStartDateList;
	}
	public void setDictChkItemsStartDateList(String dictChkItemsStartDateList) {
		this.dictChkItemsStartDateList = dictChkItemsStartDateList;
	}
	public String getPlanChkAreas() {
		return planChkAreas;
	}
	public void setPlanChkAreas(String planChkAreas) {
		this.planChkAreas = planChkAreas;
	}
	public String getAfterTabOpenAction() {
		return afterTabOpenAction;
	}
	public void setAfterTabOpenAction(String afterTabOpenAction) {
		this.afterTabOpenAction = afterTabOpenAction;
	}
	
	
}
