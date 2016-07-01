package com.lanen.view.action.archive;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;

import com.lanen.model.archive.DictArchivePosition;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;


import com.lanen.service.UserService;
import com.lanen.service.archive.DictArchivePositionService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DictArchivePositionAction extends BaseAction<DictArchivePosition> {
	
	private static final long serialVersionUID = 5639229427938690330L;
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
	private DictArchivePositionService dictArchivePositionService;
	
	public String list()
	{
		
		return "list";
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = dictArchivePositionService.isExist(model.getPositionName());
		if(!isExist)
		{
			DictArchivePosition soptype = new DictArchivePosition();
			String key = dictArchivePositionService.getKey("DictArchivePosition");
			soptype.setId(key);
			soptype.setPid(model.getPid());
			Integer sn = dictArchivePositionService.getMaxSnByPid(model.getPid());
			soptype.setSn(sn+1);
			soptype.setPositionName(model.getPositionName());
			
			
			dictArchivePositionService.save(soptype);
			
			map.put("id", key);
			map.put("pid", soptype.getPid());
			map.put("sn", soptype.getSn());
			map.put("positionName", soptype.getPositionName());
				
			map.put("success", true);
			map.put("msg", "添加成功");
			
		}else {
			map.put("success", false);
			map.put("msg", "位置名已经存在");
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void update()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = dictArchivePositionService.isExistExceptOne(model.getId(),model.getPositionName());
		if(!isExist)
		{
			DictArchivePosition soptype = dictArchivePositionService.getById(model.getId());
			
			soptype.setPid(model.getPid());
			soptype.setPositionName(model.getPositionName());
			
			dictArchivePositionService.update(soptype);
			
			map.put("id", soptype.getId());
			map.put("pid", soptype.getPid());
			map.put("sn", soptype.getSn());
			map.put("positionName", soptype.getPositionName());
			
			map.put("msg", "更新成功");
			map.put("success", true);
		}else {
			map.put("msg", "该位置名称已经存在");
			map.put("success", false);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		dictArchivePositionService.delete(model.getId());
		map.put("success", true);
		map.put("msg", "删除成功");
		writeES("删除一个档案位置类别", 940, "DictArchiveType", model.getId());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void loadComboTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//id;text; state ; children; iconCls ;
		
		List<DictArchivePosition> list = dictArchivePositionService.findAll();
		DictArchivePosition black = new DictArchivePosition();
		black.setId("");
		black.setPid("");
		black.setSn(1);
		black.setPositionName("");
		list.add(0, black);
		//生成树形结构
		getTree(list,tree);
		//按sn排序
		sortBySn(tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
	}
	
	public void loadTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//id;text; state ; children; iconCls ;
		
		List<DictArchivePosition> list = dictArchivePositionService.findAll();
		//生成树形结构
		getTree(list,tree);
		//按sn排序
		sortBySn(tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
		
	}
	public void sortBySn(List<Map<String, Object>> tree)
	{
		Collections.sort(tree, new Comparator<Map<String, Object>>() {

			public int compare(Map<String, Object> arg0,
					Map<String, Object> arg1) {
				return (Integer)arg0.get("sn")-(Integer)arg1.get("sn");
			}
		});
		for(Map<String, Object> map:tree)
		{
			if(map.get("children")!=null)
			{
				List<Map<String, Object>> childList = (List<Map<String, Object>>)map.get("children");
				sortBySn(childList);
			}
				
		}
		
	}
	public void getTree(List<DictArchivePosition> list,List<Map<String, Object>> tree)
	{
		List<DictArchivePosition> noDealList = new ArrayList<DictArchivePosition>();
		Map<String, Object> ctm = null;
		
		for(int i=0;i<list.size();i++)
		{
			DictArchivePosition type=list.get(i);
			if(type.getPid()==null||"".equals(type.getPid()))
			{//没有父类的直接加进去
				ctm = new HashMap<String, Object>();
				ctm.put("id",type.getId());
				ctm.put("sn", type.getSn());
				ctm.put("pid",type.getPid());
				ctm.put("text", type.getPositionName());
				ctm.put("children",new ArrayList<Map<String, Object>>());
				
				tree.add(ctm);
				
			}else {//有父类的处理
				
				Map<String, Object> parent = getParent(type,tree);
				
				if(parent!=null)//父类不为空，并且父类在tree中存在
				{
					Map<String, Object> ctmChile = new HashMap<String, Object>();
					ctmChile.put("id",type.getId());
					ctmChile.put("pid",type.getPid());
					ctmChile.put("sn", type.getSn());
					ctmChile.put("text",type.getPositionName());
					if(parent.get("children")==null&&!"".equals(parent.get("children")))
						parent.put("children",new ArrayList<Map<String, Object>>());
					parent.put("state", "closed");
					((List<Map<String, Object>>)parent.get("children")).add(ctmChile);
				}else {//父类不为空，并且tree中不存在,先处理list中的其他的
					noDealList.add(type);
				}
			}
			
		}
		if(noDealList.size()>0)
		{
			getTree(noDealList, tree);
		}
	}
	public Map<String, Object> getParent(DictArchivePosition type,List<Map<String, Object>> tree)
	{
		Map<String, Object> parent = null;
		for(Map<String, Object> model:tree)
		{
			if(model.get("id").equals(type.getPid()))
			{
				parent=model;
				break;
			}
			if(model.get("children")!=null)
			{
				parent=getParent(type,(List<Map<String,Object>>)model.get("children"));
				if(parent!=null)
				{
					break;
				}
			}
		}
		
		return parent;
		
	}
	
	public void isExistChildArchivePosition()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = dictArchivePositionService.isExistChild(model.getId());
		
		map.put("isExist",flag );
		
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

	
	
}
