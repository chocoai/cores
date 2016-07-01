package com.lanen.view.action.schdeule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.schedule.TblTaskState;
import com.lanen.service.schdeule.TblTaskStateService;
import com.lanen.util.DateUtil;

@Controller
@Scope("prototype")
public class TblTaskStateAction extends BaseAction<TblTaskState> {

	private static final long serialVersionUID = -4368708198660711229L;
	
	@Resource
	private TblTaskStateService tblTaskStateService;
	
	private String selectedDate;
	private String currentUserCode;
	private String scheduleIdsStr ;
	
	/**加载列表*/
	public void loadList() throws Exception{
		System.out.println(currentUserCode);
		Date oneDate = DateUtil.stringToDate(selectedDate, "yyyy-MM-dd");
		List<Map<String,Object>> list = tblTaskStateService.getOneDayOnePeopleAllTask(oneDate, currentUserCode);
		String json = JsonPluginsUtil.beanListToJson(list);
		writeJson(json);
	}
	/**保存（登记完成任务）*/
	public void save() throws Exception{
		Json json = new Json();
		if(		null == selectedDate || "".equals(selectedDate) ||
				null == currentUserCode || "".equals(currentUserCode) ||
				null == scheduleIdsStr || "".equals(scheduleIdsStr) ){
			json.setMsg("数据传输出错，请刷新页面");
		}else{
			String[] scheduleIds = scheduleIdsStr.split(",");
			tblTaskStateService.saveMuch(scheduleIds,selectedDate,currentUserCode);
			
			json.setSuccess(true);
			json.setMsg("任务完成登记成功.");
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getCurrentUserCode() {
		return currentUserCode;
	}
	public void setCurrentUserCode(String currentUserCode) {
		this.currentUserCode = currentUserCode;
	}
	public String getScheduleIdsStr() {
		return scheduleIdsStr;
	}
	public void setScheduleIdsStr(String scheduleIdsStr) {
		this.scheduleIdsStr = scheduleIdsStr;
	}

}
