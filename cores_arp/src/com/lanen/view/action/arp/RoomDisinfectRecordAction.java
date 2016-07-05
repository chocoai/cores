package com.lanen.view.action.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Area;
import com.lanen.model.Disinfectant;
import com.lanen.model.Employee;
import com.lanen.model.RoomDisinfectRecord;
import com.lanen.model.RoomDisinfectRecord_Json;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.DisinfectantService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.RoomDisinfectRecordService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoomDisinfectRecordAction extends BaseAction<RoomDisinfectRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5147650047187247611L;
	@Resource
	private RoomDisinfectRecordService roomDisinfectRecordService;
	@Resource
	private AreaService areaService;
	@Resource
	private DisinfectantService disinfectantService;
	@Resource
	private EmployeeService employeeService;

	private String page;
	private String rows;

	public String list() {
		return "list";

	}

	// 加载主页面数据
	@SuppressWarnings("unchecked")
	public void loadList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoomDisinfectRecord> list = new ArrayList<RoomDisinfectRecord>();
		Area a = new Area();
		Long area_id = null;
		String roomname = model.getDisinfectType();
		Date disDate = model.getDisinfectDate();
		if (roomname != null && !"".equals(roomname)) {
			a = areaService.getAreaByName(roomname);
			if (a != null) {
				map = roomDisinfectRecordService.getlistByRoomIdDisDate(page,
						rows, a.getId(), disDate);
				list = (List<RoomDisinfectRecord>) map.get("rows");
			}
		} else {
			area_id = 0L;
			map = roomDisinfectRecordService.getlistByRoomIdDisDate(page, rows,
					area_id, disDate);
			list = (List<RoomDisinfectRecord>) map.get("rows");
		}
		// list=roomDisinfectRecordService.findAll();
		List<RoomDisinfectRecord_Json> list2 = new ArrayList<RoomDisinfectRecord_Json>();
		if (list != null && list.size() > 0) {
			for (RoomDisinfectRecord r : list) {
				RoomDisinfectRecord_Json json = new RoomDisinfectRecord_Json();
				json.setId(r.getId());
				Long areaId = r.getArea_id();
				Area area = areaService.getById(areaId);
				if (area != null) {
					String roomName = area.getAreaname();
					json.setRoomname(roomName);
					Long Pid = area.getBlongarea();
					if (areaService.getById(Pid) != null) {
						json.setAreaname(areaService.getById(Pid).getAreaname());
					}
				}
				Long disinfectantId = r.getDisinfectant_id();
				Disinfectant disinfectant = disinfectantService
						.getById(disinfectantId);
				if (disinfectant != null) {
					json.setDisinfectantCode(disinfectant.getDisinfectantCode());
				}
				json.setDisinfectDate(r.getDisinfectDate());
				Long operatorId = r.getOperator_id();
				String operator = getEmployeeName(operatorId);// 根据员工ID查找员工姓名
				json.setOperator(operator);

				json.setDisinfectType(r.getDisinfectType());
				Long recorderId = r.getRecorder_id();
				String recorder = getEmployeeName(recorderId); // 根据员工ID查找员工姓名
				json.setRecorder(recorder);
				list2.add(json);
			}
		}

		Map<String, Object> map2 = new HashMap<String, Object>();
		Integer total = (Integer) map.get("total");
		map2.put("rows", list2);
		map2.put("total", total);
		String jsonStr = JsonPluginsUtil.beanToJson(map2, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	// 根据员工id查询员工名
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
	 * 加载已有的消毒方式
	 */
	public void loadListDisinfectType() {
		List<Map<String, Object>> list = roomDisinfectRecordService
				.loadAllDisinfectType();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 添加房间消毒记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getArea_id() != null && model.getDisinfectant_id() != null) {

			RoomDisinfectRecord r = new RoomDisinfectRecord();
			r.setArea_id(model.getArea_id());
			r.setDisinfectant_id(model.getDisinfectant_id());
			r.setDisinfectDate(model.getDisinfectDate());
			r.setOperator_id(model.getOperator_id());
			if (model.getDisinfectType() != null
					&& !"".equals(model.getDisinfectType())) {
				r.setDisinfectType(model.getDisinfectType());
			}
			r.setRecorder_id(model.getRecorder_id());
			roomDisinfectRecordService.save(r);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", r.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 房间消毒记录编辑前数据加载
	 */
	public void toEdit() {
		if (model.getId() != null) {
			RoomDisinfectRecord r = roomDisinfectRecordService.getById(model
					.getId());
			RoomDisinfectRecord_Json json = new RoomDisinfectRecord_Json();
			json.setId(r.getId());
			Long areaId = r.getArea_id();
			Area a = areaService.getById(areaId);
			if (a != null) {
				json.setBlongarea(a.getBlongarea());
			}
			json.setArea_id(areaId);
			json.setDisinfectant_id(r.getDisinfectant_id());
			json.setDisinfectDate(r.getDisinfectDate());
			json.setOperator_id(r.getOperator_id());
			json.setDisinfectType(r.getDisinfectType());
			json.setRecorder_id(r.getRecorder_id());
			String jsonStr = JsonPluginsUtil.beanToJson(json, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			RoomDisinfectRecord r = roomDisinfectRecordService.getById(model
					.getId());
			r.setArea_id(model.getArea_id());
			r.setDisinfectant_id(model.getDisinfectant_id());
			r.setDisinfectDate(model.getDisinfectDate());
			r.setOperator_id(model.getOperator_id());
			if (model.getDisinfectType() != null
					&& !"".equals(model.getDisinfectType())) {
				r.setDisinfectType(model.getDisinfectType());
			}
			r.setRecorder_id(model.getRecorder_id());
			roomDisinfectRecordService.update(r);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", r.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 房间消毒记录删除
	 */
	public void delete() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			roomDisinfectRecordService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}
}
