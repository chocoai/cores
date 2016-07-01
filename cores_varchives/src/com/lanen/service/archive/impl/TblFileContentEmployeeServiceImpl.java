package com.lanen.service.archive.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.TblFileContentEmployee;
import com.lanen.service.archive.TblFileContentEmployeeService;
import com.lanen.util.DateUtil;
@Service
public class TblFileContentEmployeeServiceImpl extends BaseDaoImpl<TblFileContentEmployee> implements
		TblFileContentEmployeeService {

	public Map<String, Object> getByCondition(String staffDept,Integer staffState,Date fileStartDate,
			Date fileEndDate, Date keepEndDate, Integer isDestory,
			Integer isValid, String searchString,Integer page,Integer rows) {
		String hql = " SELECT study.[fileRecordID] fileRecordId,study.[archiveCode],[staffName],[staffCode],[staffDept],[staffState]" +
				",archiveTypeCode,archiveTitle,storePosition,fileDate,fileRecordSn,archiveMaker,fileOperator, keepDate,remark,destoryDate,validationFlag,archiveMediaFlag,archiveMedia,archiveMediaEleCode" +
				"  FROM [CoresArchives].[dbo].[TblFileContent_Employee] study" +
				"   left join [CoresArchives].[dbo].[TblFileRecord] tblFileRecord   on tblFileRecord.fileRecordID = study.fileRecordID" +
				"  left join [CoresArchives].[dbo].[TblFileIndex] fileIndex on fileIndex.archiveCode=study.archiveCode" +
				"  where (tblFileRecord.delFlag is null or tblFileRecord.delFlag=0) ";
		
		if(staffDept!=null&&!"".equals(staffDept)&&!"全部".equals(staffDept))
		{
			hql += " and staffDept=:staffDept ";
		}
		if(staffState!=null&&!"".equals(staffState)&&staffState!=0)
		{
			hql += " and staffState=:staffState ";
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
			hql += " and ( tblFileRecord.keepDate <=:keepEndDate)";
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
			hql += " and (study.archiveCode like :searchString or staffName like :searchString or staffCode like :searchString or tblFileRecord.keyWord like :searchString" +
					" or fileIndex.archiveTitle like :searchString or staffDept like :searchString)";
		}
		
		Query query2 = getSession().createSQLQuery("select count(*) from ("+hql+") as a");
		hql+=" order by study.archiveCode desc,tblFileRecord.fileRecordSn";
		Query query = getSession().createSQLQuery(hql);
		
		if(staffDept!=null&&!"".equals(staffDept)&&!"全部".equals(staffDept))
		{
			query.setString("staffDept",staffDept);
			query2.setString("staffDept",staffDept);
		}
		if(staffState!=null&&!"".equals(staffState)&&staffState!=0)
		{
			query.setInteger("staffState",staffState);
			query2.setInteger("staffState",staffState);
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
	
		Integer total = query.list().size();
		List<Map<String,Object>> list = query.setFirstResult((page-1)*rows)
												.setMaxResults(rows)
												.setResultTransformer(new MapResultTransformer())
												.list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", list);
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<String> getExistByCodeList(List<String> empCodeList)
	{
		String sql = "SELECT  [staffCode] FROM [CoresArchives].[dbo].[TblFileContent_Employee]" +
				"  where staffCode in (:codes)";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameterList("codes", empCodeList)
										.list();
		return list;
		
	}

}
