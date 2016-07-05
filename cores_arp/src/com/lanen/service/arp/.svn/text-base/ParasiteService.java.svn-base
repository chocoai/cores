package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Parasite;

public interface ParasiteService extends BaseLongDao<Parasite>{
	//根据动物编号查询病毒检测信息
	Map<String,Object> loadListByMonkeyIdAndCdate(String rows,String page,String type);
	//根据monkeyid,cdate分组查询子项目信息
	Map<String,Object> loadListItem(String monkeyid,String cdate);
	//样品种类
	List<Map<String,String>> getYP(String mark);
	//报表--分页
	Map<String,Object> getInParasite(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getInParasite(String monkeyid,String cdate,String checkId);
	Map<String,Object> getOutParasite(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getOutParasite(String monkeyid,String cdate);
	Parasite getParasite(String monkeyid,String normalid);
	/**
	 * 最近6次寄生虫检疫
	 */
	List<Parasite> getLast6ParasiteRecord(String monkeyid);
}
