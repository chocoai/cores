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
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQACheckContentTable;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.model.qa.QAChkSop;
import com.lanen.model.qa.QAChkTblReg;
import com.lanen.model.qa.QAFileReg;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictChkItemQAFileRegService;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQACheckContentTableService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictQACheckTableService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAChkSopService;
import com.lanen.service.qa.QAChkTblRegService;
import com.lanen.service.qa.QAFileRegService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.qa.TblStudyPlanReadRecordService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkRecordAction extends BaseAction<QAChkRecord>{
	
	private static final long serialVersionUID = 4028571108726674700L;
	
	@Resource
	private QAChkRecordService qAChkRecordService;
	@Resource
	private QAChkPlanService qAChkPlanService;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private DictQACheckTableService dictQACheckTableService;
	@Resource
	private DictQACheckContentTableService dictQACheckContentTableService;
	@Resource
	private QAChkIndexService qAChkIndexService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private QAChkTblRegService qAChkTblRegService;
	@Resource
	private QAChkSopService qAChkSopService;
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private QAChkReportService qAChkReportService;
	@Resource
	private QAFileRegService qAFileRegService;
	@Resource
	private DictChkItemQAFileRegService dictChkItemQAFileRegService;
	@Resource
	private QAChkReportRecordService qAChkReportRecordService;
	@Resource
	private TblStudyPlanReadRecordService tblStudyPlanReadRecordService;
	
	private String studyNoParam;
	private Date planDate;
	private String chkItemId;
	private String chkTblId;
	private String chkPlanId;
	
	private String planIdItemId;
	private String qaIndexId;
	private String qaIndexIds;
	
	//查询条件
	private Date startChkDate;
	private Date endChkDate;

	private Integer chkIndexStatus;
	
	private String chkReportCode;
	
	private String fileRegIds;
	
	private String oneChkPlanId;
	
	private String sopRecordId;
	
	private String allOperate;
	
	//检查记录
	public String list()
	{
		ActionContext.getContext().put("studyNoParam", studyNoParam);
		
		String oneChildChkPlanId = (String)ActionContext.getContext().getSession().get("oneChildChkPlanId");
		//子页面开始检查过来的
		ActionContext.getContext().getSession().put("oneChildChkPlanId","");
		if(oneChildChkPlanId!=null&&!"".equals(oneChildChkPlanId))
		{
			//传过来的是plan的id
			QAChkPlan plan = qAChkPlanService.getById(oneChildChkPlanId);
			if(plan!=null)//任命qa或者审批计划或报告等是没有计划的
			{
				ActionContext.getContext().put("oneChkPlanIdForList",plan.getChkPlanId()+":"+plan.getChkItemId());
				ActionContext.getContext().put("onePlanChkDate",DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
			}
		}else {
			if(oneChkPlanId!=null&&!"".equals(oneChkPlanId))	//从主页面上过来的开始检查
			{
				QAChkPlan plan = qAChkPlanService.getById(oneChkPlanId);
				if(plan!=null){
					ActionContext.getContext().put("oneChkPlanIdForList",plan.getChkPlanId()+":"+plan.getChkItemId());
					ActionContext.getContext().put("onePlanChkDate",DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
				}
			}
			
		}
			
				
		
		
		QAStudyChkIndex studyChkIndex =  qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(studyChkIndex!=null)
		{
			Integer planState = studyChkIndex.getChkPlanState();
			if(planState!=null&&planState==2)
			{
				ActionContext.getContext().put("planStateForChkRecord",true );
			}else {
				ActionContext.getContext().put("planStateForChkRecord",false );
			}
			if(studyChkIndex.getScheduleChangedFlag()!=null&&studyChkIndex.getScheduleChangedFlag()==1)
			{
				//（动态标志，由SD提交日程所触发）0：无变更，1：变更，2：变更处理完毕
				//有日程变更
				ActionContext.getContext().put("scheduleChangedForRecord",true);
				
				
			}
			if((studyChkIndex.getReportState()!=null&&studyChkIndex.getReportState()==1)||(studyChkIndex.getChkPlanFinishFlag()!=null&&studyChkIndex.getChkPlanFinishFlag()==1))
			{
				ActionContext.getContext().put("studyFinishForRecord",  true);	
			}else {
				ActionContext.getContext().put("studyFinishForRecord",  false);	
			}
		}
		return "list";
	}
	//检查结果
	public String result()
	{
		ActionContext.getContext().put("studyNoParam", studyNoParam);
		if(startChkDate==null||endChkDate==null)
		{
			endChkDate = DateUtil.getTodayDate();
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(endChkDate);
			calendar1.add(Calendar.DATE, -6);
			startChkDate = calendar1.getTime();
			String startDateString = DateUtil.dateToString(startChkDate, "yyyy-MM-dd");
			String endDateString = DateUtil.dateToString(endChkDate, "yyyy-MM-dd");
			ActionContext.getContext().put("startChkDateForResult", startDateString);
			ActionContext.getContext().put("endChkDateForResult",  endDateString);
		}
		
		return "result";
	}
	//
	public void otherAllOpearte()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		//DictQACheckTable dictQACheckTable = dictQACheckTableService.getById(chkTblId);
		User user =(User) ActionContext.getContext().getSession().get("user");
		
		
		QAChkIndex qachkIndex = qAChkIndexService.getById(qaIndexId);
		if(qachkIndex==null)
		{
			TblStudyFileIndex studyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);//获取方案文件
			if(studyFileIndex!=null)
			{
				//判断是否阅读完成
				boolean isFinishRead = tblStudyPlanReadRecordService.isExistByStudyAndUser(studyNoParam,user);
				if(!isFinishRead){
					map.put("success", false);
					map.put("noFinishRead", true);
					writeJson(JsonPluginsUtil.beanToJson(map));
					return;
				}
			}
			qachkIndex = new QAChkIndex();
			String key2=qAChkIndexService.getKey("QAChkIndex");
			qachkIndex.setChkIndexId(key2);
			QAChkPlan plan = null;
			String chkItemId = planIdItemId.substring(planIdItemId.lastIndexOf(":")+1);
			DictQACheckItem item = dictQACheckItemService.getById(chkItemId);
			qachkIndex.setChkItemName(item.getChkItemName());//chkItemName是由taskName：itemName组成的
			
			if(planIdItemId.contains("s:"))
			{
				//方案或者报告是没有检查计划的
				String studyFileIndexId = planIdItemId.substring(planIdItemId.indexOf(":")+1,planIdItemId.lastIndexOf(":"));
				TblStudyFileIndex index = tblStudyFileIndexService.getById(studyFileIndexId);
				
				if(index.getFileType()==1)
				{
					//map.put("fileType", "方案");//1方案，2报告
					//1:研究；2：过程；3：设施；4方案；5：报告
					//chkType=4;
					qachkIndex.setChkType(4);
				}
				if(index.getFileType()==2)
				{
					//map.put("fileType", "报告");//1方案，2报告
				//	chkType=5;
					qachkIndex.setChkType(5);
				}
				
				
			}else {
				qachkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
				
				plan= qAChkPlanService.getById(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				plan.setChkOperator(getCurrentRealName());
				plan.setChkTime(DateUtil.getTodayDate());
				qAChkPlanService.update(plan);//更新检查记录中的实际操作者和检查时间
				
				qachkIndex.setChkPlanId(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				//oneChkIndex.setChkPlan(plan);	
				
			}
			
			qachkIndex.setChkState(1);//1检查中
			
			qachkIndex.setCreateTime(DateUtil.getTodayDate());
			qachkIndex.setOperator(getCurrentRealName());
			qachkIndex.setStudyNo(studyNoParam);
			
			qAChkIndexService.save(qachkIndex);
			
			if(planIdItemId.contains("s:"))
			{
				//方案的检查项直接加SOP
				saveItemChkSops(qachkIndex, chkItemId);
			}else{
				if(plan!=null)
				{
					//检查计划的根据 检查计划的设置来写入sop
					if(plan.getSOPFlag()==1||plan.getSOPFlag()==2){
						saveItemChkSops(qachkIndex, chkItemId);
					}
					if(plan.getSOPFlag()==2||plan.getSOPFlag()==3){
						//加方案
						QAChkSop sop = new QAChkSop();
						String keySop = qAChkSopService.getKey("QAChkSop");
						sop.setFileRecordId("0");//表示是依据方案
						sop.setQachkIndex(qachkIndex);
					//	sop.setSopCode(reg.getFileCode());
						sop.setSopName("实验方案");
					//	sop.setSopPublishDepartment(reg.getFilePublishDepartment());
					//	sop.setSopPublishTime(reg.getFilePublishTime());
						sop.setSopRecordId(keySop);
					//	sop.setSopVersion(reg.getFileVersion());
						qAChkSopService.save(sop);
					}
				}
			}
			
			qaIndexId = qachkIndex.getChkIndexId();
			if(plan!=null)
			{
				plan.setChkIndex(qachkIndex);
				qAChkPlanService.update(plan);//更新chkplan
			}
			
			
		}
		
		List<DictChkItemChkTblReg> allItemTables = dictChkItemChkTblRegService.getByChkItemId(planIdItemId.substring(planIdItemId.lastIndexOf(":")+1));
		for(DictChkItemChkTblReg ItemTable:allItemTables)
		{
			DictQACheckTable dictQACheckTable = ItemTable.getDictQacheckTable();
		
			QAChkTblReg tblReg=qAChkTblRegService.getByChkIndexAndTblCodeAndName(qaIndexId, dictQACheckTable.getChkTblCode(), dictQACheckTable.getChkTblName());
			if( tblReg==null)
			{
			    tblReg = new QAChkTblReg();
			    tblReg.setChkTblCode(dictQACheckTable.getChkTblCode());
			    tblReg.setChkTblName(dictQACheckTable.getChkTblName());
				String tblkey = qAChkTblRegService.getKey("QAChkTblReg");
				tblReg.setChkTblRegId(tblkey);
				tblReg.setQachkIndex(qachkIndex);
				Integer maxSn = qAChkTblRegService.getMaxSnByQachkIndex(qachkIndex.getChkIndexId());
				tblReg.setSn(maxSn+1);
				qAChkTblRegService.save(tblReg);
					
			}
			//Set<QAChkRecord> checkedRecords = tblReg.getQachkRecords();
			//Set<DictQACheckContentTable> contentRecords= dictQACheckTable.getDictQacheckContentTables();
			List<QAChkRecord> checkedRecords = qAChkRecordService.getByTblRegId(tblReg.getChkTblRegId());
			List<DictQACheckContentTable> contentRecords = dictQACheckContentTableService.getByChkTblId(dictQACheckTable.getChkTblId());
			for(DictQACheckContentTable content:contentRecords)
			{
				boolean isContain=false;
				for(QAChkRecord chkRecord:checkedRecords)
				{
					if(chkRecord.getChkTblContentId().equals(content.getChkTblContentId()))
					{
						isContain = true;
						break;
					}
				}
				if(!isContain)
				{
					QAChkRecord record = new QAChkRecord();
					String key = qAChkRecordService.getKey("QAChkRecord");
					record.setChkRecordId(key);
					record.setQachkIndex(qachkIndex);//包含planId和ItemName
					record.setAdvice("");
					record.setChkContent(content.getChkContent());
					record.setSn(content.getSn());
					record.setChkResult(allOperate);
					if(allOperate.equals("√"))
						record.setChkResultFlag(1);
					//if(model.getChkResult().equals("×"))
					//	record.setChkResultFlag(-1);
					if(allOperate.equals("NA"))
						record.setChkResultFlag(0);
						
					record.setChkTblContentId(content.getChkTblContentId());
					record.setChkTime(DateUtil.stringToDate(DateUtil.getNow("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
					record.setInspector(user.getRealName());
						
					record.setQachkTblReg(tblReg);
						
					qAChkRecordService.save(record);
							
				}
					
			}
			
		}
		
		map.put("success", true);
		map.put("chkIndexId",qaIndexId);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void saveItemChkSops(QAChkIndex oneChkIndex,String chkItemId)
	{
		List<DictChkItemQAFileReg> regs=dictChkItemQAFileRegService.getByChkItemId(chkItemId);
		String[] fileRegIds = new String[regs.size()];
		for(int i=0;i<regs.size();i++)
		{
			DictChkItemQAFileReg reg =regs.get(i);
			fileRegIds[i]= reg.getFileRegId();
			
		}
		List<QAFileReg> files = qAFileRegService.getByIds(fileRegIds);
		if(files!=null)
		{
			for(QAFileReg reg:files)
			{
				QAChkSop sop = new QAChkSop();
				String keySop = qAChkSopService.getKey("QAChkSop");
				sop.setFileRecordId(reg.getFileRegId());
				sop.setQachkIndex(oneChkIndex);
				sop.setSopCode(reg.getFileCode());
				sop.setSopName(reg.getFileName());
				sop.setSopPublishDepartment(reg.getFilePublishDepartment());
				sop.setSopPublishTime(reg.getFilePublishTime());
				sop.setSopRecordId(keySop);
				sop.setSopVersion(reg.getFileVersion());
				sop.setRemark(reg.getRemark());
				qAChkSopService.save(sop);
			}
		}
	}
	
	//获取该专题下的所有有任务的日期
	public void getPlanDateList()
	{
		List<Map<String, Object>> dateStrings = new ArrayList<Map<String, Object>>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		String qa = null;
		QAStudyChkIndex studyIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(studyIndex!=null&&studyIndex.getInspector()!=null)
		{
			qa = studyIndex.getInspector();
		}else {
			TblStudyItem item = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
			if(item!=null&&item.getQa()!=null)
			{
				qa=item.getQa();
			}			
		}
		if(studyIndex!=null&&studyIndex.getChkPlanState()!=null&&studyIndex.getChkPlanState()==2)//检查计划审批通过
		{
			List<Date> plans = null;
			//不包含已经结束的检查计划
			if(user.getRealName().equals(qa))
			{
				plans = qAChkPlanService.getPlanDateByStudyNoAndUser(studyNoParam,null);				

			}else
			{
				plans = qAChkPlanService.getPlanDateByStudyNoAndUser(studyNoParam,user.getRealName());				
			}
			for(Date d:plans)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("planChkTime",DateUtil.dateToString(d, "yyyy-MM-dd"));
				dateStrings.add(map);
			}
		}
	
		String json = JsonPluginsUtil.beanListToJson(dateStrings);
		writeJson(json);
	}
	//获取某一天下的所有任务
	public void getItemListByDate()
	{
		List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();

		User user = (User)ActionContext.getContext().getSession().get("user");
		String qa = null;
		QAStudyChkIndex studyIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		if(studyIndex!=null&&studyIndex.getInspector()!=null)
		{
			qa = studyIndex.getInspector();
		}else {
			TblStudyItem item = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
			if(item!=null&&item.getQa()!=null)
			{
				qa=item.getQa();
			}			
		}
		
		//如果该专题包含方案或者报告，加到combobox下
		//最多两个
		if(user.getRealName().equals(qa))
		{
			List<TblStudyFileIndex> indexs = tblStudyFileIndexService.getByStudyNo(studyNoParam);
			QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
			
			for (TblStudyFileIndex index:indexs) //方案或者报告
			{
				TblStudyPlan plan = tblStudyPlanService.getByStudyNo(studyNoParam);
				String typeCode = "";
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				if(plan!=null){
					typeCode = plan.getStudyTypeCode();
				}else if(studyItem!=null){
					typeCode = studyItem.getStudyTypeCode();
				}
					
				DictStudyType type = dictStudyTypeService.getByStudyTypeCode(typeCode);
				if(type!=null)
				{
					String groupId = type.getStudyGroupID();
					if(groupId==null)
					{
						//该实验还没有分组，
						
					}else {
						//根据组获取检查项
						List<DictChkItemStudyGroupReg> items = dictChkItemStudyGroupRegService.getByStudyGroupId(groupId); 
						for(DictChkItemStudyGroupReg chkItemStudyGroup:items)
						{
							DictQACheckItem dictQacheckItem = chkItemStudyGroup.getDictQacheckItem();
							//chkItemType 1：方案；2：报告；3：变更；4：基于研究的检查项
							
							Map<String, Object> map = new HashMap<String, Object>();
							if(index.getConfirmer()!=null)//QA已经确认过的方案或者报告
							{
								if(index.getFileType()==1&&(studyChkIndex.getStudyPlanState()==null||studyChkIndex.getStudyPlanState()==0))//1：方案；2：报告；
								{
									if(index.getChangeFlag()!=null&&index.getChangeFlag()==3)//index changeFlag =3 是变更已处理
									{
										if(dictQacheckItem.getChkItemType()==3)
										{
											map.put("chkItemId", "s:"+index.getStudyFileIndexId()+":"+dictQacheckItem.getChkItemId());
											//0：未申请；1：已申请；2：已批准；-2：未批准。每次提交时，记录提交记录。
											map.put("chkItemName", "专题变更:"+dictQacheckItem.getChkItemName());	
										}
									}else {
										if(dictQacheckItem.getChkItemType()==1)
										{
											map.put("chkItemId", "s:"+index.getStudyFileIndexId()+":"+dictQacheckItem.getChkItemId());
											map.put("chkItemName", "专题方案:"+dictQacheckItem.getChkItemName());
										}
										
									}
									
								}else if(index.getFileType()==2&&(studyChkIndex.getReportState()==null||studyChkIndex.getReportState()==0)){//报告
									if(dictQacheckItem.getChkItemType()==2)
									{
										map.put("chkItemId", "s:"+index.getStudyFileIndexId()+":"+dictQacheckItem.getChkItemId());
										map.put("chkItemName","专题报告:"+dictQacheckItem.getChkItemName() );
									}
								}
								
							}
							
							if(map.get("chkItemName")!=null)
							{						
								maps.add(map);
							}
						}
						
					}
				}
			}
			
		}
		
		List<QAChkPlan> plans = null;
		if(user.getRealName().equals(qa))
		{
			plans = qAChkPlanService.getItemsByStudyNoAndPlanDateAndUser(studyNoParam,planDate,null);
		}else {
			plans = qAChkPlanService.getItemsByStudyNoAndPlanDateAndUser(studyNoParam,planDate,user.getRealName());
		}
		for(QAChkPlan plan:plans)
		{
			if(plan.getTempChkOperatorFlag()!=null&&plan.getTempChkOperatorFlag()==1)//申请了临时检查员
			{
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			//chkItemId 是chkPlanId加上chkItemId
			map.put("chkItemId", plan.getChkPlanId()+":"+plan.getChkItemId());
			map.put("chkItemName",(plan.getTaskName()==null?"":plan.getTaskName()+":")+plan.getChkItemName() );
			maps.add(map);
		}
		
		
		String json=JsonPluginsUtil.beanListToJson(maps);
		writeJson(json);
	}
	//获取检查项对应的检查表
	public void getTableByItem()
	{
		
		List<DictQACheckTable> tables = new ArrayList<DictQACheckTable>();
		//List<DictChkItemChkTblReg> dictChkItemChkTblRegs = null;
		//if(chkPlanId.contains("s:"))
		//{
		
		List<DictChkItemChkTblReg> dictChkItemChkTblRegs = dictChkItemChkTblRegService.getByChkItemId(chkItemId.substring(chkItemId.lastIndexOf(":")+1));
//		}else {
//			dictChkItemChkTblRegs = dictChkItemChkTblRegService.getByChkItemId(chkItemId.substring(chkItemId.indexOf(":")+1));
//		}
		
		for(DictChkItemChkTblReg reg:dictChkItemChkTblRegs)
		{
			DictQACheckTable table = reg.getDictQacheckTable();
			tables.add(table);
		}
		
		String[] _nory_format = {"chkTblId", "chkTblCode","chkTblName","beginDate","endDate"};
		String json = JsonPluginsUtil.beanListToJson(tables, "yyyy-MM-dd", _nory_format, true);
		
		writeJson(json);
	}
	//检查该检查项是否存在检查索引
	public void isExistChkIndex()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkIndex qAChkIndex = null;
		
		User user =(User) ActionContext.getContext().getSession().get("user");
		if(chkPlanId.contains("s:"))
		{
			//方案或者报告
			map.put("studyFileIndex", true);
			map.put("userName",user.getRealName());
			
			String studyFileIndexId = chkPlanId.substring(chkPlanId.indexOf(":")+1,chkPlanId.lastIndexOf(":"));
			TblStudyFileIndex index = tblStudyFileIndexService.getById(studyFileIndexId);
			String chkItemId = chkPlanId.substring(chkPlanId.lastIndexOf(":")+1);
			DictQACheckItem item = dictQACheckItemService.getById(chkItemId);
			if(item!=null)
			{
				Integer chkType = 1;
				if(index.getFileType()==1)
				{
					map.put("fileType", "方案");//1方案，2报告
					//1:研究；2：过程；3：设施；4方案；5：报告
					chkType=4;
					//没有对变更进行处理
					if(index.getChangeFlag()!=null&&index.getChangeFlag()==3)//index changeFlag =3 是变更已处理
					{
						
					}
				}
				if(index.getFileType()==2)
				{
					map.put("fileType", "报告");//1方案，2报告
					chkType=5;
				}
				
				qAChkIndex = qAChkIndexService.isExistByStudyNoChkTypeAndChkItemName(index.getStudyNo(),chkType,item.getChkItemName());
				
				
				map.put("success", true);
			}else{
				map.put("success", false);
				map.put("msg", "与计划相关的检查项已经被删除！");
			}
				
			
			
		}else {
			//检查项
			String chkPlanIdString = chkPlanId.substring(0,chkPlanId.indexOf(":"));
			String chkItemIdString = chkPlanId.substring(chkPlanId.indexOf(":")+1);
			DictQACheckItem item = dictQACheckItemService.getById(chkItemIdString);
			if(item!=null)
			{
				qAChkIndex = qAChkIndexService.isExistByChkPlanIdAndChkItemName(chkPlanIdString,item.getChkItemName());
				map.put("fileType", "研究");
				
				map.put("success", true);
			}else{
				map.put("success", false);
				map.put("msg", "与计划相关的检查项已经被删除！");
			}
			
		}
		if(qAChkIndex==null)
		{
			map.put("isExist", false);
			map.put("userName",user.getRealName());
			
		}else {
			map.put("isExist", true);
			map.put("chkIndexId", qAChkIndex.getChkIndexId());
			map.put("status", qAChkIndex.getChkState());
			
			ActionContext.getContext().getSession().put("chkIndexId", qAChkIndex.getChkIndexId());
			
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	//根据检查表获取检查表的内容
	public void getTableContent()
	{
		List<DictQACheckContentTable> dictQACheckContents = dictQACheckContentTableService.getByChkTblId(chkTblId);
		//private DictQACheckTable dictQacheckTable;
	
	    String[] _nory_format = {"chkTblContentId", "sn","chkContent","remark","beginDate","endDate"};
		String json = JsonPluginsUtil.beanListToJson(dictQACheckContents, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
			
		writeJson(json);
	
	}
	
	public void getDictSopForItem()
	{
		//建立检查索引要根据字典新建检查SOP	
		String chkItemId = planIdItemId.substring(planIdItemId.lastIndexOf(":")+1);
		
		List<DictChkItemQAFileReg> regs=dictChkItemQAFileRegService.getByChkItemId(chkItemId);
		String[] fileRegIds = new String[regs.size()];
		
		for(int i=0;i<regs.size();i++)
		{
			DictChkItemQAFileReg reg =regs.get(i);
			fileRegIds[i]= reg.getFileRegId();
		}
		List<QAFileReg> files = qAFileRegService.getByIds(fileRegIds);
		if(files==null)
			files = new ArrayList<QAFileReg>();
		
		String[] _nory_format = {"fileRegId","fileType","fileTypeName","fileCode","fileName","fileVersion","filePublishTime","filePublishDepartment","remark","isVersionUpdate"};
		String json = JsonPluginsUtil.beanListToJson(files, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
			
		writeJson(json);
	}
	
	public void saveQAChkSOP()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exist", false);
		
		QAChkIndex qachkIndex = qAChkIndexService.getById(qaIndexId);
		
		if(qachkIndex==null)
		{
			qachkIndex = new QAChkIndex();
			String key2=qAChkIndexService.getKey("QAChkIndex");
			qachkIndex.setChkIndexId(key2);
			QAChkPlan plan = null;
			String chkItemId = planIdItemId.substring(planIdItemId.lastIndexOf(":")+1);
			DictQACheckItem item = dictQACheckItemService.getById(chkItemId);
			if(item==null){
				map.put("success", false);
				map.put("msg", "与该检查计划相关的检查项已经被删除！");
			}else{
				map.put("success", true);
			}
			qachkIndex.setChkItemName(item.getChkItemName());//chkItemName是由taskName：itemName组成的
				
			if(planIdItemId.contains("s:"))
			{
					//方案或者报告是没有检查计划的
					/*if(chkTypeName.equals("研究"))
					{
						qachkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
					}
					if(chkTypeName.equals("方案"))
					{
						qachkIndex.setChkType(4);
					}
					if(chkTypeName.equals("报告"))
					{
						qachkIndex.setChkType(5);
					}*/
					//方案或者报告
					
				String studyFileIndexId = planIdItemId.substring(planIdItemId.indexOf(":")+1,planIdItemId.lastIndexOf(":"));
				TblStudyFileIndex index = tblStudyFileIndexService.getById(studyFileIndexId);
					
					//Integer chkType = 1;
				if(index.getFileType()==1)
				{
						//map.put("fileType", "方案");//1方案，2报告
						//1:研究；2：过程；3：设施；4方案；5：报告
						//chkType=4;
					qachkIndex.setChkType(4);
				}
				if(index.getFileType()==2)
				{
						//map.put("fileType", "报告");//1方案，2报告
					//	chkType=5;
					qachkIndex.setChkType(5);
				}
					
					//qAChkIndex = qAChkIndexService.isExistByStudyNoChkTypeAndChkItemName(index.getStudyNo(),chkType,item.getChkItemName());
					
			}else {
				qachkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
					
				plan= qAChkPlanService.getById(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				plan.setChkOperator(getCurrentRealName());
				plan.setChkTime(DateUtil.getTodayDate());
				qAChkPlanService.update(plan);//更新检查记录中的实际操作者和检查时间
					
				qachkIndex.setChkPlanId(planIdItemId.substring(0,planIdItemId.indexOf(":")));
					//oneChkIndex.setChkPlan(plan);	
			}
				
			qachkIndex.setChkState(1);//1检查中
				
			qachkIndex.setCreateTime(DateUtil.getTodayDate());
			qachkIndex.setOperator(getCurrentRealName());
			qachkIndex.setStudyNo(studyNoParam);
				
			qAChkIndexService.save(qachkIndex);
				
			qaIndexId = qachkIndex.getChkIndexId();
			if(plan!=null)
			{
				plan.setChkIndex(qachkIndex);
				qAChkPlanService.update(plan);//更新chkplan
			}
		}
		
		
		List<QAFileReg> files = qAFileRegService.getByIds(fileRegIds.split(","));
		if(files!=null)
		{
			for(QAFileReg reg:files)
			{
				boolean isExist = qAChkSopService.isExistByIndexIdAndFileId(qaIndexId,reg.getFileRegId());
				if(!isExist)
				{
					QAChkSop sop = new QAChkSop();
					String keySop = qAChkSopService.getKey("QAChkSop");
					sop.setFileRecordId(reg.getFileRegId());
					sop.setQachkIndex(qachkIndex);
					sop.setSopCode(reg.getFileCode());
					sop.setSopName(reg.getFileName());
					sop.setSopPublishDepartment(reg.getFilePublishDepartment());
					sop.setSopPublishTime(reg.getFilePublishTime());
					sop.setSopRecordId(keySop);
					sop.setSopVersion(reg.getFileVersion());
					qAChkSopService.save(sop);
				}else {
					map.put("exist", true);
				}
				
			}
		}
		map.put("chkIndexId",qaIndexId);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	//保存检查记录，如果该检查记录对应的检查表不存在则新建一个检查表登记
	public void save()
	{
		//chkTblId是字典的检查登记表，record中是检查表登记表
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkIndex qachkIndex = qAChkIndexService.getById(qaIndexId);
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		if(qachkIndex==null)
		{
			TblStudyFileIndex studyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);//获取方案文件
			if(studyFileIndex!=null)
			{
				//判断是否阅读完成
				boolean isFinishRead = tblStudyPlanReadRecordService.isExistByStudyAndUser(studyNoParam,user);
				if(!isFinishRead){
					map.put("success", false);
					map.put("noFinishRead", true);
					writeJson(JsonPluginsUtil.beanToJson(map));
					return;
				}
			}
			qachkIndex = new QAChkIndex();
			String key2=qAChkIndexService.getKey("QAChkIndex");
			qachkIndex.setChkIndexId(key2);
			QAChkPlan plan = null;
			String chkItemId = planIdItemId.substring(planIdItemId.lastIndexOf(":")+1);
			DictQACheckItem item = dictQACheckItemService.getById(chkItemId);
			
			qachkIndex.setChkItemName(item.getChkItemName());//chkItemName是由taskName：itemName组成的
			
			if(planIdItemId.contains("s:"))
			{
				//方案或者报告是没有检查计划的
				/*if(chkTypeName.equals("研究"))
				{
					qachkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
				}
				if(chkTypeName.equals("方案"))
				{
					qachkIndex.setChkType(4);
				}
				if(chkTypeName.equals("报告"))
				{
					qachkIndex.setChkType(5);
				}*/
				//方案或者报告
				
				String studyFileIndexId = planIdItemId.substring(planIdItemId.indexOf(":")+1,planIdItemId.lastIndexOf(":"));
				TblStudyFileIndex index = tblStudyFileIndexService.getById(studyFileIndexId);
				
				//Integer chkType = 1;
				if(index.getFileType()==1)
				{
					//map.put("fileType", "方案");//1方案，2报告
					//1:研究；2：过程；3：设施；4方案；5：报告
					//chkType=4;
					qachkIndex.setChkType(4);
				}
				if(index.getFileType()==2)
				{
					//map.put("fileType", "报告");//1方案，2报告
				//	chkType=5;
					qachkIndex.setChkType(5);
				}
				
				//qAChkIndex = qAChkIndexService.isExistByStudyNoChkTypeAndChkItemName(index.getStudyNo(),chkType,item.getChkItemName());
				
				
				
			}else {
				qachkIndex.setChkType(1);//1:研究；2：过程；3：设施；4方案；5：报告
				
				plan= qAChkPlanService.getById(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				plan.setChkOperator(getCurrentRealName());
				plan.setChkTime(DateUtil.getTodayDate());
				qAChkPlanService.update(plan);//更新检查记录中的实际操作者和检查时间
				
				qachkIndex.setChkPlanId(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				//oneChkIndex.setChkPlan(plan);	
				
			}
			
			qachkIndex.setChkState(1);//1检查中
			
			qachkIndex.setCreateTime(DateUtil.getTodayDate());
			qachkIndex.setOperator(getCurrentRealName());
			qachkIndex.setStudyNo(studyNoParam);
			
			qAChkIndexService.save(qachkIndex);
			
			if(planIdItemId.contains("s:"))
			{
				//方案的检查项直接加SOP
				saveItemChkSops(qachkIndex, chkItemId);
			}else{
				if(plan!=null)
				{
					//检查计划的根据 检查计划的设置来写入sop
					if(plan.getSOPFlag()==1||plan.getSOPFlag()==2){
						saveItemChkSops(qachkIndex, chkItemId);
					}
					if(plan.getSOPFlag()==2||plan.getSOPFlag()==3){
						//加方案
						QAChkSop sop = new QAChkSop();
						String keySop = qAChkSopService.getKey("QAChkSop");
					//	sop.setFileRecordId(reg.getFileRegId());
						sop.setQachkIndex(qachkIndex);
					//	sop.setSopCode(reg.getFileCode());
						sop.setSopName("方案");
					//	sop.setSopPublishDepartment(reg.getFilePublishDepartment());
					//	sop.setSopPublishTime(reg.getFilePublishTime());
						sop.setSopRecordId(keySop);
					//	sop.setSopVersion(reg.getFileVersion());
						qAChkSopService.save(sop);
					}
				}
			}
				
			
			qaIndexId = qachkIndex.getChkIndexId();
			if(plan!=null)
			{
				plan.setChkIndex(qachkIndex);
				qAChkPlanService.update(plan);//更新chkplan
			}
		}
		
		boolean isExist = qAChkRecordService.isExistRecord(qaIndexId,model.getChkTblContentId());
		if(!isExist)
		{
			//planIdItemId='+item+'&chkTblId='+tbl+"&chkTblContentId="+tblContent.chkTblContentId+"&qaIndexId="+qaIndexId,
			QAChkRecord record = new QAChkRecord();
			String key = qAChkRecordService.getKey("QAChkRecord");
			record.setChkRecordId(key);
			//QAChkIndex qAChkIndex = qAChkIndexService.getById(qaIndexId);
			record.setQachkIndex(qachkIndex);//包含planId和ItemName
			record.setAdvice(model.getAdvice());
			DictQACheckContentTable dictQACheckContent = dictQACheckContentTableService.getById(model.getChkTblContentId());
			record.setChkContent(dictQACheckContent.getChkContent());
			record.setSn(dictQACheckContent.getSn());
			record.setChkResult(model.getChkResult());
			if(model.getChkResult().equals("√"))
				record.setChkResultFlag(1);
			if(model.getChkResult().equals("×"))
				record.setChkResultFlag(-1);
			if(model.getChkResult().equals("NA"))
				record.setChkResultFlag(0);
			record.setChkResultDesc(model.getChkResultDesc());
			
			record.setChkTblContentId(model.getChkTblContentId());
			record.setChkTime(DateUtil.stringToDate(DateUtil.getNow("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
			//User user =(User) ActionContext.getContext().getSession().get("user");
			record.setInspector(user.getRealName());
				
				//Set<QAChkTblReg> tbls = qachkIndex.getQachkTblRegs();
				
				record.setQachkIndex(qachkIndex);
				DictQACheckTable dictQACheckTable = dictQACheckTableService.getById(chkTblId);
				QAChkTblReg qachkTblReg = qAChkTblRegService.getByChkIndexAndTblCodeAndName(qaIndexId,dictQACheckTable.getChkTblCode(),dictQACheckTable.getChkTblName());
				//一个检查项可以使用多个检查表
				
				
				if(qachkTblReg!=null)
				{
					record.setQachkTblReg(qachkTblReg);
				}else {
					QAChkTblReg addQAChkTblReg = new QAChkTblReg();
					addQAChkTblReg.setChkTblCode(dictQACheckTable.getChkTblCode());
					addQAChkTblReg.setChkTblName(dictQACheckTable.getChkTblName());
					String tblkey = qAChkTblRegService.getKey("QAChkTblReg");
					addQAChkTblReg.setChkTblRegId(tblkey);
					addQAChkTblReg.setQachkIndex(qachkIndex);
					Integer maxSn = qAChkTblRegService.getMaxSnByQachkIndex(qachkIndex.getChkIndexId());
					addQAChkTblReg.setSn(maxSn+1);
					qAChkTblRegService.save(addQAChkTblReg);
					
					record.setQachkTblReg(addQAChkTblReg);
				}
				qAChkRecordService.save(record);
				map.put("success", true);
				
				map.put("chkRecordId", record.getChkRecordId());
				map.put("sn", record.getSn());
				map.put("chkTblName", record.getQachkTblReg().getChkTblName());
				map.put("chkContent", record.getChkContent());
				map.put("chkResultFlag", record.getChkResultFlag());
				map.put("chkResultDesc", record.getChkResultDesc());
				map.put("advice", record.getAdvice());
				map.put("chkTime", DateUtil.dateToString(record.getChkTime(),"yyyy-MM-dd"));
				
			/*}else {
				map.put("success", false);
				map.put("msg", "请先确认生成该检查项的检查索引");
			}*/
			//}
		}else {
			map.put("success", false);
			map.put("msg", "该检查表的本条内容已经检测过！");
		}
		map.put("qaIndexId", qaIndexId);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	//删除检查记录
	public void del()
	{
		Map<String, Object> map=new HashMap<String, Object>();
		QAChkRecord record = qAChkRecordService.getById(model.getChkRecordId());
		String tblreg = record.getQachkTblReg().getChkTblRegId();
		
		String chkIndexId = record.getQachkIndex().getChkIndexId();
		
		qAChkRecordService.delete(model.getChkRecordId());
		//判断如果该检查表下的所有内容都被删除则删除对应的表
		List<QAChkRecord> records = qAChkRecordService.getByTblRegId(tblreg);
		if(records==null||records.size()==0)//如果本检查表登记下的检查记录为空，则删除相应的检查表
		{
			qAChkTblRegService.delete(tblreg);
		}
		// 如果检查项没有内容了，则删除检查索引
		QAChkIndex chkIndex = qAChkIndexService.getById(chkIndexId);
		List<QAChkRecord> records2 = qAChkRecordService.getByChkIndexId(chkIndexId);
	
		if(records2==null||records2.size()==0)//不存在检查记录
				//不存在sop文件
		{
			if(chkIndex.getChkPlanId()!=null&&!"".equals(chkIndex.getChkPlanId()))
			{
				QAChkPlan plan = qAChkPlanService.getById(chkIndex.getChkPlanId());
				plan.setChkIndex(null);
				qAChkPlanService.update(plan);
			}
			List<QAChkSop> qachkSops = qAChkSopService.getByChkIndexId(chkIndexId);
			for(QAChkSop sop:qachkSops)
			{
				qAChkSopService.delete(sop.getSopRecordId());
			}
			qAChkIndexService.delete(chkIndexId);
			map.put("qaIndexId",null);
		}else {			
			map.put("qaIndexId",chkIndexId);
		}
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delOneSop()
	{
		Map<String, Object> map=new HashMap<String, Object>();
		
		QAChkSop sop = qAChkSopService.getById(sopRecordId);
		qAChkSopService.delete(sopRecordId);
		
		// 如果检查项没有内容了，则删除检查索引
		QAChkIndex chkIndex = sop.getQachkIndex();
		List<QAChkRecord> records2 = qAChkRecordService.getByChkIndexId(chkIndex.getChkIndexId());
		List<QAChkSop> qachkSops = qAChkSopService.getByChkIndexId(chkIndex.getChkIndexId());
		
		if((records2==null||records2.size()==0)&&//不存在检查记录
				(qachkSops==null||qachkSops.size()==0))//不存在sop文件
		{
			if(chkIndex.getChkPlanId()!=null&&!"".equals(chkIndex.getChkPlanId()))
			{
				QAChkPlan plan = qAChkPlanService.getById(chkIndex.getChkPlanId());
				plan.setChkIndex(null);
				qAChkPlanService.update(plan);
			}
			
			qAChkIndexService.delete(chkIndex.getChkIndexId());
			map.put("qaIndexId",null);
		}else {			
			map.put("qaIndexId",chkIndex.getChkIndexId());
		}
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	//获取一个检查项所以的检查记录
	public void getRecordsByTable()
	{
		//DictQACheckTable dictQACheckTable = dictQACheckTableService.getById(chkTblId);
		//一个检查项可以使用多个检查表
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<QAChkRecord> records = qAChkRecordService.getListByIndexIdAndTblId(qaIndexId);
		for(QAChkRecord record:records)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chkRecordId", record.getChkRecordId());
			map.put("sn", record.getSn());
			map.put("chkTblContentId", record.getChkTblContentId());
			map.put("chkContent", record.getChkContent());
			map.put("advice", record.getAdvice());
			map.put("chkTime", DateUtil.dateToString(record.getChkTime(), "yyyy-MM-dd"));
			map.put("inspector", record.getInspector());
			map.put("chkResultDesc", record.getChkResultDesc());
			map.put("chkResultFlag", record.getChkResultFlag());
			map.put("chkTblName", record.getQachkTblReg().getChkTblName());
			
			mapList.add(map);
		}
		
		
	//	String[] _nory_format = {"chkRecordId", "sn","chkTblContentId","chkContent","advice","chkTime","inspector","chkResultFlag"};
		String json = JsonPluginsUtil.beanListToJson(mapList, "yyyy-MM-dd HH:mm:ss");
		writeJson(json);
	}
	//获取检查索引对应的法规
	public void getSOPByChkIndex()
	{
		String json="";
		if(qaIndexId!=null&&!"".equals(qaIndexId))
		{
			List<QAChkSop> sops = qAChkSopService.getByChkIndexId(qaIndexId);
			String[] _nory_format = {"sopRecordId", "fileRecordId","sopCode","sopName","sopVersion","sopPublishTime","sopPublishDepartment","remark"};
			json = JsonPluginsUtil.beanListToJson(sops, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
			
		}else {
			List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
			
			if(planIdItemId.contains("s:"))
			{
				//方案的检查项直接加SOP
				addSopListToList(mapList,planIdItemId);
			}else{
				QAChkPlan plan = qAChkPlanService.getById(planIdItemId.substring(0,planIdItemId.indexOf(":")));
				if(plan!=null)
				{
					//检查计划的根据 检查计划的设置来写入sop
					if(plan.getSOPFlag()==1||plan.getSOPFlag()==2){
						addSopListToList(mapList,planIdItemId);
					}
					if(plan.getSOPFlag()==2||plan.getSOPFlag()==3){
						//加方案
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("sopName","方案" );
						
						mapList.add(map);
						
					}
				}
			}
			

			json = JsonPluginsUtil.beanListToJson(mapList);
			
		}
		
		writeJson(json);
		
	}
	public void addSopListToList(List<Map<String, Object>> mapList,String planIdItemId){
		String chkItemId = planIdItemId.substring(planIdItemId.lastIndexOf(":")+1);
		List<DictChkItemQAFileReg> regs = dictChkItemQAFileRegService.getByChkItemId(chkItemId);
		String[] fileRegIds = new String[regs.size()];
		for(int i=0;i<regs.size();i++)
		{
			DictChkItemQAFileReg reg =regs.get(i);
			fileRegIds[i]= reg.getFileRegId();
			
		}
		List<QAFileReg> files = qAFileRegService.getByIds(fileRegIds);
		
		if(files!=null)
		{
			for(QAFileReg reg:files)
			{
				Map<String,Object> map = new HashMap<String, Object>();
				
				//"sopRecordId", "fileRecordId","sopCode","sopName","sopVersion","sopPublishTime","sopPublishDepartment","remark"
				
				//map.put("sopRecordId",reg.gets );
				map.put("fileRecordId",reg.getFileRegId() );
				map.put("sopCode", reg.getFileCode());
				map.put("sopName", reg.getFileName());
				map.put("sopVersion",reg.getFileVersion() );
				map.put("sopPublishTime", reg.getFilePublishTime());
				map.put("sopPublishDepartment", reg.getFilePublishDepartment());
				map.put("remark", reg.getRemark());
				
				
				mapList.add(map);
				
			}
		}
		
	}
	
	
	//检查结果中获取检查项
	public void getItemsByDateInResultPage()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		//end加一天，查询的结果包括end这一天的
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(endChkDate);
		calendar1.add(Calendar.DATE, 1);
		endChkDate = calendar1.getTime();
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		//如果该专题包含方案或者报告，加到combobox下
		//最多两个
		
		//1:研究；2：过程；3：设施；4方案；5：报告
		List<QAChkIndex> indexs = qAChkIndexService.getByStudyNoAndUser(studyNoParam,startChkDate,endChkDate,user.getRealName());
		if(indexs!=null&&indexs.size()>0)
		{
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("id", "");
			map0.put("text","全部" );
			maps.add(map0);
			
			for(QAChkIndex index:indexs)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", index.getChkIndexId());
				String dataStr = DateUtil.dateToString(index.getChkFinishTime(),"yyyy-MM-dd");
				if(dataStr!=null&&!"".equals(dataStr))
				{
					map.put("text",dataStr+":"+index.getChkItemName() );					
				}else{
					map.put("text",index.getChkItemName() );	
				}
				
				maps.add(map);	
				
			}
		}	
		String json=JsonPluginsUtil.beanListToJson(maps);
		writeJson(json);
		
		
	}
	//检查结果列表
	public void getRecordResultList()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		//end加一天，查询的结果包括end这一天的
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(endChkDate);
		calendar1.add(Calendar.DATE, 1);
		endChkDate = calendar1.getTime();
		
		
		List<QAChkRecord> records = qAChkRecordService.getByStudyNoTimeItemStatusAndUser(studyNoParam,startChkDate,endChkDate,qaIndexId,chkIndexStatus,user.getRealName());
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(QAChkRecord record:records)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chkRecordId", record.getChkRecordId());
			map.put("chkTblName",record.getQachkTblReg().getChkTblName());
			map.put("chkTblRegId", record.getQachkTblReg().getChkTblRegId());
			map.put("chkIndexId", record.getQachkIndex().getChkIndexId());
			map.put("chkItemName", record.getQachkIndex().getChkItemName());
			map.put("operator", record.getQachkIndex().getOperator());
			
			QAChkIndex index= record.getQachkIndex();
			QAChkPlan plan= qAChkPlanService.getByChkIndex(index.getChkIndexId());
			if(plan!=null)
			{
			map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
			}
			//map.put("", plan.getChkFinishedFlag());
			//map.put("chkTime", DateUtil.dateToString(record.getChkTime(),"yyyy-MM-dd HH:mm:ss"));//实际检查时间是检查记录的时间
			map.put("chkTime", DateUtil.dateToString(record.getChkTime(),"yyyy-MM-dd"));//实际检查时间是检查记录的时间
			map.put("chkContent", record.getChkContent());
			map.put("chkResult", record.getChkResult());
			map.put("chkResultDesc", record.getChkResultDesc());
			map.put("chkResultFlag", record.getChkResultFlag());
			map.put("advice", record.getAdvice());
			Integer state = record.getQachkIndex().getChkState();//0:：草稿；1：检查中（启动）2：完成
			if(state==2)
			{
				map.put("isSign", true);				
			}else {
				map.put("isSign", false);	
			}
			map.put("chkItemName", record.getQachkIndex().getChkItemName());
			map.put("chkTblName", record.getQachkTblReg().getChkTblName());
			mapList.add(map);
			
		}
		
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	//用于编辑检查报告,查找特定日期中的特定检查索引下的还没有写如报告的检查记录
	public void getListByDateAndProgram()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		//startChkDate;
		// endChkDate;
		//qaIndexId
		endChkDate.setTime(endChkDate.getTime()+1000*60*60*24-1);//增加一天，查询的结果包括当天的
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		boolean isQAMBackReport = false;
		if(chkReportCode==null||"".equals(chkReportCode))//新增
		{
			//新增就只显示签过字以及没有入报告的
		}else {
			//增加报告内的
			QAChkReport report = qAChkReportService.getById(chkReportCode);
			if(report.getRptState()==-2)//QAM退回
				isQAMBackReport = true;
			
			//chkRecord.getQachkIndex().getChkIndexId()要在选择的项中
			List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
			
			if(qaIndexIds!=null&&!"".equals(qaIndexIds))
			//if(qaIndexIds!=null)
			{
				//全部的
				String[] indexs =  qaIndexIds.split(",");
				records = qAChkReportService.getByStudyAndChkTimeAndNoReport(chkReportCode,startChkDate,endChkDate,indexs);
			}else {
				//部分检查项的
				records = qAChkReportService.getByStudyAndChkTimeAndNoReport(chkReportCode,startChkDate,endChkDate,null);
			}
			
			//String[] indexs =  qaIndexIds.split(",");
		
			for(Map<String, Object> record:records)
			{
				
					Map<String, Object> map = new HashMap<String, Object>();
					
					QAChkRecord chkRecord =  qAChkRecordService.getById((String)record.get("chkRecordID"));
					
					if(chkRecord!=null)
					{
						List<Map<String, Object>> childList = null;
						boolean flag = false;
						for(Map<String, Object> existMap:mapList)
						{
							if(existMap.get("chkIndexId").equals(chkRecord.getQachkIndex().getChkIndexId()))
							{
								flag = true;
								childList = (List<Map<String, Object>>) existMap.get("children");
								break;
							}
						}
						if(!flag)
						{
							Map<String, Object> parentMap = new HashMap<String, Object>();
							childList = new ArrayList<Map<String,Object>>();
							parentMap.put("children", childList);
							parentMap.put("chkIndexId", chkRecord.getQachkIndex().getChkIndexId());
							parentMap.put("chkItemName", record.get("chkItemName")+"(报告存在项)");
							parentMap.put("chkType", record.get("chkType"));
							
							parentMap.put("status", "closed");
							
							parentMap.put("reportRecord",true );
							
							mapList.add(parentMap);
						}
						
						map.put("chkRecordId", record.get("chkRecordID"));
						
						map.put("chkIndexId", "content"+record.get("chkRecordID"));
						map.put("chkItemName", "");//子的检查项内容为空,
						map.put("operator", record.get("confirmer"));
						map.put("sn", chkRecord.getSn());//检查内容的sn
						//if(record.get("chkPlanID")!=null)
					//	{
						//	QAChkPlan plan= qAChkPlanService.getById((String)record.get("chkPlanID"));
					//		map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
							//map.put("", plan.getChkFinishedFlag());
					//	}
						map.put("chkTime", DateUtil.dateToString((Date)record.get("chkTime"),"yyyy-MM-dd HH:mm:ss"));//实际检查时间是检查记录的时间
						map.put("chkContent", record.get("chkContent"));
						if("×".equals(record.get("chkResult")))
						{
							map.put("chkResult", record.get("chkResultDesc"));			
						}else{
							map.put("chkResult", record.get("chkResult"));							
						}
							
						map.put("advice", record.get("advice"));
						
						childList.add(map);
						
					}
					
				
			}
			
		}
		//签过字没入报告的，新增和编辑都显示
		if(!isQAMBackReport)
		{
			List<Map<String, Object>> records = null;
			if(qaIndexIds!=null&&!"".equals(qaIndexIds))
			{
				//全部的
				String[] indexs =  qaIndexIds.split(",");
				records = qAChkRecordService.getByStudyAndChkTimeAndNoReport(studyNoParam,startChkDate,endChkDate,indexs,user);
			}else {
				//部分检查项的
				records = qAChkRecordService.getByStudyAndChkTimeAndNoReport(studyNoParam,startChkDate,endChkDate,null,user);
			}
			//List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> record:records)
			{
				if((Integer)record.get("chkResultFlag")!=0)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					
					List<Map<String, Object>> childList = null;
					boolean flag = false;
					for(Map<String, Object> existMap:mapList)
					{
						if(existMap.get("chkIndexId").equals(record.get("chkIndexID")))
						{
							flag = true;
							childList = (List<Map<String, Object>>) existMap.get("children");
							break;
						}
					}
					if(!flag)
					{
						Map<String, Object> parentMap = new HashMap<String, Object>();
						childList = new ArrayList<Map<String,Object>>();
						parentMap.put("children", childList);
						parentMap.put("chkIndexId", record.get("chkIndexID"));
						parentMap.put("chkItemName", record.get("chkItemName"));
						parentMap.put("chkType", record.get("chkType"));
						parentMap.put("status", "closed");
						mapList.add(parentMap);
					}
					
					
					map.put("chkRecordId", record.get("chkRecordID"));
					
					map.put("chkIndexId", "content"+record.get("chkRecordID"));
					map.put("chkItemName", "");//子的检查项内容为空,
					map.put("operator", record.get("operator"));
					map.put("sn", record.get("sn"));//检查内容的sn
					if(record.get("chkPlanID")!=null)
					{
						QAChkPlan plan= qAChkPlanService.getById((String)record.get("chkPlanID"));
						if(plan!=null)
							map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
						//map.put("", plan.getChkFinishedFlag());
					}
					map.put("chkTime", DateUtil.dateToString((Date)record.get("chkTime"),"yyyy-MM-dd HH:mm:ss"));//实际检查时间是检查记录的时间
					map.put("chkContent", record.get("chkContent"));
					if("×".equals(record.get("chkResult"))){
						map.put("chkResult", record.get("chkResultDesc"));
					}else{
						map.put("chkResult", record.get("chkResult"));
					}
					map.put("advice", record.get("advice"));
					
					childList.add(map);
					
				}
			}
			
		}
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	@SuppressWarnings("unchecked")
	public void getRecordsByReport()
	{
		//QAChkReport report = qAChkReportService.getById(chkReportCode);
		//Set<QAChkReportRecord> records = report.getQachkReportRecords();
		List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(chkReportCode);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(QAChkReportRecord record:records)
		{
				Map<String, Object> map = new HashMap<String, Object>();
				
				QAChkRecord chkRecord =  qAChkRecordService.getById(record.getChkRecordId());
				if(chkRecord!=null)
				{
					List<Map<String, Object>> childList = null;
					boolean flag = false;
					for(Map<String, Object> existMap:mapList)
					{
						if(existMap.get("chkIndexId").equals(chkRecord.getQachkIndex().getChkIndexId()))
						{
							flag = true;
							childList = (List<Map<String, Object>>) existMap.get("children");
							break;
						}
					}
					if(!flag)
					{
						Map<String, Object> parentMap = new HashMap<String, Object>();
						childList = new ArrayList<Map<String,Object>>();
						parentMap.put("children", childList);
						parentMap.put("chkIndexId", chkRecord.getQachkIndex().getChkIndexId());
						parentMap.put("chkItemName", record.getChkItemName());
						parentMap.put("status", "closed");
						mapList.add(parentMap);
					}
					
					
					map.put("chkRecordId", record.getChkRecordId());
					
					map.put("chkIndexId", "content"+record.getChkRecordId());
					map.put("chkItemName", "");//子的检查项内容为空,
					map.put("operator", record.getConfirmer());
					map.put("sn", chkRecord.getSn());//检查内容的sn
					//if(record.get("chkPlanID")!=null)
				//	{
					//	QAChkPlan plan= qAChkPlanService.getById((String)record.get("chkPlanID"));
				//		map.put("planChkTime", DateUtil.dateToString(plan.getPlanChkTime(),"yyyy-MM-dd"));
						//map.put("", plan.getChkFinishedFlag());
				//	}
					map.put("chkTime", DateUtil.dateToString((Date)record.getChkTime(),"yyyy-MM-dd HH:mm:ss"));//实际检查时间是检查记录的时间
					map.put("chkContent", record.getChkContent());
					if("×".equals(record.getChkResult()))
					{
						map.put("chkResult", record.getChkResultDesc());
					}else{
						map.put("chkResult", record.getChkResult());						
					}
					map.put("advice", record.getAdvice());
					
					childList.add(map);
					Collections.sort(childList,new MyComparator());
				}
				
			
		}
		Collections.sort(mapList,new MyComparator());
		
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	class MyComparator implements Comparator<Map<String, Object>>
	{
		public int compare(Map<String, Object> map1,Map<String, Object> map2) {
			if(((String)map1.get("chkIndexId")).contains("content")&&((String)map2.get("chkIndexId")).contains("content"))
			{
				return (Integer)map1.get("sn")-(Integer)map2.get("sn");
			}else {
				return ((String)map1.get("chkIndexId")).compareTo((String)map2.get("chkIndexId"));
			}
		
		}
		
		
	}
	
	
	
	public String getStudyNoParam() {
		return studyNoParam;
	}
	public void setStudyNoParam(String studyNoParam) {
		this.studyNoParam = studyNoParam;
	}
	public QAChkRecordService getqAChkRecordService() {
		return qAChkRecordService;
	}
	public void setqAChkRecordService(QAChkRecordService qAChkRecordService) {
		this.qAChkRecordService = qAChkRecordService;
	}
	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}
	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getChkItemId() {
		return chkItemId;
	}
	public void setChkItemId(String chkItemId) {
		this.chkItemId = chkItemId;
	}
	public DictChkItemChkTblRegService getDictChkItemChkTblRegService() {
		return dictChkItemChkTblRegService;
	}
	public QAChkSopService getqAChkSopService() {
		return qAChkSopService;
	}
	public void setqAChkSopService(QAChkSopService qAChkSopService) {
		this.qAChkSopService = qAChkSopService;
	}
	public void setDictChkItemChkTblRegService(
			DictChkItemChkTblRegService dictChkItemChkTblRegService) {
		this.dictChkItemChkTblRegService = dictChkItemChkTblRegService;
	}
	public String getChkTblId() {
		return chkTblId;
	}
	public void setChkTblId(String chkTblId) {
		this.chkTblId = chkTblId;
	}
	public DictQACheckContentTableService getDictQACheckContentTableService() {
		return dictQACheckContentTableService;
	}
	public void setDictQACheckContentTableService(
			DictQACheckContentTableService dictQACheckContentTableService) {
		this.dictQACheckContentTableService = dictQACheckContentTableService;
	}
	public String getChkPlanId() {
		return chkPlanId;
	}
	public void setChkPlanId(String chkPlanId) {
		this.chkPlanId = chkPlanId;
	}
	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}
	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}
	public QAChkIndexService getqAChkIndexService() {
		return qAChkIndexService;
	}
	public void setqAChkIndexService(QAChkIndexService qAChkIndexService) {
		this.qAChkIndexService = qAChkIndexService;
	}
	
	
	public String getPlanIdItemId() {
		return planIdItemId;
	}
	public void setPlanIdItemId(String planIdItemId) {
		this.planIdItemId = planIdItemId;
	}
	public String getQaIndexId() {
		return qaIndexId;
	}
	public void setQaIndexId(String qaIndexId) {
		this.qaIndexId = qaIndexId;
	}
	public DictQACheckTableService getDictQACheckTableService() {
		return dictQACheckTableService;
	}
	public void setDictQACheckTableService(
			DictQACheckTableService dictQACheckTableService) {
		this.dictQACheckTableService = dictQACheckTableService;
	}
	public QAChkTblRegService getqAChkTblRegService() {
		return qAChkTblRegService;
	}
	public void setqAChkTblRegService(QAChkTblRegService qAChkTblRegService) {
		this.qAChkTblRegService = qAChkTblRegService;
	}
	public Date getStartChkDate() {
		return startChkDate;
	}
	public void setStartChkDate(Date startChkDate) {
		this.startChkDate = startChkDate;
	}
	public Date getEndChkDate() {
		return endChkDate;
	}
	public void setEndChkDate(Date endChkDate) {
		this.endChkDate = endChkDate;
	}
	public String getQaIndexIds() {
		return qaIndexIds;
	}
	public void setQaIndexIds(String qaIndexIds) {
		this.qaIndexIds = qaIndexIds;
	}
	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}
	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}
	public TblStudyItemService getTblStudyItemService() {
		return tblStudyItemService;
	}
	public void setTblStudyItemService(TblStudyItemService tblStudyItemService) {
		this.tblStudyItemService = tblStudyItemService;
	}
	public DictStudyTypeService getDictStudyTypeService() {
		return dictStudyTypeService;
	}
	public void setDictStudyTypeService(DictStudyTypeService dictStudyTypeService) {
		this.dictStudyTypeService = dictStudyTypeService;
	}
	public DictChkItemStudyGroupRegService getDictChkItemStudyGroupRegService() {
		return dictChkItemStudyGroupRegService;
	}
	public void setDictChkItemStudyGroupRegService(
			DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService) {
		this.dictChkItemStudyGroupRegService = dictChkItemStudyGroupRegService;
	}
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}
	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
	public Integer getChkIndexStatus() {
		return chkIndexStatus;
	}
	public void setChkIndexStatus(Integer chkIndexStatus) {
		this.chkIndexStatus = chkIndexStatus;
	}
	public QAChkReportService getqAChkReportService() {
		return qAChkReportService;
	}
	public void setqAChkReportService(QAChkReportService qAChkReportService) {
		this.qAChkReportService = qAChkReportService;
	}
	public String getChkReportCode() {
		return chkReportCode;
	}
	public void setChkReportCode(String chkReportCode) {
		this.chkReportCode = chkReportCode;
	}
	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}
	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}
	public DictChkItemQAFileRegService getDictChkItemQAFileRegService() {
		return dictChkItemQAFileRegService;
	}
	public void setDictChkItemQAFileRegService(
			DictChkItemQAFileRegService dictChkItemQAFileRegService) {
		this.dictChkItemQAFileRegService = dictChkItemQAFileRegService;
	}
	public String getFileRegIds() {
		return fileRegIds;
	}
	public void setFileRegIds(String fileRegIds) {
		this.fileRegIds = fileRegIds;
	}
	public String getOneChkPlanId() {
		return oneChkPlanId;
	}
	public void setOneChkPlanId(String oneChkPlanId) {
		this.oneChkPlanId = oneChkPlanId;
	}
	public QAChkReportRecordService getqAChkReportRecordService() {
		return qAChkReportRecordService;
	}
	public void setqAChkReportRecordService(
			QAChkReportRecordService qAChkReportRecordService) {
		this.qAChkReportRecordService = qAChkReportRecordService;
	}
	public String getSopRecordId() {
		return sopRecordId;
	}
	public void setSopRecordId(String sopRecordId) {
		this.sopRecordId = sopRecordId;
	}
	public String getAllOperate() {
		return allOperate;
	}
	public void setAllOperate(String allOperate) {
		this.allOperate = allOperate;
	}
	
}
