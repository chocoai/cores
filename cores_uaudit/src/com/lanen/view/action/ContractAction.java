package com.lanen.view.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.service.ContractService;
import com.lanen.service.QaService;
import com.opensymphony.xwork2.ActionSupport;

/**委托管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class ContractAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -753252729295569929L;

	@Resource
	private ContractService contractService;
	
	
	private String contractCode;
	
	/**
	 * 加载合同编辑记录列表
	 */
	public void loadContractList(){
		List<Map<String,Object>> dataMapList = null;
		if(null != contractCode ){
			dataMapList = contractService.getContractList(contractCode);
		}
		
		String dataListStr = "";
		if(null != dataMapList){
			dataListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(dataListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载供试品编辑记录列表
	 */
	public void loadTestitemList(){
		List<Map<String,Object>> dataMapList = null;
		if(null != contractCode ){
			dataMapList = contractService.getTestitemList(contractCode);
		}
		
		String dataListStr = "";
		if(null != dataMapList){
			dataListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(dataListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载供试品编辑记录列表
	 */
	public void loadStudyitemList(){
		List<Map<String,Object>> dataMapList = null;
		if(null != contractCode ){
			dataMapList = contractService.getStudyitemList(contractCode);
		}
		
		String dataListStr = "";
		if(null != dataMapList){
			dataListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(dataListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
}
