package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Quarantine;

public interface QuarantineService extends BaseLongDao<Quarantine> {

	Map<String,Object> getQuarantine(String page,String rows,String name,String type);
	List<Quarantine> getQuarantineByMark(String mark);
	String getQuarantineAndMBy(String Remark,String Mremark);
	List<Map<String, String>> getMethod(String type);
	List<Map<String,String>> getQuarantineName(String type);
}
