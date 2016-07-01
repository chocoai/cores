package com.lanen.view.action.studyplan;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
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
import com.lanen.model.studyplan.TbLWeighData;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblDoseSetting_json;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTiprpAppData;
import com.lanen.model.studyplan.TblTiprpAppData_json;
import com.lanen.model.studyplan.TblTiprpAppInd;
import com.lanen.model.studyplan.TblTiprpAppInd_json;
import com.lanen.model.studyplan.TblTiprpAppRecDt;
import com.lanen.model.studyplan.TblTiprpAppRecDt_json;
import com.lanen.model.studyplan.TblWeighInd;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.TbLWeightDataService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblAnimalService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.service.studyplan.TblTiprpAppDataService;
import com.lanen.service.studyplan.TblTiprpAppIndService;
import com.lanen.service.studyplan.TblTiprpAppRecDtService;
import com.lanen.service.studyplan.TblWeightIndService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblTiprpAppIndAction extends BaseAction<TblTiprpAppInd>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 供试品申请Service*/
	@Resource
	private TblTiprpAppIndService tblTiprpAppIndService;
	@Resource
	private TblTiprpAppRecDtService tblTiprpAppRecDtService;
	@Resource
	private TblTiprpAppDataService tblTiprpAppDataService;
	
	/** 动物体重Service*/
	@Resource
	private TblWeightIndService tblWeightIndService;
	@Resource
	private TbLWeightDataService tbLWeightDataService;
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	
	// 试验计划Service
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	// 动物信息Service
	@Resource
	private TblAnimalService tblAnimalService;
	
	// 剂量设置Service
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	
	// 课题编号
	private String studyNoPara;
	
	//签字类型
	private String esType;
	
	/**
	 * 剂量设置列表显示list
	 */
	private List<TblDoseSetting> tblDoseSettingList;
	
	private List<TblDoseSetting_json> tblDoseSettingListjson;
	
	// 列表显示内容
	private List<TblAnimal> tblAnimalList;
	
    private List<TbLWeighData> tblWeghtDataList;
	
	private List<TblWeighInd> tblWeghtList;
	
	private List<TblTiprpAppInd> tblTiprpAppIndlist ;
	
	private List<TblTiprpAppData> tblTiprpAppDatalist;
	
	private List<TblTiprpAppRecDt> tblTiprpAppRecDtlist;
	
	private List<Date> addtime;
	
	private TblTiprpAppInd tblTiprpAppInd;
	
	private TblTiprpAppRecDt tblTiprpAppRecDt;
	
	private TblWeighInd tblWeighInd;
	
	private int AppInd;
	
	private Date endTime;
	
	private String RecDt;
	
	private String aniCode;
	
	private String aniWeight;
	
	private String smplWeight;
	
	private int capsNum;
	
	private String weightUnit; //体重单位
	private String DatasmplUnit;//供试品单位
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	private List<String> group;//剂量分组的组别
	
	/**
	 * 列表显示
	 * @return
	 */
	public String list(){
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		String Studydirector= studyPlan.getStudydirector();
		String usernameString = tempUser.getRealName();
		if(!Studydirector.equals(usernameString)){
			ActionContext.getContext().put("left_member", "readonly");
		}
		return "list";
	}
	
	public void toNewApply() throws ParseException{
		
		//清楚缓存
		ActionContext ac = ActionContext.getContext();  
		Map<String,Object> session = ac.getSession();
		session.remove("tblTiprpAppInd");
		session.remove("tblTiprpAppData");
		session.remove("addtime");
		
		
		//登录信息
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flage = true;
		
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		int isAnimalWegihtES =0;
		int isAnimalWegihtFY =0;
		if(!tblWeghtDataList.isEmpty()){
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
		isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
		}
		if(isAnimalWegihtFY==1){
		    tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		 }else{
	        tblWeghtDataList =  tbLWeightDataService.getByWeighSn(studyNoPara);
		 }
		for(TbLWeighData tblWeghtData:tblWeghtDataList){
			if(tblWeghtData.getWeight() == null){
				 flage = false;
			}
		}
            if(flage == false){
              map.put("success", false);
            }else{
            	map.put("success",true );
            }
			User user = (User) ActionContext.getContext().getSession().get("user");
			ActionContext.getContext().put("user", user);
			TblTiprpAppInd tblTiprpAppInd =  tblTiprpAppIndService.getByStudyNo(studyNoPara);
			if(tblTiprpAppInd != null && tblTiprpAppInd.getAppSn() > 0){
				map.put("SmplCode", tblTiprpAppInd.getSmplCode());
				map.put("WeighUnit", tblTiprpAppInd.getWeighUnit());
				map.put("SmplUnit", tblTiprpAppInd.getSmplUnit());
				map.put("DevType", tblTiprpAppInd.getDevType());
				map.put("DevVal", tblTiprpAppInd.getDevVal());
				map.put("CapsSpec",tblTiprpAppInd.getCapsSpec());
				map.put("Precision", tblTiprpAppInd.getPrecision());
				addtime = (List<Date>) ActionContext.getContext().getSession().get("addtime");
				List<Date> oldtime = new ArrayList<Date>();
				if(addtime == null){
					addtime = new ArrayList<Date>();
				}
				List<TblTiprpAppRecDt> list = tblTiprpAppRecDtService.getTiprpRecDtlist(studyNoPara, tblTiprpAppInd.getAppSn());
				for(TblTiprpAppRecDt time:list){
					oldtime.add(time.getRecDt());
				}
				int size=oldtime.size();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
				Date  smdate=sdf.parse(sdf.format(oldtime.get(0)));  
				Date  bdate=sdf.parse(sdf.format(oldtime.get(size-1)));
		        Calendar cal = Calendar.getInstance();    
		        cal.setTime(smdate);    
		        long time1 = cal.getTimeInMillis();                 
		        cal.setTime(bdate);    
		        long time2 = cal.getTimeInMillis();         
		        int between_days=(int) ((time2-time1)/(1000*3600*24));  
		       
        		for(TblTiprpAppRecDt time3:list){
					//DateFormat df = DateFormat.getDateInstance();
					Calendar   calendar   =   new   GregorianCalendar(); 
				    calendar.setTime(time3.getRecDt()); 
				    calendar.add(calendar.DATE,between_days+1);//把日期往后增加一天.整数往后推,负数往前移动 
				    Date nexttime=calendar.getTime();   //
					addtime.add(nexttime);
				}
				 ActionContext.getContext().getSession() .put("addtime",addtime);
				 map.put("StudyNo", tblTiprpAppInd.getStudyNo());
					map.put("AppSn", tblTiprpAppInd.getAppSn()+1);
			}else{
				map.put("StudyNo", studyNoPara);
				map.put("AppSn", 1);
				addtime = new ArrayList<Date>();
				ActionContext.getContext().getSession() .put("addtime",addtime);
				
			}
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}

	/**
	 * 新增保存
	 * @return String
	 */
	public void addSave() {
        Map<String,Object> map = new HashMap<String,Object>();
        if(nullCheck(tblTiprpAppInd)){
        TblTiprpAppInd newtblTiprpAppInd = tblTiprpAppIndService.getByStudyNoAppSn(tblTiprpAppInd.getStudyNo(), tblTiprpAppInd.getAppSn());
        TblTiprpAppInd tblTiprpAppInd1 = tblTiprpAppIndService.getByStudyNo(newtblTiprpAppInd.getStudyNo());
        tblTiprpAppInd1.setSmplCode(tblTiprpAppInd.getSmplCode());
        tblTiprpAppInd1.setWeighUnit(tblTiprpAppInd.getWeighUnit());
        tblTiprpAppInd1.setSmplUnit(tblTiprpAppInd.getSmplUnit());
        tblTiprpAppInd1.setCapsSpec(tblTiprpAppInd.getCapsSpec());
        tblTiprpAppInd1.setAppMan(tblTiprpAppInd.getAppMan());
        tblTiprpAppInd1.setAppTime(tblTiprpAppInd.getAppTime());
        tblTiprpAppInd1.setSmplNum(tblTiprpAppInd.getSmplNum());
        tblTiprpAppInd1.setPrecision(tblTiprpAppInd.getPrecision());
        tblTiprpAppIndService.update(tblTiprpAppInd1);
        map.put("success", true);   
        map.put("AppSn",tblTiprpAppInd1.getAppSn());
        map.put("StudyNo", tblTiprpAppInd.getStudyNo());
        }else{
        	map.put("success", false);  
        }
        String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	

	public void addSaveRecDt() throws ParseException {
        Map<String,Object> map = new HashMap<String,Object>();
        if(nullCheckRec(tblTiprpAppRecDt)){
        TblTiprpAppInd newtblTiprpAppInd = tblTiprpAppIndService.getByStudyNoAppSn(studyNoPara,tblTiprpAppRecDt.getAppSn());
        tblTiprpAppRecDtlist = tblTiprpAppRecDtService.getByStudyNo(studyNoPara, tblTiprpAppRecDt.getAppSn());
        if(tblTiprpAppRecDtlist.size() == 0 && tblTiprpAppRecDtlist.isEmpty()){
        	long nd = 1000*24*60*60;
        	long diff = endTime.getTime() - tblTiprpAppRecDt.getRecDt().getTime();
        	long day =  (long) Math.ceil(diff/nd);
        	
        	Calendar   calendar   =   new   GregorianCalendar(); 
            calendar.setTime(tblTiprpAppRecDt.getRecDt()); 
        	
            for(int i=0;i<=day; i++ ){
        	   TblTiprpAppRecDt tblTiprpAppRecDt1 = new TblTiprpAppRecDt();
        	   tblTiprpAppRecDt1.setTblTiprpAppInd(newtblTiprpAppInd);
        	   if( i==0){
                 tblTiprpAppRecDt1.setRecDt(tblTiprpAppRecDt.getRecDt());
        	   }else{
        		   calendar.add(calendar.DATE,1);
                   Date date=calendar.getTime(); 
                   tblTiprpAppRecDt1.setRecDt(date);
        	   }
               tblTiprpAppRecDt1.setAppSn(tblTiprpAppRecDt.getAppSn());
        	   tblTiprpAppRecDt1.setRecMan(tblTiprpAppRecDt.getRecMan());
        	   tblTiprpAppRecDt1.setStudyNo(studyNoPara);
        	   DateFormat fmt =new SimpleDateFormat("HH:mm");            
        	   String s = tblTiprpAppRecDt.getRecTime();
               Date date  = fmt.parse(s);
        	   tblTiprpAppRecDt1.setRecTimel(date);
        	   tblTiprpAppRecDtlist.add(tblTiprpAppRecDt1);
        	 }
        	 tblTiprpAppRecDtService.saveAllTiprpAppRecDt(tblTiprpAppRecDtlist);
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
	
	public void selectAppIndList(){
		 Long total =tblTiprpAppIndService.getTotalByStudyNo(studyNoPara);
		 List<TblTiprpAppInd> list = (List<TblTiprpAppInd>) tblTiprpAppIndService.gettblTiprpAppInd(studyNoPara);
		 if(null!=list && list.size()>0){
		 List<TblTiprpAppInd_json> tblTiprpAppIndlist = new ArrayList<TblTiprpAppInd_json>();
		 for(TblTiprpAppInd AppInd:list){
			 if(  AppInd.getAppTime() != null  && !AppInd.getAppTime().equals("") ){
		     TblTiprpAppInd_json tblTiprpAppInd = new TblTiprpAppInd_json() ;
			 tblTiprpAppInd.setStudyNo(AppInd.getStudyNo());
			 tblTiprpAppInd.setAppSn(AppInd.getAppSn());
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 String date = df.format(AppInd.getAppTime());
			 tblTiprpAppInd.setAppTime(date);
			 tblTiprpAppInd.setAppStatus(AppInd.getAppStatus());
			 tblTiprpAppIndlist.add(tblTiprpAppInd);
			 }else{
				 break;
			 }
		 }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", tblTiprpAppIndlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		}else{
		writeJson("");
		}
		 
	 }
	
	public void selectAppDataList(){
		 long i  = tblTiprpAppDataService.getTotalByStudyNo(studyNoPara);
		 List<TblTiprpAppData> list = tblTiprpAppDataService.getTiprpAppData(studyNoPara, AppInd);
		 if( list != null && list.size() > 0){
		 List<TblTiprpAppData_json> tblTiprpAppDatalist = new ArrayList<TblTiprpAppData_json>();
		 
		 tblTiprpAppInd = tblTiprpAppIndService.getByStudyNoAppSn(studyNoPara, AppInd);
		 
		// String precision = tblTiprpAppInd.getPrecision();
		 for(TblTiprpAppData AppInd:list){
			 TblTiprpAppData_json tblTiprpAppData = new TblTiprpAppData_json() ;
			 tblTiprpAppData.setAniCode(AppInd.getAniCode());
			 tblTiprpAppData.setAniWeight(AppInd.getAniWeight());
			 tblTiprpAppData.setWeighUnit(AppInd.getWeighUnit()); 
			 tblTiprpAppData.setCapsNum(AppInd.getCapsNum()); 
			 tblTiprpAppData.setSmplWeight(AppInd.getSmplWeight());
			 tblTiprpAppData.setSmplUnit(tblTiprpAppInd .getSmplUnit());
			 tblTiprpAppDatalist.add(tblTiprpAppData);
		 }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblTiprpAppDatalist);
		map.put("total", i);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		}else{
		writeJson("");
		}
		 
	 }
	
	public void selectAppRecDt(){
		 List<TblTiprpAppRecDt> list = tblTiprpAppRecDtService.getTiprpRecDtlist(studyNoPara, AppInd);
		 System.out.println(list.size());
		 if( list != null && list.size() > 0){
		 List<TblTiprpAppRecDt_json> tblTiprpAppRecDtlist = new ArrayList<TblTiprpAppRecDt_json>();
		 for(TblTiprpAppRecDt AppInd:list){
			 TblTiprpAppRecDt_json tblTiprpAppRecDt = new TblTiprpAppRecDt_json() ;
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 String date = df.format(AppInd.getRecDt());
			 tblTiprpAppRecDt.setShowtime(date); 
			 tblTiprpAppRecDt.setPrpStatus(AppInd.getPrpStatus());
			 tblTiprpAppRecDtlist.add(tblTiprpAppRecDt);
		 }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblTiprpAppRecDtlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		}else{
		writeJson("");
		}
		 
	 }
	
	public void revokeAppInd(){
		
	}
	/**
	 * 添加一个日期
	 */
	public void adddate(){
		Map<String,Object> map = new HashMap<String,Object>();
		addtime = (List<Date>) ActionContext.getContext().getSession().get("addtime");
		if(endTime !=  null ){
		if(addtime == null || addtime.size()<1){
			addtime = new ArrayList<Date>();
			map.put("success", true);
			addtime.add(endTime);
		}else{
			boolean flage = true;
			for(Date time:addtime){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
	        	String time1=sdf.format(time); 
	        	String time2=sdf.format(endTime); 
				if( time1.equals(time2) ){
					map.put("success", false);
					flage = false;
					return;
				}else{
				    map.put("success", true);
				}
			}
			if( flage ){
			addtime.add(endTime); 
			}
		}
		DateFormat df = DateFormat.getDateInstance();
		Calendar   calendar   =   new   GregorianCalendar(); 
	    calendar.setTime(endTime); 
	    calendar.add(calendar.DAY_OF_YEAR,1);//把日期往后增加一天.整数往后推,负数往前移动 
	    Date nexttime=calendar.getTime();   //
		String s = df.format(nexttime);
        ActionContext.getContext().getSession() .put("addtime",addtime);
        map.put("nexttime", s);
		}else {
		List<TblTiprpAppRecDt_json> tblTiprpAppRecDtlist = new ArrayList<TblTiprpAppRecDt_json>();
		if(null != addtime && !addtime.equals("")){
			for(Date time:addtime){
	        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
	        	String str=sdf.format(time); 
	        	TblTiprpAppRecDt_json tblTiprpAppRecDt = new TblTiprpAppRecDt_json() ;
	        	tblTiprpAppRecDt.setShowtime(str);
	        	tblTiprpAppRecDtlist.add(tblTiprpAppRecDt);
	        }
		}
	        
	     map.put("rows", tblTiprpAppRecDtlist);
		}
		String json = JsonPluginsUtil.beanToJson(map);
	    writeJson(json);
	}
	/**
	 * 删除addtime中的日期
	 */
	public void removeRecDttime(){
		addtime = (List<Date>) ActionContext.getContext().getSession().get("addtime");
		int index = 0;
		for(Date time:addtime){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
        	String time1=sdf.format(time); 
        	String time2=sdf.format(endTime); 
			if( time1.equals(time2) ){
			   addtime.remove(index);
			}
			index++;
		}
		ActionContext.getContext().getSession() .put("addtime",addtime);
		Map<String,Object> map = new  HashMap<String,Object>();
		String json = JsonPluginsUtil.beanToJson(map);
	    writeJson(json);
		
	}
	
	/**
	 * 保存申请
	 */
	public void toaddSaveAppInd(){
		Map<String,Object> map = new HashMap<String,Object>();
		//TblTiprpAppInd_json tblTiprpAppInd_json = (TblTiprpAppInd_json) ActionContext.getContext().getSession().get("tblTiprpAppInd");
		User user =(User) ActionContext.getContext().getSession().get("user");
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(tblTiprpAppInd.getStudyNo());
		int isAnimalWegihtES =0;
		int isAnimalWegihtFY =0;
		if(!tblWeghtDataList.isEmpty()){
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,tblTiprpAppInd.getStudyNo(),8);
		isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,tblTiprpAppInd.getStudyNo(),9);
		}
		if(isAnimalWegihtFY==1){
		 tblWeghtList =  tblWeightIndService.getByStudyNo(tblTiprpAppInd.getStudyNo());
		    AppInd = tblWeghtList.get(0).getWeighSn();
		 }else{
			 tblWeghtList =  tblWeightIndService.getByWeighSn(tblTiprpAppInd.getStudyNo());
			 AppInd = tblWeghtList.get(0).getWeighSn();
		 }
		 List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(tblTiprpAppInd.getStudyNo(), AppInd);
		if(nullCheck(tblTiprpAppInd)){
			TblTiprpAppInd_json tblTiprpAppInd_json1 = new TblTiprpAppInd_json();
			tblTiprpAppInd_json1.setAppMan(user.getRealName());
			tblTiprpAppInd_json1.setStudyNo(tblTiprpAppInd.getStudyNo());
			tblTiprpAppInd_json1.setAppSn(tblTiprpAppInd.getAppSn());
			tblTiprpAppInd_json1.setSmplCode(tblTiprpAppInd.getSmplCode());
			tblTiprpAppInd_json1.setWeighUnit(Indlist.get(0).getWeightUnit());
			tblTiprpAppInd_json1.setSmplUnit(tblTiprpAppInd.getSmplUnit());
			tblTiprpAppInd_json1.setCapsSpec(tblTiprpAppInd.getCapsSpec());
			tblTiprpAppInd_json1.setDevType(tblTiprpAppInd.getDevType());
			tblTiprpAppInd_json1.setDevVal(tblTiprpAppInd.getDevVal());
			tblTiprpAppInd_json1.setAutomatic(tblTiprpAppInd.getAutomatic());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(new  Date());
			tblTiprpAppInd_json1.setAppTime(date);
			tblTiprpAppInd_json1.setSmplNum(tblTiprpAppInd.getSmplNum());
			tblTiprpAppInd_json1.setPrecision(tblTiprpAppInd.getPrecision());
			tblTiprpAppInd_json1.setFixednumber(tblTiprpAppInd.getFixednumber());
			ActionContext.getContext().getSession().put("tblTiprpAppInd",tblTiprpAppInd_json1);
			map.put("success", true); 
		}else{
			if(stringCherck(tblTiprpAppInd.getSmplCode())){
				map.put("msg", "供试品编号不能为空！");
			}else if(tblTiprpAppInd.getDevVal() == null){
				map.put("msg", "允差不能为空！");
			}else if(tblTiprpAppInd.getCapsSpec() == 0){
				map.put("msg", "胶囊规格不能为空！");
			}else if(tblTiprpAppInd.getFixednumber() == 0){
				map.put("msg", "胶囊数量不能为空！");
			}
		   	map.put("success", false);
		}
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	
	/**
	 * 非空检查，false：空
	 * @param obj
	 * @return boolean getCapsSpec()
	 */
	public boolean nullCheck(TblTiprpAppInd obj){
		boolean flag = true;
		if(stringCherck(obj.getStudyNo())){
			flag = false;
		}else if(obj.getAutomatic() == 1){
			 if (obj.getCapsSpec() == 0) {
					flag = true;
			 }
			 if(obj.getFixednumber() == 0){
				  flag = true;
		        }
		}else if (stringCherck(obj.getSmplCode())) {
			flag = false;
		}else if (obj.getAutomatic() == 0) {
			 if (obj.getCapsSpec() == 0) {
					flag = false;
			 }
		}else if(obj.getAutomatic() == 2){
			  if(obj.getFixednumber() == 0){
				  flag = false;
		        }
		}else if (obj.getAppTime() == null) {
			flag = false;
		}else if(stringCherck(Double.toString(obj.getDevVal()))){
			flag = false;
		}
		return flag;
	}
	
	public void nullCheckData(){
		addtime = (List<Date>) ActionContext.getContext().getSession().get("addtime");
		Map<String,Object> map = new HashMap<String,Object>();
		if(addtime == null ){
			map.put("success", false);
		}else{
		if(addtime.size() == 0){
		 	map.put("success", false);
		}else{
		 	map.put("success", true);
		}}
		
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	public void  AppIndpreview(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		List<TblTiprpAppData_json> tblTiprpAppDatalist = new ArrayList<TblTiprpAppData_json>();
		
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		int isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
		int isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
		if(isAnimalWegihtES==1 && isAnimalWegihtFY==1 ){
			tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		}else{
			tblWeghtDataList = tbLWeightDataService.getByWeighSn(studyNoPara);
		}
		
		TblTiprpAppInd_json tblTiprpAppInd_json = (TblTiprpAppInd_json) ActionContext.getContext().getSession().get("tblTiprpAppInd");
		
		for(TbLWeighData adimal:tblWeghtDataList){
		TblTiprpAppData_json tblTiprpAppData1 = new TblTiprpAppData_json();
		tblTiprpAppData1.setAniCode(adimal.getAniCode());//动物编号
		tblTiprpAppData1.setAniWeight(adimal.getWeight());//动物体重
		tblTiprpAppData1.setStudyNo(studyNoPara);//课题编号
		tblTiprpAppData1.setWeighUnit(tblTiprpAppInd_json.getWeighUnit());//重量单位
		String precision = tblTiprpAppInd_json.getPrecision();
		tblTiprpAppData1.setSmplUnit(tblTiprpAppInd_json.getSmplUnit());
		
		double number = 0;
		int animalCodeMode = studyPlan.getAnimalCodeMode();//动物编号规则 1：A 2：B
		if( animalCodeMode == 1){
			int adimalGroup=Integer.valueOf(adimal.getAniCode().substring(0,1)).intValue();
			TblDoseSetting tblDoseSetting = tblDoseSettingService.getByStudyNoGroup(studyPlan, adimalGroup);
			String dosage= tblDoseSetting.getDosage();
			
			if(tblTiprpAppInd_json.getWeighUnit().equals("kg")){
				number =(Double.valueOf(adimal.getWeight()))*(Double.valueOf(dosage));
			}else if(tblTiprpAppInd_json.getWeighUnit().equals("g")){
				number =((Double.valueOf(adimal.getWeight()))*(Double.valueOf(dosage)))/1000;
			}
			NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			if(tblTiprpAppInd_json.getSmplUnit().equals("g")){
           	 number = number/1000;
           	 if(precision.equals("10")){
    		        nFormat.setMaximumFractionDigits(-1);
    			    nFormat.setMinimumFractionDigits(-1);
    			}else if(precision.equals("1")){
    				nFormat.setMaximumFractionDigits(0);
    			    nFormat.setMinimumFractionDigits(0);
    			}else if(precision.equals("0.1")){
    			    nFormat.setMaximumFractionDigits(1);
    			    nFormat.setMinimumFractionDigits(1); 
    			}else if(precision.equals("0.01")){
    			    nFormat.setMaximumFractionDigits(2);
    			    nFormat.setMinimumFractionDigits(2); 
    			}else if(precision.equals("0.001")){
    			    nFormat.setMaximumFractionDigits(3);
    			    nFormat.setMinimumFractionDigits(3); 
    			}
           	 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}else if(tblTiprpAppInd_json.getSmplUnit().equals("mg")){
				if(precision.equals("10")){
			        nFormat.setMaximumFractionDigits(-1);
				    nFormat.setMinimumFractionDigits(-1);
				}else if(precision.equals("1")){
					nFormat.setMaximumFractionDigits(0);
				    nFormat.setMinimumFractionDigits(0);
				}else if(precision.equals("0.1")){
				    nFormat.setMaximumFractionDigits(1);
				    nFormat.setMinimumFractionDigits(1); 
				}else if(precision.equals("0.01")){
				    nFormat.setMaximumFractionDigits(2);
				    nFormat.setMinimumFractionDigits(2); 
				}else if(precision.equals("0.001")){
				    nFormat.setMaximumFractionDigits(3);
				    nFormat.setMinimumFractionDigits(3);    
				}
				 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}
			
			
			
			tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			
		}else if( animalCodeMode == 2 ){
			int adimalGroup=Integer.valueOf(adimal.getAniCode().substring(1,2)).intValue();
			TblDoseSetting tblDoseSetting = tblDoseSettingService.getByStudyNoGroup(studyPlan, (adimalGroup+1));
			String dosage= tblDoseSetting.getDosage();
			if(tblTiprpAppInd_json.getWeighUnit().equals("kg")){
				number =(Double.valueOf(adimal.getWeight()))*(Double.valueOf(dosage));
			}else if(tblTiprpAppInd_json.getWeighUnit().equals("g")){
				number =((Double.valueOf(adimal.getWeight()))*(Double.valueOf(dosage)))/1000;
			}
			
			NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			
             if(tblTiprpAppInd_json.getSmplUnit().equals("g")){
            	 number = number/1000;
            	 if(precision.equals("10")){
     		        nFormat.setMaximumFractionDigits(-1);
     			    nFormat.setMinimumFractionDigits(-1);
     			}else if(precision.equals("1")){
     				nFormat.setMaximumFractionDigits(0);
     			    nFormat.setMinimumFractionDigits(0);
     			}else if(precision.equals("0.1")){
     			    nFormat.setMaximumFractionDigits(1);
     			    nFormat.setMinimumFractionDigits(1); 
     			}else if(precision.equals("0.01")){
     			    nFormat.setMaximumFractionDigits(2);
     			    nFormat.setMinimumFractionDigits(2); 
     			}else if(precision.equals("0.001")){
     			    nFormat.setMaximumFractionDigits(3);
     			    nFormat.setMinimumFractionDigits(3); 
     			}
            	 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}else if(tblTiprpAppInd_json.getSmplUnit().equals("mg")){
				if(precision.equals("10")){
			        nFormat.setMaximumFractionDigits(-1);
				    nFormat.setMinimumFractionDigits(-1);
				}else if(precision.equals("1")){
					nFormat.setMaximumFractionDigits(0);
				    nFormat.setMinimumFractionDigits(0);
				}else if(precision.equals("0.1")){
				    nFormat.setMaximumFractionDigits(1);
				    nFormat.setMinimumFractionDigits(1); 
				}else if(precision.equals("0.01")){
				    nFormat.setMaximumFractionDigits(2);
				    nFormat.setMinimumFractionDigits(2); 
				}else if(precision.equals("0.001")){
				    nFormat.setMaximumFractionDigits(3);
				    nFormat.setMinimumFractionDigits(3);    
				}
				 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}
			
		}
		//计算用药量
		if(tblTiprpAppInd_json.getAutomatic() == 0){
			
			double capsNum1 = 0;
			if(tblTiprpAppInd_json.getSmplUnit().equals("mg")){
				capsNum1 = number/(tblTiprpAppInd_json.getCapsSpec());
			}else if(tblTiprpAppInd_json.getSmplUnit().equals("g")){
				capsNum1 = (number*1000)/(tblTiprpAppInd_json.getCapsSpec());
			}
			int CapsNum =(int) Math.ceil(capsNum1);
			tblTiprpAppData1.setCapsNum(CapsNum);
			
			
			}else if(tblTiprpAppInd_json.getAutomatic() == 2){
				tblTiprpAppData1.setCapsNum(tblTiprpAppInd_json.getFixednumber());
			}
		    tblTiprpAppDatalist.add(tblTiprpAppData1);
		}
		ActionContext.getContext().getSession().put("tblTiprpAppInd",tblTiprpAppInd_json);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblTiprpAppDatalist);
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	
	/**
	 * 
	 */
	public void signCheck()  {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		//String msg = "";
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public void signCheck1()  {
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "";
		TblTiprpAppInd tblTiprpAppInd =  tblTiprpAppIndService.getByStudyNoAppSn(studyNoPara, AppInd);
		 if( tblTiprpAppInd != null ){
			 if(tblTiprpAppInd.getAppStatus() == 1){
			 map.put("success", true );
			 }else{
				 map.put("success", false);
			 }
		
		}else{
	     map.put("success", false);
	     map.put("error", msg);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	
	/** 签字(供试品胶囊 签字 10（esType））*/
	public void tiprpAppIndSign() {
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		try{
			TblTiprpAppInd_json tblTiprpAppInd_json = (TblTiprpAppInd_json) ActionContext.getContext().getSession().get("tblTiprpAppInd");
			es.setSigner(tempUser.getRealName());
			es.setEsType(Integer.parseInt(esType));
			if(esType.equals("10")){
				es.setEsTypeDesc("供试品胶囊信息完毕签字确认");
			}else if(esType.equals("11")){
				es.setEsTypeDesc("供试品胶囊信息撤销签字确认");
			}
			es.setDateTime(new Date());
			es.setEsId(tblESService.getKey("TblES"));
			
			
			if(esType.equals("10")){
			    esLink.setTableName("TblTiprpAppInd"+tblTiprpAppInd_json.getAppSn());
			    esLink.setDataId(studyNoPara);
			    esLink.setTblES(es);
			    esLink.setEsType(Integer.parseInt(esType));
			    esLink.setEsTypeDesc("供试品配制申请录入完毕签字确认");
			}else if(esType.equals("11")){
				esLink.setTableName("TblTiprpAppInd"+AppInd);
				esLink.setDataId(studyNoPara);
				esLink.setTblES(es);
				esLink.setEsType(Integer.parseInt(esType));
				esLink.setEsTypeDesc("供试品配制申请录入从撤销签字确认");
			}
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESService.save(es);
			tblESLinkService.save(esLink);
			if(esType.equals("10")){
				//将缓存中数据写入数据库中TblTiprpAppInd
				TblTiprpAppInd tblTiprpAppInd = new TblTiprpAppInd();
				List<TblTiprpAppData_json> tblTiprpAppData_json =   (List<TblTiprpAppData_json>) ActionContext.getContext().getSession().get("tblTiprpAppData");
				double number = 0;
		        for(TblTiprpAppData_json numbers:tblTiprpAppData_json){
		        	number = number+ Double.valueOf(numbers.getSmplWeight());
		        }
		        tblTiprpAppInd.setSmplNum(number);
		        tblTiprpAppInd.setStudyNo(tblTiprpAppInd_json.getStudyNo());
				tblTiprpAppInd.setAppSn(tblTiprpAppInd_json.getAppSn());
				tblTiprpAppInd.setSmplCode(tblTiprpAppInd_json.getSmplCode());
				tblTiprpAppInd.setWeighUnit(tblTiprpAppInd_json.getWeighUnit());
				tblTiprpAppInd.setSmplUnit(tblTiprpAppInd_json.getSmplUnit());
				tblTiprpAppInd.setCapsSpec(tblTiprpAppInd_json.getCapsSpec());
				tblTiprpAppInd.setAppStatus(1);
				tblTiprpAppInd.setInputDate(new Date());
				tblTiprpAppInd.setAppMan(tblTiprpAppInd_json.getAppMan());
				tblTiprpAppInd.setPrecision(tblTiprpAppInd_json.getPrecision());
				tblTiprpAppInd.setDevType(tblTiprpAppInd_json.getDevType());
				tblTiprpAppInd.setDevVal(tblTiprpAppInd_json.getDevVal());
				String date = tblTiprpAppInd_json.getAppTime();
		        Format f = new SimpleDateFormat("yyyy-MM-dd");
		        Date d = new Date();;
				try {
					d = (Date) f.parseObject(date);
				} catch (ParseException e) {
					json.setSuccess(false);
				}
				tblTiprpAppInd.setAppTime(d);
				tblTiprpAppInd.setId(tblTiprpAppIndService.getKey());
				List<TblTiprpAppData> Datalist = new ArrayList<TblTiprpAppData>();
				for(TblTiprpAppData_json tblTiprpAppData:tblTiprpAppData_json){
					TblTiprpAppData addoneAppData = new TblTiprpAppData();
					addoneAppData.setStudyNo(tblTiprpAppInd_json.getStudyNo());
					addoneAppData.setAppSn(tblTiprpAppInd_json.getAppSn());
					addoneAppData.setAniCode(tblTiprpAppData.getAniCode());
					addoneAppData.setAniWeight(tblTiprpAppData.getAniWeight());
					addoneAppData.setSmplWeight(tblTiprpAppData.getSmplWeight());
					addoneAppData.setCapsNum(tblTiprpAppData.getCapsNum());
					addoneAppData.setWeighUnit(tblTiprpAppInd_json.getWeighUnit());
					addoneAppData.setTblTiprpAppInd(tblTiprpAppInd);
					Datalist.add(addoneAppData);
				}
				
				addtime = (List<Date>) ActionContext.getContext().getSession().get("addtime");
				List<TblTiprpAppRecDt> recDtlist = new ArrayList<TblTiprpAppRecDt>();
				for(Date time:addtime){
					TblTiprpAppRecDt tblTiprpAppRecDt = new TblTiprpAppRecDt();
					tblTiprpAppRecDt.setStudyNo(tblTiprpAppInd_json.getStudyNo());
					tblTiprpAppRecDt.setAppSn(tblTiprpAppInd_json.getAppSn());
					tblTiprpAppRecDt.setRecDt(time);
					tblTiprpAppRecDt.setTblTiprpAppInd(tblTiprpAppInd);
					recDtlist.add(tblTiprpAppRecDt);
				}
				tblTiprpAppIndService.saveAllTilprpAll(tblTiprpAppInd,Datalist,recDtlist);
				//清楚缓存
				ActionContext ac = ActionContext.getContext();  
				Map<String,Object> session = ac.getSession();
				session.remove("tblTiprpAppInd");
				session.remove("tblTiprpAppData");
				session.remove("addtime");
			}else if(esType.equals("11")){
				      TblTiprpAppInd tblTiprpAppInd =  tblTiprpAppIndService.getByStudyNoAppSn(studyNoPara, AppInd);
			          tblTiprpAppInd.setAppStatus(-1);
				      tblTiprpAppIndService.update(tblTiprpAppInd);
			}
			 //日志录入
			 if(esType.equals("10")){
				 writeLog("签字","课题："+studyNoPara+"第"+tblTiprpAppInd_json.getAppSn()+"次 供试品配制申请，签字");
			 }else if(esType.equals("11")){
				 writeLog("撤销","课题："+studyNoPara+"第"+AppInd+"次 供试品配制撤销，签字");
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
	private void writeLog(String operatType,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("供试品(胶囊)");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	
	public void appDataonSave(){
		List<TblTiprpAppData_json> tblTiprpAppDatalist =   (List<TblTiprpAppData_json>) ActionContext.getContext().getSession().get("tblTiprpAppData");
		if(null != tblTiprpAppDatalist   && tblTiprpAppDatalist.size()>0){
			boolean flag = false;
			for(TblTiprpAppData_json obj:tblTiprpAppDatalist ){
				if(obj.getAniCode().equals(aniCode)){
					obj.setSmplWeight(smplWeight.replaceAll(",", ""));
					obj.setCapsNum(capsNum);
					flag =true;
				}
			}
			if(!flag){
				TblTiprpAppData_json tblTiprpAppData_json = new TblTiprpAppData_json();
				tblTiprpAppData_json.setAniCode(aniCode);
				tblTiprpAppData_json.setAniWeight(aniWeight);
				tblTiprpAppData_json.setCapsNum(capsNum);
				tblTiprpAppData_json.setWeighUnit(weightUnit);
				tblTiprpAppData_json.setSmplWeight(smplWeight.replaceAll(",", ""));
				tblTiprpAppDatalist.add(tblTiprpAppData_json);
				
			}
		}else{
			tblTiprpAppDatalist = new ArrayList<TblTiprpAppData_json>();
			TblTiprpAppData_json tblTiprpAppData_json = new TblTiprpAppData_json();
			tblTiprpAppData_json.setAniCode(aniCode);
			tblTiprpAppData_json.setAniWeight(aniWeight);
			tblTiprpAppData_json.setCapsNum(capsNum);
			tblTiprpAppData_json.setWeighUnit(weightUnit);
			tblTiprpAppData_json.setSmplWeight(smplWeight.replaceAll(",", ""));
			tblTiprpAppDatalist.add(tblTiprpAppData_json);
		}
		ActionContext.getContext().getSession().put("tblTiprpAppData",tblTiprpAppDatalist);
		Map<String,Object> map = new HashMap<String,Object>();
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	
	public boolean nullCheckRec(TblTiprpAppRecDt obj){
		boolean flag = true;
		if (stringCherck(obj.getRecMan())) {
			flag = false;
		}else if (obj.getRecDt() == null) {
			flag = false;
		}
		return flag;
	}
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	
	
	/**
	 * 选择剂量组
	 */
	public void SelectDoseGroup(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
	}
	

	/**勾选一行时*/
	@SuppressWarnings("unchecked")
	public void onCheckDoseGroup(){
		Map map = new HashMap();
		List<Integer> DoseGrouplist =  (List<Integer>) ActionContext.getContext().getSession().get("onCheckDoseGroup");
		for(String stri:group){
		
		if(DoseGrouplist==null ||DoseGrouplist.size() < 1 ){
			DoseGrouplist = new ArrayList<Integer>();
			map.put("success", true);
 			DoseGrouplist.add(Integer.valueOf(stri).intValue());
 		}else{
 			boolean flage = true;
 			for(Integer obj:DoseGrouplist){ 
 				if( obj == Integer.valueOf(stri).intValue()){
 					map.put("success", false);
 					flage = false;
 					return;
 				}else{
 				    map.put("success", true);
 				}
 			}
 			if( flage){
 				DoseGrouplist.add(Integer.valueOf(stri).intValue());
 			}
 		 }
		}
		ActionContext.getContext().getSession().put("onCheckDoseGroup",DoseGrouplist);
 		String json= JsonPluginsUtil.beanToJson(map);    
 		writeJson(json); 
	}
	/**
	 * 取消勾选分组
	 */
	public void onUncheckDoseGroup(){
		ActionContext ac = ActionContext.getContext();  
		Map<String,Object> session = ac.getSession();
		session.remove("onCheckDoseGroup");
		Map<String,Object> map = new  HashMap<String,Object>();
		String json = JsonPluginsUtil.beanToJson(map);
		
	    writeJson(json);
		
	}
	
	
	public void SelectDoseSettingList(){
		//根据课题编号获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取满足条件的计量设置
		tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
		List<TblDoseSetting_json> tblDoseSettingListjson = new ArrayList<TblDoseSetting_json>();
		 String dosageUnit= tblStudyPlan.getDosageUnit();
		TblDoseSetting_json tblDoseSetting_json;
		for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
			tblDoseSetting_json= new TblDoseSetting_json();
			tblDoseSetting_json.setDosage(tblDoseSetting.getDosage()); //剂量
			tblDoseSetting_json.setDosageDesc(tblDoseSetting.getDosageDesc());//剂量组说明
			tblDoseSetting_json.setDosageNum(tblDoseSetting.getDosageNum());//剂量组编号
			tblDoseSetting_json.setFemaleNum(tblDoseSetting.getFemaleNum());//雌性数量
			tblDoseSetting_json.setMaleNum(tblDoseSetting.getMaleNum());//雄性数量
			tblDoseSetting_json.setDosageUnit(dosageUnit);
			int sum = tblDoseSetting.getFemaleNum() +tblDoseSetting.getMaleNum();

			List<TblAnimal> animalList = tblAnimalService.getNoDieByStudyNo(tblStudyPlan);
			int index = 0;
			int animalCodeMode = tblStudyPlan.getAnimalCodeMode();
			int adimalGroup = 0;
			for(TblAnimal obj:animalList){
				if(obj.getAnimalCode()!= null){
				if(animalCodeMode == 1){
					adimalGroup=Integer.valueOf(obj.getAnimalCode().substring(0,1)).intValue();
					if(adimalGroup == tblDoseSetting.getDosageNum()){
						index++;
					}
				}else if(animalCodeMode == 2){
					adimalGroup=Integer.valueOf(obj.getAnimalCode().substring(1,2)).intValue();
					if((adimalGroup+1) == tblDoseSetting.getDosageNum()){
						index++;
					}
				}
				}
			}
			
			tblDoseSetting_json.setRemarks(index+"/"+sum);
			tblDoseSettingListjson.add(tblDoseSetting_json);    
			
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblDoseSettingListjson);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	
	public void toaddSaveAppIndGroup(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		List<TblTiprpAppData_json> tblTiprpAppDatalist = new ArrayList<TblTiprpAppData_json>();
		List<Integer> DoseGrouplist =  (List<Integer>) ActionContext.getContext().getSession().get("onCheckDoseGroup");//勾选的的组
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		if(tblWeghtDataList.size() > 0){
			int weightSn = tblWeghtDataList.get(0).getWeighSn();
			int isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
			int isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
			if(isAnimalWegihtES==1 && isAnimalWegihtFY==1 ){
				tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
			}else{
				tblWeghtDataList = tbLWeightDataService.getByWeighSn(studyNoPara);
			}
			
			TblTiprpAppInd_json tblTiprpAppInd_json = (TblTiprpAppInd_json) ActionContext.getContext().getSession().get("tblTiprpAppInd");
			
			for(TbLWeighData adimal:tblWeghtDataList){
			TblTiprpAppData_json tblTiprpAppData1 = new TblTiprpAppData_json();
			tblTiprpAppData1.setAniCode(adimal.getAniCode());//动物编号
			tblTiprpAppData1.setAniWeight(adimal.getWeight());//动物体重
			tblTiprpAppData1.setStudyNo(studyNoPara);//课题编号
			tblTiprpAppData1.setWeighUnit(tblTiprpAppInd_json.getWeighUnit());//重量单位
			String precision = tblTiprpAppInd_json.getPrecision();
			tblTiprpAppData1.setSmplUnit(tblTiprpAppInd_json.getSmplUnit());
			
			double number = 0;
			boolean  flage = false;
			
			TblAnimalDetailDissectPlan animalPlan= tblAnimalDetailDissectPlanService.getByStudyPlanAndAnimalCode(studyPlan, adimal.getAniCode());
			int adimalGroup =animalPlan.getGroupId();
			for(int obj:DoseGrouplist){
				   if(obj == adimalGroup){
					  flage = true;
				      break;
					}
			   }
			TblDoseSetting tblDoseSetting = tblDoseSettingService.getByStudyNoGroup(studyPlan, adimalGroup);
			String dosage= tblDoseSetting.getDosage();//剂量
			String dosageUnit= studyPlan.getDosageUnit();//剂量单位
			Double dose = 0.0;
			if(dosageUnit.equals("mg/kg")){
			    dose= Double.valueOf(dosage);
			}else if(dosageUnit.equals("g/kg")){
				dose= Double.valueOf(dosage)/1000;
			}
			if(tblTiprpAppInd_json.getWeighUnit().equals("kg")){
				number =(Double.valueOf(adimal.getWeight()))*(dose);
			}else if(tblTiprpAppInd_json.getWeighUnit().equals("g")){
				number =((Double.valueOf(adimal.getWeight()))*(dose))/1000;
			}
			NumberFormat nFormat=NumberFormat.getNumberInstance(); 
			if(tblTiprpAppInd_json.getSmplUnit().equals("g")){
	       	 number = number/1000;
	       	 if(precision.equals("10")){
			        nFormat.setMaximumFractionDigits(-1);
				    nFormat.setMinimumFractionDigits(-1);
				}else if(precision.equals("1")){
					nFormat.setMaximumFractionDigits(0);
				    nFormat.setMinimumFractionDigits(0);
				}else if(precision.equals("0.1")){
				    nFormat.setMaximumFractionDigits(1);
				    nFormat.setMinimumFractionDigits(1); 
				}else if(precision.equals("0.01")){
				    nFormat.setMaximumFractionDigits(2);
				    nFormat.setMinimumFractionDigits(2); 
				}else if(precision.equals("0.001")){
				    nFormat.setMaximumFractionDigits(3);
				    nFormat.setMinimumFractionDigits(3); 
				}
	       	 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}else if(tblTiprpAppInd_json.getSmplUnit().equals("mg")){
				if(precision.equals("10")){
			        nFormat.setMaximumFractionDigits(-1);
				    nFormat.setMinimumFractionDigits(-1);
				}else if(precision.equals("1")){
					nFormat.setMaximumFractionDigits(0);
				    nFormat.setMinimumFractionDigits(0);
				}else if(precision.equals("0.1")){
				    nFormat.setMaximumFractionDigits(1);
				    nFormat.setMinimumFractionDigits(1); 
				}else if(precision.equals("0.01")){
				    nFormat.setMaximumFractionDigits(2);
				    nFormat.setMinimumFractionDigits(2); 
				}else if(precision.equals("0.001")){
				    nFormat.setMaximumFractionDigits(3);
				    nFormat.setMinimumFractionDigits(3);    
				}
				 tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			}
			
			
			
			tblTiprpAppData1.setSmplWeight(nFormat.format(number));
			
			//计算用药量
			if(tblTiprpAppInd_json.getAutomatic() == 0){
				
				double capsNum1 = 0;
				if(tblTiprpAppInd_json.getSmplUnit().equals("mg")){
					capsNum1 = number/(tblTiprpAppInd_json.getCapsSpec());
				}else if(tblTiprpAppInd_json.getSmplUnit().equals("g")){
					capsNum1 = (number*1000)/(tblTiprpAppInd_json.getCapsSpec());
				}
				int CapsNum =(int) Math.ceil(capsNum1);
				tblTiprpAppData1.setCapsNum(CapsNum);
				
				
				}else if(tblTiprpAppInd_json.getAutomatic() == 2){
					tblTiprpAppData1.setCapsNum(tblTiprpAppInd_json.getFixednumber());
				}
			
			 if(flage){
				 //获得存活的动物
				 List<TblAnimal> animalList = tblAnimalService.getNoDieByStudyNo(studyPlan);
				 for(TblAnimal obj:animalList ){
					 if( obj.getAnimalCode()!= null && obj.getAnimalCode().equals(adimal.getAniCode() )){
					 tblTiprpAppDatalist.add(tblTiprpAppData1);
					 }
				 }
			 }
			}
			ActionContext.getContext().getSession().put("tblTiprpAppInd",tblTiprpAppInd_json);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tblTiprpAppDatalist);
		String json= JsonPluginsUtil.beanToJson(map);    
		writeJson(json);
		
	}
	

	public TblTiprpAppIndService getTblTiprpAppIndService() {
		return tblTiprpAppIndService;
	}

	public void setTblTiprpAppIndService(TblTiprpAppIndService tblTiprpAppIndService) {
		this.tblTiprpAppIndService = tblTiprpAppIndService;
	}

	public TblTiprpAppRecDtService getTblTiprpAppRecDtService() {
		return tblTiprpAppRecDtService;
	}

	public void setTblTiprpAppRecDtService(
			TblTiprpAppRecDtService tblTiprpAppRecDtService) {
		this.tblTiprpAppRecDtService = tblTiprpAppRecDtService;
	}

	public TblTiprpAppDataService getTblTiprpAppDataService() {
		return tblTiprpAppDataService;
	}

	public void setTblTiprpAppDataService(
			TblTiprpAppDataService tblTiprpAppDataService) {
		this.tblTiprpAppDataService = tblTiprpAppDataService;
	}

	public TblWeightIndService getTblWeightIndService() {
		return tblWeightIndService;
	}

	public void setTblWeightIndService(TblWeightIndService tblWeightIndService) {
		this.tblWeightIndService = tblWeightIndService;
	}

	public TbLWeightDataService getTbLWeightDataService() {
		return tbLWeightDataService;
	}

	public void setTbLWeightDataService(TbLWeightDataService tbLWeightDataService) {
		this.tbLWeightDataService = tbLWeightDataService;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	public TblAnimalService getTblAnimalService() {
		return tblAnimalService;
	}

	public void setTblAnimalService(TblAnimalService tblAnimalService) {
		this.tblAnimalService = tblAnimalService;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	

	public List<TbLWeighData> getTblWeghtDataList() {
		return tblWeghtDataList;
	}

	public void setTblWeghtDataList(List<TbLWeighData> tblWeghtDataList) {
		this.tblWeghtDataList = tblWeghtDataList;
	}

	public List<TblWeighInd> getTblWeghtList() {
		return tblWeghtList;
	}

	public void setTblWeghtList(List<TblWeighInd> tblWeghtList) {
		this.tblWeghtList = tblWeghtList;
	}

	public List<TblTiprpAppInd> getTblTiprpAppIndlist() {
		return tblTiprpAppIndlist;
	}

	public void setTblTiprpAppIndlist(List<TblTiprpAppInd> tblTiprpAppIndlist) {
		this.tblTiprpAppIndlist = tblTiprpAppIndlist;
	}

	public List<TblTiprpAppData> getTblTiprpAppDatalist() {
		return tblTiprpAppDatalist;
	}

	public void setTblTiprpAppDatalist(List<TblTiprpAppData> tblTiprpAppDatalist) {
		this.tblTiprpAppDatalist = tblTiprpAppDatalist;
	}

	public TblTiprpAppInd getTblTiprpAppInd() {
		return tblTiprpAppInd;
	}

	public void setTblTiprpAppInd(TblTiprpAppInd tblTiprpAppInd) {
		this.tblTiprpAppInd = tblTiprpAppInd;
	}

	public int getAppInd() {
		return AppInd;
	}

	public void setAppInd(int appInd) {
		AppInd = appInd;
	}

	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}

	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}

	public TblESService getTblESService() {
		return tblESService;
	}

	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}

	public TblWeighInd getTblWeighInd() {
		return tblWeighInd;
	}

	public void setTblWeighInd(TblWeighInd tblWeighInd) {
		this.tblWeighInd = tblWeighInd;
	}

	public TblTiprpAppRecDt getTblTiprpAppRecDt() {
		return tblTiprpAppRecDt;
	}

	public void setTblTiprpAppRecDt(TblTiprpAppRecDt tblTiprpAppRecDt) {
		this.tblTiprpAppRecDt = tblTiprpAppRecDt;
	}

	public List<TblTiprpAppRecDt> getTblTiprpAppRecDtlist() {
		return tblTiprpAppRecDtlist;
	}

	public void setTblTiprpAppRecDtlist(List<TblTiprpAppRecDt> tblTiprpAppRecDtlist) {
		this.tblTiprpAppRecDtlist = tblTiprpAppRecDtlist;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRecDt() {
		return RecDt;
	}

	public void setRecDt(String recDt) {
		RecDt = recDt;
	}

	public List<Date> getAddtime() {
		return addtime;
	}

	public void setAddtime(List<Date> addtime) {
		this.addtime = addtime;
	}

	public List<TblAnimal> getTblAnimalList() {
		return tblAnimalList;
	}

	public void setTblAnimalList(List<TblAnimal> tblAnimalList) {
		this.tblAnimalList = tblAnimalList;
	}

	public TblDoseSettingService getTblDoseSettingService() {
		return tblDoseSettingService;
	}

	public void setTblDoseSettingService(TblDoseSettingService tblDoseSettingService) {
		this.tblDoseSettingService = tblDoseSettingService;
	}

	public String getEsType() {
		return esType;
	}

	public void setEsType(String esType) {
		this.esType = esType;
	}

	public String getAniCode() {
		return aniCode;
	}

	public void setAniCode(String aniCode) {
		this.aniCode = aniCode;
	}

	public String getAniWeight() {
		return aniWeight;
	}

	public void setAniWeight(String aniWeight) {
		this.aniWeight = aniWeight;
	}

	public String getSmplWeight() {
		return smplWeight;
	}

	public void setSmplWeight(String smplWeight) {
		this.smplWeight = smplWeight;
	}

	public int getCapsNum() {
		return capsNum;
	}

	public void setCapsNum(int capsNum) {
		this.capsNum = capsNum;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getDatasmplUnit() {
		return DatasmplUnit;
	}

	public void setDatasmplUnit(String datasmplUnit) {
		DatasmplUnit = datasmplUnit;
	}

	public List<TblDoseSetting> getTblDoseSettingList() {
		return tblDoseSettingList;
	}

	public void setTblDoseSettingList(List<TblDoseSetting> tblDoseSettingList) {
		this.tblDoseSettingList = tblDoseSettingList;
	}

	

	public List<String> getGroup() {
		return group;
	}

	public void setGroup(List<String> group) {
		this.group = group;
	}

	public List<TblDoseSetting_json> getTblDoseSettingListjson() {
		return tblDoseSettingListjson;
	}

	public void setTblDoseSettingListjson(
			List<TblDoseSetting_json> tblDoseSettingListjson) {
		this.tblDoseSettingListjson = tblDoseSettingListjson;
	}

    

    

	
    
    

   
}
