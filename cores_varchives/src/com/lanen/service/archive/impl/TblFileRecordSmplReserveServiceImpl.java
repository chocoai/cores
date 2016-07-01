package com.lanen.service.archive.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblFileContentContract;
import com.lanen.model.TblFileRecordSmplReserve;
import com.lanen.model.TblFileRecordSpecimen;
import com.lanen.service.archive.TblFileRecordSmplReserveService;
import com.lanen.util.DateUtil;
@Service
public class TblFileRecordSmplReserveServiceImpl extends
		BaseDaoImpl<TblFileRecordSmplReserve> implements
		TblFileRecordSmplReserveService {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getByCondition(Integer isSmplKeepEndDate,Date smplKeepEndDate,Integer isDestroySmpl,
			String smplType,Date fileStartDate,
			Date fileEndDate, Date keepEndDate, Integer isDestory,
			Integer isValid, String searchString,Integer page,Integer rows) {
		String hql = " SELECT study.[fileRecordID] fileRecordId,study.[archiveCode],[fileRecordSn],[smplType],[smplCode],[smplName],[sponsorName] ,[container] ,[reserveNum],[reserveNumUnit]" +
				",[reportUnitName],[smplProvUnitName] ,[batchCode] ,[validDate] ,[reserveDate],[reserveMan],[reserveRecMan] ,[reserveRecDate],[fileOperator],[fileDate],[keepDate],[destoryDate],[destoryRegSign] ,study.[operateTime] ,study.[operator],[remark],[keyWord],[delFlag],[delTime],[smplDestoryRegSign],[smplDestoryDate] ,[storageCondition] ,[reserveBalance] ,[gross],[grossUnit],[grossBalance]" +
				",archiveTypeCode,archiveTitle,storePosition,validationFlag" +
				"  FROM [CoresArchives].[dbo].[TblFileRecord_SmplReserve] study" +
				"  left join [CoresArchives].[dbo].[TblFileIndex] tblFileIndex on tblFileIndex.archiveCode=study.archiveCode" +
				"  where (delFlag is null or delFlag=0) ";
		
		/*<option value="0" selected="selected">全部</option>
		<option value="1">农药</option>
		<option value="2">医药</option>
		<option value="3">化学品</option>*/
		if(isSmplKeepEndDate!=null&&!"".equals(isSmplKeepEndDate)&&isSmplKeepEndDate==1
				&&smplKeepEndDate!=null&&!"".equals(smplKeepEndDate))
		{
			hql += " and validDate <=:smplKeepEndDate";
		}
		if(isDestroySmpl!=null&&!"".equals(isDestroySmpl)&&isDestroySmpl==1)
		{
			
		}else{
			hql += " and (smplDestoryDate is null or smplDestoryRegSign is null )";
		}
		if(smplType!=null&&!"".equals(smplType)&&!"0".equals(smplType))
		{
			if("1".equals(smplType))
			{
				hql += " and smplType ='农药' ";
			}else if("2".equals(smplType))
			{
				hql += " and smplType ='医药' ";
			}else if("3".equals(smplType))
			{
				hql += " and smplType ='化学品' ";
			}
		}
		if(fileStartDate!=null&&!"".equals(fileStartDate))
		{
			hql += " and fileDate >=:fileStartDate";
		}
		if(fileEndDate!=null&&!"".equals(fileEndDate))
		{
			hql += " and fileDate <=:fileEndDate";
		}
		if(keepEndDate!=null&&!"".equals(keepEndDate))
		{
			hql += " and ( keepDate <=:keepEndDate)";
		}
		if(isDestory!=null&&isDestory==1)
		{
			//hql += " and tblFileRecord.destoryDate is not null and tblFileRecord.destoryRegSign is not null ";
		}else {
			hql += " and (destoryDate is null or destoryRegSign is null )";
		}
		if(isValid!=null&&isValid==1)
		{
			//hql += " and tblFileRecord.tblFileIndex.validationFlag=1";//包含是所有的
		}else {
			hql += " and (tblFileIndex.validationFlag is null or tblFileIndex.validationFlag=0)";
		}
		if(searchString!=null)
		{
			//instrumentModel,instrumentManufacturer;
			hql += " and (tblFileIndex.archiveCode like :searchString or smplCode like :searchString or smplName like :searchString or keyWord like :searchString" +
					"	or sponsorName like :searchString or batchCode like :searchString" +
					" or tblFileIndex.archiveTitle like :searchString) ";
		}
		
		Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		hql+=" order by tblFileIndex.archiveCode desc,study.fileRecordSn";
		Query query = getSession().createSQLQuery(hql);
		
		if(isSmplKeepEndDate!=null&&!"".equals(isSmplKeepEndDate)&&isSmplKeepEndDate==1
				&&smplKeepEndDate!=null&&!"".equals(smplKeepEndDate))
		{
			
			query.setString("smplKeepEndDate", DateUtil.dateToString(smplKeepEndDate,"yyyy-MM-dd"));
			query2.setString("smplKeepEndDate", DateUtil.dateToString(smplKeepEndDate,"yyyy-MM-dd"));
		}
		
		if(fileStartDate!=null&&!"".equals(fileStartDate))
		{
			query.setString("fileStartDate", DateUtil.dateToString(fileStartDate,"yyyy-MM-dd"));
			query2.setString("fileStartDate", DateUtil.dateToString(fileStartDate,"yyyy-MM-dd"));
		}
		if(fileEndDate!=null&&!"".equals(fileEndDate))
		{
			query.setString("fileEndDate", DateUtil.dateToString(fileEndDate,"yyyy-MM-dd"));
			query2.setString("fileEndDate", DateUtil.dateToString(fileEndDate,"yyyy-MM-dd"));
		}
		if(keepEndDate!=null&&!"".equals(keepEndDate))
		{
			query.setString("keepEndDate", DateUtil.dateToString(keepEndDate,"yyyy-MM-dd"));
			query2.setString("keepEndDate", DateUtil.dateToString(keepEndDate,"yyyy-MM-dd"));
		}
		
		if(searchString!=null)
		{
			query.setString("searchString", "%"+searchString+"%");
			query2.setString("searchString", "%"+searchString+"%");
		}
		
		Integer total = (Integer)query2.uniqueResult();
		List<Map<String, Object>> list = query.setFirstResult((page-1)*rows)
													.setMaxResults(rows)
													.setResultTransformer(new MapResultTransformer())
													.list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", list);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblFileRecordSmplReserve> getByArchiveCode(String archiveCode)
	{
		String hql = "from TblFileRecordSmplReserve where tblFileIndex.archiveCode=:archiveCode";
		List<TblFileRecordSmplReserve> list = getSession().createQuery(hql)
											.setParameter("archiveCode", archiveCode)
											.list();
		return list;
	}
	
	public Integer getMaxSnByArchiveCode(String archiveCode) {
		String sql = "select max(fileRecordSn) from [CoresArchives].[dbo].[TblFileRecord_SmplReserve] " +
				" where archiveCode=:archiveCode";
		Object maxSn = getSession().createSQLQuery(sql)
									.setParameter("archiveCode", archiveCode)
									.uniqueResult();
		if(maxSn!=null)
		{
			return (Integer)maxSn;
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSmplListByCode(String smplCode)
	{
		//tiType,batchCode,smplName,contractCode,sponsorName,validDate,smplCode,reserveNum,reserveUnit,reportUnitName,
		//smplProvUnitName,reserveBalance,gross,grossUnit,grossBalance,storageCondition,reserveMan,reserveDate
		String sql = "SELECT tiType,recList.CONFIRMCODE batchCode,tiName smplName,contr.venderName,contr.contractCode,sponsorName,validityPeriod validDate," +
				"	 reserve.[SMPLCODE] smplCode,reserve.[Degree],[BottleNo],reserve.[ReserveNum] reserveNum,reserve.[ReserveUnit] reserveNumUnit," +
				"		 contr.venderName reportUnitName,contr.sponsorName smplProvUnitName," +
				"		 cast( [ReserveBalance] as varchar) reserveBalance,[Gross] gross,[GrossUnit] grossUnit,[GrossBalance] grossBalance,[LabWeight],[LabBalance] ,storageCondition," +
				"		 reserve.[Room],[ReserveBottleNum],[KeepDays],[ReserveMan] reserveMan,[ReserveManCode],[LastNum] ,[ReserveDate] reserveDate,reserve.[Remark]" +
				"		  FROM [TIMSDB].[dbo].[TBSMPLRESERVE] reserve" +
				"		  left join [TIMSDB].[dbo].[TBSMPLRECLIST] recList on recList.SMPLCODE=reserve.SMPLCODE and recList.DEGREE=reserve.Degree" +
				"		left join [CoresContract].[dbo].[tblTestItem] testItem on testItem.tiNo=reserve.SMPLCODE" +
				"		 left join [CoresContract].[dbo].[tblContract] contr on contr.contractCode = testItem.contractCode" +
				" where reserve.SMPLCODE=:smplCode ";
		List<Map<String, Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("smplCode", smplCode)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}
	
	
	
}
