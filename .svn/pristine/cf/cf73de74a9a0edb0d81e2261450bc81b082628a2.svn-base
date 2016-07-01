package com.lanen.view.action.path;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Columns;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.path.DictViscera;
import com.lanen.model.path.TblAnatomyCheck;
import com.lanen.model.path.TblAnatomyReq;
import com.lanen.model.path.TblAnatomyReqAnimalList;
import com.lanen.model.path.TblAnatomyReqAnimalListHis;
import com.lanen.model.path.TblAnatomyReqAttachedViscera;
import com.lanen.model.path.TblAnatomyReqHis;
import com.lanen.model.path.TblAnatomyReqPathPlanCheck;
import com.lanen.model.path.TblAnatomyReqPathPlanCheckHis;
import com.lanen.model.path.TblAnatomyReqVisceraWeigh;
import com.lanen.model.path.TblAnatomyReqVisceraWeighHis;
import com.lanen.model.path.TblAnatomyReq_Json;
import com.lanen.model.path.TblAnatomyTask;
import com.lanen.model.path.TblVisceraFixed;
import com.lanen.model.path.TblVisceraFixedCompare;
import com.lanen.model.path.TblVisceraWeight;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.path.DictVisceraService;
import com.lanen.service.path.TblAnatomyCheckService;
import com.lanen.service.path.TblAnatomyReqAnimalListService;
import com.lanen.service.path.TblAnatomyReqAttachedVisceraService;
import com.lanen.service.path.TblAnatomyReqPathPlanCheckService;
import com.lanen.service.path.TblAnatomyReqService;
import com.lanen.service.path.TblAnatomyReqVisceraWeighService;
import com.lanen.service.path.TblAnatomyTaskService;
import com.lanen.service.path.TblVisceraFixedService;
import com.lanen.service.path.TblVisceraWeightService;
import com.lanen.service.studyplan.DictReportNumberService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.POIExcelUtil;
import com.lanen.util.StringUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblAnatomyReqAction extends BaseAction<TblAnatomyReq> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2484266726555242941L;
	/**
	 * 解剖申请  Service
	 */
	@Resource
	private TblAnatomyReqService tblAnatomyReqService;
	/**
	 * 专题计划  Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	/**
	 * 试验项目  Service
	 */
	@Resource
	private TblStudyItemService tblStudyItemService;
	/**
	 * 供试品  Service
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	/**
	 * 申请解剖动物列表  Service
	 */
	@Resource
	private TblAnatomyReqAnimalListService tblAnatomyReqAnimalListService;
//	/**
//	 * 用户  Service
//	 */
//	@Resource
//	private UserService userService;
	/**
	 * 解剖申请-脏器/组织学检查  Service
	 */
	@Resource
	private TblAnatomyReqPathPlanCheckService tblAnatomyReqPathPlanCheckService;
	/**
	 * 解剖申请-脏器称重  Service
	 */
	@Resource
	private TblAnatomyReqVisceraWeighService tblAnatomyReqVisceraWeighService;
	/**
	 * 解剖申请-脏器称重-附加脏器  Service
	 */
	@Resource
	private TblAnatomyReqAttachedVisceraService tblAnatomyReqAttachedVisceraService;
	/**
	 * 脏器字典  Service
	 */
	@Resource
	private DictVisceraService dictVisceraService;
	/**
	 * 报表编号  Service
	 */
	@Resource
	private DictReportNumberService dictReportNumberService;
	/**
	 * 剂量  Service
	 */
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	/**
	 * 动物详细解剖计划表 Service
	 */
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	
	/**
	 * 解剖详情Service
	 */
	 @Resource
	private TblAnatomyCheckService tblAnatomyCheckService;
	/**
	 * 称重详情Service
	 */
	 @Resource
	private TblVisceraWeightService tblVisceraWeightService;
	/**
	 * 固定详情Service
	 */
	 @Resource
	private TblVisceraFixedService tblVisceraFixedService;
	 /**
	  * 解剖任务Service
	  */
	 @Resource
	 private TblAnatomyTaskService tblAnatomyTaskService;
	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
