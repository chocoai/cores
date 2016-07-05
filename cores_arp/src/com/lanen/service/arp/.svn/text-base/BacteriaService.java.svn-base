package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Bacteria;

public interface BacteriaService extends BaseLongDao<Bacteria>{
	//根据动物编号查询细菌检测信息
	Map<String,Object> loadListByMonkeyIdAndCdate(String rows,String page,String type);
	//根据monkeyid,cdate分组查询子项目信息
	Map<String,Object> loadListItem(String monkeyid,String cdate);
	//细菌检测报表--分页
	Map<String,Object> getBacteria(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getBacteria(String monkeyid,String cdate,String checkId);
	/**
	 * 最近7次细菌检疫信息.
	 */
	List<Bacteria> getLast7BacteriaRecord(String monkeyid);
}
