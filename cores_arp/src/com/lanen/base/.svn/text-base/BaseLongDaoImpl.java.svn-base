package com.lanen.base;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("unchecked")
@Transactional//事物注解可以被子类继承
public class BaseLongDaoImpl<T> implements BaseLongDao<T>{
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;//这是个重要问题！
	
	public BaseLongDaoImpl(){
		//通过反射得到T的真实类型
		ParameterizedType pt =(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz =(Class)pt.getActualTypeArguments()[0];
		
	}
		
	
	public void save(T entity) {
		getSession().save(entity);
	}

	public void delete(Long id) {

		Object obj =getSession().get(clazz, id);
		getSession().delete(obj);
	}
	public void delete(Integer id) {

		Object obj =getSession().get(clazz, id);
		getSession().delete(obj);
	}
	public void update(T entity) {
		getSession().update(entity);
		
	}


	public T getById(Long id) {
		if(id==null){
			return null;
		}
		return (T)getSession().get(clazz, id);
	}


	public List<T> getByIds(Long[] ids) {
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
		"From "+clazz.getSimpleName())		
		.list();
	}
	protected Session getSession(){
		//指定数据源
//		DataSourceContextHanlder.setDataSourceType("dataSource_studyplan");
		
		Session session = sessionFactory.getCurrentSession();
		
//		DataSourceContextHanlder.clearDataSourceType();
		return session;
		
	}


	public T getById(Integer id) {
		if(id == null){
			return null;
		}
		return (T)getSession().get(clazz, id);
	}
}
