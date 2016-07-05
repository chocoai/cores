package com.lanen.service.arp;

import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Iplogin;

public interface IploginService extends BaseLongDao<Iplogin> {
	
	Map<String,Object>getListByConditions(String page, String rows, String id,String start, String end);
}
