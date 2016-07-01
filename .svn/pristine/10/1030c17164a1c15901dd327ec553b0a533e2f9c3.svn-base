package com.lanen.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanen.service.clinicaltest.TblESService;

public class TestXml {
	@Test  
	public void testSpring(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		String recDate="2013-12-12 12:13:56";
		System.out.println(recDate.substring(17, 19));
		System.out.println(recDate.length());
		TblESService tblESService= (TblESService) ac.getBean("tblESServiceImpl");
		System.out.println(tblESService.getKey());
		
		
	}

}
