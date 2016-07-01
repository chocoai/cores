package com.lanen.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;

import com.lanen.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ac = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ac
				.get(StrutsStatics.HTTP_REQUEST);
		String requestType = request.getHeader("X-Requested-With");
		HttpServletResponse response = (HttpServletResponse) ac
				.get(StrutsStatics.HTTP_RESPONSE);
		
		// 获取当前用户
		User user=(User) ActionContext.getContext().getSession().get("user");
		
		String privilegeUrl =null;
		// 获取当前访问的URL，并去掉当前应用程序的前缀（也就是 namespaceName + actionName ）
		String namespace =invocation.getProxy().getNamespace();
		String actionName =invocation.getProxy().getActionName();
		if(namespace.endsWith("/")){
			privilegeUrl=namespace+actionName;
		}else{
			privilegeUrl=namespace+"/"+actionName;
		}
		
		// 要去掉开头的'/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}
		
		// 如果未登录用户
		if(user ==null){
			// 如果是正在使用登录功能，就放行
			if(privilegeUrl.startsWith("userAction_login") || privilegeUrl.startsWith("userAction_checkPrivilege")
					||privilegeUrl.startsWith("userAction_alterPassword")||privilegeUrl.startsWith("userAction_passwordCheck")){// userAction_login, userAction_loginUI
				
				System.out.println("拦截器，登陆功能，放行！");
				return invocation.invoke();
			}else{
				
				// 是否是ajax 请求
				if (StringUtils.isNotBlank(requestType)
						&& requestType.equalsIgnoreCase("XMLHttpRequest")) {
					System.out.println("拦截器，拦截，ajax ！");
					response.setHeader("sessionstatus", "timeout");
					response.sendError(518, "session timeout.");
					return null;
				} else {
					// 如果不是去登录，就转到登录页面
					System.out.println("拦截器，拦截！");
					// 如果不是使用登录功能，就转到登陆页面
					return "loginUI";
				}
				
			}
         }else{
        	// 如果已登录用户（就判断权限）
        	 if(hasPrivilegeByUrl(privilegeUrl)){
        		 return invocation.invoke();
        	 }else{
        		// 如果没有权限，就转到提示页面
        		 return "noPrivilegeError";
        	 }
         }
		
		
	}

private boolean hasPrivilegeByUrl(String privilegeUrl){
		
		//如果带.action，就去掉.action
		int index =privilegeUrl.indexOf('.');
		if(index>-1){
			privilegeUrl=privilegeUrl.substring(0,index);
		}

		// 如果以UI后缀结尾，就去掉UI后缀，以得到对应的权限（例如：addUI与add是同一个权限）
		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}

		//所有权限的URL 的数据
		List<String> privilegeUrlList =(List<String>)ActionContext.getContext().getApplication().get("privilegeUrlList");
		if(null==privilegeUrlList ||privilegeUrlList.size()<1){
			System.out.println("不在管理之列，放行！！！");
			return true;
		}
		
		boolean falg = false;
		for(String obj:privilegeUrlList){
			if(obj.contains(privilegeUrl)){
				falg = true ;
			}
		}
//		//如果不在管理之列，则视为  有权限
		if(!falg){
		    System.out.println("不在管理之列，放行！！！");
			return true;
		}else{
			// 其他,    用户要是有权限才返回true
			//当前用户权限url列表
			List<String> myPrivilegeUrlList =  (List<String>) ActionContext.getContext().getSession().get("myPrivilegeUrlList");
			
			if(null !=myPrivilegeUrlList){
				for(String obj:myPrivilegeUrlList){
					if(obj.contains(privilegeUrl)){
						return true;
					}
				}
				return true;
			}
		}
		return false;
	}
}
