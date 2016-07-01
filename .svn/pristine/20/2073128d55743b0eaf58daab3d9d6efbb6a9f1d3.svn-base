package com.lanen.service.qa.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.qa.QAFileRegReader;
import com.lanen.model.qa.QALearnTask;
import com.lanen.service.qa.QAFileRegReaderService;
@Service
public class QAFileRegReaderImpl extends BaseDaoImpl<QAFileRegReader> implements QAFileRegReaderService{
	@SuppressWarnings("unchecked")
	public QAFileRegReader getByFileRegIdAndUser(User user, String fileRegId) {
		String sql = "SELECT [learnRecordID],reader.[learnTaskID]" +
				",[readerName],[readerCode],reader.[learnState],reader.[createTime]" +
				",[planFinishTime],[finishTime],[remark]" +
				" FROM [CoresQA].[dbo].[QAFileRegReader] reader" +
				"  left join [CoresQA].[dbo].[QALearnTask] task " +
				"  on reader.learnTaskID = task.learnTaskID" +
				"  left join [CoresQA].[dbo].[QALearnTaskFile] files" +
				"  on files.learnTaskID=task.learnTaskID" +
				"  where files.fileRegID=:fileRegId and reader.readerCode=:readerCode" +
				" and reader.readerName=:readerName";
		Query query = getSession().createSQLQuery(sql)
   	 							.setParameter("fileRegId", fileRegId)
   	 							.setParameter("readerCode", user.getUserName())
   	 							.setParameter("readerName", user.getRealName())
   	 							.setResultTransformer(new MapResultTransformer());
	
		List<Map<String, Object>> list = query.list();
		
		if(list!=null&&list.size()>0)
		{
			QAFileRegReader reader = new QAFileRegReader();
			Map<String, Object> map = list.get(0);
			reader.setCreateTime((Date)map.get("createTime"));
			reader.setFinishTime((Date)map.get("finishTime"));
			reader.setLearnRecordId((String)map.get("learnRecordID"));
			reader.setLearnState((Integer)map.get("learnState"));
			reader.setPlanFinishTime((Date)map.get("planFinishTime"));
			QALearnTask task = new QALearnTask();
			task.setLearnTaskId((String)map.get("learnTaskID"));
			reader.setQalearnTask(task);
			reader.setReaderCode((String)map.get("readerCode"));
			reader.setReaderName((String)map.get("readerName"));
			reader.setRemark((String)map.get("remark"));
			
			return reader;
		}else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<QAFileRegReader> getByFileRegId(String fileRegId) {
		String hql = "from QAFileRegReader where  ";
		getSession().createQuery(hql).list();
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<QAFileRegReader> getByTask(String learnTaskId) {
		String hql = "from QAFileRegReader where  qalearnTask.learnTaskId=:learnTaskId";
		List<QAFileRegReader> readers  = getSession().createQuery(hql)
													.setString("learnTaskId", learnTaskId)
													.list();
		return readers;
	}
	@SuppressWarnings("unchecked")
	public boolean isExistByTaskAndReader(String learnTaskId, String readerName) {
		String hql = "from QAFileRegReader where  qalearnTask.learnTaskId=:learnTaskId and readerName=:readerName";
		List<QAFileRegReader> readers  = getSession().createQuery(hql)
													.setString("learnTaskId", learnTaskId)
													.setString("readerName", readerName)
													.list();
		if(readers!=null&&readers.size()>0)
			return true;
		else {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List<QAFileRegReader> getByUser(User user,Integer learnState) {
		String hql = "from QAFileRegReader reader where  readerName=:readerName and readerCode=:readerCode and reader.learnState!=0 and reader.learnState!=-1 " ;
		if(learnState!=null&&learnState!=10)
		{
			hql += " and reader.learnState=:learnState ";
		}
		
		hql += " order by qalearnTask.createTime desc ";
		Query query = getSession().createQuery(hql)
								.setString("readerCode", user.getUserName())
								.setString("readerName", user.getRealName());
		if(learnState!=null&&learnState!=10)
		{
			query.setInteger("learnState", learnState);
		}
		List<QAFileRegReader> readers  = query.list();
		
		return readers;
	}
	@SuppressWarnings("unchecked")
	public QAFileRegReader getbyTaskAndUser(String learnTaskId, User user) {
		String hql = "from QAFileRegReader where qalearnTask.learnTaskId=:learnTaskId and readerName=:readerName and readerCode=:readerCode" +
					 " order by qalearnTask.createTime desc ";
		List<QAFileRegReader> readers  = getSession().createQuery(hql)
													.setString("learnTaskId", learnTaskId)
													.setString("readerCode", user.getUserName())
													.setString("readerName", user.getRealName())
													.list();
		if(readers!=null&&readers.size()>0)
		{
			return readers.get(0);
		}else {
			return null;
		}
	}

}
