package com.lanen.view.action.contract;



import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.contract.TblContract;

import com.lanen.service.contract.TblStudyItemService;
import com.lanen.util.ReportMap;
import com.opensymphony.xwork2.ActionContext;


/**
 * 综合查询
 * @author 小万
 *
 */
@Controller
@Scope("prototype")
public class TblIntegratedInformAction extends BaseAction<TblContract>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String studyNo;//课题编号，打印任命书用

	
	@Resource
	private TblStudyItemService tblStudyItemService;
	/**转到任命书显示页面*/
	/**转到任命书显示页面*/
	public String ireport() throws Exception{
		List<String> studyNoList = new ArrayList<String>();
		String[] strarray = studyNo.split(",");	
		for (int j = 0; j < strarray.length; j++) {
			studyNoList.add(strarray[j].trim());
		}
		tblStudyItemService.addPrintNumber(studyNoList);
		return "ireport";
	}
	
	public void printNumber(){
		List<String> studyNoList = new ArrayList<String>();
		String[] strarray = studyNo.split(",");	
		for (int j = 0; j < strarray.length; j++) {
			studyNoList.add(strarray[j].trim());
		}
		List<String> list = tblStudyItemService.selectPrintNumber(studyNoList);
		String studyNolist = "";
		Map<String,Object> map = new HashMap<String,Object>();
		if(list != null && list.size() > 0){
			for(String str:list){
				if(!studyNolist.equals("")){
					studyNolist = studyNolist + ";";
				}
				studyNolist = studyNolist + str;
			}
			map.put("success", false);
			map.put("studyNolist", studyNolist);
		}else{
			map.put("success", true);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	
	/** out 打印任命书*/
	public String outport() throws Exception{
		//参数
		Map<String,Object> paraMap = new HashMap<String, Object>();
		
		URL url = ServletActionContext.getServletContext().getResource("/jasperReport/"+"logo.jpg");
//		
//		URL url = this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", url);
		
		List<String> studyNoList = new ArrayList<String>();
		String[] strarray = studyNo.split(",");	
		for (int j = 0; j < strarray.length; j++) {
			studyNoList.add(strarray[j].trim());
		}
		
		
		//结果集
		List<Map<String,Object>> mapList = tblStudyItemService.getMapMoreListForImprot(studyNoList);
		ReportMap.getInstance(request).addCompanyInfoIntoMap(paraMap);
		
		String fileName = "SD任命书";
		ActionContext.getContext().put("paraMap", paraMap);
		ActionContext.getContext().put("mapList", mapList);
		ActionContext.getContext().put("fileName", fileName);
		
		return "outport";
	}
	public String getStudyNo() {
		return studyNo;
	}

	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	

	
}
