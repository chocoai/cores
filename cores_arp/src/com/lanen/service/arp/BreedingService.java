package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Breeding;
import com.lanen.model.Breeding_Json;

/**
 * 发情配种   service
 * @author Administrator
 */
public interface BreedingService extends BaseLongDao<Breeding> {

	/**根据条件加载发情配种信息
	 * @param page(母猴编号)
	 * @param rows(母猴编号)
	 * @param monkeyid(母猴编号)
	 * @param oestrusdate(发情日期)
	 * @param breedingdate(配种日期)
	 * @param matingdate(交配日期)
	 * @param oestrustype(发情类型)
	 * @return
	 */
	Map<String, Object> loadListByCondition(String page,String rows,String monkeyid, Date oestrusdate,
			Date breedingdate, Date matingdate, Integer oestrustype);

	/**
	 * 发情类型
	 */
	List<Map<String, String>> getAllOestrusTypeMapNo(String mark);
	
	/**
	 * 获取动物发情信息
	 */
	List<Breeding_Json> getAllOestrusById(String monkeyid);
}
