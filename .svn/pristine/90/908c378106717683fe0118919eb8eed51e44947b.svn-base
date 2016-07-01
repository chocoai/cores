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
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.model.qa.DictQACheckTable;

import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictQACheckTableService;
@Controller
@Scope("prototype")
public class DictChkItemChkTblRegAction extends BaseAction<DictChkItemChkTblReg> {

	private static final long serialVersionUID = 5226952577154407094L;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private DictQACheckTableService dictQACheckTableService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	private String chkItemId;
	private String chkTblId;
	
	private String chkTblIds;
	
	
	public void save()
	{
		String[] chkTblIdList = chkTblIds.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		for(String oneChkTblId:chkTblIdList)
		{
			
			DictChkItemChkTblReg existReg =  dictChkItemChkTblRegService.getByChkItemIdAndChkTblId(chkItemId,oneChkTblId);
			if(existReg==null)
			{
				DictChkItemChkTblReg reg = new DictChkItemChkTblReg();
				String key = dictChkItemChkTblRegService.getKey("DictChkItemChkTblReg");
				reg.setChkItemChkTblRegId(key);
				reg.setDictQacheckItem(dictQACheckItemService.getById(chkItemId));
				DictQACheckTable dictQACheckTable = dictQACheckTableService.getById(oneChkTblId);
				reg.setDictQacheckTable(dictQACheckTable);
				
				dictChkItemChkTblRegService.save(reg);
				
			}else {		
				map.put("success", false);
			}
		}
		if(map.get("success")==null)
		{
			map.put("success", true);
		}else {			
			map.put("msg", "部分检查项和检查表关系已经存在！");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void del()
	{
		dictChkItemChkTblRegService.delete(model.getChkItemChkTblRegId());
	}
	public void loadList()
	{
		List<DictChkItemChkTblReg> list = dictChkItemChkTblRegService.getByChkItemId(chkItemId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(DictChkItemChkTblReg reg:list)
		{
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("chkItemChkTblRegId", reg.getChkItemChkTblRegId());
			map.put("chkItemId", reg.getDictQacheckItem().getChkItemId());
			map.put("chkItemName", reg.getDictQacheckItem().getChkItemName());
			map.put("chkTblId", reg.getDictQacheckTable().getChkTblId());
			map.put("chkTblCode", reg.getDictQacheckTable().getChkTblCode());
			map.put("chkTblName", reg.getDictQacheckTable().getChkTblName());
			mapList.add(map);
		}
		
		//String[] _nory_changes ={"chkItemChkTblRegId","chkItemId","chkItemName","chkItemType","chkTblId","chkTblCode","chkTblName"};
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	
	
	public DictChkItemChkTblRegService getDictChkItemChkTblRegService() {
		return dictChkItemChkTblRegService;
	}
	public void setDictChkItemChkTblRegService(
			DictChkItemChkTblRegService dictChkItemChkTblRegService) {
		this.dictChkItemChkTblRegService = dictChkItemChkTblRegService;
	}




	public DictQACheckTableService getDictQACheckTableService() {
		return dictQACheckTableService;
	}




	public void setDictQACheckTableService(
			DictQACheckTableService dictQACheckTableService) {
		this.dictQACheckTableService = dictQACheckTableService;
	}




	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}




	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}




	public String getChkTblId() {
		return chkTblId;
	}




	public void setChkTblId(String chkTblId) {
		this.chkTblId = chkTblId;
	}




	public String getChkItemId() {
		return chkItemId;
	}




	public void setChkItemId(String chkItemId) {
		this.chkItemId = chkItemId;
	}
	public String getChkTblIds() {
		return chkTblIds;
	}
	public void setChkTblIds(String chkTblIds) {
		this.chkTblIds = chkTblIds;
	}
	
	
}
