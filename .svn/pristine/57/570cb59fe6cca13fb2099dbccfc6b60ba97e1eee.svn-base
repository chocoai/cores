package com.lanen.base.studyitem;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.model.clinicaltest.PoolDataId;


@SuppressWarnings("unchecked")
@Transactional//事物注解可以被子类继承
public class StudyItemBaseDaoImpl<T> implements StudyItemBaseDao<T>{
	@Resource(name="sessionFactory_studyitem")
	private SessionFactory studyItemessionFactory;
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;//这是个重要问题！
	
	public StudyItemBaseDaoImpl(){
		//通过反射得到T的真实类型
		ParameterizedType pt =(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz =(Class)pt.getActualTypeArguments()[0];
	}
		
	
	public void save(T entity) {
		
		getStudyItemSession().save(entity);
	
	}

	public void delete(String id) {

		Object obj =getStudyItemSession().get(clazz, id);
		getStudyItemSession().delete(obj);
	}
	public void update(T entity) {
		getStudyItemSession().update(entity);
		
	}


	public T getById(String id) {
		if(id==null||id.length()<1){
			return null;
		}
		return (T)getStudyItemSession().get(clazz, id);
	}


	public List<T> getByIds(String[] ids) {
		if(ids==null ||ids.length==0){
			return null;
		}
		
		return getStudyItemSession().createQuery(
				"FROM "+ clazz.getSimpleName()+" WHERE id IN(:ids) ")
				.setParameterList("ids", ids)
				.list();
	}
	public List<T> findAll() {
		return getStudyItemSession().createQuery(
		"From "+clazz.getSimpleName()+" ORDER BY id ASC ")		
		.list();
	}
	public boolean isExistById(String id) {
		Object obj =getStudyItemSession().get(clazz, id);
		return obj!=null ? true :false;
	}
	
	protected Session getSession(){
		
		Session session = sessionFactory.getCurrentSession();
		return session;
		
	}
	protected Session getStudyItemSession(){
		
		Session session = studyItemessionFactory.getCurrentSession();
		
		return session;
		
	}



	public String getKey(String entityName) {
		String oldValue="";
		String newValue="";
		PoolDataId getId=(PoolDataId) getSession().get(PoolDataId.class, entityName);
		
		if(null!=getId){
			oldValue=getId.getCurrentValue();
			newValue=getNewValue(oldValue);
			getId.setCurrentValue(newValue);
			getSession().update(getId);
			
		}else{
			getId=new PoolDataId();
			getId.setTableName(entityName);
			newValue="2000000001";
			getId.setCurrentValue(newValue);
			getSession().save(getId);
		}
		return newValue;
	}
	/**
	 * 获得下一个值
	 * @param oldValue
	 * @return
	 */
	private String getNewValue(String oldValue) {
		String data=1+oldValue;
		Long newValue=Long.valueOf(data)+1;
		String newValueString=newValue.toString().substring(1);
		return newValueString;
	}


	public String getKey() {
		String oldValue="";
		String newValue="";
		PoolDataId getId=(PoolDataId) getSession().get(PoolDataId.class, clazz.getSimpleName());
		
		if(null!=getId){
			oldValue=getId.getCurrentValue();
			newValue=getNewValue(oldValue);
			getId.setCurrentValue(newValue);
			getSession().update(getId);
			
		}else{
			getId=new PoolDataId();
			getId.setTableName(clazz.getSimpleName());
			newValue="2000000001";
			getId.setCurrentValue(newValue);
			getSession().save(getId);
		}
		return newValue;
	}

}
