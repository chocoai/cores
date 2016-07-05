package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Normal;

public interface ExportService extends BaseLongDao<Normal> {

	Map<String,Object> getExportQuarantine(String page,String rows,String orderid,String startdate,String enddate);
	Map<String,Object> getMonkeyList(String page,String rows,String id);
	Integer getNextNormalId();
	ArrayList<Object> getExcelFiledNameList();
	ArrayList<Object> getExcelFiledDataList(long id);
	List<?> getSelectHouse(String monkeyid);
	ArrayList<Object> getExcelFiledNameList(String kind);
	ArrayList<Object> getVaccineExcelFiledDataList(long id);
	ArrayList<Object> getVirusExcelFiledDataList(long id);
	ArrayList<Object> getBacteriaExcelFiledDataList(long id);
	ArrayList<Object> getParasiteExcelFiledDataList(long id);
	ArrayList<Object> getQCExcelFiledDataList(long id);
	ArrayList<Object> getTBExcelFiledDataList(long id);
	ArrayList<Object> getXCGExcelFiledDataList(long id);
	ArrayList<Object> getXYSHExcelFiledDataList(long id);
	ArrayList<Object> getInfectiousExcelFiledDataList(long id);
	ArrayList<Object> getXExcelFiledDataList(long id);
	ArrayList<Object> getSurfaceExcelFiledDataList(long id);
}
