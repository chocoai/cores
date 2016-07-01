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
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblReportRecord;
import com.lanen.model.contract.TblReportRecord_JSON;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.TblReportRecordService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;
/**
 * 报告登记记录
 * @author xiaowan
 *
 */
@Controller
@Scope("prototype")
public class TblReportRecordAction extends BaseAction<TblReportRecord>{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TblReportRecordService tblReportRecordService;
	
	@Resource
	private TblStudyItemService tblStudyItemService;//委托项目
	
	@Resource
	private TblLogService tblLogService;
	
	private String selectid;
	
    /** 跳转到报告登记页面*/
	public String list(){
		//判断是否有登记权限
		int addContract = 0;
		boolean add = (Boolean) ActionContext.getContext().getSession().get("add"); 
		if(add){
			addContract = 1;
		}
		ActionContext.getContext().put("addContract", addContract);
		return "list";
	}
	
	/** 加载窗口*/
	public void loadList(){
		List<TblReportRecord> list = tblReportRecordService.getByContractCodeList(model.getContractCode());
		if(null!=list && list.size()>0){
			 List<TblReportRecord_JSON> tblReportRecordlist = new ArrayList<TblReportRecord_JSON>();
			 for(TblReportRecord obj:list){
				 TblReportRecord_JSON tblReportRecord = new TblReportRecord_JSON() ;
				 tblReportRecord.setId(obj.getId());
				 tblReportRecord.setStudyCode(obj.getStudyCode());
				 tblReportRecord.setReportCode(obj.getReportCode());
				 tblReportRecord.setReportName(obj.getReportName());
				 tblReportRecord.setFinishDate(DateUtil.dateToString(obj.getFinishDate(), "yyyy-MM-dd"));
				 tblReportRecord.setSd(obj.getSd());
				 tblReportRecord.setSubmitter(obj.getSubmitter());
				 tblReportRecord.setSubmitDate(DateUtil.dateToString(obj.getSubmitDate(), "yyyy-MM-dd"));
				 tblReportRecord.setDeliverer(obj.getDeliverer());
				 tblReportRecord.setDeliveryDate(DateUtil.dateToString(obj.getDeliveryDate(), "yyyy-MM-dd"));
				 tblReportRecord.setReceiver(obj.getReceiver());
				 tblReportRecord.setReceiveDate(DateUtil.dateToString(obj.getReceiveDate(), "yyyy-MM-dd"));
				 tblReportRecord.setDeliveryMode(obj.getDeliveryMode());
				 tblReportRecord.setDeliveryinfo(obj.getDeliveryinfo());
				 tblReportRecord.setRemark(obj.getRemark());
				 tblReportRecordlist.add(tblReportRecord);
			 }
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("total", tblReportRecordlist.size());
			map.put("rows", tblReportRecordlist);
			map.put("id", selectid);
			String json = JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}else{
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("total",0);
			 map.put("rows","");
			 map.put("id", selectid);
			 String json = JsonPluginsUtil.beanToJson(map);
			 writeJson(json);
		}
	}
	/**保存*/
	public void addSave(){
		TblReportRecord reportRecord = new TblReportRecord();
		String id  = tblReportRecordService.getKey();
		reportRecord.setId(id);
		reportRecord.setContractCode(model.getContractCode());
		reportRecord.setStudyCode(model.getStudyCode());
		reportRecord.setReportCode(model.getReportCode());
		reportRecord.setReportName(model.getReportName());
		reportRecord.setFinishDate(model.getFinishDate());
		TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(model.getStudyCode());
		reportRecord.setSd(studyItem.getSd());
		reportRecord.setSubmitter(model.getSubmitter());
		reportRecord.setSubmitDate(model.getSubmitDate());
		reportRecord.setDeliverer(model.getDeliverer());
		reportRecord.setDeliveryDate(model.getDeliveryDate());
		reportRecord.setReceiver(model.getReceiver());
		reportRecord.setReceiveDate(model.getReceiveDate());
		reportRecord.setDeliveryMode(model.getDeliveryMode());
		reportRecord.setDeliveryinfo(model.getDeliveryinfo());
		reportRecord.setRemark(model.getRemark());
		 Map<String,Object> map = null;
		 map = new HashMap<String,Object>();
		try{
		    tblReportRecordService.save(reportRecord);
		    map.put("success",true);
		    map.put("id",id);
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "与数据库交互异常");
			System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
			System.out.println("执行结束");
		} 
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);;
	}
	
	/**加载课题下拉选*/
	public void loadstudyCode(){
		List<Map<String,String>> mapList = null;
		if(null!=model.getContractCode() && !model.getContractCode().equals("")){
			mapList =  tblReportRecordService.getBycontractCodeStudyCodeList(model.getContractCode());
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}
	}
	
	/**选择课题的SD*/
	public void selectSubmitter(){
		TblStudyItem studyItem =tblStudyItemService.getByStudyNoStudyItem(model.getStudyCode());
		Map<String,Object> map = null;
		map = new HashMap<String,Object>();
		map.put("sd",studyItem.getSd());
		map.put("success",true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**加载 接受方式*/
	public void delivery(){
		List<String> list = tblReportRecordService.getDeliveryModeList();
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
	
	/**删除操作*/
	public void onDelReportRecord(){
		Map<String,Object> map = null;
		map = new HashMap<String,Object>();
		try{
			TblReportRecord reportRecord = tblReportRecordService.getById(model.getId());
			tblReportRecordService.delete(model.getId());
			writeLog("删除报告登记","专题编号："+reportRecord.getContractCode()+" 报告编号："+reportRecord.getReportCode()+" 报告名称"+reportRecord.getReportName());
			map.put("success",true);
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "与数据库交互异常");
			System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
			System.out.println("执行结束");
		} 
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);;
		
	}
	
	/**编辑之前的查询*/
	public void onEdit(){
		TblReportRecord reportRecord = tblReportRecordService.getById(model.getId());
		Map<String,Object> map = null;
		map = new HashMap<String,Object>();
		map.put("id",reportRecord.getId());
		map.put("contractCode",reportRecord.getContractCode());
		map.put("studyCode",reportRecord.getStudyCode());
		map.put("reportCode",reportRecord.getReportCode());
		map.put("reportName",reportRecord.getReportName());
		map.put("finishDate",DateUtil.dateToString(reportRecord.getFinishDate(), "yyyy-MM-dd"));
		map.put("submitter",reportRecord.getSubmitter());
		map.put("submitDate",DateUtil.dateToString(reportRecord.getSubmitDate(),"yyyy-MM-dd"));
		map.put("deliverer",reportRecord.getDeliverer());
		map.put("deliveryDate",DateUtil.dateToString(reportRecord.getDeliveryDate(), "yyyy-MM-dd"));
		map.put("receiver",reportRecord.getReceiver());
		map.put("receiveDate",DateUtil.dateToString(reportRecord.getReceiveDate(), "yyyy-MM-dd"));
		map.put("deliveryMode",reportRecord.getDeliveryMode());
		map.put("deliveryinfo",reportRecord.getDeliveryinfo());
		map.put("remark",reportRecord.getRemark());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);;
	}
	/**编辑保存*/
	public void  editSave(){
		TblReportRecord reportRecord = tblReportRecordService.getById(model.getId());
		reportRecord.setReportCode(model.getReportCode());
		reportRecord.setReportName(model.getReportName());
		reportRecord.setFinishDate(model.getFinishDate());
		reportRecord.setSubmitter(model.getSubmitter());
		reportRecord.setSubmitDate(model.getSubmitDate());
		reportRecord.setDeliverer(model.getDeliverer());
		reportRecord.setDeliveryDate(model.getDeliveryDate());
		reportRecord.setReceiver(model.getReceiver());
		reportRecord.setReceiveDate(model.getReceiveDate());
		reportRecord.setDeliveryMode(model.getDeliveryMode());
		reportRecord.setDeliveryinfo(model.getDeliveryinfo());
		reportRecord.setRemark(model.getRemark());
		Map<String,Object> map = null;
		map = new HashMap<String,Object>();
		try{
			tblReportRecordService.update(reportRecord);
			writeLog("编辑报告登记","专题编号："+reportRecord.getContractCode()+" 报告编号："+reportRecord.getReportCode()+" 报告名称"+reportRecord.getReportName());
			map.put("success",true);
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "与数据库交互异常");
			System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
			System.out.println("执行结束");
		} 
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);;
		
	}
	
	/**检验报告编号是否重复*/
	public void reportCodecheckName(){
		if (null != model.getReportCode() && !"".equals(model.getReportCode())) {
			boolean isExist;
			isExist = tblReportRecordService.isExistByReportCode(model.getReportCode());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
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
		  tblLog.setOperatOject("报告登记");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}

	public String getSelectid() {
		return selectid;
	}

	public void setSelectid(String selectid) {
		this.selectid = selectid;
	}
	
	

}
