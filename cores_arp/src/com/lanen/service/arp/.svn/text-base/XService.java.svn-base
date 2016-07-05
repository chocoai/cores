package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Publiccode;
import com.lanen.model.X;

public interface XService extends BaseLongDao<X> {

	Map<String,Object> loadListByMonkeyId(String rows,String page,String monkeyid,String type);
	List<Map<String,String>> getCheckArea();
	/**
	 * 检疫部位
	 */
	List<Map<String,Object>> getCheckArea(String mark);
	/**
	 * 获取部位Id
	 */
	Publiccode getIdByNamess(String name);
}
