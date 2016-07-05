package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Miscarriage;
import com.lanen.model.Miscarriage_Json;

public interface MiscarriageService extends BaseLongDao<Miscarriage>{
	Map<String, Object> getAllMiscarriageAnimal(String page,String rows,String monkeyid,String start,String end);
	/**
	 * 获取动物流产信息
	 */
	List<Miscarriage_Json> getAllMiscarriageById(String monkeyid);
}
