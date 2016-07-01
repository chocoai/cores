package com.lanen.view.action;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.model.User;
import com.lanen.util.DateUtil;

@Controller
@Scope("prototype")
public class TestreportAction  extends BaseAction<User>{

	private static final long serialVersionUID = 2110706327963917876L;

	private List<Map<String,Object>> sourceList ;
	private Map<String,Object> paraMap ;
	private String fileName;
	
	
	public String report(){
		paraMap = new HashMap<String, Object>();
        
//		String number = dictReportNumberService.getNumberByReportName("临床检验申请单");
		String number = "xxxxx";
		paraMap.put("number", number == null ? "":number);
		
		paraMap.put("studyNo","2015-000001");
		URL url = this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", url);
			sourceList = new ArrayList<Map<String,Object>>();
			for(int i = 0;i<5;i++){
				for(int j = 0;j<36;j++){
					Map<String,Object> map1 = new HashMap<String,Object>();
					map1.put("group", i+1+"");
					map1.put("dosage", i+1+"");
					map1.put("maleNum", i+1+"");
					map1.put("femaleNum", i+1+"");
					map1.put("animalCode", i+"10"+j);
					map1.put("endFlag",0);
					sourceList.add(map1);
				}
			}
		fileName = "ClinicalTestApply"+DateUtil.dateToString(new Date(), "yyyyMMddsss");
		
		//脏器子报表
		Map<String,Object> subMap = new HashMap<String,Object>();
		subMap.put("number_sub", number == null ? "":number);
		subMap.put("studyNo_sub","2015-000002");
		subMap.put("logoImage_sub", url);
		subMap.put("studyName_sub", "急毒试验");
		
		List<Map<String,Object>> subSourceList = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<4;i++){
			for(int j = 0;j<15;j++){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("pathType", i+1+"");
				map1.put("visceraName","60"+j*(i+1));
				subSourceList.add(map1);
			}
		}
		
		URL subReportURL = this.getClass().getResource("PathReq_viscera.jasper");
		paraMap.put("subReportURL", subReportURL);
		
		paraMap.put("subMap", subMap);
		paraMap.put("subSourceList", new JRBeanCollectionDataSource(subSourceList));
		return "report";
	}
	
	
	public List<Map<String, Object>> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<Map<String, Object>> sourceList) {
		this.sourceList = sourceList;
	}
	public Map<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
