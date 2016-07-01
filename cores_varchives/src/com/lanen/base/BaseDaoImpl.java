package com.lanen.base;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.model.PoolDataId;



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
		Session session = sessionFactory.getCurrentSession();
		return session;
		
	}

	public boolean isExistById(String id) {
		Object obj =getSession().get(clazz, id);
		return obj!=null ? true :false;
	}


	public String getKey(String entityName) {
		String oldValue="";
		String newValue="";
		PoolDataId getId=(PoolDataId) getSession().get(PoolDataId.class, entityName);
		
		if(null!=getId){
			oldValue=getId.getCurrentValue();
			//处理null 和"" 情况
			if(null == oldValue || "".equals(oldValue.trim())){
				//1.获取第一个字段名称
				String sql1="SELECT  TOP (1)  COLUMN_NAME"+
					" FROM      INFORMATION_SCHEMA.COLUMNS" +
					" WHERE   (TABLE_NAME = '"+entityName+"') AND (ORDINAL_POSITION = 1)";
				String id = (String) getSession().createSQLQuery(sql1).uniqueResult();
				//2.获得对应表主键最大值
				String sql="select max("+id+") from " +entityName +" ";
				oldValue = (String) getSession().createSQLQuery(sql).uniqueResult();
				if(null == oldValue || "".equals(oldValue)){
					oldValue="80000000001";
				}
			}
			newValue=getNewValue(oldValue);
			getId.setCurrentValue(newValue);
			getSession().update(getId);
			
		}else{
			getId=new PoolDataId();
			getId.setTableName(entityName);
			newValue="80000000001";
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
			//处理null 和"" 情况
			if(null == oldValue || "".equals(oldValue.trim())){
				//1.获取第一个字段名称
				String sql1="SELECT  TOP (1)  COLUMN_NAME"+
					" FROM      INFORMATION_SCHEMA.COLUMNS" +
					" WHERE   (TABLE_NAME = '"+clazz.getSimpleName()+"') AND (ORDINAL_POSITION = 1)";
				String id = (String) getSession().createSQLQuery(sql1).uniqueResult();
				//2.获得对应表主键最大值
				String sql="select max("+id+") from " +clazz.getSimpleName() +" ";
				oldValue = (String) getSession().createSQLQuery(sql).uniqueResult();
				if(null == oldValue || "".equals(oldValue)){
					oldValue="10000000001";
				}
			}
			newValue=getNewValue(oldValue);
			getId.setCurrentValue(newValue);
			getSession().update(getId);
			
		}else{
			getId=new PoolDataId();
			getId.setTableName(clazz.getSimpleName());
			newValue="10000000001";
			getId.setCurrentValue(newValue);
			getSession().save(getId);
		}
		return newValue;
	}

}
