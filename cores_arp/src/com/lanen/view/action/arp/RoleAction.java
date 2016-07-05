package com.lanen.view.action.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Privilege;
import com.lanen.model.Role;
import com.lanen.model.RolePrivilege;
import com.lanen.model.Security_er;
import com.lanen.service.arp.RoleService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private RoleService roleService;

	private String rows;
	private String page;

	private String roleid;
	private String privileges;
	private List<Map<String,Object>> listRolesPrivilege;
	private List<Privilege> listAllPrivilege;
	private String privilegeIds;
	public String list() {
		return "roleList";
	}

	public void loadList() {
		Map<String, Object> map = roleService.getRoles(page, rows,model.getName());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Role l = new Role();
		if (model.getId() != null && !"".equals(model.getId())) {
			l.setId(model.getId());
			l.setName(model.getName());
			l.setDescription(model.getDescription());
			roleService.save(l);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delRole() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Role i = roleService.getById(model.getId());
			roleService.update(i);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Role r = roleService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(r);
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Role r = roleService.getById(model.getId());
			
			r.setId(model.getId());
			r.setName(model.getName());
			r.setDescription(model.getDescription());
			roleService.update(r);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", r.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public String listModuleAndPrivilege(){
		String reportPrivilege=roleService.getPrivilegeByModule("Report");//报表
		String breedingPrivilege=roleService.getPrivilegeByModule("Breeding");//饲养
		String reproductionPrivilege=roleService.getPrivilegeByModule("Reproduction");//繁殖
		String quarantinePrivilege=roleService.getPrivilegeByModule("Quarantine");//防疫
		String treatmentPrivilege=roleService.getPrivilegeByModule("Treatment");//治疗
		String sysPrivilege=roleService.getPrivilegeByModule("Sys");//系统
		
		List<Map<String,Object>> reportPrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(reportPrivilege)&&reportPrivilege!=null){
			String[]report=reportPrivilege.split(",");
			for(int i=0;i<report.length;i++){
				Map<String,Object> reportPrivilegeNameMap=new HashMap<String,Object>(); 
				String str=report[i];
				//如果该功能组下无权限
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String reportPrivilegeName = roleService
							.getPrivilegeById(id);
					reportPrivilegeNameMap.put("reportPrivilegeName",
							reportPrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					reportPrivilegeNameMap.put("reportPrivilegeFlag", flag);
				}
				reportPrivilegeNameList.add(reportPrivilegeNameMap);
			}
		}
		//将饲养管理权限装进Map
		List<Map<String,Object>> breedingPrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(breedingPrivilege) && breedingPrivilege!=null){
			String[]breeding=breedingPrivilege.split(",");
			for(int i=0;i<breeding.length;i++){
				Map<String,Object> breedingPrivilegeNameMap=new HashMap<String,Object>(); 
				String str=breeding[i];
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String breedingPrivilegeName = roleService
							.getPrivilegeById(id);
					breedingPrivilegeNameMap.put("breedingPrivilegeName",
							breedingPrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					breedingPrivilegeNameMap.put("reportPrivilegeFlag", flag);
				}
				breedingPrivilegeNameList.add(breedingPrivilegeNameMap);
			}
		}
		//将繁殖管理权限装进Map
		List<Map<String,Object>> reproductionPrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(reproductionPrivilege) && reproductionPrivilege!=null){
			String[]reproduction=reproductionPrivilege.split(",");
			for(int i=0;i<reproduction.length;i++){
				Map<String,Object> reproductionPrivilegeNameMap=new HashMap<String,Object>(); 
				String str=reproduction[i];
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String reproductionPrivilegeName = roleService
							.getPrivilegeById(id);
					reproductionPrivilegeNameMap.put(
							"reproductionPrivilegeName",
							reproductionPrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					reproductionPrivilegeNameMap.put("reportPrivilegeFlag",
							flag);
				}
				reproductionPrivilegeNameList.add(reproductionPrivilegeNameMap);
			}
		}
		//将防疫管理权限装进Map
		List<Map<String,Object>> quarantinePrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(quarantinePrivilege) && quarantinePrivilege!=null){
			String[]quarantine=quarantinePrivilege.split(",");
			for(int i=0;i<quarantine.length;i++){
				Map<String,Object> quarantinePrivilegeNameMap=new HashMap<String,Object>(); 
				String str=quarantine[i];
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String quarantinePrivilegeName = roleService
							.getPrivilegeById(id);
					quarantinePrivilegeNameMap.put("quarantinePrivilegeName",
							quarantinePrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					quarantinePrivilegeNameMap.put("reportPrivilegeFlag", flag);
				}
				quarantinePrivilegeNameList.add(quarantinePrivilegeNameMap);
			}
		}
		//将治疗管理权限装进Map
		List<Map<String,Object>> treatmentPrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(treatmentPrivilege) && treatmentPrivilege!=null){
			String[]treatment=breedingPrivilege.split(",");
			for(int i=0;i<treatment.length;i++){
				Map<String,Object> treatmentPrivilegeNameMap=new HashMap<String,Object>(); 
				String str=treatment[i];
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String treatmentPrivilegeName = roleService
							.getPrivilegeById(id);
					treatmentPrivilegeNameMap.put("treatmentPrivilegeName",
							treatmentPrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					treatmentPrivilegeNameMap.put("reportPrivilegeFlag", flag);
				}
				treatmentPrivilegeNameList.add(treatmentPrivilegeNameMap);
			}
		}
		//将系统管理权限装进Map
		List<Map<String,Object>> sysPrivilegeNameList=new ArrayList<Map<String,Object>>();
		if(!"".equals(sysPrivilege) && sysPrivilege!=null){
			String[]sys=breedingPrivilege.split(",");
			for(int i=0;i<sys.length;i++){
				Map<String,Object> sysPrivilegeNameMap=new HashMap<String,Object>(); 
				String str=sys[i];
				if (!"".equals(str)&&str!=null) {
					Long id = Long.parseLong(str);
					String sysPrivilegeName = roleService.getPrivilegeById(id);
					sysPrivilegeNameMap.put("sysPrivilegeName",
							sysPrivilegeName);
					//判断该角色是否有该权限
					boolean flag = roleService.checkRolePrivilegeById(roleid,
							id);
					sysPrivilegeNameMap.put("reportPrivilegeFlag", flag);
				}
				sysPrivilegeNameList.add(sysPrivilegeNameMap);
			}
		}
		ActionContext.getContext().put("reportPrivilegeNameList", reportPrivilegeNameList);
		ActionContext.getContext().put("breedingPrivilegeNameList", breedingPrivilegeNameList);
		ActionContext.getContext().put("reproductionPrivilegeNameList", reproductionPrivilegeNameList);
		ActionContext.getContext().put("quarantinePrivilegeNameList", quarantinePrivilegeNameList);
		ActionContext.getContext().put("treatmentPrivilegeNameList", treatmentPrivilegeNameList);
		ActionContext.getContext().put("sysPrivilegeNameList", sysPrivilegeNameList);
		
		return "privilege";
	}
	//角色下拉选矿
	public void loadListRoles() {
		List<Map<String, String>> mapList = roleService
				.getAllRoles();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	public void loadListRoleById(){
		List<?> list=roleService.getRolesById(model.getId().toString());
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		for(Object ob:list){
			Map<String,Object> map=new HashMap<String,Object>();
			Object [] objs=(Object[])ob;
			map.put("rolename", objs[3]);
			map.put("roleid", objs[2]);
			map.put("id", objs[0]);
			l.add(map);
		}
		String json=JsonPluginsUtil.beanListToJson(l);
		writeJson(json);
	}
	public void saveRole(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && !"".equals(model.getId())) {
			List<?> list = roleService.getRolesById(model.getId().toString());
			for(Object ob:list){
				Security_er er=new Security_er();
				Object[]objs=(Object[])ob;
				er.setRole_id(Integer.parseInt(objs[2]+""));
				er.setEmployee_id(objs[1]+"");
				roleService.updateRoleById(er);
			}

			map.put("success", true);
			map.put("msg", "编辑成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 授权
	 * @return
	 */
	public void loadListPrivilege(){//权限直接从页面加进去.
		if(!"".equals(model.getId())&&model.getId()!=null){
			Role role=roleService.getById(Long.valueOf(model.getId()));
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name", role.getName());
			map.put("description", role.getDescription());
			map.put("id", role.getId());
			
			//获取该角色的权限.
			List<?> listRolesPrivileges=roleService.getPrivilegeByRoleid(role.getId().toString());
			listRolesPrivilege=new ArrayList<Map<String,Object>>();
			for(Object ob:listRolesPrivileges){
				Map<String,Object> m=new HashMap<String,Object>();
				Object[]objs=(Object[])ob;
				m.put("roleid", objs[0]);
				m.put("privilegeid", objs[1]);
				listRolesPrivilege.add(m);
			}
			//获取所有权限功能.
			listAllPrivilege =roleService.getAllPrivileges();
			String strjson=JsonPluginsUtil.beanToJson(map);
			writeJson(strjson);
		}
	}
	public void saveRolesPrivilege(){
		Map<String,Object> map=new HashMap<String,Object>();
		String[]str1=privilegeIds.split(",");
		if(model.getId()!=null&&!"".equals(model.getId())){
			//
			Role r=(Role)roleService.getRoleByIds(Long.valueOf(model.getId())).get(0);
			
			//r.setPrivileges(null);
			String privileges="";
			if(request.getParameter("zl")!=null&&!"".equals(request.getParameter("zl"))){
				privileges=request.getParameter("zl")+",";
			}if(request.getParameter("bb")!=null&&!"".equals(request.getParameter("bb"))){
				privileges=privileges+request.getParameter("bb")+",";
			}if(request.getParameter("sy")!=null&&!"".equals(request.getParameter("sy"))){
				privileges=privileges+request.getParameter("sy")+",";
			}if(request.getParameter("fz")!=null&&!"".equals(request.getParameter("fz"))){
				privileges=privileges+request.getParameter("fz")+",";
			}if(request.getParameter("fy")!=null&&!"".equals(request.getParameter("fy"))){
				privileges=privileges+request.getParameter("fy")+",";
			}if(request.getParameter("zl1")!=null&&!"".equals(request.getParameter("zl1"))){
				privileges=privileges+request.getParameter("zl1")+",";
			}if(request.getParameter("xt")!=null&&!"".equals(request.getParameter("xt"))){
				privileges=privileges+request.getParameter("xt")+",";
			}
			
			roleService.deleteRolePrivileges(model.getId()+"");
		//	String [] str=privileges.split(",");
		//	for(int i=0;i<str.length;i++){
				/*Privilege p=new Privilege();
				p.setId(Long.parseLong((String)str[i]));
				p.getRoles().remove(r);
				r.getPrivileges().remove(p);
				r.getPrivileges().add(p);
				roleService.update(r);*/
				
		//		RolePrivilege rp=new RolePrivilege();
				
		//		roleService.insertRolePrivileges(model.getId()+"",str[i]+"");
		//	}
			for(int i=0;i<str1.length;i++){
				/*Privilege p=new Privilege();
				p.setId(Long.parseLong((String)str[i]));
				p.getRoles().remove(r);
				r.getPrivileges().remove(p);
				r.getPrivileges().add(p);
				roleService.update(r);*/
				
				RolePrivilege rp=new RolePrivilege();
				
				roleService.insertRolePrivileges(model.getId()+"",str1[i]+"");
			}
			map.put("success", true);
			map.put("msg", "授权成功");
			map.put("id", model.getId());
		}
		String strjson=JsonPluginsUtil.beanToJson(map);
		writeJson(strjson);
	}
	public void loadListPrivilege1(){
		Role role=roleService.getById(Long.valueOf(model.getId()));
		List<?> listSysPrivilege=roleService.getSysPrivilege();
		List<Map<String,Object>> map=new ArrayList<Map<String,Object>>();
		for(Object ob:listSysPrivilege){
			Object[]objs=(Object[])ob;
			Map m=new HashMap<String,Object>();
			m.put("id", objs[0]);
			m.put("name", objs[1]);
			m.put("description", objs[2]);
			boolean b=roleService.checkRolePrivilege(role.getId()+"", objs[0]+"");
			m.put("ck", b);
			map.add(m);
		}
		String json=JsonPluginsUtil.beanListToJson(map);
		writeJson(json);
	}
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public List<Map<String,Object>> getListRolesPrivilege() {
		return listRolesPrivilege;
	}

	public void setListRolesPrivilege(List<Map<String,Object>> listRolesPrivilege) {
		this.listRolesPrivilege = listRolesPrivilege;
	}

	public List<Privilege> getListAllPrivilege() {
		return listAllPrivilege;
	}

	public void setListAllPrivilege(List<Privilege> listAllPrivilege) {
		this.listAllPrivilege = listAllPrivilege;
	}

	public String getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
