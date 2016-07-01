package com.lanen.view.action.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictScheduleChkItem;
import com.lanen.model.schedule.TblTaskType;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictScheduleChkItemService;
import com.lanen.service.schdeule.TblTaskTypeService;

@Controller
@Scope("prototype")
public class DictScheduleChkItemAction extends BaseAction<DictScheduleChkItem> {

	private static final long serialVersionUID = 6221725789939683314L;
	@Resource
	private DictScheduleChkItemService dictScheduleChkItemService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private TblTaskTypeService tblTaskTypeService;

	private String id;// tblTaskType id
	private String chkItemId;
	
	public String list()
	{
		return "list";
	}
	public void loadList()
	{
		List<DictScheduleChkItem> items = dictScheduleChkItemService.findAll();
		String[] _nory_format = {"scheduleChkItemId","chkItemName","taskName"};
		String json = JsonPluginsUtil.beanListToJson(items,_nory_format,true);
		writeJson(json);
	}
	public void loadListBySchedule()
	{
		List<DictScheduleChkItem> items = dictScheduleChkItemService.getByScheduleNameId(model.getTaskNameId());
		
		String[] _nory_format = {"scheduleChkItemId","chkItemName","taskName"};
		String json = JsonPluginsUtil.beanListToJson(items,_nory_format,true);
		writeJson(json);
	}
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		DictScheduleChkItem existReg =  dictScheduleChkItemService.getByChkItemIdAndChkTblId(id,chkItemId);
		if(existReg==null)
		{
			DictScheduleChkItem reg = new DictScheduleChkItem();
			String key = dictScheduleChkItemService.getKey("DictScheduleChkItem");
			reg.setScheduleChkItemId(key);
			DictQACheckItem dictQACheckItem = dictQACheckItemService.getById(chkItemId);
			reg.setDictQACheckItem(dictQACheckItem);
			reg.setChkItemName(dictQACheckItem.getChkItemName());
			TblTaskType  tblTaskType = tblTaskTypeService.getById(id);
			reg.setTaskNameId(tblTaskType.getId());
			reg.setTaskName(tblTaskType.getTaskName());
	
			dictScheduleChkItemService.save(reg);
			map.put("success", true);
			map.put("scheduleChkItemId", key);
			map.put("chkItemName",dictQACheckItem.getChkItemName());
			map.put("taskName", tblTaskType.getTaskName());
		}else {		
			map.put("success", false);
			map.put("msg", "检查项和日程关系已经存在！");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void remove() {
		Map<String, Object> map = new HashMap<String, Object>();
		DictScheduleChkItem existReg =  dictScheduleChkItemService.getByChkItemIdAndChkTblId(id,chkItemId);
		if(existReg==null)
		{
			dictScheduleChkItemService.delete(model.getScheduleChkItemId());

			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "要删除的关系不存在！");
		}
		
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChkItemId() {
		return chkItemId;
	}

	public void setChkItemId(String chkItemId) {
		this.chkItemId = chkItemId;
	}

	public DictScheduleChkItemService getDictScheduleChkItemService() {
		return dictScheduleChkItemService;
	}

	public void setDictScheduleChkItemService(
			DictScheduleChkItemService dictScheduleChkItemService) {
		this.dictScheduleChkItemService = dictScheduleChkItemService;
	}

	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}

	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}

	public TblTaskTypeService getTblTaskTypeService() {
		return tblTaskTypeService;
	}

	public void setTblTaskTypeService(TblTaskTypeService tblTaskTypeService) {
		this.tblTaskTypeService = tblTaskTypeService;
	}
}
