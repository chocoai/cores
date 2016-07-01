package com.lanen.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lanen.model.User;
import com.lanen.service.DepartmentService;
import com.lanen.service.PrivilegeService;
import com.lanen.service.RoleService;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.DictSpecimenService;
import com.lanen.service.clinicaltest.PassagewayService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.studyplan.DictBioChemService;
import com.lanen.service.studyplan.DictBloodCoagService;
import com.lanen.service.studyplan.DictHematService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictUrineService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> , ServletRequestAware,ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected DictBioChemService dictBioChemService;
	@Resource
	protected DictBloodCoagService dictBloodCoagService;
	@Resource
	protected DictHematService dictHematService;
	@Resource
	protected DictUrineService dictUrineService;
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	@Resource
	protected TblLogService tblLogService;
	@Resource
	protected PassagewayService passagewayService;
	
	@Resource
	protected DictSpecimenService dictSpecimenService;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	//默认为第一页
	protected int pageNum = 1;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	

	protected T model;
	@SuppressWarnings("unchecked")
	public BaseAction(){
		try {
			//先得到model  类型
			ParameterizedType pt =(ParameterizedType)this.getClass().getGenericSuperclass();
			Class clazz =(Class)pt.getActualTypeArguments()[0];
			//通过反射生成model实例
			model =(T)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	public void writeJson(String json){
		try {
//			ServletActionContext.getResponse().getWriter().write(json);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public T getModel() {
		return model;
	}
	protected User getCurrentUser(){
		return (User)ActionContext.getContext().getSession().get("user");
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

}
