package com.lanen.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanen.service.PrivilegeService;
import com.lanen.service.RoleService;
import com.lanen.service.UserService;

public class BaseService {
	private static ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	public static UserService userService =(UserService) ac.getBean("userServiceImpl");
	public static RoleService roleService =(RoleService) ac.getBean("roleServiceImpl");
	public static PrivilegeService privilegeService =(PrivilegeService) ac.getBean("privilegeServiceImpl");
	

}
