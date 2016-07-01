package com.lanen.view.action.clinicaltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;

@Controller
@Scope("prototype")
public class TblLogAction extends BaseAction<TblLog> {
	
	private static final long serialVersionUID = 89246480186865579L;
	
	private String beginDate ;//起始日期
	private String endDate;   //结束日期
	private String host;      //操作位置
	/**列表页面*/
	public String list(){
		beginDate= DateUtil.getDateAgo(6);
		endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		
		return "list";
	}
	/**加载list数据*/
	public void loadList(){
		List<TblLog> list = new ArrayList<TblLog>();
//		tblLogService.getListByDateHostSystemName(beginDate, endDate, host, SystemMessage.getSystemName());
		if(null==beginDate || null==endDate || "".equals(beginDate) || "".equals(endDate)){
			beginDate= DateUtil.getDateAgo(6);
			endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			list=tblLogService.getListByDateHostSystemName(beginDate, endDate, "", SystemMessage.getSystemName());
		}else{
			if(null==host||"全部".equals(host)){
				host="";
			}
			list=tblLogService.getListByDateHostSystemName(beginDate, endDate, host, SystemMessage.getSystemName());
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
		map.put("id", "全部");
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
	
	
	
}
