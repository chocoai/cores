package com.lanen.view.action.schdeule;

import java.text.SimpleDateFormat;
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
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblAnmialHouseJson;
import com.lanen.model.schedule.TblResManager;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblResManagerAction extends BaseAction<TblResManager>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	@Resource
	private TblResManagerService tblResManagerService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private TblESService tblESService;
	
	@Resource
	private TblESLinkService tblESLinkService;
    
	private String parentId;//父级Id
	
	private int resKind;//类型
	
	private String resMans;
	
	private String getRerIds;
	
	private String taskKind;
	
	private String currentResId;
	private String resFullName;
	private String userNamesStr;
	private String ids;
	
	/** 列表*/
	public String list() throws Exception {
		ActionContext.getContext().put("currentResId", currentResId);
		return "list";
	}
	
	/**list加载数据(json)*/
	public void loadList() {

		List<TblAnimalHouse> objList = tblAnimalHouseService.getAll();
		
		//存放  rows
		List<TblAnmialHouseJson> list = new ArrayList<TblAnmialHouseJson>();
		TblAnmialHouseJson houseJson =null;
		Map<String, String> userMap = userService.getRealName("试验系统部");
		
		//树形表格
		for (TblAnimalHouse houes : objList) {
			houseJson = new TblAnmialHouseJson();
			houseJson.setId(houes.getId());
			houseJson.setResKind(houes.getResKind());
			houseJson.setResName(houes.getResName());
			houseJson.set_parentId(houes.getParentId());
			
			List<TblResManager> roomMans = tblResManagerService.getByHouseId(houes.getId());
			
			String mans = "";
			String signs = "";
			
			for (TblResManager obj : roomMans) {
				String resManagerName =userMap.get(obj.getResManager());
				if(obj.getSignid() == null || "".equals(obj.getSignid())){
					resManagerName = "<a style='color:#e50000;'>"+resManagerName+"</a>";
				}
				if (mans != "") {
					mans = mans + " , ";
				}
				mans = mans + resManagerName;
				String sign = obj.getSignid();
				if (null == sign) {
					if (signs != "") {
						signs = signs + " , ";
					}
					signs = signs + userMap.get(obj.getResManager());
				}
			}
			if (null != signs && !signs.equals("")) {
				houseJson.setSign(signs + " 未确认签字");
			}
			houseJson.setResManager(mans);
			if (houes.getResKind() == 1) {
				houseJson.setState("open");
			} else if (houes.getResKind() == 2) {
				houseJson.setState("closed");
			}

			houseJson.setIconCls("icon-space");
			list.add(houseJson);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("currentResId", currentResId);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**设置页面*/
	public String setUI() throws Exception {
		ActionContext.getContext().put("currentResId", currentResId);
		TblAnimalHouse  animalHouse  = tblAnimalHouseService.getById(currentResId);
		
		int resKind = animalHouse.getResKind();	
		if(resKind == 1){
			ActionContext.getContext().put("resFullName", " "+animalHouse.getResName());
		}else if(resKind == 2){
			String pid = animalHouse.getParentId();
			TblAnimalHouse house = tblAnimalHouseService.getById(pid);
			ActionContext.getContext().put("resFullName", " "+house.getResName()+" "+animalHouse.getResName());
		}else if(resKind == 3){
			String pid = animalHouse.getParentId();
			TblAnimalHouse house = tblAnimalHouseService.getById(pid);
			TblAnimalHouse house1 = tblAnimalHouseService.getById(house.getParentId());
			ActionContext.getContext().put("resFullName",  " "+house1.getResName()+" "+house.getResName()+" "+animalHouse.getResName());
		}
		
		Date nowDate = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(nowDate);  
		ActionContext.getContext().put("currentDate", str);
		return "setUI";
	}
	
	/**资源负责人设置，加载数据*/
	public void loadResManager() throws Exception{
		List<?> dataList = tblResManagerService.getDataListByHouseId(currentResId);
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		if(null != dataList){
			for(Object obj:dataList){
				map = new HashMap<String,String>();
				Object[] objs= (Object[]) obj;
				map.put("id", (String)objs[0]);
				map.put("resFullName", resFullName);
				map.put("leader",(String)objs[1]);
				map.put("startDate", DateUtil.dateToString((Date)objs[2], "yyyy-MM-dd"));
				String endDateStr = DateUtil.dateToString((Date)objs[3], "yyyy-MM-dd");
				String signer = (String)objs[4];
				if(endDateStr != null && !endDateStr.equals("") && signer !=null && !"".equals(signer) ){
					endDateStr =endDateStr+"("+(String)objs[6]+")";
				}
				map.put("endDate", endDateStr);
				map.put("signer",signer);
				map.put("resManager",(String)objs[5]);
				mapList.add(map);
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		
		writeJson(jsonStr);
	}
	
	/**加载资源负责人  */
	public void loadComboboxData() throws Exception{
		List<?> list = tblResManagerService.findUserNameRealNameByResIdPrivilegeName(currentResId,"日程管理-列表-动物试验");
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
	/**保存资源负责人（add or  edit）*/
	public void save() throws Exception {
		Json json = new Json();
		
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
		List<String> ids =new  ArrayList<String>();
		if(currentResId == null ){
			//资源id 为空
			json.setMsg("与服务器交互错误");
		}else if(startDate == null){
			json.setMsg("请选择开始日期");
		}else if(userNameList ==null || userNameList.size()<1){
			json.setMsg("请选择动物房负责人");
		}else{
			List<TblResManager> tblResManagerList = new ArrayList<TblResManager>();
			TblResManager tblResManager =null;
			for(String userName:userNameList){
				//是否，资源负责人已存在且日期区间重叠
				boolean isExist = tblResManagerService.isExist(currentResId,userName,startDate,model.getEndDate());
				if(isExist){
					String reamName = userService.getRealNameByUserName(userName);
					json.setMsg(reamName+",该负责人(选择日期区间内)已存在");
					break;
				}else{
					tblResManager  = new TblResManager();
					String id = tblResManagerService.getKey();
					tblResManager.setId(id);
					tblResManager.setResId(currentResId);
					tblResManager.setResManager(userName);
					tblResManager.setStartDate(startDate);
					tblResManager.setEndDate(model.getEndDate());
					tblResManagerList.add(tblResManager);
					ids.add(id);
				}
				if(!isExist){
					tblResManagerService.saveAllresManager(tblResManagerList);
					json.setMsg("添加成功");
					json.setSuccess(true);
				}
			}
		}
		json.setObj(ids);
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**删除资源负责人*/
	public void delete() throws Exception {
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId())){
			TblResManager tblResManager = tblResManagerService.getById(model.getId());
			if(null != tblResManager){
				if(tblResManager.getSignid() == null || tblResManager.getSignid().equals("")){
					tblResManagerService.delete(model.getId());
					json.setSuccess(true);
				}else{
					//不存在
					json.setMsg("已签字，不可以删除");
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
	/**资源负责人签字*/
	public void sign() throws Exception{
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId())){
			TblResManager tblResManager = tblResManagerService.getById(model.getId());
			if(null != tblResManager){
				if(tblResManager.getSignid() != null && !tblResManager.getSignid().equals("")){
					//不存在
					json.setMsg("已签字，不可重复签字");
				}else{
					User tempUser = (User) ActionContext.getContext().getSession().get("user");
					String realName = tempUser.getRealName();
					// 签名链接
					TblESLink esLink = new TblESLink();
					// 电子签名
					TblES es = new TblES();
					es.setEsType(405);
					es.setSigner(realName);
					es.setEsTypeDesc("资源负责人签字确认");
					es.setDateTime(new Date());
					String esId = tblESService.getKey("TblES");
					es.setEsId(esId);
				    esLink.setTableName("TblResManager");
				    esLink.setDataId(model.getId());
				    esLink.setTblES(es);
				    esLink.setEsType(405);
			        esLink.setEsTypeDesc("资源责任负责人签字确认");
				    esLink.setRecordTime(new Date());
				    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				    tblResManager.setSignid(esId);
				    if(null != tblResManager.getEndDate()){
				    	tblResManager.setEndDateSignId(esId);
				    }
				    try{
				    	tblESService.save(es);
					    tblESLinkService.save(esLink);
					    tblResManagerService.update(tblResManager);
					    json.setSuccess(true);
					    // 日志录入
					    writeLog("签字", "资源责任负责人签字确认，签字");
					}catch(Exception e){
						 json.setSuccess(false);
						 json.setMsg("与数据库交互异常");
						 System.out.println("执行失败，出错种类"+e.getMessage()+".");
					}finally{ 
						 System.out.println("执行结束");
					} 
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
	
	/**置为无效（设置结束日期406）*/
	public void novail() throws Exception {
		//406资源责任负责人设置结束日期签字确认
		Json json = new Json ();
		if(null != model.getId() && !"".equals(model.getId()) && null != model.getEndDate()){
			TblResManager tblResManager = tblResManagerService.getById(model.getId());
			if(null != tblResManager ){
				if( null == tblResManager.getEndDate()){
					if(!tblResManager.getStartDate().after(model.getEndDate())){
						User tempUser = (User) ActionContext.getContext().getSession().get("user");
						String realName = tempUser.getRealName();
						// 电子签名
						TblES es = new TblES();
						es.setEsType(406);
						es.setSigner(realName);
						es.setEsTypeDesc("资源责任负责人设置结束日期签字确认");
						es.setDateTime(new Date());
						String esId = tblESService.getKey("TblES");
						es.setEsId(esId);
						
						// 签名链接
						TblESLink esLink = new TblESLink();
						esLink.setTableName("TblResManager");
						esLink.setDataId(model.getId());
						esLink.setTblES(es);
						esLink.setEsType(406);
						esLink.setEsTypeDesc("资源责任负责人设置结束日期签字确认");
						esLink.setRecordTime(new Date());
						esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
						
						try{ 
							tblESService.save(es);
							tblESLinkService.save(esLink); 
							tblResManager.setEndDate(model.getEndDate());
							tblResManager.setEndDateSignId(esId);
							tblResManagerService.update(tblResManager);
							json.setSuccess(true);
							// 日志录入
						    writeLog("签字", "资源责任负责人设置结束日期签字确认，签字");
						    json.setSuccess(true);
						}catch(Exception e){
						     json.setSuccess(false);
						     json.setMsg("与数据库交互异常");
						     System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
						     System.out.println("执行结束");
						} 
						
						
					}else{
						json.setMsg("开始日期不能大于结束日期");
					}
				}else{
					//不存在
					json.setMsg("结束日期已设置，不可重复设置");
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
	
	/**用于查找所有用户*/
	public void MemberloadList(){
		 List<TblResManager> roomMans  = tblResManagerService.getByHouseId(model.getResId());
         List<User> newlist = new ArrayList<User>();
         String name = ""; 
         for(TblResManager obj:roomMans){
        	 name = name + obj.getResManager();
         }
 		if(taskKind.equals("1") ){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-委托管理");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("2")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-动物试验");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("3")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-临床检验");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("4")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-毒性病理");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("5")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-QA管理");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("6")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-供试品管理");
 			 for (User entry : userlist) {
 				if(name != "" && name.contains( entry.getId())){
	        		 continue;
	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("7")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-分析");
 			 for (User entry : userlist) {
 				 if(name != "" && name.contains( entry.getId())){
 	        		 continue;
 	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }else if(taskKind.equals("7")){
 			 List<User> userlist= userService.findByPrivilegeName("日程管理-列表-生态毒理");
 			 for (User entry : userlist) {
 				 if(name != "" && name.contains( entry.getId())){
 	        		 continue;
 	        	 } 
 	        	  User user2 = new User();
 	        	  user2.setId( entry.getId());
 	        	  user2.setRealName(entry.getRealName());
 				  newlist.add(user2);
 	        } 
 		 }
         Map<String,Object> map = new HashMap<String,Object>();
 		 map.put("rows", newlist);
 		 String json = JsonPluginsUtil.beanToJson(map);
 		 writeJson(json);
	}
	/**
	 * 资源负责人选择列表
	 */
	public void MemberloadList2(){
		//根据资源Id查询所有资源负责人的信息
		List<TblResManager> roomMans  = tblResManagerService.getByHouseId(model.getResId());
        Map<String, String> user= userService.getRealName("试验系统部");
        List<User> newlist = new ArrayList<User>();
        String name = ""; 
        //所有资源负责人组成字符串
        for(TblResManager obj:roomMans){
       	 name = name + obj.getResManager();
        }
        for (Map.Entry<String, String> entry : user.entrySet()) {
       	 User user2 = new User();
       	 //如果用户名和已查选的资源负责人相同，勾选
       	 if(name != "" && name.contains( entry.getKey())){
       		 user2.setRemark("1");
       	 } 
       	 user2.setId( entry.getKey());
       	 user2.setRealName(entry.getValue());
       	 newlist.add(user2);
        }
        Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", newlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**签字*/
	public void signResManager(){
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
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(405);
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc("资源责任负责人签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		List<TblResManager> list = new ArrayList<TblResManager>();
		for (int i = 0; i < singIdList.size(); i++) {
			String id = singIdList.get(i);
			TblResManager roomMans  = tblResManagerService.getById(id);
			roomMans.setSignid(esId);
			list.add(roomMans);
		}
	    esLink.setTableName("TblResManager");
	    esLink.setDataId(ids);
	    esLink.setTblES(es);
	    esLink.setEsType(405);
        esLink.setEsTypeDesc("资源责任负责人签字确认");
	    esLink.setRecordTime(new Date());
	    esLink.setLinkId(tblESLinkService.getKey("TblESLink"));

	    try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			tblResManagerService.updateAllresManager(list);
			// 日志录入
	    	writeLog("签字", "资源责任负责人签字确认，签字");
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
		tblLog.setOperatOject("资源责任负责人");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}	
	
	
	public String getGetRerIds() {
		return getRerIds;
	}

	public void setGetRerIds(String getRerIds) {
		this.getRerIds = getRerIds;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getResKind() {
		return resKind;
	}

	public void setResKind(int resKind) {
		this.resKind = resKind;
	}

	public String getResMans() {
		return resMans;
	}

	public void setResMans(String resMans) {
		this.resMans = resMans;
	}

	public String getCurrentResId() {
		return currentResId;
	}

	public void setCurrentResId(String currentResId) {
		this.currentResId = currentResId;
	}

	public String getResFullName() {
		return resFullName;
	}

	public void setResFullName(String resFullName) {
		this.resFullName = resFullName;
	}

	public String getTaskKind() {
		return taskKind;
	}

	public void setTaskKind(String taskKind) {
		this.taskKind = taskKind;
	}

	public String getUserNamesStr() {
		return userNamesStr;
	}

	public void setUserNamesStr(String userNamesStr) {
		this.userNamesStr = userNamesStr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
