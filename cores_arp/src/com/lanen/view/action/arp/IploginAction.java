package com.lanen.view.action.arp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Iplogin;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IploginService;

@Controller
@Scope("prototype")
public class IploginAction extends BaseAction<Iplogin> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854106155603814530L;
	@Resource
	private IploginService iploginService;
	@Resource
	private EmployeeService employeeService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	private String username;
	private String start;
	private String end;
	public String list() {
		return "list";
	}

	@SuppressWarnings("unchecked")
	public void loadList() {
		Long id=getEmployeeId(username);
		String strId=null;
		if(!"".equals(id)&&id!=null){
			strId=String.valueOf(id);
		}
		Map<String, Object> map = iploginService.getListByConditions(page, rows, strId,start, end);

		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd HH:mm:ss");
		writeJson(jsonStr);
	}
	/**
	 * 根据员工id查询员工名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmployeeName(Long id) {
		String name = null;
		if (id != null) {
			Employee e = employeeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}

	/**
	 * 根据员工name查询员id
	 * 
	 * @param id
	 * @return
	 */
	public Long getEmployeeId(String name) {
		Long id=null;
		if (name != null&&name!=null) {
			Employee e = employeeService.getUserByUserName(username);
			if (e != null) {
				id = e.getId();
			}
		}
		return id;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
