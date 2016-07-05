package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Symptomsarea;

public interface SymptomsareaService extends BaseLongDao<Symptomsarea> {

	Map<String,Object> getSymptomsarea(String page,String rows,String name,String sys);
	List<Map<String, String>> getAllSymptomsareaMap();
}
