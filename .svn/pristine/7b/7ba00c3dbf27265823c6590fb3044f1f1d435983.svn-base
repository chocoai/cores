package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import com.lanen.model.qa.QAChkReportRecord;
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
import com.lanen.service.qa.QAChkPlanChangeIndexService;
import com.lanen.service.qa.QAChkPlanHisService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAChkReportRecordService;
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
public class QAChkPlanHisAction extends BaseAction<QAChkPlanHis>{

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
	private QAApprovalOpinionService qAApprovalOpinionService;
	
	
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
	
	private String reason;
	
	private String dictChkItemsStartDateList;
	private String planChkAreas;
	

	public void getSOPFlagList()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("text", "SOP");
		mapList.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("text", "SOP+方案");
		mapList.add(map);
		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("text", "方案");
		mapList.add(map);
		
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	public void getPlanListByVersion()
	{
		List<QAChkPlan_JSON> planJsons=new ArrayList<QAChkPlan_JSON>();
		if(versionStr.contains(","))
		{
			//String realVersion = versionStr.substring(0, versionStr.indexOf(","));
			//qachkplanHis有效的只有一个版本
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
				planJSON.setScheduleName(plan.getScheduleName());
				planJSON.setScheduleTime(plan.getScheduleTime());
				planJSON.setTaskName(plan.getTaskName());
				planJSON.setTaskNameId(plan.getTaskNameId());
				planJSON.setNumber(plan.getNumber());
				planJSON.setSOPFlag(plan.getSOPFlag());
				
				planJSON.setChkPlanState(plan.getQastudyChkIndex().getChkPlanState());//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
				planJSON.setChkPlanVersion(2);//2代表是历史记录即是不可编辑的记录
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
				
				planJSON.setChkPlanId(plan.getChkPlanHisId());////页面的chkPlanId是chkPlanHisId
				
				planJSON.setChkPlanType(plan.getChkPlanType());
				planJSON.setChkPlanVersion(plan.getChkPlanVersion());
				planJSON.setChkTime(plan.getChkTime());
				planJSON.setCreateTime(plan.getCreateTime());
				planJSON.setPlanChkArea(plan.getPlanChkArea());
				planJSON.setPlanChkOperator(plan.getPlanChkOperator());
				planJSON.setPlanChkTime(plan.getPlanChkTime());
			//	planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
				planJSON.setScheduleChkItemId(plan.getScheduleChkItemId());
				planJSON.setScheduleName(plan.getScheduleName());
				planJSON.setScheduleTime(plan.getScheduleTime());
				planJSON.setTaskName(plan.getTaskName());
				planJSON.setTaskNameId(plan.getTaskNameId());
				planJSON.setNumber(plan.getNumber());
				planJSON.setSOPFlag(plan.getSOPFlag());
				
			//	planJSON.setChkPlanState(plan.getQastudyChkIndex().getChkPlanState());//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
				if(plan.getChkPlanVersion()==-1)
				{
					planJSON.setChkPlanVersion(1);//可编辑的版本
				}else{
					planJSON.setChkPlanVersion(2);//2代表历史版本,即不可以编辑的版本					
				}
				
				planJsons.add(planJSON);
			}
		}
		
		Collections.sort(planJsons, new Comparator<QAChkPlan_JSON>() {

			public int compare(QAChkPlan_JSON o1, QAChkPlan_JSON o2) {
				// TODO Auto-generated method stub
				return o2.getPlanChkTime().compareTo(o1.getPlanChkTime());
			}
		});
		
