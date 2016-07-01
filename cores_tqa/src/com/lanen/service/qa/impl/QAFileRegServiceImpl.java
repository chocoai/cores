package com.lanen.service.qa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAFileReg;
import com.lanen.service.qa.QAFileRegService;

@Service
public class QAFileRegServiceImpl extends BaseDaoImpl<QAFileReg> implements QAFileRegService {
	@SuppressWarnings("unchecked")
	public List<QAFileReg> getByType(String fileTypeId) {
		
		Query query = getSession().createQuery("from QAFileReg reg where reg.qafileType.fileTypeId=?")
											.setString(0, fileTypeId);
		List<QAFileReg> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public boolean isExist(QAFileReg model,String fileTypeId)
	{  
		Query query = getSession().createQuery("from QAFileReg where qafileType.fileTypeId=:fileTypeId" +
				" and fileCode=:fileCode  and fileName=:fileName and fileVersion=:fileVersion " +
				" and filePublishTime=:filePublishTime" +
				" and filePublishDepartment=:filePublishDepartment and remark=:remark" +
				" and isVersionUpdate=:isVersionUpdate")
									.setString("fileTypeId", fileTypeId)
									.setString("fileCode", model.getFileCode())
									.setString("fileName", model.getFileName())
									.setString("fileVersion", model.getFileVersion())
									.setDate("filePublishTime", model.getFilePublishTime())
									.setString("filePublishDepartment", model.getFilePublishDepartment())
									.setString("remark", model.getRemark());
		if(model.getIsVersionUpdate()==null||model.getIsVersionUpdate()==0)
		{
			query.setInteger("isVersionUpdate", 0);
		}else if(model.getIsVersionUpdate()==1)
		{
			query.setInteger("isVersionUpdate", 1);
		}
			List<QAFileReg> list = query.list();
		if(list!=null&&list.size()>0)
			return true;
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<QAFileReg> getByCondition(Integer fileType,
			String fileStateCondition, String fileSearchCondition) {
		if(fileSearchCondition==null)
			fileSearchCondition="";
		
		String hql= "SELECT distinct reg.[fileRegID],[fileType],[fileTypeID] ,[fileTypeName],[fileCode] ,[fileName] ,[fileVersion]" +
				",[filePublishTime],[filePublishDepartment],[remark],[isVersionUpdate]" +
				"  FROM [CoresQA].[dbo].[QAFileReg] reg " +
				"  left join [CoresQA].[dbo].[QALearnTaskFile] taskfile on taskfile.fileRegID=reg.fileRegID" +
				"  left join [CoresQA].[dbo].[QALearnTask] task on task.learnTaskID=taskfile.learnTaskID" +
				"  where  fileName like :fileSearchCondition ";
		/*
		  <option value="1">全部</option>
		    <option value="2">尚未安排学习的文件</option>
		    <option value="3">尚未完成学习的文件</option>
		    <option value="4">全部学习完成的文件</option>
		    <option value="5">尚未完成SOP修订的文件</option>
		*/
		//1的为全部，不用加条件
		if("2".equals(fileStateCondition))
		{
			//hql+=" and task.learnState=0 ";
			hql = "SELECT distinct reg.[fileRegID],[fileType],[fileTypeID] ,[fileTypeName],[fileCode] ,[fileName] ,[fileVersion]" +
			",[filePublishTime],[filePublishDepartment],[remark],[isVersionUpdate]" +
			"  FROM [CoresQA].[dbo].[QAFileReg] reg " +
			" where reg.fileRegID not in (select distinct fileRegID from [CoresQA].[dbo].[QALearnTaskFile])" +
			" and fileName like :fileSearchCondition " ;
		}else if("3".equals(fileStateCondition))
		{
			hql+=" and task.learnState=1 ";
		}else if("4".equals(fileStateCondition))
		{
			hql+=" and task.learnState=2 ";
		}
		/*if("5".equals(fileStateCondition))//不了解sop完成是什么。
		{
			hql+=" and task.learnState=2 ";
		}*/
		if (fileType!=null&&0!=fileType) {//全部类型			
			hql+=" and reg.fileType=:fileType";
		}
		
		Query query = getSession().createSQLQuery(hql)
								.setParameter("fileSearchCondition", "%"+fileSearchCondition+"%");
		if (fileType!=null&&0!=fileType) {//全部类型
			query.setParameter("fileType", fileType);
		}
		query.setResultTransformer(new MapResultTransformer());
		List<Map<String, Object>> list = query.list();
		//reg.[fileRegID],[fileType],[fileTypeID] ,[fileTypeName],[fileCode] ,[fileName] ,[fileVersion]" +
		//",[filePublishTime],[filePublishDepartment],[remark],[isVersionUpdate]
		List<QAFileReg> resultList=new ArrayList<QAFileReg>();
		for(Map<String, Object> map:list)
		{
			QAFileReg fileReg = new QAFileReg();
			fileReg.setFileRegId((String)map.get("fileRegID"));
			fileReg.setFileType((Integer)map.get("fileType"));
			fileReg.setFileTypeName((String)map.get("fileTypeName"));
			fileReg.setFileCode((String)map.get("fileCode"));
			fileReg.setFileName((String)map.get("fileName"));
			fileReg.setFileVersion((String)map.get("fileVersion"));
			fileReg.setFilePublishTime((Date)map.get("filePublishTime"));
			fileReg.setFilePublishDepartment((String)map.get("filePublishDepartment"));
			fileReg.setRemark((String)map.get("remark"));
			fileReg.setIsVersionUpdate((Integer)map.get("isVersionUpdate"));
			
			resultList.add(fileReg);
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<QAFileReg> getByConditionAndUser(Integer fileType,String fileStateCondition, String fileSearchCondition,User user) {
		if(fileSearchCondition==null)
			fileSearchCondition="";
		
		String hql= "SELECT distinct reg.[fileRegID],reg.[fileType],reg.[fileTypeID] ,reg.[fileTypeName],reg.[fileCode] ,reg.[fileName] ,reg.[fileVersion]" +
				",reg.[filePublishTime],reg.[filePublishDepartment],reg.[remark],reg.[isVersionUpdate]" +
				"  FROM  [CoresQA].[dbo].[QAFileReg] reg " +
				"  left join [CoresQA].[dbo].[QALearnTaskFile] taskfile on taskfile.fileRegID=reg.fileRegID" +
				"  left join [CoresQA].[dbo].[QALearnTask] task on task.learnTaskID=taskfile.learnTaskID" ;
		if(!"1".equals(fileStateCondition))
		{
			hql+="  join [CoresQA].[dbo].[QAFileRegReader] as reader on reader.learnTaskID=taskfile.learnTaskID ";
		}
		hql+= "  where  reg.fileName like :fileSearchCondition ";
		/*
		  <option value="1">全部</option>
		    <option value="2">尚未安排学习的文件</option>
		    <option value="3">尚未完成学习的文件</option>
		    <option value="4">全部学习完成的文件</option>
		    <option value="5">尚未完成SOP修订的文件</option>
		*/
		//1的为全部，不用加条件
		if("2".equals(fileStateCondition))
		{
			//hql+=" and task.learnState=0 ";
			hql = "SELECT distinct reg.[fileRegID],reg.[fileType],reg.[fileTypeID] ,reg.[fileTypeName],reg.[fileCode] ,reg.[fileName] ,reg.[fileVersion]" +
			",reg.[filePublishTime],reg.[filePublishDepartment],reg.[remark],reg.[isVersionUpdate]" +
			"  FROM [CoresQA].[dbo].[QAFileReg] reg " +
			" where reg.fileRegID not in (select distinct fileRegID from [CoresQA].[dbo].[QALearnTaskFile] as taskFile" ;
			if(user!=null&&!"".equals(user))
			{
				hql += " join [CoresQA].[dbo].[QALearnTask] as task on taskFile.learnTaskID=task.learnTaskID" +
					   " join [CoresQA].[dbo].[QAFileRegReader] as reader on reader.learnTaskID=task.learnTaskID " +
					   " where reader.readerCode=:userCode " ;
			}
			hql += ") and reg.fileName like :fileSearchCondition " ;
		}else if("3".equals(fileStateCondition))
		{
			hql+=" and task.learnState=1 ";
			if(user!=null&&!"".equals(user))
			{
				hql += " and reader.finishTime is null and readerCode=:userCode";
			}
			
		}else if("4".equals(fileStateCondition))
		{
			if(user!=null&&!"".equals(user))
			{
				hql += " and (reader.finishTime is not null and readerCode=:userCode)";
			}else {
				hql+=" and task.learnState=2 ";
				
			}
		}
		/*if("5".equals(fileStateCondition))//不了解sop完成是什么。
		{
			hql+=" and task.learnState=2 ";
		}*/
		if (fileType!=null&&0!=fileType) {//全部类型			
			hql+=" and reg.fileType=:fileType";
		}
		
		Query query = getSession().createSQLQuery(hql)
								.setParameter("fileSearchCondition", "%"+fileSearchCondition+"%");
		if (fileType!=null&&0!=fileType) {//全部类型
			query.setParameter("fileType", fileType);
		}
		if(user!=null&&!"".equals(user))
		{
			query.setParameter("userCode", user.getUserCode());
		}
		query.setResultTransformer(new MapResultTransformer());
		List<Map<String, Object>> list = query.list();
		//reg.[fileRegID],[fileType],[fileTypeID] ,[fileTypeName],[fileCode] ,[fileName] ,[fileVersion]" +
		//",[filePublishTime],[filePublishDepartment],[remark],[isVersionUpdate]
		List<QAFileReg> resultList=new ArrayList<QAFileReg>();
		for(Map<String, Object> map:list)
		{
			QAFileReg fileReg = new QAFileReg();
			fileReg.setFileRegId((String)map.get("fileRegID"));
			fileReg.setFileType((Integer)map.get("fileType"));
			fileReg.setFileTypeName((String)map.get("fileTypeName"));
			fileReg.setFileCode((String)map.get("fileCode"));
			fileReg.setFileName((String)map.get("fileName"));
			fileReg.setFileVersion((String)map.get("fileVersion"));
			fileReg.setFilePublishTime((Date)map.get("filePublishTime"));
			fileReg.setFilePublishDepartment((String)map.get("filePublishDepartment"));
			fileReg.setRemark((String)map.get("remark"));
			fileReg.setIsVersionUpdate((Integer)map.get("isVersionUpdate"));
			
			resultList.add(fileReg);
		}
		return resultList;
	}
	

}
