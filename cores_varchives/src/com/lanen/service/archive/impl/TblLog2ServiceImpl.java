package com.lanen.service.archive.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblLog;
import com.lanen.service.archive.TblLog2Service;
import com.lanen.util.DateUtil;
@Service
public class TblLog2ServiceImpl extends BaseDaoImpl<TblLog> implements TblLog2Service {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getByCondition(Integer archiveTypeFlag,Date logStartDate,Date logEndDate,String searchString,Integer page,Integer rows)
	{
		String hql=" SELECT  [ID] id,[ArchiveTypeFlag] archiveTypeFlag,[archiveCode],[fileRecordSn],[archiveTitle] ,[operateTypeFlag] ,[operateType] " +
				",[TableName] tableName ,[FieldName] fieldName,[FieldDesc] fieldDesc,[OldValue] oldValue,[NewValue] newValue" +
				",[Operator] operator,[OperateTime] operateTime,[OperateRsn] operateRsn,[OldFileRecordID] oldFileRecordID" +
				"  FROM [CoresArchives].[dbo].[TblLog] where 1=1 ";
		if(archiveTypeFlag!=null&&!"".equals(archiveTypeFlag)&&archiveTypeFlag!=0)//0是全部
		{
			if(archiveTypeFlag==4||archiveTypeFlag==11)
			{
				//4和11的情况下面处理
			}else{
				hql+=" and archiveTypeFlag=:archiveTypeFlag ";
				
			}
		}
		if(logStartDate!=null&&!"".equals(logStartDate)){
			hql+=" and operateTime>=:start ";
		}
		if(logEndDate!=null&&!"".equals(logEndDate)){
			hql+=" and operateTime<=:end ";
		}
		if(searchString!=null&&!"".equals(searchString))
		{
			hql+=" and (archiveCode like :searchString or archiveTitle like :searchString) ";
		}
		
		if(archiveTypeFlag!=null&&!"".equals(archiveTypeFlag)&&archiveTypeFlag!=0)//0是全部
		{
			if(archiveTypeFlag==4||archiveTypeFlag==11)
			{
				//4和11的情况下面处理
				hql = " SELECT  baseGlpFlag,[ID] id,[ArchiveTypeFlag] archiveTypeFlag,a.[archiveCode],a.[fileRecordSn],[archiveTitle] ,[operateTypeFlag] ,[operateType] " +
				",[TableName] tableName ,[FieldName] fieldName,[FieldDesc] fieldDesc,[OldValue] oldValue,[NewValue] newValue" +
				",a.[Operator] operator,a.[OperateTime] operateTime,[OperateRsn] operateRsn,[OldFileRecordID] oldFileRecordID" +
				" from(" + hql+")" +
				" as a" +
				"  left join [CoresArchives].[dbo].[TblFileRecord] record " +
				"  on record.fileRecordSn = a.fileRecordSn and record.archiveCode=a.archiveCode" +
				"  left join [CoresArchives].[dbo].[TblFileContent_GlpSynthesis] glp" +
				"  on glp.fileRecordID = record.fileRecordID" +
				"  where archiveTypeFlag=4 and glp.baseGlpFlag=:baseGlpFlag" ;//是综合;
			}
		}
		Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		if(archiveTypeFlag!=null&&!"".equals(archiveTypeFlag)&&archiveTypeFlag!=0)//0是全部
		{
			if(archiveTypeFlag==4||archiveTypeFlag==11)
			{
				hql+= " order by a.operateTime desc ";
			}else{
				
				hql+= " order by operateTime desc ";
			}
		}
		Query query = getSession().createSQLQuery(hql);
		
		if(archiveTypeFlag!=null&&!"".equals(archiveTypeFlag)&&archiveTypeFlag!=0)//0是全部
		{
			if(archiveTypeFlag==4||archiveTypeFlag==11)
			{
				//4和11的情况下面处理
				if(archiveTypeFlag==4){
					query.setInteger("baseGlpFlag",1);
					query2.setInteger("baseGlpFlag",1);
				}else {
					query.setInteger("baseGlpFlag",2);
					query2.setInteger("baseGlpFlag",2);
				}
			}else{
				query.setInteger("archiveTypeFlag", archiveTypeFlag);
				query2.setInteger("archiveTypeFlag", archiveTypeFlag);
			}
		}
		if(logStartDate!=null&&!"".equals(logStartDate)){
			query.setString("start",DateUtil.dateToString(logStartDate, "yyyy-MM-dd"));
			query2.setString("start",DateUtil.dateToString(logStartDate, "yyyy-MM-dd"));
		}
		if(logEndDate!=null&&!"".equals(logEndDate)){
			query.setString("end",DateUtil.dateToString(logEndDate, "yyyy-MM-dd"));
			query2.setString("end",DateUtil.dateToString(logEndDate, "yyyy-MM-dd"));
		}
		if(searchString!=null&&!"".equals(searchString))
		{
			query.setString("searchString", "%"+searchString+"%");
			query2.setString("searchString", "%"+searchString+"%");
		}
		
		Integer total = (Integer)query2.uniqueResult();
		List<Map<String, Object>> list = query.setFirstResult((page-1)*rows)
								.setMaxResults(rows)
								.setResultTransformer(new MapResultTransformer())
								.list();
		for(Map<String, Object> log:list)
		{
			log.put("operateTime",DateUtil.dateToString((Date)log.get("operateTime"),"yyyy-MM-dd"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", list);
		return map;
	}
	
}
