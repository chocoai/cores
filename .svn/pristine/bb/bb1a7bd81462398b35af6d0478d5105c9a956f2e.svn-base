package com.lanen.install;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.model.Regulation;
import com.lanen.model.User;


@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// 一、超级管理员
		User user = new User();
		user.setId("001");
		user.setUserName("admin");
		user.setUserCode("001");
		user.setRealName("管理员");
		user.setPassword(DigestUtils.md5Hex("admin")); // 要使用MD5摘要
//		Calendar cl=Calendar.getInstance();
//		cl.set(Calendar.YEAR, 2030);
//		user.setPasswordValid(cl.getTime());
		user.setUpdatePasswordTime(new Date());
		user.setFlag("可用");
		user.setCreateTime(new Date());
		session.save(user); // 保存
		// 系统管理员
		User user2 =new User();
		user2.setId("101");
		user2.setUserName("system");
		user2.setRealName("管理员");
		user2.setUserCode("101");
		user2.setPassword(DigestUtils.md5Hex("system")); // 要使用MD5摘要
		user2.setCreateTime(new Date());
		user2.setUpdatePasswordTime(new Date());
		user2.setFlag("可用");
		session.save(user2); // 保存
		// ===================================================
		//系统规则
		Regulation regulation =new Regulation();
		regulation.setRegulationName("缺省密码");
		regulation.setType("文本");
		regulation.setDefaultValue("111111");
		session.save(regulation);
		Regulation regulation2 =new Regulation();
		regulation2.setRegulationName("密码有效期（天）");
		regulation2.setType("整数");
		regulation2.setDefaultValue("90");
		session.save(regulation2);

		

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
