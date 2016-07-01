package com.lanen.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerListener implements ServletContextListener {
	
	private Timer timer=null;
	private RegulationTask regulationTask;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		
		event.getServletContext().log("定时器销毁");
		// 定时器销毁

	}

	public void contextInitialized(ServletContextEvent event) {
		// 定时器启动
		timer =new Timer(true);
		regulationTask = new RegulationTask(event.getServletContext());
		event.getServletContext().log("定时器已启动");
		timer.schedule(regulationTask, 0, 1000*60*60*24);//任务     延时0秒       间隔3秒
		event.getServletContext().log("已添加任务列表");
		

	}

}
