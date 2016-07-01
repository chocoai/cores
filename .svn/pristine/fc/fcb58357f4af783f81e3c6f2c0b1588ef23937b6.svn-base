package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lanen.model.contract.TblAppointQA;
import com.lanen.model.contract.TblAppointSD;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.PoolNumberService;
import com.lanen.service.contract.TblAppointQAService;
import com.lanen.service.contract.TblAppointSDService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblStudyScheduleService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 专题信息（原任命SD）
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class TblAppointSDAction extends BaseAction<TblAppointSD>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private TblAppointSDService tblAppointSDService;
	@Resource
	private UserService userService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblTestItemService tblTestItemService;

	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblAppointQAService tblAppointQAService;

	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	//通知信息
	@Resource
	private TblNotificationService tblNotificationService ;
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	@Resource
	private TblStudyScheduleService tblStudyScheduleService;
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	private String[] ids;
	private String[] sids;
    private Date startDate;//开始时间
	
	private Date endDate;//结束时间
	
	private String selectRowsid;
	
	private String sort;//排序列名
	
	private String order;
	
	private int qastate;
	
	private int pastate;
	
	private String tiNo;//供试品类型
	
	private String chooseOwn;//只显示自己
	
	private String searchString;//模糊查询
	
	private String studyNos;//
	
	private String rows;// 每页显示的记录数 
	
	private String page;// 当前第几页 
	
	private String remarks;//备注
	
	private String partners;//成员
	
	private String msgTitle;
	private String msgContent;
	private String receiverList;
	
	public String main() throws Exception {
	    
		return "main";
	}
	

	

	public void loadStudyItemStateList(){
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			Map<String,Object> 	map =  new HashMap<String,Object>();
			map.put("id", -1);
			map.put("text", "&nbsp;");
			mapList.add(map);
			Map<String,Object>  map1 =  new HashMap<String,Object>();
			map1.put("id", 0);
			map1.put("text", "未任命");
			mapList.add(map1);
			Map<String,Object>  map2 =  new HashMap<String,Object>();
			map2.put("id", 1);
			map2.put("text", "未确认");
			mapList.add(map2);
			Map<String,Object>  map3 =  new HashMap<String,Object>();
			map3.put("id", 2);
			map3.put("text", "已确认");
			mapList.add(map3);
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
	}
	
	
	public void selectSDinputCombobox(){
		List<User> list = userService.findByPrivilegeName("SD");
		if(null!=list && list.size()>0){
			Set<Map<String,Object>> mapList = new HashSet<Map<String,Object>>();
			Map<String,Object> map =null;
			for(User obj:list){
				map =  new HashMap<String,Object>();
				map.put("id", obj.getId());
				map.put("text", obj.getRealName());
				mapList.add(map);
			}
			List<Map<String,Object>> jsonlist = new ArrayList<Map<String,Object>>(mapList);  
			String jsonStr = JsonPluginsUtil.beanListToJson(jsonlist);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
		
	}
	
	public void selectSDinputCombobox2(){
		List<User> list = userService.findByPrivilegeName("SD");
		if(null!=list && list.size()>0){
			Set<Map<String,Object>> mapList = new HashSet<Map<String,Object>>();
			Map<String,Object> map =null;
			for(User obj:list){
				map =  new HashMap<String,Object>();
				map.put("id", obj.getRealName());
				map.put("text", obj.getRealName());
				mapList.add(map);
			}
			List<Map<String,Object>> jsonlist = new ArrayList<Map<String,Object>>(mapList);  
			String jsonStr = JsonPluginsUtil.beanListToJson(jsonlist);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
		
	}
	
	public void selectPAinputCombobox(){
		List<User> list = userService.findByPrivilegeName("病理");
		if(null!=list && list.size()>0){
			//List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			Set<Map<String,Object>> mapList = new HashSet<Map<String,Object>>();
			Map<String,Object> map =null;
			for(User obj:list){
				map =  new HashMap<String,Object>();
				map.put("id", obj.getId());
				map.put("text", obj.getRealName());
				mapList.add(map);
			}
			//String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			List<Map<String,Object>> jsonlist = new ArrayList<Map<String,Object>>(mapList);  
			String jsonStr = JsonPluginsUtil.beanListToJson(jsonlist);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
		
	}

	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
//		  tblLog.setOperatOject("SD任命");
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	

	/**QA下拉选*/
	public void selectQAinputCombobox(){
		List<User> list = userService.findByPrivilegeName("QA");
		if(null!=list && list.size()>0){
			//List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			Set<Map<String,Object>> mapList = new HashSet<Map<String,Object>>();
			Map<String,Object> map =null;
			for(User obj:list){
				map =  new HashMap<String,Object>();
				map.put("id", obj.getId());
				map.put("text", obj.getRealName());
				mapList.add(map);
			}
			//String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			List<Map<String,Object>> jsonlist = new ArrayList<Map<String,Object>>(mapList);  
			String jsonStr = JsonPluginsUtil.beanListToJson(jsonlist);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
		
	}
	/**QA保存*/
	public void toAppointQA(){
		List<String> idList = new ArrayList<String>();
		List<String> studyNoList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {//studyItem id list
			String selectid = ids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				idList.add(strarray[j].trim());
			}
		}
		//sids studyNo list
		for (int i = 0; i < sids.length; i++) {
			String selectid = sids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				studyNoList.add(strarray[j].trim());
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(idList != null && idList.size()>0){
			boolean falg = true;
			for(String obj:idList){
				TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
				if(stydyItem!=null)
				{//如果综合管理中存在则要求，如果不存在则不要求
					int sdState = stydyItem.getSdState();
					if(sdState ==0){
						if(null != stydyItem){
							falg = false;
							map.put("error", true);
							map.put("msg", " 专题:"+stydyItem.getStudyNo()+" SD未提交确认");
							break;
						}
					}
				}
				
				
			}
			
			if(falg){
				List<TblAppointQA> list = new ArrayList<TblAppointQA>();
				for(String obj:idList){
					TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
						TblAppointQA appointQA = new TblAppointQA();
						appointQA.setId(tblAppointQAService.getKey());
						int index = idList.indexOf(obj);
						appointQA.setStudyNo(studyNoList.get(index));//专题编号
						
						if(null != stydyItem){
							appointQA.setContractCode(stydyItem.getContractCode());//合同编号
							appointQA.setStudyName(stydyItem.getStudyName());//项目名称
							appointQA.settINo(stydyItem.getTiNo());//供试品编码
							String tiName = tblTestItemService.getTiNameByContractAndTiNo(stydyItem.getContractCode(), stydyItem.getTiNo());
							appointQA.settIName(tiName);//供试品名称
						}
						appointQA.setQaCode(model.getSd());//SD编码
						appointQA.setQa(userService.getRealNameByUserName(model.getSd()));//sd姓名
						appointQA.setAppointDate(new Date());//SD任命时间
						appointQA.setState(0);//状态
						list.add(appointQA);
					/*}else{
						break;
					}*/
				}
				if(null != list){
					tblAppointQAService.saveAll(list);
					map.put("success", true);
				}else{
					map.put("success", false);
				}
			}
		}else{
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}
	/**保存过后直接提交*/
	public void afterSaveSubmitAppointQA(){
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			if(!selectid.equals("") ){
				String[] strarray = selectid.split(",");		
				for (int j = 0; j < strarray.length; j++) {
					if(!strarray[j].trim().equals("")){
						idList.add(strarray[j].trim());
					}
				
				}
			}
		}
		
		//提交QA
		User user = (User) ActionContext.getContext().getSession().get("user");
		//TblAppointQA  list
		List<TblAppointQA> list = new ArrayList<TblAppointQA>();
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Map<String, Object> json = new HashMap<String, Object>();
		try{
			for(String studyNo:idList){
				TblAppointQA tblAppointQA = tblAppointQAService.getByStudyNo(studyNo);
				tblAppointQA.setState(1);
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//tblAppointQA.setPoolNum(poolNum);
				tblAppointQA.setFMCode(user.getUserName());
				
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				if(studyItem!=null)//为null的证明在综合管理中不存在的
				{
					studyItem.setQa(tblAppointQA.getQa());
					studyItem.setQaCode(tblAppointQA.getQaCode());
					list2.add(studyItem);
				}
				QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNo);
				if(qAStudyChkIndex==null)
				{
					String sd = "";
					if(studyItem!=null)
					{
						sd = studyItem.getSd();
					}else{
						sd = tblStudyPlanService.getSDByStudyNo(studyNo);
					}
					qAStudyChkIndex = new QAStudyChkIndex();
					
					qAStudyChkIndex.setChkPlanAuthor(user.getRealName());
					qAStudyChkIndex.setChkPlanCurVersion(1);
					qAStudyChkIndex.setChkPlanState(0);//0：草稿；1：提交；-1：QAM否决；2：通过
					
					qAStudyChkIndex.setReportState(0);//0：未完成；1：已完成
					qAStudyChkIndex.setSd(sd);
					qAStudyChkIndex.setStudyNo(studyNo);
					
					qAStudyChkIndex.setStudyPlanState(0);//方案审批状态：0：未完成；1：已完成
					
					//专题里面的日程信息
					TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
					if(tblStudyPlan!=null)
					{
						qAStudyChkIndex.setScheduleState(tblStudyPlan.getScheduleState());
					}
					
					qAStudyChkIndexService.save(qAStudyChkIndex);
				}
				qAStudyChkIndex.setInspector(tblAppointQA.getQa());
				qAStudyChkIndex.setInspectorAppointState(1);
				qAStudyChkIndex.setInspectorAppointTime(tblAppointQA.getAppointDate());
				
				qAStudyChkIndexService.update(qAStudyChkIndex);
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("QA负责人提交QA任命");
				es.setEsType(441);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				
				tblAppointQA.setAppointSignID(eid);
				list.add(tblAppointQA);
			
				
				esLink.setTableName("TblAppointQA");
				esLink.setDataId(tblAppointQA.getId());
				esLink.setTblES(es);
				esLink.setEsType(441);
				esLink.setEsTypeDesc("QA负责人提交QA任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESService.save(es);
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("提交任命","QA任命","提交QA任命,专题编号："+tblAppointQA.getStudyNo()+"为 "+tblAppointQA.getQa());
		  }
		  tblAppointQAService.updateAll(list,list2);
		  //  任命QA  LIST
		  Map<String, Object> mailMap = writeNotification_qa(list);
		  json.put("msgTitle", mailMap.get("msgTitle"));
		  json.put("msgContent", mailMap.get("msgContent"));
		  json.put("receiverList", mailMap.get("receiverList"));
		  
		  
		  json.put("success", true);
		  json.put("msg","签字成功");
		}catch(Exception e){
		    // json.setSuccess(false);
		     json.put("success", false);
		    // json.setMsg("与数据库交互异常");
		     json.put("msg","与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		} 	 
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	//提交QA
	public void submitAppointQA(){
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			if(!selectid.equals("") ){
				String[] strarray = selectid.split(",");		
				for (int j = 0; j < strarray.length; j++) {
					if(!strarray[j].trim().equals("")){
						idList.add(strarray[j].trim());
					}
				
				}
			}
		}
		
		//提交QA
		User user = (User) ActionContext.getContext().getSession().get("user");
		//TblAppointQA  list
		List<TblAppointQA> list = new ArrayList<TblAppointQA>();
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Map<String, Object> json = new HashMap<String, Object>();
		try{
			for(String id:idList){
				TblAppointQA tblAppointQA = tblAppointQAService.getById(id);
				tblAppointQA.setState(1);
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//tblAppointQA.setPoolNum(poolNum);
				tblAppointQA.setFMCode(user.getUserName());
				
				String studyNo = tblAppointQA.getStudyNo();
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setQa(tblAppointQA.getQa());
				studyItem.setQaCode(tblAppointQA.getQaCode());
				list2.add(studyItem);
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("QA负责人提交QA任命");
				es.setEsType(441);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				
				tblAppointQA.setAppointSignID(eid);
				list.add(tblAppointQA);
			
				
				esLink.setTableName("TblAppointQA");
				esLink.setDataId(id);
				esLink.setTblES(es);
				esLink.setEsType(441);
				esLink.setEsTypeDesc("QA负责人提交QA任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESService.save(es);
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("提交任命","QA任命","提交QA任命,专题编号："+tblAppointQA.getStudyNo()+"为 "+tblAppointQA.getQa());
			}
			 tblAppointQAService.updateAll(list,list2);
			 //  任命QA  LIST
			 Map<String, Object> mailMap = writeNotification_qa(list);
			 json.put("msgTitle", mailMap.get("msgTitle"));
			  json.put("msgContent", mailMap.get("msgContent"));
			  json.put("receiverList", mailMap.get("receiverList"));
			  
			 json.put("success",true);
			 json.put("msg","签字成功");
		}catch(Exception e){
		     //json.setSuccess(false);
		     json.put("success",false);
		     //json.setMsg("与数据库交互异常");
		     json.put("msg","与数据库交互异常");
		     System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
		     System.out.println("执行结束");
		}  
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}
	
	/**重新任命QA*/
	
	public void updateAppointQA(){
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				idList.add(strarray[j].trim());
			}
		}
		List<TblAppointQA> list = new ArrayList<TblAppointQA>();
		List<TblAppointQA> list2 = new ArrayList<TblAppointQA>();
		List<TblStudyItem> list3 = new ArrayList<TblStudyItem>();
	    Map<String, Object> json = new HashMap<String, Object>();
	    try{	
	        for(String id:idList){
				TblAppointQA tblAppointQA = tblAppointQAService.getById(id);
				tblAppointQA.setState(-1);
				tblAppointQA.setCancelDate(new Date());
				list.add(tblAppointQA);
				
				TblAppointQA appointQA = new TblAppointQA();
				appointQA.setId(tblAppointSDService.getKey());
				appointQA.setContractCode(tblAppointQA.getContractCode());//合同编号
				appointQA.setStudyName(tblAppointQA.getStudyName());//项目名称
				appointQA.setStudyNo(tblAppointQA.getStudyNo());//专题编号
				appointQA.settINo(tblAppointQA.gettINo());//供试品编码
				appointQA.settIName(tblAppointQA.gettIName());//供试品名称
				appointQA.setQaCode(model.getSd());//Qa编码 前台传过来的，用sd暂时接受
				appointQA.setQa(userService.getRealNameByUserName(model.getSd()));//sd姓名
				appointQA.setAppointDate(new Date());//SD任命时间
				appointQA.setState(1);//状态
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//appointSD.setPoolNum(poolNum);
				String studyNo = appointQA.getStudyNo();
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setQa(appointQA.getQa());
				studyItem.setQaCode(appointQA.getQaCode());
				list3.add(studyItem);
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("QA负责人重新QA");
				es.setEsType(442);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				tblESService.save(es);
				appointQA.setAppointSignID(eid);
				list2.add(appointQA);
				esLink.setTableName("TblAppointQA");
				esLink.setDataId(id);
				esLink.setTblES(es);
				esLink.setEsType(442);
				esLink.setEsTypeDesc("QA负责人重新QA任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("重新任命QA","QA任命","重新任命QA，专题编号："+appointQA.getStudyNo()+" 由"+tblAppointQA.getQa()+"改为"+appointQA.getQa());
		   }
	       tblAppointQAService.updateAgainAll(list,list2,list3);
	       // 重新任命QA   list2
	       Map<String, Object> mailMap = writeNotification_qa(list2);
	       json.put("msgTitle", mailMap.get("msgTitle"));
		   json.put("msgContent", mailMap.get("msgContent"));
		   json.put("receiverList", mailMap.get("receiverList"));
			  
			  
	       json.put("success",true);
		   json.put("msg","签字成功");
	    }catch(Exception e){
	        json.put("success",false);
	        json.put("msg","与数据库交互异常");
	        System.out.println("执行失败，出错种类"+e.getMessage()+".");
	   }finally{ 
	        System.out.println("执行结束");
	   } 
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	
    
	
	/**
	 * 发送通知(QA任命)
	 * @param mapList
	 */
	private Map<String, Object> writeNotification_qa(List<TblAppointQA> mapList) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		if(null != mapList && mapList.size()>0){
			String sender = getCurrentRealName();
			for(TblAppointQA map : mapList){
				String studyNo = (String)map.getStudyNo();
				TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNo);
				String studyNoName = "";
				if(studyItem!=null)
				{
					String tiNo = studyItem.getTiNo();
					String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
					DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
					if(dictStudyType.getAnimalHave() == 1){
						studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
					}else{
						studyNoName = testItemName+studyItem.getStudyName();
					}
				}
				//String realName = (String)map.getQa();
				String userName = (String)map.getQaCode();
				//当前时间
				String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
				//通知消息
				//TblNotification tblNotification = new TblNotification();
				String msgTitle="";
				String msgContent="";
				if(!"".equals(studyNoName))
				{
					msgTitle=("专题编号：　"+studyNo+"，专题名称：　"+studyNoName+"QA任命");
					//String msgContent = realName+",您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = "<br>";
					msgContent = msgContent+""+sender+"于"+currentDate+"任命您为 <br>专题编号：　 " +studyNo+
					"  <br>专题名称："+studyNoName+"　 <br>专题的QA检查员,特此提醒";
					
				}
				else {
					msgTitle=("专题编号：　"+studyNo+"QA任命");
					//String msgContent = realName+",您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = "<br>";
					msgContent = msgContent+""+sender+"于"+currentDate+"任命您为 <br>专题编号：　 " +studyNo+"　专题的QA检查员,特此提醒";
					
				}	
				
				//接收者列表
				String receiverList = "";
				receiverList+=(userName)+",";
				
				map2.put("msgTitle", msgTitle);
				map2.put("msgContent", msgContent);
				map2.put("receiverList", receiverList);
				
				map2.put("success", true);
				
			}
			
		}
		return map2;
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
	
	private String index;
	
	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	//加载进度条
	public void loadProgress(){
		System.out.println(model.getStudyNo());
		System.out.println(studyNos);
		List<String>  sList = new ArrayList<String>();
		if(null != studyNos && studyNos.length() >= 1 ){
			String[] studyNo = studyNos.split(",");
			for(int j = 0;j < studyNo.length;j++){
				String  progress= tblStudyScheduleService.getPercentageByStudyNo(studyNo[j]);
				double aa=Double.parseDouble(progress);
			    String    p  =Double.toString(aa * 100);
				TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(studyNo[j]);
				String progressstr = p + "#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd");
				sList.add(progressstr);
			}
		}
		Json json = new Json();
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**课题类别dialog上  供试品类别下拉框 带空白选项*/
	public void loadTestItemAndNOList(){
		List<DictTestItemType> list = dictTestItemTypeService.getAll();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			map =  new HashMap<String,String>();
			map.put("id", "-1");
			map.put("text", "&nbsp;");
			mapList.add(map);
			for(DictTestItemType obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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

	public String[] getSids() {
		return sids;
	}

	public void setSids(String[] sids) {
		this.sids = sids;
	}

	public String getSelectRowsid() {
		return selectRowsid;
	}

	public void setSelectRowsid(String selectRowsid) {
		this.selectRowsid = selectRowsid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getQastate() {
		return qastate;
	}

	public void setQastate(int qastate) {
		this.qastate = qastate;
	}

	public int getPastate() {
		return pastate;
	}

	public void setPastate(int pastate) {
		this.pastate = pastate;
	}

	public String getTiNo() {
		return tiNo;
	}

	public void setTiNo(String tiNo) {
		this.tiNo = tiNo;
	}

	public String getChooseOwn() {
		return chooseOwn;
	}

	public void setChooseOwn(String chooseOwn) {
		this.chooseOwn = chooseOwn;
	}

	public String getStudyNos() {
		return studyNos;
	}

	public void setStudyNos(String studyNos) {
		this.studyNos = studyNos;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPartners() {
		return partners;
	}

	public void setPartners(String partners) {
		this.partners = partners;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
	public String getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(String receiverList) {
		this.receiverList = receiverList;
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

}
