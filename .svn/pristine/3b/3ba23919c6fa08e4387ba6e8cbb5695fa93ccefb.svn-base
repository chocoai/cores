package com.lanen.install;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.model.clinicaltest.PoolSpecimenId;


@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		//顺序号   血液生化
		PoolSpecimenId billNo=new PoolSpecimenId();
		billNo.setTestItem(1L);
		billNo.setCurrentDate("2013-08-22");
		billNo.setSerialNumLen(3);
		billNo.setPrefix("130822");
		billNo.setCurrentSerialNum("001");
		
		session.save(billNo);
		//顺序号    血凝
		PoolSpecimenId billNo1=new PoolSpecimenId();
		billNo1.setTestItem(3L);
		billNo1.setCurrentDate("2013-08-22");
		billNo1.setSerialNumLen(3);
		billNo1.setPrefix("130822");
		billNo1.setCurrentSerialNum("101");
		
		session.save(billNo1);
		//顺序号     血常规
		PoolSpecimenId billNo2=new PoolSpecimenId();
		billNo2.setTestItem(2L);
		billNo2.setCurrentDate("2013-08-22");
		billNo2.setSerialNumLen(3);
		billNo2.setPrefix("130822");
		billNo2.setCurrentSerialNum("001");
		
		session.save(billNo2);
		

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
