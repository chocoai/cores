package com.lanen.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;


import com.lanen.model.Employee;
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
		Employee user=(Employee) ActionContext.getContext().getSession().get("user");
		//
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
			// 如果不是去登录，就转到登录页面
			
		
			// 如果是正在使用登录功能，就放行
			if(privilegeUrl.startsWith("userAction_login") || privilegeUrl.startsWith("userAction_newCheckPrivilege")|| privilegeUrl.startsWith("userAction_checkPrivilege")
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
        	 if(user.hasPrivilegeByUrl(privilegeUrl)){
        		 return invocation.invoke();
        	 }else{
        		// 如果没有权限，就转到提示页面
        		 return "noPrivilegeError";
        	 }
         }
		
		
	}

}
