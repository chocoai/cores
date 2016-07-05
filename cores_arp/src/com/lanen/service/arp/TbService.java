package com.lanen.service.arp;

import java.util.Date;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Tb;

public interface TbService extends BaseLongDao<Tb> {

	Map<String,Object> loadListByMonkeyId(String rows,String page,String monkeyid,String type);
	//TB报表
	Map<String,Object> getTB(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getTB(String monkeyid,String cdate,String checkId);
}
