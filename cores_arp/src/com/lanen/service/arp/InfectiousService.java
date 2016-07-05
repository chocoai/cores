package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Infectious;
import com.lanen.model.Quarantine;

public interface InfectiousService extends BaseLongDao<Infectious> {
	
	Map<String,Object> loadListByMonkeyId(String rows,String page,String type,String monkeyid);
	
	Map<String,Object> loadListVaccine(String page, String rows);
	List<Map<String, String>> getAllInfectious(String type);
		
	/**
	 * 获取传染病名称列表
	 */
	List<Map<String,Object>> getInfectiousMap(String type);
	/**
	 * 通过infectiousname获取id
	 */
	Quarantine getIdByNamw(String name);
}
