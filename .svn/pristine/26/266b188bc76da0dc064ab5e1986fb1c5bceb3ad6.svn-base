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
import com.lanen.service.ContractService;
import com.lanen.service.clinicaltest.StudyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**专题信息
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class StudyAction extends ActionSupport{

	private static final long serialVersionUID = 1996873173878124087L;
	@Resource
	private StudyService studyService;
	@Resource
	private ContractService contractService;
	
	private String year;//年份
	private String testItemType;// 01: 医药  02：农药  03：化学品

	
	/**主页
	 * @return
	 */
	public String study(){
		List<String> yearList = studyService.getYearList();
		List<String> yearList2 = contractService.getYearList();
		List<Map<String,String>> yearMapList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> yearMapList2 = new ArrayList<Map<String,String>>();
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
		if(null != yearList2 && yearList2.size() > 0){
			for(String year:yearList2){
				yearmap = new HashMap<String,String>();
				yearmap.put("id", year);
				yearmap.put("text", year);
				yearMapList2.add(yearmap);
			}
		}
		String yearListStr = JsonPluginsUtil.beanListToJson(yearMapList);
		String yearListStr2 = JsonPluginsUtil.beanListToJson(yearMapList2);
		
		List<Map<String,Object>> studyNoMapList = studyService.getStudyNoMapListByYear(year,"01");
		String studyNoListStr = JsonPluginsUtil.beanListToJson(studyNoMapList);
		List<Map<String,Object>> contractCodeMapList = contractService.getContractMapListByYear(year);
		String contractCodeListStr = JsonPluginsUtil.beanListToJson(contractCodeMapList);
		ActionContext.getContext().put("yearListStr", yearListStr.replace("\"", "'"));
		ActionContext.getContext().put("yearListStr2", yearListStr2.replace("\"", "'"));
		ActionContext.getContext().put("studyNoListStr", studyNoListStr.replace("\"", "'"));
		ActionContext.getContext().put("contractCodeListStr", contractCodeListStr.replace("\"", "'"));
		return "study";
	}
	
	/**加载专题编号列表
	 * @return
	 */
	public void loadStudyNoList(){
		List<Map<String,Object>> studyNoMapList = studyService.getStudyNoMapListByYear(year,testItemType);
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
	/**加载合同编号列表
	 * @return
	 */
	public void loadContractCodeList(){
		List<Map<String,Object>> studyNoMapList = contractService.getContractMapListByYear(year);
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
	

	//-------------------------
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTestItemType() {
		return testItemType;
	}

	public void setTestItemType(String testItemType) {
		this.testItemType = testItemType;
	}
	
	
}
