package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblStudyScheduleNode;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.contract.TblStudyScheduleNodeService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.NumberValidationUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class TblStudyScheduleNodeAction extends BaseAction<TblStudyScheduleNode>{

	private static final long serialVersionUID = -5105991508849657294L;

	@Resource
	private TblStudyScheduleNodeService tblStudyScheduleNodeService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	
	private String _nodeName;
	/**转到list页面*/
	public String list() throws Exception{
		if(null != model.getStudyTypeCode()){
			ActionContext.getContext().put("studyTypeCode", model.getStudyTypeCode());
			DictStudyType dictStudyType = dictStudyTypeService.getById(model.getStudyTypeCode());
			if(null != dictStudyType ){
				ActionContext.getContext().put("studyName", dictStudyType.getStudyName());
			}
		}
		return "list";
	}
	
	/**加载studyNodeTable 表格数据*/
	public void loadList() throws Exception{
		
		String jsonStr ="[]";
		if(null != model.getStudyTypeCode() && !"".equals(model.getStudyTypeCode())){
			List<TblStudyScheduleNode> list= tblStudyScheduleNodeService.getListByStudyTypeCode(model.getStudyTypeCode());
			jsonStr = JsonPluginsUtil.beanListToJson(list);
		}
		writeJson(jsonStr);
	}
	/**加载nodeNameCombobox列表*/
	public void loadNodeName(){
		List<String> nodeNameList = tblStudyScheduleNodeService.getNoDefaultNodeNameList();
		if(null != nodeNameList){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map = null;
			for(String nodeName :nodeNameList){
				map = new HashMap<String,String>();
				map.put("id", nodeName);
				map.put("text", nodeName);
				mapList.add(map);
			}
			
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}
	}
	
	/**检查nodeName是否存在*/
	public void checkNodeName(){
		if(null != model.getStudyTypeCode() && !"".equals(model.getStudyTypeCode())&&
			null != model.getNodeName() && !"".equals(model.getNodeName())){
			boolean isExist = tblStudyScheduleNodeService.isExistNodeName(model.getStudyTypeCode(),model.getNodeName());
			if(isExist){
				writeJson("false");
			}else{
				writeJson("true");
			}
		}else{
			writeJson("false");
		}
	}
	/**添加保存*/
	public void addSave(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(checkData()){
			//判读是否已经初始化
			boolean isHasInit =  tblStudyScheduleNodeService.isHasInit(model.getStudyTypeCode());
			if(!isHasInit){
				//初始化studyNode
				tblStudyScheduleNodeService.initStudyNode(model.getStudyTypeCode());
			}
			
			TblStudyScheduleNode tblStudyScheduleNode = new TblStudyScheduleNode();
			String id = tblStudyScheduleNodeService.getKey();
			tblStudyScheduleNode.setId(id);
			tblStudyScheduleNode.setDefaultNode(0);
			tblStudyScheduleNode.setNodeName(_nodeName);
			
			tblStudyScheduleNode.setNodeSn(tblStudyScheduleNodeService.getNextNodeSn(model.getNodeSn(),model.getStudyTypeCode()));
			
			tblStudyScheduleNode.setPlanDays(model.getPlanDays());
			tblStudyScheduleNode.setStudyTypeCode(model.getStudyTypeCode());
			tblStudyScheduleNodeService.save(tblStudyScheduleNode);
			
			map.put("id", id);
			map.put("success", true);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**编辑保存*/
	public void editSave(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(checkData()){
			//判读是否已经初始化
			boolean isHasInit =  tblStudyScheduleNodeService.isHasInit(model.getStudyTypeCode());
			String id = "";
			if(!isHasInit){
				//初始化studyNode
				tblStudyScheduleNodeService.initStudyNode(model.getStudyTypeCode());
				
//				id = tblStudyScheduleNodeService.getKey();
				
				TblStudyScheduleNode tblStudyScheduleNode = tblStudyScheduleNodeService.getByNodeNameStudyTypeCode(_nodeName,model.getStudyTypeCode());
				if(null != tblStudyScheduleNode){
					id = tblStudyScheduleNode.getId();
					tblStudyScheduleNode.setNodeName(_nodeName);
					tblStudyScheduleNode.setPlanDays(model.getPlanDays());
					tblStudyScheduleNodeService.update(tblStudyScheduleNode);
				}
			}else{
				id = model.getId();
				TblStudyScheduleNode tblStudyScheduleNode = tblStudyScheduleNodeService.getById(model.getId());
				tblStudyScheduleNode.setNodeName(_nodeName);
				tblStudyScheduleNode.setPlanDays(model.getPlanDays());
				tblStudyScheduleNodeService.update(tblStudyScheduleNode);
			}
			map.put("id", id);
			map.put("success", true);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**删除数据*/
	public void delete(){
		if(null != model.getId() && !"".equals(model.getId())){
			TblStudyScheduleNode tblStudyScheduleNode = tblStudyScheduleNodeService.getById(model.getId());
			if(null != tblStudyScheduleNode && tblStudyScheduleNode.getDefaultNode() != 1 
					&& !tblStudyScheduleNode.getStudyTypeCode().equals("@@@@@@")){
				tblStudyScheduleNodeService.delete(model.getId());
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**上移*/
	public void upNode(){
		if(null != model.getId() && !"".equals(model.getId())){
			TblStudyScheduleNode tblStudyScheduleNode = tblStudyScheduleNodeService.getById(model.getId());
			
			if(null != tblStudyScheduleNode && tblStudyScheduleNode.getDefaultNode() != 1 
					&& !tblStudyScheduleNode.getStudyTypeCode().equals("@@@@@@")){
				
				Map<String,Object> map = new HashMap<String,Object>();
				if(tblStudyScheduleNodeService.upNode(model.getId())){
					map.put("id", model.getId());
					map.put("success", true);
				}
				String jsonStr = JsonPluginsUtil.beanToJson(map);
				writeJson(jsonStr);
			}
		}
	}
	/**下移*/
	public void downNode(){
		if(null != model.getId() && !"".equals(model.getId())){
			TblStudyScheduleNode tblStudyScheduleNode = tblStudyScheduleNodeService.getById(model.getId());
			
			if(null != tblStudyScheduleNode && tblStudyScheduleNode.getDefaultNode() != 1 
					&& !tblStudyScheduleNode.getStudyTypeCode().equals("@@@@@@")){
				
				Map<String,Object> map = new HashMap<String,Object>();
				if(tblStudyScheduleNodeService.downNode(model.getId())){
					map.put("id", model.getId());
					map.put("success", true);
				}
				String jsonStr = JsonPluginsUtil.beanToJson(map);
				writeJson(jsonStr);
			}
		}
	}
	/**检查录入数据*/
	private boolean checkData(){
		if(null != model){
			if(null == model.getStudyTypeCode() || "".equals(model.getStudyTypeCode())){
				return false;
			}else if(null == _nodeName || "".equals(_nodeName)){
				return false;
			}else if(null != model.getPlanDays()&& !"".equals(model.getPlanDays()) && !NumberValidationUtils.isWholeNumber( model.getPlanDays())){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	
	
	//------------------------------
	public String get_nodeName() {
		return _nodeName;
	}
	
	public void set_nodeName(String nodeName) {
		_nodeName = nodeName;
	}
	
}
