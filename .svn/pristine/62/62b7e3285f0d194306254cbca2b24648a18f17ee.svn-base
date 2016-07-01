package com.lanen.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Department;
import com.lanen.model.Privilege;
import com.lanen.model.Regulation;
import com.lanen.model.Role;
import com.lanen.model.User;
import com.lanen.model.UserRoleLog;
import com.lanen.util.CryptUtils;
import com.lanen.util.DateUtil;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService{

	@Resource
	private DepartmentService departmentService;
	@Resource
	private RoleService roleService;
	@Resource
	private PrivilegeService privilegeService;
	@Resource
	private RegulationService regulationService;
	@Resource
	private UserRoleLogService usrRoleLogService;
	public User findUserByUserNamePassword(String userName, String password) {
		return (User) getSession().createQuery("FROM User u WHERE u.userName = ? AND u.password = ? ")//
		.setParameter(0, userName)//
		.setParameter(1, DigestUtils.md5Hex(password))//// 要使用MD5的摘要
		.uniqueResult();
	}

	

	@SuppressWarnings("unchecked")
	public User findByUserCode(String userCode) {
		List<User> list = getSession().createQuery(
				"FROM User u WHERE u.userCode = ?")//
				.setParameter(0, userCode)//
				.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isIdExist(String id) {
		List<User> list = getSession()
				.createQuery("FROM User u WHERE u.id = ?")//
				.setParameter(0, id)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isNameExist(String userName) {
		List<User> list = getSession().createQuery(
				"FROM User u WHERE u.userName = ?")//
				.setParameter(0, userName)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void saveWithDepartmentAndTimeAndRole(User entity,
			String departmentName, List<String> roleNameList) {
		Department department=departmentService.getByName_1(departmentName);
		entity.setDepartment(department);
		entity.setCreateTime(new Date());
		entity.setUpdatePasswordTime(new Date());
		List<Role> roleList= roleService.getRoleListByRoleNameList(roleNameList);
		if(null!=roleList&&roleList.size()>0){
			entity.setRoles(new HashSet<Role>(roleList));
		}
		Regulation regulation=regulationService.getByName("缺省密码");
		String password = "";
		if(null!=regulation){
			if(null==regulation.getSetValue()||regulation.getSetValue().trim().equals("")){
				password=regulation.getDefaultValue().trim();
				entity.setPassword(DigestUtils.md5Hex(regulation.getDefaultValue().trim()));
			}else{
				password = regulation.getSetValue().trim();
				entity.setPassword(DigestUtils.md5Hex(regulation.getSetValue().trim()));
			}
		}
//		DigestUtils.md5Hex(password)
		
//		// 另一系统同步
//		String sql = "insert into COMMONDB.dbo.TBUSERINFO(USERID,USERCODE,USERNAME,USERDEP) values(?,?,?,?)";
//		getSession().createSQLQuery(sql)
//					.setParameter(0, entity.getUserName())
//					.setParameter(1, entity.getUserName())
//					.setParameter(2, entity.getRealName())
//					.setParameter(3, department.getName())
//					.executeUpdate();
//		Query query = getSession().createSQLQuery("{Call UpdateUserPwd(?,?,1)}");
//		query.setParameter(0, entity.getUserName());
//		query.setParameter(1, password);
//		query.executeUpdate();
		//插入用户
		insertIntoUser(entity.getUserName(),entity.getRealName(),department.getName(),password);
		
		
		getSession().save(entity);
	}

	@SuppressWarnings("unchecked")
	public User getByUserName(String userName) {
		List<User> list = getSession().createQuery(
				"FROM User u WHERE u.userName = ?")//
				.setParameter(0, userName)//
				.list();
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public User getByRealName(String realName) {
		List<User> list = getSession().createQuery(
		"FROM User u WHERE u.realName = ?")//
		.setParameter(0, realName)//
		.list();
      if (null != list && list.size() > 0) {
	     return list.get(0);
      } else {
	    return null;
      }
	}
	public void update(String id, List<String> roleNameList, User user) {
		List<UserRoleLog> userRoleLogList=new ArrayList<UserRoleLog>();
		User entity=getById(id);
		Set<Role> oldRoleSet=entity.getRoles();
		List<Role> roleList= roleService.getRoleListByRoleNameList(roleNameList);
		//与另一系统同步用
		// 1.原  有无       一般毒理_登录   权限
				boolean hasDLDL = isHasPrivilege(id,"一般毒理_登录");
		
		
		//原  有无       供试品管理_登录   权限
				boolean hasGSPDL = isHasPrivilege(id,"供试品管理_登录");
		
		//原  有无       SD   权限
				boolean hasSD = isHasPrivilege(id,"SD");
		
		
		//2.现  有无       一般毒理_登录   权限
				boolean hasDLDL2 = isHasPrivilege2(roleNameList,"一般毒理_登录");
		
		//现  有无       供试品管理_登录   权限
				boolean hasGSPDL2 = isHasPrivilege2(roleNameList,"供试品管理_登录");
		//现  有无       SD   权限
				boolean hasSD2 = isHasPrivilege2(roleNameList,"SD");
				
		
		
		if(null!=oldRoleSet){
			List<Role> oldRoleList=new ArrayList<Role>(oldRoleSet);
			if(null!=roleList){
				for(Role role:oldRoleList){
					UserRoleLog userRoleLog=new UserRoleLog();
					if(!roleList.contains(role)){
						userRoleLog.setUserName(entity.getUserName());
						userRoleLog.setUserCode(entity.getId());
						userRoleLog.setSystemId(role.getSystem().getId());
						userRoleLog.setRoleId(role.getId());
						userRoleLog.setType("撤销");
						userRoleLog.setCreateTime(new Date());
						userRoleLog.setUser(user);
						userRoleLog.setPrivilegeList(toPrivilegeStringByRole(role));
						userRoleLog.setMD5(DigestUtils.md5Hex(userRoleLog.getSystemId()+userRoleLog.getRoleId()+userRoleLog.getCreateTime()));
						userRoleLogList.add(userRoleLog);
					}
				}
				for(Role role:roleList){
					UserRoleLog userRoleLog=new UserRoleLog();
					if(!oldRoleSet.contains(role)){
						userRoleLog.setUserName(entity.getUserName());
						userRoleLog.setUserCode(entity.getId());
						userRoleLog.setSystemId(role.getSystem().getId());
						userRoleLog.setRoleId(role.getId());
						userRoleLog.setType("授权");
						userRoleLog.setCreateTime(new Date());
						userRoleLog.setUser(user);
						userRoleLog.setPrivilegeList(toPrivilegeStringByRole(role));
						userRoleLog.setMD5(DigestUtils.md5Hex(userRoleLog.getSystemId()+userRoleLog.getRoleId()+userRoleLog.getCreateTime()));
						userRoleLogList.add(userRoleLog);
					}
				}
			}else{
				for(Role role:oldRoleList){
					UserRoleLog userRoleLog=new UserRoleLog();
						userRoleLog.setUserName(entity.getUserName());
						userRoleLog.setUserCode(entity.getId());
						userRoleLog.setSystemId(role.getSystem().getId());
						userRoleLog.setRoleId(role.getId());
						userRoleLog.setType("撤销");
						userRoleLog.setCreateTime(new Date());
						userRoleLog.setUser(user);
						userRoleLog.setPrivilegeList(toPrivilegeStringByRole(role));
						userRoleLog.setMD5(DigestUtils.md5Hex(userRoleLog.getSystemId()+userRoleLog.getRoleId()+userRoleLog.getCreateTime()));
						userRoleLogList.add(userRoleLog);
				}
			}
		}else{
			if(null!=roleList){
				for(Role role:roleList){
					UserRoleLog userRoleLog=new UserRoleLog();
						userRoleLog.setUserName(entity.getUserName());
						userRoleLog.setUserCode(entity.getId());
						userRoleLog.setSystemId(role.getSystem().getId());
						userRoleLog.setRoleId(role.getId());
						userRoleLog.setType("授权");
						userRoleLog.setCreateTime(new Date());
						userRoleLog.setUser(user);
						userRoleLog.setPrivilegeList(toPrivilegeStringByRole(role));
						userRoleLog.setMD5(DigestUtils.md5Hex(userRoleLog.getSystemId()+userRoleLog.getRoleId()+userRoleLog.getCreateTime()));
						userRoleLogList.add(userRoleLog);		
				}
			}
		}
		
		usrRoleLogService.saveList(userRoleLogList);
		if(null!=roleList&&roleList.size()>0){
			entity.setRoles(new HashSet<Role>(roleList));
		}else{
			entity.setRoles(null);
		}
		
		
		
		update(entity);
		
		//与另一系统同步用
		//3.判断是否要更新	
		//一般毒理_登录   权限
		if(hasDLDL != hasDLDL2){
			if(hasDLDL){ //原 有对应权限
				delPrivileage(id, "一般毒理_登录");
			}else{
				addPrivileage(id, "一般毒理_登录");
			}
		}
		//供试品管理_登录   权限
		if(hasGSPDL != hasGSPDL2){
			if(hasGSPDL){ //原 有对应权限
				delPrivileage(id, "供试品管理_登录");
			}else{
				addPrivileage(id, "供试品管理_登录");
			}
		}
		// SD   权限
		if(hasSD != hasSD2){
			if(hasSD){ //原 有对应权限
				delPrivileage(id, "SD");
			}else{
				addPrivileage(id, "SD");
			}
		}
		
	}

	public void stopId(String id, String string) {
		User user=getById(id);
		user.setFlag("停用");
        user.setFlagRemark(string);
        update(user);
	}

	public void resetPassword(String userName) {
		User entity=getByUserName(userName);
		Regulation regulation=regulationService.getByName("缺省密码");
		String newPassword = "111111";
		if(null!=regulation){
			if(null==regulation.getSetValue()||regulation.getSetValue().trim().equals("")){
				newPassword=regulation.getDefaultValue().trim();
				entity.setPassword(DigestUtils.md5Hex(regulation.getDefaultValue().trim()));
			}else{
				newPassword=regulation.getSetValue().trim();
				entity.setPassword(DigestUtils.md5Hex(regulation.getSetValue().trim()));
			}
		}
		
		updatePwd(entity,newPassword);
		
	}

	public void stopUserName(String userName, String remark) {
		User entity=getByUserName(userName);
		entity.setFlag("停用");
		entity.setFlagRemark(remark);
        update(entity);
	}
	
	
	public String toPrivilegeStringByRole(Role role){
		String str="";
		Set<Privilege> set=role.getPrivileges();
		if(null!=set){
			List<Privilege> list=new ArrayList<Privilege>(set);
			for(Privilege privilege:list){
				str=str+","+privilege.getPrivilegeName();
			}
			str=str.substring(1);
		}
		if(str.getBytes().length>7900){
			str = str.substring(0,2000);
		}
		return str;
	}



	public void update(User entity, String departmentName) {
		Department department=departmentService.getByName_1(departmentName);
		
		String sql = "update CoresUserPrivilege.dbo.tbluser"+
              		" set tbluser.userNumber = ? ,tbluser.email = ?"+
              		" ,tbluser.phone=?,tbluser.departmentId = ?"+
              		" where tbluser.userName = ? ";
		getSession().createSQLQuery(sql)
					.setParameter(0, entity.getUserNumber())
					.setParameter(1, entity.getEmail())
					.setParameter(2, entity.getPhone())
					.setParameter(3, department.getId())
					.setParameter(4, entity.getUserName())
					.executeUpdate();
		
//		if(null!=department&&department.getName().equals(departmentName)){
//			update(entity);
//		}else{
//			department=departmentService.getByName(departmentName);
//			entity.setDepartment(department);
//			//update(entity);
//			getSession().merge(entity);
//		}
	}



	public void updateWithTime(User user) {
		if(null!=user){
			user.setUpdatePasswordTime(new Date());
			getSession().update(user);
		}
	}



	public List<User> findByPrivilegeName(String privilegeName) {
		
		if(null==privilegeName  || "".equals(privilegeName)){//参数为空
			return null;
		}
		
		Privilege privilege = privilegeService.getByName(privilegeName);
		List<User> list = new ArrayList<User>();
		if(null!=privilege){
			Set<Role> set=privilege.getRoles();
			if(null!=set&&set.size()>0){//角色set不为空
				List<User> allUser = findAll();//所有用户
				if(null!=allUser&&allUser.size()>0){
					for(User user:allUser){
						Set<Role> roleSet = user.getRoles();
						if(null!=roleSet&&roleSet.size()>0){
							List<Role> roleList =new ArrayList<Role>(roleSet);
							for(Role role : roleList){
								 if(set.contains(role)){
									 list.add(user);
								 }
							}
						}
					}
				}
			}
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<User> findByPrivilegeName2(String privilegeName) {
		
		if(null==privilegeName  || "".equals(privilegeName)){//参数为空
			return null;
		}
		
		//Privilege privilege = privilegeService.getByName(privilegeName);
							
		String hql = "SELECT DISTINCT u from User u left join fetch u.roles as r left join fetch r.privileges as p " +
		" where p.privilegeName =:privilegeName and u.flag !='停用' " +
		" order by userName";
//		String hql = "SELECT DISTINCT u from User u left join  u.roles as r left join  r.privileges as p " +
//				"where p.privilegeName =:privilegeName and u.flag !='停用'";
		
//		List<User> list = new ArrayList<User>();
//		if(null!=privilege){
//			Set<Role> set=privilege.getRoles();
//			if(null!=set&&set.size()>0){//角色set不为空
//				List<User> allUser = findAll();//所有用户
//				if(null!=allUser&&allUser.size()>0){
//					for(User user:allUser){
//						if(!"停用".equals(user.getFlag())){
//							Set<Role> roleSet = user.getRoles();
//							if(null!=roleSet&&roleSet.size()>0){
//								List<Role> roleList =new ArrayList<Role>(roleSet);
//								for(Role role : roleList){
//									if(set.contains(role)){
//										list.add(user);
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		List<User> list = getSession().createQuery(hql)
										.setParameter("privilegeName", privilegeName)
										.list();
		return list;
	}



	public Map<String, Object> checkPrivilege(String userName, String password,
			String privielgeName) {
		Map<String,Object> map= new HashMap<String , Object>();
		User user =findUserByUserNamePassword(userName, password);
//		map.put("nullUserError", "");// "" 表示用 户不为空
//		map.put("user", user);       // 用  户
//		map.put("forbidden", "");    // "" 表示用户账号未停用
//		map.put("entitle", "");      // "" 表示用户有权限
//		map.put("overdue", "");      // "" 表示用户密码未过期
		if(null!=user){
			//非空用户
			map.put("nullUserError", "");
			map.put("user", user);
			if (null==user.getFlag()||"停用".equals(user.getFlag())) {
				//账号已停用
				map.put("forbidden", "账号已停用，请联系管理员！");
			}else{
				//账号未停用
				map.put("forbidden", "");
				if (checkPrivilege(user, privielgeName)) {
					// 有权限
					map.put("entitle", "");
					if (null != user.getUpdatePasswordTime()) {//密码最后更新时间不为空
						//计算更新的天数
						int updatePasswordTime = 0;
						try {
							updatePasswordTime = DateUtil.getBetweenDays(new Date(),user.getUpdatePasswordTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//判断是否超过 规定天数
						if (!regulationService.isOverTime(updatePasswordTime)) {
							//没超过规定天数
							map.put("overdue", "");
							
						} else {
							//密码过期
							map.put("overdue", "密码已过期，请修改");
						}
					} else {
						//最后修改密码时间为空
						map.put("overdue", "密码已过期，请修改");     
					}
					
				}else{
					// 无权限
					map.put("entitle", "您未被授权登录该系统");
				}
			}
		}else{
			map.put("nullUserError", "用户名或密码错误！");
		}
		return map;
	}
	
	//判断该用户是否有该权限
	public  boolean checkPrivilege(User user,String privielgeName){
		//判断用户是否为空
		if(null==user){//为空直接   返回 false
			return false;
		}else{
//			//超级管理员  ，直接返回 true
//			if("admin".equals(user.getUserName())||"administrator".equals(user.getUserName())){
//				return true;
//			}
		}
		//权限名称  为空   或者  为""   不用验证权限
		if(null==privielgeName || privielgeName.equals("")){
			return true;
		}
		
		
		return isHasPrivilege(user.getId(),privielgeName);
//		Set<Role> roles=user.getRoles();
//		if( null!=roles && roles.size()>0 ){//角色不为空
//			// 用户的角色列表
//			List<Role> roleList=new ArrayList<Role>(roles);  
//			for(Role role : roleList){
//				Set<Privilege> privileges=role.getPrivileges();
//				if(null!=privileges && privileges.size()>0){
//					//角色对应的权限列表
//					List<Privilege> privilegeList=new ArrayList<Privilege>(privileges);
//					for(Privilege privielge :privilegeList){
//						//查询到该权限  ,返回   true
//						if(privielgeName.equals(privielge.getPrivilegeName())){
//							return true;
//						}
//					}
//				}
//			}
//		}
		
//		return false;
	}
	public Map<String, Object> checkPrivilege(String userName, String password,
			String privielgeName,String privielgeName2) {
		Map<String,Object> map= new HashMap<String , Object>();
		User user =findUserByUserNamePassword(userName, password);
//		map.put("nullUserError", "");// "" 表示用 户不为空
//		map.put("user", user);       // 用  户
//		map.put("forbidden", "");    // "" 表示用户账号未停用
//		map.put("entitle", "");      // "" 表示用户有权限
//		map.put("overdue", "");      // "" 表示用户密码未过期
		if(null!=user){
			//非空用户
			map.put("nullUserError", "");
			map.put("user", user);
			if (null==user.getFlag()||"停用".equals(user.getFlag())) {
				//账号已停用
				map.put("forbidden", "账号已停用，请联系管理员！");
			}else{
				//账号未停用
				map.put("forbidden", "");
				if (checkPrivilege(user, privielgeName)) {
					// 有权限
					map.put("entitle", "");
					if (null != user.getUpdatePasswordTime()) {//密码最后更新时间不为空
						//计算更新的天数
						int updatePasswordTime = 0;
						try {
							updatePasswordTime = DateUtil.getBetweenDays(new Date(),user.getUpdatePasswordTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//判断是否超过 规定天数
						if (!regulationService.isOverTime(updatePasswordTime)) {
							//没超过规定天数
							map.put("overdue", "");
							
						} else {
							//密码过期
							map.put("overdue", "密码已过期，请修改");
						}
					} else {
						//最后修改密码时间为空
						map.put("overdue", "密码已过期，请修改");     
					}
					
				}else{
					// 无权限1
					//判断是否有权限2
					if (checkPrivilege(user, privielgeName2)) {
						// 有权限
						map.put("entitle", "");
						if (null != user.getUpdatePasswordTime()) {//密码最后更新时间不为空
							//计算更新的天数
							int updatePasswordTime = 0;
							try {
								updatePasswordTime = DateUtil.getBetweenDays(new Date(),user.getUpdatePasswordTime());
							} catch (ParseException e) {
								e.printStackTrace();
							}
							//判断是否超过 规定天数
							if (!regulationService.isOverTime(updatePasswordTime)) {
								//没超过规定天数
								map.put("overdue", "");
								
							} else {
								//密码过期
								map.put("overdue", "密码已过期，请修改");
							}
						} else {
							//最后修改密码时间为空
							map.put("overdue", "密码已过期，请修改");     
						}
						
					}else{
						// 无权限
						map.put("entitle", "您未被授权登录该系统");
					}
					//map.put("entitle", "您未被授权登录该系统");
				}
			}
		}else{
			map.put("nullUserError", "用户名或密码错误！");
		}
		return map;
	}



	public void updatePwd(User user, String password) {
		if(null!=user){
			user.setUpdatePasswordTime(new Date());
			getSession().update(user);
			try {
				String userName = user.getUserName();
				Query query = getSession().createSQLQuery("{Call UpdateUserPwd(?,?,1)}");
				query.setParameter(0, userName);
				query.setParameter(1, password);
				query.executeUpdate();
			} catch (Exception e) {
			}
		}
	}



	@SuppressWarnings("unchecked")
	public Map<String, String> getRealName(String departmentid ) {
		List<Object> userName= getSession()
					.createSQLQuery("SELECT  id, realName  FROM tbluser where departmentId = ? ")
					.setParameter(0, departmentid)
					.list();
		 Map<String ,String> nameMap =new HashMap<String , String>();
		 for(Object obj:userName){
			 Object[] objects = (Object[]) obj;
			  nameMap.put((String)objects[0],(String)objects[1]);
		 }
        if(null!=nameMap&&nameMap.size()>0){
	     return nameMap;
        }else{
	      return null;
        }
	}



	@SuppressWarnings("unchecked")
	public String getRealNameByUserName(String username) {
		List<String> userName= getSession().createSQLQuery("SELECT  u.realName  FROM tbluser u where u.userName = ? ").setParameter(0, username).list();
		if(null != userName && userName.size() > 0){
			return userName.get(0);
		}else{
			return null;
		}
	}



	public Map<String, Object> newCheckPrivilege(String userName,
			String password, List<String> privilegeNameList) {
		Map<String,Object> map= new HashMap<String , Object>();
		User user =findUserByUserNamePassword(userName, password);
//		map.put("nullUserError", "");// "" 表示用 户不为空
//		map.put("user", user);       // 用  户
//		map.put("forbidden", "");    // "" 表示用户账号未停用
//		map.put("entitle", "");      // "" 表示用户有权限
//		map.put("overdue", "");      // "" 表示用户密码未过期
		if(null!=user){
			//非空用户
			map.put("nullUserError", "");
			map.put("user", user);
			if (null==user.getFlag()||"停用".equals(user.getFlag())) {
				//账号已停用
				map.put("forbidden", "账号已停用，请联系管理员！");
			}else{
				//账号未停用
				map.put("forbidden", "");
				boolean allNo = true;//全部无权限

				for(int i=0;i<privilegeNameList.size();i++){
					if (checkPrivilege(user, privilegeNameList.get(i))) {
						// 有权限
						allNo = false;
						//map.put("entitle", "");
						if(i == 0){
							map.put("study", "true");
						}else if(i == 1){
							map.put("systemset", "true");
						}else if(i == 2){
							map.put("schedule", "true");
						}else if(i == 3){
							map.put("contract", "true");
						}else if(i==4||i==5){
							map.put("qa", "true");
						}else if(i==6){
							map.put("audit", "true");
						}else if(i==7||i==8){
							map.put("sop", "true");
						}else if(i==9||i==10){
							map.put("varchive", "true");
						}
							
						
					}
				}
				if(!allNo){
					map.put("entitle", "");  
//					String dateStr = DateUtil.getNow("yyyy-MM-dd")+SystemTool.getWindowsMACAddress();
					String ticket="";
					ticket = user.getPassword()+CryptUtils.encryptString(user.getUserName());
					map.put("ticket", ticket);
					if (null != user.getUpdatePasswordTime()) {//密码最后更新时间不为空
						//计算更新的天数
						int updatePasswordTime = 0;
						try {
							updatePasswordTime = DateUtil.getBetweenDays(new Date(),user.getUpdatePasswordTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//判断是否超过 规定天数
						if (!regulationService.isOverTime(updatePasswordTime)) {
							//没超过规定天数
							map.put("overdue", "");
							
						} else {
							//密码过期
							map.put("overdue", "密码已过期，请修改");
						}
					} else {
						//最后修改密码时间为空
						map.put("overdue", "密码已过期，请修改");     
					}
				}else{
					// 无权限
					map.put("entitle", "您未被授权登录该系统");
				}
			}
		}else{
			map.put("nullUserError", "用户名或密码错误！");
		}
		return map;
	}



	@SuppressWarnings("unchecked")
	public Map<String, String> getAllRealName() {
		List<Object> userName= getSession().createSQLQuery("SELECT  id, realName  FROM tbluser ").list();
		 Map<String ,String> nameMap =new HashMap<String , String>();
		 for(Object obj:userName){
			 Object[] objects = (Object[]) obj;
			  nameMap.put((String)objects[0],(String)objects[1]);
		 }
       if(null!=nameMap&&nameMap.size()>0){
	     return nameMap;
       }else{
	      return null;
       }
	}



	public List<?> findUserNameRealNameByPrivilegeName(String privilegeName) {

		if(null==privilegeName  || "".equals(privilegeName)){//参数为空
			return null;
		}
		String sql = 	"	select distinct tbluser.userName,tbluser.realName"+
						"	from dbo.tbluser as tbluser left join dbo.tbl_user_role as ur on tbluser.id = ur.userId "+
						"	left join dbo.tblrole as tblrole on ur.roleId = tblrole.id "+
						"	left join dbo.tbl_role_privilege as rp on tblrole.id = rp.roleId "+
						"	left join dbo.tblprivilege as tblp on rp.privilegeId = tblp.id"+
						"	where tblp.privilegeName = ? and tbluser.flag = '可用'";
		List<?> list =	 getSession().createSQLQuery(sql)
						.setParameter(0, privilegeName)
						.list();
		return list;
	}



	public List<User> findUserListByDepartmentId_1(String departmentId) {
		String sql = "SELECT [id]"+
					" ,[userName]"+
					" ,[userCode]"+
					" ,[realName]"+
					" ,[password]"+
					" ,[userNumber]"+
					" ,[flag]"+
					" ,[flagRemark]"+
					" ,[phone]"+
					" ,[email]"+
					" ,[createTime]"+
					" ,[updatePasswordTime]"+
					" ,[remark]"+
					"  FROM [CoresUserPrivilege].[dbo].[tbluser] "+
					" where departmentId = ? " +
					"  order by id ";
		List<User> userList = null ;
		if(null != departmentId && !"".equals(departmentId)){
			List<?> list = getSession()
							.createSQLQuery(sql)
							.setParameter(0, departmentId)
							.list();
			if(null != list && list.size()>0){
				userList = new ArrayList<User>();
				User user = null ;
				for(Object obj :list){
//					Map<String,Object> map = (Map<String, Object>) obj;
					Object[] objs = (Object[]) obj;
					user = new User();
					user.setId((String)objs[0]);
					user.setUserName((String)objs[1]);
					user.setUserCode((String)objs[2]);
					user.setRealName((String)objs[3]);
					user.setPassword((String)objs[4]);
					user.setUserNumber((String)objs[5]);
					user.setFlag((String)objs[6]);
					user.setFlagRemark((String)objs[7]);
					user.setPhone((String)objs[8]);
					user.setEmail((String)objs[9]);
					user.setCreateTime((Date)objs[10]);
					user.setUpdatePasswordTime((Date)objs[11]);
					user.setRemark((String)objs[12]);
					userList.add(user);
				}
			}
		}
		
		return userList;
	}



	public User getByUserName_1(String userName) {
		String sql = "SELECT [id]"+
				" ,[userName]"+
				" ,[userCode]"+
				" ,[realName]"+
				" ,[password]"+
				" ,[userNumber]"+
				" ,[flag]"+
				" ,[flagRemark]"+
				" ,[phone]"+
				" ,[email]"+
				" ,[createTime]"+
				" ,[updatePasswordTime]"+
				" ,[remark],authorId "+
				"  FROM [CoresUserPrivilege].[dbo].[tbluser] "+
				" where userName = ? " ;
		User user = null ;
		if(null != userName && !"".equals(userName)){
			List<?> list = getSession()
							.createSQLQuery(sql)
							.setParameter(0, userName)
							.list();
			if(null != list && list.size()>0){
					Object[] objs = (Object[]) list.get(0);
					user = new User();
					user.setId((String)objs[0]);
					user.setUserName((String)objs[1]);
					user.setUserCode((String)objs[2]);
					user.setRealName((String)objs[3]);
					user.setPassword((String)objs[4]);
					user.setUserNumber((String)objs[5]);
					user.setFlag((String)objs[6]);
					user.setFlagRemark((String)objs[7]);
					user.setPhone((String)objs[8]);
					user.setEmail((String)objs[9]);
					user.setCreateTime((Date)objs[10]);
					user.setUpdatePasswordTime((Date)objs[11]);
					user.setRemark((String)objs[12]);
					String userId = (String)objs[13];
					User author = null;
					if(null != userId && !"".equals(userId)){
						String realName = getRealNameById(userId);
						author = new User();
						author.setId(userId);
						author.setRealName(realName);
						user.setUser(author);
					}
			}
		}
		
		return user;
	}



	public boolean isIdExist_1(String id) {
		List<?> list = getSession()
				.createSQLQuery("select id from CoresUserPrivilege.dbo.tbluser where id = ? ")//
				.setParameter(0, id)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}



	public boolean isNameExist_1(String userName) {
		User user = getByUserName_1(userName);
		if(null != user){
			return true;
		}
		return false;
	}



	public boolean isRealNameExist_1(String realName) {
		List<?> list = getSession()
				.createSQLQuery("select id from CoresUserPrivilege.dbo.tbluser where realName = ? ")//
				.setParameter(0, realName)//
				.list();
		if (null != list && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}



	@SuppressWarnings("unchecked")
	public String getRealNameById(String id) {
		List<String> userName= getSession().createSQLQuery("SELECT  u.realName  FROM tbluser u where u.id = ? ").setParameter(0, id).list();
		if(null != userName && userName.size() > 0){
			return userName.get(0);
		}else{
			return null;
		}
	}



	@SuppressWarnings("unchecked")
	public String getPartmentNameByUsreName_1(String userName) {
		List<String> departmentNameList= getSession().createSQLQuery("SELECT  d.name  FROM tbluser u left join CoresUserPrivilege.dbo.tbldepartment as d on d.id= u.departmentId   where u.userName = ? ").setParameter(0, userName).list();
		if(null != departmentNameList && departmentNameList.size() > 0){
			return departmentNameList.get(0);
		}else{
			return null;
		}
	}



	public boolean isHasPrivilege(String userId, String privilegeName) {
		String sql = "select count(ur.userId) "+
					" from CoresUserPrivilege.dbo.tbl_user_role as ur"+
					" left join CoresUserPrivilege.dbo.tbl_role_privilege as rp on rp.roleId= ur.roleId"+
					" left join CoresUserPrivilege.dbo.tblprivilege p on rp.privilegeId = p.id"+
					" where ur.userId = ? and p.privilegeName = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, userId)
									.setParameter(1, privilegeName)
									.uniqueResult();
		if(count>0){
			return true;
		}
		return false;
	}
	public boolean isHasPrivilege(String userId, String privilegeName,String roleId) {
		
		String sql = "select count(ur.userId) "+
		" from CoresUserPrivilege.dbo.tbl_user_role as ur"+
		" left join CoresUserPrivilege.dbo.tbl_role_privilege as rp on rp.roleId= ur.roleId"+
		" left join CoresUserPrivilege.dbo.tblprivilege p on rp.privilegeId = p.id"+
		" where ur.userId = ? and p.privilegeName = ?  and ur.roleId != ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
		.setParameter(0, userId)
		.setParameter(1, privilegeName)
		.setParameter(2, roleId)
		.uniqueResult();
		if(count>0){
			return true;
		}
		return false;
	}



	public boolean isHasPrivilege2(List<String> roleNameList,
			String privilegeName) {
		if(null != roleNameList && roleNameList.size()>0){
			String sql = "select count(*) "+
			" from CoresUserPrivilege.dbo.tblrole as r "+
			" left join CoresUserPrivilege.dbo.tbl_role_privilege as rp on r.id = rp.roleId"+
			" left join CoresUserPrivilege.dbo.tblprivilege as p on rp.privilegeId = p.id"+
			" where r.roleName in ( :roleNameList ) and p.privilegeName = :privilegeName ";
			Integer count = (Integer) getSession().createSQLQuery(sql)
			.setParameterList("roleNameList", roleNameList)
			.setParameter("privilegeName", privilegeName)
			.uniqueResult();
			if(count>0){
				return true;
			}
		}
			return false;
	}



	public void addPrivileage(String userId, String privilege) {
		String realName = getRealNameById(userId);
		
		if(null != privilege && privilege.equals("一般毒理_登录")){
			//1.判断用户是否存在，若不存在则插入用户
			insertIntoUser(userId, realName, "", "s");
			
			//2.判断权限是否存在，如存在则放弃
			String sql2 = "select count(*)"+
						" from COMMONDB.dbo.TBUSERROLE ur"+
						" where ur.USERCODE = ? and ur.USERNAME= ? and ur.USERROLE = ? ";
			Integer count = (Integer) getSession().createSQLQuery(sql2)
										.setParameter(0, userId)
										.setParameter(1, realName)
										.setParameter(2, "YYTEST")
										.uniqueResult();
			if(count<1){
				//3.添加权限
				String sql = "insert into "+
				" COMMONDB.dbo.TBUSERROLE (USERCODE,USERNAME,USERROLE )"+
				" values( ?,?, 'YYTEST') ";
				getSession().createSQLQuery(sql)
				.setParameter(0, userId)
				.setParameter(1, realName)
				.executeUpdate();
			}
			
		}else if(null != privilege && privilege.equals("供试品管理_登录")){
			
			//1.判断用户是否存在，若不存在则插入用户
			insertIntoUser(userId, realName, "", "s");
			
			//2.判断权限是否存在，如存在则放弃
			String sql2 = "select count(*)"+
						" from COMMONDB.dbo.TBUSERROLE ur"+
						" where ur.USERCODE = ? and ur.USERNAME= ? and ur.USERROLE = ? ";
			Integer count = (Integer) getSession().createSQLQuery(sql2)
										.setParameter(0, userId)
										.setParameter(1, realName)
										.setParameter(2, "SMPLMANAGER")
										.uniqueResult();
			if(count<1){
				//3.添加权限
				String sql = "insert into "+
				" COMMONDB.dbo.TBUSERROLE (USERCODE,USERNAME,USERROLE )"+
				" values( ?,?, 'SMPLMANAGER') "; 
				getSession().createSQLQuery(sql)
				.setParameter(0, userId)
				.setParameter(1, realName)
				.executeUpdate();
			}
			
			
		}else if(null != privilege && privilege.equals("SD")){
			
			//1.判断用户是否存在，若不存在则插入用户
			insertIntoUser(userId, realName, "", "s");
			//2.根据SD角色
			String sql = "update COMMONDB.dbo.TBUSERINFO set USERLEVEL = 3 "+
						" where USERCODE = ? ";
			getSession().createSQLQuery(sql)
						.setParameter(0, userId)
						.executeUpdate();
		}
		
	}



	public void delPrivileage(String userId, String privilege) {
		String realName = getRealNameById(userId);
		if(null != privilege && privilege.equals("一般毒理_登录")){
			String sql = "delete "+
						" from COMMONDB.dbo.TBUSERROLE "+
						" where USERCODE = ? and USERNAME = ? and USERROLE = 'YYTEST' "; 
			getSession().createSQLQuery(sql)
						.setParameter(0, userId)
						.setParameter(1, realName)
						.executeUpdate();
			
		}else if(null != privilege && privilege.equals("供试品管理_登录")){
			String sql = "delete "+
						" from COMMONDB.dbo.TBUSERROLE "+
						" where USERCODE = ? and USERNAME = ? and USERROLE = 'SMPLMANAGER' "; 
			getSession().createSQLQuery(sql)
						.setParameter(0, userId)
						.setParameter(1, realName)
						.executeUpdate();
		}else if(null != privilege && privilege.equals("SD")){
			
			String sql = "update COMMONDB.dbo.TBUSERINFO set USERLEVEL = 0 "+
						" where USERCODE = ? ";
			getSession().createSQLQuery(sql)
						.setParameter(0, userId)
						.executeUpdate();
		}
		
		
		
	}



	public List<?> findUserNameRealNameByRoleId(String roleId) {
		if(null==roleId  || "".equals(roleId)){//参数为空
			return null;
		}
		String sql = 	"select distinct tbluser.userName,tbluser.realName"+
						" from dbo.tbluser as tbluser left join dbo.tbl_user_role as ur on tbluser.id = ur.userId "+
						" where ur.roleId = ? and tbluser.flag = '可用'";
		List<?> list =	 getSession().createSQLQuery(sql)
						.setParameter(0, roleId)
						.list();
		return list;
	}



	public void insertIntoUser(String userName, String realName,
			String departmentName, String password) {
		if(null != userName){
			//判断 用户是否 存在
			String sql1 = "select count(*)"+
			" from COMMONDB.dbo.TBUSERINFO as u"+
			" where u.USERCODE = ? ";
			Integer count = (Integer) getSession().createSQLQuery(sql1).setParameter(0, userName).uniqueResult();
			
			if(count<1){
				if(null == departmentName || "".equals(departmentName)){
					departmentName = getPartmentNameByUsreName_1(userName);
				}
				// 另一系统同步
				String sql = "insert into COMMONDB.dbo.TBUSERINFO(USERID,USERCODE,USERNAME,USERDEP) values(?,?,?,?)";
				getSession().createSQLQuery(sql)
				.setParameter(0, userName)
				.setParameter(1,userName)
				.setParameter(2, realName)
				.setParameter(3, departmentName)
				.executeUpdate();
				Query query = getSession().createSQLQuery("{Call UpdateUserPwd(?,?,1)}");
				query.setParameter(0, userName);
				query.setParameter(1, password);
				query.executeUpdate();
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserListByDepartmentId(String departmentId) {
		
		Department department = new Department(); 
		department.setId(departmentId);
		List<User> userList= getSession()
		.createQuery("FROM User where department = ? ")
								.setParameter(0, department)
				.list();
		
		return userList;
	}



	@SuppressWarnings("unchecked")
	public List<String> findPrivilegeUrlListByUserId(String userId) {
		String sql =" select distinct p.privilegePath"+
					" from CoresUserPrivilege.dbo.tbl_user_role as ur "+
					" left join CoresUserPrivilege.dbo.tbl_role_privilege as rp "+
					" on ur.roleId = rp.roleId  left join CoresUserPrivilege.dbo.tblprivilege p "+
					" on rp.privilegeId = p.id "+
					" where ur.userId = ? and p.privilegePath is not null and p.privilegePath != '' ";
		
		List<String> privilegeUrlList = getSession().createSQLQuery(sql)
													.setParameter(0, userId)
													.list();
		
		return privilegeUrlList;
	}



	@SuppressWarnings("unchecked")
	public List<String> findUserNameByPrivilegeName(String privilegeName) {
		if(null==privilegeName  || "".equals(privilegeName)){//参数为空
			return null;
		}
		String sql = 	"	select distinct tbluser.userName"+
						"	from dbo.tbluser as tbluser left join dbo.tbl_user_role as ur on tbluser.id = ur.userId "+
						"	left join dbo.tblrole as tblrole on ur.roleId = tblrole.id "+
						"	left join dbo.tbl_role_privilege as rp on tblrole.id = rp.roleId "+
						"	left join dbo.tblprivilege as tblp on rp.privilegeId = tblp.id"+
						"	where tblp.privilegeName = ? and tbluser.flag = '可用'";
		List<String> list =	 getSession().createSQLQuery(sql)
						.setParameter(0, privilegeName)
						.list();
		return list;
	}



	public String getUserNameByRealName(String studydirector) {
		String userName = "";
		String sql = "select tu.userName"+
						" from CoresUserPrivilege.dbo.tbluser as tu"+
						" where tu.realName = ? ";
		userName = (String) getSession().createSQLQuery(sql).setParameter(0, studydirector).uniqueResult();
		return userName;
	}



	
	
	
	
}
