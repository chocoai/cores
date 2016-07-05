package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Inventory;

public interface InventoryService extends BaseLongDao<Inventory> {

	/**根据盘点日期和饲养员得到盘点记录
	 * @param date 
	 * @param long1 
	 * @return
	 */
	List<Inventory> getListByInventoryDate(Long keeper, Date inventoryDate);

	/**得到表头
	 * @return
	 */
	ArrayList<Object> getExcelFiledNameList();

	/**获取表数据
	 * @param keeper
	 * @param inventoryDate
	 * @return
	 */
	ArrayList<Object> getExcelFiledDataList(Long keeper, Date inventoryDate);

}
