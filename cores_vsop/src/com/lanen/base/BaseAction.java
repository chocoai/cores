package com.lanen.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;

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
import com.lanen.service.clinicaltest.TblLogService;
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
	protected TblLogService tblLogService;
	
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
	
	public void putSearchConIntoSession(String searchString,Integer index){
		
		if(searchString!=null&&!"".equals(searchString))
		{
			LinkedList<String> conditionList ;
			if(ActionContext.getContext().getSession().get(""+index)==null||
					"".equals(ActionContext.getContext().getSession().get(""+index)))
			{
				conditionList = new LinkedList<String>();
				//在第一个添加
				conditionList.add(searchString);
			}else{
				conditionList = (LinkedList<String>)ActionContext.getContext().getSession().get(""+index);
				boolean isExist = false;
				int i=0;
				for(;i<conditionList.size();i++)
				{
					String str=conditionList.get(i);
					if(str.equals(searchString))
					{
						isExist = true;
						break;
					}
				}
				if(!isExist)
				{
					if(conditionList.size()>=10)
					{
						conditionList.remove(9);//去掉最后一个
					}
				}else{
					conditionList.remove(i);
				}
				//在第一个添加
				conditionList.add(0,searchString);
			}
			
			ActionContext.getContext().getSession().put(""+index, conditionList);
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
