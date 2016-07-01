package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.lanen.model.contract.TblAppointPathSD;
import com.lanen.model.contract.TblAppointQA;
import com.lanen.model.contract.TblAppointSD;
import com.lanen.model.contract.TblAppointSD_JSON;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.model.qa.TblStudyFileIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.PoolNumberService;
import com.lanen.service.contract.TblAppointPathSDService;
import com.lanen.service.contract.TblAppointQAService;
import com.lanen.service.contract.TblAppointSDService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblStudyScheduleService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.qa.TblStudyFileIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictTestItemTypeService;
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

	/**
	 * 
	 */
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
	private PoolNumberService poolNumberService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblAppointQAService tblAppointQAService;
	
	@Resource
	private TblAppointPathSDService tblAppointPathSDService;
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
	private TblStudyFileIndexService tblStudyFileIndexService;
	
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
	
	private String isExistFileDis;//是否是文件分发没完成的用户
	
	public String main() throws Exception {
	    
		return "main";
	}
	
	@SuppressWarnings("static-access")
	public String appointSD() {
		startDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		calendar.add(calendar.MONTH, 0);// 把日期往后增加一天.整数往后推,负数往前移动
		endDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(startDate);
		calendar1.add(calendar.MONTH, -6);// 把日期往后增加一天.整数往后推,负数往前移动
		startDate = calendar1.getTime(); // 这个时间就是日期往后推一天的结果
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
		ActionContext.getContext().put("startTime", DateUtil.dateToString(startDate, "yyyy-MM-dd"));
		ActionContext.getContext().put("endTime",  DateUtil.dateToString(endDate, "yyyy-MM-dd"));
		
		ActionContext.getContext().put("isExistFileDis", isExistFileDis);
		
		return "appointSD";
	}
	
	//前台页面显示
	@SuppressWarnings("static-access")
	public void loadAppointSDList(){		
		if(null == startDate || startDate.equals("") ){
			startDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(startDate);
			calendar.add(calendar.MONTH, 6);// 把日期往后增加一天.整数往后推,负数往前移动
			endDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
				
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean  FM = userService.checkPrivilege(user, "FM");
		boolean  SD = userService.checkPrivilege(user, "SD");
		boolean  falg2 = userService.checkPrivilege(user, "QA负责人");
		boolean  falg3 = userService.checkPrivilege(user, "QA");
		boolean  falg4 = userService.checkPrivilege(user, "病理负责人");
		boolean  falg5 = userService.checkPrivilege(user, "病理");
		boolean falg6  = userService.checkPrivilege(user, "部门负责人");
		
		//有FM任命权限
		Map<String,Object> map = new HashMap<String,Object>();
			if(FM || falg2 || falg4){
				 int  sdstate = model.getState();
				 if(falg2 || falg4){
					 sdstate = 2;
				 }
				 System.out.println("getBytartimeAndEndtimeAndStartAndSortAndOrderAndPage method");
				  map = tblAppointSDService.getBytartimeAndEndtimeAndStartAndSortAndOrderAndPage(
						 startDate, endDate, sdstate, sort, order, qastate, pastate, tiNo, page, rows,searchString);
				 
			}else if(SD || falg3 || falg5 || falg6 ){
				 System.out.println("getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrderAndPage method");
				 map = tblAppointSDService.getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrderAndPage(startDate,endDate,user.getUserName(),sort,order,tiNo,chooseOwn,page,rows,searchString);
				
			}
			//加上有分发文件要学习的专题
			if("true".equals(isExistFileDis)){//有文件分发的人，加上文件分发的专题内容
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2 = tblAppointSDService.getByNoFinishFileDis(startDate,endDate,user.getUserName(),sort,order,tiNo,chooseOwn,page,rows,searchString);
				//map中加入map2的内容（去重）
				List<TblAppointSD_JSON> jsonlist = ( List<TblAppointSD_JSON>)map.get("rows");
				Integer  total = (Integer)map.get("total");
				if(jsonlist==null)
					jsonlist = new ArrayList<TblAppointSD_JSON>();
				if(total==null)
					total=0;
				
				List<TblAppointSD_JSON> jsonlist2 = ( List<TblAppointSD_JSON>)map2.get("rows");
			//	int  total2 = (Integer)map2.get("total");
				for(TblAppointSD_JSON nofinishDis:jsonlist2)
				{
					if(!jsonlist.contains(nofinishDis))
					{
						total += 1;
					}else {
						jsonlist.remove(nofinishDis);
					}
					jsonlist.add(nofinishDis);
				}
				map.put("rows", jsonlist);
				map.put("total", total);
			}
			List<TblAppointSD_JSON> jsonlist = ( List<TblAppointSD_JSON>)map.get("rows");
			for(TblAppointSD_JSON addFileState:jsonlist)
			{
				//方案文件索引,只有方案有分发
				TblStudyFileIndex studyFileIndex = tblStudyFileIndexService.getByStudyNoAndFileType(addFileState.getStudyNo(),1);
				if(studyFileIndex!=null)
					addFileState.setFileState(studyFileIndex.getFileState());
				else
					addFileState.setFileState(3);
			}
			map.put("rows", jsonlist);
			
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		writeJson(json);
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
	 * 任命,非提交
	 */
	public void toAppointSD(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String[] studyNoArray = studyNos.split(",");
		String[] remarkArray = remarks.split(",");
		String[] partnerArray = partners.split(",");
		List<TblAppointSD> list = new ArrayList<TblAppointSD>();
		if(null != studyNoArray && studyNoArray.length>0 && (!studyNos.equals(""))){
			for(int i = 0;i<studyNoArray.length;i++){
				TblStudyItem  stydyItem= tblStudyItemService.getById(studyNoArray[i]);
				TblAppointSD appointSD = new TblAppointSD();
				appointSD.setId(tblAppointSDService.getKey());
				appointSD.setContractCode(stydyItem.getContractCode());//合同编号
				appointSD.setStudyName(stydyItem.getStudyName());//项目名称
				appointSD.setStudyNo(stydyItem.getStudyNo());//专题编号
				appointSD.settINo(stydyItem.getTiNo());//供试品编码
				String tiName = tblTestItemService.getTiNameByContractAndTiNo(stydyItem.getContractCode(), stydyItem.getTiNo());
				appointSD.settIName(tiName);//供试品名称
				appointSD.setSdCode(model.getSd());//SD编码
				
				appointSD.setSd(userService.getRealNameByUserName(model.getSd()));//sd姓名
				//appointSD.setAppointDate(new Date());//SD任命时间
				//任命日期
				appointSD.setAppointDate(model.getAppointDate());
				appointSD.setState(0);//状态
				if((!remarkArray[i].equals("-1"))&&(remarkArray[i]!= "-1")){
					appointSD.setRemark(remarkArray[i]);
				}else{
					appointSD.setRemark("");
				}
				if((!partnerArray[i].equals("-1"))&&(partnerArray[i]!= "-1")){
					appointSD.setPartner(partnerArray[i]);
				}else{
					appointSD.setPartner("");
				}
				list.add(appointSD);
			}
			if(null != list){
				tblAppointSDService.saveAll(list);
				map.put("success", true);
			}else{
				map.put("success", false);
			}
		}else{
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**重新任命*/
	public void updateAppointSD(){
//		List<String> idList = new ArrayList<String>();
//		for (int i = 0; i < ids.length; i++) {
//			String selectid = ids[i];
//			String[] strarray = selectid.split(",");		
//			for (int j = 0; j < strarray.length; j++) {
//				idList.add(strarray[j].trim());
//			}
//		}
		List<TblAppointSD> list = new ArrayList<TblAppointSD>();
		List<TblAppointSD> list2 = new ArrayList<TblAppointSD>();
		List<TblStudyItem> list3 = new ArrayList<TblStudyItem>();
		Json json = new Json();
		String[] remarkArray = remarks.split(",");
		String[] idArray = studyNos.split(",");
		String[] partnerArray = partners.split(",");
		try{  
			for(int i = 0;i<idArray.length;i++){
				TblAppointSD tblAppointSD = tblAppointSDService.getById(idArray[i]);
				tblAppointSD.setState(-1);
				tblAppointSD.setCancelDate(new Date());
				list.add(tblAppointSD);
				TblAppointSD appointSD = new TblAppointSD();
				appointSD.setId(tblAppointSDService.getKey());
				appointSD.setContractCode(tblAppointSD.getContractCode());//合同编号
				appointSD.setStudyName(tblAppointSD.getStudyName());//项目名称
				appointSD.setStudyNo(tblAppointSD.getStudyNo());//专题编号
				appointSD.settINo(tblAppointSD.gettINo());//供试品编码
				appointSD.settIName(tblAppointSD.gettIName());//供试品名称
				appointSD.setSdCode(model.getSd());//SD编码
				appointSD.setSd(userService.getRealNameByUserName(model.getSd()));//sd姓名
				appointSD.setAppointDate(new Date());//SD任命时间
				if((!remarkArray[i].equals("-1"))&&(remarkArray[i]!= "-1")){
					appointSD.setRemark(remarkArray[i]);
				}else{
					appointSD.setRemark("");
				}
				if((!partnerArray[i].equals("-1"))&&(partnerArray[i]!= "-1")){
					appointSD.setPartner(partnerArray[i]);
				}else{
					appointSD.setPartner("");
				}
				appointSD.setState(1);//状态
				String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				appointSD.setPoolNum(poolNum);
				String studyNo = appointSD.getStudyNo();
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setSd(appointSD.getSd());
				studyItem.setSdCode(appointSD.getSdCode());
				list3.add(studyItem);
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsType(431);
				es.setEsTypeDesc("FM重新SD");
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
			
				appointSD.setAppointSignID(eid);
				list2.add(appointSD);
				esLink.setTableName("TblAppointSD");
				esLink.setDataId(idArray[i]);
				esLink.setTblES(es);
				esLink.setEsType(431);
				esLink.setEsTypeDesc("FM重新SD任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESService.save(es);
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("重新任命SD","SD任命","重新任命SD，专题编号："+tblAppointSD.getStudyNo()+" 由"+tblAppointSD.getSd()+"改为"+appointSD.getSd());
			}
		   tblAppointSDService.updateAgainAll(list,list2,list3);
		   // sd任命  
		   writeNotification_sd(list2);
		   json.setSuccess(true);
		   json.setMsg("签字成功");
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
	
	//保存后直接提交 
	public void afterSaveSubmitAppointSD(){
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
		//提交SD
		User user = (User) ActionContext.getContext().getSession().get("user");
		//TblAppointSD  list
		List<TblAppointSD> list = new ArrayList<TblAppointSD>();
		//TblStudyItem   list
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Json json = new Json();
		try{
			for(String studyNo:idList){
				TblAppointSD tblAppointSD = tblAppointSDService.getByStudyNo(studyNo);
				tblAppointSD.setState(1);
				String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				tblAppointSD.setPoolNum(poolNum);
				tblAppointSD.setFMCode(user.getUserName());
				//若未设置日期,则设置为当前日期
				if(null == tblAppointSD.getAppointDate()){
					tblAppointSD.setAppointDate(new Date());
				}
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setSd(tblAppointSD.getSd());
				studyItem.setSdCode(tblAppointSD.getSdCode());
				list2.add(studyItem);
				
				QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNo);
				if(qAStudyChkIndex!=null)
				{
					qAStudyChkIndex.setSd(tblAppointSD.getSd());
					
					qAStudyChkIndexService.update(qAStudyChkIndex);
				}
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("FM提交SD任命");
				es.setEsType(430);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				tblAppointSD.setAppointSignID(eid);
				list.add(tblAppointSD);
				esLink.setTableName("TblAppointSD");
				esLink.setDataId(tblAppointSD.getId());
				esLink.setTblES(es);
				esLink.setEsType(430);
				esLink.setEsTypeDesc("FM提交SD任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESService.save(es);
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("提交任命","SD任命","提交SD任命,专题编号："+tblAppointSD.getStudyNo()+"为 "+tblAppointSD.getSd());
				// 进度计划   SD任命
				TblStudySchedule tblStudySchedule = new TblStudySchedule();
				tblStudySchedule.setId(tblStudyScheduleService.getKey());
				tblStudySchedule.setNodeName("SD任命");
				tblStudySchedule.setNodeSn(100);
				tblStudySchedule.setStudyNo(tblAppointSD.getStudyNo());
				tblStudySchedule.setActualDate(tblAppointSD.getAppointDate() !=null ? tblAppointSD.getAppointDate():new Date());
				tblStudyScheduleService.save(tblStudySchedule);
			}
		
			if(list != null){
				 tblAppointSDService.updateAll(list,list2);
				 //      list  SD  sd任命
				 writeNotification_sd(list);
			}
			json.setSuccess(true);
			json.setMsg("签字成功");
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
	
	
    //提交SD
	public void submitAppointSD(){
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
		List<String> sidList = new ArrayList<String>();
		for (int i = 0; i < sids.length; i++) {
			String selecsid = sids[i];
			if(!selecsid.equals("") ){
			String[] strarray = selecsid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				if(!strarray[j].trim().equals("")){
					sidList.add(strarray[j].trim());
				}
			}
			}
		}
		//直接提交SD推荐人
		List<TblAppointSD> slist = new ArrayList<TblAppointSD>();
		//TblStudyItem   list
		List<TblStudyItem> tblStudyItemlist = new ArrayList<TblStudyItem>();
		if(sidList != null && sidList.size()>0){
			for(String obj:sidList){
				TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
				if(null != stydyItem){
					TblAppointSD appointSD = new TblAppointSD();
					appointSD.setId(tblAppointSDService.getKey());
					appointSD.setContractCode(stydyItem.getContractCode());//合同编号
					appointSD.setStudyName(stydyItem.getStudyName());//项目名称
					appointSD.setStudyNo(stydyItem.getStudyNo());//专题编号
					appointSD.settINo(stydyItem.getTiNo());//供试品编码
					String tiName = tblTestItemService.getTiNameByContractAndTiNo(stydyItem.getContractCode(), stydyItem.getTiNo());
					appointSD.settIName(tiName);//供试品名称
					User user = userService.getByRealName(stydyItem.getSdManager());
					appointSD.setSdCode(user.getUserName());//SD编码
					appointSD.setSd(stydyItem.getSdManager());//sd姓名
					appointSD.setAppointDate(new Date());//SD任命时间
					appointSD.setPrintNumber(0);
					String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
					appointSD.setPoolNum(poolNum);
					slist.add(appointSD);
					stydyItem.setSd(appointSD.getSd());
					stydyItem.setSdCode(appointSD.getSdCode());
					tblStudyItemlist.add(stydyItem);
				}else{
					break;
				}
			}
		}
	   if(null != slist){
			tblAppointSDService.saveAllAndUpdate(slist,tblStudyItemlist);
			// sd任命
		}
		//提交SD
		User user = (User) ActionContext.getContext().getSession().get("user");
		//TblAppointSD  list
		List<TblAppointSD> list = new ArrayList<TblAppointSD>();
		//TblStudyItem   list
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Json json = new Json();
		try{
				for(TblAppointSD appointSD:slist){
					TblAppointSD tblAppointSD = tblAppointSDService.getById(appointSD.getId());
					tblAppointSD.setState(1);
					String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
					tblAppointSD.setPoolNum(poolNum);
					tblAppointSD.setFMCode(user.getUserName());
					//若未设置日期,则设置为当前日期
					if(null == tblAppointSD.getAppointDate()){
						tblAppointSD.setAppointDate(new Date());
					}
					String studyNo = tblAppointSD.getStudyNo();
					TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
					studyItem.setSd(tblAppointSD.getSd());
					studyItem.setSdCode(tblAppointSD.getSdCode());
					list2.add(studyItem);
					//签名链接
					TblESLink esLink = new TblESLink();
					//电子签名
					TblES es = new TblES();
					//验证通过则进行一下操作
					User tempUser = (User) ActionContext.getContext().getSession().get("user");
					es.setSigner(tempUser.getRealName());
					es.setEsTypeDesc("FM提交SD任命");
					es.setEsType(430);
					es.setDateTime(new Date());
					String eid = tblESService.getKey("TblES");
					es.setEsId(eid);
					tblAppointSD.setAppointSignID(eid);
					list.add(tblAppointSD);
					esLink.setTableName("TblAppointSD");
					esLink.setDataId(appointSD.getId());
					esLink.setTblES(es);
					esLink.setEsType(430);
					esLink.setEsTypeDesc("FM提交SD任命签字确认");
					esLink.setRecordTime(new Date());
					esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
					tblESService.save(es);
					tblESLinkService.save(esLink);
					//日志录入
					writeLog("提交任命","SD任命","提交SD任命,专题编号："+tblAppointSD.getStudyNo()+" 为 "+tblAppointSD.getSd());
				}
				
				for(String id:idList){
					TblAppointSD tblAppointSD = tblAppointSDService.getById(id);
					tblAppointSD.setState(1);
					String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
					tblAppointSD.setPoolNum(poolNum);
					tblAppointSD.setFMCode(user.getUserName());
					String studyNo = tblAppointSD.getStudyNo();
					TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
					studyItem.setSd(tblAppointSD.getSd());
					studyItem.setSdCode(tblAppointSD.getSdCode());
					list2.add(studyItem);
					//签名链接
					TblESLink esLink = new TblESLink();
					//电子签名
					TblES es = new TblES();
					//验证通过则进行一下操作
					User tempUser = (User) ActionContext.getContext().getSession().get("user");
					es.setSigner(tempUser.getRealName());
					es.setEsTypeDesc("FM提交SD任命");
					es.setEsType(430);
					es.setDateTime(new Date());
					String eid = tblESService.getKey("TblES");
					es.setEsId(eid);
					tblAppointSD.setAppointSignID(eid);
					list.add(tblAppointSD);
					esLink.setTableName("TblAppointSD");
					esLink.setDataId(id);
					esLink.setTblES(es);
					esLink.setEsType(430);
					esLink.setEsTypeDesc("FM提交SD任命签字确认");
					esLink.setRecordTime(new Date());
					esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
					//日志录入
					writeLog("提交任命","SD任命","提交SD任命,专题编号："+tblAppointSD.getStudyNo()+" 为"+tblAppointSD.getSd());
					// 进度计划   SD任命
					TblStudySchedule tblStudySchedule = new TblStudySchedule();
					tblStudySchedule.setId(tblStudyScheduleService.getKey());
					tblStudySchedule.setNodeName("SD任命");
					tblStudySchedule.setNodeSn(100);
					tblStudySchedule.setStudyNo(tblAppointSD.getStudyNo());
					tblStudySchedule.setActualDate(tblAppointSD.getAppointDate() !=null ? tblAppointSD.getAppointDate():new Date());
					tblESService.save(es);
					tblESLinkService.save(esLink);
					tblStudyScheduleService.save(tblStudySchedule);
				}
				if(list != null){
					 tblAppointSDService.updateAll(list,list2);
					 //      list  SD  sd任命
					 writeNotification_sd(list);
				}
				json.setSuccess(true);
				json.setMsg("签字成功");
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
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				idList.add(strarray[j].trim());
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(idList != null && idList.size()>0){
			boolean falg = true;
			for(String obj:idList){
				TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
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
			
			if(falg){
				List<TblAppointQA> list = new ArrayList<TblAppointQA>();
				for(String obj:idList){
					TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
					if(null != stydyItem){
						TblAppointQA appointQA = new TblAppointQA();
						appointQA.setId(tblAppointQAService.getKey());
						appointQA.setContractCode(stydyItem.getContractCode());//合同编号
						appointQA.setStudyName(stydyItem.getStudyName());//项目名称
						appointQA.setStudyNo(stydyItem.getStudyNo());//专题编号
						appointQA.settINo(stydyItem.getTiNo());//供试品编码
						String tiName = tblTestItemService.getTiNameByContractAndTiNo(stydyItem.getContractCode(), stydyItem.getTiNo());
						appointQA.settIName(tiName);//供试品名称
						appointQA.setQaCode(model.getSd());//SD编码
						appointQA.setQa(userService.getRealNameByUserName(model.getSd()));//sd姓名
						appointQA.setAppointDate(new Date());//SD任命时间
						appointQA.setState(0);//状态
						list.add(appointQA);
					}else{
						break;
					}
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
		Json json = new Json();
		try{
			for(String studyNo:idList){
				TblAppointQA tblAppointQA = tblAppointQAService.getByStudyNo(studyNo);
				tblAppointQA.setState(1);
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//tblAppointQA.setPoolNum(poolNum);
				tblAppointQA.setFMCode(user.getUserName());
				
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setQa(tblAppointQA.getQa());
				studyItem.setQaCode(tblAppointQA.getQaCode());
				list2.add(studyItem);
				
				QAStudyChkIndex qAStudyChkIndex = qAStudyChkIndexService.getByStudyNo(studyNo);
				if(qAStudyChkIndex!=null)
				{
					qAStudyChkIndex.setInspector(tblAppointQA.getQa());
					qAStudyChkIndex.setInspectorAppointState(1);
					qAStudyChkIndex.setInspectorAppointTime(tblAppointQA.getAppointDate());
					
					qAStudyChkIndexService.update(qAStudyChkIndex);
					
				}
				
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
		  try {
			  //  任命QA  LIST
			  writeNotification_qa(list);
		  } catch (Exception e) {
			  System.out.println("发送通知失败！");
		  }
		  json.setSuccess(true);
		  json.setMsg("签字成功");
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
		Json json = new Json();
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
			 try {
				 //  任命QA  LIST
				 writeNotification_qa(list);
			} catch (Exception e) {
				System.out.println("发送通知失败！");
			}
			 json.setSuccess(true);
			 json.setMsg("签字成功");
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
	
	
	//任命PA
	
	public void toAppointPA(){
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				idList.add(strarray[j].trim());
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(idList != null && idList.size()>0){
			boolean falg = true;
			for(String obj:idList){
				TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
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
			
			if(falg){
				List<TblAppointPathSD> list = new ArrayList<TblAppointPathSD>();
				for(String obj:idList){
					TblStudyItem  stydyItem= tblStudyItemService.getById(obj);
					if(null != stydyItem){
						TblAppointPathSD appointPA = new TblAppointPathSD();
						appointPA.setId(tblAppointPathSDService.getKey());
						appointPA.setContractCode(stydyItem.getContractCode());//合同编号
						appointPA.setStudyName(stydyItem.getStudyName());//项目名称
						appointPA.setStudyNo(stydyItem.getStudyNo());//专题编号
						appointPA.settINo(stydyItem.getTiNo());//供试品编码
						String tiName = tblTestItemService.getTiNameByContractAndTiNo(stydyItem.getContractCode(), stydyItem.getTiNo());
						appointPA.settIName(tiName);//供试品名称
						appointPA.setPathSDCode(model.getSd());//SD编码
						appointPA.setPathSD(userService.getRealNameByUserName(model.getSd()));//sd姓名
						appointPA.setAppointDate(new Date());//SD任命时间
						appointPA.setState(0);//状态
						list.add(appointPA);
					}else{
						break;
					}
				}
				if(null != list){
					tblAppointPathSDService.saveAll(list);
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
	
	//保存后直接提交病理
	public void afterSaveSubmitAppointPA(){
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
		
		//提交病理
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<TblAppointPathSD> list = new ArrayList<TblAppointPathSD>();
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Json json = new Json();
		try{
			for(String studyNo:idList){
				TblAppointPathSD tblAppointPathSD = tblAppointPathSDService.getByStudyNo(studyNo);
				tblAppointPathSD.setState(1);
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//tblAppointQA.setPoolNum(poolNum);
				tblAppointPathSD.setFMCode(user.getUserName());
				
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setPathSD(tblAppointPathSD.getPathSD());
				studyItem.setPathSDCode(tblAppointPathSD.getPathSDCode());
				list2.add(studyItem);
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("病理负责人提交病理任命");
				es.setEsType(443);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				
				tblAppointPathSD.setAppointSignID(eid);
				list.add(tblAppointPathSD);
				tblESService.save(es);
				
				esLink.setTableName("TblAppointPathSD");
				esLink.setDataId(tblAppointPathSD.getId());
				esLink.setTblES(es);
				esLink.setEsType(443);
				esLink.setEsTypeDesc("病理负责人提交病理任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("提交任命","病理专题负责人任命","提交病理专题负责人任命,专题编号："+tblAppointPathSD.getStudyNo()+"为 "+tblAppointPathSD.getPathSD());
			}
			tblAppointPathSDService.updateAll(list,list2);
			// 病理提交 LIST
			writeNotification_path(list);
			json.setSuccess(true);
			json.setMsg("签字成功");
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
	
	
	//提交   病理
	public void submitAppointPA(){
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
		
		//提交病理
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<TblAppointPathSD> list = new ArrayList<TblAppointPathSD>();
		List<TblStudyItem> list2 = new ArrayList<TblStudyItem>();
		Json json = new Json();
		try{
			for(String id:idList){
				TblAppointPathSD tblAppointPathSD = tblAppointPathSDService.getById(id);
				tblAppointPathSD.setState(1);
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//tblAppointQA.setPoolNum(poolNum);
				tblAppointPathSD.setFMCode(user.getUserName());
				
				String studyNo = tblAppointPathSD.getStudyNo();
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setPathSD(tblAppointPathSD.getPathSD());
				studyItem.setPathSDCode(tblAppointPathSD.getPathSDCode());
				list2.add(studyItem);
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("病理负责人提交病理任命");
				es.setEsType(443);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				
				tblAppointPathSD.setAppointSignID(eid);
				list.add(tblAppointPathSD);
				tblESService.save(es);
				
				esLink.setTableName("TblAppointPathSD");
				esLink.setDataId(id);
				esLink.setTblES(es);
				esLink.setEsType(443);
				esLink.setEsTypeDesc("病理负责人提交病理任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("提交任命","病理专题负责人任命","提交病理专题负责人任命,专题编号："+tblAppointPathSD.getStudyNo()+" 为 "+tblAppointPathSD.getPathSD());
			}
			tblAppointPathSDService.updateAll(list,list2);
			// 病理提交 LIST
			 writeNotification_path(list);
			 json.setSuccess(true);
			 json.setMsg("签字成功");
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
	    Json json = new Json();
	    try{	
	        for(String id:idList){
				TblAppointQA tblAppointQA = tblAppointQAService.getById(id);
				tblAppointQA.setState(-1);
				tblAppointQA.setCancelDate(new Date());
				list.add(tblAppointQA);
				
				TblAppointQA appointQA = new TblAppointQA();
				appointQA.setId(tblAppointQAService.getKey());
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
	       try {
	    	   // 重新任命QA   list2
	    	   writeNotification_qa(list2);
			} catch (Exception e) {
				System.out.println("发送通知失败！");
			}
	       json.setSuccess(true);
		   json.setMsg("签字成功");
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
	
	
	
    /**重新任命PA*/
	public void updateAppointPA(){
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			String selectid = ids[i];
			String[] strarray = selectid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				idList.add(strarray[j].trim());
			}
		}
		List<TblAppointPathSD> list = new ArrayList<TblAppointPathSD>();
		List<TblAppointPathSD> list2 = new ArrayList<TblAppointPathSD>();
		List<TblStudyItem> list3 = new ArrayList<TblStudyItem>();
	    Json json = new Json();
		try{
		    for(String id:idList){
				TblAppointPathSD tblAppointPathSD = tblAppointPathSDService.getById(id);
				tblAppointPathSD.setState(-1);
				tblAppointPathSD.setCancelDate(new Date());
				list.add(tblAppointPathSD);
				
				TblAppointPathSD appointPathSD = new TblAppointPathSD();
				appointPathSD.setId(tblAppointPathSDService.getKey());
				appointPathSD.setContractCode(tblAppointPathSD.getContractCode());//合同编号
				appointPathSD.setStudyName(tblAppointPathSD.getStudyName());//项目名称
				appointPathSD.setStudyNo(tblAppointPathSD.getStudyNo());//专题编号
				appointPathSD.settINo(tblAppointPathSD.gettINo());//供试品编码
				appointPathSD.settIName(tblAppointPathSD.gettIName());//供试品名称
				appointPathSD.setPathSDCode(model.getSd());//病理编码 前台传过来的，用sd暂时接受
				appointPathSD.setPathSD(userService.getRealNameByUserName(model.getSd()));//病理姓名
				appointPathSD.setAppointDate(new Date());//SD任命时间
				appointPathSD.setState(1);//状态
				//String poolNum = poolNumberService.getNextSDCommissionSerizlnumber();
				//appointSD.setPoolNum(poolNum);
				String studyNo = appointPathSD.getStudyNo();
				TblStudyItem studyItem = tblStudyItemService.getByStudyNoStudyItem(studyNo);
				studyItem.setPathSD(appointPathSD.getPathSD());
				studyItem.setPathSDCode(appointPathSD.getPathSDCode());
				list3.add(studyItem);
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				//验证通过则进行一下操作
				
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("病理负责人重新病理");
				es.setEsType(444);
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
				tblESService.save(es);
				appointPathSD.setAppointSignID(eid);
				list2.add(appointPathSD);
				esLink.setTableName("TblAppointPathSD");
				esLink.setDataId(id);
				esLink.setTblES(es);
				esLink.setEsType(444);
				esLink.setEsTypeDesc("病理负责人重新病理任命签字确认");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESLinkService.save(esLink);
				//日志录入
				writeLog("重新任命病理","病理专题负责人任命","重新任命病理，专题编号："+appointPathSD.getStudyNo()+" 由"+tblAppointPathSD.getPathSD()+"改为"+appointPathSD.getPathSD());
		   }
		   tblAppointPathSDService.updateAgainAll(list,list2,list3);
		   //重新任命 病理  list2
		   writeNotification_path(list2);
			json.setSuccess(true);
			json.setMsg("签字成功");
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
	
	
	/**
	 * 发送通知(SD任命)
	 * @param mapList
	 */
	private void writeNotification_sd(List<TblAppointSD> mapList) {
		if(null != mapList && mapList.size()>0){
			String sender = getCurrentRealName();
			for(TblAppointSD map : mapList){
				String studyNo = (String)map.getStudyNo();
				//String realName = (String)map.getSd();
				String userName = (String)map.getSdCode();
				//当前时间
				String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
				//通知消息
				TblNotification tblNotification = new TblNotification();
//				TblStudyItem tblStudyItem = tblStudyItemService.getByStudyNoStudyItem(map.getStudyNo());
//				String  animal = tblStudyItem.getAnimalType();
//				String  studyName = tblStudyItem.getStudyName();
//				String tiCode = tblTestItemService.getTiNameByContractAndTiNo(tblStudyItem.getContractCode(),tblStudyItem.getTiNo());
//				if( null == tiCode ||tiCode.equals("null") ){
//					tiCode = "";
//				}
//				if(null == animal  || animal.equals("null") ){
//					animal = "";
//				}
//				if( null == studyName  || studyName.equals("null") ){
//					studyName = "";
//				}
//				String str =tiCode + animal + studyName;
				TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNo);
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				String studyNoName = "";
				if(null != dictStudyType &&  dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
				tblNotification.setMsgTitle("专题负责人任命,专题编号：　"+studyNo+"，专题名称：　"+studyNoName+"");
				//String msgContent = realName+",您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				String findDate = "";
				if(null != studyItem.getFinishDate() && !studyItem.getFinishDate().equals("")){
					findDate = "要求完成日期为："+DateUtil.dateToString(studyItem.getFinishDate(),"yyyy-MM-dd");
				}
				msgContent = msgContent+"FM("+sender+")于"+currentDate+"任命您为  " +studyNo+"专题的负责人，专题名称：　"+studyNoName+""+
				findDate+
				"  ，特此提醒";
				if(null != map.getRemark() && (!map.getRemark().equals(""))){
					msgContent = msgContent + "<br>";
					msgContent = msgContent + "注："+ map.getRemark();
				}
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				
				tblNotification.setSender(getCurrentRealName());
				
				tblNotification.setSendTime(new Date());
				//接收者列表
				List<String> receiverList = new ArrayList<String>();
				receiverList.add(userName);
				tblNotificationService.save(tblNotification,receiverList);
				
			}
			
		}
		
	}
	/**
	 * 发送通知(QA任命)
	 * @param mapList
	 */
	private void writeNotification_qa(List<TblAppointQA> mapList) {
		if(null != mapList && mapList.size()>0){
			String sender = getCurrentRealName();
			for(TblAppointQA map : mapList){
				String studyNo = (String)map.getStudyNo();
				TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNo);
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				String studyNoName = "";
				if(null !=dictStudyType && dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
		
				//String realName = (String)map.getQa();
				String userName = (String)map.getQaCode();
				//当前时间
				String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
				//通知消息
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("专题编号：　"+studyNo+"，专题名称：　"+studyNoName+"QA任命");
				//String msgContent = realName+",您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				msgContent = msgContent+""+sender+"于"+currentDate+"任命您为 专题编号：　 " +studyNo+
				" 专题名称："+studyNoName+"　专题的QA负责人,特此提醒";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				
				tblNotification.setSender(getCurrentRealName());
				
				tblNotification.setSendTime(new Date());
				//接收者列表
				List<String> receiverList = new ArrayList<String>();
				receiverList.add(userName);
				tblNotificationService.save(tblNotification,receiverList);
				
			}
			
		}
		
	}
	
	/**
	 * 发送通知(病理任命)
	 * @param mapList
	 */
	private void writeNotification_path(List<TblAppointPathSD> mapList) {
		if(null != mapList && mapList.size()>0){
			String sender = getCurrentRealName();
			for(TblAppointPathSD map : mapList){
				String studyNo = (String)map.getStudyNo();
				TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNo);
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				String studyNoName = "";
				if(null !=dictStudyType && dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
				String userName = (String)map.getPathSDCode();
				//当前时间
				String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
				//通知消息
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("专题编号：　"+studyNo+"，专题名称：　"+studyNoName+"病理负责人任命");
				//String msgContent = realName+",您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				msgContent = msgContent+""+sender+"于"+currentDate+"任命您为专题编号：　" +studyNo+
				"专题名称：　"+studyNoName+"　专题的病理负责人,特此提醒";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				
				tblNotification.setSender(getCurrentRealName());
				
				tblNotification.setSendTime(new Date());
				//接收者列表
				List<String> receiverList = new ArrayList<String>();
				receiverList.add(userName);
				tblNotificationService.save(tblNotification,receiverList);
				
			}
			
		}
		
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

	public String getIsExistFileDis() {
		return isExistFileDis;
	}

	public void setIsExistFileDis(String isExistFileDis) {
		this.isExistFileDis = isExistFileDis;
	}

	public TblStudyFileIndexService getTblStudyFileIndexService() {
		return tblStudyFileIndexService;
	}

	public void setTblStudyFileIndexService(
			TblStudyFileIndexService tblStudyFileIndexService) {
		this.tblStudyFileIndexService = tblStudyFileIndexService;
	}

    
	

}
