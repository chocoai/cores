package com.lanen.service.contract;

import com.lanen.base.BaseLongDao;
import com.lanen.model.contract.ContractPoolNum;

public interface ContractPoolNumService extends  BaseLongDao<ContractPoolNum>{
	
	/**获得SD任命书编号（例如2014-001,2014-1083）
	 * @return
	 */
	String getNextSDCommissionSerizlnumber();
	
	/**
	 * 获得合同编号
	 * @return
	 */
	String getNextContractPoolNum();
	
	/**
	 * 获得供试品总个数
	 * @param contractCode合同编号
	 * @return
	 */
	int getTblTestItemTotal(String contractCode); 

}
