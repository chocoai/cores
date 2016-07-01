package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.model.User;
import com.lanen.model.studyplan.TblStudyMember;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.studyplan.TblStudyMemberService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String year ;
	private String studyNoPara;
	//是否显示验证试验     0不显示      1  显示
	private String isValidationPara;
//	private String ticket;
//	
//	private int isSystemSet =0;//1：显示系统管理
//	private int isStudyPlan =0;//1：显示课题管理系统
//	private int isSchedule = 0;//1：显示日程管理系统
//	private int isContract = 0;//1：显示委托管理系统
	
	private int currentNum  =0;//1.表示显示  课题编号      项目负责人
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private UserService userService;
	
	@Resource
	private TblStudyMemberService tblStudyMemberService;//课题成员Service
	
	private List<TblStudyMember> studyMemberList;//课题成员计划
	
	public String index() throws Exception {
//		ActionContext.getContext().put("ticket", ticket);
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(null != user){
			ActionContext.getContext().put("userName", user.getUserName());
		}
//		isStudyPlan=1;
//		if(userService.checkPrivilege(user, "综合管理-登录")){
//			isSchedule =1;
//		}else{
//			isSchedule =0;
//		}
//		if(userService.checkPrivilege(user, "委托管理_登录")){
//			isContract =1;
//		}else{
//			isContract =0;
//		}
//		
//		if(userService.checkPrivilege(user, "系统设置登录")){
//			isSystemSet =1;
//		}else{
//			isSystemSet =0;
//		}
		currentNum=0;
		//ActionContext.getContext().put("isStudyPlan", isStudyPlan);
		//ActionContext.getContext().put("isSystemSet", isSystemSet);
		//ActionContext.getContext().put("isSchedule", isSchedule);
		ActionContext.getContext().put("currentNum", currentNum);
		return "index";
	}
	
	public String indexSystemSet(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		
//		isSystemSet=1;
//		if(userService.checkPrivilege(user, "SD登录")){
//			isStudyPlan =1;
//		}else{
//			isStudyPlan =0;
//		}
		currentNum=0;
//		ActionContext.getContext().put("isStudyPlan", isStudyPlan);
//		ActionContext.getContext().put("isSystemSet", isSystemSet);
		ActionContext.getContext().put("currentNum", currentNum);
		
		return "indexSystemSet";
	}
	public String topSystemSet(){
		return "topSystemSet";
	}
	
	public String mainSytemSet(){
		return "mainSytemSet";
	}
	
	public String top() throws Exception {
		return "top";
	}
	
	public String main() throws Exception {
		return "main";
	}

	/**
	 * 左边，专题编号列表
	 * @return
	 * @throws Exception
	 */
	public String leftStudyPlan() throws Exception {

		String currentYear = DateUtil.dateToString(new Date(), "yyyy");
		if (null == year || "".equals(year)) {
			year = currentYear;
		}
		//显不显示验证试验
		if (null == isValidationPara || "".equals(isValidationPara)) {
			isValidationPara = "0";
		}
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<TblStudyPlan> tblStudyPlanList = null;
		
		List<TblStudyPlan> membertblStudyPlanList = new ArrayList<TblStudyPlan>();
	 	boolean  falg = userService.checkPrivilege(user, "FM");
	 	boolean falg1  = userService.checkPrivilege(user, "部门负责人");
	 	if(falg){
	 		List<TblStudyPlan> list  ;
	 		if("0".equals(isValidationPara)){
	 			 list = tblStudyPlanService.getAllListNoValidationNoTempTask(year);
	 		}else{
	 			 list = tblStudyPlanService.getAllListNoTempTask(year);
	 		}
	 		
	 		membertblStudyPlanList.addAll(list);
	 	}else if(falg1){
			List<String> userList  = new ArrayList<String>();
		    String departmentId  = user.getDepartment().getId();
			List<User> list = userService.getUserListByDepartmentId(departmentId);
			for(User obj:list){
				userList.add(obj.getRealName());
			}
			
			List<TblStudyPlan> mlist = null ;
			if("0".equals(isValidationPara)){
				 if(userList != null){
					 mlist = tblStudyPlanService.getDepartmentListNoValidationNoTempTask(year, userList,user
								.getRealName());
				 }
	 			
	 			tblStudyPlanList = tblStudyPlanService
				.getMyListNoValidationNoTempTask(year, user
						.getRealName());
	 		}else{
	 		   if(userList != null){
	 			 mlist = tblStudyPlanService.getDepartmentListNoTempTask(year, userList, user
							.getRealName());
	 		   }
	 			tblStudyPlanList = tblStudyPlanService.getMyListNoTempTask(
						year, user.getRealName());
	 		}
			if(mlist != null){
			  membertblStudyPlanList.addAll(mlist);
			}
	 	}else{
		 		if (null != user) {
					// 课题成员
	//				Set<String> memberList = new HashSet<String>();
					// 试验计划列表
					if ("0".equals(isValidationPara)) {
						tblStudyPlanList = tblStudyPlanService
								.getMyListNoValidationNoTempTask(year, user
										.getRealName());
						//专题成员也分验证与非验证 TODO
						List<String> list = tblStudyMemberService.getByMember(user
								.getUserName());
						if (list != null) {
							for (String StudyNo : list) {
								TblStudyPlan studyPlan = tblStudyPlanService
										.getById(StudyNo);
								String state = studyPlan.getStudyState();
								int isValidation = studyPlan.getIsValidation();
								String startDateYear = DateUtil.dateToString(studyPlan.getStudyStartDate(), "yyyy");
								if (!state.equals("0") && isValidation == 0 && startDateYear.equals(year)) {
									membertblStudyPlanList.add(studyPlan);
								}
							}
						}
	
					} else {// 显示验证试验
	
						tblStudyPlanList = tblStudyPlanService.getMyListNoTempTask(
								year, user.getRealName());
						//课题编号
						List<String> list = tblStudyMemberService.getByMember(user
								.getUserName());
						if (list != null) {
							for (String StudyNo : list) {
								TblStudyPlan studyPlan = tblStudyPlanService
										.getById(StudyNo);
								String state = studyPlan.getStudyState();
								String startDateYear = DateUtil.dateToString(studyPlan.getStudyStartDate(), "yyyy");
								if (!state.equals("0") && startDateYear.equals(year)) {
									membertblStudyPlanList.add(studyPlan);
								} 
							}
						}
	
					}
				}
	 		
	 	}
		
//		Set<String> yearSet = new HashSet<String>();
//		List<TblStudyPlan> tblStudyPlanList2 = tblStudyPlanService
//				.getAllNoTempTask();
//		if (null != tblStudyPlanList2 && tblStudyPlanList2.size() > 0) {
//			for (TblStudyPlan entity : tblStudyPlanList2) {
//				Date date = entity.getStudyStartDate();
//				if (null != date) {
//					String yearDate = DateUtil.dateToString(date, "yyyy");
//					yearSet.add(yearDate);
//				}
//
//			}
//		}
//		
//		if (!yearSet.contains(currentYear)) {
//			yearSet.add(currentYear);
//		}
//		List<String> yearList = new ArrayList<String>(yearSet);
//		// 排序
//		Collections.sort(yearList, new Comparator<String>() {
//
//			public int compare(String o1, String o2) {
//				return -o1.compareTo(o2);
//			}
//		});
		String realName = "";
		if(null != user){
			realName = user.getRealName();
		}
//		List<String> yearList = tblStudyPlanService.getYearList(realName);
		List<String> yearList = tblStudyPlanService.getYearList();
		if(null == yearList && yearList.size() < 1){
			yearList = new ArrayList<String>();
			yearList.add(currentYear);
		}else if(!yearList.contains(currentYear)){
			yearList.add(currentYear);
		}
		ActionContext.getContext().put("tblStudyPlanList", tblStudyPlanList);
		ActionContext.getContext().put("membertblStudyPlanList",
				membertblStudyPlanList);
		ActionContext.getContext().put("yearList", yearList);
		ActionContext.getContext().put("year", year);
		ActionContext.getContext().put("studyNoPara", studyNoPara);
		ActionContext.getContext().put("isValidationPara", isValidationPara);

		return "leftStudyPlan";
	}
	public String leftSystemSet() throws Exception {
		User user =(User) ActionContext.getContext().getSession().get("user");
		String privilege = "";
		if(userService.checkPrivilege(user, "系统设置-临检指标")){//权限模块  1
			privilege = privilege + "1";
		}
		if(userService.checkPrivilege(user, "系统设置-试验相关")){//权限模块  2
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "2";
			
			
		} 
		if(userService.checkPrivilege(user, "系统设置-资源分配")){//权限模块  3
			if(privilege != ""){
				privilege = privilege +","; 
			}
			privilege = privilege + "3";
		}
		ActionContext.getContext().put("userprivilege",privilege);
		return "leftSystemSet";
	}
	public String right() throws Exception {
		return "right";
	}
	//-----------------------------------
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStudyNoPara() {
		return studyNoPara;
	}
	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}
	public String getIsValidationPara() {
		return isValidationPara;
	}
	public void setIsValidationPara(String isValidationPara) {
		this.isValidationPara = isValidationPara;
	}


	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public TblStudyMemberService getTblStudyMemberService() {
		return tblStudyMemberService;
	}

	public void setTblStudyMemberService(TblStudyMemberService tblStudyMemberService) {
		this.tblStudyMemberService = tblStudyMemberService;
	}

	public List<TblStudyMember> getStudyMemberList() {
		return studyMemberList;
	}

	public void setStudyMemberList(List<TblStudyMember> studyMemberList) {
		this.studyMemberList = studyMemberList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}

	
	
}
