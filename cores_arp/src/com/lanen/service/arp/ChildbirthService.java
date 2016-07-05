package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Childbirth;
/**
 * 产仔登记   service
 * @author Administrator
 */
public interface ChildbirthService extends BaseLongDao<Childbirth> {

	/**根据条件加载产仔登记记录
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param labordate
	 * @param childercount
	 * @return
	 */
	Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, String labordate, Integer childercount);
	
	/**
	 * 根据编号查询产仔记录
	 */
	List<Childbirth> getChaildbirthById(String monkeyid);

}
