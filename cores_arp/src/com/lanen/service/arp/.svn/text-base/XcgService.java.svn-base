package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Xcg;
import com.lanen.model.Xcg_Json;

public interface XcgService extends BaseLongDao<Xcg> {

	Map<String,Object> loadListByMonkeyId(String rows,String page,String monkeyid,String type);
	Map<String,Object> getXCG(String rows,String page,String monkeyid,Date cdate);
	List<Map<String,Object>> getXCGByDate(String date,String checkId);
	/**
	 * 根据动物编号查询血常规
	 */
	List<Xcg_Json> getXcgById(String monkeyid,String checkId);
}
