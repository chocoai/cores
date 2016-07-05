package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Customer;
import com.lanen.model.Sale;

public interface SaleService extends BaseLongDao<Sale> {
    /**寻找订单记录，判断条件是当前是否有订单猴子已出场但是未添加出场记录
     * @return
     */
    List<?> getListByAddSalemonkey();

	/**得到所有订单状态在1(确认选猴)的订单
	 * @return
	 */
	List<Sale> getListBySelect();
	/**
	 * 检查订单编号
	 */
	boolean isExistTitle(String title);
	/**
	 * 销售类型
	 */
	List<Map<String,String>> getSaleTypeMap();
	boolean isExistOutMonkey(String monkeyid);
	Map<String,Object> getListBySelect1(String page,String rows);
	/**
	 * 运送地
	 */
	List<?> getListAddress();
	Customer getAddressById(Long id);
}
