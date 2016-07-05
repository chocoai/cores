package com.lanen.view.action.arp;

import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import com.lanen.model.AreaJson;
import com.lanen.model.Employee;
import com.lanen.model.Inventory;
import com.lanen.model.Inventory_Json;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.InventoryService;
import com.lanen.util.DateUtil;
import com.lanen.util.ExcelFileGenerator;

@Controller
@Scope("prototype")
public class InventoryAction extends BaseAction<Inventory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2908476472399769157L;
	@Resource
	private InventoryService inventoryService;
	@Resource
	private AreaService areaService;
	@Resource
	private EmployeeService employeeService;

	private String realmaleMonkeyCounts;// 实盘公猴数量
	private String realfemaleMonkeyCounts;// 实盘母猴数量
	private String realyuchengMonkeyCounts;// 实盘育成猴数量
	private String realcubMonkeyCounts;// 实盘仔猴数量
	private String realtotalCounts;// 实盘猴子总数量
	private InputStream fileInput;
	private String fileName;

	public String list() {
		return "list";
	}

	/**
	 * 保存盘点数据
	 * 
	 * @throws Exception
	 */
	public void saveRecord() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getKeeper() != null) {
			List<Area> list = areaService.getIdsByKeeper(model.getKeeper());
			String[] realmaleMonkeyCounts1 = realmaleMonkeyCounts.split(",");
			String[] realfemaleMonkeyCounts1 = realfemaleMonkeyCounts
					.split(",");
			String[] realyuchengMonkeyCounts1 = realyuchengMonkeyCounts
					.split(",");
			String[] realcubMonkeyCounts1 = realcubMonkeyCounts.split(",");
			String[] realtotalCounts1 = realtotalCounts.split(",");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					AreaJson json = areaService.getAreanameAndMonkeyCount(list
							.get(i).getId());
					Inventory inventory = new Inventory();
					inventory.setInventoryType(1);
					inventory.setRoomId(list.get(i).getId());
					inventory
							.setMaleMonkeyCount(json.getMaleMonkeyCount() + "");
					inventory.setRealmaleMonkeyCount(realmaleMonkeyCounts1[i]);
					inventory.setFemaleMonkeyCount(json.getFemaleMonkeyCount()
							+ "");
					inventory
							.setRealfemaleMonkeyCount(realfemaleMonkeyCounts1[i]);
					inventory.setYuchengMonkeyCount(json
							.getYuchengMonkeyCount() + "");
					inventory
							.setRealyuchengMonkeyCount(realyuchengMonkeyCounts1[i]);
					inventory.setCubMonkeyCount(json.getCubMonkeyCount() + "");
					inventory.setRealcubMonkeyCount(realcubMonkeyCounts1[i]);
					inventory.setTotalCount(json.getTotalCount() + "");
					inventory.setRealtotalCount(realtotalCounts1[i]);
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataString = sdf.format(date);
					date = sdf.parse(dataString);
					inventory.setInventoryDate(date);
					inventory.setKeeper(model.getKeeper());
					inventoryService.save(inventory);
					map.put("success", true);
					map.put("msg", "添加成功");
					map.put("inventoryDate", dataString);
				}
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 查询盘点数据
	 */
	public void loadListByKeeperAndInventoryDate() {
		List<Inventory> list = inventoryService.getListByInventoryDate(
				model.getKeeper(), model.getInventoryDate());
		List<Inventory_Json> list2 = new ArrayList<Inventory_Json>();
		for (Inventory i : list) {
			Inventory_Json json = new Inventory_Json();
			json.setId(i.getId());
			json.setInventoryType(i.getInventoryType());
			json.setAreaname(getAreaName(i.getRoomId()));
			json.setMaleMonkeyCount(i.getMaleMonkeyCount());
			json.setRealmaleMonkeyCount(i.getRealmaleMonkeyCount());
			json.setFemaleMonkeyCount(i.getFemaleMonkeyCount());
			json.setRealfemaleMonkeyCount(i.getRealfemaleMonkeyCount());
			json.setYuchengMonkeyCount(i.getYuchengMonkeyCount());
			json.setRealyuchengMonkeyCount(i.getRealyuchengMonkeyCount());
			json.setCubMonkeyCount(i.getCubMonkeyCount());
			json.setRealcubMonkeyCount(i.getRealcubMonkeyCount());
			json.setTotalCount(i.getTotalCount());
			json.setRealtotalCount(i.getTotalCount());
			json.setInventoryDate(i.getInventoryDate());
			json.setKeeperName(getEmpName(i.getKeeper()));
			list2.add(json);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list2);
		map.put("total", list2.size());
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 根据区域ID获得区域名
	 * 
	 * @param id
	 * @return
	 */
	public String getAreaName(Long id) {
		String areaName = null;
		Area area = areaService.getById(id);
		if (area != null) {
			areaName = area.getAreaname();
		}
		return areaName;
	}

	/**
	 * 根据员工ID获得员工姓名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmpName(Long id) {
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
	 * 导出应盘数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() throws Exception {
		// 获取导出的表头和数据
		// 获取表头,存放到ArrayList filedName对象中
		ArrayList<Object> filedName = areaService.getExcelFiledNameList();
		// /**获取数据, 是值存放到ArrayList dataList集合中
		// *再实例化一个ArrayList filedData集合 filedData.add(dataList);
		// */
		//
		ArrayList<Object> filedData = areaService.getExcelFiledDataList(model
				.getKeeper());
		ExcelFileGenerator generator = new ExcelFileGenerator(filedName,
				filedData);
		fileInput = generator.getInputStream();
		fileName = getFileName();
		return "export";
	}

	/**
	 * 导出盘点完成后存档数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export1() throws Exception {
		// 获取导出的表头和数据
		// 获取表头,存放到ArrayList filedName对象中
		ArrayList<Object> filedName = inventoryService.getExcelFiledNameList();
		// /**获取数据, 是值存放到ArrayList dataList集合中
		// *再实例化一个ArrayList filedData集合 filedData.add(dataList);
		// */
		//
		ArrayList<Object> filedData = inventoryService.getExcelFiledDataList(
				model.getKeeper(), model.getInventoryDate());
		ExcelFileGenerator generator = new ExcelFileGenerator(filedName,
				filedData);
		fileInput = generator.getInputStream();
		fileName = getFileName();
		return "export1";
	}

	public String getRealmaleMonkeyCounts() {
		return realmaleMonkeyCounts;
	}

	public void setRealmaleMonkeyCounts(String realmaleMonkeyCounts) {
		this.realmaleMonkeyCounts = realmaleMonkeyCounts;
	}

	public String getRealfemaleMonkeyCounts() {
		return realfemaleMonkeyCounts;
	}

	public void setRealfemaleMonkeyCounts(String realfemaleMonkeyCounts) {
		this.realfemaleMonkeyCounts = realfemaleMonkeyCounts;
	}

	public String getRealyuchengMonkeyCounts() {
		return realyuchengMonkeyCounts;
	}

	public void setRealyuchengMonkeyCounts(String realyuchengMonkeyCounts) {
		this.realyuchengMonkeyCounts = realyuchengMonkeyCounts;
	}

	public String getRealcubMonkeyCounts() {
		return realcubMonkeyCounts;
	}

	public void setRealcubMonkeyCounts(String realcubMonkeyCounts) {
		this.realcubMonkeyCounts = realcubMonkeyCounts;
	}

	public String getRealtotalCounts() {
		return realtotalCounts;
	}

	public void setRealtotalCounts(String realtotalCounts) {
		this.realtotalCounts = realtotalCounts;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 返回类型为"中文名字-20130612231234.xls"
	 * 
	 * @return
	 */
	public String getFileName() throws Exception {
		String tempName = "数据导出" + "-"
				+ DateUtil.dateToString(new Date(), "yyyy-MM-dd") + ".xls";

		fileName = new String(tempName.getBytes(), "ISO8859-1");
		return fileName;
	}

}
