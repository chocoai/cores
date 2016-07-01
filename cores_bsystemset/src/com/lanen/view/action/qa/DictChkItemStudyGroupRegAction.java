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
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQACheckItem;

import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQACheckItemService;

import com.lanen.service.qa.DictStudyGroupService;

@Controller
@Scope("prototype")
public class DictChkItemStudyGroupRegAction extends BaseAction<DictChkItemStudyGroupReg> {

	private static final long serialVersionUID = -5418330968877919880L;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;
	
	@Resource
	private DictStudyGroupService dictStudyGroupService;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	private String chkItemId;
	private String studyGroupId;
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		DictChkItemStudyGroupReg existReg =  dictChkItemStudyGroupRegService.getByChkItemIdAndChkTblId(chkItemId,studyGroupId);
		if(existReg==null)
		{
			DictChkItemStudyGroupReg reg = new DictChkItemStudyGroupReg();
			String key = dictChkItemStudyGroupRegService.getKey("DictChkItemStudyGroupReg");
			reg.setChkItemStudyGroupRegId(key);
			DictQACheckItem dictItem = dictQACheckItemService.getById(chkItemId);
			reg.setDictQacheckItem(dictItem);
			reg.setDictStudyGroup(dictStudyGroupService.getById(studyGroupId));
			
			reg.setChkFreq(model.getChkFreq());
			reg.setChkFreqFlag(model.getChkFreqFlag());
			reg.setChkFreqUnit(model.getChkFreqUnit());
			
			dictChkItemStudyGroupRegService.save(reg);
			
			map.put("chkItemId", studyGroupId+"-"+chkItemId);
			map.put("chkItemName",dictItem.getChkItemName());
			map.put("chkFreqFlag",reg.getChkFreqFlag());
			map.put("chkFreq", reg.getChkFreq());
			map.put("chkFreqUnit", reg.getChkFreqUnit());
			
			map.put("success", true);
		}else {		
			map.put("success", false);
			map.put("msg", "检查项和试验分类关系已经存在！");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void remove()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(chkItemId.contains("-"))
		{
			chkItemId = chkItemId.split("-")[1];//关系中的chkItem是groupId-chkItemId
		}
		DictChkItemStudyGroupReg existReg =  dictChkItemStudyGroupRegService.getByChkItemIdAndChkTblId(chkItemId,studyGroupId);
		if(existReg!=null)
		{
			dictChkItemStudyGroupRegService.delete(existReg.getChkItemStudyGroupRegId());
			map.put("success", true);
		}else {		
			map.put("success", false);
			map.put("msg", "检查项和检查表关系不存在！");
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void loadList()
	{
		List<DictChkItemStudyGroupReg> list = dictChkItemStudyGroupRegService.getByChkItemId(chkItemId);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(DictChkItemStudyGroupReg reg:list)
		{
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("chkItemStudyGroupRegId", reg.getChkItemStudyGroupRegId());
			map.put("chkItemId", reg.getDictQacheckItem().getChkItemId());
			map.put("chkItemName", reg.getDictQacheckItem().getChkItemName());
			map.put("studyGroupId", reg.getDictStudyGroup().getStudyGroupId());
			map.put("studyGroupName", reg.getDictStudyGroup().getStudyGroupName());
		
			mapList.add(map);
		}
		
		//String[] _nory_changes ={"chkItemChkTblRegId","chkItemId","chkItemName","chkItemType","chkTblId","chkTblCode","chkTblName"};
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	




	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}




	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}


	public String getChkItemId() {
		return chkItemId;
	}

	public void setChkItemId(String chkItemId) {
		this.chkItemId = chkItemId;
	}
	public DictStudyGroupService getDictStudyGroupService() {
		return dictStudyGroupService;
	}
	public void setDictStudyGroupService(DictStudyGroupService dictStudyGroupService) {
		this.dictStudyGroupService = dictStudyGroupService;
	}
	
	public DictChkItemStudyGroupRegService getDictChkItemStudyGroupRegService() {
		return dictChkItemStudyGroupRegService;
	}
	public void setDictChkItemStudyGroupRegService(
			DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService) {
		this.dictChkItemStudyGroupRegService = dictChkItemStudyGroupRegService;
	}
	public String getStudyGroupId() {
		return studyGroupId;
	}
	public void setStudyGroupId(String studyGroupId) {
		this.studyGroupId = studyGroupId;
	}
}
