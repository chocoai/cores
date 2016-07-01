package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblCustomer_Json;
import com.lanen.model.contract.TblRegion;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblRegionService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
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
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblLogService tblLogService;

	private String regionId ;//当前选择的地区Id
	private TblCustomer tblCustomer;	//客户信息
	private String id; 	//客户id
	private String cuid;//传到前台的 wan
	private String esType;	//签字类型
	private String content;//搜索内容
	private String addOrEdit;//进入的是addOrEdit页面
	
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
		 boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
	     String reader = getCurrentRealName();
		 List<TblCustomer_Json> list = tblCustomerService.getByRegionIdCustomerList(model.getRegionId(),readAll, reader);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 map.put("cuid",cuid+"");
		 map.put("total", list.size());
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	/**
	 * 模糊查询
	 */
	public void seach(){
		 // 传入     content  客户名或联系人或手机或电话
		 boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
		 String reader = getCurrentRealName();
		 List<TblCustomer_Json> list = tblCustomerService.getCustomerByNameOrIinkmanOrTelOrmobile(content,readAll, reader);
		 Map<String,Object> map = new HashMap<String,Object>();
	     map.put("rows", list);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	
	/**根据客户名模糊查询*/
	public void selectCustomerloadList(){
		 List<TblCustomer_Json> list = tblCustomerService.getByCustomerNameCustomerList(model.getCustomerName());
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", list);
		 String json = JsonPluginsUtil.beanToJson(map);
		 writeJson(json);
	}
	
	/**添加客户信息*/
	public void add() throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		tblCustomer.setId(tblCustomerService.getKey());
		tblCustomer.setDeleteFlag(0);
		if(nullCheck(tblCustomer)){
			tblCustomerService.save(tblCustomer);
			map.put("success",true);
			map.put("msg","添加成功");
		}
		String rid = tblCustomer.getRegionId();
		map.put("obj", rid);
		map.put("id", tblCustomer.getId());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**将客户信息记录改为删除状态，即deleteFlag设置为1*/
	public void delete() throws Exception{
		Json json=new Json();
		TblCustomer t1=tblCustomerService.getById(model.getId());
		
		//电子签名
		TblES es = new TblES();
		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		es.setSigner(tempUser.getRealName());
		es.setEsType(600);
		es.setEsTypeDesc("客户信息表客户信息删除完毕签字确认");
		es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		
		//签名链接
		TblESLink esLink = new TblESLink();
		esLink.setTableName("TblCustomer");
		esLink.setDataId(t1.getId());
		esLink.setTblES(es);
		esLink.setEsType(600);
		esLink.setEsTypeDesc("客户信息表客户信息删除完毕签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		try{
		  //保存签字链接和签字内容
		  tblESService.save(es);
		  tblESLinkService.save(esLink);
		  //先签字后执行删除操作
		  t1.setDeleteFlag(1);
		  tblCustomerService.update(t1);
		  json.setSuccess(true);
		  json.setMsg(t1.getRegionId());
		  //日志录入
		  writeLog("客户删除","客户："+t1.getCustomerName());
		}catch(Exception e){
		  json.setSuccess(false);
		  System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	
	private void writeLog(String operatType,String operatContent){
		  //记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("客户信息");
		  User user = (User) ActionContext.getContext().getSession().get("user");
		  if(null!=user){
			  tblLog.setOperator(user.getRealName());
		  }
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	/**修改客户信息*/
	public void edit() throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		TblCustomer t1=tblCustomerService.getById(tblCustomer.getId());
		if(nullCheck(tblCustomer)){
			t1.setCustomerName(tblCustomer.getCustomerName());
			t1.setCustomerType(tblCustomer.getCustomerType());
			t1.setRegionId(tblCustomer.getRegionId());
			t1.setAddress(tblCustomer.getAddress());
			t1.setLinkman(tblCustomer.getLinkman());
			t1.setTel(tblCustomer.getTel());
			t1.setMobile(tblCustomer.getMobile());
			t1.setEmail(tblCustomer.getEmail());
			t1.setHttp(tblCustomer.getHttp());
			t1.setFax(tblCustomer.getFax());
			t1.setPostalCode(tblCustomer.getPostalCode());
			t1.setTiCode(tblCustomer.getTiCode());
			tblCustomerService.update(t1);
			map.put("success",true);
			map.put("msg","修改成功");
		}
		String rid = tblCustomer.getRegionId();
		map.put("obj", rid);
		map.put("id", tblCustomer.getId());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
 
  /**供试品类型下拉选*/
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
	
	private boolean nullCheck(TblCustomer obj) {
		boolean flag = true;
		if(stringCherck(obj.getCustomerName())){
			flag = false;
		}else if (obj.getCustomerType() != 1 && obj.getCustomerType()  != 2) {
			flag = false;
		}else if (stringCherck(obj.getRegionId())) {
			flag = false;
		}else{
			
		}
		return flag;
	}
    
	/**获取该用户的地区ID 以及父类的地区ID*/
	public void getRegionIds(){
		TblCustomer customer = tblCustomerService.getById(model.getId());
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
	
	/**跳转到编辑用户的界面*/
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
	
	/**检查用户名的唯一性*/
	public void  checkcustomerName(){
		if (null != model.getCustomerName() && !"".equals(model.getCustomerName())) {
			boolean isExist;
			isExist = tblCustomerService.isExistByCustomerName(model.getCustomerName());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}
	
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
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
