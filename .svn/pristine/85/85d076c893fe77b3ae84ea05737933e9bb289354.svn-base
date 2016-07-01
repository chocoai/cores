package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.jsonAndModel.JsonUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblResManager;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.schedule.TblTaskTypeLeader;
import com.lanen.model.studyplan.DictAnimalStrain;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.model.studyplan.DictDoseUnit;
import com.lanen.model.studyplan.DictStudyTestIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblDoseSettingVersion;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.service.clinicaltest.GetIdService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.schdeule.TblStudyInfoService;
import com.lanen.service.schdeule.TblTaskTypeLeaderService;
import com.lanen.service.studyplan.DictAnimalStrainService;
import com.lanen.service.studyplan.DictAnimalTypeService;
import com.lanen.service.studyplan.DictDoseUnitService;
import com.lanen.service.studyplan.DictStudyTestIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblAnimalService;
import com.lanen.service.studyplan.TblApplyReviseService;
import com.lanen.service.studyplan.TblDissectPlanService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblDoseSettingVersionService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.service.studyplan.TblTestIndexPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblStudyPlanAction extends BaseAction<TblStudyPlan> {

	/**
	 * 序列
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 试验计划Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 课题计划检验指标Service
	 */
	@Resource
	private TblTestIndexPlanService tblTestIndexPlanService;
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	
	/**
	 * 剂量设置Service
	 */
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	@Resource
	private TblDoseSettingVersionService tblDoseSettingVersionService;
	
	//任务类型负责人
	@Resource
	private TblTaskTypeLeaderService tblTaskTypeLeaderService;
	
	@Resource
	private TblStudyInfoService tblStudyInfoService;
	
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	@Resource
	private TblResManagerService tblResManagerService;
	
	/**
	 * 解剖计划Service
	 */
	@Resource
	private TblDissectPlanService tblDissectPlanService;
	
	/**
	 * 动物信息Service
	 */
	@Resource
	private TblAnimalService tblAnimalService;
	
	/**
	 * 获取主键Service
	 */
	@Resource
	private GetIdService getIdService;
	
	/**
	 * 签名连接Service
	 */
	@Resource
	private TblESLinkService tblESLinkService;
	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;
	/**
	 * 课题类别
	 */
	@Resource
	private  DictStudyTypeService dictStudyTypeService;
	/**
	 * 电子签名Service
	 */
	@Resource
	private TblESService tblESService;
	
	/**
	 * 字典-课题缺省检验指标
	 */
	@Resource
	private DictStudyTestIndexService dictStudyTestIndexService;
	
	/**
	 * 字典-动物种类
	 */
	@Resource
	private DictAnimalTypeService dictAnimalTypeService;
	
	/**
	 * 字典-动物品系
	 */
	@Resource
	private DictAnimalStrainService dictAnimalStrainService;
	private String animalTypeId;
	
	/**试验项目（委托项目）service*/
	@Resource
	private TblStudyItemService tblStudyItemService;
	
	/**剂量单位service*/
	@Resource
	private DictDoseUnitService dictDoseUnitService;
	
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	@Resource
	private QAStudyChkIndexService qAStudyChkIndexService;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	
	private Date doseEffectiveDate;

	
	//课题编号（新的）
	private String newStudyNo;
	//老的  ul li   ,不用刷新
	private String oldUlLi;
	/**
	 * 试验计划信息
	 */
	private TblStudyPlan tblStudyPlan;
	
	/**
	 * 课题类别待选内容
	 */
	private Map<String, String> studyTypeMap;
	
	/**
	 * 课题类别已选内容
	 */
	private String studyTypeSel;
	/**
	 * 课题类别编号
	 */
	private String studyTypeCode;
	/**
	 * 复制到的课题编号
	 */
	private String sourceStudyPlanNo;
	/**
	 * 用户密码
	 */
	private String password;
	//动物种类名称
	private String typeName;
	
	//标记跳转的页面（在studyPlanMain中起作用）
	private String toWhere;
	
	//课题成员标记
	private String member;
	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	
	//选择日程的开始日期参数
	private boolean flag;
	private	String  dateOld;
	private	String  dateNew; 
	
	private Integer continueFlag;//0只检查专题基本信息，1只检查日程信息 2两个都检查
	/**
	 * 试验计划主界面
	 * @return
	 */
	public String studyPlanMain() {
		//是否动物Id签字1:已签   0未签
		int isAnimalIdES = tblESLinkService.isESLink("TblAnimal",studyNoPara,5);
		
		//根据课题编号获得试验计划的信息
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		ActionContext.getContext().getValueStack().push(tblStudyPlan);
		String state = tblStudyPlan.getStudyState();
		String hasAnimal = "0";//0:无动物，1：有动物
		if(null != tblStudyPlan.getAnimalType() && !"".equals(tblStudyPlan.getAnimalType().trim())){
			hasAnimal = "1";
		}
		if( "1".equals(state)){
			String Animalsign =tblAnimalService.getAnimalsListForStudyNo(tblStudyPlan);
			if( Animalsign != null && Animalsign.equals("add")){
				//签名链接
				TblESLink esLink = new TblESLink();
				TblESLink esLink1 = new TblESLink();
				//电子签名
				TblES es = new TblES();
				TblES es1 = new TblES();
				User tempUser = (User) ActionContext.getContext().getSession().get("user");
				es.setSigner(tempUser.getRealName());
		        es.setEsType(5);
		        es.setEsTypeDesc("动物信息表动物Id号自动录入");
		        es.setDateTime(new Date());
				es.setEsId(tblESService.getKey("TblES"));
				es1.setSigner(tempUser.getRealName());
		        es1.setEsType(6);
		        es1.setEsTypeDesc("动物信息表动物编号自动录入");
		        es1.setDateTime(new Date());
				es1.setEsId(tblESService.getKey("TblES"));
		        esLink.setTableName("TblAnimal");
				esLink.setDataId(studyNoPara);
				esLink.setTblES(es);
				esLink.setEsType(5);
		        esLink.setEsTypeDesc("动物信息表动物Id号自动录入");
		        esLink.setRecordTime(new Date());
				esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
				esLink1.setTableName("TblAnimal");
				esLink1.setDataId(studyNoPara);
				esLink1.setTblES(es1);
				esLink1.setEsType(6);
		        esLink1.setEsTypeDesc("动物信息表动物编号自动录入");
		        esLink1.setRecordTime(new Date());
				esLink1.setLinkId(tblESLinkService.getKey("TblESLink"));
				try{
					tblESService.save(es);
					tblESService.save(es1);
					tblESLinkService.save(esLink);
					tblESLinkService.save(esLink1);
				}catch(Exception e){
				     System.out.println("执行失败，出错种类"+e.getMessage()+".");
				     return "systemError";
				}finally{ 
				     System.out.println("执行结束");
				} 
				isAnimalIdES = tblESLinkService.isESLink("TblAnimal",studyNoPara,5);
				ActionContext.getContext().put("Automatic", "Automatic");
			}else if(Animalsign != null && Animalsign.equals("update")){
				ActionContext.getContext().put("Automatic", "Automatic");
			}else{
				ActionContext.getContext().put("Automatic", "");
			}
		}
		ActionContext.getContext().put("isAnimalIdES", isAnimalIdES);
		ActionContext.getContext().put("left_member", member);
		ActionContext.getContext().put("hasAnimal", hasAnimal);//是否有动物
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(userService.checkPrivilege(user, "批准修改")){
			ActionContext.getContext().put("role","have");
		}else{
			ActionContext.getContext().put("role", "nohava");
		}
		if(userService.checkPrivilege(user, "SD")){
			ActionContext.getContext().put("SD","true");
		}else{
			ActionContext.getContext().put("SD","false");
		}
		return "studyPlanMain";
	}
	

	
	/**
	 * 试验计划标签
	 * @return
	 */
	public String studyPlanTab() {
		return "studyPlanTab";
	}
	
	/**
	 * 试验计划查看
	 * @return String
	 */
	public String studyPlanView() {
		tblStudyPlan = new TblStudyPlan();//实例化
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);//根据课题编号获得试验计划的信息
		User user = (User) ActionContext.getContext().getSession().get("user");//登录信息
		ActionContext.getContext().put("user", user);
		return "studyPlanView";
	}


	/**准备课题编号列表（json datagrid）*/
	public void loadStudyNoList(){
		String userName = "";
		User user = getCurrentUser();
		if(null != user){
			userName = user.getUserName();
		}
		//查询委托项目列表(已任命SD,SD==自己,且还未建专题的,studyCode(studyNo),studyName,sponsorName,tiName,tiCode)
		List<Map<String,Object>> mapList = tblStudyItemService.getMapListByuserName(userName);
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
		
	}
	/** 新增 dialog（准备数据）*/
	public void studyPlanAdd() {
		TblStudyItem tblStudyItem = tblStudyItemService.getByStudyNoStudyItem(newStudyNo);
		//课题信息Map
		Map<String,String> studyPlanMap = new HashMap<String,String>();
		//课题类别List Map
		List<Map<String,String>> studyTypeMapList = new ArrayList<Map<String,String>>();
		//动物类别MapList
		List<Map<String,String>> animalTypeMapList = new ArrayList<Map<String,String>>();
		//动物品系MapList
		List<Map<String,String>> studyStrainMapList = new ArrayList<Map<String,String>>();
		//剂量单位MapList
		List<Map<String,String>> doseUnitMapList = new ArrayList<Map<String,String>>();
		//qaMapList
		List<Map<String,String>> qaMapList = new ArrayList<Map<String,String>>();
		//病理负责人Maplist
		List<Map<String,String>> pathMapList = new ArrayList<Map<String,String>>();
		//临检负责人Maplist
		List<Map<String,String>> clinicalTestMapList = new ArrayList<Map<String,String>>();
		
		studyPlanMap.put("studyNo", newStudyNo);
		if(null != tblStudyItem){
			studyPlanMap.put("studyNo", newStudyNo);
			
			studyPlanMap.put("studyName", tblStudyItem.getStudyName());
			studyPlanMap.put("studyTypeCode", tblStudyItem.getStudyTypeCode());
			
			String sd = null;
			if(null != tblStudyItem.getSd() && !"".equals(tblStudyItem.getSd())){
				sd = tblStudyItem.getSd();
			}else if(null != tblStudyItem.getSdManager() && !"".equals(tblStudyItem.getSdManager())){
				sd = tblStudyItem.getSdManager();
			}else{
				User user = (User) ActionContext.getContext().getSession().get("user");
				sd =  null != user ? user.getRealName():"";
			}
			studyPlanMap.put("sd", sd);
			
			studyPlanMap.put("isGLP", tblStudyItem.getGlpFlag()+"");
			studyPlanMap.put("animalType", tblStudyItem.getAnimalType());
			studyPlanMap.put("animalStrain", tblStudyItem.getAnimalStrain());
			
			//QA,病理
			if(null != tblStudyItem.getQa() && !"".equals(tblStudyItem.getQa())){
				studyPlanMap.put("qa", tblStudyItem.getQa());
			}else{
				List<?> qaList = userService.findUserNameRealNameByPrivilegeName("QA负责人");
				if(null != qaList){
					Object[] objs= (Object[]) qaList.get(0);
					studyPlanMap.put("qa",(String)objs[1]);
				}
			}
			if(null != tblStudyItem.getPathSD() && !"".equals(tblStudyItem.getPathSD())){
				studyPlanMap.put("pathSD", tblStudyItem.getPathSD());
			}else{
				List<?> pathSDList = userService.findUserNameRealNameByPrivilegeName("病理负责人");
				if(null != pathSDList){
					Object[] objs= (Object[]) pathSDList.get(0);
					studyPlanMap.put("pathSD",(String)objs[1]);
				}
			}
			
			//试验验证试验
			studyPlanMap.put("isValidation", 0+"");
			
			//动物品系列表
			List<DictAnimalStrain> animalStrainList = null;
			DictAnimalType animalType = dictAnimalTypeService.getByName(tblStudyItem.getAnimalType());
			if(null!=animalType){
				animalStrainList = dictAnimalStrainService.findByTypeId(animalType.getId());
				if(null!=animalStrainList && animalStrainList.size()>0){
					for(DictAnimalStrain obj : animalStrainList){
						Map<String,String> map = new HashMap<String,String>();
						map.put("id", obj.getStrainName());
						map.put("text", obj.getStrainName());
						studyStrainMapList.add(map);
					}
				}
			}
			String  tiCode = "";
			tiCode = tblStudyItemService.getTiCodeByStudyNo(newStudyNo);
			studyPlanMap.put("studyTiCode", tiCode);
			//获取该供试品类型的      课题类别实体
			List<DictStudyType> dictStudyTypeList = dictStudyTypeService.getByTiCode(tiCode);
			//将课题类别的编号和名称装入map中
			for(DictStudyType obj : dictStudyTypeList){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", obj.getStudyTypeCode());
				map.put("text", obj.getStudyName());
				studyTypeMapList.add(map);
			}
		}else{
			User user = (User) ActionContext.getContext().getSession().get("user");
			studyPlanMap.put("sd", null != user ? user.getRealName():"");
			//获取所有   课题类别实体
			List<DictStudyType> dictStudyTypeList = dictStudyTypeService.getAll();
			//将课题类别的编号和名称装入map中
			for(DictStudyType obj : dictStudyTypeList){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", obj.getStudyTypeCode());
				map.put("text", obj.getStudyName());
				studyTypeMapList.add(map);
			}
		}
		Map<String,String> map =null;
		//animalTypeMapList
		//处理动物种类下拉框
		List<DictAnimalType> dictAnimalTypeList=dictAnimalTypeService.findAllOrderByOrderNo();
		if(null!=dictAnimalTypeList && dictAnimalTypeList.size()>0){
			map = new HashMap<String,String>();
			map.put("id", "-1");
			map.put("text", "&nbsp;");
			animalTypeMapList.add(map);
			for(DictAnimalType obj:dictAnimalTypeList){
				map = new HashMap<String,String>();
				map.put("id", obj.getId());
				map.put("text", obj.getTypeName());
				animalTypeMapList.add(map);
			}
		}
		
		
		//doseUnitMapList剂量单位
		List<DictDoseUnit> doseUnitList = dictDoseUnitService.getAll();
		if(null!=doseUnitList && doseUnitList.size()>0){
			for(DictDoseUnit obj:doseUnitList){
				map = new HashMap<String,String>();
				map.put("id", obj.getAbbr());
				map.put("text", obj.getAbbr());
				doseUnitMapList.add(map);
			}
		}
		
		//处理QA负责人下拉框
//		List<User> qaList =userService.findByPrivilegeName("QA");
//		if(null!=qaList && qaList.size()>0){
//			for(User obj:qaList){
//				map = new HashMap<String,String>();
//				map.put("id", obj.getRealName());
//				map.put("text", obj.getRealName());
//				qaMapList.add(map);
//			}
//		}
		List<?> qaList = userService.findUserNameRealNameByPrivilegeName("QA");
		if(null!=qaList && qaList.size()>0){
			for(Object obj:qaList){
				Object[] objs = (Object[]) obj;
				map = new HashMap<String,String>();
				map.put("id", (String)objs[1]);
				map.put("text", (String)objs[1]);
				qaMapList.add(map);
			}
		}
		
		//处理病理负责人下拉框
//		List<User> pathList =userService.findByPrivilegeName("病理");
//		if(null!=pathList && pathList.size()>0){
//			for(User obj:pathList){
//				map = new HashMap<String,String>();
//				map.put("id", obj.getRealName());
//				map.put("text", obj.getRealName());
//				pathMapList.add(map);
//			}
//		}
		List<?> pathList = userService.findUserNameRealNameByPrivilegeName("病理");
		if(null!=pathList && pathList.size()>0){
			map = new HashMap<String,String>();
			map.put("id", "");
			map.put("text", "未指定");
			pathMapList.add(map);
			for(Object obj:pathList){
				Object[] objs = (Object[]) obj;
				map = new HashMap<String,String>();
				map.put("id", (String)objs[1]);
				map.put("text", (String)objs[1]);
				pathMapList.add(map);
			}
		}
		
		//处理临检负责人下拉框
//		List<User> clinicalTestList =userService.findByPrivilegeName("临检登录");
//		
//		if(null!=clinicalTestList && clinicalTestList.size()>0){
//			for(User obj:clinicalTestList){
//				map = new HashMap<String,String>();
//				map.put("id", obj.getRealName());
//				map.put("text", obj.getRealName());
//				clinicalTestMapList.add(map);
//			}
//		}
		List<?> clinicalTestList = userService.findUserNameRealNameByPrivilegeName("临检登录");
		if(null!=clinicalTestList && clinicalTestList.size()>0){
			map = new HashMap<String,String>();
			map.put("id", "");
			map.put("text", "未指定");
			clinicalTestMapList.add(map);
			for(Object obj:clinicalTestList){
				Object[] objs = (Object[]) obj;
				map = new HashMap<String,String>();
				map.put("id", (String)objs[1]);
				map.put("text", (String)objs[1]);
				clinicalTestMapList.add(map);
			}
		}
		
		
		//课题信息Map
//		Map<String,String> studyPlanMap = new HashMap<String,String>();
//		//课题类别List Map
//		List<Map<String,String>> studyTypeMapList = new ArrayList<Map<String,String>>();
		Map<String,Object> bossMap = new HashMap<String,Object>();
		bossMap.put("studyPlanMap",studyPlanMap);
		bossMap.put("studyTypeMapList",studyTypeMapList);
		bossMap.put("studyStrainMapList",studyStrainMapList);
		bossMap.put("animalTypeMapList",animalTypeMapList);
		bossMap.put("doseUnitMapList",doseUnitMapList);
		bossMap.put("qaMapList",qaMapList);
		bossMap.put("pathMapList",pathMapList);
		bossMap.put("clinicalTestMapList",clinicalTestMapList);
		String json = JsonPluginsUtil.beanToJson(bossMap);
		writeJson(json);
	}
	/**动物类别  品系  联动*/
	public void getAnimalStrain(){
		List<Map<String,String>> animalStrainMapList = new ArrayList<Map<String,String>>();
		if(null!=animalTypeId && !animalTypeId.isEmpty()){
			List<DictAnimalStrain> list = dictAnimalStrainService.findByTypeId(animalTypeId);
			Map<String,String> map = null;
			if(null!=list && list.size()>0){
				for(DictAnimalStrain obj:list){
					map = new HashMap<String,String>();
					map.put("id", obj.getStrainName());
					map.put("text", obj.getStrainName());
					animalStrainMapList.add(map);
				}
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(animalStrainMapList);
		writeJson(jsonStr);
	}
	/**
	 * 新增保存
	 * @return String
	 */
	public void addSave() {
		//设置试验状态
		tblStudyPlan.setStudyState("0");
		//Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		Map<String,Object> map = new HashMap<String, Object>();
		if(nullCheck(tblStudyPlan)){
			
			User user = (User) ActionContext.getContext().getSession().get("user");
			String sd =  null != user ? user.getRealName():"";
			if(sd.equals(tblStudyPlan.getStudydirector())){
				//根据课题类别编码获得课题类别
				//设置课题类别
				studyNoPara = tblStudyPlan.getStudyNo();
//			private int abnVisceraAnatomyCheck;    //异常组织剖检标识0,否  1，是
//			private int abnVisceraFixedFlag;    //异常组织固定标识0,否  1，是
//			private int abnVisceraHistopathCheckFlag;  //异常组织镜检标识0,否  1，是
//			private int abnVisceraWeighFlag;           //异常组织称重标识0,否  1，是
				//TODO
				tblStudyPlan.setAbnVisceraAnatomyCheck(1);
				tblStudyPlan.setAbnVisceraFixedFlag(1);
				tblStudyPlan.setAbnVisceraHistopathCheckFlag(1);
//			tblStudyPlan.setAbnVisceraWeighFlag(1);
				
				tblStudyPlanService.save(tblStudyPlan);
				//设置默认检验指标（课题类别）
				setTestIndexPlan(tblStudyPlan);
				map.put("msg", studyNoPara);
				map.put("obj", tblStudyPlan.getStudydirector());
				map.put("success", true);
				map.put("name", tempUser.getRealName());
				//写日志
				writeLog("新建","新建课题："+tblStudyPlan.getStudyNo());
				
			}else{
				map.put("msg", "专题（"+tblStudyPlan.getStudyNo()+"）对应SD为"+tblStudyPlan.getStudydirector()+"！");
			}
			
		}else{
			map.put("msg", "与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	

	/** 编辑dialog（准备数据）*/
	public void studyPlanEdit() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(newStudyNo);
		if(null!=tblStudyPlan && ( tblStudyPlan.getStudyState().equals("0") || tblStudyPlan.getStudyState().equals("3"))){
			TblStudyItem tblStudyItem = tblStudyItemService.getByStudyNoStudyItem(newStudyNo);
			//课题信息Map
			Map<String,String> studyPlanMap = new HashMap<String,String>();
			//课题类别List Map
			List<Map<String,String>> studyTypeMapList = new ArrayList<Map<String,String>>();
			//动物品系MapList
			List<Map<String,String>> studyStrainMapList = new ArrayList<Map<String,String>>();
			//课题类别List Map
			List<Map<String,String>> animalTypeMapList = new ArrayList<Map<String,String>>();
			//qaMapList
			List<Map<String,String>> doseUnitMapList = new ArrayList<Map<String,String>>();
			//qaMapList
			List<Map<String,String>> qaMapList = new ArrayList<Map<String,String>>();
			//病理负责人Maplist
			List<Map<String,String>> pathMapList = new ArrayList<Map<String,String>>();
			//临检负责人Maplist
			List<Map<String,String>> clinicalTestMapList = new ArrayList<Map<String,String>>();
			
			studyPlanMap.put("studyNo", newStudyNo);
			studyPlanMap.put("studyNo", newStudyNo);
			studyPlanMap.put("sd", tblStudyPlan.getStudydirector());
			studyPlanMap.put("isGLP", tblStudyPlan.getIsGLP()+"");
			studyPlanMap.put("isValidation", tblStudyPlan.getIsValidation()+"");
			studyPlanMap.put("animalType", tblStudyPlan.getAnimalType());
			studyPlanMap.put("animalStrain", tblStudyPlan.getAnimalStrain());
			studyPlanMap.put("studyStartDate", DateUtil.dateToString(tblStudyPlan.getStudyStartDate(), "yyyy-MM-dd"));
			studyPlanMap.put("animalImportDate",DateUtil.dateToString(tblStudyPlan.getAnimalImportDate(), "yyyy-MM-dd"));
			studyPlanMap.put("preStudyDate", DateUtil.dateToString(tblStudyPlan.getPreStudyDate(), "yyyy-MM-dd"));
			studyPlanMap.put("studyBeginDate", DateUtil.dateToString(tblStudyPlan.getStudyBeginDate(), "yyyy-MM-dd"));
			studyPlanMap.put("dosageUnit", tblStudyPlan.getDosageUnit());
			studyPlanMap.put("qa", tblStudyPlan.getQa());
			studyPlanMap.put("pathDirector", tblStudyPlan.getPathDirector());
			studyPlanMap.put("clinicalTestDir", tblStudyPlan.getClinicalTestDirector());
			studyPlanMap.put("studyName", tblStudyPlan.getStudyName());
			studyPlanMap.put("studyTypeCode", tblStudyPlan.getStudyTypeCode());
			Map<String,String> map =null;
			//animalTypeMapList //处理动物种类下拉框
			List<DictAnimalType> dictAnimalTypeList=dictAnimalTypeService.findAllOrderByOrderNo();
				if(null!=dictAnimalTypeList && dictAnimalTypeList.size()>0){
					map = new HashMap<String,String>();
					map.put("id", "-1");
					map.put("text", "&nbsp;");
					animalTypeMapList.add(map);
					for(DictAnimalType obj:dictAnimalTypeList){
						map = new HashMap<String,String>();
						map.put("id", obj.getId());
						map.put("text", obj.getTypeName());
						animalTypeMapList.add(map);
					}
				}
			//动物品系列表
			List<DictAnimalStrain> animalStrainList = null;
				DictAnimalType animalType = dictAnimalTypeService.getByName(tblStudyPlan.getAnimalType());
				if(null!=animalType){
					animalStrainList = dictAnimalStrainService.findByTypeId(animalType.getId());
					if(null!=animalStrainList && animalStrainList.size()>0){
						for(DictAnimalStrain obj : animalStrainList){
							map = new HashMap<String,String>();
							map.put("id", obj.getStrainName());
							map.put("text", obj.getStrainName());
							studyStrainMapList.add(map);
						}
					}
				}
			List<DictStudyType> dictStudyTypeList =	null;
			if(null != tblStudyItem){
				String  tiCode = "";
				tiCode = tblStudyItemService.getTiCodeByStudyNo(newStudyNo);
				
				studyPlanMap.put("studyTiCode", tiCode);
				
				//获取多有课题类别实体
				dictStudyTypeList = dictStudyTypeService.getByTiCode(tiCode);
			}else{
				dictStudyTypeList = dictStudyTypeService.getAll();
			}
			if(null!=dictStudyTypeList && dictStudyTypeList.size()>0){
				//将课题类别的编号和名称装入map中
				for(DictStudyType obj : dictStudyTypeList){
					map = new HashMap<String,String>();
					map.put("id", obj.getStudyTypeCode());
					map.put("text", obj.getStudyName());
					studyTypeMapList.add(map);
				}
			}
			
			//doseUnitMapList剂量单位
			List<DictDoseUnit> doseUnitList = dictDoseUnitService.getAll();
			if(null!=doseUnitList && doseUnitList.size()>0){
				for(DictDoseUnit obj:doseUnitList){
					map = new HashMap<String,String>();
					map.put("id", obj.getAbbr());
					map.put("text", obj.getAbbr());
					doseUnitMapList.add(map);
				}
			}
			
//			//处理QA负责人下拉框
//			List<User> qaList =userService.findByPrivilegeName("QA");
//			if(null!=qaList && qaList.size()>0){
//				for(User obj:qaList){
//					map = new HashMap<String,String>();
//					map.put("id", obj.getRealName());
//					map.put("text", obj.getRealName());
//					qaMapList.add(map);
//				}
//			}
//			//处理病理负责人下拉框
//			List<User> pathList =userService.findByPrivilegeName("病理");
//			if(null!=pathList && pathList.size()>0){
//				for(User obj:pathList){
//					map = new HashMap<String,String>();
//					map.put("id", obj.getRealName());
//					map.put("text", obj.getRealName());
//					pathMapList.add(map);
//				}
//			}
//			//处理临检负责人下拉框
//			List<User> clinicalTestList =userService.findByPrivilegeName("临检登录");
//			if(null!=clinicalTestList && clinicalTestList.size()>0){
//				for(User obj:clinicalTestList){
//					 map = new HashMap<String,String>();
//					map.put("id", obj.getRealName());
//					map.put("text", obj.getRealName());
//					clinicalTestMapList.add(map);
//				}
//			}
			
			List<?> qaList = userService.findUserNameRealNameByPrivilegeName("QA");
			if(null!=qaList && qaList.size()>0){
				for(Object obj:qaList){
					Object[] objs = (Object[]) obj;
					map = new HashMap<String,String>();
					map.put("id", (String)objs[1]);
					map.put("text", (String)objs[1]);
					qaMapList.add(map);
				}
			}
			
			List<?> pathList = userService.findUserNameRealNameByPrivilegeName("病理");
			if(null!=pathList && pathList.size()>0){
				map = new HashMap<String,String>();
				map.put("id", "");
				map.put("text", "未指定");
				pathMapList.add(map);
				for(Object obj:pathList){
					Object[] objs = (Object[]) obj;
					map = new HashMap<String,String>();
					map.put("id", (String)objs[1]);
					map.put("text", (String)objs[1]);
					pathMapList.add(map);
				}
			}
			
			List<?> clinicalTestList = userService.findUserNameRealNameByPrivilegeName("临检登录");
			if(null!=clinicalTestList && clinicalTestList.size()>0){
				map = new HashMap<String,String>();
				map.put("id", "");
				map.put("text", "未指定");
				clinicalTestMapList.add(map);
				for(Object obj:clinicalTestList){
					Object[] objs = (Object[]) obj;
					map = new HashMap<String,String>();
					map.put("id", (String)objs[1]);
					map.put("text", (String)objs[1]);
					clinicalTestMapList.add(map);
				}
			}
			
			//课题信息Map
            //Map<String,String> studyPlanMap = new HashMap<String,String>();
	    	//课题类别List Map
            //List<Map<String,String>> studyTypeMapList = new ArrayList<Map<String,String>>();
			Map<String,Object> bossMap = new HashMap<String,Object>();
			bossMap.put("studyPlanMap",studyPlanMap);
			bossMap.put("studyTypeMapList",studyTypeMapList);
			bossMap.put("animalTypeMapList",animalTypeMapList);
			bossMap.put("studyStrainMapList",studyStrainMapList);
			bossMap.put("doseUnitMapList",doseUnitMapList);
			bossMap.put("qaMapList",qaMapList);
			bossMap.put("pathMapList",pathMapList);
			bossMap.put("clinicalTestMapList",clinicalTestMapList);
			String json = JsonPluginsUtil.beanToJson(bossMap);
			writeJson(json);
		}
		
	}

	/**
	 * 编辑后保存
	 * @return String
	 */
	public void editSave() {
		Json json = new Json();
		//根据课题类别编码获得课题类别
        //DictStudyType studyType = dictStudyTypeService.getById(studyTypeSel);
		DictStudyType studyType = new DictStudyType();
		studyType.setStudyTypeCode(tblStudyPlan.getStudyTypeCode());
		//根据编辑的课题编号获取完整的试验计划信息
		TblStudyPlan newTblStudyPlan = tblStudyPlanService.getById(tblStudyPlan.getStudyNo());
		//将编辑后的数据替换原有数据
        //newTblStudyPlan.setDictStudyType(studyType);
		boolean flag = true;
		if(newTblStudyPlan.getDoseSettingFlag() == 1){
			String oldAnimalType = newTblStudyPlan.getAnimalType();
			String newAnimalType = tblStudyPlan.getAnimalType();
			if(null != oldAnimalType && !"".equals(oldAnimalType)){
				if(null != newAnimalType && !"".equals(newAnimalType)){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				if(null != newAnimalType && !"".equals(newAnimalType)){
					flag = false;
				}else{
					flag = true;
				}
			}
		}
		if(flag){
			newTblStudyPlan.setStudyName(tblStudyPlan.getStudyName());
			newTblStudyPlan.setStudyTypeCode(tblStudyPlan.getStudyTypeCode());
			newTblStudyPlan.setStudydirector(tblStudyPlan.getStudydirector());
			newTblStudyPlan.setIsGLP(tblStudyPlan.getIsGLP());
			newTblStudyPlan.setTemp(tblStudyPlan.getTemp());
			newTblStudyPlan.setAnimalType(tblStudyPlan.getAnimalType());
			newTblStudyPlan.setAnimalStrain(tblStudyPlan.getAnimalStrain());
			newTblStudyPlan.setStudyStartDate(tblStudyPlan.getStudyStartDate());
			newTblStudyPlan.setAnimalImportDate(tblStudyPlan.getAnimalImportDate());
			newTblStudyPlan.setPreStudyDate(tblStudyPlan.getPreStudyDate());
			newTblStudyPlan.setStudyBeginDate(tblStudyPlan.getStudyBeginDate());
			newTblStudyPlan.setDosageUnit(tblStudyPlan.getDosageUnit());
			newTblStudyPlan.setQa(tblStudyPlan.getQa());
			newTblStudyPlan.setPathDirector(tblStudyPlan.getPathDirector());
			newTblStudyPlan.setClinicalTestDirector(tblStudyPlan.getClinicalTestDirector());
			newTblStudyPlan.setIsValidation(tblStudyPlan.getIsValidation());
			studyNoPara = newTblStudyPlan.getStudyNo();
			//更新数据
			if(nullCheck(newTblStudyPlan)){
				//修改时保存历史表
				String state = newTblStudyPlan.getStudyState();
				if(state.equals("0")){
					tblStudyPlanService.update(newTblStudyPlan);
					json.setSuccess(true);
					json.setMsg(studyNoPara);
				}else{
					TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(newTblStudyPlan.getStudyNo());
					//获取试验计划
					TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
					//签名链接
					TblESLink esLink = new TblESLink();
					//电子签名
					TblES es = new TblES();
					User tempUser = (User) ActionContext.getContext().getSession().get("user");
					es.setEsId(getIdService.getKey("TblES"));
					es.setSigner(tempUser.getRealName());
					es.setEsType(202);
					es.setEsTypeDesc(newTblStudyPlan.getStudyNo()+"提交后再次修改编辑");
					es.setDateTime(new Date());
					
					esLink.setLinkId(getIdService.getKey("TblESLink"));
					esLink.setTableName("TblStudyPlan");
					esLink.setDataId(sPlan.getStudyNo());
					esLink.setTblES(es);
					esLink.setEsType(2);
					esLink.setEsTypeDesc(newTblStudyPlan.getStudyNo()+"提交后再次修改编辑");
					esLink.setRecordTime(new Date());
					try{
						//保存
						tblESService.save(es);
						tblESLinkService.save(esLink);
						tblStudyPlanService.updateAndSaveTblStudyPlanHis(newTblStudyPlan,tblApplyRevise);
						//写日志
						writeLog("编辑","课题："+newTblStudyPlan.getStudyNo()+"提交后再次修改编辑");
						//设置默认检验指标（课题类别）
						setTestIndexPlan(newTblStudyPlan);
						json.setSuccess(true);
						json.setMsg(studyNoPara);
					}catch(Exception e){
					     json.setSuccess(false);
					     json.setMsg("与数据库交互异常");
					     System.out.println("执行失败，出错种类"+e.getMessage()+".");
					}finally{ 
					     System.out.println("执行结束");
					} 
					
				}
			}else{
				json.setMsg("请检查录入数据！");
			}
		}else{
			json.setMsg("剂量组设计已确认，动物种类不可以从'无'到'有' 或 从'有'到'无'!");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/**
	 * 试验计划主键唯一性检查
	 * @throws Exception
	 */
	public void uniqueCheck()throws Exception {
		//调用service方法返回是否存在主键
		boolean isExisit = tblStudyPlanService.isExistById(studyNoPara);
		if (!isExisit && !studyNoPara.equals(null) ) {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("no");
		} else {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("is");
		}

	}
	/**试验编号唯一性检查(json)*/
	public void studyNOCheck(){
		if(null!=newStudyNo && !newStudyNo.isEmpty()){
			boolean isExisit = tblStudyPlanService.isExistById(newStudyNo);
			if(!isExisit){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
		
	}

	/**
	 * 非空检查，false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean nullCheck(TblStudyPlan obj){
		boolean flag = true;
		if(stringCherck(obj.getStudyNo())){
			flag = false;
		}else if (stringCherck(obj.getStudydirector())) {
			flag = false;
		}else if (obj.getIsGLP() != 0 && obj.getIsGLP() != 1) {
			flag = false;
		}else if (obj.getStudyStartDate() == null) {
			flag = false;
		}else if (stringCherck(obj.getDosageUnit())) {
			flag = false;
		}else if (stringCherck(obj.getStudyName())) {
			flag = false;
		}
//		else if (stringCherck(obj.getStudyTypeCode())) {
//			flag = false;
//		}
		else{
			
		}
		System.out.println(obj.getStudyTypeCode());
		return flag;
	}
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	
	/**
	 * 课题计划检验指标自动默认设置
	 * @param studyPlan
	 */
	private void setTestIndexPlan(TblStudyPlan studyPlan){
		List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(studyPlan);
		//如果有旧的记录则删除
		if(testIndexPlanList!=null && !testIndexPlanList.isEmpty()){
			tblTestIndexPlanService.deleteIndexPlans(testIndexPlanList);
			//清除数据，为后续存储做准备
			testIndexPlanList.clear();
		}
		DictStudyType dictStudyType = new DictStudyType();
		dictStudyType.setStudyTypeCode(studyPlan.getStudyTypeCode());
		//取得缺省检验指标
		List<DictStudyTestIndex> studyTestIndexList = dictStudyTestIndexService.getByType(dictStudyType);
		//设值
		if(studyTestIndexList != null && !studyTestIndexList.isEmpty()){
			for(DictStudyTestIndex obj : studyTestIndexList){
				TblTestIndexPlan testIndexPlan = new TblTestIndexPlan();
				testIndexPlan.setTblStudyPlan(studyPlan);
				testIndexPlan.setTestItem(obj.getTestItem());
				testIndexPlan.setTestIndex(obj.getIndexName());
				testIndexPlan.setTestIndexAbbr(obj.getIndexAbbr());
				testIndexPlan.setPrecision(2);
				testIndexPlanList.add(testIndexPlan);
			}
		}
		//保存
		tblTestIndexPlanService.saveIndexPlans(testIndexPlanList);
	}
	
	/**
	 * 签字验证
	 */
	public void signCheck() throws Exception {
		String msg = "pass";
		//获取试验计划
		TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
		msg = itemsCheck(sPlan,continueFlag);
		//返回值
		response.setContentType("htmlt");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().print(msg);
	}
	
	/**
	 *查看剂量是否修改 
	 */
	public void isDoseSettingChange()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSame", true);
		
		//获取试验计划
		TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
		
		List<TblDoseSetting> doseSettingList = tblDoseSettingService.getByStudyNo(sPlan);
		List<TblDoseSettingVersion> versionDoseList = tblDoseSettingVersionService.getMaxVersionByStudyNo(studyNoPara);
		if(versionDoseList!=null && versionDoseList.size()>0)
		{
			if(versionDoseList.size() == doseSettingList.size())
			{
				for(int i=0;i<versionDoseList.size();i++)
				{
					boolean isSame = companyDoseSettingAndHis(doseSettingList.get(i) ,versionDoseList.get(i));
					if(!isSame)
					{
						map.put("isSame",false );
						
						break;
					}
				}
			}
		}
		
		//当不存在修改的时候，isSame是true,即不用添加生效日期
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public boolean companyDoseSettingAndHis(TblDoseSetting doseSetting,TblDoseSettingVersion doseSettingVersion)
	{
		if(doseSetting == null || doseSettingVersion == null)
		{
			return false;
		}else{
			boolean flag1 = (doseSetting.getDosage()==null&&doseSettingVersion.getDosage()==null)
							||(doseSetting.getDosage()!=null&&doseSetting.getDosage().equals(doseSettingVersion.getDosage()));
				
			boolean flag2 = (doseSetting.getDosageDesc()==null&&doseSettingVersion.getDosageDesc()==null)
							||(doseSetting.getDosageDesc()!=null&&doseSetting.getDosageDesc().equals(doseSettingVersion.getDosageDesc()));
			
			boolean flag3 = doseSetting.getDosageNum()==doseSettingVersion.getDosageNum();
			
			boolean flag4 = (doseSetting.getFemaleDosage()==null&&doseSettingVersion.getFemaleDosage()==null)
						||(doseSetting.getFemaleDosage()!=null&&doseSetting.getFemaleDosage().equals(doseSettingVersion.getFemaleDosage()));
			
			boolean flag5 = doseSetting.getFemaleNum()==doseSettingVersion.getFemaleNum();
			
			boolean flag6 = (doseSetting.getFemaleThickness()==null&&doseSettingVersion.getFemaleThickness()==null)
							||(doseSetting.getFemaleThickness()!=null&&doseSetting.getFemaleThickness().equals(doseSettingVersion.getFemaleThickness()));
			boolean flag7 = (doseSetting.getFemaleVolume()==null&&doseSettingVersion.getFemaleVolume()==null)
							||(doseSetting.getFemaleVolume()!=null&&doseSetting.getFemaleVolume().equals(doseSettingVersion.getFemaleVolume()));
			
			boolean flag8 = doseSetting.getMaleNum() ==doseSettingVersion.getMaleNum();
			
			boolean flag9 = (doseSetting.getMaleThickness()==null&&doseSettingVersion.getMaleThickness()==null)
						||(doseSetting.getMaleThickness()!=null&&doseSetting.getMaleThickness().equals(doseSettingVersion.getMaleThickness()));
			
			boolean flag10 = (doseSetting.getMaleVolume()==null&&doseSettingVersion.getMaleVolume()==null)
					||(doseSetting.getMaleVolume()!=null&&doseSetting.getMaleVolume().equals(doseSettingVersion.getMaleVolume()));
			
			if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8 && flag9 && flag10)
			{
				return true;
			}else{
				return false;
			}
			
		}
	}
	
	/**
	 * 密码验证
	 * @throws Exception
	 */
	public void passwordCheck() throws Exception{
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		if(tempUser != null && password != null && DigestUtils.md5Hex(password).equals(tempUser.getPassword())){
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("yes");
		}else {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.getWriter().print("no");
		}
	}
	
	/**
	 * 需要FM审批的提交签字，没有变日程的validFlag。
	 * @return
	 */
	public String oldSign() {
		//获取试验计划
		TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		//验证通过则进行一下操作
		String eid = getIdService.getKey("TblES");
		if( !itemsCheck(sPlan).equals("doseSetting") ){
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String state = sPlan.getStudyState();
			if(state.equals("0")){
				es.setEsId(eid);
				es.setSigner(tempUser.getRealName());
				es.setEsType(2);
				es.setEsTypeDesc("试验计划");
				es.setDateTime(new Date());
				
				esLink.setLinkId(getIdService.getKey("TblESLink"));
				esLink.setTableName("TblStudyPlan");
				esLink.setDataId(sPlan.getStudyNo());
				esLink.setTblES(es);
				esLink.setEsType(2);
				esLink.setEsTypeDesc("试验计划");
				esLink.setRecordTime(new Date());
				
				//设置试验状态
				sPlan.setStudyState("1");
				try{
					//保存
					tblESService.save(es);
					tblESLinkService.save(esLink);
					tblStudyPlanService.update(sPlan);
					writeLog("签字","课题："+studyNoPara);
				}catch(Exception e){
				     System.out.println("执行失败，出错种类"+e.getMessage()+".");
				     return "systemError";
				}finally{ 
				     System.out.println("执行结束");
				} 	
				//设置动物信息
//				animalSet(sPlan);
				//写日志
				
			}else if(state.equals("3")){
				es.setEsId(eid);
				es.setSigner(tempUser.getRealName());
				es.setEsType(2);
				es.setEsTypeDesc("试验计划");
				es.setDateTime(new Date());
				esLink.setLinkId(getIdService.getKey("TblESLink"));
				esLink.setTableName("TblStudyPlan");
				esLink.setDataId(sPlan.getStudyNo());
				esLink.setTblES(es);
				esLink.setEsType(2);
				esLink.setEsTypeDesc("试验计划");
				esLink.setRecordTime(new Date());
				//设置试验状态
				sPlan.setStudyState("1");
				try{
					//保存
					tblESService.save(es);
					tblESLinkService.save(esLink);
					tblStudyPlanService.update(sPlan);
					//写日志
					writeLog("签字","课题："+studyNoPara+"再编辑后签字提交");
				}catch(Exception e){
				     System.out.println("执行失败，出错种类"+e.getMessage()+".");
				     return "systemError";
				}finally{ 
				     System.out.println("执行结束");
				} 
				
			}
		}
		// 日程计划
		List<TblSchedulePlan> tblSchedulePlans  = tblSchedulePlanService.getSchedulePlanList(2, sPlan.getStudyNo(), 2);
		if(tblSchedulePlans != null && tblSchedulePlans.size() > 0 ){
			List<String> singIdList = new ArrayList<String>();
			for(TblSchedulePlan  sign:tblSchedulePlans){
				singIdList.add(sign.getScheduleID());
			}
			try{
			     //日程提交
				 System.out.println("提交日程-----------------------------------");
			     tblSchedulePlanService.updateAllTblSchedulePlans(singIdList,eid);
			}catch(Exception e){
			     System.out.println("执行失败，出错种类"+e.getMessage()+".");
			     return "systemError";
			}finally{ 
			     System.out.println("执行结束");
			} 
			//发消息
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
			int i = singIdList.size();
			String[] ids = new String[i] ;
			int j=0;
			for(String id:singIdList){
				ids[j]=id;
				j++;
			}
			List<TblSchedulePlan> tblSchedulePlanlist = tblSchedulePlanService.getByIds(ids);
			List<String> list = new ArrayList<String>();
			TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNoPara);
			String studyNoName = ""; 
			if(null != studyItem ){
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				if(dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
			}
		
			if(null != tblSchedulePlanlist && tblSchedulePlanlist.size()>0){
				
				for(TblSchedulePlan obj:tblSchedulePlanlist){
					if(!list.contains(obj.getTaskKind()+"")){
						list.add(obj.getTaskKind()+"");
					}
				}
				//接收者列表
				List<String> receiverList;
				if(list != null && !list.equals("")){
					List<TblTaskTypeLeader> tblTaskTypeLeaderList  = tblTaskTypeLeaderService.getAllTblTaskTypeLeaderListByTaskTypeID(list);
					for(TblTaskTypeLeader  leader:tblTaskTypeLeaderList){
						String planMsg = "";
						for(TblSchedulePlan plan:tblSchedulePlanlist){
							if(leader.getTaskTypeID().equals(plan.getTaskKind()+"") ){
								if(planMsg != ""){
									planMsg = planMsg +" , ";
								}
								planMsg = planMsg +plan.getTaskName();
							}else{
								continue;
							}
						}
						//通知消息
						TblNotification tblNotification = new TblNotification();
						tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题:　"+studyNoPara+"专题名称:　"+studyNoName+"　日程提交提醒");//消息头
						//userService.getRealNameByUserName(leader.getTaskLeader())+"您好
						String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
						"专题编号:　 "+studyNoPara+"专题名称:　"+studyNoName+"，具体日程为："+planMsg+"，特此提醒！";
						tblNotification.setMsgContent(msgContent);
						tblNotification.setMsgType(1);//系统消息
						tblNotification.setSender(getCurrentRealName());// 发送者
						tblNotification.setSendTime(new Date());// 发送时间
						receiverList = new ArrayList<String>();
						receiverList.add(leader.getTaskLeader());
						tblNotificationService.save(tblNotification,receiverList);
						planMsg = "";
					}
				}
				//区域负责人接受
				TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(studyNoPara);
				if(null != tblStudyInfo){
					String resid = tblStudyInfo.getResID();
					List<TblResManager> managerList = tblResManagerService.getByHouseId(resid);
					if(null != managerList && list.contains("2")){
						for(TblResManager obj:managerList){
							String planMsg = "";
							for(TblSchedulePlan plan:tblSchedulePlanlist){
								if( 2 == plan.getTaskKind()){
									if(planMsg != ""){
										planMsg = planMsg +" , ";
									}
									planMsg = planMsg +plan.getTaskName();
								}
							}
							//通知消息
							TblNotification tblNotification = new TblNotification();
							TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
							TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
							tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题:　"+studyNoPara+"专题名称:　"+studyNoName+"日程提交提醒");//消息头
							//String msgContent = userService.getRealNameByUserName(obj.getResManager())+"您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
							"专题编号:　 "+studyNoPara+"专题名称:　"+studyNoName+"　，具体日程为："+planMsg+"，安置在你所负责的区域 （"
							+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"），请审核此专题日程，特此提醒！";
							tblNotification.setMsgContent(msgContent);
							tblNotification.setMsgType(1);//系统消息
							tblNotification.setSender(getCurrentRealName());// 发送者
							tblNotification.setSendTime(new Date());// 发送时间
							List<String> resManagerList = new ArrayList<String>();
							resManagerList.add(obj.getResManager());
							tblNotificationService.save(tblNotification,resManagerList);
						}
					}
				}
			}
		}
		return "sign";
	}
	
	
	/**
	 * 签字
	 * @return
	 */
	public String sign() {
		//获取试验计划
		TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		//验证通过则进行一下操作
		String eid = getIdService.getKey("TblES");
		String desc = "";
		if(continueFlag==0)
			desc="试验计划";
		if(continueFlag==1)
			desc="日程";
		if(continueFlag==2)
			desc="试验计划包含日程";
		if( !itemsCheck(sPlan,continueFlag).equals("doseSetting") ){
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String state = sPlan.getStudyState();
			Integer scheduleState = sPlan.getScheduleState();
			
			if(state.equals("0")||state.equals("3")||scheduleState==null||scheduleState==0||scheduleState==3){
				es.setEsId(eid);
				es.setSigner(tempUser.getRealName());
				es.setEsType(2);
				es.setEsTypeDesc(desc);
				es.setDateTime(new Date());
				esLink.setLinkId(getIdService.getKey("TblESLink"));
				esLink.setTableName("TblStudyPlan");
				esLink.setDataId(sPlan.getStudyNo());
				esLink.setTblES(es);
				esLink.setEsType(2);
				esLink.setEsTypeDesc(desc);
				esLink.setRecordTime(new Date());
				
				TblApplyRevise applyRevise = tblApplyReviseService.getByStudyNoAndType(studyNoPara,0);
				if(continueFlag==0)
				{
					if(applyRevise!=null)
					{
						if(doseEffectiveDate==null)
						{
							//applyRevise.setDoseEffectiveDate(new Date());
						}else{
							applyRevise.setDoseEffectiveDate(doseEffectiveDate);
						}
						tblApplyReviseService.update(applyRevise);
					}
					
					//设置试验状态
					sPlan.setStudyState("1");
				}else if(continueFlag==1)
				{
					sPlan.setScheduleState(1);
					//更新日程的有效标志
					tblSchedulePlanService.updateSchedulePlanValidFlagByTaskCode(studyNoPara) ;
					
				}else if(continueFlag==2)
				{
					if(applyRevise!=null)
					{
						if(doseEffectiveDate==null)
						{
							
						}else{
							applyRevise.setDoseEffectiveDate(doseEffectiveDate);
							
						}
						tblApplyReviseService.update(applyRevise);
					}
					
					sPlan.setStudyState("1");
					sPlan.setScheduleState(1);
					//更新日程的有效标志
					tblSchedulePlanService.updateSchedulePlanValidFlagByTaskCode(studyNoPara) ;
					
				}
				
				try{
					//保存
					tblESService.save(es);
					tblESLinkService.save(esLink);
					tblStudyPlanService.update(sPlan);
					String logString = "课题："+studyNoPara;
					if(continueFlag==0||continueFlag==2)
					{
						if(state.equals("0"))
						{
							logString+="试验计划";
						}else if(state.equals("3"))
						{
							logString+="实验计划再编辑";
						}
						
					}
					if(continueFlag==1||continueFlag==2)
					{
						if(scheduleState==null||scheduleState==0)
						{
							if(!("课题："+studyNoPara).equals(logString))
							{
								logString+="和";
							}
							logString+="日程";
						}else if(scheduleState==3)
						{
							if(("课题："+studyNoPara).equals(logString))
							{
								logString+="和";
							}
							logString+="日程再编辑";
						}
					}
					logString+="签字提交";
					writeLog("签字",logString);
				}catch(Exception e){
				     System.out.println("执行失败，出错种类"+e.getMessage()+".");
				     return "systemError";
				}finally{ 
				     System.out.println("执行结束");
				} 	
				//设置动物信息
//				animalSet(sPlan);
				//写日志
				
			}
		}
		if(continueFlag==1||continueFlag==2)
		{
			
			// 日程计划
			List<TblSchedulePlan> tblSchedulePlans  = tblSchedulePlanService.getSchedulePlanList(2, sPlan.getStudyNo(), 2);
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");//当前时间
			if(tblSchedulePlans != null && tblSchedulePlans.size() > 0 ){
				
				List<String> list = new ArrayList<String>();
				TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(studyNoPara);
				String studyNoName = ""; 
				if(null != studyItem ){
					String tiNo = studyItem.getTiNo();
					String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
					DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
					if(dictStudyType.getAnimalHave() == 1){
						studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
					}else{
						studyNoName = testItemName+studyItem.getStudyName();
					}
				}
			
				//if(null != tblSchedulePlanlist && tblSchedulePlanlist.size()>0){
				if(null != tblSchedulePlans && tblSchedulePlans.size()>0){
					for(TblSchedulePlan obj:tblSchedulePlans){
						if(!list.contains(obj.getTaskKind()+"")){
							list.add(obj.getTaskKind()+"");
						}
						
					}
					
					//接收者列表
					List<String> receiverList;
					if(list != null && !list.equals("")){
						List<TblTaskTypeLeader> tblTaskTypeLeaderList  = tblTaskTypeLeaderService.getAllTblTaskTypeLeaderListByTaskTypeID(list);
						for(TblTaskTypeLeader  leader:tblTaskTypeLeaderList){
							String planMsg = "";
							for(TblSchedulePlan plan:tblSchedulePlans){
								if(leader.getTaskTypeID().equals(plan.getTaskKind()+"") ){
									if(planMsg != ""){
										planMsg = planMsg +" , ";
									}
									planMsg = planMsg +plan.getTaskName();
								}else{
									continue;
								}
							}
							//通知消息
							TblNotification tblNotification = new TblNotification();
							tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题:　"+studyNoPara+"专题名称:　"+studyNoName+"　日程提交提醒");//消息头
							//userService.getRealNameByUserName(leader.getTaskLeader())+"您好
							String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
							"专题编号:　 "+studyNoPara+"专题名称:　"+studyNoName+"，具体日程为："+planMsg+"，特此提醒！";
							tblNotification.setMsgContent(msgContent);
							tblNotification.setMsgType(1);//系统消息
							tblNotification.setSender(getCurrentRealName());// 发送者
							tblNotification.setSendTime(new Date());// 发送时间
							receiverList = new ArrayList<String>();
							receiverList.add(leader.getTaskLeader());
							tblNotificationService.save(tblNotification,receiverList);
							planMsg = "";
						}
					}
					//区域负责人接受
					TblStudyInfo tblStudyInfo = tblStudyInfoService.getByStudyNo(studyNoPara);
					if(null != tblStudyInfo){
						String resid = tblStudyInfo.getResID();
						List<TblResManager> managerList = tblResManagerService.getByHouseId(resid);
						if(null != managerList && list.contains("2")){
							for(TblResManager obj:managerList){
								String planMsg = "";
								for(TblSchedulePlan plan:tblSchedulePlans){
									if( 2 == plan.getTaskKind()){
										if(planMsg != ""){
											planMsg = planMsg +" , ";
										}
										planMsg = planMsg +plan.getTaskName();
									}
								}
								//通知消息
								TblNotification tblNotification = new TblNotification();
								TblAnimalHouse tblAnimalHouse = tblAnimalHouseService.getById(tblStudyInfo.getResID());
								TblAnimalHouse ptblAnimalHouse = tblAnimalHouseService.getById(tblAnimalHouse.getParentId());
								tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，创建专题:　"+studyNoPara+"专题名称:　"+studyNoName+"日程提交提醒");//消息头
								//String msgContent = userService.getRealNameByUserName(obj.getResManager())+"您好<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
								"专题编号:　 "+studyNoPara+"专题名称:　"+studyNoName+"　，具体日程为："+planMsg+"，安置在你所负责的区域 （"
								+ptblAnimalHouse.getResName()+" - "+tblAnimalHouse.getResName()+"），请审核此专题日程，特此提醒！";
								tblNotification.setMsgContent(msgContent);
								tblNotification.setMsgType(1);//系统消息
								tblNotification.setSender(getCurrentRealName());// 发送者
								tblNotification.setSendTime(new Date());// 发送时间
								List<String> resManagerList = new ArrayList<String>();
								resManagerList.add(obj.getResManager());
								tblNotificationService.save(tblNotification,resManagerList);
							}
						}
					}
				}
			}//日程发通知结束
			
			//日程签字后要和QA管理中的日程变更联系
			//（动态标志，由SD提交日程所触发）0：无变更，1：变更，2：变更处理完毕
			Map<String,Object> chkStudyIndex = qAStudyChkIndexService.getQAStudyChkIndexByStudyNo(studyNoPara);
			if(chkStudyIndex!=null)
			{
				//存在专题检查索引
				qAStudyChkIndexService.updateScheduleChangedFlag(studyNoPara,1);
				String qaInspector = (String)chkStudyIndex.get("inspector");
				if(qaInspector!=null)
				{
					User qaUser = userService.getByRealName(qaInspector);
					//通知消息
					TblNotification tblNotification = new TblNotification();
					tblNotification.setMsgTitle("SD("+tempUser.getRealName()+")，提交专题:　"+studyNoPara+"日程提醒");//消息头
					String msgContent = "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					msgContent = msgContent+"SD("+tempUser.getRealName()+")于"+currentDate+"提交了日程，"+
					"专题编号:　 "+studyNoPara+"，请及时根据日程修改相对应的检查计划，特此提醒！";
					tblNotification.setMsgContent(msgContent);
					tblNotification.setMsgType(1);//系统消息
					tblNotification.setSender(getCurrentRealName());// 发送者
					tblNotification.setSendTime(new Date());// 发送时间
					List<String> resManagerList = new ArrayList<String>();
					resManagerList.add(qaUser.getUserName());
					tblNotificationService.save(tblNotification,resManagerList);
				}
				
				
			}
			
		}
		return "sign";
	}
	
	/**
	 * 检查所有项目
	 * @param sPlan
	 * @return
	 */
	private String itemsCheck(TblStudyPlan sPlan){
		String msg = "pass";
		//日程计划
		List<TblSchedulePlan> tblSchedulePlans  = tblSchedulePlanService.getSchedulePlanList(2, sPlan.getStudyNo(), 2);
		if(tblSchedulePlans == null || tblSchedulePlans.isEmpty()){
			msg = "tblSchedulePlan";
		}
		//解剖计划验证
		List<TblDissectPlan> dissectPlans = tblDissectPlanService.getByStudyNo(sPlan);
		if ((dissectPlans == null || dissectPlans.isEmpty()) && (null != sPlan.getAnimalType() && !"".equals(sPlan.getAnimalType()))) {
			if( msg.equals("pass")){
				msg = "dissectPlan";
			}else{
				msg = msg+",dissectPlan";
			}
		}
		//课题计划检验指标
		List<TblTestIndexPlan>testIndexPlans = tblTestIndexPlanService.getByStudyNo(sPlan);
		if ((testIndexPlans == null || testIndexPlans.isEmpty()) && (null != sPlan.getAnimalType() && !"".equals(sPlan.getAnimalType()))) {
			if( msg.equals("pass")){
				msg = "testIndexPlan";
			}else{
				msg = msg+",testIndexPlan";
			}
		}
		
		//剂量设置验证
//		List<TblDoseSetting> doseSettings = tblDoseSettingService.getByStudyNo(sPlan);
//		if (doseSettings == null || doseSettings.isEmpty()) {
//			msg = "doseSetting";
//		}
		if(sPlan.getDoseSettingFlag() == 0){
			msg = "doseSetting";
		}
		return msg;
	}
	/**
	 * 检查所有项目
	 * @param sPlan
	 * @return
	 */
	private String itemsCheck(TblStudyPlan sPlan,Integer continueFlag){
		String msg = "pass";
		if(continueFlag==0||continueFlag==2)
		{
			//解剖计划验证
			List<TblDissectPlan> dissectPlans = tblDissectPlanService.getByStudyNo(sPlan);
			if ((dissectPlans == null || dissectPlans.isEmpty()) && (null != sPlan.getAnimalType() && !"".equals(sPlan.getAnimalType()))) {
				
					msg = "dissectPlan";
			}
			//课题计划检验指标
			List<TblTestIndexPlan>testIndexPlans = tblTestIndexPlanService.getByStudyNo(sPlan);
			if ((testIndexPlans == null || testIndexPlans.isEmpty()) && (null != sPlan.getAnimalType() && !"".equals(sPlan.getAnimalType()))) {
				if( msg.equals("pass")){
					msg = "testIndexPlan";
				}else{
					msg = msg+",testIndexPlan";
				}
			}
				//剂量设置验证
	//		List<TblDoseSetting> doseSettings = tblDoseSettingService.getByStudyNo(sPlan);
	//		if (doseSettings == null || doseSettings.isEmpty()) {
	//			msg = "doseSetting";
	//		}
			if(sPlan.getDoseSettingFlag() == 0){
				msg = "doseSetting";
			}
			
		}
		if(continueFlag==1||continueFlag==2)
		{
			//日程计划
			List<TblSchedulePlan> tblSchedulePlans  = tblSchedulePlanService.getSchedulePlanList(2, sPlan.getStudyNo(), 2);
			if(tblSchedulePlans == null || tblSchedulePlans.isEmpty()){
				if(continueFlag==1)
				{
					msg = "tblSchedulePlan";
				}else if(continueFlag==2)
				{
					if( !msg.equals("pass")){
						msg = msg+",tblSchedulePlan";
					}else {
						msg = "tblSchedulePlan";
					}
				}
			}
		}
		return msg;
	}
	
	/**
	 * 动物信息自动设定
	 * @param doseObj
	 * @param studyObj
	 */
	private void animalSet(TblStudyPlan studyObj){
		List<TblAnimal> animals = new ArrayList<TblAnimal>();
		//获得现有动物信息
		if(studyObj!=null){
			animals = tblAnimalService.getByStudyNo(studyObj);
		}
		//删除旧动物信息
		if (!animals.isEmpty()) {
			tblAnimalService.deleteAnimals(animals);
		}
		//清除列表内信息
		animals.clear();
		
		//获得剂量设置
		List<TblDoseSetting> doseList = tblDoseSettingService.getByStudyNo(studyObj);
		//获取字符串所需长度
		int aNum = 0;
		for(TblDoseSetting doseObj : doseList){
			aNum += doseObj.getMaleNum()+doseObj.getFemaleNum();
		}
		int length = (""+aNum).length();
		
		int a=1;
		for(TblDoseSetting doseObj : doseList){
			for(int i=0; i<doseObj.getMaleNum(); i++){
				TblAnimal tempAnimal = new TblAnimal();
				tempAnimal.setTblStudyPlan(studyObj);
				tempAnimal.setAnimalId(""+a);
				tempAnimal.setGender(1);
				animals.add(tempAnimal);
				a++;
			}
			for(int i=0; i<doseObj.getFemaleNum(); i++){
				TblAnimal tempAnimal = new TblAnimal();
				tempAnimal.setTblStudyPlan(studyObj);
				tempAnimal.setAnimalId(""+a);
				tempAnimal.setGender(2);
				animals.add(tempAnimal);
				a++;
			}
		}
		//保存
		tblAnimalService.saveAllAnimals(animals);
	}
	
	/**
	 * ajax调用临检申请验证
	 */
	public void testApplyCheck() throws Exception {
		String retString = new String();
		//获取试验计划
		TblStudyPlan sPlan = tblStudyPlanService.getById(studyNoPara);
		if (sPlan != null && "1".equals(sPlan.getStudyState())) {
			retString = "pass";
		}else {
			retString = "unPass";
		}
		response.setContentType("htmlt");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().print(retString);
	}
	
	/**
	 * ajax 显示动物品系
	 * @return
	 */
	public void animalStrain() throws Exception{
		    if(null==typeName ||typeName.equals("")){
		    	return;
		    }
		    DictAnimalType dictAnimalType = dictAnimalTypeService.getByName(typeName);
		    if(null==dictAnimalType){
		    	return;
		    }
		    String typeId=dictAnimalType.getId();
			List<DictAnimalStrain> dictAnimalStrainList= dictAnimalStrainService.findByTypeId(typeId);
			response.setCharacterEncoding("utf-8");
	    	response.getWriter().println(JsonUtil.cityList2json(dictAnimalStrainList, "strainName"));
	}
	
	/**
	 * 异常组织的病理计划编辑
	 */
	public void  editAbnViscera(){
		Map<String,Object> map = new HashMap<String, Object>();
		TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(model.getStudyNo());
		if(tblStudyPlan!=null){
			tblStudyPlan.setAbnVisceraFixedFlag(model.getAbnVisceraFixedFlag());
			tblStudyPlan.setAbnVisceraHistopathCheckFlag(model.getAbnVisceraHistopathCheckFlag());
			tblStudyPlanService.update(tblStudyPlan);
			map.put("success",true);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 异常组织的称重
	 */
	public void editAbnVisceraWeight(){
		Map<String,Object> map = new HashMap<String, Object>();
		TblStudyPlan tblStudyPlan=tblStudyPlanService.getByStudyNo(model.getStudyNo());
		if(tblStudyPlan!=null){
			tblStudyPlan.setAbnVisceraWeighFlag(model.getAbnVisceraWeighFlag());
			tblStudyPlanService.update(tblStudyPlan);
			map.put("success",true);
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
		  tblLog.setOperatOject("课题");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	//从已有的实验复制
	public void loadListByStudyType()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<TblStudyPlan> tblStudyPlanList = tblStudyPlanService.getByStudyType(studyNoPara,user);
		List<Map<String, Object>> tblStudyPlanMapList = new ArrayList<Map<String,Object>>();
		for(TblStudyPlan tblStudyPlan:tblStudyPlanList)
		{
			if(studyNoPara.equals(tblStudyPlan.getStudyNo()))
				continue;
			Map<String, Object> studyPlanMap = new HashMap<String, Object>();
			studyPlanMap.put("studyNo", tblStudyPlan.getStudyNo());
			studyPlanMap.put("sd", tblStudyPlan.getStudydirector());
			studyPlanMap.put("isGLP", tblStudyPlan.getIsGLP()+"");
			studyPlanMap.put("isValidation", tblStudyPlan.getIsValidation()+"");
			studyPlanMap.put("animalType", tblStudyPlan.getAnimalType());
			studyPlanMap.put("animalStrain", tblStudyPlan.getAnimalStrain());
			studyPlanMap.put("studyStartDate", DateUtil.dateToString(tblStudyPlan.getStudyStartDate(), "yyyy-MM-dd"));
			studyPlanMap.put("animalImportDate",DateUtil.dateToString(tblStudyPlan.getAnimalImportDate(), "yyyy-MM-dd"));
			studyPlanMap.put("preStudyDate", DateUtil.dateToString(tblStudyPlan.getPreStudyDate(), "yyyy-MM-dd"));
			studyPlanMap.put("studyBeginDate", DateUtil.dateToString(tblStudyPlan.getStudyBeginDate(), "yyyy-MM-dd"));
			studyPlanMap.put("dosageUnit", tblStudyPlan.getDosageUnit());
			studyPlanMap.put("qa", tblStudyPlan.getQa());
			studyPlanMap.put("pathDirector", tblStudyPlan.getPathDirector());
			studyPlanMap.put("clinicalTestDir", tblStudyPlan.getClinicalTestDirector());
			studyPlanMap.put("studyName", tblStudyPlan.getStudyName());
			studyPlanMap.put("studyTypeCode", tblStudyPlan.getStudyTypeCode());
			tblStudyPlanMapList.add(studyPlanMap);
		}
		String json = JsonPluginsUtil.beanListToJson(tblStudyPlanMapList);
		writeJson(json);
	}
	public void isExistGroupingDate()
	{
		System.out.println("sourceStudyPlanNo="+sourceStudyPlanNo);
		TblStudyPlan tblStudyPlanSource=tblStudyPlanService.getByStudyNo(sourceStudyPlanNo);
		Map<String, Object> map =  new HashMap();
		Date date = tblStudyPlanSource.getStudyGroupingDate();
		//返回日期
		if(date!=null)
		{
			map.put("date", DateUtil.dateToString(date,"yyyy-MM-dd"));
		}else {
			//综合管理中的计划日期或者实际日期
			//根据studyNo和nodeName获取计划分组时间
			Date date2 = tblStudyPlanService.getGroupingTimeByStudyNoAndNodeName(sourceStudyPlanNo,"试验分组");
			if(date2!=null)
			{
				map.put("date", DateUtil.dateToString(date2,"yyyy-MM-dd"));
			}else {
				
			}
		
		}
		if(map.get("date")!=null)
		{
			String json=JsonPluginsUtil.beanToJson(map);
			System.out.println("json="+json);
			writeJson(json);
		}
	}
	//@Transactional
	public void copyStudyPlan()
	{
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		System.out.println("flag="+flag+" dateOld="+dateOld+"===dateNew="+dateNew+"  源="+sourceStudyPlanNo+" 目标="+studyNoPara);
			//删除目的专题的所有日程记录
			List<TblSchedulePlan> oldPlanList = tblSchedulePlanService.getSchedulePlanList(2, studyNoPara, 2);
			List<String> oldPlanIdsList = new ArrayList<String>();
			if(oldPlanList!=null&&oldPlanList.size()>0)
			{
				for(TblSchedulePlan tsp:oldPlanList)
				{
					oldPlanIdsList.add(tsp.getScheduleID());
				}
				tblSchedulePlanService.delectTblSchedulePlans(oldPlanIdsList);
			}
		if(flag)
		{
			long time = DateUtil.stringToDate(dateNew, "yyyy-MM-dd").getTime()-DateUtil.stringToDate(dateOld, "yyyy-MM-dd").getTime();
			List<TblSchedulePlan> tspSources = tblSchedulePlanService.getSchedulePlanList(2, sourceStudyPlanNo, 2);
			//根据源专题的scheduleId和新的开始日期，增加目标专题的日程记录
			for(TblSchedulePlan tspSource:tspSources)
			{
				TblSchedulePlan tspDestPlan = new TblSchedulePlan();
				tspDestPlan.setScheduleID(tblSchedulePlanService.getKey());//日程ID
				
				tspDestPlan.setTaskType(tspSource.getTaskType());//任务类型
				
				tspDestPlan.setTaskCode(studyNoPara);//任务识别号
				
				tspDestPlan.setCodeType(tspSource.getCodeType());//识别号类型
				//tspDestPlan.setenableDate(tspSource.get);//任务生效日期
				tspDestPlan.setStartDay(tspSource.getStartDay());//任务开始天数
				
				Date startDate = new Date(tspSource.getStartTime().getTime()+time);
				tspDestPlan.setStartTime(startDate);//执行时间开始
				
				Date endDate = new Date(tspSource.getEndTime().getTime()+time);
				tspDestPlan.setEndTime(endDate);//执行结束时间
				
				tspDestPlan.setPeriod(tspSource.getPeriod());//周期
				tspDestPlan.setPeriodUnit(tspSource.getPeriodUnit());//周期单位
				tspDestPlan.setTaskEndNum(tspSource.getTaskEndNum());//结束次数
				tspDestPlan.setTaskEndDate(tspSource.getTaskEndDate());//结束日期
			//	tspDestPlan.setTaskEndState(tspSource.get);//结束状态
			//	tspDestPlan.setTaskEndType(tspSource.get);//任务结束类型
				tspDestPlan.setTaskItemType(tspSource.getTaskItemType());//任务项目类型
				tspDestPlan.setTaskName(tspSource.getTaskName());//任务名称
			//	tspDestPlan.setValidFlag(tspSource.get);//有效标志          0,无效  1，有效   （签字时改变）
				
				tspDestPlan.setCreater(tempUser.getUserName());//创建人
				tspDestPlan.setCreateDate(new Date());//创建日期
				
				tspDestPlan.setRemark(tspSource.getRemark());//备注
			//	tspDestPlan.setFinishFlag(tspSource.get);//任务完成标志
			//	tspDestPlan.setSignId(tspSource.get);//完成签字ID
				
				tspDestPlan.setTaskKind(tspSource.getTaskKind());//任务类型 
				
				tspDestPlan.setRevolution(tspSource.getRevolution());//页面显示周期组合
				tspDestPlan.setDateTime(tspSource.getDateTime());//页面显示
				
				tspDestPlan.setShowstartTime(tspSource.getShowstartTime());//执行时间开始页面显示
				tspDestPlan.setShowendTime(tspSource.getShowendTime());//执行结束时间页面显示
				
				tspDestPlan.setDateTimeDate(tspSource.getDateTimeDate());
				//tspDestPlan.setSignName(tspSource.get);
				
				tblSchedulePlanService.save(tspDestPlan);//保存
				
			}
			
			//更新分组日期
			TblStudyPlan tblStudyPlanSource=tblStudyPlanService.getByStudyNo(sourceStudyPlanNo);
			tblStudyPlanSource.setStudyGroupingDate(DateUtil.stringToDate(dateOld, "yyyy-MM-dd"));
			TblStudyPlan tblStudyPlanDest=tblStudyPlanService.getByStudyNo(studyNoPara);
			tblStudyPlanDest.setStudyGroupingDate(DateUtil.stringToDate(dateNew, "yyyy-MM-dd"));
			
			tblStudyPlanService.update(tblStudyPlanSource);
			tblStudyPlanService.update(tblStudyPlanDest);
			
		}
			
		tblStudyPlanService.copyStudyPlan(sourceStudyPlanNo,studyNoPara);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);

		//写日志
		//writeLog("新建","新建课题："+tblStudyPlan.getStudyNo());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
		
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public TblStudyPlan getTblStudyPlan() {
		return tblStudyPlan;
	}

	public void setTblStudyPlan(TblStudyPlan tblStudyPlan) {
		this.tblStudyPlan = tblStudyPlan;
	}

	public Map<String, String> getStudyTypeMap() {
		return studyTypeMap;
	}

	public void setStudyTypeMap(Map<String, String> studyTypeMap) {
		this.studyTypeMap = studyTypeMap;
	}

	public String getStudyTypeSel() {
		return studyTypeSel;
	}

	public void setStudyTypeSel(String studyTypeSel) {
		this.studyTypeSel = studyTypeSel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNewStudyNo() {
		return newStudyNo;
	}

	public void setNewStudyNo(String newStudyNo) {
		this.newStudyNo = newStudyNo;
	}

	public String getOldUlLi() {
		return oldUlLi;
	}

	public void setOldUlLi(String oldUlLi) {
		this.oldUlLi = oldUlLi;
	}

	public String getToWhere() {
		return toWhere;
	}

	public void setToWhere(String toWhere) {
		this.toWhere = toWhere;
	}

	public String getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(String animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
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



	public TblESService getTblESService() {
		return tblESService;
	}



	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}

	
	
	public String getStudyTypeCode() {
		return studyTypeCode;
	}



	public void setStudyTypeCode(String studyTypeCode) {
		this.studyTypeCode = studyTypeCode;
	}



	public String getSourceStudyPlanNo() {
		return sourceStudyPlanNo;
	}



	public void setSourceStudyPlanNo(String sourceStudyPlanNo) {
		this.sourceStudyPlanNo = sourceStudyPlanNo;
	}



	public boolean isFlag() {
		return flag;
	}



	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getDateOld() {
		return dateOld;
	}
	public void setDateOld(String dateOld) {
		this.dateOld = dateOld;
	}

	public String getDateNew() {
		return dateNew;
	}

	public void setDateNew(String dateNew) {
		this.dateNew = dateNew;
	}



	public Integer getContinueFlag() {
		return continueFlag;
	}



	public void setContinueFlag(Integer continueFlag) {
		this.continueFlag = continueFlag;
	}



	public QAStudyChkIndexService getqAStudyChkIndexService() {
		return qAStudyChkIndexService;
	}



	public void setqAStudyChkIndexService(
			QAStudyChkIndexService qAStudyChkIndexService) {
		this.qAStudyChkIndexService = qAStudyChkIndexService;
	}



	public Date getDoseEffectiveDate() {
		return doseEffectiveDate;
	}



	public void setDoseEffectiveDate(Date doseEffectiveDate) {
		this.doseEffectiveDate = doseEffectiveDate;
	}

}
