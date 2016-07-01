package com.lanen.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Module;
import com.lanen.model.Privilege;
import com.lanen.model.Role;
import com.lanen.model.User;

@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@Resource
	private ModuleService moduleService;
	@Resource
	private RoleService roleService;
	@SuppressWarnings("unchecked")
	public boolean isIdExist(String id) {
		List<Privilege> list=getSession().createQuery("FROM Privilege p WHERE p.id = ? ")//
		.setParameter(0, id)//
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name) {
		List<Privilege> list=getSession().createQuery("FROM Privilege p WHERE p.privilegeName = ? ")//
		.setParameter(0, name)//
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public void saveWithModuleAndTime(Privilege obj, String moduleName) {
		obj.setCreateTime(new Date());
		Module module= moduleService.getById(moduleName);
		obj.setModule(module);
		save(obj);
		
	}

	@SuppressWarnings("unchecked")
	public Privilege getByName(String name) {
		List<Privilege> list=getSession().createQuery("FROM Privilege p WHERE p.privilegeName = ? ")//
		.setParameter(0, name)//
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name, String id) {
		List<Privilege> list=getSession().createQuery("FROM Privilege p WHERE p.privilegeName = ? and p.id!= ?")//
		.setParameter(0, name)//
		.setParameter(1, id)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public void update(Privilege obj, String moduleName) {
		Module module= moduleService.getById(moduleName);
		obj.setModule(module);
		update(obj);
	}

	public void deleteByName(String name) {
		Privilege entity=getByName(name);
		delete(entity.getId());
	}

	public List<Privilege> getByPrivilegeNameList(List<String> privilegeNameList) {
		List<Privilege> privilegeList=new ArrayList<Privilege>();
		if(null!=privilegeNameList&&privilegeNameList.size()>0){
			for(String privilegeName:privilegeNameList){
				privilegeList.add(getByName(privilegeName));
			}
			return privilegeList;
		}
		return null;
	}

	public List<String> getAllPrivilegeUrl() {
		List<String> privilegeUrlList=new ArrayList<String>();
		List<Privilege> privilegeList=findAll();
		if(null!=privilegeList&&privilegeList.size()>0){
			for(Privilege obj:privilegeList){
				if(null!=obj.getPrivilegePath()&&!"".equals(obj.getPrivilegePath())){
					privilegeUrlList.add(obj.getPrivilegePath());
				}
			}
			return privilegeUrlList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Privilege> getPrivilegeListByModule(Module obj) {
		List<Privilege> privilegeList = getSession().createQuery("From Privilege where module = ?")
													.setParameter(0, obj)
													.list();
		return privilegeList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPrivilegeNameByRoleId(String roleId) {
		String sql = "select  privilegeName"+
			" from CoresUserPrivilege.dbo.tbl_role_privilege rp left join CoresUserPrivilege.dbo.tblprivilege p "+
			" on rp.privilegeId = p.id "+
			" where rp.roleId = ? ";
		List<String> privilegeNameList = getSession().createSQLQuery(sql).setParameter(0, roleId).list();
		return privilegeNameList;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleListByPrivilege(Privilege privilege) {
		String sql = "select roleId"+
					" from CoresUserPrivilege.dbo.tbl_role_privilege "+
					" where privilegeId = ? ";
		
		List<String> roleIdList = getSession().createSQLQuery(sql).setParameter(0, privilege.getId()).list();
		List<Role> roleList = null;
		if(null != roleIdList && roleIdList.size()>0){
		String[] ids = new String[roleIdList.size()];
		
		for(int i = 0;i<roleIdList.size();i++){
			ids[i] = roleIdList.get(i);
		}
		
		roleList = roleService.getByIds(ids);
		}
		
		return roleList;
	}

	


}
