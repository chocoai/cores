package com.lanen.service.clinicaltest;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.TblTrace;

@Service
public class TblTraceServiceImpl extends BaseDaoImpl<TblTrace> implements TblTraceService{

	@Override
	public void save(TblTrace entity) {
		if(null == entity.getModifyTime()){
			entity.setModifyTime(new Date());
		}
		entity.setId(getKey("TblTrace"));
		super.save(entity);
	}

//	public List getListByStudyNoReqNoTestItem(String studyNo,
//			int reqNo, int testItem) {
//		StringBuffer sb = new StringBuffer(" select data.studyNo,data.reqNo,data.testItem,data.testIndex,trace.oldValue,trace.newValue,trace.modifyTime,trace.modifyReason,trace.host " +
//				"From TblClinicalTestData as data,TblTrace as trace where data.dataId = trace.dataId and trace.tableName ='TblClinicalTestData' and data.studyNo =:studyNo ");
//		if(reqNo!=0){
//			sb.append(" and data.reqNo = :reqNo ");
//		}
//		if(testItem!=0){
//			sb.append(" and data.testItem = :testItem ");
//		}
//		sb.append(" order by trace.modifyTime ");
//		Query query = getSession().createQuery(sb.toString());
//		query.setParameter("studyNo", studyNo);
//		if(reqNo!=0){
//			query.setParameter("reqNo", reqNo+"");
//		}
//		if(testItem!=0){
//			query.setParameter("testItem", testItem);
//		}
//		List list = query.list();
//		return list;
//	}

	@SuppressWarnings("unchecked")
	public List<TblTrace> getListByTableName(String tableName) {
		List<TblTrace> list = getSession().createQuery("From TblTrace where tableName = ? ")
		.setParameter(0, tableName)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblTrace> getTblClinicalTestDataTraceListByStudyNoReqNoTestItem(
			String studyNo, int reqNo, int testItem) {
		StringBuffer sb = new StringBuffer(
		"From TblTrace as t where t.tableName ='TblClinicalTestData' and t.newValue like ? order by t.modifyTime ");
		String value = studyNo+",";
		if(reqNo!=0){
			value=value+reqNo+",";
		}else{
			value=value+"%,";
		}
		if(testItem!=0){
			value=value+testItem+",";
		}else{
			value=value+"_,";
		}
		value =value+"%";
		System.out.println(value);
		Query query = getSession().createQuery(sb.toString());
		query.setParameter(0, value);
		List<TblTrace> list = query.list();
		return list;
	}

	public void saveList(List<TblTrace> tblTraceList) {
		if(null != tblTraceList && tblTraceList.size()>0){
			for(TblTrace tblTrace : tblTraceList){
				this.save(tblTrace);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TblTrace> getListByTableNameAndDataId(String tableName,
			String sessionId) {
		List<TblTrace> list=getSession().createQuery("From TblTrace where tableName =?" +
				" and dataId = ? order by modifyTime")
		.setParameter(0, tableName)
		.setParameter(1, sessionId)
		.list();
		return  list;
	}
	 
}
