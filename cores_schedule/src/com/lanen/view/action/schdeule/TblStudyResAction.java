package com.lanen.view.action.schdeule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.schedule.TblStudyRes;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyInfoService;
import com.lanen.service.schdeule.TblStudyResService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblStudyResAction extends BaseAction<TblStudyRes>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private TblStudyResService tblStudyResService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	
	@Resource
	private TblResManagerService tblResManagerService;

	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	
	@Resource
	private TblESService tblESService;
	/** 用户Service */
	@Resource
	private UserService UserService;
	
	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	
	/**试验项目（委托项目）service*/
	@Resource
	private TblStudyItemService tblStudyItemService;
	
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	
	private String resName;
	
	private String allStudyRes;
	
	private String esType;
	
	private Date startime;
	private Date endtime;
	private String theAuditId;
	
	private String resIds;
	//是否显示日期区间内的所有 试验安置
	int isSelectAllStudy = 0;
	
	private String presID;
	
	/** 列表*/
	public String list() throws Exception {
		ActionContext.getContext().put("isSelectAllStudy",isSelectAllStudy);
		ActionContext.getContext().put("studyNo",model.getStudyNo());
		Date nowDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		calendar.add(Calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
		Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    	String endtime=sdf.format(nowDate); 
    	String startime = new SimpleDateFormat("yyyy-MM-dd").format(date);
    	ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		List<TblTaskTypeLeader> tblTaskTypeLeaderList  = tblTaskTypeLeaderService.getByTaskTypeIDList("2");
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String userName = tempUser.getUserName();
		for(TblTaskTypeLeader obj:tblTaskTypeLeaderList){
			if(obj.getTaskLeader().equals(userName)){
				ActionContext.getContext().put("selectRes", true);
				break;
			}
		}
		return "list";
	}
   //tblStudyInfoService.getByStudyNo(studyNo);
	/**list加载数据(json)*/
	public void loadList() throws Exception {
		 List<?> schedulePlanList = tblSchedulePlanService.getStudyNoRes(startime, endtime,isSelectAllStudy);
		 //新的课题编号，未保存到专题安置表中
		 List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		 Map<String,Object> map = null;
		 String currentStudyNo = "";//当前课题编号
		 String resNames="";
		 int  maxStage =0;
		 //String currentAudit="";
		 String pResName = "";
		 boolean falg = false;
		 List<TblTaskTypeLeader> tblTaskTypeLeaderList  = tblTaskTypeLeaderService.getByTaskTypeIDList("2");
		 User tempUser = (User) ActionContext.getContext().getSession().get("user");
			String userName = tempUser.getUserName();
			for(TblTaskTypeLeader obj:tblTaskTypeLeaderList){
				if(obj.getTaskLeader().equals(userName)){
					falg = true;
				}
			}
		 //List<TblResManager> tblResManager = tblResManagerService.getByResManager(tempUser.getUserName());
		 if(null != schedulePlanList){
			 for(Object obj:schedulePlanList){
					
				 pResName = "";
				 Object[] objs = (Object[]) obj;
				 String studyNo = (String) objs[0];
				 //String resId = (String) objs[4];
				 TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(studyNo);
				 if(!falg){
					 if(tblStudyInfo != null){
						  String studyResId	=  tblStudyInfo.getResID();
						  Boolean falg1 =  tblResManagerService.getByResManagerAndResID(tempUser.getUserName(),studyResId);
						  if(!falg1){
							  continue;
						  }
					 }else{
						 continue;
					 }
				 }
				    //仅显示未审核数据
					if(isSelectAllStudy == 0){
						 if(tblStudyInfo != null && tblStudyInfo.getScheduleReviewSignID()!= null){
							  continue;
						 }
			        //显示所有数据
					}else if(isSelectAllStudy == 2){
						
					}
				 String resName = (String) objs[1];
				 Integer state = (Integer) objs[2];
				 //String audit =   objs[3] == null ? "": (String)objs[3] ;
				 
				 if(state == -1){
//					 newStudyNoSet.add(studyNo);
					 state = 0;
				 }
				 if(resName == null ){
					 resName ="";
				 }else if(state == 1){
					 //未签字
					 resName = "<a style='color:red;margin-left:5px;'>"+resName+"</a>";
				 }else{
					 resName = "<a style='color:black;margin-left:5px;'>"+resName+"</a>";
				 }
				 if(currentStudyNo.equals("") || currentStudyNo.equals(studyNo)){
					 currentStudyNo = studyNo;
					 resNames = resNames+resName;
					 maxStage = maxStage >state ? maxStage:state ;
					// currentAudit = currentAudit.equals("") ? audit :currentAudit;
				 }else{
					 map = new HashMap<String,Object>();
					 map.put("studyNo", currentStudyNo);
					 map.put("resName", resNames);
					 map.put("state", maxStage);
					
					 TblStudyInfo studyInfo = tblStudyInfoService.getByStudyNo(currentStudyNo);
					 pResName = "";
					 String siger  = "" ;
					 if(studyInfo != null){
						 TblAnimalHouse  tblAnimalHouse = tblAnimalHouseService.getById(studyInfo.getResID());
						 pResName = tblAnimalHouse.getResName();
						 if(null != studyInfo.getScheduleReviewSignID()){
							 TblES tblES = tblESService.getById(studyInfo.getScheduleReviewSignID());
							 siger = tblES.getSigner(); 
						 }
					
					 }
					 map.put("auditId", siger);
					 map.put("pResName", pResName);
					 if( studyInfo != null ){
						 map.put("pResID", studyInfo.getResID());
					 }
					
					 mapList.add(map);
					 
					 currentStudyNo = studyNo;//当前课题编号
					 resNames=resName;
					 maxStage =state;
				 }
			 }
		 }
		 map = new HashMap<String,Object>();
		 if(null != currentStudyNo && !"".equals(currentStudyNo)){
			 map.put("studyNo", currentStudyNo);
			 map.put("resName", resNames);
			 map.put("state", maxStage);
			// map.put("auditId", currentAudit);
			 TblStudyInfo studyInfo = tblStudyInfoService.getByStudyNo(currentStudyNo);
			 pResName = "";
			 String siger  = "" ;
			 if(studyInfo != null){
				 TblAnimalHouse  tblAnimalHouse = tblAnimalHouseService.getById(studyInfo.getResID());
				 pResName = tblAnimalHouse.getResName();
				 if(null != studyInfo.getScheduleReviewSignID()){
					 TblES tblES = tblESService.getById(studyInfo.getScheduleReviewSignID());
					 siger = tblES.getSigner(); 
				 }
			
			 }
			 map.put("auditId", siger);
			 map.put("pResName", pResName);
			 if(null != studyInfo){
				 map.put("pResID", studyInfo.getResID());
			 }
			
			 mapList.add(map);
		 }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	/**房间设置页面*/
	public String setUI() throws Exception {
		ActionContext.getContext().put("studyNo",model.getStudyNo() );
		ActionContext.getContext().put("pResID",presID);
		return "setUI";
	}
	/**加载单个课题对应房间*/
	public void loadOneStudyRes(){
		 List<?> studyResList = tblStudyResService.getResNameSignerByStudyNo(model.getStudyNo());
		 //rows
		 List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		 Map<String,Object> map = null;
		 if(null != studyResList){
			 for(Object obj:studyResList){
				 Object[] objs = (Object[]) obj;
				 String id = (String) objs[0];
				 String studyNo = (String) objs[1];
				 String resName = (String) objs[2];
				 String signer = (String) objs[3];
				
				 map = new HashMap<String,Object>();
				map.put("id", id);
				map.put("studyNo", studyNo);
				map.put("resName", resName);
				map.put("signer", signer);
				mapList.add(map);
			 }
		 }
		 String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	/**删除房间*/
	public void delete() throws Exception {
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId())){
			//多个删除
			String ids = model.getId();
			String[] strarray = ids.split(",");
			String[] arrays = new String[strarray.length];
			List<String> singIdList = new ArrayList<String>();
			for (int j = 0; j < strarray.length; j++) {
				arrays[j] = strarray[j];
				singIdList.add(strarray[j]);
			}
			
			List<TblStudyRes> tblStudyResList = tblStudyResService.getByIds(arrays);
			if(null != tblStudyResList){
				boolean falg = false;
				String str = "";
				for(TblStudyRes obj:tblStudyResList){
					if(obj.getSignId()!= null && !obj.getSignId().equals("")){
						falg = true;
						str = obj.getStudyNo();
						break;
					}
				}
				if(falg){
					//不存在
					json.setMsg(str +"已签字，不可重复删除");
					json.setSuccess(false);
				}else{
					//tblStudyResService.delete(model.getId());
					tblStudyResService.detAllStudyRes(singIdList);
					json.setSuccess(true);
				}
			}else{
				//不存在
				json.setMsg("数据交互错误");
			}
		}else{
			json.setMsg("数据交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**添加课题房间*/
	public void addStudyRes()throws Exception{
		Json json = new Json ();
		String studyNo = model.getStudyNo();
	    List<String> ids = new ArrayList<String>();
		if(null != studyNo && !"".equals(studyNo) && null != resIds && !"".equals(resIds)){
			String[] resId = resIds.split(",");
			//是否存在已添加的房间
			boolean isExist = false;
			String currentResId ="";
			for(int i = 0;i<resId.length ;i++){
				currentResId = resId[i];
				isExist = tblStudyResService.isExist(studyNo,currentResId);
				if(isExist){//已存在
					
					break;
				}
			}
			if(!isExist){
				List<TblStudyRes> tblStudyResList = new ArrayList<TblStudyRes>();
				TblStudyRes tblStudyRes = null;
				for(int i = 0;i<resId.length ;i++){
					tblStudyRes = new TblStudyRes();
					String id = tblStudyResService.getKey();
					tblStudyRes.setId(id);
					tblStudyRes.setStudyNo(studyNo);
					tblStudyRes.setResId(resId[i]);
					tblStudyRes.setState(1);
					tblStudyResList.add(tblStudyRes);
					ids.add(id);
				}
				tblStudyResService.saveAllStudyRes(tblStudyResList);
				json.setSuccess(true);
				json.setMsg("专题房间添加成功");
			}else{
				TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(currentResId);
				if(null != tblAnimalHouse){
					String resName = tblAnimalHouse.getResName();
					json.setMsg("房间:"+resName+" 已添加");
				}
			}
		}else{
			json.setMsg("与服务器交互错误");
		}
		json.setObj(ids);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**签字*/
	public void sign() throws Exception{
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId()) ){
			String ids =  model.getId();
			String[] strarray = ids.split(",");
			String[] arrays = new String[strarray.length];
			for (int j = 0; j < strarray.length; j++) {
				arrays[j] = strarray[j];
			}
			
			List<TblStudyRes> tblStudyResList = tblStudyResService.getByIds(arrays);
			if(null != tblStudyResList){
				try{
					boolean falg = false;
					String str = "";
					for(TblStudyRes obj:tblStudyResList){
						if(obj.getSignId()!= null && !obj.getSignId().equals("")){
							falg = true;
							str = obj.getStudyNo();
							break;
						}
					}
					if(falg){
						//不存在
						json.setMsg(str +"已签字，不可重复签字");
						json.setSuccess(false);
					}else{
						User tempUser = (User) ActionContext.getContext().getSession().get("user");
						List<TblStudyRes> list = new ArrayList<TblStudyRes>();
					    for(TblStudyRes studyRes:tblStudyResList){
							String realName = tempUser.getRealName();
							// 签名链接
							TblESLink esLink = new TblESLink();
							// 电子签名
							TblES es = new TblES();
							es.setEsType(415);
							es.setSigner(realName);
							es.setEsTypeDesc("试验安置签字确认");
							es.setDateTime(new Date());
							String esId = tblESService.getKey("TblES");
							es.setEsId(esId);
							tblESService.save(es);
						    esLink.setTableName("TblStudyRes");
						    esLink.setDataId(studyRes.getId());
						    esLink.setTblES(es);
						    esLink.setEsType(415);
					        esLink.setEsTypeDesc("试验安置签字确认");
						    esLink.setRecordTime(new Date());
						    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
						    tblESLinkService.save(esLink);
						    
						    studyRes.setSignId(esId);
						    studyRes.setState(2);//已确认
							String aduitId = tblStudyResService.getAduitByStudyNo(studyRes.getStudyNo());
							if(null != aduitId && !aduitId.equals("")){
									 studyRes.setAuditId(aduitId);
							}
							list.add(studyRes);
						    // 日志录入
						    writeLog("签字", "试验安置签字确认，签字");
					    }
					    tblStudyResService.updateAll(list);
					}
					json.setSuccess(true);
				}catch(Exception e){
				     json.setSuccess(false);
				     json.setMsg("与数据库交互异常");
				     System.out.println("执行失败，出错种类"+e.getMessage()+".");
				}finally{ 
				     System.out.println("执行结束");
				} 
					
					
			}else{
				//不存在
				json.setMsg("数据交互错误");
				json.setSuccess(false);
			}
		}else{
			json.setMsg("数据交互错误");
			json.setSuccess(false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	public void signStudy(){
		List<String> singIdList = new ArrayList<String>();
		if(allStudyRes.contains(",")){
			String[] strarray = allStudyRes.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				singIdList.add(strarray[j]);
			}
		}else{
			singIdList.add(allStudyRes);
		}
		Json json = new Json();
		try{
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			// 验证通过则进行一下操作
	        String esType = "415";
			es.setEsType(Integer.parseInt(esType));
			if (esType.equals("415")) {
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("实验安置签字");
			}
			es.setDateTime(new Date());
			String esId = tblESService.getKey("TblES");
			es.setEsId(esId);
			List<TblStudyRes> list = new ArrayList<TblStudyRes>();
			for(String obj:singIdList){
				TblStudyRes studyRes = tblStudyResService.getById(obj);
				studyRes.setSignId(esId);
				studyRes.setState(2);
				list.add(studyRes);
			}
			
			tblESService.save(es);
	        for(String obj:singIdList){
	        	esLink.setTableName("TblStudyRes");
	    		esLink.setDataId(obj);
	    		esLink.setTblES(es);
	    		esLink.setEsType(Integer.parseInt(esType));
	    		if (esType.equals("415")) {
	    			esLink.setEsTypeDesc("实验安置签字");
	    		}
	    		esLink.setRecordTime(new Date());
	    		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	    		tblESLinkService.save(esLink);
	    		// 日志录入
	    		if (esType.equals("415")) {
	    			writeLog("签字", "实验安置，签字");
	    		}
	        }
	        tblStudyResService.updateAll(list);
	        json.setMsg("签字成功");
			json.setSuccess(true);
		}catch(Exception e){
		     json.setSuccess(false);
		     json.setMsg("与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	//签字复核，验证用户名和密码后验证是否有权限，有权限则更新数据
	public  void signatureVerification(){
		//theAuditId  人员
		//model.getStudyNo()课题编号
		boolean falg = false;
		boolean redKind = false;
		falg = tblStudyResService.isExistThisOne(model.getStudyNo(), theAuditId);
		Map<String,Object> map = new HashMap<String,Object>();
		TblStudyInfo studyInfo = tblStudyInfoService.getByStudyNo(model.getStudyNo());
		try{
			if(falg){
				String realName = UserService.getRealNameByUserName(theAuditId);
				// 签名链接
				TblESLink esLink = new TblESLink();
				// 电子签名
				TblES es = new TblES();
				es.setEsType(421);
				es.setSigner(realName);
				es.setEsTypeDesc("日程审核签字确认");
				es.setDateTime(new Date());
				String esId = tblESService.getKey("TblES");
				es.setEsId(esId);
				tblESService.save(es);
				studyInfo.setScheduleReviewSignID(esId);
				tblStudyInfoService.update(studyInfo);
				esLink.setTableName("TblStudyRes");
				esLink.setDataId(studyInfo.getId());
				esLink.setTblES(es);
				esLink.setEsType(421);
				esLink.setEsTypeDesc("日程审核签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				// 日志录入
				writeLog("签字", "日常审核签字确认，签字");
			}
			map.put("success", falg);
			List<TblResManager> tblResManagerList = tblResManagerService.getByResManager(theAuditId);
			for(TblResManager resManager:tblResManagerList){
				TblAnimalHouse	 tblAnimalHouse = tblAnimalHouseService.getById(resManager.getResId());
				if(tblAnimalHouse.getResKind() == 1){
					redKind = true;
				}
			}
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "与数据库交互");
		    System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 
		
		//TODO 发消息
		//当前时间
		String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
		TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(studyInfo.getResID());
		TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
		if(redKind){
			List<TblResManager> managerList = tblResManagerService.getByHouseId(studyInfo.getResID());
			List<String>  receiverList = new ArrayList<String>();
			for(TblResManager manager:managerList){
				receiverList.add(manager.getResManager());
			}
			TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyInfo.getStudyNo());
			String tiNo = studyItem.getTiNo();
			String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
			DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
			String studyNoName = "";
			if(dictStudyType.getAnimalHave() == 1){
				studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
			}else{
				studyNoName = testItemName+studyItem.getStudyName();
			}
			//通知消息 给SD 和区域负责人
			TblNotification tblNotification = new TblNotification();
			tblNotification.setMsgTitle("专题编号:　"+studyInfo.getStudyNo()+"　专题名称:　"+studyNoName+"　日程审核通过提醒！");//消息头
			//String msgContent = "区域负责人，您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			msgContent = msgContent+""+userService.getRealNameByUserName(theAuditId)+"于"+currentDate+"审核了"+
			"专题编号： 　"+studyInfo.getStudyNo()+"专题名称:　"+studyNoName+"　的日程信息，此日程安置在你所负责的区域 （"
			+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"），请为此专题日程安排具体房间，特此提醒！";
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			tblNotification.setSender(getCurrentRealName());// 发送者
			tblNotification.setSendTime(new Date());// 发送时间
			tblNotificationService.save(tblNotification,receiverList);
			
		}
		 
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);	
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
		tblLog.setOperatOject("实验安置");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}
	
	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getAllStudyRes() {
		return allStudyRes;
	}

	public void setAllStudyRes(String allStudyRes) {
		this.allStudyRes = allStudyRes;
	}

	public String getEsType() {
		return esType;
	}

	public void setEsType(String esType) {
		this.esType = esType;
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTheAuditId() {
		return theAuditId;
	}

	public void setTheAuditId(String theAuditId) {
		this.theAuditId = theAuditId;
	}

	public int getIsSelectAllStudy() {
		return isSelectAllStudy;
	}

	public void setIsSelectAllStudy(int isSelectAllStudy) {
		this.isSelectAllStudy = isSelectAllStudy;
	}

	public String getResIds() {
		return resIds;
	}

	public void setResIds(String resIds) {
		this.resIds = resIds;
	}
	public String getPresID() {
		return presID;
	}
	public void setPresID(String presID) {
		this.presID = presID;
	}
	
	
	
	
}
