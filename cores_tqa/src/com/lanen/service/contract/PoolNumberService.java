package com.lanen.service.contract;

import com.lanen.base.BaseLongDao;
import com.lanen.model.contract.PoolNumber;
/**
 * 流水号池servcie
 * @author 黄国刚
 *
 */
public interface PoolNumberService extends BaseLongDao<PoolNumber> {
	
	/**获得SD任命书编号（例如2014-001,2014-1083）
	 * @return
	 */
	String getNextSDCommissionSerizlnumber();
}
