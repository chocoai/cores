package com.lanen.view.action.qa;

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
import com.lanen.model.qa.DictQAStatementTemple;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictQAStatementTempleService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DictQAStatementTempleAction extends BaseAction<DictQAStatementTemple> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private DictQAStatementTempleService dictQaStatementTempleService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	

	public String list() {
		
		
		return "list";
	}
	
	public void loadListByTiCode()
	{
		List<DictQAStatementTemple> temples = dictQaStatementTempleService.getByTiCode(model.getTiCode());
		
		writeJson(JsonPluginsUtil.beanListToJson(temples));
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExistName = dictQaStatementTempleService.isExistByTiAndName(model.getTiCode(),model.getTempleName());
		if(!isExistName)
		{
			String templeId = dictQaStatementTempleService.getKey("DictQAStatementTemple");
			DictQAStatementTemple temple = new DictQAStatementTemple();
			temple.setTempleId(templeId);
			temple.setTempleContent(model.getTempleContent());
			temple.setTempleName(model.getTempleName());
			temple.setTiCode(model.getTiCode());
			
			dictQaStatementTempleService.save(temple);
			map.put("templeId", templeId);
			map.put("templeName", temple.getTempleName());
			map.put("templeContent", temple.getTempleContent());
			map.put("tiCode", temple.getTiCode());
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "该供试品下已经存在了一个同名的模板！");
		}
			
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean isExistName = dictQaStatementTempleService.isExistByTiAndNameExceptOne(model.getTiCode(),model.getTempleName(),model.getTempleId());
		if(!isExistName)
		{
			DictQAStatementTemple temple = dictQaStatementTempleService.getById(model.getTempleId());
			
			temple.setTempleContent(model.getTempleContent());
			temple.setTempleName(model.getTempleName());
			
			dictQaStatementTempleService.update(temple);
			
			map.put("templeId", temple.getTempleId());
			map.put("templeName", temple.getTempleName());
			map.put("templeContent", temple.getTempleContent());
			map.put("tiCode", temple.getTiCode());
			
			map.put("success", true);
		}else {
			map.put("success", false);
			map.put("msg", "该供试品下已经存在了一个同名的模板！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void del()
	{
		DictQAStatementTemple temple = dictQaStatementTempleService.getById(model.getTempleId());
		dictQaStatementTempleService.delete(model.getTempleId());
		writeES("删除QA声明模板签字确认",838, "DictQAStatementTemple", model.getTempleId());
		writeLog("删除QA声明模板",temple.getTempleName(),"删除模板签字确认，签字");
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
	

	public DictQAStatementTempleService getDictQaStatementTempleService() {
		return dictQaStatementTempleService;
	}

	public void setDictQaStatementTempleService(
			DictQAStatementTempleService dictQaStatementTempleService) {
		this.dictQaStatementTempleService = dictQaStatementTempleService;
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
