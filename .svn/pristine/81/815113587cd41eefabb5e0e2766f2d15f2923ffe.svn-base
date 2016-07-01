package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblContractAttachment;

@Service
public class TblContractAttachmentServiceImpl extends BaseDaoImpl<TblContractAttachment> implements TblContractAttachmentService {

	public List<Map<String, Object>> getMapListByContractCode(
			String contractCode) {
		List<Map<String,Object>> mapList = null;
		if(null != contractCode && !"".equals(contractCode)){
			String sql = "select attachment.id,attachment.attachmentName,attachment.state"+
						" from CoresContract.dbo.tblContractAttachment attachment"+
						" where attachment.contractCode = ? ";
			List<?> list = getSession().createSQLQuery(sql)
										.setParameter(0, contractCode)
										.list();
			if(null != list && list.size()>0){
				mapList = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = null;
				for(Object obj :list ){
					map = new HashMap<String, Object>();
					Object[] objs = (Object[]) obj;
					map.put("id", (String)objs[0]);
					map.put("attachmentName", (String)objs[1]);
					map.put("state", (Integer)objs[2]);
					mapList.add(map);
				}
			}
		}
		return mapList;
	}

}
