package com.lanen.view.action;

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
import com.lanen.model.qa.QAApprovalOpinion;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkPlanChangeIndex;
import com.lanen.model.qa.QAChkPlanHis;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAApprovalOpinionService;
import com.lanen.service.qa.QAChkPlanChangeIndexService;
import com.lanen.service.qa.QAChkPlanHisService;
import com.lanen.service.qa.QAChkPlanService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkPlanChangeIndexAction extends BaseAction<QAChkPlanChangeIndex>{

	private static final long serialVersionUID = 501482584418963909L;
	//变更申请
	@Resource
	private QAChkPlanChangeIndexService qAChkPlanChangeIndexService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService ;
	@Resource
	private QAChkPlanService qAChkPlanService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private QAApprovalOpinionService qAApprovalOpinionService;
	@Resource
	private QAChkPlanHisService qAChkPlanHisService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private UserService userService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	
	private String studyNoParam;
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public void isExistApply() {
		//查看是否存在还没有批复的变更申请
		boolean isExist = qAChkPlanChangeIndexService.getByStudyNoAndStatue(studyNoParam);
		Map<String, Object> map = new HashMap<String, Object>();
		if(!isExist)
		{
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "本专题已经存在一个还没完成的变更申请");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void save() {
		Map<String, Object> map = new HashMap<String, Object>();
			QAChkPlanChangeIndex index = new QAChkPlanChangeIndex();
			String chkPlanChangeIndexId = qAChkPlanChangeIndexService.getKey("QAChkPlanChangeIndex");
			index.setChkPlanChangeIndexId(chkPlanChangeIndexId);
			index.setChangeDate(DateUtil.stringToDate(DateUtil.getNow(""),"yyyy-MM-dd HH:mm:ss"));
			User user = (User) ActionContext.getContext().getSession().get("user");
			index.setOperator(user.getRealName());
			
			QAStudyChkIndex qastudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
			index.setQastudyChkIndex(qastudyChkIndex);
			index.setChangeState(0);//0：原始；1：提交；-1：否决；2：通过
			//获取检查计划的版本
			Integer sn = qAChkPlanService.getVersionByStudyNo(studyNoParam);
			index.setSn(sn);//1、2、3.。。是检查计划的当前版本
			index.setReason(model.getReason());
			
			qAChkPlanChangeIndexService.save(index);
			
			//QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
			//studyChkIndex.setChkPlanState(3);//申请变更
			//qAStudyChkIndexService.update(studyChkIndex);
			//把目前的计划复制过去
			List<QAChkPlan> plans = qAChkPlanService.getByStudyNo(studyNoParam);
			for(QAChkPlan plan:plans)
			{
				QAChkPlanHis planHis = new QAChkPlanHis();
			
				planHis.setChkPlanHisId(qAChkPlanHisService.getKey("QAChkPlanHis"));
				
				planHis.setChkItemId(plan.getChkItemId());
				planHis.setChkItemName(plan.getChkItemName());
				planHis.setChkPlanId(qAChkPlanHisService.getKey("QAChkPlanHis"));
				planHis.setChkPlanType(plan.getChkPlanType());
				planHis.setChkPlanVersion(-1);
				
				planHis.setCreateTime(plan.getCreateTime());
				planHis.setNumber(plan.getNumber());
				planHis.setPlanChkArea(plan.getPlanChkArea());
				planHis.setPlanChkOperator(plan.getPlanChkOperator());
				planHis.setPlanChkTime(plan.getPlanChkTime());
				planHis.setStudyNo(studyNoParam);
				planHis.setScheduleChkItemId(plan.getScheduleChkItemId());
				planHis.setScheduleId(plan.getScheduleId());
				planHis.setScheduleName(plan.getScheduleName());
				planHis.setScheduleTime(plan.getScheduleTime());
				planHis.setSOPFlag(plan.getSOPFlag());
				planHis.setTaskName(plan.getTaskName());
				planHis.setTaskNameId(plan.getTaskName());
				planHis.setSOPFlag(plan.getSOPFlag());
			
				qAChkPlanHisService.save(planHis);
			}
			
			
			
			
			map.put("success", true);
			writeJson(JsonPluginsUtil.beanToJson(map));
			
	}
	public void getPlanApply()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		QAChkPlanChangeIndex changeIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		if(changeIndex==null)
		{
			map.put("success", false);
			map.put("msg", "该专题没有申请检查计划变更");
		}else {
			map.put("success", true);
			map.put("reason", changeIndex.getReason());
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void backupAndUpdateStudyPlan(String studyNoParam)
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
			his.setScheduleName(plan.getScheduleName());
			his.setScheduleTime(plan.getScheduleTime());
			his.setStudyNo(plan.getQastudyChkIndex().getStudyNo());
			his.setTaskName(plan.getTaskName());
			his.setTaskNameId(plan.getTaskNameId());
			
			qAChkPlanHisService.save(his);
			
			plan.setChkPlanVersion(plan.getChkPlanVersion()+1);
			qAChkPlanService.update(plan);
		}
		
		
		
	}
	public void qamApprovalApply()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String resultString="";
		QAStudyChkIndex studyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNoParam);
		
		User user = (User)ActionContext.getContext().getSession().get("user");
		QAChkPlanChangeIndex changeIndex = qAChkPlanChangeIndexService.getByStudyNoAndNoApproval(studyNoParam);
		changeIndex.setChangeState(model.getChangeState());
		if(model.getChangeState()==2)
		{
			changeIndex.setApprovalTime(new Date());
			changeIndex.setApprover(user.getRealName());			
		}
		qAChkPlanChangeIndexService.update(changeIndex);
		
		QAApprovalOpinion opinion = new QAApprovalOpinion();
		opinion.setApprovalName(user.getRealName());
		opinion.setApprovalOpinion(model.getReason());
		//opinion.setApprovalRecordId(changeIndex.getChkPlanChangeIndexId());
		opinion.setApprovalRecordId(qAApprovalOpinionService.getKey("QAApprovalOpinion"));
		if(model.getChangeState()==2)
		{
			opinion.setApprovalResultFlag(1);
			resultString="通过";
			opinion.setApprovalResult("通过");
			studyChkIndex.setChkPlanState(4);//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			backupAndUpdateStudyPlan(studyNoParam);
			
		}else {
			opinion.setApprovalResultFlag(-1);
			opinion.setApprovalResult("退回");
			resultString="退回";
			studyChkIndex.setChkPlanState(2);//0：草稿；1：提交；-1：QAM否决；2：通过  3申请修改 4批准修改
			
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
		
		map.put("state", studyChkIndex.getChkPlanState());
		
		//通知QA
		//当前时间
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
		
		String msgTitle=("QAM"+resultString+"了检查计划的变更申请,专题编号：　"+studyNoParam+"，专题名称："+studyNoName);
		String msgContent = "<br>";
		
		/*if(null != studyItem.getFinishDate() && !studyItem.getFinishDate().equals("")){
			findDate = "要求完成日期为："+DateUtil.dateToString(studyItem.getFinishDate(),"yyyy-MM-dd");
		}*/
		msgContent = msgContent+"QAM("+user.getRealName()+")于"+currentDate+resultString+"了检查计划变更的申请，<br>专题编号： " +studyNoParam+"，<br>专题名称："+studyNoName+"，<br>特此提醒";
		
		//接收者列表
		String receiverList = "";
		//获取QA
		String qa = studyChkIndex.getInspector();
		if(qa!=null&&!"".equals(qa))
		{
			User qaUser = userService.getByRealName(qa.trim());
			receiverList+=(qaUser.getUserName())+",";
		}
		
		map.put("msgTitle", msgTitle);
		map.put("msgContent", msgContent);
		map.put("receiverList", receiverList);
		
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
	
	
	
	public QAChkPlanChangeIndexService getqAChkPlanChangeIndexService() {
		return qAChkPlanChangeIndexService;
	}
	public void setqAChkPlanChangeIndexService(
			QAChkPlanChangeIndexService qAChkPlanChangeIndexService) {
		this.qAChkPlanChangeIndexService = qAChkPlanChangeIndexService;
	}
	public String getStudyNoParam() {
		return studyNoParam;
	}
	public void setStudyNoParam(String studyNoParam) {
		this.studyNoParam = studyNoParam;
	}




	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}




	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}




	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}




	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
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
	public QAApprovalOpinionService getqAApprovalOpinionService() {
		return qAApprovalOpinionService;
	}
	public void setqAApprovalOpinionService(
			QAApprovalOpinionService qAApprovalOpinionService) {
		this.qAApprovalOpinionService = qAApprovalOpinionService;
	}
	public QAChkPlanHisService getqAChkPlanHisService() {
		return qAChkPlanHisService;
	}
	public void setqAChkPlanHisService(QAChkPlanHisService qAChkPlanHisService) {
		this.qAChkPlanHisService = qAChkPlanHisService;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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

}
