package com.lanen.service.arp;

import java.util.Date;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Death;

public interface DeathService extends BaseLongDao<Death> {

	/**根据条件查询死亡登记信息并分页
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param dissectdate
	 * @param remarks
	 * @return
	 */
	Map<String, Object> getListByConditions(String page, String rows, String monkeyid,
			Date dissectdate, String remarks);
	
	/**
	 * 根据编号查询死亡信息.
	 */
	Death getDeathById(String monkeyid);

}
