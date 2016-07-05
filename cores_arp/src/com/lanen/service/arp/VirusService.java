package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Virus;
import com.lanen.model.Virus_Json;

public interface VirusService extends BaseLongDao<Virus>{
	//Map getSurfaceListByMonkeyId(String id);
	//List<Map<String, Object>> getAllAnimalTypeIdName();
	//根据动物编号查询病毒检测信息
	Map<String,Object> loadListByMonkeyIdAndCdate(String rows,String page,String type);
	//根据monkeyid,cdate分组查询子项目信息
	Map<String,Object> loadListItem(String monkeyid,String cdate);
	//报表-分页
	Map<String,Object> getVirus(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getVirus(String monkeyid,String cdate,String checkId);
	List<?> getLastOneVirusInfo(String monkeyid);
	/**
	 * 根据动物编号查询病毒
	 */
	List<Virus_Json> getVirusById(String monkeyid,String checkId);
}
