package com.lanen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new FileSystemXmlApplicationContext("src/applicationContext.xml");
		System.out.println("个数："+ac.getBeanDefinitionCount());
		for(int i=0;i<ac.getBeanDefinitionCount();i++)
		{
			System.out.println(ac.getBeanDefinitionNames()[i]);
		}
	
	}

}
