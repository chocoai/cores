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
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.jsonAndModel.JsonUtil;
import com.lanen.model.User;

import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.schedule.TblSchedulePlan;

import com.lanen.model.studyplan.DictAnimalStrain;
import com.lanen.model.studyplan.DictAnimalType;
import com.lanen.model.studyplan.DictDoseUnit;
import com.lanen.model.studyplan.DictStudyTestIndex;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.qa.QAStudyChkIndexService;
import com.lanen.service.schdeule.TblSchedulePlanService;
import com.lanen.service.studyplan.DictAnimalStrainService;
import com.lanen.service.studyplan.DictAnimalTypeService;
import com.lanen.service.studyplan.DictDoseUnitService;
import com.lanen.service.studyplan.DictStudyTestIndexService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.TblApplyReviseService;
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
	private QAStudyChkIndexService qAStudyChkIndexService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private TblTestIndexPlanService tblTestIndexPlanService;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;

	
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
			
			studyPlanMap.put("sd", tblStudyItem.getSd());
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



	public UserService getUserService() {
		return userService;
	}



	public TblTestIndexPlanService getTblTestIndexPlanService() {
		return tblTestIndexPlanService;
	}
	public void setTblTestIndexPlanService(
			TblTestIndexPlanService tblTestIndexPlanService) {
		this.tblTestIndexPlanService = tblTestIndexPlanService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public TblNotificationService getTblNotificationService() {
		return tblNotificationService;
	}
	public void setTblNotificationService(
			TblNotificationService tblNotificationService) {
		this.tblNotificationService = tblNotificationService;
	}

}
