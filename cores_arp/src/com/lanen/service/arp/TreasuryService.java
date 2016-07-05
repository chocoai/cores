package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Treasury;

public interface TreasuryService extends BaseLongDao<Treasury> {

	Map<String,Object> getTreasury(String page,String rows,String name,String sys);
	List<?> getTreasuryCount(String start,String end,int symptomssite);
}
