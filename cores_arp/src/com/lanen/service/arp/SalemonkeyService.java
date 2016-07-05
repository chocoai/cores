package com.lanen.service.arp;

import java.util.Date;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Salemonkey;

public interface SalemonkeyService extends BaseLongDao<Salemonkey> {

	/**根据条件加载出场记录列表
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param outdate
	 * @param blongsale
	 * @return
	 */
	Map<String, Object> getListByConditions(String page, String rows,
			String monkeyid, Date outdate, String blongsale);
}
