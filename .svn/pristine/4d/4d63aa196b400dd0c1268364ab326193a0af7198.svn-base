package com.lanen.service.archive.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.morph.reflect.reflectors.MapReflector;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblFileContentContract;
import com.lanen.service.archive.TblFileContentContractService;
import com.lanen.util.DateUtil;
@Service
public class TblFileContentContractServiceImpl extends BaseDaoImpl<TblFileContentContract> implements
		TblFileContentContractService {

	public Map<String, Object> getByCondition(Integer contractTypeFlag,Date fileStartDate,
			Date fileEndDate, Date keepEndDate, Integer isDestory,
			Integer isValid, String searchString,Integer page,Integer rows) {
		String hql = " SELECT study.[fileRecordID] fileRecordId,study.[archiveCode],[contractCode],[contractName],[contractTypeFlag],[sponsorName] ,[num],[beginDate],[endDate] ,[terminalDate]" +
				",archiveTypeCode,archiveTitle,storePosition,fileDate,fileRecordSn,archiveMaker,fileOperator, keepDate,remark,destoryDate,validationFlag,archiveMediaFlag,archiveMedia,archiveMediaEleCode" +
				"  FROM [CoresArchives].[dbo].[TblFileContent_Contract] study" +
				"   left join [CoresArchives].[dbo].[TblFileRecord] tblFileRecord   on tblFileRecord.fileRecordID = study.fileRecordID" +
				"  left join [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode=study.archiveCode" +
				"  where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) ";
		
		if(contractTypeFlag!=null&&!"".equals(contractTypeFlag)&&contractTypeFlag!=0)
		{
			hql += " and contractTypeFlag=:contractTypeFlag ";
		}
		if(fileStartDate!=null&&!"".equals(fileStartDate))
		{
			hql += " and tblFileRecord.fileDate >=:fileStartDate";
		}
		if(fileEndDate!=null&&!"".equals(fileEndDate))
		{
			hql += " and tblFileRecord.fileDate <=:fileEndDate";
		}
		if(keepEndDate!=null&&!"".equals(keepEndDate))
		{
			hql += " and ( tblFileRecord.keepDate <=:keepEndDate) ";
		}
		if(isDestory!=null&&isDestory==1)
		{
			//hql += " and tblFileRecord.destoryDate is not null and tblFileRecord.destoryRegSign is not null ";
		}else {
			hql += " and (tblFileRecord.destoryDate is null or tblFileRecord.destoryRegSign is null )";
		}
		if(isValid!=null&&isValid==1)
		{
			//hql += " and tblFileRecord.tblFileIndex.validationFlag=1";//包含是所有的
		}else {
			hql += " and (fileIndex.validationFlag is null or fileIndex.validationFlag=0)";
		}
		if(searchString!=null)
		{
			//instrumentModel,instrumentManufacturer;
			hql += " and (study.archiveCode like :searchString or contractName like :searchString or contractCode like :searchString or tblFileRecord.keyWord like :searchString" +
					" or fileIndex.archiveTitle like :searchString or sponsorName like :searchString)";
		}
		
		Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		hql+=" order by study.archiveCode desc,tblFileRecord.fileRecordSn";
		Query query = getSession().createSQLQuery(hql);
		
		if(contractTypeFlag!=null&&!"".equals(contractTypeFlag)&&contractTypeFlag!=0)
		{
			query.setInteger("contractTypeFlag",contractTypeFlag);
			query2.setInteger("contractTypeFlag",contractTypeFlag);
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
}
