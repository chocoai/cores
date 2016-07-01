package com.lanen.service.contract;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblPaymentRecord;
@Service
public class TblPaymentReocrdServiceImpl extends BaseDaoImpl<TblPaymentRecord> implements TblPaymentRecordService {

	public List<TblPaymentRecord> getPaymentRecordListByContractCode(String contractCode) {
		String sql="SELECT [id],[contractCode],[paymentDate],[amount],[receiptFlag],[operator],[operateTime],[priceUnit]"+
                    " FROM [CoresContract].[dbo].[tblPaymentRecord] where contractCode=:contractCode";
		List<?> listSql=getSession().createSQLQuery(sql).setParameter("contractCode", contractCode).list();
		List<TblPaymentRecord> list=new ArrayList<TblPaymentRecord>();
		if(null!=listSql){
			for(Object obj:listSql){
				Object[] objs = (Object[]) obj;
				TblPaymentRecord t=new TblPaymentRecord();
				t.setId((String)objs[0]);
				t.setContractCode((String)objs[1]);
				t.setPaymentDate((Date)objs[2]);
				t.setAmount((String)objs[3]);
				t.setReceiptFlag((Integer)objs[4]);
				t.setOperator((String)objs[5]);
				t.setOperateTime((Date)objs[6]);
				t.setPriceUnit((Integer)objs[7]);
				list.add(t);
			}
		}
		return list;
	}

	
}
