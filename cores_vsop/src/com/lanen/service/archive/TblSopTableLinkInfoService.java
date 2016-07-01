package com.lanen.service.archive;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblSopTableLinkInfo;

public interface TblSopTableLinkInfoService extends
		BaseDao<TblSopTableLinkInfo> {
	
	/**
	 * 
	 * @return
	 */
	List<Map<String, Object>> getListBySOPCodeAndSOPVer(String code,String ver);
	/**
	 * 
	 * @return
	 */
	List<Map<String, Object>> getListByTblCodeAndTblVer(String code,String ver);
	/**
	 * 
	 * @param sopCode
	 * @return
	 */
	List<Map<String, Object>> getEffectLinkBySOP(String sopCode);
	/**
	 * 
	 * @param tblCode
	 * @return
	 */
	List<Map<String, Object>> getEffectLinkByTbl(String tblCode);
	/**
	 * 
	 * @param code
	 * @param ver
	 * @return
	 */
	List<TblSopTableLinkInfo> getBySOPCodeAndSOPVer(String code,String ver);
	/**
	 * 
	 * @param code
	 * @param ver
	 * @return
	 */
	List<TblSopTableLinkInfo> getByTblCodeAndTblVer(String code,String ver);

}
