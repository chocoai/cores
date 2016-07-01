package com.lanen.view.action.studyplan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.lanen.model.studyplan.DictBioChem;
import com.lanen.model.studyplan.TblAnimal;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblAnimal_json;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.DictAnimalTypeService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblAnimalService;
import com.lanen.service.studyplan.TblDissectPlanService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblAnimalAction extends BaseAction<TblAnimal> {
	private static final long serialVersionUID = 1L;
	
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	// 动物信息Service
	@Resource
	private TblAnimalService tblAnimalService;
	// 动物类型Service
	@Resource
	private DictAnimalTypeService dictAnimalTypeService;
	// 试验计划Service
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	// 剂量设置Service
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	//签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	/**
	 * 解剖计划Service
	 */
	@Resource
	private TblDissectPlanService tblDissectPlanService;
	
	// 课题编号
	private String studyNoPara;
	// 列表显示内容
	private List<TblAnimal> tblAnimalList;
	private String maleNum;
	private String femaleNum;
	//计划解剖次数选择
	private List<Integer> dissectNumList;
	
	//当前序号
	private String currentSerialNum;
	private String currentSerialNum2;
	private String minAnimalId;
	private String minAnimalSex;
	private String maxAnimalId;
	private String radio;
	private List<String> radio2;
	/**
	 * 用户密码
	 */
	private String password;
	//签字类型
	private String esType;
	//页面上传的文件(动物Id号   动物编号共用)
	private File excelCodeFile;//File对象，目的是获取页面上传的文件
	private String fileName;    
	private String contentType;
	private boolean isBigAnimal;
	private String AnimaldeadReason;
	private String AnimaldeadDate;
	private int type;
	
	//上下移
	String currentAnimalId;
	String nextAnimalId;
	private String  Automatic;
	
	/**转到主页面或动物数量录入页面（雌雄树）*/
	public String animalList(){
	
		//登录信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		ActionContext.getContext().put("user", user);
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		
		isBigAnimal = dictAnimalTypeService.isBigAnimal(studyPlan.getAnimalType());
		System.out.println("studyPlan.getAnimalType()="+studyPlan.getAnimalType());
		
		
		//获取剂量设置列表
		List<TblDoseSetting> tblDoseSettingList = tblDoseSettingService.getByStudyNo(studyPlan);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		if(null != tblAnimalList && tblAnimalList.size()>0){
			ActionContext.getContext().put("listSize", tblAnimalList.size());
			//是否动物Id签字1:已签   0未签
			int isAnimalIdES = tblESLinkService.isESLink("TblAnimal",studyNoPara,5);
			//动物Id号未签字
//			if(isAnimalIdES==0){
//				//序号列表
//				List<String> serialNumList=new ArrayList<String>();
//				for(int i=0;i<tblAnimalList.size();i++){
//					serialNumList.add((i+1)+"");
//				}
//				ActionContext.getContext().put("serialNumList", serialNumList);
//				//当前序号
//				int currentSerialNum=0;
//				for(int i=tblAnimalList.size()-1;i>=0;i--){
//					if(null!=tblAnimalList.get(i).getAnimalId()&&!tblAnimalList.get(i).getAnimalId().equals("")){
//						currentSerialNum=i+2;
//						break;
//					}
//				}
//				ActionContext.getContext().put("currentSerialNum", currentSerialNum);
//				return "animalView";
//			}
			
			//是否动物编号签字 1:已签    0未签
			int isAnimalCodeES=tblESLinkService.isESLink("TblAnimal",studyNoPara,6);
			ActionContext.getContext().put("isAnimalCodeES", isAnimalCodeES);
			ActionContext.getContext().put("isAnimalIdES", isAnimalIdES);
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			String Studydirector= studyPlan.getStudydirector();
			String usernameString = tempUser.getRealName();
			if(!Studydirector.equals(usernameString)){
				ActionContext.getContext().put("left_member", "readonly");
			}
			return "animalList";
		}else{//动物数还未录入
			int minMaleNum=0;
			int minFemaleNum=0;
			if(null!=tblDoseSettingList && tblDoseSettingList.size()>0){
				for(TblDoseSetting obj:tblDoseSettingList){
					minMaleNum=minMaleNum+obj.getMaleNum();
					minFemaleNum+=obj.getFemaleNum();
				}
			}
			ActionContext.getContext().put("minMaleNum", minMaleNum);
			ActionContext.getContext().put("minFemaleNum", minFemaleNum);
			ActionContext.getContext().put("Automatic", Automatic);
			return "maleAndfemaleNum";
		}
		
	}
	/**转到动物Id号录入页面*/
	public String animalView(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		isBigAnimal = dictAnimalTypeService.isBigAnimal(studyPlan.getAnimalType());
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		if(null!=tblAnimalList && tblAnimalList.size()>0){
			ActionContext.getContext().put("listSize", tblAnimalList.size());
			
			//是否动物Id签字1:已签   0未签
			int isAnimalIdES = tblESLinkService.isESLink("TblAnimal",studyNoPara,5);
			//动物Id号未签字
			if(isAnimalIdES==0){
				//序号列表
				List<String> serialNumList=new ArrayList<String>();
				for(int i=0;i<tblAnimalList.size();i++){
					serialNumList.add((i+1)+"");
				}
				ActionContext.getContext().put("serialNumList", serialNumList);
				//当前序号
				int currentSerialNum=0;
				for(int i=tblAnimalList.size()-1;i>=0;i--){
					if(null!=tblAnimalList.get(i).getAnimalId()&&!tblAnimalList.get(i).getAnimalId().equals("")){
						currentSerialNum=i+2;
						break;
					}
				}
				ActionContext.getContext().put("currentSerialNum", currentSerialNum);
				ActionContext.getContext().put("isAnimalIdES", isAnimalIdES);
				System.out.println("isBigAnimal="+isBigAnimal);
				ActionContext.getContext().put("isBigAnimal", isBigAnimal);
				
				return "animalView";
			}
		}
		return "animalView";
	}
	/**序号下拉框 （准备数据）*/
	public void initSerialNum(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		Collections.sort(tblAnimalList,new MyComparator());
		if(null!=tblAnimalList && tblAnimalList.size()>0){
			//序号列表
//			List<String> serialNumList=new ArrayList<String>();
			List<Map<String,String>> serialNumList = new ArrayList<Map<String,String>>();
			Map<String,String> map=null;
			for(int i=0;i<tblAnimalList.size();i++){
				map = new HashMap<String,String>();
				map.put("id", (i+1)+"");
				map.put("text", (i+1)+"");
				serialNumList.add(map);
			}
			//当前序号
			int currentSerialNum=1;
			for(int i=tblAnimalList.size()-2;i>=0;i--){
				if(null!=tblAnimalList.get(i).getAnimalId()&&!tblAnimalList.get(i).getAnimalId().equals("")){
					currentSerialNum=i+2;
					break;
				}
			}
			
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("serialNumList", serialNumList);
			jsonMap.put("currentSerialNum", currentSerialNum+"");
			String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
			writeJson(jsonStr);
		}
	}
	
	/**动物雌雄数保存*/
	public String numSave(){
		//动物列表
		List<TblAnimal> animalList = new ArrayList<TblAnimal>();
		//试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//动物   雌雄数
		int intMaleNum= Integer.parseInt(maleNum);
		int intFemaleNum=Integer.parseInt(femaleNum);
		//动物
		isBigAnimal = dictAnimalTypeService.isBigAnimal(tblStudyPlan.getAnimalType());
		TblAnimal tblAnimal;
		int i=0;
		for(;i<intMaleNum;i++){
			tblAnimal= new TblAnimal();
			tblAnimal.setAniSerialNum(i+1);
			if(!isBigAnimal)
			    tblAnimal.setGender(1);
			tblAnimal.setTblStudyPlan(tblStudyPlan);
			animalList.add(tblAnimal);
		}
		for(int j=0;j<intFemaleNum;j++){
			tblAnimal= new TblAnimal();
			tblAnimal.setAniSerialNum(i+j+1);
			if(!isBigAnimal)
				tblAnimal.setGender(2);
			tblAnimal.setTblStudyPlan(tblStudyPlan);
			animalList.add(tblAnimal);
		}
		
		//保存
		tblAnimalService.saveAllAnimals(animalList);
		//日志录入
		writeLog("动物录入","课题："+studyNoPara+" 引入动物数：雄 "+maleNum+" 雌"+femaleNum);
		return "toList";
	}
	
class MyComparator implements Comparator<TblAnimal>{	
		public int compare(TblAnimal o1, TblAnimal o2) {
			if( (o1.getAnimalCode() == null || o1.getAnimalCode().isEmpty())&&
				(o2.getAnimalCode() == null || o2.getAnimalCode().isEmpty())){
				//没有动物编号按动物的顺序排序
				//return dictAnimalTypeSel.indexOf(o1)-dictAnimalTypeSel.indexOf(o1);
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

	/**动物信息列表编辑(json)*/
	public void loadList() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TblAnimal> list= tblAnimalService.getByStudyNoWithPageRows(tblStudyPlan,page,rows);
		//System.out.println("排序前："+list.size());
		Collections.sort(list,new MyComparator());
		//System.out.println("排序后：============"+list.size());
		if(null!=list && list.size()>0){
			Long total =tblAnimalService.getTotalByStudyPlan(tblStudyPlan);
			List<TblAnimal_json> tblAnimal_jsonList = new ArrayList<TblAnimal_json>();
			TblAnimal_json tblAnimal_json;
			for(TblAnimal tblAnimal:list){
				tblAnimal_json= new TblAnimal_json();
				tblAnimal_json.setAniSerialNum(tblAnimal.getAniSerialNum());
				tblAnimal_json.setAnimalCode(tblAnimal.getAnimalCode());
				tblAnimal_json.setAnimalId(tblAnimal.getAnimalId());
				tblAnimal_json.setStudyNo(tblAnimal.getTblStudyPlan().getStudyNo());
				tblAnimal_json.setGender(tblAnimal.getGender());
				tblAnimal_json.setWeight(tblAnimal.getWeight());
				tblAnimal_json.setDissectBatch(tblAnimal.getDissectBatch());
				tblAnimal_json.setId(tblAnimal.getId());
				
				
				tblAnimal_json.setDeadFlag(tblAnimal.getDeadFlag());//死亡标记
				tblAnimal_json.setDeadReason(tblAnimal.getDeadReason());//死亡原因
				if(tblAnimal.getDeadDate() != null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(tblAnimal.getDeadDate());
				tblAnimal_json.setShowdeadDate(date);
				}
				tblAnimal_json.setDeadDate(tblAnimal.getDeadDate());//死亡日期
				
				tblAnimal_json.setDeadFlagUser(tblAnimal.getDeadFlagUser());//死亡记录人
				
				tblAnimal_jsonList.add(tblAnimal_json);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows", tblAnimal_jsonList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			writeJson("");
		}
	}
	
	/**转到动物编号页面*/
	public String animalCode(){
		return "animalCode";
	}
	
	/**动物Id列表（json） */
	public void animalIdList() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TblAnimal> list= tblAnimalService.getByStudyNo(tblStudyPlan);
//		Collections.sort(list, new Comparator<TblAnimal>(){
//
//			public int compare(TblAnimal o1, TblAnimal o2) {
//				//  Auto-generated method stub
//				return o1.get;
//			}});
		if(null!=list && list.size()>0){
			Long total =tblAnimalService.getTotalByStudyPlan(tblStudyPlan);
			List<TblAnimal_json> tblAnimal_jsonList = new ArrayList<TblAnimal_json>();
			TblAnimal_json tblAnimal_json;
			for(TblAnimal tblAnimal:list){
				if(null==tblAnimal.getAnimalCode()||"".equals(tblAnimal.getAnimalCode())){
					tblAnimal_json= new TblAnimal_json();
					tblAnimal_json.setAniSerialNum(tblAnimal.getAniSerialNum());
					tblAnimal_json.setAnimalCode(tblAnimal.getAnimalCode());
					tblAnimal_json.setAnimalId(tblAnimal.getAnimalId());
					tblAnimal_json.setStudyNo(tblAnimal.getTblStudyPlan().getStudyNo());
					tblAnimal_json.setGender(tblAnimal.getGender());
					tblAnimal_json.setWeight(tblAnimal.getWeight());
					tblAnimal_json.setDissectBatch(tblAnimal.getDissectBatch());
					tblAnimal_json.setId(tblAnimal.getId());
					tblAnimal_jsonList.add(tblAnimal_json);
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows", tblAnimal_jsonList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			writeJson("");
		}
	}
	
	/**动物Code列表（json） */
	public void animalCodeList() {
		//试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//试验计划下的动物编号列表
		List<String> animalCodelist= tblAnimalService.getAnimalCodeByStudyNo(tblStudyPlan);
		//试验计划下的动物详细解剖计划列表
		List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList =tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan);
		//
		List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList_new= new ArrayList<TblAnimalDetailDissectPlan>();
		if(null!=tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size()>0){
			for(TblAnimalDetailDissectPlan obj:tblAnimalDetailDissectPlanList){
				if(!animalCodelist.contains(obj.getAnimalCode())){
					tblAnimalDetailDissectPlanList_new.add(obj);
				}
			}
			
		}
		String [] strs={"animalCode","gender"};
		String json = JsonPluginsUtil.beanListToJson(tblAnimalDetailDissectPlanList_new,strs,true);
		writeJson(json);
	}
	
	/**动物IdCode列表（json） */
	public void animalIdCodeList() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TblAnimal> list= tblAnimalService.getByStudyNo(tblStudyPlan);
		if(null!=list && list.size()>0){
			Long total =tblAnimalService.getTotalByStudyPlan(tblStudyPlan);
			List<TblAnimal_json> tblAnimal_jsonList = new ArrayList<TblAnimal_json>();
			TblAnimal_json tblAnimal_json;
			for(TblAnimal tblAnimal:list){
				if(null!=tblAnimal.getAnimalCode()&&!"".equals(tblAnimal.getAnimalCode())){
					tblAnimal_json= new TblAnimal_json();
					tblAnimal_json.setAniSerialNum(tblAnimal.getAniSerialNum());
					tblAnimal_json.setAnimalCode(tblAnimal.getAnimalCode());
					tblAnimal_json.setAnimalId(tblAnimal.getAnimalId());
					tblAnimal_json.setStudyNo(tblAnimal.getTblStudyPlan().getStudyNo());
					tblAnimal_json.setGender(tblAnimal.getGender());
					tblAnimal_json.setWeight(tblAnimal.getWeight());
					tblAnimal_json.setDissectBatch(tblAnimal.getDissectBatch());
					tblAnimal_json.setId(tblAnimal.getId());
					tblAnimal_jsonList.add(tblAnimal_json);
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows", tblAnimal_jsonList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			writeJson("");
		}
	}
	/** 动物编号保存（json） */
	public void editAnimalCode(){     
		Json json = new Json();
		if(null!=model.getId() && null!=model.getAnimalCode() &&!"".equals(model.getAnimalCode())){
			TblAnimal tblAnimal =tblAnimalService.getById(model.getId());
			TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan=tblAnimalDetailDissectPlanService.getByStudyPlanAndAnimalCode(tblAnimal.getTblStudyPlan(), model.getAnimalCode());
			tblAnimal.setDissectBatch(tblAnimalDetailDissectPlan.getDissectNum());
			tblAnimal.setAnimalCode(model.getAnimalCode());
			tblAnimalService.update(tblAnimal);
			json.setSuccess(true);
			json.setMsg("动物编号设置成功");
		}else{
			json.setMsg("动物编号设置失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/** 动物编号移除（json） */
	public void removeAnimalCode(){    
		Json json = new Json();
		if(null!=model.getId()  &&!"".equals(model.getId())){
			TblAnimal tblAnimal =tblAnimalService.getById(model.getId());
			tblAnimal.setDissectBatch(0);
			tblAnimal.setAnimalCode("");
			tblAnimalService.update(tblAnimal);
			json.setSuccess(true);
			json.setMsg("还原成功");
		}else{
			json.setMsg("还原失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	

	
	/**
	 * 动物Id号保存
	 * @return
	 */
	public String editAnimalIdSave() {
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		if(null==radio||radio.equals("")||null==currentSerialNum||currentSerialNum.equals("")||null==minAnimalId||minAnimalId.equals("")){
			return "toView";
		}
		if(radio.equals("single")){
			if(Integer.parseInt(currentSerialNum)<=tblAnimalList.size()){
				TblAnimal tblAnimal=tblAnimalList.get(Integer.parseInt(currentSerialNum)-1);
				tblAnimal.setAnimalId(minAnimalId);
				//更新一个动物Id号
				tblAnimalService.update(tblAnimal);
			}
		}else{
			if(null!=maxAnimalId && !maxAnimalId.equals("")){
				int size=tblAnimalList.size();
				int minNum=Integer.parseInt(minAnimalId);
				int maxNum=Integer.parseInt(maxAnimalId);
				int currentNum= Integer.parseInt(currentSerialNum);
				if(currentNum<size&& minNum<maxNum){
					if((size-currentNum)>=(maxNum-minNum)){
						List<TblAnimal> list= new ArrayList<TblAnimal>();
						TblAnimal tblAnimal=null;
						for(int i=0;i<=(maxNum-minNum);i++){
							tblAnimal=tblAnimalList.get(currentNum-1+i);
							tblAnimal.setAnimalId((minNum+i)+"");
							list.add(tblAnimal);
						}
						tblAnimalService.updateAnimals(list);
					}
				}
			}
		}
		
		return "toView";
	}
	/**动物Id号保存（单个录入的）*/
	public void editOneAnimalIdSave(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		isBigAnimal = dictAnimalTypeService.isBigAnimal(studyPlan.getAnimalType());
		Map<String,Object> map = new HashMap<String,Object>();
		if(Integer.parseInt(currentSerialNum)<=tblAnimalList.size()){
			if(null!=tblAnimalList && tblAnimalList.size()>0){
				//检查长度是否大于20
				boolean isTooLength = false;
				if(minAnimalId.getBytes().length>16){
					isTooLength = true;
				}
				//检验是否重复
				boolean repeat=false;
				for(TblAnimal obj:tblAnimalList){
					if(null!=obj.getAnimalId() && !obj.getAnimalId().equals("") ){
						if(obj.getAnimalId().equals(minAnimalId)){
							repeat=true;
							break;
						}
					}
				}
				if(isTooLength){
					map.put("success", false);
					map.put("msg", "动物Id号( "+minAnimalId+" )长度大于16");
					String jsonStr = JsonPluginsUtil.beanToJson(map);
					writeJson(jsonStr);
				}else if(repeat){
					map.put("success", false);
					map.put("msg", "动物Id号( "+minAnimalId+" )已存在");
					String jsonStr = JsonPluginsUtil.beanToJson(map);
					writeJson(jsonStr);
				}else{
					TblAnimal tblAnimal=tblAnimalList.get(Integer.parseInt(currentSerialNum)-1);
					tblAnimal.setAnimalId(minAnimalId);
					//System.out.println("minAnimalSex = "+minAnimalSex);
					if(isBigAnimal&&minAnimalSex!=null&&!"".equals(minAnimalSex)&&!"undefinded".equals(minAnimalSex))
					{
						int gender = Integer.parseInt(minAnimalSex);
						System.out.println("gender2333333333 = "+gender);
						tblAnimal.setGender(gender);
					}
					//更新一个动物Id号
					tblAnimalService.update(tblAnimal);
					//当前序号
					int currentSerialNum=1;
					for(int i=tblAnimalList.size()-1;i>=0;i--){
						if(null!=tblAnimalList.get(i).getAnimalId()&&!tblAnimalList.get(i).getAnimalId().equals("")){
							currentSerialNum=i+2;
							break;
						}
					}
					if(currentSerialNum>tblAnimalList.size()){
						currentSerialNum=tblAnimalList.size();
					}
					Map<String,Object> animalMap = new HashMap<String,Object>();
					animalMap.put("id", tblAnimal.getId());
					animalMap.put("animalId", tblAnimal.getAnimalId());
					animalMap.put("animalCode", tblAnimal.getAnimalCode()== null ?"":tblAnimal.getAnimalCode());
					animalMap.put("gender", tblAnimal.getGender());
					animalMap.put("weight", tblAnimal.getWeight());
					animalMap.put("dissectBatch", tblAnimal.getDissectBatch());
					animalMap.put("aniSerialNum", tblAnimal.getAniSerialNum());
					
					map.put("success", true);
					map.put("msg", "动物Id号录入成功");
					map.put("currentSerialNum", currentSerialNum);
					map.put("animal", animalMap);
					String jsonStr = JsonPluginsUtil.beanToJson(map);
					writeJson(jsonStr);
				}
			}
		}else{
			map.put("success", false);
			map.put("msg", "动物Id号录入失败");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}
	/**动物Id号保存（连续录入的）*/
	public void editMuchAnimalIdSave(){     
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		isBigAnimal = dictAnimalTypeService.isBigAnimal(studyPlan.getAnimalType());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		if(null!=tblAnimalList && tblAnimalList.size()>0&&null!=minAnimalId && !minAnimalId.equals("")&&null!=currentSerialNum &&
				!currentSerialNum.equals("")&&null!=currentSerialNum2 && !currentSerialNum2.equals("")){
			int size=tblAnimalList.size();
			int minNum=Integer.parseInt(minAnimalId);
			int currentNum= Integer.parseInt(currentSerialNum);
			int currentNum2= Integer.parseInt(currentSerialNum2);
			if(currentNum<=size&& currentNum2<=size){
					List<String> listStr = new ArrayList<String>();
					if(currentNum>currentNum2){
						currentNum=currentNum+currentNum2;
						currentNum2=currentNum-currentNum2;
						currentNum=currentNum-currentNum2;
						
					}
					int  animalIdNum =minNum;
					for(int i =currentNum ;i<=currentNum2;i++){
						listStr.add(animalIdNum+"");
						animalIdNum++;
					}
					//检验重复的号
					String errorMsg="";
					for(TblAnimal obj:tblAnimalList){
						if(listStr.contains(obj.getAnimalId())){
							errorMsg =errorMsg+obj.getAnimalId()+" ";
						}
					}
					if(!"".equals(errorMsg)){
						errorMsg="动物Id号："+errorMsg+"已存在";
						map.put("errorMsg", errorMsg);
						String jsonStr = JsonPluginsUtil.beanToJson(map);
						writeJson(jsonStr);
					}else{
						List<TblAnimal> list= new ArrayList<TblAnimal>();
						TblAnimal tblAnimal=null;
						for(int i=0;i<=(currentNum2-currentNum);i++){
							tblAnimal=tblAnimalList.get(currentNum-1+i);
							tblAnimal.setAnimalId((minNum+i)+"");
							if(isBigAnimal&&minAnimalSex!=null&&!"".equals(minAnimalSex)&&!"undefinded".equals(minAnimalSex))
							{
								int gender = Integer.parseInt(minAnimalSex);
								System.out.println("gender2222222222 = "+gender);
								tblAnimal.setGender(gender);
							}
							list.add(tblAnimal);
						}
						//更新Id号
						tblAnimalService.updateAnimals(list);
						//当前序号
						int currentSerialNum=1;
						for(int i=tblAnimalList.size()-1;i>=0;i--){
							if(null!=tblAnimalList.get(i).getAnimalId()&&!tblAnimalList.get(i).getAnimalId().equals("")){
								currentSerialNum=i+2;
								break;
							}
						}
						if(currentSerialNum>tblAnimalList.size()){
							currentSerialNum=tblAnimalList.size();
						}
						List<Map<String,Object>> animalMapList = new ArrayList<Map<String,Object>>();
						Map<String,Object> animalMap = null;
						for(TblAnimal obj:list){
							animalMap = new HashMap<String,Object>();
							animalMap.put("id", obj.getId());
							animalMap.put("animalId", obj.getAnimalId());
							animalMap.put("animalCode", obj.getAnimalCode()== null ?"":tblAnimal.getAnimalCode());
							animalMap.put("gender", obj.getGender());
							animalMap.put("weight", obj.getWeight());
							animalMap.put("dissectBatch", obj.getDissectBatch());
							animalMap.put("aniSerialNum", obj.getAniSerialNum());
							animalMapList.add(animalMap);
						}
						map.put("success", true);
						map.put("msg", "动物Id号录入成功");
						map.put("currentSerialNum", currentSerialNum);
						map.put("animalMapList", animalMapList);
						String jsonStr = JsonPluginsUtil.beanToJson(map);
						writeJson(jsonStr);
					}
				
			}else{
				map.put("errorMsg", "逻辑错误");
				String jsonStr = JsonPluginsUtil.beanToJson(map);
				writeJson(jsonStr);
			}
		}else{
			map.put("errorMsg", "与服务器交互错误");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}
	
	/**
	 * 确认id录入完成之前检查个数是否符合要求
	 */
	public  void checkMaleFemaleSize() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		int maleSize=0,femaleSize=0;
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i=0;i<tblAnimalList.size();i++){
			TblAnimal animal =  tblAnimalList.get(i);
			if(animal.getAnimalId()!=null&&!"".equals(animal.getAnimalId()))
			{
				if(animal.getGender()==1)
					maleSize+=1;
				if(animal.getGender()==2)
					femaleSize+=1;
			}	
		}
		System.out.println(" maleSize is "+maleSize+", femaleSize is"+femaleSize);
		map.put("maleSize", maleSize);
		map.put("femaleSize", femaleSize);
		//获取剂量设置列表
		int minMaleSize=0,minFemaleSize=0;
		List<TblDoseSetting> tblDoseSettingList = tblDoseSettingService.getByStudyNo(tblStudyPlan);
		for(TblDoseSetting doseSetting: tblDoseSettingList)
		{
			minMaleSize += doseSetting.getMaleNum();
			minFemaleSize += doseSetting.getFemaleNum();
		}
		map.put("minMaleSize", minMaleSize);
		map.put("minFemaleSize", minFemaleSize);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 动物Id号签字前验证
	 */
	public void signCheck() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		String msg = "";
		//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
		if(null!=tblAnimalList&&tblAnimalList.size()>0){
			for(int i=0;i<tblAnimalList.size();i++){
				if(null!=tblAnimalList.get(i).getAnimalId() && !"".equals(tblAnimalList.get(i).getAnimalId())){
					for(int j=i+1;j<tblAnimalList.size();j++){
						if(null!=tblAnimalList.get(j).getAnimalId() && !"".equals(tblAnimalList.get(j).getAnimalId())){
							if(tblAnimalList.get(i).getAnimalId().equals(tblAnimalList.get(j).getAnimalId())){
								msg+=" "+(i+1)+"和"+(j+1)+" ";
							}
						}
					}
				}else{
					map.put("animalIdError", "动物Id号未录入完毕");
					break;
				}
			}
			if(!"".equals(msg)){
			 msg="序号："+msg+"动物Id号相同";
			 map.put("msg", msg);
			}
		}else{
			msg="error";
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	
	/**动物编号签字之前的检查(json)*/
	public void checkBeforeAnimalCodeSign(){
		boolean codeEntry =true;
		//试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//试验计划下的动物编号列表
		List<String> animalCodelist= tblAnimalService.getAnimalCodeByStudyNo(tblStudyPlan);
		//试验计划下的动物详细解剖计划列表
		List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList =tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan);
		if(null!=tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size()>0){
			for(TblAnimalDetailDissectPlan obj:tblAnimalDetailDissectPlanList){
				if(!animalCodelist.contains(obj.getAnimalCode())){
					codeEntry=false;
					break;
				}
			}
			
		}
		Json json = new Json();
		if(codeEntry){
			json.setSuccess(true);
			json.setMsg("编号录入完毕");
		}else{
			json.setMsg("编号未录入完毕");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 录入的时候上下移动
	 */
	public void moveOrder()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		TblAnimal currentAnimal = tblAnimalService.getById(currentAnimalId);
		TblAnimal nextAnimal = tblAnimalService.getById(nextAnimalId);
		
		if(currentAnimal!=null&&nextAnimal!=null){
			tblAnimalService.moveOrder(currentAnimal,nextAnimal);
			map.put("success", true);
			map.put("msg","移动设置成功");
			TblAnimal currentRow = tblAnimalService.getById(currentAnimal.getId());
			Map<String, Object> animalMap = new HashMap<String,Object>();
			animalMap.put("id", currentRow.getId());
			animalMap.put("animalId", currentRow.getAnimalId());
			animalMap.put("gender", currentRow.getGender());
			animalMap.put("weight", currentRow.getWeight());
			animalMap.put("dissectBatch", currentRow.getDissectBatch());
			animalMap.put("aniSerialNum", currentRow.getAniSerialNum());
			TblAnimal nextRow = tblAnimalService.getById(nextAnimal.getId());
			Map<String, Object> nextAnimalMap = new HashMap<String,Object>();
			nextAnimalMap.put("id", nextRow.getId());
			nextAnimalMap.put("animalId", nextRow.getAnimalId());
			nextAnimalMap.put("gender", nextRow.getGender());
			nextAnimalMap.put("weight", nextRow.getWeight());
			nextAnimalMap.put("dissectBatch", nextRow.getDissectBatch());
			nextAnimalMap.put("aniSerialNum", nextRow.getAniSerialNum());

			map.put("nextRow", nextAnimalMap);
			map.put("currentRow", animalMap);
		}else{
			map.put("success", false);
			map.put("msg","移动设置失败");
		}
		//System.out.println("currentRow is "+((TblAnimal)map.get("currentRow")).getAnimalId()+"==="+((TblAnimal)map.get("currentRow")).getInputOrder());
		//System.out.println("nextRow is "+((TblAnimal)map.get("nextRow")).getAnimalId()+"==="+((TblAnimal)map.get("nextRow")).getInputOrder());
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	
	/** 签字（动物Id Code签字  5,6（esType））*/
	public String sign() {
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(Integer.parseInt(esType));
		
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		
		
		esLink.setTableName("TblAnimal");
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		if(esType.equals("5")){
			esLink.setEsTypeDesc("动物信息表动物Id号录入完毕签字确认");
		}else{
			esLink.setEsTypeDesc("动物信息表动物编号录入完毕签字确认");
		}
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
	
		tblESService.save(es);
		tblESLinkService.save(esLink);
		if(esType.equals("5")){
			es.setEsTypeDesc("动物信息表动物Id号录入完毕签字确认");
		}else{
			es.setEsTypeDesc("动物信息表动物编号录入完毕签字确认");
		}
		//日志录入
		if(esType.equals("5")){
			writeLog("签字","课题："+studyNoPara+" 动物Id号录入完毕，签字");
		}else{
			writeLog("签字","课题："+studyNoPara+" 动物编号录入完毕，签字");
		}
		 	
		return "toList";
	}
	/** 签字（动物Id 签字  5（esType））*/
	public void animalIdSign() {
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(Integer.parseInt(esType));
		if(esType.equals("5")){
			es.setEsTypeDesc("动物信息表动物Id号录入完毕签字确认");
		}else{
			es.setEsTypeDesc("动物信息表动物编号录入完毕签字确认");
		}
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		
		
		esLink.setTableName("TblAnimal");
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		if(esType.equals("5")){
			esLink.setEsTypeDesc("动物信息表动物Id号录入完毕签字确认");
		}else{
			esLink.setEsTypeDesc("动物信息表动物编号录入完毕签字确认");
		}
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		Json json = new Json();
		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			//日志录入
			if(esType.equals("5")){
				writeLog("签字","课题："+studyNoPara+" 动物Id号录入完毕，签字");
			}else{
				writeLog("签字","课题："+studyNoPara+" 动物编号录入完毕，签字");
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
	 * 编辑保存
	 * @return
	 */
	public String editSave() {
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		
		return "editSave";
	}
	
	/**
	 * 合法性检查
	 * @return
	 */
	private boolean nullCheck(List<TblAnimal> animals){
		boolean flag = true;
		for(TblAnimal a : animals){
			if(stringCherck(a.getAnimalCode())){
				flag = false;
			}else if (stringCherck(a.getWeight())) {
				flag = false;
			}else if (a.getDissectBatch() == 0) {
				flag = false;
			}
		}
		return flag;
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
		  tblLog.setOperatOject("动物信息");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	
	/**动物编号批量导入
	  */
	public void importExcel() {  
		Json json = new Json();//
		String animalId =null;//动物Id号
		String animalCode =null;//动物编号
		
		List<String> animalIdList = new ArrayList<String>();
		List<String> animalCodeList = new ArrayList<String>();
		Map<String,String> codeIdMap =new HashMap<String,String>();//编号：Id号
		//2007版读取方法
		Workbook workbook =null;
		int k =0;
		int flag =0; //指示指针访问的位置
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
													if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
														json.setMsg("请检查表头");
														break;
													}
												}else{
													json.setMsg("请检查表头");
													break;
												}
												if(null!=bCell){
													bCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物编号")){
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
												animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												if(animalId.contains(".")){
													int index = animalId.indexOf(".");
													animalId= animalId.substring(0, index);
												}
												if(null!=animalId && !animalId.equals("")){
													//添加到动物Id号列表
													animalIdList.add(animalId);
												}else{
													json.setMsg("动物Id号不能为空");
													break;
												}
												if(null!=bCell){
													bCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													animalCode=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(animalCode.contains(".")){
														int index = animalCode.indexOf(".");
														animalCode= animalCode.substring(0, index);
													}
													if(null!=animalCode && !animalCode.equals("")){
														//添加到动物编号列表
														animalCodeList.add(animalCode);
														//添加到编号Id号map中
														codeIdMap.put(animalCode, animalId);
													}
													animalId="";
													animalCode="";
												}else{
													animalId="";
												}
											}else{
												json.setMsg("动物Id号不能为空");
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
														if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
															json.setMsg("请检查表头");
															break;
														}
													}else{
														json.setMsg("请检查表头");
														break;
													}
													if(null!=bCell){
														aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物编号")){
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
													animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(animalId.contains(".")){
														int index = animalId.indexOf(".");
														animalId= animalId.substring(0, index);
													}
													if(null!=animalId && !animalId.equals("")){
														//添加到动物Id号列表
														animalIdList.add(animalId);
													}else{
														json.setMsg("动物Id号不能为空");
														break;
													}
													if(null!=bCell){
														bCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														animalCode=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
														if(animalCode.contains(".")){
															int index = animalCode.indexOf(".");
															animalCode= animalCode.substring(0, index);
														}
														if(null!=animalCode && !animalCode.equals("")){
															//添加到动物编号列表
															animalCodeList.add(animalCode);
															//添加到编号Id号map中
															codeIdMap.put(animalCode, animalId);
														}
														animalId="";
														animalCode="";
													}else{
														animalId="";
													}
												}else{
													json.setMsg("动物Id号不能为空");
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
				//验证编号的完整性，Id号有没有错的，性别是否匹配
				TblStudyPlan tblStudyPlan = new TblStudyPlan();
				tblStudyPlan.setStudyNo(studyNoPara);
				//1.验证编号的完整性（不多不少，完全相同）
				//动物详细解剖计划表 
				List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList = tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan);
				if(null!=tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size()>0){
					//动物详细解剖计划表     动物编号列表
					List<String> detailCodeList = new ArrayList<String>();
					//编号：性别
					Map<String,Integer> codeSexMap = new HashMap<String,Integer>();
					//编号：解剖次数
					Map<String,Integer> codeDissectNumMap = new HashMap<String,Integer>();
					
					for(TblAnimalDetailDissectPlan obj:tblAnimalDetailDissectPlanList){
						detailCodeList.add(obj.getAnimalCode());
						codeSexMap.put(obj.getAnimalCode(), obj.getGender());
						codeDissectNumMap.put(obj.getAnimalCode(), obj.getDissectNum());
					}
					boolean codeBoolean = true;
					if(animalCodeList.size() == detailCodeList.size()){
						Collections.sort(animalCodeList);
						Collections.sort(detailCodeList);
						for(int i=0;i<animalCodeList.size();i++){
							if(!animalCodeList.get(i).equals(detailCodeList.get(i))){
								codeBoolean=false;
								break;
							}
						}
					}else{
						codeBoolean=false;
					}
					if(codeBoolean){//动物编号相同
						//2.检查是否有不正确的Id号
						List<TblAnimal> tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
						if(null!=tblAnimalList && tblAnimalList.size()>0){
							//待编号      Id号list   
							List<String> detailIdList = new ArrayList<String>();
							Map<String,Integer> idSexMap = new HashMap<String,Integer>();
							for(TblAnimal obj:tblAnimalList){
								detailIdList.add(obj.getAnimalId());
								idSexMap.put(obj.getAnimalId(), obj.getGender());
							}
							//判断是否有     非该list表中的Id号
							boolean idBoolean =true;
							for(String str :animalIdList){
								if(!detailIdList.contains(str)){
									json.setMsg("动物Id号'"+str+"'不存在");
									idBoolean=false;
									break;
								}
							}
							if(idBoolean){//通过
								//3.动物性别是否匹配
								boolean sexBoolean =true;
								for(String code:animalCodeList){
									int codeSex = codeSexMap.get(code);
									int idSex = idSexMap.get(codeIdMap.get(code));
									if(codeSex != idSex){
										json.setMsg("动物编号'"+code+"'与动物Id号‘"+codeIdMap.get(code)+"'性别不匹配");
										sexBoolean =false;
										break;
									}
								}
								if(sexBoolean){//通过
									//进行编号
									TblAnimal tblAnimal = null;
									for(String code :animalCodeList){
										tblAnimal= tblAnimalService.getByStudyPlanAndAnimalId(tblStudyPlan, codeIdMap.get(code));
										tblAnimal.setAnimalCode(code);
										tblAnimal.setDissectBatch(codeDissectNumMap.get(code));
										tblAnimalService.update(tblAnimal);
									}
									json.setSuccess(true);
									json.setMsg("动物编号批量导入成功");
								}
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
			if("application/octet-stream".equals(contentType)){
				json.setMsg("文件被其他程序占用");
			}else{
				json.setMsg("请导入excel类型文件");
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**小动物Id号批量导入
	  */
	public void importIdExcel() {  
		System.out.println("小动物id批量导入");
		Json json = new Json();//
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		String animalId =null;//动物Id号
		
		List<String> animalIdList = new ArrayList<String>();
		//2007版读取方法
		Workbook workbook =null;
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
											System.out.println("lastCellNum is "+lastCellNum);
											if(lastCellNum<2){
												XSSFCell aCell = aRow.getCell(0);
												if(null!=aCell){
													aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
														json.setMsg("请检查表头,表头应为：动物Id号");
														break;
													}
												}else{
													json.setMsg("请检查表头，表头不能为空");
													break;
												}
											}else{
												json.setMsg("请检查表头，表应该只有一列");
												break;
											}
										}else{
											//数据行
											XSSFCell aCell = aRow.getCell(0);
											if(null!=aCell){//id号为空
												aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
												animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												if(animalId.contains(".")){
													int index = animalId.indexOf(".");
													animalId= animalId.substring(0, index);
												}
												if(null!=animalId && !animalId.equals("")){
													if(animalId.getBytes().length<17){
														//添加到动物Id号列表
														animalIdList.add(animalId);
														animalId="";
													}else{
														json.setMsg("动物Id号'"+animalId+"'长度超过16");
														break;
													}
												}else{
													json.setMsg("动物Id号不能为空");
													break;
												}
											}else{
												json.setMsg("动物Id号不能为空");
												break;
											}
										}
									}else{//end 当前行不为空
										if(rowNumOfSheet ==0){
											json.setMsg("请检查表，表中无数据");
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
												System.out.println("lastCellNum is "+lastCellNum);
												if(lastCellNum<2){
													HSSFCell aCell = aRow.getCell(0);
													if(null!=aCell){
														aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
															json.setMsg("请检查表头，表头应为：动物Id号");
															break;
														}
													}else{
														json.setMsg("请检查表头，表头不能为空");
														break;
													}
												}else{
													json.setMsg("请检查表头，表应只有一列");
													break;
												}
											}else{
												//数据行
												HSSFCell aCell = aRow.getCell(0);
												if(null!=aCell){//id号为空，则code就不检查了
													aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
													animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(animalId.contains(".")){
														int index = animalId.indexOf(".");
														animalId= animalId.substring(0, index);
													}
													if(null!=animalId && !animalId.equals("")){
														if(animalId.getBytes().length<17){
															//添加到动物Id号列表
															animalIdList.add(animalId);
															animalId="";
														}else{
															json.setMsg("动物Id号'"+animalId+"'长度超过16");
															break;
														}
													}else{
														json.setMsg("动物Id号不能为空");
														break;
													}
												}else{
													json.setMsg("动物Id号不能为空");
													break;
												}
											}
										}else{//end 当前行不为空
											if(rowNumOfSheet ==0){
												json.setMsg("请检查表，表内无数据");
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
				// 1.验证动物Id号       个数对不对
				TblStudyPlan tblStudyPlan = new TblStudyPlan();
				tblStudyPlan.setStudyNo(studyNoPara);
				//获取动物信息列表
				tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
				if(tblAnimalList.size() == animalIdList.size()){
					
					//2.验证Id 号有没有重复的
					boolean repeat =false;
					for(int i=0;i<animalIdList.size()-1;i++){
						for(int j=i+1;j<animalIdList.size();j++){
							if(animalIdList.get(i).equals(animalIdList.get(j))){
								repeat=true;
								json.setMsg("动物Id号‘"+animalIdList.get(i)+"’重复");
								break;
							}
						}
						if(repeat){
							break;
						}
					}
					if(!repeat){//没有重复的动物Id号
						List<TblAnimal> list= new ArrayList<TblAnimal>();
						TblAnimal tblAnimal=null;
						for(int i=0;i<animalIdList.size();i++){
							tblAnimal=tblAnimalList.get(i);
							tblAnimal.setAnimalId(animalIdList.get(i));
							list.add(tblAnimal);
						}
						//更新Id号
						tblAnimalService.updateAnimals(list);
						//当前序号
						int currentSerialNum=list.size();
						List<Map<String,Object>> animalMapList = new ArrayList<Map<String,Object>>();
						Map<String,Object> animalMap = null;
						for(TblAnimal obj:list){
							animalMap = new HashMap<String,Object>();
							animalMap.put("id", obj.getId());
							animalMap.put("animalId", obj.getAnimalId());
							animalMap.put("animalCode", obj.getAnimalCode()== null ?"":tblAnimal.getAnimalCode());
							animalMap.put("gender", obj.getGender());
							animalMap.put("weight", obj.getWeight());
							animalMap.put("dissectBatch", obj.getDissectBatch());
							animalMap.put("aniSerialNum", obj.getAniSerialNum());
							animalMapList.add(animalMap);
						}
						json.setSuccess(true);
						map.put("success", true);
						map.put("msg", "动物Id号批量导入成功");
						map.put("currentSerialNum", currentSerialNum);
						map.put("animalMapList", animalMapList);
						
					}
				}else{
					json.setMsg("动物Id号数量不对");
				}
			}
		}else{//文件类型不对
			if("application/octet-stream".equals(contentType)){
				json.setMsg("文件被其他程序占用");
			}else{
				json.setMsg("请导入excel类型文件");
			}
		}
		if(json.isSuccess()){
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}else{
			String jsonStr = JsonPluginsUtil.beanToJson(json);
			writeJson(jsonStr);
		}
	}
	/**大动物Id号批量导入
	  */
	public void importIdExcelBigAnimal() { 
		System.out.println("大动物id批量导入");
		Json json = new Json();//
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		String animalId =null;//动物Id号
		String gender = null;//动物性别
		
		List<String> animalIdList = new ArrayList<String>();
		List<String> genderList = new ArrayList<String>();
		//2007版读取方法
		Workbook workbook =null;
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
							System.out.println("aSheet.getLastRowNum() is "+aSheet.getLastRowNum());
							if(aSheet.getLastRowNum()>1){
								for(int rowNumOfSheet =0;rowNumOfSheet <=aSheet.getLastRowNum();rowNumOfSheet++){
									//进入aSheet的行(row)的循环
									if(null!= aSheet.getRow(rowNumOfSheet)){
										XSSFRow aRow =aSheet.getRow(rowNumOfSheet);
										if(rowNumOfSheet ==0){//表头
											int lastCellNum =aRow.getLastCellNum();
											System.out.println("lastCellNum is "+lastCellNum);
											if(lastCellNum>=2){
												XSSFCell aCell = aRow.getCell(0);
												XSSFCell bCell = aRow.getCell(1);
												if(null!=aCell&&null!=bCell){
													aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													bCell.setCellType(XSSFCell.CELL_TYPE_STRING);
													if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
														json.setMsg("请检查表头，第一列的表头应为：动物Id号");
														break;
													}
													if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("性别")){
														json.setMsg("请检查表头，第二列表头应为：性别");
														break;
													}
												}else{
													json.setMsg("请检查表头，两列都不能为空");
													break;
												}
											}else{
												json.setMsg("请检查表头，应有两列");
												break;
											}
										}else{
											//数据行
											XSSFCell aCell = aRow.getCell(0);
											XSSFCell bCell = aRow.getCell(1);
											if(null!=aCell&&null!=bCell){//id号为空
												aCell.setCellType(XSSFCell.CELL_TYPE_STRING);
												bCell.setCellType(XSSFCell.CELL_TYPE_STRING);
												animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												gender=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												
												if(animalId.contains(".")){
													int index = animalId.indexOf(".");
													animalId= animalId.substring(0, index);
												}
												if(null!=animalId && !animalId.equals("")&&gender!=null&&!"".equals(gender)){
													if(animalId.getBytes().length<17){
														//添加到动物Id号列表
														animalIdList.add(animalId);
														animalId="";
													}else{
														json.setMsg("动物Id号'"+animalId+"'长度超过16");
														break;
													}
													
													if(gender.equalsIgnoreCase("♂")||gender.equalsIgnoreCase("雄")||gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("雄性")
															||gender.equalsIgnoreCase("♀")||gender.equalsIgnoreCase("雌")||gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("雌性"))
													{
														if(gender.equalsIgnoreCase("♂")||gender.equalsIgnoreCase("雄")||gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("雄性"))
															gender = "1";
														else if(gender.equalsIgnoreCase("♀")||gender.equalsIgnoreCase("雌")||gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("雌性"))
																gender = "2";
														genderList.add(gender);
													}else {
														json.setMsg("性别可以为♂，雄，male，雄性，♀，雌，female，雌性中的任意一个");
														break;
													}
												}else{
													json.setMsg("动物Id号不能为空");
													break;
												}
											}else{
												json.setMsg("动物Id号和性别不能为空");
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
												System.out.println("lastCellNum = "+lastCellNum);
												if(lastCellNum>=2){
													HSSFCell aCell = aRow.getCell(0);
													if(null!=aCell){
														aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														System.out.println("表头是："+aCell.getStringCellValue());
														if(!aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("动物Id号")){
															json.setMsg("请检查表头");
															break;
														}
													}else{
														json.setMsg("请检查表头,第一列的名字应该是：动物Id号");
														break;
													}
													HSSFCell bCell = aRow.getCell(1);
													if(null!=bCell){
														bCell.setCellType(HSSFCell.CELL_TYPE_STRING);
														System.out.println("第二列表头是："+bCell.getStringCellValue());
														if(!bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("性别")){
															json.setMsg("请检查表头，第二列的名字应该是：性别");
															break;
														}
													}else{
														json.setMsg("请检查第二列表头");
														break;
													}
												}else{
													json.setMsg("请检查表头，表内容应该是两列");
													break;
												}
											}else{
												//数据行
												HSSFCell aCell = aRow.getCell(0);
												HSSFCell bCell = aRow.getCell(1);
												if(null!=aCell&&null!=bCell){//id号为空，则code就不检查了
													aCell.setCellType(HSSFCell.CELL_TYPE_STRING);
													bCell.setCellType(HSSFCell.CELL_TYPE_STRING);
													animalId=aCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													gender=bCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													
													if(animalId.contains(".")){
														int index = animalId.indexOf(".");
														animalId= animalId.substring(0, index);
													}
													if(null!=animalId && !animalId.equals("")&&gender!=null&&!"".equals(gender)){
														if(animalId.getBytes().length<17){
															//添加到动物Id号列表
															animalIdList.add(animalId);
															animalId="";
														}else{
															json.setMsg("动物Id号'"+animalId+"'长度超过16");
															break;
														}
														if(gender.equalsIgnoreCase("♂")||gender.equalsIgnoreCase("雄")||gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("雄性")
																||gender.equalsIgnoreCase("♀")||gender.equalsIgnoreCase("雌")||gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("雌性"))
														{
															if(gender.equalsIgnoreCase("♂")||gender.equalsIgnoreCase("雄")||gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("雄性"))
																gender = "1";
															else if(gender.equalsIgnoreCase("♀")||gender.equalsIgnoreCase("雌")||gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("雌性"))
																	gender = "2";
															genderList.add(gender);
														}else {
															json.setMsg("性别可以为♂，雄，male，雄性，♀，雌，female，雌性中的任意一个");
															break;
														}
													}else{
														json.setMsg("动物Id号和性别都不能为空");
														break;
													}
												}else{
													json.setMsg("动物Id号不能为空");
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
				// 1.验证动物Id号       个数对不对
				TblStudyPlan tblStudyPlan = new TblStudyPlan();
				tblStudyPlan.setStudyNo(studyNoPara);
				//获取动物信息列表
				tblAnimalList = tblAnimalService.getByStudyNo(tblStudyPlan);
				System.out.println("animalIdlist size is "+animalIdList.size()+", genderList size is "+genderList.size());
				if(tblAnimalList.size() == animalIdList.size()){
					
					//2.验证Id 号有没有重复的
					boolean repeat =false;
					for(int i=0;i<animalIdList.size()-1;i++){
						for(int j=i+1;j<animalIdList.size();j++){
							if(animalIdList.get(i).equals(animalIdList.get(j))){
								repeat=true;
								json.setMsg("动物Id号‘"+animalIdList.get(i)+"’重复");
								break;
							}
						}
						if(repeat){
							break;
						}
					}
					if(!repeat){//没有重复的动物Id号
						List<TblAnimal> list= new ArrayList<TblAnimal>();
						TblAnimal tblAnimal=null;
						for(int i=0;i<animalIdList.size();i++){
							tblAnimal=tblAnimalList.get(i);
							tblAnimal.setAnimalId(animalIdList.get(i));
							tblAnimal.setGender(Integer.parseInt(genderList.get(i)));
							list.add(tblAnimal);
						}
						//更新Id号
						tblAnimalService.updateAnimals(list);
						//当前序号
						int currentSerialNum=list.size();
						List<Map<String,Object>> animalMapList = new ArrayList<Map<String,Object>>();
						Map<String,Object> animalMap = null;
						for(TblAnimal obj:list){
							animalMap = new HashMap<String,Object>();
							animalMap.put("id", obj.getId());
							animalMap.put("animalId", obj.getAnimalId());
							animalMap.put("animalCode", obj.getAnimalCode()== null ?"":tblAnimal.getAnimalCode());
							animalMap.put("gender", obj.getGender());
							animalMap.put("weight", obj.getWeight());
							animalMap.put("dissectBatch", obj.getDissectBatch());
							animalMap.put("aniSerialNum", obj.getAniSerialNum());
							animalMapList.add(animalMap);
						}
						json.setSuccess(true);
						map.put("success", true);
						map.put("msg", "动物Id号批量导入成功");
						map.put("currentSerialNum", currentSerialNum);
						map.put("animalMapList", animalMapList);
						
					}
				}else{
					json.setMsg("动物Id号数量不对,应为"+tblAnimalList.size()+"个");
				}
			}
		}else{//文件类型不对
			if("application/octet-stream".equals(contentType)){
				json.setMsg("文件被其他程序占用");
			}else{
				json.setMsg("请导入excel类型文件");
			}
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
	 * 动物死亡
	 * @param str
	 * @return
	 */
	public String animalDie(){
		return "animalDie";
	}
	
	
	
	/**动物信息列表编辑(json)*/
	public void NodieloadList() {
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		List<TblAnimal> list= tblAnimalService.getNoDieByStudyNo(tblStudyPlan);
		if(null!=list && list.size()>0){
			Long total =tblAnimalService.getTotalByStudyPlan(tblStudyPlan);
			List<TblAnimal_json> tblAnimal_jsonList = new ArrayList<TblAnimal_json>();
			TblAnimal_json tblAnimal_json;
			for(TblAnimal tblAnimal:list){
				tblAnimal_json= new TblAnimal_json();
				tblAnimal_json.setAnimalId(tblAnimal.getAnimalId());
				tblAnimal_json.setAniSerialNum(tblAnimal.getAniSerialNum());
				tblAnimal_json.setAnimalCode(tblAnimal.getAnimalCode());
				tblAnimal_json.setAnimalId(tblAnimal.getAnimalId());
				tblAnimal_json.setStudyNo(tblAnimal.getTblStudyPlan().getStudyNo());
				tblAnimal_json.setGender(tblAnimal.getGender());
				tblAnimal_json.setWeight(tblAnimal.getWeight());
				if( tblAnimal.getDissectBatch() != 0){
				TblDissectPlan tblDissectPlan=tblDissectPlanService.getByStudyNo(tblStudyPlan,tblAnimal.getDissectBatch());
				Date beginDate = tblDissectPlan.getBeginDate();
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(beginDate);
				tblAnimal_json.setShowdissectBatch(date);
				}
				tblAnimal_json.setDissectBatch(tblAnimal.getDissectBatch());
				tblAnimal_json.setId(tblAnimal.getId());
				
				tblAnimal_json.setDeadFlag(tblAnimal.getDeadFlag());//死亡标记
				tblAnimal_json.setDeadReason(tblAnimal.getDeadReason());//死亡原因
				tblAnimal_json.setDeadDate(tblAnimal.getDeadDate());//死亡日期
				tblAnimal_json.setDeadFlagUser(tblAnimal.getDeadFlagUser());
				
				tblAnimal_jsonList.add(tblAnimal_json);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", total);
			map.put("rows", tblAnimal_jsonList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			writeJson("");
		}
	}
	
	
	/**
	 * 计划解剖下拉选
	 * @return
	 */
	public void Plananatomy(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		//获取动物信息列表
		tblAnimalList = tblAnimalService.getByStudyNo(studyPlan);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(null!=tblAnimalList && tblAnimalList.size()>0){
			//序号列表
//			List<String> serialNumList=new ArrayList<String>();
			List<Map<String,String>> serialNumList = new ArrayList<Map<String,String>>();
			List<TblDissectPlan> tblDissectPlanList=tblDissectPlanService.getByStudyNo(studyPlan);
			Map<String,String> map=null;
			if(tblDissectPlanList.size()>0){
			for(int i=1;i<tblDissectPlanList.size()+1;i++){
				map = new HashMap<String,String>();
				map.put("id", i+"");
				TblDissectPlan tblDissectPlan=tblDissectPlanService.getByStudyNo(studyPlan,i);
				Date beginDate = tblDissectPlan.getBeginDate();
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(beginDate);
				map.put("text","第"+i+"次   "+"解剖日期："+date);
				serialNumList.add(map);
			}
			}
		    List<String> list = tblAnimalService.getByStudyNo();
		    List<Map<String,String>> serialNumList1 = new ArrayList<Map<String,String>>();
		    Map<String,String> map1=null;
		    for(String obj:list){
						map1 = new HashMap<String,String>();
						map1.put("id", obj+"");
						map1.put("text",obj+"");
						serialNumList1.add(map1);
		    }
			jsonMap.put("serialNumList", serialNumList);
			jsonMap.put("serialNumList1", serialNumList1);
			jsonMap.put("currentSerialNum", currentSerialNum+"");
			String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
			writeJson(jsonStr);
		}
		
	}
	
	//选择动物放入session中
	@SuppressWarnings("unchecked")
	public void ToCausesofDeath(){   
		Map map = new HashMap();
		List<TblAnimal> tblAnimallist = new ArrayList<TblAnimal>() ;
		//List<String> animalCodelist = new ArrayList<String>() ; 
		int index=0;
		for(String stri:radio2){
	            index++;
 				TblAnimal tblAnimal = tblAnimalService.getById(stri);
 				tblAnimallist.add(tblAnimal);
 		 }
 		ActionContext.getContext().getSession().put("SelectionfAnimal",tblAnimallist);
 		map.put("index", index);
 		String json= JsonPluginsUtil.beanToJson(map);    
 		writeJson(json); 	
	}
	
	@SuppressWarnings("unchecked")
	public String determofdeath(){
		List<TblAnimal> tblAnimallist =  (List<TblAnimal>) ActionContext.getContext().getSession().get("SelectionfAnimal");
		ActionContext.getContext().put("SelectionfAnimaldetermofdeath",tblAnimallist);
		return "determofdeath";
	}
	
	public void onUncheckofDeath(){
		ActionContext ac = ActionContext.getContext();  
		Map<String,Object> session = ac.getSession();
		session.remove("SelectionfAnimal");
		session.remove("AnimalCodelist");
		Map<String,Object> map = new  HashMap<String,Object>();
		String json = JsonPluginsUtil.beanToJson(map);
		
	    writeJson(json);
	}
	
	/**
	 * 动物死亡签字前验证
	 */
	public void signanimalCheck()  {
		
		Map<String,Object> map = new  HashMap<String,Object>();
		String msg = "";
		 if(stringCherck(AnimaldeadReason) ){
			msg="deadReason";
			map.put("success", false);
		}else if(stringCherck(AnimaldeadDate)){
			msg="deadDate";
			map.put("success", false);
		}else{
			map.put("success", true);
		}
		map.put("msg", msg);
		String json = JsonPluginsUtil.beanToJson(map);
	    writeJson(json);
	}
	
	//动物死亡签字esType = 12
	@SuppressWarnings("unchecked")
	public void AnimalDeathSign() throws ParseException{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(Integer.parseInt(esType));
		es.setEsTypeDesc("动物信息,动物死亡信息签字确认");
		List<TblAnimal> tblAnimallist =  (List<TblAnimal>) ActionContext.getContext().getSession().get("SelectionfAnimal");
		List<TblAnimal> list = new ArrayList<TblAnimal>();
		for(TblAnimal obj:tblAnimallist ){
			TblAnimal tblAnimal = tblAnimalService.getById(obj.getId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(AnimaldeadDate);
			tblAnimal.setDeadDate(date);
			tblAnimal.setDeadReason(AnimaldeadReason);
			tblAnimal.setDeadFlagUser(tempUser.getRealName());
			tblAnimal.setDeadFlag(type);
			list.add(tblAnimal);
		}
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		esLink.setTableName("TblAnimal");
		esLink.setDataId(studyNoPara);
		esLink.setTblES(es);
		esLink.setEsType(Integer.parseInt(esType));
		esLink.setEsTypeDesc("动物信息,动物死亡信息签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		
		Json json = new Json();
		try{
		   tblESService.save(es);
		   tblESLinkService.save(esLink);
		   tblAnimalService.updateAnimals(list);
		   //日志录入
		   writeLog("签字","课题："+studyNoPara+" 动物死亡信息录入完毕，签字");
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
	
	/**根据专题编号和动物编号判断动物是否存活*/
	public void ifAnimalDeadFlag(){
		//获取试验计划
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		TblAnimal tblAnimal =tblAnimalService.getByStudyPlanAnimalCode(studyPlan, model.getAnimalCode());
		Json json = new Json();
		if(tblAnimal.getDeadFlag() != 0){
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	private boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public List<TblAnimal> getTblAnimalList() {
		return tblAnimalList;
	}

	public void setTblAnimalList(List<TblAnimal> tblAnimalList) {
		this.tblAnimalList = tblAnimalList;
	}

	public List<Integer> getDissectNumList() {
		return dissectNumList;
	}

	public void setDissectNumList(List<Integer> dissectNumList) {
		this.dissectNumList = dissectNumList;
	}

	public String getMaleNum() {
		return maleNum;
	}
	public void setMaleNum(String maleNum) {
		this.maleNum = maleNum;
	}
	public String getFemaleNum() {
		return femaleNum;
	}
	public void setFemaleNum(String femaleNum) {
		this.femaleNum = femaleNum;
	}
	public String getCurrentSerialNum() {
		return currentSerialNum;
	}
	public void setCurrentSerialNum(String currentSerialNum) {
		this.currentSerialNum = currentSerialNum;
	}
	public String getMinAnimalId() {
		return minAnimalId;
	}
	public void setMinAnimalId(String minAnimalId) {
		this.minAnimalId = minAnimalId;
	}
	public String getMaxAnimalId() {
		return maxAnimalId;
	}
	public void setMaxAnimalId(String maxAnimalId) {
		this.maxAnimalId = maxAnimalId;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEsType() {
		return esType;
	}
	public void setEsType(String esType) {
		this.esType = esType;
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
	public String getCurrentSerialNum2() {
		return currentSerialNum2;
	}
	public void setCurrentSerialNum2(String currentSerialNum2) {
		this.currentSerialNum2 = currentSerialNum2;
	}
	public File getExcelCodeFile() {
		return excelCodeFile;
	}

	public void setExcelCodeFile(File excelCodeFile) {
		this.excelCodeFile = excelCodeFile;
	}

	public void setExcelCodeFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setExcelCodeFileContentType(String contentType) {
		this.contentType = contentType;
	}
	public TblDissectPlanService getTblDissectPlanService() {
		return tblDissectPlanService;
	}
	public void setTblDissectPlanService(TblDissectPlanService tblDissectPlanService) {
		this.tblDissectPlanService = tblDissectPlanService;
	}
	
	public String getAnimaldeadReason() {
		return AnimaldeadReason;
	}
	public void setAnimaldeadReason(String animaldeadReason) {
		AnimaldeadReason = animaldeadReason;
	}
	public String getAnimaldeadDate() {
		return AnimaldeadDate;
	}
	public void setAnimaldeadDate(String animaldeadDate) {
		AnimaldeadDate = animaldeadDate;
	}
	public List<String> getRadio2() {
		return radio2;
	}
	public void setRadio2(List<String> radio2) {
		this.radio2 = radio2;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAutomatic() {
		return Automatic;
	}
	public void setAutomatic(String automatic) {
		Automatic = automatic;
	}
	public DictAnimalTypeService getDictAnimalTypeService() {
		return dictAnimalTypeService;
	}
	public void setDictAnimalTypeService(DictAnimalTypeService dictAnimalTypeService) {
		this.dictAnimalTypeService = dictAnimalTypeService;
	}
	public boolean isBigAnimal() {
		return isBigAnimal;
	}
	public void setBigAnimal(boolean isBigAnimal) {
		this.isBigAnimal = isBigAnimal;
	}
	public String getMinAnimalSex() {
		return minAnimalSex;
	}
	public void setMinAnimalSex(String minAnimalSex) {
		this.minAnimalSex = minAnimalSex;
	}
	public String getCurrentAnimalId() {
		return currentAnimalId;
	}
	public void setCurrentAnimalId(String currentAnimalId) {
		this.currentAnimalId = currentAnimalId;
	}
	public String getNextAnimalId() {
		return nextAnimalId;
	}
	public void setNextAnimalId(String nextAnimalId) {
		this.nextAnimalId = nextAnimalId;
	}
	
	 

	
}
