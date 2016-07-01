package com.lanen.service.company;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.company.CompanyInfo;

public interface CompanyInfoService extends BaseDao<CompanyInfo> {

	/**
	 * 保存公司信息
	 * @param companyName
	 * @param imgName
	 * @param file
	 * @return
	 */
	Map<String,Object> save(String companyName,String imgName,File file);
	/**
	 * 获取公司信息
	 * @return
	 */
	CompanyInfo getNewestRecord();
	
	//List<DictReportCode> getAllReportCodeList();
	
}
