package com.lanen.view.action.studyplan;

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
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan_Json;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.path.TblAnatomyReqAnimalListService;
import com.lanen.service.studyplan.TblAnimalDetailDissectPlanService;
import com.lanen.service.studyplan.TblDissectPlanService;
import com.lanen.service.studyplan.TblDoseSettingService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblDissectPlanAction extends BaseAction<TblDissectPlan> {

	private static final long serialVersionUID = 1L;

	/**
	 * 解剖计划Service
	 */
	@Resource
	private TblDissectPlanService tblDissectPlanService;
	
	/**试验计划Service*/
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 动物详细解剖
	 */
	@Resource
	private TblAnimalDetailDissectPlanService tblAnimalDetailDissectPlanService;
	 /**
	  * 剂量设置Service
	  */
	@Resource
	private TblDoseSettingService tblDoseSettingService;
	/**
	 * 解剖申请-申请解剖动物列表     service 
	 */
	@Resource
	private TblAnatomyReqAnimalListService tblAnatomyReqAnimalListService;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	
	
	/**
	 * 解剖计划列表内容
	 */
	private List<TblDissectPlan> tblDissectPlanList;
	
	/**
	 * id
	 */
	private String disId;
	
	/**
	 * 解剖次数
	 */
	private int dissectNumPara;
	/**组别*/
	private int groupNum;
	
	/**
	 * 动物列表(动物详细解剖计划表id)
	 */
	private List<String> animalDetailList;
	//课题成员标记
	private String member;
	
	/**
	 * 解剖计划列表显示
	 * @return String
	 */
	public String dissectPlanList() {
		//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//查询符合条件的内容
		tblDissectPlanList = tblDissectPlanService.getByStudyNo(tblStudyPlan);
	
		
		//获取动物详细解剖列表(该试验计划下的所有)
		List<TblAnimalDetailDissectPlan> animalDetailDissectPlanList = tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan);
			List<TblAnimalDetailDissectPlan> list0 =new  ArrayList<TblAnimalDetailDissectPlan>();
			List<TblAnimalDetailDissectPlan> list1 =new  ArrayList<TblAnimalDetailDissectPlan>();
			List<TblAnimalDetailDissectPlan> list2 =new  ArrayList<TblAnimalDetailDissectPlan>();
			List<TblAnimalDetailDissectPlan> list3 =new  ArrayList<TblAnimalDetailDissectPlan>();
			List<TblAnimalDetailDissectPlan> list4 =new  ArrayList<TblAnimalDetailDissectPlan>();
			if(null!=animalDetailDissectPlanList && animalDetailDissectPlanList.size()>0){
				for(TblAnimalDetailDissectPlan obj:animalDetailDissectPlanList){
					if(obj.getAnimalCode().substring(0, 1).equals(1+"")){
						list0.add(obj);
					}else if(obj.getAnimalCode().substring(0, 1).equals(2+"")){
						list1.add(obj);
					}else if(obj.getAnimalCode().substring(0, 1).equals(3+"")){
						list2.add(obj);
					}else if(obj.getAnimalCode().substring(0, 1).equals(4+"")){
						list3.add(obj);
					}else {
						list4.add(obj);
					}
				}
			}
			ActionContext.getContext().put("animalDetailDissectPlanList"+0, list0);
			ActionContext.getContext().put("animalDetailDissectPlanList"+1, list1);
			ActionContext.getContext().put("animalDetailDissectPlanList"+2, list2);
			ActionContext.getContext().put("animalDetailDissectPlanList"+3, list3);
			ActionContext.getContext().put("animalDetailDissectPlanList"+4, list4);
			ActionContext.getContext().put("left_member", member);
			ActionContext.getContext().put("studyState", tblStudyPlan.getStudyState());
		return "dissectPlanList";
	}
	/**表格加载数据（json）*/
	public void loadList(){
		//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		if(null!=tblStudyPlan ){
			//查询符合条件的内容
			tblDissectPlanList = tblDissectPlanService.getByStudyNo(tblStudyPlan);
			String[] _nory_changes={"id","dissectNum","beginDate","endDate","describe"};
			String jsonStr = JsonPluginsUtil.beanListToJson(tblDissectPlanList, "yyyy-MM-dd", _nory_changes, true);
			writeJson(jsonStr);
		}
	}
	
	/**为添加页面准备数据*/
	public void loadAddUIData(){
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		int dissectNextNum=tblDissectPlanService.getNextNum(tblStudyPlan);
		//已被动物编号
//		List<String> selectedAnimalCodeList = new ArrayList<String>();
		//获取动物详细解剖列表(解剖次数不为0)
//		List<TblAnimalDetailDissectPlan> animalDetailDissectPlanList = tblAnimalDetailDissectPlanService.getByStudyPlanAndNo0(tblStudyPlan);
//		if(null!=animalDetailDissectPlanList && animalDetailDissectPlanList.size()>0){
//			for(TblAnimalDetailDissectPlan  obj:animalDetailDissectPlanList){
//				selectedAnimalCodeList.add(obj.getAnimalCode());
//			}
//		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dissectNextNum", dissectNextNum);
//		map.put("selectedAnimalCodeList", selectedAnimalCodeList);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**为编辑页面准备数据*/
	public void loadEditUIData(){
		TblDissectPlan tblDissectPlan = tblDissectPlanService.getById(model.getId());
//		if(null!=tblDissectPlan){
//			TblStudyPlan tblStudyPlan = tblDissectPlan.getTblStudyPlan();
//			//已被选中动物编号（当前选中的除外）
//			List<String> selectedAnimalCodeList = new ArrayList<String>();
//			//当前选中的动物列表
//			List<String> currentSelectedAnimalCodeList = new ArrayList<String>();
//			
//			//获取动物详细解剖列表(解剖次数不为0)
//			List<TblAnimalDetailDissectPlan> animalDetailDissectPlanList = tblAnimalDetailDissectPlanService.getByStudyPlanAndNo0(tblStudyPlan);
//			if(null!=animalDetailDissectPlanList && animalDetailDissectPlanList.size()>0){
//				for(TblAnimalDetailDissectPlan  obj:animalDetailDissectPlanList){
//					if(obj.getDissectNum()!=tblDissectPlan.getDissectNum()){
//						selectedAnimalCodeList.add(obj.getAnimalCode());
//					}else{
//						currentSelectedAnimalCodeList.add(obj.getAnimalCode());
//					}
//				}
//			}
			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("selectedAnimalCodeList", selectedAnimalCodeList);
//			map.put("currentSelectedAnimalCodeList", currentSelectedAnimalCodeList);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
//		}
	}
	
	
	/** 新增保存*/
	public void addSave(){
//		List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlans = new ArrayList<TblAnimalDetailDissectPlan>();
		//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		//装入试验计划
		TblDissectPlan tblDissectPlan=model;
		tblDissectPlan.setTblStudyPlan(tblStudyPlan);
//		if(animalDetailList!=null&&!animalDetailList.isEmpty()){
//			for(String s: animalDetailList){
//				TblAnimalDetailDissectPlan animalDetailDissectPlan = tblAnimalDetailDissectPlanService.getById(s);
//				animalDetailDissectPlan.setDissectNum(tblDissectPlan.getDissectNum());
//				tblAnimalDetailDissectPlans.add(animalDetailDissectPlan);
//			}
//		}
		//验证
		if(nullCheck(tblDissectPlan)){
			String state = tblStudyPlan.getStudyState();
			if(state.equals("0")){
				tblDissectPlanService.save(tblDissectPlan);
			}else if(state.equals("3")){
				tblDissectPlanService.saveAndSaveHis(tblDissectPlan);
			}
		}else{
		}
		String[] _nory_changes={"id","dissectNum","beginDate","endDate","describe"};
		String jsonStr = JsonPluginsUtil.beanToJson(model, "yyyy-MM-dd", _nory_changes, true);
		writeJson(jsonStr);
	}
	

	
	/**编辑保存*/
	public void editSave(){
//		List<TblAnimalDetailDissectPlan> newAnimalDetailDissectPlans = new ArrayList<TblAnimalDetailDissectPlan>();
		//获取旧数据
		TblDissectPlan newTblDissectPlan = new TblDissectPlan();
		newTblDissectPlan = tblDissectPlanService.getById(model.getId());
		//替换数据
		newTblDissectPlan.setBeginDate(model.getBeginDate());
		newTblDissectPlan.setEndDate(model.getEndDate());
		newTblDissectPlan.setDescribe(model.getDescribe());
//		//获取旧的已经选择的动物列表
//		List<TblAnimalDetailDissectPlan> oldAnimalDetailDissectPlans = tblAnimalDetailDissectPlanService.getByStudyPlan(newTblDissectPlan.getTblStudyPlan(), newTblDissectPlan.getDissectNum());
//		//设置就数据为初始状态
//		for(int i=0;i<oldAnimalDetailDissectPlans.size();i++){
//			oldAnimalDetailDissectPlans.get(i).setDissectNum(0);
//		}
//		//设置新的数据
//		if(animalDetailList!=null&&!animalDetailList.isEmpty()){
//			for(String s: animalDetailList){
//				TblAnimalDetailDissectPlan animalDetailDissectPlan = tblAnimalDetailDissectPlanService.getById(s);
//				animalDetailDissectPlan.setDissectNum(model.getDissectNum());
//				newAnimalDetailDissectPlans.add(animalDetailDissectPlan);
//			}
//		}
//		List<TblAnimalDetailDissectPlan>tempList = new ArrayList<TblAnimalDetailDissectPlan>();
//		for(TblAnimalDetailDissectPlan obj1: newAnimalDetailDissectPlans){
//			for (TblAnimalDetailDissectPlan obj2: oldAnimalDetailDissectPlans) {
//				if(obj1.getId().equals(obj2.getId())){
//					tempList.add(obj2);
//				}
//			}
//		}
//		for(TblAnimalDetailDissectPlan obj: tempList){
//			newAnimalDetailDissectPlans.remove(obj);
//			oldAnimalDetailDissectPlans.remove(obj);
//		}
		
		
		//验证
		if (nullCheck(newTblDissectPlan)) {
			String state = newTblDissectPlan.getTblStudyPlan().getStudyState();
			if(state.equals("0")){
				tblDissectPlanService.update(newTblDissectPlan);
			}else if(state.equals("3")){
				tblDissectPlanService.upDateAndSaveHis(newTblDissectPlan);
			}
			
		}
		String[] _nory_changes={"id","dissectNum","beginDate","endDate","describe"};
		String jsonStr = JsonPluginsUtil.beanToJson(model, "yyyy-MM-dd", _nory_changes, true);
		writeJson(jsonStr);
	}
	
	/**删除解剖计划，如果有对应的动物详细解剖计划一并删除*/
	public void delete(){
		Map<String, Object> map=new HashMap<String, Object>();
		if(model.getId()!=null){
		   TblDissectPlan d=tblDissectPlanService.getById(model.getId());
		   if(d!=null){
			   //   TODO dissectNum 设为 0
			   tblDissectPlanService.delete(model.getId());	
	           List<TblAnimalDetailDissectPlan> list=tblAnimalDetailDissectPlanService
	                                                 .getByStudyNoAndDissectNum(studyNoPara, d.getDissectNum()) ; 
	           if(list!=null&&list.size()>0){
	        	   for(TblAnimalDetailDissectPlan tadd:list){
	        		   tadd.setDissectNum(0);
	        		   tblAnimalDetailDissectPlanService.update(tadd);
	        	   }
	           }
	           
	           map.put("success", true);
	           map.put("msg", "删除成功");
		   }
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**解剖次数对应的动物编号列表*/
	public String animalCode() throws Exception{
		if(null!=studyNoPara && !studyNoPara.equals("") && 0 != model.getDissectNum()){
			TblStudyPlan studyPlan = new TblStudyPlan();
			studyPlan.setStudyNo(studyNoPara);
			List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList = 
				tblAnimalDetailDissectPlanService.getByStudyPlan(studyPlan , model.getDissectNum());
			if(null!= tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size()>0){
				ActionContext.getContext().put("tblAnimalDetailDissectPlanList", tblAnimalDetailDissectPlanList);
			}
		}
		return "animalCode";
	}
	/**解剖动物设置*/
	public String animalSet() throws Exception{
		groupNum =0;
		if(null!=studyNoPara && !studyNoPara.equals("")){
			TblStudyPlan studyPlan =new TblStudyPlan();
			studyPlan.setStudyNo(studyNoPara);
			List<TblDoseSetting> list=tblDoseSettingService.getByStudyNo(studyPlan);
			if(null!=list)
				groupNum=list.size();	
		}
		ActionContext.getContext().put("groupNum", groupNum);
		return "animalSet";
	}
	
	/**解剖次数下拉框（）*/
	public void dissectNum(){
		if(null!=studyNoPara && !studyNoPara.equals("")){
			TblStudyPlan studyPlan =new TblStudyPlan();
			studyPlan.setStudyNo(studyNoPara);
			List<TblDissectPlan> tblDissectPlanList = tblDissectPlanService.getByStudyNo(studyPlan );
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map = new HashMap<String,String>();
			if(null!=tblDissectPlanList && tblDissectPlanList.size()>0){
				map = new HashMap<String,String>();
				map.put("id", "0");
				map.put("text","不解剖");
				list.add(map);
				for(TblDissectPlan obj:tblDissectPlanList){
					map = new HashMap<String,String>();
					map.put("id", obj.getDissectNum()+"");
					map.put("text","第"+obj.getDissectNum()+"次解剖");
					list.add(map);
				}
			}
			String jsonStr=JsonPluginsUtil.beanListToJson(list);
			writeJson(jsonStr);
		}
	}
	/**根据课题编号加载解剖次数下拉框（如果没有计划，加载空）*/
	public void dissectNumByStudyNo(){
		if(null!=studyNoPara && !studyNoPara.equals("")){
			TblStudyPlan studyPlan =new TblStudyPlan();
			studyPlan.setStudyNo(studyNoPara);
			List<TblDissectPlan> tblDissectPlanList = tblDissectPlanService.getByStudyNo(studyPlan );
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map = new HashMap<String,String>();
			if(null!=tblDissectPlanList && tblDissectPlanList.size()>0){
				for(TblDissectPlan obj:tblDissectPlanList){
					map = new HashMap<String,String>();
					map.put("id", obj.getDissectNum()+"");
					map.put("text","第"+obj.getDissectNum()+"次解剖");
					list.add(map);
				}
			}
			String jsonStr=JsonPluginsUtil.beanListToJson(list);
			writeJson(jsonStr);
		}
	}
	/**详细解剖计划datagrid数据*/
	public void loadDetialList(){
		if(null!=studyNoPara && !studyNoPara.equals("") && groupNum>0){
			TblStudyPlan tblStudyPlan = new TblStudyPlan();
			tblStudyPlan.setStudyNo(studyNoPara);
			List<TblAnimalDetailDissectPlan> tblAnimalDetailDissectPlanList =
				tblAnimalDetailDissectPlanService.getByStudyPlan(tblStudyPlan );
			
			List<TblAnimalDetailDissectPlan> currentList = new ArrayList<TblAnimalDetailDissectPlan>();
			if(null!=tblAnimalDetailDissectPlanList && tblAnimalDetailDissectPlanList.size()>0){
				for(TblAnimalDetailDissectPlan obj:tblAnimalDetailDissectPlanList){
					if(obj.getGroupId()==groupNum){
						currentList.add(obj);
					}
				}
			}
			String[] _nory_changes ={"id","animalCode","gender","dissectNum"};
			String jsonStr = JsonPluginsUtil.beanListToJson(currentList, _nory_changes , true);
			writeJson(jsonStr);
		}
	}
	/**根据课题编号和解剖次数查找详细解剖计划datagrid数据(用于解剖申请)*/
	public void getDetailListByStudyNoAndDissectNum(){
		List<Map<String, Object>> list=tblAnimalDetailDissectPlanService
                                              .getByStudyNoAndDissectNum2(studyNoPara, dissectNumPara) ; 
		List<TblAnimalDetailDissectPlan_Json> list2=new ArrayList<TblAnimalDetailDissectPlan_Json>();
		Map<String,Object> map = new HashMap<String,Object>();
//		if(list!=null&list.size()>0){
//			for(TblAnimalDetailDissectPlan addp:list){
//				TblAnimalDetailDissectPlan_Json json=new TblAnimalDetailDissectPlan_Json();
//				json.setId(addp.getId());
//				json.setAnimalCode(addp.getAnimalCode());
//				json.setGender(addp.getGender());
//				int isAnatomyReq=tblAnatomyReqAnimalListService.isHaveAnatomyReq(studyNoPara,addp.getAnimalCode());
//				json.setIsAnatomyReq(isAnatomyReq);
//				list2.add(json);
//			}
//		}
		
		 map.put("rows", list);
		 map.put("total", list.size());
		 String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		 writeJson(json);
	}
	/**根据课题编号和解剖次数查找解剖计划的开始日期和结束日期(用于解剖申请)*/
	public void getDissectPlanDateByDissectNum(){
		Map<String, Object> map=tblDissectPlanService.getDissectPlanDateByDissectNum(studyNoPara, dissectNumPara);
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("beginDate",DateUtil.dateToString((Date)map.get("beginDate"), "yyyy-MM-dd"));
		map2.put("endDate",DateUtil.dateToString((Date)map.get("endDate"), "yyyy-MM-dd"));
		map2.put("describe", (String)map.get("describe"));
		String json = JsonPluginsUtil.beanToJson(map2);
		writeJson(json);
	}
	/**每组第几个（下拉框数据）*/
	public void selectNum(){
		if(null!=studyNoPara && !studyNoPara.equals("")){
			TblStudyPlan tblStudyPlan = new TblStudyPlan();
			tblStudyPlan.setStudyNo(studyNoPara);
			List<TblDoseSetting> tblDoseSettingList= tblDoseSettingService.getByStudyNo(tblStudyPlan);
			int maxNum =0;
			int currentNum=0;
			if(null!=tblDoseSettingList && tblDoseSettingList.size()>0){
				for(TblDoseSetting obj:tblDoseSettingList){
					currentNum=(obj.getMaleNum()>obj.getFemaleNum() ?obj.getMaleNum() :obj.getFemaleNum());
					if(currentNum>maxNum){
						maxNum = currentNum;
					}
				}
			}
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			Map<String,String> map = null;
			for(int i=1;i<=maxNum;i++){
				map = new HashMap<String,String>();
				map.put("id", i+"");
				map.put("text", i+"");
				list.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(list);
			writeJson(jsonStr);
		}
	}
	/**解剖动物更新*/
	public void updateData(){
		Json json = new Json();
		if(null!=studyNoPara && !studyNoPara.isEmpty()){
			TblStudyPlan tblStudyPlan = new TblStudyPlan();
			tblStudyPlan.setStudyNo(studyNoPara);
			TblDissectPlan dissectPlan =tblDissectPlanService.getByStudyNo(tblStudyPlan,model.getDissectNum());
			
			//新数据
			List<TblAnimalDetailDissectPlan> newAnimalDetailDissectPlans = new ArrayList<TblAnimalDetailDissectPlan>();
			if(animalDetailList!=null&&!animalDetailList.isEmpty()){//新数据不为空
				//设置新的数据
				for(String s: animalDetailList){
					TblAnimalDetailDissectPlan animalDetailDissectPlan = tblAnimalDetailDissectPlanService.getById(s);
					animalDetailDissectPlan.setDissectNum(model.getDissectNum());
					newAnimalDetailDissectPlans.add(animalDetailDissectPlan);
				}
				//获得试验计划
				TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
				String state = studyPlan.getStudyState();
				if(state.equals("0")){
					tblDissectPlanService.update(dissectPlan , newAnimalDetailDissectPlans);
				}else if(state.equals("3")){
					tblDissectPlanService.updateAndSaveHis(newAnimalDetailDissectPlans);
				}
				  
				json.setSuccess(true);
				json.setMsg("保存成功");
			}else{
				json.setMsg("保存失败");
			}
		}else{
			json.setMsg("请检查数据");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 主键唯一性检查
	 */
	public void uniqueCheck() throws Exception{
		TblStudyPlan studyPlan = tblStudyPlanService.getById(studyNoPara);
		boolean flag = tblDissectPlanService.uniqueCheck(studyPlan, dissectNumPara , disId);
		if(flag){
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
	
	/**
	 * 合法性检查
	 * @return
	 */
	private boolean nullCheck(TblDissectPlan dissectPlan) {
		boolean flag = true;
		if(dissectPlan.getTblStudyPlan() == null){
			flag = false;
		}else if (dissectPlan.getDissectNum() == 0) {
			flag = false;
		}else if (dissectPlan.getBeginDate() == null) {
			flag = false;
		}else if (dissectPlan.getEndDate() == null) {
			flag = false;
		}else if (!tblDissectPlanService.uniqueCheck(dissectPlan.getTblStudyPlan(), dissectPlan.getDissectNum(), dissectPlan.getId())) {
			flag = false;
		}
		return flag;
	}
	/**
	 *加载数据库已有的解剖阶段描述
	 */
	public void getDescribeCombobox(){
		List<Map<String, Object>> list=tblDissectPlanService.getDescribeList();
		List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
		if(null!=list && list.size()>0){
			for(Map<String, Object> map:list){
				Map<String, Object> map2=new HashMap<String, Object>();
				map2.put("id", map.get("text"));
				map2.put("text", map.get("text"));
				list2.add(map2);
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list2);
		writeJson(jsonStr);
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public List<TblDissectPlan> getTblDissectPlanList() {
		return tblDissectPlanList;
	}

	public void setTblDissectPlanList(List<TblDissectPlan> tblDissectPlanList) {
		this.tblDissectPlanList = tblDissectPlanList;
	}

	public String getDisId() {
		return disId;
	}

	public void setDisId(String disId) {
		this.disId = disId;
	}

	public int getDissectNumPara() {
		return dissectNumPara;
	}

	public void setDissectNumPara(int dissectNumPara) {
		this.dissectNumPara = dissectNumPara;
	}


	public List<String> getAnimalDetailList() {
		return animalDetailList;
	}

	public void setAnimalDetailList(List<String> animalDetailList) {
		this.animalDetailList = animalDetailList;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	
}
