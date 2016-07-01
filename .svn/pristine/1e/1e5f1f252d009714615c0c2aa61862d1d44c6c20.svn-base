package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.QAChkIndex;
import com.lanen.model.qa.QAChkRecord;
import com.lanen.model.qa.QAChkReport;
import com.lanen.model.qa.QAChkReportRecord;
import com.lanen.model.qa.QAReChkRecord;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAChkIndexService;
import com.lanen.service.qa.QAChkRecordService;
import com.lanen.service.qa.QAChkReportRecordService;
import com.lanen.service.qa.QAChkReportService;
import com.lanen.service.qa.QAReChkRecordService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkReportRecordAction extends BaseAction<QAChkReportRecord>{

	private static final long serialVersionUID = -4902970405921009550L;
	
	@Resource
	private QAChkReportRecordService qAChkReportRecordService;
	@Resource
	private QAChkReportService qAChkReportService;
	@Resource
	private QAReChkRecordService qAReChkRecordService;
	@Resource
	private QAChkRecordService qAChkRecordService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private UserService userService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblStudyFileIndexService tblStudyFileIndexService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private QAChkIndexService qAChkIndexService;
	@Resource
	private TblESService tblESService;
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	private String signReChkRecord;

	//再检查签字放一起，再检查只是再检查，签字以后才会生效
	public void saveOneTempReChk()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		QAChkReport report = record.getQachkReport();
		
		//回复
		if(record.getChkResultFlag()==-1&&report.getReplyState()!=null&&report.getReplyState()==3&&
				record.getReChkFlag()!=null&&record.getReChkFlag()==1)//不符合,接受回复了,并且问题未解决
		{	
			//再检查记录
			QAReChkRecord reChkRcord = new QAReChkRecord();
			reChkRcord.setQachkReportRecord(record);
			reChkRcord.setReChkInspector(user.getRealName());
			reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
			
			//	if(model.getReChkFlag()==1)//是否需要再检查
			reChkRcord.setReChkRecord(""+model.getReChkFlag());
			//reportRecord 0：未检查；-1：问题未解决；1：问题已解决
			//0：未确认；1：问题已解决；-1：未解决
			reChkRcord.setReChkResult(model.getReChkResult());
			
			reChkRcord.setReChkTime(new Date() );
			reChkRcord.setReChkType(1);//1：回复的再检查记录；2：延迟整改再检查记录
			qAReChkRecordService.save(reChkRcord);
			
			map.put("reChkFlag",reChkRcord.getReChkRecord());
			map.put("reChkResult",reChkRcord.getReChkResult());
			
		}
		//记录申请了延迟，但是具体时间还没有
		if(record.getNeedDelay()!=null&&report.getNeedDelay()!=null&&report.getNeedDelay()==3
				&&record.getNeedDelay()==1&&record.getDelayFinishTime()==null)
		{
			QAReChkRecord reChkRcord = new QAReChkRecord();
			reChkRcord.setQachkReportRecord(record);
			reChkRcord.setReChkInspector(user.getRealName());
			reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
			
			reChkRcord.setReChkRecord(DateUtil.dateToString(model.getDelayFinishTime(),"yyyy-MM-dd HH:mm:ss"));
			
			reChkRcord.setReChkResult(1);
			reChkRcord.setReChkTime(new Date() );
			reChkRcord.setReChkType(2);//1：回复的再检查记录；2：延迟整改再检查记录
			qAReChkRecordService.save(reChkRcord);
			
			map.put("delayFinishTime",DateUtil.dateToString(DateUtil.stringToDate(reChkRcord.getReChkRecord(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"));
		}
		
		
		
		
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void hasReChkRecord()
	{
		Map<String, Object> map = new HashMap<String, Object>();
	
		String[] reChkIds = signReChkRecord.split(",");
		List<QAChkReport> signReport = new ArrayList<QAChkReport>();
		
		List<QAReChkRecord> reChkList = qAReChkRecordService.getNoSignByReportRecordIds(reChkIds);
		
		if(reChkList!=null&&reChkList.size()>0)
		{
			map.put("has", true);
		}else{
			map.put("has", false);
		}
			
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public String getReportContentTableStr(String chkReportCode){
		
		String 	reportContent="<br>报告编号："+chkReportCode+" 报告内容如下：<br> " +
		"<table style='width:870px;'> " +
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
				"<th style='width:50px;'>再检查结果" +
				"</th>" +
				"<th style='width:60px;'>延迟状态 " +
				"</th>" +
				"<th style='width:80px;'>预计日期 " +
				"</th>" +
				"<th style='width:80px;'>实际日期 " +
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
								"</td>" +
								"<td> " +reportRecord.getReplyContent()+
								"</td>" +
								"<td>" +reportRecord.getReChkResult()+
								"</td>" +
								"<td> " +str+
								"</td>" +
								"<td> " +DateUtil.dateToString(reportRecord.getDelayPlanFinishDate(),"yyyy-MM-dd")+
								"</td>" +
								"<td> " +DateUtil.dateToString(reportRecord.getDelayFinishTime(),"yyyy-MM-dd");
			}else{
				reportContent+=	"<td>" +reportRecord.getChkResult()+
								"</td>" +
								"<td>NA " +
								"</td>" +
								"<td>NA " +
								"</td>" +
								"<td>NA" +
								"</td>" +
								"<td>NA " +
								"</td>" +
								"<td>NA " +
								"</td>" +
								"<td>NA " ;
			}
			reportContent+=	"</td>" +
							"</tr> ";
		}
		
		reportContent+="</table>";
		
		return reportContent;
	}
	
	public void signReChk()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String emailList = "";
		
		String[] reChkIds = signReChkRecord.split(",");
		User user = (User)ActionContext.getContext().getSession().get("user");
		List<QAChkReport> signReport = new ArrayList<QAChkReport>();
		
		List<QAReChkRecord> reChkList = qAReChkRecordService.getNoSignByReportRecordIds(reChkIds);
		//828再检查签字
		for(QAReChkRecord reChkRecord:reChkList)
		{
			String signID = writeES("再检查签字", 828, "QAReChkRecord", reChkRecord.getReChkRecordId());
			reChkRecord.setReChkSignID(signID);
			
			QAChkReportRecord record = reChkRecord.getQachkReportRecord();
			if(!signReport.contains(record.getQachkReport()))
			{
				signReport.add(record.getQachkReport());
			}
			//reChkRecord;//对回复，存的是是否需要再捡查的结果。对整改存的是日期
			if(reChkRecord.getReChkType()==1)//1回复 2整改
			{
				record.setReChkFlag(Integer.valueOf(reChkRecord.getReChkRecord()));
				record.setReChkResult(reChkRecord.getReChkResult());
				record.setReChkInspector(reChkRecord.getReChkInspector());
				record.setReChkTime(reChkRecord.getReChkTime());
				
			}else if(reChkRecord.getReChkType()==2){
				
				record.setDelayFinishTime(DateUtil.stringToDate(reChkRecord.getReChkRecord(),"yyyy-MM-dd HH:mm:ss"));
				
				record.setDelayQainspector(reChkRecord.getReChkInspector());
				record.setDelayQaconfirmTime(reChkRecord.getReChkTime());
			}
			
			qAChkReportRecordService.update(record);
			qAReChkRecordService.update(reChkRecord);
		}
		
		String finishType="";
		for(QAChkReport report:signReport)
		{
			boolean isNeedReChk = false;
			boolean isDelayFinish = true;
			List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
			for(QAChkReportRecord recordTemp:records)
			{
				if(report.getNeedReChk()!=null&&report.getNeedReChk()==1&&
						recordTemp.getReChkFlag()!=null&&recordTemp.getReChkFlag()==1)//0：不需；1：需要；
				{
					isNeedReChk=true;
				}
				//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
				if(report.getNeedDelay()!=null&&report.getNeedDelay()!=0
						&&recordTemp.getNeedDelay()!=null&&recordTemp.getNeedDelay()==1&&
						(recordTemp.getDelayFinishTime()==null||"".equals(recordTemp.getDelayFinishTime())))//申请了，但是没有完成时间
				{
					isDelayFinish = false;
				}
			}
			
			
			//4：进入再检查；5：进入延期整改；
			String type="";
			if(isDelayFinish)
			{
				if(report.getDelayQaureceiveTime()!=null)
				{
					report.setDelayState(1);
					//map.put("finish", true);
					type = "延迟整改";
				}
			}
			if(!isNeedReChk)
			{
				if(report.getReplyInspectorReceiveTime()!=null)
				{
					//report.setNeedReply(0);
					//report.setNeedReChk(0);//0：不需要；1：需要
					report.setReChkState(1);//0：问题未解决；1：问题已解决
					if("".equals(type))
						type="回复";
					else
						type="回复和延迟整改";
				}
			}
			if(!"".equals(type))
				finishType += report.getChkReportCode()+type+"再检查完毕！<br>";
			
			boolean hasNoReceive=true;
			if((isDelayFinish||!isNeedReChk)&&
					(
						(report.getNeedReply()!=null&&report.getNeedReply()==1&&(report.getReplyState()==null||report.getReplyState()!=3))//未接收回复
						||(report.getNeedDelay()!=null&&report.getNeedDelay()!=0&&report.getNeedDelay()!=3)//未接收延迟
					)
			  )
			{
				hasNoReceive=false;
				report.setRptState(3);
			}else if(isDelayFinish&&isNeedReChk){
				report.setRptState(4);
				
			}else if(!isDelayFinish&&!isNeedReChk){
				report.setRptState(5);
			}
			
			if(!isNeedReChk&&isDelayFinish&&hasNoReceive){
					//查看是否已经完成了
					if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
							(report.getNeedReply()==null||report.getNeedReply()==0//报告不需要回复
									||(report.getNeedReply()==1&&report.getReplyState()==3))&&//回复已经被qa处理
							(report.getNeedReChk()==null||report.getNeedReChk()==0||//报告不需要再检查
									(report.getNeedReChk()==1&&report.getReChkState()==1))&&
							(report.getNeedDelay()==null||report.getNeedDelay()==0||
									(report.getNeedDelay()==3&&report.getDelayState()==1))&&//报告延迟整改完成
							report.getRptState()!=9)//报告完成
					{
						report.setRptState(9);
						report.setFinalFinishTime(new Date());
						finishType+=report.getChkReportCode()+"报告结束！<br>";
						
						List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
						String studyNoParam="";
						String reportName="";
						
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
						//通知QAM,SD,FM，报告通过了没有问题
						String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
						
						String msgTitle=("QA("+user.getRealName()+")，再检查正常结束，"+reportName+"报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"，报告完成");//消息头
						String msgContent = "<br>";
						msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"再检查正常结束，"+reportName+
						"<br>报告编号:　<a href='#' onclick='clickOneReport(\""+report.getChkReportCode()+"\");'>"+report.getChkReportCode()+"</a> <br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>报告完成，特此提醒！";
						
						msgContent+="<br>"+getReportContentTableStr(report.getChkReportCode());
						
						String receiverList = "";
						
						//String userCode = userService.getByRealName(report.getInspector()).getUserName();
						//receiverList+=(userCode)+",";
						
						//if(!"".equals(reportName))
						//{
							String qamCode = userService.getByRealName(report.getQam()).getUserName();
							receiverList+=(qamCode)+",";
							String sdCode = userService.getByRealName(report.getSd()).getUserName();
							receiverList+=(sdCode)+",";
							String fmCode = userService.getByRealName(report.getReplyFmname()).getUserName();
							receiverList+=(fmCode)+",";
							
						//}
						emailList+=msgTitle+"~"+msgContent+"~"+receiverList+"@";
						//map.put("msgTitle", msgTitle);
						//map.put("msgContent", msgContent);
						//map.put("receiverList", receiverList);
					}
					
				
			}
			qAChkReportService.update(report);
		
		}
		map.put("emailList",emailList);
		map.put("success", true);
		map.put("finishType", finishType);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void saveOneReChk()
	{
		boolean flag = true;
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		QAChkReport report = record.getQachkReport();
		User user = (User)ActionContext.getContext().getSession().get("user");
		
		if(model.getReChkFlag()==1)//需要再检查
		{
			flag = false;
			//report.setNeedReply(1);
			//report.setNeedReChk(1);//整个报告需要重新检查
			report.setReChkState(0);//0未完成，1完成
		}
		int type=0;//1回复，2延迟，3两个
		//如果回复和延迟整改的再检查一起的话，会有两条在检查记录
		//(record.getReChkResult()==null||record.getReChkResult()==0||record.getReChkResult()==-1)不管问题解决没有解决，只要需要再检查就证明回复需要再检查
		if(record.getChkResultFlag()==-1&&report.getReplyState()!=null&&report.getReplyState()==3&&
				record.getReChkFlag()!=null&&record.getReChkFlag()==1)//不符合,接受回复了,并且问题未解决
		{	
			record.setReChkFlag(model.getReChkFlag());
			record.setReChkResult(model.getReChkResult());
			record.setReChkInspector(user.getRealName());
			record.setReChkTime(new Date());
			
			//再检查记录
			QAReChkRecord reChkRcord = new QAReChkRecord();
			reChkRcord.setQachkReportRecord(record);
			reChkRcord.setReChkInspector(user.getRealName());
			reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
			//reChkRcord.setReChkRecord(reChkRecord);
			
			if(model.getReChkResult()==0)
				reChkRcord.setReChkResult(-1);
			if(model.getReChkResult()==1)
				reChkRcord.setReChkResult(1);
			reChkRcord.setReChkTime(record.getReChkTime() );
			reChkRcord.setReChkType(1);//1：回复的再检查记录；2：延迟整改再检查记录
			qAReChkRecordService.save(reChkRcord);
			type=1;//回复
			
		}
		//记录申请了延迟，但是具体时间还没有
		if(record.getNeedDelay()!=null&&report.getNeedDelay()!=null&&report.getNeedDelay()==3
				&&record.getNeedDelay()==1&&record.getDelayFinishTime()==null)
		{
			record.setDelayFinishTime(model.getDelayFinishTime());
			
			record.setDelayQainspector(user.getRealName());
			record.setDelayQaconfirmTime(new Date());
			
			QAReChkRecord reChkRcord = new QAReChkRecord();
			reChkRcord.setQachkReportRecord(record);
			reChkRcord.setReChkInspector(user.getRealName());
			reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
			//reChkRcord.setReChkRecord(reChkRecord);
			reChkRcord.setReChkResult(1);
			reChkRcord.setReChkTime(record.getDelayQaconfirmTime() );
			reChkRcord.setReChkType(2);//1：回复的再检查记录；2：延迟整改再检查记录
			qAReChkRecordService.save(reChkRcord);
			if(type==1)
				type=3;
			else
				type=2;
			
		}
		
		qAChkReportRecordService.update(record);
		
		
			boolean isNeedReChk = false;
			boolean isDelayFinish = true;
			List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
			for(QAChkReportRecord recordTemp:records)
			{
				if(report.getNeedReChk()!=null&&report.getNeedReChk()==1&&
						recordTemp.getReChkFlag()!=null&&recordTemp.getReChkFlag()==1)//0：不需；1：需要；
				{
					isNeedReChk=true;
				}
				//0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
				if(report.getNeedDelay()!=null&&report.getNeedDelay()!=0
						&&recordTemp.getNeedDelay()!=null&&recordTemp.getNeedDelay()==1&&
						(recordTemp.getDelayFinishTime()==null||"".equals(recordTemp.getDelayFinishTime())))//申请了，但是没有完成时间
				{
					isDelayFinish = false;
				}
			}
			
			String finishType="";
			//4：进入再检查；5：进入延期整改；
			if((type==2||type==3)&&isDelayFinish)
			{
				report.setDelayState(1);
				map.put("finish", true);
				map.put("delayState",1 );
				map.put("needReChk",report.getNeedReChk());
				finishType = "所有延迟整改项已检查完毕！";
			}
			if((type==1||type==3)&&!isNeedReChk)
			{
				//report.setNeedReply(0);
				//report.setNeedReChk(0);//0：不需要；1：需要
				report.setReChkState(1);//0：问题未解决；1：问题已解决
				
				map.put("finish", true);
				map.put("needReChk",0);
				map.put("delayState",report.getDelayState());
				if("".equals(finishType))
				{
					finishType="所有回复项已经再检查结束！";					
				}else {
					finishType="所有回复项和延迟整改项已经再检查结束！";
				}
			}
			
			map.put("finishType", finishType);
			boolean hasNoReceive=true;;
			//有一个完成了（再检查或者延迟有一个完成了。）
			if((isDelayFinish||!isNeedReChk)&&(report.getNeedReply()!=null&&report.getNeedReply()==1&&(report.getReplyState()==null||report.getReplyState()!=3))//回复未接收
					||(report.getNeedDelay()!=null&&report.getNeedDelay()!=0&&(report.getDelayState()==null||report.getDelayState()!=1)))
			{
				hasNoReceive=false;
			}
			if(!"".equals(finishType))
			{
				//有一个完成了（再检查或者延迟有一个完成了。）
				if((isDelayFinish||!isNeedReChk)&&(report.getNeedReply()!=null&&report.getNeedReply()==1&&(report.getReplyState()==null||report.getReplyState()!=3))//回复未接收
						||(report.getNeedDelay()!=null&&report.getNeedDelay()!=0&&(report.getDelayState()==null||report.getDelayState()!=1)))
				{
					//等待接收回复
					report.setRptState(3);
					map.put("rptState",3);
				}
				if((type==2||type==3)&&isDelayFinish&&isNeedReChk)
				{
					report.setRptState(4);
					map.put("rptState",4);
				}
				if((type==1||type==3)&&!isDelayFinish&&!isNeedReChk)
				{
					report.setRptState(5);
					map.put("rptState",5);
				}	
			}
			
		if(flag)//这次的报告记录不需要再检查了
		{	
			if(!isNeedReChk&&isDelayFinish&&hasNoReceive){
				//查看是否已经完成了
				if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
						(report.getNeedReply()==null||report.getNeedReply()==0//报告不需要回复
								||(report.getNeedReply()==1&&report.getReplyState()==3))&&//回复已经被qa处理
						(report.getNeedReChk()==null||report.getNeedReChk()==0||//报告不需要再检查
								(report.getNeedReChk()==1&&report.getReChkState()==1))&&
						(report.getNeedDelay()==null||report.getNeedDelay()==0||
								(report.getNeedDelay()==3&&report.getDelayState()==1))&&//报告延迟整改完成
						report.getRptState()!=9)//报告完成
				{
					report.setRptState(9);
					map.put("rptState",9);
					report.setFinalFinishTime(new Date());
					
					//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
					List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
					String studyNoParam="";
					String reportName="";
					//if(report.getQachkIndexes().toArray()!=null)
					//{
					//	QAChkIndex indexs = (QAChkIndex)report.getQachkIndexes().toArray()[0];
						//studyNoParam = indexs.getStudyNo();
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
					//通知QA，报告通过了没有问题
					String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
					
					String msgTitle=("QA("+user.getRealName()+")，再检查正常结束，"+reportName+"报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"，报告完成");//消息头
					String msgContent = "<br>";
					msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"再检查正常结束，"+reportName+
					"<br>报告编号:　"+report.getChkReportCode()+"<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>报告完成，特此提醒！";
					
					String receiverList = "";
					
					String userCode = userService.getByRealName(report.getInspector()).getUserName();
					receiverList+=(userCode)+",";
					if(!"".equals(reportName))
					{
						String sdCode = userService.getByRealName(report.getSd()).getUserName();
						receiverList+=(sdCode)+",";
					}
					map.put("msgTitle", msgTitle);
					map.put("msgContent", msgContent);
					map.put("receiverList", receiverList);
				}
				
			}
		}
		qAChkReportService.update(report);
		
		map.put("reChkFlag",record.getReChkFlag());
		map.put("reChkResult",record.getReChkResult() );
		map.put("delayFinishTime", DateUtil.dateToString(record.getDelayFinishTime(),"yyyy-MM-dd"));
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	//只是回复的再检查，失效
	public void saveOneReChk2()
	{
		boolean flag = true;
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		QAChkReport report = record.getQachkReport();
		if(model.getReChkFlag()==1)//需要再检查
		{
			flag = false;
			report.setNeedReply(1);
			//report.setNeedReChk(1);//整个报告需要重新检查
		}
		record.setReChkFlag(model.getReChkFlag());
		record.setReChkResult(model.getReChkResult());
		User user = (User)ActionContext.getContext().getSession().get("user");
		record.setReChkInspector(user.getRealName());
		record.setReChkTime(new Date());
		
		qAChkReportRecordService.update(record);
		
		QAReChkRecord reChkRcord = new QAReChkRecord();
		reChkRcord.setQachkReportRecord(record);
		reChkRcord.setReChkInspector(user.getRealName());
		reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
		//reChkRcord.setReChkRecord(reChkRecord);
		if(model.getReChkResult()==0)
			reChkRcord.setReChkResult(-1);
		if(model.getReChkResult()==1)
			reChkRcord.setReChkResult(1);
		reChkRcord.setReChkTime(record.getReChkTime() );
		reChkRcord.setReChkType(1);//1：回复的再检查记录；2：延迟整改再检查记录
		
		qAReChkRecordService.save(reChkRcord);
		
		if(flag)//这次的报告记录不需要再检查了
		{
			boolean isNeedReChk = false;
			List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
			for(QAChkReportRecord recordTemp:records)
			{
				if(recordTemp.getReChkFlag()!=null&&recordTemp.getReChkFlag()==1)//0：不需；1：需要；
				{
					isNeedReChk=true;
				}
			}
			if(!isNeedReChk)
			{
				report.setNeedReply(0);
				//report.setNeedReChk(0);//0：不需要；1：需要
				report.setReChkState(1);//1完成
				map.put("finish", true);
				
				//查看是否已经完成了
				if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
						(report.getNeedReply()==null||report.getNeedReply()==0||
								(report.getNeedReply()==1&&report.getReplyState()==3))&&//报告不需要回复
						(report.getNeedReChk()==null||report.getNeedReChk()==0||
								(report.getNeedReChk()==1&&report.getReChkState()==1))&&//报告不需要再检查
						(report.getDelayState()==null||report.getDelayState()==1)&&//报告延迟整改完成
						report.getRptState()!=9)//报告完成
				{
					report.setRptState(9);
					map.put("rptState",9);
					report.setFinalFinishTime(new Date());
					
					//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
					List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
					String studyNoParam="";
					String reportName="";
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
					//通知QA，报告通过了没有问题
					String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
					
					TblNotification tblNotification = new TblNotification();
					tblNotification.setMsgTitle("QA("+user.getRealName()+")，再检查正常结束，"+reportName+"报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+"，报告完成");//消息头
					String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"再检查正常结束，"+reportName+
					"<br>报告编号:　"+report.getChkReportCode()+"<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>报告完成，特此提醒！";
					tblNotification.setMsgContent(msgContent);
					tblNotification.setMsgType(1);//系统消息
					tblNotification.setSender(getCurrentRealName());// 发送者
					tblNotification.setSendTime(new Date());// 发送时间
					List<String> receiverList = new ArrayList<String>();
					
					
					String userCode = userService.getByRealName(report.getInspector()).getUserName();
					receiverList.add(userCode);
					if(!"".equals(reportName))
					{
						String sdCode = userService.getByRealName(report.getSd()).getUserName();
						receiverList.add(sdCode);
					}
					tblNotificationService.save(tblNotification,receiverList);
				}
				
			}
		}
		qAChkReportService.update(report);
		map.put("reChkFlag",model.getReChkFlag());
		map.put("reChkResult",model.getReChkResult() );
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void updateOneReportRecord()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		//QAChkRecord chkRecord = qAChkRecordService.getById(model.getChkRecordId());
		//chkRecord.setAdvice(model.getAdvice());
		//qAChkRecordService.update(chkRecord);
		
		QAChkReportRecord record = qAChkReportRecordService.getByChkRecordId(model.getChkRecordId());
		if(record!=null)
		{
			record.setAdvice(model.getAdvice());
			qAChkReportRecordService.update(record);
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg","该检查记录还没生成报告");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void saveOneDelay()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkReportRecord record = qAChkReportRecordService.getById(model.getChkReportRecordId());
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		record.setDelayFinishTime(model.getDelayFinishTime());
		record.setNeedDelay(0);
		record.setDelayQainspector(user.getRealName());
		record.setDelayQaconfirmTime(new Date());
		
		qAChkReportRecordService.update(record);
		
		QAChkReport report = record.getQachkReport();
		QAReChkRecord reChkRcord = new QAReChkRecord();
		reChkRcord.setQachkReportRecord(record);
		reChkRcord.setReChkInspector(user.getRealName());
		reChkRcord.setReChkRecordId(qAReChkRecordService.getKey("QAReChkRecord"));
		//reChkRcord.setReChkRecord(reChkRecord);
		reChkRcord.setReChkResult(1);
		reChkRcord.setReChkTime(record.getDelayQaconfirmTime() );
		reChkRcord.setReChkType(2);//1：回复的再检查记录；2：延迟整改再检查记录
		
		qAReChkRecordService.save(reChkRcord);
		
		//if(flag)//这次的报告记录不需要再检查了
		//{
			boolean isNeedDelay = false;
			List<QAChkReportRecord> records = qAChkReportRecordService.getByReportCode(report.getChkReportCode());
			for(QAChkReportRecord recordTemp:records)
			{
				if(recordTemp.getNeedDelay()!=null&&recordTemp.getNeedDelay()!=0)//0：未申请；1：已申请
				{
					isNeedDelay=true;
				}
			}
			if(!isNeedDelay)
			{
				report.setDelayState(1);//0：未完成；1：已完成
				map.put("finish",true);
				//查看是否已经完成了
				if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
						(report.getNeedReply()==null||report.getNeedReply()==0//报告不需要回复
								||(report.getNeedReply()==1&&report.getReplyState()==3))&&//回复已经被qa处理
						(report.getNeedReChk()==null||report.getNeedReChk()==0||//报告不需要再检查
								(report.getNeedReChk()==1&&report.getReChkState()==1))&&
						(report.getNeedDelay()==null||report.getNeedDelay()==0||
								(report.getNeedDelay()==3&&report.getDelayState()==1))&&//报告延迟整改完成
						report.getRptState()!=9)//报告完成
				{
					report.setRptState(9);
					map.put("rptState",9);
					report.setFinalFinishTime(new Date());
					
					//Set<QAChkIndex> chkIndexs = report.getQachkIndexes();
					List<QAChkIndex> chkIndexs = qAChkIndexService.getByReportCode(report.getChkReportCode());
					String studyNoParam=""; 
					String reportName="";
					//if(chkIndexs!=null&&chkIndexs.toArray()!=null)
					//{
					//	QAChkIndex indexs = (QAChkIndex)report.getQachkIndexes().toArray()[0];
						//studyNoParam = indexs.getStudyNo();
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
					//通知QA，报告通过了没有问题
					String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
					TblNotification tblNotification = new TblNotification();
					tblNotification.setMsgTitle("QA("+user.getRealName()+")，再检查延迟整改正常结束，"+reportName+"报告编号:　"+report.getChkReportCode()+" 专题编号:　"+studyNoParam+"，专题名称："+studyNoName+" 报告完成");//消息头
					String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = msgContent+"QA("+user.getRealName()+")于"+currentDate+"再检查延迟整改正常结束，"+reportName+
					"<br>报告编号:　"+report.getChkReportCode()+"<br>专题编号:　 "+studyNoParam+"，<br>专题名称："+studyNoName+"，<br>报告完成，特此提醒！";
					tblNotification.setMsgContent(msgContent);
					tblNotification.setMsgType(1);//系统消息
					tblNotification.setSender(getCurrentRealName());// 发送者
					tblNotification.setSendTime(new Date());// 发送时间
					List<String> receiverList = new ArrayList<String>();
					
					
					String userCode = userService.getByRealName(report.getInspector()).getUserName();
					receiverList.add(userCode);
					
					if(!"".equals(reportName))
					{
						String sdCode = userService.getByRealName(report.getSd()).getUserName();
						receiverList.add(sdCode);
					}
					
					
					tblNotificationService.save(tblNotification,receiverList);
				}else if(report.getQamsignTime()!=null&&!"".equals(report.getQamsignTime())&&
						(report.getNeedReply()!=null&&report.getNeedReply()==1)&&//报告回复还存在
					//	(report.getNeedReChk()==null||report.getNeedReChk()==0)&&//报告不需要再检查
						report.getRptState()!=9)//报告完成
				{
					report.setRptState(3);//回复中
					if(report.getNeedReChk()!=null&&report.getNeedReChk()!=0)//报告需要再检查
					{
						report.setRptState(4);//进入在检查
					}
					
				}
				
			}
		//}
		qAChkReportService.update(report);
		
		map.put("success", true);
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
	
	private String writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
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
		
		return esLink.getLinkId();
		
	}
	
	public QAChkReportRecordService getqAChkReportRecordService() {
		return qAChkReportRecordService;
	}

	public void setqAChkReportRecordService(
			QAChkReportRecordService qAChkReportRecordService) {
		this.qAChkReportRecordService = qAChkReportRecordService;
	}

	public QAChkReportService getqAChkReportService() {
		return qAChkReportService;
	}

	public void setqAChkReportService(QAChkReportService qAChkReportService) {
		this.qAChkReportService = qAChkReportService;
	}

	public QAReChkRecordService getqAReChkRecordService() {
		return qAReChkRecordService;
	}

	public void setqAReChkRecordService(QAReChkRecordService qAReChkRecordService) {
		this.qAReChkRecordService = qAReChkRecordService;
	}
	public QAChkRecordService getqAChkRecordService() {
		return qAChkRecordService;
	}
	public void setqAChkRecordService(QAChkRecordService qAChkRecordService) {
		this.qAChkRecordService = qAChkRecordService;
	}
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
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
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
	}

	public String getSignReChkRecord() {
		return signReChkRecord;
	}

	public void setSignReChkRecord(String signReChkRecord) {
		this.signReChkRecord = signReChkRecord;
	}



}
