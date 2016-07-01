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
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.archive.DictSoptype;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.QAFileType;

import com.lanen.service.UserService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.DictSOPTypeService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;


@Controller
@Scope("prototype")
public class DictSOPTypeAction extends BaseAction<DictSoptype> {
	
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
	private DictSOPTypeService dictSOPTypeService;
	
	public String list()
	{
		
		return "list";
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExist = dictSOPTypeService.isExist(model.getSopname(),model.getSoptypeCode());
		if(!isExist)
		{
			DictSoptype soptype = new DictSoptype();
			String key = dictSOPTypeService.getKey("DictSoptype");
			soptype.setId(key);
			soptype.setPid(model.getPid());
			Integer sn = dictSOPTypeService.getMaxSnByPid(model.getPid());
			soptype.setSn(sn+1);
			soptype.setSopname(model.getSopname());
			soptype.setSoptypeCode(model.getSoptypeCode());
			
			dictSOPTypeService.save(soptype);
			
			map.put("id", key);
			map.put("pid", soptype.getPid());
			map.put("sn", soptype.getSn());
			map.put("sopName", soptype.getSopname());
			map.put("soptypeCode", soptype.getSoptypeCode());
				
			map.put("success", true);
			map.put("msg", "添加成功");
			
		}else {
			map.put("success", false);
			map.put("msg", "SOP类别名或代码已经存在");
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void update()
	{
		boolean isExist = dictSOPTypeService.isExistExceptOne(model.getId(),model.getSopname(),model.getSoptypeCode());
		Map<String, Object> map = new HashMap<String, Object>();
		if(!isExist)
		{
			DictSoptype soptype = dictSOPTypeService.getById(model.getId());
			
			soptype.setPid(model.getPid());
			soptype.setSopname(model.getSopname());
			soptype.setSoptypeCode(model.getSoptypeCode());
			
			dictSOPTypeService.update(soptype);
			
			map.put("id", soptype.getId());
			map.put("pid", soptype.getPid());
			map.put("sn", soptype.getSn());
			map.put("sopName", soptype.getSopname());
			map.put("soptypeCode", soptype.getSoptypeCode());
			
			map.put("msg", "更新成功");
			map.put("success", true);
		}else {
			map.put("msg", "该SOP类别名称或代码已经存在");
			map.put("success", false);
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void delete()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		dictSOPTypeService.delete(model.getId());
		map.put("success", true);
		map.put("msg", "删除成功");
		writeES("删除一个SOP类别", 902, "DictArchiveType", model.getId());
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void loadComboTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		//id;text; state ; children; iconCls ;
		
		List<DictSoptype> list = dictSOPTypeService.findAll();
		DictSoptype black = new DictSoptype();
		black.setId("");
		black.setPid("");
		black.setSn(1);
		black.setSopname("");
		black.setSoptypeCode("");
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
		
		List<DictSoptype> list = dictSOPTypeService.findAll();
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

			public int compare(Map<String, Object> arg0,Map<String, Object> arg1) {
				if(arg0!=null&&arg1!=null)
				{
					if(arg0.get("sn")!=null&&arg1.get("sn")!=null)
					{
						return (Integer)arg0.get("sn")-(Integer)arg1.get("sn");
					}else if(arg0.get("sn")==null&&arg1.get("sn")!=null){
						return 1;
					}else if(arg0.get("sn")!=null&&arg1.get("sn")==null){
						return -1;
					}else{
						return 0;
					}
				}else if(arg0==null&&arg1!=null){
					return 1;
				}else if(arg0!=null&&arg1==null){
					return -1;
				}else{
					return 0;
				}
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
	public void getTree(List<DictSoptype> list,List<Map<String, Object>> tree)
	{
		List<DictSoptype> noDealList = new ArrayList<DictSoptype>();
		Map<String, Object> ctm = null;
		
		for(int i=0;i<list.size();i++)
		{
			DictSoptype type=list.get(i);
			if(type.getPid()==null||"".equals(type.getPid()))
			{//没有父类的直接加进去
				ctm = new HashMap<String, Object>();
				ctm.put("id",type.getId());
				ctm.put("sn", type.getSn());
				ctm.put("pid",type.getPid());
				ctm.put("text", type.getSopname());
				ctm.put("sopTypeCode", type.getSoptypeCode());
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
					ctmChile.put("text",type.getSopname());
					ctmChile.put("sopTypeCode", type.getSoptypeCode());
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
	public Map<String, Object> getParent(DictSoptype type,List<Map<String, Object>> tree)
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
	
	public void isExistChildSOPType()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = dictSOPTypeService.isExistChild(model.getId());
		
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
