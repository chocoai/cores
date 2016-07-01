package com.lanen.service;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.Role;
import com.lanen.model.Systems;

public interface SystemService extends BaseDao<Systems>{

	/**
	 * 根据id判断实体是否存在
	 * @param id
	 * @return
	 */
	boolean isIdExist(String id);

	/**
	 * 根据名称判断实体是否存在
	 * @param name
	 * @return
	 */
	boolean isNameExist(String name);

	/**
	 * 根据名称（非此id）判断实体是否存在
	 * @param name
	 * @return
	 */
	boolean isNameExist(String name, String id);

	/**
	 * 根据名称或者id判断实体是否存在
	 * @param name
	 * @return
	 */
	boolean isIdNameExist(String name);


	/**
	 * 根据系统名称查询实体
	 * @param moduleName
	 * @return
	 */
	Systems getByName(String systemName);
	
	/** 查询所有（ ）
	 * @return
	 */
	List<Systems> findAll_1();

	/**获得角色列表
	 * @param systemTable
	 * @return
	 */
	List<Role> getRoleListBySystem(Systems systemTable);
	

}