//	/**
//	 * 动物字典service
//	 */
//	@Resource
//	private DictAnimalTypeService dictAnimalTypeService;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
//	private String pathDirector;
	
	/**
	 * 动物种类
	 */
	private String animalType;
	/**
	 * 用于判断跳转到添加编辑页面时，是添加按钮触发还是编辑按钮触发
	 */
	private String addOrEdit;
	/**
	 * 解剖申请动物编号
	 */
	private String animalCodes;
	/**
	 * 解剖申请动物性别
	 */
	private String genders;
	/**
	 * 解剖申请-脏器/组织学检查-脏器列表
	 */
	private String visceraNames;
	/**
	 * 解剖申请-脏器/组织学检查-剖检标志数组
	 * 
	 */
	private String atanomyCheckFlags;
	/**
	 * 病理计划-脏器/组织学检查-固定标志数组
	 */
	private String visceraFixedFlags;
	/**
	 * 解剖申请-脏器/组织学检查-镜检标志数组
	 */
	private String histopathCheckFlags;
	/**
	 * 解剖申请-脏器称重-脏器列表
	 */
	private String visceraNames1;
	/**
	 * 解剖申请-脏器称重-成对脏器分开称重标志数组
	 */
	private String partVisceraSeparateWeighs;
	/**
	 * 解剖申请-脏器称重-固定称重标志数组
	 */
	private String fixedWeighFlags;
	/**
	 *解剖申请-脏器称重-附加脏器 
	 */
	private String attachedVisceras;
	/**
	 * 解剖申请-申请编号
	 *//*
	private String reqNo;*/
	/**
	 * 解剖申请Id
	 */
	private String  reqId;
	/**
	 * 打印历史,1:是   0：否
	 */
	private int  printHis;
	/**
	 * 解剖所见查询条件
	 */
	private String visceraName;
	private String finding;
	
	/**
	 * 用于导出excel时使用
	 */
	InputStream fileInput;
	
	/**
	 * 报表，数据源
	 */
	private List<Map<String,Object>> sourceList ;
	/**
	 * 报表，参数
	 */
	private Map<String,Object> paraMap ;
	/**
	 * 报表，名称
	 */
	private String fileName;
	/**
	 * 打印预览后回到哪儿   ，apply回编辑页面
	 */
	private String toWhere;
	/**
	 * 用于动物排序
	 */
	List<DictViscera> visceraList;
	/**
	 * 导出excel，remark
	 */
	private String remark;
	
    /**
     * 转到解剖申请list界面
     */
    public String  list(){
    	TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		ActionContext.getContext().put("studydirector", tblStudyPlan.getStudydirector());
		ActionContext.getContext().put("pathDirector", tblStudyPlan.getPathDirector());
		ActionContext.getContext().put("animalType", tblStudyPlan.getAnimalType());
    	return "list";
    }
    /**
     * 加载已有的解剖申请
     */
    public void loadList(){
    	//根据课题编号查询课题下的解剖申请以及每个申请对应的动物数量
    	List<?> list=tblAnatomyReqService.getListByStudyNo(studyNoPara);
    	List<TblAnatomyReq_Json> list2=new ArrayList<TblAnatomyReq_Json>();
    	if(list!=null&&list.size()>0){
    		for(Object obj:list){
    			Object[] objs=(Object[])obj;
    			TblAnatomyReq_Json json=new TblAnatomyReq_Json();
    			json.setId((String)objs[1]);
    			json.setReqNo((Integer)objs[3]);
    			json.setSubmitDate((Date)objs[5]);
    			json.setBeginDate((Date)objs[9]);
    			json.setEndDate((Date)objs[10]);
    			json.setAnatomyRsn((Integer)objs[6]);
//    			//获取动物数量
//    			int animalNumber=tblAnatomyReqAnimalListService.getAnimalNumberByStudyNo(studyNoPara,ar.getReqNo());
    			json.setAnimalNumber((Integer)objs[0]);
    			json.setSubmitter((String)objs[4]);
    			json.setCreateTime((Date)objs[16]);
    			json.setTempFlag((Integer)objs[15]);
//    			User user=userService.getById((String)objs[17]);
    			json.setAuthor((String)objs[19]);
 //   			json.setState((Integer)objs[16]);
    			json.setSubmitFlag((Integer)objs[18]);
    			json.setAnatomyCheckFinishSign((String)objs[20]);
    			if(json.getAnatomyCheckFinishSign()!=null&&!"".equals(json.getAnatomyCheckFinishSign()))
    			{
    				json.setSubmitFlag(3);	
    			}
    			json.setVisceraFixedWeightFinishSign((String)objs[21]);
    			//脏器暂时用于存放： 0：无历史记录   1：有历史记录
    			int state = 0;
    			String hisId = (String) objs[22];
    			if(null != hisId && !"".equals(hisId)){
    				state = 1;
    			}
				json.setState(state );
    			list2.add(json);
    			
    		}
    	}
    	System.out.println("list2="+list2);
    	Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list2);
		 map.put("total", list2.size());
		 String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		 writeJson(json);
    }
    /**跳转到新建编辑页面
     * @return
     */
    public String toAddEdit(){
    	TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		ActionContext.getContext().put("studydirector", tblStudyPlan.getStudydirector());
		ActionContext.getContext().put("pathdirector", tblStudyPlan.getPathDirector());
		ActionContext.getContext().put("abnVisceraFixedFlag", tblStudyPlan.getAbnVisceraFixedFlag());
		ActionContext.getContext().put("abnVisceraHistopathCheckFlag", tblStudyPlan.getAbnVisceraHistopathCheckFlag());
		ActionContext.getContext().put("abnVisceraWeighFlag", tblStudyPlan.getAbnVisceraWeighFlag());
		animalType=tblStudyPlan.getAnimalType();
//		ActionContext.getContext().put("animaltype", tblStudyPlan.getAnimalType());
		return "addEditAnatomyReq";
    }
    
    /**
     * 判断解剖申请的动物性别和脏器是否匹配
     */
    public void isAnimalAndVisCon(){
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	boolean isTwoAniGen = false;
    	Integer animalGender = null ;
    	
    		//解析动物编号，添加解剖申请-动物列表
    		if(animalCodes!=null&&!animalCodes.equals("")){
    			String[] animalCodes1=animalCodes.split(",");
        		String[] genders1=genders.split(",");
        		for(int i=0;i<animalCodes1.length;i++){
        			//新增解剖申请-动物列表，获得ID
        			if(animalGender==null)
        				animalGender = Integer.parseInt(genders1[i]); 
        			else if(animalGender!=Integer.parseInt(genders1[i])){
        				isTwoAniGen = true;
        			}
        		}
    		}
    		if(isTwoAniGen){
    			map.put("success",true);
        		map.put("msg","两种动物性别都有，不用检查脏器");
    		}else{
    			
	    		//解析脏器名，剖检、镜检标志，添加解剖申请-脏器/组织学检查
	    		if(visceraNames!=null&&!visceraNames.equals("")){
	    			String[] visceraNamesCheck=visceraNames.split(",");
	    			if(visceraNamesCheck.length>0)
	    			{
		    			//0,无,1:雄  ,2:雌
		    			List<String> notSameSexVisceraList = dictVisceraService.getNotSameViscListByList(visceraNamesCheck,animalGender);
	    				if(notSameSexVisceraList!=null&&notSameSexVisceraList.size()>0)
	    				{
	    					map.put("success",false);
	    					String msg = (String)map.get("msg");
	    					if(msg==null)
	    					{
	    						msg = "组织学检查脏器：";
	    					}else{
	    						msg += "<br>组织学检查脏器：";
	    					}
	    					for(String str:notSameSexVisceraList)
	    					{
	    						msg += str+",";
	    					}
	    					
	    	        		map.put("msg",msg);
	    				}
	    			}
	        		
	    		}
	    		
	    		//解析脏器名，成对脏器分开称重、固定称重标志，以及附加脏器，添加解剖申请-脏器称重以及解剖申请-脏器称重-附加脏器
	    		if(!visceraNames1.equals("")){
	    			String[] visceraNamesWeigh =visceraNames1.split(",");
	        		
	        		String[] attachedVisceras1=attachedVisceras.split(",");
	        		
	        		if(visceraNamesWeigh.length>0)
	        		{
	        			//新增解剖申请-脏器称重，获得ID
	        			List<String> notSameSexVisceraList = dictVisceraService.getNotSameViscListByList(visceraNamesWeigh,animalGender);
	    				if(notSameSexVisceraList!=null&&notSameSexVisceraList.size()>0)
	    				{
	    					map.put("success",false);
	    					String msg = (String)map.get("msg");
	    					if(msg==null)
	    					{
	    						msg = "脏器称重：";
	    					}else{
	    						msg += "<br>脏器称重：";
	    					}
	    					for(String str:notSameSexVisceraList)
	    					{
	    						msg += str+",";
	    					}
	    	        		map.put("msg",msg);
	    				}
	        		}
	    				
	    				//脏器附件list
	    				List<String> attachVisceraList = new ArrayList<String>() ;
	    				
	        			//根据脏器名查询脏器实体
	        			for(String s:attachedVisceras1)
	        			{
		        			if(s.equals("0")){
		        			}else{
		        				
		        				for(String a:s.split("、"))
		        				{
		        					attachVisceraList.add(a);
		        				}
		        				
	        				}
	        			}
	        			String[] dd = new String[attachVisceraList.size()];
	        			attachVisceraList.toArray(dd);
	        			if(dd.length>0)
	        			{
		        			//脏器附件list
		        			List<String> notSameSexVisceraList1 = dictVisceraService.getNotSameViscListByList(dd,animalGender);
		    				if(notSameSexVisceraList1!=null&&notSameSexVisceraList1.size()>0)
		    				{
		    					map.put("success",false);
		    					String msg = (String)map.get("msg");
		    					if(msg==null)
		    					{
		    						msg = "附件脏器：";
		    					}else{
		    						msg += "<br>附件脏器：";
		    					}
		    					for(String str:notSameSexVisceraList1)
		    					{
		    						msg += str+",";
		    					}
		    	        		map.put("msg",msg);
		    				}
	        			}
	        		}
	    		}
	    		
    		
    		if(map.get("msg")!=null&&!"".equals(map.get("msg")))
    		{
    			map.put("msg",map.get("msg")+"<br>和申请动物的性别不一致");
    		}
    			
        writeJson(JsonPluginsUtil.beanToJson(map));
    		
    }
    
    /**
     * 新增动物解剖申请保存或编辑解剖申请
     */
    public void add(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	TblAnatomyReq tblAnatomyReq=null;
    	List<TblAnatomyReqAnimalList> listAnimals=new ArrayList<TblAnatomyReqAnimalList>();
    	List<TblAnatomyReqPathPlanCheck> listChecks=new ArrayList<TblAnatomyReqPathPlanCheck>();
    	List<TblAnatomyReqVisceraWeigh> listWeighs=new ArrayList<TblAnatomyReqVisceraWeigh>();
    	List<TblAnatomyReqAttachedViscera> listAttachedVisceras=new ArrayList<TblAnatomyReqAttachedViscera>();
    	if(addOrEdit.equals("add") ){
    		if(studyNoPara!=null&&studyNoPara!=""){
        		tblAnatomyReq=new TblAnatomyReq();
        		//新增解剖申请，获得申请ID
        		String id=tblAnatomyReqService.getKey();
        		tblAnatomyReq.setId(id);
        		tblAnatomyReq.setStudyNo(studyNoPara);
        		Integer reqNo=tblAnatomyReqService.getReqNoByStudyNo(studyNoPara);
        		tblAnatomyReq.setReqNo(reqNo);
        		User user=getCurrentUser();
        		tblAnatomyReq.setAuthor(user.getId());
        		Date date=new Date();
        		tblAnatomyReq.setCreateTime(date);
        		tblAnatomyReq.setSubmitFlag(model.getSubmitFlag());
        		//判断提交标志，如果为1，添加提交人和提交时间
        		if(model.getSubmitFlag()==1){
        			tblAnatomyReq.setSubmitter(user.getId());
        			tblAnatomyReq.setSubmitDate(date);
        		}
        		tblAnatomyReq.setAnatomyRsn(model.getAnatomyRsn());
        		tblAnatomyReq.setAnatomyPlanNum(model.getAnatomyPlanNum());
        		tblAnatomyReq.setTestPhase(model.getTestPhase());
        		tblAnatomyReq.setBeginDate(model.getBeginDate());
        		tblAnatomyReq.setEndDate(model.getEndDate());
        		tblAnatomyReq.setTempFlag(0);
        		tblAnatomyReq.setAnatomyCheckFlag(1);
        		//根据脏器称重计划的脏器字符串判断脏器称重是否设置计划
        		if(!visceraNames1.equals("")){
        			tblAnatomyReq.setVisceraWeighFlag(1);
        		}
//        		//根据固定字符串判断脏器称重是否设置固定计划
//        		if(!atanomyCheckFlags.equals("")){
//        			tblAnatomyReq.setAnatomyCheckFlag(1);
//        		}
//        		//根据固定字符串判断脏器称重是否设置固定计划
//        		if(!visceraFixedFlags.equals("")){
//        			tblAnatomyReq.setVisceraFixedFlag(1);
//        		}
//        		//根据镜检字符串判断脏器称重是否设置镜检计划
//        		if(!histopathCheckFlags.equals("")){
//        			tblAnatomyReq.setHistopathCheckFlag(1);
//        		}
//        		tblAnatomyReqService.save(tblAnatomyReq);
        		//解析动物编号，添加解剖申请-动物列表
        		if(!animalCodes.equals("")){
        			String[] animalCodes1=animalCodes.split(",");
            		String[] genders1=genders.split(",");
            		for(int i=0;i<animalCodes1.length;i++){
            			//新增解剖申请-动物列表，获得ID
            			String id1=tblAnatomyReqAnimalListService.getKey();
            			TblAnatomyReqAnimalList tblAnatomyReqAnimalList=new TblAnatomyReqAnimalList();
            			tblAnatomyReqAnimalList.setId(id1);
            			tblAnatomyReqAnimalList.setStudyNo(studyNoPara);
            			tblAnatomyReqAnimalList.setAnatomyReqNo(reqNo);
            			tblAnatomyReqAnimalList.setAnimalCode(animalCodes1[i]);
            			tblAnatomyReqAnimalList.setGender(Integer.parseInt(genders1[i]));
//            			TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(studyNoPara);
            			//获得专题计划中动物编号规则（1:A   2:B）并解析动物编号获得动物组别
//            			int animalCodeMode=tblStudyPlan.getAnimalCodeMode();
//            			if(animalCodeMode==1){
//            				int groupId=Integer.parseInt(animalCodes1[i].substring(0, 1));
//            				tblAnatomyReqAnimalList.setGroupID(groupId);
//            			}else if(animalCodeMode==2){
//            				int groupId=Integer.parseInt(animalCodes1[i].substring(1, 2));
//            				tblAnatomyReqAnimalList.setGroupID(groupId);
//            			}
            			Integer groupId=tblAnimalDetailDissectPlanService.getGroupIdByStudyNoAndAnimalCode(studyNoPara, animalCodes1[i]);
            			tblAnatomyReqAnimalList.setGroupID(groupId);
            			listAnimals.add(tblAnatomyReqAnimalList);
//            			tblAnatomyReqAnimalListService.save(tblAnatomyReqAnimalList);
            		}
        		}
        		int anatomyCheckFlag = 1;
        		int visceraFixedFlag = 0;
        		int histopathCheckFlag = 0;
        		//解析脏器名，剖检、镜检标志，添加解剖申请-脏器/组织学检查
        		if(!visceraNames.equals("")){
        			String[] visceraNamesCheck=visceraNames.split(",");
            		String[] atanomyCheckFlags1=atanomyCheckFlags.split(",");
            		String[] visceraFixedFlags1=visceraFixedFlags.split(",");
            		String[] histopathCheckFlags1=histopathCheckFlags.split(",");
            		//获得解剖申请-脏器/组织学检查序号(Sn)
        			int sn=tblAnatomyReqPathPlanCheckService.getSn(studyNoPara,reqNo);
            		for(int i=0;i<visceraNamesCheck.length;i++){
            			//新增解剖申请-脏器/组织学检查，获得ID
            			String id2=tblAnatomyReqPathPlanCheckService.getKey();
            			TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck=new TblAnatomyReqPathPlanCheck();
            			tblAnatomyReqPathPlanCheck.setId(id2);
            			tblAnatomyReqPathPlanCheck.setStudyNo(studyNoPara);
            			tblAnatomyReqPathPlanCheck.setReqNo(reqNo);
            			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesCheck[i]);
            			if(dictViscera!=null){
            				tblAnatomyReqPathPlanCheck.setVisceraCode(dictViscera.getVisceraCode());
            				tblAnatomyReqPathPlanCheck.setVisceraName(visceraNamesCheck[i]);
            				tblAnatomyReqPathPlanCheck.setVisceraType(dictViscera.getVisceraType());
            				tblAnatomyReqPathPlanCheck.setGender(dictViscera.getGender());
            			}
            			tblAnatomyReqPathPlanCheck.setAtanomyCheckFlag(Integer.parseInt(atanomyCheckFlags1[i]));
            			tblAnatomyReqPathPlanCheck.setVisceraFixedFlag(Integer.parseInt(visceraFixedFlags1[i]));
            			if(Integer.parseInt(visceraFixedFlags1[i]) == 1){
            				visceraFixedFlag++;
            			}
            			tblAnatomyReqPathPlanCheck.setHistopathCheckFlag(Integer.parseInt(histopathCheckFlags1[i]));
            			if(Integer.parseInt(histopathCheckFlags1[i]) == 1){
            				histopathCheckFlag++;
            			}
            			tblAnatomyReqPathPlanCheck.setSn(sn);
            			sn++;
            			listChecks.add(tblAnatomyReqPathPlanCheck);
//            			tblAnatomyReqPathPlanCheckService.save(tblAnatomyReqPathPlanCheck);
            		}
        		}
        		if(anatomyCheckFlag > 0){
        			tblAnatomyReq.setAnatomyCheckFlag(1);
        		}
        		if(visceraFixedFlag > 0){
        			tblAnatomyReq.setVisceraFixedFlag(1);
        		}
        		if(histopathCheckFlag > 0){
        			tblAnatomyReq.setHistopathCheckFlag(1);
        		}
        		
        		
        		//解析脏器名，成对脏器分开称重、固定称重标志，以及附加脏器，添加解剖申请-脏器称重以及解剖申请-脏器称重-附加脏器
        		if(!visceraNames1.equals("")){
        			String[] visceraNamesWeigh =visceraNames1.split(",");
            		String[] partVisceraSeparateWeighs1=partVisceraSeparateWeighs.split(",");
            		String[] fixedWeighFlags1=fixedWeighFlags.split(",");
            		String[] attachedVisceras1=attachedVisceras.split(",");
            		//获得解剖申请-脏器称重序号(Sn)
        			int sn1=tblAnatomyReqVisceraWeighService.getSn(studyNoPara,reqNo);
            		for(int i=0;i<visceraNamesWeigh.length;i++){
            			//新增解剖申请-脏器称重，获得ID
            			String id3=tblAnatomyReqVisceraWeighService.getKey();
            			TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh=new TblAnatomyReqVisceraWeigh();
            			tblAnatomyReqVisceraWeigh.setId(id3);
            			tblAnatomyReqVisceraWeigh.setStudyNo(studyNoPara);
            			tblAnatomyReqVisceraWeigh.setReqNo(reqNo);
            			//根据脏器名查询脏器实体
            			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesWeigh[i]);
            			if(null!=dictViscera){
            				tblAnatomyReqVisceraWeigh.setVisceraCode(dictViscera.getVisceraCode());
                			tblAnatomyReqVisceraWeigh.setVisceraName(visceraNamesWeigh[i]);
                			tblAnatomyReqVisceraWeigh.setVisceraType(dictViscera.getVisceraType());
            			}
            			if(attachedVisceras1[i].equals("0")){
            				tblAnatomyReqVisceraWeigh.setAttachedVisceraFlag(0);
            			}else{
            				tblAnatomyReqVisceraWeigh.setAttachedVisceraFlag(1);
            				String[] attachedVisceras2=attachedVisceras1[i].split("、");
            				for(int j=0;j<attachedVisceras2.length;j++){
            					//新增解剖申请-脏器称重-附加脏器，获得ID
            					String id4=tblAnatomyReqAttachedVisceraService.getKey();
            					TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera=new TblAnatomyReqAttachedViscera();
            					tblAnatomyReqAttachedViscera.setId(id4);
            					tblAnatomyReqAttachedViscera.setAnatomyReqVisceraWeighID(id3);
            					DictViscera dictViscera1=dictVisceraService.getByVisceraName(attachedVisceras2[j]);
            					if(null!=dictViscera1){
            						tblAnatomyReqAttachedViscera.setVisceraCode(dictViscera1.getVisceraCode());
                					tblAnatomyReqAttachedViscera.setVisceraName(attachedVisceras2[j]);
                					tblAnatomyReqAttachedViscera.setVisceraType(dictViscera1.getVisceraType());
            					}
            					listAttachedVisceras.add(tblAnatomyReqAttachedViscera);
//            					tblAnatomyReqAttachedVisceraService.save(tblAnatomyReqAttachedViscera);
            				}
            			}
            			tblAnatomyReqVisceraWeigh.setPartVisceraSeparateWeigh(Integer.parseInt(partVisceraSeparateWeighs1[i]));
            			tblAnatomyReqVisceraWeigh.setFixedWeighFlag(Integer.parseInt(fixedWeighFlags1[i]));
            			tblAnatomyReqVisceraWeigh.setSn(sn1);
            			sn1++;
            			listWeighs.add(tblAnatomyReqVisceraWeigh);
//            			tblAnatomyReqVisceraWeighService.save(tblAnatomyReqVisceraWeigh);
            		}
        		}
        		if(tblAnatomyReq!=null&&listAnimals.size()>0&&(listChecks.size()>0||listWeighs.size()>0)){
        			tblAnatomyReqService.addSave(tblAnatomyReq,listAnimals,listChecks,listWeighs,listAttachedVisceras);
        			map.put("success",true);
            		map.put("msg","添加成功");
            		map.put("reqId", id);
            		map.put("reqNo", reqNo);
            		
            		reqId= tblAnatomyReq.getId();
        		}
        	}
    	}else if(addOrEdit.equals("edit")){
    		//如果是编辑解剖申请，根据申请ID获得申请实体
    		tblAnatomyReq=tblAnatomyReqService.getById(reqId);
    		int oldSubmitFlag = tblAnatomyReq.getSubmitFlag();
    		//放到最后检查成功后再设置值
//    		if(oldSubmitFlag != 2){
//    			tblAnatomyReq.setAnatomyRsn(model.getAnatomyRsn());
//    			tblAnatomyReq.setAnatomyPlanNum(model.getAnatomyPlanNum());
//    			tblAnatomyReq.setTestPhase(model.getTestPhase());
//    			tblAnatomyReq.setBeginDate(model.getBeginDate());
//    			tblAnatomyReq.setEndDate(model.getEndDate());
//    			tblAnatomyReq.setSubmitFlag(model.getSubmitFlag());
//    		}else{
//    			if(model.getSubmitFlag() == 1){
//    				tblAnatomyReq.setSubmitFlag(1);
//    			}
//    		}
    		tblAnatomyReq.setAnatomyCheckFlag(1);
    		//编辑保存前，先删除原有的解剖申请-动物列表
    		List<TblAnatomyReqAnimalList> listOldAnimals=tblAnatomyReqAnimalListService.getListByStudyNoAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
//    		if(list!=null&&list.size()>0){
//    			for(TblAnatomyReqAnimalList aral:list){
//    				tblAnatomyReqAnimalListService.delete(aral.getId());
//    			}
//    		}
    		//编辑保存前，先查找原有的解剖申请-脏器/组织学检查(原有的将删除)
    		List<TblAnatomyReqPathPlanCheck> listOldChecks=tblAnatomyReqPathPlanCheckService.getListByStudyNoAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
//    		if(list2!=null&&list2.size()>0){
//    			for(TblAnatomyReqPathPlanCheck arppc:list2){
//    				tblAnatomyReqPathPlanCheckService.delete(arppc.getId());
//    			}
//    		}
    		//编辑保存前，先查找原有的解剖申请-脏器称重(原有的将删除)
    		List<TblAnatomyReqVisceraWeigh> listOldWeighs=tblAnatomyReqVisceraWeighService.getListByStudyAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
    		//编辑保存前，查找原有的解剖申请-脏器称重-附加脏器集(原有的将删除)
    		List<List<TblAnatomyReqAttachedViscera>> listOldAttachedVisceras=new ArrayList<List<TblAnatomyReqAttachedViscera>>();
    		if(listOldWeighs!=null&&listOldWeighs.size()>0){
    			for(TblAnatomyReqVisceraWeigh arvw:listOldWeighs){
    				//根据解剖申请-脏器称重ID，查找附加脏器，如果有添加至listOldAttachedVisceras
    				List<TblAnatomyReqAttachedViscera> list4=tblAnatomyReqAttachedVisceraService.getListByPid(arvw.getId());
    				if(list4!=null&&list4.size()>0){
//    					for(TblAnatomyReqAttachedViscera arav:list4){
//    						tblAnatomyReqAttachedVisceraService.delete(arav.getId());
//    					}
    					listOldAttachedVisceras.add(list4);
    				}
//    				tblAnatomyReqVisceraWeighService.delete(arvw.getId());	
    			}
    		}
    		//如果是提交，设置提交人和提交时间，放在保存前设置值
//    		if(model.getSubmitFlag()==1){
//    			User user=getCurrentUser();
//    			tblAnatomyReq.setSubmitter(user.getUserName());
//    			Date date=new Date();
//    			tblAnatomyReq.setSubmitDate(date);
//    		}
    		//根据脏器称重计划的脏器字符串判断脏器称重是否设置计划
    		if(!visceraNames1.equals("")){
    			tblAnatomyReq.setVisceraWeighFlag(1);
    		}else{
    			tblAnatomyReq.setVisceraWeighFlag(0);
    		}
//    		//根据剖检字符串判断解剖申请是否需脏器固定
//    		if(!atanomyCheckFlags.equals("")){
//    			tblAnatomyReq.setAnatomyCheckFlag(1);
//    		}
//    		//根据固定字符串判断解剖申请是否需脏器固定
//    		if(!visceraFixedFlags.equals("")){
//    			tblAnatomyReq.setVisceraFixedFlag(1);
//    		}
//    		//根据镜检字符串判断解剖申请是否需脏器镜检
//    		if(!histopathCheckFlags.equals("")){
//    			tblAnatomyReq.setHistopathCheckFlag(1);
//    		}
//    		tblAnatomyReqService.update(tblAnatomyReq);
    		//解析动物编号，添加解剖申请-动物列表
    		if(!animalCodes.equals("")){
    			String[] animalCodes1=animalCodes.split(",");
        		String[] genders1=genders.split(",");
        		
        		for(int i=0;i<animalCodes1.length;i++){
        			String id1=tblAnatomyReqAnimalListService.getKey();
        			TblAnatomyReqAnimalList tblAnatomyReqAnimalList=new TblAnatomyReqAnimalList();
        			tblAnatomyReqAnimalList.setId(id1);
        			tblAnatomyReqAnimalList.setStudyNo(studyNoPara);
        			tblAnatomyReqAnimalList.setAnatomyReqNo(tblAnatomyReq.getReqNo());
        			tblAnatomyReqAnimalList.setAnimalCode(animalCodes1[i]);
        			tblAnatomyReqAnimalList.setGender(Integer.parseInt(genders1[i]));
//        			TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(studyNoPara);
//        			int animalCodeMode=tblStudyPlan.getAnimalCodeMode();
//        			if(animalCodeMode==1){
//        				int groupId=Integer.parseInt(animalCodes1[i].substring(0, 1));
//        				tblAnatomyReqAnimalList.setGroupID(groupId);
//        			}else if(animalCodeMode==2){
//        				int groupId=Integer.parseInt(animalCodes1[i].substring(1, 2));
//        				tblAnatomyReqAnimalList.setGroupID(groupId);
//        			}
        			Integer groupId=tblAnimalDetailDissectPlanService.getGroupIdByStudyNoAndAnimalCode(studyNoPara, animalCodes1[i]);
        			tblAnatomyReqAnimalList.setGroupID(groupId);
        			listAnimals.add(tblAnatomyReqAnimalList);
//        			tblAnatomyReqAnimalListService.save(tblAnatomyReqAnimalList);
        		}
    		}
    		int anatomyCheckFlag = 1;
    		int visceraFixedFlag = 0;
    		int histopathCheckFlag = 0;
    		//解析脏器名，剖检、镜检标志，添加解剖申请-脏器/组织学检查
    		if(!visceraNames.equals("")){
    			String[] visceraNamesCheck=visceraNames.split(",");
        		String[] atanomyCheckFlags1=atanomyCheckFlags.split(",");
        		String[] visceraFixedFlags1=visceraFixedFlags.split(",");
        		String[] histopathCheckFlags1=histopathCheckFlags.split(","); 
        		int sn=tblAnatomyReqPathPlanCheckService.getSn(studyNoPara,model.getReqNo());
        		for(int i=0;i<visceraNamesCheck.length;i++){
        			String id2=tblAnatomyReqPathPlanCheckService.getKey();
        			TblAnatomyReqPathPlanCheck tblAnatomyReqPathPlanCheck=new TblAnatomyReqPathPlanCheck();
        			tblAnatomyReqPathPlanCheck.setId(id2);
        			tblAnatomyReqPathPlanCheck.setStudyNo(studyNoPara);
        			tblAnatomyReqPathPlanCheck.setReqNo(tblAnatomyReq.getReqNo());
        			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesCheck[i]);
        			if(dictViscera!=null){
        				tblAnatomyReqPathPlanCheck.setVisceraCode(dictViscera.getVisceraCode());
        				tblAnatomyReqPathPlanCheck.setVisceraName(visceraNamesCheck[i]);
        				tblAnatomyReqPathPlanCheck.setVisceraType(dictViscera.getVisceraType());
        				tblAnatomyReqPathPlanCheck.setGender(dictViscera.getGender());
        			}
        			tblAnatomyReqPathPlanCheck.setAtanomyCheckFlag(Integer.parseInt(atanomyCheckFlags1[i]));
        			tblAnatomyReqPathPlanCheck.setVisceraFixedFlag(Integer.parseInt(visceraFixedFlags1[i]));
        			tblAnatomyReqPathPlanCheck.setHistopathCheckFlag(Integer.parseInt(histopathCheckFlags1[i]));
        			if(Integer.parseInt(visceraFixedFlags1[i]) == 1){
        				visceraFixedFlag++;
        			}
        			if(Integer.parseInt(histopathCheckFlags1[i]) == 1){
        				histopathCheckFlag++;
        			}
        			tblAnatomyReqPathPlanCheck.setSn(sn);
        			sn++;
        			listChecks.add(tblAnatomyReqPathPlanCheck);
//        			tblAnatomyReqPathPlanCheckService.save(tblAnatomyReqPathPlanCheck);
        		}
    		}
    		
    		if(anatomyCheckFlag > 0){
    			tblAnatomyReq.setAnatomyCheckFlag(1);
    		}else{
    			tblAnatomyReq.setAnatomyCheckFlag(0);
    		}
    		if(visceraFixedFlag > 0){
    			tblAnatomyReq.setVisceraFixedFlag(1);
    		}else{
    			tblAnatomyReq.setVisceraFixedFlag(0);
    		}
    		if(histopathCheckFlag > 0){
    			tblAnatomyReq.setHistopathCheckFlag(1);
    		}else{
    			tblAnatomyReq.setHistopathCheckFlag(0);
    		}
    		
    		//解析脏器名，成对脏器分开称重、固定称重标志，以及附加脏器，添加解剖申请-脏器称重以及解剖申请-脏器称重-附加脏器
    		if(!visceraNames1.equals("")){
    			String[] visceraNamesWeigh =visceraNames1.split(",");
        		String[] partVisceraSeparateWeighs1=partVisceraSeparateWeighs.split(",");
        		String[] fixedWeighFlags1=fixedWeighFlags.split(",");
        		String[] attachedVisceras1=attachedVisceras.split(",");
        		int sn1=tblAnatomyReqVisceraWeighService.getSn(studyNoPara,model.getReqNo());
        		for(int i=0;i<visceraNamesWeigh.length;i++){
        			String id3=tblAnatomyReqVisceraWeighService.getKey();
        			TblAnatomyReqVisceraWeigh tblAnatomyReqVisceraWeigh=new TblAnatomyReqVisceraWeigh();
        			tblAnatomyReqVisceraWeigh.setId(id3);
        			tblAnatomyReqVisceraWeigh.setStudyNo(studyNoPara);
        			tblAnatomyReqVisceraWeigh.setReqNo(tblAnatomyReq.getReqNo());
        			DictViscera dictViscera=dictVisceraService.getByVisceraName(visceraNamesWeigh[i]);
        			if(null!=dictViscera){
        				tblAnatomyReqVisceraWeigh.setVisceraCode(dictViscera.getVisceraCode());
            			tblAnatomyReqVisceraWeigh.setVisceraName(dictViscera.getVisceraName());
            			tblAnatomyReqVisceraWeigh.setVisceraType(dictViscera.getVisceraType());
        			}
        			if(attachedVisceras1[i].equals("0")){
        				tblAnatomyReqVisceraWeigh.setAttachedVisceraFlag(0);
        			}else{
        				tblAnatomyReqVisceraWeigh.setAttachedVisceraFlag(1);
        				String[] attachedVisceras2=attachedVisceras1[i].split("、");
        				for(int j=0;j<attachedVisceras2.length;j++){
        					String id4=tblAnatomyReqAttachedVisceraService.getKey();
        					TblAnatomyReqAttachedViscera tblAnatomyReqAttachedViscera=new TblAnatomyReqAttachedViscera();
        					tblAnatomyReqAttachedViscera.setId(id4);
        					tblAnatomyReqAttachedViscera.setAnatomyReqVisceraWeighID(id3);
        					DictViscera dictViscera1=dictVisceraService.getByVisceraName(attachedVisceras2[j]);
        					if(null!=dictViscera1){
        						tblAnatomyReqAttachedViscera.setVisceraCode(dictViscera1.getVisceraCode());
            					tblAnatomyReqAttachedViscera.setVisceraName(attachedVisceras2[j]);
            					tblAnatomyReqAttachedViscera.setVisceraType(dictViscera1.getVisceraType());
        					}
        					
        					listAttachedVisceras.add(tblAnatomyReqAttachedViscera);
//        					tblAnatomyReqAttachedVisceraService.save(tblAnatomyReqAttachedViscera);
        				}
        			}
        			tblAnatomyReqVisceraWeigh.setPartVisceraSeparateWeigh(Integer.parseInt(partVisceraSeparateWeighs1[i]));
        			tblAnatomyReqVisceraWeigh.setFixedWeighFlag(Integer.parseInt(fixedWeighFlags1[i]));
        			tblAnatomyReqVisceraWeigh.setSn(sn1);
        			sn1++;
        			listWeighs.add(tblAnatomyReqVisceraWeigh); 
//        			tblAnatomyReqVisceraWeighService.save(tblAnatomyReqVisceraWeigh);
        		}
    		}
    		if(tblAnatomyReq!=null&&listAnimals.size()>0&&(listChecks.size()>0||listWeighs.size()>0)){
    			if(oldSubmitFlag == 2){
    				TblAnatomyTask anatomyTask = tblAnatomyTaskService.getByStudyNoReqNo(tblAnatomyReq.getStudyNo(),tblAnatomyReq.getReqNo());
        			if(null != anatomyTask &&( null == anatomyTask.getAnatomyCheckFinishSign()
        					|| "".equals(anatomyTask.getAnatomyCheckFinishSign().trim()) ) ){
        				//被移除的待解剖的动物列表
        				List<String> animalCodeList = new ArrayList<String>();
        				//被移除的待解剖脏器编号列表
        				List<String> visceraCodeList_check =  new ArrayList<String>();
        				//被移除的待固定脏器编号列表
        				List<String> visceraCodeList_fixed =  new ArrayList<String>();
        				//被移除的待称重脏器编号列表
        				List<String> visceraCodeList_weigh =  new ArrayList<String>();
        				if(null != listOldAnimals && listOldAnimals.size() > 0){
        					boolean exist = false;
        					for(TblAnatomyReqAnimalList obj:listOldAnimals){
        						String animalCode = obj.getAnimalCode();
        						for(TblAnatomyReqAnimalList newobj:listAnimals){
        							if(animalCode.equals(newobj.getAnimalCode())){
        								exist =  true;
        								break;
        							}
        						}
        						if(!exist){
        							animalCodeList.add(animalCode);
        						}
        						exist = false;
        					}
        				}
        				if(null != listOldChecks && listOldChecks.size() > 0){
        					boolean exist = false;
        					boolean exist2 = false;
        					for(TblAnatomyReqPathPlanCheck obj:listOldChecks){
        						String visceraCode = obj.getVisceraCode();
        						int visceraFixedFlag2 = obj.getVisceraFixedFlag();
        						for(TblAnatomyReqPathPlanCheck newobj:listChecks){
        							if(visceraCode.equals(newobj.getVisceraCode())){
        								exist =  true;
        								if(visceraFixedFlag2 == 1 && visceraCode.equals(newobj.getVisceraCode()) 
        										&& newobj.getVisceraFixedFlag() == 1){
        									exist2 =  true;
        								}
        								break;
        							}
        						}
        						if(!exist){
        							visceraCodeList_check.add(visceraCode);
        						}
        						if(visceraFixedFlag2 == 1 && !exist2){
        							visceraCodeList_fixed.add(visceraCode);
        						}
        						exist = false;
        						exist2 = false;
        					}
        				}
        				if(null != listOldWeighs && listOldWeighs.size() > 0){
        					boolean exist = false;
        					for(TblAnatomyReqVisceraWeigh obj:listOldWeighs){
        						String visceraCode = obj.getVisceraCode();
        						for(TblAnatomyReqVisceraWeigh newobj:listWeighs){
        							if(visceraCode.equals(newobj.getVisceraCode())){
        								exist =  true;
        								break;
        							}
        						}
        						if(!exist){
        							visceraCodeList_weigh.add(visceraCode);
        						}
        						exist = false;
        					}
        				}
        				
//        				//被移除的待解剖的动物列表
//        				List<String> animalCodeList = new ArrayList<String>();
//        				//被移除的待解剖脏器编号列表
//        				List<String> visceraCodeList_check =  new ArrayList<String>();
//        				//被移除的待固定脏器编号列表
//        				List<String> visceraCodeList_fixed =  new ArrayList<String>();
//        				//被移除的待称重脏器编号列表
//        				List<String> visceraCodeList_weigh =  new ArrayList<String>();
        				boolean success = true;
        				if(null != animalCodeList && animalCodeList.size() > 0){
        					List<String> errorAnimalCodeList = tblAnatomyReqService.getErrorAnimalCodeList(tblAnatomyReq.getStudyNo(),animalCodeList);
        					if(null != errorAnimalCodeList && errorAnimalCodeList.size() > 0){
        						success = false;
        						map.put("success",false);
                				String msg ="动物（"+errorAnimalCodeList.get(0)+"）已解剖，不可以从申请中剔除！";
								map.put("msg",msg );
        					}
        				}
        				if(success && null != visceraCodeList_check && visceraCodeList_check.size() > 0){
        					List<String> errorVisceraCodeList_check = tblAnatomyReqService.getErrorCheckVisceraCodeList(tblAnatomyReq.getStudyNo(),visceraCodeList_check);
        					if(null != errorVisceraCodeList_check && errorVisceraCodeList_check.size() > 0){
        						success = false;
        						map.put("success",false);
        						String visceraCode = errorVisceraCodeList_check.get(0);
        						String visceraName = "";
        						for(TblAnatomyReqPathPlanCheck obj:listOldChecks){
            						if(visceraCode.equals(obj.getVisceraCode())){
            							visceraName = obj.getVisceraName();
            							break;
            						}
            					}
                				String msg ="动物脏器（"+visceraName+"）已有解剖记录，不可以从申请中剔除！";
								map.put("msg",msg );
        					}
        				}
        				if(success && null != visceraCodeList_fixed && visceraCodeList_fixed.size() > 0){
        					List<String> errorVisceraCodeList_fixed = tblAnatomyReqService.getErrorFixedVisceraCodeList(tblAnatomyReq.getStudyNo(),visceraCodeList_fixed);
        					if(null != errorVisceraCodeList_fixed && errorVisceraCodeList_fixed.size() > 0){
        						success = false;
        						map.put("success",false);
        						String visceraCode = errorVisceraCodeList_fixed.get(0);
        						String visceraName = "";
        						for(TblAnatomyReqPathPlanCheck obj:listOldChecks){
            						if(visceraCode.equals(obj.getVisceraCode())){
            							visceraName = obj.getVisceraName();
            							break;
            						}
            					}
                				String msg ="动物脏器（"+visceraName+"）已有固定记录，不可以从申请中剔除！";
								map.put("msg",msg );
        					}
        				}
        				if(success && null != visceraCodeList_weigh && visceraCodeList_weigh.size() > 0){
        					List<String> errorVisceraCodeList_weigh = tblAnatomyReqService.getErrorWeighVisceraCodeList(tblAnatomyReq.getStudyNo(),visceraCodeList_weigh);
        					if(null != errorVisceraCodeList_weigh && errorVisceraCodeList_weigh.size() > 0){
        						success = false;
        						map.put("success",false);
        						String visceraCode = errorVisceraCodeList_weigh.get(0);
        						String visceraName = "";
        						for(TblAnatomyReqVisceraWeigh obj:listOldWeighs){
            						if(visceraCode.equals(obj.getVisceraCode())){
            							visceraName = obj.getVisceraName();
            							break;
            						}
            					}
                				String msg ="动物脏器（"+visceraName+"）已有称重记录，不可以从申请中剔除！";
								map.put("msg",msg );
        					}
        				}
        				
        				if(success){
        		    		if(oldSubmitFlag != 2){
        		    			tblAnatomyReq.setAnatomyRsn(model.getAnatomyRsn());
        		    			tblAnatomyReq.setAnatomyPlanNum(model.getAnatomyPlanNum());
        		    			tblAnatomyReq.setTestPhase(model.getTestPhase());
        		    			tblAnatomyReq.setBeginDate(model.getBeginDate());
        		    			tblAnatomyReq.setEndDate(model.getEndDate());
        		    			tblAnatomyReq.setSubmitFlag(model.getSubmitFlag());
        		    		}else{
        		    			if(model.getSubmitFlag() == 1){
        		    				tblAnatomyReq.setSubmitFlag(1);
        		    			}
        		    		}
	        		    	if(model.getSubmitFlag()==1){
	        	    			User user=getCurrentUser();
	        	    			tblAnatomyReq.setSubmitter(user.getUserName());
	        	    			Date date=new Date();
	        	    			tblAnatomyReq.setSubmitDate(date);
	        	    		}
        					tblAnatomyReqService.editSave(listOldAnimals,listOldChecks,listOldWeighs,listOldAttachedVisceras,tblAnatomyReq,listAnimals,listChecks,listWeighs,listAttachedVisceras);
            				map.put("success",true);
            				map.put("msg","编辑成功");
            				map.put("reqId", tblAnatomyReq.getId());
            				map.put("reqNo", tblAnatomyReq.getReqNo());
            				reqId= tblAnatomyReq.getId();
        				}
        			}else{
        				if(oldSubmitFlag != 2){
    		    			tblAnatomyReq.setAnatomyRsn(model.getAnatomyRsn());
    		    			tblAnatomyReq.setAnatomyPlanNum(model.getAnatomyPlanNum());
    		    			tblAnatomyReq.setTestPhase(model.getTestPhase());
    		    			tblAnatomyReq.setBeginDate(model.getBeginDate());
    		    			tblAnatomyReq.setEndDate(model.getEndDate());
    		    			tblAnatomyReq.setSubmitFlag(model.getSubmitFlag());
    		    		}else{
    		    			if(model.getSubmitFlag() == 1){
    		    				tblAnatomyReq.setSubmitFlag(1);
    		    			}
    		    		}
        		    	if(model.getSubmitFlag()==1){
        	    			User user=getCurrentUser();
        	    			tblAnatomyReq.setSubmitter(user.getUserName());
        	    			Date date=new Date();
        	    			tblAnatomyReq.setSubmitDate(date);
        	    		}
        				tblAnatomyReqService.editSave(listOldAnimals,listOldChecks,listOldWeighs,listOldAttachedVisceras,tblAnatomyReq,listAnimals,listChecks,listWeighs,listAttachedVisceras);
        				map.put("success",true);
        				map.put("msg","编辑成功");
        				map.put("reqId", tblAnatomyReq.getId());
        				map.put("reqNo", tblAnatomyReq.getReqNo());
        				reqId= tblAnatomyReq.getId();
        			}
    			}else{
    				if(oldSubmitFlag != 2){
		    			tblAnatomyReq.setAnatomyRsn(model.getAnatomyRsn());
		    			tblAnatomyReq.setAnatomyPlanNum(model.getAnatomyPlanNum());
		    			tblAnatomyReq.setTestPhase(model.getTestPhase());
		    			tblAnatomyReq.setBeginDate(model.getBeginDate());
		    			tblAnatomyReq.setEndDate(model.getEndDate());
		    			tblAnatomyReq.setSubmitFlag(model.getSubmitFlag());
		    		}else{
		    			if(model.getSubmitFlag() == 1){
		    				tblAnatomyReq.setSubmitFlag(1);
		    			}
		    		}
    		    	if(model.getSubmitFlag()==1){
    	    			User user=getCurrentUser();
    	    			tblAnatomyReq.setSubmitter(user.getUserName());
    	    			Date date=new Date();
    	    			tblAnatomyReq.setSubmitDate(date);
    	    		}
    				tblAnatomyReqService.editSave(listOldAnimals,listOldChecks,listOldWeighs,listOldAttachedVisceras,tblAnatomyReq,listAnimals,listChecks,listWeighs,listAttachedVisceras);
    				map.put("success",true);
    				map.put("msg","编辑成功");
    				map.put("reqId", tblAnatomyReq.getId());
    				map.put("reqNo", tblAnatomyReq.getReqNo());
    				reqId= tblAnatomyReq.getId();
    			}
    		}
    		
    	}
    	
    	
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    /**
     * 解剖申请编辑前数据加载
     */
    public void toEdit(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(reqId!=null&&!reqId.equals("")){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(reqId);
    		map.put("id", tblAnatomyReq.getId());
    		map.put("anatomyRsn", tblAnatomyReq.getAnatomyRsn());
    		map.put("anatomyPlanNum", tblAnatomyReq.getAnatomyPlanNum());
    		map.put("beginDate", DateUtil.dateToString(tblAnatomyReq.getBeginDate(), "yyyy-MM-dd"));
    		map.put("endDate", DateUtil.dateToString(tblAnatomyReq.getEndDate(), "yyyy-MM-dd"));
    		map.put("testPhase", tblAnatomyReq.getTestPhase());
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    /**
     * 解剖申请删除
     */
    public void delete(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	if(model.getId()!=null){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(model.getId());
    		//根据课题编号和申请编号，获得解剖申请-动物列表
    		List<TblAnatomyReqAnimalList> listAnimals=tblAnatomyReqAnimalListService.getListByStudyNoAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
    		//根据课题编号和申请编号，获得解剖申请-脏器/组织学检查
    		List<TblAnatomyReqPathPlanCheck> listChecks=tblAnatomyReqPathPlanCheckService.getListByStudyNoAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
    		//根据课题编号和申请编号，获得解剖申请-脏器称重
    		List<TblAnatomyReqVisceraWeigh> listWeighs=tblAnatomyReqVisceraWeighService.getListByStudyAndReqNo(studyNoPara, tblAnatomyReq.getReqNo());
    		//编辑保存前，查找解剖申请-脏器称重-附加脏器集
    		List<List<TblAnatomyReqAttachedViscera>> listAttachedVisceras=new ArrayList<List<TblAnatomyReqAttachedViscera>>();
    		if(listWeighs!=null&&listWeighs.size()>0){
    			for(TblAnatomyReqVisceraWeigh arvw:listWeighs){
    				//根据脏器称重ID获得附加脏器，如果有添加至listAttachedVisceras
    				List<TblAnatomyReqAttachedViscera> list4=tblAnatomyReqAttachedVisceraService.getListByPid(arvw.getId());
    				if(list4!=null&&list4.size()>0){
    					listAttachedVisceras.add(list4);
    				}
    			}
    		}
    		//将解剖申请，解剖申请-动物列表，解剖申请-脏器/组织学检查，解剖申请-脏器称重，解剖申请-脏器称重-附加脏器一起删除
    		tblAnatomyReqService.deleteReqAndRelated(tblAnatomyReq,listAnimals,listChecks,listWeighs,listAttachedVisceras);
    		map.put("success",true);
    		map.put("msg","删除成功");
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    /**
     * 提交申请
     */
    public void submit(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	if(model.getId()!=null&&!model.getId().equals("")){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(model.getId());
    		if(tblAnatomyReq.getVisceraWeighFlag()!=0||tblAnatomyReq.getVisceraFixedFlag()!=0){
    			tblAnatomyReq.setSubmitFlag(1);
        		Date date=new  Date();
        		tblAnatomyReq.setSubmitDate(date);
        		User user=getCurrentUser();
        		tblAnatomyReq.setSubmitter(user.getId());
        		tblAnatomyReqService.update(tblAnatomyReq);
        		map.put("success",true);
        		map.put("msg","提交成功");
        		map.put("reqId", tblAnatomyReq.getId());
    		}
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    /**
     * 撤销申请
     */
    public void cancel(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	if(model.getId()!=null&&!model.getId().equals("")){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(model.getId());
    		if(tblAnatomyReq.getSubmitFlag() == 1){
    			tblAnatomyReq.setSubmitFlag(-1);
    			tblAnatomyReqService.update(tblAnatomyReq);
    			map.put("success",true);
    			map.put("msg","撤销成功");
    			map.put("reqId", tblAnatomyReq.getId());
    			
    			//通知消息 给病理负责人，专题病理负责人
    			//userName
    			List<String> pathSdList = tblAnatomyReqService.getPathSdCodeList(tblAnatomyReq.getStudyNo());
    			
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("解剖申请撤销提醒");//消息头
				String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				String currentTimeStr = DateUtil.getNow("yyyy年MM月dd HH:mm");
				String anatomyRsnStr = "";
				if ( tblAnatomyReq.getAnatomyRsn() == 1 ){
					anatomyRsnStr = "计划解剖";
				}else if( tblAnatomyReq.getAnatomyRsn() == 2 ){
					anatomyRsnStr = "濒死解剖";
				}else{
					anatomyRsnStr = "死亡解剖";
				}
				msgContent = msgContent+getCurrentRealName()+"于"+currentTimeStr+"撤销了解剖申请" +
						"（专题编号：" +tblAnatomyReq.getStudyNo()+
						"，计划解剖日期：" + DateUtil.dateToString(tblAnatomyReq.getBeginDate(), "yyyy-MM-dd")   +
						"，解剖原因：" +anatomyRsnStr+
						"），特此提醒！";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				tblNotification.setSender(getCurrentRealName());// 发送者
				tblNotification.setSendTime(new Date());// 发送时间
			
				tblNotificationService.save(tblNotification,pathSdList);
    			
				//日志
				String operatContent = getCurrentRealName()+"于"+currentTimeStr+"撤销了解剖申请" +
				"（专题编号：" +tblAnatomyReq.getStudyNo()+
				"，计划解剖日期：" + DateUtil.dateToString(tblAnatomyReq.getBeginDate(), "yyyy-MM-dd")   +
				"，解剖原因：" +anatomyRsnStr+
				"）";
				writeLog("撤销解剖申请",operatContent);
    			
    		}
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
    	writeJson(jsonStr);
    }
    /**
     * 确认临时申请
     */
    public void confirmTempReq()
	{
    	Map<String,Object> map = new HashMap<String, Object>();
    	if(model.getId()!=null&&!model.getId().equals("")){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(model.getId());
    		if(tblAnatomyReq.getTempFlag() == 1){
    			tblAnatomyReq.setTempFlag(2);
    			tblAnatomyReq.setTempConfirmMan(getCurrentRealName());
    			tblAnatomyReqService.update(tblAnatomyReq);
    			map.put("success",true);
    			map.put("msg","确认临时申请成功");
    			map.put("reqId", tblAnatomyReq.getId());
    			
    			String currentTimeStr = DateUtil.getNow("yyyy年MM月dd HH:mm");
				String anatomyRsnStr = "";
				if ( tblAnatomyReq.getAnatomyRsn() == 1 ){
					anatomyRsnStr = "计划解剖";
				}else if( tblAnatomyReq.getAnatomyRsn() == 2 ){
					anatomyRsnStr = "濒死解剖";
				}else{
					anatomyRsnStr = "死亡解剖";
				}
				
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
			
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
				es.setEsType(448);
				es.setEsTypeDesc("确认临时解剖申请");
				es.setDateTime(new Date());
				String eid = tblESService.getKey("TblES");
				es.setEsId(eid);
			
				
				esLink.setTableName("TblAnatomyReq");
				esLink.setDataId(model.getId());
				esLink.setTblES(es);
				esLink.setEsType(448);
				esLink.setEsTypeDesc("确认临时解剖申请签字");
				esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				tblESService.save(es);
				tblESLinkService.save(esLink);
    			
				//日志
				String operatContent = getCurrentRealName()+"于"+currentTimeStr+"确认了临时解剖申请" +
				"（专题编号：" +tblAnatomyReq.getStudyNo()+
				"，计划解剖日期：" + DateUtil.dateToString(tblAnatomyReq.getBeginDate(), "yyyy-MM-dd")   +
				"，解剖原因：" +anatomyRsnStr+
				"）";
				writeLog("确认临时解剖申请",operatContent);
    			
    		}
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
    	writeJson(jsonStr);
	}
    /**
     * 变更申请
     */
    public void change(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("success",false);
    	if(model.getId()!=null&&!model.getId().equals("")){
    		TblAnatomyReq tblAnatomyReq=tblAnatomyReqService.getById(model.getId());
    		//1.检查     1）申请已提交   2）对应为空或对应任务未解剖完成
    		if(tblAnatomyReq.getSubmitFlag() == 1){
//    			tblAnatomyReq.setSubmitFlag(-1);
//    			tblAnatomyReqService.update(tblAnatomyReq);
    			TblAnatomyTask anatomyTask = tblAnatomyTaskService.getByStudyNoReqNo(tblAnatomyReq.getStudyNo(),tblAnatomyReq.getReqNo());
    			if(null == anatomyTask || null == anatomyTask.getAnatomyCheckFinishSign()
    					|| "".equals(anatomyTask.getAnatomyCheckFinishSign().trim())){
    				
    				tblAnatomyReqService.change(tblAnatomyReq);
    				
    				map.put("success",true);
    				map.put("msg","变更中...");
    				map.put("reqId", tblAnatomyReq.getId());
    			}
    			
    		}
    	}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
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
		  tblLog.setOperatOject("解剖申请");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
    
	/**
	 * 变更申请检查
	 */
	public void changeCheck(){
		//TODO 
		Json json = new Json();
		if(null != model.getStudyNo() && model.getReqNo() > 0){
			
			TblAnatomyTask anatomyTask = tblAnatomyTaskService.getByStudyNoReqNo(model.getStudyNo(),model.getReqNo());
			if(null != anatomyTask){
				String anatomyCheckFinishSign = anatomyTask.getAnatomyCheckFinishSign();
				if(null == anatomyCheckFinishSign || "".equals(anatomyCheckFinishSign.trim())){
					json.setSuccess(true);
				}else{
					json.setSuccess(false);
				}
			}else{
				json.setSuccess(true);
			}
		}
		String jsonstr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonstr);
	}
    /**
     * 撤销申请检查
     */
    public void cancelCheck(){
    	Json json = new Json();
    	if(null != model.getStudyNo() && model.getReqNo() > 0){
    		
    		Boolean exist = tblAnatomyTaskService.isExistByStudyNoReqNo(model.getStudyNo(),model.getReqNo());
    		json.setSuccess(!exist);
    	}
    	String jsonstr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonstr);
    }
    /**
     * 浏览解剖信息：动物信息，解剖，称重，固定
     * @return
     */
    public String toCheckDetail()
    {
    	//解剖所见
    	//称重
    	//固定
    	ActionContext.getContext().put("studyNoPara", studyNoPara);
    	ActionContext.getContext().put("reqId",reqId );
    	ActionContext.getContext().put("reqNo",model.getReqNo() );
    	ActionContext.getContext().put("beginDate",DateUtil.dateToString(model.getBeginDate(),"yyyy-MM-dd"));
    	ActionContext.getContext().put("createTime",DateUtil.dateToString(model.getCreateTime(),"yyyy-MM-dd"));
    	
    	return "anatomyDetail";
    }
    /**
     * 根据studyNo获取解剖所见的数据
     * @return
     */
    public void getAnatomyCheckData()
    {
    	if(visceraName==null)
    		visceraName="全部";
    	if(finding==null)
    		finding="全部";
    	List<TblAnatomyCheck> anatomyCheckList = tblAnatomyCheckService.getAnatomyCheckByStudyNoAndReqNo(studyNoPara,model.getReqNo(),visceraName,finding);
    	if(null != anatomyCheckList && anatomyCheckList.size() > 0){
    		for(TblAnatomyCheck obj:anatomyCheckList){
    			if(null != obj.getSubVisceraName() && !"".equals(obj.getSubVisceraName())){
    				obj.setVisceraName(obj.getSubVisceraName());
    			}
    		}
    	}
    	//if(anatomyCheckList!=null&&anatomyCheckList.size()>0)
    	//{
	    	String jsonStr = JsonPluginsUtil.beanListToJson(anatomyCheckList,"yyyy-MM-dd HH:mm");
	    	writeJson(jsonStr);
    	//}
    }
   
    /**根据studyNo获取解剖所见的脏器信息
     * 
     */
    public void getAnatomyCheckVisceraData()
    {
    	System.out.println("getAnatomyCheckVisceraData is "+model.getReqNo());
    	List<Map<String, Object>> anatomyCheckList = tblAnatomyCheckService.getAnatomyCheckVisceraByStudyNoAndReqNo(studyNoPara,model.getReqNo());
    	if(anatomyCheckList!=null&&anatomyCheckList.size()>0)
    	{
    		String json = JsonPluginsUtil.beanListToJson(anatomyCheckList,"yyyy-MM-dd HH:mm");
    		writeJson(json);
    	}
    }
    /**根据studyNo,reqNo获取解剖所见的脏器信息
     * 
     */
    public void getAnatomyCheckFindingData()
    {
    
    	System.out.println("getAnatomyCheckFindingData is "+model.getReqNo());
    	List<Map<String, Object>> anatomyCheckList = tblAnatomyCheckService.getAnatomyCheckFindingByStudyNoAndReqNo(studyNoPara,model.getReqNo());
    	if(anatomyCheckList!=null&&anatomyCheckList.size()>0)
    	{
    		String json = JsonPluginsUtil.beanListToJson(anatomyCheckList,"yyyy-MM-dd HH:mm");
    		writeJson(json);
    	}
    }
    /**
     * 根据studyNo,reqNo获取脏器称重的脏器数据
     */
    public void getVisceraWeightVisceraData()
    {
    	List<Map<String,Object>> visceraWeightList = tblVisceraWeightService.getVisceraWeightVisceraByStudyNoAndReqNo(studyNoPara,model.getReqNo());
    	//if(visceraWeightList!=null&&visceraWeightList.size()>0)
    	//{
    		String json = JsonPluginsUtil.beanListToJson(visceraWeightList,"yyyy-MM-dd HH:mm:ss");
    		writeJson(json);
    	//}
    }
    /**
     * 根据studyNo,reqNo获取脏器称重的数据
     * @return
     */
    public void getVisceraWeightData()
    {
    	if(visceraName==null)
    		visceraName="全部";
    	List<TblVisceraWeight> visceraWeightList = tblVisceraWeightService.getVisceraWeightByStudyNoAndReqNo(studyNoPara,model.getReqNo(),visceraName);
    	Collections.sort(visceraWeightList,new Comparator<TblVisceraWeight>() {

			public int compare(TblVisceraWeight o1, TblVisceraWeight o2) {
				
				return o1.getAnimalCode().compareTo(o2.getAnimalCode());
			}
		});
    	if(visceraWeightList!=null&&visceraWeightList.size()>0)
    	{
    		String json = JsonPluginsUtil.beanListToJson(visceraWeightList,"yyyy-MM-dd HH:mm:ss");
    		writeJson(json);
    	}
    }
    /**
     * 根据studyNo获取脏器称重的交叉表数据
     * @return
     */
    public Map<String,Object> getVisceraWeightColumnsAndRows()
    {
    	if(visceraName==null)
    		visceraName="全部";
    	List<TblVisceraWeight> visceraWeightList = tblVisceraWeightService.getVisceraWeightByStudyNoAndReqNo(studyNoPara,model.getReqNo(),visceraName);
    	List<Columns> columns = new ArrayList<Columns>();
    	Map<String,Map<String,Object>> rows = new HashMap<String, Map<String,Object>>();
    	List<Map<String, Object>> rowsList = new ArrayList<Map<String,Object>>();
    	Columns column = new Columns();
    	column.setTitle("动物编号");
    	column.setField("animalCode");
    	column.setWidth(80);
    	columns.add(column);
    	List<String> tempVisceraList = new ArrayList<String>();
    	if(visceraWeightList!=null&&visceraWeightList.size()>0)
    	{
    		for(TblVisceraWeight visceraWeight:visceraWeightList)
    		{
    			if(!isExistInList(columns,tempVisceraList,visceraWeight.getVisceraName()+"("+visceraWeight.getWeightUnit()+")"))
    			{
    				tempVisceraList.add(visceraWeight.getVisceraName()+"("+visceraWeight.getWeightUnit()+")");	
    			}
    			Map<String,Object> row = rows.get(visceraWeight.getAnimalCode());
    			if(row==null)
    			{
    				 row = new HashMap<String,Object>();
    				 row.put("animalCode",visceraWeight.getAnimalCode());
    				 rows.put(visceraWeight.getAnimalCode(), row);
    			}
    			//String key = visceraWeight.getVisceraName()+"("+visceraWeight.getWeightUnit()+")";
    			String key = visceraWeight.getVisceraName();
    			if(key.contains("("))
    				key = key.substring(0,key.indexOf("("));
    			row.put(key, visceraWeight.getWeight());
    		}

    	}
    	List<Entry<String, Map<String, Object>>> tempForSort = new ArrayList<Entry<String, Map<String, Object>>>();
    	tempForSort.addAll(rows.entrySet());
    	//交叉表称重的rows是动物，按照动物顺序排序
    	Collections.sort(tempForSort,new Comparator<Entry<String, Map<String, Object>>>() {

			public int compare(Entry<String, Map<String, Object>> o1,
					Entry<String, Map<String, Object>> o2) {
				return ((String)o1.getKey()).compareTo((String)o2.getKey());
			}
		});
    	for(String str:tempVisceraList)
    	{
    		Columns column2 = new Columns();
    		column2.setTitle(str);
    		column2.setField(str.substring(0,str.indexOf("(")));
    		column2.setWidth(100);
    		columns.add(column2);
    	}
    	tempVisceraList=null;//释放
    	for(Entry<String, Map<String, Object>> entry : tempForSort)
    	{
    		rowsList.add(entry.getValue());
    	}
    	
    	List<List<Columns>> columnsList = new ArrayList<List<Columns>>();
    	columnsList.add(columns);
    	
    	HashMap<String,Object> map = new HashMap<String,Object>();
    	map.put("columns", columnsList);
    	map.put("rows", rowsList);
    	map.put("success", true);
    	return map;
    }
    public void getVisceraWeightCompareData()
    {
    	Map<String,Object> map = getVisceraWeightColumnsAndRows();
    	String json = JsonPluginsUtil.beanToJson(map);
    	writeJson(json);
    }
    /**
     * 称重数据转存为excel文件
     */
    @SuppressWarnings("unchecked")
    public String weightOutExcel() throws IOException
    {
    	TblAnatomyReq req = tblAnatomyReqService.getById(reqId);
	   Map<String,Object> map = getVisceraWeightColumnsAndRows();
	   List<Map<String, Object>> rowsList = (List<Map<String, Object>>)map.get("rows");
	   List<List<Columns>> columnsList = (List<List<Columns>>)map.get("columns");
	   POIExcelUtil excelUtil = new POIExcelUtil();
		HSSFSheet sheet = excelUtil.newSheet("脏器称重");
		HSSFRow row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("动物编号");
		
		if(columnsList!=null&&!"".equals(columnsList)&&columnsList.size()>0)
		{
			List<Columns> columns = columnsList.get(0);
			for(int i=1;i<columns.size();i++)
			{
				Columns column = columns.get(i);
				row0.createCell(i).setCellValue(column.getTitle().substring(0,column.getTitle().indexOf("(")));
			}
		}
        for(int i=1;i<=rowsList.size();i++)
        {
         // System.out.println("rowList.size is "+rowsList.size());
          Map<String, Object> rowData = rowsList.get(i-1);
   	      HSSFRow row = sheet.createRow(i);
   	      row.createCell(0).setCellValue((String)rowData.get("animalCode"));
          for(int columnNum=1;columnNum<row0.getLastCellNum();columnNum++)
          {
        	  String key = row0.getCell(columnNum).getStringCellValue();
        	  
        	  row.createCell(columnNum).setCellValue((String)rowData.get(key));
        	  
          }
          
        }
 	 	//boolean result = excelUtil.save("脏器称重excel");
 	 	HSSFWorkbook wb = excelUtil.getWb();
 	 	ByteArrayOutputStream fos = new ByteArrayOutputStream();
 	 	wb.write(fos);
 		fileInput =  new ByteArrayInputStream(fos.toByteArray());
 		fos.close();
		fileName = studyNoPara+" "+DateUtil.dateToString(req.getBeginDate(),"yyyy-MM-dd")+" 脏器称重.xls";
		//System.out.println(request.getCharacterEncoding());
		//fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
		fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
	  return "saveExcel";
	  
    }
    /**
     * 
     * @param list
     * @param tempList
     * @param visceraName
     * @return
     */
    public boolean isExistInList(List<Columns> list,List<String> tempList,String visceraName)
    {
    	boolean flag = false;
    	for(Columns column:list)
    	{
    		if(column.getTitle().equals(visceraName))
    		{
    			flag = true;
    			break;
    		}
    	}
    	if(!flag)
    	{
    		for(String temp:tempList)
    			if(temp.equals(visceraName))
    			{
    				flag = true;
    				break;
    			}
    	}
    	return flag;
    }
    /**根据studyNo获取脏器固定的脏器数据
     * 
     */
    public void getVisceraFixedVisceraData()
    {
    	System.out.println("action studyNoPara,reqNo is "+studyNoPara+"===="+model.getReqNo());
    	
    	List<Map<String, Object>> visceraFixedList = tblVisceraFixedService.getVisceraFixedVisceraByStudyNoAndReqNo(studyNoPara,model.getReqNo());
    	String json = JsonPluginsUtil.beanListToJson(visceraFixedList,"yyyy-mm-dd HH:mm:ss");
    	writeJson(json);
    	
    }
    /**
     * 根据studyNo获取脏器固定的数据
     * @return
     */
    public void getVisceraFixedData()
    {
    	System.out.println("action studyNoPara,reqNo is "+studyNoPara+"===="+model.getReqNo());
    	if(visceraName==null)
    		visceraName="全部";
    	List<TblVisceraFixed> visceraFixedList = tblVisceraFixedService.getVisceraFixedByStudyNoAndReqNo(studyNoPara,model.getReqNo(),visceraName);
    	Collections.sort(visceraFixedList,new Comparator<TblVisceraFixed>() {
			public int compare(TblVisceraFixed o1, TblVisceraFixed o2) {
				
				return o1.getAnimalCode().compareTo(o2.getAnimalCode());
			}
		});
    	//if(visceraFixedList!=null&&visceraFixedList.size()>0)
    	//{
    		String json = JsonPluginsUtil.beanListToJson(visceraFixedList,"yyyy-mm-dd HH:mm:ss");
    		writeJson(json);
    	//}
    }
    /**
     * 固定交叉表
     */
    public Map<String,Object> getVisceraFixedColumnsAndRows()
    {
        visceraList = dictVisceraService.findAll();
    	System.out.println("getVisceraFixedColumnsAndRows is "+model.getReqNo());
    	if(visceraName==null)
    		visceraName = "全部";
    	
    	List<Map<String,Object>> mapList2 = tblVisceraFixedService.getNormallessListByStudyNoAndReqNo(studyNoPara,model.getReqNo());//备注
		System.out.println("mapList2 size = "+mapList2.size()); 
		String remarksString ;
		    if(null != mapList2 && mapList2.size() > 0 ){
		    	remarksString = "备注：（";
		    	for(Map<String,Object> mapObj:mapList2){
		    		remarksString = remarksString+(String)mapObj.get("animalCode")+" ";//动物编号
		  			String visceraNameString =  (String)mapObj.get("visceraName");//脏器名称
		  			if(null == visceraNameString ){
		  				visceraName = "";
		  			}else{
		  				remarksString = remarksString+" "+visceraNameString;
		  			}
//		  			String subVisceraName =  (String)mapObj.get("subVisceraName");//子脏器名称
//		  			if(null == subVisceraName ){
//		  				subVisceraName = "";
//		  			}else{
//		  				remarks = remarks+" "+subVisceraName;
//		  			}
		  			String anatomyPos  =  (String)mapObj.get("anatomyPos");//解剖学所见部位60
		  			if(null == anatomyPos ){
		  				anatomyPos = "";
		  			}else{
		  				remarksString = remarksString+""+anatomyPos;
		  			}
		  			String bodySurfacePos =  (String)mapObj.get("bodySurfacePos");//体表部位60
		  			if(null == bodySurfacePos ){
		  				bodySurfacePos = "";
		  			}else{
		  				remarksString = remarksString+""+bodySurfacePos;
		  			}
		  			String anatomyFingding =  (String)mapObj.get("anatomyFingding"); //解剖所见100
		  			if(null == anatomyFingding ){
		  				anatomyFingding = "";
		  			}else{
		  				remarksString = remarksString+""+anatomyFingding;
		  			}
			
		  			remarksString = remarksString+"; ";
		    	}
		    	
		    }else{
		    	remarksString = "备注：";
		    }
    	
    	
    	//List<TblVisceraFixed> visceraFixedList = tblVisceraFixedService.getVisceraFixedByStudyNoAndReqNo(studyNoPara,model.getReqNo(),visceraName);
    	List<List<TblVisceraFixedCompare>> visceraFixedList = tblVisceraFixedService.getVisceraFixedByStudyNoAndReqNo2(studyNoPara,model.getReqNo(),visceraName);
    	
    	List<Columns> columns = new ArrayList<Columns>();
    	Map<String,Map<String,String>> rows = new HashMap<String, Map<String,String>>();
    	List<Map<String, String>> rowsList = new ArrayList<Map<String,String>>();
    	Columns column = new Columns();
    	column.setTitle("脏器");
    	column.setField("visceraName");
    	column.setWidth(80);
    	columns.add(column);
    	List<String> tempVisceraList = new ArrayList<String>();
    	Map<String,String> row0 = new HashMap<String,String>();
    	row0.put("visceraName","完成时间");
    	rows.put("完成时间", row0);
    	String fixedString="";
    	if(visceraFixedList!=null&&visceraFixedList.size()>0)
    	{
    		for(List<TblVisceraFixedCompare> fixedList:visceraFixedList)
    		{
    			String returnValue = "";
    			for(TblVisceraFixedCompare vf:fixedList)
    			{
    				//已固定
					if(vf.get_fixedFlag()!=null&&"Y".equals(vf.get_fixedFlag()))
					{
						returnValue="√";
					}
					 if(vf.get_autolyzaFlag()!=null&&"Y".equals(vf.get_autolyzaFlag()))
					{
						
						if(vf.getSubVisceraName()!=null)
						{//子脏器自溶
							if(!"√".equals(returnValue)){
								returnValue+=vf.getSubVisceraName()+"自溶";
							}
							
						}else
						{
							//主脏器
							returnValue="自溶";
						}
					}
					if(vf.get_missFlag()!=null&&("Y".equals(vf.get_missFlag())||"Y2".equals(vf.get_missFlag())))
					{
						
							if(vf.getSubVisceraName()!=null)
							{
								
								if(vf.getMissingRsn()!=null)
									fixedString+=" "+vf.getAnimalCode()+" "+vf.getSubVisceraName()+vf.get_missType()+":"+vf.getMissingRsn()+";";
								else 
									fixedString+=" "+vf.getAnimalCode()+" "+vf.getSubVisceraName()+vf.get_missType()+";";
								if(!"√".equals(returnValue))
								{
									returnValue+=vf.getSubVisceraName()+vf.get_missType();	
								}
							}else
							{	
								if(vf.getMissingRsn()!=null)
									fixedString+=" "+vf.getAnimalCode()+" "+vf.getVisceraName()+vf.get_missType()+":"+vf.getMissingRsn()+";";
								else 
									fixedString+=" "+vf.getAnimalCode()+" "+vf.getVisceraName()+vf.get_missType()+";";
			
								returnValue=vf.get_missType();
							}
	
					}
		
    			}
    			if(!isExistInList(columns,tempVisceraList,fixedList.get(0).getAnimalCode()))
    			{
    				tempVisceraList.add(fixedList.get(0).getAnimalCode());	
    			}
    			Map<String,String> row = rows.get(fixedList.get(0).getVisceraName());
    			if(row==null)
    			{
    				 row = new HashMap<String,String>();
    				 row.put("visceraName",fixedList.get(0).getVisceraName());
    				 rows.put(fixedList.get(0).getVisceraName(), row);
    			}
    			if(row0.get(fixedList.get(0).getAnimalCode())==null)
    			{
    				//fixed.getOperateTime()是tblAnatomyAnimal中的固定完成时间。
    				row0.put(fixedList.get(0).getAnimalCode(), DateUtil.dateToString(fixedList.get(0).getOperateTime(),"HH:mm"));
    			}
    			row.put(fixedList.get(0).getAnimalCode(), returnValue);
    		}

    	}
    	if(fixedString!=null&&!"".equals(fixedString))
    	{
    		if("备注：".equals(remarksString))
    			remarksString = "备注：（";
    		remarksString = remarksString+fixedString+ "）";
    	}else {
    		if("备注：".equals(remarksString))
    			remarksString = remarksString+ "NA";
		}
    	
    	for(String str:tempVisceraList)
    	{
    		Columns column2 = new Columns();
        	column2.setTitle(str);
        	column2.setField(str);
        	column2.setWidth(80);
        	columns.add(column2);
        	
    	}
    	tempVisceraList=null;//释放
    	
    	for(Entry<String, Map<String, String>> entry:rows.entrySet())
    	{
    		rowsList.add(entry.getValue());
    	}
    	Collections.sort(rowsList,new Comparator<Map<String, String>>() {
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				
				String visceraNameString = o1.get("visceraName");
				String visceraNameString2 = o2.get("visceraName");
				int snFixed=0,snFixed2=0;
				if(visceraNameString.equals("完成时间"))
					return -1;
				else if(visceraNameString2.equals("完成时间"))
					return 1;
				else{
					for(DictViscera viscera:visceraList)
					{
						if(viscera.getVisceraName().equals(visceraNameString))
						{
							snFixed = viscera.getSnFixed();
						}
						if(viscera.getVisceraName().equals(visceraNameString2))
						{
							snFixed2 = viscera.getSnFixed();
						}
						if(snFixed!=0&&snFixed2!=0)
							break;
					}
				}
				return snFixed-snFixed2;
			}
		});
    	//脏器固定的列是动物，按照动物的animalCode排序
    	Collections.sort(columns,new Comparator<Columns>() {
			public int compare(Columns o1, Columns o2) {
				if("脏器".equals(o1.getTitle()))
					return -1;
				else
					return (o1.getTitle()).compareTo(o2.getTitle());
			}
		});
    	List<List<Columns>> columnsList = new ArrayList<List<Columns>>();
    	columnsList.add(columns);
    	
    	
	  		
    	
    	HashMap<String,Object> map = new HashMap<String,Object>();
    	map.put("columns", columnsList);
    	map.put("rows", rowsList);
    	map.put("remark",remarksString);
    	map.put("success", true);
    	columns = null;
    	rows = null;
    	
    	return map;
    	
    }
    public void getVisceraFixedCompareData()
    {
    	Map<String,Object> map = getVisceraFixedColumnsAndRows();
    	String json = JsonPluginsUtil.beanToJson(map);
    	writeJson(json);
    }
    /**
     * 固定数据转出为excel
     */
    @SuppressWarnings("unchecked")
    public String fixedOutExcel() throws IOException
    {
    	TblAnatomyReq req = tblAnatomyReqService.getById(reqId);
    	Map<String,Object> map = getVisceraFixedColumnsAndRows();
    	List<Map<String, String>> rowsList = (List<Map<String, String>>)map.get("rows");
  	    List<List<Columns>> columnsList = (List<List<Columns>>)map.get("columns");
  	   
  	    POIExcelUtil excelUtil = new POIExcelUtil();
		HSSFSheet sheet = excelUtil.newSheet("脏器固定");
		HSSFRow row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("脏器");
		
		if(columnsList!=null&&!"".equals(columnsList)&&columnsList.size()>0)
		{
			List<Columns> columns = columnsList.get(0);
			for(int i=1;i<columns.size();i++)
			{
				Columns column = columns.get(i);
				row0.createCell(i).setCellValue(column.getTitle());
			}
		}
      for(int i=1;i<=rowsList.size();i++)
      {
        Map<String, String> rowData = rowsList.get(i-1);
 	      HSSFRow row = sheet.createRow(i);
 	      row.createCell(0).setCellValue((String)rowData.get("visceraName"));
        for(int columnNum=1;columnNum<row0.getLastCellNum();columnNum++)
        {
      	  String key = row0.getCell(columnNum).getStringCellValue();
      	  row.createCell(columnNum).setCellValue((String)rowData.get(key));
        }
        
      }
      //备注
  		HSSFRow remarkRow = sheet.createRow(sheet.getLastRowNum()+1);
  		HSSFCell remarkCell = remarkRow.createCell(0);
  		//remarkCell.setCellNum((short)columnsList.size());
  		System.out.println("remark=============="+remark);
  		remarkCell.setCellValue(remark);
    
	/*  boolean result = excelUtil.save("脏器固定.xls");
	  Map<String, Object> map2 = new HashMap<String, Object>();
	  map2.put("success", result);
	  writeJson(JsonPluginsUtil.beanToJson(map2));*/
      HSSFWorkbook wb = excelUtil.getWb();
	 	ByteArrayOutputStream fos = new ByteArrayOutputStream();
	 	wb.write(fos);
		fileInput =  new ByteArrayInputStream(fos.toByteArray());
		fos.close();
		
		fileName = studyNoPara+" "+DateUtil.dateToString(req.getBeginDate(),"yyyy-MM-dd")+" 脏器固定.xls";
		System.out.println(request.getCharacterEncoding());
		//fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
		fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
		
		return "saveExcel";
    }
    /*转到报表页面*/
	public String toReport(){
		return "report";
	}
	
	/**
	 * 生成报表文件
	 */
	public String loadReport(){
		String studyNo = "";
		String studyName = "";
		String anatomyRsn = "";
		String animalType = "";
		String testItem = "";
		String testPhase = "";
		String anatomyDate = "";
		String number = "";
		
		String dosageUnit = "";
		
		URL logoImage = null;
		URL subReportURL = null;
		//主报表
		paraMap = new HashMap<String, Object>();//参数列表
		number = dictReportNumberService.getNumberByReportName("病理检查申请单（二）");
		logoImage = this.getClass().getResource("logo.jpg");
		subReportURL = this.getClass().getResource("PathReq_viscera.jasper");
		studyNo = studyNoPara;
		
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getByStudyNo(studyNo);
//		int animalCodeMode = 1;
//		if( 2 == tblStudyPlan.getAnimalCodeMode()){
//			animalCodeMode = 2;
//		}
		
		if(null != tblStudyPlan){
//			studyName = tblStudyPlan.getStudyName();
			TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(StringUtil.studyNoRemoveFN(tblStudyPlan.getStudyNo()));
			if(null != studyItem){
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType ;
				try{
					dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
					if(null == dictStudyType){
						dictStudyType = dictStudyTypeService.getByStudyNametiNo(studyItem.getStudyName(),tiNo);
					}
				}catch(Exception e){
					dictStudyType = null ;
				}
				if(null != dictStudyType && dictStudyType.getAnimalHave() == 1){
					studyName = testItemName+tblStudyPlan.getAnimalType()+studyItem.getStudyName();
				}else{
					studyName = testItemName+studyItem.getStudyName();
				}
			}else{
				studyName = tblStudyPlan.getStudyName();
			}
			
			
			dosageUnit = tblStudyPlan.getDosageUnit();
			
			animalType = tblStudyPlan.getAnimalType();
	        String animalStrain = tblStudyPlan.getAnimalStrain();
	        if(null != animalStrain || !"".equals(animalStrain)){
	        	if(!animalStrain.contains(animalType)){
	        		animalType = animalType +" "+animalStrain;
	        	}else{
	        		animalType = animalStrain;
	        	}
	        }
		}
		
		//非历史
		if(printHis != 1){
			Integer anatomyReqNo = 0;
			TblAnatomyReq tblAnatomyReq = tblAnatomyReqService.getById(reqId);
			if(null != tblAnatomyReq){
				
				anatomyReqNo = tblAnatomyReq.getReqNo();
				Integer anatomyRsnInt = tblAnatomyReq.getAnatomyRsn();
				if(null != anatomyRsnInt){
					switch (anatomyRsnInt) {
					case 1:
						anatomyRsn = "计划解剖";
						break;
					case 2:
						anatomyRsn = "濒死解剖";
						break;
					case 3:
						anatomyRsn = "死亡解剖";
						break;
					case 4:
						anatomyRsn = "安乐死解剖";
						break;
					default:
						break;
					}
				}
				testPhase = tblAnatomyReq.getTestPhase();
				Date beginDate = tblAnatomyReq.getBeginDate();
				Date endDate = tblAnatomyReq.getEndDate();
				if(null != beginDate && null != endDate){
					if(beginDate.equals(endDate)){
						anatomyDate = DateUtil.dateToString(beginDate, "yyyy-MM-dd");
					}else{
						anatomyDate = DateUtil.dateToString(beginDate, "yyyy-MM-dd")+"～"+
						DateUtil.dateToString(endDate, "yyyy-MM-dd");
					}
				}
				
//			Integer anatomyCheckFlag = tblAnatomyReq.getAnatomyCheckFlag();  //需剖检标识
				Integer visceraWeighFlag = tblAnatomyReq.getVisceraWeighFlag(); //需脏器称重标识
				Integer visceraFixedFlag = tblAnatomyReq.getVisceraFixedFlag();  //需脏器固定标识
				Integer histopathCheckFlag = tblAnatomyReq.getHistopathCheckFlag(); //需镜检标识
//			if(null != anatomyCheckFlag && anatomyCheckFlag == 1){
				testItem = "剖检";
//			}
				if(null != visceraWeighFlag && visceraWeighFlag == 1){
					testItem =testItem+ "、脏器称重";
				}
				if(null != visceraFixedFlag && visceraFixedFlag == 1){
					testItem = testItem+ "、脏器固定";
				}
				if(null != histopathCheckFlag && histopathCheckFlag == 1){
					testItem =testItem+  "、组织病理学检查";
				}
				if(null != tblStudyPlan){
					int abnVisceraHistopathCheckFlag = tblStudyPlan.getAbnVisceraHistopathCheckFlag();
					if(abnVisceraHistopathCheckFlag == 1){
						testItem = testItem+"、对肉眼观察异常器官进行组织病理学检查";
					}
				}
			}
			
			paraMap.put("number", number == null ? "":number);
			paraMap.put("logoImage", logoImage);
			paraMap.put("studyNo", StringUtil.studyNoRemoveFN(studyNo));
			paraMap.put("studyName", studyName);
			paraMap.put("anatomyRsn", anatomyRsn);
			paraMap.put("animalType", animalType);
			paraMap.put("testItem", testItem);
			paraMap.put("testPhase", testPhase);
			paraMap.put("anatomyDate", anatomyDate);
			
			sourceList = new ArrayList<Map<String,Object>>();
			//map (animal.groupID,animal.animalCode,animal.gender,dose.dosage,dose.femaleNum, 1 as endFlag)
//		sourceList = tblAnatomyReqService.getMapListByStudyNoReqNo(studyNo,reqNo);
			//剂量组列表，根据专题和申请时间获取剂量组设置列表
			List<TblDoseSetting> tblDoseSettingList = tblDoseSettingService.getListByStudyNoAndDate(studyNo,tblAnatomyReq.getCreateTime());
			if(null != tblDoseSettingList){
				List<TblAnatomyReqAnimalList> animalList = tblAnatomyReqAnimalListService.getListByStudyNoAndReqNo(studyNo,anatomyReqNo);
				if(null != animalList){
					for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
						int group = tblDoseSetting.getDosageNum();
//					if(animalCodeMode == 2){
//						group = group -1;
//					}
						int maleNum = 0;//该组雄性动   物数
						int femaleNum = 0;//该组雌性动   物数
						for(TblAnatomyReqAnimalList animal:animalList){
							if(group == animal.getGroupID()){
								if(animal.getGender() == 1){
									maleNum++;
								}else{
									femaleNum++;
								}
								
							}
						}
						//准备剂量
						String dosage = tblDoseSetting.getDosage();
						String femaleDosage = tblDoseSetting.getFemaleDosage();
						if(null != femaleDosage && !"".equals(femaleDosage)){
							dosage = "♂ "+dosage+" "+dosageUnit+"♀ "+femaleDosage+" "+dosageUnit;
						}else{
							dosage = dosage+" "+dosageUnit;
						}
						//添加数据
						Map<String,Object> map = null;
						for(TblAnatomyReqAnimalList animal:animalList){
							if(group == animal.getGroupID()){
								map = new HashMap<String,Object>();
								map.put("group", group);
								map.put("groupDesc", tblDoseSetting.getDosageDesc());
								map.put("dosage", dosage);
								map.put("maleNum",maleNum);
								map.put("femaleNum",femaleNum);
								map.put("animalCode",animal.getAnimalCode());
								map.put("endFlag",0);
								sourceList.add(map);
							}
						}
						//计算是否是  6 的整数倍，不是补齐
						if( 0 != (maleNum+femaleNum)%6){
							int count = 6 - (maleNum+femaleNum)%6;
							for(int i = 0;i < count;i++){
								map = new HashMap<String,Object>();
								map.put("group", group);
								map.put("groupDesc", tblDoseSetting.getDosageDesc());
								map.put("dosage", dosage);
								map.put("maleNum",maleNum);
								map.put("femaleNum",femaleNum);
								map.put("animalCode","-");
								map.put("endFlag",0);
								sourceList.add(map);
							}
						}
					}
				}
			}
			
//		for(int i = 0;i<5;i++){
//			for(int j = 0;j<36;j++){
//				Map<String,Object> map1 = new HashMap<String,Object>();
//				map1.put("group", i+1+"");
//				map1.put("dosage", i+1+"");
//				map1.put("maleNum", i+1+"");
//				map1.put("femaleNum", i+1+"");
//				map1.put("animalCode", i+"10"+j);
//				map1.put("endFlag",0);
//				sourceList.add(map1);
//			}
//		}
			fileName = "AnatomyReq"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
			
			//脏器子报表
			Map<String,Object> subMap = new HashMap<String,Object>();
			subMap.put("number_sub", number == null ? "":number);
			subMap.put("logoImage_sub", logoImage);
			subMap.put("studyNo_sub", StringUtil.studyNoRemoveFN(studyNo));
			subMap.put("studyName_sub", studyName);
			subMap.put("anatomyRsn_sub", anatomyRsn);
			subMap.put("animalType_sub", animalType);
			subMap.put("testItem_sub", testItem);
			subMap.put("taskPhase_sub", testPhase);
			subMap.put("anatomyDate_sub", anatomyDate);
			
			List<Map<String,Object>> subSourceList = new ArrayList<Map<String,Object>>();
			
			List<TblAnatomyReqPathPlanCheck> tblAnatomyReqPathPlanChecklist = tblAnatomyReqPathPlanCheckService.getListByStudyNoAndReqNo(studyNo,anatomyReqNo);
			if(null != tblAnatomyReqPathPlanChecklist){
//			 private int atanomyCheckFlag;  //是否需要剖检
//			    private int visceraFixedFlag;   //是否需要固定
//			    private int histopathCheckFlag; //是否需要镜检
				
				Map<String,Object> map  = null;
				int anatomyCount = 0;
				int fixedCount = 0;
				int hisCount = 0;
				for(TblAnatomyReqPathPlanCheck obj:tblAnatomyReqPathPlanChecklist){
					int atanomyCheckFlag = obj.getAtanomyCheckFlag();
					int visceraFixedFlag = obj.getVisceraFixedFlag();
					int histopathCheckFlag = obj.getHistopathCheckFlag();
					if(atanomyCheckFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",1);
						map.put("pathType","需剖检的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						anatomyCount++;
					}
					if(visceraFixedFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",2);
						map.put("pathType","需固定的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						fixedCount++;
					}
					if(histopathCheckFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",3);
						map.put("pathType","需组织病理学检查的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						hisCount++;
					}
				}
				
				if(0 != anatomyCount%5){
					int count = 5 - anatomyCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",1);
						map.put("pathType","需剖检的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				if(0 != fixedCount%5){
					int count = 5 - fixedCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",2);
						map.put("pathType","需固定的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				if(0 != hisCount%5){
					int count = 5 - hisCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",3);
						map.put("pathType","需组织病理学检查的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				
				
				
			}
			List<TblAnatomyReqVisceraWeigh> tblAnatomyReqVisceraWeighList = tblAnatomyReqVisceraWeighService.getListByStudyAndReqNo(studyNo,anatomyReqNo);
			if(null != tblAnatomyReqVisceraWeighList){
				int weightCount = 0;
				Map<String,Object> map  = null;
				for(TblAnatomyReqVisceraWeigh obj:tblAnatomyReqVisceraWeighList){
					
					map = new HashMap<String,Object>();
					map.put("group",4);
					map.put("pathType","需称重的脏器");
					if(obj.getAttachedVisceraFlag()==1)
					{
						String attachedNames = tblAnatomyReqAttachedVisceraService.getAttachedVisceraNamesByPid(obj.getId());
						map.put("visceraName",obj.getVisceraName()+"("+attachedNames+")");
					}else {
						map.put("visceraName",obj.getVisceraName());
					}
					subSourceList.add(map);
					weightCount++;
				}
				if(0 != weightCount%5){
					int count = 5 - weightCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",4);
						map.put("pathType","需称重的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
			}
			Collections.sort(subSourceList, new Comparator<Map<String,Object>>(){
				
				public int compare(Map<String, Object> map1,
						Map<String, Object> map2) {
					return (Integer)map1.get("group") - (Integer)map2.get("group");
				}
			});
//		for(int i = 0;i<4;i++){
//			for(int j = 0;j<15;j++){
//				Map<String,Object> map1 = new HashMap<String,Object>();
//				map1.put("group",4);
//				map1.put("pathType", i+1+"");
//				map1.put("visceraName","60"+j*(i+1));
//				subSourceList.add(map1);
//			}
//		}
			
			paraMap.put("subReportURL", subReportURL);
			paraMap.put("subMap", subMap);
			paraMap.put("subSourceList", new JRBeanCollectionDataSource(subSourceList));
		}else{
			Integer anatomyReqNo = 0;
			TblAnatomyReqHis tblAnatomyReqHis = tblAnatomyReqService.getByHisId(reqId);
			if(null != tblAnatomyReqHis){
				
				anatomyReqNo = tblAnatomyReqHis.getReqNo();
				Integer anatomyRsnInt = tblAnatomyReqHis.getAnatomyRsn();
				if(null != anatomyRsnInt){
					switch (anatomyRsnInt) {
					case 1:
						anatomyRsn = "计划解剖";
						break;
					case 2:
						anatomyRsn = "濒死解剖";
						break;
					case 3:
						anatomyRsn = "死亡解剖";
						break;
					case 4:
						anatomyRsn = "安乐死解剖";
						break;
					default:
						break;
					}
				}
				testPhase = tblAnatomyReqHis.getTestPhase();
				Date beginDate = tblAnatomyReqHis.getBeginDate();
				Date endDate = tblAnatomyReqHis.getEndDate();
				if(null != beginDate && null != endDate){
					if(beginDate.equals(endDate)){
						anatomyDate = DateUtil.dateToString(beginDate, "yyyy-MM-dd");
					}else{
						anatomyDate = DateUtil.dateToString(beginDate, "yyyy-MM-dd")+"～"+
						DateUtil.dateToString(endDate, "yyyy-MM-dd");
					}
				}
				
				//			Integer anatomyCheckFlag = tblAnatomyReq.getAnatomyCheckFlag();  //需剖检标识
				Integer visceraWeighFlag = tblAnatomyReqHis.getVisceraWeighFlag(); //需脏器称重标识
				Integer visceraFixedFlag = tblAnatomyReqHis.getVisceraFixedFlag();  //需脏器固定标识
				Integer histopathCheckFlag = tblAnatomyReqHis.getHistopathCheckFlag(); //需镜检标识
				testItem = "剖检";
				if(null != visceraWeighFlag && visceraWeighFlag == 1){
					testItem =testItem+ "、脏器称重";
				}
				if(null != visceraFixedFlag && visceraFixedFlag == 1){
					testItem = testItem+ "、脏器固定";
				}
				if(null != histopathCheckFlag && histopathCheckFlag == 1){
					testItem =testItem+  "、组织病理学检查";
				}
				if(null != tblStudyPlan){
					int abnVisceraHistopathCheckFlag = tblStudyPlan.getAbnVisceraHistopathCheckFlag();
					if(abnVisceraHistopathCheckFlag == 1){
						testItem = testItem+"、对肉眼观察异常器官进行组织病理学检查";
					}
				}
			}
			
			paraMap.put("number", number == null ? "":number);
			paraMap.put("logoImage", logoImage);
			paraMap.put("studyNo", StringUtil.studyNoRemoveFN(studyNo));
			paraMap.put("studyName", studyName);
			paraMap.put("anatomyRsn", anatomyRsn);
			paraMap.put("animalType", animalType);
			paraMap.put("testItem", testItem);
			paraMap.put("testPhase", testPhase);
			paraMap.put("anatomyDate", anatomyDate);
			
			sourceList = new ArrayList<Map<String,Object>>();
			//map (animal.groupID,animal.animalCode,animal.gender,dose.dosage,dose.femaleNum, 1 as endFlag)
			//剂量组列表
			List<TblDoseSetting> tblDoseSettingList = tblDoseSettingService.getListByStudyNoAndDate(studyNo, tblAnatomyReqHis.getCreateTime());
			if(null != tblDoseSettingList){
				List<TblAnatomyReqAnimalListHis> animalHisList = tblAnatomyReqAnimalListService.getHisListByStudyNoAndReqNo(studyNo,anatomyReqNo);
				if(null != animalHisList){
					for(TblDoseSetting tblDoseSetting:tblDoseSettingList){
						int group = tblDoseSetting.getDosageNum();
						int maleNum = 0;//该组雄性动   物数
						int femaleNum = 0;//该组雌性动   物数
						for(TblAnatomyReqAnimalListHis animal:animalHisList){
							if(group == animal.getGroupID()){
								if(animal.getGender() == 1){
									maleNum++;
								}else{
									femaleNum++;
								}
								
							}
						}
						//准备剂量
						String dosage = tblDoseSetting.getDosage();
						String femaleDosage = tblDoseSetting.getFemaleDosage();
						if(null != femaleDosage && !"".equals(femaleDosage)){
							dosage = "♂ "+dosage+" "+dosageUnit+"♀ "+femaleDosage+" "+dosageUnit;
						}else{
							dosage = dosage+" "+dosageUnit;
						}
						//添加数据
						Map<String,Object> map = null;
						for(TblAnatomyReqAnimalListHis animal:animalHisList){
							if(group == animal.getGroupID()){
								map = new HashMap<String,Object>();
								map.put("group", group);
								map.put("groupDesc", tblDoseSetting.getDosageDesc());
								map.put("dosage", dosage);
								map.put("maleNum",maleNum);
								map.put("femaleNum",femaleNum);
								map.put("animalCode",animal.getAnimalCode());
								map.put("endFlag",0);
								sourceList.add(map);
							}
						}
						//计算是否是  6 的整数倍，不是补齐
						if( 0 != (maleNum+femaleNum)%6){
							int count = 6 - (maleNum+femaleNum)%6;
							for(int i = 0;i < count;i++){
								map = new HashMap<String,Object>();
								map.put("group", group);
								map.put("groupDesc", tblDoseSetting.getDosageDesc());
								map.put("dosage", dosage);
								map.put("maleNum",maleNum);
								map.put("femaleNum",femaleNum);
								map.put("animalCode","-");
								map.put("endFlag",0);
								sourceList.add(map);
							}
						}
					}
				}
			}
			
			fileName = "AnatomyReq"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
			
			//脏器子报表
			Map<String,Object> subMap = new HashMap<String,Object>();
			subMap.put("number_sub", number == null ? "":number);
			subMap.put("logoImage_sub", logoImage);
			subMap.put("studyNo_sub", StringUtil.studyNoRemoveFN(studyNo));
			subMap.put("studyName_sub", studyName);
			subMap.put("anatomyRsn_sub", anatomyRsn);
			subMap.put("animalType_sub", animalType);
			subMap.put("testItem_sub", testItem);
			subMap.put("taskPhase_sub", testPhase);
			subMap.put("anatomyDate_sub", anatomyDate);
			
			List<Map<String,Object>> subSourceList = new ArrayList<Map<String,Object>>();
			
			List<TblAnatomyReqPathPlanCheckHis> tblAnatomyReqPathPlanCheckHislist = tblAnatomyReqPathPlanCheckService.getHisListByStudyNoAndReqNo(studyNo,anatomyReqNo);
			if(null != tblAnatomyReqPathPlanCheckHislist){
//			 private int atanomyCheckFlag;  //是否需要剖检
//			    private int visceraFixedFlag;   //是否需要固定
//			    private int histopathCheckFlag; //是否需要镜检
				
				Map<String,Object> map  = null;
				int anatomyCount = 0;
				int fixedCount = 0;
				int hisCount = 0;
				for(TblAnatomyReqPathPlanCheckHis obj:tblAnatomyReqPathPlanCheckHislist){
					int atanomyCheckFlag = obj.getAtanomyCheckFlag();
					int visceraFixedFlag = obj.getVisceraFixedFlag();
					int histopathCheckFlag = obj.getHistopathCheckFlag();
					if(atanomyCheckFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",1);
						map.put("pathType","需剖检的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						anatomyCount++;
					}
					if(visceraFixedFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",2);
						map.put("pathType","需固定的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						fixedCount++;
					}
					if(histopathCheckFlag == 1){
						map = new HashMap<String,Object>();
						map.put("group",3);
						map.put("pathType","需组织病理学检查的脏器");
						map.put("visceraName",obj.getVisceraName());
						subSourceList.add(map);
						hisCount++;
					}
				}
				
				if(0 != anatomyCount%5){
					int count = 5 - anatomyCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",1);
						map.put("pathType","需剖检的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				if(0 != fixedCount%5){
					int count = 5 - fixedCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",2);
						map.put("pathType","需固定的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				if(0 != hisCount%5){
					int count = 5 - hisCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",3);
						map.put("pathType","需组织病理学检查的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
				
				
				
			}
			List<TblAnatomyReqVisceraWeighHis> tblAnatomyReqVisceraWeighHisList = tblAnatomyReqVisceraWeighService.getHisListByStudyAndReqNo(studyNo,anatomyReqNo);
			if(null != tblAnatomyReqVisceraWeighHisList){
				int weightCount = 0;
				Map<String,Object> map  = null;
				for(TblAnatomyReqVisceraWeighHis obj:tblAnatomyReqVisceraWeighHisList){
					
					map = new HashMap<String,Object>();
					map.put("group",4);
					map.put("pathType","需称重的脏器");
					if(obj.getAttachedVisceraFlag()==1)
					{
						String attachedNames = tblAnatomyReqAttachedVisceraService.getAttachedVisceraNamesByPid(obj.getId());
						map.put("visceraName",obj.getVisceraName()+"("+attachedNames+")");
					}else {
						map.put("visceraName",obj.getVisceraName());
					}
					subSourceList.add(map);
					weightCount++;
				}
				if(0 != weightCount%5){
					int count = 5 - weightCount%5;
					for(int i = 0;i < count ;i++){
						map = new HashMap<String,Object>();
						map.put("group",4);
						map.put("pathType","需称重的脏器");
						map.put("visceraName","-");
						subSourceList.add(map);
					}
				}
			}
			Collections.sort(subSourceList, new Comparator<Map<String,Object>>(){
				
				public int compare(Map<String, Object> map1,
						Map<String, Object> map2) {
					return (Integer)map1.get("group") - (Integer)map2.get("group");
				}
			});
			
			paraMap.put("subReportURL", subReportURL);
			paraMap.put("subMap", subMap);
			paraMap.put("subSourceList", new JRBeanCollectionDataSource(subSourceList));
		}
		return "loadReport";
	}
    
    
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}
//	public void setPathDirector(String pathDirector) {
//		this.pathDirector = pathDirector;
//	}
//	public String getPathDirector() {
//		return pathDirector;
//	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAddOrEdit(String addOrEdit) {
		this.addOrEdit = addOrEdit;
	}
	public String getAddOrEdit() {
		return addOrEdit;
	}
	public void setAnimalCodes(String animalCodes) {
		this.animalCodes = animalCodes;
	}
	public String getAnimalCodes() {
		return animalCodes;
	}
	public String getVisceraNames() {
		return visceraNames;
	}
	public void setVisceraNames(String visceraNames) {
		this.visceraNames = visceraNames;
	}
	public String getAtanomyCheckFlags() {
		return atanomyCheckFlags;
	}
	public void setAtanomyCheckFlags(String atanomyCheckFlags) {
		this.atanomyCheckFlags = atanomyCheckFlags;
	}
	public String getHistopathCheckFlags() {
		return histopathCheckFlags;
	}
	public void setHistopathCheckFlags(String histopathCheckFlags) {
		this.histopathCheckFlags = histopathCheckFlags;
	}
	public String getVisceraNames1() {
		return visceraNames1;
	}
	public void setVisceraNames1(String visceraNames1) {
		this.visceraNames1 = visceraNames1;
	}
	public String getPartVisceraSeparateWeighs() {
		return partVisceraSeparateWeighs;
	}
	public void setPartVisceraSeparateWeighs(String partVisceraSeparateWeighs) {
		this.partVisceraSeparateWeighs = partVisceraSeparateWeighs;
	}
	public String getFixedWeighFlags() {
		return fixedWeighFlags;
	}
	public void setFixedWeighFlags(String fixedWeighFlags) {
		this.fixedWeighFlags = fixedWeighFlags;
	}
	public String getAttachedVisceras() {
		return attachedVisceras;
	}
	public void setAttachedVisceras(String attachedVisceras) {
		this.attachedVisceras = attachedVisceras;
	}
	public void setGenders(String genders) {
		this.genders = genders;
	}
	public String getGenders() {
		return genders;
	}
//	public void setReqNo(Integer reqNo) {
//		this.reqNo = reqNo;
//	}
//	public Integer getReqNo() {
//		return reqNo;
//	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getReqId() {
		return reqId;
	}
	/*public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}*/
	public List<Map<String, Object>> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<Map<String, Object>> sourceList) {
		this.sourceList = sourceList;
	}
	public Map<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getToWhere() {
		return toWhere;
	}
	public void setToWhere(String toWhere) {
		this.toWhere = toWhere;
	}
	public String getVisceraFixedFlags() {
		return visceraFixedFlags;
	}
	public void setVisceraFixedFlags(String visceraFixedFlags) {
		this.visceraFixedFlags = visceraFixedFlags;
	}
	public String getVisceraName() {
		return visceraName;
	}
	public void setVisceraName(String visceraName) {
		this.visceraName = visceraName;
	}
	public String getFinding() {
		return finding;
	}
	public void setFinding(String finding) {
		this.finding = finding;
	}
	public InputStream getFileInput() {
		return fileInput;
	}
	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPrintHis() {
		return printHis;
	}
	public void setPrintHis(int printHis) {
		this.printHis = printHis;
	}
	
	
}
