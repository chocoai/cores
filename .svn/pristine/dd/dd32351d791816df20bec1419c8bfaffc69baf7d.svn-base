package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.io.CopyUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblContractHis;
import com.lanen.model.contract.TblCustomer;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.contract.TblTestItemAndStudyItemJson;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.ContractPoolNumService;
import com.lanen.service.contract.TblContractAttachmentService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblCustomerService;
import com.lanen.service.contract.TblNotificationService;
//import com.lanen.service.contract.TblRegionService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.util.CopyUtil;
import com.lanen.util.DateUtil;
import com.lanen.util.NumberValidationUtils;
import com.lanen.util.StringUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 合同信息Action
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class TblContractAction extends BaseAction<TblContract>{

	private static final long serialVersionUID = 489362500678063946L;
	//当前  合同表主键
	private String currentId;
	//客户名称
	private String customerName;
	//合同签订日期范围（查询合同列表用     loadContractCode  ）
	private Date minDate;
	private Date maxDate;
	
	private int owner ;    	// 1:是自己合同    0：非自己合同
	private int write;		// 1:写的权限         0：无写的权限   
	/**
	 * 合同编辑原因
	 */
	private String contractEditRsn;
	
	@Resource
	private TblContractService tblContractService;
	@Resource
	private TblCustomerService tblCustomerService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblLogService tblLogService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private UserService userService;
	@Resource
	private ContractPoolNumService contractPoolNumService;
	
	@Resource
	private TblContractAttachmentService tblContractAttachmentService;
	
	
	/**左边导航*/
	public String left() throws Exception{
		String maxDateStr = DateUtil.getNow("yyyy-MM-dd");
		String minDateStr = DateUtil.getDateStrAgoMonth(3);
		ActionContext.getContext().put("maxDate", maxDateStr);
		ActionContext.getContext().put("minDate", minDateStr);
		return "left";
	}
	
	/**左边导航，加载合同编号列表*/
	public void loadContractCode() throws Exception{
		String contractCode = model.getContractCode();
		
		if(null == contractCode || !contractCode.matches("^@.*")){
			contractCode = "";
		}else{
			contractCode = contractCode.replaceAll("@", "");
		}
		String sponsorId = model.getSponsorId();
		if(null==sponsorId || !NumberValidationUtils.isPositiveInteger(sponsorId)){
			sponsorId = "";
		}
		//存放有  success   rows   currentId
		Map<String,Object> map = new HashMap<String, Object>();
		if(null != minDate && null != maxDate){
			if(minDate.after(maxDate)){
				Date t = minDate;
				minDate = maxDate;
				maxDate = t;
			}
			//是否查看所有
			boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
			//读者
			String reader = getCurrentRealName();
			List<Map<String,String>> rowList = tblContractService.getIdCodeMapListByDateContractCodeSponsorId(
					minDate,maxDate,contractCode,sponsorId,reader,readAll);
			map.put("success", true);
			map.put("rows", rowList);
			String currentId ="";
			if(null !=contractCode && !"".equals(contractCode) ){
				currentId =tblContractService.getIdByContractCode(contractCode);
			}else if (null != rowList && rowList.size()>0){
				currentId = rowList.get(0).get("id");
			}
			map.put("currentId", currentId);
			
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		
		writeJson(jsonStr);
	}
	
	/**左边导航，加载合同编号下拉框*/
	public void loadCodeComboboxData() throws Exception{
		
		String contractCode = model.getContractCode();
		
		//是否查看所有
		boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
		//读者
		String reader = getCurrentRealName();
		
		List<Map<String,String>> mapList = tblContractService.getMapListByContractCode(contractCode,reader,readAll);
		
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		
		writeJson(jsonStr);
		
	}
	
	/**左边导航，加载客户名称下拉框*/
	public void loadNameComboboxData() throws Exception{
		if(null == customerName){
			customerName = "";
		}
		//是否查看所有
		boolean readAll = (Boolean) ActionContext.getContext().getSession().get("read");
		//读者
		String reader = getCurrentRealName();
		
		List<Map<String,String>> mapList = tblContractService.getMapListByName(customerName,reader,readAll);
		
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		
		writeJson(jsonStr);
		
	}
	
	/**左边导航，加载合同签订日期区间*/
	public void loadMinMaxDate() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=model.getSponsorId() && !"".equals(model.getSponsorId())){
			//map 存放        {minDate: ""  ,     maxDate:""}
			map = tblContractService.getMinMaxDateMapBySponsorId(model.getSponsorId());
			map.put("success", "true");
		}else if(null!=model.getContractCode() && !"".equals(model.getContractCode())
				&& model.getContractCode().matches("^@.*")){
			//map 存放        {minDate: ""  ,     maxDate:""}    以及nameCombobox 的值
			String contractCode = model.getContractCode().replaceAll("@", "");
			map = tblContractService.getDateRowsMapByContractCode(contractCode);
			map.put("success", "true");
		}
			
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	
	/**合同主操作区域*/
	public String main() throws Exception{
		if(null != currentId){
			model = tblContractService.getById(currentId);
			String contractCode = model.getContractCode();
			//合同附件列表
			List<Map<String,Object>> mapList = tblContractAttachmentService.getMapListByContractCode(contractCode);
			ActionContext.getContext().put("attachmentList", mapList);
			//是否有写的权限
			boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
			String reader = getCurrentRealName();
			if(writeAll){
				write = 1;
			}else{
				write = 0;
			}
			if(reader.equals(model.getOperator())){
				owner = 1;
			}else{
				owner = 0;
			}
			//
			int contractConfrim = 0;   	//  合同提交按钮状态
			int contractEdit = 0;   	//	合同编辑按钮状态
			int contractAgainEdit= 0;   //	合同再编辑按钮状态
			int attachment = 0;   		//	合同附件
			int testItemAdd = 0;   		//	供试品添加
			
			int contractState = model.getContractState();
			if(owner == 1 ){//自己的合同
				switch (contractState) {
				case 0:
						contractConfrim = 1;
						contractEdit = 1;
						contractAgainEdit = 0;
						attachment = 1;
						testItemAdd = 1;
						break;
				case 1:
						contractConfrim = 0;
						contractEdit = 0;
						if(write == 1){
							contractAgainEdit = 1;
						}
						attachment = 0;
						testItemAdd = 0;
					break;
				case 2:
						contractConfrim = 1;
						contractEdit = 1;
						contractAgainEdit = 0;
						attachment = 1;
						testItemAdd = 1;
					break;
				case 3:
					break;
				case -1:
					break;

				default:
					break;
				}
			}else{
				//非自己合同
				switch (contractState) {
				case 0:
						contractConfrim = 0;
						contractEdit = 0;
						contractAgainEdit = 0;
						attachment = 0;
						testItemAdd = 0;
						break;
				case 1:
						contractConfrim = 0;
						contractEdit = 0;
						if(write == 1){
							contractAgainEdit = 1;
						}
						attachment = 0;
						testItemAdd = 0;
					break;
				case 2:
						if(write == 1){
							contractConfrim = 1;
							contractEdit = 1;
							contractAgainEdit = 0;
							attachment = 1;
							testItemAdd = 1;
						}
					break;
				case 3:
					break;
				case -1:
					break;

				default:
					break;
				}
			}
//			int contractConfrim = 0;   	//  合同提交按钮状态
//			int contractEdit = 0;   	//	合同编辑按钮状态
//			int contractAgainEdit= 0;   //	合同再编辑按钮状态
//			int attachment = 0;   		//	合同附件
//			int testItemAdd = 0;   		//	供试品添加
			ActionContext.getContext().put("contractConfrim", contractConfrim);
			ActionContext.getContext().put("contractEdit", contractEdit);
			ActionContext.getContext().put("contractAgainEdit", contractAgainEdit);
			ActionContext.getContext().put("attachment", attachment);
			ActionContext.getContext().put("testItemAdd", testItemAdd);
			//判断是否有登记权限
			int addContract = 0;
			boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
			if(add){
				addContract = 1;
			}
			ActionContext.getContext().put("addContract", addContract);
		}
		return "main";
	}
	
	/**检查合同编号的唯一性*/
	public void checkContractCode() throws Exception{
		if(null!=model.getContractCode() && !"".equals(model.getContractCode())){
			boolean isExist = tblContractService.isExistByContractCode(model.getContractCode());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	
	/**检查合同编号的唯一性(当前id除外)*/
	public void checkContractCodeWithcurrentId() throws Exception{
		if(null!=model.getContractCode() && !"".equals(model.getContractCode())
				&& null != currentId && !"".equals(currentId) ){
				boolean isExist = tblContractService.isExistByContractCode(model.getContractCode(),currentId);
				if(!isExist){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}else{
				writeJson("false");
			}
	}
	
	
	/**添加保存*/
	public void addSave() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		if(dataCheck(model)){
			String id = tblContractService.getKey();
			TblContract tblContract = new TblContract();
			tblContract.setId(id);
			tblContract.setContractCode(model.getContractCode());
			tblContract.setContractName(model.getContractName());
			
			tblContract.setSponsorId(model.getSponsorId());
			tblContract.setSponsorName(model.getSponsorName());
			TblCustomer sponsor = tblCustomerService.getById(model.getSponsorId());
			if(null != sponsor){
				tblContract.setSponsorAddress(sponsor.getAddress());
				tblContract.setSponsorEmail(sponsor.getEmail());
				tblContract.setSponsorFax(sponsor.getFax());
				tblContract.setSponsorLinkman(sponsor.getLinkman());
				tblContract.setSponsorTel(sponsor.getTel());
				tblContract.setSponsorMobile(sponsor.getMobile());
			}
			
			tblContract.setVenderId(model.getVenderId());
			TblCustomer vender = tblCustomerService.getById(model.getVenderId());
			tblContract.setVenderName(vender.getCustomerName());
			if(vender.getCustomerName().equals(model.getSponsorName())){
				tblContract.setSponsorIsVender(1);
			}else{
				tblContract.setSponsorIsVender(0);
			}
			if(null != vender){
				tblContract.setVenderAddress(vender.getAddress());
				tblContract.setVenderEmail(vender.getEmail());
				tblContract.setVenderFax(vender.getFax());
				tblContract.setVenderLinkman(vender.getLinkman());
				tblContract.setVenderTel(vender.getTel());
				tblContract.setVenderMobile(vender.getMobile());
			}
			if(null != model.getContractPrice() && (!model.getContractPrice().equals(""))){
				tblContract.setContractPrice(model.getContractPrice());
				tblContract.setPriceUnit(model.getPriceUnit());
			}else{
				tblContract.setContractPrice("");
				tblContract.setPriceUnit(0);
			}
			tblContract.setSigningDate(model.getSigningDate());
			tblContract.setEffectiveDate(model.getEffectiveDate());
			tblContract.setFinishDate(model.getFinishDate());
			tblContract.setContractState(0);
			tblContract.setOperator(getCurrentRealName());
			tblContract.setRemark(model.getRemark());
			try{
				//保存
				tblContractService.save(tblContract);
				writeLog("合同添加","合同编号："+model.getContractCode());
				map.put("id", id);
				map.put("contractCode", model.getContractCode());
				map.put("success", true);
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
	
	
	/**编辑准备数据*/
	public void editUI() throws Exception{
		
		if(null != currentId && !"".equals(currentId)){
			TblContract tblContract = tblContractService.getById(currentId);
			if(null !=tblContract){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", tblContract.getId());
				map.put("contractCode", tblContract.getContractCode());
				map.put("contractName", tblContract.getContractName());
				map.put("sponsorId", tblContract.getSponsorId());
				map.put("sponsorName", tblContract.getSponsorName());
				map.put("sponsorIsVender", tblContract.getSponsorIsVender());
				map.put("venderId", tblContract.getVenderId());
				map.put("venderName", tblContract.getVenderName());
				map.put("contractPrice", tblContract.getContractPrice());
				map.put("priceUnit", tblContract.getPriceUnit());
				map.put("signingDate", DateUtil.dateToString(tblContract.getSigningDate(), "yyyy-MM-dd") );
				map.put("effectiveDate", DateUtil.dateToString(tblContract.getEffectiveDate(), "yyyy-MM-dd"));
				map.put("finishDate", DateUtil.dateToString(tblContract.getFinishDate(), "yyyy-MM-dd"));
				map.put("remark", tblContract.getRemark());
				//
				map.put("contractState", tblContract.getContractState());
				String jsonStr = JsonPluginsUtil.beanToJson(map);
				writeJson(jsonStr);
			}
		}
	}
	
	/**编辑保存*/
	public void editSave() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String id = model.getId();
		if(dataCheck(model) && null !=id && !"".equals(id) ){
			
			TblContract tblContract = tblContractService.getById(id);
			TblContract tblContract2 = new TblContract();
			CopyUtil.Copy(tblContract, tblContract2);
			if(null != tblContract ){
				//write || owner
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				if(writeAll || tblContract.getOperator().equals(getCurrentRealName())){
					//编辑前合同编号
					String oldContractCode = tblContract.getContractCode();
					if(tblContract.getContractState() == 0){
						tblContract.setContractCode(model.getContractCode());
					}
					tblContract.setContractName(model.getContractName());
					
					tblContract.setSponsorId(model.getSponsorId());
					tblContract.setSponsorName(model.getSponsorName());
					TblCustomer sponsor = tblCustomerService.getById(model.getSponsorId());
					if(null != sponsor){
						tblContract.setSponsorAddress(sponsor.getAddress());
						tblContract.setSponsorEmail(sponsor.getEmail());
						tblContract.setSponsorFax(sponsor.getFax());
						tblContract.setSponsorLinkman(sponsor.getLinkman());
						tblContract.setSponsorTel(sponsor.getTel());
						tblContract.setSponsorMobile(sponsor.getMobile());
					}
					tblContract.setVenderId(model.getVenderId());
					TblCustomer vender = tblCustomerService.getById(model.getVenderId());
					if(vender.getCustomerName().equals(model.getSponsorName())){
						tblContract.setSponsorIsVender(1);
					}else{
						tblContract.setSponsorIsVender(0);
					}
					tblContract.setVenderName(vender.getCustomerName());
					if(null != vender){
						tblContract.setVenderAddress(vender.getAddress());
						tblContract.setVenderEmail(vender.getEmail());
						tblContract.setVenderFax(vender.getFax());
						tblContract.setVenderLinkman(vender.getLinkman());
						tblContract.setVenderTel(vender.getTel());
						tblContract.setVenderMobile(vender.getMobile());
					}
					if(null != model.getContractPrice() && (!model.getContractPrice().equals(""))){
						tblContract.setContractPrice(model.getContractPrice());
						tblContract.setPriceUnit(model.getPriceUnit());
					}else{
						tblContract.setContractPrice("");
						tblContract.setPriceUnit(0);
					}
					tblContract.setSigningDate(model.getSigningDate());
					tblContract.setEffectiveDate(model.getEffectiveDate());
					tblContract.setFinishDate(model.getFinishDate());
					tblContract.setOperator(getCurrentRealName());
					tblContract.setRemark(model.getRemark());
					
					if(tblContract.getContractState() != 2){
						try{
							//保存
							tblContractService.update(tblContract,oldContractCode);
							writeLog("合同编辑","合同编号："+tblContract.getContractCode());
							map.put("id", id);
							map.put("success", true);
						}catch(Exception e){
							map.put("success", false);
							map.put("msg", "与数据库交互异常");
							System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
							System.out.println("执行结束");
						} 
					}else{
						//合同再编辑状态下  编辑
						
						TblContractHis tblContractHis = new TblContractHis();
						
						CopyUtil.Copy(tblContract2, tblContractHis);
						
						tblContractHis.setId(null);
						tblContractHis.setOldId(tblContract.getId());
						tblContractHis.setOperateRsn(contractEditRsn);
						tblContractHis.setOperateTime(new Date());
						tblContractHis.setOperateType("编辑");
						String realName = getCurrentRealName();
						try{
							//合同编辑
							tblContractService.update(tblContract,tblContractHis,realName);
							writeLog("合同编辑","合同编号："+tblContract.getContractCode());
							map.put("id", id);
							map.put("success", true);
						}catch(Exception e){
							map.put("success", false);
							map.put("msg", "与数据库交互异常");
							System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
							System.out.println("执行结束");
						} 
					}
				}
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**合同提交前的检查*/
	public void checkBeforeSubmit(){
		Json json = new Json();
		if(null != currentId && !currentId.equals("")){
			TblContract tblContract = tblContractService.getById(currentId);
			if(null != tblContract){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if(writeAll){
					write = 1;
				}else{
					write = 0;
				}
				if(reader.equals(tblContract.getOperator())){
					owner = 1;
				}else{
					owner = 0;
				}
				
				int contractState = tblContract.getContractState();
				if(contractState == 0 || contractState == 2){
					if((contractState == 0 && owner == 1 ) || 
							(contractState == 2 &&( owner == 1 || write == 1))){
						//检查是否有供试品及对应委托项目
						List<TblTestItem> tblTestItemList=tblTestItemService.getListByContract(tblContract.getContractCode());
						if(null != tblTestItemList && tblTestItemList.size()>0){
							boolean success = true;
							for(TblTestItem tblTestItem : tblTestItemList){
								List<TblStudyItem> tblStudyItemList =  tblStudyItemService.getListByTiNo(tblTestItem.getTiNo());
								if(null == tblStudyItemList || tblStudyItemList.size()<1){
									success = false;
									json.setMsg("请添加供试品("+tblTestItem.getTiNo()+")对应的委托项目");
									break;
								}
							}
							json.setSuccess(success);
						}else{
							json.setMsg("请添加供试品和委托项目");
						}
					}else{
						json.setMsg("无权限此操作");
					}
					
				}else{
					json.setMsg("合同已提交不可重复提交");
				}
			}else{
				json.setMsg("与服务器交互错误");
			}
		}else{
			json.setMsg("与服务器交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/**合同提交*/
	public void submit()throws Exception{
		Json json = new Json();
		if(null != currentId && !currentId.equals("")){
			
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			String realName = getCurrentRealName();
			es.setEsId(tblContractService.getKey("TblES"));
			es.setSigner(realName);
			es.setEsType(601);
			es.setEsTypeDesc("合同提交");
			es.setDateTime(new Date());
			esLink.setLinkId(tblContractService.getKey("TblESLink"));
			esLink.setTableName("TblContract");
			esLink.setDataId(currentId);
			esLink.setTblES(es);
			esLink.setEsType(601);
			esLink.setEsTypeDesc("合同提交");
			esLink.setRecordTime(new Date());
			
			TblContract tblContract = tblContractService.getById(currentId);
			String contractCode = tblContract.getContractCode();
			
			boolean falg = false;
			try{
			   //保存签字
			   tblESService.save(es);
			   tblESLinkService.save(esLink);
			   try{
				 //更新状态
//				 tblContractService.b();
			     tblContractService.updateContractState(contractCode);
//			     tblContractService.r();
			     //写日志
    		     writeLog("合同提交","合同编号："+contractCode);
			     json.setSuccess(true);
				 falg = true;
			   }catch(Exception e){
				     json.setSuccess(false);
					 System.out.println("执行失败，出错种类"+e.getMessage()+".");
			   }
			   //写日志
		       writeLog("合同提交","合同编号："+contractCode);
			 }catch(Exception e){
				 json.setSuccess(false);
				 System.out.println("执行失败，出错种类"+e.getMessage()+".");
			 }finally{
			     System.out.println("执行结束!");
			 }
			 
			 if(falg){
				 //发送通知
				 writeNotification(contractCode);
			 }else{
				 json.setMsg("数据交互错误");
			 }
			
			
		}else{
			json.setMsg("数据交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**发送通知*/
	private void writeNotification(String contractCode) {
		List<TblStudyItem> studyItemList = tblStudyItemService.getByContractCode(contractCode);
		if(null != studyItemList && studyItemList.size()>0){
			String studyNos = "";
			int count =0;
			for(TblStudyItem obj : studyItemList){
				if(obj.getSdState() != 1){
					if("".equals(studyNos)){
						studyNos=obj.getStudyNo();
					}else{
						studyNos =studyNos+","+obj.getStudyNo();
					}
					count++;
				}
			}
			if(!studyNos.equals("") && count>0){
				TblNotification tblNotification = new TblNotification();
				tblNotification.setMsgTitle("专题("+studyNos+")SD待任命提醒");
				String msgContent = "";
				msgContent = msgContent+"合同(编号:"+contractCode+")已提交，有 "+count
				+" 个委托项目（编号:"+studyNos+"）的SD待任命,特此提醒";
				tblNotification.setMsgContent(msgContent);
				tblNotification.setMsgType(1);//系统消息
				tblNotification.setSender(getCurrentRealName());
				tblNotification.setSendTime(new Date());
				//接收者列表
				List<String> receiverList = userService.findUserNameByPrivilegeName("FM");
				tblNotificationService.save(tblNotification,receiverList);
			}
		}
		
		//发送给供试品
		List<TblTestItem>  tblTestItemList =  tblTestItemService.getListByContract(contractCode);
		if(null != tblTestItemList && tblTestItemList.size()>0){
			String testItems = "";
			int count =0;
			for(TblTestItem obj : tblTestItemList){
				if(obj.getConfirmSign() == null ){
					if("".equals(testItems)){
						testItems=obj.getTiNo();
					}else{
						testItems =testItems+","+obj.getTiNo();
					}
					count++;
				}
			}

			TblNotification tblNotification = new TblNotification();
			tblNotification.setMsgTitle("合同("+contractCode+")提交，供试品待确认提醒");
			
			String msgContent = "";
			msgContent = msgContent+"合同(编号:"+contractCode+")已提交,此合同含共有"+count+"个供试品(" +testItems+
					")待确认,特此提醒!";
			tblNotification.setMsgContent(msgContent);
			tblNotification.setMsgType(1);//系统消息
			
			tblNotification.setSender(getCurrentRealName());
			
			tblNotification.setSendTime(new Date());
			//接收者列表
			List<String> receiverList2 =  userService.findUserNameByPrivilegeName("供试品确认-查看");
			tblNotificationService.save(tblNotification,receiverList2);
			
		}
		
	}
	/**合同再编辑*/
	public void againEdit()throws Exception{
		Json json = new Json();
		if(null != currentId && !currentId.equals("")){
			//获取合同
			TblContract tblContract = tblContractService.getById(currentId);
			if(null != tblContract){
				String contractCode = tblContract.getContractCode();
				//是否有读写全部的权限
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				if(writeAll && tblContract.getContractState() == 1){
					//签名链接
					TblESLink esLink = new TblESLink();
					//电子签名
					TblES es = new TblES();
					String realName = getCurrentRealName();
					es.setEsId(tblContractService.getKey("TblES"));
					es.setSigner(realName);
					es.setEsType(602);
					es.setEsTypeDesc("合同置为可编辑");
					es.setDateTime(new Date());
					
					esLink.setLinkId(tblContractService.getKey("TblESLink"));
					esLink.setTableName("TblContract");
					esLink.setDataId(currentId);
					esLink.setTblES(es);
					esLink.setEsType(602);
					esLink.setEsTypeDesc("合同置为可编辑");
					esLink.setRecordTime(new Date());
					
					try{
						//保存签字
						tblESService.save(es);
						tblESLinkService.save(esLink);
						//更新状态为2
						tblContract.setContractState(2);
						tblContractService.update(tblContract);
						json.setSuccess(true);
						//写日志
						writeLog("合同置为可编辑","合同编号："+contractCode);
					}catch(Exception e){
					     json.setSuccess(false);
						 System.out.println("执行失败，出错种类"+e.getMessage()+".");
					}finally{
						json.setMsg("与服务器交互错误");
					}
				
				}else{
					json.setMsg("无此权限");
				}
			}else{
				json.setMsg("与服务器交互错误");
			}
		}else{
			json.setMsg("与服务器交互错误");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/** 加载供试品&委托项目 */
	public void testItemAndStudyItemloadList() {
		List<TblTestItemAndStudyItemJson> listJson = new ArrayList<TblTestItemAndStudyItemJson>();
		if(null != model.getId() && !"".equals(model.getId())){
			TblContract tblContract = tblContractService.getById(model.getId());
			String contractCode = tblContract.getContractCode();
			// 供试品及委托试验列表
			List<?> list = tblContractService.getbyContractCodetestItemAndStudyItem(contractCode);
			
			String tiNos = "";
			if (null != list) {
				for (Object obj : list) {
					Object[] objs = (Object[]) obj;
					String tiNo = (String) objs[2];
					if (!tiNos.contains("@" +tiNo+ "@")) {
						TblTestItemAndStudyItemJson tjson = new TblTestItemAndStudyItemJson();
						tiNos = tiNos + "@" + tiNo + "@";
						tjson.setId((String) objs[0]);// 供试品主键
						tjson.setTiNo(tiNo);// 供试品编号
						tjson.setTiName((String) objs[3]);// 供试品名称
						tjson.setTiType((String) objs[4]);// 供试品类型
						// tjson.setContent((String)objs[6]);//供试品含量
						// tjson.setPhysical((String)objs[9]);//外观
						tjson.setIconCls("icon-space");// 无图标
						String sign = "";
						if( null != objs[62]){
							sign = "<a>,供试品已确认</a>";
						}else{
							sign = ",<a style='color:red;'>供试品未确认</a>";
						}
						
						//显示备注
						tjson.setText(((objs.length<65||(String)objs[64]==null)?"含量:":(String)objs[64]+":") + (String) objs[5] + " , 外观：" + (String) objs[8]+sign);
						tjson.setKind("G");
						// 供试品状态 0 ，1 第61个
						tjson.setTstate((Integer) objs[60]);
						listJson.add(tjson);
					}
					TblTestItemAndStudyItemJson sjson = new TblTestItemAndStudyItemJson();
					if (null != objs[35]) {
						sjson.setIconCls("icon-space");// 无图片
						sjson.setId("s" + (String) objs[35]);// 委托试验主键
						sjson.set_parentId((String) objs[0]);// 公式品Id做未父类id
						if (null != objs[40]) {
							sjson.setTiNo((String) objs[40]);// 课题编号
						}
						String text = "";
                        if(null != objs[63] && !objs[63].equals("")){
                        	text = "要求完成日期:" + DateUtil.dateToString((Date) objs[63], "yyyy-MM-dd")+";";
                        }				
						if (null != objs[44] && !objs[44].equals("")) {
							text = text +  "  SD:" + (String) objs[44];
						}
						sjson.setText(text);
						if ((Integer) objs[41] == 1) {
							sjson.setTiType("GLP");// GLP类型
						} else {
							sjson.setTiType("非GLP");// GLP类型
						}
						sjson.setKind("W");
						// 委托项目状态 0 ，1 第62个
						sjson.setTstate((Integer) objs[61]);
						sjson.setTiName((String) objs[39]);// 委托项目名称
						listJson.add(sjson);
					}
				}
			}
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("rows", listJson);
		 String json = JsonPluginsUtil.beanToJson(map);
		 System.out.println(json);
		 writeJson(json);
	}
	/**检查该客户是否有合同，sponsorId*/
	public void checkCustomer(){
		Json json = new Json();
		if(null != model.getSponsorId() && !"".equals(model.getSponsorId())){
			boolean exist = tblContractService.isExistBySponsorId(model.getSponsorId());
			if(exist){
				json.setSuccess(true);
				json.setMsg("合同存在");
			}else{
				json.setMsg("该客户暂无合同");
			}
		}else{
			json.setMsg("与服务器交互错误");
		}
		 String jsonStr = JsonPluginsUtil.beanToJson(json);
		 writeJson(jsonStr);
	}
	
	
	/**
	 * 数据检查（非空，长度），false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean dataCheck(TblContract obj){
		boolean flag = true;
//		if(!StringUtil.checkStingAndLength(obj.getContractCode(), 50)){
//			flag = false;
//		}else 
		if(!StringUtil.checkStingAndLength(obj.getContractName(), 200)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getSponsorId(), 20)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getSponsorName(), 200)){
			flag = false;
		//}
		//else if(!StringUtil.checkStingAndLength(obj.getVenderId(), 20)){
		//	flag = false;
		//}else if(!StringUtil.checkStingAndLength(obj.getVenderName(), 200)){
		//	flag = false;
		//}
		//else if(!StringUtil.checkStingAndLength(obj.getContractPrice(), 20)){
		//	flag = false;//合同金额 NumberValidationUtils
		//}else if(!NumberValidationUtils.isPositiveRealNumber(obj.getContractPrice())){
		//	flag = false;
		//}else if(obj.getPriceUnit()<1 || obj.getPriceUnit()>4){
		//	flag = false;
		}else if(null == obj.getSigningDate()){
			flag = false;
		}else if(null == obj.getEffectiveDate()){
			flag = false;
		}else if(null == obj.getFinishDate()){
			flag = false;
		}else if(null != obj.getRemark() && obj.getRemark().getBytes().length>400){
			flag = false;
		} 
		
		if(StringUtil.checkStingAndLength(obj.getContractPrice(), 20) && NumberValidationUtils.isPositiveRealNumber(obj.getContractPrice()) ){
			if(obj.getPriceUnit()==0){
				flag = false;
	    	}
		}
		return flag;
	}
    /**金额单位下拉选*/
    public void getPriceUnitByContractCode(){
    	
    	Integer priceUnit=tblContractService.getPriceUnitByContractCode(model.getContractCode());
    	if(priceUnit != null ){
			Map<String,Object> map =null;
			if(priceUnit!=null){
				map =  new HashMap<String,Object>();
	    		if(priceUnit==1){
	    			map.put("id",priceUnit);
	    			map.put("text", "元");
	    		}else if(priceUnit==2){
	    			map.put("id",priceUnit);
	    			map.put("text", "美元");
	    		}else if(priceUnit==3){
	    			map.put("id",priceUnit);
	    			map.put("text", "欧元");
	    		}else {
	    			map.put("id",priceUnit);
	    			map.put("text", "万元");
	    		}
	    	}
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
    	
    }
    /** 获得下一个合同编号*/
    public void getNextContractPoolNum(){
    	String contractNum = contractPoolNumService.getNextContractPoolNum();
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put("num", contractNum);
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    	
    }
    
    /** 获得下一个供试品编号（医药类）*/
    public void getMedicineNextTblTestItemPoolNum(){
    	int total = contractPoolNumService.getTblTestItemTotal(model.getContractCode());
    	String tiNo;
    	if(total < 10){
    		 tiNo =  model.getContractCode()+"-1-"+String.format("%02d", total+1);
    	}else{
    		 tiNo = (total+1)+"";
    	}
    	
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put("num", tiNo);
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
    
    /** 获得下一个供试品编号（非医药类） */
    public void getOtherNextTblTestItemPoolNum(){
    	int total = contractPoolNumService.getTblTestItemTotal(model.getContractCode());
    	String tiNo;
    	if(total < 10){
    		 tiNo =  model.getContractCode()+"-"+String.format("%02d", total+1);
    	}else{
    		 tiNo = (total+1)+"";
    	}
    	
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put("num", tiNo);
    	String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
    }
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		  //记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("合同");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public String getCurrentId() {
		return currentId;
	}
	public void setCurrentId(String currentId) {
		this.currentId = currentId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public int getWrite() {
		return write;
	}
	public void setWrite(int write) {
		this.write = write;
	}

	public String getContractEditRsn() {
		return contractEditRsn;
	}

	public void setContractEditRsn(String contractEditRsn) {
		this.contractEditRsn = contractEditRsn;
	}
    

}
