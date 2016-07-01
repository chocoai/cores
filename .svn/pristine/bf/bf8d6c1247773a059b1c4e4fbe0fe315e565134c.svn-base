package com.lanen.base;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("unchecked")
@Transactional//事物注解可以被子类继承
public class BaseDaoImpl<T> implements BaseDao<T>{
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;//这是个重要问题！
	
	public BaseDaoImpl(){
		//通过反射得到T的真实类型
		ParameterizedType pt =(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz =(Class)pt.getActualTypeArguments()[0];
	}
		

	public void save(T entity) {
		
	    getSession().save(entity);
	
	}

	public void delete(String id) {

		Object obj =getSession().get(clazz, id);
		getSession().delete(obj);
	}
	public void update(T entity) {
		getSession().update(entity);
		
	}


	public T getById(String id) {
		if(id==null||id.length()<1){
			return null;
		}
		return (T)getSession().get(clazz, id);
	}


	public List<T> getByIds(String[] ids) {
		if(ids==null ||ids.length==0){
			return null;
		}
		
		return getSession().createQuery(
				"FROM "+ clazz.getSimpleName()+" WHERE id IN(:ids) ")
				.setParameterList("ids", ids)
				.list();
	}
	public List<T> findAll() {
		return getSession().createQuery(
		"From "+clazz.getSimpleName()+" ORDER BY id ASC ")		
		.list();
	}
	protected Session getSession(){
		//指定数据源
//		DataSourceContextHanlder.setDataSourceType("dataSource_studyplan");
		
		Session session = sessionFactory.getCurrentSession();
		
//		DataSourceContextHanlder.clearDataSourceType();
		return session;
		
	}

	public boolean isExistById(String id) {
		Object obj =getSession().get(clazz, id);
		return obj!=null ? true :false;
	}

}
