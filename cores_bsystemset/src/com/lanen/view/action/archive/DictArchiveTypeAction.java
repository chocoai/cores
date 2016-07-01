package com.lanen.view.action.archive;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;

import com.lanen.service.UserService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DictArchiveTypeAction extends BaseAction<DictArchiveType> {
	
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
	private DictArchiveTypeService dictArchiveTypeService;
	
	private String oldArchiveTypeCode;
	
	public String list()
	{
		
		return "list";
	}
	
	public void checkTypeName()
	{
		if(null != model.getArchiveTypeName()){
			boolean isExist = false;
			isExist = dictArchiveTypeService.hasTypeName(model.getArchiveTypeName());
			
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
		if(null != model.getArchiveTypeCode()){
			boolean isExist = false;
			isExist = dictArchiveTypeService.hasTypeCode(model.getArchiveTypeCode());
			
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
		List<DictArchiveType> types = dictArchiveTypeService.getAll();
		writeJson(JsonPluginsUtil.beanListToJson(types));
	}
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExistName = dictArchiveTypeService.hasTypeName(model.getArchiveTypeName());
		boolean isExistCode = dictArchiveTypeService.hasTypeCode(model.getArchiveTypeCode());
		if(!isExistName&&!isExistCode)
		{
			DictArchiveType archiveType = new DictArchiveType();
			archiveType.setArchiveTypeCode(model.getArchiveTypeCode());
			archiveType.setArchiveTypeFlag(model.getArchiveTypeFlag());
			archiveType.setArchiveTypeName(model.getArchiveTypeName());
			
			dictArchiveTypeService.save(archiveType);
			map.put("archiveTypeCode", model.getArchiveTypeCode());
			map.put("archiveTypeFlag", model.getArchiveTypeFlag());
			map.put("archiveTypeName", model.getArchiveTypeName());
			
			map.put("success", true);
			map.put("msg", "添加成功");
		}else {
			map.put("success", false);
			if(isExistName)
			{
				map.put("msg", "类别名已经存在");
			}
			if(isExistCode)
			{
				map.put("msg", "类别代码已经存在");
			}
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		DictArchiveType archiveType = dictArchiveTypeService.getByArchiveTypeCode(oldArchiveTypeCode);
		archiveType.setArchiveTypeCode(model.getArchiveTypeCode());
		archiveType.setArchiveTypeFlag(model.getArchiveTypeFlag());
		archiveType.setArchiveTypeName(model.getArchiveTypeName());
		
		dictArchiveTypeService.update(archiveType);
		map.put("archiveTypeCode", model.getArchiveTypeCode());
		map.put("archiveTypeFlag", model.getArchiveTypeFlag());
		map.put("archiveTypeName", model.getArchiveTypeName());
		
		map.put("msg", "更新成功");
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		dictArchiveTypeService.deleteByTypeCode(model.getArchiveTypeCode());
		map.put("success", true);
		map.put("msg", "删除成功");
		writeES("删除一个档案类别", 901, "DictArchiveType", model.getArchiveTypeCode());
		
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
