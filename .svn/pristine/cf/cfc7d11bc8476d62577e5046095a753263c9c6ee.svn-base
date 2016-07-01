package com.lanen.service.schdeule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.jsonAndModel.Columns;
import com.lanen.model.schedule.TblSchedulePlan;
import com.lanen.model.schedule.TblSchedulePlan_Json;
import com.lanen.util.DateUtil;
import com.lanen.util.Trans2PinYin;
@Service
public class TblSchedulePlanServiceImpl extends BaseDaoImpl<TblSchedulePlan> implements TblSchedulePlanService  {

	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan> getSchedulePlan(int taskType,String StudyNo,int CodeType) {
		Query query = getSession().createSQLQuery("{Call getSchedulePlan(?,?,?)}");
		query.setParameter(0, taskType );
		query.setParameter(1, StudyNo);
		query.setParameter(2, CodeType);
		List list =query.list(); 
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblSchedulePlan tblSchedulePlan = new TblSchedulePlan();
				Object[] objs = (Object[]) list.get(i);
				if(objs[0]==null)
				{
					continue;
				}
				String dateStr = new SimpleDateFormat("yyyy-MM-dd").format((Date)objs[0]);
				tblSchedulePlan.setDateTime(dateStr);
				String taskName = (String) objs[1];
				tblSchedulePlan.setTaskName(taskName);
				tblSchedulePlan.setPeriodUnit((Integer)objs[3]);
				tblSchedulePlan.setPeriod((Integer)objs[2]);
				tblSchedulePlan.setDateTimeDate((Date)objs[0]);
				tblSchedulePlanlist.add(tblSchedulePlan);
			}
			
		}
		return tblSchedulePlanlist;
	}

	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan> selecTblSchedulePlans(String taslCode,
			Date oldtime, String taskName) {
		List<TblSchedulePlan> TblSchedulePlanList = getSession().createQuery("FROM TblSchedulePlan t WHERE t.taskCode = ? AND t.taskName = ? " +
				"  AND  t.startTime  <= ? AND ? <= t.endTime").
		setParameter(0, taslCode).setParameter(1, taskName).setParameter(2,oldtime).setParameter(3,oldtime).
		list();
		return TblSchedulePlanList;
	}

	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan> getSchedulePlanList(int taskType,String StudyNo,int CodeType) {
		List<TblSchedulePlan> TblSchedulePlanList = getSession().createQuery("FROM TblSchedulePlan t WHERE t.taskType = ? AND t.taskCode = ? AND t.codeType = ? ")
		.setParameter(0, taskType).setParameter(1, StudyNo).setParameter(2,CodeType).list();
		return TblSchedulePlanList;
	}

	public void updateAllTblSchedulePlans(List<String> singIdlist,String singid) {
		for(String obj:singIdlist){
			TblSchedulePlan schedulePlan = getById(obj);
			schedulePlan.setSignId(singid);
			schedulePlan.setValidFlag(1);
			getSession().update(schedulePlan);
		}
	}

	public void delectTblSchedulePlans(List<String> singIdlist) {
		for(String obj:singIdlist){
			TblSchedulePlan schedulePlan = getById(obj);
			getSession().delete(schedulePlan);
		}
	}

	//在一定时间里相同的任务名称的日程
	public List<TblSchedulePlan> getSameTaskNameSchedulePlan(Date startime,
			Date endtime, String taskName,int CodeType,int taskType) {
		Query query = getSession().createSQLQuery("{Call getsameTaskNameSchedulePlan(?,?,?,?,?)}");
		query.setParameter(0, startime );
		query.setParameter(1, endtime);
		query.setParameter(2, taskName);
		query.setParameter(3, CodeType);
		query.setParameter(4, taskType);
		List<?> list =query.list(); 
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		if(null !=list && list.size()>1){
			for(int i =0;i<list.size();i++){
				TblSchedulePlan tblSchedulePlan = new TblSchedulePlan();
				Object[] objs = (Object[]) list.get(i);
				if(null != (Date)objs[1] ){
					tblSchedulePlan.setTaskCode((String)objs[0]);//课题编号
					String dateStr = new SimpleDateFormat("yyyy-MM-dd").format((Date)objs[1]);//时间
					tblSchedulePlan.setDateTime(dateStr);
					String taskName1 = (String) objs[2];//任务名称
					tblSchedulePlan.setTaskName(taskName1);
					tblSchedulePlan.setPeriodUnit((Integer)objs[4]);
					tblSchedulePlan.setPeriod((Integer)objs[3]);
					tblSchedulePlan.setDateTimeDate((Date)objs[1]);
					tblSchedulePlanlist.add(tblSchedulePlan);
				}
				
			}
			
		}
		if(null !=tblSchedulePlanlist && tblSchedulePlanlist.size()>0){
			return tblSchedulePlanlist;
		}else{
			return null;
		}
		
	}


	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan> getAllschedulePlanList() {
		List<TblSchedulePlan> list = getSession().createQuery("FROM TblSchedulePlan t ").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean getBySchedulePlan(List<String> list) {
		boolean falg = true;
	for(String taskName:list){
		List<TblSchedulePlan> TblSchedulePlanList = getSession().createQuery("FROM TblSchedulePlan t WHERE t.taskName = ? ").
	    setParameter(0, taskName).list();
		if(TblSchedulePlanList.size() > 0){
			falg = false;
			break;
		}
	}
		return falg;
	}

	public Map<String, Object> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(
			Date startDate, Date endDate, int taskKind, int codeType,
			int taskType,boolean isAllDate) {
		Query query = getSession().createSQLQuery("{Call getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(?,?,?,?,?)}");
		query.setParameter(0, startDate );
		query.setParameter(1, endDate);
		query.setParameter(2, taskKind);
		query.setParameter(3, codeType);
		query.setParameter(4, taskType);
		List<?> list =query.list();
		//开始日期>结束日期
		if(startDate.after(endDate)){
			return new HashMap<String,Object>();
		}
		List<String> dateStrList = new ArrayList<String>();
		while(!startDate.after(endDate)){
			dateStrList.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
			startDate = DateUtil.AddDate(startDate, 1);
		}
		/***存放，columns，rows ，存放最终返回值*/
		Map<String,Object> map = new HashMap<String,Object>();
		//存放  field：data  列表
		List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
		Map<String,String> rowMap = null;
		//列list（列 columns）
		List<List<Columns>> columnsList= new ArrayList<List<Columns>>();
		//任务编号Set，
		Set<String> taskCodeSet = new HashSet<String>();
		//任务编号任务名称Set
		Set<String> taskCodeTaskNameSet = new HashSet<String>();
		Map<String,String> taskCodeTaskNameMap = new HashMap<String,String>();
		
		if(null !=list && list.size()>0){
			for(Object obj:list){
				Object[] objs= (Object[]) obj;
				rowMap = new HashMap<String,String>();
				//Date date = (Date) objs[0];
				String taskCode = (String) objs[2];
				String taskName = (String) objs[3];
				String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
				
				taskCodeSet.add(taskCode);
				taskCodeTaskNameSet.add(taskCode+"T"+taskNamePinyin);
				taskCodeTaskNameMap.put(taskCode+"T"+taskNamePinyin, taskName);
				
				//String dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
				//String leaderName = (String) objs[10];
//				rowMap.put("dateCol", dateStr);
//				rowMap.put(taskCode+"T"+taskNamePinyin, leaderName);
//				rowsList.add(rowMap);
			}
			for(String str :dateStrList){
				
				rowMap = new HashMap<String,String>();
				String  weekDay = DateUtil.getWeekDay(str, "yyyy-MM-dd"); 
				for(Object obj:list){
					Object[] objs= (Object[]) obj;
					Date date = (Date) objs[0];
					String taskCode = (String) objs[2];
					String taskName = (String) objs[3];
					String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
					
					String taskCodeTaskName = taskCode+"T"+taskNamePinyin;
					String dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
					String leaderName = (String) objs[10];
					Integer isFinish = (Integer) objs[12];
					if(isFinish == 1){
						leaderName ="<a style='color:blue;'>"+leaderName+"</a>";
					}else{
						leaderName ="<a style='color:red;'>"+leaderName+"</a>";
					}
					if(str.equals(dateStr)){
						if(rowMap.keySet().contains(taskCodeTaskName)){
							if(!rowMap.get(taskCodeTaskName).contains(leaderName)){
								rowMap.put(taskCodeTaskName, rowMap.get(taskCodeTaskName)+","+leaderName);
							}
						}else{
							rowMap.put(taskCodeTaskName, leaderName);
						}
					}
				}
				//TODO 去除多余的 <a style='color:red;'>"+"[未指定]"+"</a>";
				Set<String> tempTaskCodeTaskNameSet = rowMap.keySet();
				//待修改数据
				Map<String,String> editMap = new HashMap<String,String>();
				if(null != tempTaskCodeTaskNameSet){
					for(String temptaskCodeTaskName : tempTaskCodeTaskNameSet){
						String tempLeaderName = rowMap.get(temptaskCodeTaskName);
						if(null != tempLeaderName && ( tempLeaderName.contains(",<a style='color:red;'>[未指定]</a>")
								|| tempLeaderName.contains("<a style='color:red;'>[未指定]</a>,") )){
							tempLeaderName = tempLeaderName.replaceAll(",<a style='color:red;'>\\[未指定\\]</a>", "");
							tempLeaderName = tempLeaderName.replaceAll("<a style='color:red;'>\\[未指定\\]</a>,", "");
							//rowMap.put(temptaskCodeTaskName, tempLeaderName);
							editMap.put(temptaskCodeTaskName, tempLeaderName);
						}
					}
				}
				//开始去除
				Set<String> tempEditKeySet = editMap.keySet();
				if(null != tempEditKeySet){
					for(String temptaskCodeTaskName : tempEditKeySet){
						String tempLeaderName = editMap.get(temptaskCodeTaskName);
						rowMap.put(temptaskCodeTaskName, tempLeaderName);
						System.out.println(temptaskCodeTaskName);
					}
				}
				
				if(isAllDate){
					rowMap.put("dateCol", str+"("+weekDay+")");
					rowsList.add(rowMap);
				}else{
					if(rowMap.keySet().size()>0){
						rowMap.put("dateCol", str+"("+weekDay+")");
						rowsList.add(rowMap);
					}
				}
			}
			columnsList =getColumnsList(taskCodeSet,taskCodeTaskNameSet,taskCodeTaskNameMap);
			
		}
		map.put("columns", columnsList);
		map.put("rows", rowsList);
		return map;
	}

	/**获得 columnsList
	 * @param taskCodeSet     
	 * @param taskCodeTaskNameSet
	 * @return
	 */
	private List<List<Columns>> getColumnsList(Set<String> taskCodeSet,
			Set<String> taskCodeTaskNameSet,Map<String,String> taskCodeTaskNameMap) {
		List<String> taskCodeList = new ArrayList<String>(taskCodeSet);
		List<String> taskCodeTaskNameList = new ArrayList<String>(taskCodeTaskNameSet);
		
		List<List<Columns>> columnsList= new ArrayList<List<Columns>>();
		List<Columns> list = new ArrayList<Columns>();
		Columns column = new Columns();
//		column.setRowspan(2);
//		column.setField("dateCol");
//		column.setTitle("日期");
//		list.add(column);
		for(String taskCode:taskCodeList){
			int i = 0;
			column = new Columns();
			column.setTitle(taskCode);
			for(String str:taskCodeTaskNameList){
				if(str.startsWith(taskCode+"T")){
					i++;
				}
			}
			column.setColspan(i);
			column.setWidth(i*80);
			list.add(column);
		}
		columnsList.add(list);
		list = new ArrayList<Columns>();
		for(String taskCode:taskCodeList){
			for(String str:taskCodeTaskNameList){
				if(str.startsWith(taskCode+"T")){
					column = new Columns();
					column.setTitle(taskCodeTaskNameMap.get(str));
					column.setField(str);
					int j = 0;
					for(String str1:taskCodeTaskNameList){
						if(str1.startsWith(taskCode+"T")){
							j++;
						}
					}
					if(j == 1){
						column.setWidth(160);
					}
					
					list.add(column);
				}
			}
		}
		columnsList.add(list);
		return columnsList;
	}
	

	
	
	public Map<String, Object> getScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(
			Date startDate, Date endDate, int taskKind, int codeType,
			int taskType, boolean isAllDate, List<String> studyNoList,
			List<String> resIdList, List<String> taskNameList,
			List<String> leaderNameList) {
		Query query = getSession().createSQLQuery("{Call getScheduleLeaderNamebyDateTaskKindCodeTypeTaskTypeNew(?,?,?,?,?)}");
		query.setParameter(0, startDate );
		query.setParameter(1, endDate);
		query.setParameter(2, taskKind);
		query.setParameter(3, codeType);
		query.setParameter(4, taskType);
		List<?> datalist =query.list();
		//开始日期>结束日期
		if(startDate.after(endDate)){
			return new HashMap<String,Object>();
		}
		List<String> dateStrList = new ArrayList<String>();
		while(!startDate.after(endDate)){
			dateStrList.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
			startDate = DateUtil.AddDate(startDate, 1);
		}
		//过滤后存放于list
		List<Object> list = new ArrayList<Object>();
		for(Object obj:datalist){
			Object[] objs = (Object[]) obj;
			String taskCode = (String) objs[2];
			String taskName = (String) objs[3];
			String resId = (String) objs[7];
			String leaderName = (String) objs[10];
			if(null != studyNoList && studyNoList.size()>0){
				if(!studyNoList.contains(taskCode)){
					continue;
				}
			}
			if(null != taskNameList && taskNameList.size()>0){
				if(!taskNameList.contains(taskName)){
					continue;
				}
			}
			if(null != resIdList && resIdList.size()>0){
				if(!resIdList.contains(resId)){
					continue;
				}
			}
			if(null != leaderNameList && leaderNameList.size()>0){
				if(!leaderNameList.contains(leaderName)){
					continue;
				}
			}
			//TODO 判断日程类别负责人类别
	        if(  null != objs[13]) {
	        	if((Integer)objs[13]!=taskKind){
	        		continue;
	        	}
	        	
			}
			list.add(obj);
		}
		/***存放，columns，rows ，存放最终返回值*/
		Map<String,Object> map = new HashMap<String,Object>();
		//存放  field：data  列表
		List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
		Map<String,String> rowMap = null;
		//列list（列 columns）
		List<List<Columns>> columnsList= new ArrayList<List<Columns>>();
		//任务编号Set，
		Set<String> taskCodeSet = new HashSet<String>();
		//任务编号任务名称Set
		Set<String> taskCodeTaskNameSet = new HashSet<String>();
		Map<String,String> taskCodeTaskNameMap = new HashMap<String,String>();
		
		if(null !=list && list.size()>0){
			for(Object obj:list){
				Object[] objs= (Object[]) obj;
				rowMap = new HashMap<String,String>();
				String taskCode = (String) objs[2];
				String taskName = (String) objs[3];
				String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
				taskCodeSet.add(taskCode);
				taskCodeTaskNameSet.add(taskCode+"T"+taskNamePinyin);
				taskCodeTaskNameMap.put(taskCode+"T"+taskNamePinyin, taskName);
			}
			for(String str :dateStrList){
				rowMap = new HashMap<String,String>();
				String  weekDay = DateUtil.getWeekDay(str, "yyyy-MM-dd"); 
				for(Object obj:list){
					Object[] objs= (Object[]) obj;
					Date date = (Date) objs[0];
					String taskCode = (String) objs[2];
					String taskName = (String) objs[3];
					
					String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
					
					String taskCodeTaskName = taskCode+"T"+taskNamePinyin;
					String dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
					String leaderName = (String) objs[10];
					Integer isFinish = (Integer) objs[12];
					if(isFinish == 1){
						leaderName ="<a style='color:blue;'>"+leaderName+"</a>";
					}else{
						leaderName ="<a style='color:red;'>"+leaderName+"</a>";
					}
					if(str.equals(dateStr)){
//						<a style=''>"+testData.getTestData()+"</a>
						if(rowMap.keySet().contains(taskCodeTaskName)){
							if(!rowMap.get(taskCodeTaskName).contains(leaderName)){
								rowMap.put(taskCodeTaskName, rowMap.get(taskCodeTaskName)+","+leaderName);
							}
						}else{
							rowMap.put(taskCodeTaskName, leaderName);
						}
					}
				}
				
				//TODO 去除多余的 <a style='color:red;'>"+"\\[未指定\\]"+"</a>";
				Set<String> tempTaskCodeTaskNameSet = rowMap.keySet();
				//待修改数据
				Map<String,String> editMap = new HashMap<String,String>();
				if(null != tempTaskCodeTaskNameSet){
					for(String temptaskCodeTaskName : tempTaskCodeTaskNameSet){
						String tempLeaderName = rowMap.get(temptaskCodeTaskName);
						if(null != tempLeaderName && ( tempLeaderName.contains(",<a style='color:red;'>[未指定]</a>")
								|| tempLeaderName.contains("<a style='color:red;'>[未指定]</a>,") )){
							tempLeaderName = tempLeaderName.replaceAll(",<a style='color:red;'>\\[未指定\\]</a>", "");
							tempLeaderName = tempLeaderName.replaceAll("<a style='color:red;'>\\[未指定\\]</a>,", "");
							//rowMap.put(temptaskCodeTaskName, tempLeaderName);
							editMap.put(temptaskCodeTaskName, tempLeaderName);
						}
					}
				}
				//开始去除
				Set<String> tempEditKeySet = editMap.keySet();
				if(null != tempEditKeySet){
					for(String temptaskCodeTaskName : tempEditKeySet){
						String tempLeaderName = editMap.get(temptaskCodeTaskName);
						rowMap.put(temptaskCodeTaskName, tempLeaderName);
					}
				}
				
				
				
				
				
				
				if(isAllDate){
					rowMap.put("dateCol", str+"("+weekDay+")");
					rowsList.add(rowMap);
				}else{
					if(rowMap.keySet().size()>0){
						rowMap.put("dateCol", str+"("+weekDay+")");
						rowsList.add(rowMap);
					}
				}
			}
			columnsList =getColumnsList(taskCodeSet,taskCodeTaskNameSet,taskCodeTaskNameMap);
		}
		map.put("columns", columnsList);
		map.put("rows", rowsList);
		return map;
	}

	public Map<String, Object> getOneDateScheduleLeaderNamebyDateTaskKindCodeTypeTaskType(
			Date startDate,Date endDate,int taskKind,int codeType,int taskType,boolean isAllDate
			,List<String> studyNoList ,List<String> resIdList ,List<String> taskNameList,List<String> leaderNameList) {
		Query query = getSession().createSQLQuery("{Call getScheduleLeaderNamebyDateTaskKindCodeTypeTaskTypeNew(?,?,?,?,?)}");
		query.setParameter(0, startDate );
		query.setParameter(1, startDate);
		query.setParameter(2, taskKind);
		query.setParameter(3, codeType);
		query.setParameter(4, taskType);
		List<?> datalist =query.list();
		
		//开始日期>结束日期
		if(startDate.after(endDate)){
			return new HashMap<String,Object>();
		}
		List<String> dateStrList = new ArrayList<String>();
		while(!startDate.after(endDate)){
			dateStrList.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
			startDate = DateUtil.AddDate(startDate, 1);
		}
		//过滤后存放于list
		List<Object> list = new ArrayList<Object>();
		for(Object obj:datalist){
			Object[] objs = (Object[]) obj;
			String taskCode = (String) objs[2];
			String taskName = (String) objs[3];
			String resId = (String) objs[7];
			String leaderName = (String) objs[10];
			if(null != studyNoList && studyNoList.size()>0){
				if(!studyNoList.contains(taskCode)){
					continue;
				}
			}
			if(null != taskNameList && taskNameList.size()>0){
				if(!taskNameList.contains(taskName)){
					continue;
				}
			}
			if(null != resIdList && resIdList.size()>0){
				if(!resIdList.contains(resId)){
					continue;
				}
			}
			if(null != leaderNameList && leaderNameList.size()>0){
				if(!leaderNameList.contains(leaderName)){
					continue;
				}
			}
			//TODO 判断日程类别负责人类别
	        if(  null != objs[13]) {
	        	if((Integer)objs[13]!=taskKind){
	        		continue;
	        	}
	        	
			}
			list.add(obj);
		}
		
		
		
		
		/***存放，columns，rows ，存放最终返回值*/
		Map<String,Object> map = new HashMap<String,Object>();
		//存放  field：taskName  列表
		List<Map<String,String>> rowsList = new ArrayList<Map<String,String>>();
		Map<String, String> rowMap = null;
		//列list（列 columns）
		List<List<Columns>>  columnsList= new ArrayList<List<Columns>>();
		//任务编号Set，
		Set<String> taskCodeSet = new HashSet<String>();
		//任务名称
		Set<String> taskNameSet = new HashSet<String>();
		//  任务名称：名称拼音
		Map<String,String> taskNameMap = new HashMap<String,String>();
		if(null != list && list.size() > 0){
			for(Object obj:list){
				Object[] objs= (Object[]) obj;
				String taskCode = (String) objs[2];
				String taskName = (String) objs[3];
				String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
				//String leaderName = (String) objs[10];
				taskCodeSet.add(taskCode);//任务编号
				taskNameSet.add(taskName);
				taskNameMap.put(taskName,taskNamePinyin);
			}
			for (String str: taskCodeSet) {
				rowMap = new HashMap<String,String>();
				for(Object obj:list){
					Object[] objs= (Object[]) obj;
					String taskCode = (String) objs[2];
					String taskName = (String) objs[3];
					String taskNamePinyin = Trans2PinYin.trans2PinYin(taskName);
					
					String leaderName = (String) objs[10];
					Integer isFinish = (Integer) objs[12];
					//TODO 判断日程类别负责人类别
			        if(  null != objs[13]) {
			        	if((Integer)objs[13]!=taskKind){
			        		continue;
			        	}
			        	
					}
					if(isFinish == 1){
						leaderName ="<a style='color:blue;'>"+leaderName+"</a>";
					}else{
						leaderName ="<a style='color:red;'>"+leaderName+"</a>";
					}
					if(str.equals(taskCode)){
						if(rowMap.keySet().contains(taskNamePinyin)){
							if(!rowMap.get(taskNamePinyin).contains(leaderName)){
								rowMap.put(taskNamePinyin, rowMap.get(taskNamePinyin)+","+leaderName);
							}
						}else{
							rowMap.put(taskNamePinyin, leaderName);
						}
					}
				}
				rowMap.put("taskCode",str);
				
				//TODO 去除多余的 <a style='color:red;'>"+"[未指定]"+"</a>";
				Set<String> tempTaskCodeTaskNameSet = rowMap.keySet();
				//待修改数据
				Map<String,String> editMap = new HashMap<String,String>();
				if(null != tempTaskCodeTaskNameSet){
					for(String temptaskCodeTaskName : tempTaskCodeTaskNameSet){
						String tempLeaderName = rowMap.get(temptaskCodeTaskName);
						if(null != tempLeaderName && ( tempLeaderName.contains(",<a style='color:red;'>[未指定]</a>")
								|| tempLeaderName.contains("<a style='color:red;'>[未指定]</a>,") )){
							tempLeaderName = tempLeaderName.replaceAll(",<a style='color:red;'>\\[未指定\\]</a>", "");
							tempLeaderName = tempLeaderName.replaceAll("<a style='color:red;'>\\[未指定\\]</a>,", "");
							//rowMap.put(temptaskCodeTaskName, tempLeaderName);
							editMap.put(temptaskCodeTaskName, tempLeaderName);
						}
					}
				}
				//开始去除
				Set<String> tempEditKeySet = editMap.keySet();
				if(null != tempEditKeySet){
					for(String temptaskCodeTaskName : tempEditKeySet){
						String tempLeaderName = editMap.get(temptaskCodeTaskName);
						rowMap.put(temptaskCodeTaskName, tempLeaderName);
						System.out.println(temptaskCodeTaskName);
					}
				}
				
				
				
				
				rowsList.add(rowMap);
			}
			columnsList =getOneDateColumnsList(taskNameSet,taskNameMap);
		}
		
		
		
		map.put("columns", columnsList);
		map.put("rows", rowsList);
		return map;
	}
	
	/**获得 columnsList
	 * @param taskCodeSet     
	 * @param taskCodeTaskNameSet
	 * @return
	 */
	private List<List<Columns>> getOneDateColumnsList(Set<String> taskNameSet,
			Map<String,String> taskNameMap) {
		
		List<List<Columns>> columnsList= new ArrayList<List<Columns>>();
		List<Columns> list = new ArrayList<Columns>();
		//Columns column = new Columns();
		//column.setField("taskCode");
		//column.setTitle("专题编号");
		//list.add(column);
			for(String str:taskNameSet){
				Columns column = new Columns();
					column.setTitle(str);
					column.setField(taskNameMap.get(str));
					list.add(column);
			}
		columnsList.add(list);
		return columnsList;
		
		
	}

	public List<?> getAllByTimeschedulePlanList(Date startTime,
			Date endTime,String taskKind) {
//		List<TblSchedulePlan> TblSchedulePlanList = getSession().createQuery("FROM TblSchedulePlan t WHERE  " +
//		" ? <= t.endTime ").setParameter(0, startTime).list();
		String sql=" " +
				"select distinct tss.taskCode from " +
			" (select  tsp.taskCode,tsp.startTime,tsp.endTime,tsp.taskName,ttp.id, ttp.taskKind from CoresSchedule.dbo.tblSchedulePlan as tsp" +
			" left join CoresSystemSet.dbo.tblTaskType as ttp on tsp.taskName = ttp.taskName " +
			"  and( tsp.endTime  between :startTime and  :endTime" +
			"  or tsp.startTime  between :startTime and  :endTime" +
			"  or (tsp.startTime  < :startTime  and  :endTime< tsp.endTime)" +
			"  ) and tsp.validFlag = 1 " +
			" ) as tss left join " +
			"  CoresSystemSet.dbo.tblTaskTypeField  as ttf  " +
			"  on ttf.tttId = tss.id where  tss.taskKind = :taskKind or ttf.taskKind2 = :taskKind ";
       List<?> list= getSession().createSQLQuery(sql).setParameter("startTime", startTime).setParameter("endTime", endTime)
       .setParameter("taskKind", taskKind)
       .list();
       return list;
	}


	@SuppressWarnings("unchecked")
	public List<String> getTaskNameNoInTaskType(String taskKind) {
		 String sql="SELECT  distinct  a.[taskName] FROM [CoresSchedule].[dbo].[tblSchedulePlan] as a " +
		 		"WHERE a.[taskName]  not in (  SELECT b.[taskName] FROM  [CoresSystemSet].[dbo].[tblTaskType] as b ) and  a.[taskKind] = ?";
		 List<String> list= getSession().createSQLQuery(sql).setParameter(0, taskKind).list();
		 return list;
	}

	public List<?> getStudyNoRes(Date startime, Date endtime,int isSelectAllStudy) {
		String sql = "select study.studyNo,house.resName,(case when sr.state is null then -1 else sr.state end )as state,es.signer,sr.resId"+
					" from ("+
					" select distinct study.studyNo from"+
					" CoresSchedule.dbo.tblSchedulePlan as schedule left join CoresStudy.dbo.tblStudyPlan as study " +
					" on schedule.taskCode = study.studyNo and schedule.codeType =2"+
					" where  study.studyStartDate between :startime and :endtime and schedule.signId is not null and schedule.signId !=''"+
					" )as study" +
					" left join  CoresSchedule.dbo.tblStudyRes as sr "+
					" on study.studyNo = sr.studyNo left join "+
					" CoresSystemSet.dbo.tblAnimalHouse as house on sr.resId = house.id left join "+
					" CoresUserPrivilege.dbo.tblES as es on sr.auditId = es.esId "+
					" where 1=1 ";
		String orderby = " order by study.studyNo";
		
		sql = sql + orderby;
		List<?> list = getSession().createSQLQuery(sql)
						.setParameter("startime", startime)
						.setParameter("endtime", endtime)
						.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<String> getTblSchedulePlantaskCode(Date startDate , Date endDate) {
		List<String> taskCodelist= getSession().createSQLQuery("SELECT distinct  taskCode  FROM " +
				"[CoresSchedule].[dbo].[tblSchedulePlan]  as a  left join [CoresStudy].[dbo].[tblStudyPlan]  as b " +
				" ON  a.taskCode = b.studyNo  WHERE a.signId is not null and(  b.studyBeginDate between ? and ?" +
				"  )group by a.taskCode ").setParameter(0, startDate).setParameter(1, endDate).list();
		return taskCodelist;
	}

	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan> getHasSubmitSchedulePlan(int taskType,String StudyNo,int CodeType) {
		Query query = getSession().createSQLQuery("{Call getHasSubmitSchedulePlan(?,?,?)}");
		query.setParameter(0, taskType );
		query.setParameter(1, StudyNo);
		query.setParameter(2, CodeType);
		List list =query.list(); 
		List<TblSchedulePlan> tblSchedulePlanlist = new ArrayList<TblSchedulePlan>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblSchedulePlan tblSchedulePlan = new TblSchedulePlan();
				Object[] objs = (Object[]) list.get(i);
				String dateStr = new SimpleDateFormat("yyyy-MM-dd").format((Date)objs[0]);
				tblSchedulePlan.setDateTime(dateStr);
				String taskName = (String) objs[1];
				tblSchedulePlan.setTaskName(taskName);
				tblSchedulePlan.setPeriodUnit((Integer)objs[3]);
				tblSchedulePlan.setPeriod((Integer)objs[2]);
				tblSchedulePlan.setDateTimeDate((Date)objs[0]);
				tblSchedulePlanlist.add(tblSchedulePlan);
			}
			
		}
		return tblSchedulePlanlist;
	}
	@SuppressWarnings("unchecked")
	public List<TblSchedulePlan_Json> getHasSubmitSchedulePlanJson(int taskType,String StudyNo,int CodeType) {
		Query query = getSession().createSQLQuery("{Call getHasSubmitSchedulePlan2(?,?,?)}");//已经提交的日程
		query.setParameter(0, taskType );
		query.setParameter(1, StudyNo);
		query.setParameter(2, CodeType);
		List<Object[]> list =query.list(); 
		List<TblSchedulePlan_Json> tblSchedulePlanlist = new ArrayList<TblSchedulePlan_Json>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblSchedulePlan_Json tblSchedulePlan = new TblSchedulePlan_Json();
				Object[] objs = (Object[]) list.get(i);
				String dateStr = new SimpleDateFormat("yyyy-MM-dd").format((Date)objs[0]);
				tblSchedulePlan.setDateTime(dateStr);
				String taskName = (String) objs[1];
				tblSchedulePlan.setTaskName(taskName);
				tblSchedulePlan.setPeriodUnit((Integer)objs[3]);
				tblSchedulePlan.setPeriod((Integer)objs[2]);
				tblSchedulePlan.setDateTimeDate((Date)objs[0]);
				tblSchedulePlan.setNumber((Integer)objs[4]);//第几次执行
				tblSchedulePlan.setScheduleID((String)objs[5]);
				
				tblSchedulePlanlist.add(tblSchedulePlan);
			}
			
		}
		return tblSchedulePlanlist;
	}

	public void submitSchedulePlanByTaskCode(String studyNo, String signid) {
		String sql = " update    tblSchedulePlan set validFlag = 1,signId = :signid "+
                     " WHERE   (taskType = 2) AND (codeType = 2) AND (validFlag = 0) AND (signId IS NULL) "+
                     " and taskCode = :studyNo ";
        getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).setParameter("signid", signid).executeUpdate();
		
	}
	
	public void updateSchedulePlanValidFlagByTaskCode(String studyNo) {
		String sql = " update  tblSchedulePlan set validFlag = 1 "+
                     " WHERE   validFlag = 0 "+
                     " and taskCode = :studyNo ";
        getSession().createSQLQuery(sql).setParameter("studyNo", studyNo).executeUpdate();
		
	}

}
