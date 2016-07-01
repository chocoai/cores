package com.lanen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Role;
import com.lanen.model.Systems;
import com.lanen.model.User;

@Service
public class SystemServiceImpl extends BaseDaoImpl<Systems> implements SystemService{

	@SuppressWarnings("unchecked")
	public boolean isIdExist(String id) {
		List<Systems> list=getSession().createQuery("FROM Systems s WHERE s.id = ? ")//
		.setParameter(0, id)//
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name) {
		List<Systems> list=getSession().createQuery("FROM Systems s WHERE s.systemName = ? ")//
		.setParameter(0, name)//
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name, String id) {
		List<Systems> list=getSession().createQuery("FROM Systems s WHERE s.systemName = ? AND s.id!=? ")//
		.setParameter(0, name)//
		.setParameter(1, id)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean isIdNameExist(String name) {
		List<Systems> list=getSession().createQuery("FROM Systems s WHERE s.systemName = ? OR s.id=? ")//
		.setParameter(0, name)//
		.setParameter(1, name)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public Systems getByName(String systemName) {
		List<Systems> list=getSession().createQuery("FROM Systems s WHERE s.systemName = ? ")//
		.setParameter(0, systemName)//
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public List<Systems> findAll_1() {
		String sql = "SELECT   id, systemName, remark"+
						" FROM      tblsystem";
		List<Systems> systemList = null ;
		
			List<?> list = getSession()
							.createSQLQuery(sql)
							.list();
			if(null != list && list.size()>0){
				systemList = new ArrayList<Systems>();
				Systems systems = null ;
				for(Object obj :list){
					Object[] objs = (Object[]) obj;
					systems = new Systems();
					systems.setId((String)objs[0]);
					systems.setSystemName((String)objs[1]);
					systems.setRemark((String)objs[2]);
					systemList.add(systems);
				}
			}
		
		return systemList;
	}
	@SuppressWarnings("unchecked")
	public List<Role> getRoleListBySystem(Systems systemTable) {
		
		
		
		List<Role> roleList=getSession().createQuery("FROM Role where system = ? ")
										.setParameter(0, systemTable)
										.list();
		return roleList;
	}


	

}
