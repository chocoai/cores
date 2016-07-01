package com.lanen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Privilege;
import com.lanen.model.Role;
import com.lanen.model.Systems;
import com.lanen.model.User;

@Service
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {

	@Resource
	private PrivilegeService privilegeService;
	@Resource
	private UserService userService;
	
	@Resource
	private SystemService systemService;
	public void saveWithTime(Role role) {
		role.setCreateTime(new Date());
		this.save(role);
	}

	@SuppressWarnings("unchecked")
	public Role findByRoleName(String roleName) {
		
		List<Role> list = getSession().createQuery(
				"FROM Role r WHERE r.roleName = ?")//
				.setParameter(0, roleName)//
				.list();
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
	public List<Role> findListByRoleNames(List<String> editDeskList) {
		List<Role> list=new ArrayList<Role>();
		for(String roleName:editDeskList){
			list.add(findByRoleName(roleName));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isIdExist(String id) {
		List<Role> list = getSession().createQuery(
				"FROM Role r WHERE r.id = ?")//
				.setParameter(0, id)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name) {
		List<Role> list = getSession().createQuery(
				"FROM Role r WHERE r.roleName = ?")//
				.setParameter(0, name)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void saveWithModuleAndTimeAndPrivilege(Role role, String systemName,
			List<String> privilegeNameList) {
		List<Privilege> privilegeList=privilegeService.getByPrivilegeNameList(privilegeNameList);
		Set<Privilege> privilegeSet= new HashSet<Privilege>(privilegeList);
		role.setPrivileges(privilegeSet);
		role.setCreateTime(new Date());
		Systems system=systemService.getByName(systemName);
		role.setSystem(system);
		save(role);
		
	}

	@SuppressWarnings("unchecked")
	public boolean isNameExist(String name, String id) {
		List<Role> list = getSession().createQuery(
				"FROM Role r WHERE r.roleName = ? AND r.id!=? ")//
				.setParameter(0, name)//
				.setParameter(1, id).list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateWithPrivilege(Role role, List<String> privilegeNameList) {
		
		//与另一系统同步用
		String oldRoleName = getById(role.getId()).getRoleName();
		List<String> roleNameList = new ArrayList<String>();
		roleNameList.add(oldRoleName);
		
		//TODO 1.原  有无       一般毒理_登录   权限
		boolean hasDLDL = userService.isHasPrivilege2(roleNameList,"一般毒理_登录");
		
		
		//原  有无       供试品管理_登录   权限
		boolean hasGSPDL = userService.isHasPrivilege2(roleNameList,"供试品管理_登录");
		//原  有无       SD   权限
		boolean hasSD = userService.isHasPrivilege2(roleNameList,"SD");
		
		//2.现  有无       一般毒理_登录   权限
		boolean hasDLDL2 = privilegeNameList.contains("一般毒理_登录");
		
		//现  有无       供试品管理_登录   权限
		boolean hasGSPDL2 = privilegeNameList.contains("供试品管理_登录");
		//现  有无       SD   权限
		boolean hasSD2 = privilegeNameList.contains("SD");
		//3.角色对应用户列表
		List<?> userNameRealNameList1 = userService.findUserNameRealNameByRoleId(role.getId());
		List<?> userNameRealNameList2 = userService.findUserNameRealNameByRoleId(role.getId());
		List<?> userNameRealNameList3 = userService.findUserNameRealNameByRoleId(role.getId());
				
		
		
		List<Privilege> privilegeList=privilegeService.getByPrivilegeNameList(privilegeNameList);
		Set<Privilege> privilegeSet=null;
		if(null!=privilegeList){
			privilegeSet= new HashSet<Privilege>(privilegeList);
		}
//		getSession().merge(role.getPrivileges());////
		role.setPrivileges(privilegeSet);
		getSession().merge(role);
//		update(role);
		
		
		
		//与另一系统同步用
		//4.判断是否要更新	
		
		
		//一般毒理_登录   权限
		if(hasDLDL != hasDLDL2){
			if(hasDLDL){ //原 有对应权限
				if(null != userNameRealNameList1 && userNameRealNameList1.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						
						//删除前判断该用户是否真的有该权限
						
						hasDLDL2 = userService.isHasPrivilege(id, "一般毒理_登录",role.getId());
						if(!hasDLDL2){
							userService.delPrivileage(id, "一般毒理_登录");
						}
					}
				}
			}else{
				if(null != userNameRealNameList1 && userNameRealNameList1.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						
						userService.addPrivileage(id, "一般毒理_登录");
					}
				}
			}
		}
		//供试品管理_登录   权限
		if(hasGSPDL != hasGSPDL2){
			if(hasGSPDL){ //原 有对应权限
				if(null != userNameRealNameList2 && userNameRealNameList2.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						
						//删除前判断该用户是否真的有该权限
						hasGSPDL2 = userService.isHasPrivilege(id, "供试品管理_登录",role.getId());
						if(!hasGSPDL2){
							userService.delPrivileage(id, "供试品管理_登录");
						}
					}
				}
			}else{
				if(null != userNameRealNameList2 && userNameRealNameList2.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						userService.addPrivileage(id, "供试品管理_登录");
					}
				}
			}
		}
		// SD   权限
		if(hasSD != hasSD2){
			if(hasSD){ //原 有对应权限
				if(null != userNameRealNameList3 && userNameRealNameList3.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						
						//删除前判断该用户是否真的有该权限
						hasSD2 = userService.isHasPrivilege(id, "SD",role.getId());
						if(!hasSD2){
							userService.delPrivileage(id, "SD");
						}
					}
				}
			}else{
				if(null != userNameRealNameList3 && userNameRealNameList3.size()>0){
					for(Object obj :userNameRealNameList1){
						Object[] objs = (Object[]) obj;
						String id = (String) objs[0];
						userService.addPrivileage(id, "SD");
					}
				}
			}
		}
	}

	public List<Role> getRoleListByRoleNameList(List<String> roleNameList) {
		List<Role> roleList=new ArrayList<Role>();
		if(null!=roleNameList&&roleNameList.size()>0){
			for(String str:roleNameList){
				roleList.add(findByRoleName(str));
			}
			return roleList;
		}
		return null;
	}

	public List<Role> findRoleListBySystemId_1(String systemId) {

		String sql = "SELECT   id, roleName, createTime, remark "+
					" FROM      tblrole"+
					" WHERE   (systemId = ? )"+
					" ORDER BY roleName ";
		List<Role> roleList = null;
		if (null != systemId && !"".equals(systemId)) {

			List<?> list = getSession()
					.createSQLQuery(sql)
					.setParameter(0, systemId)
					.list();
			if (null != list && list.size() > 0) {
				roleList = new ArrayList<Role>();
				Role role = null;
				for (Object obj : list) {
					Object[] objs = (Object[]) obj;
					role = new Role();
					role.setId((String)objs[0]);
					role.setRoleName((String)objs[1]);
					role.setCreateTime((Date)objs[2]);
					role.setRemark((String)objs[3]);
					roleList.add(role);
				}
			}
		}

		return roleList;
	}

	public List<Role> findRoleListByUserId_1(String userId) {
		String sql = "select r.id,r.roleName,r.createTime,r.remark"+
					" from CoresUserPrivilege.dbo.tbl_user_role as ur left join "+
					" CoresUserPrivilege.dbo.tblrole as r on ur.roleId = r.id"+
					" where ur.userId = ? ";
		
		List<Role> roleList = null;
		if (null != userId && !"".equals(userId)) {

			List<?> list = getSession()
					.createSQLQuery(sql)
					.setParameter(0, userId)
					.list();
			if (null != list && list.size() > 0) {
				roleList = new ArrayList<Role>();
				Role role = null;
				for (Object obj : list) {
					Object[] objs = (Object[]) obj;
					role = new Role();
					role.setId((String)objs[0]);
					role.setRoleName((String)objs[1]);
					role.setCreateTime((Date)objs[2]);
					role.setRemark((String)objs[3]);
					roleList.add(role);
				}
			}
		}

		return roleList;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserListByRole(Role obj) {
		String sql = "select userId"+
					" from CoresUserPrivilege.dbo.tbl_user_role "+
					" where roleId = ? ";
		List<String> userIdList = getSession().createSQLQuery(sql).setParameter(0, obj.getId()).list();
		List<User> userList = null;
		if(null != userIdList && userIdList.size()>0){
			String[] ids = new String[userIdList.size()];
			
			for(int i = 0;i<userIdList.size();i++){
				ids[i] = userIdList.get(i);
			}
			
			userList = userService.getByIds(ids);
		}
		
		return userList;
	}

}
