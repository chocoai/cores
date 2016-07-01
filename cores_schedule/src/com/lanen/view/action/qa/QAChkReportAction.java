package com.lanen.view.action.qa;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.model.qa.QAChkSop;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAApprovalOpinionService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAChkSopService;
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
	private QAChkReportRecordService qAChkReportRecordService;
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
	private QAApprovalOpinionService qAApprovalOpinionService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	
	private String studyNoParam;
	private String records;
	
	private Integer time;//年份
	private Integer status;// 0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成
	private String searchCondition;
	
	private String printContent;//reply or delay
	
	private Date startDate;//查询消息的开始日期
	private Date endDate;//查询消息的结束日期
	
	private String delayFmreveiwRemark;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public String list()
	{
		ActionContext.getContext().getSession().put("studyNoParam", studyNoParam);
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean  falg = userService.checkPrivilege(user, "FM");
		boolean  falg1 = userService.checkPrivilege(user, "SD");
		boolean  falg2 = userService.checkPrivilege(user, "QA负责人");
		boolean  falg3 = userService.checkPrivilege(user, "QA");
		boolean  falg4 = userService.checkPrivilege(user, "病理负责人");
		boolean  falg5 = userService.checkPrivilege(user, "病理");
		if(falg){
			ActionContext.getContext().put("role", "FM");
		}else if(falg1){
			ActionContext.getContext().put("role", "SD");
		}else if(falg2){
			ActionContext.getContext().put("role", "QALead");
		}else if(falg3){
			ActionContext.getContext().put("role", "QA");
		}else if(falg4){
			ActionContext.getContext().put("role", "PathSDLead");
		}else if(falg5){
			ActionContext.getContext().put("role", "PathSD");
		}
        boolean falg6  = userService.checkPrivilege(user, "部门负责人");
		ActionContext.getContext().put("department", falg6);
		
		ActionContext.getContext().put("time",time);
		ActionContext.getContext().put("status",status);
		ActionContext.getContext().put("searchCondition",searchCondition);
		ActionContext.getContext().put("chkReportCode",model.getChkReportCode());
		
		Object s = ActionContext.getContext().getSession().get("selectReportCode");
		if(s!=null&&!"".equals(s)){
			ActionContext.getContext().put("chkReportCode",s );
			ActionContext.getContext().getSession().put("selectReportCode", "");
		}
		
		return "list";
	}
	public String newestList()
	{
		endDate = DateUtil.getTodayDate();
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(endDate);
		calendar1.add(Calendar.DATE, -6);
		startDate = calendar1.getTime();
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		
		return "newestList";
	}

	public void getByTimeStatusAndCondition()
	{
		/*searchCondition	
		status	0
		time 2015*/
		
		if(time==null||time==0)
		{
			List<Integer> years = qAChkReportService.getYears();
			time = years.get(years.size()-1);
			
		}
		if(status==null)
		{
			status=10;//默认选择全部
		}
		List<Map<String, Object>> mapList=new ArrayList<Map<String,Object>>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		boolean isFM = userService.checkPrivilege(user, "FM");
		List<QAChkReport> reports = null;
		if(isFM)
		{
			reports = qAChkReportService.getByTimeStatusAndCondition(time,status,searchCondition,null);
		}else {
			reports = qAChkReportService.getByTimeStatusAndCondition(time,status,searchCondition,user.getRealName());
		}
		
		for(QAChkReport report:reports)
		{
			if(!user.getRealName().equals(report.getInspector())&&(report.getRptState()==null||report.getRptState()==0))
			{
				continue;
			}
			//String[] _nory_format={"chkReportCode","qam","createTime","rptState"};
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chkReportCode", report.getChkReportCode());
			//Set<QAChkIndex> indexs = report.getQachkIndexes();
			List<QAChkIndex> indexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
			map.put("catalog", "研究");//如果不是不是方案或者变更（方案或者变更是没有检查计划的），直接返回研究
			if(indexs!=null&&indexs.size()>0)
			{
				QAChkIndex index = (QAChkIndex)(indexs.toArray()[0]);
				String studyNo = index.getStudyNo();
				Integer chkType = index.getChkType();
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
				map.put("studyNo", studyNo);
				map.put("sd", report.getSd());
				if(report.getDelayFmname()!=null)
					map.put("fm", report.getDelayFmname());
				else if(report.getReplyFmname()!=null)
					map.put("fm", report.getReplyFmname());
				
			}
			map.put("createTime",DateUtil.dateToString(report.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
			map.put("rptState", report.getRptState());
			map.put("qa", report.getInspector());
			map.put("qam",report.getQam() );
			map.put("needReply", report.getNeedReply());
			
			if(user.getRealName().equals(report.getQam())||user.getRealName().equals(report.getInspector())
					||user.getRealName().equals(report.getSd()))
			{
				map.put("haveRight", true);
			}else {
				map.put("haveRight", false);
			}
			
			//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
			if(report.getNeedDelay()!=null)	
			{
				String delayState = "";
				switch (report.getNeedDelay()) {
					case 0:
						delayState="未申请";
						break;
					case 1:
						delayState="提交FM";
						break;
					case -1:
						delayState="FM有意见";
						break;
					case 2:
						delayState="提交QA";
						break;
					case 3:
						delayState="QA确认接收";
						break;
					
				}
				
				map.put("needDelay", delayState);
			}else {
				map.put("needDelay","无延迟整改信息");
			}
			//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
			if(report.getReplyState()!=null)
			{
				String replyState="";
				switch (report.getReplyState()) {
				case 0:
					replyState="草稿";
					break;
				case 1:
					replyState="提交FM";
					break;
				case -1:
					replyState="FM有意见";
					break;
				case 2:
					replyState="提交QA";
					break;
				case 3:
					replyState="QA确认接收";
					break;
	
				default:
					break;
				}
				map.put("replyState", replyState);
			}else
			{
				map.put("replyState", "无回复信息");
			}
		
			
			
			mapList.add(map);
				
		}
			
			
			String json = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(json);
		
	}
	public void isReplyFinish()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		//Set<QAChkReportRecord> reportRecords = report.getQachkReportRecords();
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
		boolean flag = true;
		if(reportRecords!=null&&reportRecords.size()>0)
		{
			for(QAChkReportRecord reportRecord:reportRecords)
			{
				if(reportRecord.getChkResultFlag()==-1&&(reportRecord.getReplyContent()==null||"".equals(reportRecord.getReplyContent())))
				{
					flag = false;
					break;
				}
			}
		}
		map.put("isFinish", flag);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
		
	}
	
	public String getReportContentTableStr(String chkReportCode){
		
		String 	reportContent="<br>报告编号："+chkReportCode+" 报告内容如下：<br> " +
		"<table style='width:720px;'> " +
			"<tr>" +
				"<th style='width:120px;'>检查项 " +
				"</th>" +
				"<th style='width:240px;'>检查内容 " +
				"</th>" +
				"<th style='width:120px;'>检查结果" +
				"</th>" +
				"<th style='width:120px;'>建议 " +
				"</th>" +
				"<th style='width:120px;'>回复" +
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
				reportContent+=	"<td>" +reportRecord.getChkResultDesc()	+
								"</td>" +
								"<td> " +reportRecord.getAdvice()+
								"</td>" +
								"<td> " +reportRecord.getReplyContent();
			}else{
				reportContent+=	"<td>" +reportRecord.getChkResult()+
								"</td>" +
								"<td> NA" +
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
			
			if(reportContent.getBytes().length>3500){
				reportContent+= "<tr>" +"<td colspan='7'>...</td> </tr>"; 
				break;
			}
			
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
	//回复
	public void commitReplyToFM()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		//report.setReplyState(1);//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
		//检查报告不经过FM审批SD回复了
		report.setReplyState(2);
		List<User> FMS = userService.findByPrivilegeName("FM");
		String fm = "";
		if(FMS!=null&&FMS.size()>0)
		{
			report.setReplyFmname(FMS.get(0).getRealName());
			fm = FMS.get(0).getUserName();
		}
		String replyState="";
		switch (report.getReplyState()) {
		case 0:
			replyState="草稿";
			break;
		case 1:
			replyState="提交FM";
			break;
		case -1:
			replyState="FM有意见";
			break;
		case 2:
			replyState="提交QA";
			break;
		case 3:
			replyState="QA确认接收";
			break;

		default:
			break;
		}
		map.put("replyState", replyState);
		qAChkReportService.update(report);
		
		User tempUser = (User)ActionContext.getContext().getSession().get("user");
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		if(studyNoParam==null||"".equals(studyNoParam))
		{
			//studyNoParam=((QAChkIndex)(report.getQachkIndexes().toArray()[0])).getStudyNo();
			studyNoParam = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
		}
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String tiNo = studyItem.getTiNo();
		String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
		DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
		String studyNoName = "";
		if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
			studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
		}else{
			studyNoName = testItemName+studyItem.getStudyName();
		}
			String msgTitle=("SD("+tempUser.getRealName()+")，提交了回复，报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"　需要审批");//消息头
			String msgContent = "<br>";
			msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了回复，"+
			"<br>报告编号:<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a> <br>专题编号:"+studyNoParam+"，<br>专题名称:"+studyNoName+"， <br> 需要审批，特此提醒！";
			
			msgContent += "<br>"+getReportContentTableStr(report.getChkReportCode());
			
			String receiverList = "";
			
			receiverList+=(fm)+",";
			
			User u = userService.getByRealName(report.getInspector());
			if(u!=null)
			{
				String qa=u.getUserName();
				receiverList+=qa+",";
			}
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
		
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("SD提交回复完毕");
			es.setEsType(820);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);
			
			esLink.setTableName("QAChkReport");
			esLink.setDataId(report.getChkReportCode());
			esLink.setTblES(es);
			esLink.setEsType(820);
			esLink.setEsTypeDesc("SD提交回复完毕签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	//延迟整改
	public void hasDelayRecord()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		//Set<QAChkReportRecord> reportRecords = report.getQachkReportRecords();
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
		boolean flag = false;
		if(reportRecords!=null&&reportRecords.size()>0)
		{
			for(QAChkReportRecord reportRecord:reportRecords)
			{
				//reportRecord.getChkResultFlag()==-1
				//if(reportRecord.getReplyContent()==null||"".equals(reportRecord.getReplyContent()))
				if(reportRecord.getDelayRsn()!=null&&reportRecord.getDelayPlanFinishDate()!=null&&reportRecord.getNeedDelay()!=null)
				{
					flag = true;
					break;
				}
			}
		}
		map.put("hasDelayRecord", flag);
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void commitDelayToFM()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		//needDelay  0：未申请；1：FM退回；2：提交QA检查员；3：qa检查员确认接收
		//delayStatus 0：未完成；1：已完成
		report.setDelayState(0);
		//report.setNeedDelay(1);
		report.setNeedDelay(2);//延迟整改的申请也不经过FM审批了
		
		String replyState="";
		switch (report.getNeedDelay()) {
			case 0:
				replyState="草稿";
				break;
			case 1:
				replyState="提交FM";
				break;
			case -1:
				replyState="FM有意见";
				break;
			case 2:
				replyState="提交QA";
				break;
			case 3:
				replyState="QA确认接收";
				break;
	
			default:
				break;
		}
		map.put("needDelay", replyState);
		qAChkReportService.update(report);
		
		String fm = "";
		if(report.getDelayFmname()==null||"".equals(report.getDelayFmname()))
		{
			List<User> FMS = userService.findByPrivilegeName("FM");
			
			if(FMS!=null&&FMS.size()>0)
			{
				report.setDelayFmname(FMS.get(0).getRealName());
				fm = FMS.get(0).getUserName(); 
			}
			
		}else{
			fm = userService.getByRealName(report.getDelayFmname()).getUserName();
		}
		String qa = userService.getByRealName(report.getInspector()).getUserName();
		//通知FM，QA
		User tempUser = (User)ActionContext.getContext().getSession().get("user");
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		if(studyNoParam==null||"".equals(studyNoParam))
		{
			//studyNoParam=((QAChkIndex)(report.getQachkIndexes().toArray()[0])).getStudyNo();
			studyNoParam = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
		}
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String tiNo = studyItem.getTiNo();
		String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
		DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
		String studyNoName = "";
		if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
			studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
		}else{
			studyNoName = testItemName+studyItem.getStudyName();
		}
		
			String msgTitle=("SD("+tempUser.getRealName()+")，提交延迟整改，报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+" 专题名称:"+studyNoName+"　");//消息头
			String msgContent = "<br>";
			msgContent += msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了延迟整改，"+
			"<br>报告编号:<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a><br>专题编号:"+studyNoParam+"，<br>专题名称:"+studyNoName+"，<br>，特此提醒！";
			
			msgContent += "<br>"+getReportContentTableStr2(report.getChkReportCode());
			
			String receiverList = "";
			
			receiverList+=(fm)+",";
			
			receiverList+=(qa)+",";
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
		
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("SD提交延迟整改完毕");
			es.setEsType(821);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);
			
			esLink.setTableName("QAChkReport");
			esLink.setDataId(report.getChkReportCode());
			esLink.setTblES(es);
			esLink.setEsType(821);
			esLink.setEsTypeDesc("SD提交延迟整改完毕签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	/**转到报告回复显示页面*/
	public String ireport() throws Exception
	{
		ActionContext.getContext().getSession().put("chkReportCode", model.getChkReportCode());
		
		ActionContext.getContext().put("time",time);
		ActionContext.getContext().put("status",status);
		ActionContext.getContext().put("searchCondition",searchCondition);
		
		ActionContext.getContext().getSession().put("printContent",printContent);
		return "ireport";
	}
	
	/** out 打印回复信息*/
	public String outport() throws Exception{
		//参数
		Map<String,Object> paraMap = new HashMap<String, Object>();
		String location = "/jasperReport/ReplyReport.jasper";//replyReport
		if("delay".equals(printContent))
			location = "/jasperReport/DelayReport.jasper";//DelayReport
		String reportName = "QAU检查报告回复";
		if("delay".equals(printContent))
			reportName = "QAU检查报告整改措施延迟落实申请单";
		
		URL url = ServletActionContext.getServletContext().getResource("/jasperReport/"+"logo.jpg");
		paraMap.put("logoImage", url);
		URL checkUrl = ServletActionContext.getServletContext().getResource("/jasperReport/"+"right.jpg");
		paraMap.put("checkImage", checkUrl);
		
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
	//	Set<QAChkIndex> indexs = report.getQachkIndexes();
		List<QAChkIndex> indexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
		if(indexs!=null&&indexs.size()>0)
		{
			QAChkIndex index = (QAChkIndex)(indexs.toArray()[0]);
			String studyNo = index.getStudyNo();
			paraMap.put("studyNo", studyNo);
		}
		paraMap.put("reportCode", report.getChkReportCode());
		paraMap.put("reportName", reportName);
		paraMap.put("reportQAM", report.getQam());
		paraMap.put("delayDate", DateUtil.dateToString(report.getDelayFmapprovalTime(),"yyyy-MM-dd"));
		if("delay".equals(printContent))//延迟整改
		{
			//意见在审批表中
			//int approvalType 1：报告；2：回复；3：延迟整改；4：检查计划,
			//int operatorType 1：FM；2：QAM；3：QA检查员
			QAApprovalOpinion opinion = qAApprovalOpinionService.getByReportAndType(report.getChkReportCode(),3,1);
			if(opinion!=null){
				//1：过；-1：未过
				if(opinion.getApprovalResultFlag() ==-1)
					paraMap.put("hasComment",1);//0无意见，1有意见
				else if(opinion.getApprovalResultFlag() == 1){
					paraMap.put("hasComment",0);//0无意见，1有意见
				}
				paraMap.put("comment", opinion.getApprovalOpinion());
			}
			
			
		}else {//回复
			paraMap.put("hasComment", report.getReplyFmreviewResult());//0无意见，1有意见
			paraMap.put("comment", report.getReplyFmreveiwRemark());
			paraMap.put("needRecheck",report.getNeedReChk());
			if(report.getNeedReChk()!=null)
			{
				if(report.getNeedReChk()==null||report.getNeedReChk()==0){//0不需要 1需要
					paraMap.put("solved",3);//除了0和1的任何一个数字
				}else if(report.getReChkState()==null||report.getReChkState()==0)
					paraMap.put("solved",0);//0：问题未解决；1：问题已解决
				else if(report.getReChkState()==1)
					paraMap.put("solved",1);//0：问题未解决；1：问题已解决
			}
		}
		
		
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(model.getChkReportCode());
		
		//结果集
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for(QAChkReportRecord record:reportRecords)
		{
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("chkItemName", record.getChkItemName());
			map.put("chkContent", record.getChkContent());
			if("×".equals(record.getChkResult()))
			{
				map.put("chkResult", record.getChkResultDesc());	
			}else{
				map.put("chkResult", record.getChkResult());				
			}
			map.put("advice", record.getAdvice());
			map.put("replyContent", record.getReplyContent());
			if("×".equals(record.getChkResult()))
			{
				if(record.getNeedDelay()!=null&&record.getNeedDelay()==1)
					map.put("needDelay","已申请" );
				else {
					map.put("needDelay","未申请" );
				}
			}else {
				map.put("needDelay","NA" );
			}
			map.put("delayRsn", record.getDelayRsn());
			map.put("delayPlanFinishDate", record.getDelayPlanFinishDate());
			
			mapList.add(map);
		}
		
		String fileName = reportName;
		ReportMap.getInstance(request).addCompanyInfoIntoMap(paraMap);
		ActionContext.getContext().put("paraMap", paraMap);
		ActionContext.getContext().put("mapList", mapList);
		ActionContext.getContext().put("fileName", fileName);
		ActionContext.getContext().put("location",location);
		
		return "outport";
	}
	//FM批复
	public void saveReplyApproval()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("FM批复报告回复完毕");
		es.setEsType(804);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName("QAChkReport");
		esLink.setDataId(model.getChkReportCode());
		esLink.setTblES(es);
		esLink.setEsType(804);
		esLink.setEsTypeDesc("FM批复报告回复完毕签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
		writeLog("FM批复报告回复完毕","FM批复报告回复完毕","FM批复报告回复完毕,报告编号："+model.getChkReportCode());
		
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		
		report.setReplyFmapprovalTime(new Date());
		report.setReplyFmreveiwRemark(model.getReplyFmreveiwRemark());
		report.setReplyFmreviewResult(model.getReplyFmreviewResult());
		if(model.getReplyFmreviewResult()==1)//审批有意见
		{
			report.setReplyState(-1);//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收			
		}else {
			report.setReplyState(2);//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收	
		}
		
		qAChkReportService.update(report);
		
		
		map.put("success", true);
		
		//0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收
		if(report.getReplyState()!=null)
		{
			String replyState="";
			switch (report.getReplyState()) {
			case 0:
				replyState="草稿";
				break;
			case 1:
				replyState="提交FM";
				break;
			case -1:
				replyState="FM有意见";
				break;
			case 2:
				replyState="提交QA";
				break;
			case 3:
				replyState="QA确认接收";
				break;

			default:
				break;
			}
			map.put("replyState", replyState);
		}else
		{
			map.put("replyState", "无回复信息");
		}
		//map.put("replyState", report.getReplyState());
		
		QAApprovalOpinion opinion = new QAApprovalOpinion();
		opinion.setApprovalName(tempUser.getRealName());
		opinion.setApprovalOpinion(model.getReplyFmreveiwRemark());
		opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
		if(model.getReplyFmreviewResult()==1)//审批有意见
		{
			opinion.setApprovalResult("有意见");
			opinion.setApprovalResultFlag(-1);
			
		}else {
			opinion.setApprovalResult("无意见");
			opinion.setApprovalResultFlag(1);
		}
		
			
			//通知QA继续检查
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		if(studyNoParam==null||"".equals(studyNoParam))
		{
			//studyNoParam=((QAChkIndex)(report.getQachkIndexes().toArray()[0])).getStudyNo();
			studyNoParam = qAChkIndexService.getStudyNoByReportCode(report.getChkReportCode());
		}
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
		String tiNo = studyItem.getTiNo();
		String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
		DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
		String studyNoName = "";
		if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
			studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
		}else{
			studyNoName = testItemName+studyItem.getStudyName();
		}
			String msgTitle=("FM("+tempUser.getRealName()+")，审批回复:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+" 专题名称:"+studyNoName+"　需要接收");//消息头
			String msgContent = "<br>";
			msgContent = msgContent+"FM("+tempUser.getRealName()+")于"+currentDate+"审批了报告回复，"+
			"<br>报告编号:"+report.getChkReportCode()+"<br>专题编号:"+studyNoParam+"<br>专题名称:"+studyNoName+"，<br>请及时接收回复，特此提醒！";
			
			String receiverList = "";
			
			User qaUser = userService.getByRealName(report.getInspector());
			receiverList+=(qaUser.getUserName())+",";
			
			map.put("msgTitle", msgTitle);
			map.put("msgContent", msgContent);
			map.put("receiverList", receiverList);
			
		opinion.setApprovalTime(new Date());
		opinion.setApprovalType(2);//1：报告；2：回复；3：延迟整改；4：检查计划
		opinion.setObjectVersion(1);//可选：对于检查计划变更，为1、2、3.等
		opinion.setOperatorType(1);//1：FM；2：QAM；3：QA检查员
		opinion.setObjectCode(report.getChkReportCode());
		
		qAApprovalOpinionService.save(opinion);
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void acceptDelayApply()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("FM审批延迟整改完毕");
		es.setEsType(805);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
	
		tblESService.save(es);
		
		esLink.setTableName("QAChkReport");
		esLink.setDataId(model.getChkReportCode());
		esLink.setTblES(es);
		esLink.setEsType(805);
		esLink.setEsTypeDesc("FM批复延迟整改完毕签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		writeLog("FM批复延迟整改完毕", "FM批复延迟整改完毕", "FM批复延迟整改完毕,报告编号："+model.getChkReportCode());
		
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
	
		report.setDelayFmapprovalTime(new Date());	
		report.setDelayFmname(tempUser.getRealName());
		//delayState传过来的是report的needDelay的值
		report.setNeedDelay(model.getDelayState());//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
		report.setDelayState(0);//0：未完成；1：已完成
			//通知QA
			//Set<QAChkIndex> indexs = report.getQachkIndexes();
			List<QAChkIndex> indexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
			if(indexs!=null&&indexs.size()>0)
			{
				String studyNoParam = ((QAChkIndex)indexs.toArray()[0]).getStudyNo();
				String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
				
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNoParam);
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				String studyNoName = "";
				if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
				String msgTitle=("FM("+tempUser.getRealName()+")，批复报告延迟整改, 报告编号："+report.getChkReportCode()+" 专题编号:　"+studyNoParam+" 专题名称:"+studyNoName+"　请查看SD的延迟整改");//消息头
				//userService.getRealNameByUserName(leader.getTaskLeader())+"您好
				String msgContent = "<br>";
				msgContent = msgContent+"FM("+tempUser.getRealName()+")于"+currentDate+"批复了报告延迟整改，"+
				"<br>报告编号:"+report.getChkReportCode()+"<br>专题编号:"+studyNoParam+"<br>专题名称:"+studyNoName+"，<br>请及时查看回复，特此提醒！";
				
				String receiverList = "";
				User reportQA = userService.getByRealName(report.getInspector());
				if(reportQA!=null)
				{
					String userCode = reportQA.getUserName();
					receiverList+=(userCode)+",";	
				}
				map.put("msgTitle", msgTitle);
				map.put("msgContent", msgContent);
				map.put("receiverList", receiverList);
			}
			
		//}
		qAChkReportService.update(report);
		//增加审批意见记录
		QAApprovalOpinion opinion = new QAApprovalOpinion();
		opinion.setApprovalName(tempUser.getRealName());
		
		opinion.setApprovalOpinion(delayFmreveiwRemark);
		opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
		if(model.getDelayState()==-1)
		{
			opinion.setApprovalResult("未过");//1：过；-1：未过
			opinion.setApprovalResultFlag(-1);
		}else if(model.getDelayState()==2)
		{
			opinion.setApprovalResult("过");
			opinion.setApprovalResultFlag(1);
		}
		opinion.setApprovalTime(new Date());
		opinion.setApprovalType(3);//1：报告；2：回复；3：延迟整改；4：检查计划
		opinion.setObjectVersion(1);//可选：对于检查计划变更，为1、2、3.等
		opinion.setOperatorType(1);//1：FM；2：QAM；3：QA检查员
		opinion.setObjectCode(report.getChkReportCode());
		qAApprovalOpinionService.save(opinion);
		
		map.put("success", true);
		//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
		if(report.getNeedDelay()!=null)	
		{
			String delayState = "";
			switch (report.getNeedDelay()) {
				case 0:
					delayState="未申请";
					break;
				case 1:
					delayState="提交FM";
					break;
				case -1:
					delayState="FM有意见";
					break;
				case 2:
					delayState="提交QA";
					break;
				case 3:
					delayState="QA确认接收";
					break;
				
			}
			
			map.put("needDelay", delayState);
		}else {
			map.put("needDelay","无延迟整改信息");
		}
		
		
		//map.put("delayState", report.getDelayState());
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	
	public void getSOPByReport()
	{
		List<String> qaIndexIds = qAChkIndexService.getByReport(model.getChkReportCode());
		List<QAChkSop> sops = qAChkSopService.getByChkIndexIds(qaIndexIds);
		String[] _nory_format = {"sopRecordId", "fileRecordId","sopCode","sopName","sopVersion","sopPublishTime","sopPublishDepartment","remark"};
		String json = JsonPluginsUtil.beanListToJson(sops, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
		writeJson(json);
	}
	public void getReportRecordByReport()
	{
		QAChkReport report = qAChkReportService.getById(model.getChkReportCode());
		
		List<QAChkReportRecord> reportRecords = qAChkReportRecordService.getByReportCode(model.getChkReportCode());
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
		ActionContext.getContext().getSession().put("qam",report.getQam() );
		ActionContext.getContext().getSession().put("reportQA",report.getInspector() );
		//report.getQachkReportReaders();
		
		
		String[] _nory_format = {"chkReportRecordId", "chkItemName","chkContent","chkResult","chkResultDesc","chkResultFlag","replyContent","reChkResult","needDelay","delayDesc","delayRsn","delayPlanFinishDate","delayFinishTime","chkTime","inspector","advice"};
		String json = JsonPluginsUtil.beanListToJson(reportRecords, "yyyy-MM-dd HH:mm:ss", _nory_format, true);
		writeJson(json);
		
	}

	//报告的状态
	public void getReportNewestList()
	{
		//方案的签字TblStudyFileIndex
		//报告有报告（rptState），回复（replyState）和整改（delayState）三类签字
		//qa管理的的有计划（QAStudyChkIndex chkPlanState），记录（QAChkIndex 2）两类签字
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(endDate);
		calendar1.add(Calendar.DATE, 1);
		endDate = calendar1.getTime();//endDate增加一天，查询结果包括endDate那天
		List<Map<String, Object>> maps = qAChkReportService.getByDateAndSignStatus(startDate,endDate,searchCondition);//获取某个时间段内有签字信息的报告
		// linkId,eslink.[recordTime],[chkReportCode],[studyNo],eslink.[esTypeDesc],es.signer
		for(Map<String, Object> map:maps)
		{
			map.put("recordTime", DateUtil.dateToString((Date)map.get("recordTime"), "yyyy-MM-dd HH:mm:ss"));
		}
		writeJson(JsonPluginsUtil.beanListToJson(maps));
		
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


	public Integer getTime() {
		return time;
	}


	public void setTime(Integer time) {
		this.time = time;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getSearchCondition() {
		return searchCondition;
	}


	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}


	public String getPrintContent() {
		return printContent;
	}


	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public QAApprovalOpinionService getqAApprovalOpinionService() {
		return qAApprovalOpinionService;
	}
	public void setqAApprovalOpinionService(
			QAApprovalOpinionService qAApprovalOpinionService) {
		this.qAApprovalOpinionService = qAApprovalOpinionService;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}
	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
	public String getDelayFmreveiwRemark() {
		return delayFmreveiwRemark;
	}
	public void setDelayFmreveiwRemark(String delayFmreveiwRemark) {
		this.delayFmreveiwRemark = delayFmreveiwRemark;
	}
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
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
	

}
