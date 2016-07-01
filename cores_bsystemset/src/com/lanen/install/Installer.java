package com.lanen.install;


import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.model.contract.TblStudyScheduleNode;


@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		//项目进度计划设置（字典默认项）
		TblStudyScheduleNode  tblStudyScheduleNode = new TblStudyScheduleNode();
		
		tblStudyScheduleNode.setId("01");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(100);
		tblStudyScheduleNode.setNodeName("SD任命");
//		tblStudyScheduleNode.setPlanDays()
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("02");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(200);
		tblStudyScheduleNode.setNodeName("试验开始");
//		tblStudyScheduleNode.setPlanDays()
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("03");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(300);
		tblStudyScheduleNode.setNodeName("试验分组");
		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("04");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(400);
		tblStudyScheduleNode.setNodeName("首次给药");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("05");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(500);
		tblStudyScheduleNode.setNodeName("给药结束");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("06");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(600);
		tblStudyScheduleNode.setNodeName("试验期末");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("07");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(700);
		tblStudyScheduleNode.setNodeName("恢复期末");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("08");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(800);
		tblStudyScheduleNode.setNodeName("试验完成");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
		
		tblStudyScheduleNode = new TblStudyScheduleNode();
		tblStudyScheduleNode.setId("09");
		tblStudyScheduleNode.setStudyTypeCode("@@@@@@");
		tblStudyScheduleNode.setNodeSn(900);
		tblStudyScheduleNode.setNodeName("归档");
//		tblStudyScheduleNode.setPlanDays("0");
		tblStudyScheduleNode.setDefaultNode(1);
		session.saveOrUpdate(tblStudyScheduleNode);
	} 

	public static void main(String[] args) {
		System.out.println("正在执行安装...");

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

		System.out.println("== 安装完毕 ==");
		System.exit(0);
	}
}
