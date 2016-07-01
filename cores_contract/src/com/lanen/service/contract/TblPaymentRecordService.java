package com.lanen.service.contract;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblPaymentRecord;

public interface TblPaymentRecordService extends BaseDao<TblPaymentRecord> {
	
    /**根据合同查询该合同的付款记录信息
     * @param contract
     * @return
     */
    List<TblPaymentRecord> getPaymentRecordListByContractCode(String contractCode);
}
