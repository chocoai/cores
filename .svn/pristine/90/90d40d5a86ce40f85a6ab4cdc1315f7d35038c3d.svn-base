package com.lanen.view.action.studyplan;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.DictBloodCoag;
import com.lanen.model.studyplan.DictHemat;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictUrine;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblClinicalTestReq;
import com.lanen.model.studyplan.TblClinicalTestReqIndex;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;
import com.lanen.model.studyplan.TblClinicalTestReq_json;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.model.studyplan.TblTestIndexPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.DictAnimalTypeService;
import com.lanen.service.studyplan.DictReportNumberService;
import com.lanen.service.studyplan.TblAnimalService;
import com.lanen.service.studyplan.TblClinicalTestReqService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.service.studyplan.TblTestIndexPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblClinicalTestReqAction extends BaseAction<TblClinicalTestReq> {

	private static final long serialVersionUID = 1L;
	/** 临检申请Service*/
	@Resource
	private TblClinicalTestReqService tblClinicalTestReqService;
	/** 试验计划Service*/
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	/** 动物类型Service*/
	@Resource
	private DictAnimalTypeService dictAnimalTypeService;
	
	@Resource
	private DictReportNumberService dictReportNumberService;
	/** 动物信息Service*/
	@Resource
	private TblAnimalService tblAnimalService;
	/** 计划检验指标Service*/
	@Resource
	private TblTestIndexPlanService tblTestIndexPlanService;
	/**电子签名*/
	@Resource
	private TblESService tblESService;
	/***电子签名链接表*/
	@Resource
	private TblESLinkService tblESLinkService;
	/**通知信息*/
	@Resource
	private TblNotificationService tblNotificationService;
	
	/**试验项目（委托项目）service*/
	@Resource
	private TblStudyItemService tblStudyItemService;
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	
	/** 课题编号 */
	private String studyNoPara;
	/** 申请编号*/
	private int reqNoPara;
	/*** 试验计划*/
	private TblStudyPlan tblStudyPlan;
	/*** 临检申请*/
	private TblClinicalTestReq tblClinicalTestReq;
	/*** 临检申请列表*/
	private List<TblClinicalTestReq> tblClinicalTestReqList;
	
	
	/** 动物编号列表  打印时动物信息*/
	//private List<DictAnimalType> dictAnimalTypeSel;
	private List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List;
	/** 试验阶段列表*/
	private Map<Integer, String> testPhaseMap;
	/** 实验阶段次数*/
	private String testPhaseNum;
	/*** 实验阶段类型（检疫期，适应期，给药期，恢复期，其他）*/
	private int testPhaseType;
	/*** 动物信息id*/
	private List<String> animalIds;
	/**导出默认文件名*/
	private String fileName;
	//生化指标
	private List<String> dictBioChemList;
	//尿常规
	private List<String> dictUrineList;
	//血常规
	private List<String> dictHematList;
	//血凝
	private List<String> dictBloodCoagList;
	
	//生化指标待选
	private List<DictBioChem> dictBioChemListSel;
	private Map<String, String> bioChemMap;
	private List<TestIndexForList> bioChemList;//已选（后改的）
	//尿常规待选
	private List<DictUrine> dictUrineListSel;
	private Map<String, String> urineMap;
	private List<TestIndexForList> urineList;//已选（后改的）
	//血常规待选
	private List<DictHemat> dictHematListSel;
	private Map<String, String> hematMap;
	private List<TestIndexForList> hematList;//已选（后改的）
	//血凝待选
	private List<DictBloodCoag> dictBloodCoagListSel;
	private Map<String, String> bloodCoagMap;
	private List<TestIndexForList> bloodCoagList;//已选（后改的）
	
	private List<TblAnimal> tblAnimalList;
	/** 打印时查询数据参数*/
	private Map<String, Object> paraMap;
	//打印预览后回到哪儿   ，apply回编辑页面
	private String toWhere;
	
	//父类序号
	private int parentReqNo1;
	//子类序号
	private int sunReqNo;
	
	//签字类型
	private String esType;
	/**
	 * 列表显示
	 * @return
	 */
	public String list(){
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获得临检申请列表
		tblClinicalTestReqList = new ArrayList<TblClinicalTestReq>();
		tblClinicalTestReqList=tblClinicalTestReqService.getByStudyPlan(tblStudyPlan, null, null);
		
		ActionContext.getContext().put("listSize", tblClinicalTestReqList.size());
		
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
	/**加载数据list（json）*/
	public void loadList(){
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获得临检申请列表
		tblClinicalTestReqList = new ArrayList<TblClinicalTestReq>();
		tblClinicalTestReqList=tblClinicalTestReqService.getByStudyPlan(tblStudyPlan, null, null);
		
		String[] _nory_changes={"id","reqNo","testPhase","beginDate","endDate","createDate","testOther","remark","es","parentReqNo","temp"};
		String jsonStr= JsonPluginsUtil.beanListToJson(tblClinicalTestReqList, "yyyy-MM-dd", _nory_changes, true);
		writeJson(jsonStr);
	}
	/**新建/编辑页面  加载  第几次解剖下拉框数据 （json）*/
	public void select(){
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		//下拉框数据
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		//已选过的
		List<Integer> list = new ArrayList<Integer>();
		
		Map<String,String> map =null;
		map = new  HashMap<String,String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;"); 
		mapList.add(map);
		if(null!=tblAnimalList && tblAnimalList.size()>0){
			for(TblAnimal obj:tblAnimalList){
				if(!list.contains(obj.getDissectBatch())){
					map = new  HashMap<String,String>();
					map.put("id", obj.getDissectBatch()+"");
					if(obj.getDissectBatch() != 0){
						map.put("text", "第"+obj.getDissectBatch()+"次解剖");
					}else{
						if(mapList.size()>1){
							map.put("text", "其他");
						}else{
							map.put("text", "全部");
						}
					}
					mapList.add(map);
					list.add(obj.getDissectBatch());
				}
			}
		}
		String jsonStr= JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	
	
	/** 临检申请进入编辑页面*/   
	public String clinicalTestApply() {
		ActionContext ac = ActionContext.getContext();  
		Map<String, Object> session = ac.getSession();
		session.remove("parentReqNo");
		if( parentReqNo1!= 0){
			ActionContext.getContext().getSession().put("parentReqNo",parentReqNo1);
			ActionContext.getContext().put("PReqNo",parentReqNo1);
		}
		
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//判断是否有申请单编号
		if(reqNoPara == 0){
			tblClinicalTestReq = new TblClinicalTestReq();
			tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		}else {
			tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
			int ReqNoP = tblClinicalTestReq.getParentReqNo();
			if(ReqNoP != 0 ){
			  ActionContext.getContext().put("PReqNo",ReqNoP);
			}
		}
		
		if(tblClinicalTestReq.getTestPhase()!=null){
			String testPhase= tblClinicalTestReq.getTestPhase();
			String[] tempString = tblClinicalTestReq.getTestPhase().split(":");
			if (tempString.length > 1) {
//				char c;
				String c;
				if("检疫期".equals(tempString[0])){
					testPhaseType = 1;
				}
				if("适应期".equals(tempString[0])){
					testPhaseType = 2;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("给药期".equals(tempString[0])){
					testPhaseType = 3;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("恢复期".equals(tempString[0])){
					testPhaseType = 4;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("给药期末".equals(tempString[0])){
					testPhaseType = 5;
				}
				if("恢复期末".equals(tempString[0])){
					testPhaseType = 6;
				}
				
				
				if("其他".equals(tempString[0])){
					testPhaseType = 7;
					testPhaseNum = tempString[1];
				}
			}else{
				if("检疫期".equals(testPhase)){
					testPhaseType = 1;
				}else if("给药期末".equals(testPhase)){
					testPhaseType = 5;
				}else if("恢复期末".equals(testPhase)){
					testPhaseType = 6;
				}
			}
		}
		
		//设置实验阶段
		testPhaseMap = new HashMap<Integer, String>();
		testPhaseMap.put(1, "检疫期");
		testPhaseMap.put(2, "适应期");
		testPhaseMap.put(3, "给药期");
		testPhaseMap.put(4, "恢复期");
		testPhaseMap.put(5, "给药期末");
		testPhaseMap.put(6, "恢复期末");
		testPhaseMap.put(7, "其他");
		
		//获取生化指标可选项
		dictBioChemListSel = dictBioChemService.getAll();
		//获取尿常规可选项
		dictUrineListSel = dictUrineService.getAll();
		//获取血常规可选项
		dictHematListSel = dictHematService.getAll();
		//获取血凝可选项
		dictBloodCoagListSel = dictBloodCoagService.getAll();
		//生化指标可选项填装到Map中
		bioChemList =new ArrayList<TestIndexForList>();
		TestIndexForList testIndexForList = null;
		for(DictBioChem bioChemTemp : dictBioChemListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(bioChemTemp.getName());
			testIndexForList.setNameAbbr(bioChemTemp.getName()+"("+bioChemTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==1 && testIndexPlan.getTestIndex().equals(bioChemTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestIndex().equals(bioChemTemp.getName()) && tempTest.getTestitem() == 1 ){
							testIndexForList.setFlag(true);
						}
				}
			}
			bioChemList.add(testIndexForList);
		}
		//尿常规可选项填装到Map中
		urineList =new ArrayList<TestIndexForList>();
		for(DictUrine urineTemp : dictUrineListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(urineTemp.getName());
			testIndexForList.setNameAbbr(urineTemp.getName()+"("+urineTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==4 && testIndexPlan.getTestIndex().equals(urineTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestIndex().equals(urineTemp.getName()) && tempTest.getTestitem() == 4 ){
							testIndexForList.setFlag(true);
						}
				}
			}
			urineList.add(testIndexForList);
		}
		//血常规可选项填装到Map中
		hematList =new ArrayList<TestIndexForList>();
		for(DictHemat hematTemp : dictHematListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(hematTemp.getName());
			testIndexForList.setNameAbbr(hematTemp.getName()+"("+hematTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==2 &&testIndexPlan.getTestIndex().equals(hematTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestIndex().equals(hematTemp.getName())&& tempTest.getTestitem() == 2 ){
							testIndexForList.setFlag(true);
						}
				}
			}
			hematList.add(testIndexForList);
		}
		//血凝可选项填装到Map中
		bloodCoagList =new ArrayList<TestIndexForList>();
		for(DictBloodCoag bloodCoagTemp : dictBloodCoagListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(bloodCoagTemp.getName());
			testIndexForList.setNameAbbr(bloodCoagTemp.getName()+"("+bloodCoagTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==3 && testIndexPlan.getTestIndex().equals(bloodCoagTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestIndex().equals(bloodCoagTemp.getName()) && tempTest.getTestitem() == 3 ){
							testIndexForList.setFlag(true);
						}
				}
			}
			bloodCoagList.add(testIndexForList);
		}
		//获取动物信息(已选)
		animalIds = new ArrayList<String>();
		
		int parentReqNo = tblClinicalTestReq.getParentReqNo();
		if( parentReqNo1!= 0){
			//新建附件申请
			tblAnimalList = new ArrayList<TblAnimal>();
			TblClinicalTestReq parenttblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, parentReqNo1);
			tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(parenttblClinicalTestReq.getTblClinicalTestReqIndex2s());
			Date yesterday = DateUtil.getYesterday();
			for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
				TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
				if(animal!=null && (animal.getDeadFlag() == 0 ||(animal.getDeadFlag() >= 1 && yesterday.compareTo(animal.getDeadDate()) < 0))){
//				if(animal!=null && (animal.getDeadFlag() == 0 ||(animal.getDeadFlag() == 1 && yesterday.compareTo(new Date())<0))){
					tblAnimalList.add(animal);
				}
			}
			
		}else if(parentReqNo != 0){
			//编辑附加申请
			tblAnimalList = new ArrayList<TblAnimal>();
			TblClinicalTestReq parenttblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, parentReqNo);
			tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(parenttblClinicalTestReq.getTblClinicalTestReqIndex2s());
			Date yesterday = DateUtil.getYesterday();
			for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
				TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
				if(animal!=null && (animal.getDeadFlag() == 0 ||(animal.getDeadFlag() >= 1 && yesterday.compareTo(animal.getDeadDate()) < 0))){
//				if(animal!=null && (animal.getDeadFlag() == 0 ||(animal.getDeadFlag() == 1 && yesterday.compareTo(new Date())<0))){
					tblAnimalList.add(animal);
				}
			}
			List<String> idList = new ArrayList<String>();
			for(TblAnimal animal:tblAnimalList){
				idList.add(animal.getId());
			}
			//是否要排序
			boolean isSort = false;
			
			int reqNo= tblClinicalTestReq.getReqNo();
			TblClinicalTestReq tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNo);
			tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReq.getTblClinicalTestReqIndex2s());
			for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
				TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
				if(animal!=null ){
					animalIds.add(animal.getId());
					
					if(!idList.contains(animal.getId())){
						tblAnimalList.add(animal);
						isSort = true;
					}
				}
			}
			//if(isSort){
				Collections.sort(tblAnimalList, new MyComparator2());
			//}
			
		
		}else{
			//新建、编辑    非附加申请
			//获取动物信息(待选)
			tblAnimalList = tblAnimalService.getNoDieANDTodayDieByStudyNo(tblStudyPlan);
			List<String> idList = new ArrayList<String>();
			for(TblAnimal animal:tblAnimalList){
				idList.add(animal.getId());
			}
			//是否要排序
			boolean isSort = false;
			//获得动物编号
			tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReq.getTblClinicalTestReqIndex2s());
			for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
				TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
				if(animal!=null){
					animalIds.add(animal.getId());
					
					if(!idList.contains(animal.getId())){
						tblAnimalList.add(animal);
						isSort = true;
					}
				}
				
			}
			
			//if(isSort){
				//dictAnimalTypeSel = dictAnimalTypeService.findAllOrderByOrderNo();
				Collections.sort(tblAnimalList, new MyComparator2());
			//}
		}
		
		ActionContext.getContext().put("bloodCoagList", bloodCoagList);
		ActionContext.getContext().put("hematList", hematList);
		ActionContext.getContext().put("urineList", urineList);
		ActionContext.getContext().put("bioChemList", bioChemList);
		
		return "clinicalTestApply";
	}
	
	/**获取 '第'后面数字
	 * @param str
	 * @return
	 */
	private String getNum(String str){
		String result = "";
		int begin = str.indexOf("第");
		if(begin > -1){
			int i = begin+1;
			while( (i+1) <str.length() && str.substring(i, i+1).matches("[0-9]")){
				result = result+str.substring(i, i+1);
				i++;
			}
		}
		return result;
	}
	
	public  class TestIndexForList{
		private String name;
		private String nameAbbr;
		private boolean flag =false;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNameAbbr() {
			return nameAbbr;
		}
		public void setNameAbbr(String nameAbbr) {
			this.nameAbbr = nameAbbr;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
	}

	/**保存(异步)*/
	public void save() {
		//检验指标
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList =new ArrayList<TblClinicalTestReqIndex>();
		List<TblClinicalTestReqIndex2>tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
		//取得试验计划
		Integer number =    (Integer) ActionContext.getContext().getSession().get("parentReqNo");
		if(  null != number ){
			tblClinicalTestReq.setParentReqNo(number);
		}else{
			tblClinicalTestReq.setParentReqNo(0);
		}
		if(reqNoPara != 0){
			tblClinicalTestReq.setParentReqNo(reqNoPara);
		}
		
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		tblClinicalTestReq.setStudyNo(studyNoPara);
		tblClinicalTestReq.setCreateDate(new Date());
		testPhaseMap = new HashMap<Integer, String>();
		testPhaseMap.put(1, "检疫期");
		testPhaseMap.put(2, "适应期");
		testPhaseMap.put(3, "给药期");
		testPhaseMap.put(4, "恢复期");
		
		testPhaseMap.put(5, "给药期末");
		testPhaseMap.put(6, "恢复期末");
		testPhaseMap.put(7, "其他");
		String test ="";
		test = test + testPhaseMap.get(testPhaseType);
		if(testPhaseType == 2){
			test = test+":" + "第"+ testPhaseNum + "次检查";
		}
		if(testPhaseType == 3 || testPhaseType == 4){
			test = test+":" + "第"+ testPhaseNum + "周检查";
		}
		if(testPhaseType == 7){
			test = test+":" + testPhaseNum;
		}
		tblClinicalTestReq.setTestPhase(test);
		//设置检验指标
		if(null!=dictBioChemList && dictBioChemList.size()>0){
			//设置生化指标
			for(String bioChem : dictBioChemList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(1);
				tempIndex.setTestIndex(bioChem);
				DictBioChem temp = dictBioChemService.getById(bioChem);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictHematList && dictHematList.size()>0){
			//设置血常规
			for(String hemat : dictHematList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(2);
				tempIndex.setTestIndex(hemat);
				DictHemat temp = dictHematService.getById(hemat);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictBloodCoagList && dictBloodCoagList.size()>0){
			//设置血凝
			for(String bloodCoag : dictBloodCoagList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(3);
				tempIndex.setTestIndex(bloodCoag);
				DictBloodCoag temp = dictBloodCoagService.getById(bloodCoag);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictUrineList && dictUrineList.size()>0){
			//设置尿常规
			for(String urine : dictUrineList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(4);
				tempIndex.setTestIndex(urine);
				DictUrine temp = dictUrineService.getById(urine);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=animalIds && animalIds.size()>0){
			//设置动物编号
			for(String anId : animalIds){
				TblClinicalTestReqIndex2 tempIndex2 = new TblClinicalTestReqIndex2();
				tempIndex2.setTblStudyPlan(tblStudyPlan);
				TblAnimal animal = tblAnimalService.getById(anId);
				tempIndex2.setGender(animal.getGender());
				tempIndex2.setAnimalId(animal.getAnimalId());
				tempIndex2.setAnimalCode(animal.getAnimalCode());
				tblClinicalTestReqIndex2List.add(tempIndex2);
			}
		}
		Map<String,Object> json = new HashMap<String,Object>();
		if(null!=tblClinicalTestReqIndexList && tblClinicalTestReqIndexList.size()>0 && null!= tblClinicalTestReqIndex2List&& tblClinicalTestReqIndex2List.size()>0){
			//新建保存的话  flag==0
			int flag = tblClinicalTestReq.getReqNo();
//			tblClinicalTestReqService.saveOrUpdateClinicalTestReq(tblClinicalTestReq, tblClinicalTestReqIndexList, tblClinicalTestReqIndex2List);
			TblClinicalTestReq tblClinicalTestReq_back = tblClinicalTestReqService.saveOrUpdateClinicalTestReq2(tblClinicalTestReq, tblClinicalTestReqIndexList, tblClinicalTestReqIndex2List);
			
			//=tblClinicalTestReqService.getMaxReqNoByStudyPlan(tblStudyPlan);
//			if(0!=tblClinicalTestReq.getReqNo()){
//				currentReqNo =tblClinicalTestReq.getReqNo();
//				currentid =tblClinicalTestReq.getId();
//			}else{
//				currentReqNo = tblClinicalTestReqService.getMaxReqNoByStudyPlan(tblStudyPlan);
//				TblClinicalTestReq currentReq = tblClinicalTestReqService.getByStudyPlanReqNo(tblStudyPlan,currentReqNo);
//				currentid =currentReq.getId();
//			}
			if(null != tblClinicalTestReq_back){
				int currentReqNo =tblClinicalTestReq_back.getReqNo();
				String currentid =tblClinicalTestReq_back.getId();
				json.put("success",true);
				json.put("msg","保存成功");
				json.put("currentReqNo", currentReqNo);
				json.put("currentid", currentid);
				if( flag==0){
					//写日志
					writeLog("新建","课题:"+studyNoPara+"申请编号："+currentReqNo);
				}
			}else{
				json.put("success",false);
				json.put("msg","保存失败");
			}
		}else{
			json.put("success",false);
			json.put("msg","保存失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		//return "save";
	}
	
	
	/**临时申请保存*/
	public void formalsave(){
		TblClinicalTestReq tblClinicalTestReq1 =  tblClinicalTestReqService.getById(tblClinicalTestReq.getId());
		//取得试验计划
		tblClinicalTestReq1.setBeginDate(tblClinicalTestReq.getBeginDate());
		tblClinicalTestReq1.setEndDate(tblClinicalTestReq.getEndDate());
		testPhaseMap = new HashMap<Integer, String>();
		testPhaseMap.put(1, "检疫期");
		testPhaseMap.put(2, "适应期");
		testPhaseMap.put(3, "给药期");
		testPhaseMap.put(4, "恢复期");
		testPhaseMap.put(5, "给药期末");
		testPhaseMap.put(6, "恢复期末");
		testPhaseMap.put(7, "其他");
		String test ="";
		test = test + testPhaseMap.get(testPhaseType);
		if(testPhaseType == 2){
			test = test+":" + "第"+ testPhaseNum + "次检查";
		}
		if(testPhaseType == 3 || testPhaseType == 4){
			test = test+":" + "第"+ testPhaseNum + "周检查";
		}
		if(testPhaseType == 7){
			test = test+":" + testPhaseNum;
		}
		tblClinicalTestReq1.setTestPhase(test);
		tblClinicalTestReqService.update(tblClinicalTestReq1);
		int currentReqNo = tblClinicalTestReq1.getReqNo();
		String currentid =  tblClinicalTestReq1.getId();		
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success",true);
		json.put("msg","保存成功");
		json.put("currentReqNo", currentReqNo);
		json.put("currentid", currentid);
		json.put("studyNo", tblClinicalTestReq1.getStudyNo());
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**临时申请转为正式申请*/
	public void aftertoformal(){	
		TblClinicalTestReq tblClinicalTestReq1 =  tblClinicalTestReqService.getById(tblClinicalTestReq.getId());
		studyNoPara = tblClinicalTestReq1.getStudyNo();
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名 17
		TblES es = new TblES();
		//验证通过则进行一下操作
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(Integer.parseInt(esType));
		es.setEsTypeDesc("临时申请转为正式申请签字确认");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		tblClinicalTestReq1.setTemp(3);
		esLink.setTableName("TblClinicalTestReq");
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		esLink.setEsTypeDesc("临时申请转为正式申请签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			tblClinicalTestReqService.update(tblClinicalTestReq1);
			//日志录入
			writeLog("签字","课题："+studyNoPara+"临时申请转为正式申请，签字");
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
	/**保存(json),预览保存*/
	public void saveWithjson() {
		//检验指标
		List<TblClinicalTestReqIndex> tblClinicalTestReqIndexList =new ArrayList<TblClinicalTestReqIndex>();
		List<TblClinicalTestReqIndex2>tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>();
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		Integer number =    (Integer) ActionContext.getContext().getSession().get("parentReqNo");
		if(  null != number ){
			tblClinicalTestReq.setParentReqNo(number);
		}else{
			tblClinicalTestReq.setParentReqNo(0);
		}
		
		if(reqNoPara != 0){
			tblClinicalTestReq.setParentReqNo(reqNoPara);
		}
		
		tblClinicalTestReq.setTblStudyPlan(tblStudyPlan);
		tblClinicalTestReq.setStudyNo(studyNoPara);
		tblClinicalTestReq.setCreateDate(new Date());
		testPhaseMap = new HashMap<Integer, String>();
		testPhaseMap.put(1, "检疫期");
		testPhaseMap.put(2, "适应期");
		testPhaseMap.put(3, "给药期");
		testPhaseMap.put(4, "恢复期");
		testPhaseMap.put(5, "给药期末");
		testPhaseMap.put(6, "恢复期末");
		testPhaseMap.put(7, "其他");
		String test ="";
		test = test + testPhaseMap.get(testPhaseType);
		if(testPhaseType == 2){
			test = test+":" + "第"+ testPhaseNum + "次检查";
		}
		if(testPhaseType == 3 || testPhaseType == 4){
			test = test+":" + "第"+ testPhaseNum + "周检查";
		}
		if(testPhaseType == 7){
			test = test+":" + testPhaseNum;
		}
		tblClinicalTestReq.setTestPhase(test);
		//设置检验指标
		if(null!=dictBioChemList && dictBioChemList.size()>0){
			//设置生化指标
			for(String bioChem : dictBioChemList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(1);
				tempIndex.setTestIndex(bioChem);
				DictBioChem temp = dictBioChemService.getById(bioChem);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictHematList && dictHematList.size()>0){
			//设置血常规
			for(String hemat : dictHematList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(2);
				tempIndex.setTestIndex(hemat);
				DictHemat temp = dictHematService.getById(hemat);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictBloodCoagList && dictBloodCoagList.size()>0){
			//设置血凝
			for(String bloodCoag : dictBloodCoagList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(3);
				tempIndex.setTestIndex(bloodCoag);
				DictBloodCoag temp = dictBloodCoagService.getById(bloodCoag);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=dictUrineList && dictUrineList.size()>0){
			//设置尿常规
			for(String urine : dictUrineList){
				//初始化
				TblClinicalTestReqIndex tempIndex = new TblClinicalTestReqIndex();
				//设置课题类别实体
				tempIndex.setTblStudyPlan(tblStudyPlan);
				tempIndex.setTestitem(4);
				tempIndex.setTestIndex(urine);
				DictUrine temp = dictUrineService.getById(urine);
				tempIndex.setTestIndexAbbr(temp.getAbbr());
				tempIndex.setTestIndexUnit(temp.getUnit());
				tblClinicalTestReqIndexList.add(tempIndex);
			}
		}
		if(null!=animalIds && animalIds.size()>0){
			//设置动物编号
			for(String anId : animalIds){
				TblClinicalTestReqIndex2 tempIndex2 = new TblClinicalTestReqIndex2();
				tempIndex2.setTblStudyPlan(tblStudyPlan);
				TblAnimal animal = tblAnimalService.getById(anId);
				tempIndex2.setGender(animal.getGender());
				tempIndex2.setAnimalId(animal.getAnimalId());
				tempIndex2.setAnimalCode(animal.getAnimalCode());
				tblClinicalTestReqIndex2List.add(tempIndex2);
			}
		}
		
		//新建保存的话  flag==0
		int flag = tblClinicalTestReq.getReqNo();
//		tblClinicalTestReqService.saveOrUpdateClinicalTestReq(tblClinicalTestReq, tblClinicalTestReqIndexList, tblClinicalTestReqIndex2List);
		TblClinicalTestReq tblClinicalTestReq_back = tblClinicalTestReqService.saveOrUpdateClinicalTestReq2(tblClinicalTestReq, tblClinicalTestReqIndexList, tblClinicalTestReqIndex2List);
//		int reqNo=tblClinicalTestReqService.getMaxReqNoByStudyPlan(tblStudyPlan);
		if(null != tblClinicalTestReq_back){
			int reqNo = tblClinicalTestReq_back.getReqNo();
			if( flag==0){
				//写日志
				writeLog("新建","课题:"+studyNoPara+"申请编号："+reqNo);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("reqNo", reqNo);
//			if(0!=tblClinicalTestReq.getReqNo()){
//				map.put("reqNo", tblClinicalTestReq.getReqNo());
//			}
			map.put("studyNo",  tblStudyPlan.getStudyNo());
			Json json = new Json();
			json.setSuccess(true);
			json.setMsg("保存成功");
			json.setObj(map);
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}else{
			Json json = new Json();
			json.setSuccess(false);
			json.setMsg("保存失败");
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}
		
	}
	
	
	
	/**删除*/
	public void delete(){
		Json json = new Json();
		TblClinicalTestReq tblClinicalTestReq=tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		if(null!=tblClinicalTestReq && tblClinicalTestReq.getEs()==0){
			tblClinicalTestReqService.delete(tblClinicalTestReq.getId());
			json.setSuccess(true);
			json.setMsg("删除成功");
		}else{
			json.setMsg("删除失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 临时转正式
	 */
	public String toformal(){
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		
		if(tblClinicalTestReq.getTestPhase()!=null){
			String testPhase= tblClinicalTestReq.getTestPhase();
			String[] tempString = tblClinicalTestReq.getTestPhase().split(":");
			if (tempString.length > 1) {
//				char c;
				String c;
				if("检疫期".equals(tempString[0])){
					testPhaseType = 1;
				}
				if("适应期".equals(tempString[0])){
					testPhaseType = 2;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("给药期".equals(tempString[0])){
					testPhaseType = 3;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("恢复期".equals(tempString[0])){
					testPhaseType = 4;
//					c = tempString[1].charAt(1);
					c = getNum(tempString[1]);
					testPhaseNum = c+"";
				}
				if("给药期末".equals(tempString[0])){
					testPhaseType = 5;
				}
				if("恢复期末".equals(tempString[0])){
					testPhaseType = 6;
				}
				if("其他".equals(tempString[0])){
					testPhaseType = 7;
					testPhaseNum = tempString[1];
				}
			}else{
				if("检疫期".equals(testPhase)){
					testPhaseType = 1;
				}else if("给药期末".equals(testPhase)){
					testPhaseType = 5;
				}else if("恢复期末".equals(testPhase)){
					testPhaseType = 6;
				}
			}
		}
		
		//设置实验阶段
		testPhaseMap = new HashMap<Integer, String>();
		testPhaseMap.put(1, "检疫期");
		testPhaseMap.put(2, "适应期");
		testPhaseMap.put(3, "给药期");
		testPhaseMap.put(4, "恢复期");
		testPhaseMap.put(5, "给药期末");
		testPhaseMap.put(6, "恢复期末");
		testPhaseMap.put(7, "其他");
		
		//设置默认检验指标
		dictBioChemList = new ArrayList<String>();
		dictUrineList = new ArrayList<String>();
		dictHematList = new ArrayList<String>();
		dictBloodCoagList = new ArrayList<String>();
		if(tblClinicalTestReq.getReqNo()==0){
			List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
			for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
				if(testIndexPlan.getTestItem() == 1){
					dictBioChemList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if(testIndexPlan.getTestItem() == 4){
					dictUrineList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if (testIndexPlan.getTestItem() == 2) {
					dictHematList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if (testIndexPlan.getTestItem() == 3) {
					dictBloodCoagList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}
			}
		}else {
			//获得检验指标
			List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
			for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
				if(tempTest.getTestitem() == 1){
					dictBioChemList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if(tempTest.getTestitem() == 4){
					dictUrineList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if (tempTest.getTestitem() == 2) {
					dictHematList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if (tempTest.getTestitem() == 3) {
					dictBloodCoagList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}
			}
		}
		//获取动物信息
		tblAnimalList = new ArrayList<TblAnimal>();
		//获得动物编号
		tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReq.getTblClinicalTestReqIndex2s());
		for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
			TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
			if(animal!=null){
				tblAnimalList.add(animal);
			}
		}
		return "toformal";
	}
	
	/**
	 * 查看
	 * @return
	 */
	public String view(){
		//取得试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		//设置默认检验指标
		/*dictBioChemList = new ArrayList<String>();
		dictUrineList = new ArrayList<String>();
		dictHematList = new ArrayList<String>();
		dictBloodCoagList = new ArrayList<String>();
		if(tblClinicalTestReq.getReqNo()==0){
			List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
			for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
				if(testIndexPlan.getTestItem() == 1){
					dictBioChemList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if(testIndexPlan.getTestItem() == 4){
					dictUrineList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if (testIndexPlan.getTestItem() == 2) {
					dictHematList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}else if (testIndexPlan.getTestItem() == 3) {
					dictBloodCoagList.add(testIndexPlan.getTestIndex()+"("+testIndexPlan.getTestIndexAbbr()+")");
				}
			}
		}else {
			//获得检验指标
			List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
			for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
				if(tempTest.getTestitem() == 1){
					dictBioChemList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if(tempTest.getTestitem() == 4){
					dictUrineList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if (tempTest.getTestitem() == 2) {
					dictHematList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}else if (tempTest.getTestitem() == 3) {
					dictBloodCoagList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
				}
			}
		}*/
//-------------------------------------------------------------------------------------------------------
		//获取生化指标可选项
		dictBioChemListSel = dictBioChemService.getAll();
		//获取尿常规可选项
		dictUrineListSel = dictUrineService.getAll();
		//获取血常规可选项
		dictHematListSel = dictHematService.getAll();
		//获取血凝可选项
		dictBloodCoagListSel = dictBloodCoagService.getAll();
		//生化指标可选项填装到Map中
		bioChemList =new ArrayList<TestIndexForList>();
		TestIndexForList testIndexForList = null;
		for(DictBioChem bioChemTemp : dictBioChemListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(bioChemTemp.getName());
			testIndexForList.setNameAbbr(bioChemTemp.getName()+"("+bioChemTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==1 && testIndexPlan.getTestIndex().equals(bioChemTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestitem() == 1 && tempTest.getTestIndex().equals(bioChemTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}
			if(testIndexForList.isFlag())
				bioChemList.add(testIndexForList);
		}
		//尿常规可选项填装到Map中
		urineList =new ArrayList<TestIndexForList>();
		for(DictUrine urineTemp : dictUrineListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(urineTemp.getName());
			testIndexForList.setNameAbbr(urineTemp.getName()+"("+urineTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==4 && testIndexPlan.getTestIndex().equals(urineTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestitem() == 4 && tempTest.getTestIndex().equals(urineTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}
			if(testIndexForList.isFlag())
				urineList.add(testIndexForList);
		}
		//血常规可选项填装到Map中
		hematList =new ArrayList<TestIndexForList>();
		for(DictHemat hematTemp : dictHematListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(hematTemp.getName());
			testIndexForList.setNameAbbr(hematTemp.getName()+"("+hematTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==2 &&testIndexPlan.getTestIndex().equals(hematTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestitem() == 2 && tempTest.getTestIndex().equals(hematTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}
			if(testIndexForList.isFlag())
				hematList.add(testIndexForList);
		}
		//血凝可选项填装到Map中
		bloodCoagList =new ArrayList<TestIndexForList>();
		for(DictBloodCoag bloodCoagTemp : dictBloodCoagListSel){
			testIndexForList = new TestIndexForList();
			testIndexForList.setName(bloodCoagTemp.getName());
			testIndexForList.setNameAbbr(bloodCoagTemp.getName()+"("+bloodCoagTemp.getAbbr()+")");
			if(tblClinicalTestReq.getReqNo()==0){
				List<TblTestIndexPlan> testIndexPlanList = tblTestIndexPlanService.getByStudyNo(tblStudyPlan);
				for(TblTestIndexPlan testIndexPlan :  testIndexPlanList){
						if(testIndexPlan.getTestItem()==3 && testIndexPlan.getTestIndex().equals(bloodCoagTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}else {
				//获得检验指标
				List<TblClinicalTestReqIndex> clinicalTestReqIndexList = new ArrayList<TblClinicalTestReqIndex>(tblClinicalTestReq.getTblClinicalTestReqIndexs());
				for(TblClinicalTestReqIndex tempTest : clinicalTestReqIndexList){
						if(tempTest.getTestitem() == 3 && tempTest.getTestIndex().equals(bloodCoagTemp.getName())){
							testIndexForList.setFlag(true);
						}
				}
			}
			if(testIndexForList.isFlag())
				bloodCoagList.add(testIndexForList);
		}
		ActionContext.getContext().put("bloodCoagList", bloodCoagList);
		ActionContext.getContext().put("hematList", hematList);
		ActionContext.getContext().put("urineList", urineList);
		ActionContext.getContext().put("bioChemList", bioChemList);
		//------------------------------------------------------------------------------------------------
		
		
		//获取动物信息
		//tblAnimalList = new ArrayList<TblAnimal>();
		//获得动物编号
		tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReq.getTblClinicalTestReqIndex2s());
		
		
		/*for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
			TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
			if(animal!=null){
				tblAnimalList.add(animal);
			}
		}*/
		
		//dictAnimalTypeSel = dictAnimalTypeService.findAllOrderByOrderNo();
		Collections.sort(tblClinicalTestReqIndex2List, new MyComparator());
		return "view";
	}
	
	/**
	 * 电子签名,临检提交
	 * @return
	 */
	public String sign(){
		TblClinicalTestReq tblClinicalTestReq= tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		tblClinicalTestReq.setEs(1);
		//通知消息
		writeNotification(tblClinicalTestReq);
		//签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(4);
		es.setEsTypeDesc("临检申请");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey());
		esLink.setTableName("TblClinicalTestReq");
		esLink.setDataId(tblClinicalTestReq.getId());
		esLink.setTblES(es);
		esLink.setEsType(4);
		es.setEsTypeDesc("临检申请");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey());
	   try{
		  tblESService.save(es);
		  tblESLinkService.save(esLink);
		  tblClinicalTestReqService.update(tblClinicalTestReq);
		  //写日志
		  writeLog("提交","课题:"+studyNoPara+"申请编号："+reqNoPara);
	   }catch(Exception e){
		  System.out.println("执行失败，出错种类"+e.getMessage()+".");
	   }finally{ 
		  System.out.println("执行结束");
	   } 
	   return "toList";
	}
	
	/**发送通知*/
	private void writeNotification(TblClinicalTestReq tblClinicalTestReq) {
		if(null != tblClinicalTestReq ){
			//SD
			String sd = getCurrentRealName();
			//当前时间
			String currentDate = DateUtil.dateToString(new Date(), "yyyy年MM月dd日 HH时mm分");
			//当前课题编号
			String currentStudyNo = tblClinicalTestReq.getStudyNo();
			//当前申请编号
			String currentReqNo = tblClinicalTestReq.getReqNo()+"";
			Date startDate = tblClinicalTestReq.getBeginDate();
			Date endDate = tblClinicalTestReq.getEndDate();
			//计划检查日期
			String checkDate = DateUtil.dateToString(startDate, "yyyy-MM-dd");
			if(!startDate.equals(endDate)){
				checkDate = checkDate+" ～ "+DateUtil.dateToString(endDate, "yyyy-MM-dd");
			}
			//动物数(标本数)
			int animalCount = 0;
			Set<?> animalset = tblClinicalTestReq.getTblClinicalTestReqIndex2s();
			if(null != animalset){
				animalCount = animalset.size();
			}
			//检测项目
			String testItem = "";
			Set<TblClinicalTestReqIndex> indexSet = tblClinicalTestReq.getTblClinicalTestReqIndexs();
			if(null != indexSet){
				boolean testitem1 = false;//生化检验
				boolean testitem2 = false;//血液检验
				boolean testitem3 = false;//血凝检验
				boolean testitem4 = false;//尿液检验
				
				for(TblClinicalTestReqIndex obj : indexSet){
					switch (obj.getTestitem()) {
					case 1:	testitem1=true;break;
					case 2:	testitem2=true;break;
					case 3:	testitem3=true;break;
					case 4:	testitem4=true;break;
					default:
						break;
					}
				}
				
				if(testitem1){
					testItem ="生化检验";
				}
				if(testitem2){
					if("".equals(testItem)){
						testItem ="血液检验";
					}else{
						testItem =testItem+"、血液检验";
					}
				}
				if(testitem3){
					if("".equals(testItem)){
						testItem ="血凝检验";
					}else{
						testItem =testItem+"、血凝检验";
					}
				}
				if(testitem4){
					if("".equals(testItem)){
						testItem ="尿液检验";
					}else{
						testItem =testItem+"、尿液检验";
					}
				}
			}
			
			TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(currentStudyNo);
			if(null != studyItem){
				String tiNo = studyItem.getTiNo();
				String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
				DictStudyType dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
				String studyNoName = "";
				if(dictStudyType.getAnimalHave() == 1){
					studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
				}else{
					studyNoName = testItemName+studyItem.getStudyName();
				}
				
				//通知消息
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("SD("+sd+")提交临检申请单,申请单号：　"+currentReqNo+"提醒");
				String msgContent = "临检,您好<br>&nbsp;&nbsp;&nbsp;&nbsp;";
				msgContent = msgContent+"SD("+sd+")于"+currentDate+"提交了临检申请单," +
				"专题编号:　"+currentStudyNo+"专题名称:　"+studyNoName+",申请单号为"+currentReqNo+",检测项目为"+testItem+"," +
				"检验标本数 "+animalCount+" 个,计划检测日期为"+checkDate+",特此提醒";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				
				tblNotification.setSender(getCurrentRealName());
				
				tblNotification.setSendTime(new Date());
				//接收者列表
				List<String> receiverList = userService.findUserNameByPrivilegeName("临检登录");
				tblNotificationService.save(tblNotification,receiverList);
			}
		}
		
	}
	
	/**电子签名2 
	 * 临检提交  
	 */
	public void sign2(){
		TblClinicalTestReq tblClinicalTestReq= tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		tblClinicalTestReq.setEs(1);//改变提交状态    //  0,为签字   1，签字
		
		//通知消息
		writeNotification(tblClinicalTestReq);
		//签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		// 验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(4);
		es.setEsTypeDesc("临检申请");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey());
		esLink.setTableName("TblClinicalTestReq");
		esLink.setDataId(tblClinicalTestReq.getId());
		esLink.setTblES(es);
		esLink.setEsType(4);
		esLink.setEsTypeDesc("临检申请");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey());
		Json json = new Json();
		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			tblClinicalTestReqService.update(tblClinicalTestReq);
			//写日志
			writeLog("提交","课题:"+studyNoPara+"申请编号："+reqNoPara);
			json.setSuccess(true);
			json.setMsg("提交成功");
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
	
	/*转到报表页面*/
	public String ireport(){
		return "ireport";
	}
	
	/**
	 * 导出
	 */
	public String outportClinicalTestReq(){
		
		//获取试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		boolean isBigAnimal = dictAnimalTypeService.isBigAnimal(tblStudyPlan.getAnimalType());
		//获取临检申请数据
		tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, reqNoPara);
		//如果未查到数据
		if(tblClinicalTestReq == null){
			return "systemError";
		}
		paraMap = new HashMap<String, Object>();
		
		String date = "";
        String beginDateStr = DateUtil.dateToString(tblClinicalTestReq.getBeginDate(), "yyyy-MM-dd");
        String endDateStr = DateUtil.dateToString(tblClinicalTestReq.getEndDate(),"yyyy-MM-dd");
        if(beginDateStr.equals(endDateStr)){
     	   date = beginDateStr;
        }else{
     	   date = beginDateStr+" -- "+endDateStr;
        }
        paraMap.put("date",date);
        
		String number = dictReportNumberService.getNumberByReportName("临床检验申请单");
		paraMap.put("number", number == null ? "":number);

		//设置检测项目
		String testIndex1="";
		String testIndex2="";
		String testIndex3="";
		String testIndex4="";
		//拼接每一个检测项目的字符串
		/*Set<TblClinicalTestReqIndex> tblClinicalTestReqIndexs = tblClinicalTestReq.getTblClinicalTestReqIndexs();
		for (TblClinicalTestReqIndex obj: tblClinicalTestReqIndexs) {
			if(obj.getTestitem()==1){
				testIndex1= testIndex1+obj.getTestIndex()+"("+obj.getTestIndexAbbr()+")   ";
			}
			if(obj.getTestitem()==2){
				testIndex2= testIndex2+obj.getTestIndex()+"("+obj.getTestIndexAbbr()+")   ";
			}  
			if(obj.getTestitem()==3){
				testIndex3= testIndex3+obj.getTestIndex()+"("+obj.getTestIndexAbbr()+")   ";
			}
			if(obj.getTestitem()==4){
				testIndex4= testIndex4+obj.getTestIndex()+"("+obj.getTestIndexAbbr()+")   ";
			}
		}*/
		//------------------------------------------------------------------
		/*if(tempTest.getTestitem() == 1){
			dictBioChemList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
		}else if(tempTest.getTestitem() == 4){
			dictUrineList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
		}else if (tempTest.getTestitem() == 2) {
			dictHematList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
		}else if (tempTest.getTestitem() == 3) {
			dictBloodCoagList.add(tempTest.getTestIndex()+"("+tempTest.getTestIndexAbbr()+")");
		}*/
		//获取生化指标可选项
		dictBioChemListSel = dictBioChemService.getAll();
		//获取尿常规可选项
		dictUrineListSel = dictUrineService.getAll();
		//获取血常规可选项
		dictHematListSel = dictHematService.getAll();
		//获取血凝可选项
		dictBloodCoagListSel = dictBloodCoagService.getAll();
		//拼接每一个检测项目的字符串  TODO 
   		Set<TblClinicalTestReqIndex> tblClinicalTestReqIndexs = tblClinicalTestReq.getTblClinicalTestReqIndexs();
   		//存放申请指标对应缩写
   		List<String> bioChemTestIndexAbbrList = new ArrayList<String>();
   		List<String> hematTestIndexAbbrList = new ArrayList<String>();
   		List<String> bloodCoagTestIndexAbbrList = new ArrayList<String>();
   		List<String> urineTestIndexAbbrList = new ArrayList<String>();
   		for (TblClinicalTestReqIndex obj: tblClinicalTestReqIndexs) {
   			if(obj.getTestitem()==1){
   				bioChemTestIndexAbbrList.add(obj.getTestIndexAbbr());
   			}
   			if(obj.getTestitem()==2){
   				hematTestIndexAbbrList.add(obj.getTestIndexAbbr());
   			}  
   			if(obj.getTestitem()==3){
   				bloodCoagTestIndexAbbrList.add(obj.getTestIndexAbbr());
   			}
   			if(obj.getTestitem()==4){
   				urineTestIndexAbbrList.add(obj.getTestIndexAbbr());
   			}
   		}
   		for (DictBioChem obj: dictBioChemListSel) {
   			if(bioChemTestIndexAbbrList.contains(obj.getAbbr())){
   				testIndex1= testIndex1+obj.getName()+"("+obj.getAbbr()+")   ";
   			}
   		}
   		for (DictHemat obj: dictHematListSel) {
   			if(hematTestIndexAbbrList.contains(obj.getAbbr())){
   				testIndex2= testIndex2+obj.getName()+"("+obj.getAbbr()+")   ";
   			}
   		}
   		for (DictBloodCoag obj: dictBloodCoagListSel) {
   			if(bloodCoagTestIndexAbbrList.contains(obj.getAbbr())){
   				testIndex3= testIndex3+obj.getName()+"("+obj.getAbbr()+")   ";
   			}
   		}
   		for (DictUrine obj: dictUrineListSel) {
   			if(urineTestIndexAbbrList.contains(obj.getAbbr())){
   				testIndex4= testIndex4+obj.getName()+"("+obj.getAbbr()+")   ";
   			}
   		}
		//------------------------------------------------------------------
		
		String testIndex5=tblClinicalTestReq.getTestOther();
		if(testIndex5 == null || testIndex5.isEmpty()){
   			testIndex5 = "  NA";
   		}
		String testIndex6=tblClinicalTestReq.getRemark();
		if(testIndex6 == null || testIndex6.isEmpty()){
			testIndex6 = "  NA";
   		}
		String testName1="血液生化检查";
		String testName2="血液常规检查";
		String testName3="凝血功能检查";
		String testName4="尿液检查";
		String testName5="其他检测项目";
		String testName6="备注";
		
		paraMap.put("studyNo", tblStudyPlan.getStudyNo());
		if(tblStudyPlan.getAnimalStrain().contains(tblStudyPlan.getAnimalType()))
		{
			paraMap.put("animalType", tblStudyPlan.getAnimalStrain());
		}else {
			paraMap.put("animalType",tblStudyPlan.getAnimalType()+" "+tblStudyPlan.getAnimalStrain());
		}
//		paraMap.put("studyType", tblStudyPlan.getIsGLP()==1?"GLP研究":"非GLP研究");
		paraMap.put("testPhase", tblClinicalTestReq.getTestPhase());
		paraMap.put("testName1", testName1);
		paraMap.put("testIndex1", testIndex1.equals("") ? "  NA" :testIndex1);
		paraMap.put("testName2", testName2);
		paraMap.put("testIndex2", testIndex2.equals("") ? "  NA" :testIndex2);
		paraMap.put("testName3", testName3);
		paraMap.put("testIndex3", testIndex3.equals("") ? "  NA" :testIndex3);
		paraMap.put("testName4", testName4);
		paraMap.put("testIndex4", testIndex4.equals("") ? "  NA" :testIndex4);
		paraMap.put("testName5", testName5);
		paraMap.put("testIndex5", testIndex5);
		paraMap.put("testName6", testName6);
		paraMap.put("testIndex6", testIndex6);
//		paraMap.put("reqNo", tblClinicalTestReq.getReqNo());
//		String imageRead = request.getRealPath("/logo.jpg");
//		获得图片路径
//		File imageFile   =   new   File(imageRead);
//		InputStream   imageIn =null;
//		try {
//			imageIn   =   new   FileInputStream(imageFile);
//			paraMap.put("logoImage", imageIn);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		URL url = this.getClass().getResource("logo.jpg");
			paraMap.put("logoImage", url);
		
		tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(tblClinicalTestReq.getTblClinicalTestReqIndex2s());
		/*//获取动物信息
		tblAnimalList = new ArrayList<TblAnimal>();
		for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
			TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
			if(animal!=null){
				tblAnimalList.add(animal);
			}
		}*/
		
		//dictAnimalTypeSel = dictAnimalTypeService.findAllOrderByOrderNo();
		Collections.sort(tblClinicalTestReqIndex2List, new MyComparator());
		
		
		fileName = "ClinicalTestApply"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		if(!isBigAnimal)
		{
			//如果是小动物，存在animalCode只显示animalCode,不显示animalId，如果animalCode不存在则显示animalId。
			for(TblClinicalTestReqIndex2 index2:tblClinicalTestReqIndex2List)
			{
				if(null != index2.getAnimalCode() && !"".equals(index2.getAnimalCode()))
				{
					index2.setAnimalId("NA");	
				}
			}
		}	
		return "outportClinicalTestReq";
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("临检申请");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public void EstablishRelationShip(){
		//获取试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取临检申请数据
		
		TblClinicalTestReq parenttblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, parentReqNo1);
		TblClinicalTestReq sunparenttblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, sunReqNo);
		//父类动物
		//获取动物信息(已选)
		List<String> parentanimalIds = new ArrayList<String>();
		//获取动物信息(待选)
		//List<TblAnimal>  parenttblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		//获得动物编号
		tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(parenttblClinicalTestReq.getTblClinicalTestReqIndex2s());
		for(TblClinicalTestReqIndex2 tempIndex2 : tblClinicalTestReqIndex2List){
			TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, tempIndex2.getAnimalId());
			if(animal!=null){
				parentanimalIds.add(animal.getId());
			}
		}
		
		//子类类动物
		//获取动物信息(已选)
		List<String> sunanimalIds = new ArrayList<String>();
		//获取动物信息(待选)
		//List<TblAnimal>  suntblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		//获得动物编号
		tblClinicalTestReqIndex2List = new ArrayList<TblClinicalTestReqIndex2>(sunparenttblClinicalTestReq.getTblClinicalTestReqIndex2s());
		for(TblClinicalTestReqIndex2 suntempIndex2 : tblClinicalTestReqIndex2List){
			TblAnimal animal = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, suntempIndex2.getAnimalId());
			if(animal!=null){
				sunanimalIds.add(animal.getId());
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flage = true ;
		for(String sunid:sunanimalIds){
			
			for(String parentids:parentanimalIds){
				if(parentids == sunid){
					flage = true;
					break;
				}else{
					flage = false;
				}
			}
			if(!flage){
				break;
			}
		
		}	
		if(flage){
			map.put("success", true);
		}else{
			map.put("success", false);
		}		
		String json= JsonPluginsUtil.beanToJson(map);
		writeJson(json);			
	}
	
	
	public void ToConfirmTheAssociation(){
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		Json json = new Json();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(Integer.parseInt(esType));
		if(esType.equals("13")){
			es.setEsTypeDesc("临检申请关联关系完毕签字确认");
		}else if(esType.equals("14")){
			es.setEsTypeDesc("临检申请解除关联关系完毕签字确认");
		}
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		if(esType.equals("13")){
		    esLink.setTableName("TblClinicalTestReq");
		    esLink.setDataId(studyNoPara);
		    esLink.setTblES(es);
		    esLink.setEsType(Integer.parseInt(esType));
		}else if(esType.equals("14")){
			esLink.setTableName("TblClinicalTestReq");
			esLink.setDataId(studyNoPara);
			esLink.setTblES(es);
			esLink.setEsType(Integer.parseInt(esType));
		}
		if(esType.equals("13")){
			esLink.setEsTypeDesc("临检申请建立关联关系完毕签字确认");
		}else if(esType.equals("14")){
			esLink.setEsTypeDesc("临检申请解除关联关系完毕签字确认");
		}
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			if(esType.equals("13")){
				//获取试验计划
				tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
				//获取临检申请数据
				tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, sunReqNo);
				tblClinicalTestReq.setParentReqNo(parentReqNo1);
				tblClinicalTestReqService.update(tblClinicalTestReq);
			}else if(esType.equals("14")){
				//获取试验计划
				tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
				//获取临检申请数据
				tblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, sunReqNo);
				tblClinicalTestReq.setParentReqNo(0);
				tblClinicalTestReqService.update(tblClinicalTestReq);
			}
			//日志录入
			if(esType.equals("13")){
				writeLog("签字","课题："+studyNoPara+"临检申请建立关联关系配申请，签字");
			}else if(esType.equals("14")){
				writeLog("签字","课题："+studyNoPara+"临检申请解除关联关系配申请，签字");
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
	
	public void CheckTheSelectedRelationship(){
		//获取试验计划
		tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取临检申请数据
		//TblClinicalTestReq parenttblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndReqNO(studyNoPara, parentReqNo1);
		List<TblClinicalTestReq> suntblClinicalTestReq = tblClinicalTestReqService.findByStudyNoAndparentReqNo(studyNoPara, parentReqNo1);
		Map<String,Object> map = new HashMap<String,Object>();
		//int parentNo =  parenttblClinicalTestReq.getReqNo();
		if(suntblClinicalTestReq == null){
			map.put("success", false);
		}else{
			List<TblClinicalTestReq_json>  tblClinicalTestReq_jsonList =  new ArrayList<TblClinicalTestReq_json>();
			for(TblClinicalTestReq obj:suntblClinicalTestReq){
				
				TblClinicalTestReq_json tblClinicalTestReq_json = new TblClinicalTestReq_json();
				tblClinicalTestReq_json.setReqNo(obj.getReqNo());
				tblClinicalTestReq_json.setTestPhase(obj.getTestPhase());
				if(obj.getEs() == 1){
					tblClinicalTestReq_jsonList.add(tblClinicalTestReq_json);
				}
			}
			map.put("rows",tblClinicalTestReq_jsonList);
			map.put("success", true);
		}
	    String json= JsonPluginsUtil.beanToJson(map);
	    writeJson(json);	
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

	public TblClinicalTestReq getTblClinicalTestReq() {
		return tblClinicalTestReq;
	}

	public void setTblClinicalTestReq(TblClinicalTestReq tblClinicalTestReq) {
		this.tblClinicalTestReq = tblClinicalTestReq;
	}

	public Map<Integer, String> getTestPhaseMap() {
		return testPhaseMap;
	}

	public void setTestPhaseMap(Map<Integer, String> testPhaseMap) {
		this.testPhaseMap = testPhaseMap;
	}

	public String getTestPhaseNum() {
		return testPhaseNum;
	}

	public void setTestPhaseNum(String testPhaseNum) {
		this.testPhaseNum = testPhaseNum;
	}

	public List<String> getDictBioChemList() {
		return dictBioChemList;
	}

	public void setDictBioChemList(List<String> dictBioChemList) {
		this.dictBioChemList = dictBioChemList;
	}

	public List<String> getDictUrineList() {
		return dictUrineList;
	}

	public void setDictUrineList(List<String> dictUrineList) {
		this.dictUrineList = dictUrineList;
	}

	public List<String> getDictHematList() {
		return dictHematList;
	}

	public void setDictHematList(List<String> dictHematList) {
		this.dictHematList = dictHematList;
	}

	public List<String> getDictBloodCoagList() {
		return dictBloodCoagList;
	}

	public void setDictBloodCoagList(List<String> dictBloodCoagList) {
		this.dictBloodCoagList = dictBloodCoagList;
	}

	public Map<String, String> getBioChemMap() {
		return bioChemMap;
	}

	public void setBioChemMap(Map<String, String> bioChemMap) {
		this.bioChemMap = bioChemMap;
	}

	public Map<String, String> getUrineMap() {
		return urineMap;
	}

	public void setUrineMap(Map<String, String> urineMap) {
		this.urineMap = urineMap;
	}

	public Map<String, String> getHematMap() {
		return hematMap;
	}

	public void setHematMap(Map<String, String> hematMap) {
		this.hematMap = hematMap;
	}

	public Map<String, String> getBloodCoagMap() {
		return bloodCoagMap;
	}

	public void setBloodCoagMap(Map<String, String> bloodCoagMap) {
		this.bloodCoagMap = bloodCoagMap;
	}

	public List<TblAnimal> getTblAnimalList() {
		return tblAnimalList;
	}

	public void setTblAnimalList(List<TblAnimal> tblAnimalList) {
		this.tblAnimalList = tblAnimalList;
	}

	public int getTestPhaseType() {
		return testPhaseType;
	}

	public void setTestPhaseType(int testPhaseType) {
		this.testPhaseType = testPhaseType;
	}

	public List<String> getAnimalIds() {
		return animalIds;
	}

	public void setAnimalIds(List<String> animalIds) {
		this.animalIds = animalIds;
	}

	public List<TblClinicalTestReq> getTblClinicalTestReqList() {
		return tblClinicalTestReqList;
	}

	public void setTblClinicalTestReqList(
			List<TblClinicalTestReq> tblClinicalTestReqList) {
		this.tblClinicalTestReqList = tblClinicalTestReqList;
	}

	public int getReqNoPara() {
		return reqNoPara;
	}

	public void setReqNoPara(int reqNoPara) {
		this.reqNoPara = reqNoPara;
	}

	public List<TblClinicalTestReqIndex2> getTblClinicalTestReqIndex2List() {
		return tblClinicalTestReqIndex2List;
	}

	public void setTblClinicalTestReqIndex2List(
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List) {
		this.tblClinicalTestReqIndex2List = tblClinicalTestReqIndex2List;
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

	public int getParentReqNo1() {
		return parentReqNo1;
	}
	public void setParentReqNo1(int parentReqNo1) {
		this.parentReqNo1 = parentReqNo1;
	}
	public int getSunReqNo() {
		return sunReqNo;
	}
	public void setSunReqNo(int sunReqNo) {
		this.sunReqNo = sunReqNo;
	}
	public String getEsType() {
		return esType;
	}
	public void setEsType(String esType) {
		this.esType = esType;
	}
	
	class MyComparator implements Comparator<TblClinicalTestReqIndex2>{
		
		public int compare(TblClinicalTestReqIndex2 o1, TblClinicalTestReqIndex2 o2) {
			
			
			if( (o1.getAnimalCode() == null || o1.getAnimalCode().isEmpty())&&
				(o2.getAnimalCode() == null || o2.getAnimalCode().isEmpty())){
				TblAnimal animal1 = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, o1.getAnimalId());
				TblAnimal animal2 = tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, o2.getAnimalId());
				//没有动物编号按动物的顺序排序
				return animal1.getAniSerialNum() -animal2.getAniSerialNum();
			}else if((o1.getAnimalCode() == null || o1.getAnimalCode().isEmpty())){
				return -1;
			}else if((o2.getAnimalCode() == null || o2.getAnimalCode().isEmpty())){
				return 1;
			}else{
				//有动物编号按动物编号排序
				return o1.getAnimalCode().compareTo(o2.getAnimalCode());
			}
		}
		
	}
 class MyComparator2 implements Comparator<TblAnimal>{
		
		public int compare(TblAnimal o1, TblAnimal o2) {
			if( (o1.getAnimalCode() == null || o1.getAnimalCode().isEmpty())&&
				(o2.getAnimalCode() == null || o2.getAnimalCode().isEmpty())){
				//没有动物编号按动物的顺序排序
				return o1.getAniSerialNum() -o2.getAniSerialNum();
			}else if((o1.getAnimalCode() == null || o1.getAnimalCode().isEmpty())){
				return -1;
			}else if((o2.getAnimalCode() == null || o2.getAnimalCode().isEmpty())){
				return 1;
			}else{
				//有动物编号按动物编号排序
				return o1.getAnimalCode().compareTo(o2.getAnimalCode());
			}
		}
		
	}

	public DictAnimalTypeService getDictAnimalTypeService() {
		return dictAnimalTypeService;
	}
	public void setDictAnimalTypeService(DictAnimalTypeService dictAnimalTypeService) {
		this.dictAnimalTypeService = dictAnimalTypeService;
	}
	/*public List<DictAnimalType> getDictAnimalTypeSel() {
		return dictAnimalTypeSel;
	}
	public void setDictAnimalTypeSel(List<DictAnimalType> dictAnimalTypeSel) {
		this.dictAnimalTypeSel = dictAnimalTypeSel;
	}*/

}
