package com.lanen.service.qa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.qa.QAFileReg;
import com.lanen.service.qa.QAFileRegService;
@Service
public class QAFileRegServiceImpl extends BaseDaoImpl<QAFileReg> implements QAFileRegService {
	@SuppressWarnings("unchecked")
	public List<QAFileReg> getByType(String fileTypeId) {
		List<Map<String, Object>> list = getSession().createSQLQuery("SELECT [fileRegID],[fileType] ,[fileTypeID] ,[fileTypeName] ,[fileCode] ,[fileName] ,[fileVersion],[filePublishTime],[filePublishDepartment] ,[remark],[isVersionUpdate] FROM [CoresQA].[dbo].[QAFileReg] where fileTypeID=?")
											.setParameter(0, fileTypeId)
											.setResultTransformer(new MapResultTransformer())
											.list();
		List<QAFileReg> list2 = new ArrayList<QAFileReg>();
		for(Map<String, Object> map:list)
		{
			QAFileReg reg = new QAFileReg();
			reg.setFileRegId((String)(map.get("fileRegID")));
			reg.setFileType((Integer)map.get("fileType"));
			reg.setFileTypeName((String)map.get("fileTypeName"));
			reg.setFileCode((String)map.get("fileCode"));
			reg.setFileName((String)map.get("fileName"));
			reg.setFileVersion((String)map.get("fileVersion"));
			reg.setFilePublishTime((Date)map.get("filePublishTime"));
			reg.setFilePublishDepartment((String)map.get("filePublishDepartment"));
			reg.setRemark((String)map.get("remark"));
			reg.setIsVersionUpdate((Integer)map.get("isVersionUpdate"));
			//[fileTypeID] id的怎么处理？
			
			
			list2.add(reg);
		}
		return list2;
	}

	

}
