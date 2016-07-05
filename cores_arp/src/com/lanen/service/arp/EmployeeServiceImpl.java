package com.lanen.service.arp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Employee;
import com.lanen.model.Privilege;
import com.lanen.model.Role;

@Service
public class EmployeeServiceImpl extends BaseLongDaoImpl<Employee> implements EmployeeService {
    //查询当前所有未删除的用户
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees(String condition) {
		List<Employee> list=null;
		String hql="FROM Employee where deleted!=-1 ";
		if(condition!=null&&!"".equals(condition)){
				hql=hql+" and name like :condition or employeeid like :condition";
				hql = hql +"  ORDER BY ( CASE WHEN description != '离职'   THEN  1 WHEN description = '离职' THEN 2 END) ";
				list=getSession().createQuery(hql).setParameter("condition","%"+condition+"%").list();
		
		}else{
			hql = hql +"  ORDER BY ( CASE WHEN description != '离职'   THEN  1 WHEN description = '离职' THEN 2 END) ";
			list=getSession().createQuery(hql).list();
		}
		
		return list;
	}
	
	  //根据名称查询实体
	@SuppressWarnings("unchecked")
	public Employee getByUserName(String userName) {
		List<Employee> list = getSession().createQuery(" FROM Employee u WHERE u.userid = ? ")//
		.setParameter(0, userName)//
		.list();
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}



//	public Employee getById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	public void updatePwd(Employee user) {
		// TODO Auto-generated method stub
		
	}
	//表名小写.
	public List<Map<String, String>> getAllEmployeesMap() {
		String sql="SELECT ID,NAME FROM employee WHERE deleted!=-1 ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		map = new HashMap<String, String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		mapList.add(map);
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}
	//小写.
	public List<Map<String, String>> getAllEmployeesMapNo() {
		String sql="SELECT ID,NAME FROM employee WHERE deleted!=-1 ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}

	public boolean isExistEmployeeid(String employeeid) {
		if(null != employeeid){
			List<?> list = getSession().createQuery(" From Employee where employeeid = :employeeid and deleted!=-1 ")
						.setParameter("employeeid", employeeid)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	//判断该用户是否有该权限
	public  boolean checkPrivilege(Employee user,String privielgeName){
		//判断用户是否为空
		if(null==user){//为空直接   返回 false
			return false;
		}else{
//			//超级管理员  ，直接返回 true
			if("admin".equals(user.getName())||"administrator".equals(user.getName())){
				return true;
			}
		}
		//权限名称  为空   或者  为""   不用验证权限
		if(null==privielgeName || privielgeName.equals("")){
			return true;
		}
		
		
		//return isHasPrivilege(user.getId(),privielgeName);
		return isHasPrivilege(user.getEmployeeid(),privielgeName);

	}
	public boolean isHasPrivilege(String userId, String privilegeName) {
		String sql = "select count(er.employee_id) "+
					" from security_er as er"+
					" left join role_privilege as rp on rp.roleId= er.role_id"+
					" left join sys_function p on rp.privilegeId = p.id"+
					" where er.employee_id = ? and p.name = ? ";
		//String sql = "select count(ur.userId) "+
		//" from CoresUserPrivilege.dbo.tbl_user_role as ur"+
		///" left join CoresUserPrivilege.dbo.tbl_role_privilege as rp on rp.roleId= ur.roleId"+
		//" left join CoresUserPrivilege.dbo.tblprivilege p on rp.privilegeId = p.id"+
		//" where ur.userId = ? and p.privilegeName = ? ";
		Query query=getSession().createSQLQuery(sql);
		BigInteger count = (BigInteger) query.setParameter(0, userId).setParameter(1, privilegeName).list().get(0);
		if(count.intValue()>0){
			return true;
		}
		return false;
	}
	
	public List<String> findPrivilegeUrlListByUserId(String userId) {
		String sql =" select distinct p.url"+
					" from security_er as er "+
					" left join role_privilege as rp "+
					" on er.role_id = rp.roleId  left join sys_function p "+
					" on rp.privilegeId = p.id "+
					" where er.employee_id = ? and p.url is not null and p.url != '' ";
		
		List<String> privilegeUrlList = getSession().createSQLQuery(sql)
													.setParameter(0, userId)
													.list();
		
		return privilegeUrlList;
	}
	public String getRolename(Integer id){
		String sql="select name from security_role where id=?";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		return (String)query.list().get(0);
	}
	
	public Map<String, Object> newCheckPrivilege(String userid,
			String password, List<String> privilegeNameList) {
		Map<String,Object> map= new HashMap<String , Object>();
		Employee user =findUserByUserNamePassword(userid, password);
//		map.put("nullUserError", "");// "" 表示用 户不为空
//		map.put("user", user);       // 用  户
//		map.put("forbidden", "");    // "" 表示用户账号未停用
//		map.put("entitle", "");      // "" 表示用户有权限
//		map.put("overdue", "");      // "" 表示用户密码未过期
		if(null!=user){
			//非空用户
			map.put("nullUserError", "");
			map.put("user", user);
//			if (null==user.getFlag()||"停用".equals(user.getFlag())) {
				//账号已停用
//				map.put("forbidden", "账号已停用，请联系管理员！");
//			}else{
				//账号未停用
				map.put("forbidden", "");
				boolean allNo = true;//全部无权限
				for(int i=0;i<7;i++){
					if (checkPrivilege(user, privilegeNameList.get(i))) {
						// 有权限
						allNo = false;
						//map.put("entitle", "");
						if(i == 0){
							map.put("zonglSet", "true");
						}else if(i == 1){
							map.put("reportset", "true");	
						}else if(i==2){
							map.put("siySet", "true");
						}else if(i==3){
							map.put("fanzSet", "true");
						}else if(i==4){
							map.put("fangySet", "true");
						}else if(i==5){
							map.put("zhilSet", "true");
						}else if(i==6){
							map.put("systemset", "true");
						}
				}
				if(!allNo){
					map.put("entitle", "");  
//					String dateStr = DateUtil.getNow("yyyy-MM-dd")+SystemTool.getWindowsMACAddress();
					String ticket="";
//					ticket = user.getPassword()+CryptUtils.encryptString(user.getUserName());
//					map.put("ticket", ticket);
					/*if (null != user.getUpdatePasswordTime()) {//密码最后更新时间不为空
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
					}*/
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
	
	public Employee findUserByUserNamePassword(String userid, String password) {
		return (Employee) getSession().createQuery("FROM Employee u WHERE u.userid = ? AND u.password = ? ")//
		.setParameter(0, userid)//
		.setParameter(1, password)//.setParameter(1, DigestUtils.md5Hex(password))//// 要使用MD5的摘要
		.uniqueResult();
	}
	//根据名称查询实体
	@SuppressWarnings("unchecked")
	public Employee getUserByUserName(String name) {
		List<Employee> list = getSession().createQuery(" FROM Employee u WHERE u.name = ? ")//
		.setParameter(0, name)//
		.list();
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Employee> findByPrivilegeName(String privilegeName) {
		String sql="select id,name,userid,password from employee where role_id in(select roleId from role_privilege where privilegeId=(select id from sys_function where name=:privilegeName))";
		List<?> list=getSession().createSQLQuery(sql).setParameter("privilegeName", privilegeName).list();
		List<Employee> listUser=new ArrayList<Employee>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Employee user=new Employee();
			//user.setId((Long)objs[0]);
			user.setName((String)objs[1]);
			user.setUserid((String)objs[2]);
			user.setPassword((String)objs[3]);
			listUser.add(user);
		}
		return listUser;
	}

	public Map<String, Object> checkPrivilege(String userName, String password,
			String privilegeName) {
		Map<String,Object> map=new HashMap<String,Object>();
		
		Employee user =findUserByUserNamePassword(userName, password);
		
		if(user!=null){
			map.put("nullUserError", "");
			map.put("user", user);
			
			map.put("overdue","");//账号过期没做设置.
			if(user.getDeleted()==1){
				map.put("forbidden", "账号已停用，请联系管理员！");
			}else{
				map.put("forbidden", "");
				boolean b=checkPrivilege(user, privilegeName);
				if(b){
					map.put("entitle", "");
				}else{
					map.put("entitle", "您未被授权登录该系统");
				}
			}
			
		}else{
			map.put("nullUserError", "用户名或密码错误！");
		}
		return map;
		/*String sql="select id,employeeid,name,userid,password from Employee where role_id in(select roleId from role_privilege where privilegeId=(select id from sys_function where name=:privilegeName))" +
				" and userid=:userName and password=:password";
		List<?> list=getSession().createSQLQuery(sql).setParameter("privilegeName", privilegeName).setParameter("userName", userName).setParameter("password", password).list();
		List<Employee> listUser=new ArrayList<Employee>();
		for(Object ob:list){
			Object[]objs=(Object[])ob;
			Employee user=new Employee();
			//user.setId((Long)objs[0]);
			user.setEmployeeid((String)objs[1]);
			user.setName((String)objs[2]);
			user.setUserid((String)objs[3]);
			user.setPassword((String)objs[4]);
			listUser.add(user);
		}
		if(listUser.size()>0){
			map.put("nullUserError", "");
			map.put("user", listUser.get(0));
			if(listUser.get(0).getDeleted()==1){
				map.put("forbidden", "禁用");
			}else{
				map.put("forbidden", "");
				map.put("entitle", "");
				map.put("overdue","");
			}
		}else{
			map.put("entitle", "无权限");
		}
		return map;*/
	}

	public void updatePwd(Employee user, String newpassword) {
		if (user!=null) {
			String sql = "update employee set password=? where userid=? and employeeid=?";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter(0, newpassword);
			query.setParameter(1, user.getUserid());
			query.setParameter(2, user.getEmployeeid());
			query.executeUpdate();
		}
	}
	public Map<String,Object> getAllEmployee(){
			String sql="select id,name from employee where deleted!=-1 ";
			Query query = getSession().createSQLQuery(sql);
			List<?> list = query.list();
			Map<String, Object> mapList = new HashMap<String, Object>();
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				mapList.put((String)objs[1], objs[0]);
			}
			return mapList;
	}

	public List<?> getRoleByEmployeeId(String userid) {
		String sql="select employee_id,role_id from security_er ";
		if(!"".equals(userid)&&userid!=null){
			sql=sql+" where employee_id=:employeeid";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(userid)&&userid!=null){
			query.setParameter("employeeid", userid);
		}
		return (List<?>)query.list();
	}
	public List<Role> getAllRole(){
		String hql="From Role";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
	public boolean checkEmployeeRole(String employeeid, String roleid) {
		String sql="select count(*) from security_er where 1=1 ";
		if(!"".equals(roleid)&&roleid!=null){
			sql=sql+" and role_id=:roleid";
		}
		if(!"".equals(employeeid)&&employeeid!=null){
			sql=sql+" and employee_id=:employeeid";
		}
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(roleid)&&roleid!=null){
			query.setParameter("roleid", roleid);
		}
		if(!"".equals(employeeid)&&employeeid!=null){
			query.setParameter("employeeid", employeeid);
		}
		BigInteger i=(BigInteger) query.list().get(0);
		if(i.intValue()>0){
			return true;
		}
		return false;
	}
}