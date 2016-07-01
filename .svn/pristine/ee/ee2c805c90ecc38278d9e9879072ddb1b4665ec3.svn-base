package com.lanen.service.clinicaltest;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lanen.model.clinicaltest.PoolDataId;

//@Transactional
@Service
public class GetIdServiceImpl implements GetIdService {

	@Resource
	private SessionFactory sessionFactory;
	public String getKey(String entityName) {
		String oldValue="";
		String newValue="";
		PoolDataId getId=(PoolDataId) getSession().get(PoolDataId.class, entityName);
//		String hql = "from PoolDataId as dataId  where dataId.tableName = ? "; 
//		Query query = getSession().createQuery(hql).setParameter(0, entityName);
//		query.setLockMode("dataId",LockMode.UPGRADE);
////		query.setLockOptions(LockOptions.UPGRADE);
//		PoolDataId getId = (PoolDataId) query.uniqueResult();
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
				String sql="select max("+id+") from " +entityName+" ";
				oldValue = (String) getSession().createSQLQuery(sql).uniqueResult();
				if(null == oldValue || "".equals(oldValue)){
					oldValue="20000000001";
				}
			}
			newValue=getNewValue(oldValue);
			getId.setCurrentValue(newValue);
			getSession().update(getId);
			
		}else{
			getId=new PoolDataId();
			getId.setTableName(entityName);
			newValue="20000000001";
			getId.setCurrentValue(newValue);
			getSession().save(getId);
		}
		return newValue;
//		Calendar calendar =Calendar.getInstance();
//            String format = "yyyyMMddhhmmssnnn";
//		SimpleDateFormat sdf = new SimpleDateFormat(format);
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return System.currentTimeMillis()+"";
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
	private  Session getSession(){
		return sessionFactory.getCurrentSession();
		
	}

}
