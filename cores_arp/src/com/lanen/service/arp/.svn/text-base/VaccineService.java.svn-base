package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Vaccine;
import com.lanen.model.Vaccine_Json;

public interface VaccineService extends BaseLongDao<Vaccine> {
	
	Map<String,Object> loadListByMonkeyId(String rows,String page,String type,String monkeyid);
	//获取疫苗类型
	List<Map<String,String>> getYMLX(String type);
	Map<String,Object> loadListVaccine(String page, String rows);
	Map<String,Object> getVaccine(String page,String rows,String monkeyid,Date cdate);
	List<Vaccine_Json> getVaccine(String monkeyid,Date cdate,String checkId);
	List<?> getVaccineId(String monkeyid,String normalid);
	List<String> getDistinctMonkeyid();
	List<?> getVaccineIdByName(String name);
	/**
	 * 最新一次的疫苗检疫记录.
	 */
	List<Vaccine> getLast3VaccineRecord(String monkeyid);
	/**
	 * 根据动物编号查询疫苗记录.
	 */
	List<Vaccine_Json> getVaccineById(String monkeyid,String checkId);
}
