package com.lanen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Department;

@Service
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService{


	@SuppressWarnings("unchecked")
	public boolean isIdExist(String department_id) {
		List<Department> list = getSession().createQuery(
				"FROM Department d WHERE d.id=?")//
				.setParameter(0, department_id)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean isNameExist(String department_name) {
		@SuppressWarnings("unchecked")
		List<Department> list =getSession().createQuery("FROM Department d WHERE d.name=?")//
				.setParameter(0, department_name)//
				.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public Department getByName(String item) {
		@SuppressWarnings("unchecked")
		List<Department> list=getSession().createQuery("FROM Department d WHERE d.name=?").setParameter(0, item).list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public boolean isNameExist(String department_name, String department_id) {
		@SuppressWarnings("unchecked")
		List<Department> list =getSession().createQuery("FROM Department d WHERE d.name=? AND d.id!=?")//
				.setParameter(0, department_name)//
				.setParameter(1, department_id)
				.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public void deleteByName(String item) {
		delete(getByName(item).getId());
		
	}

	public Department getByName_1(String item) {
		
		String sql = "SELECT [id],[name],[remark]"+
					" FROM [CoresUserPrivilege].[dbo].[tbldepartment]" +
					"  where name = ? ";
		List<?> list=getSession().createSQLQuery(sql)
										.setParameter(0, item)
										.list();
		if(null!=list&&list.size()>0){
			Object obj = list.get(0);
			Object[] objs = (Object[]) obj;
			Department department = new Department();
			department.setId((String)objs[0]);
			department.setName((String)objs[1]);
			department.setRemark((String)objs[2]);
			return department;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<String> findAllName_1() {
		String sql = "select name from CoresUserPrivilege.dbo.tbldepartment order by id";
		List<String> list=getSession().createSQLQuery(sql).list();
		return list;
	}

	public List<Department> findAll_1() {
		String sql = "SELECT [id],[name],[remark]"+
		" FROM [CoresUserPrivilege].[dbo].[tbldepartment] " +
		"  order by id ";
		List<?> list=getSession().createSQLQuery(sql).list();
		List<Department> departmentList = null;
		if(null!=list&&list.size()>0){
			departmentList  = new ArrayList<Department>();
			Department department = null;
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				department = new Department();
				department.setId((String)objs[0]);
				department.setName((String)objs[1]);
				department.setRemark((String)objs[2]);
				departmentList.add(department);
			}
		}
		return departmentList;
	}



}
