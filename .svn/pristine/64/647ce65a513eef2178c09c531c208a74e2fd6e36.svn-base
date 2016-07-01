package com.lanen.service.contract;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblReportRecord;

public interface TblReportRecordService extends BaseDao<TblReportRecord>{
	
	/**
	 * 根据合同编号获取课题下拉选
	 * @param contractCode
	 * @return
	 */
	List<Map<String, String>> getBycontractCodeStudyCodeList(String contractCode);

	/**接受方式（去重）
	 * @return
	 */
	List<String> getDeliveryModeList();
	/**
	 * 根据合同编号获取list集合
	 * @param contractCode
	 * @return
	 */
	List<TblReportRecord> getByContractCodeList(String contractCode);
	
	
	boolean isExistByReportCode(String reportCode);
}
