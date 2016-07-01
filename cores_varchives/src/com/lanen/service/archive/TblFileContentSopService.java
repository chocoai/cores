package com.lanen.service.archive;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.TblFileContentSop;

public interface TblFileContentSopService extends BaseDao<TblFileContentSop> {

	/**
	 * 
	 * @return
	 */
	Map<String, Object> getByCondition(Integer fileFlag,Integer isAll,Integer sopflag,Integer isNowValid,Integer isInvalid,Integer needChange,Date changeEndDate,Integer yearNum,Integer yearNumUnit, Date fileStartDate,Date fileEndDate,Date keepEndDate,Integer isDestory,Integer isValid,String searchString,Integer page,Integer rows);
	/**
	 * 
	 * @param sopcode
	 * @return
	 */
	List<TblFileContentSop> getBySopCode(String sopcode);
	/**
	 * 根据编号获取最大版本号
	 * @param sopcode
	 * @return
	 */
	TblFileContentSop getMaxVerByCode(String sopcode);
	
	/**
	 * 获取没有归档的该编号的sop
	 * @param sopcode
	 * @return
	 */
	List<TblFileContentSop> getNotArchiveListByCode(String sopcode);
	/**
	 * 根据编号获取已经入档的最大版本号
	 * @param sopcode
	 * @return
	 */
	TblFileContentSop getArchiveMaxVerByCode(String sopcode);
	/**
	 * 
	 * @param sopcode
	 * @param sopflag
	 * @return
	 */
	boolean isExistCodeInSop(String sopcode,Integer sopflag);
	/**
	 * 
	 */
	boolean isExistCodeAndVer(String sopcode,String sopver);
	/**
	 * 
	 * @param sopcode
	 * @param sopver
	 * @return
	 */
	boolean isExistCodeAndVerInArchive(String sopcode,String sopver);
	/**
	 * 
	 */
	boolean isExistCodeAndVerExceptOne(String sopcode,String sopver,String fileRecordId);
	
	/**
	 * 获取当前所有有效的sop，或sop表格
	 * @param sopflag
	 * @return
	 */
	List<TblFileContentSop> getAllvalidListByFlag(Integer sopflag);
	
	/**
	 * 
	 * @param sopCodeAndSopVerList
	 * @return
	 */
	List<String> getExistBySopCodeAndVerList(List<String> sopCodeAndSopVerList);
}
