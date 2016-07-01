package com.lanen.view.action.qa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.DictQACheckContentTable;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.service.qa.DictQACheckContentTableService;
import com.lanen.service.qa.DictQACheckTableService;
@Controller
@Scope("prototype")
public class DictQACheckContentTableAction extends BaseAction<DictQACheckContentTable> {

	private static final long serialVersionUID = 716560070309612154L;
	@Resource
	private DictQACheckContentTableService dictQACheckContentTableService;
	@Resource
	private DictQACheckTableService dictQACheckTableService;
	
	private String chkTblId;//检查表id
	
	public void loadListByChkTblId()
	{
		List<DictQACheckContentTable> list = dictQACheckContentTableService.getByChkTblId(chkTblId);
		
		String[] _nory_changes ={"chkTblContentId","sn","chkContent"};
		String json = JsonPluginsUtil.beanListToJson(list,_nory_changes,true);
		writeJson(json);
	}
	public void saveQACheckContetTable()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean exisTable = dictQACheckContentTableService.isExistChkTblAndContent(chkTblId,model.getChkContent());
		if(!exisTable)
		{
			DictQACheckContentTable contentTable = new DictQACheckContentTable();
			String key = dictQACheckContentTableService.getKey("DictQACheckContentTable");
			DictQACheckTable dictQacheckTable = dictQACheckTableService.getById(chkTblId);
	
			contentTable.setChkTblContentId(key);
			contentTable.setChkContent(model.getChkContent());
			contentTable.setDictQacheckTable(dictQacheckTable);
			//获取最大的序号
			Integer maxSn = dictQACheckContentTableService.getMaxSnByChkTblId(chkTblId);
			contentTable.setSn(maxSn+1);
			
			dictQACheckContentTableService.save(contentTable);
			map.put("success", true);
			map.put("chkTblContentId", contentTable.getChkTblContentId());
			map.put("sn", contentTable.getSn());
			map.put("chkContent", contentTable.getChkContent());
		}else {
			map.put("success", false);
			map.put("msg", "该内容已经存在");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void editQACheckContetTable()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
			DictQACheckContentTable contentTable = dictQACheckContentTableService.getById(model.getChkTblContentId());
			if(!model.getChkContent().equals(contentTable.getChkContent()))
			{
				contentTable.setChkContent(model.getChkContent());
				boolean exisTable = dictQACheckContentTableService.isExistChkTblAndContent(chkTblId,model.getChkContent());
				if(!exisTable)
				{
					dictQACheckContentTableService.update(contentTable);
					map.put("success", true);
				}else {
					map.put("success", false);
					map.put("msg", "该内容已经存在");
				}
			}else {
				map.put("success", false);
				map.put("msg", "没有做任何修改");
			}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		dictQACheckContentTableService.delete(model.getChkTblContentId());
		
	}
	
	public String getChkTblId() {
		return chkTblId;
	}
	public void setChkTblId(String chkTblId) {
		this.chkTblId = chkTblId;
	}


	public DictQACheckContentTableService getDictQACheckContentTableService() {
		return dictQACheckContentTableService;
	}


	public void setDictQACheckContentTableService(
			DictQACheckContentTableService dictQACheckContentTableService) {
		this.dictQACheckContentTableService = dictQACheckContentTableService;
	}

}
