package com.lanen.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lanen.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> , ServletRequestAware,ServletResponseAware {
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	

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
	protected String getCurrentRealName(){
		User user = (User)ActionContext.getContext().getSession().get("user");
		if(null != user){
			return user.getRealName();
		}
		return "";
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

}
