package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblReportRecord;
@Service
public class TblReportRecordServiceImpl extends BaseDaoImpl<TblReportRecord> implements TblReportRecordService {

	public List<Map<String, String>> getBycontractCodeStudyCodeList(String contractCode) {
		String  sql = "  SELECT [studyNo] " +
				" FROM [CoresContract].[dbo].[tblStudyItem] WHERE [contractCode] = :contractCode and " +
				"  [sd] is not null and [sd] != ''";
		List<?> list = getSession().createSQLQuery(sql).setParameter("contractCode", contractCode).list();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		if(null != list && list.size()>0){
			Map<String,String> map = null;
			for(Object obj:list){
			map = new HashMap<String, String>();
			map.put("id", (String)obj);
			map.put("text",(String)obj);
			mapList.add(map);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getDeliveryModeList() {
		String sql = "SELECT DISTINCT [deliveryMode]"+
		" FROM      tblReportRecord";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblReportRecord> getByContractCodeList(String contractCode) {
		List<TblReportRecord> list = getSession().createQuery("From TblReportRecord where contractCode = ? ")
		.setParameter(0, contractCode)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByReportCode(String reportCode) {
		List<TblCustomer> list=getSession().createQuery("From TblReportRecord t where t.reportCode = ? ")//
		.setParameter(0, reportCode)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

}
