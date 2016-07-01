package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItemHis;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.lanen.util.CopyUtil;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**供试品信息-确认
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class TblTestItemAction extends BaseAction<TblTestItem>{

	private static final long serialVersionUID = -5186635829680692253L;
	
	@Resource
	private TblTestItemService tblTestItemService;
	
	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	
	@Resource
	private TblCustomerService tblCustomerService;
	
	//签字service
	@Resource
	private TblESService tblESService;

	@Resource
	private TblESLinkService tblESLinkService;

	@Resource
	private TblContractService tblContractService;
	
	@Resource
	private TblNotificationService tblNotificationService;
	
	private TblTestItem tblTestItem;
	
	private Date startDate;//结束日期
	private Date endDate; //开始日期
	private String tiCode;//供试品列别
	private String tiName;//模糊查询条件
	private String isConfirm;//是否确认     "":全部,"0":未确认,"1":已确认
	
	private String ids;//确认签字方法用
	
	private Integer csponsorIsVender;//提供方即合同出具方
	private String cvenderId;
	private String cvenderName;
	
	private String testitemEditRsn;
	
	/**
	 * list页面
	 * @return
	 */
	public String list (){
		String startTime = DateUtil.getDateAgo(6);
		String endTime = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		
		ActionContext.getContext().put("startTime", startTime);
		ActionContext.getContext().put("endTime", endTime);
		User user = (User)ActionContext.getContext().getSession().get("user");
		boolean falg1  = userService.checkPrivilege(user, "供试品文件-查看");
		boolean falg2  = userService.checkPrivilege(user, "供试品文件-登记");
		boolean falg3  = userService.checkPrivilege(user, "供试品信息-确认");
		ActionContext.getContext().put("chakan", falg1);
		ActionContext.getContext().put("dengji", falg2);
		ActionContext.getContext().put("queren", falg3);
		return "list";
	}
	
	/**加载供试品信息*/
	public void loadtestItemList(){
		User user = (User)ActionContext.getContext().getSession().get("user");
		boolean isReadAll  = userService.checkPrivilege(user, "供试品信息-查看全部");
		String sd = "全部不能查看";//以姓名去匹配，
		if(isReadAll){
			sd = "";//查看全部
		}else{
			boolean isSD  = userService.checkPrivilege(user, "SD");
			if(isSD){
				//如果不能查看全部，SD 还可以查看自己
				sd = user.getRealName();
			}
		}
//		List<TblTestItem_Json> listjson = tblTestItemService.getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD(startDate, endDate, model.getTiCode(),model.getTiName(),isConfirm,sd);
		List<Map<String,Object>> listjson = tblTestItemService.getByStartimeAndEndtimeAndTiCodeConfirmFlagAndSD2(DateUtil.dateToString(startDate, "yyyy-MM-dd"),DateUtil.dateToString(endDate, "yyyy-MM-dd") , model.getTiCode(),model.getTiName(),isConfirm,sd);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", listjson);
		map.put("total", listjson.size());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**课题类别dialog上  供试品类别下拉框 带空白选项*/
	public void loadTestItemAndNOList(){
		List<DictTestItemType> list = dictTestItemTypeService.getAll();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			map =  new HashMap<String,String>();
			map.put("id", "-1");
			map.put("text", "&nbsp;");
			mapList.add(map);
			for(DictTestItemType obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}

	/**
	 * 确认签字
	 */
	public void confirm(){
		
		Json json = new Json();
		if(null != ids && !ids.equals("")){
			String[] testItemIds =ids.split(",");
			if(null != testItemIds && testItemIds.length>0){
				//签名链接
				TblESLink esLink = new TblESLink();
				//电子签名
				TblES es = new TblES();
				String esId = tblTestItemService.getKey("TblES");
				String realName = getCurrentRealName();
				es.setEsId(esId);
				es.setSigner(realName);
				es.setEsType(445);
				es.setEsTypeDesc("供试品确认签字");
				es.setDateTime(new Date());
				
				esLink.setLinkId(tblTestItemService.getKey("TblESLink"));
				esLink.setTableName("TblTestItem");
				esLink.setDataId(testItemIds[0]);
				esLink.setTblES(es);
				esLink.setEsType(445);
				esLink.setEsTypeDesc("供试品确认签字");
				esLink.setRecordTime(new Date());
				boolean falg = false;
				try{
					//保存签字
					tblESService.save(es);
					tblESLinkService.save(esLink);
					//确认
					tblTestItemService.confirm(testItemIds,esId);
				    json.setSuccess(true);
				    falg = true;
				 }catch(Exception e){
				    json.setSuccess(false);
				    json.setMsg("与数据库交互异常");
				    System.out.println("执行失败，出错种类"+e.getMessage()+".");
				}finally{ 
				     System.out.println("执行结束");
				} 
				if(falg){
					//日志和通知
					for(int i = 0 ;i < testItemIds.length; i++ ){
						String testItemId = testItemIds[i];
						TblTestItem tblTestItem = tblTestItemService.getById(testItemId);
						
						Set<String> receiverSet = new HashSet<String>();
			    		String contractCode = tblTestItem.getContractCode();
			    		String tiNo = tblTestItem.getTiNo();
			    		TblContract tblContract = tblContractService.getByCode(contractCode);
			    		if(null != tblContract){
			    			String operator = tblContract.getOperator();
			    			User user = userService.getByRealName(operator);
			    			if(null != user){
			    				String userName = user.getUserName();
			    				receiverSet.add(userName);
			    			}
			    		}
			    		List<?> list =userService.findUserNameRealNameByPrivilegeName("委托管理_编辑");
			    		if(null != list){
			    			for(Object obj:list){
			    				Object[] objs = (Object[]) obj;
			    				receiverSet.add((String)objs[0]);
			    			}
			    		}
			    		if(null != receiverSet && receiverSet.size()>0){
			    			//发通知
			    			writeNotification(contractCode,tiNo,new ArrayList<String>(receiverSet));
			    		}
			    		writeLog("供试品确认签字","合同编号："+contractCode+" 供试品编号："+tiNo);
			    		
					}
				}
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 原tblCustomerAction
	 */
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
	/**加载 样品检测后处理    列表*/
	public void reserveUnit(){
		List<String> list = tblTestItemService.getReserveUnitList();
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
	
	/**
	 * 查看供试品
	 */
	public void todEidtUI(){
		System.out.println(model.getId());
		TblTestItem testItem   = tblTestItemService.getById(model.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",testItem.getId());	// 合同编号
		map.put("contractCode",testItem.getContractCode());	// 合同编号
		map.put("tiNo",testItem.getTiNo());// 供试品编号 手动录入
		map.put("tiCode",testItem.getTiCode());// 供试品编码 从字典读取
		map.put("tiName",testItem.getTiName());	// 供试品名称
		map.put("sponsorIsVender",testItem.getSponsorIsVender());// 委托方即厂家
		map.put("venderId",testItem.getVenderId());// 厂家名称
		map.put("venderName",testItem.getVenderName());// 厂家名称
		map.put("contentLabel", testItem.getContentLabel());//含量/浓度、纯度标签
		map.put("content", testItem.getContent());//含量/浓度、纯度
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
		map.put("reserveNum", testItem.getReserveNum());// 留样量
		map.put("reserveUnit", testItem.getReserveUnit());// 留样量单位
		
		if(null == testItem.getReserveNum() || "".equals(testItem.getReserveNum())){
			//只读留样量
			String reserveNum2 = tblTestItemService.getReserveNum(testItem.getTiNo());
			map.put("reserveNum2", reserveNum2);// 留样量2
		}else{
			map.put("reserveNum2", testItem.getReserveNum()+" "+ testItem.getReserveUnit());// 留样量2
		}
		
		map.put("validityPeriod", DateUtil.dateToString(testItem.getValidityPeriod(),"yyyy-MM-dd") );// 有效期限
		map.put("isFailureDateFlag",testItem.getIsFailureDateFlag());
		map.put("failureDatePrecision",testItem.getFailureDatePrecision());
		
	//	map.put("validityPeriod",testItem.getValidityPeriod());// 有效期限
		map.put("storageCondition", testItem.getStorageCondition());// 存储条件
		map.put("batchNo", testItem.getBatchNo());// 批号
		TblContract tblContract = tblContractService.getByCode(testItem.getContractCode());
		map.put("sponsorIsVenderC", tblContract.getSponsorIsVender());
		map.put("venderIdC", tblContract.getVenderId());
		map.put("venderNameC", tblContract.getVenderName());
		map.put("sponsorId", tblContract.getSponsorId());
		map.put("sponsorName",  tblContract.getSponsorName());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**编辑+签字*/
	public void editSave(){

    	Map<String,Object> map = new HashMap<String,Object>();
    	String tiId=model.getId();
    	TblTestItem testItem   = tblTestItemService.getById(tiId);
					if(nullCheck(tblTestItem)){
			    		//testItem.setContractCode(model.getContractCode());
//			    		if(testItem.getState() == 0){
//			    			testItem.setTiNo(model.getTiNo());
//			    		}
			    		//testItem.setTiCode(model.getTiCode());
			    		//DictTestItemType  dictTestItemType = dictTestItemTypeService.getById(tblTestItem.getTiCode());
			    		//testItem.setTiType(dictTestItemType.getTiType());
//			    		System.out.println(dictTestItemType.getTiType());
			    		testItem.setTiName(tblTestItem.getTiName());
			    		testItem.setSponsorIsVender(tblTestItem.getSponsorIsVender());
			    		testItem.setVenderId(tblTestItem.getVenderId());// 厂家ID
//			    		System.out.println(tblTestItem.getVenderId());
//			    		System.out.println("1");
			    		TblCustomer customer  = tblCustomerService.getById(tblTestItem.getVenderId());
			    		if(null != customer){
			    			String venderName = customer.getCustomerName();
			    			testItem.setVenderName(venderName);// 厂家名称
			    			//String regionId = customer.getRegionId();
			    			//TblRegion tblRegion = tblRegionService.getById(regionId);
			    			String address = customer.getAddress();
			    			//String	venderAddress = "";
			    			//if(tblRegion.getLevel() == 1 || tblRegion.getLevel() == 2){
			    			//	venderAddress = tblRegion.getRegionName() + " "+address;
			    			//}else{
			    			//	String pid = tblRegion.getPid();
			    			//	TblRegion tblRegion1 = tblRegionService.getById(pid);
			    			//	venderAddress = tblRegion1.getRegionName()+","+tblRegion.getRegionName() + " "+address;
			    			//}
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
			    		
			    		testItem.setContentLabel(tblTestItem.getContentLabel());
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
			    		testItem.setReserveNum(tblTestItem.getReserveNum());
			    		testItem.setReserveUnit(tblTestItem.getReserveUnit());
			    		testItem.setValidityPeriod(tblTestItem.getValidityPeriod());
			    		testItem.setIsFailureDateFlag(tblTestItem.getIsFailureDateFlag());
			    		testItem.setFailureDatePrecision(tblTestItem.getFailureDatePrecision());
			    		
			    		//map.put("validityPeriod", DateUtil.dateToString(testItem.getValidityPeriod(), "yyyy-MM-dd"));// 有效期限
			    		testItem.setStorageCondition(tblTestItem.getStorageCondition());
			    		
			    		//签名链接
						TblESLink esLink = new TblESLink();
						//电子签名
						TblES es = new TblES();
						String esId = tblTestItemService.getKey("TblES");
						String realName = getCurrentRealName();
						es.setEsId(esId);
						es.setSigner(realName);
						es.setEsType(445);
						es.setEsTypeDesc("供试品确认签字");
						es.setDateTime(new Date());
						
						esLink.setLinkId(tblTestItemService.getKey("TblESLink"));
						esLink.setTableName("TblContract");
						esLink.setDataId(tblTestItem.getId());
						esLink.setTblES(es);
						esLink.setEsType(445);
						esLink.setEsTypeDesc("供试品确认签字");
						esLink.setRecordTime(new Date());
						
						
			    		
						testItem.setConfirmSign(esId);
						
						
						//更新合同的报告出具方
						TblContract contract = new TblContract();
//						TblContract contract2 =  tblContractService.getByCode(testItem.getContractCode());
//						contract.setSponsorIsVender(csponsorIsVender);
//						contract.setContractCode(testItem.getContractCode());
//						if(csponsorIsVender == 1){
//							contract.setVenderId(contract2.getSponsorId());
//							contract.setVenderAddress(contract2.getSponsorAddress());
//							contract.setVenderEmail(contract2.getSponsorEmail());
//							contract.setVenderFax(contract2.getSponsorFax());
//							contract.setVenderLinkman(contract2.getVenderLinkman());
//							contract.setVenderMobile(contract2.getSponsorMobile());
//							contract.setVenderName(contract2.getSponsorName());
//							contract.setVenderTel(contract2.getSponsorTel());
//						}else{
//							contract.setVenderId(cvenderId);
//							TblCustomer tcustomer = tblCustomerService.getById(cvenderId);
//							contract.setVenderAddress(tcustomer.getAddress());
//							contract.setVenderEmail(tcustomer.getEmail());
//							contract.setVenderFax(tcustomer.getFax());
//							contract.setVenderLinkman(tcustomer.getLinkman());
//							contract.setVenderMobile(tcustomer.getMobile());
//							contract.setVenderName(tcustomer.getCustomerName());
//							contract.setVenderTel(tcustomer.getTel());
//			    		}
					
						boolean falg = false;
						
						try{
							//保存签字
							tblESService.save(es);
							tblESLinkService.save(esLink);
							tblTestItemService.update(testItem,contract);
				    		writeLog("供试品确认签字","合同编号："+testItem.getContractCode()+" 供试品编号："+testItem.getTiNo());
				    		map.put("id",tiId);
				    		map.put("success",true);
							map.put("msg","签字成功");
							falg = true;
						}catch(Exception e){
							map.put("success",false);
							map.put("msg","签字失败");
							falg = false;
						    System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
						    System.out.println("执行结束");
						}
						
						if(falg){
								//发给合同创建者和市场部头   的通知      tblContractService
					    		Set<String> receiverSet = new HashSet<String>();
					    		String contractCode = testItem.getContractCode();
					    		
					    		TblContract tblContract = tblContractService.getByCode(contractCode);
					    		if(null != tblContract){
					    			String operator = tblContract.getOperator();
					    			User user = userService.getByRealName(operator);
					    			if(null != user){
					    				String userName = user.getUserName();
					    				receiverSet.add(userName);
					    			}
					    		}
					    		
					    		List<?> list =userService.findUserNameRealNameByPrivilegeName("委托管理_编辑");
					    		if(null != list){
					    			for(Object obj:list){
					    				Object[] objs = (Object[]) obj;
					    				receiverSet.add((String)objs[0]);
					    			}
					    		}
					    		
					    		if(null != receiverSet && receiverSet.size()>0){
					    			writeNotification(contractCode,testItem.getTiNo(),new ArrayList<String>(receiverSet));
					    		}
						}
			    	
			    	}
    	
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    
	}
	
	/**签字后的编辑保存*/
	public void editSaveAfterSign(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		String tiId=model.getId();
		TblTestItem testItem   = tblTestItemService.getById(tiId);
		
		TblTestItem testItem2   = new TblTestItem();
		try {
			CopyUtil.Copy(testItem, testItem2);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		if(nullCheck(tblTestItem)){
			//testItem.setContractCode(model.getContractCode());
//			    		if(testItem.getState() == 0){
//			    			testItem.setTiNo(model.getTiNo());
//			    		}
			//testItem.setTiCode(model.getTiCode());
			//DictTestItemType  dictTestItemType = dictTestItemTypeService.getById(tblTestItem.getTiCode());
			//testItem.setTiType(dictTestItemType.getTiType());
//			    		System.out.println(dictTestItemType.getTiType());
			testItem.setTiName(tblTestItem.getTiName());
			testItem.setSponsorIsVender(tblTestItem.getSponsorIsVender());
			testItem.setVenderId(tblTestItem.getVenderId());// 厂家ID
//			    		System.out.println(tblTestItem.getVenderId());
//			    		System.out.println("1");
			TblCustomer customer  = tblCustomerService.getById(tblTestItem.getVenderId());
			if(null != customer){
				String venderName = customer.getCustomerName();
				testItem.setVenderName(venderName);// 厂家名称
				//String regionId = customer.getRegionId();
				//TblRegion tblRegion = tblRegionService.getById(regionId);
				String address = customer.getAddress();
				//String	venderAddress = "";
				//if(tblRegion.getLevel() == 1 || tblRegion.getLevel() == 2){
				//	venderAddress = tblRegion.getRegionName() + " "+address;
				//}else{
				//	String pid = tblRegion.getPid();
				//	TblRegion tblRegion1 = tblRegionService.getById(pid);
				//	venderAddress = tblRegion1.getRegionName()+","+tblRegion.getRegionName() + " "+address;
				//}
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
			
			testItem.setContentLabel(tblTestItem.getContentLabel());
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
			testItem.setReserveNum(tblTestItem.getReserveNum());
			testItem.setReserveUnit(tblTestItem.getReserveUnit());
			testItem.setValidityPeriod(tblTestItem.getValidityPeriod());
    		testItem.setIsFailureDateFlag(tblTestItem.getIsFailureDateFlag());
    		testItem.setFailureDatePrecision(tblTestItem.getFailureDatePrecision());
			//map.put("validityPeriod", DateUtil.dateToString(testItem.getValidityPeriod(), "yyyy-MM-dd"));// 有效期限
			testItem.setStorageCondition(tblTestItem.getStorageCondition());
			
			//更新合同的报告出具方(报告出具放改为不可编辑)
			TblContract contract = new TblContract();
//			TblContract contract2 =  tblContractService.getByCode(testItem.getContractCode());
//			contract.setSponsorIsVender(csponsorIsVender);
//			contract.setContractCode(testItem.getContractCode());
//			if(csponsorIsVender == 1){
//				contract.setVenderId(contract2.getSponsorId());
//				contract.setVenderAddress(contract2.getSponsorAddress());
//				contract.setVenderEmail(contract2.getSponsorEmail());
//				contract.setVenderFax(contract2.getSponsorFax());
//				contract.setVenderLinkman(contract2.getVenderLinkman());
//				contract.setVenderMobile(contract2.getSponsorMobile());
//				contract.setVenderName(contract2.getSponsorName());
//				contract.setVenderTel(contract2.getSponsorTel());
//			}else{
//				contract.setVenderId(cvenderId);
//				TblCustomer tcustomer = tblCustomerService.getById(cvenderId);
//				contract.setVenderAddress(tcustomer.getAddress());
//				contract.setVenderEmail(tcustomer.getEmail());
//				contract.setVenderFax(tcustomer.getFax());
//				contract.setVenderLinkman(tcustomer.getLinkman());
//				contract.setVenderMobile(tcustomer.getMobile());
//				contract.setVenderName(tcustomer.getCustomerName());
//				contract.setVenderTel(tcustomer.getTel());
//			}
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
				tblTestItemService.update(testItem,tblTestItemHis,realName,contract);
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
			
//			try{
//				//保存签字
//				tblTestItemService.update(testItem,contract);
//				writeLog("供试品确认签字","合同编号："+testItem.getContractCode()+" 供试品编号："+testItem.getTiNo());
//				map.put("id",tiId);
//				map.put("success",true);
//				map.put("msg","签字成功");
//			}catch(Exception e){
//				map.put("success",false);
//				map.put("msg","签字失败");
//				System.out.println("执行失败，出错种类"+e.getMessage()+".");
//			}finally{ 
//				System.out.println("执行结束");
//			}
			
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
		
	}
	
	
	/**
	 * 发送通知
	 * 
	 * @param
	 */
	private void writeNotification(String contractCode, String tiNo,
			List<String> receiverList) {
		//String sender = getCurrentRealName();
		// 当前时间
		//String currentDate = DateUtil.dateToString(new Date(),
		//		"yyyy年MM月dd日 HH时mm分");
		// 通知消息
		TblNotification tblNotification = new TblNotification();
		tblNotification.setMsgTitle("供试品("+tiNo+")确认签字");
		String msgContent = "供试品(供试品编号:"+tiNo+",合同编号:"+contractCode+")已确认签字,特此提醒";
		tblNotification.setMsgContent(msgContent);
		tblNotification.setMsgType(1);// 系统消息

		tblNotification.setSender(getCurrentRealName());

		tblNotification.setSendTime(new Date());
		tblNotificationService.save(tblNotification, receiverList);

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
	
	///--------------------------------------------
	private boolean nullCheck(TblTestItem obj) {
		boolean flag = true;
		if (stringCherck(obj.getTiName())) {
			flag = false;
		}else if (stringCherck(obj.getContent())) {
			flag = false;
		}else if (stringCherck(obj.getPhysical())) {
			flag = false;
		}
		return flag;
	}
	public boolean stringCherck(String str){
		return null == str || "".equals(str);
	}
	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTiCode() {
		return tiCode;
	}

	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}

	public String getTiName() {
		return tiName;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public TblTestItem getTblTestItem() {
		return tblTestItem;
	}

	public void setTblTestItem(TblTestItem tblTestItem) {
		this.tblTestItem = tblTestItem;
	}

	public Integer getCsponsorIsVender() {
		return csponsorIsVender;
	}

	public void setCsponsorIsVender(Integer csponsorIsVender) {
		this.csponsorIsVender = csponsorIsVender;
	}

	public String getCvenderId() {
		return cvenderId;
	}

	public void setCvenderId(String cvenderId) {
		this.cvenderId = cvenderId;
	}

	public String getCvenderName() {
		return cvenderName;
	}

	public void setCvenderName(String cvenderName) {
		this.cvenderName = cvenderName;
	}

	public String getTestitemEditRsn() {
		return testitemEditRsn;
	}

	public void setTestitemEditRsn(String testitemEditRsn) {
		this.testitemEditRsn = testitemEditRsn;
	}


	
}
