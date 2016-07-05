package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Normal;
import com.lanen.model.Normal_Json;

public interface NormalService extends BaseLongDao<Normal> {

	Map<String,Object> getChildMonkey(String page,String rows,String monkeyid,String startdate,String enddate);
	//更新Normal表
	void updateNormalById(String id,String type);
	//由id查询normal表是否存在检测项目,monkeyid,checkdate
	List<Map<String,Object>> listItem(String id);	
	boolean isExistNormalid(String id);
	//查询检疫时间.
	String getCheckDateByTitle(String title);
	/**
	 * 查询检疫信息.
	 */
	Normal_Json getCheckItemInfo(String title);
}
