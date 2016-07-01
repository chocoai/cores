package com.lanen.view.action;

import java.util.ArrayList;
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
import com.lanen.model.TblFileIndex;
import com.lanen.model.User;
import com.lanen.model.archive.DictArchiveType;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.service.archive.DictArchiveTypeService;
import com.lanen.service.archive.TblFileIndexService;
import com.lanen.service.archive.TblFileRecordService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
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
	
	private Integer index;
	private LinkedList<String> searchHisList;
	
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
}
