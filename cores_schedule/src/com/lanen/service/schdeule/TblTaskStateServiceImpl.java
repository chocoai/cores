package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblTaskState;
import com.lanen.util.DateUtil;

@Service
public class TblTaskStateServiceImpl extends BaseDaoImpl<TblTaskState> implements TblTaskStateService{

	@Resource
	private TblSchedulePlanService tblSchedulePlanService;
	
	public List<Map<String, Object>> getOneDayOnePeopleAllTask(Date oneDay,
			String onePeople) {
		if(null == oneDay || null == onePeople || "".equals(onePeople)){
			return null;
		}
		List<?> list = 	getSession().createSQLQuery("{Call getOneDayOnePeopleAllTask(?,?)}")
									.setParameter(0, oneDay)
									.setParameter(1, onePeople)
									.list();
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		if(null != list && list.size()>0){
			Map<String ,Object> map = null;
			for(Object obj :list){
				map = new HashMap<String,Object>();
				Object[] objs = (Object[]) obj;
				String scheduleId = (String) objs[1];
				String taskCode = (String) objs[2];
				String taskName = (String) objs[4];
				Integer isFinish = (Integer) objs[7];
				map.put("scheduleId", scheduleId);
				map.put("taskCode", taskCode);
				map.put("taskName", taskName);
				map.put("isFinish", isFinish);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public void saveMuch(String[] scheduleIds, String selectedDate,
			String currentUserCode) {
		Date date = DateUtil.stringToDate(selectedDate, "yyyy-MM-dd");
		String todayStr = DateUtil.getNow("yyyy-MM-dd");
		Date today = DateUtil.stringToDate(todayStr, "yyyy-MM-dd");
		
		List<TblSchedulePlan> tblSchedulePlanList = tblSchedulePlanService.getByIds(scheduleIds);
		
		if(null != tblSchedulePlanList){
			TblTaskState tblTaskState =null;
			for(TblSchedulePlan obj:tblSchedulePlanList){
				tblTaskState = new TblTaskState();
				tblTaskState.setId(getKey());
				tblTaskState.setScheduleId(obj.getScheduleID());
				tblTaskState.setCodeType(obj.getCodeType());
				tblTaskState.setTaskCode(obj.getTaskCode());
				tblTaskState.setTaskName(obj.getTaskName());
				tblTaskState.setFinishDate(today);
				tblTaskState.setTaskDate(date);
				tblTaskState.setLeader(currentUserCode);
				getSession().save(tblTaskState);
			}
		}
	}
			
}
