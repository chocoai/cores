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
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblCustomer_Json;
import com.lanen.model.contract.TblRegion;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblRegionService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户信息Action
 * @author 黄国刚
 *
 */

@Controller
@Scope("prototype")
public class TblCustomerAction extends BaseAction<TblCustomer>{

	private static final long serialVersionUID = -3651859491446943503L;
	@Resource
	private TblCustomerService tblCustomerService;
	@Resource
	private TblRegionService tblRigonService;
	//当前选择的地区Id
	private String regionId ;
	//客户信息
	private TblCustomer tblCustomer;
	//客户id
	private String id;
	private String cuid;//传到前台的 wan
	
	//签字类型
	private String esType;
	
	//搜索内容
	private String content;
	
	//进入的是addOrEdit页面
	private String addOrEdit;
	/**进入list页面*/
	public String list() throws Exception{
		TblRegion tblRigon= tblRigonService.getById(model.getRegionId());
		if(null != tblRigon){
			int level = tblRigon.getLevel();
			if(level != 1){
				String pid = tblRigon.getPid();
				ActionContext.getContext().put("level1pid", pid);
				TblRegion tblRigon1= tblRigonService.getById(pid);
				int level2 = tblRigon1.getLevel();
				if(level2 == 2 ){
					String pid2 = tblRigon1.getPid();
					ActionContext.getContext().put("level2pid", pid2);
				}else{
					ActionContext.getContext().put("level2pid", "");
				}
			}else{
				ActionContext.getContext().put("level1pid", "");
			}
		}
		int addContract = 0;
		boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
		if(add){
			addContract = 1;
		}
		ActionContext.getContext().put("addContract", addContract);
		return "list";
	}
	/**加载数据*/
	public void loadList(){
	     String reader = getCurrentRealName();
		 List<TblCustomer_Json> list = tblCustomerService.getByRegionIdCustomerList(model.getRegionId(),true, reader);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 map.put("cuid",cuid+"");
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	/**根据客户名模糊查询*/
	public void selectCustomerloadList(){
		System.out.println(model.getCustomerName());
		 List<TblCustomer_Json> list = tblCustomerService.getByCustomerNameCustomerList(model.getCustomerName());
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
		
	}
 
   /**获得供试品类型*/
   public void getAllDictTestItemType(){
	  List<DictTestItemType> list1  = tblCustomerService.getAllDictTestItemTypes();
	  List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		ComboTreeModel ctm =null;
		for(DictTestItemType obj:list1){
			ctm = new ComboTreeModel();
			ctm.setId(obj.getTiCode());
			ctm.setText(obj.getTiType());
			ctm.setIconCls("icon-space");
			list.add(ctm);
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		writeJson(json);  
  }
    
	public void getRegionIds(){
		TblCustomer customer = tblCustomerService.getById(model.getId());
		System.out.println(model.getId());
		String regionId=customer.getRegionId();
		String pid= "";
		String pid2= "";
		pid=tblCustomerService.getPid(regionId);
		if( !pid.equals("")&& pid != null){
			pid2=tblCustomerService.getPid(pid);
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("regionId", regionId);
		map.put("pid", pid);
		map.put("pid2", pid2);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public void todEitCustomer(){
		TblCustomer customer = tblCustomerService.getById(model.getId());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",customer.getId());
		map.put("address",customer.getAddress());
		map.put("customerName",customer.getCustomerName());// 客户名称
		map.put("customerType",customer.getCustomerType());	// 客户类型  1 厂家 2委托方
		map.put("regionId",customer.getRegionId());// 地区ID   (地区表主键)
		map.put("address",customer.getAddress());// 地址
		map.put("linkman",customer.getLinkman());// 联系人
		map.put("tel",customer.getTel());// 电话
		map.put("mobile",customer.getMobile());// 手机
		map.put("email",customer.getEmail());// 电邮
		map.put("http",customer.getHttp());// 网址
		map.put("fax",customer.getFax());	// 传真
		map.put("postalCode",customer.getPostalCode());// 邮政编码
		map.put("tiCode",customer.getTiCode());// 主要产品类型
		TblRegion tblRigon= tblRigonService.getById(customer.getRegionId());
		int level = tblRigon.getLevel();
		if(level != 1){
			String pid = tblRigon.getPid();
			map.put("level1pid",pid);
			TblRegion tblRigon1= tblRigonService.getById(pid);
			int level2 = tblRigon1.getLevel();
			if(level2 == 2 ){
				String pid2 = tblRigon1.getPid();
				map.put("level2pid",pid2);
			}
		}
		map.put("level", tblRigon.getLevel());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public void setTblCustomer(TblCustomer tblCustomer) {
		this.tblCustomer = tblCustomer;
	}
	public TblCustomer getTblCustomer() {
		return tblCustomer;
	}
	public String getAddOrEdit() {
		return addOrEdit;
	}
	public void setAddOrEdit(String addOrEdit) {
		this.addOrEdit = addOrEdit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getEsType() {
		return esType;
	}
	public void setEsType(String esType) {
		this.esType = esType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
