package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Gestation;

/**
 * 妊娠检查  service
 * @author Administrator
 */
public interface GestationService extends BaseLongDao<Gestation> {

	/**根据条件加载妊娠检查记录
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param checkdate
	 * @param ishave
	 * @return
	 */
	Map<String, Object> loadListByCondition(String page, String rows,
			String monkeyid, Date checkdate, Integer ishave);
    /**
     * 根据编号查询妊娠记录.
     */
	List<Gestation> getGestationById(String monkeyid);
}
