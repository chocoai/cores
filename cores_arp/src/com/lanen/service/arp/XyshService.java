package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Xysh;
import com.lanen.model.Xysh_Json;

public interface XyshService extends BaseLongDao<Xysh> {

	Map<String,Object> loadListByMonkeyId(String rows,String page,String monkeyid,String type);
	Map<String,Object> getXYSH(String rows,String page,String monkeyid,Date cdate);
	List<Map<String,Object>> getXYSHByDate(String date,String checkId);
	/**
	 * 根据动物编号查询血生化
	 */
	List<Xysh_Json> getXyshById(String monkeyid,String checkId);
}
