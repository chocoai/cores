package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Animaltype;

public interface AnimaltypeService extends BaseLongDao<Animaltype>{
	List<Map<String, Object>> getAllAnimalTypeIdName();
	Map<String,Object> getAllAnimalType(String rows,String page);
	List<String> getAnimalTypes();
}
