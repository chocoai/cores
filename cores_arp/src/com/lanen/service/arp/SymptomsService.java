package com.lanen.service.arp;

import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Symptoms;

public interface SymptomsService extends BaseLongDao<Symptoms> {

	Map<String,Object> getSymptoms(String page,String rows,String name,String sys);
}
