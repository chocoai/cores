package com.lanen.service.qa.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.qa.QAFileAttachment;
import com.lanen.service.qa.QAFileAttachmentService;
@Service
public class QAFileAttachmentServiceImpl extends BaseDaoImpl<QAFileAttachment> implements QAFileAttachmentService{

	@SuppressWarnings("unchecked")
	public List<QAFileAttachment> getByFileRegId(String FileRegId) {
		List<QAFileAttachment> list = getSession().createQuery("from QAFileAttachment where qafileReg.fileRegId=?")
													.setString(0,FileRegId)
													.list();
	
		return list;
	}

}
