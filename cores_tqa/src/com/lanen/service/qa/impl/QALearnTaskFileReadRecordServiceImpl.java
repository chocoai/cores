package com.lanen.service.qa.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QALearnTaskFileReadRecord;
import com.lanen.service.qa.QALearnTaskFileReadRecordService;
@Service
public class QALearnTaskFileReadRecordServiceImpl extends BaseDaoImpl<QALearnTaskFileReadRecord>
		implements QALearnTaskFileReadRecordService {

	@SuppressWarnings("unchecked")
	public boolean isExistByLearnRecFileAndAttach(String learnRecordId,String fileRegId,String attachmentId)
	{
		String sql = "from QALearnTaskFileReadRecord where qafileRegReader.learnRecordId=:learnRecordId and fileRegId=:fileRegId and attachmentId=:attachmentId";
		List<QALearnTaskFileReadRecord> list = getSession().createQuery(sql)
															.setString("learnRecordId", learnRecordId)
															.setString("fileRegId", fileRegId)
															.setString("attachmentId", attachmentId)
															.list();
		if(list!=null&&list.size()>0)
		{
			return true;
		}else{
			return false;
		}
			
	}
	@SuppressWarnings("unchecked")
	public QALearnTaskFileReadRecord getByLearnRecFileAndAttach(String learnRecordId,String fileRegId,String attachmentId)
	{
		String sql = "from QALearnTaskFileReadRecord where qafileRegReader.learnRecordId=:learnRecordId and fileRegId=:fileRegId and attachmentId=:attachmentId";
		List<QALearnTaskFileReadRecord> list = getSession().createQuery(sql)
															.setString("learnRecordId", learnRecordId)
															.setString("fileRegId", fileRegId)
															.setString("attachmentId", attachmentId)
															.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else{
			return null;
		}
			
	}
	
	@SuppressWarnings("unchecked")
	public List<QALearnTaskFileReadRecord> getByLearnRecFile(String learnRecordId,String fileRegId)
	{
		String sql = "from QALearnTaskFileReadRecord where qafileRegReader.learnRecordId=:learnRecordId and fileRegId=:fileRegId ";
		List<QALearnTaskFileReadRecord> list = getSession().createQuery(sql)
															.setString("learnRecordId", learnRecordId)
															.setString("fileRegId", fileRegId)
															.list();
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer getAllTaskAttachFinishNum(String learnRecordID)
	{
		
		String sql = "SELECT count([fileReadRecordID]) FROM [CoresQA].[dbo].[QALearnTaskFileReadRecord] " +
					" where learnRecordID=:learnRecordID and readEndTime is not null ";
		List<Integer> list = getSession().createSQLQuery(sql)
										.setString("learnRecordID", learnRecordID)		
										.list();
		
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else{
			return 0;
		}
			
	}
	@SuppressWarnings("unchecked")
	public Integer getAllTaskAttachNum(String learnTaskId)
	{
		
		String sql = "SELECT count(att.attachmentID)  FROM [CoresQA].[dbo].[QALearnTaskFile] taskFile" +
				"	  left join [CoresQA].[dbo].[QAFileAttachment] att	on taskFile.fileRegID = att.fileRegID" +
				"		where learnTaskID=:learnTaskId";
		List<Integer> list = getSession().createSQLQuery(sql)
										.setString("learnTaskId", learnTaskId)		
										.list();
		
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else{
			return 0;
		}
			
	}
	@SuppressWarnings("unchecked")
	public List<QALearnTaskFileReadRecord> getByTask(String learnTaskId)
	{
		String sql = "from QALearnTaskFileReadRecord where qafileRegReader.qalearnTask.learnTaskId=:learnTaskId";
		List<QALearnTaskFileReadRecord> list = getSession().createQuery(sql)
															.setString("learnTaskId", learnTaskId)
															.list();
		
		return list;
	}
	
	

	

}
