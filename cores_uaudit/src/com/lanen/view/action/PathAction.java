package com.lanen.view.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.service.clinicaltest.PathService;
import com.lanen.util.NumberValidationUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**病理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class PathAction extends ActionSupport{

	private static final long serialVersionUID = 1996873173878124087L;
	@Resource
	private PathService pathService;
	
	private String year;//年份
	private int operateType;//类型：
							//		8:全部，包括1，2，3，4
							//		1:数据确认-添加 
							//    	2:数据确认-编辑 
							//   	3:数据确认-删除 
							//   	4:数据修改 
							//   	5:申请变更 
							//   	6:申请撤销 
							//		7:全部 ：申请变更 ,申请撤销 
							
							//		9:全部   10,11
							//		10:镜检数据-添加
							//		11:镜检数据-删除
	
							//		12:全部   13,14,15
							//		13:重新称量
							//		14:删除
							//		15:编辑
	
							//		16:全部   17,18
							//		17:数据确认_添加
							//		18:数据修改_添加
	
							
	
	private String studyNo;
	private int reqNo;
	private int change ; 	// 1 ：代表是变更前数据（查询his表）
	
	/**主页
	 * @return
	 */
	public String index(){
		
		return "index";
	}
	/** 病理
	 * @return
	 */
	public String path(){
		List<String> yearList = pathService.getYearList();
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
		
		List<Map<String,Object>> studyNoMapList = pathService.getStudyNoMapListByYear(year);
		String studyNoListStr = JsonPluginsUtil.beanListToJson(studyNoMapList);
		ActionContext.getContext().put("yearListStr", yearListStr.replace("\"", "'"));
		ActionContext.getContext().put("studyNoListStr", studyNoListStr.replace("\"", "'"));
		return "path";
	}
	
	/**加载专题编号列表
	 * @return
	 */
	public void loadStudyNoList(){
		List<Map<String,Object>> studyNoMapList = pathService.getStudyNoMapListByYear(year);
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
	 * 加载解剖所见数据
	 */
	public void loadAnatomyCheckData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo && operateType > 0){
			if(operateType == 1 || operateType == 2 || operateType == 3){
				dataMapList = pathService.getDataConfirmMapList(studyNo,operateType);
			}else if(operateType == 4 ){
				dataMapList = pathService.getDataEditMapList(studyNo);
			}else if(operateType == 8){
//				dataMapList = pathService.getDataConfirmMapList(studyNo,1);
//				List<Map<String,Object>> dataMapList2 = pathService.getDataConfirmMapList(studyNo,2);
//				List<Map<String,Object>> dataMapList3 = pathService.getDataConfirmMapList(studyNo,3);
				dataMapList = pathService.getDataConfirmMapList(studyNo,8);
				List<Map<String,Object>> dataMapList4 = pathService.getDataEditMapList(studyNo);
				
				if(null == dataMapList){
					dataMapList = new ArrayList<Map<String,Object>>();
				}
//				if(null != dataMapList2){
//					dataMapList.addAll(dataMapList2);
//				}
//				if(null != dataMapList3){
//					dataMapList.addAll(dataMapList3);
//				}
				if(null != dataMapList4){
					dataMapList.addAll(dataMapList4);
				}
			}
		}
		
		if(null != dataMapList){
			Collections.sort(dataMapList, new Comparator<Map<String,Object>>(){

				public int compare(Map<String, Object> obj1,
						Map<String, Object> obj2) {
					String animalCode1 = (String) obj1.get("animalCode");
					String animalCode2 = (String) obj2.get("animalCode");
					if(null != animalCode1 && null != animalCode2 && !animalCode1.equals(animalCode2)){
						if(NumberValidationUtils.isPositiveInteger(animalCode1) && NumberValidationUtils.isPositiveInteger(animalCode2)){
							return Integer.parseInt(animalCode1) - Integer.parseInt(animalCode2);
						}
					}
					return 0;
				}});
		}
		
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	 * 加载脏器称重数据
	 */
	public void loadVisceraWeightData(){
		//TODO 
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo && operateType > 0){
			dataMapList = pathService.getVisceraWeightDataMapList(studyNo,operateType);
		}
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	 * 加载解剖申请数据
	 */
	public void loadAnatomyReqData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo && operateType > 0){
			if(operateType == 6){
				dataMapList = pathService.getAnatomyReqCancelMapList(studyNo);
			}else if(operateType == 5 ){
				dataMapList = pathService.getAnatomyReqChangeMapList(studyNo);
			}else if((operateType == 7 )){
				dataMapList = pathService.getAnatomyReqChangeMapList(studyNo);
				List<Map<String,Object>> dataMapList2 = pathService.getAnatomyReqCancelMapList(studyNo);
				if(null != dataMapList && null != dataMapList2){
					dataMapList.addAll(dataMapList2);
				}else {
					if(null == dataMapList ){
						dataMapList = dataMapList2;
					}
				}
			}
		}
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	 * 加载申请数据
	 */
	public void loadAnatomyReqViewData(){
		Map<String,Object> data  = new HashMap<String,Object>();
		if(null != studyNo && reqNo > 0){
			data = pathService.getReqView(studyNo,reqNo,change);
		}
		String dataStr = JsonPluginsUtil.beanToJson(data);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(dataStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载镜检数据
	 */
	public void loadHistopathCheckData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo && operateType > 0){
			if(operateType == 9){
				dataMapList = pathService.getHistopathCheckMapList(studyNo,operateType);
			}else if(operateType == 10 ){
				dataMapList = pathService.getHistopathCheckMapList(studyNo,operateType);
			}else if((operateType == 11 )){
				dataMapList = pathService.getHistopathCheckMapList(studyNo,operateType);
			}
		}
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	 * 加载固定数据
	 */
	public void loadVisceraFixedData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo && operateType > 0){
			dataMapList = pathService.getVisceraFixedMapList(studyNo,operateType);
		}
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	 * 加载死亡日期修改痕迹
	 */
	public void loadDeadDateData(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo ){
			dataMapList = pathService.getAnimalDeadDateTraceMapList(studyNo);
		}
		String studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
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
	public PathService getPathService() {
		return pathService;
	}
	public void setPathService(PathService pathService) {
		this.pathService = pathService;
	}
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}
	public int getChange() {
		return change;
	}
	public void setChange(int change) {
		this.change = change;
	}
	
	
}
