package com.lanen.service.schdeule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblTaskTypeField;

@Service
public class TblTaskTypeFieldServiceImpl extends BaseDaoImpl<TblTaskTypeField> implements TblTaskTypeFieldService{

	@SuppressWarnings("unchecked")
	public List<TblTaskTypeField> getByTaskTypeFieldId(String taskTypeField) {
		List<TblTaskTypeField> list=getSession().createQuery("FROM TblTaskTypeField  d WHERE  d.tttId = ? ")//
		.setParameter(0, taskTypeField)
		.list();
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
		}
	
	}

	@SuppressWarnings("unchecked")
	public List<TblTaskTypeField> getByTaskKind2(int taskKind) {
		List<TblTaskTypeField> list=getSession().createQuery("FROM TblTaskTypeField  d WHERE  d.taskKind2 = ? ")//
		.setParameter(0, taskKind)
		.list();
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
		}
	}

}
