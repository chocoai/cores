package com.lanen.service.qa.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.model.User;
import com.lanen.model.qa.QALearnTask;
import com.lanen.service.qa.QALearnTaskService;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
@Service
public class QALearnTaskServiceImpl extends BaseDaoImpl<QALearnTask> implements QALearnTaskService{

	@SuppressWarnings("unchecked")
	public boolean isFinishedByTask(QALearnTask qALearnTask) {
		String hql = "SELECT task.[learnTaskID],[purpose],[student],task.[createTime],task.[learnState]" +
				"  FROM [CoresQA].[dbo].[QALearnTask] task" +
				"  left join [CoresQA].[dbo].[QAFileRegReader] reader" +
				"  on reader.learnTaskID = task.learnTaskID" +
				"  where reader.learnState!=2" +
				"  and task.learnTaskID=:learnTaskID ";
		Query query = getSession().createSQLQuery(hql)
   	 							.setString("learnTaskID", qALearnTask.getLearnTaskId());
		List<Object[]> list = query.list();
		if(list!=null&&list.size()>0)//如果存在没有学习完成的
			return false;
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<QALearnTask> getAll(Integer learnState) {
		String sql = "from QALearnTask  ";
		if(learnState!=null&&learnState!=10)
		{
			sql+="where learnState=:learnState ";
		}
		sql += " order by createTime desc";
		Query query = getSession().createQuery(sql);
		if(learnState!=null&&learnState!=10)
		{
			query.setInteger("learnState", learnState);
		}
		List<QALearnTask> tasks = query.list();
		return tasks;
	}
	@SuppressWarnings("unchecked")
	public List<QALearnTask> getByExceptState(Integer learnState,Integer exceptState)
	{
		String sql = "from QALearnTask where  1=1 ";
		if(learnState!=null&&learnState!=10)
		{
			sql+=" and learnState=:learnState ";
		}
		if(exceptState!=null&&exceptState==0){
			sql+="and (learnState is not null and learnState!=0) ";
		}
		sql += " order by createTime desc";
		Query query = getSession().createQuery(sql);
		if(learnState!=null&&learnState!=10)
		{
			query.setInteger("learnState", learnState);
		}
		List<QALearnTask> tasks = query.list();
		return tasks;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistTaskByFile(String fileRegId) {
		String hql = "SELECT [learnTaskFileID],task.[learnTaskID],[fileRegID]" +
					"  FROM [CoresQA].[dbo].[QALearnTaskFile] taskFile" +
					" 	 left join [CoresQA].[dbo].[QALearnTask] task" +
					"    on taskFile.learnTaskID = task.learnTaskID"+
					"  where fileRegID=:fileRegId and (task.learnState is null or task.learnState!=2) ";
		Query query = getSession().createSQLQuery(hql)
								.setString("fileRegId", fileRegId);
		List<Object[]> list = query.list();
		if(list!=null&&list.size()>0)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistTaskByFileType(String FileTypeId) {
		String hql = "SELECT [learnTaskFileID],task.[learnTaskID],taskfile.[fileRegID],taskfile.[fileTypeID]" +
				"  FROM [CoresQA].[dbo].[QALearnTaskFile] taskfile" +
				"  left join [CoresQA].[dbo].[QALearnTask] task on task.learnTaskID=taskfile.learnTaskID" +
				"  where taskfile.[fileTypeID]=:FileTypeId and (task.learnState is null or task.learnState!=2)";
		Query query = getSession().createSQLQuery(hql)
									.setString("FileTypeId", FileTypeId);
		List<Object[]> list = query.list();
		if(list!=null&&list.size()>0)//
			return true;
		
		return false;
	}
	
	public List<Map<String,Object>> isExistAttByTask(String learnTaskId)
	{
		String hql ="select attCount.num,reg.fileName from ( " +
					    "  select count(fileAtt.attachmentID) num,taskFile.fileRegID from" +
						"  (	" +
						"		SELECT  [learnTaskFileID],[learnTaskID] ,[fileRegID] FROM [CoresQA].[dbo].[QALearnTaskFile] " +
						"		where [learnTaskID]=:learnTaskId " +
						"  ) taskFile" +
						"  left join [CoresQA].[dbo].[QAFileAttachment] fileAtt" +
						"  on taskFile.fileRegID = fileAtt.fileRegID" +
						"  group by taskFile.[learnTaskID],taskFile.[fileRegID]"+
					")  attCount" +
					" left join [CoresQA].[dbo].[QAFileReg] reg" +
					" on reg.fileRegID = attCount.fileRegID";
		
		Query query = getSession().createSQLQuery(hql)
								.setString("learnTaskId", learnTaskId)
								.setResultTransformer(new MapResultTransformer());
		List<Map<String,Object>> num = query.list();
		
		
		return num;
	}
	
	public boolean isExistReaderByTask(String learnTaskId)
	{
		String hql ="SELECT count( [learnRecordID]) num  FROM [CoresQA].[dbo].[QAFileRegReader]" +
				"  where learnTaskID=:learnTaskId " +
				"  group by [learnTaskID]";
		Query query = getSession().createSQLQuery(hql)
								.setString("learnTaskId", learnTaskId);
		Integer num = (Integer)query.uniqueResult();
		
		if(num!=null&&num>0)
		return true;
		
		return false;
		
	}
	
	
}
