package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblRegion;
import com.lanen.model.contract.TblRigonJson;
import com.lanen.service.contract.TblRegionService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblRegionAction extends BaseAction<TblRegion>{

	private static final long serialVersionUID = 168291290820143494L;

	@Resource
	private TblRegionService tblRigonService;
	
	private String cid;//用于返回时打开指定节点
	private String cuid;
	
	//查询条件
	private String conditionItem;
	
	public String list(){
		ActionContext.getContext().put("cuid", cuid);
		return "list";
	}
	
	public void getTblRigonTreedatagrid(){
		List<TblRegion> list = tblRigonService.getAllRegions();
		List<TblRigonJson> jsonList = new ArrayList<TblRigonJson>();
		for (TblRegion  obj:list) {
			TblRigonJson json = new TblRigonJson();
			json.setId(obj.getId());//id
			json.set_parentId(obj.getPid());//父类id
			json.setLevel(obj.getLevel());//级别
			json.setRegionName(obj.getRegionName());//地区名称
			json.setIconCls("icon-space");//图标
			if(obj.getLevel() == 2){
				if(obj.getRegionName().equals("上海市")||obj.getRegionName().equals("北京市")
						||obj.getRegionName().equals("重庆市")||obj.getRegionName().equals("天津市")
						||obj.getRegionName().equals("香港特别行政区")||obj.getRegionName().equals("澳门特别行政区")){
					
				}else{
					for (TblRegion  obj1:list) {
						if(obj1.getPid().equals(obj.getId())){
							json.setState("closed");
						}
					}
					
				}
				
			}
			jsonList.add(json);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", jsonList);
		if( (null != cid) && (!cid.equals(""))){
			map.put("cid", cid);
		}
		
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	/**加载combobox数据*/
	public void loadComboboxData(){
		if(null == conditionItem){
			conditionItem = "";
		}
		List<Map<String,String>> mapList = tblRigonService.getMapListByName(conditionItem);
		
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		
		writeJson(jsonStr);
	}
	
	/**根据level 查询地区生成下拉选*/
    public void loadLevel1(){
    	String pid = model.getPid();
    	if(pid ==  null || pid.equals("") ){
    		pid = "-1";
    	}
    	List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
    	List<TblRegion> listRegions= tblRigonService.getByRegLevel(model.getLevel(),pid);
    	
		ComboTreeModel ctm =null;
		for(TblRegion region:listRegions){
			ctm = new ComboTreeModel();
			ctm.setId(region.getId());
			ctm.setText(region.getRegionName());
			ctm.setIconCls("icon-space");
			list.add(ctm);
			
		}
//		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
//		System.out.print(json);
		writeJson(json);
		
	}
    
    /**根据level 查询地区生成下拉选 查询已存在客户的地区*/
    public void loadHaveLevel1(){
    	String pid = model.getPid();
    	if(pid ==  null || pid.equals("") ){
    		pid = "-1";
    	}
    	List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
    	List<TblRegion> listRegions= tblRigonService.getByHaveCutRegLevel(model.getLevel(),pid);
    	
		ComboTreeModel ctm =null;
		for(TblRegion region:listRegions){
			ctm = new ComboTreeModel();
			ctm.setId(region.getId());
			ctm.setText(region.getRegionName());
			ctm.setIconCls("icon-space");
			list.add(ctm);
			
		}
//		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
//		System.out.print(json);
		writeJson(json);
		
	}
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getConditionItem() {
		return conditionItem;
	}

	public void setConditionItem(String conditionItem) {
		this.conditionItem = conditionItem;
	}

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	
	

}
