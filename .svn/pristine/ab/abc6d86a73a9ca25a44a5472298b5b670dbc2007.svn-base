package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.archive.DictArchivePosition;
import com.lanen.model.TblFileIndex;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.service.archive.DictArchivePositionService;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.util.MathUtils;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;
@Controller
@Scope("prototype")
public class TblFileIndexAction extends BaseAction<TblFileIndex> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473051465861378121L;
	@Resource
	private TblFileIndexService tblFileIndexService;
	@Resource
	private TblFileRecordService tblFileRecordService;
	@Resource
	private DictArchiveTypeService dictArchiveTypeService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private DictArchivePositionService dictArchivePositionService;
	
	private Integer index;
	private LinkedList<String> searchHisList;
	
	private String archiveTypeCode;
	
 public void hasOtherInThisPlace()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		if(model.getStorePosition()==null||"".equals(model.getStorePosition()))
		{
			map.put("has",false);
		}else{
			List<String> list = tblFileIndexService.getStudyRecordByPosition(model.getStorePosition(),model.getArchiveCode());
			if(list!=null&&list.size()>0)
			{
				String str = "";
				for (String s:list) {
					str += s+" ";
				}
				map.put("codes", str);
				map.put("has",true);
			}else {
				map.put("has",false);
			}
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	public void changePos()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TblFileIndex fileIndex = tblFileIndexService.getById(model.getArchiveCode());
		if(fileIndex!=null){
			fileIndex.setStorePosition(model.getStorePosition());
			tblFileIndexService.update(fileIndex);
			
			writeES("变更位置", 934, "TblFileIndex", model.getArchiveCode());
			
			map.put("success", true);
		}else{
			map.put("success", false);
			map.put("msg", "该档案不存在！");
		}
		
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public String searchHisList()
	{
		searchHisList = new LinkedList<String>();
		if(index==null||"".equals(index))
			index=0;
		Object searchList = ActionContext.getContext().getSession().get(""+index);
		if(searchList!=null)
		{
			searchHisList.addAll((LinkedList<String>)searchList);
		}
		/*searchHisList.add("a");
		searchHisList.add("a2");
		searchHisList.add("a3");
		searchHisList.add("a4");
		searchHisList.add("a5");
		searchHisList.add("a6");*/
		
		return "searchHisList";
	}
	
	
	public void getArchiveTypes()
	{
		//1：专题；2：QA检查资料；3：SOP资料；4：综合资料；5：仪器资料；6：人员档案；7：行政综合资料；8：合同；9：供试品留样；10标本 11基建
		List<DictArchiveType> types = dictArchiveTypeService.getByArchiveTypeFlag(model.getArchiveTypeFlag());
		writeJson(JsonPluginsUtil.beanListToJson(types));
	}
	
	public void getLastFileOperate()
	{
		String fileOperater = tblFileRecordService.getLastFileOperateByType(model.getArchiveTypeFlag());
		Map<String, Object> map = new HashMap<String, Object>();
		if(fileOperater!=null)
		{
			map.put("last", fileOperater);
		}else {
			map.put("last", "");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	public void getLastFileOperateList()//留样需要获取前十个所有的
	{
		List<Map<String, Object>> fileOperaterList = tblFileRecordService.getLastFileOperateListByType(model.getArchiveTypeFlag());
		Map<String, Object> map = new HashMap<String, Object>();
		if(fileOperaterList!=null)
		{
			map.put("total", fileOperaterList.size());
			map.put("rows", fileOperaterList);
		}else {
			map.put("total", 0);
			map.put("rows", new ArrayList<Map<String, Object>>());
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
	}
	
	
	//一些公共的方法
	public void getMaxArchiveCode() {
		Map<String, Object> map = new HashMap<String, Object>();
		String archiveCode = tblFileIndexService.getMaxCodeByTypeCode(archiveTypeCode);
		
		if(archiveCode==null)
		{
			map.put("archiveCode","XSZK-"+archiveTypeCode);
		}else {
			map.put("archiveCode", MathUtils.add1ToStringInt(archiveCode));
			
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	public void loadArchivePositionTree()
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
				if(arg0!=null&&arg1!=null)
				{
					if(arg0.get("sn")==null&&arg1.get("sn")!=null)
					{
						return -1;
					}else if(arg0.get("sn")!=null&&arg1.get("sn")!=null)
					{
						return (Integer)arg0.get("sn")-(Integer)arg1.get("sn");
					}else if(arg0.get("sn")!=null&&arg1.get("sn")==null){
						return 1;
					}else return 0;
				}else if(arg0==null&&arg1!=null){
					return -1;
				}else if(arg0!=null&&arg1==null)
				{
					return 1;
				}else return 0;
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
	
	public void getUserList()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		/*List<User> users = userService.findByPrivilegeName2("档案管理_查看");
		List<User> users2 = userService.findByPrivilegeName2("档案管理_编辑");
		for (User user:users2) {
			if(!users.contains(user))
			{
				users.add(user);
			}
		}*/
		List<User> users = userService.getUserListByDepartmentId("档案部");//档案部的id和名称都是：档案部
		for(User user:users)
		{
			Map<String,Object> map = new HashMap<String, Object>();
			//map.put("userName", user.getUserName());
			map.put("realName", user.getRealName());
			mapList.add(map);
		}
		writeJson(JsonPluginsUtil.beanListToJson(mapList));
		
	}
	
	private String writeES(String EsTypeDesc,int EsType,String tableName,String dataId)
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
		
		return esLink.getLinkId();
		
	}
	public LinkedList<String> getSearchHisList() {
		return searchHisList;
	}
	public void setSearchHisList(LinkedList<String> searchHisList) {
		this.searchHisList = searchHisList;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getArchiveTypeCode() {
		return archiveTypeCode;
	}
	public void setArchiveTypeCode(String archiveTypeCode) {
		this.archiveTypeCode = archiveTypeCode;
	}
}
