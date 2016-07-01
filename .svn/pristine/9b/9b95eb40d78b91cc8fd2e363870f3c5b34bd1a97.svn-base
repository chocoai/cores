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
import com.lanen.service.clinicaltest.ScheduleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**综合管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class ScheduleAction extends ActionSupport{

	private static final long serialVersionUID = 1996873173878124087L;
	
	@Resource
	private ScheduleService scheduleService;
	
	private int operateType;//类型：1：重新任命SD 2：重新任命QA检查员 3：重新任命病理专题负责人 4：全部（包括123）
	private String studyNo;
	
	
	/**
	 * 加载数据（删除或重测数据）
	 */
	public void loadData(){
		String jsonStr = "";
		Long t = System.currentTimeMillis();
		if(operateType == 1){
			List<Map<String,Object>> mapList = scheduleService.getAppointSDData(studyNo);
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}else if(operateType == 2){
			List<Map<String,Object>> mapList = scheduleService.getAppointQAData(studyNo);
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}else if(operateType == 3){
			List<Map<String,Object>> mapList = scheduleService.getAppointPathSDData(studyNo);
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}else if(operateType == 4){
			List<Map<String,Object>> mapList = scheduleService.getAppointSDData(studyNo);
			List<Map<String,Object>> mapList2 = scheduleService.getAppointQAData(studyNo);
			List<Map<String,Object>> mapList3 = scheduleService.getAppointPathSDData(studyNo);
//			operate
			if(null != mapList){
				for(Map<String,Object> map:mapList){
					map.put("operate", "重新任命SD");
				}
			}else{
				mapList = new ArrayList<Map<String,Object>>();
			}
			
			if(null != mapList2){
				for(Map<String,Object> map:mapList2){
					map.put("operate", "重新任命QA检查员");
				}
				mapList.addAll(mapList2);
			}
			if(null != mapList3){
				for(Map<String,Object> map:mapList3){
					map.put("operate", "重新任命病理专题负责人");
				}
				mapList.addAll(mapList3);
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
