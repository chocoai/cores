package com.lanen.service.qa.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.User;
import com.lanen.model.qa.QALearnTaskFile;
import com.lanen.service.qa.QALearnTaskFileService;

import freemarker.core.ReturnInstruction.Return;
@Service
public class QALearnTaskFileServiceImpl extends BaseDaoImpl<QALearnTaskFile> implements QALearnTaskFileService{
	@SuppressWarnings("unchecked")
	public List<QALearnTaskFile> getByFileRegId(String fileRegId) {
		String hql = "from QALearnTaskFile where fileRegId=:fileRegId ";
		Query query = getSession().createQuery(hql)
   	 							.setString("fileRegId", fileRegId);
		List<QALearnTaskFile> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<QALearnTaskFile> getFileListByTaskId(String learnTaskId) {
		String hql = "from QALearnTaskFile where qalearnTask.learnTaskId=:learnTaskId ";
		Query query = getSession().createQuery(hql)
   	 							.setString("learnTaskId", learnTaskId);
		List<QALearnTaskFile> list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistByTaskAndFile(String learnTaskId, String fileRegId) {
		String hql = "from QALearnTaskFile where qalearnTask.learnTaskId=:learnTaskId and fileRegId=:fileRegId";
		Query query = getSession().createQuery(hql)
   	 							.setString("learnTaskId", learnTaskId)
   	 							.setString("fileRegId", fileRegId);
		List<QALearnTaskFile> list = query.list();
		if(list!=null&&list.size()>0)
			return true;
		else {
			return false;
		}
	}
	
	

}
