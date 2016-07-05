package com.lanen.service.arp;

import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Routine;

public interface RoutineService extends BaseLongDao<Routine> {

	Map<String, Object> loadListByCondition(String page,String rows,String monkeyid);
	Map<String, Object> loadNormalList();
	Map<String,Object> virus(String monkeyid);
}
