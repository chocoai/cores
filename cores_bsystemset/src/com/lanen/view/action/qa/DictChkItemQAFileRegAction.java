package com.lanen.view.action.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Remove;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.service.qa.DictChkItemQAFileRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.QAFileRegService;
@Controller
@Scope("prototype")
public class DictChkItemQAFileRegAction extends BaseAction<DictChkItemQAFileReg> {
	private static final long serialVersionUID = -2237274509183603361L;
	@Resource
	private DictChkItemQAFileRegService dictChkItemQAFileRegService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private QAFileRegService qAFileRegService;
	
	private String chkItemId;
	private String fileRegId;
	
	private String fileRegIds;
	
	public String list() {
		return "list";
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		String[] fileRegIdList = fileRegIds.split(",");
		for(String oneFileRegId:fileRegIdList)
		{
			DictChkItemQAFileReg existReg =  dictChkItemQAFileRegService.getByChkItemIdAndChkTblId(chkItemId,oneFileRegId);
			if(existReg==null)
			{
				DictChkItemQAFileReg reg = new DictChkItemQAFileReg();
				String key = dictChkItemQAFileRegService.getKey("DictChkItemChkTblReg");
				reg.setChkItemQAFileRegId(key);
				reg.setDictQacheckItem(dictQACheckItemService.getById(chkItemId));
				reg.setFileRegId(oneFileRegId);
				
				dictChkItemQAFileRegService.save(reg);
				
			}else {		
				map.put("success", false);
			}
		}
		if (map.get("success")==null) {
			map.put("success", true);			
		}else {
			map.put("msg", "部分检查项和检查表关系已经存在！");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void remove()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		dictChkItemQAFileRegService.delete(model.getChkItemQAFileRegId());
		map.put("success", true);
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	public void loadList()
	{
		List<DictChkItemQAFileReg> list = dictChkItemQAFileRegService.findAll();
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(DictChkItemQAFileReg reg:list)
		{
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("chkItemChkTblRegId", reg.getChkItemQAFileRegId());
			map.put("chkItemId", reg.getDictQacheckItem().getChkItemId());
			map.put("chkItemName", reg.getDictQacheckItem().getChkItemName());
			map.put("fileRegId", reg.getFileRegId());
			if(reg.getFileRegId()!=null&&!"".equals(reg.getFileRegId()))
			{
				map.put("fileName", qAFileRegService.getById(reg.getFileRegId()).getFileName());
			
				mapList.add(map);
			}
		}
		
		//String[] _nory_changes ={"chkItemChkTblRegId","chkItemId","chkItemName","chkItemType","chkTblId","chkTblCode","chkTblName"};
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	public void loadListByChkItem()
	{
		List<DictChkItemQAFileReg> list = dictChkItemQAFileRegService.getByChkItemId(chkItemId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(DictChkItemQAFileReg reg:list)
		{
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("chkItemChkTblRegId", reg.getChkItemQAFileRegId());
			map.put("chkItemId", reg.getDictQacheckItem().getChkItemId());
			map.put("chkItemName", reg.getDictQacheckItem().getChkItemName());
			map.put("fileRegId", reg.getFileRegId());
			if(reg.getFileRegId()!=null&&!"".equals(reg.getFileRegId()))
			{
				map.put("fileName", qAFileRegService.getById(reg.getFileRegId()).getFileName());
			
				mapList.add(map);
			}
		}
		
		//String[] _nory_changes ={"chkItemChkTblRegId","chkItemId","chkItemName","chkItemType","chkTblId","chkTblCode","chkTblName"};
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	public DictChkItemQAFileRegService getDictChkItemQAFileRegService() {
		return dictChkItemQAFileRegService;
	}
	public void setDictChkItemQAFileRegService(
			DictChkItemQAFileRegService dictChkItemQAFileRegService) {
		this.dictChkItemQAFileRegService = dictChkItemQAFileRegService;
	}
	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}
	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}
	public String getFileRegId() {
		return fileRegId;
	}
	public void setFileRegId(String fileRegId) {
		this.fileRegId = fileRegId;
	}
	public String getChkItemId() {
		return chkItemId;
	}
	public void setChkItemId(String chkItemId) {
		this.chkItemId = chkItemId;
	}
	public QAFileRegService getqAFileRegService() {
		return qAFileRegService;
	}
	public void setqAFileRegService(QAFileRegService qAFileRegService) {
		this.qAFileRegService = qAFileRegService;
	}

	public String getFileRegIds() {
		return fileRegIds;
	}

	public void setFileRegIds(String fileRegIds) {
		this.fileRegIds = fileRegIds;
	}
	
	

}
