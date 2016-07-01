package com.lanen.view.action.qa;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lanen.model.qa.DictQACheckContentTable;
import com.lanen.model.qa.DictQACheckTable;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkItemChkTblRegService;
import com.lanen.service.qa.DictQACheckContentTableService;
import com.lanen.service.qa.DictQACheckTableService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictQACheckTableAction extends BaseAction<DictQACheckTable>{
	private static final long serialVersionUID = -9109857744204942816L;
	@Resource
	private DictQACheckTableService dictQACheckTableService;
	@Resource
	private DictQACheckContentTableService dictQACheckContentTableService;
	@Resource
	private DictChkItemChkTblRegService dictChkItemChkTblRegService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	private String chkTblId;
	
	public String list()
	{
		return "list";
	}
	public void loadList()
	{
		
		List<DictQACheckTable> list = null;
		if((model.getChkTblCode()!=null&&!"".equals(model.getChkTblCode()))||(model.getChkTblName()!=null&&!"".equals(model.getChkTblName())))
		{
			list = dictQACheckTableService.getByChkTblCodeAndName(model.getChkTblCode(),model.getChkTblName());
			
		}else{
			list = dictQACheckTableService.getByChkTblCodeAndName("","");
		}
		
		String[] _nory_changes ={"chkTblId","chkTblCode","chkTblName"};
		
		String json = JsonPluginsUtil.beanListToJson(list,_nory_changes , true);
		writeJson(json);
	}
	
	public void addSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = dictQACheckTableService.isExistByCodeAndName(model.getChkTblCode(),model.getChkTblName());
		if(!isExist)
		{
			DictQACheckTable table = new DictQACheckTable();
			String key = dictQACheckTableService.getKey("DictQACheckTable");
			table.setChkTblId(key);
			table.setChkTblCode(model.getChkTblCode());
			table.setChkTblName(model.getChkTblName());
			
			dictQACheckTableService.save(table);
			map.put("chkTblId", key);
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "已经存在编号或者名称相同的数据");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		DictQACheckTable table = dictQACheckTableService.getById(model.getChkTblId());
		if(table.getChkTblCode().equals(model.getChkTblCode())&&table.getChkTblName().equals(model.getChkTblName()))
		{
			map.put("success", false);
			map.put("msg", "没有做任何修改");
		}else {
			
			boolean isExist = dictQACheckTableService.isExistByCodeAndNameExceptOne(model.getChkTblCode(),model.getChkTblName(),model.getChkTblId());
			if(isExist)
			{
				map.put("success", false);
				map.put("msg", "编号或者名称已经存在");
			}else {
				table.setChkTblCode(model.getChkTblCode());
				table.setChkTblName(model.getChkTblName());
				
				dictQACheckTableService.update(table);
				map.put("success", true);
			}
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void del()
	{
		//删除级联的
		DictQACheckTable table = dictQACheckTableService.getById(model.getChkTblId());
		List<DictQACheckContentTable> contents = dictQACheckContentTableService.getByChkTblId(model.getChkTblId());
		for (DictQACheckContentTable content:contents) {
			dictQACheckContentTableService.delete(content.getChkTblContentId());
		}
		List<DictChkItemChkTblReg> regs = dictChkItemChkTblRegService.getByChkTblId(model.getChkTblId());
		for(DictChkItemChkTblReg reg:regs)
		{
			dictChkItemChkTblRegService.delete(reg.getChkItemChkTblRegId());
		}
		
		dictQACheckTableService.delete(model.getChkTblId());
		
		writeES("删除检查表签字确认",837, "DictQACheckTable", model.getChkTblId());
		writeLog("删除检查表",table.getChkTblName(),"删除检查表签字确认，签字");
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

	
	public DictQACheckTableService getDictQACheckTableService() {
		return dictQACheckTableService;
	}
	public void setDictQACheckTableService(
			DictQACheckTableService dictQACheckTableService) {
		this.dictQACheckTableService = dictQACheckTableService;
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
	public DictChkItemChkTblRegService getDictChkItemChkTblRegService() {
		return dictChkItemChkTblRegService;
	}
	public void setDictChkItemChkTblRegService(
			DictChkItemChkTblRegService dictChkItemChkTblRegService) {
		this.dictChkItemChkTblRegService = dictChkItemChkTblRegService;
	}
	
	
	
	
	
	
	
}
