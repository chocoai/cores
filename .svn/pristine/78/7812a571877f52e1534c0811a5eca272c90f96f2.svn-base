package com.lanen.view.action.qa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.annotations.Source;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.qa.DictChkArea;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.qa.DictChkAreaService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class DictChkAreaAction extends BaseAction<DictChkArea> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5758184417696935784L;
	
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictChkAreaService dictChkAreaService;
	
	
	public String list()
	{
		
		return "list";
	}
	
	public void getParentById()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		DictChkArea dictChkArea = dictChkAreaService.getById(model.getAreaID());
		
		map.put("parentAreaID", dictChkArea.getParentAreaID());
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	public void isExistChildArea()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = dictChkAreaService.isExistChildByParentId(model.getAreaID());
		
		map.put("isExistChild", flag);
		map.put("success", true);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void save()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = dictChkAreaService.isExistByName(model.getAreaName());
		if(!flag)
		{
			DictChkArea area = new DictChkArea();
			area.setAreaID(dictChkAreaService.getKey("DictChkArea"));
			area.setAreaName(model.getAreaName());
			area.setParentAreaID(model.getParentAreaID());
			
			dictChkAreaService.save(area);
			map.put("id",area.getAreaID());
			map.put("text", area.getAreaName());
			map.put("parentId", area.getParentAreaID());
			
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("msg", "区域名称已经存在！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void editSave()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = dictChkAreaService.isExistByNameExceptOne(model.getAreaName(),model.getAreaID());
		if(!flag)
		{
			DictChkArea area = dictChkAreaService.getById(model.getAreaID());
			area.setAreaName(model.getAreaName());
			area.setParentAreaID(model.getParentAreaID());
			
			dictChkAreaService.update(area);
			map.put("id",area.getAreaID());
			map.put("text", area.getAreaName());
			map.put("parentId", area.getParentAreaID());
			
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("msg", "区域名称已经存在");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void del()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		dictChkAreaService.delete(model.getAreaID());
		
		writeES("删除一个区域", 829, "DictChkArea", model.getAreaID());
		map.put("success", true);
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	public void loadList()
	{
		List<ComboTreeModel> tree = new ArrayList<ComboTreeModel>();
		
		List<DictChkArea> list = dictChkAreaService.getAll();
		//生成树形结构
		getTree(list,tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
		
		
	}
	public void loadComboTreeList()
	{
		List<ComboTreeModel> tree = new ArrayList<ComboTreeModel>();
		
		ComboTreeModel ctm = new ComboTreeModel();
		ctm = new ComboTreeModel();
		ctm.setId("");
		ctm.setText("");
		
		tree.add(ctm);
		
		List<DictChkArea> list = dictChkAreaService.getAll();
		//生成树形结构
		getTree(list,tree);
		
		String json = JsonPluginsUtil.beanListToJson(tree);
		writeJson(json);
	}
	public void getTree(List<DictChkArea> list,List<ComboTreeModel> tree)
	{
		List<DictChkArea> noDealList = new ArrayList<DictChkArea>();
		ComboTreeModel ctm = null;
	
		for(int i=0;i<list.size();i++)
		{
			DictChkArea type=list.get(i);
			if(type.getParentAreaID()==null||"".equals(type.getParentAreaID()))
			{
				//没有父类就是第一级，直接加入tree
				ctm = new ComboTreeModel();
				ctm.setId(type.getAreaID());
				ctm.setText(type.getAreaName());
				
				tree.add(ctm);
				
			}else {//有父类的处理
				
				ComboTreeModel parent = getParent(type,tree);
				if(parent!=null)//父类不为空，并且父类在tree中存在
				{
					ComboTreeModel ctmChile = new ComboTreeModel();
					ctmChile.setId(type.getAreaID());
					ctmChile.setText(type.getAreaName());
					if(parent.getChildren()==null)
						parent.setChildren(new ArrayList<ComboTreeModel>());
					parent.setState("closed");
					parent.getChildren().add(ctmChile);
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
	public ComboTreeModel getParent(DictChkArea type,List<ComboTreeModel> tree)
	{
		ComboTreeModel parent = null;
		for(ComboTreeModel model:tree)
		{
			if(model.getId().equals(type.getParentAreaID()))
			{
				parent=model;
				break;
			}
			if(model.getChildren()!=null)
			{
				parent=getParent(type,model.getChildren());
				if(parent!=null)
				{
					break;
				}
			}
		}
		
		return parent;
		
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
}
