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
import com.lanen.model.schedule.TblSOLeader;
import com.lanen.model.schedule.TblSOLeaderJson;
import com.lanen.model.schedule.TblTOLeader;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblSOLeaderService;
import com.lanen.service.schdeule.TblStudyResService;
import com.lanen.service.schdeule.TblTOLeaderService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 课题操作
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class TblSOLeaderAction extends BaseAction<TblSOLeader> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TblSOLeaderService tblSOLeaderService;

	
	/** 用户Service */
	@Resource
	private UserService UserService;
	
	@Resource
	private TblTOLeaderService tblTOLeaderService;
	
	@Resource
	private TblESService tblESService;
	
	@Resource
	private TblESLinkService tblESLinkService;

	@Resource
	private  TblStudyResService tblStudyResService;
	private String resMans;

	private Date startime;
	private Date endtime;
	
	private String solid;
	
	private String userNamesStr;
	
	private String sort;//排序列名
	
	private String order;

	private String batchLeaderAndTaskName;//人和项目
	
	private String scheduleIDs;//日程id
	
	private String ids;
	/** 列表 */
	@SuppressWarnings("static-access")
	public String list() throws Exception {
		Date nowDate = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
		Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endtime = sdf.format(nowDate);
		String startime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		ActionContext.getContext().put("solid", model.getId());
		String privilege = "";
		User user1 = (User) ActionContext.getContext().getSession().get("user");
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理") ){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 1;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 2;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 3;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 4;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 5;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 6;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 7;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 8;
		}
		ActionContext.getContext().put("privilege", privilege);
		
		return "list";
	}

	public void loadList() {

     List<TblSOLeaderJson> tollist = tblTOLeaderService.getTOLeader(startime, endtime);
        List<TblSOLeaderJson> listJson = new ArrayList<TblSOLeaderJson>();
		String taskName = "";
		//判断是否打开下拉树
		String setStates = "";
		boolean isFloor = false;//是否是区域负责人
		User user1 = (User) ActionContext.getContext().getSession().get("user");
		for(TblSOLeaderJson obj:tollist){
			boolean falg = false;
			int taskKind = obj.getTaskKind();
			if(taskKind == 1 ){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理") ){
					falg = true;
				}
			} 
			if(taskKind == 2){
			    if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")){
					falg = true;
				}else{
					// 判断是否是区域负责人
					System.out.print("--");
					boolean study = tblStudyResService.isExistThisStudyNoAndUser(obj.getStudyNo(), user1.getUserName());
					if(study){
						falg = true;
						isFloor = true;
					}else{
						continue;
					}
				}
			} 
			
			if(taskKind == 3){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")){
					falg = true;
				}
			} 
			if(taskKind == 4){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")){
					falg = true;
				}
			} 
			if(taskKind == 5){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")){
					falg = true;
				}
			} 
			if(taskKind == 6){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")){
					falg = true;
				}
			}
			if(taskKind == 7){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")){
					falg = true;
				}
			}
			if(taskKind == 8){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")){
					falg = true;
				}
			}
			
			if((!("#"+taskName+"#").contains("#"+obj.getId()+"#") )|| taskName.equals("")){
				TblSOLeaderJson leaderJson = new TblSOLeaderJson();
				leaderJson.setId(obj.getId());
				leaderJson.setTaskName(obj.getTaskName());
				leaderJson.setIconCls("icon-space");
				if(falg){
					leaderJson.setPrivilege("1");
				}else{
					leaderJson.setPrivilege("0");
				}
				
				leaderJson.set_parentId(obj.get_parentId());
				String leaderstr = "";
				for(TblSOLeaderJson obj1:tollist){
					if(obj1.getTaskName().equals(obj.getTaskName()) && obj1.getStudyNo().equals(obj.getStudyNo())){
						
						if(obj1.getSoleader() != null){
							if(leaderstr != ""){
								leaderstr = leaderstr + ",";
							}
						if( (null == obj1.getSignId() || obj1.getSignId().equals("")) ){
							leaderstr =leaderstr+"<a style='color:red;' >" +obj1.getSoleader()+"</a>";
						}else{
							
								leaderstr =leaderstr+ obj1.getSoleader();
							}
						}
					}
					
				}
				leaderJson.setSoleader(leaderstr);
				leaderJson.setStartDate(obj.getStartDate());
				String show = leaderJson.getPrivilege();
				if(show.equals("1")){
					listJson.add(leaderJson);
				}
			
				taskName = taskName +"#"+ obj.getId()+"#";
				setStates = setStates + obj.get_parentId() +",";
			}
			
		}
		List<TblSOLeader> list =tblSOLeaderService.getBySOlList(startime, endtime);
		String studyNo = "";
		for(TblSOLeader obj:list){
			if((!studyNo.contains(obj.getStudyNo()) )|| studyNo.equals("")){
				TblSOLeaderJson leaderJson = new TblSOLeaderJson();
				if( isFloor ){
					boolean study = tblStudyResService.isExistThisStudyNoAndUser(obj.getStudyNo(), user1.getUserName());
					if(!study){
						continue;
					}
				}
				
				leaderJson.setId(obj.getId());
				leaderJson.setStudyNo(obj.getStudyNo());
				leaderJson.setIconCls("icon-space");
				leaderJson.setPrivilege("1");
				leaderJson.set_parentId("");
				String leaderstr = "";
				for(TblSOLeader obj1:list){
					if(obj1.getStudyNo().equals(obj.getStudyNo()) ){
						String leaderName ="";
						int leaderTaskKind = obj1.getTaskKind();
						    if(leaderTaskKind == 1 ){
							   leaderName ="委托管理：";
							} 
							if(leaderTaskKind == 2){
							   leaderName ="动物试验：";
							} 
							if(leaderTaskKind == 3){
							   leaderName ="临床检验：";
							} 
							if(leaderTaskKind == 4){
								leaderName ="毒性病理：";
							} 
							if(leaderTaskKind == 5){
								leaderName ="QA管理：";
							} 
							if(leaderTaskKind == 6){
								leaderName ="供试品管理：";
							}
							if(leaderTaskKind == 7){
								leaderName ="分析：";
							}
							if(leaderTaskKind == 8){
								leaderName ="生态毒理：";
							}
						
						if(obj1.getSoleader() != null){
							 if(!leaderstr.equals("") ){
								 leaderstr = leaderstr +" ; ";
							 }
							 if( (null == obj1.getSignId() || obj1.getSignId().equals(""))){
								leaderstr =leaderstr+"<a style='color:red;' >"+leaderName +obj1.getSoleader()+"</a>";
							 }else{
								leaderstr =leaderstr+ leaderName +obj1.getSoleader();
							 }
						}
					}
					
				}
				leaderJson.setSoleader(leaderstr);
				
				listJson.add(leaderJson);
				studyNo = studyNo + obj.getStudyNo()+",";
			}
			
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", listJson);
		map.put("solid", solid);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void loadListSOL() {
		List<TblSOLeader> signlist = tblSOLeaderService.getByStudyNo(model
				.getStudyNo());
		List<TblSOLeaderJson> list = new ArrayList<TblSOLeaderJson>();

		for (TblSOLeader obj : signlist) {
			TblSOLeaderJson json = new TblSOLeaderJson();
			json.setId(obj.getId());
			json.setStudyNo(obj.getStudyNo());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = "";
			String endTime = "";
			if (null != obj.getStartDate()) {
				startTime = sdf.format(obj.getStartDate());
			}
			String endTimeAndName = "";
			if (null != obj.getEndDate()) {
				endTime = sdf.format(obj.getEndDate());
				String esignid = obj.getEndDateSignId();
				if (null != esignid) {
					TblES tblES = tblESService.getById(esignid);
					endTimeAndName = endTime + "(" + tblES.getSigner() + ")";
				}else{
					endTimeAndName = endTime;
				}
			} else {
				json.setFinsh("1");
			}

			json.setEndDate(endTimeAndName);
			json.setStartDate(startTime);
			String name =  UserService.getRealNameByUserName(obj.getSoleader());

			String signId = obj.getSignId();
			if (null != signId && !signId.equals("")) {
				TblES tblES = tblESService.getById(signId);
				json.setSignId(tblES.getSigner());
			} else {
				json.setFinsh("1");
			}

			json.setSoleader(name);
			if(obj.getTaskKind() == model.getTaskKind()){
				list.add(json);
			}
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}

	public String addUI() {
		ActionContext.getContext().put("id", model.getId());
		ActionContext.getContext().put("studyNo", model.getStudyNo());
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(nowDate);
		ActionContext.getContext().put("nowDay", str);
		/**
		 * 课题所在资源的人员选择
		 */
		List<TblSOLeader> signlist = tblSOLeaderService.getByStudyNo(model
				.getStudyNo());
		String name = "";
		for (TblSOLeader obj : signlist) {
			if (name != "") {
				name = name + ",";
			}
			String leader = UserService.getRealNameByUserName(obj.getSoleader());
			name = name + leader;
		}
		ActionContext.getContext().put("name", name);
		return "addUI";
	}

	public void addSOLeader() {
		List<?> list = tblSOLeaderService.studyresanimalhouseresManagersoleader(model.getStudyNo());
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id);
       	 	map.put("text",text);
       	 	mapList.add(map);
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	
	public void addFloorSOLeader() {
		List<?> list = tblSOLeaderService.studyresresManagersoleader(model.getStudyNo());
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id = (String) objs[0];
        	String text = (String) objs[1];
       	 	map = new HashMap<String,String>();
       	 	map.put("id", id);
       	 	map.put("text",text);
       	 	mapList.add(map);
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}

	public void selectAllSOLeader() {
        String studyNo = model.getStudyNo();
		User user1 =(User) ActionContext.getContext().getSession().get("user");
		List<Object> list = new ArrayList<Object>();
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理")&& (model.getTaskKind() == 1)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-委托管理",1);
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")&& (model.getTaskKind() == 2)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-动物试验",2);
			list.addAll(list1);
		} 
		
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")&& (model.getTaskKind() == 3)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-临床检验",3);
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")&&( model.getTaskKind() == 4)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-毒性病理",4);
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")&& (model.getTaskKind() == 5)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-QA管理",5);
			list.addAll(list1);
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")&& (model.getTaskKind() == 6)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-供试品管理",6);
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")&& (model.getTaskKind() == 7)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-分析",7);
			list.addAll(list1);
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")&& (model.getTaskKind() == 8)){
			List<?> list1 = tblSOLeaderService.findUserNameRealNameByResIdPrivilegeName(studyNo,"日程管理-列表-生态毒理",8);
			list.addAll(list1);
		}
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
        for (Object obj:list) {
        	Object[] objs = (Object[]) obj;
        	String id1 = (String) objs[0];
        	String text = (String) objs[1];
        	map = new HashMap<String,String>();
           	map.put("id", id1);
           	map.put("text",text);
           	boolean falg = true;
        	for(Map<String,String> obj1:mapList){
        		if(obj1.equals(map)){
        			falg = false;
        			break;
        		}
        	}
        	if(falg){
        		 mapList.add(map);
        	}
        }
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}

	public void add() {
		String[] array = null;
		if (resMans.contains(",")) {
			array = resMans.split(",");
		}
		List<TblSOLeader> list = new ArrayList<TblSOLeader>();
		if (null == array) {
			TblSOLeader soLeader = new TblSOLeader();
			soLeader.setSoleader(resMans);
			soLeader.setStartDate(model.getStartDate());
			soLeader.setEndDate(model.getEndDate());
			soLeader.setStudyNo(model.getStudyNo());
			list.add(soLeader);
		} else {
			for (int i = 0; i < array.length; i++) {
				TblSOLeader soLeader = new TblSOLeader();
				soLeader.setSoleader(array[i]);
				soLeader.setStartDate(model.getStartDate());
				soLeader.setEndDate(model.getEndDate());
				soLeader.setStudyNo(model.getStudyNo());
				list.add(soLeader);
			}
		}
		/**
		 * 课题所在资源的人员选择
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		boolean falg = true;
		List<TblSOLeader> SOLlist = tblSOLeaderService.getByStudyNo(model.getStudyNo());
		for (TblSOLeader sol : SOLlist) {
			for (TblSOLeader obj : list) {
				if (sol.getSoleader().equals(obj.getSoleader())
						&& ((null == sol.getEndDate()) || (sol.getEndDate() != null && obj
								.getStartDate().before(sol.getEndDate())))) {
					falg = false;
					String name = UserService.getRealNameByUserName(obj.getSoleader()); 
					map.put("msg", name);
					break;

				}
			}
		}
		if (falg) {
			tblSOLeaderService.saveAllSOLeader(list);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**
	 * 签字确认
	 */
	public void signAllSOLeader() {
		String id = resMans;
		Json json = new Json();
		TblSOLeader soleader = tblSOLeaderService.getById(id);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(401);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("专题负责人签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		soleader.setSignId(esId);
		soleader.setEndDate(model.getEndDate());
		Date endDate = soleader.getEndDate();
		if (null != endDate) {
			soleader.setEndDateSignId(esId);
		}
		esLink.setTableName("TblSOLeader");
		esLink.setDataId(id);
		esLink.setTblES(es);
		esLink.setEsType(401);
		esLink.setEsTypeDesc("专题负责人签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		try{
			tblESService.save(es);
		    tblESLinkService.save(esLink);
			// 日志录入
			tblSOLeaderService.update(soleader);
			writeLog("签字", "专题负责人签字确认，签字");
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
	
    public void signAll(){
    	List<String> singIdList = new ArrayList<String>();
		if(ids.contains(",")){
			String[] strarray = ids.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				singIdList.add(strarray[j]);
			}
		}else{
			singIdList.add(ids);
		}
		
    	Json json = new Json();
    	
    	List<TblSOLeader> list = new ArrayList<TblSOLeader>();
    	try{
    		 for (int i = 0; i < singIdList.size(); i++) {
    				String id = singIdList.get(i);
    			    TblSOLeader soleader = tblSOLeaderService.getById(id);
    				User tempUser = (User) ActionContext.getContext().getSession().get("user");
    				// 签名链接
    				TblESLink esLink = new TblESLink();
    				// 电子签名
    				TblES es = new TblES();
    				es.setEsType(401);
    				es.setSigner(tempUser.getRealName());
    				es.setEsTypeDesc("专题负责人签字确认");
    				es.setDateTime(new Date());
    				String esId = tblESService.getKey("TblES");
    				es.setEsId(esId);
    				tblESService.save(es);
    				soleader.setSignId(esId);
    				soleader.setEndDate(model.getEndDate());
    				Date endDate = soleader.getEndDate();
    				if (null != endDate) {
    					soleader.setEndDateSignId(esId);
    				}
    				list.add(soleader);
    				esLink.setTableName("TblSOLeader");
    				esLink.setDataId(id);
    				esLink.setTblES(es);
    				esLink.setEsType(401);
    				esLink.setEsTypeDesc("专题负责人签字确认");
    				esLink.setRecordTime(new Date());
    				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    				tblESLinkService.save(esLink);
    				// 日志录入
    				writeLog("签字", "专题负责人签字确认，签字");
    	    	}
    	    	tblSOLeaderService.updateAll(list);
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
	// 删除未签字的人员
	public void delectSOLeader() {
		tblSOLeaderService.delete(model.getId());
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("success", true);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void setupInvalid(){
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		String esType = "402";
		es.setEsType(Integer.parseInt(esType));
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("专题操作者设置无效签字");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		
		TblSOLeader  leader= tblSOLeaderService.getById(model.getId());
		leader.setEndDate(model.getEndDate());
		leader.setEndDateSignId(esId);
		
 
        esLink.setTableName("TblSOLeader");
    	esLink.setDataId(model.getId());
    	esLink.setTblES(es);
    	esLink.setEsType(Integer.parseInt(esType));
    	esLink.setEsTypeDesc("专题操作者设置无效签字");
    	esLink.setRecordTime(new Date());
    	esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
    	 try{
    		 tblESService.save(es);
     	     tblESLinkService.save(esLink);
     	     tblSOLeaderService.update(leader);
     		 // 日志录入
     		 writeLog("签字", "专题操作者设置无效签字，签字");
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
		tblLog.setOperatOject("专题负责人");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}

	// 设置负责人时间
	public String editUI() {
		String id = model.getId();
		ActionContext.getContext().put("id", id);
		ActionContext.getContext().put("studyNo", model.getStudyNo());
		ActionContext.getContext().put("taskKind", model.getTaskKind());
		String taskKindName ="";
		int taskKind = model.getTaskKind();
		if(taskKind==1){
			taskKindName = "委托管理";
		}else if(taskKind==2){
			taskKindName = "动物试验";
		}else if(taskKind==3){
			taskKindName = "临床检验";
		}else if(taskKind==4){
			taskKindName = "毒性病理";
		}else if(taskKind==5){
			taskKindName = "QA管理 ";
		}else if(taskKind==6){
			taskKindName = "供试品管理";
		}else if(taskKind==7){
			taskKindName = "分析";
		}else if(taskKind==8){
			taskKindName = "生态毒理";
		}
		ActionContext.getContext().put("taskKindName", taskKindName);
		
		return "editUI";
	}
	
	/**保存资源负责人（add or  edit）*/
	public void save() throws Exception {
		Json json = new Json();
		//2030-12-31
		
		Date startDate = model.getStartDate();
		//用户名列表
		List<String> userNameList = null;
		if(null != userNamesStr && userNamesStr.length() >= 1 ){
			userNameList = new ArrayList<String>();
			String[] userNames = userNamesStr.split(",");
			for(int j = 0;j < userNames.length;j++){
				userNameList.add(userNames[j]);
			}
		}
		List<String> ids = new ArrayList<String>();
		if(model.getStudyNo() == null ){
			//资源id 为空
			json.setMsg("与服务器交互错误");
		}else if(startDate == null){
			json.setMsg("请选择开始日期");
		}else if(userNameList ==null || userNameList.size()<1){
			json.setMsg("请选择常规任务操作者");
		}else{
			List<TblSOLeader> list = new ArrayList<TblSOLeader>();
			TblSOLeader leader =null;
			for(String userName:userNameList){
				//是否，资源负责人已存在且日期区间重叠
			
				boolean isExist = tblSOLeaderService.isExist(model.getStudyNo(),userName,startDate,model.getEndDate(),model.getTaskKind());
				if(isExist){
					String reamName = userService.getRealNameByUserName(userName);
					json.setMsg(reamName+",该负责人(选择日期区间内)已存在");
					break;
				}else{
					leader  = new TblSOLeader();
					String id = tblSOLeaderService.getKey();
					leader.setId(id);
					leader.setSoleader(userName);
					leader.setStudyNo(model.getStudyNo());
					leader.setStartDate(model.getStartDate());
					leader.setTaskKind(model.getTaskKind());
					list.add(leader);
					ids.add(id);
				}
				if(!isExist){
					tblSOLeaderService.saveAllSOLeader(list);
					json.setMsg("添加成功");
					json.setSuccess(true);
				}
			}
		}
		json.setObj(ids);
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	//跳转到批量设置
	public String toBatchSet(){
		Date startDate = model.getStartDate();
		Date  endDate = model.getEndDate();
		String endtime;
		String startime;
		if(startDate == null || endDate == null){
			Date nowDate = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(nowDate);
			calendar.add(calendar.MONTH, -3);// 把日期往后增加一天.整数往后推,负数往前移动
			Date date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 endtime = sdf.format(nowDate);
			 startime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		}else{
			 endtime = new SimpleDateFormat("yyyy-MM-dd").format(endDate);;
			 startime = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		}
		ActionContext.getContext().put("endtime", endtime);
		ActionContext.getContext().put("startime", startime);
		String privilege = "";
		User user1 = (User) ActionContext.getContext().getSession().get("user");
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理") ){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 1;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 2;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 3;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 4;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 5;
		} 
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 6;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 7;
		}
		if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")){
			if( privilege != ""){
				privilege = privilege +",";
			}
			privilege = privilege + 8;
		}
		ActionContext.getContext().put("privilege", privilege);
		
		return "toBatchSet";
	}

	
   public void loadListBatchSet(){
	   List<TblSOLeaderJson> tollist = tblTOLeaderService.getBatchTOLeader(startime, endtime,sort,order);
       List<TblSOLeaderJson> listJson = new ArrayList<TblSOLeaderJson>();
		String taskName = "";
		//判断是否打开下拉树
		String setStates = "";
		User user1 = (User) ActionContext.getContext().getSession().get("user");
		for(TblSOLeaderJson obj:tollist){
			boolean falg = false;
			int taskKind = obj.getTaskKind();
			if(taskKind == 1 ){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-委托管理") ){
					falg = true;
				}
			} 
			if(taskKind == 2){
			    if(userService.checkPrivilege(user1, "日程管理-常规任务分配-动物试验")){
					falg = true;
				}else{
					//判断是否是区域负责人
					System.out.print("--");
					boolean study = tblStudyResService.isExistThisStudyNoAndUser(obj.getStudyNo(), user1.getUserName());
					if(study){
						falg = true;
					}else{
						continue;
					}
				}
			} 
			
			if(taskKind == 3){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-临床检验")){
					falg = true;
				}
			} 
			if(taskKind == 4){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-毒性病理")){
					falg = true;
				}
			} 
			if(taskKind == 5){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-QA管理")){
					falg = true;
				}
			} 
			if(taskKind == 6){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-供试品管理")){
					falg = true;
				}
			}
			if(taskKind == 7){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-分析")){
					falg = true;
				}
			}
			if(taskKind == 8){
			if(userService.checkPrivilege(user1, "日程管理-常规任务分配-生态毒理")){
					falg = true;
				}
			}
			
			if((!("#"+taskName+"#").contains("#"+obj.getId()+"#") )|| taskName.equals("")){
				TblSOLeaderJson leaderJson = new TblSOLeaderJson();
				leaderJson.setId(obj.getId());
				//leaderJson.setStudyNo(obj.getStudyNo());
				leaderJson.setTaskName(obj.getTaskName());
				leaderJson.setIconCls("icon-space");
				if(falg){
					leaderJson.setPrivilege("1");
				}else{
					continue;
				}
				String leaderstr = "";
				for(TblSOLeaderJson obj1:tollist){
					if(obj1.getTaskName().equals(obj.getTaskName()) && obj1.getStudyNo().equals(obj.getStudyNo())){
						if(obj1.getSoleader() != null){
							if(leaderstr != ""){
								leaderstr = leaderstr + ",";
							}
						if( (null == obj1.getSignId() || obj1.getSignId().equals("")) ){
							leaderstr =leaderstr+"<a style='color:red;' >" +obj1.getSoleader()+"</a>";
						}else{
							
								leaderstr =leaderstr+ obj1.getSoleader();
							}
						}
					}
					
				}
				leaderJson.setSoleader(leaderstr);
				leaderJson.setStartDate(obj.getStartDate());
				leaderJson.setStudyNo(obj.getStudyNo());
				listJson.add(leaderJson);
				taskName = taskName +"#"+ obj.getId()+"#";
				setStates = setStates + obj.get_parentId() +",";
			}
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", listJson);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
   }
   
    /**批量保存*/
    public void saveBatchSetAction(){
    	List<String>  tList = new ArrayList<String>();
		if(null != batchLeaderAndTaskName && batchLeaderAndTaskName.length() >= 1 ){
			String[] userNames = batchLeaderAndTaskName.split(",");
			for(int j = 0;j < userNames.length;j++){
				tList.add(userNames[j]);
			}
		}
		List<String>  sList = new ArrayList<String>();
		if(null != scheduleIDs && scheduleIDs.length() >= 1 ){
			String[] schedule = scheduleIDs.split(",");
			for(int j = 0;j < schedule.length;j++){
				sList.add(schedule[j]);
			}
		}
    	List<?> list = tblTOLeaderService.getScheduleIdAndleaderName(sList,tList);
    	List<TblTOLeader> tlist = new ArrayList<TblTOLeader>();
    	for(int i =0;i<list.size();i++){
			Object[] objs = (Object[]) list.get(i);
			boolean falg = tblTOLeaderService. isExist((String)objs[0], (String)objs[1], model.getStartDate(), model.getEndDate());
			if(falg){
				continue;
			}
			if(null != objs[0] && null != objs[1] ){
				 TblTOLeader leader = new TblTOLeader();
				 leader.setId(tblTOLeaderService.getKey());
				 leader.setScheduleId((String)objs[0]);
				 leader.settOLeader((String)objs[1]);
				 leader.setStartDate(model.getStartDate());
				 leader.setEndDate(model.getEndDate());
				 tlist.add(leader);
			}
			
    	}
    	tblTOLeaderService.saveAllLeaderList(tlist);
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("success", true);
	    String json = JsonPluginsUtil.beanToJson(map);
	    writeJson(json);
    }
    
    /**
     * 批量保存前  检查
     */
    public void saveBatchSetCheck(){
    	Json json = new Json();
    	String msg = "";
    	List<String>  tList = new ArrayList<String>();
    	if(null != batchLeaderAndTaskName && batchLeaderAndTaskName.length() >= 1 ){
    		String[] userNames = batchLeaderAndTaskName.split(",");
    		for(int j = 0;j < userNames.length;j++){
    			tList.add(userNames[j]);
    		}
    	}
    	List<String>  sList = new ArrayList<String>();
    	if(null != scheduleIDs && scheduleIDs.length() >= 1 ){
    		String[] schedule = scheduleIDs.split(",");
    		for(int j = 0;j < schedule.length;j++){
    			sList.add(schedule[j]);
    		}
    	}
    	//scheduleID,taskLeader(日程id,src0129)
    	List<?> list = tblTOLeaderService.getScheduleIdAndleaderName(sList,tList);
    	for(int i =0;i<list.size();i++){
    		Object[] objs = (Object[]) list.get(i);
    		boolean falg = tblTOLeaderService. isExist((String)objs[0], (String)objs[1], model.getStartDate(), model.getEndDate());
    		if(falg){
    			String realName = userService.getRealNameByUserName((String)objs[1]);
    			msg = msg +realName+" 在日程("+objs[2]+","+objs[3]+")下已存在,<br>";
    		}
    	}
    	if(msg.equals("")){
    		json.setSuccess(true);
    	}else{
    		msg = msg+"已存在数据将不保存，是否继续";
    		json.setMsg(msg);
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(json);
    	writeJson(jsonStr);
    }
    //签字前检查
    public void checkSignBatch(){
    	List<String>  sList = new ArrayList<String>();
    	if(null != scheduleIDs && scheduleIDs.length() >= 1 ){
			String[] schedule = scheduleIDs.split(",");
			for(int j = 0;j < schedule.length;j++){
				sList.add(schedule[j]);
			}
		}
    	List<TblTOLeader> list = tblTOLeaderService.getByScheduleIdListLeaderList(sList);
    	Json json = new Json();
    	if(null != list && list.size() > 0 ){
    		json.setSuccess(true);
    	}else{
    		json.setSuccess(false);
    	}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
    }
	//批量签字
	public void signBatchSetAction(){
		List<String>  sList = new ArrayList<String>();
		if(null != scheduleIDs && scheduleIDs.length() >= 1 ){
			String[] schedule = scheduleIDs.split(",");
			for(int j = 0;j < schedule.length;j++){
				sList.add(schedule[j]);
			}
		}
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 应该仅取未签字的
		List<TblTOLeader> tblTOLeader = tblTOLeaderService.getByScheduleIdListLeaderList(sList);
		List<TblTOLeader> list = new ArrayList<TblTOLeader>();
		Json json = new Json();
		if(null != tblTOLeader && tblTOLeader.size() > 0){
		    try{
				for(TblTOLeader leader:tblTOLeader){
					// 签名链接
					TblESLink esLink = new TblESLink();
					// 电子签名
					TblES es = new TblES();
					es.setEsType(403);
					es.setSigner(tempUser.getRealName());
					es.setEsTypeDesc("任务负责人签字确认");
					es.setDateTime(new Date());
					String esId = tblESService.getKey("TblES");
					es.setEsId(esId);
					tblESService.save(es);
					
					TblTOLeader tOLeader = tblTOLeaderService.getById(leader.getId());
					tOLeader.setSignId(esId);
					Date endDate = tOLeader.getEndDate();
					if (null != endDate) {
						tOLeader.setEndDateSignId(esId);
					}
					//tblTOLeaderService.update(tOLeader);
					list.add(tOLeader);
					esLink.setTableName("TblTOLeader");
					esLink.setDataId(leader.getId());
					esLink.setTblES(es);
					esLink.setEsType(403);
					esLink.setEsTypeDesc("任务负责人签字确认");
					esLink.setRecordTime(new Date());
					esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
					tblESLinkService.save(esLink);
					// 日志录入
					writeLog("签字", "任务负责人签字确认，签字");
				}
				
				tblTOLeaderService.updateAll(list);
				json.setSuccess(true);
				json.setMsg("签字成功");
		    }catch(Exception e){
		        json.setSuccess(false);
		        json.setMsg("与数据库交互异常");
		        System.out.println("执行失败，出错种类"+e.getMessage()+".");
		   }finally{ 
		        System.out.println("执行结束");
		   } 
			
		}else{
			json.setSuccess(false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}
	public String getBatchLeaderAndTaskName() {
		return batchLeaderAndTaskName;
	}

	public void setBatchLeaderAndTaskName(String batchLeaderAndTaskName) {
		this.batchLeaderAndTaskName = batchLeaderAndTaskName;
	}

	public String getScheduleIDs() {
		return scheduleIDs;
	}

	public void setScheduleIDs(String scheduleIDs) {
		this.scheduleIDs = scheduleIDs;
	}

	public String getResMans() {
		return resMans;
	}

	public void setResMans(String resMans) {
		this.resMans = resMans;
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

	public String getSolid() {
		return solid;
	}

	public void setSolid(String solid) {
		this.solid = solid;
	}

	public String getUserNamesStr() {
		return userNamesStr;
	}

	public void setUserNamesStr(String userNamesStr) {
		this.userNamesStr = userNamesStr;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
}
