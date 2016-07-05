package com.lanen.view.action.arp;

import java.text.ParseException;
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
import com.lanen.model.Disinfectant;
import com.lanen.model.Disinfectant_Json;
import com.lanen.model.Disinfectantmaterial;
import com.lanen.model.Employee;
import com.lanen.service.arp.DisinfectantService;
import com.lanen.service.arp.DisinfectantmaterialService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.RoomDisinfectRecordService;

@Controller
@Scope("prototype")
public class DisinfectantAction extends BaseAction<Disinfectant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3988948513953717961L;
	@Resource
	private DisinfectantService disinfectantService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private RoomDisinfectRecordService roomDisinfectRecordService;
	@Resource
	private DisinfectantmaterialService disinfectantmaterialService;

	private Disinfectant_Json disinfectantJson;
	private String ids;
	private String materialnames;
	private String contents;
	private String productionbatchs;
	private String validdates;
	private String suppliers;

	/**
	 * 获取消毒液的编码和ID
	 */
	public void loadListDisinfectant() {
		List<Map<String, Object>> list = disinfectantService.getAllRoomIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 加载所有数据
	 */
	public void loadList() {
		List<Disinfectant> list = disinfectantService.getALLbyCreateDate();
		List<Disinfectant_Json> list2 = new ArrayList<Disinfectant_Json>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (list != null && list.size() > 0) {
			for (Disinfectant d : list) {
				Disinfectant_Json json = new Disinfectant_Json();
				json.setId(d.getId());
				json.setDisinfectantCode(d.getDisinfectantCode());
				json.setValiddate(d.getValiddate());
				String creator = getEmployeeName(d.getCreatedBy());
				json.setCreator(creator);
				json.setCreatedDate(d.getCreatedDate());
				int isUsed = roomDisinfectRecordService.isUsed(d.getId());
				json.setIsUsed(isUsed);
				list2.add(json);
			}
		}
		map.put("rows", list2);
		map.put("total", list2.size());
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
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
	 * 检查消毒液编号是否已存在
	 */
	public void checkCode() {
		if (null != model.getDisinfectantCode()
				&& !"".equals(model.getDisinfectantCode())) {
			boolean isExist = disinfectantService.isExistName(model
					.getDisinfectantCode());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/**
	 * 添加消毒剂
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getDisinfectantCode() != null
				&& !"".equals(model.getDisinfectantCode())) {
			Disinfectant d = new Disinfectant();
			d.setDisinfectantCode(model.getDisinfectantCode());
			d.setValiddate(model.getValiddate());
			d.setCreatedBy(model.getCreatedBy());
			d.setCreatedDate(model.getCreatedDate());
			disinfectantService.save(d);
			// 批量添加
			String[] materialnames1 = materialnames.split(",");
			String[] contends1 = contents.split(",");
			String[] productionbatchs1 = productionbatchs.split(",");
			String[] validdates1 = validdates.split(",");
			String[] suppliers1 = suppliers.split(",");
			for (int i = 0; i < materialnames1.length; i++) {
				Disinfectantmaterial dm = new Disinfectantmaterial();
				dm.setMaterialname(materialnames1[i]);
				dm.setContent(contends1[i]);
				if (i < productionbatchs1.length) {
					dm.setProductionbatch(productionbatchs1[i]);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (i < validdates1.length) {
					if (validdates1[i] != null && !"".equals(validdates1[i])) {
						try {
							Date validDate = sdf.parse(validdates1[i]);
							dm.setValiddate(validDate);
						} catch (ParseException e) {
							e.printStackTrace();
							String jsonStr = JsonPluginsUtil.beanToJson(map);
							writeJson(jsonStr);
							return;
						}
					}
				}
				if (i < suppliers1.length) {
					dm.setSupplier(suppliers1[i]);
				}
				dm.setDisinfectant_id(d.getId());
				disinfectantmaterialService.save(dm);
			}
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", d.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑前填充数据
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Disinfectant d = disinfectantService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑消毒液
	 */
	public void editSave() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Disinfectant d = disinfectantService.getById(model.getId());
			d.setDisinfectantCode(model.getDisinfectantCode());
			d.setValiddate(model.getValiddate());
			d.setCreatedBy(model.getCreatedBy());
			d.setCreatedDate(model.getCreatedDate());
			disinfectantService.update(d);
			// 批量编辑
			String[] ids1 = ids.split(",");
			String[] materialnames1 = materialnames.split(",");
			String[] contends1 = contents.split(",");
			String[] productionbatchs1 = productionbatchs.split(",");
			String[] validdates1 = validdates.split(",");
			String[] suppliers1 = suppliers.split(",");
			List<Disinfectantmaterial> list = disinfectantmaterialService
					.getListByDisinfectantId(d.getId());
			if (list != null && list.size() > 0) {
				for (Disinfectantmaterial disinfectantmaterial : list) {
					String dmId = disinfectantmaterial.getId() + "";
					// 利用ID判断页面是否有配方被删除，如果有数据库执行删除操作
					if (!isHave(dmId, ids1)) {
						disinfectantmaterialService.delete(disinfectantmaterial
								.getId());
					}
				}
			}
			for (int i = 0; i < materialnames1.length; i++) {
				if (ids1.length > 0 && ids1[i] != null && !"".equals(ids1[i])) {
					// 执行更新配方操作
					Long dmId = Long.parseLong(ids1[i]);
					Disinfectantmaterial dm = disinfectantmaterialService
							.getById(dmId);
					dm.setMaterialname(materialnames1[i]);
					dm.setContent(contends1[i]);
					if (i < productionbatchs1.length) {
						dm.setProductionbatch(productionbatchs1[i]);
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (i < validdates1.length) {
						if (validdates1[i] != null
								&& !"".equals(validdates1[i])) {
							try {
								Date validDate = sdf.parse(validdates1[i]);
								dm.setValiddate(validDate);
							} catch (ParseException e) {
								e.printStackTrace();
								String jsonStr = JsonPluginsUtil
										.beanToJson(map);
								writeJson(jsonStr);
								return;
							}
						}
					}
					if (i < suppliers1.length) {
						dm.setSupplier(suppliers1[i]);
					}
					dm.setDisinfectant_id(d.getId());
					disinfectantmaterialService.update(dm);
				} else {
					// 执行添加配方操作
					Disinfectantmaterial dm = new Disinfectantmaterial();
					dm.setMaterialname(materialnames1[i]);
					dm.setContent(contends1[i]);
					if (i < productionbatchs1.length) {
						dm.setProductionbatch(productionbatchs1[i]);
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (i < validdates1.length) {
						if (validdates1[i] != null
								&& !"".equals(validdates1[i])) {
							try {
								Date validDate = sdf.parse(validdates1[i]);
								dm.setValiddate(validDate);
							} catch (ParseException e) {
								e.printStackTrace();
								String jsonStr = JsonPluginsUtil
										.beanToJson(map);
								writeJson(jsonStr);
								return;
							}
						}
					}
					if (i < suppliers1.length) {
						dm.setSupplier(suppliers1[i]);
					}
					dm.setDisinfectant_id(d.getId());
					disinfectantmaterialService.save(dm);
				}
			}
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", d.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 删除消毒液并删除该消毒液的配方
	 */
	public void delDisinfectant() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && !"".equals(model.getId())) {
			Long id = model.getId();
			disinfectantService.delete(id);
			List<Disinfectantmaterial> list = disinfectantmaterialService
					.getListByDisinfectantId(id);
			if (list != null && list.size() > 0) {
				for (Disinfectantmaterial d : list) {
					disinfectantmaterialService.delete(d.getId());
				}
			}
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 判断一个字符串是否在指定的字符串数组中
	 * 
	 * @param string
	 * @param strings
	 * @return
	 */
	public boolean isHave(String string, String[] strings) {
		boolean isHave = false;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].equals(string)) {
				isHave = true;
			}
		}
		return isHave;
	}

	public Disinfectant_Json getDisinfectantJson() {
		return disinfectantJson;
	}

	public void setDisinfectantJson(Disinfectant_Json disinfectantJson) {
		this.disinfectantJson = disinfectantJson;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMaterialnames() {
		return materialnames;
	}

	public void setMaterialnames(String materialnames) {
		this.materialnames = materialnames;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getProductionbatchs() {
		return productionbatchs;
	}

	public void setProductionbatchs(String productionbatchs) {
		this.productionbatchs = productionbatchs;
	}

	public String getValiddates() {
		return validdates;
	}

	public void setValiddates(String validdates) {
		this.validdates = validdates;
	}

	public String getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}

}
