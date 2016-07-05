package com.lanen.service.arp;

import java.util.Date;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Publiccode;

public interface PubliccodeService extends BaseLongDao<Publiccode> {

	Map<String,Object> getQC(String rows,String page,String monkeyid,Date cdate);
	Map<String,Object> getQC(String monkeyid,String cdate,String checkId);
}
