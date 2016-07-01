package com.lanen.view.action.archive;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.archive.DictAdministrationType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;

import com.lanen.service.UserService;
import com.lanen.service.archive.DictAdministrationTypeService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.util.CryptUtils;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DictAdministrationTypeAction extends BaseAction<DictAdministrationType> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6413773696255447638L;
	//签名链接表Service
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblLogService tblLogService;
	@Resource
	private UserService userService;
	@Resource
	private DictAdministrationTypeService dictAdministrationTypeService;
	
	private String oldArchiveTypeCode;
	
	public String list()
	{
		
		return "list";
	}
	
	public void checkTypeName()
	{
		if(null != model.getDocTypeName()){
			boolean isExist = false;
			isExist = dictAdministrationTypeService.hasTypeName(model.getDocTypeName());
			
			if(isExist){
				writeJson("false");
			}else{
				writeJson("true");
			}
		}else{
			writeJson("false");
		}
	
	}
	public void checkTypeCode()
	{
		if(null != model.getDocTypeFlag()){
			boolean isExist = false;
			isExist = dictAdministrationTypeService.hasTypeCode(model.getDocTypeFlag());
			
			if(isExist){
				writeJson("false");
			}else{
				writeJson("true");
			}
		}else{
			writeJson("false");
		}
		
	}

	public void loadList()
	{
		List<DictAdministrationType> types = dictAdministrationTypeService.getAll();
		writeJson(JsonPluginsUtil.beanListToJson(types));
	}
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExistName = dictAdministrationTypeService.hasTypeName(model.getDocTypeName());
		boolean isExistCode = dictAdministrationTypeService.hasTypeCode(model.getDocTypeFlag());
		if(!isExistName&&!isExistCode)
		{
			Integer maxSn = dictAdministrationTypeService.getMaxSn();
			DictAdministrationType archiveType = new DictAdministrationType();
			archiveType.setDocTypeFlag(model.getDocTypeFlag());
			archiveType.setDocTypeName(model.getDocTypeName());
			archiveType.setSn(maxSn+1);
			
			dictAdministrationTypeService.save(archiveType);
			map.put("docTypeFlag", model.getDocTypeFlag());
			map.put("sn", model.getSn());
			map.put("docTypeName", model.getDocTypeName());
			
			map.put("success", true);
			map.put("msg", "添加成功");
		}else {
			map.put("success", false);
			if(isExistName)
			{
				map.put("msg", "综合名称已经存在");
			}
			if(isExistCode)
			{
				map.put("msg", "综合代码已经存在");
			}
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExistName = dictAdministrationTypeService.hasTypeName(model.getDocTypeName());
		if(!isExistName)
		{
			DictAdministrationType archiveType = dictAdministrationTypeService.getByArchiveTypeCode(oldArchiveTypeCode);
			archiveType.setDocTypeFlag(model.getDocTypeFlag());
			archiveType.setDocTypeName(model.getDocTypeName());
			
			dictAdministrationTypeService.update(archiveType);
			map.put("docTypeFlag", model.getDocTypeFlag());
			map.put("sn", model.getSn());
			map.put("docTypeName", model.getDocTypeName());
			
			map.put("msg", "更新成功");
			map.put("success", true);
		}else{
			map.put("msg", "综合名称已经存在");
			map.put("success", false);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		dictAdministrationTypeService.deleteByTypeCode(model.getDocTypeFlag());
		map.put("success", true);
		map.put("msg", "删除成功");
		writeES("删除一个档案类别", 941, "DictArchiveType", model.getDocTypeName());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
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
	
	private void writeLog(String operatType,String operatContent,User user){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(SystemMessage.getSystemFullName());
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}

	public String getOldArchiveTypeCode() {
		return oldArchiveTypeCode;
	}

	public void setOldArchiveTypeCode(String oldArchiveTypeCode) {
		this.oldArchiveTypeCode = oldArchiveTypeCode;
	}

	
	
}
