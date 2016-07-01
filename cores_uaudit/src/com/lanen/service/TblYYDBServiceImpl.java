package com.lanen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.util.DateUtil;

@Service
@Transactional
public class TblYYDBServiceImpl implements TblYYDBService{

	@Resource
	private SessionFactory sessionFactory;

	public List<Map<String, Object>> getYYDBLogList(String operatType,
			String beginDate, String endDate) {
		
		if(null==beginDate || null==endDate || "".equals(beginDate) || "".equals(endDate)){
			beginDate= DateUtil.getDateAgo(6);
			endDate =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		}
		
//		Date dateFrom = DateUtil.stringToDate(beginDate, "yyyy-MM-dd");
//		Date dateTo = DateUtil.stringToDate(endDate, "yyyy-MM-dd");
		String dateFrom = beginDate;
		String dateTo = endDate;
		
//		field :'id',
//		field:'operatOject',
//		field:'operator',
//		field:'operatTime',
//		field:'operatContent',
//		field:'remark',
		List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		if(null == operatType || "".equals(operatType)){
			Long t = System.currentTimeMillis();
			System.out.println(t);
			List<?> list = getSystemLog("ALL","",dateFrom,dateTo);
			Long t2 = System.currentTimeMillis();
			System.out.println(t2-t);
			int i = 1;
			if(null != list && list.size() > 0){
				for(Object obj:list){
					Object[] objs = (Object[]) obj;
					String logFlag = (String) objs[1];
					if(logFlag.equals("LOGIN") || logFlag.equals("OTHER")){
//					String studyNo = (String) objs[0];
						String operatContent = (String) objs[2];
						Date operatTime = (Date) objs[4];
						String operator = (String) objs[5];
						map = new HashMap<String,Object>();
						map.put("id", i+"");
						if(logFlag.equals("LOGIN")){
							map.put("operatType", "登录");
						}else{
							map.put("operatType", "其他");
						}
						map.put("operatOject", "一般毒理系统");
						map.put("operator",operator);
						map.put("operatTime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
						map.put("operatContent",operatContent);
						map.put("remark","");
						
						maplist.add(map);
						
						i++;
					}
				}
			}
			Long t3 = System.currentTimeMillis();
			System.out.println(t3-t2);
		}else{
				List<?> list = getSystemLog(operatType,"",dateFrom,dateTo);
				int i = 1;
				if(null != list && list.size() > 0){
					for(Object obj:list){
						Object[] objs = (Object[]) obj;
//					String studyNo = (String) objs[0];
						String logFlag = (String) objs[1];
						String operatContent = (String) objs[2];
						Date operatTime = (Date) objs[4];
						String operator = (String) objs[5];
						map = new HashMap<String,Object>();
						map.put("id", i+"");
						if(logFlag.equals("LOGIN")){
							map.put("operatType", "登录");
						}else{
							map.put("operatType", "其他");
						}
						map.put("operatOject", "一般毒理系统");
						map.put("operator",operator);
						map.put("operatTime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
						map.put("operatContent",operatContent);
						map.put("remark","");
						
						maplist.add(map);
						
						i++;
					}
				}
		}
		return maplist;
	}
	
	/**执行存储过程 yydb.dbo.GetSystemLog
	 * @return
	 */
	private List<?> getSystemLog(String logType,String testcode,String dateFrom ,String dateTo){
		
		String sql = "{call yydb.dbo.GetSystemLog(?,?,7,?,?,'','')}";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
									.setParameter(0, logType)
									.setParameter(1, testcode)
									.setParameter(2, dateFrom)
									.setParameter(3, dateTo)
									.list();
		return list;
	}

	public List<Map<String, Object>> getStudyLogList(String studyNo,
			String operateType) {
		
		//1.准备数据
		if(null == operateType || "".equals(operateType)){
			operateType = "ALL";
		}
		String dateFrom = "1972-01-01";
		String dateTo = DateUtil.getNow("yyyy-MM-dd");
		
		List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		//2.查询及整理数据
//		 <option value="ALL">全部</option>   
//	    <option value="TESTCODE">专题信息</option>   
//	    <option value="ANIMAL">动物分组</option>   
//	    <option value="WEIGHT">体重称重</option>   
//	    <option value="FOOD">摄食</option>   
//	    <option value="TESTOTHER">其他</option>   
		if(operateType.equals("ALL")){
			List<?> list_1 = getSystemLog("TESTCODE",studyNo,dateFrom,dateTo);
			if(null != list_1 && list_1.size() > 0){
				for(Object obj:list_1){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					map.put("operatType", "专题信息");
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
			List<?> list_2 = getSystemLog("ANIMAL",studyNo,dateFrom,dateTo);
			if(null != list_2 && list_2.size() > 0){
				for(Object obj:list_2){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					map.put("operatType", "动物分组");
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
			List<?> list_3 = getSystemLog("WEIGHT",studyNo,dateFrom,dateTo);
			if(null != list_3 && list_3.size() > 0){
				for(Object obj:list_3){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					map.put("operatType", "体重称重");
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
			List<?> list_4 = getSystemLog("FOOD",studyNo,dateFrom,dateTo);
			if(null != list_4 && list_4.size() > 0){
				for(Object obj:list_4){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					map.put("operatType", "摄食");
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
			List<?> list_5 = getSystemLog("TESTOTHER",studyNo,dateFrom,dateTo);
			if(null != list_5 && list_5.size() > 0){
				for(Object obj:list_5){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					map.put("operatType", "其他");
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
		}else{
			List<?> list = getSystemLog(operateType,studyNo,dateFrom,dateTo);
			
			if(null != list && list.size() > 0){
				String logFlag ="";
				if(operateType.equals("TESTCODE") ){
					logFlag  = "专题信息";
				}else if(operateType.equals("ANIMAL") ){
					logFlag = "动物分组";
				}else if(operateType.equals("WEIGHT") ){
					logFlag = "体重称重";
				}else if(operateType.equals("FOOD") ){
					logFlag = "摄食";
				}else if(operateType.equals("TESTOTHER") ){
					logFlag = "其他";
				}
				for(Object obj:list){
					map = new HashMap<String,Object>();
					Object[] objs = (Object[]) obj;
//					String logFlag = (String) objs[1];
					String testcode = (String) objs[0];
					String operatContent = (String) objs[2];
					Date operatTime = (Date) objs[4];
					String operator = (String) objs[5];
					         
					map.put("operatType",logFlag);
					map.put("testcode",testcode);
					map.put("logdesc",operatContent);
					map.put("operator",operator);
					map.put("logtime",DateUtil.dateToString(operatTime, "yyyy-MM-dd HH:mm:ss"));
					
					maplist.add(map);
					
				}
			}
		}
		return maplist;
	}

	public List<Map<String, Object>> loadDataTrace(String studyNo) {
		List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		//2.查询及整理数据
		List<?> list = getRawDataTraceInfo(studyNo);
		if(null != list && list.size() > 0){
			for(Object obj:list){
				map = new HashMap<String,Object>();
				Object[] objs = (Object[]) obj;
				
				String testcode = (String) objs[1];
				String animals = (String) objs[2];
				String datatype = (String) objs[3];
				String olddata = (String) objs[4];
				String newdata = (String) objs[5];
				String rawdatatime = (String) objs[6];
				String datachangersn = (String) objs[7];
				Date datachangetime = (Date) objs[8];
				String operator = (String) objs[9];
				map.put("testcode",testcode);
				map.put("animals",animals);
				map.put("datatype",datatype);
				map.put("olddata",olddata);
				map.put("newdata",newdata);
				map.put("rawdatatime",rawdatatime);
				map.put("datachangersn",datachangersn);
				map.put("datachangetime",DateUtil.dateToString(datachangetime, "yyyy-MM-dd HH:mm:ss"));
				map.put("operator",operator);
				
				maplist.add(map);
				
			}
		}
		return maplist;
	}
	/**执行存储过程 yydb.dbo.GetRawDataTraceInfo
	 * @return
	 */
	private List<?> getRawDataTraceInfo(String studyNo){
		
		String sql = "{call yydb.dbo.GetRawDataTraceInfo(?)}";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
									.setParameter(0, studyNo)
									.list();
		return list;
	}
	
}
