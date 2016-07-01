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
import com.lanen.model.TblFileRecordSpecimen;
import com.lanen.service.archive.TblFileRecordSpecimenService;
import com.lanen.util.DateUtil;
@Service
public class TblFileRecordSpecimenServiceImpl extends BaseDaoImpl<TblFileRecordSpecimen> implements
		TblFileRecordSpecimenService {
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getByCondition(Integer isDestroySpecimen,
			Integer specimenTypeFlag,Date fileStartDate,
			Date fileEndDate, Date keepEndDate, Integer isDestory,
			Integer isValid, String searchString,Integer page,Integer rows) {
		String hql = " SELECT study.[fileRecordID] fileRecordId,study.[archiveCode],[fileRecordSn]" +
				"      ,[specimenTypeFlag] ,[studyNo] ,[studyNoType] ,[studyName],[SD] sd,[fileNum] ,[fileNumUnit],[fileOperator] ,[fileDate],[keepDate],[destoryDate],[destoryRegSign] ,study.[operateTime] ,study.[operator]" +
				"	  ,[remark],[keyWord],[delFlag],[delTime] ,[specimenDestoryRegSign],[specimenDestoryDate] ,[smplDestoryDate] ,[smplDestoryRegSign]" +
				"     ,[archiveTypeCode],[archiveTypeFlag],[storePosition],[archiveTitle] ,[validationFlag]"+
				"  FROM [CoresArchives].[dbo].[TblFileRecord_Specimen] study" +
				"  left join [CoresArchives].[dbo].[TblFileIndex] tblFileIndex on tblFileIndex.archiveCode=study.archiveCode " +
				" where (delFlag is null or delFlag=0) ";
		
		
		if(isDestroySpecimen!=null&&!"".equals(isDestroySpecimen)&&isDestroySpecimen==1)
		{
			
		}else{
			hql += " and (specimenDestoryDate is null or specimenDestoryRegSign is null )";
		}
		
		if(specimenTypeFlag!=null&&!"".equals(specimenTypeFlag)&&specimenTypeFlag!=0)//0是全部
		{
			hql += " and specimenTypeFlag = :specimenTypeFlag";
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
			hql += " and (tblFileIndex.archiveCode like :searchString or studyNo like :searchString or studyName like :searchString or keyWord like :searchString" +
					" or tblFileIndex.archiveTitle like :searchString )";
		}
		
		Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		hql+=" order by tblFileIndex.archiveCode desc,fileRecordSn";
		Query query = getSession().createSQLQuery(hql);
		
		if(specimenTypeFlag!=null&&!"".equals(specimenTypeFlag)&&specimenTypeFlag!=0)
		{
			query.setInteger("specimenTypeFlag", specimenTypeFlag);
			query2.setInteger("specimenTypeFlag", specimenTypeFlag);
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
	public List<TblFileRecordSpecimen> getByArchiveCode(String archiveCode)
	{
		String hql = "from TblFileRecordSpecimen where tblFileIndex.archiveCode=:archiveCode";
		List<TblFileRecordSpecimen> list = getSession().createQuery(hql)
											.setParameter("archiveCode", archiveCode)
											.list();
		return list;
	}
	
	public Integer getMaxSnByArchiveCode(String archiveCode) {
		String sql = "select max(fileRecordSn) from [CoresArchives].[dbo].[TblFileRecord_Specimen] " +
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
}