		String[] _nory_format = {"chkPlanId","chkPlanState","studyNo", "chkPlanType",
				"number","SOPFlag","chkItemId","chkItemName","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion"};
		String json = JsonPluginsUtil.beanListToJson(planJsons, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);		
	}
	
	
	public void save()
	{
		
			
				QAChkPlanHis qAChkPlan = new QAChkPlanHis();
				
				String key=qAChkPlanHisService.getKey("QAChkPlanHis");
				qAChkPlan.setChkPlanHisId(key);
				
				qAChkPlan.setChkItemId(model.getChkItemId());
				qAChkPlan.setChkItemName(model.getChkItemName());
				
				TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				
				//qAChkPlan.setChkOperator(item.getQa());
				if(item!=null)
					qAChkPlan.setPlanChkOperator(item.getQa());
				
				qAChkPlan.setChkPlanType(1);//1:研究；2：过程；3：设施
				
				qAChkPlan.setChkPlanVersion(-1);//变更添加的记录版本是-1
				
				
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
				
				qAChkPlan.setScheduleId(model.getScheduleId());
				
				qAChkPlan.setPlanChkTime(model.getPlanChkTime());
				qAChkPlan.setScheduleTime(model.getScheduleTime());
				
				
				//qAChkPlan.setQastudyChkIndex(qAStudyChkIndex);
				qAChkPlan.setStudyNo(studyNoParam);
			
				qAChkPlanHisService.save(qAChkPlan);
				
			//}
			
		//}
		
	}
	
	public void updatePlanTimeOrArea()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlanHis plan = qAChkPlanHisService.getById(model.getChkPlanId());//这里的chkPlanId是chkPlanHisId
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
		qAChkPlanHisService.update(plan);
		
		map.put("success",true);
		map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
		map.put("planChkArea", plan.getPlanChkArea());
		map.put("SOPFlag", plan.getSOPFlag());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	
	
	public void delAllStudyPlan(String studyNoParam)
	{
		List<QAChkPlanHis> existPlans = qAChkPlanHisService.getByStudyNoAndVersion(studyNoParam,-1);
		for(QAChkPlanHis plan :existPlans)
		{
			qAChkPlanHisService.delete(plan.getChkPlanHisId());
		}
		
	}
	public void getCirclePlans(QAStudyChkIndex qastudyChkIndex ,List<QAChkPlanHis> plans,List<TblSchedulePlan_Json> tblSchedulePlanlist,String[] planChkAreaList)
	{
		//因为备份完以后，plan数据库就删除了。所以不用看了。
		//Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
		
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		//备份上一个版本的
		//backupStudyPlan();//可以不传参数吗？
		delAllStudyPlan(studyNoParam);
		
		Map<String, Integer> maxSnMap = new HashMap<String, Integer>();
		
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
					
					QAChkPlanHis plan = new QAChkPlanHis();
					//1：方案；2：报告；3：变更；4：基于研究的检查项
					DictQACheckItem qachkItem = dictQACheckItemService.getByItemName(4,item.getChkItemName());
					if(qachkItem!=null)//检查项是基于研究的检查项
					{
						
						plan.setChkItemId(qachkItem.getChkItemId());
						plan.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
						plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
						plan.setChkPlanVersion(-1);
						plan.setCreateTime(new Date());
						
						plan.setNumber(0);
						plan.setSOPFlag(model.getSOPFlag());
						
						plan.setChkItemName(item.getChkItemName());
						if(studyItem!=null)
						{
							plan.setPlanChkOperator(studyItem.getQa());
						}
						plan.setPlanChkTime(schedulePlan.getDateTimeDate());
						//plan.setQastudyChkIndex(qastudyChkIndex);
						plan.setStudyNo(studyNoParam);
						
						plan.setScheduleChkItemId(item.getScheduleChkItemId());
						plan.setScheduleId(schedulePlan.getScheduleID());
						plan.setScheduleName(schedulePlan.getTaskName());
						plan.setScheduleTime(schedulePlan.getDateTimeDate());
						plan.setTaskName(schedulePlan.getTaskName());
						plan.setTaskNameId(item.getTaskNameId());
						plan.setPlanChkArea(planChkArea);
						
						plans.add(plan);
					}else {
						continue;
					}
				}else {
					Collections.sort(plans,new Comparator<QAChkPlanHis>() {

						public int compare(QAChkPlanHis plan1, QAChkPlanHis plan2) {
							if(plan1.getPlanChkTime().before(plan2.getPlanChkTime()))
							{
								return 1;
							}
							return -1;
						}
					});
					//周期的日程，三个月循环加入一次
				
					for(QAChkPlanHis onePlan:plans)
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
									QAChkPlanHis plan = new QAChkPlanHis();
									
									plan.setChkItemId(qachkItem.getChkItemId());
									
									plan.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
									
									plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
									plan.setChkPlanVersion(-1);
									plan.setCreateTime(new Date());
									plan.setNumber(maxSnMap.get(onePlan.getScheduleId()));
									plan.setSOPFlag(model.getSOPFlag());
									
									plan.setChkItemName(item.getChkItemName());
									if(studyItem!=null)
									{
										plan.setPlanChkOperator(studyItem.getQa());
									}
									plan.setPlanChkTime(schedulePlan.getDateTimeDate());
									//plan.setQastudyChkIndex(qastudyChkIndexs);
									plan.setStudyNo(studyNoParam);
									
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
	public void getPlansByChkItemGroupReg(QAStudyChkIndex qastudyChkIndex,Integer days,List<QAChkPlanHis> plans, List<DictChkItemStudyGroupReg> regs,String[] startDateList,String[] planChkAreaList)
	//public void getPlansByChkItemGroupReg(QAStudyChkIndex qastudyChkIndex,Integer days,List<QAChkPlanHis> plans, List<DictChkItemStudyGroupReg> regs)
	{
		//因为备份完以后，plan数据库就删除了。所以不用看了。
		//Integer maxVersion = qAChkPlanHisService.getMaxVersionByStudyNo(studyNoParam);
		
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
					QAChkPlanHis plan = new QAChkPlanHis();
					plan.setChkItemId(reg.getDictQacheckItem().getChkItemId());
					
					plan.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
					
					plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
					plan.setChkPlanVersion(-1);//变更的版本
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
					
					//plan.setQastudyChkIndex(qastudyChkIndex);
					plan.setStudyNo(studyNoParam);
					
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
					QAChkPlanHis plan = new QAChkPlanHis();
					
					plan.setChkItemId(reg.getDictQacheckItem().getChkItemId());
					
					plan.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
					
					plan.setChkPlanType(1);//;//1:研究；2：过程；3：设施
					plan.setChkPlanVersion(-1);
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
					
					//plan.setQastudyChkIndex(qastudyChkIndex);
					plan.setStudyNo(studyNoParam);
					
				//	plan2.setScheduleChkItemId(item.getScheduleChkItemId());
				//	plan2.setScheduleId(schedulePlan.getScheduleID());
				//	plan2.setScheduleName(schedulePlan.getTaskName());
				//	plan2.setScheduleTime(schedulePlan.getDateTimeDate());
				//	plan2.setTaskName(schedulePlan.getTaskName());
				//	plan2.setTaskNameId(item.getTaskNameId());
					
					plans.add(plan);
					Date tempDate = startDate;
					int num=0;
					for(int i=0;i<dates.size();i++)
					{
						Date date = dates.get(i);
						
						Calendar calendar1 = new GregorianCalendar();
						calendar1.setTime(tempDate);
						calendar1.add(Calendar.MONTH, 3);
						boolean flag = calendar1.getTime().after(date);
						if(!flag)
						{
							num+=1;
							tempDate = date;
							//时间间隔超过三个月
							QAChkPlanHis plan2 = new QAChkPlanHis();
							
							plan2.setChkItemId(reg.getDictQacheckItem().getChkItemId());
							
							plan2.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
							
							plan2.setChkPlanType(1);//;//1:研究；2：过程；3：设施
							plan2.setChkPlanVersion(-1);
							plan2.setCreateTime(new Date());
							plan2.setNumber(num);
							plan2.setSOPFlag(model.getSOPFlag());
							plan2.setChkItemName(reg.getDictQacheckItem().getChkItemName());
							if(studyItem!=null)
							{
								plan2.setPlanChkOperator(studyItem.getQa());
							}
							plan2.setPlanChkTime(date);
							plan2.setPlanChkArea(planChkArea);
							
							//plan2.setQastudyChkIndex(qastudyChkIndex);
							plan2.setStudyNo(studyNoParam);
							
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
		
		List<QAChkPlanHis> plans = new ArrayList<QAChkPlanHis>();
		List<TblSchedulePlan_Json> tblSchedulePlanlist = tblSchedulePlanService.getHasSubmitSchedulePlanJson(2,studyNoParam, 2);
		
		if(planChkAreas==null)
			planChkAreas="";
		String[] planChkAreaList = planChkAreas.split(",");
		
		getCirclePlans( qastudyChkIndex , plans, tblSchedulePlanlist,planChkAreaList);//保存循环的记录
		
		//保存进数据库
		for(QAChkPlanHis plan:plans)
		{
			qAChkPlanHisService.save(plan);
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
		
		List<QAChkPlanHis> plans = new ArrayList<QAChkPlanHis>();
		
		
		DictStudyType dictStudyType = dictStudyTypeService.getByStudyTypeCode(studyItem.getStudyTypeCode());
		//List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupId(dictStudyType.getStudyGroupID());
		//chkindex chkType 1:研究；2：过程；3：设施；4方案；5：报告
		//chkItemType 1：方案；2：报告；3：变更；4：基于研究的检查项
		List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupAndChkType(dictStudyType.getStudyGroupID(),1);
		
		Integer days = dictStudyType.getStudyPeriod();
		if(days!=null)
		{
			String unit = dictStudyType.getStudyPeriodUnit();//天 周 月
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
			
			if(regs!=null&&dateList.length>=regs.size()//所有检查项的开始日期都过来了
					&&planChkAreaList!=null&&planChkAreaList.length>=regs.size())//所有检查项的开始日期都过来了
			{
			
				getPlansByChkItemGroupReg(qastudyChkIndex,days, plans, regs,dateList,planChkAreaList);
			
				//保存进数据库
				for(QAChkPlanHis plan:plans)
				{
					qAChkPlanHisService.save(plan);
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
		QAChkPlanHis plan =  qAChkPlanHisService.getById(model.getChkPlanId());
		if(plan!=null)
		{
			//if(plan.getChkIndex()==null)
			//{
				qAChkPlanHisService.delete(model.getChkPlanId());
				map.put("success", true);
			//}else {
			//	map.put("success", false);
			//	map.put("msg", "该计划已经开始检查，不可以删除");
				
			//}
		}else{
			map.put("success", false);
			map.put("msg", "该计划已经不存在！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void hasCommitted()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkPlanChangeIndex changeIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoCommit(studyNoParam);
		if(changeIndex!=null)
		{
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "不存在没有提交的变更申请！");
		}
	
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void commitPlans()//提交的是变更计划
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkPlanChangeIndex changIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		if(changIndex!=null)
		{
			changIndex.setChangeState(1);
			
			qAChkPlanChangeIndexService.update(changIndex);
			
			writeES("提交检查计划变更申请",817,"QAChkPlanChangeIndex",changIndex.getChkPlanChangeIndexId());
			//日志录入
			writeLog("提交检查计划变更申请","提交检查计划申请变更","提交检查计划申请变更,专题编号："+studyNoParam);
			
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
			//当前时间
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
		
			String msgTitle = "QA申请检查计划变更,专题编号：　"+studyNoParam+"，专题名称："+studyNoName;
			String msgContent = "<br>";
	
			msgContent = msgContent+"QA("+getCurrentRealName()+")于"+currentDate+"提交申请检查计划变更，<br>专题编号： " +studyNoParam+"，<br>专题名称："+studyNoName+"，<br>特此提醒";
			
			//接收者列表
			String receiverList = "";
			//获取QAM
			List<User> qams = userService.findByPrivilegeName("QA负责人");
			if(qams!=null&&qams.size()>0)
			{
				receiverList+=(qams.get(0).getUserName())+",";
			}
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
			
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("msg", "不存在需要提交的检查计划变更申请");
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	//获取需要审批的检查计划
	public void getPlanList()
	{
		//0：草稿；1：提交；-1：QAM否决；2：通过
		List<QAChkPlan_JSON> planJsons=new ArrayList<QAChkPlan_JSON>();
		QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(index!=null&&index.getChkPlanState()!=null&&index.getChkPlanState()==2)
		{
			//Set<QAChkPlan> plans = index.getQachkPlans();
			//QAStudyChkIndex的主键是studyNo
			List<QAChkPlanHis> plans = qAChkPlanHisService.getByStudyNoAndVersion(index.getStudyNo(),-1);
			for(QAChkPlanHis plan:plans)
			{
				QAChkPlan_JSON planJSON=new QAChkPlan_JSON();
				planJSON.setStudyNo(plan.getStudyNo());
				planJSON.setChkFinishedFlag(plan.getChkFinishedFlag());
			//	planJSON.setChkIndex(plan.getChkIndex());
				planJSON.setChkItemId(plan.getChkItemId());
				planJSON.setChkItemName(plan.getChkItemName());
				planJSON.setChkOperator(plan.getChkOperator());
				
				planJSON.setChkPlanId(plan.getChkPlanHisId());//页面的chkPlanId是chkPlanHisId
				
				planJSON.setChkPlanType(plan.getChkPlanType());
				planJSON.setChkPlanVersion(plan.getChkPlanVersion());
				planJSON.setChkTime(plan.getChkTime());
				planJSON.setCreateTime(plan.getCreateTime());
				planJSON.setPlanChkArea(plan.getPlanChkArea());
				planJSON.setPlanChkOperator(plan.getPlanChkOperator());
				planJSON.setPlanChkTime(plan.getPlanChkTime());
				//planJSON.setQastudyChkIndex(plan.getQastudyChkIndex());
				
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
		
		String[] _nory_format = {"chkPlanId","studyNo", "chkPlanType","chkItemId",
				"number","SOPFlag","chkItemName","scheduleTime","scheduleName","createTime","planChkTime","planChkArea","chkFinishedFlag","planChkOperator","chkOperator","chkTime","chkPlanVersion"};
		String json = JsonPluginsUtil.beanListToJson(planJsons, "yyyy-MM-dd", _nory_format, true);
		writeJson(json);		
	
	}
	
	public void saveChkPlanResult()//审批变更的记录
	{
		String resultString="";
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAChkPlanChangeIndex changeIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		if(changeIndex!=null&&changeIndex.getChangeState()!=null&&changeIndex.getChangeState()==1)//0：原始；1：提交；-1：否决；2：通过,-2:撤销
		{
			changeIndex.setChangeState(status);
			
			qAChkPlanChangeIndexService.update(changeIndex);
			
			QAApprovalOpinion opinion = new QAApprovalOpinion();
			opinion.setApprovalName(user.getRealName());
			
			opinion.setApprovalOpinion(reason);
			
			//opinion.setApprovalRecordId(changeIndex.getChkPlanChangeIndexId());
			opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
			if(status==2)
			{
				opinion.setApprovalResultFlag(1);
				resultString="通过";
				opinion.setApprovalResult("通过");
				
				Integer planVersion = updateChangeChkPlanToRealPlan(studyNoParam);//弄到真正的计划中。
				
				studyChkIndex.setChkPlanCurVersion(planVersion);
				studyChkIndex.setChkPlanApprover(getCurrentRealName());
				studyChkIndex.setChkPlanApprovalTime(new Date());
				
				//签名写入
				writeES("检查计划变更审批通过",830,"QAChkPlanChangeIndex",changeIndex.getChkPlanChangeIndexId());
				//日志录入
				writeLog("审批计划变更","检查计划变更审批通过","审批通过检查计划变更,专题检查索引编号："+studyNoParam);
				
				
				
			}else if(status==-1){
				opinion.setApprovalResultFlag(-1);
				opinion.setApprovalResult("退回");
				resultString="退回";
				//签名写入
				writeES("检查计划变更QAM否决",831,"QAChkPlanChangeIndex",changeIndex.getChkPlanChangeIndexId());
				resultString="否决";
				//日志录入
				writeLog("审批计划变更","QAM否决检查计划变更","QAM否决检查计划变更,专题检查索引编号："+studyNoParam);
				
				//删除历史表中存在的变更记录
				deleteChangeRecordByStudyNo(studyNoParam);
			}
			qAStudyChkIndexService.update(studyChkIndex);
			
			opinion.setApprovalTime(new Date());
			opinion.setApprovalType(4);//1：报告；2：回复；3：延迟整改；4：检查计划
			opinion.setObjectCode(studyNoParam);
			opinion.setObjectVersion(changeIndex.getSn());
			opinion.setOperatorType(2);//1：FM；2：QAM；3：QA检查员
			
			qAApprovalOpinionService.save(opinion);
			
			
			
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
			
			String msgTitle="QAM"+resultString+"了检查计划变更,专题编号：　"+studyNoParam+"，专题名称："+studyNoName;
			
			String msgContent = "<br>";
			msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+resultString+"了检查计划变更，<br>专题编号： " +studyNoParam+"，<br>专题名称："+studyNoName+"  ，特此提醒";
			
			//接收者列表
			String receiverList = "";
			//获取QA
			String qa = studyChkIndex.getInspector();
			if(qa!=null&&!"".equals(qa))
			{
				User qaUser = userService.getByRealName(qa);
				receiverList+=(qaUser.getUserName())+",";
			}
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
			map.put("success", true);
		}else{
			//if(changeIndex.getChangeState()!=null&&changeIndex.getChangeState()==-2)//0：原始；1：提交；-1：否决；2：通过,-2:撤销
			//{
				map.put("success", false);
				map.put("msg", "变更申请已经撤销");
			//}
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void backupAndDeleteStudyPlan()
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
			his.setNumber(plan.getNumber());
			his.setSOPFlag(plan.getSOPFlag());
			
			qAChkPlanHisService.save(his);
			
			//plan.setChkPlanVersion(plan.getChkPlanVersion()+1);
			//qAChkPlanService.update(plan);
			
			qAChkPlanService.delete(plan.getChkPlanId());
		}
		//QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getById(studyNoParam);
	//	qAStudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
	//	qAStudyChkIndex.setScheduleChangedFlag(2);
	//	qAStudyChkIndexService.update(qAStudyChkIndex);
		
		
	}
	public Integer updateChangeChkPlanToRealPlan(String studyNoParam)
	{
		QAStudyChkIndex chkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		List<QAChkPlanHis> changePlanRecord = qAChkPlanHisService.getByStudyNoAndVersion(studyNoParam, -1);//变更的数据
		Integer curVersion = qAChkPlanService.getVersionByStudyNo(studyNoParam);
		
		backupAndDeleteStudyPlan();
		
		for(QAChkPlanHis change:changePlanRecord)
		{
			QAChkPlan plan = new QAChkPlan();
			
			plan.setChkItemId(change.getChkItemId());
			plan.setChkItemName(change.getChkItemName());
			plan.setChkPlanId(qAChkPlanService.getKey("QAChkPlan"));
			plan.setChkPlanType(change.getChkPlanType());
			plan.setChkPlanVersion(curVersion+1);
			
			plan.setCreateTime(change.getCreateTime());
			plan.setNumber(change.getNumber());
			plan.setPlanChkArea(change.getPlanChkArea());
			plan.setPlanChkOperator(change.getPlanChkOperator());
			plan.setPlanChkTime(change.getPlanChkTime());
			plan.setQastudyChkIndex(chkIndex);
			plan.setScheduleChkItemId(change.getScheduleChkItemId());
			plan.setScheduleId(change.getScheduleId());
			plan.setScheduleName(change.getScheduleName());
			plan.setScheduleTime(change.getScheduleTime());
			plan.setSOPFlag(change.getSOPFlag());
			plan.setTaskName(change.getTaskName());
			plan.setTaskNameId(change.getTaskName());
			plan.setSOPFlag(change.getSOPFlag());
			
			qAChkPlanService.save(plan);
			
			qAChkPlanHisService.delete(change.getChkPlanHisId());
		}
		
		
		return curVersion+1;
	
	}
	public void deleteChangeRecordByStudyNo(String studyNoParam)
	{
		
		List<QAChkPlanHis> changePlanRecord = qAChkPlanHisService.getByStudyNoAndVersion(studyNoParam, -1);//变更的数据
		for(QAChkPlanHis change:changePlanRecord)
		{
			qAChkPlanHisService.delete(change.getChkPlanHisId());
		}
		
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
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	
	
}
