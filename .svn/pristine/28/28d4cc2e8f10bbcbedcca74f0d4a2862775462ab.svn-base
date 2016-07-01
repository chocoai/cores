package com.lanen.view.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.service.TblYYDBService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
@Controller
@Scope("prototype")
public class TblLogAction extends BaseAction<TblLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6782653371953421575L;
	@Resource
	protected TblLogService tblLogService;
	@Resource
	protected TblYYDBService tblYYDBService;
	private String beginDate ;//起始日期
	private String endDate;   //结束日期
	private String host;      //操作位置
	
	private int systemNameIndex;
//	/**列表页面*/
//	public String list(){
//		beginDate= DateUtil.getDateAgo(6);
//		endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
//		
//		return "list";
//	}
	/**列表页面，审计*/
	public String loglist(){
		beginDate= DateUtil.getDateAgo(6);
		endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		if(systemNameIndex == 0){
			systemNameIndex = 1;
		}
		return "loglist";
	}
	/**加载list数据_审计*/
	public void loadLogList(){

		String jsonStr = "";
		//系统名称不一样，日志查询地方不同
		if(systemNameIndex == 1 || systemNameIndex == 2 || systemNameIndex == 3 || systemNameIndex == 5 || systemNameIndex == 6){
			List<TblLog> list = new ArrayList<TblLog>();
			String systemName = "";
			switch (systemNameIndex) {
			case 1:
				systemName = "临床检验管理系统";
				break;
			case 2:
				systemName = "毒性病理管理系统";
				break;
			case 3:
				systemName = "综合管理系统";
				break;
			case 5:
				systemName = "QA管理系统";
				break;
			case 6:
				systemName = "委托管理系统";
				break;
				
			default:
				break;
			}
			if(null==beginDate || null==endDate || "".equals(beginDate) || "".equals(endDate)){
				beginDate= DateUtil.getDateAgo(6);
				endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
				model.setOperatType("");
			}
			list=tblLogService.getListByDateHostTypeSystemName(beginDate, endDate, "",model.getOperatType(), systemName);
			jsonStr = JsonPluginsUtil.beanListToJson(list, "yyyy-MM-dd HH:mm:ss");
			
		}else if(systemNameIndex == 4){
			List<Map<String,Object>> mapList = tblYYDBService.getYYDBLogList(model.getOperatType(),beginDate,endDate);
			jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		}
		writeJson(jsonStr);
	}
	/**加载list数据*/
	public void loadList(){
		List<TblLog> list = new ArrayList<TblLog>();
//		tblLogService.getListByDateHostSystemName(beginDate, endDate, host, SystemMessage.getSystemName());
		if(null==beginDate || null==endDate || "".equals(beginDate) || "".equals(endDate)){
			beginDate= DateUtil.getDateAgo(6);
			endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			list=tblLogService.getListByDateHostTypeSystemName(beginDate, endDate, "","", SystemMessage.getSystemName());
		}else{
			if(null==host||"全部".equals(host)){
				host="";
			}
			list=tblLogService.getListByDateHostTypeSystemName(beginDate, endDate, host,model.getOperatType(), SystemMessage.getSystemName());
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list, "yyyy-MM-dd HH:mm:ss");
		writeJson(jsonStr);
	}
	/**加载操作位置下拉框*/
	public void selectHostList(){
		List<String> hostList= tblLogService.getOperatHostListBySystemName(SystemMessage.getSystemName());
		//下拉框数据
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map =null;
		map = new  HashMap<String,String>();
		map.put("id", "");
		map.put("text", "全部"); 
		mapList.add(map);
		if(null!=hostList && hostList.size()>0){
			for(String str : hostList){
				map = new  HashMap<String,String>();
				map.put("id", str);
				map.put("text", str); 
				mapList.add(map);
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	/**加载操作类型下拉框_审计*/
	public void selectLogOperatTypeList(){
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map =null;
		//系统名称不一样，日志查询地方不同
		if(systemNameIndex == 1 || systemNameIndex == 2 || systemNameIndex == 3 || systemNameIndex == 5 || systemNameIndex == 6){
			String systemName = "";
			switch (systemNameIndex) {
			case 1:
				systemName = "临床检验管理系统";
				break;
			case 2:
				systemName = "毒性病理管理系统";
				break;
			case 3:
				systemName = "综合管理系统";
				break;
			case 5:
				systemName = "QA管理系统";
				break;
			case 6:
				systemName = "委托管理系统";
				break;
				
			default:
				break;
			}
			List<String> hostList= tblLogService.getOperatTypeListBySystemName(systemName);
			//下拉框数据
			map = new  HashMap<String,String>();
			map.put("id", "");
			map.put("text", "全部"); 
			mapList.add(map);
			if(null!=hostList && hostList.size()>0){
				for(String str : hostList){
					map = new  HashMap<String,String>();
					map.put("id", str);
					map.put("text", str); 
					mapList.add(map);
				}
			}
		}else if(systemNameIndex == 4){
			//一般毒理系统
			map = new  HashMap<String,String>();
//			map.put("id", "ALL");
			map.put("id", "");
			map.put("text", "全部"); 
			mapList.add(map);
			map = new  HashMap<String,String>();
			map.put("id", "LOGIN");
			map.put("text", "登录"); 
			mapList.add(map);
			map = new  HashMap<String,String>();
			map.put("id", "OTHER");
			map.put("text", "其他"); 
			mapList.add(map);
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
//	/**加载操作类型下拉框*/
//	public void selectOperatTypeList(){
//		List<String> hostList= tblLogService.getOperatTypeListBySystemName(SystemMessage.getSystemName());
//		//下拉框数据
//		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
//		Map<String,String> map =null;
//		map = new  HashMap<String,String>();
//		map.put("id", "");
//		map.put("text", "全部"); 
//		mapList.add(map);
//		if(null!=hostList && hostList.size()>0){
//			for(String str : hostList){
//				map = new  HashMap<String,String>();
//				map.put("id", str);
//				map.put("text", str); 
//				mapList.add(map);
//			}
//		}
//		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
//		writeJson(jsonStr);
//	}
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getSystemNameIndex() {
		return systemNameIndex;
	}
	public void setSystemNameIndex(int systemNameIndex) {
		this.systemNameIndex = systemNameIndex;
	}

}
