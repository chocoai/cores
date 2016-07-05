package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jfree.chart.JFreeChart;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Task;

public interface SchedulePlanService extends BaseLongDao<Task>{
	
	Map<String,Object> getSchedulebyTaskKindCodeTypeTaskType(String rows, String page,Integer status,String title,Date begindate,Date enddate,Integer typeid,Long ower);
	List<Map<String,String>> getSchedulePlanMapNo(String mark);
	Map<String,Object> getSchedulePlanTips(String d1,String d2);
	JFreeChart createBarChart();
	//计划类型名称.
	String getTypeName(Integer id);
}
