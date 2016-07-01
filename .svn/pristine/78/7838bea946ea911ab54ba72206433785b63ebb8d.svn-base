package com.lanen.view.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQAStatementTemple;
import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkReportReader;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.model.qa.QAChkSop;
import com.lanen.model.qa.QAChkTblReg;
import com.lanen.model.qa.QAReChkRecord;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQAStatementTempleService;
import com.lanen.service.qa.QAApprovalOpinionService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.service.qa.QAChkReportReaderService;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAChkSopService;
import com.lanen.service.qa.QAChkTblRegService;
import com.lanen.service.qa.QAReChkRecordService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.ReportMap;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;

import com.lanen.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkReportAction extends BaseAction<QAChkReport>{

	private static final long serialVersionUID = 996677080224389439L;
	
	@Resource
	private QAChkReportService qAChkReportService;
	@Resource
	private QAChkRecordService qAChkRecordService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private UserService userService;
	@Resource
	private QAChkIndexService qAChkIndexService;
	@Resource
	private QAChkSopService qAChkSopService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private QAApprovalOpinionService qAApprovalOpinionService;
	@Resource
	private QAChkPlanService qAChkPlanService;
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private DictQAStatementTempleService dictQAStatementTempleService;
	@Resource
	private QAChkReportRecordService qAChkReportRecordService;
	@Resource
	private QAChkTblRegService qAChkTblRegService;
	@Resource
	private QAChkReportReaderService qAChkReportReaderService;
	@Resource
	private QAReChkRecordService qAReChkRecordService;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;

	
	private String studyNoParam;
	private String records;

	private Integer confirmReportType;//0回复，1整改2两者
	private String QAMainPage;
	//主页面报告查询条件
	private Date reportStartDate;
	private Date reportEndDate;
	private Integer reportStatus;
	private Integer reportCatalog;
	private String reportSearcher;
	
	private Integer approvalResult;
	private Integer approvalResultFlag;
	private String approvalOpinion;
	
	private String location;
	private String fileName;
	
	private Integer addOrEdit;//1新增2编辑
	
	private String printContent;
	
	private String templeId;
	
	private String selectChkReportCode;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	private String reports;
	private String emailList;
	
	private Integer chkType;
	
	public String list()
	{
		String role = (String)ActionContext.getContext().getSession().get("role");
		if(role==null||"".equals(role))
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
			
		}
		
		if(reportEndDate==null&&reportStartDate==null)
		{
			reportEndDate = DateUtil.getTodayDate();
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(reportEndDate);
			calendar1.add(Calendar.DATE, -6);
			reportStartDate = calendar1.getTime();
		}
		ActionContext.getContext().put("reportStartDate2", DateUtil.dateToString(reportStartDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("reportEndDate2",  DateUtil.dateToString(reportEndDate, "yyyy-MM-dd"));
		
		
		if((QAMainPage==null&&studyNoParam==null)||"true".equals(QAMainPage))//主界面的
		{
			ActionContext.getContext().put("QAMainPage", true);
			
			
		}else{//子界面的
			
			//从子页面的开始检查过来的。
			String oneChkPlanId = (String)ActionContext.getContext().getSession().get("oneChildChkPlanId");
			if(oneChkPlanId!=null&&!"".equals(oneChkPlanId))
			{
				ActionContext.getContext().getSession().put("oneChildChkPlanId","");
				//oneChkPlanId=oneChkPlanId.substring(5);
				
				//QAChkReportRecord reportRecord2 = qAChkReportRecordService.getById(oneChkPlanId);
				//ActionContext.getContext().put("selectChkReportCode", reportRecord2.getQachkReport().getChkReportCode());
				//ActionContext.getContext().put("selectStudyNoPara", reportRecord2.getStudyNo());
				
				if(oneChkPlanId.startsWith("second5"))//检查报告
				{
					String reportId = oneChkPlanId.substring(7);
						
					QAChkReport report1 = qAChkReportService.getById(reportId);
					//String studyNo = qAChkIndexService.getStudyNoByReportCode(reportId);
					ActionContext.getContext().put("selectChkReportCode2", report1.getChkReportCode());
					//ActionContext.getContext().put("selectStudyNoPara", studyNo);
				
				}
			}else{
				//从主页面的开始检查过来的。
				ActionContext.getContext().put("selectChkReportCode2",selectChkReportCode);
			}
			
			//从检查记录中过来的填写检查报告
			Object newReport = ActionContext.getContext().getSession().get("newReportForReportPage");
			if(newReport!=null&&!"".equals(newReport))
			{
				ActionContext.getContext().getSession().put("newReportForReportPage","");
				ActionContext.getContext().put("newReportForReportPage", newReport);
			}
			
			ActionContext.getContext().put("studyNoParam", studyNoParam);
			if(!"".equals(studyNoParam))
			{
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				String sd = "";
				if(studyItem!=null)
				{
					sd = studyItem.getSd();
				}else{
					sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
				}
				ActionContext.getContext().getSession().put("sd", sd);
				QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(studyNoParam);
				String qa = null;
				if(index!=null&&index.getInspector()!=null)
				{
					qa=index.getInspector();
				}else {
					TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
					if(item!=null)
					{
						qa=item.getQa();			
					}
				}
				ActionContext.getContext().getSession().put("qa", qa);
				User user = (User)ActionContext.getContext().getSession().get("user");
				if(user.getRealName().equals(qa))
				{
					ActionContext.getContext().put("isReportQAInReport", true);
				}else {
					ActionContext.getContext().put("isReportQAInReport", false);
				}
				if(index!=null&&index.getReportState()!=null&&index.getReportState()==1)
				{
					ActionContext.getContext().put("haveRight",false );
				}else {
					
						if(user.getRealName().equals(qa))
						{
							ActionContext.getContext().put("haveRight",true );
							if(index!=null&&(index.getChkPlanFinishTime()==null||"".equals(index.getChkPlanFinishTime())))
							{
								ActionContext.getContext().put("haveRightForEndStudy",true );
							}
							if(index!=null&&index.getChkPlanFinishConfirmer()!=null)
							{
								ActionContext.getContext().put("haveRightForiReportStudy",true );
							}
						}else {
							List<QAChkRecord> records = qAChkRecordService.getByStudyNo(studyNoParam);
							for(QAChkRecord record:records)
							{
								if(user.getRealName().equals(record.getInspector()))
								{
									ActionContext.getContext().put("haveRight",true );
									break;
								}
							}
						}
						
				}
				
				if(index!=null&&(index.getReportState()!=null&&index.getReportState()==1)||(index.getChkPlanFinishFlag()!=null&&index.getChkPlanFinishFlag()==1))
				{
					ActionContext.getContext().put("studyFinishForReport",  true);	
				}else {
					ActionContext.getContext().put("studyFinishForReport",  false);	
				}
				
			}
		}
		
		//ActionContext.getContext().put("QAMainPage", QAMainPage);
		ActionContext.getContext().put("studyNoParam", studyNoParam);
		//ActionContext.getContext().put("reportStartDate", reportStartDate);
		//ActionContext.getContext().put("reportEndDate", reportEndDate);
		ActionContext.getContext().put("reportStatus", reportStatus);
		ActionContext.getContext().put("reportCatalog", reportCatalog);
		ActionContext.getContext().put("reportSearcher", reportSearcher);
		ActionContext.getContext().put("chkReportCode",model.getChkReportCode());
		
		return "list";
	}

	public void getChkItemSizeByChkType()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(addOrEdit==2)//编辑
		{
			studyNoParam = qAChkIndexService.getStudyNoByReportCode(model.getChkReportCode());
		}
		
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		if(studyItem!=null)
		{
			DictStudyType studyType = dictStudyTypeService.getByStudyTypeCode(studyItem.getStudyTypeCode());
			if(studyType!=null&&studyType.getStudyGroupID()!=null)
			{
				List<DictChkItemStudyGroupReg> groupItems = dictChkItemStudyGroupRegService.getByStudyGroupAndChkType(studyType.getStudyGroupID(),chkType);
				if(groupItems!=null)
				{
					map.put("success", true);
					map.put("itemSize",groupItems.size());
				}else{
					map.put("success", false);
				}
					
			}else{
				map.put("success", false);
			}
			
		}else{
			map.put("success", false);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReport report = null;
		//String key = qAChkReportService.getKey("QAChkReport"); 
		List<DictChkItemStudyGroupReg> dictChkItems = new ArrayList<DictChkItemStudyGroupReg>();
		if(addOrEdit==1)//新增
		{
			report = new QAChkReport();
			String todayKey = qAChkReportService.getKeyByStudyNo(studyNoParam);
			String num = todayKey.substring(todayKey.length()-2);
			String numStr = "00";
			if(Integer.parseInt(num)<99&&addOrEdit==1)//新增
			{
				if(Integer.parseInt(num)<9)
				{
					numStr = "-0"+(Integer.parseInt(num)+1);
				}else if(Integer.parseInt(num)<99)
				{
					numStr = "-"+(Integer.parseInt(num)+1);
				}
				report.setChkReportCode(studyNoParam+numStr);
				
			}else{
				map.put("success", false);
				map.put("msg", "专题的报告的个数大于一百个！");
				return;
			}
			report.setRptState(0);//0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
			
		}else if(addOrEdit==2){//编辑
			report=qAChkReportService.getById(model.getChkReportCode());
			if(studyNoParam==null||"".equals(studyNoParam))//编辑的时候studyNo可能不存在
			{
				studyNoParam = qAChkIndexService.getStudyNoByReportCode(model.getChkReportCode());
			}
			//删除这次不存在的项
			//Set<QAChkReportRecord> reportRecords = report.getQachkReportRecords();
			List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(model.getChkReportCode());
			for(QAChkReportRecord reportRe:reportRecords)
			{
				if(!records.contains(reportRe.getChkRecordId()))
				{
					qAChkReportRecordService.delete(reportRe.getChkReportRecordId());
					QAChkRecord record = qAChkRecordService.getById(reportRe.getChkRecordId());
					QAChkIndex index = record.getQachkIndex();
					if(index.getQachkReport()!=null)
					{
						index.setQachkReport(null);
						qAChkIndexService.update(index);
					}
				}else {
					int index = records.indexOf(reportRe.getChkRecordId());
					int end = index+reportRe.getChkRecordId().length();
					if(end<records.length()-1)
						end+=1;
					records=records.substring(0,index)+records.substring(end);
				}
			}
			
		}
		
			//report.setChkReportCode(key);
			report.setCreateTime(new Date());
			
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			report.setInspector(tempUser.getRealName());
			report.setInspectorSignTime(new Date());
			QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
			String sd = qAStudyChkIndex.getSd();
			if(sd==null||"".equals(sd))
			{
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				if(studyItem!=null)
				{
					sd = studyItem.getSd();
				}else{
					sd = tblStudyPlanService.getSDByStudyNo(studyNoParam);
				}
			}
			report.setSd(sd);
			
			
			
			//QAM是权限找第一个就行了。
			List<User> qAMList = userService.findByPrivilegeName("QA负责人");
			if(qAMList!=null&&qAMList.size()>0)
			{
				report.setQam(qAMList.get(0).getRealName());
			}
			//SD和QA检查员在报告记录中有，report如果多个检查索引即多个检查项在一个报告中的情况，SD和QA的处理
			qAChkReportService.save(report);
			List<User> fmList = userService.findByPrivilegeName("FM");
			if(fmList!=null&&fmList.size()>0)
			{
				report.setReplyFmname(fmList.get(0).getRealName());
			}
			
		//&&(map.get("success")==null||"".equals(map.get("success")))
		if(addOrEdit==1||addOrEdit==2)
		{
			Map<String,QAChkIndex> reportChkIndexs = new HashMap<String,QAChkIndex>();
			
			String[] recordIds = records.split(",");//可能是有多个QAChkIndex，即有多个检查项
			
			for(String id:recordIds)
			{
				
				QAChkRecord record = qAChkRecordService.getById(id);
				if(record!=null&&!"".equals(record))
				{
					QAChkReportRecord reportRecord = new QAChkReportRecord();
					reportRecord.setAdvice(record.getAdvice());
					reportRecord.setChkContent(record.getChkContent());
					reportRecord.setChkItemName(record.getQachkIndex().getChkItemName());
					reportRecord.setChkRecordId(record.getChkRecordId());
					String keyString = qAChkReportRecordService.getKey("QAChkReportRecord");
					reportRecord.setChkReportRecordId(keyString);
					reportRecord.setChkResult(record.getChkResult());
					reportRecord.setChkResultDesc(record.getChkResultDesc());
					reportRecord.setChkResultFlag(record.getChkResultFlag());
					reportRecord.setChkTime(record.getChkTime());
					
					if("×".equals(record.getChkResult()))
					{
						if(report.getNeedReply()==null||(report.getNeedReply()!=null&&report.getNeedReply()!=1))
						{
							report.setNeedReply(1);//0不需要，1需要
						}
					}
					
					//签字信息，签字是对一个检查项目签字的 QAChkIndex
					TblESLink link = tblESLinkService.getByEntityNameAndDataIdType("QAChkIndex", record.getQachkIndex().getChkIndexId(), 802);//802表示一个检查项的签字确认
					if(link!=null)
					{		
						TblES es = link.getTblES();
						reportRecord.setConfirmer(es.getSigner());
						reportRecord.setConfirmTime(link.getRecordTime());
					}
					reportRecord.setInspector(record.getInspector());//检查索引和检查记录里的inspector应该是一样的。
					reportRecord.setQachkReport(report);//跟report关联
					reportRecord.setStudyNo(record.getQachkIndex().getStudyNo());
					
					qAChkReportRecordService.save(reportRecord);
					if(reportChkIndexs.get(record.getQachkIndex().getChkIndexId())==null)
					{
						reportChkIndexs.put(record.getQachkIndex().getChkIndexId(), record.getQachkIndex());
					}
				}
			}
			qAChkReportService.update(report);
			for(Entry<String, QAChkIndex> entry:reportChkIndexs.entrySet())
			{
				QAChkIndex index = entry.getValue();
				index.setQachkReport(report);
				qAChkIndexService.update(index);//更新index和report的关系
			}
			
			//Set<QAChkIndex> indexs = report.getQachkIndexes();
			map.put("catalog", "研究");//如果不是不是方案或者变更（方案或者变更是没有检查计划的），直接返回研究
			Integer chkType = qAChkIndexService.getChkTypeByReportCode(report.getChkReportCode());
			//if(indexs!=null&&indexs.size()>0)
			//{
				//QAChkIndex oneIndex = (QAChkIndex)(indexs.toArray()[0]);
				//Integer chkType = oneIndex.getChkType();
				if(chkType!=null)
				{//1:研究；2：过程；3：设施；4方案；5：报告
					if(chkType==1)
						map.put("catalog", "研究");
					else if(chkType==2)
						map.put("catalog", "过程");
					else if(chkType==3)
						map.put("catalog", "设施");
					else if(chkType==4)
						map.put("catalog", "方案");
					else if(chkType==5)
						map.put("catalog", "报告");
				}
			//}
			map.put("chkReportCode",report.getChkReportCode());
			map.put("qam",report.getQam());
			map.put("createTime",DateUtil.dateToString(report.getCreateTime(),"yyyy-MM-dd"));
			map.put("rptState",report.getRptState());
			
			map.put("success", true);
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void del()
	{
		
		//QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		//Set<QAChkIndex> chkIndexs=report.getQachkIndexes();
	//	List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(model.getChkReportCode());
		String[] reportIds = reports.split(",");
		List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCodes(reportIds);
		for(QAChkIndex chkIndex:chkIndexs)
		{
			chkIndex.setQachkReport(null);
			qAChkIndexService.update(chkIndex);
		}
		//List<QAChkReportReader> reportReaders = qAChkReportReaderService.getByReportCode(model.getChkReportCode());
		List<QAChkReportReader> reportReaders = qAChkReportReaderService.getByReportCodes(reportIds);
		for(QAChkReportReader reader:reportReaders)
		{
			qAChkReportReaderService.delete(reader.getCcId());
		}
		//List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(model.getChkReportCode());
		List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCodes(reportIds);
		for(QAChkReportRecord record:records)
		{
			qAChkReportRecordService.delete(record.getChkReportRecordId());
		}
		
		List<QAChkReport> reportList = qAChkReportService.getByIds(reportIds);
		for(QAChkReport report:reportList)
		{
			qAChkReportService.delete(report.getChkReportCode());			
		}
		
		
	}
	public void getReportList()
	{
		//List<QAChkIndex> indexs = null;
		List<Map<String,Object>> reports = null;
		if(QAMainPage!=null&&"true".equals(QAMainPage))//主页面
		{
			//indexs = qAChkIndexService.findAll();
			//reportEndDate要增加一天
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(reportEndDate);
			calendar1.add(Calendar.DATE, 1);
			reportEndDate = calendar1.getTime();
			
			//indexs = qAChkIndexService.getByConditions(reportStartDate,reportEndDate,reportStatus,reportCatalog,reportSearcher);
			reports = qAChkReportService.getByConditions(reportStartDate,reportEndDate,reportStatus,reportCatalog,reportSearcher);
			
		}else if(!"".equals(studyNoParam)){
			//indexs = qAChkIndexService.getByStudyNo(studyNoParam);
			reports = qAChkReportService.getByStudyNo(studyNoParam);
		}else {
			//indexs = new ArrayList<QAChkIndex>();//子页面什么都没选择的情况
			reports = new ArrayList<Map<String, Object>>();
		}
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//if(indexs!=null)
		//{
			/*for(QAChkIndex index:indexs)
			{
				if(index.getQachkReport()!=null)
				{
					boolean flag = false;
					for(QAChkReport existReport:reports)
					{
						if(index.getQachkReport().getChkReportCode().equals(existReport.getChkReportCode()))
						{
							flag = true;
						}
					}
					if(!flag)
					{				
						reports.add(index.getQachkReport());
					}
				}
			}*/
			/*private String chkReportCode; 
		     private String sd;
		     private String inspector;
		     private java.util.Date inspectorSignTime;
		     private String qam;
		     private java.util.Date qamsignTime;
		     private Integer rptState;//0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
		     private Integer needReply;
		     private Integer replyState;
		     private java.util.Date replyFmapprovalTime;
		     private String replyFmname;
		     private Integer replyFmreviewResult;
		     private String replyFmreveiwRemark;
		     private java.util.Date replyFmreveiwTime;
		     private java.util.Date replyInspectorReceiveTime;
		     private String replyInspectorReciveName;
		     private Integer needReChk;
		     private Integer reChkState;
		     private Integer needDelay;
		     private Integer delayState;
		     private java.util.Date delayFmapprovalTime;
		     private String delayFmname;
		     private java.util.Date delayQaureceiveTime;
		     private String delayQaureceiveName;
		     private java.util.Date finalFinishTime;
		     private String finalFinishSign;*/
			
			User user = (User)ActionContext.getContext().getSession().get("user");
			if(reports!=null)
			{
			for(Map<String, Object> report:reports)
			{
				if(user.getRealName().equals(report.get("inspector"))||user.getRealName().equals(report.get("QAM")))
				{
					if(report.get("rptState")==null||(Integer)report.get("rptState")==0)
					{
						//草稿状态的只有自己可以看
						if(!user.getRealName().equals(report.get("inspector"))){
							continue;
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("chkReportCode", report.get("chkReportCode"));
					map.put("sd", report.get("SD"));
					map.put("inspector", report.get("inspector"));
					//String sNo = ((QAChkIndex)(report.getQachkIndexes().toArray()[0])).getStudyNo();
					String sNo = qAChkIndexService.getStudyNoByReportCode((String)report.get("chkReportCode"));
					map.put("studyNo", sNo);
					map.put("qam",report.get("QAM"));
					map.put("createTime",DateUtil.dateToString((Date)report.get("createTime"),"yyyy-MM-dd"));
					map.put("rptState", report.get("rptState"));
					if(report.get("rptState")!=null&&(Integer)report.get("rptState")==-2)
					{
						QAApprovalOpinion opinion = qAApprovalOpinionService.getByTypeAndReportAndResultAndOperator(1, (String)report.get("chkReportCode"),-1, 2);
						map.put("qamApprovalOpinion",opinion.getApprovalOpinion());
					}
					
					map.put("needDelay", report.get("needDelay"));
					map.put("needReply", report.get("needReply"));
					map.put("delayState", report.get("delayState"));
					map.put("replyState", report.get("replyState"));
					map.put("needReChk", report.get("needReChk"));
					map.put("reChkState", report.get("reChkState"));
					map.put("operate", "aa");
					if(report.get("replyState")!=null
							&&((Integer)report.get("replyState")==2||(Integer)report.get("replyState")==-1)//FM批复过了
							&&report.get("replyFmreviewResult")!=null)
					{
						if((Integer)report.get("replyFmreviewResult")==1)//0：无意见；1：有意见
						{
							map.put("replyFmreveiwRemark", report.get("replyFmreveiwRemark"));
						}else {
							map.put("replyFmreveiwRemark", "");
						}
					}else {
						map.put("replyFmreveiwRemark",null);
					}
					
					QAApprovalOpinion opinion = qAApprovalOpinionService.getByTypeAndReportAndOperator(3,(String)report.get("chkReportCode"),1);
					//approvalType 1：报告；2：回复；3：延迟整改；4：检查计划
					//objectCode 专题/报告编号
					//operatorType 1：FM；2：QAM；3：QA检查员
					if(opinion!=null)
					{
						//1：过；-1：未过
						if(opinion.getApprovalResultFlag()==1)
							map.put("delayFmreveiwRemark", "");
						else {
							map.put("delayFmreveiwRemark", opinion.getApprovalOpinion());
						}
					}else {
						map.put("delayFmreveiwRemark",null );
					}
					
				
					QAStudyChkIndex index = qAStudyChkIndexService.getByStudyNo(sNo);
					/*String qa = null;
					if(index!=null&&index.getInspector()!=null)
					{
						qa=index.getInspector();
					}else {
						TblStudyItem item =  tblStudyItemService.getByStudyNoStudyItem(sNo);
						if(item!=null)
						{
							qa=item.getQa();			
						}
					}*/
					//ActionContext.getContext().getSession().put("qa", qa);
					//Set<QAChkIndex> reportIndexes = report.getQachkIndexes();
					map.put("catalog", "研究");//如果不是不是方案或者变更（方案或者变更是没有检查计划的），直接返回研究
					//if(reportIndexes!=null&&reportIndexes.size()>0)
					//{
						//QAChkIndex oneIndex = (QAChkIndex)(reportIndexes.toArray()[0]);
						//Integer chkType = oneIndex.getChkType();
						Integer chkType = qAChkIndexService.getChkTypeByReportCode((String)report.get("chkReportCode"));
						if(chkType!=null)
						{//1:研究；2：过程；3：设施；4方案；5：报告
							if(chkType==1)
								map.put("catalog", "研究");
							else if(chkType==2)
								map.put("catalog", "过程");
							else if(chkType==3)
								map.put("catalog", "设施");
							else if(chkType==4)
								map.put("catalog", "方案");
							else if(chkType==5)
								map.put("catalog", "报告");
						}
					//}
				
					//qa和qam可以显示报告
					if(index!=null&&index.getReportState()!=null&&index.getReportState()==1)
					{
						map.put("haveRight",false );
					}else {	
						if(user.getRealName().equals(report.get("inspector")))
						{
							map.put("haveRight",true );
						}
					}
					
					if(report.get("QAMSignTime")!=null&&!"".equals(report.get("QAMSignTime"))&&
						(report.get("needReply")==null||(Integer)report.get("needReply")==0)&&//报告不需要回复
						(report.get("needReChk")==null||(Integer)report.get("needReChk")==0)&&//报告不需要再检查
						(report.get("delayState")==null||(Integer)report.get("delayState")==1)&&//报告延迟整改完成
						(Integer)report.get("rptState")!=9)//报告完成
					{
						map.put("canEndReport", true);
					}else {
						map.put("canEndReport", false);
					}
					
					mapList.add(map);
				}else {//只显示自己有关的报告
					
				}
			}
			}
			
		//}
		/*String[] _nory_format={"chkReportCode","sd","inspector","qam","createTime","rptState",
					"delayState","replyState","needReChk","reChkState"};*/
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	public Map<String,Object> getMapFromMapList(String chkReportCode,List<Map<String,Object>> list){
		
		for(Map<String,Object> map:list)
		{
			if(map.get("chkReportCode")!=null&&((String)map.get("chkReportCode")).equals(chkReportCode))
			{
				return map;
			}
		}
		return null;
	}
	public void commit()
	{
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String[] reportIds = reports.split(",");
		Map<String,Object> map = new HashMap<String, Object>();
		
		String reportTypeString = "";
		
		String emailListTemp = "";
	
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		
		List<QAChkReport> reportList = qAChkReportService.getByIds(reportIds);
		List<Map<String,Object>> reportStudyNoAndName = qAChkReportService.getStudyInfoByReportCodeList(reportIds);
		for(QAChkReport report:reportList)
		{
			String receiverList = "";
			//0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
			//QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
			//1:研究；2：过程；3：设施；4方案；5：报告
			//String reportStudyNo = studyNoParam = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
			
			Integer reportType = qAChkIndexService.getChkTypeByReportCode(report.getChkReportCode());
			if(reportType==1)
				reportTypeString="基于研究";
			if(reportType==4)
				reportTypeString="方案";
			if(reportType==5)
				reportTypeString="专题报告";
			
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc(reportTypeString+"检查报告提交");
			es.setEsType(803);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);
			
			esLink.setTableName("QAChkReport");
			esLink.setDataId(report.getChkReportCode());
			esLink.setTblES(es);
			esLink.setEsType(803);
			esLink.setEsTypeDesc(reportTypeString+"检查报告提交签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			
			writeLog(reportTypeString+"检查报告提交签字确认",reportTypeString+"检查报告提交签字确认",reportTypeString+"检查报告提交签字确认,报告编号："+report.getChkReportCode());
			
			report.setInspectorSignTime(new Date());
			report.setRptState(1);
			
			qAChkReportService.update(report);
			
			Map<String,Object> studyInfo = getMapFromMapList(report.getChkReportCode(),reportStudyNoAndName);
			//通知QAM去审批
			String msgTitle=("QA("+tempUser.getRealName()+")，提交了"+reportTypeString+"检查报告"+report.getChkReportCode()+","+"　需要审批");//消息头
			String msgContent = "<br>";
			msgContent = msgContent+"QA("+tempUser.getRealName()+")于"+currentDate+"提交了"+reportTypeString+"检查报告"+
			"<br>报告编号:"+report.getChkReportCode();
			if(studyInfo!=null)
			{
				msgContent+="<br>专题编号："+studyInfo.get("studyNo")+
						"<br>专题名称：" +studyInfo.get("studyName")+
						" 需要审批，特此提醒！";
			}
			msgContent+="<br>"+getReportContentTableStr(report.getChkReportCode());
		
			
			
			receiverList+=userService.getByRealName(report.getQam()).getUserName()+",";
			
			//map.put("msgTitle", msgTitle);
			//map.put("msgContent", msgContent);
			//map.put("receiverList", receiverList);
			emailListTemp+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
		}
		
		
		
		map.put("emailList", emailListTemp);
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public String getReportContentTableStr(String chkReportCode){
		
		String 	reportContent="<br>报告编号："+chkReportCode+" 报告内容如下：<br> " +
		"<table style='width:600px;'> " +
			"<tr>" +
				"<th style='width:120px;'>检查项 " +
				"</th>" +
				"<th style='width:240px;'>检查内容 " +
				"</th>" +
				"<th style='width:120px;'>检查结果" +
				"</th>" +
				"<th style='width:120px;'>建议 " +
				"</th>" +
			"</tr> ";
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(chkReportCode);
	
		for(QAChkReportRecord reportRecord:reportRecords)
		{
			if(reportContent.getBytes().length>3500){
				reportContent+= "<tr>" +"<td colspan='4'>...</td> </tr>"; 
				break;
			}
			reportContent+= "<tr>" +
								"<td> " +reportRecord.getChkItemName()+
								"</td>" +
								"<td> " +reportRecord.getChkContent()+
								"</td>" ;
			if(reportRecord.getChkResult().equals("×"))
			{
				reportContent+=	"<td>" +reportRecord.getChkResultDesc()	+
								"</td>" +
								"<td> " +reportRecord.getAdvice();
			}else{
				reportContent+=	"<td>" +reportRecord.getChkResult()+
								"</td>" +
								"<td> NA" ;
			}
			reportContent+=	"</td>" +
							"</tr> ";
		}
		
		reportContent+="</table>";
		
		return reportContent;
	}
	//加上回复和延迟
	public String getReportContentTableStr2(String chkReportCode){
		
		String 	reportContent="<br>报告编号："+chkReportCode+" 报告内容如下：<br> " +
		"<table style='width:860px;'> " +
			"<tr>" +
				"<th style='width:120px;'>检查项 " +
				"</th>" +
				"<th style='width:240px;'>检查内容 " +
				"</th>" +
				"<th style='width:120px;'>检查结果" +
				"</th>" +
				"<th style='width:120px;'>建议 " +
				"</th>" +
				"<th style='width:120px;'>回复 " +
				"</th>" +
				"<th style='width:70px;'>延迟状态 " +
				"</th>" +
				"<th style='width:70px;'>预计日期 " +
				"</th>" +
			"</tr> ";
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(chkReportCode);
	
		for(QAChkReportRecord reportRecord:reportRecords)
		{
			reportContent+= "<tr>" +
								"<td> " +reportRecord.getChkItemName()+
								"</td>" +
								"<td> " +reportRecord.getChkContent()+
								"</td>" ;
			if(reportRecord.getChkResult().equals("×"))
			{
				String str = "未申请";
				if(reportRecord.getNeedDelay()!=null&&reportRecord.getNeedDelay()==1)
					str = "已申请";
				reportContent+=	"<td>" +reportRecord.getChkResultDesc()	+
								"</td>" +
								"<td> " +reportRecord.getAdvice()+
								"</td>"+
								"<td> " +reportRecord.getReplyContent()+
								"</td>" +
								"<td>" + str +
								"</td>" +
								"<td>"+DateUtil.dateToString(reportRecord.getDelayPlanFinishDate(),"yyyy-MM-dd") ;
			}else{
				reportContent+=	"<td>" +reportRecord.getChkResult()+
								"</td>" +
								"<td> NA" +
								"</td>"+
								"<td>NA" +
								"</td>" +
								"<td>NA " +
								"</td>" +
								"<td>NA";
			}
			reportContent+=	"</td>" +
							"</tr> ";
		}
		
		reportContent+="</table>";
		
		return reportContent;
	}
	public void qamApprovalReport()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		String emailListTemp = "";
		
		String[] reportIds = reports.split(",");
		List<QAChkReport> reportList = qAChkReportService.getByIds(reportIds);
		//QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		for(QAChkReport report:reportList)
		{
			
			studyNoParam=qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
		
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
			
			QAApprovalOpinion opinion = new QAApprovalOpinion();
			opinion.setApprovalName(user.getRealName());
			opinion.setApprovalOpinion(approvalOpinion);
			opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
			String approvalResult = "";
			if(approvalResultFlag==1)//通过
			{
				approvalResult = "审批通过";
				opinion.setApprovalResult("通过");
				report.setQamsignTime(new Date());
				report.setRptState(2);
				map.put("rptState",2);
				//如果都符合条件则报告完成，如果有不符合的则进入SD回复状态
				boolean flag = false;
				//Set<QAChkReportRecord> reportRecords = report.getQachkReportRecords();
				List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
				for(QAChkReportRecord reportRecord:reportRecords)
				{
					if(reportRecord.getChkResult().equals("×"))
					{
						flag = true;
						report.setRptState(3);
						report.setNeedReply(1);
						map.put("rptState",3);
						break;
					}
						
				}
				
				//发给SD和FM
				//通知SD回复
				if(flag)//存在不符合项
				{
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
					
					String msgTitle=("QAM("+user.getRealName()+")，审批报告:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"　需要SD("+report.getSd()+")回复");//消息头
					String msgContent = "<br>";
					msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+"审批通过了报告，"+
					"<br>报告编号:<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a><br>专题编号:"+studyNoParam+"，<br>专题名称:"+studyNoName+"，<br>需要SD("+report.getSd()+")回复，特此提醒！";
					
					msgContent +="<br>"+ getReportContentTableStr(report.getChkReportCode());
					
					String receiverList = "";
					/*if(sd==null)
					{
						QAStudyChkIndex index = qAStudyChkIndexService.getById(studyNoParam);
						sd = index.getSd();
					}*/
					String userCode = userService.getByRealName(report.getSd()).getUserName();
					receiverList+=(userCode)+",";
					List<User> users = userService.findByPrivilegeName("FM");
					if(users!=null&&users.size()>0)
					{
						receiverList+=(users.get(0).getUserName())+",";
					}
					
					emailListTemp+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
					//map.put("msgTitle", msgTitle);
					//map.put("msgContent", msgContent);
					//map.put("receiverList", receiverList);
					
				}else {
					
					//报告结束
					report.setRptState(9);
					map.put("rptState",9);
					report.setFinalFinishTime(new Date());
					String reportName="";
					//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
					List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
					if(chkIndexs!=null&&chkIndexs.size()>0)
					{
						Integer chkType = ((QAChkIndex)(chkIndexs.toArray()[0])).getChkType();//1:研究；2：过程；3：设施；4方案；5：报告
						if(chkType==4||chkType==5)
						{
							QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
							if(chkType==4)
							{
								TblStudyFileIndex fileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);
								fileIndex.setFileState(2);
								tblStudyFileIndexService.update(fileIndex);
								reportName="方案";
								studyChkIndex.setStudyPlanState(1);//方案审批状态 0：未完成；1：已完成
								studyChkIndex.setStudyPlanTime(new Date());
								qAStudyChkIndexService.update(studyChkIndex);
							}else if(chkType==5){
								reportName="专题";
								
								TblStudyFileIndex fileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 2);
								fileIndex.setFileState(2);
								tblStudyFileIndexService.update(fileIndex);
								
								studyChkIndex.setReportState(1);//专题报告状态 （SD写的）0：未完成；1：已完成
								studyChkIndex.setReportFinishTime(new Date());
								qAStudyChkIndexService.update(studyChkIndex);
								
							}
							
						}
					}
					TblESLink link = tblESLinkService.getByEntityNameAndDataId("QAChkReport",report.getChkReportCode() );
					
					report.setFinalFinishSign(link.getLinkId());//QA提交的签字id
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
					//通知QA,SD,FM，报告通过了没有问题
					String msgTitle=("QAM("+user.getRealName()+")，审批"+reportName+"报告:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+" 没有问题，报告完成");//消息头
					String msgContent = "<br>";
					msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+"审批通过了"+reportName+"报告，"+
					"<br>报告编号:　<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a><br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>没有问题，报告完成，特此提醒！";
					
					msgContent +="<br>"+ getReportContentTableStr(report.getChkReportCode());
					
					String receiverList = "";
					
					String userCode = userService.getByRealName(report.getInspector()).getUserName();
					receiverList+=(userCode)+",";
					
				//	if(!"".equals(reportName))
					//{
						String sdCode = userService.getByRealName(report.getSd()).getUserName();
						receiverList+=(sdCode)+",";
						if(report.getReplyFmname()!=null&&!"".equals(report.getReplyFmname()))
						{
							String fmCode = userService.getByRealName(report.getReplyFmname()).getUserName();
							receiverList+=(fmCode)+",";
						}
					//}
				
					emailListTemp+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
					//map.put("msgTitle", msgTitle);
					//map.put("msgContent", msgContent);
					//map.put("receiverList", receiverList);
				}
				
			}
			if(approvalResultFlag==-1)//不通过
			{
				approvalResult = "退回";
				
				opinion.setApprovalResult("不通过");
				report.setRptState(-2);
				map.put("rptState",-2);
				
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
				//通知QA继续检查
				String msgTitle=("QAM("+user.getRealName()+")，审批报告:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"　需要回复");//消息头
				String msgContent = "<br>";
				msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+"由于"+approvalOpinion+"审批报告没有通过，"+
				"<br>报告编号:"+report.getChkReportCode()+"<br>专题编号:"+studyNoParam+"，<br>专题名称:"+studyNoName+"，<br>请修改报告，特此提醒！";
				
				msgContent += "<br>"+getReportContentTableStr(report.getChkReportCode());
				
				String receiverList = "";
				
				User qaUser = userService.getByRealName(report.getInspector());
				receiverList+=(qaUser.getUserName())+",";
				
				emailListTemp+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
				//map.put("msgTitle", msgTitle);
				//map.put("msgContent", msgContent);
				//map.put("receiverList", receiverList);
				
			}
			opinion.setApprovalResultFlag(approvalResultFlag);
			opinion.setApprovalTime(new Date());
			opinion.setApprovalType(1);//1：报告；2：回复；3：延迟整改；4：检查计划
			opinion.setObjectVersion(1);//可选：对于检查计划变更，为1、2、3.等
			opinion.setOperatorType(2);//1：FM；2：QAM；3：QA检查员
			opinion.setObjectCode(report.getChkReportCode());
			
			qAApprovalOpinionService.save(opinion);
			
			qAChkReportService.update(report);
			
			if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
					(report.getNeedReply()==null||report.getNeedReply()==0)&&//报告不需要回复
					(report.getNeedReChk()==null||report.getNeedReChk()==0)&&//报告不需要再检查
					(report.getDelayState()==null||report.getDelayState()==1)&&//报告延迟整改完成
					report.getRptState()!=9)//报告完成
			{
					map.put("canEndReport", true);
			}else {
				map.put("canEndReport", false);
			}
			if(approvalResultFlag==1)//通过
			{
				saveESLink(818,"QAM审批通过了报告","QAChkReport",report.getChkReportCode());				
			}
			if(approvalResultFlag==-1)//不通过
			{
				saveESLink(840,"QAM审批不通过报告","QAChkReport",report.getChkReportCode());	
			}
			
		}
		map.put("emailList", emailListTemp);
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void getSOPByReport()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		String[] reportIds = reports.split(",");
		//List<String> qaIndexIds = qAChkIndexService.getByReport(model.getChkReportCode(),user);
		List<String> qaIndexIds = qAChkIndexService.getByReports(reportIds,user);
		List<QAChkSop> sops = qAChkSopService.getByChkIndexIds(qaIndexIds);
		if(sops==null)
			sops = new ArrayList<QAChkSop>();
		//要不要去重呢？
		List<QAChkSop> removeList = new ArrayList<QAChkSop>();
		for(QAChkSop sop:sops)
		{
			int index = sops.indexOf(sop);
			
			for (int i = 0; i < index; i++) {
				if(sop.getFileRecordId()!=null&&sop.getFileRecordId().equals(sops.get(i).getFileRecordId()))
				{
					removeList.add(sop);
					break;
				}
			}
		}
		sops.removeAll(removeList);
		
		String[] _nory_format = {"sopRecordId", "fileRecordId","sopCode","sopName","sopVersion","sopPublishTime","sopPublishDepartment","remark"};
		String json = JsonPluginsUtil.beanListToJson(sops, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
		writeJson(json);
	}
	public void getReportRecordByReport()
	{
		//List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(model.getChkReportCode());
		String[] reportIds = reports.split(",");
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCodes(reportIds);
		
		/*
	private String chkReportRecordId;
     private QAChkReport qachkReport;
     private String studyNo;
     private String chkRecordId;
     private String chkItemName;
     private String chkContent;
     private String chkResult;
     private String remark;
     private java.util.Date chkTime;
     private String inspector;
     private String advice;
     private java.util.Date confirmTime;
     private String confirmer;
     private Integer chkResultFlag;
     private String replyContent;
     private String replyRemark;
     private Integer reChkFlag;
     private Integer reChkResult;
     private String reChkInspector;
     private java.util.Date reChkTime;
     private Integer needDelay;
     private String delayDesc;
     private String delayRsn;
     private java.util.Date delayPlanFinishDate;
     private String delaySd;
     private java.util.Date delayFinishTime;
     private java.util.Date delayQaconfirmTime;
     private String delayQainspector;
		 */
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(QAChkReportRecord record:reportRecords)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chkReportCode", record.getQachkReport().getChkReportCode());
			map.put("chkReportRecordId", record.getChkReportRecordId());
			map.put("chkItemName", record.getChkItemName());
			map.put("chkContent", record.getChkContent());
			map.put("chkResult", record.getChkResult());
			map.put("chkResultDesc", record.getChkResultDesc());
			map.put("chkResultFlag", record.getChkResultFlag());
			map.put("replyContent", record.getReplyContent());
			map.put("reChkResult", record.getReChkResult());
			map.put("needDelay", record.getNeedDelay());
			//map.put("delayState", record.getd);
			map.put("delayDesc", record.getDelayDesc());
			map.put("delayRsn", record.getDelayRsn());
			map.put("delayPlanFinishDate",  DateUtil.dateToString(record.getDelayPlanFinishDate(),"yyyy-MM-dd"));
			map.put("delayFinishTime", DateUtil.dateToString(record.getDelayFinishTime(),"yyyy-MM-dd"));
			map.put("chkTime", record.getChkTime());
			map.put("inspector", record.getInspector());
			map.put("advice", record.getAdvice());
			
			mapList.add(map);
		}
		//String[] _nory_format = {"chkReportRecordId", "chkItemName","chkContent","chkResult","chkResultFlag","replyContent"
		//		,"reChkResult","needDelay","delayState","delayDesc","delayRsn",
		//		"delayPlanFinishDate","delayFinishTime","chkTime","inspector","advice"};
		//String json = JsonPluginsUtil.beanListToJson(reportRecords, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	}

	public void getReChkRecordByReport()
	{

		//List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getReChkByReportCode(model.getChkReportCode());
		String[] reportIds = reports.split(",");
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getReChkByReportCodes(reportIds);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for(QAChkReportRecord record:reportRecords)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			//"chkReportRecordId", "chkItemName","chkContent","chkResult","chkResultFlag","chkTime","inspector","advice","replyContent"
			//,"needDelay","delayState","needReply","replyState","delayRsn","delayPlanFinishDate","delayFinishTime",
			//"reChkResult","reChkFlag","remark"};
			map.put("chkReportCode", record.getQachkReport().getChkReportCode());
			map.put("reportReplyState",record.getQachkReport().getReplyState() );
			map.put("reportNeedDelay",record.getQachkReport().getNeedDelay() );
			
			map.put("chkReportRecordId", record.getChkReportRecordId());
			map.put("chkItemName", record.getChkItemName());
			map.put("chkContent", record.getChkContent());
			map.put("chkResult", record.getChkResult());
			map.put("chkResultDesc", record.getChkResultDesc());
			map.put("chkResultFlag", record.getChkResultFlag());
			map.put("chkTime", record.getChkTime());
			map.put("inspector", record.getInspector());
			map.put("advice", record.getAdvice());
			map.put("replyContent", record.getReplyContent());
			map.put("needDelay", record.getNeedDelay());
			map.put("delayRsn", record.getDelayRsn());
			map.put("delayPlanFinishDate", DateUtil.dateToString(record.getDelayPlanFinishDate(),"yyyy-MM-dd"));
			
			map.put("delayFinishTime", DateUtil.dateToString(record.getDelayFinishTime(),"yyyy-MM-dd"));
			map.put("reChkResult", record.getReChkResult());
			map.put("reChkFlag", record.getReChkFlag());
			
			List<QAReChkRecord> reChkRecords = qAReChkRecordService.getByReportRecord(record.getChkReportRecordId());
			if(reChkRecords!=null&&reChkRecords.size()>0)//如果存在还没有签字的再捡查记录，就显示没检查的再捡查记录，如果不存在则显示记录中自己的
			{
				for(QAReChkRecord reChkRecord:reChkRecords)
				{
					if(reChkRecord.getReChkType()==1)//回复
					{
						map.put("reChkResultTemp", reChkRecord.getReChkResult());
						map.put("reChkFlagTemp", reChkRecord.getReChkRecord());	
					}else if(reChkRecord.getReChkType()==2){
						if(reChkRecord.getReChkRecord().length()>10)
							map.put("delayFinishTimeTemp", reChkRecord.getReChkRecord().substring(0, 10));
						
					}
				}
			
			}
			{
				if(map.get("delayFinishTimeTemp")==null)
				{
					map.put("delayFinishTimeTemp", DateUtil.dateToString(record.getDelayFinishTime(),"yyyy-MM-dd"));
				}
				if(map.get("reChkResultTemp")==null)	
					map.put("reChkResultTemp", record.getReChkResult());
				if(map.get("reChkFlagTemp")==null)
					map.put("reChkFlagTemp", record.getReChkFlag());
			}
			map.put("remark", record.getRemark());
				
			
		
			mapList.add(map);
		}
		
		
		//String[] _nory_format = {"chkReportRecordId", "chkItemName","chkContent","chkResult","chkResultFlag","chkTime","inspector","advice","replyContent"
		//		,"needDelay","delayState","needReply","replyState","delayRsn","delayPlanFinishDate","delayFinishTime","reChkResult","reChkFlag","remark"};
		//String json = JsonPluginsUtil.beanListToJson(reportRecords, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
	
	}
	
	//qa确认接收回复或者整改
	public void confirmDelayOrReply()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String emailList = "";
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		//confirmReportType;//0回复，1整改2两者
		List<QAChkReport> reportList = qAChkReportService.getByIds(reports.split(","));
		
		//QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		for(QAChkReport report:reportList)
		{
			//map.put("replyState", report.getReplyState());
			//map.put("delayState", report.getDelayState());
			String receiveType = "";
			if(confirmReportType==0||confirmReportType==2)
			{
				receiveType="回复";
				if(report.getReplyState()!=null&&
						(report.getReplyState()==-1||report.getReplyState()==2))
				{
					report.setReplyInspectorReceiveTime(new Date());
					report.setReplyInspectorReciveName(user.getRealName());
					report.setReplyState(3);//qa接收
					if(model.getNeedReChk()==1)
					{
						report.setRptState(4);//进入再检查
					}
					report.setNeedReChk(model.getNeedReChk());
				//	map.put("replyState", report.getReplyState());
					//map.put("needReChk", report.getNeedReChk());
				}
				
			}
			if(confirmReportType==1||confirmReportType==2)
			{
				receiveType="延迟整改";
				if(confirmReportType==2)
				{
					receiveType="回复和延迟整改";
				}
				if(report.getNeedDelay()!=null&&
						(report.getNeedDelay()==-1||report.getNeedDelay()==2))
				{
					report.setDelayQaureceiveTime(new Date());
					report.setDelayQaureceiveName(user.getRealName());
					report.setNeedDelay(3);
					if(model.getNeedReChk()==1)
					{
						report.setRptState(5);//进入延迟整改
					}else{
						report.setDelayState(1);//如果不需要再检查则延迟整改完成
					}
					//map.put("needDelay", report.getNeedDelay());
				}
			}
			
			
			if(model.getNeedReChk()==1)
			{
				
				//报告需要再检查，把不符合的项都设置成需要再检查
				List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
				for(QAChkReportRecord record:records)
				{
					if(confirmReportType==0||confirmReportType==2)//回复
					{
						if(record.getChkResultFlag()==-1)//不符合项
						{
							record.setReChkFlag(1);//需要再检查
						}
					}
					if(confirmReportType==1||confirmReportType==2)//延迟整改
					{
						if(record.getChkResultFlag()==-1
								&&record.getDelayPlanFinishDate()!=null&&record.getDelayFinishTime()==null)//还没有处理的延迟整改
						{
							record.setNeedDelay(1);
						}
					}
				}
			}else {
				if(confirmReportType==1||confirmReportType==2)//延迟整改
				{
					report.setDelayState(1);//不需要再检查的话，延迟整改就结束了。
				}
				if(confirmReportType==0||confirmReportType==2)//回复
				{
					//report.setNeedReply(0);
				}
				//	report.setRptState(9);//完成
				//查看是否已经完成了
				if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
						(report.getNeedReply()==null||report.getNeedReply()==0//不需要回复
								||(report.getNeedReply()==1&&report.getReplyState()==3))&&//回复已经被qa处理
						(report.getNeedReChk()==null||report.getNeedReChk()==0||
								(report.getNeedReChk()==1&&report.getReChkState()==1))&&//报告不需要再检查
						(report.getNeedDelay()==null||report.getNeedDelay()==0|| //未申请
								(report.getNeedDelay()==3&&report.getDelayState()==1))&&//报告延迟整改完成
						report.getRptState()!=9)//报告完成
				{
					report.setRptState(9);
					map.put("rptState",9);
					report.setFinalFinishTime(new Date());
					String reportName="";
					//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
					List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
					
					String studyNoParam="";
					//if(report.getQachkIndexes().toArray()!=null)
					//{
					//	QAChkIndex indexs = (QAChkIndex)report.getQachkIndexes().toArray()[0];
					//	studyNoParam = indexs.getStudyNo();
					//}
					studyNoParam = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
					if(chkIndexs!=null&&chkIndexs.size()>0)
					{
						Integer chkType = ((QAChkIndex)(chkIndexs.toArray()[0])).getChkType();//1:研究；2：过程；3：设施；4方案；5：报告
						if(chkType==4||chkType==5)
						{
							QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
							if(chkType==4)
							{
								reportName="方案";
								TblStudyFileIndex fileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 1);
								fileIndex.setFileState(2);
								tblStudyFileIndexService.update(fileIndex);
								
								studyChkIndex.setStudyPlanState(1);//方案审批状态 0：未完成；1：已完成
								studyChkIndex.setStudyPlanTime(new Date());
								qAStudyChkIndexService.update(studyChkIndex);
							}else if(chkType==5){
								reportName="专题";
								TblStudyFileIndex fileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(studyNoParam, 2);
								fileIndex.setFileState(2);
								tblStudyFileIndexService.update(fileIndex);
								studyChkIndex.setReportState(1);//专题报告状态 （SD写的）0：未完成；1：已完成
								studyChkIndex.setReportFinishTime(new Date());
								qAStudyChkIndexService.update(studyChkIndex);
								
							}
							
						}
					}
					
					
					TblESLink link = tblESLinkService.getByEntityNameAndDataId("QAChkReport",report.getChkReportCode() );
					
					report.setFinalFinishSign(link.getLinkId());//QA提交的签字id
					map.put("reportFinish",true);
					
					//通知QAM，SD，FM，报告通过了没有问题
					String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
					
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
					
					String msgTitle=("QA("+user.getRealName()+")，确认接收"+reportName+"报告的"+receiveType+",不需要再检查，报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+" 报告完成");//消息头
					String msgContent = "<br>";
					msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"确认接收"+reportName+"报告的"+receiveType+",<br>不需要再检查，"+
					"<br>报告编号:　<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a> <br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+" <br>报告完成，特此提醒！";
					
					msgContent+="<br>"+ getReportContentTableStr2(report.getChkReportCode());
					
					String receiverList = "";
					
					String userCode = userService.getByRealName(report.getInspector()).getUserName();
					receiverList+=(userCode)+",";
					//if(!"".equals(reportName))
					//{
						String sdCode = userService.getByRealName(report.getSd()).getUserName();
						receiverList+=(sdCode)+",";
						String fmCode = userService.getByRealName(report.getReplyFmname()).getUserName();
						receiverList+=(fmCode)+",";
					//}
					emailList+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
					//map.put("msgTitle", msgTitle);
				//	map.put("msgContent", msgContent);
					//map.put("receiverList", receiverList);
				}
				
				map.put("rptState", report.getRptState());
			}
			map.put("emailList", emailList);
			qAChkReportService.update(report);
			
			saveESLink(819,"QA接收报告"+receiveType+"","QAChkReport",report.getChkReportCode());
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void saveReChk()//confirmAll的方法，----无效
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		boolean flag = true;
		boolean reChkFlag = false;
		//Set<QAChkReportRecord> reportRecords = report.getQachkReportRecords();
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
		
		for(QAChkReportRecord record:reportRecords)
		{
			if(record.getChkResultFlag()!=null&&record.getChkResultFlag()==-1)
			{
				if(record.getReChkFlag()!=null&&record.getReChkFlag()==1)
				{
					reChkFlag = true;//有需要重新检查的报告记录
				}
				if(record.getReChkFlag()!=null&&record.getReChkFlag()==1&&
						record.getReChkResult()!=null&&record.getReChkResult()==-1)//检查结果为不符合，需要重新检查并且问题未解决
				{
					flag = false;
					break;
				}
			}
		}
		
		//问题都已经解决了。
		if(!reChkFlag)
		{
			//report.setNeedReChk(1);//0：不需要；1：需要
			
			if(flag)
			{
				report.setReChkState(1);//0：问题未解决；1：问题已解决
			} else {
				report.setReChkState(0);//0：问题未解决；1：问题已解决
			}
			
		}else {
			//report.setNeedReChk(0);
			report.setRptState(0);
		}
		
		qAChkReportService.update(report);
		map.put("rptState", report.getRptState());
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void saveESLink(Integer esType,String typeDesc,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(typeDesc);
		es.setEsType(esType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(esType);
		esLink.setEsTypeDesc(typeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
	}
	public void confirmFinishReport()//单个报告的完成签字，----无效
	{
		saveESLink(816,"报告完成签字","QAChkReport",model.getChkReportCode());
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		report.setRptState(9);
		report.setFinalFinishSign(user.getRealName());
		report.setFinalFinishTime(new Date());
		qAChkReportService.update(report);
		
	}
	public void isAllReportFinish()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean flag = true;
		List<QAChkIndex> chkIndexs = qAChkIndexService.getByStudyNo(studyNoParam);
		if(chkIndexs!=null&&chkIndexs.size()>0)
		{
			for(QAChkIndex chkIndex:chkIndexs)
			{
				QAChkReport report = chkIndex.getQachkReport();
				if(report!=null&&report.getRptState()!=9)
				{
					flag = false;
				}
			}
		
			if(flag)
			{
				map.put("success", true);
				
			}else {
				map.put("success", false);
				map.put("msg", "该专题目前存在报告没有完成的情况，请确保所有报告都已经完成");
			}
			
		}else {
			map.put("success", false);
			map.put("msg", "该专题目前还没有任何检查记录，请确认！");	
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void confirmStudyFinishReport()
	{
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		studyChkIndex.setChkPlanFinishTime(new Date());
		studyChkIndex.setChkPlanFinishConfirmer(user.getRealName());
		studyChkIndex.setChkPlanFinishFlag(1);//0：未完成；1：完成
		
		qAStudyChkIndexService.update(studyChkIndex);
		
	}

	public void getQATemple()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String tiCode = tblStudyItemService.getTiCodeByStudyNo(studyNoParam);
		List<DictQAStatementTemple> temples = dictQAStatementTempleService.getByTiCode(tiCode);
		if(temples==null||temples.size()==0)
		{
			String str = "";
			if("01".equals(tiCode))
			{
				str="医药";
			}else if("02".equals(tiCode))
			{
				str="农药";
			}else if("03".equals(tiCode))
			{
				str="化学品";
			}
			map.put("msg", str+"相对应的QA声明模板不存在，请相关负责人设置模板！");
		}else {
			if(temples.size()==1)
			{
				map.put("only", true);
				map.put("templeId", temples.get(0).getTempleId());	
			}else {
				map.put("many", true);
				map.put("rows", temples);
			}
		}
		
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	/**转到报告回复显示页面*/
	public String ireport() throws Exception
	{
		
		ActionContext.getContext().getSession().put("studyNoParam", studyNoParam);
		//+'&printContent=report'/study
		ActionContext.getContext().getSession().put("chkReportCode",model.getChkReportCode());
		
		ActionContext.getContext().put("QAMainPage", QAMainPage);
		ActionContext.getContext().put("studyNoParam", studyNoParam);
		ActionContext.getContext().put("reportStartDate", reportStartDate);
		ActionContext.getContext().put("reportEndDate", reportEndDate);
		ActionContext.getContext().put("reportStatus", reportStatus);
		ActionContext.getContext().put("reportCatalog", reportCatalog);
		ActionContext.getContext().put("reportSearcher", reportSearcher);
		
		ActionContext.getContext().getSession().put("printContent",printContent);
		ActionContext.getContext().getSession().put("templeId",templeId);
		return "ireport";
	}
	
	/** out 打印回复信息*/
	public String outport() throws Exception{
		if("study".equals(printContent))//出具QA声明
		{
			//参数
			Map<String,Object> paraMap = new HashMap<String, Object>();
			String location = "/jasperReport/QAQualityReport.jasper";//replyReport
			String reportName = "质量保证声明";
			
			URL url = ServletActionContext.getServletContext().getResource("/jasperReport/"+"logo.jpg");
			paraMap.put("logoImage", url);
			
			if(templeId!=null)
			{
				DictQAStatementTemple temple = dictQAStatementTempleService.getById(templeId);
				paraMap.put("templeContent", temple.getTempleContent());
			}
			
			List<QAChkIndex> chkIndexs = qAChkIndexService.getByStudyNo(studyNoParam);
			List<QAChkReport> reports = new ArrayList<QAChkReport>();
			for(QAChkIndex chkIndex:chkIndexs)
			{
				if(chkIndex.getQachkReport()!=null&&!reports.contains(chkIndex.getQachkReport()))
				{
					reports.add(chkIndex.getQachkReport());
				}
				
			}
			
			//paraMap.put("reportCode", report.getChkReportCode());
			paraMap.put("reportName", reportName);
			paraMap.put("studyNo", studyNoParam);
			
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
			
			paraMap.put("studyName",studyNoName);
			
			/*TblStudyPlan studyPlan = tblStudyPlanService.getByStudyNo(studyNoParam);
			if(studyPlan!=null)
				paraMap.put("studyName",studyPlan.getStudyName() );
			else {
				paraMap.put("studyName","" );			
			}*/
			
			//paraMap.put("qam", report.getQam());
			//paraMap.put("delayDate", report.getDelayFmapprovalTime());
			//结果集
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			
			for (QAChkReport report:reports) {
				/*
				List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
				for(QAChkReportRecord record:reportRecords)
				{
					if(record.getChkResultFlag()!=null&&record.getChkResultFlag()==-1)
					{
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("chkItemName", record.getChkItemName());
						map.put("chkContent", record.getChkContent());
						map.put("chkResult", record.getChkResult());
						map.put("advice", record.getAdvice());
						map.put("replyContent", record.getReplyContent());
						
						map.put("needDelay",record.getNeedDelay() );
						map.put("delayRsn", record.getDelayRsn());
						map.put("delayPlanFinishDate", record.getDelayPlanFinishDate());
						
						map.put("chkTime", DateUtil.dateToString(record.getChkTime(),"yyyy-MM-dd"));
						map.put("reChkTime",  DateUtil.dateToString(record.getReChkTime(),"yyyy-MM-dd"));
						//0：未检查；-1：问题未解决；1：问题已解决
						String reChkResult = "";
						if(record.getReChkResult()==null||record.getReChkResult()==0)
						{
							reChkResult = "未检查";
						}else if(record.getReChkResult()==-1){
							reChkResult = "问题未解决";
						}else if(record.getReChkResult()==1){
							reChkResult = "问题已解决";
						}
						map.put("reChkResult", reChkResult);
						
						map.put("reportDate", DateUtil.dateToString(report.getCreateTime(),"yyyy-MM-dd"));
						
						mapList.add(map);
					}
				}*/
				//Set<QAChkIndex> indexs = report.getQachkIndexes();
				List<QAChkIndex> indexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
				for(QAChkIndex chkindex:indexs)
				{
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("chkItemName", chkindex.getChkItemName());
					
					map.put("chkTime", DateUtil.dateToString(chkindex.getChkFinishTime(),"yyyy-MM-dd"));
					//approvalType 1：报告；2：回复；3：延迟整改；4：检查计划
					//resultFlag 1：过；-1：未过
					//1：FM；2：QAM；3：QA检查员
					QAApprovalOpinion qAApprovalOpinion=qAApprovalOpinionService.getByTypeAndReportAndResultAndOperator(1, report.getChkReportCode(), 1, 2);
					map.put("reportDate", DateUtil.dateToString(qAApprovalOpinion.getApprovalTime(),"yyyy-MM-dd"));
					
					mapList.add(map);
				}
				
			}
			
			String fileName = "出具QA声明";
			ReportMap.getInstance(request).addCompanyInfoIntoMap(paraMap);
			ActionContext.getContext().put("paraMap", paraMap);
			ActionContext.getContext().put("mapList", mapList);
			ActionContext.getContext().put("fileName", fileName);
			ActionContext.getContext().put("location",location);
		}else if("report".equals(printContent)){
			//参数
			Map<String,Object> paraMap = new HashMap<String, Object>();
			String location = "/jasperReport/StudyChkReport.jasper";//replyReport
			
			URL url = ServletActionContext.getServletContext().getResource("/jasperReport/"+"logo.jpg");
			paraMap.put("logoImage", url);
			URL checkUrl = ServletActionContext.getServletContext().getResource("/jasperReport/"+"right.jpg");
			paraMap.put("checkImage", checkUrl);
			
			QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
			//String studyNo = ((QAChkIndex)(report.getQachkIndexes().toArray()[0])).getStudyNo();
			String studyNo = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());

			paraMap.put("reportCode", model.getChkReportCode());
			paraMap.put("studyNo", studyNo);
			
			paraMap.put("qam", report.getQam());
			
			paraMap.put("send", report.getSd());
			paraMap.put("cc", report.getDelayFmname());
			
			String chkTables = "";
			Integer reportType = 1;
			//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
			List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
			for(QAChkIndex chkIndex:chkIndexs)
			{
				reportType = chkIndex.getChkType();
			//	Set<QAChkTblReg> chkTblRegs = chkIndex.getQachkTblRegs();
				List<QAChkTblReg> chkTblRegs = qAChkTblRegService.getByChkIndexId(chkIndex.getChkIndexId());
				for(QAChkTblReg chkTblReg:chkTblRegs)
				{
					if(!"".equals(chkTables))
						chkTables+=",";
					chkTables+=chkTblReg.getChkTblName();
				}
			}
			paraMap.put("chkTables", chkTables);
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			
				List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
				
				for(QAChkReportRecord record:reportRecords)
				{
					//方案或者报告的检查报告中，√和NA的不列，加一句格式符合。1:研究；2：过程；3：设施；4方案；5：报告
					if((reportType!=4&&reportType!=5)||(record.getChkResultFlag()!=null&&record.getChkResultFlag()==-1))
					{
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("chkItemName", record.getChkItemName());
						map.put("chkContent", record.getChkContent());
						if("×".equals(record.getChkResult())){//错的时候写发现问题
							map.put("chkResult", record.getChkResultDesc());
							map.put("advice", record.getAdvice());
						}else {
							
							map.put("chkResult", record.getChkResult());	
							map.put("advice", "NA");
						}
						
						map.put("replyContent", record.getReplyContent());
						
						map.put("needDelay",record.getNeedDelay() );
						map.put("delayRsn", record.getDelayRsn());
						map.put("delayPlanFinishDate", record.getDelayPlanFinishDate());
						
						map.put("chkTime", DateUtil.dateToString(record.getChkTime(),"yyyy-MM-dd"));
						map.put("reChkTime", DateUtil.dateToString(record.getReChkTime(),"yyyy-MM-dd"));
						//0：未检查；-1：问题未解决；1：问题已解决
						String reChkResult = "";
						if( record.getChkResultFlag()!=-1)
						{
							reChkResult="NA";
						}else if(record.getReChkResult()==null||record.getReChkResult()==0){
							reChkResult = "未检查";
						}else if(record.getReChkResult()==-1){
							reChkResult = "问题未解决";
						}else if(record.getReChkResult()==1){
							reChkResult = "问题已解决";
						}
						map.put("reChkResult", reChkResult);
						
						QAApprovalOpinion qAApprovalOpinion=qAApprovalOpinionService.getByTypeAndReportAndResultAndOperator(1, report.getChkReportCode(), 1, 2);
						map.put("reportDate", DateUtil.dateToString(qAApprovalOpinion.getApprovalTime(),"yyyy-MM-dd"));
						
					//	map.put("reportDate", DateUtil.dateToString(report.getCreateTime(),"yyyy-MM-dd"));
						
						mapList.add(map);
					}
				}
				if(reportType==4||reportType==5){
					paraMap.put("reportSumWord", "格式符合要求");
				}else {
					paraMap.put("reportSumWord", "");
				}
				
				paraMap.put("chkTables", chkTables);
			
			String fileName = "QAU检查报告";
			ReportMap.getInstance(request).addCompanyInfoIntoMap(paraMap);
			ActionContext.getContext().put("paraMap", paraMap);
			ActionContext.getContext().put("mapList", mapList);
			ActionContext.getContext().put("fileName", fileName);
			ActionContext.getContext().put("location",location);
			
		}
			
		return "outport";
	}
	
	public void sendNotificationList()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(emailList!=null)
		{
			String emailLists[] = emailList.split("@");
		
			try {
				for (String str1:emailLists) {
					msgTitle = str1.split("~")[0];
					msgContent = str1.split("~")[1];
					receiverList = str1.split("~")[2];
					if(receiverList!=null&&receiverList.length()>1)
					{
						receiverList = receiverList.substring(0,receiverList.length()-1);
					}
					
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
					TblNotification tblNotification = new TblNotification();
					tblNotification.setId(tblNotificationService.getKey("TblNotification"));
					
					tblNotification.setMsgTitle(msgTitle);//消息头
					tblNotification.setMsgType(1);//系统消息
					tblNotification.setSender(getCurrentRealName());// 发送者
					tblNotification.setSendTime(new Date());// 发送时间
					if(msgContent.length()<4000)
					{
						tblNotification.setMsgContent(msgContent);
						tblNotificationService.save(tblNotification,receList);
						map.put("success", true);
					}else{
						map.put("success", false);
						map.put("msg","邮件内容太大，发送邮件失败！");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("msg","发送邮件过程中出现异常"+e.getMessage());
			}
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
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
	
	public QAChkReportService getqAChkReportService() {
		return qAChkReportService;
	}

	public void setqAChkReportService(QAChkReportService qAChkReportService) {
		this.qAChkReportService = qAChkReportService;
	}
	
	public String getStudyNoParam() {
		return studyNoParam;
	}

	public void setStudyNoParam(String studyNoParam) {
		this.studyNoParam = studyNoParam;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public QAChkRecordService getqAChkRecordService() {
		return qAChkRecordService;
	}

	public void setqAChkRecordService(QAChkRecordService qAChkRecordService) {
		this.qAChkRecordService = qAChkRecordService;
	}

	public QAChkReportRecordService getqAChkReportRecordService() {
		return qAChkReportRecordService;
	}

	public void setqAChkReportRecordService(
			QAChkReportRecordService qAChkReportRecordService) {
		this.qAChkReportRecordService = qAChkReportRecordService;
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

	public QAChkIndexService getqAChkIndexService() {
		return qAChkIndexService;
	}

	public void setqAChkIndexService(QAChkIndexService qAChkIndexService) {
		this.qAChkIndexService = qAChkIndexService;
	}

	public QAChkSopService getqAChkSopService() {
		return qAChkSopService;
	}

	public void setqAChkSopService(QAChkSopService qAChkSopService) {
		this.qAChkSopService = qAChkSopService;
	}

	public TblStudyItemService getTblStudyItemService() {
		return tblStudyItemService;
	}

	public void setTblStudyItemService(TblStudyItemService tblStudyItemService) {
		this.tblStudyItemService = tblStudyItemService;
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

	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}

	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public Integer getConfirmReportType() {
		return confirmReportType;
	}
	public void setConfirmReportType(Integer confirmReportType) {
		this.confirmReportType = confirmReportType;
	}

	public String getQAMainPage() {
		return QAMainPage;
	}

	public void setQAMainPage(String qAMainPage) {
		QAMainPage = qAMainPage;
	}

	public Date getReportStartDate() {
		return reportStartDate;
	}

	public void setReportStartDate(Date reportStartDate) {
		this.reportStartDate = reportStartDate;
	}

	public Date getReportEndDate() {
		return reportEndDate;
	}

	public void setReportEndDate(Date reportEndDate) {
		this.reportEndDate = reportEndDate;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getReportCatalog() {
		return reportCatalog;
	}

	public void setReportCatalog(Integer reportCatalog) {
		this.reportCatalog = reportCatalog;
	}

	public String getReportSearcher() {
		return reportSearcher;
	}

	public void setReportSearcher(String reportSearcher) {
		this.reportSearcher = reportSearcher;
	}

	public Integer getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public QAApprovalOpinionService getqAApprovalOpinionService() {
		return qAApprovalOpinionService;
	}

	public void setqAApprovalOpinionService(
			QAApprovalOpinionService qAApprovalOpinionService) {
		this.qAApprovalOpinionService = qAApprovalOpinionService;
	}

	public Integer getApprovalResultFlag() {
		return approvalResultFlag;
	}

	public void setApprovalResultFlag(Integer approvalResultFlag) {
		this.approvalResultFlag = approvalResultFlag;
	}

	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}

	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}

	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}

	public Integer getAddOrEdit() {
		return addOrEdit;
	}

	public void setAddOrEdit(Integer addOrEdit) {
		this.addOrEdit = addOrEdit;
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

	public String getPrintContent() {
		return printContent;
	}

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	public DictQAStatementTempleService getDictQAStatementTempleService() {
		return dictQAStatementTempleService;
	}

	public void setDictQAStatementTempleService(
			DictQAStatementTempleService dictQAStatementTempleService) {
		this.dictQAStatementTempleService = dictQAStatementTempleService;
	}

	public String getTempleId() {
		return templeId;
	}

	public void setTempleId(String templeId) {
		this.templeId = templeId;
	}

	public String getSelectChkReportCode() {
		return selectChkReportCode;
	}

	public void setSelectChkReportCode(String selectChkReportCode) {
		this.selectChkReportCode = selectChkReportCode;
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

	public String getReports() {
		return reports;
	}

	public void setReports(String reports) {
		this.reports = reports;
	}

	public String getEmailList() {
		return emailList;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public Integer getChkType() {
		return chkType;
	}

	public void setChkType(Integer chkType) {
		this.chkType = chkType;
	}

	

}
