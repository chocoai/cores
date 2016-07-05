package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Surface;

public interface SurfaceService extends BaseLongDao<Surface>{
	Map getSurfaceListByMonkeyId(String id);
	List<Map<String, Object>> getAllAnimalTypeIdName();
	//根据动物编号查询体表检测信息
	Map<String,Object> loadListByMonkeyId(String rows,String page,String type,String id);
	List<?> getSurfaceBymonkeyidAndCheckdate(String monkeyid,Date checkdate);
	List<?> getSurfaceByNormalId(String monkeyid,Integer normalid);
}
