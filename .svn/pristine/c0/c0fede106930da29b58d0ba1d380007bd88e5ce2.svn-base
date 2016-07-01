package com.lanen.service.archive.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblFileContentQacheck;
import com.lanen.model.TblFileContentSop;
import com.lanen.model.TblFileIndex;
import com.lanen.model.TblFileRecord;
import com.lanen.service.archive.TblFileContentSopService;
import com.lanen.util.DateUtil;
@Service
public class TblFileContentSopServiceImpl extends BaseDaoImpl<TblFileContentSop> implements
		TblFileContentSopService {
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getByCondition(Integer fileFlag,Integer isAll,Integer sopflag,Integer isNowValid,Integer isInvalid,Integer needChange,Date changeEndDate,
			Integer yearNum,Integer yearNumUnit,Date fileStartDate,
			Date fileEndDate, Date keepEndDate, Integer isDestory,
			Integer isValid, String searchString,Integer page,Integer rows) {
		//获取最高版本的sop
		
		/*
		}*/
		String hql = "  select ( case when highest.SOPVer is not null then 1 else 0 end) as isHigh, [fileRecordID] ,sopRecord.[archiveCode],[SOPTypeCode] ,[SOPTypeName]  ,sopRecord.[SOPCode],[SOPName],sopRecord.[SOPVer] ,[SOPEffectiveDate],[SOPInvalidDate]," +
				" 	[archiveTypeCode] ,[archiveTypeFlag],[storePosition],[archiveTitle],[validationFlag] ," +
				" 	[fileRecordSn] ,[archiveMaker],[fileOperator],[fileDate],[fileDateType],[keepDate],[destoryDate]," +
				" 	[destoryRegSign] ,[archiveMediaFlag],[archiveMedia],[remark] ,[keyWord],[delFlag] ,[delTime],[SOPFlag],SOPFile,SOPFileName" +
				" from" +
				" (" +
				"   select sop.[fileRecordID] ,sop.[archiveCode],[SOPTypeCode] ,[SOPTypeName]  ,[SOPCode],[SOPName],sop.[SOPVer] ,[SOPEffectiveDate],[SOPInvalidDate]," +
				"	 fileIndex.[archiveTypeCode] ,fileIndex.[archiveTypeFlag],[storePosition],[archiveTitle],[validationFlag] ," +
				"	 record.[fileRecordSn] ,record.[archiveMaker],record.[fileOperator],record.[fileDate],[fileDateType],record.[keepDate],record.[destoryDate]," +
				"	 record.[destoryRegSign] ,record.[archiveMediaFlag],record.[archiveMedia],[remark] ,[keyWord],[delFlag] ,[delTime],[SOPFlag],SOPFile,SOPFileName" +
				"	 from [CoresArchives].[dbo].[TblFileContent_SOP] sop" +
				"	 left join [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID=sop.fileRecordID" +
				"	 left join [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode = record.archiveCode" ;
				

		String hql2 = " )  sopRecord" +
				" left join (SELECT sop.[SOPCode] ,max(cast([SOPVer] as int)) SOPVer FROM [CoresArchives].[dbo].[TblFileContent_SOP] sop" +
				"			 left join [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID=sop.fileRecordID" +
				"			 left join [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode = record.archiveCode " ;
		
		String hql3 = "	 group by sop.SOPCode ) as highest" +
				"   on highest.[SOPCode] = sopRecord.[SOPCode] and highest.SOPVer = sopRecord.SOPVer" ;
		
		
		//isNowValid仅现行sop
		//isInvalid显示已作废sop
		//needChange需修订的sop
		String cond = " where (delFlag is null or delFlag=0) ";
		//fileFlag
		if(fileFlag!=null&&!"".equals(fileFlag))
		{
			if(fileFlag==0){
				cond += " and record.fileFlag=0 ";
			}else if(fileFlag==-1){
				cond += " and record.fileFlag=-1 ";
			}
		}
		if(isNowValid!=null&&isNowValid==1)//仅显示现行的
		{
			cond += " and (sopeffectiveDate<GETDATE() ) ";//当前有效的
		}
		
		
		if(isInvalid!=null&&isInvalid==1){//isInvalid显示已作废sop
			
		}else{
			cond+=" and (sopinvalidDate is null or sopinvalidDate>GETDATE()) ";//没有作废
		}
		if(needChange!=null&&needChange==1){//显示需要修订的，只显示最高版本的并且没有作废的
			
			if(changeEndDate!=null&&!"".equals(changeEndDate))
			{	//修订期限
				if(yearNumUnit!=null)
				{
					if(yearNumUnit==1)//年
					{
						cond += " and dateadd(yyyy,:yearNum,sopeffectiveDate)<=:changeEndDate ";
					}else if(yearNumUnit==2)//月
					{
						cond += " and dateadd(mm,:yearNum,sopeffectiveDate)<=:changeEndDate ";
					}else if(yearNumUnit==3)//日
					{
						cond += " and dateadd(dd,:yearNum,sopeffectiveDate)<=:changeEndDate ";
					}
				}
			}
		}
		
	
	if(sopflag==null||sopflag==0)
	{
		cond += " and (sop.SOPFlag is null or sop.SOPFlag=0) ";
	}else if(sopflag==1)
	{
		cond += " and (sop.SOPFlag=1) ";
	}
	
	if(fileStartDate!=null&&!"".equals(fileStartDate))
	{
		cond += " and record.fileDate >=:fileStartDate";
	}
	if(fileEndDate!=null&&!"".equals(fileEndDate))
	{
		cond += " and record.fileDate <=:fileEndDate";
	}
	if(keepEndDate!=null&&!"".equals(keepEndDate))
	{
		cond += " and ( record.keepDate <=:keepEndDate)";
	}
	if(isDestory!=null&&isDestory==1)
	{
		//hql += " and tblFileRecord.destoryDate is not null and tblFileRecord.destoryRegSign is not null ";
	}else {
		cond += " and (record.destoryDate is null or record.destoryRegSign is null )";
	}
	if(isValid!=null&&isValid==1)
	{
		//hql += " and tblFileRecord.tblFileIndex.validationFlag=1";//包含是所有的
	}else {
		cond += " and (fileIndex.validationFlag is null or fileIndex.validationFlag=0)";
	}
	if(searchString!=null)
	{
		cond += " and (fileIndex.archiveCode like :searchString or [SOPTypeCode] like :searchString or [SOPTypeName] like :searchString" +
				" or [SOPCode] like :searchString or [SOPName] like :searchString  or record.keyWord like :searchString" +
				"	or fileIndex.archiveTitle like :searchString)";
	}
	
	hql = hql+cond+hql2+cond+hql3 +" where 1=1 ";
	
	if(isNowValid!=null&&isNowValid==1)//仅显示现行的
	{
		//只显示最高版本的SOP
		hql += " and (highest.SOPVer is not null ";
	}
	
	if(isAll!=null&&isAll==1)//全部版本的
	{
		if(isNowValid!=null&&isNowValid==1)
		{
			hql += " or highest.SOPVer is null) ";
		}
	}else{
		if(isNowValid!=null&&isNowValid==1)
		{
			hql += ")";
		}else{
			hql += " and (highest.SOPVer is not null )";
		}
	}
	//
	Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		hql+=" order by  sopRecord.[SOPCode] desc,cast(sopRecord.[SOPVer] as int) desc,sopRecord.archiveCode desc,sopRecord.fileRecordSn ";
		Query query = getSession().createSQLQuery(hql);
		
			
		if(needChange!=null&&needChange==1){//显示需要修订的，只显示最高版本的并且没有作废的
			
			
			if(needChange!=null&&needChange==1){//显示需要修订的，只显示最高版本的并且没有作废的
				
				if(changeEndDate!=null&&!"".equals(changeEndDate))
				{
					if(yearNumUnit!=null)
					{
						query.setParameter("changeEndDate", DateUtil.dateToString(changeEndDate,"yyyy-MM-dd"));
						query.setParameter("yearNum", yearNum);
						query2.setParameter("changeEndDate", DateUtil.dateToString(changeEndDate,"yyyy-MM-dd"));
						query2.setParameter("yearNum", yearNum);
					}
				}
				
			
			}
		}
		
		
		if(fileStartDate!=null&&!"".equals(fileStartDate))
		{
			query.setParameter("fileStartDate", DateUtil.dateToString(fileStartDate,"yyyy-MM-dd"));
			query2.setParameter("fileStartDate", DateUtil.dateToString(fileStartDate,"yyyy-MM-dd"));
		}
		if(fileEndDate!=null&&!"".equals(fileEndDate))
		{
			query.setParameter("fileEndDate", DateUtil.dateToString(fileEndDate,"yyyy-MM-dd"));
			query2.setParameter("fileEndDate", DateUtil.dateToString(fileEndDate,"yyyy-MM-dd"));
		}
		if(keepEndDate!=null&&!"".equals(keepEndDate))
		{
			query.setParameter("keepEndDate", DateUtil.dateToString(keepEndDate,"yyyy-MM-dd"));
			query2.setParameter("keepEndDate", DateUtil.dateToString(keepEndDate,"yyyy-MM-dd"));
		}
		
		if(searchString!=null)
		{
			query.setParameter("searchString", "%"+searchString+"%");
			query2.setParameter("searchString", "%"+searchString+"%");
		}
		
		Integer total = (Integer)query2.uniqueResult();
		List<Map<String, Object>> list = query.setResultTransformer(new MapResultTransformer())
											.setFirstResult((page-1)*rows)
											.setMaxResults(rows)
											.list();
		List<TblFileContentSop> sopList = new ArrayList<TblFileContentSop>();
		for(Map<String, Object> map:list)
		{
			TblFileIndex fileIndex = new TblFileIndex();
			fileIndex.setArchiveCode((String)map.get("archiveCode"));
			fileIndex.setArchiveTitle((String)map.get("archiveTitle"));
			fileIndex.setArchiveTypeCode((String)map.get("archiveTypeCode"));
			fileIndex.setArchiveTypeFlag((Integer)map.get("archiveTypeFlag"));
			fileIndex.setOperateTime((Date)map.get("operatorTime"));
			fileIndex.setOperator((String)map.get("operator"));
			fileIndex.setStorePosition((String)map.get("storePosition"));
			fileIndex.setValidationFlag((Integer)map.get("validationFlag"));
			//" fileIndex.[archiveCode] ,fileIndex.[archiveTypeCode] ,fileIndex.[archiveTypeFlag],[storePosition],[archiveTitle],[validationFlag] ,fileIndex.[operateTime] ,fileIndex.[operator]," +
			///" record.[fileRecordID],record.[archiveCode] ,record.[fileRecordSn] ,record.[archiveMaker],
			//record.[fileOperator],record.[fileDate],record.[keepDate],record.[destoryDate]," +
			//" record.[destoryRegSign] ,record.[archiveMediaFlag],record.[archiveMedia],record.[operateTime] 
			//,record.[operator],[remark] ,[keyWord],[delFlag] ,[delTime]" +
			TblFileRecord record = new TblFileRecord();
			record.setArchiveMaker((String)map.get("archiveMaker"));
			record.setArchiveMedia((String)map.get("archiveMedia"));
			record.setArchiveMediaFlag((Integer)map.get("archiveMediaFlag"));
			Integer isHigh = (Integer)map.get("isHigh");
			record.setDelFlag(isHigh);
			record.setDelTime((Date)map.get("delTime"));
			record.setDestoryDate((Date)map.get("destoryDate"));
			record.setDestoryRegSign((String)map.get("destoryRegSign"));
			record.setFileDate((Date)map.get("fileDate"));
			record.setFileDateType((Integer)map.get("fileDateType"));
			record.setFileOperator((String)map.get("fileOperator"));
			record.setFileRecordId((String)map.get("fileRecordID"));
			record.setFileRecordSn((Integer)map.get("fileRecordSn"));
			record.setKeepDate((Date)map.get("keepDate"));
			record.setKeyWord((String)map.get("keyWord"));
			record.setOperateTime((Date)map.get("operateTime"));
			record.setOperator((String)map.get("operator"));
			record.setRemark((String)map.get("remark"));
			record.setTblFileIndex(fileIndex);
			
			
			TblFileContentSop sop = new TblFileContentSop();
			//sop.[fileRecordID] ,sop.[archiveCode],[SOPTypeCode] ,[SOPTypeName]  ,[SOPCode],[SOPName],sop.[SOPVer] ,[SOPEffectiveDate],[SOPInvalidDate]," +
			sop.setArchiveCode((String)map.get("archiveCode"));
			sop.setFileRecordId((String)map.get("fileRecordID"));
			sop.setSopcode((String)map.get("SOPCode"));
			sop.setSopeffectiveDate((Date)map.get("SOPEffectiveDate"));
			sop.setSopinvalidDate((Date)map.get("SOPInvalidDate"));
			sop.setSopname((String)map.get("SOPName"));
			sop.setSoptypeCode((String)map.get("SOPTypeCode"));
			sop.setSoptypeName((String)map.get("SOPTypeName"));
			sop.setSopver((String)map.get("SOPVer"));
			sop.setSopflag((Integer)map.get("SOPFlag"));
			if(map.get("SOPFile")!=null&&!"".equals(map.get("SOPFile")))
			{
				sop.setSopfile("22222".getBytes());
			}else {
				sop.setSopfile(null);
			}
			sop.setSopfileName((String)map.get("SOPFileName"));
			sop.setTblFileRecord(record);
			
			
			sopList.add(sop);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", sopList);
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TblFileContentSop> getBySopCode(String sopcode)
	{
		// TODO Auto-generated method stub
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
				" and sopcode=:sopcode order by cast(sopver as int) desc";
		
		List<TblFileContentSop> sops = getSession().createQuery(hql).
											setString("sopcode", sopcode).list();
		
		
		return sops;
	}
	@SuppressWarnings("unchecked")
	public TblFileContentSop getMaxVerByCode(String sopcode) {
		// TODO Auto-generated method stub 
		//fileFlag;//0归档，-1没归档
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
				" and sopcode=:sopcode order by cast(sopver as int) desc";
		
		List<TblFileContentSop> sops = getSession().createQuery(hql).setString("sopcode", sopcode).list();
		if(sops!=null&&sops.size()>0)
			return sops.get(0);
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<TblFileContentSop> getNotArchiveListByCode(String sopcode)
	{
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
		" and sopcode=:sopcode and (tblFileRecord.fileFlag=-1)" +//还没归档的
		" order by cast(sopver as int) desc";

		List<TblFileContentSop> sops = getSession().createQuery(hql)
													.setString("sopcode", sopcode)
													.list();
		
		return sops;
	}
	
	@SuppressWarnings("unchecked")
	public TblFileContentSop getArchiveMaxVerByCode(String sopcode) {
		// TODO Auto-generated method stub 
		//fileFlag;//0归档，-1没归档
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
				" and sopcode=:sopcode and (tblFileRecord.fileFlag is null or tblFileRecord.fileFlag=0) " +//已经归档的
				" order by cast(sopver as int) desc";
		
		List<TblFileContentSop> sops = getSession().createQuery(hql).setString("sopcode", sopcode).list();
		if(sops!=null&&sops.size()>0)
			return sops.get(0);
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistCodeInSop(String sopcode,Integer sopflag)
	{
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
					" and sopcode=:sopcode ";
		if(sopflag==null||sopflag==0)//0是sop 1是表格
		{
			hql+="and (sopflag is null or sopflag=0) ";
		}else if(sopflag==1){
			hql+="and (sopflag=1) ";
		}
		hql+=" order by cast(sopver as int) desc";
		List<TblFileContentSop> sops = getSession().createQuery(hql)
												.setString("sopcode", sopcode)
												.list();
		if(sops!=null&&sops.size()>0)
			return true;
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistCodeAndVer(String sopcode,String sopver)
	{
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
					" and sopcode=:sopcode and sopver=:sopver";
		
		List<TblFileContentSop> sops = getSession().createQuery(hql)
											.setString("sopcode", sopcode)
											.setString("sopver", sopver)
											.list();
		if(sops!=null&&sops.size()>0)
			return true;
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistCodeAndVerInArchive(String sopcode,String sopver)
	{
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
					" and sopcode=:sopcode and sopver=:sopver and (tblFileRecord.fileFlag is null or tblFileRecord.fileFlag=0)";//已经归档

		List<TblFileContentSop> sops = getSession().createQuery(hql)
										.setString("sopcode", sopcode)
										.setString("sopver", sopver)
										.list();
		if(sops!=null&&sops.size()>0)
		return true;
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistCodeAndVerExceptOne(String sopcode,String sopver,String fileRecordId)
	{
		String hql = " from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
					" and sopcode=:sopcode and sopver=:sopver and fileRecordId!=:fileRecordId";
		
		List<TblFileContentSop> sops = getSession().createQuery(hql)
											.setString("sopcode", sopcode)
											.setString("sopver", sopver)
											.setString("fileRecordId", fileRecordId)
											.list();
		if(sops!=null&&sops.size()>0)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblFileContentSop> getAllvalidListByFlag(Integer sopflag)
	{
		String hql = "from TblFileContentSop where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) " +
				" and (tblFileRecord.destoryDate is null or tblFileRecord.destoryRegSign is null )" +
				" and sopeffectiveDate<GETDATE() and (sopinvalidDate is null or sopinvalidDate>GETDATE() ) ";
		if(sopflag==null||sopflag==0)
		{
			hql += " and (sopflag is null or sopflag=0) ";
		}else if(sopflag==1)
		{
			hql += " and (sopflag=1) ";
		}
		
		List<TblFileContentSop> list = getSession().createQuery(hql)
													.list();
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<String> getExistBySopCodeAndVerList(List<String> sopCodeAndSopVerList)
	{
		String sql = "SELECT [SOPCode] ,[SOPVer]  FROM [CoresArchives].[dbo].[TblFileContent_SOP] ";
		if(sopCodeAndSopVerList!=null&&sopCodeAndSopVerList.size()>0)
		{
			sql += " where (";
			
			for(String str:sopCodeAndSopVerList)
			{
				if(str.contains("-"))
				{
					sql+=" (SOPCode='"+str.substring(0, str.lastIndexOf("-"))+"' and SOPVer="+Integer.valueOf(str.substring(str.lastIndexOf("-")+1))+") or";
				}
			}
			sql = sql.substring(0,sql.length()-3 );//去掉最后一个or
			sql += ")";
			
		}
		List<Object> list = getSession().createSQLQuery(sql).list();
		List<String> strList = new ArrayList<String>();
		
		for(Object obj:list)
		{
			Object[] objs = (Object[])obj;
			if(objs[1]!=null&&!"".equals(objs[1]))
			{
				if(Integer.parseInt((String)objs[1])>0&&Integer.parseInt((String)objs[1])<10)
				{
					objs[1]="0"+objs[1];
				}
			}
			strList.add(objs[0]+"-"+objs[1]);
		}
		
		return strList;
	}
	
}
