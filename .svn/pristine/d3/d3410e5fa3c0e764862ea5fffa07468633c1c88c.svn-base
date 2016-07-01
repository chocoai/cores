package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.contract.TblTestItemAttachment;
@Service
public class TblTestItemAttachmentServiceImpl extends BaseDaoImpl<TblTestItemAttachment> implements  TblTestItemAttachmentService {

	@SuppressWarnings("unchecked")
	public List<TblTestItemAttachment> getByTestItemCode(String testItemCode) {
		List<Map<String,Object>> getlist = getSession().createSQLQuery("select  id,testItemCode,attachmentName,remark,appendDate,operator " +
				" from CoresSchedule.dbo.tblTestItemAttachment where testItemCode = ? ")
		.setParameter(0, testItemCode).setResultTransformer(new MapResultTransformer())
		.list();
		//return list;
		List<TblTestItemAttachment> list = new ArrayList<TblTestItemAttachment>();
		for (Map<String,Object> map : getlist) {
			TblTestItemAttachment attachment = new TblTestItemAttachment();
			attachment.setId((String)map.get("id"));
			attachment.setTestItemCode((String)map.get("testItemCode"));
			attachment.setAttachmentName((String)map.get("attachmentName"));
			attachment.setRemark((String)map.get("remark"));
			attachment.setAppendDate((Date)map.get("appendDate"));
			attachment.setOperator((String)map.get("operator"));
			list.add(attachment);
		}
		return list;
	}

	public void saveList(List<TblTestItemAttachment> list) {
		for(TblTestItemAttachment obj : list){
			getSession().save(obj);
		}
		
	}

}
