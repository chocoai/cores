package com.lanen.view.action.qa;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.jsonAndModel.TreeModel;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictStudyGroup;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictStudyGroupService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictStudyGroupAction extends BaseAction<DictStudyGroup>  {
	private static final long serialVersionUID = -3719818695214710418L;
	@Resource
	private DictStudyGroupService dictStudyGroupService;
	
	@Resource
	private DictStudyTypeService  dictStudyTypeService;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	
	public String list()
	{
		return "list";
	}
	public void loadList()
	{
		List<DictStudyGroup> allGroup = dictStudyGroupService.findAll();
		 
		String[] _nory_changes ={"studyGroupId","studyGroupName"};
		
		String json = JsonPluginsUtil.beanListToJson(allGroup,_nory_changes , true);
		
		writeJson(json);
		
	}	
	
	public void addSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isexist = dictStudyGroupService.isExistByStudyGroupName(model.getStudyGroupName());
		if(!isexist)
		{
			DictStudyGroup studyGroup = new DictStudyGroup();
			String key =  dictStudyGroupService.getKey("DictStudyGroup");
			studyGroup.setStudyGroupId(key);
			studyGroup.setStudyGroupName(model.getStudyGroupName());
			dictStudyGroupService.save(studyGroup);
			map.put("success", true);
			map.put("studyGroupId", key);
			map.put("studyGroupName", model.getStudyGroupName());
		}else {
			map.put("success", false);
			map.put("msg", "已经存在该实验分类");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		DictStudyGroup studyGroup = dictStudyGroupService.getById(model.getStudyGroupId());
		if(studyGroup.getStudyGroupName().equals(model.getStudyGroupName()))
		{
			map.put("success", false);
			map.put("msg", "没有做任何改动");
		}else {
			boolean isexist = dictStudyGroupService.isExistByStudyGroupName(model.getStudyGroupName());
			if(isexist)
			{
				map.put("success", false);
				map.put("msg", "已经存在该实验分类");
			}else {
				studyGroup.setStudyGroupName(model.getStudyGroupName());
				dictStudyGroupService.update(studyGroup);
				map.put("success", true);
				map.put("studyGroupId", studyGroup.getStudyGroupId());
				map.put("studyGroupName", model.getStudyGroupName());
			}
		}
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		//先解除关系,设置studyType的groupid为null
		DictStudyGroup dictStudyGroup = dictStudyGroupService.getById(model.getStudyGroupId());
		
		
		List<DictStudyType> types = dictStudyTypeService.getListByStudyGroupId(model.getStudyGroupId());
		for(DictStudyType type:types)
		{
			type.setStudyGroupID(null);
			dictStudyTypeService.update(type);
		}
		List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupId(model.getStudyGroupId());
		for(DictChkItemStudyGroupReg reg:regs)
		{
			dictChkItemStudyGroupRegService.delete(reg.getChkItemStudyGroupRegId());
		}
		
		dictStudyGroupService.delete(dictStudyGroup.getStudyGroupId());
		writeES("删除试验类别签字确认", 839, "DictStudyGroup", model.getStudyGroupId());
		writeLog("删除试验类别",dictStudyGroup.getStudyGroupName(),"删除试验类别签字确认，签字");
		
	}
	public void loadGroupAndItemTree()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> tm =null;
		List<DictStudyGroup> groups=dictStudyGroupService.findAll();
		List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.findAll();
		
		for(DictStudyGroup dsg:groups)
		{
			tm = new HashMap<String, Object>();
			tm.put("id",dsg.getStudyGroupId());
			tm.put("text",dsg.getStudyGroupName());
			
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			//List<DictChkItemStudyGroupReg> regs = dictChkItemStudyGroupRegService.getByStudyGroupId(dsg.getStudyGroupId());
			for(DictChkItemStudyGroupReg reg:regs)
			{
				if(reg.getDictStudyGroup().getStudyGroupId().equals(dsg.getStudyGroupId()))
				{					
					Map<String, Object> tmChild = new HashMap<String, Object>();
					tmChild.put("id",dsg.getStudyGroupId()+"-"+reg.getDictQacheckItem().getChkItemId());
					tmChild.put("text",reg.getDictQacheckItem().getChkItemName());
					tmChild.put("chkFreq",reg.getChkFreq());
					tmChild.put("chkFreqFlag",reg.getChkFreqFlag());
					tmChild.put("chkFreqUnit",reg.getChkFreqUnit());
					
					children.add(tmChild);
				}
			}
			if(children.size()>0){
				tm.put("state","closed");
				tm.put("children",children);
			}
			list.add(tm);
		}
	
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		writeJson(json);
		
	}
	
	private void writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
	{
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		//验证通过则进行一下操作
		
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsTypeDesc(EsTypeDesc);
		es.setEsType(EsType);
		es.setDateTime(new Date());
		String eid = tblESService.getKey("TblES");
		es.setEsId(eid);
		tblESService.save(es);
		
		esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(EsType);
		esLink.setEsTypeDesc(EsTypeDesc+"签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOjbect,String operatContent){
		
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOjbect);
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}


	public DictStudyTypeService getDictStudyTypeService() {
		return dictStudyTypeService;
	}



	public DictStudyGroupService getDictStudyGroupService() {
		return dictStudyGroupService;
	}
	public void setDictStudyGroupService(DictStudyGroupService dictStudyGroupService) {
		this.dictStudyGroupService = dictStudyGroupService;
	}
	public void setDictStudyTypeService(DictStudyTypeService dictStudyTypeService) {
		this.dictStudyTypeService = dictStudyTypeService;
	}
	
	public DictChkItemStudyGroupRegService getDictChkItemStudyGroupRegService() {
		return dictChkItemStudyGroupRegService;
	}
	public void setDictChkItemStudyGroupRegService(
			DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService) {
		this.dictChkItemStudyGroupRegService = dictChkItemStudyGroupRegService;
	}
	public TblESService getTblESService() {
		return tblESService;
	}
	public void setTblESService(TblESService tblESService) {
		this.tblESService = tblESService;
	}
	public TblESLinkService getTblESLinkService() {
		return tblESLinkService;
	}
	public void setTblESLinkService(TblESLinkService tblESLinkService) {
		this.tblESLinkService = tblESLinkService;
	}
	
	
	
	
	

}
