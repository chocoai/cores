package com.lanen.view.action.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblPaymentRecord;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.TblPaymentRecordService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblPaymentRecordAction extends BaseAction<TblPaymentRecord>{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6224662085859430713L;
	
	@Resource
	private TblPaymentRecordService tblPaymentRecordService;//引入合同付款记录的service
	@Resource
	private TblLogService tblLogService;

	private String contractCode;	//合同编号

	private TblPaymentRecord tblPaymentRecord;	//实体
	
	/**点击收款进入收款记录界面*/
	public String paymentRecord(){
		contractCode=model.getContractCode();
		//判断是否有登记权限
		int addContract = 0;
		boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
		if(add){
			addContract = 1;
		}
		ActionContext.getContext().put("addContract", addContract);
	    return "paymentRecord";	
	}
		
	/**加载合同的收款记录数据*/
	public void loadPaymentRecordList(){
		contractCode=model.getContractCode();
		List<TblPaymentRecord> list=tblPaymentRecordService.getPaymentRecordListByContractCode(contractCode);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("total",list.size());
		String json = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		writeJson(json);
	}

		
	/**添加收款记录*/
	public void add(){
		Map<String,Object> map = new HashMap<String, Object>();
		TblPaymentRecord t = new TblPaymentRecord();
		String id = tblPaymentRecordService.getKey();
		t.setId(id);
		String operator=getCurrentRealName();
		t.setOperator(operator);
		t.setContractCode(model.getContractCode());
		t.setPaymentDate(model.getPaymentDate());
		t.setAmount(model.getAmount());
		t.setPriceUnit(model.getPriceUnit());
		t.setReceiptFlag(model.getReceiptFlag());
		t.setOperateTime(model.getOperateTime());
		try{
		   tblPaymentRecordService.save(t);
		   map.put("success",true);
		   map.put("msg","添加成功");
		   map.put("id", id);
		}catch(Exception e){
		   map.put("success", false);
		   map.put("msg", "与数据库交互异常");
		   System.out.println("执行失败，出错种类"+e.getMessage()+".");
	    }finally{ 
		   System.out.println("执行结束");
		}    
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**转到编辑界面*/
	public void toEdit(){
		TblPaymentRecord t=tblPaymentRecordService.getById(model.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=t){
			map.put("id", t.getId());
			map.put("paymentDate",DateUtil.dateToString(t.getPaymentDate(), "yyyy-MM-dd"));
			System.out.println(DateUtil.dateToString(t.getPaymentDate(), "yyyy-MM-dd"));
			map.put("amount", t.getAmount());
			map.put("receiptFlag", t.getReceiptFlag());
			map.put("operateTime",DateUtil.dateToString( t.getOperateTime(), "yyyy-MM-dd"));
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**编辑保存*/
	public void editSave(){
		TblPaymentRecord t=tblPaymentRecordService.getById(model.getId());
		String amout=t.getAmount();
		t.setPaymentDate(model.getPaymentDate());
		t.setAmount(model.getAmount());
		t.setReceiptFlag(model.getReceiptFlag());
		t.setOperateTime(model.getOperateTime());
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			tblPaymentRecordService.update(t);
			writeLog("付款记录编辑","付款记录id："+model.getId()+" 原金额"+amout+" 更新金额"+t.getAmount());
			map.put("success",true);
			map.put("msg","编辑成功");
			map.put("id", model.getId());
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "与数据库交互异常");
			System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
			System.out.println("执行结束");
		} 
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**删除*/
	public void delete(){
		Map<String,Object> map = new HashMap<String,Object>();
		if(null != model.getId()){
			TblPaymentRecord tblPaymentRecord= tblPaymentRecordService.getById(model.getId());
			if(null != tblPaymentRecord){
				     tblPaymentRecordService.delete(model.getId());
				     writeLog("付款记录删除","付款记录id："+model.getId()+" 金额"+tblPaymentRecord.getAmount());
					 map.put("success",true);
			  }else{
					 map.put("success",false);
			  }
		}else{
			map.put("success",false);
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
		  tblLog.setOperatOject("付款记录");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public TblPaymentRecord getTblPaymentRecord() {
		return tblPaymentRecord;
	}
	public void setTblPaymentRecord(TblPaymentRecord tblPaymentRecord) {
		this.tblPaymentRecord = tblPaymentRecord;
	}
	
}
