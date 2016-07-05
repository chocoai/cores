package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Employee;
import com.lanen.model.Privilege;
import com.lanen.model.Role;

public interface EmployeeService extends BaseLongDao<Employee> {
	
	/**
	 * 根据名称查询实体
	 * @param value
	 * @return
	 */
	Employee getByUserName(String userName);
	
	/**
	 * 更新用户密码及修改密码时间，同时调用存储过程
	 * @param user
	 * @param password
	 */
	void updatePwd(Employee user);
	
	/**
	 * 获取所有未删除员工的信息
	 * @param condition 
	 * @return
	 */
    List<Employee> getAllEmployees(String condition);
//    /**根据ID查找实体
//     * @param id
//     * @return
//     */
//    Employee getById(Integer id);
    
    /**
     *  获取员工列表Map(String id)
     */
    List<Map<String, String>> getAllEmployeesMap();
    
    /**
     *  获取员工列表Map(String id)
     */
    List<Map<String, String>> getAllEmployeesMapNo();

	/**检查表中工号是否已存在
	 * @param employeeid
	 * @return
	 */
	boolean isExistEmployeeid(String employeeid);
	
	boolean checkPrivilege(Employee user,String privielgeName);
	boolean isHasPrivilege(String employeeId, String privilegeName);
	List<String> findPrivilegeUrlListByUserId(String employeeid);
	String getRolename(Integer id);
	/**根据用户名  、密码、和权限名称列表    判断用户是否有权限
	 * @param userName
	 * @param password
	 * @param privilegeNameList
	 * @return
	 */
	Map<String, Object> newCheckPrivilege(String userName, String password,
			List<String> privilegeNameList);
	Employee getUserByUserName(String name);
	
	/**
	 * 查询有权限的用户
	 */
	List<Employee> findByPrivilegeName(String privilegeName);
	/**
	 * 查询该权限的用户名密码
	 */
	Map<String,Object> checkPrivilege(String userName, String password,String privilegeName);
	/**
	 * 更新用户密码
	 * @param user
	 * @param password
	 */
	void updatePwd(Employee user,String newpassword);
	/**
	 * FX存放employee
	 */
	Map<String,Object> getAllEmployee();
	
	/**
	 * 查询员工角色
	 */
	List<?> getRoleByEmployeeId(String employee_id);
	/**
	 * 所有角色
	 */
	List<Role> getAllRole();
	/**
	 * 检查员工角色
	 * @param employeeid
	 * @param roleid
	 * @return
	 */
	public boolean checkEmployeeRole(String employeeid, String roleid);
		
}
