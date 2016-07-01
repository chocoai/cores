package com.lanen.view.action.studyplan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.lanen.model.studyplan.TbLWeightData_json;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblWeighInd;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.TbLWeightDataService;
import com.lanen.service.studyplan.TblAnimalService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.service.studyplan.TblTiprpAppIndService;
import com.lanen.service.studyplan.TblWeightIndService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblWeightIndAction extends BaseAction<TblWeighInd> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int page;
	private int rows;
	
	// 动物体重Service
	@Resource
	private TblWeightIndService tblWeighIndService;
	@Resource
	private TblTiprpAppIndService tblTiprpAppIndService;
	
	@Resource
	private TbLWeightDataService tbLWeightDataService;
	
	// 试验计划Service
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	// 动物信息Service
	@Resource
	private TblAnimalService tblAnimalService;
	
	// 剂量设置Service
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	
	//签字类型
	private String esType;
	
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	/** 动物体重Service*/
	@Resource
	private TblWeightIndService tblWeightIndService;
	
	// 课题编号
	private String studyNoPara;
	
	// 列表显示内容
	private List<TblAnimal> tblAnimalList;
	
	private List<TbLWeighData> tblWeghtDataList;
	
	private List<TblWeighInd> tblWeghtList;
	
	
	//当前序号
	private String currentSerialNum;
	private String newWeightInd;
	
	private TblWeighInd tblWeighInd;
	
	private int AppInd;
	
	//复核人姓名
	private String FHsingName;
	
    public TblWeightIndService getTblWeightIndService() {
		return tblWeightIndService;
	}

	public void setTblWeightIndService(TblWeightIndService tblWeightIndService) {
		this.tblWeightIndService = tblWeightIndService;
	}

	//页面上传的文件(动物Id号   动物编号共用)
	private File excelCodeFile;//File对象，目的是获取页面上传的文件
	//private String fileName;    
	private String contentType;
	
	private String id;
	
	private int sign;//标志位
	
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
	
	public void selectWeighIndList(){
		List<TblWeighInd> list  =  tblWeightIndService.gettblWeighInd(studyNoPara);
		 if(null!=list && list.size()>0){
		 List<TblWeighInd> tblWeighIndlist = new ArrayList<TblWeighInd>();
		 for(TblWeighInd WeighInd:list){
		    TblWeighInd tblWeighInd = new TblWeighInd() ;
		    tblWeighInd.setStudyNo(WeighInd.getStudyNo());
		    tblWeighInd.setWeighSn(WeighInd.getWeighSn());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(WeighInd.getWeighDate());
			tblWeighInd.setWeighDate1(date);
			tblWeighInd.setVerStatus(WeighInd.getVerStatus());
			//获取tblWeighInd主键作为签名链接的数据ID
			TblESLink tblESLink1= tblESLinkService.getByEntityNameAndDataIdType("TbLWeightData"+WeighInd.getWeighSn(), studyNoPara,8);
            if( tblESLink1 != null ){
            	String name = tblESLink1.getTblES().getSigner();//签字人
            	Date date2 = tblESLink1.getTblES().getDateTime();//签字时间
   			    String showdata = df.format(date2);
            	tblWeighInd.setAudit(name+"  "+showdata);
            }			
			TblESLink tblESLink2= tblESLinkService.getByEntityNameAndDataIdType("TbLWeightData"+WeighInd.getWeighSn(), studyNoPara,9);
			if( tblESLink2 != null){
				String name1 = tblESLink2.getTblES().getSigner();//签字人
            	Date date3 = tblESLink2.getTblES().getDateTime();//签字时间
   			    String showdata1 = df.format(date3);
				tblWeighInd.setReview(name1+"  "+showdata1);
			}
			
			tblWeighIndlist.add(tblWeighInd);
		 }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",tblWeighIndlist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		}else{
		writeJson("");
		}
		 
	 }
	
	public void selectTbLWeighDataList(){
		if(AppInd == 0){
			tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
			int isAnimalWegihtES =0;
			int isAnimalWegihtFY =0;
			if(!tblWeghtDataList.isEmpty()){
			int weightSn = tblWeghtDataList.get(0).getWeighSn();
			isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
			isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
			}
			if(isAnimalWegihtFY==1){
			 tblWeghtList =  tblWeightIndService.getByStudyNo(studyNoPara);
			 AppInd = tblWeghtList.get(0).getWeighSn();
			 }else{
				 tblWeghtList =  tblWeightIndService.getByWeighSn(studyNoPara);
				 if(null != tblWeghtList && !tblWeghtList.equals("") && tblWeghtList.size() > 0){
					 AppInd = tblWeghtList.get(0).getWeighSn();
				 }
			
			 }
		 }
		 List<TbLWeighData> Datalist = (List<TbLWeighData>) tblWeightIndService.getTbLWeighData(studyNoPara, AppInd);
		 List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(studyNoPara, AppInd);
		 if( Datalist != null && Datalist.size() > 0){
		 List<TbLWeighData> tbLWeighDatalist = new ArrayList<TbLWeighData>();
		 for(TbLWeighData WeighData:Datalist){
			 TbLWeighData tbLWeighData = new TbLWeighData() ;
			 tbLWeighData.setAniCode(WeighData.getAniCode());
			 tbLWeighData.setWeight(WeighData.getWeight());
			 tbLWeighData.setUnit(Indlist.get(0).getWeightUnit());
			 tbLWeighDatalist.add(tbLWeighData);
		 }
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", tbLWeighDatalist);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		}else{
		writeJson("");
		}
		 
	 }
	
	public void toeditTblWeighInd(){
		List<TblWeighInd> list = tblWeightIndService.getByStudyNo(studyNoPara);
		TblWeighInd tblWeighInd = list.get(0);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("StudyNo",tblWeighInd.getStudyNo());
		map.put("WeighSn",tblWeighInd.getWeighSn());
		map.put("WeightUnit1",tblWeighInd.getWeightUnit());
		Date data = tblWeighInd.getWeighDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String showdate = df.format(data);
		map.put("NowDate", showdate);
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void toeditTblWeighIndinfo(){
		List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(tblWeighInd.getStudyNo(), tblWeighInd.getWeighSn());
		TblWeighInd tblWeighInd1 = Indlist.get(0);
		tblWeighInd1.setStudyNo(tblWeighInd.getStudyNo());
		tblWeighInd1.setWeighSn(tblWeighInd.getWeighSn());
		tblWeighInd1.setWeighDate(tblWeighInd.getWeighDate());
		tblWeighInd1.setWeightUnit(tblWeighInd.getWeightUnit());
		tblWeightIndService.update(tblWeighInd1);
		Map<String,Object> map = new HashMap<String,Object>();
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void tonewanimalweight(){
		Map<String,Object> map = new HashMap<String,Object>();
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TblAnimal> animalList = tblAnimalService.getNoDieByStudyNo(tblStudyPlan);
		if(animalList.size()<1 || animalList== null){
			map.put("sign",1);
			}else{
		List<TblWeighInd> list = (List<TblWeighInd>) tblWeightIndService.getByStudyNo(studyNoPara);
		if(list.isEmpty()){
			map.put("StudyNo",studyNoPara);
			map.put("WeighSn",1);
			map.put("WeightUnit","kg");
			map.put("NowDate", DateUtil.getNow("yyyy-MM-dd"));
			map.put("success",true);
		}else{
			tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
			int isAnimalWegihtFY =0;
			if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
			int weightSn = tblWeghtDataList.get(0).getWeighSn();
			int isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
			isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
			}
			if( isAnimalWegihtFY == 1){
				map.put("StudyNo",list.get(0).getStudyNo());
				map.put("WeighSn",list.get(0).getWeighSn()+1);
				map.put("WeightUnit",list.get(0).getWeightUnit());
				map.put("NowDate", DateUtil.getNow("yyyy-MM-dd"));
				map.put("success",true);
			}else{
				map.put("success",false);
			}
			
		}
		}
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void toSavetblWeighInd(){
		Map<String,Object> map = new HashMap<String,Object>();
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(tblWeighInd.getStudyNo());
		int isAnimalWegihtES =1;
		int isAnimalWegihtFY =1;
		if(!tblWeghtDataList.isEmpty()){
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,tblWeighInd.getStudyNo(),8);
		isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,tblWeighInd.getStudyNo(),9);
		}
		
		if( isAnimalWegihtFY == 0){

		}else{
			if(nullCheckRec(tblWeighInd)){
			//List<TblWeighInd> tblWeighInd_List = new ArrayList<TblWeighInd>();
			TblWeighInd tblWeighInd1 = new TblWeighInd();
			tblWeighInd1.setStudyNo(tblWeighInd.getStudyNo());
			tblWeighInd1.setWeighSn(tblWeighInd.getWeighSn());
			tblWeighInd1.setWeighDate(tblWeighInd.getWeighDate());
			tblWeighInd1.setWeightUnit(tblWeighInd.getWeightUnit());
			tblWeighInd1.setId(tblWeightIndService.getKey());
			//tblWeighInd_List.add(tblWeighInd1);
			
			TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(tblWeighInd.getStudyNo());
			//List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(tblWeighInd.getStudyNo(), tblWeighInd.getWeighSn());
			List<TblAnimal> animalList = tblAnimalService.getNoDieByStudyNo(tblStudyPlan);
			List<TbLWeighData> tblWeighData_List = new ArrayList<TbLWeighData>();
			boolean flage = false;
			for(TblAnimal tblAnimal:animalList){
				if(null != tblAnimal.getAnimalCode()  && !tblAnimal.getAnimalCode().equals("")){
					flage = true;
					TbLWeighData tbLWeightData = new TbLWeighData();
				    tbLWeightData.setTblWeighInd(tblWeighInd1);
				    tbLWeightData.setCollTime(new Date());
				    if( tblWeighInd.getWeighSn() == 1){
				     tbLWeightData.setWeight(tblAnimal.getWeight());
				    }
				    tbLWeightData.setWeighSn(tblWeighInd.getWeighSn());
				    tbLWeightData.setAniCode(tblAnimal.getAnimalCode());
				    tbLWeightData.setStudyNo(tblAnimal.getTblStudyPlan().getStudyNo());
				    tbLWeightData.setUnit(tblWeighInd.getWeightUnit());
				    tblWeighData_List.add(tbLWeightData);
				}
			}
			 //tblWeightIndService.saveAllAnimalWeighInds(tblWeighInd_List);
			 if(flage){
			  tblWeightIndService.save(tblWeighInd1);
			  tbLWeightDataService.saveAllAnimalWeighData(tblWeighData_List);	
			  map.put("success", true);
			  }else{
				  map.put("success", false);
				  map.put("AniCode", true);
			  }
			}else{
			map.put("success", false);
			}
		}
		
		map.put("StudyNo",tblWeighInd.getStudyNo());
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	
	public boolean nullCheckRec(TblWeighInd obj){
		boolean flag = true;
		if (obj.getWeighDate() == null) {
			flag = false;
		}
		return flag;
	}
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	 /**序号下拉框 （准备数据）*/
	public void initSerialNum(){
		//获取动物信息列表
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
			//序号列表
			List<Map<String,String>> serialNumList = new ArrayList<Map<String,String>>();
			Map<String,String> map=null;
			for(int i=0;i<tblWeghtDataList.size();i++){
				map = new HashMap<String,String>();
				map.put("id", tblWeghtDataList.get(i).getId());
				String weight = tblWeghtDataList.get(i).getAniCode();
				map.put("text",weight);
				serialNumList.add(map);
			}
			//当前序号
			String currentSerialNum=tblWeghtDataList.get(0).getAniCode();
			for(int i=tblWeghtDataList.size()-1;i>=0;i--){
               
				if(null!=tblWeghtDataList.get(i).getWeight() &&!tblWeghtDataList.get(i).getWeight().equals("")){
					if(i == tblWeghtDataList.size()-1 ){
						currentSerialNum=tblWeghtDataList.get(tblWeghtDataList.size()-1).getAniCode();
						break;
					}else{
						currentSerialNum=tblWeghtDataList.get(i+1).getAniCode();
						break;
					}	
				}
				
				
			}
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("serialNumList", serialNumList);
			jsonMap.put("currentSerialNum", currentSerialNum+"");
			String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
			writeJson(jsonStr);
		}
	}
	
	public void loadList(){
    	TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TbLWeighData> list= tbLWeightDataService.getByStudyNoWithPageRows(studyNoPara,page,rows);
		List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(studyNoPara, list.get(0).getWeighSn());
		String unit = Indlist.get(0).getWeightUnit();
		Date shouwDate = Indlist.get(0).getWeighDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(shouwDate);
		if(null!=list && list.size()>0){
			Long total =tblAnimalService.getTotalByStudyPlan(tblStudyPlan);
			List<TbLWeightData_json> tbLWeightData_jsonList = new ArrayList<TbLWeightData_json>();
			TbLWeightData_json tbLWeightData;
			for(TbLWeighData tblAnimal:list){
				tbLWeightData= new TbLWeightData_json();
				tbLWeightData.setId(tblAnimal.getId());
				tbLWeightData.setStudyNo(studyNoPara);
				tbLWeightData.setAniCode(tblAnimal.getAniCode());
				tbLWeightData.setWeight(tblAnimal.getWeight());
				tbLWeightData.setUnit(unit);
				
				tbLWeightData.setShowtime(date);
				tbLWeightData_jsonList.add(tbLWeightData);						
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows", tbLWeightData_jsonList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			writeJson("");
		}
	}
	
	
	/**
	 * 显示录入体重次数和时间
	 */
	public void toNewTiprpAppInd(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().put("user", user);
		//动物列表
		//TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//List<TblAnimal> animalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		tblWeghtList =  tblWeightIndService.getByStudyNo(studyNoPara);
		Map<String,Object> map = new HashMap<String,Object>();
		if( tblWeghtList.isEmpty() ){
			map.put("empty", 1);
	    }else{
	    	
	    	int isAnimalWegihtES = 0;
			int isAnimalWegihtFY = 0;
			tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
			if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
				int weightSn = tblWeghtDataList.get(0).getWeighSn();
				isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
				isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
				ActionContext.getContext().put("isAnimalWegihtES", isAnimalWegihtES);
				ActionContext.getContext().put("isAnimalWegihtFY", isAnimalWegihtFY);
			}else{
				ActionContext.getContext().put("isAnimalWegihtES", isAnimalWegihtES);
				ActionContext.getContext().put("isAnimalWegihtFY", isAnimalWegihtFY);
			}
			if(isAnimalWegihtES==1&&isAnimalWegihtFY==1){
				 Date weightDate = tblWeghtDataList.get(0).getCollTime();
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				 String date = df.format(weightDate);
				 int weightSn = tblWeghtDataList.get(0).getWeighSn();
				   map.put("weightSn", weightSn);
				 if(null != weightDate ){
				   map.put("weightDate", date);
				  }
			 }else{
				 int index=0;
				     //第一次未审核
				     if (tblWeghtDataList.get(0).getWeighSn() == 1) {
				    	 Date weightDate = tblWeghtDataList.get(0).getCollTime();
						 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						 String date = df.format(weightDate);
						 int weightSn = tblWeghtDataList.get(0).getWeighSn();
						   map.put("weightSn", weightSn);
						 if(null != weightDate ){
						   map.put("weightDate", date);
						  }
						 index = 1;
						 map.put("sign", 1 );
					 }else{
					  tblWeghtDataList = tbLWeightDataService.getByWeighSn(studyNoPara);
					  Date weightDate = tblWeghtDataList.get(0).getCollTime();
					  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					  String date = df.format(weightDate);
					  int weightSn =tblWeghtDataList.get(0).getWeighSn();
					   map.put("weightSn", weightSn);
					  if(null != weightDate ){
					   map.put("weightDate", date);
				       }
					 index= weightSn+1;
					}
				     map.put("msg", "(注:第  "+index+" 次体重未复核)");
			 }
	    }
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	
	
	/**
	 * 跳转录入动物体重
	 * @return
	 */
    public String toadd(){
    	//登录信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().put("user", user);
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		
		int isAnimalWegihtES =0;
		int isAnimalWegihtFY =0;
		if(!tblWeghtDataList.isEmpty()){
		ActionContext.getContext().put("listSize", tblWeghtDataList.size());
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
		isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
		ActionContext.getContext().put("isAnimalWegihtES", isAnimalWegihtES);
 		ActionContext.getContext().put("isAnimalWegihtFY", isAnimalWegihtFY);}
 		return "toadd";
//		//获取动物信息列表
//		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
//		if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
//			ActionContext.getContext().put("listSize", tblWeghtDataList.size());
//			int weightSn = tblWeghtDataList.get(0).getWeighSn();
//			int isAnimalWegihtES = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,8);
//			int isAnimalWegihtFY = tblESLinkService.isESLink("TbLWeightData"+weightSn,studyNoPara,9);
//			if( (isAnimalWegihtFY == 1 && isAnimalWegihtES == 1) || weightSn==1 ){
//				List<TbLWeighData> tblWeighData_List = new ArrayList<TbLWeighData>();
//				for(TbLWeighData tblAnimal:tblWeghtDataList){
//					TbLWeighData tbLWeightData = new TbLWeighData();
//					tbLWeightData.setWeight("");
//					tbLWeightData.setStudyNo(tblAnimal.getStudyNo());
//					tbLWeightData.setTblWeighInd(tblAnimal.getTblWeighInd());
//					tbLWeightData.setWeighSn(tblAnimal.getWeighSn()+1);
//					tbLWeightData.setAniCode(tblAnimal.getAniCode());
//					tblWeighData_List.add(tbLWeightData);
//				}
//				tbLWeightDataService.saveAllAnimalWeighData(tblWeighData_List);	
//				ActionContext.getContext().put("isAnimalWegihtES", 0);
//				ActionContext.getContext().put("isAnimalWegihtFY", 0);
//		 }else{
//			ActionContext.getContext().put("isAnimalWegihtES", isAnimalWegihtES);
//			ActionContext.getContext().put("isAnimalWegihtFY", isAnimalWegihtFY);
//		 }
//		}

	}
	/**动物体重保存（单个录入的）*/
	public void editOneAnimalIdSave(){
		 String regex = "^(?!0(\\d|\\.0+$|$))\\d+(\\.\\d{1,9})?$";
		 Map<String,Object> map = new HashMap<String,Object>();
		 if(newWeightInd.matches(regex)&&  newWeightInd.length()<=10){
			//获取动物信息列表
				tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
				int index = 1;
				int index1 = 2; 
				for(int i=0;i<tblWeghtDataList.size();i++){
					if(tblWeghtDataList.get(i).getAniCode().equals(currentSerialNum) ){
		        	   index = i;
		        	   
		        	   if( i ==tblWeghtDataList.size()-1){
		    			   index1 = i;
		    		   }else{
		    			   index1 = i+1;
		    		   }
		           }
				   	
				}
			
				if(currentSerialNum != null ){
					if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
						TbLWeighData tbweightData=tbLWeightDataService.getByIdWeight(currentSerialNum,studyNoPara);		
						tbweightData.setWeight(newWeightInd);
						tbweightData.setCollTime(new Date());
						//输入一个动物体重
						tbLWeightDataService.update(tbweightData);
						//当前序号
						String currentSerialNum=tblWeghtDataList.get(0).getAniCode();
						for(int i=tblWeghtDataList.size()-1;i>=0;i--){
							if(null!=tblWeghtDataList.get(i).getWeight() &&!tblWeghtDataList.get(i).getWeight().equals("")){
								if(i == tblWeghtDataList.size()-1 ){
									currentSerialNum=tblWeghtDataList.get(tblWeghtDataList.size()-1).getAniCode();
									break;
								}else{
									currentSerialNum=tblWeghtDataList.get(i+1).getAniCode();
									break;
								}
							}
						}
						Map<String,Object> animalMap = new HashMap<String,Object>();
						animalMap.put("id", tbweightData.getId());
						animalMap.put("studyNo", tbweightData.getStudyNo());
						animalMap.put("aniCode", tbweightData.getAniCode());
						animalMap.put("weight", tbweightData.getWeight());
						map.put("success", true);
						map.put("currentSerialNum", currentSerialNum);
						map.put("animal", animalMap);
						map.put("index", index);
						map.put("index1", index1);
						String jsonStr = JsonPluginsUtil.beanToJson(map);
						writeJson(jsonStr);
					}
				}else{
					map.put("success", false);
					map.put("msg", "动物体重录入失败");
					String jsonStr = JsonPluginsUtil.beanToJson(map);
					writeJson(jsonStr);
				}
		 }else{
			    map.put("success", false);
				map.put("msg", "请输入正确的体重格式");
				String jsonStr = JsonPluginsUtil.beanToJson(map);
				writeJson(jsonStr);
		 }
	}
	
	private Object match(String regex, String newWeightInd2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**动物体重批量导入
	  */
	public void importExcel() {  
		//TODO
		Json json = new Json();//
		Map<String,Object> map = new HashMap<String,Object>();
		//String animalId =null;//动物Id号
		String animalCode =null;//动物编号
		String animalWeight =null;//动物体重animalWeight
		
		//List<String> animalIdList = new ArrayList<String>();
		List<String> animalCodeList = new ArrayList<String>();
		List<String> animalWightList = new ArrayList<String>();
		Map<String,String> codeIdMap =new HashMap<String,String>();//编号：体重
		
		//2007版读取方法
		Workbook workbook =null;
		//int k =0;
		//int flag =0; //指示指针访问的位置
		List<String> typeList = new ArrayList<String>();
		typeList.add("application/kset");
		typeList.add("application/excel");
		typeList.add("application/vnd.ms-excel");
		typeList.add("application/msexcel");
		typeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		if(typeList.contains(contentType)){//判断下文件类型
			if(excelCodeFile!=null){
				String path=excelCodeFile.getAbsolutePath();//获取文件的路径
				try {
					workbook = new XSSFWorkbook(path);
					if(workbook.getNumberOfSheets()>0){//sheet
						if(null!=workbook.getSheetAt(0)){
							//获取第一个sheet
							XSSFSheet aSheet = (XSSFSheet) workbook.getSheetAt(0);
							if(aSheet.getLastRowNum()>1){
								for(int rowNumOfSheet =0;rowNumOfSheet <=aSheet.getLastRowNum();rowNumOfSheet++){
									//进入aSheet的行(row)的循环
									if(null!= aSheet.getRow(rowNumOfSheet)){
										XSSFRow aRow =aSheet.getRow(rowNumOfSheet);
										if(rowNumOfSheet ==0){//表头
											int lastCellNum =aRow.getLastCellNum();
											if(lastCellNum<3){
												XSSFCell aCell = aRow.getCell(0);
												XSSFCell bCell = aRow.getCell(1);
												if(null!=aCell){
													aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物编号")){
														json.setMsg("请检查表头");
														break;
													}
												}else{
													json.setMsg("请检查表头");
													break;
												}
												if(null!=bCell){
													aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物体重")){
														json.setMsg("请检查表头");
														break;
													}
												}else{
													json.setMsg("请检查表头");
													break;
												}
											}else{
												json.setMsg("请检查表头");
												break;
											}
										}else{
											//数据行
											XSSFCell aCell = aRow.getCell(0);
											XSSFCell bCell = aRow.getCell(1);
											if(null!=aCell){//id号为空，则code就不检查了
												aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
												animalCode=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												if(animalCode.contains(".")){
													int index = animalCode.indexOf(".");
													animalCode= animalCode.substring(0, index);
												}
												if(null!=animalCode && !animalCode.equals("")){
													//添加到动物编号列表
													if(!animalCodeList.contains(animalCode)){
														animalCodeList.add(animalCode);
													} else{
														json.setMsg("动物编号不能重复");
														break;
													}
													
												}else{
													json.setMsg("动物编号不能为空");
													break;
												}
												if(null!=bCell){
													bCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													animalWeight=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													BigDecimal db = new BigDecimal(animalWeight);
													animalWeight = db.toPlainString();
													String regex = "^(?!0(\\d|\\.0+$|$))\\d+(\\.\\d{1,9})?$";
													 if(animalWeight.matches(regex) ){
														 if(null!=animalWeight && !animalWeight.equals("")){
																//添加到动物编号列表
																animalWightList.add(animalWeight);
																//添加到编号Id号map中
																codeIdMap.put(animalCode,animalWeight);
															}
															animalCode="";
															animalWeight="";
													 }else{
														 json.setMsg("请检查录入数据:动物 编号 ："+animalCode+" 的体重为 ："+animalWeight);
														 break;
													 }
//													if(animalWeight.contains(".")){
//														int index = animalWeight.indexOf(".");
//														animalWeight= animalWeight.substring(0, index);
//													}
													
												}else{
													animalCode="";
												}
											}else{
												json.setMsg("动物编号不能为空");
												break;
											}
										}
									}else{//end 当前行不为空
										if(rowNumOfSheet ==0){
											json.setMsg("请检查表头");
											break;
										}
									}
								}//end aSheet的行的循环
							}else{
								json.setMsg("excel数据为空");
							}
						}else{//end   获取第一个sheet是否为空
							json.setMsg("excel为空");
						}
					}else{ //end sheets  >0
						json.setMsg("excel为空");
					}
				} catch (Exception e) {
					// TODO 
					//下面使用的是2003（workbook的赋值不同，其他与2007基本相同）
					InputStream is = null;
					try{
						is=	new FileInputStream(path);
						workbook = new HSSFWorkbook(is);
						if(workbook.getNumberOfSheets()>0){//sheet
							if(null!=workbook.getSheetAt(0)){
								//获取第一个sheet
								HSSFSheet aSheet = (HSSFSheet) workbook.getSheetAt(0);
								if(aSheet.getLastRowNum()>1){
									for(int rowNumOfSheet =0;rowNumOfSheet <=aSheet.getLastRowNum();rowNumOfSheet++){
										//进入aSheet的行(row)的循环
										if(null!= aSheet.getRow(rowNumOfSheet)){
											HSSFRow aRow =aSheet.getRow(rowNumOfSheet);
											if(rowNumOfSheet ==0){//表头
												int lastCellNum =aRow.getLastCellNum();
												if(lastCellNum<3){
													HSSFCell aCell = aRow.getCell(0);
													HSSFCell bCell = aRow.getCell(1);
													if(null!=aCell){
														aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物编号")){
															json.setMsg("请检查表头");
															break;
														}
													}else{
														json.setMsg("请检查表头");
														break;
													}
													if(null!=bCell){
														aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物体重")){
															json.setMsg("请检查表头");
															break;
														}
													}else{
														json.setMsg("请检查表头");
														break;
													}
												}else{
													json.setMsg("请检查表头");
													break;
												}
											}else{
												//数据行
												HSSFCell aCell = aRow.getCell(0);
												HSSFCell bCell = aRow.getCell(1);
												if(null!=aCell){//id号为空，则code就不检查了
													aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
													animalCode=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(animalCode.contains(".")){
														int index = animalCode.indexOf(".");
														animalCode= animalCode.substring(0, index);
													}
													if(null!=animalCode && !animalCode.equals("")){
														//添加到动物Id号列表
														if(!animalCodeList.contains(animalCode)){
															animalCodeList.add(animalCode);
														}else{
															json.setMsg("动物编号不能重复");
															break;
														}
													}else{
														json.setMsg("动物编号不能为空");
														break;
													}
													if(null!=bCell){
														bCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														animalWeight=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
														BigDecimal db = new BigDecimal(animalWeight);
														animalWeight = db.toPlainString();
														String regex = "^(?!0(\\d|\\.0+$|$))\\d+(\\.\\d{1,9})?$";
														 if(animalWeight.matches(regex) ){
															 if(null!=animalWeight && !animalWeight.equals("")){
																	//添加到动物编号列表
																	animalWightList.add(animalWeight);
																	//添加到编号Id号map中
																	codeIdMap.put(animalCode,animalWeight);
																}
																animalCode="";
																animalWeight="";
														 }else{
															 json.setMsg("请检查录入数据:动物"+animalCode+"的体重为"+animalWeight);
															 break;
														 }
													}else{
														animalCode="";
													}
												}else{
													json.setMsg("动物编号不能为空");
													break;
												}
											}
										}else{//end 当前行不为空
											if(rowNumOfSheet ==0){
												json.setMsg("请检查表头");
												break;
											}
										}
									}//end aSheet的行的循环
								}else{
									json.setMsg("excel数据为空");
								}
							}else{//end   获取第一个sheet是否为空
								json.setMsg("excel为空");
							}
						}else{ //end sheets  >0
							json.setMsg("excel为空");
						}
						
					}catch(Exception ex){
						json.setMsg("文件读取失败");
					}finally{
						if(is!=null)
							try {
								is.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					}
				}
			}else{
				json.setMsg("文件读取失败");
			}
			String  msg = json.getMsg();
			if(null==msg || "".equals(msg)){//消息为空，表示读取顺利，
				// 验证编号的完整性，Id号有没有错的，性别是否匹配
				TblStudyPlan tblStudyPlan = new TblStudyPlan();
				tblStudyPlan.setStudyNo(studyNoPara);
				//1.验证编号的完整性（不多不少，完全相同）
				//动物详细解剖计划表 
				tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
				if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
					boolean codeBoolean = true;
					if(codeBoolean){//动物编号相同
						//2.检查是否有不正确的Id号
						if(null!=tblWeghtDataList && tblWeghtDataList.size()>0){
							//待编号      编号List   
							List<String> detailIdList = new ArrayList<String>();
							for(TbLWeighData obj:tblWeghtDataList){
								detailIdList.add(obj.getAniCode());
							}
							//判断是否有     非该list表中的编号
							boolean idBoolean =true;
							for(String str :animalCodeList){
								if(!detailIdList.contains(str)){
									json.setMsg("动物编号'"+str+"'不存在");
									idBoolean=false;
									break;
								}
							}
							if(idBoolean){//通过
									//进行编号
								//List<TbLWeighData> list= new ArrayList<TbLWeighData>();
								//当前序号
								String currentSerialNum=tblWeghtDataList.get(0).getAniCode();
								List<Map<String,Object>> animalMapList = new ArrayList<Map<String,Object>>();
								Map<String,Object> animalMap = null;
								int index = animalCodeList.size();
									for(String code :animalCodeList){
										TbLWeighData tblAnimal = new TbLWeighData();
										tblAnimal= tbLWeightDataService.getByStudyPlanAndAnimalId(studyNoPara,code);
										tblAnimal.setWeight(codeIdMap.get(code));
										tblAnimal.setAniCode(code);
										tblAnimal.setCollTime(new Date());
										tbLWeightDataService.update(tblAnimal);
										
										animalMap = new HashMap<String,Object>();
										animalMap.put("id", tblAnimal.getId());
										animalMap.put("studyNo", tblAnimal.getTblWeighInd().getStudyNo());
										animalMap.put("aniCode", code);
										animalMap.put("weight", codeIdMap.get(code));
										animalMap.put("index", index);
										animalMapList.add(animalMap);
									}
									json.setSuccess(true);
									map.put("animalMapList", animalMapList);
									map.put("success", true);
									map.put("msg", "动物体重批量导入成功");
									map.put("currentSerialNum", currentSerialNum);
							}
						}else{
							json.setMsg("参数错误，刷新页面后重试");
						}
						
					}else{
						json.setMsg("动物编号不匹配或不完整");
					}
				}else{
					json.setMsg("参数错误，刷新页面后重试");
				}
				
			}
		}else{//文件类型不对
			json.setMsg("请导入excel类型文件");
		}
		if(json.isSuccess()){
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}else{
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}
	}
    
	/**
	 * 动物体重签字前验证
	 */
	public void signCheck() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		String msg = "";
		//获取动物信息列表
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		if(null!=tblWeghtDataList&&tblWeghtDataList.size()>0){
			for(int i=0;i<tblWeghtDataList.size();i++){
				if(null!=tblWeghtDataList.get(i).getWeight() && !"".equals(tblWeghtDataList.get(i).getWeight())){
					
				}else{
					map.put("animalIdError", "动物体重未录入完毕");
					break;
				}
			}
		}else{
			msg="error";
			map.put("animalIdError", msg);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	
	/** 签字（动物Id 签字  8（esType））*/
	public void animalWeightSign() {
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		
		es.setEsType(Integer.parseInt(esType));
		
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		
		tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
		int weightSn = tblWeghtDataList.get(0).getWeighSn();
		esLink.setTableName("TbLWeightData"+weightSn);
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		Json json = new Json();
		try{
			 if(esType.equals("8")){
					esLink.setEsTypeDesc("动物信息,动物体重录入完毕签字确认");
			 }else if(esType.equals("9")){
					esLink.setEsTypeDesc("动物信息,动物体重复核完毕签字确认");
			 }
			 if(esType.equals("8")){
				es.setSigner(tempUser.getRealName());
				es.setEsTypeDesc("动物信息表动物体重录入完毕签字确认");
				tblESService.save(es);
				tblESLinkService.save(esLink);
			 }else if(esType.equals("9")){
				es.setSigner(FHsingName);
				es.setEsTypeDesc("动物信息表动物体重复核完毕签字确认");
				tblWeghtDataList = tbLWeightDataService.getByStudyNo(studyNoPara);
				List<TblWeighInd>  Indlist = (List<TblWeighInd>) tblWeightIndService.getnewtblTblWeighIndInd(studyNoPara, tblWeghtDataList.get(0).getWeighSn());
			    tblWeighInd = Indlist.get(0);
			    tblWeighInd.setVerStatus(1);
			    tblESService.save(es);
				tblESLinkService.save(esLink);
			    tblWeightIndService.update(tblWeighInd);
			 }
			 json.setSuccess(true);
			 json.setMsg("签字成功");
			 //日志录入
			 if(esType.equals("8")){
				writeLog("签字","课题："+studyNoPara+" 动物体重录入完毕，签字");
			 }else if(esType.equals("9")){
				writeLog("签字","课题："+studyNoPara+" 动物体重复核完毕，签字");
			 }
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
	
	public String getCurrentSerialNum() {
		return currentSerialNum;
	}

	public void setCurrentSerialNum(String currentSerialNum) {
		this.currentSerialNum = currentSerialNum;
	}

	public String getNewWeightInd() {
		return newWeightInd;
	}

	public void setNewWeightInd(String newWeightInd) {
		this.newWeightInd = newWeightInd;
	}
    
	public int getAppInd() {
		return AppInd;
	}

	public void setAppInd(int appInd) {
		AppInd = appInd;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public TblWeightIndService getTblWeighIndService() {
		return tblWeighIndService;
	}

	public void setTblWeighIndService(TblWeightIndService tblWeighIndService) {
		this.tblWeighIndService = tblWeighIndService;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public TblAnimalService getTblAnimalService() {
		return tblAnimalService;
	}

	public void setTblAnimalService(TblAnimalService tblAnimalService) {
		this.tblAnimalService = tblAnimalService;
	}

	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}

	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}

	public TblDoseSettingService getTblDoseSettingService() {
		return tblDoseSettingService;
	}

	public void setTblDoseSettingService(TblDoseSettingService tblDoseSettingService) {
		this.tblDoseSettingService = tblDoseSettingService;
	}

	public List<TblAnimal> getTblAnimalList() {
		return tblAnimalList;
	}

	public void setTblAnimalList(List<TblAnimal> tblAnimalList) {
		this.tblAnimalList = tblAnimalList;
	}

	public TbLWeightDataService getTbLWeightDataService() {
		return tbLWeightDataService;
	}

	public void setTbLWeightDataService(TbLWeightDataService tbLWeightDataService) {
		this.tbLWeightDataService = tbLWeightDataService;
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

	public File getExcelCodeFile() {
		return excelCodeFile;
	}

	public void setExcelCodeFile(File excelCodeFile) {
		this.excelCodeFile = excelCodeFile;
	}

//	public void setExcelCodeFileFileName(String fileName) {
//		this.fileName = fileName;
//	}
	
	public void setExcelCodeFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getEsType() {
		return esType;
	}

	public void setEsType(String esType) {
		this.esType = esType;
	}

	public TblESService getTblESService() {
		return tblESService;
	}

	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TblWeighInd getTblWeighInd() {
		return tblWeighInd;
	}

	public void setTblWeighInd(TblWeighInd tblWeighInd) {
		this.tblWeighInd = tblWeighInd;
	}

	public TblTiprpAppIndService getTblTiprpAppIndService() {
		return tblTiprpAppIndService;
	}

	public void setTblTiprpAppIndService(TblTiprpAppIndService tblTiprpAppIndService) {
		this.tblTiprpAppIndService = tblTiprpAppIndService;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public String getFHsingName() {
		return FHsingName;
	}

	public void setFHsingName(String fHsingName) {
		FHsingName = fHsingName;
	}
    
   
	
	

}
