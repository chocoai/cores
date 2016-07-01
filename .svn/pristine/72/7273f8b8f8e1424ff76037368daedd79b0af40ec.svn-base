package com.lanen.view.action.studyplan;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.lanen.base.BaseAction;
import com.lanen.model.clinicaltest.DictComParam;
import com.lanen.model.clinicaltest.DictInstrument;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class DictInstrumentAction  extends BaseAction<DictInstrument>{
	private static final long serialVersionUID = 1L;
	
	private String createDateString;
	
	private String comPort;           //接入串口名称
	private String baudRateString;             //波特率
	private String dataBitString;              //数据位
	private String stopBitString;              //停止位
	private String checkModeString;            //校验方式
	
	/** 列表*/
	public String list() throws Exception {
		List<DictInstrument>  objList;
		objList=dictInstrumentService.getAll();
        ActionContext.getContext().put("objList", objList);
		return "list";
	}
	/** 删除*/
	public String delete() throws Exception {
		dictInstrumentService.delete(model.getInstrumentId());
		return "toList";
	}
	/** 修改*/
	public String edit() throws Exception {
		if(!checkValue()){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "saveUI";
		}
		model.setCreateDate(DateUtil.stringToDate(createDateString, "yyyy-MM-dd"));
		
		dictInstrumentService.update(model);
		return "toList";
	}
	/** 修改页面*/
	public String editUI() throws Exception {
		ActionContext.getContext().put("oldId", model.getInstrumentId());
		DictInstrument obj  =dictInstrumentService.getById(model.getInstrumentId());
		createDateString =DateUtil.dateToString(obj.getCreateDate(), "yyyy-MM-dd");
		ActionContext.getContext().put("createDateString", createDateString);
		ActionContext.getContext().getValueStack().push(obj);
		return "saveUI";
	}
	/** 增加*/
	public String add() throws Exception {
		if(!checkValue()){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","信息填写有误！");
			return "saveUI";
		}
		String instrumentId = model.getInstrumentId();
		boolean isExist = dictInstrumentService.isExistByInstrumentId(instrumentId);
		if(isExist){
			ActionContext.getContext().getValueStack().push(model);
			addFieldError("error","名称已存在！");
			return "saveUI";
		}
		model.setCreateDate(DateUtil.stringToDate(createDateString, "yyyy-MM-dd"));
		dictInstrumentService.save(model);
		return "toList";
	}
	/** 增加页面*/
	public String addUI() throws Exception {
		
		return "saveUI";
	}
	/** 设备接口参数设置页面*/
	public String comParamUI() throws Exception {
		DictInstrument obj  =dictInstrumentService.getById(model.getInstrumentId());
		DictComParam dictComParam=obj.getDictComParam();
		if(null!=dictComParam){
			comPort=dictComParam.getComPort();       
			baudRateString=dictComParam.getBaudRate()+"";
			dataBitString=dictComParam.getDataBit()+""; 
			stopBitString=dictComParam.getStopBit()+"";
			checkModeString=dictComParam.getCheckMode()+"";
			ActionContext.getContext().put("comPort", comPort);
			ActionContext.getContext().put("baudRateString", baudRateString);
			ActionContext.getContext().put("dataBitString", dataBitString);
			ActionContext.getContext().put("stopBitString", stopBitString);
			ActionContext.getContext().put("checkModeString", checkModeString);
			

		}
		ActionContext.getContext().getValueStack().push(obj);
		return "comParamUI";
	}
	/**设置接口参数*/
	public String comParam(){
		DictInstrument obj  =dictInstrumentService.getById(model.getInstrumentId());
		DictComParam dictComParam=obj.getDictComParam();
		if(null==dictComParam){
			dictComParam=new DictComParam();
		}
			dictComParam.setDictInstrument(obj);
			dictComParam.setInstrumentName(obj.getInstrumentName());
			dictComParam.setComPort(comPort);
			dictComParam.setBaudRate(baudRateString);
			dictComParam.setDataBit(dataBitString);
			dictComParam.setStopBit(stopBitString);
			dictComParam.setCheckMode(checkModeString);
			obj.setDictComParam(dictComParam);
		dictInstrumentService.update(obj);
		return "toList";
	}
	
	

	/** 异步调用 ，检查name是否存在 */
	public String checkInStrumentId() throws Exception {
		System.out.println("异步");
		String instrumentId = model.getInstrumentId();
		boolean isExist = dictInstrumentService.isExistByInstrumentId(instrumentId);

		if (!isExist) {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();
			try {
				out.print("no");
				out.flush();
			} finally {
				out.close();
			}
		} else {
			response.setContentType("htmlt");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();
			try {
				out.print("is");
				out.flush();
			} finally {
				out.close();
			}

		}

		return null;
	}
	
	/**验证输入的值*/
	public boolean checkValue(){
		if(null==model.getInstrumentId()||"".equals(model.getInstrumentId())){
			return false;
		}else if(null==model.getInstrumentName()||"".equals(model.getInstrumentName())){
			return false;
		}else if(null==model.getManufacturer()||"".equals(model.getManufacturer())){
			return false;
		}else if(null==model.getModelNumber()||"".equals(model.getModelNumber())){
			return false;
		}else if(null==model.getDirector()||"".equals(model.getDirector())){
			return false;
		}
		
		return true;
	}
	//---------------------------------------
	public String getCreateDateString() {
		return createDateString;
	}
	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	public String getComPort() {
		return comPort;
	}
	public void setComPort(String comPort) {
		this.comPort = comPort;
	}
	public String getBaudRateString() {
		return baudRateString;
	}
	public void setBaudRateString(String baudRateString) {
		this.baudRateString = baudRateString;
	}
	public String getDataBitString() {
		return dataBitString;
	}
	public void setDataBitString(String dataBitString) {
		this.dataBitString = dataBitString;
	}
	public String getStopBitString() {
		return stopBitString;
	}
	public void setStopBitString(String stopBitString) {
		this.stopBitString = stopBitString;
	}
	public String getCheckModeString() {
		return checkModeString;
	}
	public void setCheckModeString(String checkModeString) {
		this.checkModeString = checkModeString;
	}
	

}
