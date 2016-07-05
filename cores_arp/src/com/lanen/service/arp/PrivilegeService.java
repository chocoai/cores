package com.lanen.service.arp;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.Privilege;

public interface PrivilegeService extends BaseDao<Privilege>{
	
	/**
	 * 根据权限名称列表  得到  权限列表
	 * @param privilegeNameList
	 * @return
	 */
	List<Privilege> getByPrivilegeNameList(List<String> privilegeNameList);

	/**
	 * 查询所有权限路径列表
	 * @return
	 */
	List<String> getAllPrivilegeUrl();
	
}
