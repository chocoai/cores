package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblContractHis;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItemHis;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.lanen.util.CopyUtil;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
/**
 * 供试品
 * @author wan
 *
 */
@Controller
@Scope("prototype")
public class TblTestItemAction extends BaseAction<TblTestItem>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5186635829680692253L;
	
	
	/**
	 * 供试品编辑原因
	 */
	private String testitemEditRsn;
	
	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	@Resource
	private TblCustomerService tblCustomerService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private TblContractService tblContractService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblLogService tblLogService;
	
	private TblTestItem tblTestItem;
	
	/**供试品添加*/
	public void addTblTestItem(){
		Map<String,Object> map = new HashMap<String,Object>();
		String addId=tblTestItemService.getKey();
		tblTestItem.setId(addId);
		String tiCode = tblTestItem.getTiCode();
		DictTestItemType  dictTestItemType = dictTestItemTypeService.getById(tiCode);
		tblTestItem.setTiType(dictTestItemType.getTiType());
		TblCustomer customer  = tblCustomerService.getById(tblTestItem.getVenderId());
		if(null != customer){
			String venderName = customer.getCustomerName();
			tblTestItem.setVenderName(venderName);// 厂家名称
			String address = customer.getAddress();
			tblTestItem.setVenderAddress(address);// 厂家地址
			tblTestItem.setVenderLinkman(customer.getLinkman());// 厂家联系人
			tblTestItem.setVenderTel(customer.getTel());// 厂家电话
			if(null != customer.getMobile() &&  !customer.getMobile().equals("")){
				tblTestItem.setVenderMobile(customer.getMobile());
			}
			if(null != customer.getEmail() &&  !customer.getEmail().equals("")){
				tblTestItem.setVenderEmail(customer.getEmail());
			}
			if(null != customer.getFax() && !customer.getFax().equals("")){
				tblTestItem.setVenderFax(customer.getFax());
			}
		}
		if(nullCheck(tblTestItem)){
			try{
				tblTestItemService.save(tblTestItem);
				writeLog("供试品添加","合同编号："+tblTestItem.getContractCode()+" 供试品编号："+tblTestItem.getTiNo());
				map.put("success",true);
				map.put("msg","添加成功");
				map.put("id", addId);
			}catch(Exception e){
				 map.put("success", false);
				 map.put("msg", "与数据库交互异常");
				 System.out.println("执行失败，出错种类"+e.getMessage()+".");
			}finally{ 
				 System.out.println("执行结束");
			} 
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
		
	}
	
	/**
	 * 根据合同编号,查询供试品类型(tiCode),
	 * 先看该合同下最后添加的供试品类型,若无,则选择合同客户的主要产品类型
	 */
	public void ticode(){
		Json json = new Json();
		if(null != model.getContractCode() && !"".equals(model.getContractCode())){
			String tiCode = tblTestItemService.getTiCodeByContractCode(model.getContractCode());
			if(null != tiCode && !"".equals(tiCode)){
				json.setSuccess(true);
				json.setMsg(tiCode);
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**加载 含量/浓度/纯度 列表*/
	public void content(){
		List<String> list = tblTestItemService.getContentList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				if(obj.length() < 20){
					map.put("id", obj);
					map.put("text", obj);
					mapList.add(map);
				}
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**加载 含量/浓度/纯度 列表*/
	public void contentLabel(){
		List<String> list = tblTestItemService.getContentLabelList();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		if(null!=list && list.size()>0){
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				if(obj!=null&&obj.length() < 100){
					map.put("id", obj);
					map.put("text", obj);
					mapList.add(map);
				}
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			Map<String,String> map =null;
			
			map =  new HashMap<String,String>();
			
			map.put("id", "含量/浓度/纯度");
			map.put("text", "含量/浓度/纯度");
			mapList.add(map);
			
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
			//writeJson("");
		}
	}
	
	/**加载 外观/状态 列表*/
	public void physical(){
		List<String> list = tblTestItemService.getPhysicalList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				if(obj.length() < 20){
					map.put("id", obj);
					map.put("text", obj);
					mapList.add(map);
				}
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**加载 存储条件 列表*/
	public void storageCondition(){
		List<String> list = tblTestItemService.getStorageConditionList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj);
				map.put("text", obj);
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**加载 稳定性与均一性分析    列表*/
	public void analysis(){
		List<String> list = tblTestItemService.getAnalysisList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj);
				map.put("text", obj);
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**加载 样品检测后处理    列表*/
	public void postTreatment(){
		List<String> list = tblTestItemService.getPostTreatmentList();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(String obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj);
				map.put("text", obj);
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	/**检查*/
	private boolean nullCheck(TblTestItem obj) {
		boolean flag = true;
		if(stringCherck(obj.getContractCode())){
			flag = false;
		}
//		else if (stringCherck(obj.getTiNo())) {
//			flag = false;
//		}
		else if (stringCherck(obj.getTiName())) {
			flag = false;
//		}else if (stringCherck(obj.getContent())) {
//			flag = false;
//		}else if (stringCherck(obj.getPhysical())) {
//			flag = false;
		}else if (stringCherck(obj.getTiName())) {
			flag = false;
		}else if (stringCherck(obj.getTiName())) {
			flag = false;
		}else{
			
		}
		return flag;
	}

	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	
	/**异步校验供试品编号*/
	public void checkTestItemtiNo(){
		if (null != model.getTiNo() && !"".equals(model.getTiNo())) {
			boolean isExist;
			isExist = tblTestItemService.isExistByTestItemtiNo(model.getTiNo());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}
	
	/**获取编辑信息*/
	public void todEidtUI(){
		TblTestItem testItem   = tblTestItemService.getById(model.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",testItem.getId());	// 合同编号
		map.put("contractCode",testItem.getContent());	// 合同编号
		map.put("tiNo",testItem.getTiNo());// 供试品编号 手动录入
		map.put("tiCode",testItem.getTiCode());// 供试品编码 从字典读取
		map.put("tiName",testItem.getTiName());	// 供试品名称
		map.put("sponsorIsVender",testItem.getSponsorIsVender());// 委托方即厂家
		map.put("venderId",testItem.getVenderId());// 厂家名称
		map.put("venderName",testItem.getVenderName());// 厂家名称
		map.put("content", testItem.getContent());//含量/浓度、纯度
		map.put("contentLabel", testItem.getContentLabel());//含量/浓度、纯度标签
		map.put("physical", testItem.getPhysical());//外观/状态
		map.put("sealNo",testItem.getSealNo());// 封样号
		map.put("fileNo", testItem.getFileNo());// 备案号
		map.put("meltPoint", testItem.getMeltPoint());// 熔点
		map.put("boilPoint", testItem.getBoilPoint());// 沸点
		map.put("photolysis", testItem.getPhotolysis());// 光解性
		map.put("volatility", testItem.getVolatility());// 挥发性
		map.put("composition", testItem.getComposition());// 成分
		map.put("density", testItem.getDensity());// 相对密度
		map.put("waterSolubility", testItem.getWaterSolubility());// 水中溶解度
		map.put("waterStability", testItem.getWaterStability());// 水中稳定性
		map.put("solventSolubility", testItem.getSolventSolubility());// 有机溶剂溶解度
		map.put("solventStability", testItem.getSolventStability());// 有机溶剂稳定性
		map.put("ph", testItem.getPh());// PH值
		map.put("securityMeasures", testItem.getSecurityMeasures());// 特殊安全防护措施
		map.put("analysis", testItem.getAnalysis());// 稳定性和均一性分析
		map.put("postTreatment", testItem.getPostTreatment());// 样品检测后处理
		map.put("cas", testItem.getCas());// CAS
		map.put("validityPeriod", DateUtil.dateToString(testItem.getValidityPeriod(), "yyyy-MM-dd") );// 有效期限
		map.put("isFailureDateFlag", testItem.getIsFailureDateFlag());
		map.put("failureDatePrecision", testItem.getFailureDatePrecision());
		map.put("storageCondition", testItem.getStorageCondition());// 存储条件
		map.put("batchNo", testItem.getBatchNo());
		
		TblContract tblContract = tblContractService.getByContractCode(testItem.getContractCode());
		map.put("contractState", tblContract.getContractState());
		map.put("state", testItem.getState());
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**供试品编辑保存*/
    public void editSave(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	String tiId=model.getId();
    	TblTestItem testItem   = tblTestItemService.getById(tiId);
//    	TblTestItem testItem2   = tblTestItemService.getById(tiId);
    	TblTestItem testItem2   = new TblTestItem();
		try {
			CopyUtil.Copy(testItem, testItem2);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
    	//检查是否有写的权限或自己的，且当前合同是否可编辑
		TblContract tblContract = tblContractService.getByCode(testItem.getContractCode());
		if(null != tblContract){
			if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
					||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
					if(nullCheck(tblTestItem)){
			    		testItem.setContractCode(tblTestItem.getContractCode());
			    		if(testItem.getState() == 0){
			    			testItem.setTiNo(tblTestItem.getTiNo());
			    		}
			    		testItem.setTiCode(tblTestItem.getTiCode());
			    		DictTestItemType  dictTestItemType = dictTestItemTypeService.getById(tblTestItem.getTiCode());
			    		testItem.setTiType(dictTestItemType.getTiType());
			    		testItem.setTiName(tblTestItem.getTiName());
			    		testItem.setSponsorIsVender(tblTestItem.getSponsorIsVender());
			    		testItem.setVenderId(tblTestItem.getVenderId());// 厂家ID
			    		TblCustomer customer  = tblCustomerService.getById(tblTestItem.getVenderId());
			    		if(null != customer){
			    			String venderName = customer.getCustomerName();
			    			testItem.setVenderName(venderName);// 厂家名称
			    			String address = customer.getAddress();
			    			testItem.setVenderAddress(address);// 厂家地址
			    			testItem.setVenderLinkman(customer.getLinkman());// 厂家联系人
			    			testItem.setVenderTel(customer.getTel());// 厂家电话
			    			if(null != customer.getMobile() &&  !customer.getMobile().equals("")){
			    				testItem.setVenderMobile(customer.getMobile());
			    			}
			    			if(null != customer.getEmail() &&  !customer.getEmail().equals("")){
			    				testItem.setVenderEmail(customer.getEmail());
			    			}
			    			if(null != customer.getFax() && !customer.getFax().equals("")){
			    				testItem.setVenderFax(customer.getFax());
			    			}
			    		}
			    		
			    		testItem.setContent(tblTestItem.getContent());
			    		testItem.setPhysical(tblTestItem.getPhysical());
			    		testItem.setSealNo(tblTestItem.getSealNo());
			    		testItem.setFileNo(tblTestItem.getFileNo());
			    		testItem.setMeltPoint(tblTestItem.getMeltPoint());
			    		testItem.setBoilPoint(tblTestItem.getBoilPoint());
			    		testItem.setPhotolysis(tblTestItem.getPhotolysis());
			    		testItem.setVolatility(tblTestItem.getVolatility());
			    		testItem.setComposition(tblTestItem.getComposition());
			    		testItem.setDensity(tblTestItem.getDensity());
			    		testItem.setWaterSolubility(tblTestItem.getWaterSolubility());
			    		testItem.setWaterStability(tblTestItem.getWaterStability());
			    		testItem.setSolventSolubility(tblTestItem.getSolventSolubility());
			    		testItem.setSolventStability(tblTestItem.getSolventStability());
			    		testItem.setPh(tblTestItem.getPh());
			    		testItem.setSecurityMeasures(tblTestItem.getSecurityMeasures());
			    		testItem.setAnalysis(tblTestItem.getAnalysis());
			    		testItem.setPostTreatment(tblTestItem.getPostTreatment());
			    		testItem.setCas(tblTestItem.getCas());
			    		testItem.setValidityPeriod(tblTestItem.getValidityPeriod());
			    		testItem.setStorageCondition(tblTestItem.getStorageCondition());
			    		testItem.setBatchNo(tblTestItem.getBatchNo());
			    		
			    		testItem.setFailureDatePrecision(tblTestItem.getFailureDatePrecision());
			    		testItem.setIsFailureDateFlag(tblTestItem.getIsFailureDateFlag());
			    		
			    		testItem.setContentLabel(tblTestItem.getContentLabel());
			    		
			    		if(tblContract.getContractState() != 2 || testItem.getState() != 1){
			    			try{
			    				tblTestItemService.update(testItem);
			    				writeLog("供试品编辑","合同编号："+tblTestItem.getContractCode()+" 供试品编号："+tblTestItem.getTiNo());
			    				map.put("id",tiId);
			    				map.put("success",true);
			    				map.put("msg","修改成功");
			    			}catch(Exception e){
			    				map.put("success", false);
			    				map.put("msg", "与数据库交互异常");
			    				System.out.println("执行失败，出错种类"+e.getMessage()+".");
			    			}finally{ 
			    				System.out.println("执行结束");
			    			} 
			    		}else{
			    			//合同再编辑状态下  编辑供试品
							
							TblTestItemHis tblTestItemHis = new TblTestItemHis();
							try {
								CopyUtil.Copy(testItem2, tblTestItemHis);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							tblTestItemHis.setId(null);
							tblTestItemHis.setOldId(testItem.getId());
							tblTestItemHis.setOperateRsn(testitemEditRsn);
							tblTestItemHis.setOperateTime(new Date());
							tblTestItemHis.setOperateType("编辑");
							String realName = getCurrentRealName();
			    			try{
			    				tblTestItemService.update(testItem,tblTestItemHis,realName);
			    				writeLog("供试品编辑","合同编号："+tblTestItem.getContractCode()+" 供试品编号："+tblTestItem.getTiNo());
			    				map.put("id",tiId);
			    				map.put("success",true);
			    				map.put("msg","修改成功");
			    			}catch(Exception e){
			    				map.put("success", false);
			    				map.put("msg", "与数据库交互异常");
			    				System.out.println("执行失败，出错种类"+e.getMessage()+".");
			    			}finally{ 
			    				System.out.println("执行结束");
			    			} 
			    		}
			    		
			    	
					}
				}else{
//					json.setMsg("无权限此操作");
				}
			}else{
//				json.setMsg("当前合同不可编辑");
			}
		}
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    
	/**删除供试品 */
	public void delTblTestItem(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != model.getId()){
			TblTestItem tblTestItem = tblTestItemService.getById(model.getId());
			//检查是否有写的权限或自己的，且当前合同是否可编辑
			TblContract tblContract = tblContractService.getByCode(tblTestItem.getContractCode());
			if(null != tblContract){
				if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
					boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
					String reader = getCurrentRealName();
					if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
						||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
						
						List<TblStudyItem> tblStudyItemList =  tblStudyItemService.getListByTiNo(tblTestItem.getTiNo());
						if(null == tblStudyItemList || tblStudyItemList.size()<1){
							try{
								tblTestItemService.delete(model.getId());
								writeLog("供试品删除","合同编号："+tblTestItem.getContractCode()+" 供试品编号："+tblTestItem.getTiNo());
								map.put("success",true);
							}catch(Exception e){
								 map.put("success", false);
								 map.put("msg", "与数据库交互异常");
								 System.out.println("执行失败，出错种类"+e.getMessage()+".");
							}finally{ 
								 System.out.println("执行结束");
							} 
						
						}else{
							map.put("success",false);
						}
					}else{
//						json.setMsg("无权限此操作");
						map.put("success",false);
					}
				}else{
//					json.setMsg("当前合同不可编辑");
					map.put("success",false);
				}
			}
			
		}else{
			map.put("success",false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/** 判断是否已确认 */
	public void checkTestItemConfirmSign(){
		Boolean falg =  tblTestItemService.checkTestItemConfirmSign(model.getTiNo());
		Map<String,Object> map = new HashMap<String,Object>();
		if(falg){
			map.put("success",false);
		}else{
			map.put("success",true);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
	      //记录日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("供试品");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public TblTestItem getTblTestItem() {
		return tblTestItem;
	}

	public void setTblTestItem(TblTestItem tblTestItem) {
		this.tblTestItem = tblTestItem;
	}

	public String getTestitemEditRsn() {
		return testitemEditRsn;
	}

	public void setTestitemEditRsn(String testitemEditRsn) {
		this.testitemEditRsn = testitemEditRsn;
	}

	
}
