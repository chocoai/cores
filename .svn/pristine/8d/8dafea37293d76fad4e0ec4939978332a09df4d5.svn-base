package com.lanen.listener;

import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

public class RegulationTask extends TimerTask {
	private ServletContext context;
	private static boolean isRunning = false;
//	private static boolean flag = true;
//	private static final int C_SCHEDULE_HOUR = 15;
	
	public RegulationTask(ServletContext context){
		this.context=context;
	}

	@Override
	public  void run() {//synchronized
		Calendar cal=Calendar.getInstance();
		if(!isRunning){
//			   if(C_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY) && flag){
				isRunning = true;
				context.log("开始执行指定任务");
				
				//需要执行的代码
				System.out.println("4");
				
				
				
				
			        isRunning = false;
//			        flag = false;
			        context.log("指定任务结束");
//				}


			    }else {
				context.log("上一次任务执行还未结束");
				}

//			    if(C_SCHEDULE_HOUR != cal.get(Calendar.HOUR_OF_DAY)){
//
//			    flag = true;
//			} 

	}

}
