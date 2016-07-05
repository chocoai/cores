package com.lanen.service.arp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Changeroom;

public interface ChangeroomService extends BaseLongDao<Changeroom> {

	/**根据动物编号，调栏日期查询调栏记录，没有条件查询全部
	 * @param page
	 * @param rows
	 * @param monkeyid
	 * @param changeroomdate
	 * @return
	 */
	Map<String, Object> getListByConditions(String page, String rows,
			String monkeyid, Date changeroomdate);
	List<?> getListByConditions(String monkeyid,String changeroomdate,Long xkeeper,Long ykeeper);
	Map<String, Object> getChangeroom(String page, String rows,String monkeyid, Date changeroomdate,Long xkeeper,Long ykeeper);
	/**
	 * 动物调拨时，获取原饲养员，现饲养员
	 */
	List<Long> getXKeeper();
	List<Long> getYKeeper();
	/**
	 * 根据动物号获取调拨记录
	 */
	List<Changeroom> getChangeroomById(String monkeyid);

}
