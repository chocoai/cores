package com.lanen.util;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanen.service.contract.PoolNumberService;

public class TestXml {
	@Test  
	public void testSpring(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PoolNumberService poolNumberService= (PoolNumberService) ac.getBean("poolNumberServiceImpl");
		System.out.println(poolNumberService.getNextSDCommissionSerizlnumber());
		
		
	}

}
