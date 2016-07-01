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
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictScheduleChkItem;
import com.lanen.model.qa.QAChkPlan;
import com.lanen.model.qa.QAChkPlanHis;
import com.lanen.model.qa.QAChkPlan_JSON;
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
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictScheduleChkItemService;
import com.lanen.service.qa.QAChkPlanChangeIndexService;
import com.lanen.service.qa.QAChkPlanHisService;
import com.lanen.service.qa.QAChkPlanService;
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
public class QAStudyChkIndexAction extends BaseAction<QAStudyChkIndex>{

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
	
	private String studyNoPara;
	private String index;
	private String newStudyNo;
	private String qa;
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public void uniqueCheck()
	{
		//调用service方法返回是否存在主键
		boolean isExisit = qAStudyChkIndexService.isExistByStudyNo(studyNoPara);
		if (!isExisit && !studyNoPara.equals(null) ) {
			writeJson("no");
			//response.getWriter().print("no");
		} else {
			
			writeJson("is");
		}

	}
	public void studyNOCheck()
	{
		if(null!=newStudyNo && !newStudyNo.isEmpty()){
			boolean isExisit = qAStudyChkIndexService.isExistById(newStudyNo);
			if(!isExisit){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	
	public void addSave()
	{
		TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(newStudyNo);
		if(qa!=null&&studyItem!=null&&!qa.equals(studyItem.getQa()))
		{
			studyItem.setQa(qa);
			studyItem.setQaCode(userService.getByRealName(qa).getUserCode());
			studyItem.setQaAppointDate(new Date());
			studyItem.setQaState(1);
			tblStudyItemService.update(studyItem);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		QAStudyChkIndex studyChkIndex = new QAStudyChkIndex();
		studyChkIndex.setStudyNo(newStudyNo);
		studyChkIndex.setInspector(qa);
		//studyChkIndex.setSd(model.getSd());
		String sd = "";
		if(studyItem!=null&&studyItem.getSd()!=null)
		{
			sd=studyItem.getSd();
		}else {
			 sd = tblStudyPlanService.getSDByStudyNo(newStudyNo);
		}
		studyChkIndex.setSd(sd);
		
		studyChkIndex.setInspectorAppointState(1);
		studyChkIndex.setInspectorAppointTime(new Date());
		
		qAStudyChkIndexService.save(studyChkIndex);
		
		//签名写入
		writeES("QAM手动增加专题",824,"QAStudyChkIndex",newStudyNo);
		//日志录入
		writeLog("手动增加QA专题","QAM手动增加专题","QAM手动增加专题,专题编号："+newStudyNo);
		
		//发邮件
		//通知QA他有了新的专题
		String studyNoName = "";
		if (studyItem!=null) {
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			
		}
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
		String msgTitle=("QAM("+getCurrentRealName()+") 新增专题，专题编号："+newStudyNo+"，专题名称："+studyNoName+"　，任命你为专题的QA检查员");//消息头
		String msgContent = "<br>";
		msgContent = msgContent+"QAM("+getCurrentRealName()+")于"+currentDate+"新增了专题，"+
		"<br>专题编号:"+newStudyNo+"，<br>专题名称:"+studyNoName+"，<br>任命你为专题的QA检查员，特此提醒！";
	
		String receiverList = "";
		receiverList += userService.getByRealName(qa).getUserName()+",";
		
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
	
	public QAChkPlanService getqAChkPlanService() {
		return qAChkPlanService;
	}
	public void setqAChkPlanService(QAChkPlanService qAChkPlanService) {
		this.qAChkPlanService = qAChkPlanService;
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}
	public void setStudyNoPara(String studyNoParam) {
		this.studyNoPara = studyNoParam;
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
	
	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}
	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public String getNewStudyNo() {
		return newStudyNo;
	}
	public void setNewStudyNo(String newStudyNo) {
		this.newStudyNo = newStudyNo;
	}
	public String getQa() {
		return qa;
	}
	public void setQa(String qa) {
		this.qa = qa;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
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
	
	
}
