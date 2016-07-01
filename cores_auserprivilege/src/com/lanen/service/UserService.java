package com.lanen.service;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.User;

public interface UserService extends BaseDao<User> {

	/**
	 * 通过用户名密码 查询用户
	 * @param userName
	 * @param password
	 * @return
	 */
	User findUserByUserNamePassword(String userName, String password);

	/**
	 * 根据用户编号查询实体
	 * @param moduleName
	 * @return
	 */
	public User findByUserCode(String userCode);

	/**
	 * 判断id是否存在
	 * @param id
	 * @return
	 */
	boolean isIdExist(String id);

	/**
	 * 判断userName是否存在
	 * @param id
	 * @return
	 */
	boolean isNameExist(String userName);

	/**
	 * 保存实体，带部门，角色列表（null ，无用）无另一系统同步
	 * @param entity
	 * @param departmentName
	 * @param roleNameList
	 */
	void saveWithDepartmentAndTimeAndRole(User entity, String departmentName,
			List<String> roleNameList);

	/**
	 * 根据名称查询实体
	 * @param value
	 * @return
	 */
	User getByUserName(String userName);
	
	/**
	 * 根据用户名查询实体 
	 * @param realName
	 * @return
	 */
	User getByRealName(String realName);

	/**
	 * 跟新实体，带权限名称列表(授权)
	 * @param trim
	 * @param roleNameList
	 * @param user 
	 */
	void update(String id, List<String> roleNameList, User user);

	/**
	 * 账号停用
	 * @param id
	 * @param string
	 */
	void stopId(String id, String string);

	/**
	 * 密码重置
	 * @param userName
	 */
	void resetPassword(String userName);

	/**
	 * 账号停用
	 * @param userName
	 * @param string
	 */
	void stopUserName(String userName, String remark);

	/**
	 * 更新用户，包括部门（无权限和密码问题）
	 * @param entity
	 * @param departmentName
	 */
	void update(User entity, String departmentName);

	/**
	 * 更新实体（需要重新设定最后修改密码时间）
	 * @param user
	 */
	void updateWithTime(User user);

	

	/**
	 * 根据权限名称查询有该权限的所有用户
	 * @param privilegeName
	 * @return
	 */
	List<User> findByPrivilegeName(String privilegeName);
	/**
	 * 根据权限名称查询有该权限的所有用户(不包括停用用户)
	 * @param privilegeName
	 * @return
	 */
	List<User> findByPrivilegeName2(String privilegeName);
	/**
	 * 根据权限名称查询有该权限的所有用户(userName,realName),未停用用户
	 * @param privilegeName
	 * @return
	 */
	List<?> findUserNameRealNameByPrivilegeName(String privilegeName);
	/**
	 * 根据权限名称查询有该权限的所有用户(userName),未停用用户
	 * @param privilegeName
	 * @return
	 */
	List<String> findUserNameByPrivilegeName(String privilegeName);

	/**
	 * 根据用户名  、密码、和权限名称判断用户是否有权限
	 * @param userName
	 * @param password
	 * @param privielgeName
	 * @return
	 */
	Map<String, Object> checkPrivilege(String userName, String password, String privielgeName);
	/**
	 * 根据用户名  、密码、和权限名称      权限名称2       判断用户是否有权限
	 * @param userName
	 * @param password
	 * @param privielgeName
	 * @return
	 */
	Map<String, Object> checkPrivilege(String userName, String password, String privielgeName,String privielegeName2);
	
	/**
	 * 判断该用户是否有该权限
	 * @param user
	 * @param privielgeName
	 * @return
	 */
	public  boolean checkPrivilege(User user,String privielgeName);
	
	
	/**
	 * 更新用户密码及修改密码时间，同时调用存储过程
	 * @param user
	 * @param password
	 */
	void updatePwd(User user,String password);
	
	/**
	 * 根据用户名查询真实姓名
	 */
	Map<String ,String> getRealName(String departmentid );
	
	
	/**
	 * 根据用户名查询真实姓名
	 * @param username
	 * @return
	 */
	String getRealNameByUserName(String username);

	/**根据用户名  、密码、和权限名称列表    判断用户是否有权限
	 * @param userName
	 * @param password
	 * @param privilegeNameList
	 * @return
	 */
	Map<String, Object> newCheckPrivilege(String userName, String password,
			List<String> privilegeNameList);
	
	/**
	 * 根据用户名查询真实姓名
	 */
	Map<String ,String> getAllRealName();
	
	/** 根据部门id 查询用户列表
	 * @param departmentId
	 * @return
	 */
	List<User> findUserListByDepartmentId_1(String departmentId);
	
	/**根据用户名查询用户（null）
	 * @param userName
	 * @return
	 */
	User getByUserName_1(String userName);
	
	/**判断id是否存在（）
	 * @param userName
	 * @return
	 */
	boolean isIdExist_1(String userName);

	/**判断用户名是否存在（）
	 * @param userName
	 * @return
	 */
	boolean isNameExist_1(String userName);

	/**判断姓名是否存在（）
	 * @param realName
	 * @return
	 */
	boolean isRealNameExist_1(String realName);
	/**
	 * 根据用户名查询真实姓名
	 * @param username
	 * @return
	 */
	String getRealNameById(String id);
	
	/**根据用户名查询部门名称
	 * @param userName
	 * @return
	 */
	String getPartmentNameByUsreName_1(String userName);
	
	
	/**根据用户id，权限名称，判读该用是否有该权限
	 * @param userId
	 * @param privilegeName
	 * @return
	 */
	boolean isHasPrivilege(String userId,String privilegeName);
	
	
	/**判断该用户除这个角色外，其他角色是否有该权限
	 * @param userId
	 * @param string
	 * @param roleId
	 * @return
	 */
	boolean isHasPrivilege(String userId, String privilegeName, String roleId);
	
	
	/**根据角色列表，权限名称，判读该用是否有该权限
	 * @param userId
	 * @param privilegeName
	 * @return
	 */
	boolean isHasPrivilege2(List<String> roleNameList,String privilegeName);
	
	
	/**增加权限  若用户名不存在，在该系统中添加，或权限已存在则不再重复添加（与另一系统同步用）
	 * @param userId
	 * @param privilege
	 */
	void addPrivileage(String userId,String privilege);
	
	/**增加权限（与另一系统同步用）
	 * @param userId
	 * @param privilege
	 */
	void delPrivileage(String userId,String privilege);

	/** 根据角色id 查询 用户名和姓名列表
	 * @param id
	 * @return
	 */
	List<?> findUserNameRealNameByRoleId(String id);
	
	
	
	/** 与另一系统同步用，增加用户（若存在则放弃）
	 * @param userName
	 * @param realName
	 * @param departmentName
	 * @param password
	 */
	void insertIntoUser(String userName,String realName,String departmentName,String password);

	/**根据部门id，查询用户列表
	 * @param id
	 * @return
	 */
	List<User> getUserListByDepartmentId(String id);
	
	/**根据用户id，查询该用户的所有权限url列表
	 * @param userId
	 * @return
	 */
	List<String> findPrivilegeUrlListByUserId(String userId);
	
	/**根据realName 查询userName
	 * @param studydirector
	 * @return
	 */
	String getUserNameByRealName(String studydirector);
	
}
