package com.lanen.view.action.studyplan;


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
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblApplyReviseAction extends BaseAction<TblApplyRevise>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 签名连接Service
	 */
	@Resource
	private TblESLinkService tblESLinkService;
	
	/**
	 * 电子签名Service
	 */
	@Resource
	private TblESService tblESService;
	

	/**试验项目（委托项目）service*/
	@Resource
	private TblStudyItemService tblStudyItemService;
	
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	//通知信息
	@Resource
	private TblNotificationService tblNotificationService ;
	
	private String studyNoPara;
	
	private Integer continueFlag;//0试验计划，1日程，2两者
	
	public void  applyRevise(){
		String desc="";
		if(continueFlag==0||continueFlag==2)
		{
			desc="试验计划";
		}
		if(continueFlag==1||continueFlag==2)
		{
			if("试验计划".equals(desc))
			{
				desc+="和";
			}
			desc+="日程";
		}
		//根据课题编号获得试验计划的信息
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setEsType(201);
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("申请"+desc+"修改");
		if(continueFlag==0||continueFlag==2)
		{
			//修改专题状态
			//tblStudyPlan.setStudyState("2");
			tblStudyPlan.setStudyState("3");//FM审批通过
		}
		if(continueFlag==1||continueFlag==2)
		{
			//修改专题状态
			//tblStudyPlan.setScheduleState(2);
			tblStudyPlan.setScheduleState(3);//FM审批通过
		}
		
		//申请表
		TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(studyNoPara);
		
		//如果是一次申请两个则版本一样
		TblApplyRevise applyRevise = new TblApplyRevise();
		TblApplyRevise applyRevise2 = null;
		
		applyRevise.setApplyDate(new Date());
		applyRevise.setApprovalDate(new Date());
		//applyRevise.setApplyFlag(0);
		applyRevise.setApplyFlag(1);//FM审批通过
		
		applyRevise.setApplyUser(tempUser.getUserName());
		applyRevise.setReason(model.getReason());
		applyRevise.setId(tblApplyReviseService.getKey());
		applyRevise.setStudyNo(studyNoPara);
		applyRevise.setSubmitDate(new Date());
		
		if(tblApplyRevise == null){
			//applyRevise.setVersion(0);
			applyRevise.setVersion(1);//审批通过为1
		}else{
			//一个专题最多同时存在两个申请，一个专题基本信息，一个日程信息的修改申请。
			if(tblApplyRevise!=null&&tblApplyRevise.getApplyFlag()==1)
			{
				//如果已经审批，则版本为审批过的版本
				//applyRevise.setVersion(tblApplyRevise.getVersion());	
				applyRevise.setVersion(tblApplyRevise.getVersion()+1);//审批通过版本加1
			}else {
				//如果还存在的申请没有审批，则版本加1
				//applyRevise.setVersion(tblApplyRevise.getVersion()+1);
				applyRevise.setVersion(tblApplyRevise.getVersion()+2);
			}
		}
		if (continueFlag==2) {
			applyRevise.setType(0);//0计划   1日程   
			
			applyRevise2 = new TblApplyRevise();
			applyRevise2.setApplyDate(new Date());
			applyRevise.setApprovalDate(new Date());
			applyRevise2.setApplyFlag(1);//FM审批通过
			
			applyRevise2.setType(1);//0计划   1日程   2两者
			applyRevise2.setApplyUser(tempUser.getUserName());
			applyRevise2.setReason(model.getReason());
			applyRevise2.setId(tblApplyReviseService.getKey());
			applyRevise2.setStudyNo(studyNoPara);
			applyRevise2.setSubmitDate(new Date());
			
			if(tblApplyRevise == null){
				//applyRevise2.setVersion(0);
				applyRevise2.setVersion(1);
			}else{
				//一个专题最多同时存在两个申请，一个专题基本信息，一个日程信息的修改申请。
				if(tblApplyRevise!=null&&tblApplyRevise.getApplyFlag()==1)
				{
					//如果已经审批，则版本为审批过的版本
					//applyRevise2.setVersion(tblApplyRevise.getVersion());	
					applyRevise2.setVersion(tblApplyRevise.getVersion()+1);
				}else {
					//如果还存在的申请没有审批，则版本加1
					//applyRevise2.setVersion(tblApplyRevise.getVersion()+1);	
					applyRevise2.setVersion(tblApplyRevise.getVersion()+2);	
				}
			}
			
		}else {
			applyRevise.setType(continueFlag);//0计划   1日程   
		}
		esLink.setTableName("TblStudyInfo");
		esLink.setDataId(tblStudyPlan.getStudyNo());
		esLink.setTblES(es);
		esLink.setEsType(201);
		esLink.setEsTypeDesc("申请"+desc+"修改");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	
		TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNoPara);
		String studyNoName = "";
		if(null != studyItem){
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			if(dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
		}else{
			studyNoName = tblStudyPlan.getStudyName();
		}
		
		boolean falg = false;
		Map<String,Object> map  = new HashMap<String,Object>();
		try{
		   tblESService.save(es);
		   tblESLinkService.save(esLink);
           tblApplyReviseService.save(applyRevise);
           if (continueFlag==2) {
        	   tblApplyReviseService.save(applyRevise2);
           }
		   tblStudyPlanService.update(tblStudyPlan);
		   
		   //备份所有的版本表
		   tblApplyReviseService.backUpAllByStudyNo(studyNoPara);
			
		   //日志录入
		   writeLog("签字","课题："+studyNoPara+" SD :"+tempUser.getRealName()+"申请修改专题信息");
		   map.put("success", true);
		   falg = true;
		}catch(Exception e){
			 map.put("success", false);
			 falg = false;
		     map.put("msg", "与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
		if(falg){
			//发消息
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			//接收者列表
			List<String> receiverList = userService.findUserNameByPrivilegeName("批准修改");
			TblNotification tblNotification = new TblNotification();
			tblNotification.setMsgTitle("专题编号：　"+studyNoPara+"专题名称：　"+studyNoName+"，专题信息申请修改！");//消息头
			//String msgContent = "区域负责人，您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			msgContent = msgContent+""+tempUser.getRealName()+"于"+currentDate+"申请修改"+
			studyNoPara+"的专题信息，修改原因:"+model.getReason()+"，请查阅审批，特此提醒！";
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			tblNotificationService.save(tblNotification,receiverList);
		}	
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**查询申请信息*/
	public void approvalRevise(){
		List<TblApplyRevise> tblApplyRevise = tblApplyReviseService.getByStudyNo2(studyNoPara);
		String reason = "";
		String studyNo = "";
		Integer type=0;
		Map<String,Object> map  = new HashMap<String,Object>();
		if(tblApplyRevise!= null&&tblApplyRevise.size()>0){
			reason = tblApplyRevise.get(0).getReason();
			studyNo = tblApplyRevise.get(0).getStudyNo();
			type = tblApplyRevise.get(0).getType();
			String itemName = "";
			if(type==null||type==0)
			{
				itemName = "试验计划";
			}else if(type==1)
			{
				itemName = "日程";
			}else if(type==2)
			{
				itemName = "试验计划和日程";
			}
			reason="专题"+studyNo+"的"+itemName+",因"+reason+"申请修改";
			if(tblApplyRevise.size()>=2)
			{
				if(tblApplyRevise.get(1)==null||tblApplyRevise.get(1).getApprovalDate()==null||"".equals(tblApplyRevise.get(1).getApprovalDate())
						||tblApplyRevise.get(1).getApprovalUser()==null||"".equals(tblApplyRevise.get(1).getApprovalUser()))
				{
					String reason2=tblApplyRevise.get(1).getReason();
					Integer type2 = tblApplyRevise.get(1).getType();
					if((type==null||type==0)&&type2==1)//上一次是计划修改,这次是日程
					{
						type=2;
					}
					if((type2==null||type2==0)&&type==1)//这一次是计划修改,上次是日程
					{
						type=2;
					}
					
					if(type2==null||type2==0)
					{
						itemName = "试验计划";
					}
					if(type2==1)
					{
						itemName = "日程";
					}
					if(type2==2)
					{
						itemName = "试验计划和日程";
					}
					reason+="<br>专题"+studyNo+"的"+itemName+",因"+reason2+"申请修改";
					
				}
			}
		}
		map.put("type",type );
		map.put("reason", reason);
		map.put("studyNo",studyNo);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**签字批准*/
	public void signAllowApprovalRevise(){
		Map<String,Object> map  = new HashMap<String,Object>();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		List<TblApplyRevise> tblApplyRevises = tblApplyReviseService.getByStudyNo2(studyNoPara);
		if(null != tblApplyRevises&&tblApplyRevises.size()>0){
			//接收者列表
			List<String> receiverList = new ArrayList<String>();
			String str ="";
			String refusalReason = "";
			//根据课题编号获得试验计划的信息
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
			
			if(continueFlag==2)//两个都批了
			{
				for(TblApplyRevise tblApplyRevise:tblApplyRevises)
				{
					tblApplyRevise.setApprovalDate(new Date());
					tblApplyRevise.setApplyFlag(model.getApplyFlag());
					tblApplyRevise.setApprovalUser(tempUser.getUserName());
					receiverList.add(tblApplyRevise.getApplyUser());
					//根据课题编号获得试验计划的信息
				//	TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
					if(model.getApplyFlag() == 1){
						tblApplyRevise.setVersion((tblApplyRevise.getVersion()+1));
						tblApplyReviseService.update(tblApplyRevise);
						str = "允许";
						//修改专题状态
						if(tblApplyRevise.getType()==null||tblApplyRevise.getType()==0||tblApplyRevise.getType()==2)
						{
							tblStudyPlan.setStudyState("3");
						}
						if(tblApplyRevise.getType()==1||tblApplyRevise.getType()==2)
						{
							tblStudyPlan.setScheduleState(3);
						}
					}else{
						tblApplyRevise.setRefusalReason(model.getRefusalReason());
						tblApplyReviseService.update(tblApplyRevise);
						str = "拒绝";
						//修改专题状态
						//tblStudyPlan.setStudyState("1");
						if(tblApplyRevise.getType()==null||tblApplyRevise.getType()==0||tblApplyRevise.getType()==2)
						{
							tblStudyPlan.setStudyState("1");
						}
						if(tblApplyRevise.getType()==1||tblApplyRevise.getType()==2)
						{
							tblStudyPlan.setScheduleState(1);
						}
						refusalReason = "拒绝理由为："+model.getRefusalReason()+",";
					}
				}
			}else if(continueFlag==0){
				for(TblApplyRevise tblApplyRevise:tblApplyRevises)
				{
					if(tblApplyRevise.getType()==null||tblApplyRevise.getType()==0)
					{
						tblApplyRevise.setApprovalDate(new Date());
						tblApplyRevise.setApplyFlag(model.getApplyFlag());
						tblApplyRevise.setApprovalUser(tempUser.getUserName());
						receiverList.add(tblApplyRevise.getApplyUser());
					}
					//根据课题编号获得试验计划的信息
					//TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
					if(model.getApplyFlag() == 1){
						tblApplyRevise.setVersion((tblApplyRevise.getVersion()+1));
						tblApplyReviseService.update(tblApplyRevise);
						str = "允许";
						//修改专题状态
						tblStudyPlan.setStudyState("3");
					}else{
						tblApplyRevise.setRefusalReason(model.getRefusalReason());
						tblApplyReviseService.update(tblApplyRevise);
						str = "拒绝";
						//修改专题状态
						tblStudyPlan.setStudyState("1");
						
						refusalReason = "拒绝理由为："+model.getRefusalReason()+",";
					}
				}
				
			}else {
				for(TblApplyRevise tblApplyRevise:tblApplyRevises)
				{
					if(tblApplyRevise.getType()==1)
					{
						tblApplyRevise.setApprovalDate(new Date());
						tblApplyRevise.setApplyFlag(model.getApplyFlag());
						tblApplyRevise.setApprovalUser(tempUser.getUserName());
						receiverList.add(tblApplyRevise.getApplyUser());
						
						
						if(model.getApplyFlag() == 1){
							tblApplyRevise.setVersion((tblApplyRevise.getVersion()+1));
							tblApplyReviseService.update(tblApplyRevise);
							str = "允许";
							//修改专题状态
							tblStudyPlan.setScheduleState(3);
						}else{
							tblApplyRevise.setRefusalReason(model.getRefusalReason());
							tblApplyReviseService.update(tblApplyRevise);
							str = "拒绝";
							//修改专题状态
							tblStudyPlan.setScheduleState(1);
							
							refusalReason = "拒绝理由为："+model.getRefusalReason()+",";
						}
					}
				}
			}
			
		
			
			//发消息
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			
			//receiverList.add(tblApplyRevise.getApplyUser());
			TblNotification tblNotification = new TblNotification();
		
		
			TblESLink esLink = new TblESLink();
			TblES es = new TblES();
			es.setEsType(202);
			es.setDateTime(new Date());
			es.setEsId(tblESService.getKey("TblES"));
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("同意申请专题修改");
			esLink.setTableName("TblStudyInfo");
			esLink.setDataId(tblStudyPlan.getStudyNo());
			esLink.setTblES(es);
			esLink.setEsType(202);
			esLink.setEsTypeDesc("同意申请专题修改");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			boolean falg = false;
			try{
				tblESService.save(es);
				tblESLinkService.save(esLink);
				tblStudyPlanService.update(tblStudyPlan);
				if(model.getApplyFlag() == 1){
					//备份所有的版本表
					tblApplyReviseService.backUpAllByStudyNo(studyNoPara);
				}
				//日志录入
				writeLog("签字","课题："+studyNoPara+" SD :"+tempUser.getRealName()+"审批修改专题信息");
				map.put("success", true);
				falg = true;
			}catch(Exception e){
				map.put("success", false);
				falg = false;
				map.put("msg", "与数据库交互异常");
				System.out.println("执行失败，出错种类"+e.getMessage()+".");
			}finally{ 
				System.out.println("执行结束");
			} 
			if(falg){
				tblNotification.setMsgTitle("专题编号：　"+studyNoPara+"，专题信息"+str+"申请修改！");//消息头
				//String msgContent = "区域负责人，您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				msgContent = msgContent+""+tempUser.getRealName()+"于"+currentDate+"审批了专题"+
				studyNoPara+"的专题申请修改信息，"+str+"专题申请修改，"+refusalReason+"特此提醒！";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				tblNotification.setSender(getCurrentRealName());// 发送者
				tblNotification.setSendTime(new Date());// 发送时间
				tblNotificationService.save(tblNotification,receiverList);
			}
		}else{
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("课题");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}
	public Integer getContinueFlag() {
		return continueFlag;
	}
	public void setContinueFlag(Integer continueFlag) {
		this.continueFlag = continueFlag;
	}
	
	

}
