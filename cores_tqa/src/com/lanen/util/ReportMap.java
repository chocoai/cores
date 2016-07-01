package com.lanen.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;



import com.lanen.model.company.CompanyInfo;
import com.lanen.service.company.CompanyInfoService;

@Service
public class ReportMap {
	
	@Resource
	private CompanyInfoService companyInfoService;
	
	
	
	public static ReportMap getInstance(HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		//ApplicationContext ac2 = WebApplicationContextUtils .getWebApplicationContext(servletContext);
		/*for(String name:ac1.getBeanDefinitionNames()){
			System.out.println(ac1.getBean(name));
		}
		System.out.println(ac1.getBeanDefinitionCount()+"================================="+ac2.getBeanDefinitionCount());
		for(String name:ac2.getBeanDefinitionNames()){
			System.out.println(ac2.getBean(name));
		}*/
		//ac2.getBean("beanId");
		return (ReportMap)ac1.getBean("reportMap");
	}


	public void addCompanyInfoIntoMap( Map<String,Object> map){
		
			CompanyInfo info = companyInfoService.getNewestRecord();
			 
			if(info!=null)
			{
				 String companyName = info.getCompanyName();
				 byte[] logo = info.getCompanyLogo();
				 if(companyName==null||"".equals(companyName)
						 ||logo==null||logo.length==0)
				 {
					 
					 return ;
					
				 }
				 map.put("companyName", companyName);
				 
				
				 try {
					// File file = new File("bin/image/logo.jpg");
					 File file = new File(info.getImgName());
					 FileOutputStream fos = new FileOutputStream(file);
					 fos.write(info.getCompanyLogo());
					 fos.close();
					 
					 map.put("logo", new FileInputStream(file));
					// map.put("logo", new ByteArrayInputStream(CompanyInfoBean.companyLogo));
					 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				// Messager.showWarnMessage("请在系统设置中设置公司信息！");
				 return ;
			}
		
		
		
		
	 }
	 
	
	 
	 

}
