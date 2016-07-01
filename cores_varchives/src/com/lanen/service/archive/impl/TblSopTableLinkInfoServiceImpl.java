package com.lanen.service.archive.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.cfg.SetSimpleValueTypeSecondPass;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblSopTableLinkInfo;
import com.lanen.service.archive.TblSopTableLinkInfoService;

@Service
public class TblSopTableLinkInfoServiceImpl extends
		BaseDaoImpl<TblSopTableLinkInfo> implements TblSopTableLinkInfoService {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListBySOPCodeAndSOPVer(String code,String ver)
	{
	
		//String hql = "from TblSopTableLinkInfo where sopver=:sopver and sopcode=:sopcode";
		String sql = "select [ID] id,sop.[SOPVer] sopver,sop.[SOPCode] sopcode,[TblVer],[TblCode]," +
					" sop.archiveCode,sop.fileRecordID fileRecordId,[SOPEffectiveDate] sopeffectiveDate,SOPInvalidDate sopinvalidDate, " +
					" case when SOPInvalidDate is not null then 'true' else 'false' end as isInvalid," +
					" SOPName sopname,SOPTypeCode soptypeCode,SOPTypeName soptypeName" +
					",SOPFlag sopflag,atype.archiveTypeCode,archiveTypeName,archiveTitle,storePosition," +
					" fileDate,fileRecordSn,archiveMaker,fileOperator" +
					",keepDate,remark,destoryDate,validationFlag,archiveMediaFlag,archiveMedia,delFlag,1 operate" +
				" from" +
				" (SELECT [ID] ,[SOPVer] sopver,[SOPCode],[TblVer],[TblCode]" +
				"  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo] " +
				"  where SOPCode=:sopcode and SOPVer=:sopver ) as rel" +
				"  left join [CoresArchives].[dbo].[TblFileContent_SOP] sop on sop.SOPCode=rel.[TblCode] and sop.SOPVer=rel.[TblVer] " +
				"  left join  [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID = sop.fileRecordID" +
				"  left join  [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode = record.archiveCode" +
				"  left join [CoresSystemSet].[dbo].[DictArchiveType] atype on atype.archiveTypeCode = fileIndex.archiveTypeCode" +
				" order by [SOPEffectiveDate] desc";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
													.setParameter("sopcode", code)
													.setParameter("sopver",ver)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListByTblCodeAndTblVer(String code,String ver)
	{
		String sql = "select rel.[ID] id,sop.[SOPVer] sopver,sop.[SOPCode] sopcode,[TblVer],[TblCode]," +
					" sop.archiveCode,sop.fileRecordID fileRecordId,[SOPEffectiveDate] sopeffectiveDate,SOPInvalidDate sopinvalidDate, " +
					" case when SOPInvalidDate is not null then 'true' else 'false' end as isInvalid," +
					" SOPName sopname,SOPTypeCode soptypeCode,SOPTypeName soptypeName" +
					",SOPFlag sopflag,atype.archiveTypeCode,archiveTypeName,archiveTitle,storePosition," +
					" fileDate,fileRecordSn,archiveMaker,fileOperator" +
					",keepDate,remark,destoryDate,validationFlag,archiveMediaFlag,archiveMedia,delFlag" +
				" from" +
				" (SELECT [ID] ,[SOPVer] sopver,[SOPCode],[TblVer],[TblCode]" +
				"  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo] " +
				" where [TblCode]=:sopcode and [TblVer]=:sopver) as rel "+
				"  left join [CoresArchives].[dbo].[TblFileContent_SOP] sop on sop.SOPCode=rel.SOPCode and sop.SOPVer=rel.SOPVer " +
				"  left join  [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID = sop.fileRecordID" +
				"  left join  [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode = record.archiveCode" +
				"  left join [CoresSystemSet].[dbo].[DictArchiveType] atype on atype.archiveTypeCode = fileIndex.archiveTypeCode" +
				" order by [SOPEffectiveDate] desc";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
										.setParameter("sopcode", code)
										.setParameter("sopver",ver)
										.setResultTransformer(new MapResultTransformer())
										.list();
		return list;
		 
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEffectLinkBySOP(String sopcode)
	{
		String sql = "SELECT [ID],link.[SOPVer] ,link.[SOPCode],[TblVer] ,[TblCode]" +
				" from" +
				"(" +
				"	SELECT [ID],[SOPVer] ,[SOPCode],[TblVer] ,[TblCode]" +
				"	  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo]" +
				"	  where SOPCode=:sopcode " +
				"	  and SOPVer = (select max(cast([SOPVer] as int))  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo]" +
				"	  where SOPCode=:sopcode )" +
				" ) as link" +
				"  left join [CoresArchives].[dbo].[TblFileContent_SOP] sop on sop.SOPCode=link.TblCode and sop.SOPVer=link.TblVer" +
				"  left join [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID = sop.fileRecordID" +
				"  where sop.SOPEffectiveDate<GETDATE() and (SOPInvalidDate is null or SOPInvalidDate>GETDATE() )" +
				"  and (record.delFlag is null or record.delFlag=0) " +
				"  and (record.destoryDate is null or record.destoryRegSign is null )";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
												.setParameter("sopcode", sopcode)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return list;
		
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getEffectLinkByTbl(String tblCode)
	{
		
		String sql = "SELECT [ID],link.[SOPVer] ,link.[SOPCode],[TblVer] ,[TblCode]" +
					" from" +
					"(" +
					"	SELECT [ID],[SOPVer] ,[SOPCode],[TblVer] ,[TblCode]" +
					"	  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo]" +
					"	  where TblCode=:sopcode " +
					"	  and TblVer = (select max(cast([TblVer] as int))  FROM [CoresArchives].[dbo].[TblSopTableLinkInfo]" +
					"	  where TblCode=:sopcode )" +
					" ) as link" +
					"  left join [CoresArchives].[dbo].[TblFileContent_SOP] sop on sop.SOPCode=link.SOPCode and sop.SOPVer=link.SOPVer" +
					"  left join [CoresArchives].[dbo].[TblFileRecord] record on record.fileRecordID = sop.fileRecordID" +
					"  where sop.SOPEffectiveDate<GETDATE() and (SOPInvalidDate is null or SOPInvalidDate>GETDATE() )" +
					"  and (record.delFlag is null or record.delFlag=0) " +
					"  and (record.destoryDate is null or record.destoryRegSign is null )";
		List<Map<String, Object>> list = getSession().createSQLQuery(sql)
												.setParameter("sopcode", tblCode)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblSopTableLinkInfo> getBySOPCodeAndSOPVer(String code,String ver)
	{
		String hql = "from TblSopTableLinkInfo where sopcode=:sopcode and sopver=:sopver";
		List<TblSopTableLinkInfo> list = getSession().createQuery(hql)
													.setString("sopcode", code)
													.setString("sopver", ver)
													.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblSopTableLinkInfo> getByTblCodeAndTblVer(String code,String ver)
	{
		String hql = "from TblSopTableLinkInfo where tblCode=:tblCode and tblVer=:tblVer";
		List<TblSopTableLinkInfo> list = getSession().createQuery(hql)
													.setString("tblCode", code)
													.setString("tblVer", ver)
													.list();
		return list;
	}

}
