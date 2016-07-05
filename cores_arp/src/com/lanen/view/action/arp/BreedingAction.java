package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Breeding;
import com.lanen.service.arp.BreedingService;
import com.lanen.util.Constant;

@Controller
@Scope("prototype")
public class BreedingAction extends BaseAction<Breeding> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5420726313312986412L;
	/**
	 * 发情配种 service
	 */
	@Resource
	private BreedingService breedingService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	public String list() {
		return "breedingList";
	}

	/**
	 * 数据加载
	 */
	public void loadList() {
		Map<String, Object> mapList = breedingService.loadListByCondition(page,
				rows, model.getMonkeyid(), model.getOestrusdate(),
				model.getBreedingdate(), model.getMatingdate(),
				model.getOestrustype());

		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 *发情类型 
	 * 
	 */
	public void getOestrusType(){
		List<Map<String,String>> listMap=breedingService.getAllOestrusTypeMapNo(Constant.BREEDING_TYPE_MARK);
		String jsonStr=JsonPluginsUtil.beanListToJson(listMap);
		writeJson(jsonStr);
	}
	
	/**
	 * 添加发情配种记录
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Breeding breeding = new Breeding();
		if (null != model.getMonkeyid() && !model.getMonkeyid().equals("")) {
			breeding.setMonkeyid(model.getMonkeyid());
			breeding.setOestrusdate(model.getOestrusdate());
			breeding.setOestrustype(model.getOestrustype());
			breeding.setBreedingdate(model.getBreedingdate());
			breeding.setMatingdate(model.getMatingdate());
			breeding.setMalesmonkeyid(model.getMalesmonkeyid());
			breeding.setVeterinarian(model.getVeterinarian());
			breeding.setProtector(model.getProtector());
			breeding.setRecorder(model.getRecorder());
			breeding.setOperater(model.getOperater());
			breeding.setRemark(model.getRemark());
			breeding.setCreatetime(new Date());
			breedingService.save(breeding);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", breeding.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑发情配种记录前加载数据
	 */
	public void toEdit() {
		if (null != model.getId() && model.getId() != 0) {
			Breeding breeding = breedingService.getById(model.getId());
			String jsonString = JsonPluginsUtil.beanToJson(breeding,
					"yyyy-MM-dd");
			writeJson(jsonString);
		}
	}

	/**
	 * 编辑发情配种记录后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != model.getId() && model.getId() != 0) {
			Breeding breeding = breedingService.getById(model.getId());
			breeding.setMonkeyid(model.getMonkeyid());
			breeding.setOestrusdate(model.getOestrusdate());
			breeding.setOestrustype(model.getOestrustype());
			breeding.setBreedingdate(model.getBreedingdate());
			breeding.setMatingdate(model.getMatingdate());
			breeding.setMalesmonkeyid(model.getMalesmonkeyid());
			breeding.setVeterinarian(model.getVeterinarian());
			breeding.setProtector(model.getProtector());
			breeding.setRecorder(model.getRecorder());
			breeding.setOperater(model.getOperater());
			breeding.setRemark(model.getRemark());
			breeding.setLastmodifytime(new Date());
			breedingService.update(breeding);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", breeding.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 删除记录
	 */
	public void delete() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			breedingService.delete(model.getId());
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
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
}
