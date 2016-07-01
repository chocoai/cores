package com.lanen.service.schdeule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.model.schedule.TblTaskTypeField;
@Service
public class TblTaskTypeServiceImpl extends BaseDaoImpl<TblTaskType> implements TblTaskTypeService  {

	@SuppressWarnings("unchecked")
	public List<TblTaskType> getAll(List<Integer> taskTypeID) {
		List<TblTaskType> list=getSession().createQuery("FROM TblTaskType  d  where d.taskKind in ( :taskKind )").setParameterList("taskKind", taskTypeID).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByTaskName(String taskName) {
		List<TblTaskType> list=getSession().createQuery("FROM TblTaskType  d WHERE  d.taskName = ? ")//
		.setParameter(0, taskName)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "null" })
	public List<Integer> gettaskKind(List<Integer> taskTypeID) {
		List<Integer> list= getSession().createSQLQuery("select distinct taskKind  FROM TblTaskType t where taskKind in ( :taskKind )")
		.setParameterList("taskKind", taskTypeID).list();
		if( null == list&&list.size()<0 ){
			return null;
		}else{
			return list;
		}
		
	}


	@SuppressWarnings("unchecked")
	public List<TblTaskType> getTaskTypeName(int taskTypeID) {
		List<TblTaskType> list= getSession().createQuery("FROM TblTaskType  d WHERE  d.taskKind = ? ")//
		.setParameter(0, taskTypeID)
		.list();
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
		}
	
	}

	public void delectAllTask(List<String> list) {
		for(String obj:list){
			delete(obj);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTaskTypeID() {
		List<String> list= getSession().createSQLQuery("select id  FROM tblTaskType t ").list();
		if( null == list && list.size()>0 ){
			return null;
		}else{
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TblTaskType> getByTypeName(String name) {
		List<TblTaskType> list= getSession().createQuery("FROM TblTaskType  d WHERE  d.taskName = ? ")//
		.setParameter(0, name)
		.list();
		if(null != list && list.size() > 0){
			return list;
		}else{
			return null;
		}
		
	}

	public void updateTaskType(List<TblTaskType> list) {
      for(TblTaskType obj:list){
    	  update(obj);
      }	
	}

	public void saveAllTaskType(TblTaskType taskType,
			List<TblTaskTypeField> fidlist) {
		//保存taskType
		getSession().save(taskType);
		for(TblTaskTypeField field:fidlist){
			field.setId(getKey("TblTaskTypeField"));
			getSession().save(field);
		}		
	}

	public void updateTaskTypeAndTblTaskTypeFields(TblTaskType taskType,List<TblTaskTypeField> oldfidlist,
			List<TblTaskTypeField> fidlist) {
		//跟新taskType
		getSession().update(taskType);
		for(TblTaskTypeField fild:oldfidlist){
			getSession().delete(fild);
		}
		for(TblTaskTypeField field:fidlist){
			field.setId(getKey("TblTaskTypeField"));
			getSession().save(field);
		}
	}
	@SuppressWarnings("unchecked")
	public List<TblTaskType> getTblTaskTypeListhaveV() {
        String hql="FROM TblTaskType  order by taskKind ";
		List<TblTaskType> list = getSession().createQuery(hql).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblTaskType> getTblTaskTypeList() {
		String hql="FROM TblTaskType where validFlag is null or validFlag != '1' order by taskKind ";
		
		List<TblTaskType> list = getSession().createQuery(hql).list();
		return list;
	}



}
