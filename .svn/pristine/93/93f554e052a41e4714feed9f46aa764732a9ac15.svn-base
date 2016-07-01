package com.lanen.view.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.service.TblYYDBService;
import com.lanen.service.clinicaltest.PathService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**一般毒理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class YydbAction extends ActionSupport{

	private static final long serialVersionUID = -5146590998281197112L;

	@Resource
	private TblYYDBService tblYYDBService;
	
	private String operateType;//类型：
//	 <option value="ALL">全部</option>   
//	    <option value="TESTCODE">专题信息</option>   
//	    <option value="ANIMAL">动物分组</option>   
//	    <option value="WEIGHT">体重称重</option>   
//	    <option value="FOOD">摄食</option>   
//	    <option value="TESTOTHER">其他</option>   
	
	private String studyNo;
	
	/**
	 * 加载专题操作日志
	 */
	public void loadStudyData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo ){
			dataMapList = tblYYDBService.getStudyLogList(studyNo,operateType);
		}
		
		String studyNoListStr = "";
		if(null != dataMapList){
			studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(studyNoListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载数据修改跟踪日志
	 */
	public void loadDataTrace(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo ){
			dataMapList = tblYYDBService.loadDataTrace(studyNo);
		}
		
		String studyNoListStr = "";
		if(null != dataMapList){
			studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(studyNoListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	
	
	
}
