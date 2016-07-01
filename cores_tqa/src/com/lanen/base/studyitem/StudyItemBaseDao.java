package com.lanen.base.studyitem;

import java.util.List;


public interface StudyItemBaseDao<T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	 void save(T entity);
	 /**
	  * 删除实体
	  * @param id
	  */
	 void delete(String id);
	 /**
	  * 更新实体
	  * @param entity
	  */
     void update(T entity);
     /**
      * 查询实体    ,如果id  为空  ，返回  null
      * @param id
      * @return
      */
     T getById(String id);
     /**
      * 查询实体
      * @param ids
      * @return
      */
     List<T> getByIds(String[] ids);
     /**
      *查询所有
      * @return
      */
     List<T>  findAll();
     
     
     /**
      * id是否存在
      * @param id
      * @return
      */
     boolean isExistById(String id);
     
     /**
      * 获取主键
      * @param entityName
      * @return
      */
     public String getKey(String entityName);
     
     public String getKey();

}
