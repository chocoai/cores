package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Leavebreast;
import com.lanen.model.Yzc;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.YzcService;

@Controller
@Scope("prototype")
public class YzcAction extends BaseAction<Yzc> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854106155603814530L;
	@Resource
	private YzcService yzcService;
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
			strId=id.toString();
		}
		Map<String, Object> map = yzcService.getListByConditions(page, rows, model.getYzcmane());

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
		Long id = null;
		if (id != null) {
			Employee e = employeeService.getByUserName(name);
			if (e != null) {
				id = e.getId();
			}
		}
		return id;
	}
	public void add(){
		Map<String,Object> map=new HashMap<String,Object>();
		Yzc y=new Yzc();
		if(!"".equals(model.getYzcmane())&&model.getYzcmane()!=null){
			
			y.setYzcmane(model.getYzcmane());
			y.setXxdz(model.getXxdz());
			y.setDwxz(model.getDwxz());
			y.setFzr(model.getFzr());
			y.setFrdb(model.getFrdb());
			y.setYzbm(model.getYzbm());
			y.setLxdh(model.getLxdh());
			y.setZjrs(model.getZjrs());
			y.setJsrs(model.getJsrs());
			y.setSyyrs(model.getSyyrs());
			y.setSyrs(model.getSyrs());
			y.setJcrs(model.getJcrs());
			y.setHqrs(model.getHqrs());
			y.setGlrs(model.getGlrs());
			y.setZgzrs(model.getZgzrs());
			y.setJzmj(model.getJzmj());
			y.setSymj(model.getSymj());
			y.setSys(model.getSys());
			y.setJyf(model.getJyf());
			y.setSlf(model.getSlf());
			y.setSyf(model.getSyf());
			y.setYqsb(model.getYqsb());
			y.setDeleted(0);
			yzcService.save(y);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", y.getId());
			
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void toEdit(){
		if (model.getId() != null) {
			Yzc d = yzcService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}
	public void editSave(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(model.getId())&&model.getId()!=null){
			Yzc y=yzcService.getById(model.getId());
			y.setYzcmane(model.getYzcmane());
			y.setXxdz(model.getXxdz());
			y.setDwxz(model.getDwxz());
			y.setFzr(model.getFzr());
			y.setFrdb(model.getFrdb());
			y.setYzbm(model.getYzbm());
			y.setLxdh(model.getLxdh());
			y.setZjrs(model.getZjrs());
			y.setJsrs(model.getJsrs());
			y.setSyyrs(model.getSyyrs());
			y.setSyrs(model.getSyrs());
			y.setJcrs(model.getJcrs());
			y.setHqrs(model.getHqrs());
			y.setGlrs(model.getGlrs());
			y.setZgzrs(model.getZgzrs());
			y.setJzmj(model.getJzmj());
			y.setSymj(model.getSymj());
			y.setSys(model.getSys());
			y.setJyf(model.getJyf());
			y.setSlf(model.getSlf());
			y.setSyf(model.getSyf());
			y.setYqsb(model.getYqsb());
			yzcService.update(y);
			map.put("success", true);
			map.put("msg", "修改成功");
			map.put("id", y.getId());
			
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	public void delYzc(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(model.getId())&&model.getId()!=null){
			Yzc y=yzcService.getById(model.getId());
			y.setDeleted(1);
			yzcService.update(y);
			
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
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
