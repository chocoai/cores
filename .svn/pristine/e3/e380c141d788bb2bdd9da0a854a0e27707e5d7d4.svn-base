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
	public List<QAFileType> getListByFileType(Integer fileType) {
		 String hql = " select [fileTypeID] ,[fileType] ,[fileTypeName] ,[parentFileTypeID] from [CoresQA].[dbo].[QAFileType] where fileType=:fileType ";
    	 Query query = getSession().createSQLQuery(hql)
    	 							.setParameter("fileType", fileType);
		List<QAFileType> list = query.list();
		
		return list;
	}

	

}
