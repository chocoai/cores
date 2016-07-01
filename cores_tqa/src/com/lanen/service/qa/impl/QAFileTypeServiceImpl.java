package com.lanen.service.qa.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAFileType;
import com.lanen.service.qa.QAFileTypeService;
@Service
public class QAFileTypeServiceImpl extends BaseDaoImpl<QAFileType> implements QAFileTypeService {


     @SuppressWarnings("unchecked")
	public boolean isExistByFileTypeAndNameAndParent(Integer fileType,
			String fileTypeName, String parentId) {
    	 String hql = "from QAFileType where fileType=:fileType and fileTypeName=:fileTypeName ";
    	 if(parentId!=null&&!"".equals(parentId))
    		 hql+=" and parentFileTypeId=:parentFileTypeId";
    	 
    	 Query query = getSession().createQuery(hql)
    	 							.setInteger("fileType", fileType)
									.setString("fileTypeName", fileTypeName);
    	 if(parentId!=null&&!"".equals(parentId))
    		 query.setString("parentFileTypeId", parentId);
		List<QAFileType> list = query.list();
		
		if(list!=null&&list.size()>0)
			return true;
		
		return false;
	}
     @SuppressWarnings("unchecked")
	public List<QAFileType> getListByFileType(Integer fileType) {
		 String hql = "from QAFileType where fileType=:fileType ";
    	 Query query = getSession().createQuery(hql)
    	 							.setInteger("fileType", fileType);
		List<QAFileType> list = query.list();
		
		return list;
	}
     @SuppressWarnings("unchecked")
	public List<QAFileType> getListByParentId(String parentFileTypeId)
     {
    	 String hql = "from QAFileType where parentFileTypeId=:parentFileTypeId ";
    	 Query query = getSession().createQuery(hql)
    	 						.setString("parentFileTypeId", parentFileTypeId);
		List<QAFileType> list = query.list();
		
		return list;
     }
    
	

}
