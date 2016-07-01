package com.lanen.util;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lanen.service.contract.TblStudyScheduleService;

public class TestXml {

	@Test
	public void testTblStudySchedule(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		TblStudyScheduleService tblStudyScheduleService = (TblStudyScheduleService) ac.getBean("tblStudyScheduleServiceImpl");
		List list =tblStudyScheduleService.getListByStudyNo("34-1-1");
		
		
	}
}
