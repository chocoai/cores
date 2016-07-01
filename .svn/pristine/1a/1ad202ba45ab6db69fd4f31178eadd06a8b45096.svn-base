package com.lanen.view.action.studyplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.studyplan.TblStudyMember;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.UserService;
import com.lanen.service.studyplan.TblStudyMemberService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblStudyMemberAction extends BaseAction<TblStudyMember>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 课题成员Service*/
	@Resource
	private TblStudyMemberService tblStudyMemberService;
	/** 用户Service*/
	@Resource
	private UserService UserService;
	
	// 课题编号
	private String studyNoPara;
	
	
	private List<TblStudyMember> studyMemberList;
	
    private List<String> selecteds;
	
	private List<String> selecteds1;
	
	private String MemberId;
	// 试验计划Service
	@Resource
	private TblStudyPlanService tblStudyPlanService;
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
	
	/**部门list*/
	public void DepartmentnameList(){
		 List<User> list = UserService.findByPrivilegeName("SD登录");
		if(null!=list && list.size()>0){
			Set<Map<String,String>> serialNumSet = new HashSet<Map<String,String>>();
			Map<String,String> map=null;
			for(User user:list){
				map = new HashMap<String,String>();
				map.put("id", user.getDepartment().getName()+"");
				map.put("text",user.getDepartment().getName()+"");
				serialNumSet.add(map);
			}
			List<Map<String,String>> serialNumList = new ArrayList<Map<String,String>>(serialNumSet);
			map = new HashMap<String,String>();
			map.put("id", "-1");
			map.put("text","&nbsp;");
			serialNumList.add(0,map);
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("serialNumList", serialNumList);
			String jsonStr= JsonPluginsUtil.beanToJson(jsonMap);
			writeJson(jsonStr);
	}

  }
	
	public void loadList(){
		studyMemberList = tblStudyMemberService.getByStudyNo(studyNoPara);
		List<TblStudyMember> list = new ArrayList<TblStudyMember>();
		for(TblStudyMember obj:studyMemberList){
			TblStudyMember  studyMember = new TblStudyMember ();
			studyMember.setStudyNo(obj.getStudyNo());
			User user= UserService.getById(obj.getMember());
			studyMember.setName(user.getRealName());
			studyMember.setId(obj.getId());
			studyMember.setDepartmentname(user.getDepartment().getName());
			list.add(studyMember);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows",list);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	public void MemberloadList(){
		 User tempUser = (User) ActionContext.getContext().getSession().get("user");
		 //获取所有用户
		 List<User> list = UserService.findByPrivilegeName("SD登录");
		 List<User> newlist = new ArrayList<User>();
		 studyMemberList = tblStudyMemberService.getByStudyNo(studyNoPara);
		 boolean falge = true;
		 for(User user:list){
			 User newuser = new User();
			 newuser.setId(user.getId());
			 newuser.setRealName(user.getRealName());
			 for(TblStudyMember obj:studyMemberList){
			    if(obj.getMember().equals(user.getUserName()) ){
			    	falge = false;
			    }	 
			 }
			 newuser.setDepartmentname(user.getDepartment().getName());
			 String username = tempUser.getRealName();
			 if(username.equals(user.getRealName())){
				 falge = false;
			 }
			 if(falge){
				 newlist.add(newuser);	
			 }
			 falge = true;
		 }
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows",newlist);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	public void AddStudyMemberSave(){
	    List<TblStudyMember> list = new ArrayList<TblStudyMember>();
		for(String obj:selecteds){
			TblStudyMember  studyMember = new TblStudyMember ();
			studyMember.setStudyNo(studyNoPara);
			studyMember.setMember(obj);
		    list.add(studyMember);
		}
		tblStudyMemberService.saveTblStudyMemberList(list);
		Map<String,Object> map = new HashMap<String,Object>();
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public void  removeMembersaction(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != MemberId && !MemberId.equals("")){
			tblStudyMemberService.delete(MemberId);
			 map.put("success",true);
		}else{
			map.put("success", false );
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public TblStudyMemberService getTblStudyMemberService() {
		return tblStudyMemberService;
	}

	public void setTblStudyMemberService(TblStudyMemberService tblStudyMemberService) {
		this.tblStudyMemberService = tblStudyMemberService;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public List<TblStudyMember> getStudyMemberList() {
		return studyMemberList;
	}

	public void setStudyMemberList(List<TblStudyMember> studyMemberList) {
		this.studyMemberList = studyMemberList;
	}

	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public List<String> getSelecteds() {
		return selecteds;
	}

	public void setSelecteds(List<String> selecteds) {
		this.selecteds = selecteds;
	}

	public List<String> getSelecteds1() {
		return selecteds1;
	}

	public void setSelecteds1(List<String> selecteds1) {
		this.selecteds1 = selecteds1;
	}

	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	public TblStudyPlanService getTblStudyPlanService() {
		return tblStudyPlanService;
	}

	public void setTblStudyPlanService(TblStudyPlanService tblStudyPlanService) {
		this.tblStudyPlanService = tblStudyPlanService;
	}
     
	
	
	
}
