package com.lanen.view.action.qa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.DictChkItemChkTblReg;
import com.lanen.model.qa.DictChkItemQAFileReg;
import com.lanen.model.qa.DictChkItemStudyGroupReg;
import com.lanen.model.qa.DictQACheckItem;
import com.lanen.model.qa.DictScheduleChkItem;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictChkItemQAFileRegService;
import com.lanen.service.qa.DictChkItemStudyGroupRegService;
import com.lanen.service.qa.DictQACheckItemService;
import com.lanen.service.qa.DictScheduleChkItemService;
import com.lanen.service.qa.DictStudyGroupService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DictQACheckItemAction extends BaseAction<DictQACheckItem>{
	private static final long serialVersionUID = -1513401319145805352L;
	@Resource
	private DictQACheckItemService dictQACheckItemService;
	@Resource
	private DictStudyGroupService dictStudyGroupService;
	@Resource
	private DictScheduleChkItemService dictScheduleChkItemService;
	@Resource
	private DictChkItemStudyGroupRegService dictChkItemStudyGroupRegService;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private DictChkItemQAFileRegService dictChkItemQAFileRegService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	private String studyGroupId;
	
	public String list()
	{
		
		return "list";
	}
	public void loadList()
	{
		List<DictQACheckItem> items = dictQACheckItemService.getAll();
		
	     String[] _nory_changes ={"studyGroupId","chkItemId","chkItemType","chkItemName"};
		String json = JsonPluginsUtil.beanListToJson(items,_nory_changes , true);
		writeJson(json);
	}
	public void loadScheduleChkItemList()
	{
		List<DictQACheckItem> items = dictQACheckItemService.getByType(4);
		
	     String[] _nory_changes ={"studyGroupId","chkItemId","chkItemType","chkItemName"};
		String json = JsonPluginsUtil.beanListToJson(items,_nory_changes , true);
		writeJson(json);
	}
	public void addSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = dictQACheckItemService.isExistByTypeAndName( model.getChkItemType(),model.getChkItemName());
		if(!isExist)
		{
			DictQACheckItem table = new DictQACheckItem();
			String key = dictQACheckItemService.getKey("DictQACheckItem");
			table.setChkItemId(key);
			table.setChkItemType(model.getChkItemType());
			table.setChkItemName(model.getChkItemName());
			
			dictQACheckItemService.save(table);
			map.put("success", true);
			map.put("studyGroupId", null);
			map.put("chkItemId", key);
			map.put("chkItemType", model.getChkItemType());
			map.put("chkItemName", model.getChkItemName());
			
			
			
		}else {
			map.put("success", false);
			map.put("msg", "相同的数据已经存在！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		DictQACheckItem table = dictQACheckItemService.getById(model.getChkItemId());
		if(table.getChkItemType().equals(model.getChkItemType())&&table.getChkItemName().equals(model.getChkItemName()))
		{
			map.put("success", false);
			map.put("msg", "没有做任何修改！");
		}else {
			boolean isExist = dictQACheckItemService.isExistByTypeAndName( model.getChkItemType(),model.getChkItemName());
			if(isExist)
			{
				map.put("success", false);
				map.put("msg", "相同的数据已经存在！");
			}else{
				table.setChkItemType(model.getChkItemType());
				table.setChkItemName(model.getChkItemName());
				dictQACheckItemService.update(table);
				//同时更新日程检查项关系中的检查项名称
				
				List<DictScheduleChkItem> schedultItemSet = dictScheduleChkItemService.getByChkItemId(table.getChkItemId());
				if(schedultItemSet!=null&&schedultItemSet.size()>0)
				{
					for(DictScheduleChkItem schedultItem:schedultItemSet)
					{
						schedultItem.setChkItemName(model.getChkItemName());
						dictScheduleChkItemService.update(schedultItem);
					}
				}
				
				map.put("success", true);
				map.put("chkItemId", table.getChkItemId());
				map.put("chkItemType", table.getChkItemType());
				map.put("chkItemName", table.getChkItemName());
			}
				
		}
			
		
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		// "dictChkItemStudyGroupRegs","dictChkItemChkTblRegs" "dictScheduleChkItems" "dictChkItemQAFileRegs" 
		List<DictChkItemStudyGroupReg> dictChkItemStudyGroupRegs =  dictChkItemStudyGroupRegService.getByChkItemId(model.getChkItemId());
		for(DictChkItemStudyGroupReg reg:dictChkItemStudyGroupRegs)
		{
			dictChkItemStudyGroupRegService.delete(reg.getChkItemStudyGroupRegId());
		}
		List<DictChkItemChkTblReg> dictChkItemChkTblRegs = dictChkItemChkTblRegService.getByChkItemId(model.getChkItemId());
		for(DictChkItemChkTblReg reg:dictChkItemChkTblRegs)
		{
			dictChkItemChkTblRegService.delete(reg.getChkItemChkTblRegId());
		}
		List<DictScheduleChkItem> dictScheduleChkItems = dictScheduleChkItemService.getByChkItemId(model.getChkItemId());
		for(DictScheduleChkItem reg:dictScheduleChkItems)
		{
			dictScheduleChkItemService.delete(reg.getScheduleChkItemId());
		}
		List<DictChkItemQAFileReg> dictChkItemQAFileRegs = dictChkItemQAFileRegService.getByChkItemId(model.getChkItemId());
		for(DictChkItemQAFileReg reg:dictChkItemQAFileRegs)
		{
			dictChkItemQAFileRegService.delete(reg.getChkItemQAFileRegId());
		}
		
		DictQACheckItem dictQACheckItem=dictQACheckItemService.getById(model.getChkItemId());
		dictQACheckItemService.delete(model.getChkItemId());
		
		writeES("删除检查项签字确认",836, "DictQACheckItem", model.getChkItemId());
		writeLog("删除检查项",dictQACheckItem.getChkItemName(),"删除检查项签字确认，签字");
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
	
	
	
	
	public DictQACheckItemService getDictQACheckItemService() {
		return dictQACheckItemService;
	}
	public void setDictQACheckItemService(
			DictQACheckItemService dictQACheckItemService) {
		this.dictQACheckItemService = dictQACheckItemService;
	}
	public String getStudyGroupId() {
		return studyGroupId;
	}
	public void setStudyGroupId(String studyGroupId) {
		this.studyGroupId = studyGroupId;
	}
	public DictStudyGroupService getDictStudyGroupService() {
		return dictStudyGroupService;
	}
	public void setDictStudyGroupService(DictStudyGroupService dictStudyGroupService) {
		this.dictStudyGroupService = dictStudyGroupService;
	}
	

}
