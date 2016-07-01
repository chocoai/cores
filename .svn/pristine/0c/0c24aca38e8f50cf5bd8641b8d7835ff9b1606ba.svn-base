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
import com.lanen.service.clinicaltest.ClinicalTestService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**临检
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class ClinicalTestAction extends ActionSupport{

	private static final long serialVersionUID = 1996873173878124087L;
	
	@Resource
	private ClinicalTestService clinicalTestService;
	
	private String year;//年份
	private int operateType;//类型：1：临检指标重测 2：临检数据编辑\删除
	private String studyNo;
	/**主页
	 * @return
	 */
	public String index(){
		return "index";
	}
	/**专题审计信息
	 * @return
	 */
	public String clinicaltest(){
		List<String> yearList = clinicalTestService.getYearList();
		List<Map<String,String>> yearMapList = new ArrayList<Map<String,String>>();
		Map<String,String>  yearmap = null;
		if(null != yearList && yearList.size() > 0){
			for(String year:yearList){
				yearmap = new HashMap<String,String>();
				yearmap.put("id", year);
				yearmap.put("text", year);
				yearMapList.add(yearmap);
			}
			year = yearList.get(0);
		}
		String yearListStr = JsonPluginsUtil.beanListToJson(yearMapList);
		
		List<Map<String,Object>> studyNoMapList = clinicalTestService.getStudyNoMapListByYear(year);
		String studyNoListStr = JsonPluginsUtil.beanListToJson(studyNoMapList);
		ActionContext.getContext().put("yearListStr", yearListStr.replace("\"", "'"));
		ActionContext.getContext().put("studyNoListStr", studyNoListStr.replace("\"", "'"));
		
		return "clinicaltest";
	}
	
	/**
	 * 加载专题编号列表
	 */
	public void loadStudyNoList(){
		List<Map<String,Object>> studyNoMapList = clinicalTestService.getStudyNoMapListByYear(year);
		String studyNoListStr = JsonPluginsUtil.beanListToJson(studyNoMapList);
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
	 * 加载数据（编辑删除或重测数据）
	 */
	public void loadClinicalData(){
		String jsonStr = "";
		Long t = System.currentTimeMillis();
		if(operateType == 1){
			List<Map<String,Object>> mapList = clinicalTestService.getClinicalTestData(studyNo);
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}else{
			List<Map<String,Object>> mapList = clinicalTestService.getEditTrace(studyNo);
			List<Map<String,Object>> mapList2 = clinicalTestService.getDelTrace(studyNo);
			if(null != mapList){
				mapList.addAll(mapList2);
			}else{
				mapList = mapList2;
			}
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}

}
